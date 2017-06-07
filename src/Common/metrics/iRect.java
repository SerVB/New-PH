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
 * Прямоугольник.
 */
public class iRect {

    public int x;
    public int y;
    public int w;
    public int h;

//    public iRect(int x, int y, int w, int h) {
//        this.x = x;
//        this.y = y;
//        this.w = w;
//        this.h = h;
//    }
//
//    public iRect(iSize size) {
//        this(0, 0, size.w, size.h);
//    }
//
//    public iRect(iRect other) {
//        this(other.x, other.y, other.w, other.h);
//    }
//
//    public void DeflateRect(int Int) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public int y2() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public iPoint point() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void plus(iRect other) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void plus(iPoint point) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public iSize size() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public boolean PtInRect(iPoint pos) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

    public iRect() {
        this.x = 0;
        this.y = 0;

        this.w = 0;
        this.h = 0;
    }

    public iRect(final int x, final int y, final int w, final int h) {
        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;
    }

    public iRect(final iPoint point, final iSize size) {
        this.x = point.x;
        this.y = point.y;

        this.w = size.w;
        this.h = size.h;
    }

    public iRect (final iPoint p1, final iPoint p2) {
        int min_x = Math.min(p1.x, p2.x);
        int min_y = Math.min(p1.y, p2.y);
        int max_x = Math.max(p1.x, p2.x);
        int max_y = Math.max(p1.y, p2.y);

        x = min_x;
        y = min_y;
        w = (max_x - min_x) + 1;
        h = (max_y - min_y) + 1;
    }

    public iRect ( const iSize& size )
    : iSize(size) {}

    public iRect ( const RECT& rect )
    : iPoint(rect.left,rect.top), iSize(rect.right-rect.left, rect.bottom-rect.top) {}

    public inline sint32 x1 () const
    { return x; }

    public inline sint32 y1 () const
    { return y; }

    public inline sint32 x2 () const
    { return x+w-1; }

    public inline sint32 y2 () const
    { return y+h-1; }

    public inline iPoint Center() const
    { return iPoint(x+w/2,y+h/2); }

    public inline iPoint TopRight() const
    { return iPoint(x2(),y); }

    public inline iPoint TopLeft() const
    { return iPoint(x,y); }

    public inline iPoint BottomRight() const
    { return iPoint(x2(),y2()); }

    public inline iPoint BottomLeft() const
    { return iPoint(x,y2()); }

    public inline iSize size () const
    { return iSize(w,h); }

    public inline iPoint point () const
    { return iPoint(x,y); }

    public inline bool PtInRect(const sint32 _x, const sint32 _y) const
    { return (_x>=x && _y>=y && _x<sint32(x+w) && _y<sint32(y+h)); }

    public inline bool PtInRect(const iPoint& pnt) const
    { return PtInRect(pnt.x,pnt.y); }

    public void Reset()
    { x=y=w=h=0; }

    public inline bool IsEmpty() const { return (w==0 || h==0); }

    public operator RECT() const
    {
        RECT rect={x,y,x+w,y+h};
        return rect;
    }

    public inline bool operator== (const iRect &rect) const
    { return ((x==rect.x) && (y==rect.y) && (w==rect.w) && (h==rect.h)); }

    public inline bool operator!= (const iRect &rect) const
    { return ! operator == ( rect ); }

    public inline iRect operator + (const iPoint &pos) const
    { return (iRect(x+pos.x,y+pos.y,w,h)); }

    public inline iRect operator - (const iPoint &pos) const
    { return (iRect(x-pos.x,y-pos.y,w,h)); }

    public inline iRect operator + (const iRect &rect) const
    {
        iRect rc( *this );
        return  rc += rect;
    }

    public iRect& operator += (const iPoint &point)
    {
        x += point.x;
        y += point.y;
        return *this;
    }

    public iRect& operator += (const iRect &rect)
    {
        if (IsEmpty()) *this = rect;

        sint32 min_x = iMIN(rect.x, x);
        sint32 min_y = iMIN(rect.y, y);

        sint32 max_x = iMAX(rect.x2(), x2());
        sint32 max_y = iMAX(rect.y2(), y2());

        x = min_x;
        y = min_y;
        w = max_x-min_x+1;
        h = max_y-min_y+1;

        return *this;
    }

    public inline void InflateRect(uint32 left, uint32 top, uint32 right, uint32 bottom)
    {
        x -= left;
        y -= top;
        w += (left+right);
        h += (top+bottom);
    }

    public inline void InflateRect(uint32 x_offs, uint32 y_offs)
    { InflateRect(x_offs,y_offs,x_offs,y_offs); }

    public inline void InflateRect(uint32 offs)
    { InflateRect(offs,offs,offs,offs); }

    public inline void DeflateRect(uint32 left, uint32 top, uint32 right, uint32 bottom)
    {
        x += left;
        y += top;
        w -= (left+right);
        h -= (top+bottom);
    }

    public inline void DeflateRect(uint32 x_offs, uint32 y_offs)
    { DeflateRect(x_offs,y_offs,x_offs,y_offs); }

    public inline void DeflateRect(uint32 offs)
    { DeflateRect(offs,offs,offs,offs); }

    // Returns rectangle with specified size aligned in specified dst rect
iRect AlignRect(const iSize& ss, const iRect dr, Alignment al);
}
