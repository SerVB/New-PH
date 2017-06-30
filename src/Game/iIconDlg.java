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

import newph.metric.iPoint;
import Common.metrics.iRect;
import Common.metrics.iSize;

/**
 * Base text with icon dialog MB_OK.
 */
public class iIconDlg extends iTextDlg {

    public iIconDlg(iViewMgr pViewMgr, final String title, final String text, SpriteId sid, int pid) {
        super(pViewMgr, title, text, pid);

        this.m_sid = sid;
    }

    @Override
    protected void DoCompose(final iRect clRect) {
        iRect rc = clRect;
        super.DoCompose(clRect);
        iSize ssiz = gGfxMgr.Dimension(m_sid);
        iPoint sa = gGfxMgr.Anchor(m_sid);
        rc.y = rc.y2() - 15 - 10 - ssiz.h;
        rc.x = rc.x + (rc.w/2 - ssiz.w/2);
        gGfxMgr.Blit(m_sid, gApp.Surface(), rc.point().minus(sa));
    }

    @Override
    protected final iSize ClientSize() {
        iSize res = super.ClientSize();
        iSize ssiz = gGfxMgr.Dimension(m_sid);
        res.h += ssiz.h + 10;
        return res;
    }

    protected SpriteId    m_sid;
}
