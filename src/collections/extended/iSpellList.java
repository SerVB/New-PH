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
package collections.extended;

import entries.iSpellEntry;
import java.util.ArrayList;

/**
 *
 */
public class iSpellList extends ArrayList<iSpellEntry> {

    /**
     * Adds an unique element to the end of this list.
     * If this list contains element, it won't be added.
     * @param spellId Spell ID of the spell to be added.
     * @return True if the element was added, else if not.
     */
    public boolean AddUnique(final int spellId) {
        if (Find(spellId) == -1) {
            return super.add(new iSpellEntry(spellId));
        } else {
            return false;
        }
    }

    public int Find(final int spellId) {
        for (int idx = 0; idx < super.size(); idx++) {
            if (super.get(idx).id == spellId) {
                return idx;
            }
        }
        
        return -1;
    }
}
