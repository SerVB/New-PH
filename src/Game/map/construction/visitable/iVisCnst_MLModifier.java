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

import Constants.PROVIDER;
import Constants.ENDUR;
import Constants.RIT;
import newph.metric.iPoint;
import Common.iRewardItem;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import collections.simple.iRewardsCtr;
import newph.staticFunction.Tracer;

/**
 *
 */
public class iVisCnst_MLModifier extends iVisCnst {

    public iVisCnst_MLModifier(iVisCnstT_MLModifier pProto, final iPoint pos) {
        super(pProto, pos, true);
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        super.NewWeek(weekDesk);
        m_Visitors.clear();
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
            iVisCnstT_MLModifier pProto = DynamicCast<iVisCnstT_MLModifier>(m_pProto);
            Tracer.check(pProto);
            if (bActive) {
                iRewardsCtr rew = new iRewardsCtr();
                if (pProto.MoraleModifier()) {
                    rew.add(new iRewardItem(RIT.MORALE, 0, pProto.MoraleModifier()));
                }
                if (pProto.LuckModifier()) {
                    rew.add(new iRewardItem(RIT.LUCK, 0, pProto.LuckModifier()));
                }
                iDlg_Rewards dlg = new iDlg_Rewards(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.Message()],
                        pVisitor,
                        rew
                );
                dlg.DoModal();
            }
            if (pProto.MoraleModifier()) {
                pVisitor.Enchs().add(m_pProto.ProtoId() | PROVIDER.FLAG, FSK.MORALE, ENDUR.NEXTBATTLE, pProto.MoraleModifier());
            }
            if (pProto.LuckModifier()) {
                pVisitor.Enchs().add(m_pProto.ProtoId() | PROVIDER.FLAG, FSK.LUCK, ENDUR.NEXTBATTLE, pProto.LuckModifier());
            }
        }

        super.OnActivate(pVisitor, bActive);
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return pVisitor.Enchs().Registered(m_pProto.ProtoId() | PROVIDER.FLAG);
    }

}
