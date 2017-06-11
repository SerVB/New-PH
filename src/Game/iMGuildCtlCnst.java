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

import Common.iDynamicBuffer;
import utils.tracer;
import Constants.*;
import collections.extended.iSpellList;
import entries.iSpellEntry;
import java.util.ArrayList;

/**
 *
 */
public class iMGuildCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iMGuildCtlCnst );

    public iMGuildCtlCnst(iCastle _pCastle, int _cnst, int _level) {
        super(_pCastle, _cnst);

        this.level = _level;
    }

    @Override
    public final String Desc() {
        return gTextMgr[TRID.CTLCNST_COMMON_MGUILD_DESC];
    }

    @Override
    public void OnInit() {
        InitSpells();
    }

    @Override
    public void NewDay() {
        if (pCastle.Visitor() && pCastle.Visitor().ManaPts() < pCastle.Visitor().MaxManaPts()) {
            pCastle.Visitor().ManaPts() = pCastle.Visitor().MaxManaPts();
        }
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        // Spells
        buff.Write(spells.size());
        for (int xx=0; xx<spells.size(); ++xx) {
            buff.Write(spells.get(xx).id);
        }
        // Visitors
        buff.Write(visitors.size());
        for (int xx=0; xx < visitors.size(); ++xx) {
            buff.Write(visitors.get(xx));
        }
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        // Spells
        int spcnt = (int) buff.Read();
        spells.clear();
        while (spcnt-- > 0) {
            int spell = (int) buff.Read();
            spells.add(new iSpellEntry(spell));
        }
        // Visitors
        int viscnt = (int) buff.Read();
        visitors.clear();
        while (viscnt-- > 0) {
            int visitor = (int) buff.Read();
            visitors.add(visitor);
        }
    }

    @Override
    public void OnVisitorEnter(iHero pHero) {
        tracer.check(pHero != null);
        for (int xx = 0; xx < spells.size(); ++xx) {
            if (pHero.CanLearnSpell(spells.get(xx).id)) {
                pHero.LearnSpell(spells.get(xx).id);
            }
        }
        visitors.AddUnique(pHero.Proto().m_protoId);
    }

    @Override
    public final boolean Visited(final iHero pHero) {
        return (visitors.Find(pHero.Proto().m_protoId) != -1);
    }

    public final int SpellsCount() {
        return spells.size();
    }

    public final int Spell(int idx) {
        tracer.check( idx < spells.size() );
        return spells.get(idx).id;
    }

    public final iSpellList SpellList() {
        return spells;
    }

//    public friend class iLibraryCtlCnst;


    protected void InitSpells() {
        spells.clear();
        int ctype = pCastle.Proto().Type();
        int count = CTL.MAGE_GUILD_SPELLS[ctype][level-1];

        int reqSpell = MSP.INVALID;

        // Process exceptions
        if (ctype == CTLT.NECROPOLIS && level == 2) {
            reqSpell = MSP.DEATHRIPPLE;
        } else if (ctype == CTLT.FORTRESS && level == 2) {
            reqSpell = MSP.SUMMONSPRITES;
        }

        SelectSpells(spells, CTL.MAGE_GUILD_FILTER[ctype][level-1], count, reqSpell);
    }

    protected void RefreshSpells() {
        InitSpells();
        visitors.clear();
        if (pCastle.Visitor() != null) {
            OnVisitorEnter(pCastle.Visitor());
        }
    }


    private ArrayList<Integer>  visitors;
    private iSpellList          spells;
    private final int           level;

}
