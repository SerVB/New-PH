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
package Game.view;

import Common.metrics.iPoint;
import Common.metrics.iRect;
import Constants.view.*;
import utils.tracer;

/**
 * List Box (virtual).
 */
public abstract class iListBox extends iBaseCtrl implements IViewCmdHandler {

    public iListBox(iViewMgr pViewMgr, IViewCmdHandler pCmdHandler, final iRect rect, int uid) {

        super(pViewMgr, pCmdHandler, rect, VIEWCLSID.LIST_BOX, uid, ViewState.Visible | ViewState.Enabled);

        m_pScrollBar = null;
        m_selItem = -1;
        m_scrVal = 0;
        m_lcTime = 0;
        m_lcIdx = -1;

    }

    public void SetScrollBar(iScrollBar pScrollBar) {
        m_pScrollBar = pScrollBar;
        if (m_pScrollBar != null) {
            m_pScrollBar.SetCommandHandler(this);
            m_pScrollBar.SetMetrics(LBItemsCount(), PageSize());
            m_pScrollBar.SetCurPos(m_scrVal);
        }
    }

    public void UpdateListBox() {
        m_scrVal = 0;
        m_pScrollBar.SetMetrics(LBItemsCount(), PageSize());
        m_pScrollBar.SetCurPos(m_scrVal);

        if (LBItemsCount() != 0) {
            m_selItem = 0;
        } else {
            m_selItem = -1;
        }
        if (m_pCmdHandler != null) {
            m_pCmdHandler.iCMDH_ControlCommand(this, CCI.LBSELCHANGED, m_selItem);
        }

        Invalidate();
    }

    public void SetCurPos(int sval) {
        m_scrVal = sval;
    }

    public void SetCurSel(int idx, boolean bNotify) {
        tracer.check(idx == -1 || IsValidIdx(idx));
        if (idx != m_selItem) {
            m_selItem = idx;
            if (bNotify && m_pCmdHandler != null) {
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.LBSELCHANGED, m_selItem);
            }
            if (idx != -1) {
                if (m_selItem < m_scrVal) {
                    if (m_pScrollBar != null) {
                        m_pScrollBar.SetCurPos(m_selItem);
                    }
                    SetCurPos(m_selItem);
                } else if (m_selItem > (m_scrVal+PageSize()-1) ) {
                    int npos = m_selItem - PageSize() + 1;
                    if (m_pScrollBar != null) {
                        m_pScrollBar.SetCurPos(npos);
                    }
                    SetCurPos(npos);
                }
            }
            Invalidate();
        }
    }

    public void SelPrev() {
        if (m_selItem > 0) {
            SetCurSel(m_selItem - 1, true);
        }
    }

    public void SelNext() {
        if ((m_selItem + 1) < LBItemsCount()) {
            SetCurSel(m_selItem + 1, true);
        }
    }

    public int SelItem() {
        return m_selItem;
    }

    public int PageSize() {
        return m_Rect.h / LBItemHeight();
    }


    // Pure methods
    public abstract void ComposeLBBackground(final iRect rect);
    public abstract void ComposeLBItem(int iIdx, boolean bSel, final iRect irc);
    public abstract int LBItemHeight();
    public abstract int LBItemsCount();

    @Override
    public void OnMouseDown(final iPoint pos) {
        int nIdx = IdxByPos(pos);
        if (!IsValidIdx(nIdx)) {
            nIdx = -1;
        }
        SetCurSel(nIdx, true);
    }

    @Override
    public void OnMouseClick(final iPoint pos) {
        if (m_pCmdHandler != null) {
            long nt = System.currentTimeMillis();
            if ( m_lcTime != 0 && nt > m_lcTime && (nt-m_lcTime) < 500 && m_selItem != -1 && m_lcIdx == m_selItem) {
                m_lcTime = 0;
                m_lcIdx = -1;
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.LBSELDBLCLICK, m_selItem);
            } else {
                m_lcTime = nt;
                m_lcIdx = m_selItem;
            }
        }
    }

    @Override
    public void OnCompose() {
        iRect rc = GetScrRect();
        ComposeLBBackground(rc);
        int ih = LBItemHeight();
        int ic = LBItemsCount();
        int ps = PageSize();
        iRect irc = new iRect(rc.x, rc.y, rc.w, ih);
        for (int xx = m_scrVal; xx < Math.min(ic, m_scrVal+ps); ++xx){
            ComposeLBItem(xx, xx==m_selItem, irc);
            irc.y += ih;
        }
    }

    @Override
    public void iCMDH_ControlCommand(iView pView, int cmd, int param) {
        tracer.check(m_pScrollBar != null && m_pScrollBar == pView);
        SetCurPos(param);
    }


    protected boolean IsValidIdx(int idx) {
        return idx < LBItemsCount();
    }

    protected int IdxByPos(final iPoint pos) {
        return m_scrVal + (pos.y-GetScrRect().y)/LBItemHeight();
    }

    protected iScrollBar    m_pScrollBar;
    protected int        m_scrVal;
    protected int        m_selItem;
    protected long        m_lcTime;
    protected int        m_lcIdx;

}
