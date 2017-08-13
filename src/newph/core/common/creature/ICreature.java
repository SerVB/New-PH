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
package newph.core.common.creature;

import java.util.EnumSet;
import newph.core.common.creature.property.Level;
import newph.core.common.creature.property.Perk;
import newph.core.common.creature.property.Size;
import newph.core.common.creature.property.Speed;
import newph.core.common.creature.property.Transportation;
import newph.core.common.iMineralSet;
import newph.core.enumeration.Nation;

/**
 * Creature Interface.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public interface ICreature {

    /**
     * Returns the growth divider of the creature level.
     *
     * @return The growth divider.
     */
    public int getGrowthDivider();

    /**
     * Returns the power index of the creature.
     *
     * @return The power index.
     */
    public int getPowerIndex();

    /**
     * Returns the growth rate of the creature.
     *
     * @return The growth rate.
     */
    public int getGrowth();

    public Level getLevel();

    public Nation getNation();

    public int getAttack();

    public int getDefence();

    public Speed getSpeed();

    public Size getSize();

    public Transportation getTransType();

    public int getShots();

    public int getHits();

    public int getDamageMin();

    public int getDamageMax();

    public iMineralSet getCost();

    public EnumSet<Perk> getPerks();

}
