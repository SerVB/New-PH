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

/**
 * Nation Type.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum Nation {

    NEUTRAL(Ideology.IDEOLOGY_NEUTRAL),
    HIGHMEN(Ideology.IDEOLOGY_GOOD),
    BARBARIANS(Ideology.IDEOLOGY_EVIL),
    WIZARDS(Ideology.IDEOLOGY_GOOD),
    BEASTMEN(Ideology.IDEOLOGY_EVIL),
    ELVES(Ideology.IDEOLOGY_GOOD),
    UNDEADS(Ideology.IDEOLOGY_EVIL);

    public final Ideology ideology;

    private Nation(final Ideology ideology) {
        this.ideology = ideology;
    }

}
