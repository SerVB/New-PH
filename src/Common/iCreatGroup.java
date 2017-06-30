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

import newph.util.Tracer;
import Constants.*;

/**
 * Описание группы существ.
 */
public class iCreatGroup {

    /**
     * Тип существ.
     */
    private int  m_creatType;

    /**
     * Количество существ в группе.
     */
    private int m_count;

    /**
     * Конструктор.
     * @param ct тип существ.
     * @param count количество существ.
     */
    public iCreatGroup(int ct, int count) {
        this.m_creatType = ct;
        this.m_count     = count;
    }

    /**
     * Конструктор без параметра. Вызывает iCreatGroup(ct, RANDOM.QUANTITY).
     * @param ct тип существ.
     */
    public iCreatGroup(int ct) {
//        iCreatGroup(ct, RANDOM.QUANTITY); // Не работает??
        this.m_creatType = ct;
        this.m_count     = RANDOM.QUANTITY;
    }

    /**
     * Конструктор без параметров.
     * Вызывает iCreatGroup(CREAT_UNKNOWN, RANDOM.QUANTITY);
     */
    public iCreatGroup() {
//        iCreatGroup(CREAT.UNKNOWN); // Не работает??
        this.m_creatType = CREAT.UNKNOWN;
        this.m_count     = RANDOM.QUANTITY;
    }

    /**
     * Заменить группу на новую.
     * @param ct тип существ.
     * @param count количество существ.
     */
    public void Reset(int ct, int count) {
        this.m_creatType = ct;
        this.m_count = count;
    }

    /**
     * Заменить группу на новую без параметра.
     * Вызывает Reset(ct, RANDOM.QUANTITY).
     * @param ct тип существ.
     */
    public void Reset(int ct) {
        Reset(ct, RANDOM.QUANTITY);
    }

    /**
     * Заменить группу на новую без параметров.
     */
    public void Reset() {
        Reset(CREAT.UNKNOWN, RANDOM.QUANTITY);
    }

    /**
     * Существует ли группа?
     * @return true если существует, false иначе.
     */
    public boolean IsValid() {
        return m_creatType != CREAT.UNKNOWN;
    }

    /**
     * Получить тип существ группы.
     * @return тип существ.
     */
    public int Type() {
        return m_creatType;
    }

    /**
     * Задать тип существ группы.
     * @param ct новый тип существ.
     */
    public void setType(int ct) {
        m_creatType = ct;
    }

    /**
     * Получить количество существ в группе.
     * @return количество существ.
     */
    public int Count() {
        return m_count;
    }

    /**
     * Задать количество существ в группе.
     * @param count
     */
    public void setCount(int count) {
        m_count = count;
    }

    /**
     * Добавить существ в группы.
     * @param count количество добавляемых к уже существующим.
     */
    public void addCount(long count) {
        m_count += count;
    }

    /**
     * Получить мощь группы.
     * @return мощь.
     */
    public long GroupPower() {
        return m_count * CREAT.DESC[m_creatType].pidx;
    }

    /**
     *
     * @param weeks
     */
    public void Grow(long weeks) {
        Tracer.check(m_count != RANDOM.VAL);

        while(weeks > 0) {
            int div = CREAT.GROWTH_DIVIDER[CREAT.DESC[m_creatType].level - 1];
            if (m_count < CREAT.DESC[m_creatType].growth*2) {
                div /= 2;
            }

            m_count = m_count + (m_count+(div-1)) / div;

            weeks--;
        }
    }

    /**
     *
     * Вызывает Grow(1)
     */
    public void Grow() {
        Grow(1);
    }
}
