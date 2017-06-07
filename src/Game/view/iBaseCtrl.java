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
 * Base view-port based control.
 */
public class iBaseCtrl extends iView {

    /**
     * C-tor.
     * @param pViewMgr
     * @param pCmdHandler
     * @param rect
     * @param clsId
     * @param uid
     * @param state
     */
    public iBaseCtrl(iViewMgr pViewMgr, IViewCmdHandler pCmdHandler, final iRect rect, int clsId, int uid, int state) {
        super(pViewMgr, rect, clsId, uid, state);

        m_pCmdHandler = pCmdHandler;
    }

    /**
     * Sets Command Handler.
     * @param pCmdHandler Command Handler to be set.
     */
    public void SetCommandHandler(IViewCmdHandler pCmdHandler) {
            m_pCmdHandler = pCmdHandler;
    }

    /**
     * Command Handler.
     */
    protected IViewCmdHandler m_pCmdHandler;

}
