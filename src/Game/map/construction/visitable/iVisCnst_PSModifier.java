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

import newph.core.metric.iPoint;
import Constants.*;
import Constants.RIT;
import Game.iHero;
import collections.simple.iRewardsCtr;

/**
 *
 */
public class iVisCnst_PSModifier extends iVisCnst {

    public iVisCnst_PSModifier(iVisCnstT_PSModifier pProto, final iPoint pos) {
        super(pProto, pos, true);
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
            iVisCnstT_PSModifier pPSModifier = DynamicCast<iVisCnstT_PSModifier>(m_pProto);
            tracer.check(pPSModifier != null);
            if (bActive) {
                iRewardsCtr rew = new iRewardsCtr();
                for (int psid = 0; psid<PRSKILL.COUNT; ++psid) {
                    if (pPSModifier.PSModifier().val[psid]) {
                        rew.add(iRewardItem(RIT.FURTSKILL, FSK.ATTACK + psid, pPSModifier.PSModifier().val[psid]));
                    }
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
            pVisitor.ConstFurtSkills() += pPSModifier.PSModifier();
        }

        super.OnActivate(pVisitor, bActive);
    }

}
