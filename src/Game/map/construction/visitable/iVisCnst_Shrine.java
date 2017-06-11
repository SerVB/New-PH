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
import Constants.MSP;
import Constants.*;
import Game.iHero;
import collections.extended.iSpellList;
import java.util.ArrayList;
import utils.ChangeableString;
import utils.tracer;

/**
 *
 */
public class iVisCnst_Shrine extends iVisCnst {

    public iVisCnst_Shrine(iVisCnstT_Shrine pProto, final iPoint pos) {
        super(pProto, pos, true);

        m_spell = MSP.INVALID;
    }

    public void OnActivate(iHero pVisitor, boolean bActive) {
        if (pVisitor.CanLearnSpell(m_spell)) {
            if (bActive) {
                iIconDlg idlg = new iIconDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.Message()] + "\"" + gTextMgr[TRID.SPNAME_MAGICARROW+m_spell] + "\".",
                        PDGG.SPELL_ICONS + m_spell,
                        pVisitor.Owner().PlayerId()
                );
                idlg.DoModal();
            }
            pVisitor.LearnSpell(m_spell);
        } else {
            if (bActive) {
                iTextDlg tdlg = new iTextDlg(
                        gApp.ViewMgr(),
                        gTextMgr[m_pProto.NameKey()],
                        gTextMgr[m_pProto.Message()] + "\"" + gTextMgr[TRID.SPNAME_MAGICARROW + m_spell] + "\". " +
                                gTextMgr[TRID.OBJ_SHRINE_NOWISDOM],
                        pVisitor.Owner().PlayerId()
                );
                tdlg.DoModal();
            }
        }
        m_visPlayers.AddUnique(pVisitor.Owner().PlayerId());

        super.OnActivate(pVisitor, bActive);
    }

    @Override
    public void Serialize(iDynamicBuffer dbuff) {
        // Common data serialization
        super.Serialize(dbuff);
        // Write spell
        dbuff.Write(m_spell);
        // Visited players
        dbuff.Write(m_visPlayers.size());
        for (int player : m_visPlayers) {
            dbuff.Write(player);
        }
    }

    @Override
    public int Unserialize(iDynamicBuffer dbuff) {
        int flag = super.Unserialize(dbuff);
        if (flag == 0) {
            // Init by default
            iSpellList spells = new iSpellList();
            SelectSpells(spells, SpellFilter(SPT.MASK_ALL, SPELL.LEVEL_MASKS[m_pProto.SpellLevel()], MST.MASK_ALL), 1, MSP.INVALID);
            tracer.check(!spells.isEmpty());
            m_spell = spells.get(0).id;
        } else {
            // Skill
            int spell = (int) dbuff.Read();
            m_spell = spell;
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
            ctext.changeTo(gTextMgr[TRID.SPNAME_MAGICARROW+m_spell]);
            return true;
        }
        return false;
    }

    public int Spell() {
        return m_spell;
    }


    private int m_spell;
    private final ArrayList<Integer> m_visPlayers = new ArrayList();
}
