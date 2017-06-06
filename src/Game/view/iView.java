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

import Common.iPoint;
import Common.iRect;
import Common.iSize;
import Constants.view.ViewState;
import collections.simple.iViewList;
import utils.tracer;

/**
 *
 */
public class iView {

    public iView(iViewMgr pViewMgr, final iRect rect, int clsId, int uid, int state) {
        m_bNeedRedraw = true;
        m_bTracking = false;
        m_pParent = null;

        CreateView(pViewMgr, rect, clsId, uid, state);
    }

    public boolean AddChild(iView pChild) {
        pChild.m_pParent = this;
        m_Childs.add(pChild);
        return true;
    }

    public boolean RemoveChild(iView pChild) {
        if (pChild != null && m_Childs.remove(pChild)){
            pChild.m_pParent = null;
            return true;
        }
        return false;
    }

    public iView GetChildByPos(final iPoint pos) {
        // reverse find (in future remake to z-order)
        if (m_Childs.isEmpty()) {
            return null;
        }

        for (int xx = m_Childs.size()-1; xx >= 0; --xx) {
            if (
                    m_Childs.get(xx).m_bVisible &&
                    m_Childs.get(xx).m_bEnabled &&
                    m_Childs.get(xx).m_Rect.PtInRect(pos)
            ) {
                return m_Childs.get(xx);
            }
        }

        return null;
    }

    public iView GetChildById(int uid) {
        for (int xx = 0; xx < m_Childs.size(); ++xx) {
            if (m_Childs.get(xx).m_UID == uid) {
                return m_Childs.get(xx);
            }
        }
        return null;
    }

    // Message process
    public void Compose(iRect rect) {
        if (m_bVisible) {
            OnCompose();
            for (int xx=0; xx<m_Childs.size(); ++xx) {
                m_Childs.get(xx).Compose(rect);
            }
            if (m_bNeedRedraw) {
                rect.plus(GetScrRect());
                m_bNeedRedraw = false;
            }
        }
    }

    public boolean MouseDown(final iPoint pos) {
        if (!m_bEnabled) {
            // only topmost window can receive messages in disabled state
            tracer.check(m_pParent == null);
            m_pMgr.SetViewCapture(this);
            m_bTracking = true;
            return true;
        }

        iView vp = GetChildByPos(new iPoint(pos, '-', GetScrRect().point()));

        if (vp == null || !vp.MouseDown(pos)) {
            OnMouseDown(pos);
            m_pMgr.SetViewCapture(this);
            m_bTracking = true;
        }

        return true;
    }

    public boolean MouseUp(final iPoint pos) {
        if (m_bTracking) {
            m_bTracking = false;
            m_pMgr.ReleaseViewCapture();
            OnMouseUp(pos);
            if (GetScrRect().PtInRect(pos)) {
                OnMouseClick(pos);
            }
        } else {
            tracer.check(0);
        }

        return true;
    }

    public boolean MouseTrack(final iPoint pos) {
        if (m_bTracking && m_bEnabled) {
            OnMouseTrack(pos);
        }
        return true;
    }

    // Overrides
    public void OnTimer(int tid) {}
    public void OnCompose() {}
    public void OnRectChanged(final iRect rc) {}
    public void OnMouseDown(final iPoint pos) {}
    public void OnMouseUp(final iPoint pos) {}
    public void OnMouseClick(final iPoint pos) {}
    public void OnMouseTrack(final iPoint pos) {}

    // View metrics
    public void SetSize(final iSize newSize) {
        m_Rect.w = newSize.w;
        m_Rect.h = newSize.h;
        OnRectChanged(m_Rect);
    }

    public void SetPos(final iPoint newPos) {
        m_Rect.x = newPos.x;
        m_Rect.y = newPos.y;
        OnRectChanged(m_Rect);
    }

    public void SetRect(final iRect rect) {
        m_Rect = rect;
        OnRectChanged(m_Rect);
    }

    // inlines
    public iSize GetSize() {
        return m_Rect.size();
    }

    public iPoint GetPos() {
        return m_Rect.point();
    }

    public iRect GetRect() {
        return m_Rect;
   }

    public iPoint GetScrPos() {
        iPoint res = new iPoint(m_Rect.point());
        if (m_pParent != null) {
            res.plus(m_pParent.GetScrPos());
        }
        return res;
    }

    public iRect GetScrRect() {
        iRect res = new iRect(m_Rect);
        if(m_pParent != null) {
            res.plus(m_pParent.GetScrPos());
        }
        return res;
    }

    public iView GetParent() {
        return m_pParent;
    }

    public boolean IsEnabled() {
        return m_bEnabled && (m_pParent == null || (m_pParent != null && m_pParent.IsEnabled()));
    }

    public void SetEnabled(boolean bEnabled) {
        m_bEnabled = bEnabled;
        Invalidate();
    }

    public void SetEnabled() {
        SetEnabled(true);
    }

    public boolean IsVisible() {
        return m_bVisible;
    }

    public void SetVisible(boolean bVisible) {
        m_bVisible = bVisible;
        Invalidate();
    }

    public void SetVisible() {
        SetVisible(true);
    }

    public int GetClassId() {
        return m_clsId;
    }

    public int GetUID() {
        return m_UID;
    }

    public boolean NeedRedraw() {
        return m_bNeedRedraw;
    }

    public void Invalidate() {
        m_bNeedRedraw = true;
        if (m_pParent != null) {
            m_pParent.Invalidate();
        }
    }


    private boolean CreateView(iViewMgr pViewMgr, final iRect rect, int clsId, int uid, int state) {
        if (pViewMgr == null) {
            return false;
        }

        m_pMgr      = pViewMgr;
        m_bVisible  = (state & ViewState.Visible) > 0;
        m_bEnabled  = (state & ViewState.Enabled) > 0;
        m_Rect      = rect;
        m_clsId     = clsId;
        m_UID       = uid;

        return true;
    }

    private void Cleanup() {
        m_Childs.clear();
    }

    protected int                m_UID;
    protected int                m_clsId;
    protected iRect                m_Rect;

    protected boolean                m_bEnabled;
    protected boolean                m_bVisible;
    protected boolean                m_bTracking;
    protected boolean                m_bNeedRedraw;

    protected iViewMgr            m_pMgr;
    protected iView                m_pParent;
    protected iViewList            m_Childs;
}
