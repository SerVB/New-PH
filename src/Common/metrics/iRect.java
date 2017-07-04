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

import newph.core.metric.iPoint;
import Constants.metrics.*;

/**
 * Прямоугольник.
 */
public class iRect {

    public int x;
    public int y;
    public int w;
    public int h;

    public void setRect(final int x, final int y, final int w, final int h) {
        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;
    }

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

    public iRect(final iPoint p1, final iPoint p2) {
        int min_x = Math.min(p1.x, p2.x);
        int min_y = Math.min(p1.y, p2.y);
        int max_x = Math.max(p1.x, p2.x);
        int max_y = Math.max(p1.y, p2.y);

        x = min_x;
        y = min_y;

        w = (max_x - min_x) + 1;
        h = (max_y - min_y) + 1;
    }

    public iRect(final iSize size) {
        this.x = 0;
        this.y = 0;

        this.w = size.w;
        this.h = size.h;
    }

    public iRect(final iRect other) {
        this.x = other.x;
        this.y = other.y;

        this.w = other.w;
        this.h = other.h;
    }

    public int x1() {
        return x;
    }

    public int y1() {
        return y;
    }

    public int x2() {
        return x + w - 1;
    }

    public int y2() {
        return y + h - 1;
    }

    public iPoint getCenter() {
        return new iPoint(x + w / 2, y + h / 2);
    }

    public iPoint getTopRight() {
        return new iPoint(x2(), y);
    }

    public iPoint getTopLeft() {
        return new iPoint(x, y);
    }

    public iPoint getBottomRight() {
        return new iPoint(x2(), y2());
    }

    public iPoint getBottomLeft() {
        return new iPoint(x, y2());
   }

    public iSize getSize() {
        return new iSize(w, h);
    }

    public iPoint getPoint() {
        return new iPoint(x, y);
    }

    public boolean isPtInRect(final int x, final int y) {
        return (
                this.x <= x &&
                this.y <= y &&

                x <= this.x + this.w - 1 &&
                y <= this.y + this.h - 1
        );
    }

    public boolean isPtInRect(final iPoint pnt) {
        return isPtInRect(pnt.x, pnt.y);
    }

    public void reset() {
        x = y = w = h = 0;
    }

    public boolean isEmpty() {
        return (w==0 || h==0);
    }

    public boolean equals(final iRect other) {
        return (
                this.x == other.x &&
                this.y == other.y &&

                this.w == other.w &&
                this.h == other.h
        );
    }

    public iRect(final iRect rect, final char oper, final iPoint point) {
        switch (oper) {
            case '+':
                this.x = rect.x + point.x;
                this.y = rect.y + point.y;

                this.w = rect.w;
                this.h = rect.h;
                break;
            case '-':
                this.x = rect.x - point.x;
                this.y = rect.y - point.y;

                this.w = rect.w;
                this.h = rect.h;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    public iRect(final iRect first, final char oper, final iRect second) {
        switch (oper) {
            case '+':
                this.x = first.x + second.x;
                this.y = first.y + second.y;

                this.w = first.w + second.w;
                this.h = first.h + second.h;
                break;
            case '-':
                this.x = first.x - second.x;
                this.y = first.y - second.y;

                this.w = first.w - second.w;
                this.h = first.h - second.h;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    public void add(final iPoint point) {
        this.x += point.x;
        this.y += point.y;
    }

    public void add(final iRect rect) {
        int min_x = Math.min(rect.x, this.x);
        int min_y = Math.min(rect.y, this.y);

        int max_x = Math.max(rect.x2(), this.x2());
        int max_y = Math.max(rect.y2(), this.y2());

        x = min_x;
        y = min_y;

        w = max_x - min_x + 1;
        h = max_y - min_y + 1;
    }

    public void inflateRect(int left, int top, int right, int bottom) {
        x -= left;
        y -= top;

        w += left + right;
        h += top + bottom;
    }

    public void inflateRect(int x_offs, int y_offs) {
        inflateRect(x_offs, y_offs, x_offs, y_offs);
    }

    public void inflateRect(int offs) {
        inflateRect(offs, offs, offs, offs);
    }

    public void deflateRect(int left, int top, int right, int bottom) {
        x += left;
        y += top;

        w -= (left + right);
        h -= (top + bottom);
    }

    public void deflateRect(int x_offs, int y_offs) {
        deflateRect(x_offs, y_offs, x_offs, y_offs);
    }

    public void deflateRect(int offs) {
        deflateRect(offs, offs, offs, offs);
    }

    /**
     * Returns rectangle with specified size aligned in specified dst rect.
     * @param ss
     * @param dr
     * @param al Alignment.
     * @return
     */
    iRect AlignRect(final iSize ss, final iRect dr, final int al) {
        iRect orc = new iRect(ss);

        int sw = ss.w;
        int sh = ss.h;
        int dw = dr.w;
        int dh = dr.h;

        // Vertical alignment
        if ((al & Alignment.AlignTop) != 0) {
            orc.y = dr.y;
        } else if ((al & Alignment.AlignBottom) != 0) {
            orc.y = dr.y + dh - sh;
        } else {
            orc.y = dr.y + ((dh>>1) - (sh>>1));
        }

        // Horizontal alignment
        if ((al & Alignment.AlignLeft) != 0) {
            orc.x = dr.x;
        } else if ((al & Alignment.AlignRight) != 0) {
            orc.x = dr.x + dw - sw;
        } else {
            orc.x = dr.x + ((dw>>1) - (sw>>1));
        }

        return orc;
    }

    /**
     * Converts Size to Rect.
     * @deprecated Use {@link #iRect(Common.metrics.iSize)} instead!
     * @param size Size to convert.
     * @return Converted to Size Rect.
     */
    public final static iRect sizeToRect(final iSize size) {
        return new iRect(0, 0, size.w, size.h);
    }
    
}
