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

import Common.metrics.iPoint;
import Constants.*;
import Game.iHero;
import utils.ChangeableString;
import utils.tracer;

/**
 *
 */
public class iVisCnst_Teleport extends iVisCnst {

    public iVisCnst_Teleport(iVisCnstT_Teleport pProto, final iPoint pos) {
        super(pProto, pos, true);

        m_tgtIdx = -1;
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        if ((IsTwoWay() || IsEntrance()) && m_tgtIdx != -1) {
            iVisCnst_Teleport pTarget = gGame.Map().GetTeleport(m_tgtIdx);
            tracer.check(pTarget != null);
            // Check for visitor at target cell
            if (iHero pTeleportVisitor = DynamicCast<iHero>(gGame.Map().m_CoverMap.GetAt(pTarget.Pos().x,pTarget.Pos().y))) {
                tracer.check(pTeleportVisitor.GetLocation() == pTarget);
                if (pTeleportVisitor.Owner().PlayerId() != pVisitor.Owner().PlayerId()) {
                    pVisitor.AttackHero(pTeleportVisitor);
                } else {
                    pVisitor.MeetHero(pTeleportVisitor);
                }
            } else {
                gGame.Map().MoveHero(pVisitor.Pos(), pTarget.Pos());
                pVisitor.SetPos(pTarget.Pos());
                pVisitor.Owner().UpdateFogMap();
                gGame.OnHeroPosChanged(pVisitor, pVisitor.Pos());
                gGame.OnHeroChanged(pVisitor);
                gGame.OnHeroTeleport(pVisitor, Pos(), pTarget.Pos());
            }
        } else {
            if (bActive) {
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[TRID.OBJ_TELEPORT_EXIT_MSG],
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        }
    }

    public int GetTarget() {
        return (IsTwoWay() || IsEntrance())?m_tgtIdx:-1;
    }

    public int GetOrigin() {
        return (IsTwoWay() || !IsEntrance())?m_tgtIdx:-1;
    }

    public int Index() {
        return ((iVisCnstT_Teleport)m_pProto).Index();
    }

    public boolean IsEntrance() {
        return ((iVisCnstT_Teleport)m_pProto).IsEntrance();
    }

    public boolean IsTwoWay() {
        return ((iVisCnstT_Teleport)m_pProto).IsTwoWay();
    }

    public void SetTarget(int tgtIdx) {
        m_tgtIdx = tgtIdx;
    }

    @Override
    public boolean CustomPopupText(final iHero pVisitor, ChangeableString ctext) {
        if (IsTwoWay()) {
            ctext.changeTo(gTextMgr[TRID.OBJ_TELEPORT_TWOWAY]);
        } else if (IsEntrance()) {
            ctext.changeTo(gTextMgr[TRID.OBJ_TELEPORT_ONEWAY_ENT]);
        } else {
            ctext.changeTo(gTextMgr[TRID.OBJ_TELEPORT_ONEWAY_EXT]);
        }
        return true;
    }

    @Override
    public boolean ShowVisitedLabel() {
        return false;
    }


    private int    m_tgtIdx;
}
