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
 * Predefined construction types
 */
public class cm_constructions {

    // Visitables "enum VIS_CNST_TYPE"
    final static int VCNST_BASIC        = 0;  // Generic Visitable construction
    final static int VCNST_STABLE       = 1;  // Adds 'n' action points (end of week to each visitor)
    final static int VCNST_GAZEBO       = 2;  // Adds 'n' exp points (once to each visitor)
    final static int VCNST_MANASOURCE   = 3;  // Adds 'n' mana points (end of week to each visitor)
    final static int VCNST_MLMODIFIER   = 4;  // Modifies morale and luck
    final static int VCNST_PSMODIFIER   = 5;  // Modifies set of primary skills by mask
    final static int VCNST_OBELISK      = 6;  // Opens one element of puzzle map
    final static int VCNST_SIGN         = 7;  // Shows message
    final static int VCNST_DWELLING     = 8;  // Dwelling
    final static int VCNST_WITCHHUT     = 9;  // Witch Hut (learn random or specified secondary skill)
    final static int VCNST_SHRINE       = 10; // Magic shrine (learn random spell of specified (1-3) level)
    final static int VCNST_TREASURY     = 11; // Resource treasury
    final static int VCNST_TELEPORT     = 12; // Teleports hero
    final static int VCNST_KEYMASTER    = 13; // Gives specified key
    final static int VCNST_KNWLTREE     = 14; // Tree of Knowledge
    final static int VCNST_WINDMILL     = 15; // Windmill (gives random ammount of random mineral one time per week)
    final static int VCNST_WEEKLYMIN    = 16; // Gives fixed ammount of specified mineral(s) one time per week
    final static int VCNST_COUNT        = 17;

////////////////////////////////////////////////////////////////////////////////
    // Какие-то левые комменты из исхов:
    // Windmill (2-5 of random resource (except gold) one time per week)
    // Water wheel (+1000gp one time per week)
    // University (lern one or more from 4 secondray skills for 2.000)
    // Dragon utopia
    // Altar of sacrifice (creatures and artifacts to experience)
    // Market
    // Black market
    // Treasures ('n' units of 'x' creature type guards 'm' mineral set)
    // Den of thieves (shows detailed world information)
    // Shrine of magic (learns random 1-3 level spell)
    // Keymaster's Tent
    // Seer's hut
    // Crypt/Graveyard
    // Corps/Skeleton
////////////////////////////////////////////////////////////////////////////////

    // Ownerables "enum OWN_CNST_TYPE"
    final static int OCNST_BASIC        = 0; // Generic Ownerable construction
    final static int OCNST_FURTSKILL    = 1; // Modifies one or several owner's further skills
    final static int OCNST_COUNT        = 2;

}
