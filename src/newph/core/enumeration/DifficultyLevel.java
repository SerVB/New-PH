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

import newph.core.common.iMineralSet;

/**
 * Difficulty Level.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum DifficultyLevel {

    UNDEFINED(
            new iMineralSet(),
            new iMineralSet()
    ),

    EASY(
            new iMineralSet(15000, 30, 30, 15, 15, 15, 15),
            new iMineralSet( 2500,  5,  5,  2,  2,  2,  2)
    ),

    NORMAL(
            new iMineralSet(10000, 20, 20, 10, 10, 10, 10),
            new iMineralSet( 5000, 10, 10,  5,  5,  5,  5)
    ),

    HARD(
            new iMineralSet( 5000, 10, 10,  5,  5,  5,  5),
            new iMineralSet(15000, 30, 30, 15, 15, 15, 15)
    ),

    EXPERT(
            new iMineralSet( 2500,  5,  5,  2,  2,  2,  2),
            new iMineralSet(25000, 50, 50, 25, 25, 25, 25)
    ),

    IMPOSSIBLE(
            new iMineralSet(    0,  0,  0,  0,  0,  0,  0),
            new iMineralSet(50000, 80, 80, 40, 40, 40, 40)
    );

    public final iMineralSet humanInitialResources;
    public final iMineralSet computerInitialResources;

    private DifficultyLevel(final iMineralSet humanInitialResources, final iMineralSet computerInitialResources) {
        this.humanInitialResources = humanInitialResources;
        this.computerInitialResources = computerInitialResources;
    }

}
