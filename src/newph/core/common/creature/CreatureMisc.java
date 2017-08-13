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
 * Misc Creature.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum CreatureMisc implements ICreature {

    CREAT_UNKNOWN(0),

    /* Random values */
    CREAT_RANDOM   (0x0F00),
    CREAT_RANDOM_L1(0x0F01),
    CREAT_RANDOM_L2(0x0F02),
    CREAT_RANDOM_L3(0x0F03),
    CREAT_RANDOM_L4(0x0F04),
    CREAT_RANDOM_L5(0x0F05),
    CREAT_RANDOM_L6(0x0F06);

    public final int value;

    private CreatureMisc(int value) {
        this.value = value;
    }

    @Override
    public int getPowerIndex() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getGrowthDivider() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getGrowth() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public Level getLevel() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public Nation getNation() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getAttack() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getDefence() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public Speed getSpeed() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public Size getSize() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public Transportation getTransType() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getShots() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getHits() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getDamageMin() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public int getDamageMax() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public iMineralSet getCost() {
        throw new UnsupportedOperationException("Not available!");
    }

    @Override
    public EnumSet<Perk> getPerks() {
        throw new UnsupportedOperationException("Not available!");
    }

}
