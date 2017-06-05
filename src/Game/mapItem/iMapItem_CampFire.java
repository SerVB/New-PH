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
package Game.mapItem;

import Common.iPoint;
import Constants.*;

/**
 *
 */
public class iMapItem_CampFire extends iMapItem {

    public iMapItem_CampFire(final iPoint pos, int goldCount, int addMinType, int addMinCount);

    public iMapItem_CampFire(final iPoint pos, int goldCount, int addMinType) {
        this(pos, goldCount, addMinType, RANDOM.VAL);
    }

    public iMapItem_CampFire(final iPoint pos, int goldCount) {
        this(pos, goldCount, MINERAL.UNKNOWN);
    }

    public iMapItem_CampFire(final iPoint pos) {
        this(pos, RANDOM.VAL);
    }

    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage);
    public void Serialize(iDynamicBuffer buff);

    public int NameKey() {
        return TRID.MAPRES_CAMPFIRE;
    }

//    public IMPL_TYPEAWARE( iMapItem_CampFire );

    private int    m_addMinType;
    private int            m_addMinCount;
    private int            m_goldCount;
}
