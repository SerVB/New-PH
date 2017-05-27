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

import Common.iMineralSet;

/**
 * Creatures
 */
public class CREAT {

    public final static int UNKNOWN         = -1;

    /* Knight */
    public final static int PEASANT         = 0;
    public final static int ARCHER          = 1;
    public final static int PIKEMAN         = 2;
    public final static int SWORDSMAN       = 3;
    public final static int CAVALRY         = 4;
    public final static int PALADIN         = 5;

    /* Barbarian */
    public final static int GOBLIN          = 6;
    public final static int ORC             = 7;
    public final static int WOLF            = 8;
    public final static int OGRE            = 9;
    public final static int TROLL           = 10;
    public final static int CYCLOP          = 11;

    /* Wizard */
    public final static int HALFLING        = 12;
    public final static int BOAR            = 13;
    public final static int GOLEM           = 14;
    public final static int ROC             = 15;
    public final static int MAGE            = 16;
    public final static int TITAN           = 17;

    /* Warlock */
    public final static int CENTAUR         = 18;
    public final static int GARGOYLE        = 19;
    public final static int GRIFFIN         = 20;
    public final static int MINOTAUR        = 21;
    public final static int HYDRA           = 22;
    public final static int BLACK_DRAGON    = 23;

    /* Sorcer */
    public final static int SPRITE          = 24;
    public final static int DWARF           = 25;
    public final static int ELF             = 26;
    public final static int DRUID           = 27;
    public final static int UNICORN         = 28;
    public final static int PHOENIX         = 29;

    /* Necromant */
    public final static int SKELETON        = 30;
    public final static int ZOMBIE          = 31;
    public final static int MUMMY           = 32;
    public final static int VAMPIRE         = 33;
    public final static int LICH            = 34;
    public final static int BONE_DRAGON     = 35;

    /* Neutral = 9 creatures */
    public final static int ROGUE           = 36;
    public final static int NOMAD           = 37;
    public final static int GHOST           = 38;
    public final static int GENIE           = 39;
    public final static int MEDUSA          = 40;
    public final static int EARTH_ELEMENTAL = 41;
    public final static int AIR_ELEMENTAL   = 42;
    public final static int FIRE_ELEMENTAL  = 43;
    public final static int WATER_ELEMENTAL = 44;

    public final static int COUNT           = 45;

    /* Random values */
    public final static int RANDOM          = 0x0F00;
    public final static int RANDOM_L1       = RANDOM | 1;
    public final static int RANDOM_L2       = RANDOM | 2;
    public final static int RANDOM_L3       = RANDOM | 3;
    public final static int RANDOM_L4       = RANDOM | 4;
    public final static int RANDOM_L5       = RANDOM | 5;
    public final static int RANDOM_L6       = RANDOM | 6;

    public final static DESCRIPTOR[] DESC = {
        /* Knight */

        new DESCRIPTOR(
            // Peasant
            1,NATION.HIGHMEN,                           // Level, nation
            1,1,                                        // Attack, defence
            3,1,TRANS.WALK,0,                           // Speed, size, transportation, range attack
            2,                                          // Hit points
            1,1,                                        // Min, max damage
            new iMineralSet(20,0,0,0,0,0,0),            // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            22, 15, CPERK.NONE                          // growth rate, power index, perks
        ),
        new DESCRIPTOR(
            // Archer
            2,NATION.HIGHMEN,
            5,3,
            5,1,TRANS.WALK,24,
            10,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            10, 140, CPERK.DOUBLESHOT
        ),
        new DESCRIPTOR(
            // Pikeman
            3,NATION.HIGHMEN,
            5,9,
            5,1,TRANS.WALK,0,
            25,
            3,4,
            new iMineralSet(250,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            7, 250, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Swordsman
            4,NATION.HIGHMEN,
            7,9,
            7,1,TRANS.WALK,0,
            30,
            4,6,
            new iMineralSet(300,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 409, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Cavalry
            5,NATION.HIGHMEN,
            10,9,
            10,2,TRANS.WALK,0,
            40,
            5,10,
            new iMineralSet(375,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 689, CPERK.JOUSTING
        ),
        new DESCRIPTOR(
            // Paladin
            6,NATION.HIGHMEN,
            9,12,
            10,1,TRANS.WALK,0,
            65,
            10,20,
            new iMineralSet(1000,0,0,0,0,1,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 1764, CPERK.DOUBLEATTACK
        ),

        /* Barbarian */
        new DESCRIPTOR(
            // Goblin
            1,NATION.BARBARIANS,
            4,1,
            5,1,TRANS.WALK,0,
            3,
            1,2,
            new iMineralSet(40,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            20, 33, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Orc
            2,NATION.BARBARIANS,
            3,4,
            3,1,TRANS.WALK,5,
            15,
            3,4,
            new iMineralSet(175,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            10, 155, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Wolf
            3,NATION.BARBARIANS,
            6,2,
            9,2,TRANS.WALK,0,
            20,
            3,5,
            new iMineralSet(200,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            7, 262, CPERK.DOUBLEATTACK
        ),
        new DESCRIPTOR(
            // Ogre
            4,NATION.BARBARIANS,
            9,5,
            5,1,TRANS.WALK,0,
            60,
            5,7,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 627, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Troll
            5,NATION.BARBARIANS,
            10,5,
            8,1,TRANS.WALK,15,
            40,
            7,12,
            new iMineralSet(700,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 807, CPERK.REGENERATES
        ),
        new DESCRIPTOR(
            // Cyclop
            6,NATION.BARBARIANS,
            12,9,
            8,1,TRANS.WALK,0,
            100,
            12,24,
            new iMineralSet(1000,0,0,0,0,1,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 2036, CPERK.TWOHEXATTACK
        ),

        /* Wizard */
        new DESCRIPTOR(
            // Halfling
            1,NATION.WIZARDS,
            2,1,
            3,1,TRANS.WALK,5,
            3,
            1,3,
            new iMineralSet(50,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            18, 41, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Boar
            2,NATION.WIZARDS,
            5,4,
            8,2,TRANS.WALK,0,
            15,
            2,3,
            new iMineralSet(150,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            8, 149, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Golem
            3,NATION.WIZARDS,
            7,10,
            4,1,TRANS.WALK,0,
            35,
            4,5,
            new iMineralSet(350,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 401, CPERK.LIFELESS | CPERK.QUARTERDMG
        ),
        new DESCRIPTOR(
            // Roc
            4,NATION.WIZARDS,
            7,7,
            9,2,TRANS.FLY,0,
            40,
            5,8,
            new iMineralSet(400,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 527, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Mage
            5,NATION.WIZARDS,
            12,8,
            9,1,TRANS.WALK,15,
            35,
            7,9,
            new iMineralSet(700,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 748, CPERK.NOMELEEPENALTY
        ),
        new DESCRIPTOR(
            // Titan
            6,NATION.WIZARDS,
            15,15,
            14,1,TRANS.WALK,20,
            300,
            20,30,
            new iMineralSet(5000,0,0,0,2,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            3, 6950, CPERK.NOMELEEPENALTY
        ),

        /* Warlock */
        new DESCRIPTOR(
            // Centaur
            1,NATION.BEASTMEN,
            3,1,
            5,2,TRANS.WALK,5,
            5,
            1,2,
            new iMineralSet(60,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            18, 47, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Gargoyle
            2,NATION.BEASTMEN,
            4,7,
            9,1,TRANS.FLY,0,
            15,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            8, 175, CPERK.LIFELESS
        ),
        new DESCRIPTOR(
            // Griffin
            3,NATION.BEASTMEN,
            6,6,
            8,2,TRANS.FLY,0,
            25,
            3,5,
            new iMineralSet(300,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 334, CPERK.RETALTOALL
        ),
        new DESCRIPTOR(
            // Minotaur
            4,NATION.BEASTMEN,
            9,8,
            8,1,TRANS.WALK,0,
            45,
            5,10,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 682, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Hydra
            5,NATION.BEASTMEN,
            8,9,
            3,2,TRANS.WALK,0,
            75,
            6,12,
            new iMineralSet(800,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 872, CPERK.NONRETALATTACK | CPERK.ADJACENTATTACK
        ),
        new DESCRIPTOR(
            // Black dragon
            6,NATION.BEASTMEN,
            14,14,
            15,2,TRANS.FLY,0,
            300,
            25,50,
            new iMineralSet(5000,0,0,0,0,0,2), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            3, 8528, CPERK.ALLMAGICIMM | CPERK.TWOHEXATTACK
        ),

        /* Sorcer */
        new DESCRIPTOR(
            // Sprite
            1,NATION.ELVES,
            4,2,
            6,1,TRANS.FLY,0,
            2,
            1,2,
            new iMineralSet(50,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            18, 39, CPERK.NONRETALATTACK
        ),
        new DESCRIPTOR(
            // Dwarf
            2,NATION.ELVES,
            6,6,
            4,1,TRANS.WALK,0,
            20,
            2,4,
            new iMineralSet(250,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            8, 190, CPERK.PROCRESIST40
        ),
        new DESCRIPTOR(
            // Elf
            3,NATION.ELVES,
            5,5,
            8,1,TRANS.WALK,20,
            15,
            2,3,
            new iMineralSet(300,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 194, CPERK.DOUBLESHOT
        ),
        new DESCRIPTOR(
            // Druid
            4,NATION.ELVES,
            7,7,
            8,1,TRANS.WALK,15,
            25,
            5,8,
            new iMineralSet(400,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 433, CPERK.NOMELEEPENALTY
        ),
        new DESCRIPTOR(
            // Unicorn
            5,NATION.ELVES,
            10,9,
            9,2,TRANS.WALK,0,
            60,
            7,14,
            new iMineralSet(700,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 819, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Phoenix
            6,NATION.ELVES,
            12,10,
            18,2,TRANS.FLY,0,
            100,
            20,40,
            new iMineralSet(1500,0,0,1,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            3, 3064, CPERK.FIREMAGICIMM | CPERK.TWOHEXATTACK
        ),


        /* Necromant */
        new DESCRIPTOR(
            // Skeleton
            1,NATION.UNDEADS,
            4,3,
            5,1,TRANS.WALK,0,
            4,
            2,3,
            new iMineralSet(75,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            18, 62, CPERK.UNDEAD
        ),
        new DESCRIPTOR(
            // Zombie
            2,NATION.UNDEADS,
            5,2,
            5,1,TRANS.WALK,0,
            20,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            8, 153, CPERK.UNDEAD
        ),
        new DESCRIPTOR(
            // Mummy
            3,NATION.UNDEADS,
            6,6,
            7,1,TRANS.WALK,0,
            30,
            3,4,
            new iMineralSet(300,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6, 320, CPERK.UNDEAD
        ),
        new DESCRIPTOR(
            // Vampire
            4,NATION.UNDEADS,
            8,6,
            9,1,TRANS.FLY,0,
            40,
            5,7,
            new iMineralSet(650,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            5, 746, CPERK.UNDEAD | CPERK.NONRETALATTACK | CPERK.DRAINLIFES
        ),
        new DESCRIPTOR(
            // Lich
            5,NATION.UNDEADS,
            7,13,
            8,1,TRANS.WALK,15,
            35,
            8,10,
            new iMineralSet(900,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 795, CPERK.UNDEAD | CPERK.NOMELEEPENALTY | CPERK.LICHSHOOT
        ),
        new DESCRIPTOR(
            // Bone dragon
            6,NATION.UNDEADS,
            11,9,
            13,2,TRANS.FLY,0,
            150,
            25,45,
            new iMineralSet(1500,0,0,1,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            3, 3559, CPERK.UNDEAD
        ),

        /* Neutral = 9 */
        new DESCRIPTOR(
            // Rogue
            1,NATION.NEUTRAL,
            6,1,
            6,1,TRANS.WALK,0,
            4,
            1,2,
            new iMineralSet(50,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            8, 54, CPERK.NONRETALATTACK
        ),
        new DESCRIPTOR(
            // Nomad
            3,NATION.NEUTRAL,
            7,5,
            9,2,TRANS.WALK,0,
            20,
            2,5,
            new iMineralSet(200,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            6,244, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Ghost
            5,NATION.NEUTRAL,
            8,6,
            7,1,TRANS.FLY,0,
            20,
            4,6,
            new iMineralSet(1000,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 468, CPERK.UNDEAD | CPERK.GHOST
        ),
        new DESCRIPTOR(
            // Genie
            6,NATION.NEUTRAL,
            10,9,
            10,1,TRANS.FLY,0,
            50,
            20,30,
            new iMineralSet(750,0,0,0,1,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            3, 1725, CPERK.DOHALF
        ),
        new DESCRIPTOR(
            // Medusa
            4,NATION.NEUTRAL,
            8,9,
            5,2,TRANS.WALK,0,
            35,
            6,10,
            new iMineralSet(350,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 600, CPERK.NONE
        ),
        new DESCRIPTOR(
            // Earth elemental
            4,NATION.NEUTRAL,
            8,8,
            4,1,TRANS.WALK,0,
            50,
            4,5,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 525, CPERK.EARTHMAGICIMM | CPERK.LIFELESS
        ),
        new DESCRIPTOR(
            // Air elemental
            4,NATION.NEUTRAL,
            7,7,
            7,1,TRANS.WALK,0,
            35,
            2,8,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 428, CPERK.AIRMAGICIMM | CPERK.LIFELESS
        ),
        new DESCRIPTOR(
            // Fire elemental
            4,NATION.NEUTRAL,
            8,6,
            6,1,TRANS.WALK,0,
            40,
            4,5,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 455, CPERK.FIREMAGICIMM | CPERK.LIFELESS
        ),
        new DESCRIPTOR(
            // Water elemental
            4,NATION.NEUTRAL,
            6,8,
            5,1,TRANS.WALK,0,
            45,
            3,7,
            new iMineralSet(500,0,0,0,0,0,0), // Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            4, 512, CPERK.WATERMAGICIMM | CPERK.LIFELESS
        )
    };

    /**
     * Creature descriptor.
     */
    public static class DESCRIPTOR {

        /**
         * Unit level (1-6).
         */
        public int level;

        /**
         * Unit nation.
         */
        public int nation;

        /**
         * Attack skill.
         */
        public long attack;

        /**
         * Deffence skill.
         */
        public long defence;

        /**
         * Unit speed (1-20).
         */
        public int speed;

        /**
         * Unit size (1 or 2 - used in battle).
         */
        public int size;

        /**
         * Transportation method.
         */
        public int transType;

        /**
         * 0 -- means no range attack.
         */
        public long shots;

        /**
         * Hit points (health).
         */
        public long hits;

        /**
         * Minimum damage.
         */
        public long damage_min;

        /**
         * Maximum damage.
         */
        public long damage_max;

        /**
         * Cost per unit.
         */
        public iMineralSet cost;

        /**
         * Growth rate.
         */
        public long growth;

        /**
         * Power index (used for AI).
         */
        public long pidx;

        /**
         * Creature perks.
         */
        public int perks;

        /**
         * Конструктор.
         * @param level Unit level (1-6).
         * @param nation Unit nation.
         * @param attack Attack skill.
         * @param defence Deffence skill.
         * @param speed Unit speed (1-20).
         * @param size Unit size (1 or 2 - used in battle).
         * @param transType Transportation method.
         * @param shots 0 -- means no range attack.
         * @param hits Hit points (health).
         * @param damage_min Minimum damage.
         * @param damage_max Maximum damage.
         * @param cost Cost per unit.
         * @param growth Growth rate.
         * @param pidx Power index (used for AI).
         * @param perks Creature perks.
         */
        public DESCRIPTOR(
                int level,
                int nation,

                long attack,
                long defence,
                int speed,
                int size,

                int transType,
                long shots,
                long hits,
                long damage_min,
                long damage_max,

                iMineralSet cost,
                long growth,
                long pidx,
                int perks
        ) {
            this.level = level;
            this.nation = nation;

            this.attack = attack;
            this.defence = defence;
            this.speed = speed;
            this.size = size;

            this.transType = transType;
            this.shots = shots;
            this.hits = hits;
            this.damage_min = damage_min;
            this.damage_max = damage_max;

            this.cost = cost;
            this.growth = growth;
            this.pidx = pidx;
            this.perks = perks;
        }
    }
}
