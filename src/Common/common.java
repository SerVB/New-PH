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
 * all
 * copies or substantial portions of the Software.
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
     * Surface type mask
     * Маски поверхности каждого типа
     */
    final static long[] SURF_TYPE_MASK = {
        1 << STYPE_WATER,
        1 << STYPE_SAND,
        1 << STYPE_DIRT,
        1 << STYPE_GRASS,
        1 << STYPE_SWAMP,
        1 << STYPE_LAVA,
        1 << STYPE_WASTELAND,
        1 << STYPE_DESERT,
        1 << STYPE_SNOW,
        1 << STYPE_NDESERT,
        1 << STYPE_PAVEMENT,
        1 << STYPE_NWASTELAND
    };

    /**
     * Стоимость перемещения по поверхности каждого типа
     */
    final static long[] SURF_MOVE_COST = {
        255, // Water
        12,  // Sand
        6,   // Dirt
        6,   // Grass
        14,  // Swamp
        10,  // Lava
        8,   // Wasteland
        12,  // Desert
        10,  // Snow
        12,  // new desert
        4,   // Pavement
        9    // new wasteland
    };

    /**
     * Цвет поверхности (для миникарты, что ли?)
     */
    final static int[] SURF_COLOR = {
        RGB16(8, 28, 128),  // Water
        RGB16(214,182,148), // Sand
        RGB16(99,48,8),     // Dirt
        RGB16(24,97,16),    // Grass
        RGB16(0,44,0),      // Swamp
        RGB16(48,48,48),    // Lava
        RGB16(165,85,16),   // Wasteland
        RGB16(181,138,24),  // Desert
        RGB16(220,220,220), // Snow
        RGB16(192,160,0),   // new Desert
        RGB16(160,160,160), // Pavement
        RGB16(192,192,160)  // new wasteland
    };

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
    /**
     * Начальные ресурсы для каждого из уровней сложности.
     * В INITIAL_RESOURCES[0] для человека,
     * в INITIAL_RESOURCES[1] для компьютера.
     */
    final static iMineralSet[][] INITIAL_RESOURCES = {
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
////////////////////////////////////////////////////////////////////////////////

    /**
     * Цвета для каждого из игроков (16-ти битные, Карл!).
     */
    final static long[] PLAYER_COLORS = {
        0x1F<<11,           // Red,   0x1F == 0b11111  -> На красный 5 бит
        0x30<<5,            // Green, 0x30 == 0b110000 -> На зеленый 6 бит
        0x1F,               // Blue,                   -> На синий тоже 5 бит
        0x30<<5 | 0x1F,     // Cyan
        0x1D<<11 | 0x1F,    // Magenta
        0x1D<<11 | 0x38<<5  // Yellow
    };

    /**
     * Цвета текста? для каждого из игроков.
     */
    final static String[] PLAYER_TEXT_COLORS = {
        "#FF99",
        "#F8E8",
        "#F99F",
        "#F7EE",
        "#FE7E",
        "#FEE7"
    };

    /**
     * Типы идеологий? НИПов?.
     */
    final static int[] NATION_TYPE_IDEOLOGY = {
        IDEOLOGY_NEUTRAL,
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL,
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL,
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL
    };

    /**
     * Типы идеологий? героев.
     */
    final static int[] HERO_TYPE_IDEOLOGY = {
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL,
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL,
        IDEOLOGY_GOOD,
        IDEOLOGY_EVIL
    };
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    /**
     * Доступные для обвеса артефактами места героя
     * (голова, руки, ноги и т.д.).
     */
    final static int[] HERO_ART_CELL_ASSIGN = {
        ART_ASSIGN_HEAD,
        ART_ASSIGN_NECK,
        ART_ASSIGN_TORSO,
        ART_ASSIGN_HANDS,
        ART_ASSIGN_HANDS,
        ART_ASSIGN_FINGERS,
        ART_ASSIGN_FINGERS,
        ART_ASSIGN_SHOULDERS,
        ART_ASSIGN_LEGS,
        ART_ASSIGN_FEET,
        ART_ASSIGN_MISC,
        ART_ASSIGN_MISC,
        ART_ASSIGN_MISC,
        ART_ASSIGN_MISC
    };

    /**
     * Описание бонусов для героев каждой расы.
     * Пустым комментарием обозначается фактическое отсутствие бонуса, т.е.
     * можно будет потом убрать!
     */
    final static long[][][] ULTART_STDMODIF = {
        {
            // Knight
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 4}, {FSK_KNOWLEDGE, 4}, {FSK_LOGISTICS, 20}, {FSK_SPEED, 2}
        },{
            // Barbarian
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 4}, {FSK_KNOWLEDGE, 4}, {FSK_RESIST, 30}, {/**/FSK_OFFENCE, 0}
        },{
            // Wizard
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 6}, {FSK_KNOWLEDGE, 6}, {/**/FSK_RESIST, 0}, {/**/FSK_OFFENCE, 0}
        },{
            // Warlock
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 6}, {FSK_KNOWLEDGE, 6}, {/**/FSK_RESIST, 0}, {/**/FSK_OFFENCE, 0}
        },{
            // Sorceress
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 6}, {FSK_KNOWLEDGE, 6}, {/**/FSK_RESIST, 0}, {/**/FSK_OFFENCE, 0}
        },{
            // Necromancer
            {FSK_MIN_GOLD, 5000}, {FSK_ATTACK, 4}, {FSK_DEFENCE, 4}, {FSK_POWER, 4}, {FSK_KNOWLEDGE, 4}, {FSK_NECROMANCY, 15}, {/**/FSK_OFFENCE, 0}
        }
    };

    /**
     * Специальные маски героев для каждой из рас.
     */
    final static long[] ULTART_SPECFLAGS = {
        SHF_INVALID,        // Knight
        SHF_NORANGEPENALTY, // Barbarian
        SHF_SUMRESBOUNS,    // Wizard
        SHF_MANARESTORE,    // Warlock
        SHF_DMGSPBONUS,     // Sorceress
        SHF_NECRBONUS       // Necromancer
    };

    /**
     * Основные навыки героев для каждой из рас. В чем измеряется???
     */
    final static long[][] HERO_PRIM_SKILL = {
        {25,50,5,20},    // Knight
        {60,25,5,10},    // Barbarian
        {10,10,40,40},    // Wizard
        {15,10,45,30},    // Warlock
        {15,15,30,40},    // Sorceress
        {15,15,40,30}    // Necromancer
    };

    /**
     * Вторичные навыки героев для каждой из рас. В чем измеряется???
     * Описание очереди навыков для изучения???
     * или доступных для изучения навыков???
     */
    final static long[][] HERO_SEC_SKILL = {
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

    /**
     * 72 штуки каких-то точек.
     */
    final static iPoint[] HERO_FLAG_ANCHOR = {
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

    /**
     * Делитель материалов???.
     */
    final static long[] MINERALS_DIVIDER = {
        1000, 2, 2, 1, 1, 1, 1
    };

}
