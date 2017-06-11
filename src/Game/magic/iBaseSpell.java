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
package Game.magic;

import Constants.*;
import Game.iHero;

/**
 * Base spell class.
 */
public class iBaseSpell {

    public iBaseSpell(final int spellId) {
        m_spellId = spellId;
    }

    public int GetSchoolLevel(final int spellId, final iHero pCaster) { 
        if (pCaster == null) {
            return MSL.NONE;
        }
        
        return pCaster.FurtSkill(FSK.MAG_AIR + SPELL.DESCRIPTORS[spellId].school);
    }
    
    public int GetSpellCost(final int spellId, final iHero pCaster) {
        int res = SPELL.DESCRIPTORS[spellId].bcost;
        int msl = GetSchoolLevel(spellId, pCaster);
        
        if (msl > MSL.NONE) {
            res -= SPELL.DESCRIPTORS[spellId].level + 1;
        }
        
        return res;
    }

    // inlines
    public int NameKey() {
        return TRID.SPNAME_MAGICARROW + m_spellId;
    }
    
    public int DescKey(final int level) {
        return TRID.SPDESC_MAGICARROW_NON + m_spellId * MSL.COUNT + level;
    }
    
    public int Icon() {
        return PDGG.SPELL_ICONS + m_spellId;
    }
    
    public int Type() {
        return SPELL.DESCRIPTORS[m_spellId].type;
    }
    
    public int Level() {
        return SPELL.DESCRIPTORS[m_spellId].level;
    }
    
    public int School() {
        return SPELL.DESCRIPTORS[m_spellId].school;
    }
    
    public int Cost(final int level) {
        int res = SPELL.DESCRIPTORS[m_spellId].bcost;
        
        if (level > MSL.NONE) {
            res -= SPELL.DESCRIPTORS[m_spellId].level + 1;
        }
        
        return res;
    }
    
    public int SpellClass() {
        return SPELL.DESCRIPTORS[m_spellId].spClass;
    }
    
    public int Id() {
        return m_spellId;
    }
    
    public int GetSchoolLevel(final iHero pCaster) {
        return GetSchoolLevel(m_spellId, pCaster);
    }

    protected int m_spellId;
    
}
