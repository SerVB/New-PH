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
//
//#define DO_INCLUDE_CREAT_DESC
//#include "cm_creatures.cpp"

/**
 *
 */
public class cm_castles {

    // Castle types //
    final static int CTLT_CITADEL    = 0;
    final static int CTLT_STRONGHOLD = 1;
    final static int CTLT_TOWER      = 2;
    final static int CTLT_DUNGEON    = 3;
    final static int CTLT_FORTRESS   = 4;
    final static int CTLT_NECROPOLIS = 5;
    final static int CTLT_RANDOM     = 6;
    final static int CTLT_COUNT      = 7;
    final static int CTLT_INVALID    = 0xFF;

    // Castle dimensions //
    final static int CTLS_SMALL  = 1; // Village
    final static int CTLS_MEDIUM = 2; // Town
    final static int CTLS_LARGE  = 3; // City
    final static int CTLS_COUNT  = 4;

    // Castle orientation //
    final static int CTLO_LEFT  = 0; // Left
    final static int CTLO_RIGHT = 1; // Right
    final static int CTLO_COUNT = 2;

    // Castle constructions //

    final static int CTLCNST_INVALID = -1;

    // Dwellings = Жилища //
    final static int CTLCNST_DWELLINGS = 0;

    final static int CTLCNST_DWELL_PEASNHUT    = 0;
    final static int CTLCNST_DWELL_ARCHTOWER   = 1;
    final static int CTLCNST_DWELL_GHOUSE      = 2;
    final static int CTLCNST_DWELL_BARRACKS    = 3;
    final static int CTLCNST_DWELL_JARENA      = 4;
    final static int CTLCNST_DWELL_CATHEDRAL   = 5;
    final static int CTLCNST_DWELL_GBARRACKS   = 6;
    final static int CTLCNST_DWELL_ORCTOWER    = 7;
    final static int CTLCNST_DWELL_WOLFDEN     = 8;
    final static int CTLCNST_DWELL_OGREFORT    = 9;
    final static int CTLCNST_DWELL_TRBRIDGE    = 10;
    final static int CTLCNST_DWELL_CYCAVE      = 11;
    final static int CTLCNST_DWELL_HLFHUT      = 12;
    final static int CTLCNST_DWELL_BOARPEN     = 13;
    final static int CTLCNST_DWELL_GFACTORY    = 14;
    final static int CTLCNST_DWELL_CLIFFNEST   = 15;
    final static int CTLCNST_DWELL_MAGETOWER   = 16;
    final static int CTLCNST_DWELL_CLDCASTLE   = 17;
    final static int CTLCNST_DWELL_CENTCAVE    = 18;
    final static int CTLCNST_DWELL_CRYPT       = 19;
    final static int CTLCNST_DWELL_GRIFTOWER   = 20;
    final static int CTLCNST_DWELL_LABYRINTH   = 21;
    final static int CTLCNST_DWELL_HYDRAPOND   = 22;
    final static int CTLCNST_DWELL_DRAGCAVE    = 23;
    final static int CTLCNST_DWELL_TREEHOUSE   = 24;
    final static int CTLCNST_DWELL_DWCOTTAGE   = 25;
    final static int CTLCNST_DWELL_HOMESTEAD   = 26;
    final static int CTLCNST_DWELL_STONEHENGE  = 27;
    final static int CTLCNST_DWELL_UNGLADE     = 28;
    final static int CTLCNST_DWELL_REDTOWER    = 29;
    final static int CTLCNST_DWELL_EXCAVATION  = 30;
    final static int CTLCNST_DWELL_GRAVEYARD   = 31;
    final static int CTLCNST_DWELL_PYRAMID     = 32;
    final static int CTLCNST_DWELL_MANSION     = 33;
    final static int CTLCNST_DWELL_MAUSOLEUM   = 34;
    final static int CTLCNST_DWELL_LABORATORY  = 35;

    // Common constructions //
    final static int CTLCNST_MAGEGUILDS    = 36;
    final static int CTLCNST_MAGEGUILD_L1  = 36;
    final static int CTLCNST_MAGEGUILD_L2  = 37;
    final static int CTLCNST_MAGEGUILD_L3  = 38;
    final static int CTLCNST_MAGEGUILD_L4  = 39;
    final static int CTLCNST_MAGEGUILD_L5  = 40;
    final static int CTLCNST_MAGICNODE     = 41;
    final static int CTLCNST_TAVERN        = 42;
    final static int CTLCNST_MARKETPLACE   = 43;

    // Common mines //
    final static int CTLCNST_MINES         = 44;
    final static int CTLCNST_TOWNHALL      = 44;
    final static int CTLCNST_CITYHALL      = 45;
    final static int CTLCNST_OREMINE       = 46;
    final static int CTLCNST_SAWMILL       = 47;
    final static int CTLCNST_ALCHLAB       = 48;
    final static int CTLCNST_GEMSMINE      = 49;
    final static int CTLCNST_CRYSTALMINE   = 50;
    final static int CTLCNST_SULFURMINE    = 51;

    final static int CTLCNST_MOAT      = 52;
    final static int CTLCNST_MTURRET   = 53;
    final static int CTLCNST_LTURRET   = 54;
    final static int CTLCNST_RTURRET   = 55;

    // Dwelling enchancers //
    final static int CTLCNST_SHOOTINGRANGE     = 56;
    final static int CTLCNST_MESSHALL          = 57;
    final static int CTLCNST_OAKWOOD           = 58;
    final static int CTLCNST_WATERFALL         = 59;
    final static int CTLCNST_MINERSGUILD       = 60;
    final static int CTLCNST_UNEARTHEDGRAVES   = 61;

    // Other special constructions //
    final static int CTLCNST_OBSERVPOST        = 62;
    final static int CTLCNST_FORTIFICATION     = 63;
    final static int CTLCNST_HALLOFVALHALLA    = 64;
    final static int CTLCNST_ADOBE             = 65;
    final static int CTLCNST_WALLOFKNOWLEDGE   = 66;
    final static int CTLCNST_LIBRARY           = 67;
    final static int CTLCNST_ALTAR             = 68;
    final static int CTLCNST_MANAVORTEX        = 69;
    final static int CTLCNST_TREASURY          = 70;
    final static int CTLCNST_MYSTICPOUND       = 71;
    final static int CTLCNST_NECRAMPLIFIER     = 72;
    final static int CTLCNST_COVEROFDARKNESS   = 73;

    final static int CTLCNST_COUNT             = 74;
    // Конец зданий замка //

    // Types of castle constructions
    final static int CCT_GENERIC           = 0;
    final static int CCT_DWELLING          = 1;  // fparam = Creature type
    final static int CCT_MAGEGUILD         = 2;  // fparam = guild level
    final static int CCT_TAVERN            = 3;  // -
    final static int CCT_MAGICNODE         = 4;  // -
    final static int CCT_MINE              = 5;  // fparam = mineral type, sparam = quantity
    final static int CCT_PERM_FSK_MOD      = 6;  // fparam = skill, sparam = modifier
    final static int CCT_DWELL_ENC         = 7;  // fparam = dwelling id, sparam = modifier
    final static int CCT_OBSERVPOST        = 8;  // -
    final static int CCT_LIBRARY           = 9;  // -
    final static int CCT_MANAVORTEX        = 10; // -
    final static int CCT_TREASURY          = 11; // -
    final static int CCT_MYSTICPOUND       = 12; // -
    final static int CCT_NECRAMPLIFIER     = 13; // -
    final static int CCT_COVEROFDARKNESS   = 14; // -
    final static int CCT_COUNT             = 15;

    // CTL_TYPE_MASK
    final static int CTLTM_CITADEL	= 1 << CTLT_CITADEL; // = 0;
    final static int CTLTM_STRONGHOLD	= 1 << CTLT_STRONGHOLD;
    final static int CTLTM_TOWER	= 1 << CTLT_TOWER;
    final static int CTLTM_DUNGEON	= 1 << CTLT_DUNGEON;
    final static int CTLTM_FORTRESS	= 1 << CTLT_FORTRESS;
    final static int CTLTM_NECROPOLIS	= 1 << CTLT_NECROPOLIS;
    final static int CTLTM_MIGHT	= CTLTM_CITADEL | CTLTM_STRONGHOLD; // = CTLTM_STRONGHOLD;
    final static int CTLTM_MAGIC	= CTLTM_TOWER | CTLTM_DUNGEON;
    final static int CTLTM_MISC	= CTLTM_FORTRESS | CTLTM_NECROPOLIS;
    final static int CTLTM_ALL		= CTLTM_MIGHT | CTLTM_MAGIC | CTLTM_MISC;

    // CTL_SIZE_MASK
    final static int CTLSM_S	= 1 << CTLS_SMALL;
    final static int CTLSM_M	= 1 << CTLS_MEDIUM;
    final static int CTLSM_L	= 1 << CTLS_LARGE;
    final static int CTLSM_ML	= CTLSM_M | CTLSM_L;
    final static int CTLSM_SML	= CTLSM_S | CTLSM_M | CTLSM_L;

    /**
     *
     */
    class CTCNSTCAP {
        long	siz;
        long	type;

        /**
         *
         * @param _type
         * @param _siz
         * @return
         */
        boolean Support(int _type, int _siz) {
                return (type & (1<<_type)) > 0 && (siz & (1<<_siz)) > 0;
        }

        /**
         * Конструктор
         * @param _siz
         * @param _type
         */
        public CTCNSTCAP(
                long _siz,
                long _type
        ) {
            this.siz = _siz;
            this.type = _type;
        }
    }

    /**
     *
     */
    class CTLCNST_DESC_STRUCT {
        int         type;
        iMineralSet price;
        int[]       depend = new int[2];
        CTCNSTCAP   caps;
        long        fparam;
        long        sparam;

        CTLCNST_DESC_STRUCT(
                int         _type,
                iMineralSet _price,
                int         _depend0,
                int         _depend1,
                CTCNSTCAP   _caps,
                long        _fparam,
                long        _sparam) {
            this.type       = _type;
            this.price      = _price;
            this.depend[0]  = _depend0;
            this.depend[1]  = _depend1;
            this.caps       = _caps;
            this.fparam     = _fparam;
            this.sparam     = _sparam;
        }
    }

    /**
     * Список рас героев
     */
    final static int[] CTL_HEROES = {
        HTM_KNIGHT,     // Рыцарь
        HTM_BARBARIAN,  // Варвар
        HTM_WIZARD,     // Волшебник
        HTM_WARLOCK,    // Чернокнижник
        HTM_SORCERESS,  // Колдун
        HTM_NECROMANCER // Некромант
    };

    /**
     * Направление взгляда героя, заспаунившегося в замке
     */
    final static int[] HERO_ORIENT = {
        1, // Left
        7  // Right
    };

    /**
     * Начальный / Минимальный золотой доход замка
     */
    final long CTL_INCOME = 500;

    // Pricelist for castle constructions
    final CTLCNST_DESC_STRUCT[] CTLCNSTS_DESC = {
        new CTLCNST_DESC_STRUCT(
            //
            // Peasants' hut
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(200, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN, CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_CITADEL),
            cm_creatures.CREAT_PEASANT, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Archers' tower
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_PEASNHUT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_CITADEL),
            cm_creatures.CREAT_ARCHER, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Guardhouse
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_ARCHTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_CITADEL),
            cm_creatures.CREAT_PIKEMAN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Barracks
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 10, 0, 0, 0, 0),
            CTLCNST_DWELL_GHOUSE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_CITADEL),
            cm_creatures.CREAT_SWORDSMAN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Jousting arena
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 20, 0, 0, 0, 0),
            CTLCNST_DWELL_BARRACKS,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_CITADEL),
            cm_creatures.CREAT_CAVALRY, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Cathedral
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(8000, 0, 20, 0, 0, 20, 0),
            CTLCNST_DWELL_BARRACKS,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_CITADEL),
            cm_creatures.CREAT_PALADIN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Goblin barracks
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(300, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_GOBLIN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Orc tower
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(800, 0, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_GBARRACKS,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_ORC, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Wolf pen
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_ORCTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_WOLF, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Ogre fort
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 10, 0, 0, 0, 0),
            CTLCNST_DWELL_ORCTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_OGRE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Troll bridge
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 20, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_OGREFORT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_TROLL, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Cyclops cave
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(8000, 20, 0, 0, 0, 20, 0),
            CTLCNST_DWELL_WOLFDEN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_STRONGHOLD),
            cm_creatures.CREAT_CYCLOP, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Halflings' hit
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(400, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_TOWER),
            cm_creatures.CREAT_HALFLING, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Boars' pen
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(800, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_HLFHUT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_TOWER),
            cm_creatures.CREAT_BOAR, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Golem factory
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 5, 5, 0, 0, 0),
            CTLCNST_DWELL_HLFHUT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_TOWER),
            cm_creatures.CREAT_GOLEM, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Cliff nest
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_BOARPEN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_TOWER),
            cm_creatures.CREAT_ROC, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage tower
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3500, 5, 5, 5, 5, 5, 5),
            CTLCNST_DWELL_GFACTORY,CTLCNST_MAGEGUILD_L1,
            new CTCNSTCAP(CTLSM_SML, CTLTM_TOWER),
            cm_creatures.CREAT_MAGE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Cloud castle
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(25000, 5, 5, 0, 30, 0, 0),
            CTLCNST_DWELL_CLIFFNEST,CTLCNST_DWELL_MAGETOWER,
            new CTCNSTCAP(CTLSM_ML, CTLTM_TOWER),
            cm_creatures.CREAT_TITAN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Centaurs' cave
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_DUNGEON),
            cm_creatures.CREAT_CENTAUR, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Crypt
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 10, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_CENTCAVE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_DUNGEON),
            cm_creatures.CREAT_GARGOYLE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Griffin tower
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_CRYPT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_DUNGEON),
            cm_creatures.CREAT_GRIFFIN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Labyrinth
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 0, 0, 10, 0, 0),
            CTLCNST_DWELL_GRIFTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_DUNGEON),
            cm_creatures.CREAT_MINOTAUR, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Hydra pond
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 0, 0, 0, 0, 0, 10),
            CTLCNST_DWELL_LABYRINTH,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_DUNGEON),
            cm_creatures.CREAT_HYDRA, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Dragon cave
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(30000, 30, 0, 0, 0, 0, 30),
            CTLCNST_DWELL_HYDRAPOND,CTLCNST_MAGEGUILD_L2,
            new CTCNSTCAP(CTLSM_ML, CTLTM_DUNGEON),
            cm_creatures.CREAT_BLACK_DRAGON, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Treehouse
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 5, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_FORTRESS),
            cm_creatures.CREAT_SPRITE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Dwarf cottage
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_TREEHOUSE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_FORTRESS),
            cm_creatures.CREAT_DWARF, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Homestead
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 0, 10, 0, 0, 0, 0),
            CTLCNST_DWELL_DWCOTTAGE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_FORTRESS),
            cm_creatures.CREAT_ELF, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Stonehenge
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 10, 0, 5, 0, 0, 0),
            CTLCNST_DWELL_HOMESTEAD,CTLCNST_MAGEGUILD_L1,
            new CTCNSTCAP(CTLSM_SML, CTLTM_FORTRESS),
            cm_creatures.CREAT_DRUID, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Unicorn glade
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 10, 0, 10, 0, 0),
            CTLCNST_DWELL_STONEHENGE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_FORTRESS),
            cm_creatures.CREAT_UNICORN, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Red tower
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(10000, 30, 0, 20, 0, 0, 0),
            CTLCNST_DWELL_UNGLADE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_FORTRESS),
            cm_creatures.CREAT_PHOENIX, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Excavation
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(400, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_SKELETON, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Graveyard
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_EXCAVATION,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_ZOMBIE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Pyramid
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_GRAVEYARD,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_MUMMY, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mansion
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 10, 0, 5, 0, 0),
            CTLCNST_DWELL_PYRAMID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_VAMPIRE, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mausoleum
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 0, 10, 0, 0, 0, 5),
            CTLCNST_DWELL_MANSION,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_LICH, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Laboratory
            //
            CCT_DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(10000, 10, 10, 20, 0, 0, 0),
            CTLCNST_DWELL_MAUSOLEUM,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_NECROPOLIS),
            cm_creatures.CREAT_BONE_DRAGON, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage guild 1
            //
            CCT_MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 5, 5, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_ALL),
            1, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage guild 2
            //
            CCT_MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 4, 4, 4, 4),
            CTLCNST_MAGEGUILD_L1,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_ALL),
            2, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage guild 3
            //
            CCT_MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 6, 6, 6, 6),
            CTLCNST_MAGEGUILD_L2, CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_ALL),
            3, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage guild 4
            //
            CCT_MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 8, 8, 8, 8),
            CTLCNST_MAGEGUILD_L3, CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_MAGIC | CTLTM_MISC),
            4, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mage guild 5
            //
            CCT_MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 10, 10, 10, 10),
            CTLCNST_MAGEGUILD_L4, CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_L, CTLTM_MAGIC | CTLTM_MISC),
            5, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Magic node
            //
            CCT_MAGICNODE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 10, 0, 2, 2, 2, 2),
            CTLCNST_MAGEGUILD_L2,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_L, CTLTM_MAGIC),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Tavern
            //
            CCT_TAVERN,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_ALL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Marketplace
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 5, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_ALL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Town hall
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_M, CTLTM_ALL),
            MINERAL_GOLD, 500
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // City hall
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 0, 0, 0, 0, 0, 0),
            CTLCNST_MAGEGUILD_L1,CTLCNST_MARKETPLACE,
            new CTCNSTCAP(CTLSM_L, CTLTM_ALL),
            MINERAL_GOLD, 1500
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Ore mine
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 5, 0, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_MIGHT | CTLTM_MISC),
            MINERAL_ORE, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Sawmill
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 5, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_SML, CTLTM_MIGHT | CTLTM_MISC),
            MINERAL_WOOD, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Alchemists' laboratory
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_MISC),
            MINERAL_MERCURY, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Gems mine
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_TOWER),
            MINERAL_GEMS, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Crystal mine
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_MIGHT),
            MINERAL_CRYSTAL, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Sulfur mine
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST_MARKETPLACE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_DUNGEON),
            MINERAL_SULFUR, 1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Moat
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_ALL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mine turret
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 5, 0, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_ALL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Left turret
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST_MTURRET,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_MIGHT),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Right turret
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST_MTURRET,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_MIGHT),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Shooting range
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_ARCHTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_CITADEL),
            CTLCNST_DWELL_ARCHTOWER, cm_creatures.CREAT_DESC[cm_creatures.CREAT_ARCHER].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mess hall
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_ORCTOWER,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_STRONGHOLD),
            CTLCNST_DWELL_ORCTOWER, cm_creatures.CREAT_DESC[cm_creatures.CREAT_ORC].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Oak wood
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_BOARPEN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_TOWER),
            CTLCNST_DWELL_BOARPEN, cm_creatures.CREAT_DESC[cm_creatures.CREAT_BOAR].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Waterfall
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_CENTCAVE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_DUNGEON),
            CTLCNST_DWELL_CENTCAVE, cm_creatures.CREAT_DESC[cm_creatures.CREAT_CENTAUR].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Miners guild
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 5, 5, 0, 0, 0, 0),
            CTLCNST_DWELL_DWCOTTAGE,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_FORTRESS),
            CTLCNST_DWELL_DWCOTTAGE, cm_creatures.CREAT_DESC[cm_creatures.CREAT_DWARF].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Unearthed graves
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_EXCAVATION,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_NECROPOLIS),
            CTLCNST_DWELL_EXCAVATION, cm_creatures.CREAT_DESC[cm_creatures.CREAT_SKELETON].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Observation post
            //
            CCT_OBSERVPOST,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 5, 5, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_CITADEL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Fortification
            //
            CCT_GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 10, 0, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_CITADEL),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Hall of Valhalla
            //
            CCT_PERM_FSK_MOD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_STRONGHOLD),
            FSK_ATTACK, +1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Adobe
            //
            CCT_DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 10, 0, 0, 0, 0, 0),
            CTLCNST_DWELL_OGREFORT,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_STRONGHOLD),
            CTLCNST_DWELL_OGREFORT, cm_creatures.CREAT_DESC[cm_creatures.CREAT_OGRE].growth
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Wall of knowledge
            //
            CCT_PERM_FSK_MOD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 5, 0, 0, 0, 0, 0),
            CTLCNST_MAGEGUILD_L1,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_TOWER),
            FSK_KNOWLEDGE, +1
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Library
            //
            CCT_LIBRARY,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 5, 5, 5, 5),
            CTLCNST_MAGEGUILD_L2,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_TOWER),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Altar
            //
            CCT_MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 0, 0, 0, 0, 0),
            CTLCNST_TAVERN,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_DUNGEON),
            MINERAL_GOLD, 1000
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mana vortex
            //
            CCT_MANAVORTEX,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 1, 1, 1, 1),
            CTLCNST_MAGEGUILD_L1,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_DUNGEON),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Treasury
            //
            CCT_TREASURY,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST_MINERSGUILD,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_FORTRESS),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Mystic pound
            //
            CCT_MYSTICPOUND,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 1, 1, 1, 1, 1, 1),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_FORTRESS),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Necromancy amplifier
            //
            CCT_NECRAMPLIFIER,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_NECROPOLIS),
            0, 0
        ),
        new CTLCNST_DESC_STRUCT(
            //
            // Cover of darkness
            //
            CCT_COVEROFDARKNESS,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST_INVALID,CTLCNST_INVALID,
            new CTCNSTCAP(CTLSM_ML, CTLTM_NECROPOLIS),
            0, 0
)
    };

    /**
     * Описание для каждого замка заклинаний,
     * открываемых по каждому сдедующему уровню гильдии
     */
    iSpellFilter[][] CTL_MAGE_GUILD_FILTER = {
        {
            // Citadel
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_GOOD)
        },{
            // Stronghold
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_EVIL)
        },{
            // Tower
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FOURTH, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIFTH, MST_MASK_GOOD)
        },{
            // Dungeon
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FOURTH, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIFTH, MST_MASK_EVIL)
        },{
            // Fortress
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FOURTH, MST_MASK_GOOD),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIFTH, MST_MASK_GOOD)
        },{
            // Necropolis
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIRST, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_SECOND, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_THIRD, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FOURTH, MST_MASK_EVIL),
            new iSpellFilter(SPT_MASK_ALL, SPL_MASK_FIFTH, MST_MASK_EVIL)
        }
    };

    /**
     * Количество заклинаний каждой гильдии в порядке открытия
     */
    final long[][] CTL_MAGE_GUILD_SPELLS = {
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