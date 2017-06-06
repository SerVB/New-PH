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
package Game.map.construction.ownerable;

import Common.iArmy;
import Common.iPoint;
import Constants.*;
import Game.iHero;
import Game.iWeekDesc;
import Game.map.construction.iMapCnst;
import utils.tracer;

/**
 * Ownerables.
 */
public class iOwnCnst extends iMapCnst {

    public iOwnCnst(iOwnCnstT pProto, final iPoint pos) {
        super(pos);

        m_pProto = pProto;
        m_Owner = PID.NEUTRAL;
    }

    public iOwnCnstT Proto() {
        return m_pProto;
    }

    /**
     *
     * @return Player ID.
     */
    public int Owner() {
        return m_Owner;
    }

    public iArmy Guard() {
        return m_Guard;
    }

    public void SetOwner(int newOwner) {
        int oldOwner = m_Owner;
        m_Owner = newOwner;
        OnOwnerChanged(newOwner, oldOwner);
    }

    public void OnOwnerChanged(int newOwner, int oldOwner) {}

    public void NewDay() {}

    public void NewWeek(final iWeekDesc weekDesk) {
        if (m_Owner == PID.NEUTRAL) {
            // grow guardians population for neutral construction
            for (int xx = 0; xx < 7; ++xx) {
                if (m_Guard.At(xx).IsValid()) {
                    m_Guard.At(xx).Grow();
                }
            }
        }
    }

    @Override
    public boolean Activate(iHero pVisitor, boolean bActive) {
        if (m_Owner != pVisitor.Owner().PlayerId() && !m_Guard.Empty()) {
            if (bActive) {
                iDlg_FightGuard dlg = new iDlg_FightGuard(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        m_Guard, pVisitor.Owner().PlayerId(),
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
        } else if (m_Owner == pVisitor.Owner().PlayerId()) {
            if (bActive) {
                iDlg_LeaveGuards lgdlg = new iDlg_LeaveGuards(gApp.ViewMgr(), pVisitor, this);
                lgdlg.DoModal();
                return true;
            } else {
                // not implemented
                //check(0);
            }
        } else {
            if (m_Owner != PID.NEUTRAL) {
                iPlayer oldPlayer = gGame.Map().FindPlayer(m_Owner);
                tracer.check(oldPlayer != null);
                oldPlayer.m_Cnsts.Remove(iPlayer.iCLIt(this));
                oldPlayer.UpdateFogMap();
            } else {
                gGame.Map().m_OwnCnsts.Remove(iGameWorld.iOCIt(this));
            }
            SetOwner(pVisitor.Owner().PlayerId());
            pVisitor.Owner().AddCnst(this);
        }

        OnActivate(pVisitor, bActive);
        return true;
    }

    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (bActive) {
            gSfxMgr.PlaySound(CSND.FLAG_MINE);
            if (m_pProto.MessageIcon() != 0xFFFF) {
                iIconDlg dlg = new iIconDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.Message()],
                        m_pProto.MessageIcon(),
                        pVisitor.Owner().PlayerId()
                );
                dlg.DoModal();
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


    protected iArmy     m_Guard;
    protected iOwnCnstT m_pProto;
    protected int       m_Owner;
}
