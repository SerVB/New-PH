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
package Game;

import newph.staticFunction.Tracer;

/**
 * Клетка.
 */
public class iCell {

    /**
     * Конструктор.
     */
    public iCell() {
        this.surf = 0;
        this.avatar = 0;
    }

    /**
     *
     * @param idx
     * @return
     */
    public int SurfNode(int idx) {
        Tracer.check(0 <= idx && idx <= 3);
        return (surf >> ((3-idx)*4)) & 0xF;
    }

    /**
     *
     * @return
     */
    public boolean Solid() {
        return (SurfNode(0) == SurfNode(1)) &&
               (SurfNode(1) == SurfNode(2)) &&
               (SurfNode(2) == SurfNode(3));
    }

    /**
     *
     */
    public int surf;

    /**
     *
     */
    public int avatar;
}
