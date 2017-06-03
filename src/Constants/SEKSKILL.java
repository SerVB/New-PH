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
 * Secondary skill.
 */
public class SEKSKILL {

    /**
     * Описание вторичных навыков.
     * Для каждого навыка описана каждая из трех ступеней.
     */
    public final static int[][][] DESC = {
        {
            // Estates
            {FSK.MIN_GOLD, 250},{FSK.MIN_GOLD, 500},{FSK.MIN_GOLD, 1000},
        },{
            // Leadership
            {FSK.MORALE, 1},{FSK.MORALE, 2},{FSK.MORALE, 3}
        },{
            // Luck
            {FSK.LUCK, 1},{FSK.LUCK, 2},{FSK.LUCK, 3}
        },{
            // Diplomacy
            {FSK.DIPLOMACY, 20},{FSK.DIPLOMACY, 40},{FSK.DIPLOMACY, 60}
        },{
            // Air
            {FSK.MAG_AIR, 1},{FSK.MAG_AIR, 2},{FSK.MAG_AIR, 3}
        },{
            // Earth
            {FSK.MAG_EARTH, 1},{FSK.MAG_EARTH, 2},{FSK.MAG_EARTH, 3}
        },{
            // Fire
            {FSK.MAG_FIRE, 1},{FSK.MAG_FIRE, 2},{FSK.MAG_FIRE, 3}
        },{
            // Water
            {FSK.MAG_WATER, 1},{FSK.MAG_WATER, 2},{FSK.MAG_WATER, 3}
        },{
            // Wisdom
            {FSK.WISDOM, 1},{FSK.WISDOM, 2},{FSK.WISDOM, 3}
        },{
            // Necromancy
            {FSK.NECROMANCY, 10},{FSK.NECROMANCY, 20},{FSK.NECROMANCY, 30}
        },{
            // Mysticism
            {FSK.MANAPTS, 2},{FSK.MANAPTS, 4},{FSK.MANAPTS, 6}
        },{
            // Intelligence
            {FSK.INTELLIGENCE, 25},{FSK.INTELLIGENCE, 50},{FSK.INTELLIGENCE, 100}
        },{
            // Resistance
            {FSK.RESIST, 5},{FSK.RESIST, 10},{FSK.RESIST, 20}
        },{
            // Sorcery
            {FSK.SORCERY, 5},{FSK.SORCERY, 10},{FSK.SORCERY, 15}
        },{
            // Learning
            {FSK.LEARNING, 5},{FSK.LEARNING, 10},{FSK.LEARNING, 15}
        },{
            // Scouting
            {FSK.SCOUTING, 2},{FSK.SCOUTING, 4},{FSK.SCOUTING, 6}
        },{
            // Logistics
            {FSK.LOGISTICS, 10},{FSK.LOGISTICS, 20},{FSK.LOGISTICS, 30}
        },{
            // Pathfinding
            {FSK.PATHFINDING, 25},{FSK.PATHFINDING, 50},{FSK.PATHFINDING, 75}
        },{
            // Archery
            {FSK.ARCHERY, 10},{FSK.ARCHERY, 25},{FSK.ARCHERY, 50}
        },{
            // Ballistics
            {FSK.BALLISTICS, 1},{FSK.BALLISTICS, 2},{FSK.BALLISTICS, 3}
        },{
            // Offence
            {FSK.OFFENCE, 10},{FSK.OFFENCE, 20},{FSK.OFFENCE, 30}
        },{
            // Armorer
            {FSK.ARMORER, 5},{FSK.ARMORER, 10},{FSK.ARMORER, 15}
        }
    };
}
