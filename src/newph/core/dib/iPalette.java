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
package newph.core.dib;

import newph.core.staticFunction.Tracer;

/**
 * 256-colors palette.
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iPalette {

    public iPalette() {}

    public iPalette(final int[] pal) {
        Init(pal);
    }

    public final void Init(final int[] pal) {
        System.arraycopy(pal, 0, m_Palette, 0, 256);
    }

    public final void set(final int nIndex, final int color) {
        Tracer.check(0 <= nIndex && nIndex < 256);
        m_Palette[nIndex] = color;
    }

    public final int get(final int nIndex) {
        Tracer.check(0 <= nIndex && nIndex < 256);
        return m_Palette[nIndex];
    }

    public final int[] GetPtr() {
        return m_Palette;
    }
    
    private final int[] m_Palette = new int[256]; // uint16
    
}
