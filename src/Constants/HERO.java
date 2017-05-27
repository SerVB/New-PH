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

/**
 * Hero.
 */
public class HERO {

    /**
     * Направление взгляда героя, заспаунившегося в замке.
     */
    public final static int[] ORIENT = {
        1, // Left
        7  // Right
    };

    /**
     * Initial hero army.
     */
    public final static long[][][] INITIAL_ARMY = {
        {
            // Knight
            {
                CREAT.PEASANT, 25, 50
            },{
                CREAT.ARCHER, 0, 7
            }

        },{
            // Barbarian
            {
                CREAT.GOBLIN, 15, 30
            },{
                CREAT.ORC, 0, 8
            }
        },{
            // Wizard
            {
                CREAT.HALFLING, 10, 20
            },{
                CREAT.BOAR, 0, 6
            }
        },{
            // Warlock
            {
                CREAT.CENTAUR, 11, 22
            },{
                CREAT.GARGOYLE, 0, 6
            }
        },{
            // Sorceress
            {
                CREAT.SPRITE, 14, 28
            },{
                CREAT.DWARF, 0, 8
            }
        },{
            // Necromancer
            {
                CREAT.SKELETON, 20, 40
            },{
                CREAT.ZOMBIE, 0, 9
            }
        }
    };
}
