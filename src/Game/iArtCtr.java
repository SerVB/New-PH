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

import utils.tracer;
import Constants.*;
import ConstantsGame.*;
import java.util.ArrayList;

/**
 *
 */
public class iArtCtr {

    public iArtCtr(iHero pOwner) {
        this.m_pOwner = pOwner;
        for (int xx = 0; xx < AC.COUNT; ++xx) {
            m_Dressed.add(new iArtCell(xx));
        }
    }

    //
    public int Pop() {
        tracer.check(BackPackCount());
        System.err.println("Что означает pop? Удалить первый или последний элемент?");
//        return m_BackPack.Pop();
        return m_BackPack.remove(m_BackPack.size() - 1);
    }

    //
    public final boolean HasArtType(int artt) {
        for (int xx = 0; xx < m_Dressed.size(); ++xx) {
            if (!m_Dressed.get(xx).Empty() && gGame.ItemMgr().m_artMgr[m_Dressed.get(xx).artId].Type() == artt)
                return true;
        }
        return false;
    }

    public final boolean CanAttach(int artId) {
        return gGame.ItemMgr().m_artMgr.get(artId).CanAttach(m_pOwner);
    }

    public void Push(int artId) {
        // try to dress
        if (CanAttach(artId)) {
            for (int xx = 0; xx < m_Dressed.size(); ++xx){
                if (HERO.ART_CELL_ASSIGN[m_Dressed.get(xx).cell] == gGame.ItemMgr().m_artMgr.get(artId).Assign() &&
                    m_Dressed.get(xx).Empty()) {
                    PushDressed(artId, xx);
                    return;
                }
            }
        }

        // push to back pack
        PushToBackPack(artId);
    }

    public final boolean CanSmartPush(int artId) {
        iArtifactMgr artMgr = gGame.ItemMgr().m_artMgr;
        // find cell with most weak weared artifact
        int cell = AC.UNDEFINED;
        int level = artMgr.get(artId).Level();
        if (CanAttach(artId) && m_pOwner.NeedArtifact(artId)) {
            for (int xx = 0; xx < m_Dressed.size(); ++xx){
                if (HERO.ART_CELL_ASSIGN[m_Dressed.get(xx).cell] == artMgr[artId].Assign()) {
                    if (m_Dressed.get(xx).Empty()) {
                        cell = xx;
                        level = ART_LEVEL.NONE;
                        break;
                    } else if (artMgr.get(m_Dressed.get(xx).artId).Level() < level) {
                        cell = xx;
                        level = artMgr.get(m_Dressed.get(xx).artId).Level();
                    }
                }
            }
        }

        return (cell != AC.UNDEFINED);
    }

    public void SmartPush(int artId) {
        iArtifactMgr artMgr = gGame.ItemMgr().m_artMgr;
        // find cell with most weak weared artifact
        int cell = AC.UNDEFINED;
        int level = artMgr.get(artId).Level();
        if (CanAttach(artId) && m_pOwner.NeedArtifact(artId)) {
            for (int xx = 0; xx < m_Dressed.size(); ++xx){
                if (HERO.ART_CELL_ASSIGN[m_Dressed.get(xx).cell] == artMgr[artId].Assign()) {
                    if (m_Dressed.get(xx).Empty()) {
                        cell = xx;
                        level = ART_LEVEL.NONE;
                        break;
                    } else if (artMgr.get(m_Dressed.get(xx).artId).Level() < level) {
                        cell = xx;
                        level = artMgr.get(m_Dressed.get(xx).artId).Level();
                    }
                }
            }
        }

        if (cell == AC.UNDEFINED) {
            // push to back pack
            PushToBackPack(artId);
        } else {
            if (!m_Dressed.get(cell).Empty()) {
                // detach
                int oldArt = Pop(true, cell);
                // move to backpack
                PushToBackPack(oldArt);
            }
            // wear
            PushDressed(artId, cell);
        }
    }

    public void PushDressed(int artId, int cell) {
        tracer.check(m_Dressed.get(cell).Empty() && CanAttach(artId));

        m_Dressed.get(cell).artId = artId;
        gGame.ItemMgr().m_artMgr.get(artId).Attach(m_pOwner);
    }

    public void PushToBackPack(int artId) {
        m_BackPack.add(artId);
    }

    public int Pop(boolean bDressed, int idx) {
        if (bDressed) {
            tracer.check( idx < m_Dressed.size() && !m_Dressed.get(idx).Empty());
            int result = m_Dressed.get(idx).artId;
            m_Dressed.get(idx).Reset();
            gGame.ItemMgr().m_artMgr.get(result).Dettach(m_pOwner);
            return result;
        } else {
            tracer.check(idx < m_BackPack.size());
            int result = m_BackPack.get(idx);
            m_BackPack.remove(idx);
            return result;
        }
    }

    public void PopBackPack(ArrayList<Integer> artList) {
        artList.addAll(m_BackPack);
        m_BackPack.clear();
    }

    public void PopAll(ArrayList<Integer> artList) {
        // Dressed artifacts
        for (int xx = 0; xx < m_Dressed.size(); ++xx) {
            if (m_Dressed.get(xx).artId != 0xFFFF) {
                artList.add(m_Dressed.get(xx).artId);
                gGame.ItemMgr().m_artMgr.get(m_Dressed.get(xx).artId).Dettach(m_pOwner);
                m_Dressed.get(xx).artId = 0xFFFF;
            }
        }

        // BackPack artifacts
        PopBackPack(artList);
    }

    public final void GetAll(ArrayList<Integer> artList) {
        // Dressed artifacts
        for (int xx = 0; xx < m_Dressed.size(); ++xx) {
            if (m_Dressed.get(xx).artId != 0xFFFF) {
                artList.add(m_Dressed.get(xx).artId);
            }
        }
        // BackPack artifacts
        artList.addAll(m_BackPack);
    }

    // Backpack
    public final int BackPackCount() {
        return m_BackPack.size();
    }

    public final int BackPackItem(int idx) {
        tracer.check(idx < m_BackPack.size());
        return m_BackPack.get(idx);
    }

    public final iArtCell DressedItem(int cell) {
        return m_Dressed.get(cell);
    }

    public ArrayList<Integer> BackPack() {
        return m_BackPack;
    }

    public final int TotalCount() {
        int res = 0;
        // Dressed artifacts
        for (int xx = 0; xx < m_Dressed.size(); ++xx)
            if (m_Dressed.get(xx).artId != 0xFFFF)
                res++;
        // BackPack artifacts
        res += m_BackPack.size();

        return res;
    }

    private final ArrayList<iArtCell> m_Dressed = new ArrayList<>();
    private final ArrayList<Integer> m_BackPack = new ArrayList<>();
    private iHero            m_pOwner;
}
