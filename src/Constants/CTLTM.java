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
 * Маска типа замка.
 * Castle type mask.
 */
public class CTLTM {

    /**
     * Citadel.
     */
    public final static int CITADEL = 1 << CTLT.CITADEL;

    /**
     * Stronghold.
     */
    public final static int STRONGHOLD = 1 << CTLT.STRONGHOLD;

    /**
     * Tower.
     */
    public final static int TOWER = 1 << CTLT.TOWER;

    /**
     * Dungeon.
     */
    public final static int DUNGEON = 1 << CTLT.DUNGEON;

    /**
     * Fortress.
     */
    public final static int FORTRESS = 1 << CTLT.FORTRESS;

    /**
     * Necropolis.
     */
    public final static int NECROPOLIS = 1 << CTLT.NECROPOLIS;

    /**
     * Might.
     */
    public final static int MIGHT = CITADEL | STRONGHOLD;

    /**
     * Magic.
     */
    public final static int MAGIC = TOWER | DUNGEON;

    /**
     * Misc (Разное).
     */
    public final static int MISC = FORTRESS | NECROPOLIS;

    /**
     * All.
     */
    public final static int ALL = MIGHT | MAGIC | MISC;

}
