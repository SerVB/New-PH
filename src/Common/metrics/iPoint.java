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

import helperFunction.MathOperations;

/**
 * Contains coordinates "x" and "y".
 */
public class iPoint {

    /**
     * C-tor. Sets metrics to zero.
     */
    public iPoint() {
        x = 0;
        y = 0;
    }

    /**
     * C-tor.
     * @param x "X" coord.
     * @param y "Y" coord.
     */
    public iPoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * C-tor. Copies iPoint object.
     * @param other Object to be copied.
     */
    public iPoint(final iPoint other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void add(final iPoint pos) {
        x += pos.x;
        y += pos.y;
    }

    public void subtract(final iPoint pos) {
        x -= pos.x;
        y -= pos.y;
    }

    /**
     * C-tor. Makes operations with metrics of two iPoint objects.
     * @param first First object.
     * @param oper Operator ('+', '-').
     * @param second Second object.
     */
    public iPoint(final iPoint first, final char oper, final iPoint second) {
        switch (oper) {
            case '+':
                this.x = first.x + second.x;
                this.y = first.y + second.y;
                break;
            case '-':
                this.x = first.x - second.x;
                this.y = first.y - second.y;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    /**
     * Adds iSize metrics to object metrics
     * ("w" to "x", "h" to "y").
     * @param size iSize object.
     */
    public void add(final iSize size) {
        x += size.w;
        y += size.h;
    }

    /**
     * Subtracts iSize metrics from object metrics
     * ("w" from "x", "h" from "y").
     * @param size iSize object.
     */
    public void subtract(final iSize size) {
        x -= size.w;
        y -= size.h;
    }

    /**
     * C-tor. Makes operations with metrics of iPoint object and iSize object.
     * @param point iPoint object.
     * @param oper Operator ('+', '-').
     * @param size iSize object.
     */
    public iPoint(final iPoint point, final char oper, final iSize size) {
        switch (oper) {
            case '+':
                this.x = point.x + size.w;
                this.y = point.y + size.h;
                break;
            case '-':
                this.x = point.x - size.w;
                this.y = point.y - size.h;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    /**
     * Adds value to each metric.
     * @param offs Value to be added.
     */
    public void add(int offs) {
        x += offs;
        y += offs;
    }

    /**
     * Subtracts values from metrics.
     * @param offs Value to be subtracted.
     */
    public void subtract(int offs) {
        x -= offs;
        x -= offs;
    }

    /**
     * C-tor. Makes operations with metrics of iPoint object and value.
     * @param point iPoint object.
     * @param oper Operator ('+', '-').
     * @param offs Value to be operated.
     */
    public iPoint(final iPoint point, final char oper, final int offs) {
        switch (oper) {
            case '+':
                this.x = point.x + offs;
                this.y = point.y + offs;
                break;
            case '-':
                this.x = point.x - offs;
                this.y = point.y - offs;
                break;
            default:
                throw new IllegalArgumentException("Invalid oper '" + oper + "'!");
        }
    }

    /**
     * Compares the object with the other object.
     * @param other Other object.
     * @return True if objects are equal, false if not.
     */
    public boolean equals(final iPoint other) {
        return (this.x == other.x) && (this.y == other.y);
    }

    public int GetSqDelta(final iPoint pnt) {
        return Math.max( MathOperations.dif(pnt.x, x), MathOperations.dif(pnt.y, y));
    }

    public int GetDelta(final iPoint pnt) {
        int dx = pnt.x - x;
        int dy = pnt.y - y;

        return (int) Math.sqrt((double)(dx * dx + dy * dy));
    }

    public void MoveX(int offset_x) {
        x += offset_x;
    }

    public void MoveY(int offset_y) {
        y += offset_y;
    }

    public void Move(int offset_x, int offset_y) {
        x += offset_x;
        y += offset_y;
    }

    public int x;
    public int y;

}
