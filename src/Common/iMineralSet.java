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

package Common;

/**
 * Набор минералов
 */
public class iMineralSet {
    /**
     * Количество различных минералов
     */
    private final int NUM_OF_MINERALS = 7;

    /**
     * Массив хранилища
     */
    public long[] set = new long[NUM_OF_MINERALS];

    /**
     * Конструктор
     * @param _price0 Gold
     * @param _price1 Ore
     * @param _price2 Wood
     * @param _price3 Merc
     * @param _price4 Gems
     * @param _price5 Cryst
     * @param _price6 Sulfur
     */
    public iMineralSet(
        long         _price0,
        long         _price1,
        long         _price2,
        long         _price3,
        long         _price4,
        long         _price5,
        long         _price6
    ) {
        set[0] = _price0;
        set[1] = _price1;
        set[2] = _price2;
        set[3] = _price3;
        set[4] = _price4;
        set[5] = _price5;
        set[6] = _price6;
    }
}
