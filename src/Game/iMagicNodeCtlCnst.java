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

import newph.staticFunction.Tracer;
import Constants.*;

/**
 *
 */
public class iMagicNodeCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iMagicNodeCtlCnst );

    public iMagicNodeCtlCnst(iCastle _pCastle, int _cnst) {
        super(_pCastle, _cnst);
        Tracer.check(_cnst == CTLCNST.MAGICNODE);
    }

    @Override
    public void OnBuild() {
        if (pCastle.Owner() != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(pCastle.Owner());
            pPlayer.FurtSkills().plusValue(FSK.MANAPTS, 1);
        }
    }

    @Override
    public void OnOwnerChanged(int newOwner, int oldOwner) {
        if (oldOwner != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(oldOwner);
            pPlayer.FurtSkills().minusValue(FSK.MANAPTS, 1);
        }

        if (newOwner != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(newOwner);
            pPlayer.FurtSkills().plusValue(FSK.MANAPTS, 1);
        }
    }

}
