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

import Common.metrics.iPoint;
import Game.iHero;
import utils.tracer;

/**
 *
 */
public class iVisCnst_Obelisk extends iVisCnst {

    public iVisCnst_Obelisk(iVisCnstT_Obelisk pProto, final iPoint pos) {
        super(pProto, pos, false);
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        tracer.check(!Visited(pVisitor));
        int opened = pVisitor.Owner().OpenObelisk();
        m_Visitors.add(pVisitor.Owner().PlayerId());
        if (bActive) {
            iPuzzleMapDlg pmdlg = new iPuzzleMapDlg(
                    gApp.ViewMgr(),
                    pVisitor.Owner(),
                    opened
            );
            pmdlg.DoModal();
        } else {
            iAI_Player pOwner = DynamicCast<iAI_Player>(pVisitor.Owner());
            tracer.check(pOwner != null);
            pOwner.UpdateUltSeekPoint();
        }
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        for (int visitor : m_Visitors) {
            if (pVisitor.Owner() == null || visitor == pVisitor.Owner().PlayerId()) {
                return true;
            }
        }
        return false;
    }

}
