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
package Game.map.construction.visitable;

import Constants.ENDUR;
import Common.metrics.iPoint;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import utils.tracer;

/**
 *
 */
public class iVisCnst_Stable extends iVisCnst {

    public iVisCnst_Stable(iVisCnstT_Stable pProto, final iPoint pos) {
        super(pProto, pos, false);
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        super.NewWeek(weekDesk);
        m_Visitors.clear();
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        super.OnActivate(pVisitor, bActive);
        iVisCnstT_Stable pStable = DynamicCast<iVisCnstT_Stable>(m_pProto);
        tracer.check(pStable);
        pVisitor.AddMoves(pStable.ActPts());
        pVisitor.Enchs().add(m_pProto.Type(), FSK.LOGISTICS, ENDUR.NEXTWEEK, pStable.ActPts());
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return pVisitor.Enchs().Registered(m_pProto.Type());
    }

}
