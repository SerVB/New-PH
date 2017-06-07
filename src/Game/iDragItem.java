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

import Common.metrics.iPoint;
import Common.iCreatGroup;
import Common.metrics.iRect;
import Common.metrics.iSize;
import Constants.*;

/**
 *
 */
public class iDragItem extends IDragGlyph {

    public iDragItem(
        iArmyListEx pSender,
        int fromCell,
        boolean bSplit,
        final iCreatGroup cGroup,
        final iPoint pos,
        final iSize siz,
        boolean bCanDismiss
    ) {
        this.m_pSender = pSender;
        this.m_fromCell = fromCell;
        this.m_bSplit = bSplit;
        this.m_cGroup = cGroup;
        this.m_pos = pos;
        this.m_siz = siz;
        this.m_bCanDismiss = bCanDismiss;
        this.m_pReceiver = null;
        this.m_toCell = -1;
    }

    public void ComposeDragGlyph() {
        iRect rc = new iRect(m_pos.x - m_siz.w / 2, m_pos.y - m_siz.h / 2, m_siz.w, m_siz.h);

        ComposeCreatureCell(gApp.Surface(), rc, m_cGroup.Type(), m_cGroup.Count(), VL.EXPERT);

        if (m_pReceiver == null || m_toCell == -1)
            gApp.Surface().FillRect(rc, COLOR.RED, 32);
        else
            gApp.Surface().FillRect(rc, COLOR.GREEN, 32);
    }

    public boolean     m_bCanDismiss;
    public boolean     m_bSplit;
    public iSize       m_siz;
    public iArmyListEx m_pSender;
    public int         m_fromCell;
    public iArmyListEx m_pReceiver;
    public int         m_toCell;
    public iCreatGroup m_cGroup;
    public iPoint      m_pos;
}
