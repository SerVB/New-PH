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

import newph.core.metric.iPoint;

/**
 * Animation Object.
 */
public class iAniObj extends TypeAware {

    protected final iPoint[] cellOffset = {
        new iPoint(0,-20), new iPoint(20,-9), new iPoint(40,0),  new iPoint(20,9),
        new iPoint(0,20),  new iPoint(-20,9), new iPoint(-40,0), new iPoint(-20,-9)
    };

    public enum AniType {
        CellAni,
        FullScreenAni
    };

    public IMPL_TYPEAWARE( iAniObj );

    public iAniObj( IAniCB pcb, double t, final iPoint pos) {
        this.m_pCB = pcb;
        this.m_Duration = t;
        this.m_Timer = 0.0;
        this.m_Pos = pos;
        this.m_type = AniType.CellAni;
    }

    public iAniObj( IAniCB pcb, double t) {
        this.m_pCB = pcb;
        this.m_Duration = t;
        this.m_Timer = 0.0;
        this.m_Pos = cInvalidPoint;
        this.m_type = AniType.FullScreenAni;
    }

    public void Process(double t) {
        m_Timer += t;
        if (m_Timer >= m_Duration) {
            m_pCB.AniDone();
        }
    }

    public iPoint Pos() {
        return m_Pos;
    }

    public AniType Type() {
        return m_type;
    }


    protected AniType    m_type;
    protected iPoint    m_Pos;
    protected IAniCB    m_pCB;
    protected double    m_Timer;
    protected double    m_Duration;
}
