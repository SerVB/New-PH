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
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Constants;

/**
 * Spell class.
 */
public class SPC {

    public final static int DAMAGE     = 0;  // fparam = damage
    public final static int FURTSKILL  = 1;  // fparam = skill, sparam = modifier
    public final static int DISRAY     = 2;  // fparam = defence modifier
    public final static int RESURRECT  = 3;  // fparam = hit points, sparam = true/not
    public final static int SUMMON     = 4;  // fparam = creature type, sparam = quantity
    public final static int BLESS      = 5;  // fparam = modifier (+1, 0, -1), sparam = anti spell
    public final static int BLIND      = 6;  // fparam = retail power (%), sparam = not used
    public final static int EARTHQUAKE = 7;  // fparam = walls ammount
    public final static int DISPEL     = 8;  // - none -
    public final static int CURE       = 9;  // fparam = hit points per spell power
    public final static int TOWNPORTAL = 10; // fparam = (_?_)
    public final static int ANTIMAGIC  = 11; // fparam = maxLevel
    public final static int PRAYER     = 12; // fparam = value
    public final static int VISION     = 13; // fparam = radius
    public final static int COUNT      = 14;

}
