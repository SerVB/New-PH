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
 * all
 * copies or substantial portions of the Software.
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

//#include "stdafx.h"
//#include "serialize.h"

/**
 * Time-Events
 */
public class iTimeEvent {

    final static int EFREQ_NEVER  = 0;
    final static int EFREQ_1DAY   = 1;
    final static int EFREQ_2DAYS  = 2;
    final static int EFREQ_3DAYS  = 3;
    final static int EFREQ_4DAYS  = 4;
    final static int EFREQ_5DAYS  = 5;
    final static int EFREQ_6DAYS  = 6;
    final static int EFREQ_1WEEK  = 7;
    final static int EFREQ_2WEEKS = 8;
    final static int EFREQ_3WEEKS = 9;
    final static int EFREQ_1MONTH = 10;
    final static int EFREQ_COUNT  = 11;

    private String      m_name;
    private String      m_text;
    private long        m_playerMask;
    private long        m_fTime;
    private int         m_repTime;
    private iMineralSet m_minerals;

    /**
     * Конструктор
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
        this.m_playerMask = PIM_ALL;
        this.m_fTime = 1;
        this.m_repTime = EFREQ_NEVER;

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
        else if (m_repTime == EFREQ_NEVER)
            return false;
        else
            return ( (curDay-m_fTime) % cm_events.EventFreqDays[m_repTime] ) == 0;
    }
}
