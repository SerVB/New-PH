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
package Game;

import Constants.*;
import ConstantsGame.*;

/**
 * Battle helper functions.
 */
public class HelperFunctions {

    /**
     * Unit's color.
     * @param pid Player ID.
     * @return
     */
    public final static String GetUnitsColor(int pid) {
        return (pid == PID.NEUTRAL)? "#FAAA" : PLAYER.TEXT_COLORS[pid];
    }

    /**
     * Calculates delta distance between two cells.
     * @param px1
     * @param py1
     * @param px2
     * @param py2
     * @return
     */
    public final static int BattleCellsDelta(int px1, int py1, int px2, int py2) {
        int nx1 = px1 - py1 / 2;
        int ny1 = px1 + (py1 + 1) / 2;
        int nx2 = px2 - py2 / 2;
        int ny2 = px2 + (py2 + 1) / 2;

        int dx = nx2 - nx1;
        int dy = ny2 - ny1;

//        if (iSIGN(dx) == iSIGN(dy)) {
        if (dx * dy >= 0) {
            return Math.max(Math.abs(dx), Math.abs(dy));
        }
        return Math.abs(dx) + Math.abs(dy);
    }

    public final static int BattleCellsOrient(int fx, int fy, int tx, int ty, int orient) {
        int xdiff = (orient==0) ? (tx - fx) : (fx-tx);

        if ( ((fy & 1) == 0 && (ty & 1) != 0 && orient == 0) ||
             ((fy & 1) != 0 && (ty & 1) == 0 && orient != 0) )
            xdiff -= 1;

        if (xdiff < 0)
//            orient = !orient;
            orient = (orient != 0) ? 0 : 1;
        return orient;
    }

    public final static int BattleDirOrient(int dir) {
        if (dir > 0 && dir < 4)
            return ORIENT.Right;
        else
            return ORIENT.Left;
    }
}
