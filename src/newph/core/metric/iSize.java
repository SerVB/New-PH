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

package newph.core.metric;

/**
 * Size class. Contains width "w" and height "h" metrics.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class iSize {

    /**
     * Constructs Size object with zero metrics.
     */
    public iSize() {
        this(0, 0);
    }

    /**
     * Constructs Size object with given width and height.
     * @param w The width.
     * @param h The height.
     */
    public iSize(final int w, final int h) {
        this.w = w;
        this.h = h;
    }

    /**
     * Constructs Size object with copied metrics.
     * @param toBeCopied The other Size to be copied.
     */
    public iSize(final iSize toBeCopied) {
        this(toBeCopied.w, toBeCopied.h);
    }
    
    public iSize(final iRect rect) {
        this(rect.w, rect.h);
    }

    /**
     * Increments width and height by given value.
     * @param increment The value.
     */
    public void add(final int increment) {
        w += increment;
        h += increment;
    }

    /**
     * Decrements with and height by given value.
     * @param decrement The value.
     */
    public void subtract(final int decrement) {
        add(-decrement);
    }

    /**
     * Constructs Size object.
     * Adds the given value to the given Size object.
     * (Operator +)
     * @param size      The Size object.
     * @param increment The value.
     * @return          New Size object.
     */
    public static iSize addToSize(final iSize size, final int increment) {
        iSize res = new iSize(size);
        res.add(increment);
        
        return res;
    }

    /**
     * Constructs Size object.
     * Subtracts the given value from the given Size object.
     * (Operator -)
     * @param size      The Size object.
     * @param decrement The value.
     * @return          New Size object.
     */
    public static iSize subtractFromSize(final iSize size, final int decrement) {
        iSize res = new iSize(size);
        res.subtract(decrement);
        
        return res;
    }

    /**
     * Checks equality with the other Size object.
     * (Operator ==)
     * @param other The other Size object.
     * @return      True if equal, false if not.
     */
    public boolean equals(final iSize other) {
        return
                this.w == other.w &&
                this.h == other.h;
    }

    /**
     * Inflates size: adds the given offsets to metrics.
     * @param widthOffset   The width offset.
     * @param heigthOffset  The height offset.
     */
    public void inflateSize(final int widthOffset, final int heigthOffset) {
        w += widthOffset;
        h += heigthOffset;
    }

    /**
     * Inflates size: adds the given offset to metrics.
     * @param offset The common offset.
     */
    public void inflateSize(final int offset) {
        inflateSize(offset, offset);
    }

    /**
     * Deflates size: subtracts the given offsets from metrics.
     * @param widthOffset   The width offset.
     * @param heigthOffset  The height offset.
     */
    public void deflateSize(final int widthOffset, final int heigthOffset) {
        inflateSize(-widthOffset, -heigthOffset);
    }

    /**
     * Deflates size: subtracts the given offset from metrics.
     * @param offset The common offset.
     */
    public void DeflateSize(final int offset) {
        deflateSize(offset, offset);
    }

    /**
     * Returns aspect ratio (width / height).
     * @return The aspect ratio.
     */
    public double getAspectRatio() {
        return (double) w / (double) h;
    }

    public int w;
    public int h;
    
}
