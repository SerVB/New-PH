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

import Constants.DRC;
import Common.iArmy;
import Common.iDynamicBuffer;
import newph.metric.iPoint;
import Constants.*;
import Game.iHero;
import Game.iVisitors;
import Game.iWeekDesc;
import Game.map.construction.iMapCnst;
import utils.ChangeableString;
import utils.serialize;

/**
 * Visitable constuction.
 */
public class iVisCnst extends iMapCnst {

//    public IMPL_TYPEAWARE( iVisCnst );

    public iVisCnst(iVisCnstT pProto, final iPoint pos, boolean bCustomGUI) {
        super(pos);

        this.m_pProto = pProto;
        this.m_bCustomGUI = bCustomGUI;
    }

    public iVisCnstT Proto() {
        return m_pProto;
    }

    public iArmy Guard() {
        return m_Guard;
    }

    public String CustomMessage() {
        return m_customMsg;
    }

    public void NewDay() {}

    public void NewWeek(final iWeekDesc weekDesk) {}

    @Override
    public boolean Activate(iHero pVisitor, boolean bActive) {
        if (!m_Guard.Empty()) {
            if (bActive) {
                iDlg_FightGuard dlg = new iDlg_FightGuard(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        m_Guard,
                        pVisitor.Owner().PlayerId(),
                        VL.BASIC
                );
                if (dlg.DoModal() == DRC.YES) {
                    iBattleInfo bi = new iBattleInfo(pVisitor, this);
                    gGame.BeginBattle(bi);
                }
            } else {
                iBattleInfo bi = new iBattleInfo(pVisitor, this);
                gGame.BeginBattle(bi);
            }
            return false;
        } else {
            if (bActive) {
                if (!m_customMsg.isEmpty()) {
                    iTextDlg dlg = new iTextDlg(
                            gApp.ViewMgr(),
                            "",
                            m_customMsg,
                            pVisitor.Owner().PlayerId()
                    );
                    dlg.DoModal();
                } else if (!m_bCustomGUI){
                    if (Visited(pVisitor)) {
                        iTextDlg dlg = new iTextDlg(
                                gApp.ViewMgr(),
                                gTextMgr[m_pProto.NameKey()],
                                gTextMgr[m_pProto.VisMessage()],
                                pVisitor.Owner().PlayerId()
                        );
                        dlg.DoModal();
                        return false;
                    } else {
                        iTextDlg dlg = new iTextDlg(
                                gApp.ViewMgr(),
                                gTextMgr[m_pProto.NameKey()],
                                gTextMgr[m_pProto.Message()],
                                pVisitor.Owner().PlayerId()
                        );
                        dlg.DoModal();
                    }
                }
            }
        }

        OnActivate(pVisitor, bActive);
        return true;
    }

    public void OnActivate(iHero pVisitor, boolean bActive) {
        m_Visitors.add(pVisitor.Proto().m_protoId);
    }

    public boolean Visited(final iHero pVisitor) {
        return (m_Visitors.contains(pVisitor.Proto().m_protoId));
    }

    public void Serialize(iDynamicBuffer dbuff) {
        // Guard
        serialize.Serialize(dbuff, m_Guard);
        // Custom message
        serialize.Serialize(dbuff, m_customMsg);
        // Visitors
        dbuff.Write(m_Visitors.size());
        for (int visitor : m_Visitors) {
            dbuff.Write(visitor);
        }
        // flag
        dbuff.Write(1);
    }

    public int Unserialize(iDynamicBuffer dbuff) {
        // Guard
        serialize.Unserialize(dbuff, m_Guard);
        ::InitArmy(m_Guard);
        // Custom message
        serialize.Unserialize(dbuff, m_customMsg);
        // Visitors
        int vcnt = (int) dbuff.Read();
        while (vcnt-- > 0) {
            int visitor = (int) dbuff.Read();
            m_Visitors.add(visitor);
        }
        // flag
        int flag = (int) dbuff.Read();
        return flag;
    }

    public boolean CustomPopupText(final iHero pVisitor, ChangeableString ctext) {
        return false;
    }

    public boolean ShowVisitedLabel() {
        return true;
    }

    public iVisitors Visitors() {
        return m_Visitors;
    }


    protected boolean        m_bCustomGUI;
    protected iArmy        m_Guard;
    protected String    m_customMsg;
    protected iVisitors    m_Visitors;
    protected iVisCnstT    m_pProto;
}
