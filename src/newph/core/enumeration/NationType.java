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
public enum NationType {

    NEUTRAL(IdeologyType.IDEOLOGY_NEUTRAL),
    HIGHMEN(IdeologyType.IDEOLOGY_GOOD),
    BARBARIANS(IdeologyType.IDEOLOGY_EVIL),
    WIZARDS(IdeologyType.IDEOLOGY_GOOD),
    BEASTMEN(IdeologyType.IDEOLOGY_EVIL),
    ELVES(IdeologyType.IDEOLOGY_GOOD),
    UNDEADS(IdeologyType.IDEOLOGY_EVIL);

    public final IdeologyType ideology;

    private NationType(final IdeologyType ideology) {
        this.ideology = ideology;
    }

}
