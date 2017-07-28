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

import newph.core.type.Changeable;

/**
 * Operations.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class Operations {

    /**
     * Prevents from creating an instance of the class.
     */
    private Operations() {}

    /**
     * Swaps content of two objects.
     *
     * @param a The first object.
     * @param b The second object.
     */
    public static final void iSwap(final Changeable<?> a, final Changeable<?> b) {
        Object tmp = a.value;
        a.setObject(b.value);
        b.setObject(tmp);
    }

    /**
     * Restricts the value between min and max values.
     *
     * @param min   Min value.
     * @param max   Max value.
     * @param value Value.
     * @return      Restricted value.
     */
    public static final int clamp(final int min, final int max, final int value) {
        Tracer.check(min <= max);

        if (min <= value && value <= max) {
            return value;
        } else if (value < min) {
            return min;
        } else { // if (max < value)
            return max;
        }
    }

    /**
     * Returns the absolute difference between two values.
     *
     * @param x First value.
     * @param y Second value.
     * @return  Absolute value of difference.
     */
    public static final int dif(final int x, final int y) {
        return (x > y) ? (x - y) : (y - x);
    }

    /**
     * Sign function.
     *
     * @param x Value.
     * @return  1 if the value is greater than 0, 0 if the value is 0, - 1 if the value is smaller than 0.
     */
    public static final int sign(final int x) {
        if (0 < x) {
            return 1;
        } else if (x < 0) {
            return -1;
        } else { // if (x == 0)
            return 0;
        }
    }

    /**
     * Aligns the given value according to the alignment step.
     *
     * @param value     The value.
     * @param step      The alignment step.
     * @return          The aligned value.
     */
    public static final int align(final int value, final int step) {
        return ((value + step - 1) / step) * step;
    }

    public static final int wrap(int val, int minv, int wrap) {
        Tracer.check(minv < wrap);

        if (val < minv) {
            return val + wrap - minv;
        } else if (wrap <= val) {
            return val - wrap + minv;
        } else { // if (minv <= val < wrap)
            return val;
        }
    }

    /**
     * Rounds negative and positive double mathematically.
     *
     * @param d Double to round.
     * @return  Rounded correctly double.
     */
    public static final double roundDouble(double d) {
        if (d < 0.0) {
            return Math.ceil(d - 0.5);
        } else {
            return Math.floor(d + 0.5);
        }
    }

}
