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

package newph.core.enumeration.creature.property;

/**
 * Creature Speed.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum Speed {

    SUPERSLOW(1),
    ULTRASLOW(2),
    VERYSLOW(3),
    EXTRASLOW(4),
    SLOW(5),
    SWIFT(6),
    EXTRASWIFT(7),
    VERYSWIFT(8),

    /**
     * (2 to 2).
     */
    ULTRASWIFT(9),

    /**
     * (1 to 2 or 2 to 1).
     */
    SUPERSWIFT(10),

    /**
     * (1 to 1).
     */
    QUICK(11),
    EXTRAQUICK(12),
    VERYQUICK(13),
    ULTRAQUICK(14),
    SUPERQUICK(15),
    FAST(16),
    EXTRAFAST(17),
    VERYFAST(18),
    ULTRAFAST(19),
    SUPERFAST(20),
    MAX(21);

    public final int speed;

    private Speed(final int speed) {
        this.speed = speed;
    }

}
