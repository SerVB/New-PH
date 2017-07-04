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

import Constants.AC;
import newph.core.staticFunction.Tracer;
import Constants.*;

/**
 * Artifacts container.
 */
public class iArtCell {

    public iArtCell(int _cell, int _artId) {
        this.cell = _cell;
        this.artId = _artId;
    }

    public iArtCell(int _cell) {
        this.cell = _cell;
        this.artId = 0xFFFF;
    }

    public final boolean Empty() {
        return artId == 0xFFFF;
    }

    public void Reset() {
        artId = 0xFFFF;
    }

    public int AssignType() {
        if (artId != 0xFFFF) {
            return gGame.ItemMgr().m_artMgr.get(artId).Assign();
        }

        Tracer.check(cell != AC.UNDEFINED);

        return HERO.ART_CELL_ASSIGN[cell];
    }

    public int cell;
    public int artId;
}
