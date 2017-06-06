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

import Constants.view.*;

/**
 *
 */
public class iTopmostView extends iView {

    // c-tor, d-tor
    public iTopmostView(iViewMgr pViewMgr) {
        super(pViewMgr, pViewMgr.Metrics(), VIEWCLSID.GENERIC_VIEWPORT, 0, ViewState.Visible | ViewState.Enabled);
    }

    // Message handler
    public boolean ProcessMessage(final iInput.iEntry msg) {
            switch (msg.taskType) {
                    case iInput.iEntry.MouseMove:
                        MouseTrack(new iPoint(msg.px,msg.py));
                        break;
                    case iInput.iEntry.MouseDown:
                        MouseDown(new iPoint(msg.px,msg.py));
                        break;
                    case iInput.iEntry.MouseUp:
                        MouseUp(new iPoint(msg.px,msg.py));
                        break;
                    case iInput.iEntry.KeyDown:
                        KeyDown(msg.key);
                        break;
                    case iInput.iEntry.KeyUp:
                       KeyUp(msg.key);
                       break;
            }
            return true;
    }

    public boolean KeyDown(int key) {
            return OnKeyDown(key);
    }

    public boolean KeyUp(int key) {
            return OnKeyUp(key);
    }

    // Overrides
    public boolean OnKeyDown(int key) {
        return false;
    }

    public boolean OnKeyUp(int key) {
        return false;
    }

}
