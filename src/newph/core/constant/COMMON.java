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

package newph.core.constant;

import newph.core.metric.iPoint;

/**
 * Common Constants.
 *
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class COMMON {

    /**
     * Prevents from creating an instance of the class.
     */
    private COMMON() {}

    /**
     * 72 points.
     */
    public static final iPoint[] HERO_FLAG_ANCHOR = {
        new iPoint(4,7), new iPoint(4,5), new iPoint(4,4), new iPoint(4,5), new iPoint(4,7),
        new iPoint(4,6), new iPoint(4,4), new iPoint(4,5), new iPoint(4,6),

        new iPoint(8,7), new iPoint(9,7), new iPoint(8,7), new iPoint(9,8), new iPoint(10,8),
        new iPoint(10,7), new iPoint(9,7), new iPoint(8,7), new iPoint(7,7),

        new iPoint(11,8), new iPoint(12,8), new iPoint(11,8), new iPoint(10,8), new iPoint(10,9),
        new iPoint(9,9), new iPoint(10,8), new iPoint(11,8), new iPoint(10,8),

        new iPoint(13,7), new iPoint(14,7), new iPoint(13,8), new iPoint(11,9), new iPoint(13,9),
        new iPoint(14,10), new iPoint(14,9), new iPoint(14,8), new iPoint(12,7),

        //
        new iPoint(32,8), new iPoint(32,9), new iPoint(32,10), new iPoint(32,8), new iPoint(32,9),
        new iPoint(32,10), new iPoint(32,11), new iPoint(32,8), new iPoint(32,9),

        //
        new iPoint(20,7), new iPoint(19,7), new iPoint(20,8), new iPoint(19,9), new iPoint(20,9),
        new iPoint(19,10), new iPoint(19,9), new iPoint(19,8), new iPoint(22,7),

        new iPoint(22,8), new iPoint(21,8), new iPoint(22,8), new iPoint(23,8), new iPoint(23,9),
        new iPoint(24,9), new iPoint(23,8), new iPoint(22,8), new iPoint(23,8),

        new iPoint(25,7), new iPoint(24,7), new iPoint(25,7), new iPoint(24,8), new iPoint(23,8),
        new iPoint(23,7), new iPoint(24,7), new iPoint(25,7), new iPoint(26,7)
    };

}
