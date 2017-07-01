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
import newph.staticFunction.Tracer;
import Constants.*;

/**
 *
 */
public class iTavernCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iTavernCtlCnst );

    public iTavernCtlCnst(iCastle _pCastle, int _cnst) {
        super(_pCastle, _cnst);

        m_bRecruited = false;

        Tracer.check(_cnst == CTLCNST.TAVERN);
    }

    @Override
    public void OnInit() {
        m_curRumor = gGame.Map().Rand(39);
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        m_bRecruited = false;
        m_curRumor = gGame.Map().Rand(39);
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        buff.Write(m_curRumor);
        buff.Write(m_bRecruited ? 1 : 0);
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        int curRumor;
        int bRecruited;
        curRumor = (int) buff.Read();
        bRecruited = (int) buff.Read();
        m_curRumor = curRumor;
        m_bRecruited = bRecruited > 0;
    }

    @Override
    public void OnVisitorEnter(iHero pHero) {
        pHero.VarFurtSkills().plusValue(FSK.MORALE, 1);
    }

    @Override
    public void OnVisitorLeave(iHero pHero) {
        pHero.VarFurtSkills().minusValue(FSK.MORALE, 1);
    }

    public final iHero Visitor() {
        if (m_bRecruited) {
            return null;
        }

        iPlayer pOwner = gGame.Map().FindPlayer(pCastle.Owner());
        Tracer.check(pOwner != null);
        return pOwner.TavernVisitor();
    }

    public iHero RecruitVisitor() {
        Tracer.check(CanRecruit());
        iPlayer pOwner = gGame.Map().FindPlayer(pCastle.Owner());
        Tracer.check(pOwner != null);
        m_bRecruited = true;
        return pOwner.RecruitTavernVisitor(pCastle);
    }

    public final boolean CanRecruit() {
        iPlayer pOwner = gGame.Map().FindPlayer(pCastle.Owner());

        return  pOwner.TavernVisitor() &&
                !m_bRecruited &&
                !pCastle.Visitor() &&
                pOwner.Minerals().Has(pOwner.TavernVisitor().Proto().m_Cost) &&
                pOwner.HeroCount() < 4;
    }

    public final int CurRumor() {
        return m_curRumor;
    }


    private boolean    m_bRecruited;
    private int    m_curRumor;
}
