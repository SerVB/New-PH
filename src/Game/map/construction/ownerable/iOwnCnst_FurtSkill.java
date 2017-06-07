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
package Game.map.construction.ownerable;

import Common.iFurtSkills;
import Common.metrics.iPoint;
import Constants.*;
import utils.tracer;

/**
 *
 */
public class iOwnCnst_FurtSkill extends iOwnCnst {

    public iOwnCnst_FurtSkill(iOwnCnstT_FurtSkill pProto, final iPoint pos) {
        super(pProto, pos);
    }

    /**
     *
     * @param newOwner Player ID.
     * @param oldOwner Player ID.
     */
    @Override
    public void OnOwnerChanged(int newOwner, int oldOwner) {
        iOwnCnstT_FurtSkill pProto = (iOwnCnstT_FurtSkill)(m_pProto);
        tracer.check(pProto != null);
        if (newOwner == oldOwner) {
            return;
        }
        iFurtSkills nAmount = pProto.FurtSkills();
        // subtract income
        if (oldOwner != PID.NEUTRAL) {
            gGame.Map().FindPlayer(oldOwner).FurtSkills() -= nAmount;
        }
        // add income
        if (newOwner != PID.NEUTRAL) {
            iPlayer newOwnerP = gGame.Map().FindPlayer(newOwner);
            // TRICK: #3 - double income from ownerables (only gold & minerals)
            iAI_Player aiOwner = DynamicCast<iAI_Player>(newOwnerP);
            if ( aiOwner && (aiOwner.m_HackedTricks & 0xa != 0)) {
                for( int n = FSK.MIN_GOLD; n != FSK.COUNT; n++ ) {
                    int val = nAmount.Value(n);
                    val = (val*2 + Math.max(val, 1) )/2;
                    nAmount.SetValue(n, val);
                }
            }
            // -------------------------
            gGame.Map().FindPlayer(newOwner).FurtSkills() += nAmount;
        }
    }

}
