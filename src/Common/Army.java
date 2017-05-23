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

/**
 *
 */
class iCreatGroup {
    private int  m_creatType;
    private long m_count;

    final long[] CREAT_GROWTH_DIVIDER = {
	9, 9, 10, 10, 11, 12
    };

    public iCreatGroup(int ct, long count) {
        this.m_creatType = ct;
        this.m_count = count;
    }

    public iCreatGroup(int ct) {
        iCreatGroup(ct, RANDOM_QUANTITY);
    }

    public iCreatGroup() {
        iCreatGroup(cm_creatures.CREAT_UNKNOWN, RANDOM_QUANTITY);
    }

    public void Reset(int ct, long count) {
        this.m_creatType = ct;
        this.m_count = count;
    }

    public void Reset(int ct) {
        Reset(ct, RANDOM_QUANTITY);
    }

    public void Reset() {
        Reset(cm_creatures.CREAT_UNKNOWN, RANDOM_QUANTITY);
    }

    public boolean IsValid() {
        return m_creatType != cm_creatures.CREAT_UNKNOWN;
    }

    public int Type() {
        return m_creatType;
    }

    public void setType(int ct) {
        m_creatType = ct;
    }

    public long Count() {
        return m_count;
    }

    public void setCount(long count) {
        m_count = count;
    }

    public void addCount(long count) {
        setCount(m_count + count);
    }

    public long GroupPower() {
        return m_count*CREAT_DESC[m_creatType].pidx;
    }

    public void Grow(long weeks) {
        tracer.check(m_count != RAND_VAL);
        while(weeks > 0) {
            long div = CREAT_GROWTH_DIVIDER[cm_creatures.CREAT_DESC[m_creatType].level-1];
            if (m_count < cm_creatures.CREAT_DESC[m_creatType].growth*2) div /= 2;

            m_count = m_count + (m_count+(div-1)) / div;

            weeks--;
        }
    }

    public void Grow() {
        Grow(1);
    }
}

//////////////////////////////////////////////////////////////////////////
class iArmy {

    /**
     * Максимальное количество групп в армии.
     */
    public final static int MAX_ARMY_SIZE = 7;

    /**
     * Хранилище всех групп существ армии.
     */
    private iCreatGroup[] m_creatGroups = new iCreatGroup[MAX_ARMY_SIZE];

    /**
     * Можно ли добавить группу существ данного типа?
     * @param ct тип добавляемых существ
     * @return true если можно, false если нельзя
     */
    public boolean CanAddGroup(int ct) {
        tracer.check(ct != cm_creatures.CREAT_UNKNOWN);
        for(int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            if (!m_creatGroups[xx].IsValid() ||  m_creatGroups[xx].Type() == ct)
                return true;
        }
        return false;
    }

    /**
     * Добавить группу
     * @param ct
     * @param count
     * @return
     */
    public boolean AddGroup(int ct, long count) {
        int firstEmpty = -1;

        check(ct != cm_creatures.CREAT_UNKNOWN && count != RANDOM_QUANTITY);

        // first try to find similar type group
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx){
            if (m_creatGroups[xx].Type() == ct) {
                m_creatGroups[xx].addCount(count);
                return true;
            }
            else if (firstEmpty == -1 && !m_creatGroups[xx].IsValid()) {
                firstEmpty = xx;
            }
        }

        // Если группы такого типа нет, проверяем наличие первой свободной ячейки
        if (firstEmpty != -1){
            m_creatGroups[firstEmpty].setType(ct);
            m_creatGroups[firstEmpty].setCount(count);
            return true;
        }

        // there is no free cell
        return false;
    }

    /**
     * Скорость самого медленного существа в армии.
     * @return значение скорости
     */
    public int SlowestSpeed() {
        int res = cm_creatures.SPEED_MAX;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].IsValid() && cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].speed < res)
                res = cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].speed;
        return res;
    }

    /**
     * Самое слабое существо в армии.
     * @return объект группы существ
     */
    public iCreatGroup WeakestCreatures() {
        int idx = -1;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                    (idx == -1 || cm_creatures.CREAT_DESC[m_creatGroups[idx].Type()].pidx > cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].pidx))
                idx=xx;
        }
        tracer.check(idx != -1);
        return m_creatGroups[idx];
    }

    /**
     * Самая слабая группа существ в армии.
     * @return объект группы существ
     */
    public iCreatGroup WeakestGroup() {
        int idx = -1;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                    (idx == -1 || m_creatGroups[idx].GroupPower() > m_creatGroups[xx].GroupPower()))
                idx=xx;
        }
        tracer.check(idx != -1);
        return m_creatGroups[idx];
    }

    /**
     * ???
     * @return
     */
    public boolean JoinGroups() {
        boolean bJoined = false;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            for (int yy = xx + 1; yy < MAX_ARMY_SIZE; ++yy) {
                if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                        m_creatGroups[xx].Type() == m_creatGroups[yy].Type()) {
                    m_creatGroups[xx].addCount(m_creatGroups[yy].Count());
                    m_creatGroups[yy].Reset();
                    bJoined = true;
                }
            }
        }
        return bJoined;
    }

    /**
     * Удалить группу.
     * @param idx номер удаляемой группы.
     */
    public void ResetGroup(int idx) {
        m_creatGroups[idx].Reset();
    }

    /**
     * Удалить всю армию.
     */
    public void ResetArmy() {
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            m_creatGroups[xx].Reset();
    }

    /**
     * Задать группу.
     * @param idx номер группы.
     * @param ct тип существа.
     * @param count количество.
     */
    public void SetGroup(int idx, int ct, long count) {
        tracer.check( idx < MAX_ARMY_SIZE );
        m_creatGroups[idx].setType(ct);
        m_creatGroups[idx].setCount(count);
    }

    /**
     * Сильнейшее существо.
     * @return тип существа.
     */
    public int StrongestCreature() {
        int ct = cm_creatures.CREAT_UNKNOWN;
        long pidx = 0;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                    cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].pidx > pidx) {
                pidx = cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].pidx;
                ct = m_creatGroups[xx].Type();
            }
        }
        tracer.check(ct != cm_creatures.CREAT_UNKNOWN);
        return ct;
    }

    /**
     * Модификатор морали.
     * @return мораль.
     */
    public long MoraleModifier() {
        long res = 0;
        boolean bUndeads = false;
        long alignments = 0;
        long nat_mask = 0;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                    (nat_mask & (1<<cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].nation)) == 0 ) {
                alignments ++;
                nat_mask |= (1<<cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].nation);
                if (cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].nation == NATION_UNDEADS)
                    bUndeads = true;
            }
        }

        // Troops of >=5 alignment -3
        if (alignments >= 5) res -= 3;
        // Troops of 4 alignment -2
        else if (alignments == 4) res -= 2;
        // Troops of 3 alignment -1
        else if (alignments == 3) res -= 1;
        // All troops of one alignment +1
        else if (alignments <= 1) res += 1;
        // Undead in army -1
        if (bUndeads) res -= 1;
        return res;
    }

    /**
     * Пуста ли армия?
     * @return true если пуста, false если не пуста
     */
    public boolean Empty() {
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN)
                return false;
        return true;
    }

    /**
     * Есть ли существа типа ct?
     * @param ct тип существа
     * @return true если есть, false если нет
     */
    public boolean HasCreatures(int ct) {
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].Type() == ct)
                return true;
        return false;
    }

    /**
     * Первая группа.
     * @return объект первой группы в армии
     */
    public iCreatGroup FirstGroup() {
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN)
                return m_creatGroups[xx];
        tracer.check(0); // Такого не должно быть, ведь армия не пустая
        return m_creatGroups[0];
    }

    /**
     * Количество групп в армии.
     * @return количество групп
     */
    public int GroupCount() {
        int cnt = 0;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN)
                ++cnt;
        return cnt;
    }

    /**
     * Сила армии.
     * @return число силы армии
     */
    public long ArmyPower() {
        long res = 0;
        for (int xx = 0; xx < MAX_ARMY_SIZE; ++xx)
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN)
                res += m_creatGroups[xx].GroupPower();
        return res;
    }

    /**
     * Геттер группы по номеру.
     * @param idx номер группы (от 0 до 6)
     * @return объект группы
     */
    public iCreatGroup At(int idx) {
        tracer.check( idx < MAX_ARMY_SIZE );
        return m_creatGroups[idx];
    }
//    public inline const iCreatGroup& operator[](uint32 idx) const { return At(idx); }
//    public inline iCreatGroup& operator[](uint32 idx) { return At(idx); }

    /**
     * Заменяет текущую армию на армию other.
     * Не понятно, будет ли это вообще работать!!!
     * Нужно изучить больше матчасти!
     * @param other армия, которую нужно скопировать в текущую
     */
    public void set(iArmy other) {
//    public void operator= (const iArmy& other)    { memcpy(m_creatGroups, other.m_creatGroups, sizeof(m_creatGroups)); }
        System.arraycopy(other.m_creatGroups, 0, this.m_creatGroups, 0, MAX_ARMY_SIZE);
    }
};