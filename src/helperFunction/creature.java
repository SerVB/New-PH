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
package helperFunction;

import Constants.*;
import java.util.ArrayList;
import newph.util.Tracer;

/**
 * Helper Functions for Creatures.
 */
public class creature {

    /**
     *
     * @param ct Creature Type.
     * @return
     */
    public final static int NormalizeCreatType(int ct) {
        if (ct < CREAT.RANDOM) return ct;
        else if (ct == CREAT.RANDOM) {
            return gGame.Map().Rand(CREAT.COUNT);
        } else {
            int lvl = ct - CREAT.RANDOM;
            ArrayList<Integer> cid = new ArrayList();
            for (int xx = 0; xx < CREAT.COUNT; ++xx) {
                if (CREAT.DESC[xx].level == lvl) {
                    cid.add(xx);
                }
            }
            Tracer.check(!cid.isEmpty());
            return cid.get(gGame.Map().Rand(cid.size()));
        }
    }

}
