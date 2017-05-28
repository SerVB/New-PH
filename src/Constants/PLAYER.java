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

import Common.iMineralSet;

/**
 *
 */
public class PLAYER {

    /**
     * Начальные ресурсы для каждого из уровней сложности. <BR>
     * В INITIAL_RESOURCES[0] для человека, <BR>
     * в INITIAL_RESOURCES[1] для компьютера.
     */
    public final static iMineralSet[][] INITIAL_RESOURCES = {
        {
            // For Human
            // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(15000, 30, 30, 15, 15, 15, 15), // Easy
            new iMineralSet(10000, 20, 20, 10, 10, 10, 10), // Normal
            new iMineralSet( 5000, 10, 10,  5,  5,  5,  5), // Hard
            new iMineralSet( 2500,  5,  5,  2,  2,  2,  2), // Expert
            new iMineralSet(    0,  0,  0,  0,  0,  0,  0)  // Impossible
        },{
            // For Computer
            // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet( 2500,  5,  5,  2,  2,  2,  2), // Easy
            new iMineralSet( 5000, 10, 10,  5,  5,  5,  5), // Normal
            new iMineralSet(15000, 30, 30, 15, 15, 15, 15), // Hard
            new iMineralSet(25000, 50, 50, 25, 25, 25, 25), // Expert
            new iMineralSet(50000, 80, 80, 40, 40, 40, 40)  // Impossible
        }
    };


    /**
     * Цвета для каждого из игроков (16-ти битные, Карл!). Нужно перевести в 24-х битные!
     */
    public final static long[] COLORS = {
        0x1F<<11,           // Red,   0x1F == 0b11111  -> На красный 5 бит
        0x30<<5,            // Green, 0x30 == 0b110000 -> На зеленый 6 бит
        0x1F,               // Blue,                   -> На синий тоже 5 бит
        0x30<<5 | 0x1F,     // Cyan
        0x1D<<11 | 0x1F,    // Magenta
        0x1D<<11 | 0x38<<5  // Yellow
    };

    /**
     * Цвета текста? для каждого из игроков. Нужно перевести в 24-х битные!
     */
    public final static String[] TEXT_COLORS = {
        "#FF99",
        "#F8E8",
        "#F99F",
        "#F7EE",
        "#FE7E",
        "#FEE7"
    };

}
