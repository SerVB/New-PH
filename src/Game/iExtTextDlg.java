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
package Game;

import Common.iRect;
import Common.iSize;
import Constants.*;
import ConstantsGame.*;

/**
 * Extended text dialog MB_OK.
 */
public class iExtTextDlg extends iBaseGameDlg {

    public iExtTextDlg(iViewMgr pViewMgr, final String title, final String text, final String sign, int pid) {
        super(pViewMgr, pid);

        this.m_title = title;
        this.m_text = text;
        this.m_sign = sign;
    }

    public void OnCreateDlg() {
        iRect clRect = ClientRect();
        AddChild(new iTextButton(m_pMgr,this,new iRect(clRect.x+(clRect.w/2-20),clRect.y2()-DEF.BTN_HEIGHT,40,DEF.BTN_HEIGHT),TRID.OK, DRC.OK));
    }

    @Override
    protected void DoCompose(final iRect clRect) {
        iRect rc = clRect;

        if (!m_title.isEmpty()) {
            int h = gTextComposer.TextBoxOut(dlgfc_hdr, gApp.Surface(), m_title, rc);
            rc.y += h;
            rc.y += 10;
        }

        if (!m_text.isEmpty()) {
            int h = gTextComposer.TextBoxOut(dlgfc_plain, gApp.Surface(), m_text, rc);
            rc.y += h;
            h += 10;
        }

        if (!m_sign.isEmpty()) {
            rc.y += 5;
            int h = gTextComposer.TextBoxOut(dlgfc_topic, gApp.Surface(), m_sign, rc);
            rc.y += h;
            h += 5;
        }
    }

    @Override
    protected final iSize ClientSize() {
        int w = 130;
        if (m_text.length() > 20) {
            w += Math.max(0, Math.min(70, m_text.Length()/4));
        }
        int h = 0;

        // title
        if (!m_title.isEmpty()) {
            iSize s = gTextComposer.GetTextBoxSize(m_title, w, dlgfc_hdr);
            h += s.h;
            h += 10;
        }

        // text
        if (!m_text.isEmpty()) {
            iSize s = gTextComposer.GetTextBoxSize(m_text, w, dlgfc_plain);
            h += s.h;
            h += 10;
        }

        // sign
        if (!m_text.isEmpty()) {
            iSize s = gTextComposer.GetTextBoxSize(m_sign, w, dlgfc_topic);
            h += s.h;
            h += 10;
        }

        // ok button
        h += DEF.BTN_HEIGHT;

        return new iSize(w, h);
    }


    private void iCMDH_ControlCommand(iView pView, int cmd, int param) {
        int uid = pView.GetUID();
        if (cmd == CCI.BTNCLICK) {
            EndDialog(uid);
        }
    }

    protected String    m_title;
    protected String    m_text;
    protected String    m_sign;

}
