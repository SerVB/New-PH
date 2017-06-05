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

package Common;

import utils.tracer;
import java.util.ArrayList;

/**
 *
 */
public class iSpannedMap {
    // Shape
    public final static int Circle = 0;
    public final static int Square = 1;

    /**
     * Хранилище
     */
    private ArrayList<iSpan> m_SpanList = new ArrayList();

    /**
     * Constructs with circle or square spaned map
     * @param shape
     * @param radius
     */
    public iSpannedMap(int shape, int radius) {
        tracer.check(0 <= radius);

        switch (shape) {
            case Circle:
                MakeCircleSpan(radius);
                break;
            case Square:
                MakeSquareSpan(radius);
                break;
            default:
                tracer.check(0);
                break;
        }
    }

    /**
     *
     * @param shape
     */
    public iSpannedMap(int shape) {
        this(shape, 1);
    }

    /**
     *
     */
    public iSpannedMap() {
        this(Square);
    }

    /**
     *
     * @return
     */
    public long SpanLinesCount() {
        return m_SpanList.size();
    }

    /**
     *
     * @param idx
     * @return
     */
    public iSpan get(int idx) {
        tracer.check( 0 <= idx && idx < m_SpanList.size() );
        return m_SpanList.get(idx);
    }

    /**
     *
     * @param radius
     */
    private void MakeCircleSpan(int radius) {
//        m_SpanList.SetSize(radius*2+1);

        int cx    = 0;
        int cy    = radius;
        int df    = 1 - radius;
        int d_e   = 3;
        int d_se  = -2 * radius + 5;

        // Filled circle
        do {
            m_SpanList.set(radius-cx, new iSpan(-cx,-cy,cy));

            if ( cx > 0 )
                m_SpanList.set(radius+cx, new iSpan(cx,-cy,cy));

            if ( df < 0 ) {
                df   += d_e;
                d_e  += 2;
                d_se += 2;
            } else {
                if( cx != cy ) {
                    m_SpanList.set(radius-cy, new iSpan(-cy,-cx,cx));
                    if ( cy > 0 )
                        m_SpanList.set(radius+cy, new iSpan(cy,-cx,cx));
                }
                df   += d_se;
                d_e  += 2;
                d_se += 4;
                cy--;
            }
            cx++;
        } while ( cx <= cy );
    }

    /**
     *
     * @param radius
     */
    private void MakeSquareSpan(int radius) {
        int cnt = 2*radius + 1;
//        m_SpanList.SetSize(cnt);

        for (int yy = 0; yy < cnt; ++yy) {
            m_SpanList.get(yy).ypos  = -radius + yy;
            m_SpanList.get(yy).begin = -radius;
            m_SpanList.get(yy).end   = radius;
        }
    }
}
