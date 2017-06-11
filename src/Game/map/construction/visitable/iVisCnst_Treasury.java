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

import Common.iDynamicBuffer;
import Common.iFormat;
import Common.iSpellFilter;
import Constants.*;
import Game.iHero;
import collections.simple.iRewardsCtr;
import collections.extended.iSpellList;
import utils.serialize;
import utils.tracer;

/**
 *
 */
public class iVisCnst_Treasury extends iVisCnst {

    public iVisCnst_Treasury(iVisCnstT_Treasury pProto, final iPoint pos) {
        super(pProto, pos, true);

        m_bVisited = false;
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (Visited(pVisitor)) {
            if (bActive) {
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        iFormat.format(
                                gTextMgr[TRID.OBJ_TREASURY_VISITED],
                                gTextMgr[m_pProto.NameKey()]
                        ),
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        } else {
            if (bActive) {
                iDlg_Rewards dlg = new iDlg_Rewards(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[TRID.OBJ_TREASURY_DISCOVER],
                        pVisitor,
                        m_rewards
                );
                dlg.DoModal();
            }
            for (int xx = 0; xx < m_rewards.size(); ++xx) {
                pVisitor.ReceiveReward(m_rewards.get(xx));
            }
            m_bVisited = true;
        }
        super.OnActivate(pVisitor, bActive);
    }

    @Override
    public void Serialize(iDynamicBuffer dbuff) {
        // Common data serialization
        super.Serialize(dbuff);
        // Is visited
        dbuff.Write(m_bVisited ? 1 : 0);
        // Rewards
        serialize.Serialize(dbuff, m_rewards);
    }

    @Override
    public int Unserialize(iDynamicBuffer dbuff) {
        int flag = super.Unserialize(dbuff);
        if (flag == 0) {
            // Init by default
            tracer.check(m_Guard.Empty());
            iVisCnstT_Treasury pProto = DynamicCast<iVisCnstT_Treasury>(m_pProto);
            tracer.check(pProto != null);
            iTrVarCtr tvar = pProto.GetVarCtr();
            m_bVisited = false;
            int val = gGame.Map().Rand(100);
            boolean bSel = false;
            for (int xx = 0; xx < tvar.VariantsCount(); ++xx) {
                if (val <= tvar.GetVariant(xx).probability) {
                    m_Guard = tvar.GetVariant(xx).guards;
                    m_rewards = tvar.GetVariant(xx).rewards;
                    bSel = true;
                    break;
                }
                val -= tvar.GetVariant(xx).probability;
            }
            tracer.check(bSel);
            // Initialize reward item (if necessary)
            for (int xx = 0; xx < m_rewards.size(); ++xx) {
                if (m_rewards.get(xx).m_type == RIT.ARTIFACT) {
                    // random artifact
                    m_rewards.get(xx).m_fParam = gGame.ItemMgr().m_artMgr.SelectRandomArtifact((ART_LEVEL_TYPE)m_rewards.get(xx).m_fParam);
                } else if (m_rewards.get(xx).m_type == RIT.MAGICSPELL) {
                    // random spell
                    iSpellList spells = new iSpellList();
                    if (m_rewards.get(xx).m_fParam == 0) {
                        SelectSpells(
                                spells,
                                new iSpellFilter(SPT.MASK_ALL, SPL.MASK_ALL, MST.MASK_ALL),
                                1,
                                MSP.INVALID
                        );
                    }
                    else {
                        SelectSpells(
                                spells,
                                new iSpellFilter(
                                        SPT.MASK_ALL,
                                        SPELL.LEVEL_MASKS[m_rewards.get(xx).m_fParam-1],
                                        MST.MASK_ALL
                                ),
                                1,
                                MSP.INVALID
                        );
                    }
                    tracer.check(!spells.isEmpty());
                    m_rewards.get(xx).m_fParam = spells.get(0).id;
                }
            }
        } else {
            // Is visited
            int bVisited = (int) dbuff.Read();
            m_bVisited = bVisited > 0;
            // Rewards
            serialize.Unserialize(dbuff, m_rewards);
        }
        return flag;
    }

    @Override
    public boolean Visited(final iHero pVisitor) {
        return m_bVisited;
    }

    private boolean        m_bVisited;
    private iRewardsCtr    m_rewards;

}
