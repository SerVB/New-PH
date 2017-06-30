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

/**
 * Scroll Bar.
 */
public abstract class iScrollBar extends iBaseCtrl implements IViewCmdHandler {


    public iScrollBar(iViewMgr pViewMgr, IViewCmdHandler pCmdHandler, final iRect rect, int uid, int flags) {
        super(pViewMgr, pCmdHandler, rect, VIEWCLSID.SCROLL_BAR, uid, ViewState.Visible | ViewState.Enabled);

        m_flags = flags;
        m_totSiz = 0;
        m_pagSiz = 0;
        m_curPos = 0;
        m_bThumbTrack = false;

        if (IsHorizontal()) {
            super.AddChild(m_pUp = new iTBButton(
                    pViewMgr,
                    this,
                    new iRect(
                            0,
                            0,
                            GetDefBtnSiz(),
                            rect.h
                    ),
                    uid+1,
                    ViewState.Visible | ViewState.Enabled,
                    this,
                    EL.BtnLeft
            ));

            super.AddChild(m_pDown = new iTBButton(
                    pViewMgr,
                    this,
                    new iRect(
                            rect.w-GetDefBtnSiz(),
                            0,
                            GetDefBtnSiz(),
                            rect.h
                    ),
                    uid+2,
                    ViewState.Visible | ViewState.Enabled,
                    this,
                    EL.BtnRight
            ));
        } else {
            super.AddChild(m_pUp = new iTBButton(
                    pViewMgr,
                    this,
                    new iRect(
                            0,
                            0,
                            rect.w,
                            GetDefBtnSiz()
                    ),
                    uid+1,
                    ViewState.Visible | ViewState.Enabled,
                    this,
                    EL.BtnUp
            ));

            super.AddChild(m_pDown = new iTBButton(
                    pViewMgr,
                    this,
                    new iRect(
                            0,
                            rect.h-GetDefBtnSiz(),
                            rect.w,
                            GetDefBtnSiz()
                    ),
                    uid+2,
                    ViewState.Visible | ViewState.Enabled,
                    this,
                    EL.BtnDown
            ));
        }

        CalcThumbMetrix();
    }

    public iScrollBar(iViewMgr pViewMgr, IViewCmdHandler pCmdHandler, final iRect rect, int uid) {
        this(pViewMgr, pCmdHandler, rect, uid, 0);
    }

    public void SetMetrics(int totSiz, int pagSiz) {
        m_totSiz = totSiz;
        m_pagSiz = pagSiz;
        m_curPos = 0;

        CalcThumbMetrix();
    }

    public boolean SetCurPos(int nVal) {
        nVal = Math.max(0, Math.min(Math.max(0, m_totSiz-m_pagSiz), nVal));

        if (m_totSiz == 0 || nVal == m_curPos) {
            return false;
        }

        m_curPos = nVal;
        CalcThumbMetrix();

        return true;
    }

    public boolean PageUp() {
        if (SetCurPos(m_curPos-m_pagSiz)) {
            if (m_pCmdHandler != null) {
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.SBPAGEUP, m_curPos);
            }

            Invalidate();
            return true;
        }
        return false;
    }

    public boolean PageDown() {
        if (SetCurPos(m_curPos+m_pagSiz)) {
            if (m_pCmdHandler != null) {
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.SBPAGEDOWN, m_curPos);
            }
            Invalidate();
            return true;
        }
        return false;
    }

    public int CurPos() {
        return m_curPos;
    }

    public int MaxPos() {
        return m_totSiz - m_pagSiz;
    }

    public final boolean IsHorizontal() {
        return (m_flags & ScrollBarFlags.Horizontal) != 0;
    }

    public final int GetDefBtnSiz() {
        return 19;
    }

    // Pure methods
    public abstract void ComposeSBElement(int el, final iRect rc, int flags);


    @Override
    public void OnMouseDown(final iPoint pos) {
        int res = HitTest(new iPoint(pos, '-', GetScrRect().point()));

        switch (res) {
            case HTR.Thumb:
                m_bThumbTrack = true;
                m_trackAnchor = pos;
                m_trackPos = m_curPos;
                Invalidate();
                break;
            case HTR.PgUp:
                if (PageUp()) {
                    Invalidate();
                }
                break;
            case HTR.PgDown:
                if (PageDown()) {
                    Invalidate();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void OnMouseUp(final iPoint pos) {
        m_bThumbTrack = false;

        Invalidate();
    }

    @Override
    public void OnMouseTrack(final iPoint pos) {
        if (m_bThumbTrack) {
            int n;
            if (IsHorizontal()) {
                n = pos.x - m_trackAnchor.x;
            } else {
                n = pos.y - m_trackAnchor.y;
            }
            if (SetCurPos(m_trackPos + NItems(n))) {
                if (m_pCmdHandler != null) {
                    m_pCmdHandler.iCMDH_ControlCommand(this, CCI.SBTRACKING, m_curPos);
                }

                Invalidate();
            }
        }
    }

    @Override
    public void OnCompose() {
        iRect rc = new iRect(GetScrRect());
        if (IsHorizontal()){
            ComposeSBElement(
                    EL.Bkgnd,
                    new iRect(
                            rc.x+m_thumbMetrix.es,
                            rc.y,
                            m_thumbMetrix.h,
                            rc.h
                    ),
                    0
            );

            ComposeSBElement(
                    EL.Thumb,
                    new iRect(
                            rc.x+m_thumbMetrix.es+m_thumbMetrix.s1,
                            rc.y,
                            m_thumbMetrix.th,
                            rc.h
                    ),
                    (m_bEnabled ? 0 : ButtonState.Disabled) | (m_bThumbTrack ? ButtonState.Pressed : 0)
            );
        } else {
            ComposeSBElement(
                    EL.Bkgnd,
                    new iRect(
                            rc.x,
                            rc.y+m_thumbMetrix.es,
                            rc.w,
                            m_thumbMetrix.h
                    ),
                    0
            );

            ComposeSBElement(
                    EL.Thumb,
                    new iRect(
                            rc.x,
                            rc.y+m_thumbMetrix.es+m_thumbMetrix.s1,
                            rc.w,
                            m_thumbMetrix.th
                    ),
                    (m_bEnabled ? 0 : ButtonState.Disabled) | (m_bThumbTrack ? ButtonState.Pressed : 0)
            );
        }
    }


    protected int HitTest(final iPoint pos) {
        int val;
        if (IsHorizontal()) {
            val = pos.x - m_thumbMetrix.es;
        } else {
            val = pos.y - m_thumbMetrix.es;
        }

        if (val < m_thumbMetrix.s1) {
            return HTR.PgUp;
        }

        val -= m_thumbMetrix.s1;
        if (val < m_thumbMetrix.th) {
            return HTR.Thumb;
        }

        return HTR.PgDown;
    }

    protected int ItemsHeight(int icnt) {
        return (m_thumbMetrix.h * icnt)/m_totSiz;
    }

    protected int NItems(int n) {
        return (m_totSiz * n)/m_thumbMetrix.h;
    }

    protected final void CalcThumbMetrix() {
        if (IsHorizontal()){
            m_thumbMetrix.es = GetDefBtnSiz();
            m_thumbMetrix.h = m_Rect.w - m_thumbMetrix.es*2;
        } else {
            m_thumbMetrix.es = GetDefBtnSiz();
            m_thumbMetrix.h = m_Rect.h - m_thumbMetrix.es*2;
        }

        if (m_totSiz == 0) {
            m_thumbMetrix.th = m_thumbMetrix.h;
            m_thumbMetrix.s1 = m_thumbMetrix.s2 = 0;
        } else {
            m_thumbMetrix.th = Math.max(ItemsHeight(Math.min(m_pagSiz, m_totSiz)),m_thumbMetrix.es);
            if (m_curPos == 0) {
                m_thumbMetrix.s1 = 0;
            }
            else {
                int fpx = m_thumbMetrix.h - m_thumbMetrix.th;
                m_thumbMetrix.s1 = (fpx*m_curPos)/(m_totSiz-m_pagSiz);
            }
            m_thumbMetrix.s2 = m_thumbMetrix.h - m_thumbMetrix.th - m_thumbMetrix.s1;
        }
    }

    @Override
    public void iCMDH_ControlCommand(iView pView, int cmd, int param) {
        int uid = pView.GetUID();
        if (uid == m_UID+1) {
            // Scroll up
            if (SetCurPos(m_curPos-1)) {
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.SBLINEUP, m_curPos);
            }
        } else if (uid == m_UID+2) {
            // Scroll down
            if (SetCurPos(m_curPos+1)) {
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.SBLINEDOWN, m_curPos);
            }
        }
    }

    protected class ThumbMetrix {
        public int es, h, s1, th, s2;
    }

    protected int                m_flags;
    protected boolean                m_bThumbTrack;
    protected iPoint                m_trackAnchor;
    protected int                m_trackPos;

    protected ThumbMetrix            m_thumbMetrix;
    protected int                m_totSiz;
    protected int                m_pagSiz;
    protected int                m_curPos;
    protected iTBButton            m_pUp;
    protected iTBButton            m_pDown;

}
