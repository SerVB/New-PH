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

package newph.core.dib;

/**
 * The pixel mask constants.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class COLOR_MASK {
    
    /**
     * The pixel mask for all channels except the alpha channel.
     * #ColorType: (R5G6B5A0 = RGB16, R4G4B4A4 = RGBA16, R5G5B5A1 = RGBCK16)
     */
    public static final int[] COLOR = {
        0b1111_1111_1111_1111,
        0b1111_1111_1111_0000,
        0b1111_1111_1111_1110
    };
    
    /**
     * The pixel mask for the red channel.
     * #ColorType: (R5G6B5A0 = RGB16, R4G4B4A4 = RGBA16, R5G5B5A1 = RGBCK16)
     */
    public static final int[] RED = {
        0b1111_1000_0000_0000,
        0b1111_0000_0000_0000,
        0b1111_1000_0000_0000
    };
    
    /**
     * The pixel mask for the green channel.
     * #ColorType: (R5G6B5A0 = RGB16, R4G4B4A4 = RGBA16, R5G5B5A1 = RGBCK16)
     */
    public static final int[] GREEN = {
        0b0000_0111_1110_0000,
        0b0000_1111_0000_0000,
        0b0000_0111_1100_0000
    };
    
    /**
     * The pixel mask for the blue channel.
     * #ColorType: (R5G6B5A0 = RGB16, R4G4B4A4 = RGBA16, R5G5B5A1 = RGBCK16)
     */
    public static final int[] BLUE = {
        0b0000_0000_0001_1111, 
        0b0000_0000_1111_0000, 
        0b0000_0000_0011_1110
    };
    
    /**
     * The pixel mask for the alpha channel.
     * #ColorType: (R5G6B5A0 = RGB16, R4G4B4A4 = RGBA16, R5G5B5A1 = RGBCK16)
     */
    public static final int[] ALPHA = {
        0b0000_0000_0000_0000,
        0b0000_0000_0000_1111,
        0b0000_0000_0000_0001
    };
    
}
