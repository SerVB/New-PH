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

import Common.metrics.iRect;

/**
 * Skroll Bar.
 */
public class iTBButton extends iButton {

    /**
     *
     * @param pViewMgr
     * @param pCmdHandler
     * @param rect
     * @param uid
     * @param state
     * @param pScrollBar
     * @param el EL.
     */
    public iTBButton(
            iViewMgr pViewMgr,
            IViewCmdHandler pCmdHandler,
            final iRect rect,
            int uid,
            int state,
            iScrollBar pScrollBar,
            int el
    ) {
        super(pViewMgr, pCmdHandler, rect, uid, state);
        m_pScrollBar = pScrollBar;
        m_el = el;
    }

    @Override
    public void OnCompose() {
        m_pScrollBar.ComposeSBElement(m_el, GetScrRect(), GetButtonState());
    }


    private final int        m_el;
    private final iScrollBar m_pScrollBar;

}
