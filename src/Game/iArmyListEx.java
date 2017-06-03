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

import Common.iArmy;
import Common.iCreatGroup;
import Common.iFurtSkills;
import Common.iPoint;
import Common.iRect;
import Common.iSize;
import Common.tracer;
import Constants.*;
import java.util.ArrayList;

/**
 * Army List control Extended (with drag'n'drop capabilities).
 */
public class iArmyListEx extends iBaseCtrl {

    public iArmyListEx(
            iViewMgr pViewMgr,
            IViewCmdHandler pCmdHandler,
            final iPoint pos,
            iCheckButton pSplitBtn,
            final iSize itemSiz,
            int uid
    ) {
        super(pViewMgr, pCmdHandler, new iRect(pos.x, pos.y,(itemSiz.w+1)*7-1,itemSiz.h),GENERIC.VIEWPORT, uid, Visible | Enabled);

        this.m_itemW = itemSiz.w;
        this.m_dCell = -1;
        this.m_pArmy = null;
        this.m_pOwner = null;
        this.m_bCanDismiss = false;
        this.m_pDragItem = null;
        this.m_pDropItem = null;
        this.m_lastClick = 0;
        this.m_bSplit = false;
        this.m_pSplitBtn = pSplitBtn;

        AddCompetitor(this);
    }

    // Drag'n'Drop
    public void AddCompetitor(iArmyListEx pCompetitor) {
        m_Competitors.Add(pCompetitor);
    }

    public void BeginDrag(int fromCell, iCreatGroup cGroup, final iPoint pos) {
        tracer.check(m_pDragItem == null);

        m_pDragItem = new iDragItem(
                this,
                fromCell,
                (m_bSplit || (m_pSplitBtn && m_pSplitBtn.IsChecked())),
                cGroup,
                pos,
                iSize(m_itemW, m_Rect.h),
                m_bCanDismiss || m_pArmy.GroupCount() > 1
        );

        m_pMgr.SetDragGlyph(m_pDragItem);
        Drag(pos);
    }

    public void EndDrag() {
        tracer.check(m_pDragItem != null);
        tracer.check(m_pArmy != null);
        m_pMgr.SetDragGlyph(null);
        if (m_pDragItem.m_pReceiver != null)
            m_pDragItem.m_pReceiver.Drop();
        m_pDragItem = null;
        if (m_pSplitBtn)
            m_pSplitBtn.SetChecked(false);
        Invalidate();
    }

    public void Drag(final iPoint pos) {
        tracer.check(m_pDragItem != null);
        m_pDragItem.m_pos = pos;
        for (int xx = 0; xx < m_Competitors.GetSize(); ++xx) {
            if (m_Competitors.get(xx).GetScrRect().PtInRect(pos)) {
                if (m_pDragItem.m_pReceiver != m_Competitors.get(xx)) {
                    if (m_pDragItem.m_pReceiver != null)
                        m_pDragItem.m_pReceiver.LeaveDrop();
                    m_Competitors.get(xx).EnterDrop(m_pDragItem);
                }
                if (m_pDragItem.m_pReceiver != null)
                    m_pDragItem.m_pReceiver.DragDrop();
                Invalidate();
                return;
            }
        }
        if (m_pDragItem.m_pReceiver != null)
            m_pDragItem.m_pReceiver.LeaveDrop();
        Invalidate();
    }

    public void EnterDrop(iDragItem pDragItem) {
        if (m_pArmy == null)
            return;
        tracer.check(m_pDropItem == null);
        m_pDropItem = pDragItem;
        m_pDropItem.m_pReceiver = this;
        m_pDropItem.m_toCell = -1;
        Invalidate();
    }

    public void LeaveDrop() {
        tracer.check(m_pDropItem != null);
        m_pDropItem.m_pReceiver = null;
        m_pDropItem.m_toCell = -1;
        m_pDropItem = null;
        Invalidate();
    }

    public boolean Drop() {
        if (m_pDropItem.m_toCell == -1) {
            LeaveDrop();
            Invalidate();
            return false;
        }

        if (m_pDropItem.m_pSender == m_pDropItem.m_pReceiver && m_pDropItem.m_fromCell == m_pDropItem.m_toCell) {
            // (same cell) do nothing
        } else if (m_pArmy.At(m_pDropItem.m_toCell).Type() == m_pDropItem.m_cGroup.Type()) {
            // (similar type of creatures)
            if (m_pDropItem.m_bSplit) {
                // split mode (show split dialog)
                int flags = iDlg_SplitCreatures.Normal;
                if (m_pDropItem != m_pDragItem){
                    if (!m_pDropItem.m_bCanDismiss && m_pDropItem.m_pSender.Army().GroupCount() <= 1)
                        flags |= iDlg_SplitCreatures.SafeG1;
                    if (!m_bCanDismiss && m_pArmy.GroupCount() <= 1)
                        flags |= iDlg_SplitCreatures.SafeG2;
                }
                iDlg_SplitCreatures scdlg = new iDlg_SplitCreatures(
                        m_pMgr,
                        PID.NEUTRAL,
                        m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell),
                        m_pArmy.At(m_pDropItem.m_toCell),
                        flags
                );
                scdlg.DoModal();
            } else {
                // normal mode (merge groups)
                m_pArmy.At(m_pDropItem.m_toCell).addCount( m_pDropItem.m_cGroup.Count() );
                m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell).Reset();
            }
        } else {
            if (m_pDropItem.m_bSplit) {
                // show split dialog
                int flags = iDlg_SplitCreatures.Normal;
                if (
                        m_pDropItem != m_pDragItem &&
                        !m_pDropItem.m_bCanDismiss &&
                        m_pDropItem.m_pSender.Army().GroupCount() <= 1
                ) {
                        flags |= iDlg_SplitCreatures.SafeG1;
                }
                iDlg_SplitCreatures scdlg = new iDlg_SplitCreatures(
                        m_pMgr,
                        PID.NEUTRAL,
                        m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell),
                        m_pArmy.At(m_pDropItem.m_toCell),
                        flags
                );
                scdlg.DoModal();
            } else {
                // swap cells
                iSwap(m_pArmy.At(m_pDropItem.m_toCell),m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell));
            }
        }
        LeaveDrop();
        Invalidate();
        return true;
    }

    public boolean DragDrop() {
        int toCell = Pos2Cell(m_pDropItem.m_pos);
        if (m_pDropItem.m_bSplit) {
            if (toCell != -1) {
                iCreatGroup fGroup = m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell);
                iCreatGroup tGroup = m_pArmy.At(toCell);
                if (!tGroup.IsValid() || tGroup.Type() == fGroup.Type())
                    m_pDropItem.m_toCell = toCell;
            }
        } else {
            if (toCell == -1
                || m_pDropItem.m_bCanDismiss
                || m_pDropItem == m_pDragItem
                || (m_pArmy.At(toCell).IsValid() && m_pArmy.At(toCell).Type() != m_pDropItem.m_pSender.Army().At(m_pDropItem.m_fromCell).Type()))
                m_pDropItem.m_toCell = toCell;
            else
                m_pDropItem.m_toCell = -1;
        }
        return true;
    }

    //
    public void SetSplitMode(boolean bSplit) {
        m_bSplit = bSplit;
    }

    public void ToggleSplitMode() {
        SetSplitMode(!m_bSplit);
    }

    public boolean IsSplitMode() {
        return m_bSplit;
    }

    public void SetArmy(iArmy pArmy, iHero pOwner, boolean bCanDismiss) {
        m_pArmy = pArmy;
        m_pOwner = pOwner;
        m_bCanDismiss = bCanDismiss;
        m_dCell = -1;

        Invalidate();
    }

    public iArmy Army() {
        return m_pArmy;
    }

    private void OnCompose() {
        iRect rc = GetScrRect();

        iRect cMetrix = new iRect(rc.x,rc.y,m_itemW,rc.h);
        for (int xx = 0; xx < 7; ++xx) {
            gApp.Surface().FrameRect(new iRect(cMetrix.x-1,cMetrix.y-1,cMetrix.w+2,cMetrix.h+2), COLOR.BLACK);
            if (m_pArmy != null) {
                ComposeCreatureButton(
                        gApp.Surface(),
                        cMetrix,
                        m_pArmy.At(xx).Type(),
                        m_pArmy.At(xx).Count(),
                        VL.EXPERT,
                        m_dCell == xx,
                        (m_pDragItem != null && xx == m_pDragItem.m_fromCell),
                        (m_pDropItem != null && xx == m_pDropItem.m_toCell)
                );
            } else {
                ComposeCreatureButton(gApp.Surface(), cMetrix, CREAT.UNKNOWN, 0, VL.EXPERT, m_dCell == xx, false, false);
            }
            cMetrix.x += cMetrix.w + 1;
        }
    }

    private void OnMouseDown(final iPoint pos) {
        if (m_pArmy == null)
            return;

        int ncell = Pos2Cell(pos);
        if (!m_pArmy.At(ncell).IsValid())
            return;

        m_dCell = ncell;
        m_dragAnchor = pos;

        Invalidate();
    }

    private void OnMouseUp(final iPoint pos) {
        if (m_pDragItem != null) {
            EndDrag();
        }
    }

    private void OnMouseClick(final iPoint pos) {
        if (m_pDragItem != null)
            return;
        if (m_pArmy != null && m_dCell != -1 && Pos2Cell(pos) == m_dCell) {
            long nt = GetTickCount();
            int nv = m_dCell;
            m_dCell = -1;
            Invalidate();

            if ( m_lastClick != 0 && nt > m_lastClick && (nt - m_lastClick) < 500) {
                m_lastClick = 0;
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.BTNDBLCLICK, nv);
                if (m_pArmy.At(nv).Type() != CREAT.UNKNOWN) {
                    iFurtSkills fs = new iFurtSkills();
                    if (m_pOwner)
                        fs = m_pOwner.GetFullFurtSkills();
                    else
                        fs.Reset();
                    iDlg_CreatInfo dlg = new iDlg_CreatInfo(
                            m_pMgr,
                            PID.NEUTRAL,
                            m_pArmy.At(nv),
                            fs,
                            m_bCanDismiss || m_pArmy.GroupCount()>1,
                            m_pArmy.MoraleModifier()
                    );
                    dlg.DoModal();
                }
            } else {
                m_lastClick = nt;
                m_pCmdHandler.iCMDH_ControlCommand(this, CCI.BTNCLICK, nv);
            }
        }
    }

    private void OnMouseTrack(final iPoint pos) {
        if (m_pDragItem != null) {
            Drag(pos);
        } else if (m_pArmy && pos.GetSqDelta(m_dragAnchor) > DRAG.DELTA && m_dCell != -1 && m_pArmy.At(m_dCell).IsValid()) {
            BeginDrag(m_dCell, m_pArmy.At(m_dCell), pos);
            m_dCell = -1;
        }
    }

    private int Pos2Cell(final iPoint pos) {
        return Math.min(((pos.x-GetScrRect().x) / m_itemW), 6);
    }

    private ArrayList<iArmyListEx> iCompList;

    private boolean            m_bCanDismiss;
    private boolean            m_bSplit;
    private int            m_dCell;
    private iArmy            m_pArmy;
    private iHero            m_pOwner;
    private int            m_itemW;
    private long            m_lastClick; // Time of last click

    private iPoint            m_dragAnchor;
    private iDragItem        m_pDragItem;
    private iDragItem        m_pDropItem;
    private iCompList        m_Competitors;
    private iCheckButton    m_pSplitBtn;
}
