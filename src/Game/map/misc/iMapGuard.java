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
package Game.map.misc;

import Constants.map.MR;
import Constants.DRC;
import Constants.GDISP;
import Common.iCreatGroup;
import Common.iFormat;
import Common.iPoint;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import Game.map.iBaseMapObject;
import helperFunction.*;
import utils.tracer;

/**
 *
 */
public class iMapGuard extends iBaseMapObject {

    public iMapGuard(final iPoint pos, int ct, int cnt, int joinVal, int disp, boolean bNotGrow) {
        super(pos, true);

        this.m_creatures = new iCreatGroup(creature.NormalizeCreatType(ct), cnt);
        this.m_disp = disp;
        this.m_bNotGrow = bNotGrow;

        m_spriteId = PDGG.MINIMON + m_creatures.Type();

        // Init quantity
        if (cnt == RANDOM.QUANTITY) {
            m_creatures.setCount(
                    ( 150 + gGame.Map().Rand(80) + CREAT.DESC[m_creatures.Type()].level * 10 ) /
                    int_sqrt(CREAT.DESC[m_creatures.Type()].pidx)
            );
        }

        m_joinVal = joinVal;
    }

    public iMapGuard(final iPoint pos, int ct, int cnt, int joinVal, int disp) {
        this(pos, ct, cnt, joinVal, disp, false);
    }

    public iMapGuard(final iPoint pos, int ct, int cnt, int joinVal) {
        this(pos, ct, cnt, joinVal, GDISP.AGGRESSIVE);
    }

    public int CheckMeetResult(iHero pHero) {
        boolean bFlee = m_disp != GDISP.SAVAGE && pHero.Army().ArmyPower() > (m_creatures.GroupPower()*2);

        // join ?
        int dip = Math.min(100, 5 + pHero.FurtSkill(FSK.DIPLOMACY) / 2);
        boolean bJoin = (m_disp == GDISP.COMPLIANT || (m_disp == GDISP.AGGRESSIVE && pHero.Army().ArmyPower() > m_creatures.GroupPower() && (dip > m_joinVal)));

        if (bFlee && bJoin) {
            return MR.JOIN_OR_FLEE;
        } else if (bFlee) {
            return MR.FLEE;
        } else if (bJoin) {
            return MR.JOIN_OR_ATTACK;
        } else {
            return MR.ATTACK;
        }
    }

    public boolean Activate(iHero pHero, boolean bActive) {
        int mr = CheckMeetResult(pHero);
        if (bActive) {
            // Show message
            if (!m_message.isEmpty()) {
                iTextDlg dlg = new iTextDlg(gApp.ViewMgr(), "", m_message, pHero.Owner().PlayerId());
                dlg.DoModal();
            }

            if (mr == MR.JOIN_OR_FLEE || mr == MR.JOIN_OR_ATTACK) {
                // Join Army
                iQuestDlg dj = new iQuestDlg(
                        gApp.ViewMgr(),
                        "",
                        iFormat.format(
                                gTextMgr[TRID.CREAT_JOIN_MESSAGE],
                                gTextMgr[TRID.CREATURE_PEASANT_F2+Creatures().Type()*3]
                        ),
                        pHero.Owner().PlayerId()
                );
                if (dj.DoModal() == DRC.YES) {
                    if (!pHero.Army().AddGroup(m_creatures.Type(),m_creatures.Count())) {
                        iDlg_ArmyRoom arDlg = new iDlg_ArmyRoom(gApp.ViewMgr(), pHero, m_creatures);
                        arDlg.DoModal();
                    }
                    gSfxMgr.PlaySound(CSND.DEL_GUARD);
                    return true;
                }
            }

            if (mr == MR.JOIN_OR_FLEE || mr == MR.FLEE) {
                iQuestDlg de = new iQuestDlg(
                        gApp.ViewMgr(),
                        "",
                        iFormat.format(
                                gTextMgr[TRID.CREAT_ESCAPE_MESSAGE],
                                gTextMgr[TRID.CREATURE_PEASANT_F2+Creatures().Type()*3]
                        ),
                        pHero.Owner().PlayerId()
                );
                if (de.DoModal() == DRC.NO) {
                    gSfxMgr.PlaySound(CSND.DEL_GUARD);
                    return true;
                }
            }
            // Here is battle
            iBattleInfo bi = new iBattleInfo(pHero, this);
            gGame.BeginBattle(bi);
            return false;

        } else {
            iAI_Player pOwner = DynamicCast<iAI_Player>(pHero.Owner());
            tracer.check(pOwner);
            // TRICK: #2 - join all encountered creatures
            if ( pOwner.m_HackedTricks & 0x0c != 0 ) {
                pOwner.ProcessJoinCreatures(pHero.Army(), m_creatures);
                mr = MR.JOIN_OR_FLEE;
            }
            // ------------------------------
            if (mr == MR.FLEE || mr == MR.ATTACK || !pOwner.ProcessJoinCreatures(pHero.Army(), m_creatures)) {
                // Here is battle
                iBattleInfo bi = new iBattleInfo(pHero, this);
                gGame.BeginBattle(bi);
                return false;
            }
        }
        return true;
    }

    public void NewWeek(final iWeekDesc weekDesk) {
        if (!m_bNotGrow) {
            m_creatures.Grow();
        }
    }

    public iCreatGroup Creatures() {
        return m_creatures;
    }

    public int Disposition() {
        return m_disp;
    }

    public boolean NotGrow() {
        return m_bNotGrow;
    }

    public int Sprite() {
        return m_spriteId;
    }

    public String Message() {
        return m_message;
    }

    public int JoinVal() {
        return m_joinVal;
    }

//    public IMPL_TYPEAWARE( iMapGuard );


    private int            m_joinVal;
    private iCreatGroup        m_creatures;
    private String        m_message;
    private int        m_spriteId;
    private int    m_disp;
    private boolean            m_bNotGrow;

}
