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
 *
 */
public class iVanishItem implements iIListNode {

    public iVanishItem(iBaseMapObject pObj, double clockWork) {
        this.m_pContent = pObj;
        this.m_LifeTime = clockWork;
        this.m_ClockWork = clockWork;
    }

    public void Process(double t) {
        if (m_ClockWork >= t) {
            m_ClockWork -= t;
        } else {
            m_ClockWork = 0.0;
        }
    }

    public boolean Vanished() {
        return m_ClockWork == 0.0;
    }

    public int State(int maxVal) {
        return (int) Math.floor(m_ClockWork * maxVal / m_LifeTime);
    }

    public iBaseMapObject DettachContent() {
        iBaseMapObject res = m_pContent;
        m_pContent = null;
        return m_pContent;
    }

    public iBaseMapObject Content() {
        return m_pContent;
    }

    private iBaseMapObject  m_pContent;
    private double          m_LifeTime;
    private double          m_ClockWork;
}
