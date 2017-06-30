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
package Game.map.item;

import Constants.map.MAPITEM;
import Constants.DRC;
import Common.iCreatGroup;
import Common.iDynamicBuffer;
import newph.metric.iPoint;
import Constants.*;
import Game.iHero;
import newph.util.Tracer;

/**
 *
 */
public class iMapItem_Lamp extends iMapItem {

    public iMapItem_Lamp(final iPoint pos, int count) {
        super(pos, MAPITEM.LAMP, PDGG.RES_LAMP);

        this.m_count = count;

        if (m_count == RANDOM.VAL) {
            m_count = 2 + gGame.Map().Rand(4);
        }
    }

    public iMapItem_Lamp(final iPoint pos) {
        this(pos, RANDOM.VAL);
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        Tracer.check(m_count > 0);
        if (bActive) {
            iCreatGroup cg = new iCreatGroup(CREAT.GENIE, m_count);
            iDlg_Recruit rdlg = new iDlg_Recruit(gApp.ViewMgr(), cg, pHero.Army(), pHero.Owner().PlayerId());
            if (rdlg.DoModal() == DRC.OK) {
                m_count = cg.Count();
                if (m_count == 0) {
                    gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
                }
            }
        } else {
            iAI_Player pOwner = DynamicCast<iAI_Player>(pHero.Owner());
            Tracer.check(pOwner);
            iCreatGroup cg = new iCreatGroup(CREAT.GENIE, m_count);
            if (pOwner.ProcessRecruitCreatures(pHero.Army(), cg)) {
                m_count -= cg.Count();
            }
        }

        return m_count == 0;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_count);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_LAMP;
    }

    public int Count() {
        return m_count;
    }

//    public IMPL_TYPEAWARE( iMapItem_Lamp );

    private int            m_count;
}
