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

package newph.core.staticFunction;

import newph.core.metric.iPoint;

/**
 * Common Static Function class.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class Common {

    /**
     * Prevents from creating an instance of the class.
     */
    private Common() {}

    /**
     *
     * @param pnt
     * @param maxv
     * @param game If the call is from game ({@code #ifdef _HMM_GAME_}).
     * @return
     */
    public static final int CalcCellSeq(final iPoint pnt, final int maxv, final boolean game) {
        int result = pnt.x;
        result += ~(pnt.y << 16);
        result ^= (pnt.x >> 5);
        result += (pnt.y << 3);
        result ^= (pnt.x >> 13);
        result += ~(pnt.y << 9);
        result ^= (result >> 17);

        if (game) {
            result = iRandTable[(result ^ (result >> 8) ^ (result >> 16)) & 255];
        } else {
            result = iTables.crc32[(result ^ (result >> 8) ^ (result >> 16)) & 255];
        }
        return result % maxv;
    }

}
