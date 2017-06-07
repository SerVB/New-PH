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

import Constants.DLG;
import Common.metrics.iRect;
import Common.metrics.iSize;

/**
 * Base game dialog.
 */
public class iBaseGameDlg extends iDialog implements IViewCmdHandler {

    public iBaseGameDlg(iViewMgr pViewMgr, int pid) {
        super(pViewMgr);
        this.m_pid = pid;
    }

    public final iRect ClientRect() {
        iRect rc = new iRect(GetDlgMetrics());
        rc.DeflateRect(DLG.FRAME_SIZE);
        return rc;
    }

    public final iSize GetMaxClientSize() {
        iSize res = gApp.Surface().GetSize();

        res.w -= DLG.FRAME_SIZE * 2;
        res.h -= DLG.FRAME_SIZE * 2;

        return res;
    }

    // pure virtuals
    public abstract void DoCompose(final iRect clRect);
    public abstract iSize ClientSize();


    private void OnCompose() {
        ComposeDlgBkgnd(gApp.Surface(), m_Rect, m_pid, true);
        iRect rc = m_Rect;
        rc.DeflateRect(DLG.FRAME_SIZE);
        DoCompose(rc);
    }

    private iSize GetDlgMetrics() {
        iSize siz = ClientSize();
        iSize max = gApp.Surface().GetSize();

        siz.w = Math.max(80, Math.min(max.w - 6, siz.w+DLG.FRAME_SIZE*2));
        siz.h = Math.max(40, Math.min(max.h - 6, siz.h+DLG.FRAME_SIZE*2));

        return siz;
    }

    protected int    m_pid;
}
