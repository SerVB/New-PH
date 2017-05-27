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

/**
 * Generic GameView
 * Java не поддерживает мультинаследование!
 */
public class iChildGameView extends iTopmostView {
//class iChildGameView : public iTopmostView, public IViewCmdHandler

    public iChildGameView(boolean bEternal, int cvParentView) {
        super(gApp.ViewMgr()); // iTopmostView(&gApp.ViewMgr())
        m_bEternal   = bEternal;
        m_cvParentView = cvParentView;
    }

    public boolean Eternal() {
        return m_bEternal;
    }

    int cvParentView() {
        return m_cvParentView;
    }

    boolean Process(long t) {
        return true;
    }

    void OnActivate(boolean bActivate) {

    }

    private int           m_cvParentView;
    private boolean       m_bEternal;
}
