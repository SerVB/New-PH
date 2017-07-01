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
package newph.constant.dib;

/**
 * Color constants.
 */
public final class COLOR {

    /**
     * Array of 32 grayscale colors.
     */
    public final static int[] BWPAL = {
        0x0000, 0x0841, 0x1082, 0x18C3, 0x2104, 0x2945, 0x3186, 0x39C7,
        0x4208, 0x4A49, 0x528A, 0x5ACB, 0x630C, 0x6B4D, 0x738E, 0x7BCF,
        0x8410, 0x8C51, 0x9492, 0x9CD3, 0xA514, 0xAD55, 0xB596, 0xBDD7,
        0xC618, 0xCE59, 0xD69A, 0xDEDB, 0xE71C, 0xEF5D, 0xF79E, 0xFFDF
    };
    
    public final static int cColor_White    = 0xFFFF;
    public final static int cColor_Black    = 0x0000;
    public final static int cColor_Gray192  = 0xC618;
    public final static int cColor_Gray128  = 0x8410;
    public final static int cColor_Gray64   = 0x4208;
    public final static int cColor_Red      = 0xF800;
    public final static int cColor_Green64  = 0x0200;
    public final static int cColor_Green128 = 0x03E0;
    public final static int cColor_Green192 = 0x05E0;
    public final static int cColor_Green    = 0x07E0;
    public final static int cColor_Blue     = 0x001F;
    public final static int cColor_Blue64   = 0x0008;
    public final static int cColor_Blue128  = 0x0010;
    public final static int cColor_Blue192  = 0x0018;
    public final static int cColor_Yello    = 0xFFE0;
    public final static int cColor_Yello192 = 0xBDE0;

    // 32 colors
    public final static int[] tintGradient = {
        0x4101,    0x4101,    0x4921,    0x4942,    0x5142,    0x5162,    0x5983,    0x61a3,
        0x61e3,    0x6a04,    0x7224,    0x7a65,    0x8285,    0x8ac6,    0x8ae6,    0x9327,
        0xa368,    0xa389,    0xabc9,    0xb40a,    0xbc4b,    0xc48c,    0xcccd,    0xd4ed,
        0xdd2e,    0xe54f,    0xed90,    0xedd0,    0xf5f1,    0xf611,    0xf632,    0xf652
    };
    
    // 32 colors
    public final static int[] hcbwGradient = {
        0x4101,    0x4101,    0x4921,    0x4942,    0x5142,    0x5162,    0x5983,    0x61a3,
        0x61e3,    0x6a04,    0x7224,    0x7a65,    0x8285,    0x8ac6,    0x8ae6,    0x9327,
        0xa368,    0xa389,    0xabc9,    0xb40a,    0xbc4b,    0xc48c,    0xcccd,    0xd4ed,
        0xdd2e,    0xe54f,    0xed90,    0xedd0,    0xf5f1,    0xf611,    0xf632,    0xf652
    };

}
