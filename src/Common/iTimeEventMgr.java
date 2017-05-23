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

import java.util.ArrayList;

/**
 * Time events manager
 */
public class iTimeEventMgr {

    /**
     * Хранилище событий.
     */
    private ArrayList<iTimeEvent> m_events = new ArrayList<>();

    /**
     * Возвращает количество событий.
     * @return количество событий.
     */
    public int EventsCount() {
        return m_events.size();
    }

    /**
     * Возвращает событие по индексу.
     * @param idx индекс.
     * @return событие.
     */
    public iTimeEvent Event(int idx) {
        return m_events.get(idx);
    }

    /**
     * Добавляет событие.
     * @param event событие для добавления.
     */
    public void AddEvent(iTimeEvent event) {
        m_events.add(event);
    }

    /**
     * Удаляет событие по индексу.
     * @param idx индекс.
     */
    public void DeleteEvent(int idx) {
        m_events.remove(idx);
    }
    /**
     * Удаляет все события.
     */
    public void DeleteAll() {
        m_events.clear();
    }
}
