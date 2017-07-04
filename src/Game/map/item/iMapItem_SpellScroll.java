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

import Constants.map.MAPITEM;
import Common.iDynamicBuffer;
import Common.iFormat;
import newph.core.metric.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_SpellScroll extends iMapItem {

    public iMapItem_SpellScroll(final iPoint pos, int spellId) {
        super(pos, MAPITEM.SPELLSCROLL, PDGG.RES_SPELL_SCROLL);

        this.m_spellId = spellId;
    }

    public int SpellId() {
        return m_spellId;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        if (bActive) {
            iIconDlg dlg = new iIconDlg(
                    gApp.ViewMgr(),
                    gTextMgr[TRID.MAPRES_SPELLSCROLL],
                    iFormat.format(
                            gTextMgr[TRID.MAPRES_SPELL_SCROLL_MSG],
                            gTextMgr[TRID.SPNAME_MAGICARROW + m_spellId]
                    ),
                    PDGG.SPELL_ICONS + m_spellId,
                    pHero.Owner().PlayerId()
            );
            dlg.DoModal();
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
        }

        pHero.LearnSpell(m_spellId, true);
        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_spellId);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_SPELLSCROLL;
    }

//    public IMPL_TYPEAWARE( iMapItem_SpellScroll );

    private int    m_spellId;
}
