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

import newph.core.staticFunction.MathOperations;

/**
 * Contains gradient pixel codes.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class iGradient {

    public iGradient(final int[] pGrad, final int cnt) {
        this.m_pGrad = pGrad;
        this.m_cnt = cnt;
    }
    
    public iGradient(final iGradient other) {
        this.m_pGrad = other.m_pGrad;
        this.m_cnt = other.m_cnt;
    }
    
    public final int Count() {
        return m_cnt;
    }
    
    public final int GradValue(final int pos) {
        if (!IsValid()) {
            return 0;
        } else {
            return m_pGrad[MathOperations.clamp(0, m_cnt-1, pos)];
        }
    }
    
    public final boolean IsValid() {
        return m_cnt > 0;
    }

    private final int[] m_pGrad; // int is color code
    private int         m_cnt;

}
