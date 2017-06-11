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

import Common.iCreatGroup;
import Constants.*;
import Game.iBattleGroup;
import Game.iBattleUnit_Hero;
import Game.iHero;

/**
 *
 */
public class iSpell_Summon extends iCombatSpell {
    
    public iSpell_Summon(final int spellId) {
        super(spellId);
    }
    
    public int EstimateQuant(final iHero pCaster) {
        int msl = GetSchoolLevel(pCaster);
        // quantity
        int quant = SPELL.DESCRIPTORS[m_spellId].eff[msl].sparam * pCaster.FurtSkill(FSK_POWER);
        // sorcery modifier
        int sorcery = pCaster.FurtSkill(FSK.SORCERY);
        if (sorcery != 0) {
            quant += quant * sorcery / 100;
        }
        // SHF_SUMRESBOUNS
        if (pCaster.HasSpecFlag(SHF.SUMRESBOUNS)) {
            quant += quant / 2;
        }
        return quant;
    }
    
    public int CreatType() {
        return SPELL.DESCRIPTORS[m_spellId].eff[MSL.NONE].fparam;
    }
    
    @Override
    public boolean Cast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        if (!super.Cast(pCaster, pTarget)) {
            return false;
        }
        int msl = GetSchoolLevel(pCaster.GetHero());

        int quant = EstimateQuant(pCaster.GetHero());
        iCreatGroup cg = new iCreatGroup(CreatType(), quant);
        iBattleMap pmap = new iBattleMap();
        pTarget.BattleWrapper().Engine().MakePassMap(pmap);
        pTarget.BattleWrapper().Engine().Summon(
                pCaster.Owner().GetSide(), 
                cg, 
                FindSummonCell(
                        pmap,
                        pCaster.Owner().GetSide(), 
                        CREAT.DESC[cg.Type()].size==2
                )
        );
        return true;
    }
    
}
