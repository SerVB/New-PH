/*
 * MIT License
 *
 * Copyright (c) 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package Game;

import Common.iArmy;
import entries.iMeleeEntry;
import Common.iFurtSkills;
import Common.iPoint;
import utils.tracer;
import Constants.*;
import ConstantsGame.*;
import collections.simple.iDistMap;
import collections.simple.iMeleeList;
import collections.simple.iShotList;
import collections.simple.iSpellList;
import entries.iShootEntry;
import entries.iSpellEntry;

/**
 *
 */
public class iBattleGroup {

    // c-tor
    public iBattleGroup(
            final iCreatGroup cg,
            int idx,
            iBattleMember pOwner,
            iBattleWrapper pWrapper,
            int moraleModifier
    ) {
        this.m_creatType = cg.Type();
        this.m_initCount = cg.Count();
        this.m_creatCount = cg.Count();
        this.m_idx = idx;
        this.m_bWaited = false;
        this.m_toDefend = 0;
        this.m_spImmunity = 0;
        this.m_retails = 0;
        this.m_bMoraleAttack = false;
        this.m_bInMoat = false;
        this.m_roundMorale = MLM.NEUTRAL;
        this.m_bBlinded = false;
        this.m_retPenalty = 1;
        this.m_blessState = 0;
        this.m_hitPoints = CREAT.DESC[cg.Type()].hits + pOwner.FurtSkills().Value(FSK.HITS);
        this.m_shots = CREAT.DESC[cg.Type()].shots;
        this.m_Pos = cInvalidPoint;
        this.m_State = STATE.Idle;
        this.m_pOwner = pOwner;
        this.m_pWrapper = pWrapper;
        this.m_Orient = pOwner.GetSide();
        this.m_Size = CREAT.DESC[cg.Type()].size;
        this.m_passMap = new Map(13, 11);
        this.m_distMap = new Map(13, 11);
        this.m_casualties = 0;
        this.m_furtSkills = pOwner.FurtSkills();

        // special creatures processing
        if ((CREAT.DESC[m_creatType].perks & CPERK.PROCRESIST40) != 0)
            m_furtSkills.plusValue(FSK.RESIST, 40);

        m_furtSkills.plusValue(FSK.MORALE, moraleModifier);

        NewRound();
    }

    // Grop specs
    public final iBattleMember Owner() {
        return m_pOwner;
    }

    public final int Type() {
        return m_creatType;
    }

    public final long Count() {
        return m_creatCount;
    }

    public final int ArmyGroupIndex() {
        return m_idx;
    }

    public final int Orient() {
        return m_Orient;
    }

    public final int State() {
        return m_State;
    }

    public final boolean IsAlive() {
        return m_State != STATE.Dead;
    }

    public final boolean CanShoot() {
        return CREAT.DESC[m_creatType].shots != 0;
    }

    public final int Shots() {
        return m_shots;
    }

    public final int HitPointsLeft() {
        return m_hitPoints;
    }

    public final int HitPoints() {
        return CREAT.DESC[m_creatType].hits + m_furtSkills.Value(FSK.HITS);
    }

    public final int TotalCasHitPoints() {
        return Math.max(0, m_initCount * HitPoints() - TotalHitPoints());
    }

    public final int TotalHitPoints() {
        return HitPoints() * (m_creatCount - 1) + m_hitPoints;
    }

    public final int AttackSkill() {
        return Math.max(1, CREAT.DESC[m_creatType].attack + m_furtSkills.Value(FSK.ATTACK));
    }

    public final int DefenceSkill() {
        return Math.max(1, CREAT.DESC[m_creatType].defence + m_furtSkills.Value(FSK.DEFENCE));
    }

    public final boolean CanWait() {
        return !m_bWaited;
    }

    public final boolean CanAct() {
        return !m_bBlinded;
    }

    public final boolean CanRetail() {
        return (
                !m_bBlinded || m_retPenalty!=0) &&
                ((CREAT.DESC[m_creatType].perks & CPERK.RETALTOALL) != 0 ||
                 m_retails < (1 + m_furtSkills.Value(FSK.COUNTERSTRIKE)) );
    }

    public void SetRetailed() {
        m_retails++;
    }

    public final int Size() {
        return m_Size;
    }

    public final int Morale() {
        int countedMorale = (CREAT.DESC[m_creatType].perks &  CPERK.UNDEAD) != 0 ? 0 : m_furtSkills.Value(FSK.MORALE);

        return Math.max(-3, Math.min(3, countedMorale));

    }

    public final int Luck() {
        return Math.max(-3, Math.min(3, m_furtSkills.Value(FSK.LUCK)));
    }

    public final int RoundMorale() {
        return m_roundMorale;
    }

    public void ResetRoundMorale() {
        m_roundMorale = MLM.NEUTRAL;
    }

    public final int CalcLuck() {
        int lm = Luck();
        int rval = gGame.Map().Rand(100);
        if (lm > 0 && (lm*5 > rval)) {
            return MLM.POSITIVE;
        } else if (lm < 0 && ((-lm)*5 > rval)) {
            return MLM.NEGATIVE;
        } else {
            return MLM.NEUTRAL;
        }
    }

    // Common creature abilities
    public final int TransType() {
        return CREAT.DESC[m_creatType].transType;
    }

    public final int Perks() {
        return CREAT.DESC[m_creatType].perks;
    }

    public final boolean HasPerk(int perk) {
        return (CREAT.DESC[m_creatType].perks & perk) != 0;
    }

    public final int Speed() {
        return Math.max(1, CREAT.DESC[m_creatType].speed + m_furtSkills.Value(FSK.SPEED));
    }

    public final int MinDamage() {
        return CREAT.DESC[m_creatType].damage_min;
    }

    public final int MaxDamage() {
        return CREAT.DESC[m_creatType].damage_max;
    }

    public final int ActMinDamage() {
        return (m_blessState>0) ? CREAT.DESC[m_creatType].damage_max : CREAT.DESC[m_creatType].damage_min;
    }

    public final int ActMaxDamage() {
        return (m_blessState<0) ? CREAT.DESC[m_creatType].damage_min : CREAT.DESC[m_creatType].damage_max;
    }

    // Dislocation helpers
    public final boolean IsGroupCell(final iPoint pos) {
        return m_Pos == pos || (m_Size==2 && TailPos() == pos);
    }
    public final iPoint Pos() {
        return m_Pos;
    }

    public final iPoint TailPos() {
        if (m_Size == 1)
            return m_Pos;
        return new iPoint(m_Pos.x + TAIL.OFFSET[m_Orient], m_Pos.y);
    }

    // Moat
    public final boolean InMoat() {
        return m_bInMoat;
    }

    public void EnterMoat() {
        tracer.check(!m_bInMoat);
        m_bInMoat = true;
        m_furtSkills.minusValue(FSK.DEFENCE, 3);
    }

    public void LeaveMoat() {
        tracer.check(m_bInMoat);
        m_bInMoat = false;
        m_furtSkills.plusValue(FSK.DEFENCE, 3);
    }

    // Spells
    public void AddSpell(final iCombatSpell pSpell, int rounds, int param) {
        m_spells.add(new iSpellEntry(pSpell, rounds, param));
    }

    public void RemoveSpell(int spell) {
        for (int xx = 0; xx < m_spells.size();) {
            if (m_spells.get(xx).m_pSpell.Id() == spell) {
                m_spells.get(xx).m_pSpell.OnRemove(this, m_spells.get(xx).m_param);
                m_spells.remove(xx);
            } else {
                ++xx;
            }
        }
    }

    public void RemoveSpellAt(int idx) {
        m_spells.get(idx).m_pSpell.OnRemove(this, m_spells.get(idx).m_param);
        m_spells.remove(idx);
    }

    public final iSpellList SpellList() {
        return m_spells;
    }

    public final int SpellsDispos() {
        int positive = 0;
        int negative = 0;
        for (int xx = 0; xx < m_spells.size() && (positive == 0 || negative == 0); ++xx) {
            if (m_spells.get(xx).m_pSpell.SpellDispos() == SPD.POSITIVE) {
                ++positive;
            } else if (m_spells.get(xx).m_pSpell.SpellDispos() == SPD.NEGATIVE) {
                ++negative;
            }
        }

        if (positive != 0 && negative != 0) {
            return SPD.NEUTRAL;
        } else if (positive != 0) {
            return SPD.POSITIVE;
        } else if (negative != 0) {
            return SPD.NEGATIVE;
        } else {
            return SPD.NONE;
        }
    }

    public void SetSpellImmunity(int spim) {
        m_spImmunity = spim;
    }

    public final int SpellImmunity() {
        return m_spImmunity;
    }

    public final boolean SpellImmunity(int splevel) {
        return (m_spImmunity & (1 << splevel)) != 0;
    }

    // Bless / Curse state
    public final int BlessState() {
        return m_blessState;
    }

    public void SetBlessState(int newState) {
        m_blessState = newState;
    }

    // Blind state
    public void SetBlindState(boolean bBlinded, int penalty) {
        m_bBlinded = bBlinded;
        m_retPenalty = penalty;
    }

    public final boolean IsBlinded() {
        return m_bBlinded;
    }

    public final int GetRetPenalty() {
        return m_retPenalty;
    }

    //
    public void NewRound() {
        m_bWaited = false;
        m_retails = 0;

        // Defend
        if (m_toDefend != 0) {
            m_furtSkills.minusValue(FSK.DEFENCE, m_toDefend);
            m_toDefend = 0;
        }

        // Calculate morale state
        int mm = Morale();
        int rval = gGame.Map().Rand(100);
        if (mm > 0 && (mm*5 > rval)) {
            m_roundMorale = MLM.POSITIVE;
        } else if (mm < 0 && ((-mm)*5 > rval)) {
            m_roundMorale = MLM.NEGATIVE;
        } else {
            m_roundMorale = MLM.NEUTRAL;
        }

        // Process special perks
        if ((CREAT.DESC[m_creatType].perks & CPERK.REGENERATES) != 0) {
            m_hitPoints = HitPoints();
        }

        // Update spells
        for (int xx = 0; xx < m_spells.size();) {
            switch (m_spells.get(xx).m_duration) {
                case DurationType.DurInfinit:
                    ++xx;
                    break;
                case 1:
                    m_spells.get(xx).m_pSpell.OnRemove(this, m_spells.get(xx).m_param);
                    m_spells.remove(xx);
                    //OutputDebugString(_T("Remove spell!\n"));
                    break;
                default:
                    m_spells.get(xx).m_duration--;
                    ++xx;
                    //OutputDebugString(_T("Decrease spell duration.\n"));
                    break;
            }
        }
    }

    public void DestroyGroup() {
        m_casualties += m_creatCount;
        m_creatCount = 0;
        m_hitPoints = 0;
        SetState(STATE.Dead);
    }

    public void Wait() {
        tracer.check(!m_bWaited);
        m_bWaited = true;
    }

    public void Defend() {
        tracer.check(m_toDefend == 0);
        m_toDefend = DefenceSkill() >> 2;
        m_furtSkills.plusValue(FSK.DEFENCE, m_toDefend);
    }

    public void GoodMorale() {
        m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_GOOD_MORALE], gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
        m_pWrapper.AddCellEvent(String.format("#I%04X", PDGG.ICN_MORALE), m_Pos);
    }

    public void BadMorale() {
        m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_BAD_MORALE], gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
        m_pWrapper.AddCellEvent(String.format("#I%04X", PDGG.ICN_MORALE+2), m_Pos);
    }

    public void GoodLuck() {
        m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_GOOD_LUCK], gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
        m_pWrapper.AddCellEvent(String.format("#I%04X", PDGG.ICN_LUCK), m_Pos);
    }

    public void BadLuck() {
        m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_BAD_LUCK], gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
        m_pWrapper.AddCellEvent(String.format("#I%04X", PDGG.ICN_LUCK+2), m_Pos);
    }

    public final int CalcJoustingBonus(final iBattleGroup pTarget) {
        if ((CREAT.DESC[m_creatType].perks & CPERK.JOUSTING) != 0) {
//            return iCLAMP<sint32>(0, 50, (BattleCellsDelta(m_Pos.x, m_Pos.y, pTarget.Pos().x, pTarget.Pos().y)-1)*5);
            return Math.max(0, Math.min(50, (BattleCellsDelta(m_Pos.x, m_Pos.y, pTarget.Pos().x, pTarget.Pos().y)-1)*5));
        }
        return 0;
    }

    public final int CalcDamage(final iBattleGroup pTarget, int damage, boolean bRange, int penalty, int joustBonus) {
        double udmg = (double)damage;

        // NB: Replace with fix-point but beware 64K overflow!!
        //////////////////////////////////////////////////////////////

        double adif = (double)( AttackSkill() - pTarget.DefenceSkill() );
        // Increase melle attack skill according to Melee attack skill
        if (bRange) {
            adif += m_furtSkills.Value(FSK.RANGEATTACK);
        } else {
            adif += m_furtSkills.Value(FSK.MELEEATTACK);
        }

        if (adif >= 0) {
            damage = Math.max(1, (int)(udmg * (1.0f + 0.1f*Math.min(adif,20.0f)) * m_creatCount));
        } else {
            damage = Math.max(1, (int)(udmg * (1.0f + 0.05f*Math.max(adif,-16.0f)) * m_creatCount));
        }

        // Check half damage cases (far range attack and melee for shooters w/o NO_MELEE_PENALTY perk)
        if (bRange && penalty > 1) {
            damage = Math.max(damage/penalty, 1);
        } else if (!bRange && CREAT.DESC[m_creatType].shots != 0 && (CREAT.DESC[m_creatType].perks & CPERK.NOMELEEPENALTY) == 0) {
            damage = Math.max(damage/2, 1);
        }

        // Increase melee damage according to Offence skill
        if (!bRange && m_furtSkills.Value(FSK.OFFENCE) != 0) {
            damage += damage * m_furtSkills.Value(FSK.OFFENCE) / 100;
        }

        // Increase range attack damage according to Archery skill
        if (bRange && m_furtSkills.Value(FSK.ARCHERY) != 0) {
            damage += damage * m_furtSkills.Value(FSK.ARCHERY) / 100;
        }

        // Increase damage according to jousting bonus
        if (joustBonus != 0) {
            damage += damage * joustBonus / 100;
        }

        // Decrease damage according to Armorer skill
        if (pTarget.m_furtSkills.Value(FSK.ARMORER) != 0) {
            damage -= Math.min((damage * pTarget.m_furtSkills.Value(FSK.ARMORER))/100, damage - 1);
        }

        // Decrease damage according to air shield skill
        if (bRange && pTarget.m_furtSkills.Value(FSK.AIRSHIELD) != 0) {
            damage -= Math.min((damage * pTarget.m_furtSkills.Value(FSK.AIRSHIELD))/100, damage-1);
        }

        // Decrease damage according to shield skill
        if (!bRange && pTarget.m_furtSkills.Value(FSK.SHIELD) != 0) {
            damage -= Math.min((damage * pTarget.m_furtSkills.Value(FSK.SHIELD))/100, damage-1);
        }

        return damage;
    }

    public void Attack(iBattleGroup pTarget, int aflags, int penalty, int joustBonus, int luck) {
        boolean bRange = (aflags & AttackFlags.RangeAttack) != 0;

        // calculate damage
        int damage = CalcDamage(pTarget, ActMinDamage(), bRange, penalty, joustBonus);
        if (ActMinDamage() != ActMaxDamage()) {
            int max_dmg = CalcDamage(pTarget, ActMaxDamage(), bRange, penalty, joustBonus);
            damage += gGame.Map().Rand((max_dmg-damage+1));
        }

        //
        if (bRange && (aflags & AttackFlags.LichCloud) == 0) {
            tracer.check(m_shots > 0);
            --m_shots;
        }

        // Modify damage according to luck
        if (luck == MLM.POSITIVE) {
            damage *= 2;
        } else if (luck == MLM.NEGATIVE) {
            damage = Math.max(damage/2, 1);
        }

        // Genie's half
        if (HasPerk(CPERK.DOHALF) && gGame.Map().Rand(10) == 0) {
            m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_BAT_HALF],gTextMgr[TRID.CREATURE_PEASANT_F2+m_creatType*3]));
            int halfExp;
            int dead = pTarget.Half(halfExp);
            tracer.check(dead);
            //OutputDebugString(iFormat(_T("%s do half to %s"),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3],gTextMgr[TRID_CREATURE_PEASANT_F2+pTarget.Type()*3]).CStr());
            m_pOwner.AddExperience(halfExp);
        } else {
            m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_BAT_DODAMAGE],gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3],damage));
            int dead = pTarget.ReceiveDamage(damage, bRange);
            //OutputDebugString(iFormat(_T("%s do %d damage to %s (penalty: %d)\n"),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3],damage,gTextMgr[TRID_CREATURE_PEASANT_F2+pTarget.Type()*3],penalty).CStr());
            m_pOwner.AddExperience(damage);

            // special creatures
            if (HasPerk(CPERK.GHOST)) {
                // Flies, creatures killed by Ghosts become Ghosts
                if ( !pTarget.HasPerk(CPERK.LIFELESS) &&  !pTarget.HasPerk(CPERK.UNDEAD) ) {
                    m_casualties = Math.max(m_casualties-dead,0);
                    m_creatCount += dead;
                }
            } else if (HasPerk(CPERK.DRAINLIFES)) {
                // Regenerates from the blood of target
                if ( !pTarget.HasPerk(CPERK.LIFELESS) &&  !pTarget.HasPerk(CPERK.UNDEAD) ) {
                    int rcnt = Resurrect(damage);
                    //OutputDebugString(iFormat(_T("%d vampire resurrected (%dhp)\n"), rcnt, damage).CStr());
                }
            }
        }
    }

    public int ReceiveDamage(Integer damage, boolean bRange) {
        String cellMsg = FormatNumber(damage);

        // Remove Paralize and Blind spells
        if (m_bBlinded) {
            RemoveSpell(MSP.BLIND);
        }

        //OutputDebugString(iFormat(_T("%s receives %d of damage: "),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3],damage).CStr());
        int dead = 0;
        if (damage >= TotalHitPoints()) {
            m_casualties += m_creatCount;
            damage = TotalHitPoints();
            //OutputDebugString(iFormat(_T("%d units dead (Group destroyed)\n"),m_creatCount).CStr());
            dead = m_creatCount;
            m_creatCount = 0;
            m_hitPoints = 0;
            SetState(STATE.Dead);
        } else {
            m_hitPoints -= damage;
            while(m_hitPoints <= 0) {
                ++dead;
                m_creatCount--;
                m_hitPoints += HitPoints();
            }
            //OutputDebugString(iFormat(_T("%d units dead\n"),dead).CStr());
            tracer.check(m_creatCount > 0);
            m_casualties += dead;
        }

        if (dead != 0) {
            m_pWrapper.AddLogEvent(GetUnitsColor(m_pOwner.Owner()) + "#S0--- " + String.format(gTextMgr[TRID.MSG_BAT_PERISH],dead,gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
            cellMsg += String.format(" (-%d)", dead);
        }
        m_pWrapper.AddCellEvent(cellMsg, m_Pos);

        return dead;
    }

    public int Half(int damage) {
        int dead = (m_creatCount+1) / 2;
        m_casualties += dead;
        m_creatCount -= dead;
        if (m_creatCount == 0) {
            damage = m_hitPoints;
            m_hitPoints = 0;
            SetState(STATE.Dead);
        } else {
            damage = dead * HitPoints();
        }

        m_pWrapper.AddLogEvent(GetUnitsColor(m_pOwner.Owner()) + "#S0--- " + String.format(gTextMgr[TRID.MSG_BAT_PERISH],dead,gTextMgr[TRID.CREATURE_PEASANT_F3+m_creatType*3]));
        m_pWrapper.AddCellEvent("HALF", m_Pos);

        return dead;
    }

    public final boolean CanResurrect() {
        return (CREAT.DESC[m_creatType].perks & CPERK.GHOST) == 0 && (m_casualties > 0 || HitPoints() > HitPointsLeft());
    }

    public int Resurrect(int hp) {
        int resurrected = 0;
        m_hitPoints += hp;

        while (m_hitPoints > HitPoints()) {
            m_hitPoints -= HitPoints();
            resurrected++;
            m_creatCount++;
        }

        if (m_creatCount > m_initCount) {
            resurrected = m_casualties;
            m_creatCount = m_initCount;
            m_hitPoints = HitPoints();
        }

        m_casualties -= resurrected;

        if (m_State == STATE.Dead) {
            SetState(STATE.Idle);
        }

        return resurrected;
    }

    public void Restore(int hp) {
        m_hitPoints = Math.min(m_hitPoints+hp, HitPoints());
    }

    public void SetState(int nState) {
        //_LOG(iFormat(_T("%s changes state to '%s'\n"),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3], STATE_NAMES[nState]).CStr());
        m_State = nState;
    }

    public void Place(final iPoint pos) {
        boolean bInMoat = (m_passMap.GetAt(pos.x,pos.y)&0x7F) == CT.MOAT || ( m_Size == 2 && (m_passMap.GetAt(pos.x+TAIL.OFFSET[m_Orient],pos.y)&0x7F) == CT.MOAT);

        /*
        FILE* nfile = fopen("C:\\!!!_!!!.dump","wb");
        fwrite(m_passMap.GetPtr(),m_passMap.GetWidth()*m_passMap.GetHeight(),1,nfile);
        fclose(nfile);*/

        if ( bInMoat && !m_bInMoat) {
            EnterMoat();
        } else if (!bInMoat && m_bInMoat) {
            LeaveMoat();
        }
        //_LOG(iFormat(_T("%s moves to %d,%d\n"),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3], pos.x,pos.y).CStr());
        m_Pos = pos;
    }

    public void Rotate() {
        //_LOG(iFormat(_T("%s rotates\n"),gTextMgr[TRID_CREATURE_PEASANT_F2+m_creatType*3]).CStr());
        if (m_Size == 2) {
            m_Pos.x += TAIL.OFFSET[m_Orient];
        }
        m_Orient = m_Orient == 0 ? 1 : 0;
    }

    public void GetMeleePosition(final iBattleGroup pTarget, final iPoint pos, int mdir, iPoint meleePos, Integer meleeOrient) {
        if (mdir == MDIR.RSPTOP || mdir == MDIR.RSPBOTTOM || mdir == MDIR.LSPTOP  || mdir == MDIR.LSPBOTTOM) {
            // target should be double sized and attack point should be between location cells
            tracer.check(pTarget.Size() == 2);
            int sor = (mdir == MDIR.RSPTOP || mdir == MDIR.RSPBOTTOM)?0:1;
            int sdir = (mdir == MDIR.RSPTOP || mdir == MDIR.LSPTOP)?0:2;
            meleePos.x = pos.x + HEX.NEBS[pos.y&1][sor][sdir][0];
            meleePos.y = pos.y + HEX.NEBS[pos.y&1][sor][sdir][1];
            meleeOrient = m_Orient;
            int ptcx = meleePos.x+TAIL.OFFSET[meleeOrient];
            int ptcy = meleePos.y;
            if (!m_passMap.IsValidPos(ptcx, ptcy) || (m_passMap.GetAt(ptcx, ptcy) & 0x7F) != CT.PASSABLE) {
                meleeOrient = meleeOrient == 0 ? 1 : 0;
            }
        } else if (mdir == MDIR.NORTH || mdir == MDIR.SOUTH) {
            // assaulter should be double sized
            tracer.check(m_Size == 2);
            int mmd = (mdir == MDIR.NORTH)?0:2;
            meleePos.x = pos.x + HEX.NEBS[pos.y&1][m_Orient][mmd][0];
            meleePos.y = pos.y + HEX.NEBS[pos.y&1][m_Orient][mmd][1];
            meleeOrient = m_Orient;
        } else {
            // standard move directions
            int dir = mdir >> 1;
            meleeOrient = BattleDirOrient(dir) == 0 ? 1 : 0;
            meleePos.x = pos.x + HEX.MELEE_NEBS1[pos.y&1][dir][0];
            meleePos.y = pos.y + HEX.MELEE_NEBS1[pos.y&1][dir][1];
        }
    }

    //
    public void Synchronize(iArmy army) {
        if (m_idx == -1)
            return;
        tracer.check(army.At(m_idx).Type() == m_creatType);
        army.At(m_idx).setCount(m_creatCount);
        if (m_casualties != 0) {
            m_casualties = 0;
            tracer.check(army.At(m_idx).Count() >= 0);
            if (army.At(m_idx).Count() == 0)
                army.At(m_idx).setType(CREAT.UNKNOWN);
        }
    }

    public void InitPassMap(final iBattleMap obsMap, final iCastleFort pFort) {
        m_passMap.CopyFrom(obsMap);
        tracer.check(m_meleeList.isEmpty() && m_shotList.isEmpty() && m_potTargets.isEmpty());
        // insert fort elements
        if (pFort != null) {
            pFort.ProcessPassMap(m_passMap, m_pOwner.MemberType() == iBattleMember.Castle);
        }
    }

    public void UpdatePassMap(
            final iBattleMap obsMap,
            final ArrayList<iBattleGroup> mg,
            final ArrayList<iBattleGroup> eg,
            final iCastleFort pFort
    ) {
        // Reset
        m_passMap.CopyFrom(obsMap);
        m_meleeList.clear();
        m_shotList.clear();
        m_potTargets.clear();

        //uint32 val = m_passMap.GetAt(9,1);

        // insert fort elements
        if (pFort != null) {
            pFort.ProcessPassMap(m_passMap, m_pOwner.MemberType() == iBattleMember.Castle);
        }

        //m_passMap.GetAt(9,1);

        // insert creatures
        for (int xx=0; xx<mg.size(); ++xx) {
            if (!mg.get(xx).IsAlive() || mg.get(xx) == this) {
                continue;
            }
            m_passMap.GetAt(mg.get(xx).Pos().x, mg.get(xx).Pos().y) = CT.FRIENDLYC;
            if (mg.get(xx).size() == 2) {
                m_passMap.GetAt(mg.get(xx).Pos().x + TAIL.OFFSET[mg.get(xx).Orient()], mg.get(xx).Pos().y) = CT.FRIENDLYC;
            }
        }
        for (int xx=0; xx<eg.size(); ++xx) {
            if (!eg.get(xx).IsAlive()) {
                continue;
            }
            m_passMap.GetAt(eg.get(xx).Pos().x, eg.get(xx).Pos().y) = CT.ENEMYC;
            if (eg.get(xx).size() == 2) {
                m_passMap.GetAt(eg.get(xx).Pos().x+TAIL.OFFSET[eg.get(xx).Orient()],eg.get(xx).Pos().y) = CT.ENEMYC;
            }
        }
    }

    public final iBattleMap PassMap() {
        return m_passMap;
    }

    public final iMapT<iDistCell> DistMap() {
        return m_distMap;
    }

    public void AddMeleeEntry(final iPoint pos, int mdir) {
        tracer.check(mdir < MDIR.COUNT);
        for (int xx=0; xx<m_meleeList.size(); ++xx){
            if (m_meleeList.get(xx).m_pos == pos) {
                m_meleeList.get(xx).m_cfg |= (1<<mdir);
                return;
            }
        }
        m_meleeList.add(new iMeleeEntry(pos, 1<<mdir));
    }

    public void AddMeleeMask(final iPoint pos, int mmask) {
        m_meleeList.add(new iMeleeEntry(pos, mmask));
    }

    public void AddShootEntry(final iPoint pos, int penalty) {
        m_shotList.add(new iShootEntry(pos, penalty));
    }

    public void AddPotTargetMask(final iPoint pos, int mmask) {
        m_potTargets.add(new iMeleeEntry(pos, mmask));
    }

    public final int MeleeListCount() {
        return m_meleeList.size();
    }

    public final iMeleeEntry GetMeleeEntry(int idx) {
        return m_meleeList.get(idx);
    }

    public final iMeleeEntry GetMeleeEntry(final iPoint pos) {
        for (int xx = 0; xx < m_meleeList.size(); ++xx)
            if (m_meleeList.get(xx).m_pos == pos)
                return m_meleeList.get(xx);

        return null;
    }

    public final int ShootListCount() {
        return m_shotList.size();
    }

    public final iShootEntry GetShootEntry(int idx) {
        return m_shotList.get(idx);
    }

    public final iShootEntry GetShootEntry(final iPoint pos) {
        for (int xx = 0; xx < m_shotList.size(); ++xx)
            if (m_shotList.get(xx).m_pos == pos)
                return m_shotList.get(xx);
        return null;
    }

    public final int PotTargetsCount() {
        return m_potTargets.size();
    }

    public final iMeleeEntry GetPotTargetEntry(int idx) {
        return m_potTargets.get(idx);
    }

    public final iMeleeEntry GetPotTargetEntry(final iPoint pos) {
        for (int xx = 0; xx < m_potTargets.size(); ++xx)
            if (m_potTargets.get(xx).m_pos == pos)
                return m_potTargets.get(xx);
        return null;
    }

    public final boolean CanMove(int x, int y) {
        return m_passMap.IsValidPos(x,y) &&
               ((m_passMap.GetAt(x,y) & 0x80) != 0 || IsGroupCell(new iPoint(x,y)));
    }

    public final int Casualties() {
        return m_casualties;
    }

    public iBattleWrapper BattleWrapper() {
        return m_pWrapper;
    }

    public iFurtSkills FurtSkills() {
        return m_furtSkills;
    }

    protected iFurtSkills        m_furtSkills;


    private int            m_spImmunity;
    private iSpellList        m_spells;
    private boolean            m_bBlinded;
    private int            m_retPenalty;

    private iBattleMember    m_pOwner;
    private iBattleWrapper    m_pWrapper;
    private iPoint            m_Pos;
    private int            m_Size;
    private int    m_creatType;
    private int            m_creatCount;
    private int            m_initCount;
    private int            m_idx;
    private int            m_blessState;

    private int            m_casualties;

    private boolean            m_bWaited;
    private int            m_toDefend;
    private int            m_retails;
    private boolean            m_bMoraleAttack;
    private boolean            m_bInMoat;
    private int        m_roundMorale;
    private int            m_hitPoints;
    private int            m_shots;
    private int            m_State;
    private int            m_Orient;

    private iDistMap        m_distMap;
    private iBattleMap        m_passMap;
    private iMeleeList        m_meleeList;
    private iMeleeList        m_potTargets;
    private iShotList         m_shotList;
}
