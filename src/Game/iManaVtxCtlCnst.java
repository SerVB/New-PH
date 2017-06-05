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

import Common.iDynamicBuffer;
import utils.tracer;

/**
 *
 */
public class iManaVtxCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iManaVtxCtlCnst );

    public iManaVtxCtlCnst(iCastle _pCastle, int _cnst) {
        super(_pCastle, _cnst);

        this.m_bVisited = false;
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        m_bVisited = false;
        if (pCastle.Visitor()) {
            OnVisitorEnter(pCastle.Visitor());
        }
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        // Visitors
        buff.Write(m_bVisited ? 1 : 0);
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        // Visitors
        long bVisited = buff.Read();
        m_bVisited = bVisited != 0;
    }

    @Override
    public void OnVisitorEnter(iHero pHero) {
        tracer.check(pHero != null);
        if (!Visited(pHero)) {
            DoubleManaPoints(pHero);
        }
    }

    @Override
    public final boolean Visited(final iHero pHero) {
        return m_bVisited;
    }

    private void DoubleManaPoints(iHero pHero) {
        tracer.check(!m_bVisited);
        int act_pts = pHero.MaxManaPts() * 2;
        if (pHero.ManaPts() < act_pts) {
            pHero.setManaPts(act_pts);
            m_bVisited = true;
        }
    }

    private boolean    m_bVisited;
}
