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
 * Magic School Type.
 */
public class MST {

    public final static int AIR   = 0;
    public final static int EARTH = 1;
    public final static int FIRE  = 2;
    public final static int WATER = 3;
    public final static int COUNT = 4;

    public final static int MASK_AIR   = 0b0001;
    public final static int MASK_EARTH = 0b0010;
    public final static int MASK_FIRE  = 0b0100;
    public final static int MASK_WATER = 0b1000;
    public final static int MASK_GOOD  = MASK_AIR | MASK_EARTH | MASK_WATER;
    public final static int MASK_EVIL  = MASK_AIR | MASK_EARTH | MASK_FIRE;
    public final static int MASK_ALL   = MASK_AIR | MASK_EARTH | MASK_WATER | MASK_FIRE;
    
}
