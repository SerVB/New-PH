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
import newph.core.metric.iPoint;
import Constants.*;
import Game.iHero;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iMapItem_Chest extends iMapItem {

    public iMapItem_Chest(final iPoint pos, int count) {
        super(pos, MAPITEM.CHEST, PDGG.RES_CHEST);
        this.m_count = count;

        if (m_count == RANDOM.VAL) {
            m_count = 500 + (gGame.Map().Rand(10) * 100);
        }
    }

    public iMapItem_Chest(final iPoint pos) {
        this(pos, RANDOM.VAL);
    }

    public int ExpPtCount() {
        return m_count;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        if (bActive) {
            iDlg_Chest dlg = new iDlg_Chest(gApp.ViewMgr(), pHero, this);
            int res = dlg.DoModal();
            if (res == iDlg_Chest.Gold) {
                pHero.Owner().AddMineral(MINERAL.GOLD, dlg.GoldCount(), true);
                if (bActive) {
                    gGame.AddCellMsg(iFormat.format("+%d#I%04X",dlg.GoldCount(),PDGG.RES_MINI_ICONS + MINERAL.GOLD), m_Pos);
                }
            } else if (res == iDlg_Chest.Experience) {
                pHero.ReceiveExperience(dlg.ExpCount());
            } else {
                Tracer.check(0);
            }
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
        } else {
            iAI_Player pOwner = DynamicCast<iAI_Player>(pHero.Owner());
            Tracer.check(pOwner != null);
            pOwner.ProcessChest(pHero, this);
        }

        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_count);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_CHEST;
    }

//    public IMPL_TYPEAWARE( iMapItem_Chest );

    private int m_count;
}
