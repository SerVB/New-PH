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

import newph.metric.iPoint;
import Constants.*;

/**
 * Animation Object.
 */
public class iAniObj_FadeOut extends iAniObj {

    public IMPL_TYPEAWARE( iAniObj_FadeOut );

    public iAniObj_FadeOut(IAniCB pcb, SpriteId sid, final iPoint pos) {
        super(pcb, 0.8 / DEF.ANI_SPEED, pos);
        this.m_Sid = sid;
    }

    /**
     * Get Animation Info.
     * @param sid
     * @param a
     */
    public void GetAniInfo(SpriteId sid, Integer a) {
        sid = m_Sid;
        a = new iCLAMP<Byte>( 0, 63, (byte) Math.ceil((m_Duration - m_Timer) * 64.0 / m_Duration) );
    }


    private SpriteId    m_Sid;
}
