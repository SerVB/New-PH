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
 * Spell Target Type mask (used for combat spells).
 */
public class STT {

    public final static int NONE        = 0b0000000;
    public final static int FRIENDLY    = 0b0000001;
    public final static int ENEMY       = 0b0000010;
    public final static int UNDEAD      = 0b0000100;
    public final static int LIFELESS    = 0b0001000;
    public final static int NORMAL      = 0b0010000;
    public final static int TROOPS      = 0b0100000;
    public final static int SHOOTERS    = 0b1000000;
    public final static int FRUNDEADS   = FRIENDLY | UNDEAD   | TROOPS   | SHOOTERS;
    public final static int FRNUNDEADS  = FRIENDLY | LIFELESS | NORMAL   | TROOPS   | SHOOTERS;
    public final static int ENNUNDEADS  = ENEMY    | LIFELESS | NORMAL   | TROOPS   | SHOOTERS;
    public final static int ENNORMALS   = ENEMY    | NORMAL   | TROOPS   | SHOOTERS;
    public final static int ALLUNDEADS  = FRIENDLY | ENEMY    | UNDEAD   | TROOPS   | SHOOTERS;
    public final static int ALLNUNDEADS = FRIENDLY | ENEMY    | NORMAL   | LIFELESS | TROOPS | SHOOTERS;
    public final static int ALLNORMALS  = FRIENDLY | ENEMY    | NORMAL   | TROOPS   | SHOOTERS;
    public final static int FRNORMALS   = FRIENDLY | NORMAL   | TROOPS   | SHOOTERS;
    public final static int FRTROOPS    = FRIENDLY | UNDEAD   | LIFELESS | NORMAL   | TROOPS;
    public final static int FRSHOOTERS  = FRIENDLY | UNDEAD   | LIFELESS | NORMAL   | SHOOTERS;
    public final static int ALLFRIENDLY = FRIENDLY | UNDEAD   | LIFELESS | NORMAL   | TROOPS | SHOOTERS;
    public final static int ALLENEMY    = ENEMY    | UNDEAD   | LIFELESS | NORMAL   | TROOPS | SHOOTERS;
    public final static int ALL         = FRIENDLY | ENEMY    | UNDEAD   | LIFELESS | NORMAL | TROOPS | SHOOTERS;

}
