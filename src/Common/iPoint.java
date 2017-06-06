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
 * Содержит две целочисленные переменные.
 */
public class iPoint {

    public int x;
    public int y;

    public iPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public iPoint() {

    }



    public iPoint(iPoint other) {
        this.x = other.x;
        this.y = other.y;
    }

    public void minus(iPoint other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void plus(iPoint other) {
        this.x += other.x;
        this.y += other.y;
    }

    public iPoint(iPoint first, char oper, iPoint second) {
        switch (oper) {
        case '-':
            this.x = first.x - second.x;
            this.y = first.y - second.y;
            break;
        case '+':
            this.x = first.x + second.x;
            this.y = first.y + second.y;
            break;
        default:
            throw new UnsupportedOperationException(oper + "Not supported yet.");
        }
    }

}
