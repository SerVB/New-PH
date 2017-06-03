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

import entries.iMeleeEntry;
import Common.iFurtSkills;
import Common.iPoint;
import Constants.*;
import ConstantsGame.*;
import java.util.ArrayList;

/**
 *
 */
public class iBattleGroup {

    // c-tor
    public iBattleGroup(final iCreatGroup cg, int idx, iBattleMember pOwner, iBattleWrapper pWrapper, int moraleModifier);

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
                ((CREAT.DESC[m_creatType].perks & CPERK.RETALTOALL) ||
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

    public final int CalcLuck();

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
        return iPoint(m_Pos.x + TAIL.OFFSET[m_Orient], m_Pos.y);
    }

    // Moat
    public final boolean InMoat() {
        return m_bInMoat;
    }

    public void EnterMoat();
    public void LeaveMoat();

    // Spells
    public void AddSpell(final iCombatSpell pSpell, int rounds, int param);
    public void RemoveSpell(int spell);
    public void RemoveSpellAt(int idx);

    public final ArrayList<iSpellEntry> SpellList() {
        return m_spells;
    }

    public final int SpellsDispos();

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
    public void NewRound();
    public void DestroyGroup();
    public void Wait();
    public void Defend();
    public void GoodMorale();
    public void BadMorale();
    public void GoodLuck();
    public void BadLuck();
    public final int CalcJoustingBonus(final iBattleGroup pTarget);
    public final int CalcDamage(final iBattleGroup pTarget, int damage, boolean bRange, int penalty, int joustBonus);
    public void Attack(iBattleGroup pTarget, int aflags, int penalty, int joustBonus, int luck);
    public int ReceiveDamage(Integer damage, boolean bRange);
    public int Half(int damage);
    public final boolean CanResurrect();
    public int Resurrect(int hp);
    public void Restore(int hp);
    public void SetState(int nState);
    public void Place(final iPoint pos);
    public void Rotate();
    public void GetMeleePosition(final iBattleGroup pTarget, final iPoint pos, int mdir, iPoint meleePos, Integer meleeOrient);

    //
    public void Synchronize(iArmy army);
    public void InitPassMap(final iBattleMap obsMap, final iCastleFort pFort);
    public void UpdatePassMap(
            final iBattleMap obsMap,
            final ArrayList<iBattleGroup> mg,
            final ArrayList<iBattleGroup> eg,
            final iCastleFort pFort
    );

    public final iBattleMap PassMap() {
        return m_passMap;
    }

    public final iMapT<iDistCell> DistMap() {
        return m_distMap;
    }

    public void AddMeleeEntry(final iPoint pos, int mdir);
    public void AddMeleeMask(final iPoint pos, int mmask);
    public void AddShootEntry(final iPoint pos, int penalty);
    public void AddPotTargetMask(final iPoint pos, int mmask);

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
    private ArrayList<iSpellEntry>        m_spells;
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

    private iMapT<iDistCell>        m_distMap;
    private iBattleMap        m_passMap;
    private ArrayList<iMeleeEntry>        m_meleeList;
    private ArrayList<iMeleeEntry>        m_potTargets;
    private ArrayList<iShootEntry>        m_shotList;
}
