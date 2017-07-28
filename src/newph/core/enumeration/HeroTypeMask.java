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
package newph.core.enumeration;

import newph.core.type.EnumC;

/**
 * Hero Type Mask.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum HeroTypeMask implements EnumC {

    HTM_KNIGHT      (0b000001),
    HTM_BARBARIAN   (0b000010),
    HTM_WIZARD      (0b000100),
    HTM_WARLOCK     (0b001000),
    HTM_SORCERESS   (0b010000),
    HTM_NECROMANCER (0b100000),

    HTM_GOOD        (HTM_KNIGHT.value | HTM_WIZARD.value | HTM_SORCERESS.value),
    HTM_EVIL        (HTM_BARBARIAN.value | HTM_WARLOCK.value | HTM_NECROMANCER.value),
    HTM_MIGHT       (HTM_KNIGHT.value | HTM_BARBARIAN.value),
    HTM_MISC        (HTM_SORCERESS.value | HTM_NECROMANCER.value),
    HTM_MAGIC       (HTM_WIZARD.value | HTM_WARLOCK.value),

    HTM_ALL         (HTM_GOOD.value | HTM_EVIL.value);

    //<editor-fold defaultstate="collapsed" desc="C-like enum (for indexing and count)">
    /**
     * The value of the element.
     */
    private int value;

    /**
     * Constructs a new element with the next value.
     */
    private HeroTypeMask() {
        this(NextValueHolder.nextValue); // Call the constructor with the next value
    }

    /**
     * Constructs a new element with the specified value.
     * @param value The specified value.
     */
    private HeroTypeMask(final int value) {
        this.value = value;                     // Set the specified value to this value
        NextValueHolder.nextValue = value + 1;  // Increment the next value for a next element
    }

    /**
     * Returns the value of the element.
     * @return The value of the element.
     */
    @Override
    public final int getValue() {
        return value;
    }

    /**
     * Object that holds the next value.
     */
    private final static class NextValueHolder {

        /**
         * The next value.
         */
        private static int nextValue = 0;

    }
    //</editor-fold>

}
