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
package newph.core.common.army;

import java.util.EnumSet;
import newph.core.common.creature.CreatureMisc;
import newph.core.common.creature.ICreature;
import newph.core.common.creature.property.Speed;
import newph.core.enumeration.Nation;
import newph.core.staticFunction.Tracer;

/**
 * Army Class.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public final class iArmy {

    private static final int ARMY_SIZE = 7;

    private iCreatGroup[] m_creatGroups;

//    public iArmy() {
//        this.m_creatGroups = new iCreatGroup[ARMY_SIZE];
//        for (int ii = 0; ii < ARMY_SIZE; ii++) {
//            m_creatGroups[ii] = iCreatGroup.newRandomGroup();
//        }
//    }

    public boolean CanAddGroup(final ICreature ct) {
        Tracer.check(ct != CreatureMisc.CREAT_UNKNOWN);

        for (int xx = 0; xx < ARMY_SIZE; ++xx){
            if (!m_creatGroups[xx].IsValid() ||  m_creatGroups[xx].Type() == ct) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the group to the army.
     *
     * @param ct    The creature type.
     * @param count The creatures count.
     * @return      {@code True} if the group was added, {@code false} otherwise.
     */
    public boolean AddGroup(final ICreature ct, final int count) {
        Tracer.check(ct != CreatureMisc.CREAT_UNKNOWN);

        final int NO_EMPTY_SLOT = -1;

        int firstEmptySlot = NO_EMPTY_SLOT;

        /* Firstly try to find similar type group: */
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() == ct) {
                m_creatGroups[xx].setCount(m_creatGroups[xx].Count() + count);
                if(m_creatGroups[xx].Count() <= 0) {
                    m_creatGroups[xx].setType(CreatureMisc.CREAT_UNKNOWN);
                }
                return true;
            } else if (firstEmptySlot == NO_EMPTY_SLOT && !m_creatGroups[xx].IsValid()) {
                firstEmptySlot = xx;
            }
        }

        /* Secondly try to add group to an empty slot: */
        if (0 < count) {
            if (firstEmptySlot != NO_EMPTY_SLOT) {
                m_creatGroups[firstEmptySlot].setType(ct);
                m_creatGroups[firstEmptySlot].setCount(count);
                return true;
            }
        } else {
            return true; // no need to select a slot for negative
        }

        return false; // there is no free slot
    }

    public int CreatureCount(final ICreature ct) {
        int res = 0;
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            if (m_creatGroups[xx].Type() == ct) {
                res += m_creatGroups[xx].Count();
            }
        }
        return res;
    }

    public int SlowestSpeed() {
        Speed res = Speed.MAX;
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            if (
                    m_creatGroups[xx].IsValid() &&
                    m_creatGroups[xx].Type().getSpeed().getSpeedValue() < res.getSpeedValue()
            ) {
                res = m_creatGroups[xx].Type().getSpeed();
            }
        }
        return res.getSpeedValue();
    }

    public iCreatGroup WeakestCreatures() {
        final int NO_INDEX = -1;
        int idx = NO_INDEX;
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            if (
                    m_creatGroups[xx].Type() != CreatureMisc.CREAT_UNKNOWN &&
                    (
                            idx == NO_INDEX ||
                            m_creatGroups[xx].Type().getPowerIndex() < m_creatGroups[idx].Type().getPowerIndex()
                    )
            ) {
                idx = xx;
            }
        }
        Tracer.check(idx != NO_INDEX);
        return m_creatGroups[idx];
    }

    public iCreatGroup WeakestGroup() {
        final int NO_INDEX = -1;
        int idx = NO_INDEX;
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            if (
                    m_creatGroups[xx].Type() != CreatureMisc.CREAT_UNKNOWN &&
                    (
                            idx == -1 ||
                            m_creatGroups[xx].GroupPower() < m_creatGroups[idx].GroupPower()
                    )
            ) {
                idx = xx;
            }
        }
        Tracer.check(idx != -1);
        return m_creatGroups[idx];
    }

    public boolean JoinGroups() {
        boolean bJoined = false;
        for (int xx = 0; xx < ARMY_SIZE; ++xx) {
            for (int yy = xx + 1; yy < ARMY_SIZE; ++yy) {
                if (
                        m_creatGroups[xx].Type() != CreatureMisc.CREAT_UNKNOWN &&
                        m_creatGroups[xx].Type() == m_creatGroups[yy].Type()
                ) {
                    m_creatGroups[xx].setCount(m_creatGroups[xx].Count() + m_creatGroups[yy].Count());
                    m_creatGroups[yy].Reset();
                    bJoined = true;
                }
            }
        }
        return bJoined;
    }

    public void ResetGroup(final int idx) {
        m_creatGroups[idx].Reset();
    }

    public void ResetArmy() {
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            creatureGroup.Reset();
        }
    }

    public void SetGroup(int idx, ICreature ct, int count) {
        Tracer.check(0 <= idx && idx < ARMY_SIZE);
        m_creatGroups[idx].Reset(ct, count);
    }

    public ICreature StrongestCreature() {
        ICreature ct = CreatureMisc.CREAT_UNKNOWN;
        int pidx = 0;
        for (int xx=0; xx<ARMY_SIZE; ++xx) {
            if (
                    m_creatGroups[xx].Type() != CreatureMisc.CREAT_UNKNOWN &&
                    pidx < m_creatGroups[xx].Type().getPowerIndex()
            ) {
                pidx = m_creatGroups[xx].Type().getPowerIndex();
                ct = m_creatGroups[xx].Type();
            }
        }
        Tracer.check(ct != CreatureMisc.CREAT_UNKNOWN);
        return ct;
    }

    public int MoraleModifier() {
        int res = 0;
        boolean bUndeads = false;
        int alignments = 0;
        EnumSet<Nation> nations = EnumSet.noneOf(Nation.class);
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (
                    creatureGroup.Type() != CreatureMisc.CREAT_UNKNOWN &&
                    !nations.contains(creatureGroup.Type().getNation())
            ) {
                alignments++;
                nations.add(creatureGroup.Type().getNation());
                if (creatureGroup.Type().getNation() == Nation.UNDEADS) {
                    bUndeads = true;
                }
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

    public boolean Empty() {
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (creatureGroup.Type() != CreatureMisc.CREAT_UNKNOWN) {
                return false;
            }
        }
        return true;
    }

    public boolean HasCreatures(final ICreature ct) {
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (creatureGroup.Type() == ct) {
                return true;
            }
        }
        return false;
    }

    public iCreatGroup FirstGroup() {
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (creatureGroup.Type() != CreatureMisc.CREAT_UNKNOWN) {
                return creatureGroup;
            }
        }
        Tracer.check(0);
        return m_creatGroups[0];
    }

    public int GroupCount() {
        int cnt = 0;
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (creatureGroup.Type() != CreatureMisc.CREAT_UNKNOWN) {
                ++cnt;
            }
        }
        return cnt;
    }

    public int ArmyPower() {
        int res = 0;
        for (final iCreatGroup creatureGroup : m_creatGroups) {
            if (creatureGroup.Type() != CreatureMisc.CREAT_UNKNOWN) {
                res += creatureGroup.GroupPower();
            }
        }
        return res;
    }

    public iCreatGroup At(int idx) {
        Tracer.check(0 <= idx && idx < ARMY_SIZE);
        return m_creatGroups[idx];
    }

    public void set(final iArmy other) {
        for (int ii = 0; ii < ARMY_SIZE; ii++) {
            this.m_creatGroups[ii] = new iCreatGroup(other.m_creatGroups[ii]);
        }
    }

}
