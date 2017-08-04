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

/**
 * Mineral Type.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum MineralType {

    MINERAL_UNKNOWN(-1, -1),
    MINERAL_GOLD(1000, 1),
    MINERAL_ORE(2, 250),
    MINERAL_WOOD(2, 250),
    MINERAL_MERCURY(1, 500),
    MINERAL_GEMS(1, 500),
    MINERAL_CRYSTAL(1, 500),
    MINERAL_SULFUR(1, 500);

    public final int divider;
    public final int exchangeRate;

    private MineralType(final int divider, final int exchangeRate) {
        this.divider = divider;
        this.exchangeRate = exchangeRate;
    }

}
