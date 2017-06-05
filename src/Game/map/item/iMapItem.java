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

import Constants.DRC;
import Common.iArmy;
import Common.iDynamicBuffer;
import Common.iPoint;
import Constants.*;
import Game.map.iBaseMapObject;
import Game.iHero;

/**
 *
 */
public class iMapItem extends iBaseMapObject implements iIListNode {

    /**
     *
     * @param pos
     * @param mit Map Item Type.
     * @param sid Sprite ID.
     */
    public iMapItem(final iPoint pos, int mit, final int sid) {
        super(pos, true);

        this.m_mapItemType = mit;
        this.m_spriteId = sid;
    }

    public int Type() {
        return m_mapItemType;
    }

    public int Sprite() {
        return m_spriteId;
    }

    public String Message() {
        return m_message;
    }

    public iArmy Guard() {
        return m_Guard;
    }

    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (bActive) {
            if (bShowMessage && !m_message.isEmpty()) {
                iTextDlg dlg = new iTextDlg(gApp.ViewMgr(), "", m_message, pHero.Owner().PlayerId());
                dlg.DoModal();
            }
            if (!Guard().Empty()) {
                if (bActive) {
                    iDlg_FightGuard dlg = new iDlg_FightGuard(gApp.ViewMgr(), gTextMgr[NameKey()], m_Guard, PID.NEUTRAL, VL.EXPERT);
                    if (dlg.DoModal() == DRC.YES) {
                        iBattleInfo bi = new iBattleInfo(pHero, this);
                        gGame.BeginBattle(bi);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void Serialize(iDynamicBuffer buff) {}

    public abstract int NameKey();

//    public IMPL_TYPEAWARE( iMapItem );


    protected iArmy            m_Guard;
    protected String        m_message;
    protected int        m_spriteId;
    protected int    m_mapItemType;
}
