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

import Common.RGB;

/**
 * Surface.
 */
public class SURF {

    /**
     * Surface type mask.
     * Маски поверхности каждого типа.
     */
    public final static long[] TYPE_MASK = {
        1 << STYPE.WATER,
        1 << STYPE.SAND,
        1 << STYPE.DIRT,
        1 << STYPE.GRASS,
        1 << STYPE.SWAMP,
        1 << STYPE.LAVA,
        1 << STYPE.WASTELAND,
        1 << STYPE.DESERT,
        1 << STYPE.SNOW,
        1 << STYPE.NDESERT,
        1 << STYPE.PAVEMENT,
        1 << STYPE.NWASTELAND
    };

    /**
     * Стоимость перемещения по поверхности каждого типа.
     */
    public final static long[] MOVE_COST = {
        255, // Water
        12,  // Sand
        6,   // Dirt
        6,   // Grass
        14,  // Swamp
        10,  // Lava
        8,   // Wasteland
        12,  // Desert
        10,  // Snow
        12,  // new desert
        4,   // Pavement
        9    // new wasteland
    };

    /**
     * Цвет поверхности (для миникарты, что ли?).
     */
    public final static RGB[] COLOR = {
        new RGB(8, 28, 128),  // Water
        new RGB(214,182,148), // Sand
        new RGB(99,48,8),     // Dirt
        new RGB(24,97,16),    // Grass
        new RGB(0,44,0),      // Swamp
        new RGB(48,48,48),    // Lava
        new RGB(165,85,16),   // Wasteland
        new RGB(181,138,24),  // Desert
        new RGB(220,220,220), // Snow
        new RGB(192,160,0),   // new Desert
        new RGB(160,160,160), // Pavement
        new RGB(192,192,160)  // new wasteland
    };

}
