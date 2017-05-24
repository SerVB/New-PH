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
 * all
 * copies or substantial portions of the Software.
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
 * spell effect descriptor (4 x 2 = 8 bytes)
 */
public class SPELL_EFFECT_DESC {

    /**
     * Target type
     */
    public int tgtType;

    /**
     * Target mode
     */
    public int tgtMode;

    /**
     * First parameter
     */
    public int fparam;

    /**
     * Second parameter
     */
    public long sparam;

    /**
     * Конструктор без двух параметров.
     * @param tgtType
     * @param tgtMode
     */
    public SPELL_EFFECT_DESC(int tgtType, int tgtMode) {
        this.tgtType = tgtType;
        this.tgtMode = tgtMode;
    }

    /**
     * Конструктор без одного параметра.
     * @param tgtType
     * @param tgtMode
     * @param fparam
     */
    public SPELL_EFFECT_DESC(int tgtType, int tgtMode, int fparam) {
        this.tgtType = tgtType;
        this.tgtMode = tgtMode;
        this.fparam = fparam;
    }

    /**
     * Конструктор.
     * @param tgtType
     * @param tgtMode
     * @param fparam
     * @param sparam
     */
    public SPELL_EFFECT_DESC(int tgtType, int tgtMode, int fparam, long sparam) {
        this.tgtType = tgtType;
        this.tgtMode = tgtMode;
        this.fparam = fparam;
        this.sparam = sparam;
    }

}
