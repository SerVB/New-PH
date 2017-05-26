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

/**
 * ISOMETRIC_PROJECTION_HANDLING
 */
public class iIsoMetric {
    final static long ATOM_WIDTH = 8;
    final static long ATOM_HEIGHT = 3;

    private final long m_CellFactor;


    /**
     * Конструктор.
     * @param cf Cell factor of isometric cell (cf = CellWidth/ATOM_WIDTH).
     */
    public iIsoMetric(long cf) {
        this.m_CellFactor = cf;
    }

    public long GetCellWidth(long cf) {
        return cf * ATOM_WIDTH;
    }

    public long GetCellHeight(long cf) {
        return (GetCellWidth(cf) - 1) / 2;
    }

    public long GetCellWidth() {
        return GetCellWidth(m_CellFactor);
    }

    public long GetCellHeight() {
        return GetCellHeight(m_CellFactor);
    }

    public iSize GetCellSize() {
        return new iSize(GetCellWidth(), GetCellHeight());
    }

    public long GetCellStepX() {
        return GetCellWidth() / 2;
    }

    public long GetCellStepY() {
        return (GetCellHeight() + 1) / 2;
    }

    public iPoint GetScreenOffset(final iPoint cellOffset) {
        return new iPoint((cellOffset.x - cellOffset.y) * GetCellStepX(),
                          (cellOffset.x + cellOffset.y) * GetCellStepY());
    }

    public iPoint Map2Screen(final iPoint pos) {
        long n = m_CellFactor * 2;
        return new iPoint( (-2* n * pos.y) + (2 * n * pos.x),
                           (    n * pos.y) + (    n * pos.x));
    }

    public iPoint Screen2Map(final iPoint pos) {
        long n    = 2 * m_CellFactor;
        long coef = 4 * n;

        long n2 = n * 2;
        long n4 = n * 4;

        long px = coef * pos.x;
        long py = coef * ( pos.y - (GetCellHeight() / 2) );

        long px2 = px + ( (px/2) <= py ? n4 : 0 );

        long nx = (  px / n4 + py / n2);
        long ny = (-px2 / n4 + py / n2);

        nx += (nx>0) ? n2 : -n2;
        ny += (ny>0) ? n2 : -n2;

        return new iPoint(nx / coef, ny  / coef);
    }
}
