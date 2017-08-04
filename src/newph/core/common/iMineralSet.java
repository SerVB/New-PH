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

package newph.core.common;

import java.util.Arrays;
import newph.core.enumeration.MineralType;

/**
 * Mineral Set Class.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class iMineralSet {

    public final int[] quant;

    public void Reset() {
        Arrays.fill(quant, 0);
    }

    public int Has(final iMineralSet ms)  {
        int cnt = 0;
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            if (quant[xx] < ms.quant[xx]) {
                return 0;
            } else if (ms.quant[xx] > 0) {
                cnt = (cnt > 0) ? Math.min(cnt,quant[xx] / ms.quant[xx]) : quant[xx] / ms.quant[xx];
            }
        }
        return cnt;
    }

    public iMineralSet multiply(int val) {
        final iMineralSet result = new iMineralSet();
        result.Reset();
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            result.quant[xx] = quant[xx] * val;
        }
        return result;
    }

    public boolean Empty() {
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            if (quant[xx] != 0) {
                return false;
            }
        }
        return true;
    }

    public iMineralSet DeficientAmount(final iMineralSet other) {
        final iMineralSet result = new iMineralSet();
        result.Reset();
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            if (other.quant[xx] > quant[xx]) {
                result.quant[xx] = other.quant[xx] - quant[xx];
            }
        }
        return result;
    }

    public iMineralSet Intersect(final iMineralSet other) {
        final iMineralSet result = new iMineralSet();
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            result.quant[xx] = Math.min(quant[xx], other.quant[xx]);
        }
        return result;
    }

    public void Normalize() {
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            if (quant[xx] < 0) {
                quant[xx] = 0;
            }
        }
    }

    public boolean equals(final iMineralSet ms) {
        for (int xx=0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            if (quant[xx] != ms.quant[xx]) {
                return false;
            }
        }
        return true;
    }

    public void add(final iMineralSet ms) {
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            quant[xx] += ms.quant[xx];
        }
    }

    public void subtract(final iMineralSet ms) {
        for (int xx = 0; xx < MineralType.MINERAL_COUNT.getValue(); ++xx) {
            quant[xx] -= ms.quant[xx];
        }
    }

    public iMineralSet() {
        quant = new int[MineralType.MINERAL_COUNT.getValue()];

        Reset();
    }

    public iMineralSet(final iMineralSet other) {
        this(other.quant);
    }

    public iMineralSet(final int[] quant) {
        this.quant = new int[MineralType.MINERAL_COUNT.getValue()];

        System.arraycopy(quant, 0, this.quant, 0, quant.length);
    }

}
