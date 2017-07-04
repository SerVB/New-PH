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

import newph.core.metric.iPoint;
import Common.metrics.iRect;
import Common.metrics.iSize;
import collections.simple.iDialogStack;
import collections.simple.iTimerList;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iViewMgr {

    public iViewMgr(iGXApp pApp) {
        m_pApp = pApp;
        m_Surf = pApp.Display().GetSurface();
        m_pCurView = null;
        m_pCapView = null;
        m_pPopupView = null;
        m_timerCounter = 0;
    }

    public boolean ProcessMessage(final iInput.iEntry msg) {
        if (m_pPopupView) {
            // In case of popup view
            switch(msg.taskType) {
                case iInput.iEntry.MouseMove:
                    if (m_pCapView != null) {
                        m_pCapView.MouseTrack(new iPoint(msg.px,msg.py));
                    } else {
                        m_pPopupView.MouseTrack(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.MouseDown:
                    if (m_pCapView != null) {
                        m_pCapView.MouseDown(new iPoint(msg.px,msg.py));
                    } else {
                        if (!m_pPopupView.GetRect().PtInRect(msg.px,msg.py)) {
                            m_pPopupView.HidePopup();
                            if (m_pCurView != null) {
                                m_pCurView.Invalidate();
                            }
                        } else {
                            m_pPopupView.MouseDown(new iPoint(msg.px,msg.py));
                        }
                    }
                    break;
                case iInput.iEntry.MouseUp:
                    if (m_pCapView != null) {
                        m_pCapView.MouseUp(new iPoint(msg.px,msg.py));
                    } else {
                        if (m_pPopupView.IsVisible()) {
                            m_pPopupView.MouseUp(new iPoint(msg.px,msg.py));
                        }
                        else {
                            HidePopup();
                        }
                    }
                    break;
                case iInput.iEntry.KeyDown:
                    m_pApp.KeyDown(msg.key);
                    break;
                case iInput.iEntry.KeyUp:
                    m_pApp.KeyUp(msg.key);
                    break;
            }
        } else if (!m_dlgStack.isEmpty()) {
            // In case of dialog stack
            iDialog pDlg = m_dlgStack.get(m_dlgStack.size()-1);
            switch(msg.taskType) {
                case iInput.iEntry.MouseMove:
                    if (m_pCapView != null) {
                        m_pCapView.MouseTrack(new iPoint(msg.px,msg.py));
                    } else {
                        pDlg.MouseTrack(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.MouseDown:
                    if (m_pCapView != null) {
                        m_pCapView.MouseDown(new iPoint(msg.px,msg.py));
                    } else {
                        pDlg.MouseDown(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.MouseUp:
                    if (m_pCapView != null) {
                        m_pCapView.MouseUp(new iPoint(msg.px,msg.py));
                    } else {
                        pDlg.MouseUp(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.KeyDown:
                    if (!pDlg.KeyDown(msg.key)) {
                        m_pApp.KeyDown(msg.key);
                    }
                    break;
                case iInput.iEntry.KeyUp:
                    if (!pDlg.KeyUp(msg.key)) {
                        m_pApp.KeyUp(msg.key);
                    }
                    break;
            }
        } else if (m_pCurView != null) {
            // In general case
            switch(msg.taskType) {
                case iInput.iEntry.MouseMove:
                    if (m_pCapView != null) {
                        m_pCapView.MouseTrack(new iPoint(msg.px,msg.py));
                    } else {
                        m_pCurView.MouseTrack(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.MouseDown:
                    if (m_pCapView != null) {
                        m_pCapView.MouseDown(new iPoint(msg.px,msg.py));
                    } else {
                        m_pCurView.MouseDown(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.MouseUp:
                    if (m_pCapView != null) {
                        m_pCapView.MouseUp(new iPoint(msg.px,msg.py));
                    } else {
                        m_pCurView.MouseUp(new iPoint(msg.px,msg.py));
                    }
                    break;
                case iInput.iEntry.KeyDown:
                    if (!m_pCurView.KeyDown(msg.key)) {
                        m_pApp.KeyDown(msg.key);
                    }
                    break;
                case iInput.iEntry.KeyUp:
                    if (!m_pCurView.KeyUp(msg.key)) {
                        m_pApp.KeyUp(msg.key);
                    }
                    break;
            }
        } else {
            //
            switch(msg.taskType) {
                case iInput.iEntry.KeyDown:
                    m_pApp.KeyDown(msg.key);
                    break;
                case iInput.iEntry.KeyUp:
                    m_pApp.KeyUp(msg.key);
                    break;
            }
        }

        return true;
    }

    public void Compose(iRect rect) {
        // Compose current topmost view
        if (m_pCurView != null && m_pCurView.NeedRedraw()) {
            m_pCurView.Compose(rect);
        }

        // Compose dialog stack
        for (iDialog dlg : m_dlgStack) {
            dlg.Compose(rect);
        }

        // Compose Popup view
        if (m_pPopupView) {
            m_pPopupView.Compose(rect);
        }

        // Compose drag'n'drop glyph
        if (m_pDragGlyph != null) {
            m_pDragGlyph.ComposeDragGlyph();
        }
    }

    public iGXApp App() {
        return m_pApp;
    }

    public iDib Surface() {
        return m_Surf;
    }

    public iSize Metrics() {
        return m_Surf.GetSize();
    }

    public void SetCurView(iTopmostView pCurView) {
        m_pCurView = pCurView;
    }

    public iView CurView() {
        return (iView) m_pCurView;
    }

    // Capture view
    public void SetViewCapture(iView pCapView) {
        Tracer.check(m_pCapView == null);
        m_pCapView = pCapView;
    }

    public iView ReleaseViewCapture() {
        Tracer.check(m_pCapView != null);
        iView nView = m_pCapView;
//        m_pCapView = NULL;
        return nView;
    }

    public iView CapturedView() {
        return m_pCapView;
    }

    // Modal stack
    public boolean HasModalDlg() {
        return !m_dlgStack.isEmpty();
    }

    public void PushModalDlg(iDialog pDlg) {
        m_dlgStack.add(pDlg);
    }

    public iDialog PopModalDlg() {
        if (m_pCurView != null) {
            m_pCurView.Invalidate();
        }
        return m_dlgStack.remove(m_dlgStack.size() - 1);
    }

    // Popup windows
    public void TrackPopup(iPopupView pPopupView, final iPoint pos, final iRect bound, Alignment al) {
        Tracer.check(!m_pPopupView && pPopupView);
        m_pPopupView = pPopupView;
        m_pPopupView.TrackPopup(pos, bound, al);
    }

    public void HidePopup() {
        Tracer.check(m_pPopupView != null);
        m_pPopupView = null;
        if (m_pCurView != null) {
            m_pCurView.Invalidate();
        }
    }

    // Drag'n'Drop glyph
    public void SetDragGlyph(IDragGlyph pDragGlyph) {
        m_pDragGlyph = pDragGlyph;
    }

    public IDragGlyph DragGlyph() {
        return m_pDragGlyph;
    }

    // Timer processing
    public void Process(int interval) {
        if (!m_timers.isEmpty()) {
            m_timerCounter += interval;
            while (!m_timers.isEmpty() && m_timers.get(m_timers.size()-1).timer <= m_timerCounter) {
                iTimerHandler item = m_timers.remove(m_timers.size()-1);
                item.pHandler.OnTimer(item.tid);
            }
        }
    }

    public void SetTimer(int period, int tid, iView pHandler) {
        if (m_timers.isEmpty()) {
            // reset timer counter
            m_timerCounter = 0;
            m_timers.add(new iTimerHandler(period, tid, pHandler));
        } else if (m_timers.get(m_timers.size()-1).timer >= (period+m_timerCounter) ) {
            m_timers.add(new iTimerHandler(period+m_timerCounter, tid, pHandler));
        } else {
            int ntimer = period+m_timerCounter;
            for (int xx = 0; xx < m_timers.size(); ++xx) {
                if (ntimer >= m_timers.get(xx).timer) {
                    m_timers.add(xx, new iTimerHandler(ntimer, tid, pHandler));
                    break;
                }
            }
        }
    }

    public void CleanupTimers(iView pHandler) {
        if (pHandler == null) {
            m_timers.clear();
        } else {
            for (int xx = 0; xx < m_timers.size();){
                if (m_timers.get(xx).pHandler == pHandler) {
                    m_timers.remove(xx);
                } else {
                    ++xx;
                }
            }
        }
    }

    public void CleanupTimers() {
        CleanupTimers(null);
    }

    private iDib                m_Surf;
    private iGXApp                m_pApp;
    private iTopmostView        m_pCurView;
    private iView                m_pCapView;
    private IDragGlyph            m_pDragGlyph;
    private iPopupView            m_pPopupView;

    private iDialogStack    m_dlgStack;

    private iTimerList    m_timers;
    private int        m_timerCounter;
}
