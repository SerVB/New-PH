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
import newph.metric.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_Mineral extends iMapItem {

    /**
     *
     * @param pos
     * @param mt Mineral type.
     * @param count
     */
    public iMapItem_Mineral(final iPoint pos, int mt, int count) {
        super(pos, MAPITEM.MINERAL, PDGG.RES_ICONS+mt);

        this.m_mineralType = mt;
        this.m_count = count;

        if (mt == MINERAL.UNKNOWN) {
            mt = MINERAL.GOLD + gGame.Map().Rand(MINERAL.COUNT);
            m_spriteId = PDGG.RES_ICONS + mt;
            m_mineralType = mt;
        }

        if (m_count == RANDOM.QUANTITY) {
            switch (mt) {
                case MINERAL.GOLD:
                    m_count = 500 + gGame.Map().Rand(501);
                    break;
                case MINERAL.WOOD:
                case MINERAL.ORE:
                    m_count = 5 + gGame.Map().Rand(6);
                    break;
                default:
                    m_count = 3 + gGame.Map().Rand(4);
                    break;
            }
        }
    }

    public int MineralType() {
        return m_mineralType;
    }

    public int MineralCount() {
        return m_count;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }

        pHero.Owner().AddMineral(m_mineralType, m_count);
        if (bActive) {
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
            gGame.AddCellMsg(String.format("+%d#I%04X",m_count,PDGG.RES_MINI_ICONS + m_mineralType), m_Pos);
        }
        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_mineralType);
        buff.Write(m_count);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_GOLD + m_mineralType;
    }

//    public IMPL_TYPEAWARE( iMapItem_Mineral );

    private int m_mineralType;
    private int m_count;

}
