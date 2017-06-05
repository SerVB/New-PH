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

/**
 *
 */
public class iDwellEncCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iDwellEncCtlCnst );

    public iDwellEncCtlCnst(iCastle _pCastle, int _cnst, int _dwel, int _mod) {
        super(_pCastle, _cnst);

        this.dwel = _dwel;
        this.mod = _mod;

        int aa = 0;
    }

    @Override
    public final String Desc() {
        return String.format(gTextMgr[TRID.CTLCNST_DWELL_ENC_DESC], Name(), gTextMgr[TRID.CREATURE_PEASANT_F3 + CTLCNSTS.DESC[dwel].fparam * 3], mod);
    }

    public final int DwellingType() {
        return dwel;
    }

    public final int GrowthMod() {
        return mod;
    }


    private final int   dwel;
    private final int   mod;
}
