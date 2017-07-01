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
package helperFunction;

import newph.metric.iPoint;
import Constants.*;
import Game.iBattleGroup;
import Game.magic.combatSpell.iCombatSpell;
import newph.staticFunction.Tracer;

/**
 *
 */
public class Magic {
    
    public final static boolean AddSpellToList(
            final iBattleGroup pTarget,
            final iCombatSpell pSpell,
            final int duration,
            final int param
    ) {
        for (int xx = 0; xx < pTarget.SpellList().size(); ++xx) {
            if (pTarget.SpellList().get(xx).m_pSpell.Id() == pSpell.Id()) {
                pTarget.SpellList().get(xx).m_duration = duration;
                return false;
            }
        }
        
        pTarget.AddSpell(pSpell, duration, param);
        return true;
    }
    
    public final static iPoint FindSummonCell(
            final iBattleMap pmap,
            final int side,
            final boolean bDoubleSize
    ) {
        int x;
        int found = 0;
        int[] ylist = new int[11];

        if (side == iBattleMember.Assaulter) {
            x = 0;
        } else {
            x = 12;
        }
        
        if (bDoubleSize) {
            x += TAIL.OFFSET[side == 0 ? 1 : 0];
        }

        while (found == 0) {
            for (int yy=0; yy<11; ++yy) {
                int pp = pmap.GetAt(x, yy);
                if (
                        (pmap.GetAt(x, yy)&0x7F) == CT.PASSABLE &&
                        (!bDoubleSize ||
                        (pmap.GetAt(x + TAIL.OFFSET[side],yy)&0x7F)== CT.PASSABLE)
                ) {
                    ylist[found++] = yy;
                }
            }
            if (found == 0) {
                x += TAIL.OFFSET[side == 0 ? 1 : 0];
            }
        }

        Tracer.check(found>0 && found<=11);
        return new iPoint(x, ylist[gGame.Map().Rand(found)]);
    }
    
}
