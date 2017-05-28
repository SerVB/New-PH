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
 *
 */
public class common {

    /**
     * Размер карты (а в каких единицах измерения?,
     * это сторона квадрата что ли?).
     */
    final static long[] MAP_SIZ_SIZE = {
        32,  // S
        64,  // M
        128, // L
        256  // XL
    };

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////



    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////


    /**
     * Описание вторичных навыков.
     * Для каждого навыка описана каждая из трех ступеней.
     */
    final static long[][][] SEKSKILL_DESC = {
        {
            // Estates
            {FSK_MIN_GOLD, 250},{FSK_MIN_GOLD, 500},{FSK_MIN_GOLD, 1000},
        },{
            // Leadership
            {FSK_MORALE, 1},{FSK_MORALE, 2},{FSK_MORALE, 3}
        },{
            // Luck
            {FSK_LUCK, 1},{FSK_LUCK, 2},{FSK_LUCK, 3}
        },{
            // Diplomacy
            {FSK_DIPLOMACY, 20},{FSK_DIPLOMACY, 40},{FSK_DIPLOMACY, 60}
        },{
            // Air
            {FSK_MAG_AIR, 1},{FSK_MAG_AIR, 2},{FSK_MAG_AIR, 3}
        },{
            // Earth
            {FSK_MAG_EARTH, 1},{FSK_MAG_EARTH, 2},{FSK_MAG_EARTH, 3}
        },{
            // Fire
            {FSK_MAG_FIRE, 1},{FSK_MAG_FIRE, 2},{FSK_MAG_FIRE, 3}
        },{
            // Water
            {FSK_MAG_WATER, 1},{FSK_MAG_WATER, 2},{FSK_MAG_WATER, 3}
        },{
            // Wisdom
            {FSK_WISDOM, 1},{FSK_WISDOM, 2},{FSK_WISDOM, 3}
        },{
            // Necromancy
            {FSK_NECROMANCY, 10},{FSK_NECROMANCY, 20},{FSK_NECROMANCY, 30}
        },{
            // Mysticism
            {FSK_MANAPTS, 2},{FSK_MANAPTS, 4},{FSK_MANAPTS, 6}
        },{
            // Intelligence
            {FSK_INTELLIGENCE, 25},{FSK_INTELLIGENCE, 50},{FSK_INTELLIGENCE, 100}
        },{
            // Resistance
            {FSK_RESIST, 5},{FSK_RESIST, 10},{FSK_RESIST, 20}
        },{
            // Sorcery
            {FSK_SORCERY, 5},{FSK_SORCERY, 10},{FSK_SORCERY, 15}
        },{
            // Learning
            {FSK_LEARNING, 5},{FSK_LEARNING, 10},{FSK_LEARNING, 15}
        },{
            // Scouting
            {FSK_SCOUTING, 2},{FSK_SCOUTING, 4},{FSK_SCOUTING, 6}
        },{
            // Logistics
            {FSK_LOGISTICS, 10},{FSK_LOGISTICS, 20},{FSK_LOGISTICS, 30}
        },{
            // Pathfinding
            {FSK_PATHFINDING, 25},{FSK_PATHFINDING, 50},{FSK_PATHFINDING, 75}
        },{
            // Archery
            {FSK_ARCHERY, 10},{FSK_ARCHERY, 25},{FSK_ARCHERY, 50}
        },{
            // Ballistics
            {FSK_BALLISTICS, 1},{FSK_BALLISTICS, 2},{FSK_BALLISTICS, 3}
        },{
            // Offence
            {FSK_OFFENCE, 10},{FSK_OFFENCE, 20},{FSK_OFFENCE, 30}
        },{
            // Armorer
            {FSK_ARMORER, 5},{FSK_ARMORER, 10},{FSK_ARMORER, 15}
        }
    };
    //////////////////////////////////////////////////////////////////////////



}
