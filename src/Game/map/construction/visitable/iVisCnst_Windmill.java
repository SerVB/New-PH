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
package Game.map.construction.visitable;

import Common.iDynamicBuffer;
import newph.metric.iPoint;
import Common.iRewardItem;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import collections.simple.iRewardsCtr;

/**
 *
 */
public class iVisCnst_Windmill extends iVisCnst {

    public iVisCnst_Windmill(iVisCnstT_Windmill pProto, final iPoint pos) {
        super(pProto, pos, true);
        m_bVisited = false;
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        m_bVisited = false;
        UpdateMinerals();
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (Visited(pVisitor)) {
            if (bActive) {
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.VisMessage()],
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        } else {
            if (bActive) {
                iRewardsCtr rew = new iRewardsCtr();
                rew.add(new iRewardItem(RIT.MINERAL, m_mtype, m_mcnt));
                iDlg_Rewards dlg = new iDlg_Rewards(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.Message()],
                        pVisitor,
                        rew
                );
                dlg.DoModal();
            }
            pVisitor.Owner().AddMineral(m_mtype, m_mcnt);
            m_bVisited = true;
        }
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return m_bVisited;
    }

    @Override
    public void Serialize(iDynamicBuffer dbuff) {
        // Common data serialization
        super.Serialize(dbuff);
        //
        dbuff.Write(m_bVisited ? 1 : 0);
        dbuff.Write(m_mtype);
        dbuff.Write(m_mcnt);
    }

    @Override
    public int Unserialize(iDynamicBuffer dbuff) {
        int flag = super.Unserialize(dbuff);
        if (flag == 0) {
            // Init by default
            m_bVisited = false;
            UpdateMinerals();
        } else {
            // Read data
            int bVisited = (int) dbuff.Read();
            m_bVisited = bVisited != 0;
            int mtype = (int) dbuff.Read();
            m_mtype = mtype;
            m_mcnt = (int) dbuff.Read();
        }
        return flag;
    }

    private void UpdateMinerals() {
        m_mtype = MINERAL.ORE + gGame.Map().Rand(6);
        m_mcnt = 3 + gGame.Map().Rand(4);
    }

    private int    m_mtype;
    private int            m_mcnt;
    private boolean            m_bVisited;

}
