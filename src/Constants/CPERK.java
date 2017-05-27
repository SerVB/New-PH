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

package Constants;

/**
 * Creature perk.
 */
public class CPERK {

    /**
     * Creature perks count.
     */
    public final static int COUNT           = 21;

    /**
     * No perks.
     */
    public final static int NONE            = 0;

    /**
     * Shots twice (rangers, grand elves, etc).
     */
    public final static int DOUBLESHOT      = 0x00000001;

    /**
     * Attack the target twice (paladins, wolves, etc).
     */
    public final static int DOUBLEATTACK    = 0x00000002;

    /**
     * Scores full damage even at melee.
     */
    public final static int NOMELEEPENALTY  = 0x00000004;

    /**
     * Non retaliated attack (vampires, rogues, sprites, hydras, etc).
     */
    public final static int NONRETALATTACK  = 0x00000008;

    /**
     * Retaliates against every attack (griffins).
     */
    public final static int RETALTOALL      = 0x00000010;

    /**
     * Two-hex attack (dragons).
     */
    public final static int TWOHEXATTACK    = 0x00000020;

    /**
     * Attacks all adjacent enemies (hydras).
     */
    public final static int ADJACENTATTACK  = 0x00000040;

    /**
     * Range attack affects adjacent hexes except undeads (Liches).
     */
    public final static int LICHSHOOT       = 0x00000080;

    /**
     * (All necropolis creatures + Ghosts).
     */
    public final static int UNDEAD          = 0x00000100;

    /**
     * (Golems, gargoyles, elementals).
     */
    public final static int LIFELESS        = 0x00000200;

    /**
     * (Trolls).
     */
    public final static int REGENERATES     = 0x00000400;

    /**
     * (Cavalry).
     */
    public final static int JOUSTING        = 0x00000800;

    /**
     * (Air elementals).
     */
    public final static int AIRMAGICIMM     = 0x00001000;

    /**
     * (Earth elementals).
     */
    public final static int EARTHMAGICIMM   = 0x00002000;

    /**
     * (Fire elementals, Phoenix).
     */
    public final static int FIREMAGICIMM    = 0x00004000;

    /**
     * (Water elementals).
     */
    public final static int WATERMAGICIMM   = 0x00008000;

    /**
     * 40% magic resistance (Dwarves).
     */
    public final static int PROCRESIST40    = 0x00010000;

    /**
     * Receives only qurter damage from damage spells (Golems).
     */
    public final static int QUARTERDMG      = 0x00020000;

    /**
     * Ghost perk.
     */
    public final static int GHOST           = 0x00040000;

    /**
     * Genie perk.
     */
    public final static int DOHALF          = 0x00080000;

    /**
     * Drains life (Vampires).
     */
    public final static int DRAINLIFES      = 0x00100000;

    /**
     * (Black draggons).
     */
    public final static int ALLMAGICIMM     = AIRMAGICIMM | EARTHMAGICIMM | FIREMAGICIMM | WATERMAGICIMM;
}
