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

import Common.iArmy;
import Common.iPoint;
import Common.iRewardItem;
import Game.iHero;
import Game.map.iBaseMapObject;
import collections.simple.iRewardsCtr;

/**
 *
 */
public class iMapEvent extends iBaseMapObject implements iIListNode {

    public iMapEvent(final iPoint pos) {
        super(pos, false);
    }

    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {

        // Guards
        if (!m_guard.Empty()) {
            // Show Attack message
            if (bActive && bShowMessage) {
                iTextDlg dlg = new iTextDlg(
                        gApp.ViewMgr(),
                        "",
                        (m_attackMessage.isEmpty()) ? gTextMgr[TRID.MSG_MAPEVENT_ATTACK] : m_attackMessage,
                        pHero.Owner().PlayerId()
                );
                dlg.DoModal();
            }
            // Here is battle
            iBattleInfo bi = new iBattleInfo(pHero, this);
            gGame.BeginBattle(bi);
            return false;
        } else if (bActive && bShowMessage && !m_attackMessage.isEmpty()) {
            iTextDlg dlg = new iTextDlg(
                    gApp.ViewMgr(),
                    "",
                    m_attackMessage,
                    pHero.Owner().PlayerId()
            );
            dlg.DoModal();
        }

        // Rewards
        if (!m_rewards.isEmpty()) {
            // Show Reward message
            if (bActive) {
                iDlg_Rewards dlg = new iDlg_Rewards(gApp.ViewMgr(), "", m_rewardMessage, pHero, m_rewards);
                dlg.DoModal();
            }

            for (iRewardItem reward : m_rewards) {
                pHero.ReceiveReward(reward);
            }
        } else if (bActive && !m_rewardMessage.isEmpty()) {
            iTextDlg dlg = new iTextDlg(gApp.ViewMgr(), "", m_rewardMessage, pHero.Owner().PlayerId());
            dlg.DoModal();
        }
        return true;
    }

    public iArmy Guard() {
        return m_guard;
    }

    public String RewardMessage() {
        return m_rewardMessage;
    }

    public String AttackMessage() {
        return m_attackMessage;
    }

    public int PlayerMask() {
        return m_playerMask;
    }

    public iRewardsCtr Rewards() {
        return m_rewards;
    }

//    public IMPL_TYPEAWARE( iMapEvent );


    private iRewardsCtr    m_rewards;
    private int        m_playerMask;
    private String    m_rewardMessage;
    private String    m_attackMessage;
    private iArmy        m_guard;
}
