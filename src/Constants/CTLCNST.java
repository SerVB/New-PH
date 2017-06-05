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
 * Строение замка.
 * Castle construction.
 */
public class CTLCNST {

    public final static int INVALID = -1;

    // Dwellings = Жилища //
    public final static int DWELLINGS = 0;

    public final static int DWELL_PEASNHUT    = 0;
    public final static int DWELL_ARCHTOWER   = 1;
    public final static int DWELL_GHOUSE      = 2;
    public final static int DWELL_BARRACKS    = 3;
    public final static int DWELL_JARENA      = 4;
    public final static int DWELL_CATHEDRAL   = 5;
    public final static int DWELL_GBARRACKS   = 6;
    public final static int DWELL_ORCTOWER    = 7;
    public final static int DWELL_WOLFDEN     = 8;
    public final static int DWELL_OGREFORT    = 9;
    public final static int DWELL_TRBRIDGE    = 10;
    public final static int DWELL_CYCAVE      = 11;
    public final static int DWELL_HLFHUT      = 12;
    public final static int DWELL_BOARPEN     = 13;
    public final static int DWELL_GFACTORY    = 14;
    public final static int DWELL_CLIFFNEST   = 15;
    public final static int DWELL_MAGETOWER   = 16;
    public final static int DWELL_CLDCASTLE   = 17;
    public final static int DWELL_CENTCAVE    = 18;
    public final static int DWELL_CRYPT       = 19;
    public final static int DWELL_GRIFTOWER   = 20;
    public final static int DWELL_LABYRINTH   = 21;
    public final static int DWELL_HYDRAPOND   = 22;
    public final static int DWELL_DRAGCAVE    = 23;
    public final static int DWELL_TREEHOUSE   = 24;
    public final static int DWELL_DWCOTTAGE   = 25;
    public final static int DWELL_HOMESTEAD   = 26;
    public final static int DWELL_STONEHENGE  = 27;
    public final static int DWELL_UNGLADE     = 28;
    public final static int DWELL_REDTOWER    = 29;
    public final static int DWELL_EXCAVATION  = 30;
    public final static int DWELL_GRAVEYARD   = 31;
    public final static int DWELL_PYRAMID     = 32;
    public final static int DWELL_MANSION     = 33;
    public final static int DWELL_MAUSOLEUM   = 34;
    public final static int DWELL_LABORATORY  = 35;

    // Common constructions //
    public final static int MAGEGUILDS    = 36;

    public final static int MAGEGUILD_L1  = 36;
    public final static int MAGEGUILD_L2  = 37;
    public final static int MAGEGUILD_L3  = 38;
    public final static int MAGEGUILD_L4  = 39;
    public final static int MAGEGUILD_L5  = 40;

    public final static int MAGICNODE     = 41;
    public final static int TAVERN        = 42;
    public final static int MARKETPLACE   = 43;

    // Common mines //
    public final static int MINES         = 44;

    public final static int TOWNHALL      = 44;
    public final static int CITYHALL      = 45;
    public final static int OREMINE       = 46;
    public final static int SAWMILL       = 47;
    public final static int ALCHLAB       = 48;
    public final static int GEMSMINE      = 49;
    public final static int CRYSTALMINE   = 50;
    public final static int SULFURMINE    = 51;

    // Defences //
    public final static int MOAT      = 52;
    public final static int MTURRET   = 53;
    public final static int LTURRET   = 54;
    public final static int RTURRET   = 55;

    // Dwelling enchancers //
    public final static int SHOOTINGRANGE     = 56;
    public final static int MESSHALL          = 57;
    public final static int OAKWOOD           = 58;
    public final static int WATERFALL         = 59;
    public final static int MINERSGUILD       = 60;
    public final static int UNEARTHEDGRAVES   = 61;

    // Other special constructions //
    public final static int OBSERVPOST        = 62;
    public final static int FORTIFICATION     = 63;
    public final static int HALLOFVALHALLA    = 64;
    public final static int ADOBE             = 65;
    public final static int WALLOFKNOWLEDGE   = 66;
    public final static int LIBRARY           = 67;
    public final static int ALTAR             = 68;
    public final static int MANAVORTEX        = 69;
    public final static int TREASURY          = 70;
    public final static int MYSTICPOUND       = 71;
    public final static int NECRAMPLIFIER     = 72;
    public final static int COVEROFDARKNESS   = 73;

    // Count //
    public final static int COUNT             = 74;

    public final static int[][] DEFAULT_CONSTRUCTIONS = {
        { TAVERN, DWELL_PEASNHUT },
        { TAVERN, DWELL_GBARRACKS },
        { TAVERN, DWELL_HLFHUT },
        { TAVERN, DWELL_CENTCAVE },
        { TAVERN, DWELL_TREEHOUSE },
        { TAVERN, DWELL_EXCAVATION }
    };

}
