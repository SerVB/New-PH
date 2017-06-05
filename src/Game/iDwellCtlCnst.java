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
import Constants.*;
import ConstantsGame.*;

/**
 *
 */
public class iDwellCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iDwellCtlCnst );

    public iDwellCtlCnst(iCastle _pCastle, int _cnst, int _crType) {
        super(_pCastle, _cnst);

        this.crType = _crType;
        this.crCount = CREAT.DESC[_crType].growth;
    }

    @Override
    public final String Desc() {
        return String.format(gTextMgr[TRID.CTLCNST_DWELLINGS_DESC], Name().CStr(), gTextMgr[TRID.CREATURE_PEASANT_F1+crType*3+2]);
    }

    @Override
    public void OnBuild() {
        crCount = Growth();
    }

    @Override
    public void NewWeek(final iWeekDesc weekDesk) {
        if (weekDesk.wkType == WeekDesc.Normal) {
            crCount += Growth();
        } else if (weekDesk.wkType == WeekDesc.Plague) {
            crCount /= 2;
        } else if (weekDesk.wkType == WeekDesc.CreatPlus) {
            crCount += Growth();
            if (weekDesk.crType == crType) {
                crCount += 5;
            }
        } else if (weekDesk.wkType == WeekDesc.CreatDoubles) {
            crCount += Growth();
            if (weekDesk.crType == crType) {
                crCount *= 2;
            }
        }
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        // Creatures quantity
        buff.Write(crCount);
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        // Creatures quantity
        crCount = (int) buff.Read();
    }

    public int Growth() {
        int res = CREAT.DESC[crType].growth;

        // try to find dwel modifiers
        for (int xx = 0; xx < pCastle.Cnsts().Count(); ++xx) {
            final iCtlCnst pCnst = pCastle.Cnsts().At(xx);
            if (pCnst.IsBuilt() && CTLCNSTS.DESC[pCnst.Type()].type == CCT.DWELL_ENC) {
                iDwellEncCtlCnst pDwellEnc = (iDwellEncCtlCnst) pCnst;
                if (pDwellEnc.DwellingType() == Type()) {
                    res += pDwellEnc.GrowthMod();
                }
            }
        }

        return res;
    }

    public final int CrType() {
        return crType;
    }

    public final int CrCount() {
        return crCount;
    }


    private final int   crType;
    private int         crCount;

}
