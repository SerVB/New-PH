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

package newph.core.common.creature.property;

/**
 * Creature Perks Masks and Count.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum Perk {

    /**
     * Void perk.
     */
    CPERK_VOID,

    /**
     * Shots twice (rangers, grand elves, etc).
     */
    CPERK_DOUBLESHOT,

    /**
     * Attack the target twice (paladins, wolves, etc).
     */
    CPERK_DOUBLEATTACK,

    /**
     * Scores full damage even at melee.
     */
    CPERK_NOMELEEPENALTY,

    /**
     * Non retaliated attack (vampires, rogues, sprites, hydras, etc).
     */
    CPERK_NONRETALATTACK,

    /**
     * Retaliates against every attack (griffins).
     */
    CPERK_RETALTOALL,

    /**
     * Two-hex attack (dragons).
     */
    CPERK_TWOHEXATTACK,

    /**
     * Attacks all adjacent enemies (hydras).
     */
    CPERK_ADJACENTATTACK,

    /**
     * Range attack affects adjacent hexes except undeads (liches).
     */
    CPERK_LICHSHOOT,

    /**
     * All necropolis creatures + ghosts.
     */
    CPERK_UNDEAD,

    /**
     * Golems, gargoyles, elementals.
     */
    CPERK_LIFELESS,

    /**
     * Trolls.
     */
    CPERK_REGENERATES,

    /**
     * Cavalry.
     */
    CPERK_JOUSTING,

    /**
     * Air elementals.
     */
    CPERK_AIRMAGICIMM,

    /**
     * Earth elementals.
     */
    CPERK_EARTHMAGICIMM,

    /**
     * Fire elementals, Phoenix.
     */
    CPERK_FIREMAGICIMM,

    /**
     * Water elementals.
     */
    CPERK_WATERMAGICIMM,

    /**
     * 40% magic resistance (dwarves).
     */
    CPERK_40PROCRESIST,

    /**
     * Receives only quarter damage from damage spells (golems).
     */
    CPERK_QUARTERDMG,

    /**
     * Ghost perk.
     */
    CPERK_GHOST,

    /**
     * Genie perk.
     */
    CPERK_DOHALF,

    /**
     * Drains life (vampires).
     */
    CPERK_DRAINLIFES;

}
