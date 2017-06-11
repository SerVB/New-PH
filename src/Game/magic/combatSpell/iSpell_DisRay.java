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

/**
 *
 */
public class iSpell_DisRay extends iCombatSpell {
    
    public iSpell_DisRay(final int spellId) {
        super(spellId);
    }
    
    @Override
    public boolean Cast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        if (!super.Cast(pCaster, pTarget)) {
            return false;
        }
        int msl = GetSchoolLevel(pCaster.GetHero());
        int param = SPELL.DESCRIPTORS[m_spellId].eff[msl].fparam;
        pTarget.FurtSkills().minusValue(FSK.DEFENCE, param);
        pTarget.AddSpell(this, -1, param);
        return true;
    }
    
    @Override
    public void OnRemove(final iBattleGroup pTarget, final int param) {
        pTarget.FurtSkills().plusValue(FSK.DEFENCE, param);
    }
    
}
