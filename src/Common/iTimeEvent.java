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

package Common;


import Constants.*;

/**
 * Time-Events
 */
public class iTimeEvent {

    public String      m_name;
    public String      m_text;
    public long        m_playerMask;
    public long        m_fTime;
    public int         m_repTime;
    public iMineralSet m_minerals;

    /**
     * Конструктор.
     * @param name
     * @param text
     * @param playerMask
     * @param ftime
     * @param repTime
     * @param minerals
     */
    public iTimeEvent(String name, String text, long playerMask, long ftime, int repTime, iMineralSet minerals) {
        this.m_name = name;
        this.m_text = text;
        this.m_playerMask = playerMask;
        this.m_fTime = ftime;
        this.m_repTime = repTime;
        this.m_minerals = minerals;
    }

    /**
     * Конструктор без параметров
     */
    public iTimeEvent() {
        this.m_playerMask = PIM.ALL;
        this.m_fTime = 1;
        this.m_repTime = EFREQ.NEVER;

        m_minerals.Reset();
    }


    /**
     *
     * @param pid ID игрока.
     * @param curDay текущий день.
     * @return
     */
    public boolean IsConform(int pid, long curDay) {
        // player
        if ( (m_playerMask & (1 << pid)) == 0 )
            return false;
        //
        if (curDay == m_fTime)
            return true;
        else if (m_repTime == EFREQ.NEVER)
            return false;
        else
            return ( (curDay-m_fTime) % EVENT.FREQ_DAYS[m_repTime] ) == 0;
    }
}
