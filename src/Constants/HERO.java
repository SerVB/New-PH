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

import Common.metrics.iPoint;

/**
 * Hero.
 */
public class HERO {

    //const float HERO_SPEED_VAL[5] = { 2.5f, 3.0f, 3.5f, 4.5f, 5.5f };
    public final static double[] SPEED_VAL = {
        2.5, 3.0, 3.5, 4.5, 5.5
    };

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
    public final static int[][][] INITIAL_ARMY = {
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

    /**
     * Типы идеологий героев.
     */
    public final static int[] TYPE_IDEOLOGY = {
        IDEOLOGY.GOOD,
        IDEOLOGY.EVIL,
        IDEOLOGY.GOOD,
        IDEOLOGY.EVIL,
        IDEOLOGY.GOOD,
        IDEOLOGY.EVIL
    };

    /**
     * Доступные для обвеса артефактами места героя (голова, руки, ноги и т.д.).
     */
    public final static int[] ART_CELL_ASSIGN = {
        ART_ASSIGN.HEAD,
        ART_ASSIGN.NECK,
        ART_ASSIGN.TORSO,
        ART_ASSIGN.HANDS,
        ART_ASSIGN.HANDS,
        ART_ASSIGN.FINGERS,
        ART_ASSIGN.FINGERS,
        ART_ASSIGN.SHOULDERS,
        ART_ASSIGN.LEGS,
        ART_ASSIGN.FEET,
        ART_ASSIGN.MISC,
        ART_ASSIGN.MISC,
        ART_ASSIGN.MISC,
        ART_ASSIGN.MISC
    };

    /**
     * Вероятность получения основного навыка при новом уровне в процентах.
     */
    public final static int[][] PRIM_SKILL = {
        {25,50,5,20},    // Knight
        {60,25,5,10},    // Barbarian
        {10,10,40,40},   // Wizard
        {15,10,45,30},   // Warlock
        {15,15,30,40},   // Sorceress
        {15,15,40,30}    // Necromancer
    };

    /**
     * Вероятность получения вторичного навыка при новом уровне. Значение / сумму * 100%.
     * Досчитать суммы!
     */
    public final static int[][] SEC_SKILL = {
        {
            // Knight (сумма = 50)
            2,6,6,4,        // Estates, Leadership, Luck, Diplomacy
            1,1,0,2,        // Air, Earth, Fire, Water
            1,0,0,1,2,0,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,4,3,            // Scouting, Logistics, Pathfinding,
            3,3,2,4            // Archery, Ballistics, Offence, Armorer
        },{
            // Barbarian (сумма = 48)
            2,4,4,3,        // Estates, Leadership, Luck, Diplomacy
            1,1,2,0,        // Air, Earth, Fire, Water
            1,0,0,1,1,0,2,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,3,5,            // Scouting, Logistics, Pathfinding,
            4,3,5,3            // Archery, Ballistics, Offence, Armorer
        },{
            // Wizard (сумма = ...)
            2,2,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,0,5,        // Air, Earth, Fire, Water
            6,0,4,2,2,3,5,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,2,2,            // Scouting, Logistics, Pathfinding,
            0,0,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Warlock (сумма = ...)
            2,0,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,5,0,        // Air, Earth, Fire, Water
            4,1,4,3,2,5,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            4,2,3,            // Scouting, Logistics, Pathfinding,
            0,0,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Sorceress (сумма = ...)
            2,2,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,0,5,        // Air, Earth, Fire, Water
            6,0,4,2,2,3,5,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,2,2,            // Scouting, Logistics, Pathfinding,
            1,1,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Necromancer (сумма = ...)
            2,0,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,5,0,        // Air, Earth, Fire, Water
            4,5,4,3,2,5,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            4,2,3,            // Scouting, Logistics, Pathfinding,
            1,1,0,0            // Archery, Ballistics, Offence, Armorer
        }
    };

    /**
     * 72 штуки каких-то точек.
     */
    public final static iPoint[]FLAG_ANCHOR = {
        new iPoint(4,7), new iPoint(4,5), new iPoint(4,4), new iPoint(4,5), new iPoint(4,7),
        new iPoint(4,6), new iPoint(4,4), new iPoint(4,5), new iPoint(4,6),

        new iPoint(8,7), new iPoint(9,7), new iPoint(8,7), new iPoint(9,8), new iPoint(10,8),
        new iPoint(10,7), new iPoint(9,7), new iPoint(8,7), new iPoint(7,7),

        new iPoint(11,8), new iPoint(12,8), new iPoint(11,8), new iPoint(10,8), new iPoint(10,9),
        new iPoint(9,9), new iPoint(10,8), new iPoint(11,8), new iPoint(10,8),

        new iPoint(13,7), new iPoint(14,7), new iPoint(13,8), new iPoint(11,9), new iPoint(13,9),
        new iPoint(14,10), new iPoint(14,9), new iPoint(14,8), new iPoint(12,7),

        //
        new iPoint(32,8), new iPoint(32,9), new iPoint(32,10), new iPoint(32,8), new iPoint(32,9),
        new iPoint(32,10), new iPoint(32,11), new iPoint(32,8), new iPoint(32,9),

        //
        new iPoint(20,7), new iPoint(19,7), new iPoint(20,8), new iPoint(19,9), new iPoint(20,9),
        new iPoint(19,10), new iPoint(19,9), new iPoint(19,8), new iPoint(22,7),

        new iPoint(22,8), new iPoint(21,8), new iPoint(22,8), new iPoint(23,8), new iPoint(23,9),
        new iPoint(24,9), new iPoint(23,8), new iPoint(22,8), new iPoint(23,8),

        new iPoint(25,7), new iPoint(24,7), new iPoint(25,7), new iPoint(24,8), new iPoint(23,8),
        new iPoint(23,7), new iPoint(24,7), new iPoint(25,7), new iPoint(26,7),
    };
}
