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
import Common.iFormat;
import Common.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_CampFire extends iMapItem {

    public iMapItem_CampFire(final iPoint pos, int goldCount, int addMinType, int addMinCount) {
        super(pos, MAPITEM.CAMPFIRE, PDGG.RES_CMFIRE);

        this.m_addMinType = addMinType;
        this.m_addMinCount = addMinCount;
        this.m_goldCount = goldCount;

        if (m_goldCount == RANDOM.VAL) {
            m_goldCount = 400 + (gGame.Map().Rand(3))*100;
        }

        if (m_addMinType == MINERAL.UNKNOWN) {
            m_addMinType = MINERAL.ORE + gGame.Map().Rand(6);
        }
        if (m_addMinCount == RANDOM.VAL) {
            m_addMinCount = 4 + gGame.Map().Rand(3);
        }
    }

    public iMapItem_CampFire(final iPoint pos, int goldCount, int addMinType) {
        this(pos, goldCount, addMinType, RANDOM.VAL);
    }

    public iMapItem_CampFire(final iPoint pos, int goldCount) {
        this(pos, goldCount, MINERAL.UNKNOWN);
    }

    public iMapItem_CampFire(final iPoint pos) {
        this(pos, RANDOM.VAL);
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }

        pHero.Owner().AddMineral(MINERAL.GOLD, m_goldCount);
        pHero.Owner().AddMineral(m_addMinType, m_addMinCount);
        if (bActive) {
            gGame.AddCellMsg(iFormat.format("+%d#I%04X +%d#I%04X",m_goldCount,PDGG.RES_MINI_ICONS, m_addMinCount, PDGG.RES_MINI_ICONS + m_addMinType), m_Pos);
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
        }
        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_goldCount);
        buff.Write(m_addMinType);
        buff.Write(m_addMinCount);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_CAMPFIRE;
    }

//    public IMPL_TYPEAWARE( iMapItem_CampFire );

    private int m_addMinType;
    private int m_addMinCount;
    private int m_goldCount;
}
