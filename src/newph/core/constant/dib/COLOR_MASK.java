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
package newph.core.constant.dib;

/**
 * Color mask constants.
 */
public class COLOR_MASK {
    
//                               for: {   RGB16  ,   RGBA16 ,   RGBCK16 }
    public final static int[] RED   = {  0x1F<<11,   0xF<<12,   0x1F<<11};
    public final static int[] GREEN = {  0x3F<< 5,   0xF<< 8,   0x1F<< 6};
    public final static int[] BLUE  = {  0x1F,       0xF<< 4,   0x1F<< 1};
    public final static int[] COLOR = {0xFFFF,     0xFFF<< 4, 0x7FFF<< 1};
    public final static int[] ALPHA = {     0,       0xF,        0x1    };
    
}
