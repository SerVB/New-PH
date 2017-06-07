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
import Common.metrics.iPoint;
import Constants.*;
import Game.iHero;

/**
 *
 */
public class iMapItem_Artifact extends iMapItem {

    /**
     *
     * @param pos
     * @param aid Artifact ID.
     */
    public iMapItem_Artifact(final iPoint pos, int aid) {
        super(pos, MAPITEM.ARTIFACT, gGame.ItemMgr().m_artMgr[aid].Sprite());

        this.m_artId = aid;
    }

    public int ArtifactId() {
        return m_artId;
    }

    @Override
    public boolean Activate(iHero pHero, boolean bActive, boolean bShowMessage) {
        if (!super.Activate(pHero, bActive, bShowMessage)) {
            return false;
        }
        if (bActive) {
            iDlg_ArtInfo dlg = new iDlg_ArtInfo(gApp.ViewMgr(), m_artId, pHero);
            dlg.DoModal();
            gSfxMgr.PlaySound(CSND.PICKUP01 + (m_Pos.x+m_Pos.y) % 5);
            pHero.Artifacts().Push(m_artId);
        } else {
            pHero.Artifacts().SmartPush(m_artId);
        }

        return true;
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        buff.Write(m_artId);
    }

    @Override
    public int NameKey() {
        return TRID.MAPRES_ARTIFACT;
    }

//    public IMPL_TYPEAWARE( iMapItem_Artifact );

    private final int m_artId;

}
