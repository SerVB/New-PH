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
package newph.core.enumeration;

import static newph.core.staticFunction.RGB.RGB16;

/**
 * Surface Type.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum SurfaceType {

    WATER       (255, RGB16(  8,  28, 128), 0b0000_0000_0001),
    SAND        ( 12, RGB16(214, 182, 148), 0b0000_0000_0010),
    DIRT        (  6, RGB16( 99,  48,   8), 0b0000_0000_0100),
    GRASS       (  6, RGB16( 24,  97,  16), 0b0000_0000_1000),
    SWAMP       ( 14, RGB16(  0,  44,   0), 0b0000_0001_0000),
    LAVA        ( 10, RGB16( 48,  48,  48), 0b0000_0010_0000),
    WASTELAND   (  8, RGB16(165,  85,  16), 0b0000_0100_0000),
    DESERT      ( 12, RGB16(181, 138,  24), 0b0000_1000_0000),
    SNOW        ( 10, RGB16(220, 220, 220), 0b0001_0000_0000),
    NDESERT     ( 12, RGB16(192, 160,   0), 0b0010_0000_0000),
    PAVEMENT    (  4, RGB16(160, 160, 160), 0b0100_0000_0000),
    NWASTELAND  (  9, RGB16(192, 192, 160), 0b1000_0000_0000);

    /**
     * Surface move cost.
     */
    public final int moveCost;

    /**
     * Surface miniMapColor.
     */
    public final int miniMapColor;

    /**
     * Surface mask.
     */
    public final int mask;

    private SurfaceType(final int moveCost, final int miniMapColor, final int mask) {
        this.moveCost = moveCost;
        this.miniMapColor = miniMapColor;
        this.mask = mask;
    }

}
