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
 * Creature Speed.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum Speed {

    /**
     * Speed of 1.
     */
    SUPERSLOW(1),

    /**
     * Speed of 2.
     */
    ULTRASLOW(2),

    /**
     * Speed of 3.
     */
    VERYSLOW(3),

    /**
     * Speed of 4.
     */
    EXTRASLOW(4),

    /**
     * Speed of 5.
     */
    SLOW(5),

    /**
     * Speed of 6.
     */
    SWIFT(6),

    /**
     * Speed of 7.
     */
    EXTRASWIFT(7),

    /**
     * Speed of 8.
     */
    VERYSWIFT(8),

    /**
     * Speed of 9 (2 to 2).
     */
    ULTRASWIFT(9),

    /**
     * Speed of 10 (1 to 2 or 2 to 1).
     */
    SUPERSWIFT(10),

    /**
     * Speed of 11 (1 to 1).
     */
    QUICK(11),

    /**
     * Speed of 12.
     */
    EXTRAQUICK(12),

    /**
     * Speed of 13.
     */
    VERYQUICK(13),

    /**
     * Speed of 14.
     */
    ULTRAQUICK(14),

    /**
     * Speed of 15.
     */
    SUPERQUICK(15),

    /**
     * Speed of 16.
     */
    FAST(16),

    /**
     * Speed of 17.
     */
    EXTRAFAST(17),

    /**
     * Speed of 18.
     */
    VERYFAST(18),

    /**
     * Speed of 19.
     */
    ULTRAFAST(19),

    /**
     * Speed of 20.
     */
    SUPERFAST(20),

    /**
     * Speed of 21.
     */
    MAX(21);

    private final int speedValue;

    public int getSpeedValue() {
        return speedValue;
    }

    private Speed(final int speedValue) {
        this.speedValue = speedValue;
    }

}
