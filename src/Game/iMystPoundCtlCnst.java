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

import Constants.ConstructionState;
import Common.iDynamicBuffer;
import Constants.*;
import utils.ChangeableString;
import newph.staticFunction.Tracer;

/**
 *
 */
public class iMystPoundCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iMystPoundCtlCnst );

    public iMystPoundCtlCnst(iCastle _pCastle, int _cnst) {
        super(_pCastle, _cnst);
        this.m_mt = MINERAL.UNKNOWN;
        this.m_cnt = 0;
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        if (pCastle.Owner() != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(pCastle.Owner());
            m_mt = MINERAL.MERCURY + gGame.Map().Rand(4);
            m_cnt = 2 + gGame.Map().Rand(4);
            pPlayer.Minerals().addQuant(m_mt, m_cnt);
        }
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        // Last week production
        buff.Write(m_mt);
        buff.Write(m_cnt);
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        // Last week production

        int mt, cnt;
        mt  = (int) buff.Read();
        cnt = (int) buff.Read();
        m_mt  = mt;
        m_cnt = cnt;
    }

    @Override
    public final boolean GetActivityText(ChangeableString text) {
        Tracer.check(state == ConstructionState.Built);
        if (m_mt == MINERAL.UNKNOWN) {
            text.changeTo(gTextMgr[TRID.CTLCNST_MYSTICPOUND_NPROD]);
        } else {
            text.changeTo(String.format(gTextMgr[TRID.CTLCNST_MYSTICPOUND_PROD], Mineral2Text(m_mt, m_cnt)));
        }
        return true;
    }

    /**
     * Mineral Type.
     */
    private int    m_mt;

    /**
     * Mineral Count.
     */
    private int            m_cnt;
}
