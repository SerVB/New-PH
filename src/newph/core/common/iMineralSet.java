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

package newph.core.common;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;
import newph.core.enumeration.Mineral;

/**
 * Mineral Set Class.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class iMineralSet {
    
    private final EnumMap<Mineral, Integer> quantity = new EnumMap<>(Mineral.class);
    
    public iMineralSet() {
        for (final Mineral mineral : Mineral.values()) {
            this.setQuantity(mineral, 0);
        }
    }

    public iMineralSet(final iMineralSet other) {
        for (final Mineral mineral : Mineral.values()) {
            this.setQuantity(mineral, other.getQuantity(mineral));
        }
    }

    public iMineralSet(
            final int gold,
            final int ore,
            final int wood,
            final int mercury,
            final int gems,
            final int crystals,
            final int sulfur
    ) {
        setQuantity(Mineral.GOLD,     gold);
        setQuantity(Mineral.ORE,      ore);
        setQuantity(Mineral.WOOD,     wood);
        setQuantity(Mineral.MERCURY,  mercury);
        setQuantity(Mineral.GEMS,     gems);
        setQuantity(Mineral.CRYSTALS, crystals);
        setQuantity(Mineral.SULFUR,   sulfur);
    }

    public void Reset() {
        quantity.clear();
    }

    /**
     * Returns the integer "how many times this mineral set has the other mineral set".
     * 
     * @param other The other mineral other.
     * @return      The integer result.
     */
    public int Has(final iMineralSet other)  {
        int cnt = 0;
        
        for (final Mineral mineral : Mineral.values()) {
            if (this.getQuantity(mineral) < other.getQuantity(mineral)) {
                return 0;
            } else if (other.getQuantity(mineral) > 0) {
                if (0 < cnt) {
                    cnt = Math.min(cnt, this.getQuantity(mineral) / other.getQuantity(mineral));
                } else {
                    cnt = this.getQuantity(mineral) / other.getQuantity(mineral);
                }
            }
        }
        
        return cnt;
    }
    
    private void setQuantity(final Mineral mineral, final int newQuantity) {
        quantity.put(mineral, newQuantity);
    }
    
    private int getQuantity(final Mineral mineral) {
        return quantity.get(mineral);
    }

    /**
     * Checks if the mineral set is empty.
     * 
     * @return {@code True} if all the mineral quantities are null, {@code false} if not.
     */
    public boolean Empty() {
        for (final Mineral mineral : Mineral.values()) {
            if (this.getQuantity(mineral) != 0) {
                return false;
            }
        }
        return true;
    }

    public static iMineralSet Intersect(final iMineralSet first, final iMineralSet second) {
        final iMineralSet result = new iMineralSet();
        
        for (final Mineral mineral : Mineral.values()) {
            result.setQuantity(mineral, Math.min(first.getQuantity(mineral), second.getQuantity(mineral)));
        }
        
        return result;
    }

    public void Normalize() {
        for (final Mineral mineral : Mineral.values()) {
            if (this.getQuantity(mineral) < 0) {
                this.setQuantity(mineral, 0);
            }
        }
    }

    public void add(final iMineralSet other) {
        for (final Mineral mineral : Mineral.values()) {
            setQuantity(mineral, this.getQuantity(mineral) + other.getQuantity(mineral));
        }
    }

    public void subtract(final iMineralSet other) {
        for (final Mineral mineral : Mineral.values()) {
            setQuantity(mineral, this.getQuantity(mineral) - other.getQuantity(mineral));
        }
    }

    public void multiply(final int value) {
        for (final Mineral mineral : Mineral.values()) {
            this.setQuantity(mineral, this.getQuantity(mineral) * value);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.quantity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final iMineralSet other = (iMineralSet) obj;
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "iMineralSet{" + quantity.toString() + '}';
    }

}
