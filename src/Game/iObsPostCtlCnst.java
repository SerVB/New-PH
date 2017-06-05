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
public class iObsPostCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iObsPostCtlCnst );

    public iObsPostCtlCnst(iCastle _pCastle, int _cnst) {
        super(_pCastle, _cnst);
    }

    @Override
    public void OnBuild() {
        pCastle.ScoutMod() += 4;
        if (pCastle.Owner() != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(pCastle.Owner());
            pPlayer.FurtSkills().plusValue(FSK.ACTPTS, 5);
            pPlayer.UpdateFogMap();
        }
    }

    @Override
    public void OnOwnerChanged(int newOwner, int oldOwner) {
        if (oldOwner != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(oldOwner);
            pPlayer.FurtSkills().minusValue(FSK.ACTPTS, 5);
        }

        if (newOwner != PID.NEUTRAL) {
            iPlayer pPlayer = gGame.Map().FindPlayer(newOwner);
            pPlayer.FurtSkills().plusValue(FSK.ACTPTS, 5);
        }
    }

}
