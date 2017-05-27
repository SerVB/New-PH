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

//#include "stdafx.h"
//#ifdef DO_INCLUDE_CREAT_DESC

public class cm_creatures {

    /*
     *	Creatures
     */
    final static int CREAT_UNKNOWN         = -1;
    /* Knight */
    final static int CREAT_PEASANT         = 0;
    final static int CREAT_ARCHER          = 1;
    final static int CREAT_PIKEMAN         = 2;
    final static int CREAT_SWORDSMAN       = 3;
    final static int CREAT_CAVALRY         = 4;
    final static int CREAT_PALADIN         = 5;

    /* Barbarian */
    final static int CREAT_GOBLIN          = 6;
    final static int CREAT_ORC             = 7;
    final static int CREAT_WOLF            = 8;
    final static int CREAT_OGRE            = 9;
    final static int CREAT_TROLL           = 10;
    final static int CREAT_CYCLOP          = 11;

    /* Wizard */
    final static int CREAT_HALFLING        = 12;
    final static int CREAT_BOAR            = 13;
    final static int CREAT_GOLEM           = 14;
    final static int CREAT_ROC             = 15;
    final static int CREAT_MAGE            = 16;
    final static int CREAT_TITAN           = 17;

    /* Warlock */
    final static int CREAT_CENTAUR         = 18;
    final static int CREAT_GARGOYLE        = 19;
    final static int CREAT_GRIFFIN         = 20;
    final static int CREAT_MINOTAUR        = 21;
    final static int CREAT_HYDRA           = 22;
    final static int CREAT_BLACK_DRAGON    = 23;

    /* Sorcer */
    final static int CREAT_SPRITE          = 24;
    final static int CREAT_DWARF           = 25;
    final static int CREAT_ELF             = 26;
    final static int CREAT_DRUID           = 27;
    final static int CREAT_UNICORN         = 28;
    final static int CREAT_PHOENIX         = 29;

    /* Necromant */
    final static int CREAT_SKELETON        = 30;
    final static int CREAT_ZOMBIE          = 31;
    final static int CREAT_MUMMY           = 32;
    final static int CREAT_VAMPIRE         = 33;
    final static int CREAT_LICH            = 34;
    final static int CREAT_BONE_DRAGON     = 35;

    /* Neutral = 9 штук */
    final static int CREAT_ROGUE           = 36;
    final static int CREAT_NOMAD           = 37;
    final static int CREAT_GHOST           = 38;
    final static int CREAT_GENIE           = 39;
    final static int CREAT_MEDUSA          = 40;
    final static int CREAT_EARTH_ELEMENTAL = 41;
    final static int CREAT_AIR_ELEMENTAL   = 42;
    final static int CREAT_FIRE_ELEMENTAL  = 43;
    final static int CREAT_WATER_ELEMENTAL = 44;

    final static int CREAT_COUNT           = 45;

    /* Random values */
    final static int CREAT_RANDOM          = 0x0F00;
    final static int CREAT_RANDOM_L1       = CREAT_RANDOM | 1;
    final static int CREAT_RANDOM_L2       = CREAT_RANDOM | 2;
    final static int CREAT_RANDOM_L3       = CREAT_RANDOM | 3;
    final static int CREAT_RANDOM_L4       = CREAT_RANDOM | 4;
    final static int CREAT_RANDOM_L5       = CREAT_RANDOM | 5;
    final static int CREAT_RANDOM_L6       = CREAT_RANDOM | 6;

////////////////////////////////////////////////////////////////////////////////
    final static int CPERKS_COUNT          = 21;           // Количество перков

    final static int CPERK_NONE            = 0;            // Нет перков
    final static int CPERK_DOUBLESHOT      = 0x00000001;   // Shots twice (rangers, grand elves, etc)
    final static int CPERK_DOUBLEATTACK    = 0x00000002;   // Attack the target twice (paladins, wolves, etc)
    final static int CPERK_NOMELEEPENALTY  = 0x00000004;   // scores full damage even at melee (монахи, личи)
    final static int CPERK_NONRETALATTACK  = 0x00000008;   // Non retaliated attack (vampires, rogues, sprites, hydras, etc)
    final static int CPERK_RETALTOALL      = 0x00000010;   // Retaliates against every attack (griffins)
    final static int CPERK_TWOHEXATTACK    = 0x00000020;   // Two-hex attack (dragons)
    final static int CPERK_ADJACENTATTACK  = 0x00000040;   // Attacks all adjacent enemies (hydras)
    final static int CPERK_LICHSHOOT       = 0x00000080;   // Range attack affects adjacent hexes except undeads (Liches)
    final static int CPERK_UNDEAD          = 0x00000100;   // (All necropolis creatures + ghosts) -- Нежить
    final static int CPERK_LIFELESS        = 0x00000200;   // (Golems, gargoyles, elementals) -- Безжизненное существо
    final static int CPERK_REGENERATES     = 0x00000400;   // Восстановление жизней каждый раунд (Trolls)
    final static int CPERK_JOUSTING        = 0x00000800;   // С разбега урон увеличивается (Cavalry)
    final static int CPERK_AIRMAGICIMM     = 0x00001000;   // (Air elementals)
    final static int CPERK_EARTHMAGICIMM   = 0x00002000;   // (Earth elementals)
    final static int CPERK_FIREMAGICIMM    = 0x00004000;   // (Fire elementals, Phoenix)
    final static int CPERK_WATERMAGICIMM   = 0x00008000;   // (Water elementals)
    final static int CPERK_40PROCRESIST    = 0x00010000;   // 40% magic resistance (dwarves)
    final static int CPERK_QUARTERDMG      = 0x00020000;   // receives only qurter damage from damage spells (golems)
    final static int CPERK_GHOST           = 0x00040000;   // Ghost perk
    final static int CPERK_DOHALF          = 0x00080000;   // Genie perk
    final static int CPERK_DRAINLIFES      = 0x00100000;   // Drains life (vampires)
    final static int CPERK_ALLMAGICIMM     = CPERK_AIRMAGICIMM | CPERK_EARTHMAGICIMM |
            CPERK_FIREMAGICIMM | CPERK_WATERMAGICIMM;    // Black draggons
////////////////////////////////////////////////////////////////////////////////
    final static int SPEED_SUPERSLOW   = 1;
    final static int SPEED_ULTRASLOW   = 2;
    final static int SPEED_VERYSLOW    = 3;
    final static int SPEED_EXTRASLOW   = 4;
    final static int SPEED_SLOW        = 5;
    final static int SPEED_SWIFT       = 6;
    final static int SPEED_EXTRASWIFT  = 7;
    final static int SPEED_VERYSWIFT   = 8;
    final static int SPEED_ULTRASWIFT  = 9;    // (2 to 2) -- ??
    final static int SPEED_SUPERSWIFT  = 10;   // (1 to 2 or 2 to 1) -- ??
    final static int SPEED_QUICK       = 11;   // (1 to 1) -- ??
    final static int SPEED_EXTRAQUICK  = 12;
    final static int SPEED_VERYQUICK   = 13;
    final static int SPEED_ULTRAQUICK  = 14;
    final static int SPEED_SUPERQUICK  = 15;
    final static int SPEED_FAST        = 16;
    final static int SPEED_EXTRAFAST   = 17;
    final static int SPEED_VERYFAST    = 18;
    final static int SPEED_ULTRAFAST   = 19;
    final static int SPEED_SUPERFAST   = 20;
    final static int SPEED_MAX         = 21;
////////////////////////////////////////////////////////////////////////////////
    final static int TRANS_WALK = 0; // Передвежение по земле
    final static int TRANS_FLY  = 1; // Передвижение по воздуху
////////////////////////////////////////////////////////////////////////////////

    final static CREAT_DESCRIPTOR[] CREAT_DESC = {
                /* Knight */
        new CREAT_DESCRIPTOR(
            // Peasant
            1,NATION_HIGHMEN,
            1,1,                                        // Attack, defence
            3,1,TRANS_WALK,0,                            // Speed, size, transportation, range attack
            2,                                            // Hit points
            1,1,                                        // Min, max damage
            new iMineralSet(20,0,0,0,0,0,0),                            // Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            22, 15, CPERK_NONE                            // growth rate, power index, perls
        ),
        new CREAT_DESCRIPTOR(
            // Archer
            2,NATION_HIGHMEN,
            5,3,
            5,1,TRANS_WALK,24,
            10,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            10, 140, CPERK_DOUBLESHOT
        ),
        new CREAT_DESCRIPTOR(
            // Pikeman
            3,NATION_HIGHMEN,
            5,9,
            5,1,TRANS_WALK,0,
            25,
            3,4,
            new iMineralSet(250,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            7, 250, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Swordsman
            4,NATION_HIGHMEN,
            7,9,
            7,1,TRANS_WALK,0,
            30,
            4,6,
            new iMineralSet(300,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 409, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Cavalry
            5,NATION_HIGHMEN,
            10,9,
            10,2,TRANS_WALK,0,
            40,
            5,10,
            new iMineralSet(375,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 689, CPERK_JOUSTING
        ),
        new CREAT_DESCRIPTOR(
            // Paladin
            6,NATION_HIGHMEN,
            9,12,
            10,1,TRANS_WALK,0,
            65,
            10,20,
            new iMineralSet(1000,0,0,0,0,1,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 1764, CPERK_DOUBLEATTACK
        ),

        /* Barbarian */
        new iMineralSet(
            // Goblin
            1,NATION_BARBARIANS,
            4,1,
            5,1,TRANS_WALK,0,
            3,
            1,2,
            new iMineralSet(40,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            20, 33, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Orc
            2,NATION_BARBARIANS,
            3,4,
            3,1,TRANS_WALK,5,
            15,
            3,4,
            new iMineralSet(175,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            10, 155, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Wolf
            3,NATION_BARBARIANS,
            6,2,
            9,2,TRANS_WALK,0,
            20,
            3,5,
            new iMineralSet(200,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            7, 262, CPERK_DOUBLEATTACK
        ),
        new CREAT_DESCRIPTOR(
            // Ogre
            4,NATION_BARBARIANS,
            9,5,
            5,1,TRANS_WALK,0,
            60,
            5,7,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 627, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Troll
            5,NATION_BARBARIANS,
            10,5,
            8,1,TRANS_WALK,15,
            40,
            7,12,
            new iMineralSet(700,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 807, CPERK_REGENERATES
        ),
        new CREAT_DESCRIPTOR(
            // Cyclop
            6,NATION_BARBARIANS,
            12,9,
            8,1,TRANS_WALK,0,
            100,
            12,24,
            new iMineralSet(1000,0,0,0,0,1,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 2036, CPERK_TWOHEXATTACK
        ),

        /* Wizard */
        new iMineralSet(
            // Halfling
            1,NATION_WIZARDS,
            2,1,
            3,1,TRANS_WALK,5,
            3,
            1,3,
            new iMineralSet(50,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            18, 41, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Boar
            2,NATION_WIZARDS,
            5,4,
            8,2,TRANS_WALK,0,
            15,
            2,3,
            new iMineralSet(150,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            8, 149, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Golem
            3,NATION_WIZARDS,
            7,10,
            4,1,TRANS_WALK,0,
            35,
            4,5,
            new iMineralSet(350,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 401, CPERK_LIFELESS | CPERK_QUARTERDMG
        ),
        new CREAT_DESCRIPTOR(
            // Roc
            4,NATION_WIZARDS,
            7,7,
            9,2,TRANS_FLY,0,
            40,
            5,8,
            new iMineralSet(400,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 527, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Mage
            5,NATION_WIZARDS,
            12,8,
            9,1,TRANS_WALK,15,
            35,
            7,9,
            new iMineralSet(700,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 748, CPERK_NOMELEEPENALTY
        ),
        new CREAT_DESCRIPTOR(
            // Titan
            6,NATION_WIZARDS,
            15,15,
            14,1,TRANS_WALK,20,
            300,
            20,30,
            new iMineralSet(5000,0,0,0,2,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            3, 6950, CPERK_NOMELEEPENALTY
        ),

        /* Warlock */
        new iMineralSet(
            // Centaur
            1,NATION_BEASTMEN,
            3,1,
            5,2,TRANS_WALK,5,
            5,
            1,2,
            new iMineralSet(60,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            18, 47, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Gargoyle
            2,NATION_BEASTMEN,
            4,7,
            9,1,TRANS_FLY,0,
            15,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            8, 175, CPERK_LIFELESS
        ),
        new CREAT_DESCRIPTOR(
            // Griffin
            3,NATION_BEASTMEN,
            6,6,
            8,2,TRANS_FLY,0,
            25,
            3,5,
            new iMineralSet(300,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 334, CPERK_RETALTOALL
        ),
        new CREAT_DESCRIPTOR(
            // Minotaur
            4,NATION_BEASTMEN,
            9,8,
            8,1,TRANS_WALK,0,
            45,
            5,10,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 682, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Hydra
            5,NATION_BEASTMEN,
            8,9,
            3,2,TRANS_WALK,0,
            75,
            6,12,
            new iMineralSet(800,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 872, CPERK_NONRETALATTACK | CPERK_ADJACENTATTACK
        ),
        new CREAT_DESCRIPTOR(
            // Black dragon
            6,NATION_BEASTMEN,
            14,14,
            15,2,TRANS_FLY,0,
            300,
            25,50,
            new iMineralSet(5000,0,0,0,0,0,2),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            3, 8528, CPERK_ALLMAGICIMM | CPERK_TWOHEXATTACK
        ),

        /* Sorcer */
        new iMineralSet(
            // Sprite
            1,NATION_ELVES,
            4,2,
            6,1,TRANS_FLY,0,
            2,
            1,2,
            new iMineralSet(50,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            18, 39, CPERK_NONRETALATTACK
        ),
        new CREAT_DESCRIPTOR(
            // Dwarf
            2,NATION_ELVES,
            6,6,
            4,1,TRANS_WALK,0,
            20,
            2,4,
            new iMineralSet(250,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            8, 190, CPERK_40PROCRESIST
        ),
        new CREAT_DESCRIPTOR(
            // Elf
            3,NATION_ELVES,
            5,5,
            8,1,TRANS_WALK,20,
            15,
            2,3,
            new iMineralSet(300,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 194, CPERK_DOUBLESHOT
        ),
        new CREAT_DESCRIPTOR(
            // Druid
            4,NATION_ELVES,
            7,7,
            8,1,TRANS_WALK,15,
            25,
            5,8,
            new iMineralSet(400,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 433, CPERK_NOMELEEPENALTY
        ),
        new CREAT_DESCRIPTOR(
            // Unicorn
            5,NATION_ELVES,
            10,9,
            9,2,TRANS_WALK,0,
            60,
            7,14,
            new iMineralSet(700,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 819, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Phoenix
            6,NATION_ELVES,
            12,10,
            18,2,TRANS_FLY,0,
            100,
            20,40,
            new iMineralSet(1500,0,0,1,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            3, 3064, CPERK_FIREMAGICIMM | CPERK_TWOHEXATTACK
        ),


        /* Necromant */
        new iMineralSet(
            // Skeleton
            1,NATION_UNDEADS,
            4,3,
            5,1,TRANS_WALK,0,
            4,
            2,3,
            new iMineralSet(75,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            18, 62, CPERK_UNDEAD
        ),
        new CREAT_DESCRIPTOR(
            // Zombie
            2,NATION_UNDEADS,
            5,2,
            5,1,TRANS_WALK,0,
            20,
            2,3,
            new iMineralSet(200,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            8, 153, CPERK_UNDEAD
        ),
        new CREAT_DESCRIPTOR(
            // Mummy
            3,NATION_UNDEADS,
            6,6,
            7,1,TRANS_WALK,0,
            30,
            3,4,
            new iMineralSet(300,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6, 320, CPERK_UNDEAD
        ),
        new CREAT_DESCRIPTOR(
            // Vampire
            4,NATION_UNDEADS,
            8,6,
            9,1,TRANS_FLY,0,
            40,
            5,7,
            new iMineralSet(650,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            5, 746, CPERK_UNDEAD | CPERK_NONRETALATTACK | CPERK_DRAINLIFES
        ),
        new CREAT_DESCRIPTOR(
            // Lich
            5,NATION_UNDEADS,
            7,13,
            8,1,TRANS_WALK,15,
            35,
            8,10,
            new iMineralSet(900,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 795, CPERK_UNDEAD | CPERK_NOMELEEPENALTY | CPERK_LICHSHOOT
        ),
        new CREAT_DESCRIPTOR(
            // Bone dragon
            6,NATION_UNDEADS,
            11,9,
            13,2,TRANS_FLY,0,
            150,
            25,45,
            new iMineralSet(1500,0,0,1,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            3, 3559, CPERK_UNDEAD
        ),

        /* Neutral = 9 */
        new iMineralSet(
            // Rogue
            1,NATION_NEUTRAL,
            6,1,
            6,1,TRANS_WALK,0,
            4,
            1,2,
            new iMineralSet(50,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            8, 54, CPERK_NONRETALATTACK
        ),
        new CREAT_DESCRIPTOR(
            // Nomad
            3,NATION_NEUTRAL,
            7,5,
            9,2,TRANS_WALK,0,
            20,
            2,5,
            new iMineralSet(200,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            6,244, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Ghost
            5,NATION_NEUTRAL,
            8,6,
            7,1,TRANS_FLY,0,
            20,
            4,6,
            new iMineralSet(1000,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 468, CPERK_UNDEAD | CPERK_GHOST
        ),
        new CREAT_DESCRIPTOR(
            // Genie
            6,NATION_NEUTRAL,
            10,9,
            10,1,TRANS_FLY,0,
            50,
            20,30,
            new iMineralSet(750,0,0,0,1,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            3, 1725, CPERK_DOHALF
        ),
        new CREAT_DESCRIPTOR(
            // Medusa
            4,NATION_NEUTRAL,
            8,9,
            5,2,TRANS_WALK,0,
            35,
            6,10,
            new iMineralSet(350,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 600, CPERK_NONE
        ),
        new CREAT_DESCRIPTOR(
            // Earth elemental
            4,NATION_NEUTRAL,
            8,8,
            4,1,TRANS_WALK,0,
            50,
            4,5,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 525, CPERK_EARTHMAGICIMM | CPERK_LIFELESS
        ),
        new CREAT_DESCRIPTOR(
            // Air elemental
            4,NATION_NEUTRAL,
            7,7,
            7,1,TRANS_WALK,0,
            35,
            2,8,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 428, CPERK_AIRMAGICIMM | CPERK_LIFELESS
        ),
        new CREAT_DESCRIPTOR(
            // Fire elemental
            4,NATION_NEUTRAL,
            8,6,
            6,1,TRANS_WALK,0,
            40,
            4,5,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 455, CPERK_FIREMAGICIMM | CPERK_LIFELESS
        ),
        new CREAT_DESCRIPTOR(
            // Water elemental
            4,NATION_NEUTRAL,
            6,8,
            5,1,TRANS_WALK,0,
            45,
            3,7,
            new iMineralSet(500,0,0,0,0,0,0),                            //    Gold,    Ore,    Wood,    Merc,    Gems,    Cryst,    Sulfur
            4, 512, CPERK_WATERMAGICIMM | CPERK_LIFELESS
        )
    );

    //////////////////////////////////////////////////////////////////////////
    final static long[][][] INITIAL_HERO_ARMY = {
        {
            // Knight
            {
                CREAT_PEASANT, 25, 50
            },{
                CREAT_ARCHER, 0, 7
            }

        },{
            // Barbarian
            {
                CREAT_GOBLIN, 15, 30
            },{
                CREAT_ORC, 0, 8
            }
        },{
            // Wizard
            {
                CREAT_HALFLING, 10, 20
            },{
                CREAT_BOAR, 0, 6
            }
        },{
            // Warlock
            {
                CREAT_CENTAUR, 11, 22
            },{
                CREAT_GARGOYLE, 0, 6
            }
        },{
            // Sorceress
            {
                CREAT_SPRITE, 14, 28
            },{
                CREAT_DWARF, 0, 8
            }
        },{
            // Necromancer
            {
                CREAT_SKELETON, 20, 40
            },{
                CREAT_ZOMBIE, 0, 9
            }
        }
    };
}
