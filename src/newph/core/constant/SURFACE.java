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

package newph.core.constant;

import newph.core.enumeration.SurfaceType;
import static newph.core.staticFunction.RGB.RGB16;

/**
 * Surface constants.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class SURFACE {

    /**
     * Prevents from creating an instance of the class.
     */
    private SURFACE() {}

    /**
     * Surface type mask [STYPE_COUNT].
     */
    public static final int[] SURF_TYPE_MASK = {
        1 << SurfaceType.STYPE_WATER.getValue(),
        1 << SurfaceType.STYPE_SAND.getValue(),
        1 << SurfaceType.STYPE_DIRT.getValue(),
        1 << SurfaceType.STYPE_GRASS.getValue(),
        1 << SurfaceType.STYPE_SWAMP.getValue(),
        1 << SurfaceType.STYPE_LAVA.getValue(),
        1 << SurfaceType.STYPE_WASTELAND.getValue(),
        1 << SurfaceType.STYPE_DESERT.getValue(),
        1 << SurfaceType.STYPE_SNOW.getValue(),
        1 << SurfaceType.STYPE_NDESERT.getValue(),
        1 << SurfaceType.STYPE_PAVEMENT.getValue(),
        1 << SurfaceType.STYPE_NWASTELAND.getValue()
    };

    /**
     * Surface move cost [STYPE_COUNT].
     */
    public static final int[] SURF_MOVE_COST = {
        255,    // Water
        12,        // Sand
        6,        // Dirt
        6,        // Grass
        14,        // Swamp
        10,        // Lava
        8,        // Wasteland
        12,        // Desert
        10,        // Snow
        12,        // new desert
        4,        // Pavement
        9,        // new wasteland
    };

    /**
     * Surface color [STYPE_COUNT].
     */
    public static final int[] SURF_COLOR = {
        RGB16(8, 28, 128),    // Water
        RGB16(214,182,148),    // Sand
        RGB16(99,48,8),        // Dirt
        RGB16(24,97,16),    // Grass
        RGB16(0,44,0),        // Swamp
        RGB16(48,48,48),    // Lava
        RGB16(165,85,16),    // Wasteland
        RGB16(181,138,24),    // Desert
        RGB16(220,220,220),    // Snow
        RGB16(192,160,0),    // new Desert
        RGB16(160,160,160),    // Pavement
        RGB16(192,192,160),    // new wasteland
    };

}
