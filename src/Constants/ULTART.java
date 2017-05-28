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
 *
 */
public class ULTART {

    /**
     * Описание бонусов для героев каждой расы.
     */
    final static long[][][] STDMODIF = {
        {
            // Knight
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 4}, {FSK.KNOWLEDGE, 4}, {FSK.LOGISTICS, 20}, {FSK.SPEED, 2}
        },{
            // Barbarian
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 4}, {FSK.KNOWLEDGE, 4}, {FSK.RESIST, 30}, {FSK.VOID2, 0}
        },{
            // Wizard
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 6}, {FSK.KNOWLEDGE, 6}, {FSK.VOID, 0}, {FSK.VOID2, 0}
        },{
            // Warlock
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 6}, {FSK.KNOWLEDGE, 6}, {FSK.VOID, 0}, {FSK.VOID2, 0}
        },{
            // Sorceress
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 6}, {FSK.KNOWLEDGE, 6}, {FSK.VOID, 0}, {FSK.VOID2, 0}
        },{
            // Necromancer
            {FSK.MIN_GOLD, 5000}, {FSK.ATTACK, 4}, {FSK.DEFENCE, 4}, {FSK.POWER, 4}, {FSK.KNOWLEDGE, 4}, {FSK.NECROMANCY, 15}, {FSK.VOID2, 0}
        }
    };

    /**
     * Специальные маски героев для каждой из рас.
     */
    final static long[] SPECFLAGS = {
        SHF_INVALID,        // Knight
        SHF_NORANGEPENALTY, // Barbarian
        SHF_SUMRESBOUNS,    // Wizard
        SHF_MANARESTORE,    // Warlock
        SHF_DMGSPBONUS,     // Sorceress
        SHF_NECRBONUS       // Necromancer
    };

}
