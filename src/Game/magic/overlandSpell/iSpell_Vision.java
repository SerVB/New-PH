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
package Game.magic.overlandSpell;

import Constants.*;
import Game.iHero;

/**
 *
 */
public class iSpell_Vision extends iOverlandSpell {
    
    public iSpell_Vision(final int spellId) {
        super(spellId);
    }
    
    @Override
    public boolean Cast(final iHero pCaster) {
        if (!super.Cast(pCaster)) {
            return false;
        }
        
        int msl = GetSchoolLevel(m_spellId, pCaster);
        pCaster.AddCharm(m_spellId, SPELL.DESCRIPTORS[m_spellId].eff[msl].fparam);
        return true;
    }
    
    @Override
    public void OnRemove(final iHero pCaster, final int param) {}
    
}
