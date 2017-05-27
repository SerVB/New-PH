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

import Common.CTCNSTCAP;
import Common.cm_creatures;
import Common.iMineralSet;

/**
 * Castle constructions.
 */
public class CTLCNSTS {

    /**
     * Castle constructions description.
     */
    public final DESC_STRUCT[] DESC = {
        new DESC_STRUCT(
            //
            // Peasants' hut
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(200, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.CITADEL),
            cm_creatures.CREAT_PEASANT, 0
        ),
        new DESC_STRUCT(
            //
            // Archers' tower
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_PEASNHUT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.CITADEL),
            cm_creatures.CREAT_ARCHER, 0
        ),
        new DESC_STRUCT(
            //
            // Guardhouse
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_ARCHTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.CITADEL),
            cm_creatures.CREAT_PIKEMAN, 0
        ),
        new DESC_STRUCT(
            //
            // Barracks
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 10, 0, 0, 0, 0),
            CTLCNST.DWELL_GHOUSE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.CITADEL),
            cm_creatures.CREAT_SWORDSMAN, 0
        ),
        new DESC_STRUCT(
            //
            // Jousting arena
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 20, 0, 0, 0, 0),
            CTLCNST.DWELL_BARRACKS, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.CITADEL),
            cm_creatures.CREAT_CAVALRY, 0
        ),
        new DESC_STRUCT(
            //
            // Cathedral
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(8000, 0, 20, 0, 0, 20, 0),
            CTLCNST.DWELL_BARRACKS, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.CITADEL),
            cm_creatures.CREAT_PALADIN, 0
        ),
        new DESC_STRUCT(
            //
            // Goblin barracks
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(300, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_GOBLIN, 0
        ),
        new DESC_STRUCT(
            //
            // Orc tower
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(800, 0, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_GBARRACKS, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_ORC, 0
        ),
        new DESC_STRUCT(
            //
            // Wolf pen
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_ORCTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_WOLF, 0
        ),
        new DESC_STRUCT(
            //
            // Ogre fort
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 10, 0, 0, 0, 0),
            CTLCNST.DWELL_ORCTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_OGRE, 0
        ),
        new DESC_STRUCT(
            //
            // Troll bridge
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 20, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_OGREFORT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_TROLL, 0
        ),
        new DESC_STRUCT(
            //
            // Cyclops cave
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(8000, 20, 0, 0, 0, 20, 0),
            CTLCNST.DWELL_WOLFDEN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.STRONGHOLD),
            cm_creatures.CREAT_CYCLOP, 0
        ),
        new DESC_STRUCT(
            //
            // Halflings' hit
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(400, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.TOWER),
            cm_creatures.CREAT_HALFLING, 0
        ),
        new DESC_STRUCT(
            //
            // Boars' pen
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(800, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_HLFHUT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.TOWER),
            cm_creatures.CREAT_BOAR, 0
        ),
        new DESC_STRUCT(
            //
            // Golem factory
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 5, 5, 0, 0, 0),
            CTLCNST.DWELL_HLFHUT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.TOWER),
            cm_creatures.CREAT_GOLEM, 0
        ),
        new DESC_STRUCT(
            //
            // Cliff nest
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_BOARPEN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.TOWER),
            cm_creatures.CREAT_ROC, 0
        ),
        new DESC_STRUCT(
            //
            // Mage tower
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3500, 5, 5, 5, 5, 5, 5),
            CTLCNST.DWELL_GFACTORY, CTLCNST.MAGEGUILD_L1,
            new CTCNSTCAP(CTLSM.SML, CTLTM.TOWER),
            cm_creatures.CREAT_MAGE, 0
        ),
        new DESC_STRUCT(
            //
            // Cloud castle
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(25000, 5, 5, 0, 30, 0, 0),
            CTLCNST.DWELL_CLIFFNEST, CTLCNST.DWELL_MAGETOWER,
            new CTCNSTCAP(CTLSM.ML, CTLTM.TOWER),
            cm_creatures.CREAT_TITAN, 0
        ),
        new DESC_STRUCT(
            //
            // Centaurs' cave
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.DUNGEON),
            cm_creatures.CREAT_CENTAUR, 0
        ),
        new DESC_STRUCT(
            //
            // Crypt
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 10, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_CENTCAVE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.DUNGEON),
            cm_creatures.CREAT_GARGOYLE, 0
        ),
        new DESC_STRUCT(
            //
            // Griffin tower
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_CRYPT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.DUNGEON),
            cm_creatures.CREAT_GRIFFIN, 0
        ),
        new DESC_STRUCT(
            //
            // Labyrinth
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 0, 0, 10, 0, 0),
            CTLCNST.DWELL_GRIFTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.DUNGEON),
            cm_creatures.CREAT_MINOTAUR, 0
        ),
        new DESC_STRUCT(
            //
            // Hydra pond
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 0, 0, 0, 0, 0, 10),
            CTLCNST.DWELL_LABYRINTH, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.DUNGEON),
            cm_creatures.CREAT_HYDRA, 0
        ),
        new DESC_STRUCT(
            //
            // Dragon cave
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(30000, 30, 0, 0, 0, 0, 30),
            CTLCNST.DWELL_HYDRAPOND, CTLCNST.MAGEGUILD_L2,
            new CTCNSTCAP(CTLSM.ML, CTLTM.DUNGEON),
            cm_creatures.CREAT_BLACK_DRAGON, 0
        ),
        new DESC_STRUCT(
            //
            // Treehouse
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 5, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.FORTRESS),
            cm_creatures.CREAT_SPRITE, 0
        ),
        new DESC_STRUCT(
            //
            // Dwarf cottage
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_TREEHOUSE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.FORTRESS),
            cm_creatures.CREAT_DWARF, 0
        ),
        new DESC_STRUCT(
            //
            // Homestead
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 0, 10, 0, 0, 0, 0),
            CTLCNST.DWELL_DWCOTTAGE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.FORTRESS),
            cm_creatures.CREAT_ELF, 0
        ),
        new DESC_STRUCT(
            //
            // Stonehenge
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 10, 0, 5, 0, 0, 0),
            CTLCNST.DWELL_HOMESTEAD, CTLCNST.MAGEGUILD_L1,
            new CTCNSTCAP(CTLSM.SML, CTLTM.FORTRESS),
            cm_creatures.CREAT_DRUID, 0
        ),
        new DESC_STRUCT(
            //
            // Unicorn glade
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 10, 0, 10, 0, 0),
            CTLCNST.DWELL_STONEHENGE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.FORTRESS),
            cm_creatures.CREAT_UNICORN, 0
        ),
        new DESC_STRUCT(
            //
            // Red tower
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(10000, 30, 0, 20, 0, 0, 0),
            CTLCNST.DWELL_UNGLADE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.FORTRESS),
            cm_creatures.CREAT_PHOENIX, 0
        ),
        new DESC_STRUCT(
            //
            // Excavation
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(400, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_SKELETON, 0
        ),
        new DESC_STRUCT(
            //
            // Graveyard
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_EXCAVATION, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_ZOMBIE, 0
        ),
        new DESC_STRUCT(
            //
            // Pyramid
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 10, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_GRAVEYARD, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_MUMMY, 0
        ),
        new DESC_STRUCT(
            //
            // Mansion
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 10, 0, 5, 0, 0),
            CTLCNST.DWELL_PYRAMID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_VAMPIRE, 0
        ),
        new DESC_STRUCT(
            //
            // Mausoleum
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(4000, 0, 10, 0, 0, 0, 5),
            CTLCNST.DWELL_MANSION, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_LICH, 0
        ),
        new DESC_STRUCT(
            //
            // Laboratory
            //
            CCT.DWELLING,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(10000, 10, 10, 20, 0, 0, 0),
            CTLCNST.DWELL_MAUSOLEUM, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.NECROPOLIS),
            cm_creatures.CREAT_BONE_DRAGON, 0
        ),
        new DESC_STRUCT(
            //
            // Mage guild 1
            //
            CCT.MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 5, 5, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.ALL),
            1, 0
        ),
        new DESC_STRUCT(
            //
            // Mage guild 2
            //
            CCT.MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 4, 4, 4, 4),
            CTLCNST.MAGEGUILD_L1, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.ALL),
            2, 0
        ),
        new DESC_STRUCT(
            //
            // Mage guild 3
            //
            CCT.MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 6, 6, 6, 6),
            CTLCNST.MAGEGUILD_L2, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.ALL),
            3, 0
        ),
        new DESC_STRUCT(
            //
            // Mage guild 4
            //
            CCT.MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 8, 8, 8, 8),
            CTLCNST.MAGEGUILD_L3, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.MAGIC | CTLTM.MISC),
            4, 0
        ),
        new DESC_STRUCT(
            //
            // Mage guild 5
            //
            CCT.MAGEGUILD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 10, 10, 10, 10),
            CTLCNST.MAGEGUILD_L4, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.L, CTLTM.MAGIC | CTLTM.MISC),
            5, 0
        ),
        new DESC_STRUCT(
            //
            // Magic node
            //
            CCT.MAGICNODE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 10, 0, 2, 2, 2, 2),
            CTLCNST.MAGEGUILD_L2, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.L, CTLTM.MAGIC),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Tavern
            //
            CCT.TAVERN,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.ALL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Marketplace
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 0, 5, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.ALL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Town hall
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.M, CTLTM.ALL),
            MINERAL_GOLD, 500
        ),
        new DESC_STRUCT(
            //
            // City hall
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 0, 0, 0, 0, 0, 0),
            CTLCNST.MAGEGUILD_L1, CTLCNST.MARKETPLACE,
            new CTCNSTCAP(CTLSM.L, CTLTM.ALL),
            MINERAL_GOLD, 1500
        ),
        new DESC_STRUCT(
            //
            // Ore mine
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 5, 0, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.MIGHT | CTLTM.MISC),
            MINERAL_ORE, 1
        ),
        new DESC_STRUCT(
            //
            // Sawmill
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(3000, 0, 5, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.SML, CTLTM.MIGHT | CTLTM.MISC),
            MINERAL_WOOD, 1
        ),
        new DESC_STRUCT(
            //
            // Alchemists' laboratory
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.MISC),
            MINERAL_MERCURY, 1
        ),
        new DESC_STRUCT(
            //
            // Gems mine
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.TOWER),
            MINERAL_GEMS, 1
        ),
        new DESC_STRUCT(
            //
            // Crystal mine
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.MIGHT),
            MINERAL_CRYSTAL, 1
        ),
        new DESC_STRUCT(
            //
            // Sulfur mine
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST.MARKETPLACE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.DUNGEON),
            MINERAL_SULFUR, 1
        ),
        new DESC_STRUCT(
            //
            // Moat
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.ALL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Mine turret
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 5, 0, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.ALL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Left turret
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST.MTURRET, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.MIGHT),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Right turret
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST.MTURRET, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.MIGHT),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Shooting range
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 0, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_ARCHTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.CITADEL),
            CTLCNST.DWELL_ARCHTOWER, cm_creatures.CREAT_DESC[cm_creatures.CREAT_ARCHER].growth
        ),
        new DESC_STRUCT(
            //
            // Mess hall
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_ORCTOWER, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.STRONGHOLD),
            CTLCNST.DWELL_ORCTOWER, cm_creatures.CREAT_DESC[cm_creatures.CREAT_ORC].growth
        ),
        new DESC_STRUCT(
            //
            // Oak wood
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_BOARPEN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.TOWER),
            CTLCNST.DWELL_BOARPEN, cm_creatures.CREAT_DESC[cm_creatures.CREAT_BOAR].growth
        ),
        new DESC_STRUCT(
            //
            // Waterfall
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_CENTCAVE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.DUNGEON),
            CTLCNST.DWELL_CENTCAVE, cm_creatures.CREAT_DESC[cm_creatures.CREAT_CENTAUR].growth
        ),
        new DESC_STRUCT(
            //
            // Miners guild
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(500, 5, 5, 0, 0, 0, 0),
            CTLCNST.DWELL_DWCOTTAGE, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.FORTRESS),
            CTLCNST.DWELL_DWCOTTAGE, cm_creatures.CREAT_DESC[cm_creatures.CREAT_DWARF].growth
        ),
        new DESC_STRUCT(
            //
            // Unearthed graves
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 0, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_EXCAVATION, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.NECROPOLIS),
            CTLCNST.DWELL_EXCAVATION, cm_creatures.CREAT_DESC[cm_creatures.CREAT_SKELETON].growth
        ),
        new DESC_STRUCT(
            //
            // Observation post
            //
            CCT.OBSERVPOST,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 5, 5, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.CITADEL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Fortification
            //
            CCT.GENERIC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 10, 0, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.CITADEL),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Hall of Valhalla
            //
            CCT.PERM_FSK_MOD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1000, 5, 5, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.STRONGHOLD),
            FSK_ATTACK, +1
        ),
        new DESC_STRUCT(
            //
            // Adobe
            //
            CCT.DWELL_ENC,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 10, 0, 0, 0, 0, 0),
            CTLCNST.DWELL_OGREFORT, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.STRONGHOLD),
            CTLCNST.DWELL_OGREFORT, cm_creatures.CREAT_DESC[cm_creatures.CREAT_OGRE].growth
        ),
        new DESC_STRUCT(
            //
            // Wall of knowledge
            //
            CCT.PERM_FSK_MOD,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2000, 5, 0, 0, 0, 0, 0),
            CTLCNST.MAGEGUILD_L1, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.TOWER),
            FSK_KNOWLEDGE, +1
        ),
        new DESC_STRUCT(
            //
            // Library
            //
            CCT.LIBRARY,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 5, 5, 5, 5),
            CTLCNST.MAGEGUILD_L2, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.TOWER),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Altar
            //
            CCT.MINE,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 0, 0, 0, 0, 0),
            CTLCNST.TAVERN, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.DUNGEON),
            MINERAL_GOLD, 1000
        ),
        new DESC_STRUCT(
            //
            // Mana vortex
            //
            CCT.MANAVORTEX,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 1, 1, 1, 1),
            CTLCNST.MAGEGUILD_L1, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.DUNGEON),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Treasury
            //
            CCT.TREASURY,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(5000, 5, 5, 0, 0, 0, 0),
            CTLCNST.MINERSGUILD, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.FORTRESS),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Mystic pound
            //
            CCT.MYSTICPOUND,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 1, 1, 1, 1, 1, 1),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.FORTRESS),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Necromancy amplifier
            //
            CCT.NECRAMPLIFIER,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(2500, 0, 0, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.NECROPOLIS),
            0, 0
        ),
        new DESC_STRUCT(
            //
            // Cover of darkness
            //
            CCT.COVEROFDARKNESS,
            //    Gold, Ore, Wood, Merc, Gems, Cryst, Sulfur
            new iMineralSet(1500, 5, 0, 0, 0, 0, 0),
            CTLCNST.INVALID, CTLCNST.INVALID,
            new CTCNSTCAP(CTLSM.ML, CTLTM.NECROPOLIS),
            0, 0
        )
    };

    /**
     * Castle construction descriptor structure.
     */
    class DESC_STRUCT {

        /**
         * Тип.
         */
        public int type;

        /**
         * Цена.
         */
        public iMineralSet price;

        /**
         *
         */
        public int[] depend = new int[2];

        /**
         *
         */
        public CTCNSTCAP caps;

        /**
         * First parameter.
         */
        public long fparam;

        /**
         * Second parameter.
         */
        public long sparam;

        /**
         * Конструктор.
         * @param _type Тип.
         * @param _price Цена.
         * @param _depend0
         * @param _depend1
         * @param _caps
         * @param _fparam First parameter.
         * @param _sparam Second parameter.
         */
        public DESC_STRUCT(
                int         _type,
                iMineralSet _price,
                int         _depend0,
                int         _depend1,
                CTCNSTCAP   _caps,
                long        _fparam,
                long        _sparam
        ) {
            this.type       = _type;
            this.price      = _price;
            this.depend[0]  = _depend0;
            this.depend[1]  = _depend1;
            this.caps       = _caps;
            this.fparam     = _fparam;
            this.sparam     = _sparam;
        }
    }
}
