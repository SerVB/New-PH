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

import Constants.view.CCI;
import Constants.DRC;
import Common.metrics.iRect;
import Common.metrics.iSize;
import Constants.*;

/**
 * Base text dialog MB_OK.
 */
public class iTextDlg extends iBaseGameDlg {

    public iTextDlg(iViewMgr pViewMgr, final String title, final String text, int pid, iTextComposer.FontConfig fc_title, iTextComposer.FontConfig fc_text) {
        super(pViewMgr, pid);

        this.m_title = title;
        this.m_text = text;
        this.m_fcTitle = fc_title;
        this.m_fcText = fc_text;
    }

    public iTextDlg(iViewMgr pViewMgr, final String title, final String text, int pid, iTextComposer.FontConfig fc_title) {
        this(pViewMgr, title, text, pid, fc_title, dlgfc_plain);
    }

    public iTextDlg(iViewMgr pViewMgr, final String title, final String text, int pid) {
        this(pViewMgr, title, text, pid, dlgfc_hdr);
    }

    public void OnCreateDlg() {
        iRect clRect = ClientRect();
        AddChild(new iTextButton(m_pMgr,this,new iRect(clRect.x+(clRect.w/2-20),clRect.y2()-DEF.BTN_HEIGHT,40,DEF.BTN_HEIGHT),TRID.OK, DRC.OK));
    }

    public void SetText(final String text) {
        m_text = text;
    }

    @Override
    protected void DoCompose(final iRect clRect) {
        iRect rc = clRect;

        if (!m_title.isEmpty()) {
            int h = gTextComposer.TextBoxOut(m_fcTitle, gApp.Surface(), m_title, rc);
            rc.y += h;
            rc.y += 10;
        }

        if (!m_text.isEmpty()) {
            int h = gTextComposer.TextBoxOut(m_fcText, gApp.Surface(), m_text, rc);
            rc.y += h;
            h += 10;
        }
    }

    @Override
    protected iSize ClientSize() {
        int w = 130;
        if (m_text.length() > 20) {
            w += Math.max(0, Math.min(70, m_text.length()/4));
        }
        int h = 0;

        // title
        if (!m_title.isEmpty()) {
            iSize s = gTextComposer.GetTextBoxSize(m_title, w, m_fcTitle);
            h += s.h;
            h += 10;
        }

        // text
        if (!m_text.isEmpty()) {
            iSize s = gTextComposer.GetTextBoxSize(m_text, w, m_fcText);
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


    protected iTextComposer.FontConfig m_fcTitle;
    protected iTextComposer.FontConfig m_fcText;
    protected String    m_title;
    protected String    m_text;
}
