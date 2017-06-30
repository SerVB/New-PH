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
package Game.magic.overlandSpell;

import newph.metric.iPoint;
import Constants.*;
import Game.iCastle;
import Game.iHero;
import Game.iPlayer;
import newph.util.Tracer;

/**
 *
 */
public class iSpell_TownPortal extends iOverlandSpell {
    
    public iSpell_TownPortal(int spellId) {
        super(spellId);
    }
    
    @Override
    public boolean Cast(final iHero pCaster) {
        super.Cast(pCaster);
        int msl = GetSchoolLevel(m_spellId, pCaster);

        iPlayer pOwner = pCaster.Owner();
        if (pOwner.PlayerType() == PT.HUMAN) {
            if (SPELL.DESCRIPTORS[m_spellId].eff[msl].fparam != 0) {
                // Town portal
                iDlg_TownList tlDlg = new iDlg_TownList(
                        gApp.ViewMgr(),
                        pOwner,
                        gTextMgr[TRID.SELECT_DESTINATION],
                        TRID.OK
                );
                if (tlDlg.DoModal() == DRC.OK) {
                    iCastle pDest = tlDlg.Destination();
                    Tracer.check(pDest != null && pDest.Visitor() == null);
                    iPoint oldPos = pCaster.Pos();
                    gGame.Map().MoveHero(pCaster.Pos(), pDest.Pos());
                    pCaster.SetPos(pDest.Pos());
                    pCaster.ResetPath();
                    pCaster.setAngle(HERO.ORIENT[pDest.Proto().Orient()]);
                    gGame.OnHeroChanged(pCaster);
                    gGame.OnHeroTeleport(pCaster, oldPos, pDest.Pos());
                    gSfxMgr.PlaySound(CSND.TELEPORT_OUT);
                    return true;
                }
            } else {
                // Town gate
                iPoint hpos = pCaster.Pos();
                iCastle pDest = null;
                int dist = 0;
                for (iPlayer::iCtLIt cit = pOwner.CastleFirst(); cit != pOwner.CastleEnd(); ++cit) {
                    sint32 nval = iABS(hpos.x - (*cit).Pos().x) + iABS(hpos.y - (*cit).Pos().y);
                    if (!pDest || nval < dist) {
                        pDest = *cit;
                        dist = nval;
                    }
                }
                if (pDest != null && pDest.Visitor() == null) {
                    //pCaster.Deinit(false);
                    //pCaster.Init(pOwner, pDest.Pos(), HERO_ORIENT[pDest.Proto().Orient()]);
                    //gGame.Map().MoveHero(hpos, pDest.Pos());
                    gGame.Map().MoveHero(pCaster.Pos(), pDest.Pos());
                    pCaster.SetPos(pDest.Pos());
                    pCaster.ResetPath();
                    pCaster.setAngle(HERO.ORIENT[pDest.Proto().Orient()]);
                    gGame.OnHeroChanged(pCaster);
                    gGame.OnHeroTeleport(pCaster, hpos, pDest.Pos());
                    gSfxMgr.PlaySound(CSND.TELEPORT_OUT);
                    return true;
                }
            }
        } else {}
        return false;
    }
}
