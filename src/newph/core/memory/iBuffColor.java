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

/**
 * Replaces {@code iBuff<color>}
 * @author SerVB
 * @since "GitHub new sources"
 */
public class iBuffColor extends iBuff<Integer> {
    
    public iBuffColor() {
        super();
    }

    public iBuffColor(final int siz) {
        super(siz);
    }

    public iBuffColor(final ArrayList<Integer> buff, final int siz) {
        super(buff, siz);
    }

    public iBuffColor(final iBuff<Integer> buff)  {
        super(buff);
    }

    @Override
    public Integer[] getAr() {
        Integer[] ar = {};
        ar = super.GetPtr().toArray(ar);
        
        return ar;
    }

}
