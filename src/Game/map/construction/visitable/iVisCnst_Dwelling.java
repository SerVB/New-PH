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

import Constants.WeekDesc;
import Constants.DRC;
import Common.iCreatGroup;
import Common.iDynamicBuffer;
import newph.metric.iPoint;
import Common.iFormat;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import newph.util.Tracer;

/**
 *
 */
public class iVisCnst_Dwelling extends iVisCnst {

    public iVisCnst_Dwelling(iVisCnstT_Dwelling pProto, final iPoint pos) {
        super(pProto, pos, true);

        this.m_creatType = pProto.CreatType();
        this.m_growth = CREAT.DESC[pProto.CreatType()].growth;
        this.m_creatCount = 0;
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        switch (weekDesk.wkType) {
            case WeekDesc.Normal:
                m_creatCount += m_growth;
                break;
            case WeekDesc.Plague:
                m_creatCount /= 2;
                break;
            case WeekDesc.CreatPlus:
                m_creatCount += m_growth;
                if (weekDesk.crType == m_creatType) m_creatCount += 5;
                break;
            case WeekDesc.CreatDoubles:
                m_creatCount += m_growth;
                if (weekDesk.crType == m_creatType) m_creatCount *= 2;
                break;
            default:
                break;
        }
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (Visited(pVisitor)) {
            if (bActive) {
                iCreatGroup cg = new iCreatGroup(m_creatType, m_creatCount);
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        iFormat.format(
                                gTextMgr[TRID.OBJ_DWELL_EMPTY],
                                gTextMgr[TRID.CREATURE_PEASANT_F3+cg.Type()*3]
                        ),
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        } else {
            if (bActive) {
                iCreatGroup cg = new iCreatGroup(m_creatType, m_creatCount);
                if (!JoinForFree()) {
                    // Buy
                    iDlg_Recruit rdlg = new iDlg_Recruit(
                            gApp.ViewMgr(),
                            cg,
                            pVisitor.Army(),
                            pVisitor.Owner().PlayerId()
                    );
                    if (rdlg.DoModal() == DRC.OK) {
                        m_creatCount = cg.Count();
                    }
                } else {
                    // Join Army
                    iQuestDlg dj = new iQuestDlg(
                            gApp.ViewMgr(),
                            gTextMgr[m_pProto.NameKey()],
                            iFormat(
                                    gTextMgr[TRID.OBJ_DWELL_RECR_QUEST],
                                    gTextMgr[TRID.CREATURE_PEASANT_F3+cg.Type()*3]
                            ),
                            pVisitor.Owner().PlayerId()
                    );
                    if (dj.DoModal() == DRC.YES) {
                        iIconDlg idlg = new iIconDlg(
                                gApp.ViewMgr(),
                                gTextMgr[m_pProto.NameKey()],
                                iFormat(
                                        gTextMgr[TRID.OBJ_DWELL_RECR_MSG],
                                        m_creatCount,
                                        gTextMgr[TRID.CREATURE_PEASANT_F3+cg.Type()*3]
                                ),
                                PDGG.MINIMON + cg.Type(),
                                pVisitor.Owner().PlayerId()
                        );
                        idlg.DoModal();
                        if (!pVisitor.Army().AddGroup(cg.Type(),cg.Count())) {
                            iDlg_ArmyRoom arDlg = new iDlg_ArmyRoom(
                                    gApp.ViewMgr(),
                                    pVisitor,
                                    cg
                            );
                            arDlg.DoModal();
                        }
                        m_creatCount = 0;
                    }
                }
            } else {
                iAI_Player pOwner = DynamicCast<iAI_Player>(pVisitor.Owner());
                Tracer.check(pOwner != null);
                iVisCnstT_Dwelling pProto = DynamicCast<iVisCnstT_Dwelling>(m_pProto);
                Tracer.check(pProto);
                iCreatGroup cg = new iCreatGroup(pProto.CreatType(),m_creatCount);
                if (JoinForFree()) {
                    // Join
                    if (pOwner.ProcessJoinCreatures(pVisitor.Army(), cg)) {
                        m_creatCount = 0;
                    }
                } else {
                    // Buy
                    if (pOwner.ProcessRecruitCreatures(pVisitor.Army(), cg)) {
                        m_creatCount -= cg.Count();
                    }
                }
            }
        }
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return m_creatCount == 0;
    }

    @Override
    public void Serialize(iDynamicBuffer dbuff) {
        // Common data serialization
        super.Serialize(dbuff);
        // Write creatures count
        dbuff.Write(m_creatCount);
    }

    @Override
    public int Unserialize(iDynamicBuffer dbuff) {
        int flag = super.Unserialize(dbuff);
        if (flag == 0) {
            // Init by default
            m_creatCount = m_growth;
        } else {
            // Read data
            m_creatCount = (int) dbuff.Read();
        }
        return flag;
    }

    public int CreatType() {
        return m_creatType;
    }

    public int CreatCount() {
        return m_creatCount;
    }

    public boolean JoinForFree() {
        return CREAT.DESC[m_creatType].level < 3;
    }

    private final int m_growth;
    private final int m_creatType;
    private int       m_creatCount;

}
