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
package Game.map.item;

import Constants.map.MAPITEM;
import Common.iDynamicBuffer;
import newph.core.metric.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_ManaCrystal extends iMapItem {

    public iMapItem_ManaCrystal(final iPoint pos, int count) {
        super(pos, MAPITEM.MANACRYSTAL, PDGG.RES_MCRYST);
        this.m_count = count;

        if (m_count == RANDOM.QUANTITY) {
            m_count = 5 + gGame.Map().Rand(20);
        }
    }

    public int ManaPtCount() {
        return m_count;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        pHero.addManaPts(m_count);
        if (bActive) {
            gGame.AddCellMsg(String.format("+%d#I%04X",m_count,PDGG.ICN_MANA), m_Pos);
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
        }
        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_count);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_MANACRYSTAL;
    }

//    public IMPL_TYPEAWARE( iMapItem_ManaCrystal );

    private int            m_count;
}
