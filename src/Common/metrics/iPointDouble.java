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

import newph.metric.iPoint;

/**
 * Double based iPoint.
 */
public class iPointDouble {

    public iPointDouble() {
        this.x = 0;
        this.y = 0;
    }

    public iPointDouble(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public iPointDouble(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public iPointDouble(final iPoint pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void add(final iPointDouble pos) {
        x += pos.x;
        y += pos.y;
    }

    public void subtract(final iPointDouble pos) {
        x -= pos.x;
        y -= pos.y;
    }

    public iPointDouble(final iPointDouble first, final char oper, final iPointDouble second) {
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

    public boolean equals(final iPointDouble other) {
        return (this.x == other.x) && (this.y == other.y);
    }

    public iPoint IntPoint() {
        return new iPoint((int) Math.ceil(x), (int) Math.ceil(y));
    }

    public double x;
    public double y;

}
