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

import Constants.*;
import ConstantsGame.*;

/**
 *
 */
public class iBattleUnit_Turret extends iBattleUnit {

    public IMPL_TYPEAWARE( iBattleUnit_Turret );

    public iBattleUnit_Turret(iBattleMember pOwner, int et, iBattleWrapper pWrapper) {
        super(UnitType.Turret, pOwner);

        this.m_et = et;
        this.m_pWrapper = pWrapper;
    }

    @Override
    public final boolean IsAlive() {
        return true;
    }

    @Override
    public final boolean CanWait() {
        return false;
    }

    @Override
    public final int Speed() {
        return 0xFF;
    }

    public void Attack(iBattleGroup pTarget) {
        // calculate damage
        int damage = 50;

        m_pWrapper.AddLogEvent("#S0" + GetUnitsColor(m_pOwner.Owner()) + String.format(gTextMgr[TRID.MSG_BAT_TURRET_DODAMAGE],damage));
        String cellMsg = FormatNumber(damage);

        int dead = pTarget.ReceiveDamage(damage, true);

        // Increase experience value (according with damage provided to enemy troops)
        //if (pTarget.m_pOwner.GetSide() != m_pOwner.GetSide()) m_experience += damage;

        //_LOG(iFormat(_T("%s do %d damage to %s"),_T("Turret"),damage,gTextMgr[TRID_CREATURE_PEASANT_F2+pTarget.Type()*3]).CStr());
    }

    public iBattleWrapper       m_pWrapper;
    public int                  m_et; // iCastleFort.ElementType
}
