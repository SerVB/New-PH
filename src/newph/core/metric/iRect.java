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
 * Rectangle class.
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iRect {
    
    public int x;
    public int y;
    public int w;
    public int h;

    /// Default constructor
    public iRect() {
        x = 0;
        y = 0;
        
        w = 0;
        h = 0;
    }
    
    /// Constructs iRect object with given coordinate of left top corner, width and height
    public iRect(final int x, final int y, final int w, final int h) {
        this.x = x;
        this.y = y;
        
        this.w = w;
        this.h = h;
    }

    /// Constructs iRect object with given coordinate of left top corner, width and height
    public iRect(final iPoint point, final iSize size) {
        x = point.x;
        y = point.y;
        
        w = size.w;
        h = size.h;
    }

    /// Constructs iRect object with two given coordinates
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

    /// Constructs iRect object with specified size
    public iRect(final iSize size) {
        x = 0;
        y = 0;
        
        w = size.w;
        h = size.h;
    }

    /// Constructs iRect object with RECT structure
    public iRect(final iRect rect) {
        set(rect);
    }

    /// Returns x coordinate of left-top corner
    public int x1() { 
        return x; 
    }

    /// Returns y coordinate of left-top corner
    public int y1() { 
        return y; 
    }

    /// Returns x coordinate of right-bottom corner
    public int x2() { 
        return x + w - 1; 
    }
    
    /// Returns y coordinate of right-bottom corner
    public int y2() { 
        return y + h - 1; 
    }

    /// Returns center point of rect
    public iPoint Center() {
        return new iPoint(x + w / 2, y + h / 2);
    }

    /// Returns coordinate of right-top corner
    public iPoint TopRight() {
        return new iPoint(x2(), y);
    }

    /// Returns coordinate of left-top corner
    public iPoint TopLeft() {
        return new iPoint(x, y);
    }

    /// Returns coordinate of right-bottom corner
    public iPoint BottomRight() {
        return new iPoint(x2(), y2());
    }

    /// Returns coordinate of left-bottom corner
    public iPoint BottomLeft() {
        return new iPoint(x, y2());
    }

    /// Returns size of rect
    public iSize size() { 
        return new iSize(w, h); 
    }

    /// Returns coordinate of left-top corner
    public iPoint point() { 
        return new iPoint(x,y); 
    }

    /// @brief Determines whether the specified point lies within the specified rectangle. 
    /// A point is within a rectangle if it lies on the left or top side or is within all four sides. 
    public boolean PtInRect(final int _x, final int _y) { 
        return 
                x <= _x &&
                y <= _y &&
                _x <= x + w - 1 &&
                _y <= y + h - 1;
    }

    /// @brief Determines whether the specified point lies within the specified rectangle. 
    /// A point is within a rectangle if it lies on the left or top side or is within all four sides. 
    public boolean PtInRect(final iPoint pnt) { 
        return PtInRect(pnt.x, pnt.y);
    }

    /// Resets rect variables to zero
    public void Reset() {
        x = y = w = h = 0;
    }

    /// Validates width and height of the rect
    public boolean IsEmpty() {
        return w == 0 || h == 0;
    }
    
    /// Equality operator
    public boolean equals(final iRect other) {
        return 
                x == other.x &&
                y == other.y &&
                w == other.w &&
                h == other.h;
    }

    /// operator +
    public static iRect addToRect(final iRect rect, final iPoint pos) {
        return new iRect(rect.x + pos.x, rect.y + pos.y, rect.w, rect.h);
    }

    /// operator -
    public static iRect subtractFromRect(final iRect rect, final iPoint pos) {
        return new iRect(rect.x - pos.x, rect.y - pos.y, rect.w, rect.h);
    }

    /// operator +
    public static iRect addToRect(final iRect rect, final iRect other) {
        iRect res = new iRect(rect);
        res.add(other);
        
        return  res;
    }

    /// operator +=
    public void add(final iRect rect) {
        if (rect.IsEmpty()) {
            return;
        }
        
        if (IsEmpty()) {
            set(rect);
        }
        
        final int min_x = Math.min(rect.x, x);
        final int min_y = Math.min(rect.y, y);

        final int max_x = Math.max(rect.x2(), x2());
        final int max_y = Math.max(rect.y2(), y2());

        x = min_x;
        y = min_y;
        w = max_x - min_x + 1;
        h = max_y - min_y + 1;
    }

    /// Inflates rect
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

    /// Inflates rect
    public void InflateRect(final int x_offs, final int y_offs) {
        InflateRect(x_offs, y_offs, x_offs, y_offs);
    }

    /// Inflates rect
    public void InflateRect(final int offs) {
        InflateRect(offs, offs, offs, offs);
    }

    /// Deflates rect
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

    /// Deflates rect
    public void DeflateRect(final int x_offs, final int y_offs) {
        DeflateRect(x_offs, y_offs, x_offs, y_offs);
    }

    /// Deflates rect
    public void DeflateRect(final int offs) {
        DeflateRect(offs, offs, offs, offs);
    }
    
    public void set(final iRect rect) {
        x = rect.x;
        y = rect.y;
        
        w = rect.w;
        h = rect.h;
    }
    
}
