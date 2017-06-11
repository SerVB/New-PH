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

import Constants.*;
import Game.iBattleGroup;
import Game.iBattleUnit_Hero;
import Game.iHero;

/**
 *
 */
public class iSpell_Resurrect extends iCombatSpell {
    
    public iSpell_Resurrect(final int spellId) {
        super(spellId);
    }
    
    public int EstimateHitPoints(final iHero pCaster) {
        // Restore hit points
        int msl = GetSchoolLevel(pCaster);
        int hp = pCaster.FurtSkill(FSK.POWER) * SPELL.DESCRIPTORS[m_spellId].eff[msl].fparam;
        // sorcery modifier
        int sorcery = pCaster.FurtSkill(FSK.SORCERY);
        if (sorcery != 0) {
            hp += hp * sorcery / 100;
        }
        // SHF_SUMRESBOUNS
        if (pCaster.HasSpecFlag(SHF.SUMRESBOUNS)) {
            hp += hp / 2;
        }
        return hp;
    }
    
    @Override
    public boolean CanCast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        if (!super.CanCast(pCaster, pTarget)) {
            return false;
        }
        return pTarget.CanResurrect();
    }
    
    @Override
    public boolean Cast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        if (!super.Cast(pCaster, pTarget)) {
            return false;
        }

        int hp = EstimateHitPoints(pCaster.GetHero());
        pTarget.Resurrect(hp);

        return true;
    }
    
    @Override
    public String ActionText(final iHero pCaster) {
        return super.ActionText(pCaster);
    }
    
    @Override
    public boolean AffectsToDead() {
        return true;
    }
    
}
