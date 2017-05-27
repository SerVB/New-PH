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

package Constants;

/**
 * Spell level.
 */
public class SPL {

    public final static int FIRST  = 0;
    public final static int SECOND = 1;
    public final static int THIRD  = 2;
    public final static int FOURTH = 3;
    public final static int FIFTH  = 4;
    public final static int COUNT  = 5;

    public final static int MASK_NONE   = 0b00000;
    public final static int MASK_FIRST  = 0b00001;
    public final static int MASK_SECOND = 0b00010;
    public final static int MASK_THIRD  = 0b00100;
    public final static int MASK_FOURTH = 0b01000;
    public final static int MASK_FIFTH  = 0b10000;
    public final static int MASK_ALL    = MASK_FIRST | MASK_SECOND | MASK_THIRD | MASK_FOURTH | MASK_FIFTH;

}
