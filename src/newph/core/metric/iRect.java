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

import newph.core.type.Hash;

/**
 * Rectangle class.
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iRect {

    /**
     * The "x" (horizontal) coordinate of the top-left corner. 
     */
    public int x;

    /**
     * The "y" (vertical) coordinate of the top-left corner. 
     */
    public int y;

    /**
     * The width (horizontal size).
     */
    public int w;
    
    /**
     * The height (vertical size).
     */
    public int h;

    /**
     * Constructs the Rectangle object with metrics of zero.
     */
    public iRect() {
        x = 0;
        y = 0;
        
        w = 0;
        h = 0;
    }
    
    /**
     * Constructs the Rectangle object with given coordinates of the left top corner, the width and the height.
     * @param x The "x" (horizontal) coordinate of the left top corner.
     * @param y The "y" (vertical) coordinate of the left top corner.
     * @param w The width of the Rectangle.
     * @param h The height of the Rectangle.
     */
    public iRect(final int x, final int y, final int w, final int h) {
        this.x = x;
        this.y = y;
        
        this.w = w;
        this.h = h;
    }

    /**
     * Constructs the Rectangle object with the given Point of the left-top corner and Size.
     * @param point The Point of the left-top corner.
     * @param size  The Size.
     */
    public iRect(final iPoint point, final iSize size) {
        this(point.x, point.y, size.w, size.h);
    }

    /**
     * Constructs the Rectangle object with two given corner Points.
     * @param p1 The first Point.
     * @param p2 The second Point.
     */
    public iRect(final iPoint p1, final iPoint p2) {
        final int min_x = Math.min(p1.x, p2.x);
        final int min_y = Math.min(p1.y, p2.y);
        final int max_x = Math.max(p1.x, p2.x);
        final int max_y = Math.max(p1.y, p2.y);

        x = min_x;
        y = min_y;
        w = (max_x - min_x) + 1;
        h = (max_y - min_y) + 1;
    }

    /**
     * Constructs the Rectangle object with the specified Size. 
     * The left-top corner of the Rectangle sets to the origin.
     * @param size The specified size.
     */
    public iRect(final iSize size) {
        x = 0;
        y = 0;
        
        w = size.w;
        h = size.h;
    }

    /**
     * Constructs the Rectangle object by copying the other Rectangle.
     * @param other The other Rectangle.
     */
    public iRect(final iRect other) {
        set(other);
    }

    /**
     * Returns the "x" coordinate of the left-top corner.
     * @return The "x" coordinate of the left-top corner.
     */
    public int x1() { 
        return x; 
    }
    
    /**
     * Returns the "y" coordinate of the left-top corner.
     * @return The "y" coordinate of the left-top corner.
     */
    public int y1() { 
        return y; 
    }

    /**
     * Returns the "x" coordinate of the right-bottom corner.
     * @return The "x" coordinate of the right-bottom corner.
     */
    public int x2() { 
        return x + w - 1; 
    }
    
    /**
     * Returns the "y" coordinate of the right-bottom corner.
     * @return The "y" coordinate of the right-bottom corner.
     */
    public int y2() { 
        return y + h - 1; 
    }

    /**
     * Returns the center Point of the Rectangle.
     * @return The center Point of the Rectangle.
     */
    public iPoint Center() {
        return new iPoint(x + w / 2, y + h / 2);
    }

    /**
     * Returns the Point of the right-top corner.
     * @return The Point of the right-top corner.
     */
    public iPoint TopRight() {
        return new iPoint(x2(), y);
    }

    /**
     * Returns the Point of the left-top corner.
     * @return The Point of the left-top corner.
     */
    public iPoint TopLeft() {
        return new iPoint(x, y);
    }

    /**
     * Returns the Point of the right-bottom corner.
     * @return The Point of the right-bottom corner.
     */
    public iPoint BottomRight() {
        return new iPoint(x2(), y2());
    }

    /**
     * Returns the Point of the left-bottom corner.
     * @return The Point of the left-bottom corner.
     */
    public iPoint BottomLeft() {
        return new iPoint(x, y2());
    }

    /**
     * Returns the Size of the Rectangle.
     * @return The Size of the Rectangle.
     */
    public iSize size() { 
        return new iSize(w, h); 
    }

    /**
     * Returns the Point of the left-top corner.
     * @return The Point of the left-top corner.
     */
    public iPoint point() { 
        return new iPoint(x, y); 
    }

    /**
     * Determines whether the specified point lies within the Rectangle. 
     * A point is within a rectangle if it lies on the left or top side or is within all four sides.
     * @param _x    The "x" coordinate of the point.
     * @param _y    The "y" coordinate of the point.
     * @return      {@code True} if the Point lies within the Rectangle, {@code false} otherwise.
     */
    public boolean PtInRect(final int _x, final int _y) { 
        return 
                x <= _x && _x <= x + w - 1 &&
                y <= _y && _y <= y + h - 1;
    }

    /**
     * Determines whether the specified point lies within the Rectangle. 
     * A point is within a rectangle if it lies on the left or top side or is within all four sides.
     * @param point The Point.
     * @return      {@code True} if the Point lies within the Rectangle, {@code false} otherwise.
     */
    public boolean PtInRect(final iPoint point) { 
        return PtInRect(point.x, point.y);
    }

    /**
     * Resets the Rectangle's metrics to zero.
     */
    public void Reset() {
        x = y = w = h = 0;
    }

    /**
     * Validates the width and the height of the Rectangle.
     * @return {@code True} if at least one metric is equal to zero, {@code false} otherwise.
     */
    public boolean IsEmpty() {
        return w == 0 || h == 0;
    }

    /**
     * Constructs the new Rectangle object: adds the Point to the Rectangle.
     * Doesn't modify the source Rectangle and Point.
     * Ex. operator+.
     * @param rect  The Rectangle.
     * @param pos   The Point.
     * @return      The new Rectangle.
     */
    public static iRect addToRect(final iRect rect, final iPoint pos) {
        return new iRect(rect.x + pos.x, rect.y + pos.y, rect.w, rect.h);
    }

    /**
     * Constructs the new Rectangle object: subtracts the Point from the Rectangle.
     * Doesn't modify the source Rectangle and Point.
     * Ex. operator-.
     * @param rect  The Rectangle.
     * @param pos   The Point.
     * @return      The new Rectangle.
     */
    public static iRect subtractFromRect(final iRect rect, final iPoint pos) {
        return new iRect(rect.x - pos.x, rect.y - pos.y, rect.w, rect.h);
    }

    /**
     * Constructs the new Rectangle object: adds two Rectangles.
     * Doesn't modify these two Rectangles.
     * Ex. operator+.
     * @param first     The first Rectangle.
     * @param second    The second Rectangle.
     * @return          The new Rectangle.
     */
    public static iRect addToRect(final iRect first, final iRect second) {
        iRect res = new iRect(first);
        res.add(second);
        
        return  res;
    }

    /**
     * Changes the Rectangle: adds the other Rectangle's metrics.
     * Ex. operator+=.
     * @param other The other Rectangle.
     */
    public void add(final iRect other) {
        if (other.IsEmpty()) {
            return;
        }
        
        if (IsEmpty()) {
            set(other);
        }
        
        final int min_x = Math.min(other.x, x);
        final int min_y = Math.min(other.y, y);

        final int max_x = Math.max(other.x2(), x2());
        final int max_y = Math.max(other.y2(), y2());

        x = min_x;
        y = min_y;
        w = max_x - min_x + 1;
        h = max_y - min_y + 1;
    }
    
    /**
     * Inflates the rectangle. Subtracts offset from each metric.
     * @param left      The left offset.
     * @param top       The top offset.
     * @param right     The right offset.
     * @param bottom    The bottom offset.
     */
    public void InflateRect(
            final int left,
            final int top,
            final int right,
            final int bottom
    ) {
        x -= left;
        y -= top;
        w += left + right;
        h += top + bottom;
    }
    
    /**
     * Inflates the rectangle. Subtracts offset from each metric.
     * @param x_offs The horizontal offset.
     * @param y_offs The vertical offset.
     */
    public void InflateRect(final int x_offs, final int y_offs) {
        InflateRect(x_offs, y_offs, x_offs, y_offs);
    }
    
    /**
     * Inflates the rectangle. Subtracts offset from each metric.
     * @param offs The offset.
     */
    public void InflateRect(final int offs) {
        InflateRect(offs, offs);
    }

    /**
     * Deflates the rectangle. Subtracts offset from each metric.
     * @param left      The left offset.
     * @param top       The top offset.
     * @param right     The right offset.
     * @param bottom    The bottom offset.
     */
    public void DeflateRect(
            final int left,
            final int top,
            final int right,
            final int bottom
    ) {
        InflateRect(-left, -top, -right, -bottom);
//        x += left;
//        y += top;
//        w -= (left+right);
//        h -= (top+bottom);
    }
    
    /**
     * Deflates the rectangle. Subtracts offset from each metric.
     * @param x_offs The horizontal offset.
     * @param y_offs The vertical offset.
     */
    public void DeflateRect(final int x_offs, final int y_offs) {
        DeflateRect(x_offs, y_offs, x_offs, y_offs);
    }

    /**
     * Deflates the rectangle. Subtracts offset from each metric.
     * @param offs The offset.
     */
    public void DeflateRect(final int offs) {
        DeflateRect(offs, offs);
    }
    
    /**
     * Sets the metrics to the other object's metrics.
     * @param rect The other object.
     */
    public void set(final iRect rect) {
        x = rect.x;
        y = rect.y;
        
        w = rect.w;
        h = rect.h;
    }

    @Override
    public int hashCode() {
        Hash hash = Hash.std();
        hash.insert(x);
        hash.insert(y);
        hash.insert(w);
        hash.insert(h);
        return hash.getResult();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final iRect other = (iRect) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.w != other.w) {
            return false;
        }
        if (this.h != other.h) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iRect{" + "x=" + x + ", y=" + y + ", w=" + w + ", h=" + h + '}';
    }
    
}
