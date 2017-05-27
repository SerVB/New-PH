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
 * Visitable construction type.
 */
public class VCNST {

    /**
     * Generic Visitable construction.
     */
    public final static int BASIC       = 0;

    /**
     * Adds 'n' action points (end of week to each visitor).
     */
    public final static int STABLE      = 1;

    /**
     * Adds 'n' exp points (once to each visitor).
     */
    public final static int GAZEBO      = 2;

    /**
     * Adds 'n' mana points (end of week to each visitor).
     */
    public final static int MANASOURCE  = 3;

    /**
     * Modifies morale and luck.
     */
    public final static int MLMODIFIER  = 4;

    /**
     * Modifies set of primary skills by mask.
     */
    public final static int PSMODIFIER  = 5;

    /**
     * Opens one element of puzzle map.
     */
    public final static int OBELISK     = 6;

    /**
     * Shows message.
     */
    public final static int SIGN        = 7;

    /**
     * Dwelling.
     */
    public final static int DWELLING    = 8;

    /**
     * Witch Hut (learn random or specified secondary skill).
     */
    public final static int WITCHHUT    = 9;

    /**
     * Magic shrine (learn random spell of specified (1-3) level).
     */
    public final static int SHRINE      = 10;

    /**
     * Resource treasury.
     */
    public final static int TREASURY    = 11;

    /**
     * Teleports hero.
     */
    public final static int TELEPORT    = 12;

    /**
     * Gives specified key.
     */
    public final static int KEYMASTER   = 13;

    /**
     * Tree of Knowledge.
     */
    public final static int KNWLTREE    = 14;

    /**
     * Windmill (gives random ammount of random mineral one time per week).
     */
    public final static int WINDMILL    = 15;

    /**
     * Gives fixed ammount of specified mineral(s) one time per week.
     */
    public final static int WEEKLYMIN   = 16;

    /**
     * Visitables count.
     */
    public final static int COUNT       = 17;

}

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