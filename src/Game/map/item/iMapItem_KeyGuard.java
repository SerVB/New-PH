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
import Constants.DRC;
import Common.iDynamicBuffer;
import newph.core.metric.iPoint;
import Constants.*;
import Game.iHero;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iMapItem_KeyGuard extends iMapItem {

    public iMapItem_KeyGuard(final iPoint pos, int key) {
        super(pos, MAPITEM.KEYGUARD, PDGG.KEY_GUARDS+key);
        this.m_key = key;
    }

    public int GetKeyType() {
        return m_key;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        if (bActive) {
            if (pHero.Owner().HasKey(m_key)) {
                iQuestDlg dlg = new iQuestDlg(
                        gApp.ViewMgr(),
                        gTextMgr[TRID.MAPRES_KEYGUARD],
                        gTextMgr[TRID.MAPRES_KEYGUARD_OK_MSG],
                        pHero.Owner().PlayerId()
                );
                if (dlg.DoModal() == DRC.YES) {
                    gSfxMgr.PlaySound(CSND.DEL_GUARD);
                    return true;
                }
            } else {
                iTextDlg dlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[TRID.MAPRES_KEYGUARD],
                        gTextMgr[TRID.MAPRES_KEYGUARD_NOPASS_MSG],
                        pHero.Owner().PlayerId()
                );
                dlg.DoModal();
                return false;
            }
        } else {
            Tracer.check(pHero.Owner().HasKey(m_key));
            if (pHero.Owner().HasKey(m_key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_key);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_KEYGUARD;
    }

//    public IMPL_TYPEAWARE( iMapItem_KeyGuard );

    private final int   m_key;
}
