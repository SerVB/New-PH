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

/**
 * Hero Type constants.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class HERO_TYPE {

    /**
     * Prevents from creating an instance of the class.
     */
    private HERO_TYPE() {}

    /**
     * Secondary Skills [HERO_TYPE_COUNT][SECSK_COUNT].
     */
    public static final int[][] HERO_SEC_SKILL = {
        {
            // Knight
            2,6,6,4,        // Estates, Leadership, Luck, Diplomacy
            1,1,0,2,        // Air, Earth, Fire, Water
            1,0,0,1,2,0,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,4,3,            // Scouting, Logistics, Pathfinding,
            3,3,2,4            // Archery, Ballistics, Offence, Armorer
        },{
            // Barbarian
            2,4,4,3,        // Estates, Leadership, Luck, Diplomacy
            1,1,2,0,        // Air, Earth, Fire, Water
            1,0,0,1,1,0,2,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,3,5,            // Scouting, Logistics, Pathfinding,
            4,3,5,3            // Archery, Ballistics, Offence, Armorer
        },{
            // Wizard
            2,2,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,0,5,        // Air, Earth, Fire, Water
            6,0,4,2,2,3,5,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,2,2,            // Scouting, Logistics, Pathfinding,
            0,0,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Warlock
            2,0,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,5,0,        // Air, Earth, Fire, Water
            4,1,4,3,2,5,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            4,2,3,            // Scouting, Logistics, Pathfinding,
            0,0,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Sorceress
            2,2,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,0,5,        // Air, Earth, Fire, Water
            6,0,4,2,2,3,5,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            3,2,2,            // Scouting, Logistics, Pathfinding,
            1,1,0,0            // Archery, Ballistics, Offence, Armorer
        },{
            // Necromancer
            2,0,2,3,        // Estates, Leadership, Luck, Diplomacy
            3,3,5,0,        // Air, Earth, Fire, Water
            4,5,4,3,2,5,4,    // Wisdom, Necromancy, Mysticism, Intelligence, Resistance, Sorcery, Learning
            4,2,3,            // Scouting, Logistics, Pathfinding,
            1,1,0,0            // Archery, Ballistics, Offence, Armorer
        }
    };

}
