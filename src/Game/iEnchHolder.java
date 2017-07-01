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

import Common.iFurtSkills;
import newph.staticFunction.Tracer;
import Constants.FSK;
import java.util.ArrayList;

/**
 *
 */
public class iEnchHolder {

    public iEnchHolder(iFurtSkills furtSkills) {
        this.m_furtSkills = furtSkills;
    }

    public void Add(int _provider, int _type, int _dur, int _mod) {
        m_items.add(new iEnchItem(_provider, _type, _dur, _mod));

        if (_type != FSK.INVALID)
            m_furtSkills.plusValue(_type, _mod);
    }

    public final int Count() {
        return m_items.GetSize();
    }

    public final iEnchItem get(int idx) {
        Tracer.check(idx < m_items.GetSize());
        return m_items.get(idx);
    }

    /*
    inline const iEnchItem& operator[](uint32 idx) const
    { check(idx<m_items.GetSize()); return m_items[idx]; }

    inline sint32 Mods(FURTHER_SKILLS type) const
    { sint32 mod=0; for (uint32 xx=0; xx<m_items.GetSize(); ++xx) if (m_items[xx].type == type) mod+=m_items[xx].mod; return mod; }
    */

    public final boolean Registered(int _provider) {
        for (int xx=0; xx < m_items.GetSize(); ++xx)
            if (m_items.get(xx).provider == _provider)
                return true;
        return false;
    }

    public void RemoveDur(int dur)
    {
        for (int xx=0; xx < m_items.GetSize();) {
            if (m_items.get(xx).dur == dur)
                RemoveAt(xx);
            else
                ++xx;
        }
    }


    private void RemoveAt(int idx) {
        if (m_items.get(idx).type != FSK.INVALID)
            m_furtSkills.minusValue(m_items.get(idx).type, m_items.get(idx).mod);
        m_items.RemoveAt(idx);
    }


    private iFurtSkills            m_furtSkills;
    private final ArrayList<iEnchItem> m_items = new ArrayList<>();
}
