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
 *
 */
public class CREAT_DESCRIPTOR {
    /**
     * Unit level (1-6).
     */
    int         level;
    /**
     * Unit alignment (право или лево в битве, что ли?).
     */
    int         nation;

    /**
     * Attack skill.
     */
    long        attack;
    /**
     * Deffence skill.
     */
    long        defence;
    /**
     * Unit speed (1-20).
     */
    int         speed;
    /**
     * Unit size (1 or 2 - used in battle).
     */
    int         size;

    /**
     * Transportation method.
     */
    int         transType;
    /**
     * 0 -- means no range attack.
     */
    long        shots;
    /**
     * Hit points (health).
     */
    long        hits;
    /**
     * Minimum damage.
     */
    long        damage_min;
    /**
     * Maximum damage.
     */
    long        damage_max;

    /**
     * Cost per unit.
     */
    iMineralSet cost;
    /**
     * Growth rate.
     */
    long        growth;
    /**
     * Power index (used for AI).
     */
    long        pidx;
    /**
     * Creature perks.
     */
    int         perks;

    public CREAT_DESCRIPTOR(
            int level,
            int nation,

            long attack,
            long defence,
            long speed,
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
