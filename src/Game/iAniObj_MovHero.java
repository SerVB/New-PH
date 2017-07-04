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

import newph.core.metric.iPoint;
import Constants.*;

/**
 * Animation Object.
 */
public class iAniObj_MovHero extends iAniObj {

    public IMPL_TYPEAWARE( iAniObj_MovHero );

    public iAniObj_MovHero(IAniCB pcb, iHero pHero) {
        super(pcb, pHero.Path().GetStepLength() / HERO.SPEED_VAL[gSettings.GetEntryValue(CET_HEROSPEED)], pHero.Pos());

        this.m_aniSpeed = HERO.SPEED_VAL[gSettings.GetEntryValue(CET_HEROSPEED)];
        this.m_pHero = pHero;
        this.m_tgtPos = pHero.Path().GetStepPos();
    }

    public iPoint PtOffset() {
        return new iPoint(
            (int)Math.ceil( cellOffset[m_pHero.Angle()].x * (1.0 * m_Timer / m_Duration) ),
            (int)Math.ceil( cellOffset[m_pHero.Angle()].y * (1.0 * m_Timer / m_Duration) )
        );
    }

    public void GetAniInfo(SpriteId sid, iPoint offset) {
        sid = ((long)Math.ceil(m_Timer * 15.0 * m_aniSpeed)) % 8 + 1;
        offset = PtOffset();
    }

    public iHero Hero() {
        return m_pHero;
    }

    public final iPoint TargetPos() {
        return m_tgtPos;
    }


    private iPoint    m_tgtPos;
    private iHero    m_pHero;
    private double    m_aniSpeed;
}
