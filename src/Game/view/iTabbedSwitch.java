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

import newph.metric.iPoint;
import Common.metrics.iRect;
import Constants.view.*;
import newph.staticFunction.Tracer;

/**
 * Tabbed switch control.
 */
public abstract class iTabbedSwitch extends iBaseCtrl {

    /**
     * C-tor.
     * @param pViewMgr
     * @param pCmdHandler
     * @param rect
     * @param tabcnt
     * @param uid
     * @param state
     */
    public iTabbedSwitch(
            iViewMgr pViewMgr,
            IViewCmdHandler pCmdHandler,
            final iRect rect,
            int tabcnt,
            int uid,
            int state
    ) {
        super(pViewMgr, pCmdHandler, rect, VIEWCLSID.TABBED_SWITCH, uid, state);

        m_TabsCount = tabcnt;
        m_CurTab = 0;
        m_FocTab = -1;
        m_CurFocTab = -1;

        m_tabStates = new int[tabcnt];
        for (int xx=0; xx<tabcnt; ++xx)
            m_tabStates[xx] = 1;

        m_ItemWidth = m_Rect.w / m_TabsCount;

        Tracer.check(m_ItemWidth);
        Tracer.check((rect.w % m_TabsCount) == 0);
    }

    /**
     * C-tor.
     * @param pViewMgr
     * @param pCmdHandler
     * @param rect
     * @param tabcnt
     * @param uid
     */
    public iTabbedSwitch(
            iViewMgr pViewMgr,
            IViewCmdHandler pCmdHandler,
            final iRect rect,
            int tabcnt,
            int uid
    ) {
        this(pViewMgr, pCmdHandler, rect, tabcnt, uid, ViewState.Visible | ViewState.Enabled);
    }

    // Pure methods
    public abstract void ComposeTabItem(int idx, int itemState, final iRect rect);

    // inlines
    public void EnableTab(int idx, boolean bEnable) {
        Tracer.check(idx<m_TabsCount);
        m_tabStates[idx] = bEnable ? 1 : 0;
    }

    public void EnableTab(int idx) {
        EnableTab(idx, true);
    }

    public boolean IsTabEnabled(int idx) {
        Tracer.check(idx < m_TabsCount);
        return m_tabStates[idx] != 0;
    }

    public int GetTabsCount() {
        return m_TabsCount;
    }

    public void SetCurrentTab(int ntab) {
        Tracer.check(ntab<m_TabsCount);
        m_CurTab = ntab;
    }

    public int GetCurrentTab() {
        return m_CurTab;
    }

    public int GetFocusedTab() {
        return m_CurFocTab;
    }


    @Override
    public void OnMouseDown(final iPoint pos) {
        int ntab = GetTabByPos(new iPoint(pos, '-', GetScrRect().point()));

        if (ntab >= 0 && ntab < m_TabsCount && m_tabStates[ntab] != 0) {
            m_CurFocTab = m_FocTab = ntab;
        }

        Invalidate();
    }

    @Override
    public void OnMouseUp(final iPoint pos) {
        int tab = GetTabByPos(new iPoint(pos, '-', GetScrRect().point()));
        if (tab == m_FocTab){
            if (m_CurTab == m_FocTab) {
                if (m_pCmdHandler != null && IsEnabled()) {
                    m_pCmdHandler.iCMDH_ControlCommand(this, CCI.BTNCLICK, m_CurTab);
                }
            } else {
                m_CurTab = m_FocTab;
                if (m_pCmdHandler != null && IsEnabled()) {
                    m_pCmdHandler.iCMDH_ControlCommand(this, CCI.TABCHANGED, m_CurTab);
                }
            }

            Invalidate();
        }

        m_CurFocTab = m_FocTab = -1;
    }

    @Override
    public void OnMouseTrack(final iPoint pos) {
        int tab = GetTabByPos(new iPoint(pos, '-', GetScrRect().point()));

        if (tab != m_CurFocTab) {
            Invalidate();
        }

        if (tab == m_FocTab) {
            m_CurFocTab = tab;
        } else {
            m_CurFocTab = -1;
        }
    }

    @Override
    public void OnCompose() {
        iRect rc = GetScrRect();
        rc.w = m_ItemWidth;
        for (int xx = 0; xx < m_TabsCount; ++xx) {
            int state = 0;

            if (xx == GetFocusedTab()) {
                state |= ButtonState.Pressed;
            }
            if (xx == GetCurrentTab()) {
                state |= ButtonState.Selected;
            }
            if (m_tabStates[xx] == 0) {
                state |= ButtonState.Disabled;
            }

            ComposeTabItem(xx, state, rc);
            rc.x += m_ItemWidth;
        }
    }



    protected int GetTabByPos(final iPoint pos) {
        if (new iRect(m_Rect.size()).PtInRect(pos)) {
            return -1;
        } else {
            return (pos.x) / m_ItemWidth;
        }
    }

    protected int[] m_tabStates;
    protected int m_ItemWidth;
    protected int m_TabsCount;
    protected int m_CurTab;
    protected int m_CurFocTab;
    protected int m_FocTab;

}
