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
package newph.core.enumeration.creature;

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

}
