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

/**
 * Generic push putton.
 */
public class iButton extends iBaseCtrl {

    /**
     * C-tor.
     * @param pViewMgr
     * @param pCmdHandler Command Handler.
     * @param rect
     * @param uid
     * @param state
     */
    public iButton(iViewMgr pViewMgr, IViewCmdHandler pCmdHandler, final iRect rect, int uid, int state) {
        super(pViewMgr, pCmdHandler, rect, VIEWCLSID.PUSH_BUTTON, uid, state);

        m_lastClick = 0;
        m_state = 0;
    }

    /**
     * Returns Button's State.
     * @return Button's State.
     */
    public int GetButtonState() {
        return m_state | ( IsEnabled() ? 0 : ButtonState.Disabled );
    }


    @Override
    public void OnMouseDown(final iPoint pos) {
        m_state |= ButtonState.Pressed;

        OnBtnDown();
        Invalidate();
    }

    @Override
    public void OnMouseUp(final iPoint pos) {
        if ((m_state & ButtonState.Pressed) != 0) {
            m_state ^= ButtonState.Pressed;

            OnBtnUp();
            Invalidate();
        }
    }

    @Override
    public void OnMouseClick(final iPoint pos) {
        if (m_pCmdHandler != null && IsEnabled()) {
            long nt = System.currentTimeMillis();
            if ( m_lastClick != 0 && nt > m_lastClick && (nt-m_lastClick) < 500) {
                m_lastClick = 0;
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.BTNDBLCLICK, 0);
            } else {
                m_lastClick = nt;
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.BTNCLICK, 0);
            }
        }
    }

    @Override
    public void OnMouseTrack(final iPoint pos) {
        if (GetScrRect().PtInRect(pos)) {
            if ( (m_state & ButtonState.Pressed) != ButtonState.Pressed) {
                m_state |= ButtonState.Pressed;

                Invalidate();
            }
        } else if ((m_state & ButtonState.Pressed) != 0) {
            m_state ^= ButtonState.Pressed;

            Invalidate();
        }
}


    protected void OnBtnDown() {}
    protected void OnBtnUp() {}

    protected int m_state;

    /**
     * Millis of last click.
     */
    protected long m_lastClick;
}
