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

import Common.iSpellFilter;

/**
 *
 */
public class CTL {

    /**
     * Список рас героев.
     */
    public final static int[] HEROES = {
        HTM.KNIGHT,
        HTM.BARBARIAN,
        HTM.WIZARD,
        HTM.WARLOCK,
        HTM.SORCERESS,
        HTM.NECROMANCER
    };

    /**
     * Начальный / Минимальный золотой доход замка.
     */
    public final static int INCOME = 500;


    /**
     * Описание для каждого замка заклинаний, открываемых по каждому сдедующему уровню гильдии.
     */
    public final static iSpellFilter[][] MAGE_GUILD_FILTER = {
        {
            // Citadel
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_GOOD)
        },{
            // Stronghold
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_EVIL)
        },{
            // Tower
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FOURTH, MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIFTH,  MST.MASK_GOOD)
        },{
            // Dungeon
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FOURTH, MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIFTH,  MST.MASK_EVIL)
        },{
            // Fortress
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FOURTH, MST.MASK_GOOD),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIFTH,  MST.MASK_GOOD)
        },{
            // Necropolis
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIRST,  MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_SECOND, MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_THIRD,  MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FOURTH, MST.MASK_EVIL),
            new iSpellFilter(SPT.MASK_ALL, SPL.MASK_FIFTH,  MST.MASK_EVIL)
        }
    };

    /**
     * Количество заклинаний каждой гильдии в порядке открытия.
     */
    public final static int[][] MAGE_GUILD_SPELLS = {
        {
            // Citadel
            5, 4, 3
        },{
            // Stronghold
            5, 4, 3
        },{
            // Tower
            5, 4, 3, 2, 1
        },{
            // Dungeon
            5, 4, 3, 2, 1
        },{
            // Fortress
            5, 4, 3, 2, 1
        },{
            // Necropolis
            5, 4, 3, 2, 1
        }
    };
}
