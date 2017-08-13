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
package newph.core.common.army;

import newph.core.common.creature.CreatureMisc;
import newph.core.common.creature.ICreature;
import newph.core.constant.RANDOM;
import newph.core.staticFunction.Tracer;

/**
 * Creature Group Class. Contains creature type and the number of creatures in the group.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class iCreatGroup {

    /**
     * Creature type.
     */
    private ICreature m_creatType;

    /**
     * Creatures count.
     */
    private int m_count;

    /**
     * Constructs Creature Group by given parameters.
     *
     * @param m_creatType   Creature type.
     * @param m_count       Creatures count.
     */
    private iCreatGroup(final ICreature m_creatType, final int m_count) {
        this.m_creatType = m_creatType;
        this.m_count = m_count;
    }

    /**
     * Constructs Creature Group: copies the other Creature Group's parameters.
     *
     * @param other The group to be copied.
     */
    public iCreatGroup(final iCreatGroup other) {
        this.m_count = other.m_count;
        this.m_creatType = other.m_creatType;
    }

    /**
     * Constructs a random creature group.
     *
     * @return The new instance of the Creature Group class.
     */
    public static iCreatGroup newRandomGroup() {
        return new iCreatGroup(CreatureMisc.CREAT_RANDOM, RANDOM.RANDOM_QUANTITY);
    }

    /**
     * Constructs a creature group of a random quantity.
     *
     * @param creature  The creature type.
     * @return          The new instance of the Creature Group class.
     */
    public static iCreatGroup newRandomQuantity(final ICreature creature) {
        return new iCreatGroup(creature, RANDOM.RANDOM_QUANTITY);
    }

    /**
     * Constructs a creature group by given parameters.
     *
     * @param creature  The creature type.
     * @param count     The creatures count.
     * @return          The new instance of the Creature Group class.
     */
    public static iCreatGroup newGroup(final ICreature creature, final int count) {
        return new iCreatGroup(creature, count);
    }

    /**
     * Resets the creature group:
     * sets the creature type to random creature and the creatures count to random quantity.
     */
    public void Reset() {
        m_creatType = CreatureMisc.CREAT_RANDOM;
        m_count     = RANDOM.RANDOM_QUANTITY;
    }

    /**
     * Resets the creature group:
     * sets the creature type to the given type and the creatures count to random quantity.
     *
     * @param creature The new creature type.
     */
    public void Reset(final ICreature creature) {
        m_creatType = creature;
        m_count     = RANDOM.RANDOM_QUANTITY;
    }

    /**
     * Resets the creature group:
     * sets the creature type to the given type and the creatures count to the given count.
     *
     * @param creature  The new creature type.
     * @param count     The new creatures count.
     */
    public void Reset(final ICreature creature, final int count) {
        m_creatType = creature;
        m_count     = count;
    }

    /**
     * Checks if the creature group is valid.
     *
     * @return {@code True} if the group is valid, {@code false} otherwise.
     */
    public boolean IsValid() {
        return m_creatType != CreatureMisc.CREAT_UNKNOWN;
    }

    /**
     * Returns the creature type.
     *
     * @return The creature type.
     */
    public ICreature Type() {
        return m_creatType;
    }

    public void setType(final ICreature creature) {
        this.m_creatType = creature;
    }

    /**
     * Returns the creature count.
     *
     * @return The creature count.
     */
    public int Count() {
        return m_count;
    }

    public void setCount(final int count) {
        this.m_count = count;
    }

    /**
     * Returns the group power index.
     * <p>
     * The group power index is the creature power index multiplied by the creature count.
     *
     * @return The group power index.
     */
    public int GroupPower() {
        return m_count * m_creatType.getPowerIndex();
    }

    /**
     * Grows the creature count by one week.
     */
    public void Grow() {
        Grow(1);
    }

    /**
     * Grows the creature count by some weeks.
     *
     * @param weeks The weeks number.
     */
    public void Grow(final int weeks) {
        Tracer.check(m_count != RANDOM.RAND_VAL);

        int remainingWeeks = weeks;

        while (remainingWeeks > 0) {
            int div = m_creatType.getGrowthDivider();

            if (m_count < m_creatType.getGrowth() * 2) {
                div /= 2;
            }

            m_count += (m_count + div - 1) / div;

            remainingWeeks--;
        }
    }

}
