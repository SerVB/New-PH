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
 * Армия
 */
public class iArmy {

    /**
     * Максимальное количество групп в армии.
     */
    public final static int ARMY_PLACES_COUNT = 7;

    /**
     * Хранилище всех групп существ армии.
     * Нужно инициализировать!
     */
    private iCreatGroup[] m_creatGroups = new iCreatGroup[ARMY_PLACES_COUNT];

    /**
     * Можно ли добавить группу существ данного типа?
     * @param ct тип добавляемых существ
     * @return true если можно, false если нельзя
     */
    public boolean CanAddGroup(int ct) {
        tracer.check(ct != cm_creatures.CREAT_UNKNOWN);

        for(int xx = 0; xx < ARMY_PLACES_COUNT; ++xx) {
            if (!m_creatGroups[xx].IsValid() ||  m_creatGroups[xx].Type() == ct)
                return true;
        }
        return false;
    }

    /**
     * Добавить группу существ в армию.
     * @param ct тип группы существ.
     * @param count количество существ.
     * @return true если группа добавлена, false если нет.
     */
    public boolean AddGroup(int ct, long count) {
        int firstEmpty = -1;

        tracer.check(ct != cm_creatures.CREAT_UNKNOWN && count != RANDOM_QUANTITY);

        // first try to find similar type group
        for (int xx = 0; xx < ARMY_PLACES_COUNT; ++xx){
            if (m_creatGroups[xx].Type() == ct) {
                m_creatGroups[xx].addCount(count);

                return true;
            } else if (firstEmpty == -1 && !m_creatGroups[xx].IsValid()) {
                firstEmpty = xx;
            }
        }

        if (firstEmpty != -1){
            m_creatGroups[firstEmpty].setType(ct);
            m_creatGroups[firstEmpty].setCount(count);

            return true;
        }

        // there is no free cell
        return false;
    }

    /**
     * Получить скорость самого медленного существа армии.
     * @return скорость.
     */
    public int SlowestSpeed() {
        int res = cm_creatures.SPEED_MAX;

        for (iCreatGroup cg : m_creatGroups)
            if (cg.IsValid() && cm_creatures.CREAT_DESC[cg.Type()].speed < res)
                res = cm_creatures.CREAT_DESC[cg.Type()].speed;

        return res;
    }

    /**
     * Получить группу самого слабого существа в армии.
     * @return группа существ.
     */
    public iCreatGroup WeakestCreatures() {
        int idx = -1;

        for (int xx = 0; xx < ARMY_PLACES_COUNT; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                (idx == -1 ||
                 cm_creatures.CREAT_DESC[m_creatGroups[idx].Type()].pidx > cm_creatures.CREAT_DESC[m_creatGroups[xx].Type()].pidx)) {
                idx=xx;
            }
        }

        tracer.check(idx != -1);

        return m_creatGroups[idx];
    }

    /**
     * Получить самую слабую группу существ армии.
     * @return группа существ.
     */
    public iCreatGroup WeakestGroup() {
        int idx = -1;

        for (int xx = 0; xx < ARMY_PLACES_COUNT; ++xx) {
            if (m_creatGroups[xx].Type() != cm_creatures.CREAT_UNKNOWN &&
                ( idx == -1 || m_creatGroups[idx].GroupPower() > m_creatGroups[xx].GroupPower() )) {

                idx = xx;
            }
        }

        tracer.check(idx != -1);

        return m_creatGroups[idx];
    }

    /**
     * Объединить группы одних и тех же существ в армии.
     * @return true если произошло хотя бы одно объединение, false если ни одного объединения не произошло.
     */
    public boolean JoinGroups() {
        boolean bJoined = false;

        for (int xx = 0; xx < ARMY_PLACES_COUNT; ++xx) {
            for (int yy = xx + 1; yy < ARMY_PLACES_COUNT; ++yy) {
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
     * Удалить группу по номеру.
     * @param idx номер удаляемой группы.
     */
    public void ResetGroup(int idx) {
        m_creatGroups[idx].Reset();
    }

    /**
     * Удалить всю армию.
     */
    public void ResetArmy() {
        for (iCreatGroup cg : m_creatGroups)
            cg.Reset();
    }

    /**
     * Задать группу.
     * @param idx номер группы.
     * @param ct тип существа.
     * @param count количество.
     */
    public void SetGroup(int idx, int ct, long count) {
        tracer.check(idx < ARMY_PLACES_COUNT );

        m_creatGroups[idx].setType(ct);
        m_creatGroups[idx].setCount(count);
    }

    /**
     * Получить тип сильнейшего существа армии.
     * @return тип существа.
     */
    public int StrongestCreature() {
        int ct = cm_creatures.CREAT_UNKNOWN;
        long pidx = 0;

        for (iCreatGroup cg : m_creatGroups) {
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN && cm_creatures.CREAT_DESC[cg.Type()].pidx > pidx) {

                pidx = cm_creatures.CREAT_DESC[cg.Type()].pidx;
                ct = cg.Type();
            }
        }

        tracer.check(ct != cm_creatures.CREAT_UNKNOWN);

        return ct;
    }

    /**
     * Получить модификатор морали армии.
     * @return мораль.
     */
    public long MoraleModifier() {
        long res = 0;
        boolean bUndeads = false;
        long nations = 0;
        long nat_mask = 0;

        for (iCreatGroup cg : m_creatGroups) {
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN &&
                ( nat_mask & (1<<cm_creatures.CREAT_DESC[cg.Type()].nation)) == 0 ) {

                nations++;

                nat_mask |= ( 1 << cm_creatures.CREAT_DESC[cg.Type()].nation );

                if (cm_creatures.CREAT_DESC[cg.Type()].nation == NATION_UNDEADS)
                    bUndeads = true;
            }
        }

        if (nations >= 5) {
            res -= 3;
        } else if (nations == 4) {
            res -= 2;
        } else if (nations == 3) {
            res -= 1;
        } else if (nations == 1) {
            res += 1;
        }

        if (bUndeads){
            res -= 1;
        }

        return res;
    }

    /**
     * Пуста ли армия?
     * @return true если пуста, false если не пуста.
     */
    public boolean Empty() {
        for (iCreatGroup cg : m_creatGroups)
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN)
                return false;

        return true;
    }

    /**
     * Имеются ли существа типа ct в армии?
     * @param ct тип существа для проверки.
     * @return true если есть, false если нет.
     */
    public boolean HasCreatures(int ct) {
        for (iCreatGroup cg : m_creatGroups)
            if (cg.Type() == ct)
                return true;

        return false;
    }

    /**
     * Получить первую по порядку группу армии.
     * @return первая группа.
     */
    public iCreatGroup FirstGroup() {
        for (iCreatGroup cg : m_creatGroups)
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN)
                return cg;

        tracer.check(0);
        return m_creatGroups[0];
    }

    /**
     * Получить количество групп в армии.
     * @return количество групп.
     */
    public int GroupCount() {
        int cnt = 0;

        for (iCreatGroup cg : m_creatGroups)
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN)
                ++cnt;

        return cnt;
    }

    /**
     * Получить мощь армии.
     * @return мощь.
     */
    public long ArmyPower() {
        long res = 0;

        for (iCreatGroup cg : m_creatGroups)
            if (cg.Type() != cm_creatures.CREAT_UNKNOWN)
                res += cg.GroupPower();

        return res;
    }

    /**
     * Получить группу существ по номеру.
     * @param idx номер группы (от 0 до 6).
     * @return объект группы.
     */
    public iCreatGroup At(int idx) {
//    public inline const iCreatGroup& operator[](uint32 idx) const { return At(idx); }
//    public inline iCreatGroup& operator[](uint32 idx) { return At(idx); }

        tracer.check(idx < ARMY_PLACES_COUNT );

        return m_creatGroups[idx];
    }

    /**
     * Заменить текущую армию на армию other.
     * @param other армия для копирования в текущую.
     */
    public void set(iArmy other) {
//    public void operator= (const iArmy& other)    { memcpy(m_creatGroups, other.m_creatGroups, sizeof(m_creatGroups)); }

        System.arraycopy(other.m_creatGroups, 0, this.m_creatGroups, 0, ARMY_PLACES_COUNT);
    }
}