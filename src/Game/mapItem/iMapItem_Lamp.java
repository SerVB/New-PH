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

import Common.iDynamicBuffer;
import Common.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_Lamp extends iMapItem {

    public iMapItem_Lamp(final iPoint pos, int count);

    public iMapItem_Lamp(final iPoint pos) {
        this(pos, RANDOM.VAL);
    }

    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage);

    public void Serialize(iDynamicBuffer buff);

    @Override
    public int NameKey() {
        return TRID.MAPRES_LAMP;
    }

    public int Count() {
        return m_count;
    }

//    public IMPL_TYPEAWARE( iMapItem_Lamp );

    private int            m_count;
}
