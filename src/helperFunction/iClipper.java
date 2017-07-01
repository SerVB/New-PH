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
package helperFunction;

import newph.metric.iPoint;
import Common.metrics.iRect;
import newph.servb.Changeable;

/**
 *
 */
public class iClipper {

    private static int pow2(final int val) {
        return val * val;
    }

    public static boolean isLineIntersectCircle(final iPoint cp, final int cr, final iPoint p1, final iPoint p2) {
        int x01 = p1.x - cp.x;
        int y01 = p1.y - cp.y;
        int x02 = p2.x - cp.x;
        int y02 = p2.y - cp.y;

        int dx = x02 - x01;
        int dy = y02 - y01;

        int a = pow2(dx) + pow2(dy);
        int b = 2 * (x01 * dx + y01 * dy);
        int c = pow2(x01) + pow2(y01) - pow2(cr);

        if (-b < 0) {
            return c < 0;
        } else if ( -b < (2 * a) ) {
            return (4 * a * c - pow2(b)) < 0;
        } else {
            return (a + b + c) < 0;
        }

//        if ( !iRect(p1,p2).PtInRect(cp) ) return false;
//
//        float dist = (( (p1.y - p2.y) * cp.x + (p2.x - p1.x) * cp.y + (p1.x * p2.y - p2.x * p1.y)) / sqrt( (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y) ));
//        if (dist < 0 ) dist = -dist;
//
//        bool res = cr > dist;
//        OutputDebugString(iFormat(_T("Circle (%d, %d, %d), Line (%d, %d . %d, %d) : %f\n"), cp.x, cp.y, cr, p1.x, p1.y, p2.x, p2.y, dist).CStr());
//        return res;

    }

    public static boolean clipPoint(final iPoint pnt, final iRect rect) {
        pnt.x = MathOperations.clamp(rect.x, rect.x2(), pnt.x);
        pnt.y = MathOperations.clamp(rect.y, rect.y2(), pnt.y);
        return true;
    }

    public static boolean clipHLine(final iPoint pnt1, final Changeable<Integer> x2, final iRect rect) {

        if (pnt1.y < rect.y || pnt1.y > rect.y2()) {
            return false;
        }

        pnt1.x   = MathOperations.clamp(rect.x, rect.x2(), pnt1.x);

        x2.value = MathOperations.clamp(rect.x, rect.x2(), x2.value);

        return pnt1.x != x2.value;

    }

    public static boolean clipVLine(final iPoint pnt1, final Changeable<Integer> y2, final iRect rect) {

        if (pnt1.x < rect.x || pnt1.x > rect.x2()) {
            return false;
        }

        pnt1.y   = MathOperations.clamp(rect.y, rect.y2(), pnt1.y);
        y2.value = MathOperations.clamp(rect.y, rect.y2(), y2.value);

        return pnt1.y != y2.value;

    }

    private static void CLIPCODE(final iPoint p, final Changeable<Integer> c, final iRect clip) {
        c.value = 0;
        if ( (p.x) < clip.x1() )    c.value |= 8;
        if ( (p.x) > clip.x2() )    c.value |= 4;
        if ( (p.y) < clip.y1() )    c.value |= 2;
        if ( (p.y) > clip.y2() )    c.value |= 1;
    }

    public static boolean clipLine(final iPoint pnt1, final iPoint pnt2, final iRect rect) {
        Changeable<Integer> c1 = new Changeable(-42);
        Changeable<Integer> c2 = new Changeable(-42);
        iPoint d = new iPoint();
        iRect clip = new iRect(rect);

        CLIPCODE( pnt1, c1, clip );
        CLIPCODE( pnt2, c2, clip );

        while ( c1.value != 0 || c2.value != 0 ) {
            if ( (c1.value & c2.value) != 0 ) {
                return false;
            }
            d.x = pnt2.x - pnt1.x;
            d.y = pnt2.y - pnt1.y;
            if ( c1.value != 0 ) {
                if ( pnt1.x < clip.x1() ) {
                    pnt1.y += d.y * ( clip.x1() - pnt1.x ) / d.x;
                    pnt1.x = clip.x1();
                } else if ( pnt1.x > clip.x2() ) {
                    pnt1.y += d.y * ( clip.x2() - pnt1.x ) / d.x;
                    pnt1.x = clip.x2();
                } else if ( pnt1.y < clip.y1() ) {
                    pnt1.x += d.x * ( clip.y1() - pnt1.y ) / d.y;
                    pnt1.y = clip.y1();
                } else if ( pnt1.y > clip.y2() ) {
                    pnt1.x += d.x * ( clip.y2() - pnt1.y ) / d.y;
                    pnt1.y = clip.y2();
                }
                CLIPCODE( pnt1, c1, clip );
            } else {
                if ( pnt2.x < clip.x1() ) {
                    pnt2.y += d.y * ( clip.x1() - pnt2.x ) / d.x;
                    pnt2.x = clip.x1();
                } else if ( pnt2.x > clip.x2() ) {
                    pnt2.y += d.y * ( clip.x2() - pnt2.x ) / d.x;
                    pnt2.x = clip.x2();
                } else if ( pnt2.y < clip.y1() ) {
                    pnt2.x += d.x * ( clip.y1() - pnt2.y ) / d.y;
                    pnt2.y = clip.y1();
                } else if ( pnt2.y > clip.y2() ) {
                    pnt2.x += d.x * ( clip.y2() - pnt2.y ) / d.y;
                    pnt2.y = clip.y2();
                }
                CLIPCODE( pnt2, c2, clip );
            }
        }
        return true;
    }

    public static boolean intersectRect(final iRect dst_rect, final iRect src_rect1, final iRect src_rect2) {
        int x1 = Math.max(src_rect1.x, src_rect2.x);
        int y1 = Math.max(src_rect1.y, src_rect2.y);
        int x2 = Math.min(src_rect1.x2(), src_rect2.x2()) + 1;
        int y2 = Math.min(src_rect1.y2(), src_rect2.y2()) + 1;

        if ( (x2 - x1) > 0 && (y2 - y1) > 0 ) {
            dst_rect.setRect(x1, y1, x2-x1, y2-y1);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isIntersectRect(final iRect src_rect1, final iRect src_rect2) {
        int x1 = Math.max(src_rect1.x, src_rect2.x);
        int y1 = Math.max(src_rect1.y, src_rect2.y);
        int x2 = Math.min(src_rect1.x2(), src_rect2.x2()) + 1;
        int y2 = Math.min(src_rect1.y2(), src_rect2.y2()) + 1;

        return ( (x2 - x1) > 0 && (y2 - y1) > 0 );
    }

    public static boolean clipRect(final iRect rc, final iRect rect) {
        if (rc.x < rect.x) {
            int val = rect.x - rc.x;
            if (val > rc.w) {
                return false;
            }
            rc.w -= val;
            rc.x = rect.x;
        }

        if (rc.y < rect.y) {
            int val = rect.y - rc.y;
            if (val > rc.h) {
                return false;
            }
            rc.h -= val;
            rc.y = rect.y;
        }

        if (rect.x2() < rc.x2()) {
            int val = rc.x2() - rect.x2();
            if (val > rc.w) {
                return false;
            }
            rc.w -= val;
        }

        if (rect.y2() < rc.y2()) {
            int val = rc.y2() - rect.y2();
            if (val > rc.h) {
                return false;
            }
            rc.h -= val;
        }
        return (rc.w > 0 && rc.h > 0);
    }

    public static boolean iClipRectRect(
            final iRect dst_rc,
            final iRect dst_rect,
            final iRect src_rc,
            final iRect src_rect
    ) {

        //iSize cl = src_rc;
        int clw = src_rc.w;
        int clh = src_rc.h;

        // check ridiculous cases
        if (!isIntersectRect(dst_rc,dst_rect) || !isIntersectRect(src_rc,src_rect)) {
            return false;
        }

        // clip src left
        if ( src_rc.x < 0 ) {
            clw += src_rc.x;
            dst_rc.x -= src_rc.x;
            src_rc.x = 0;
        }
        // clip src top
        if ( src_rc.y < 0 ) {
            clh += src_rc.y;
            dst_rc.y -= src_rc.y;
            src_rc.y = 0;
        }
        // clip src right
        if ( src_rc.x + clw > src_rect.w ) {
            clw = src_rect.w - src_rc.x;
        }
        // clip src bottom
        if ( src_rc.y + clh > src_rect.h ) {
            clh = src_rect.h - src_rc.y;
        }
        // clip dest left
        if ( dst_rc.x < dst_rect.x ) {
            dst_rc.x -= dst_rect.x;
            clw += dst_rc.x;
            src_rc.x -= dst_rc.x;
            dst_rc.x = dst_rect.x;
        }
        // clip dest top
        if ( dst_rc.y < dst_rect.y ) {
            dst_rc.y -= dst_rect.y;
            clh += dst_rc.y;
            src_rc.y -= dst_rc.y;
            dst_rc.y = dst_rect.y;
        }
        // clip dest right
        if ( dst_rc.x + clw >= dst_rect.x2()+1 ) {
            clw = dst_rect.x2() - dst_rc.x + 1;
        }
        // clip dest bottom
        if ( dst_rc.y + clh >= dst_rect.y2()+1 ) {
            clh = dst_rect.y2() - dst_rc.y + 1;
        }
        // bail out on zero size
        if ( ( clw <= 0 ) || ( clh <= 0 ) ) {
            return false;
        }

        dst_rc.w = clw;
        dst_rc.h = clh;

        src_rc.w = clw;
        src_rc.h = clh;

        return true;
    }

}
