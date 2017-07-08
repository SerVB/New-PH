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
package newph.core.memory;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @param <T>
 * @author SerVB
 * @since "GitHub new sources"
 */
public abstract class iBuff<T> {

    public iBuff() {}

    public iBuff(final int siz) {
        Allocate(siz);
    }

    public iBuff(final ArrayList<T> buff, final int siz) {
        Allocate(buff, siz);
    }

    public iBuff(final iBuff<T> buff)  {
        Allocate(buff);
    }

    public final boolean IsClean() {
        return m_pBuff == null || m_pBuff.isEmpty();
    }

    public final void Allocate(final int siz) {
        Clean();
        m_pBuff = new ArrayList<>(siz);
        
        for (int ii = 0; ii < siz; ii++) {
            m_pBuff.add(null);
        }
        
//        memset(m_pBuff,0,m_Siz*sizeof(T));
    }
    
    public final void Allocate(final T[] ar, final int siz) {
        Allocate(new ArrayList<>(Arrays.asList(ar)), siz);
    }

    public final void Allocate(final ArrayList<T> buff, final int siz) {
        Clean();
        
        m_pBuff.addAll(buff);
        
        trimToSize(siz); // memcpy(m_pBuff,buff,m_Siz*sizeof(T));
    }
    
    /**
     * Removes the last elements to trim buffer to the size.
     * @param size The size.
     */
    private void trimToSize(final int size) {
        while (size < m_pBuff.size()) {
            final int lastId = m_pBuff.size() - 1;
            m_pBuff.remove(lastId);
        }
    }

    public final void Allocate(final iBuff<T> buff) {
        Clean();
        
        m_pBuff.addAll(buff.GetPtr());
    }

    public final void Clean() {
        m_pBuff = new ArrayList<>();
    }

    public final int GetSize() {
        return m_pBuff.size();
    }

    protected final ArrayList<T> GetPtr() {
        return m_pBuff;
    }
    
    /**
     * Sets the specified element to the specified value.
     * @param idx   Index of element.
     * @param elem  New value of element.
     * @return      Old value of element.
     */
    public final T set(final int idx, final T elem) {
        return m_pBuff.set(idx, elem);
    }
    
    public T at(final int idx) {
        return m_pBuff.get(idx);
    }
    
    public abstract T[] getAr();

    private ArrayList<T> m_pBuff;
    
}
