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
package Game.magic.combatSpell;

import Common.iFormat;
import Constants.*;
import Game.iBattleGroup;
import Game.iBattleUnit_Hero;
import Game.iBattleWrapper;
import Game.iHero;

/**
 *
 */
public class iSpell_Damage extends iCombatSpell {
    
    
    public iSpell_Damage(final int spellId) {
        super(spellId);
    }
    
    public int EstimateDamage(final iHero pCaster, final iBattleGroup pTarget) {
        int msl = GetSchoolLevel(pCaster);
        // basic damage
        int damage = pCaster.FurtSkill(FSK.POWER) * SPELL.DESCRIPTORS[m_spellId].eff[msl].fparam;
        // sorcery modifier
        int sorcery = pCaster.FurtSkill(FSK.SORCERY);
        if (sorcery != 0) {
            damage += damage * sorcery / 100;
        }
        // SHF_DMGSPBONUS
        if (pCaster.HasSpecFlag(SHF.DMGSPBONUS)) {
            damage += (damage>>1);
        }
        if (pTarget != null) {
            // resistance modifier
            int resist = pTarget.FurtSkills().Value(FSK.RES_AIR + School());
            if (resist != 0) {
                damage -= damage * resist / 100;
            }
            // Special creatures modifier
            if ((CREAT.DESC[pTarget.Type()].perks & CPERK.QUARTERDMG) != 0) {
                damage = Math.max(damage / 2, 1);
            }
        }
        return damage;
    }
    
    public int EstimateDamage(final iHero pCaster) {
        return EstimateDamage(pCaster, null);
    }
    
    @Override
    public boolean Cast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        if (!super.Cast(pCaster, pTarget)) {
            return false;
        }
        
        int damage = EstimateDamage(pCaster.GetHero(), pTarget);
        iBattleWrapper pWrap = pTarget.BattleWrapper();

        String cellMsg = String.valueOf(damage);
        int dead = pTarget.ReceiveDamage(damage, false);
        if (dead != 0){
            cellMsg = cellMsg.concat(String.format(" (-%d)", dead));
        }

        if (pTarget.Owner().GetSide() != pCaster.Owner().GetSide()) {
            pCaster.Owner().AddExperience(damage);
        }
        pWrap.AddCellEvent(cellMsg, pTarget.Pos());

        return true;
    }
    
    @Override
    public String ActionText(final iHero pCaster) {
        return iFormat.format(gTextMgr[TRID.MSG_SPELL_DOES_DAMAGE], EstimateDamage(pCaster));
    }
    
}
