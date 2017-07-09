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
 * Line class.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class iLine {

    /**
     * Constructs the Line with zero metrics.
     */
    public iLine() {
        m_Point1 = new iPoint(0, 0);
        m_Point2 = new iPoint(0, 0);
    }

    /**
     * Constructs the Line with copied metrics.
     * @param toBeCoppied The other line.
     */
    public iLine(final iLine toBeCoppied) {
        this(toBeCoppied.GetPoint1(), toBeCoppied.GetPoint2());
    }
    
    /**
     * Constructs the Line object from two points.
     * @param tpoint1 The first point.
     * @param tpoint2 The second point.
     */
    public iLine(final iPoint tpoint1, final iPoint tpoint2) {
        m_Point1 = new iPoint(tpoint1);
        m_Point2 = new iPoint(tpoint2);
    }
    
    /**
     * Constructs iLine object from coordinates of two points.
     * @param x1 "x" of first point.
     * @param y1 "y" of first point.
     * @param x2 "x" of second point.
     * @param y2 "y" of second point.
     */
    public iLine(final int x1, final int y1, final int x2, final int y2) {
        m_Point1 = new iPoint(x1, y1);
        m_Point2 = new iPoint(x2, y2);
    }

    /**
     * Returns first point's "x" coordinate.
     * @return The coordinate.
     */
    public final int X1() {
        return m_Point1.x;
    }

    /**
     * Returns first point's "y" coordinate.
     * @return The coordinate.
     */
    public final int Y1() {
        return m_Point1.y;
    }

    /**
     * Returns second point's "x" coordinate.
     * @return The coordinate.
     */
    public final int X2() {
        return m_Point2.x;
    }

    /**
     * Returns second point's "y" coordinate.
     * @return The coordinate.
     */
    public final int Y2() {
        return m_Point2.y;
    }
    
    /**
     * Returns first point.
     * @return The point.
     */
    public final iPoint GetPoint1() {
        return m_Point1;
    }

    /**
     * Returns second point.
     * @return The point.
     */
    public final iPoint GetPoint2() {
        return m_Point2;
    }

    /**
     * The first point.
     */
    private iPoint m_Point1;
    
    /**
     * The second point.
     */
    private iPoint m_Point2;
    
}
