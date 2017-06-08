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
package Common.metrics;

/**
 * Contains weight "w" and height "h".
 */
public class iSize {

    /**
     * C-tor. Sets metrics to zero.
     */
    public iSize() {
        w = 0;
        h = 0;
    }

    /**
     * C-tor.
     * @param w Width.
     * @param h Height.
     */
    public iSize(final int w, final int h) {
        this.w = w;
        this.h = h;
    }

    /**
     * C-tor. Copies iSize object.
     * @param other Object to be copied.
     */
    public iSize(final iSize other) {
        this.w = other.w;
        this.h = other.h;
    }

    /**
     * @deprecated Use {@link #InflateSize(int)} instead!
     * Adds value to each metric.
     * @param value Value to be added.
     */
    public void add(final int value) {
        w += value;
        h += value;
    }

    /**
     * @deprecated Use {@link #DeflateSize(int)} instead!
     * Subtracts value from each metric.
     * @param value Value to be subtracted.
     */
    public void subtract(final int value) {
        w -= value;
        h -= value;
    }

    /**
     * C-tor. Makes operations with metrics of two iSize objects.
     * @param first First object.
     * @param oper Operator ('+').
     * @param second Second object.
     */
    public iSize(final iSize first, final char oper, final iSize second) {
        switch (oper) {
            case '+':
                this.w = first.w + second.w;
                this.h = first.h + second.h;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    /**
     * Returns new object with the value added to each metric. Doesn't modify the original object.
     * @param value Value to be add.
     * @return New object.
     */
    public iSize plus(final int value) {
        return new iSize(w + value, h + value);
    }

    /**
     * Returns new object with the value subtracted from each metric. Doesn't modify the original object.
     * @param value Value to be subtracted.
     * @return New object.
     */
    public iSize minus(final int value) {
        return new iSize(w - value, h - value);
    }

    /**
     * Compares the object with the other object.
     * @param other Other object.
     * @return True if objects are equal, false if not.
     */
    public boolean equals(final iSize other) {
        return (this.w == other.w) && (this.h == other.h);
    }

    /**
     * Adds values to metrics.
     * @param w_offs Value to be added to width.
     * @param h_offs Value to be added to height.
     */
    public void inflateSize(int w_offs, int h_offs) {
        w += w_offs;
        h += h_offs;
    }

    /**
     * Adds value to each metric.
     * @param offs Value to be added.
     */
    public void inflateSize(int offs) {
        inflateSize(offs, offs);
    }

    /**
     * Subtracts values from metrics.
     * @param w_offs Value to be added to width.
     * @param h_offs Value to be added to height.
     */
    public void deflateSize(int w_offs, int h_offs) {
        w -= w_offs;
        h -= h_offs;
    }

    /**
     * Subtracts value from each metric.
     * @param offs Value to be subtracted.
     */
    public void deflateSize(int offs) {
        deflateSize(offs, offs);
    }

    /**
     * Returns aspect ratio width:height.
     * @return Aspect ratio.
     */
    public double getAspectRatio() {
        return (double)(w) / (double)(h);
    }

    /**
     * Sets metrics to zero.
     */
    public void toZero() {
        w = h = 0;
    }

    /**
     * Checks if each metric is equal to zero.
     * @return True if equals to zero, false if not.
     */
    public boolean isZero() {
        return w == 0 && h == 0;
    }

    /**
     * Width.
     */
    public int w;

    /**
     * Height.
     */
    public int h;

}
