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
import Common.iPoint;
import Constants.*;
import Game.iHero;
import java.util.ArrayList;
import utils.ChangeableString;

/**
 *
 */
public class iVisCnst_WitchHut extends iVisCnst {

    public iVisCnst_WitchHut(iVisCnstT_WitchHut pProto, final iPoint pos) {
        super(pProto, pos, true);

        this.m_skill = SECSK.NONE;
    }

    @Override
    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (Visited(pVisitor)) {
            if (bActive) {
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        iFormat.format(
                                gTextMgr[TRID.OBJ_WITCHHUT_MSG2],
                                gTextMgr[TRID.HSKILL_ESTATES+m_skill]
                        ),
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        } else {
            if (pVisitor.SecSkills().SkillLevel(m_skill) == SSLVL.NONE && pVisitor.SecSkills().Count() < 8) {
                if (bActive) {
                    iWitchHutDlg whdlg = new iWitchHutDlg(gApp.ViewMgr(), m_skill, pVisitor.Owner().PlayerId());
                    whdlg.DoModal();
                    //iIconDlg idlg(&gApp.ViewMgr(), gTextMgr[m_pProto.NameKey()], iFormat(gTextMgr[TRID_OBJ_WITCHHUT_MSG1], gTextMgr[TRID_HSKILL_ESTATES+m_skill]), PDGG_SEC_SKILLS + m_skill, pVisitor.Owner().PlayerId());
                    //idlg.DoModal();
                }
                pVisitor.SecSkills().Set(m_skill, SSLVL.BASIC);
            } else {
                if (bActive) {
                    iTextDlg tdlg = new iTextDlg(
                            gApp.ViewMgr(),
                            gTextMgr[m_pProto.NameKey()],
                            iFormat.format(
                                    gTextMgr[TRID.OBJ_WITCHHUT_MSG3],
                                    gTextMgr[TRID.HSKILL_ESTATES+m_skill]
                            ),
                            pVisitor.Owner().PlayerId()
                    );
                    tdlg.DoModal();
                }
            }
        }
        m_visPlayers.AddUnique(pVisitor.Owner().PlayerId());

        super.OnActivate(pVisitor, bActive);
    }

    @Override
    public void Serialize(iDynamicBuffer dbuff) {
        // Common data serialization
        super.Serialize(dbuff);
        // Write skill
        dbuff.Write(m_skill);
        // Visited players
        dbuff.Write(m_visPlayers.size());
        for (int xx = 0; xx < m_visPlayers.size(); ++xx) {
            dbuff.Write(m_visPlayers.get(xx));
        }
    }

    @Override
    public int Unserialize(iDynamicBuffer dbuff) {
        int flag = super.Unserialize(dbuff);
        if (flag == 0) {
            // Init by default
            do {
                m_skill = gGame.Map().Rand(SECSK.COUNT);
            } while (m_skill == SECSK.NECROMANCY || m_skill == SECSK.LEADERSHIP);
        } else {
            // Skill
            int skill = (int) dbuff.Read();
            m_skill = skill;
            // Visited players
            int vpcnt = (int) dbuff.Read();
            for (int xx = 0; xx < vpcnt; ++xx) {
                int vpid = (int) dbuff.Read();
                m_visPlayers.add(vpid);
            }
        }
        return flag;
    }

    @Override
    public boolean CustomPopupText(final iHero pVisitor, ChangeableString ctext) {
        if (pVisitor && m_visPlayers.Find(pVisitor.Owner().PlayerId()) != -1 ) {
            ctext.changeTo(gTextMgr[TRID.HSKILL_ESTATES+m_skill]);
            return true;
        }
        return false;
    }

    private int                 m_skill;
    private ArrayList<Integer>  m_visPlayers = new ArrayList();
}
