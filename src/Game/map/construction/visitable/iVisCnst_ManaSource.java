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

import Constants.PROVIDER;
import Constants.ENDUR;
import newph.core.metric.iPoint;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iVisCnst_ManaSource extends iVisCnst {

    public iVisCnst_ManaSource(iVisCnstT_ManaSource pProto, final iPoint pos) {
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
        int act_pts = pVisitor.MaxManaPts() * ManaPts();
        if (pVisitor.ManaPts() < act_pts) {
            pVisitor.setManaPts(act_pts);
        }
        pVisitor.Enchs().add(m_pProto.ProtoId() | PROVIDER.FLAG, FSK.INVALID, ENDUR.NEXTWEEK, 0);
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return pVisitor.Enchs().Registered(m_pProto.ProtoId() | PROVIDER.FLAG);
    }

    public int ManaPts() {
        iVisCnstT_ManaSource pManaSource = DynamicCast<iVisCnstT_ManaSource>(m_pProto);
        Tracer.check(pManaSource != null);
        return pManaSource.ManaPts();
    }

}
