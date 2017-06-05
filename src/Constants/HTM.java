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

package Constants;

/**
 * HTM. (Mask)
 */
public class HTM {

    /**
     * Рыцарь.
     */
    public final static int KNIGHT      = 0b000001;

    /**
     * Варвар.
     */
    public final static int BARBARIAN   = 0b000010;

    /**
     * Волшебник.
     */
    public final static int WIZARD      = 0b000100;

    /**
     * Чернокнижник.
     */
    public final static int WARLOCK     = 0b001000;

    /**
     * Колдун.
     */
    public final static int SORCERESS   = 0b010000;

    /**
     * Некромант.
     */
    public final static int NECROMANCER = 0b100000;

    /**
     * All.
     */
    public final static int ALL = KNIGHT | BARBARIAN | WIZARD | WARLOCK | SORCERESS | NECROMANCER;
}
