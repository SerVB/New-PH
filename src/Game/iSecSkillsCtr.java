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

import Constants.SSLVL;
import Common.iFurtSkills;
import entries.iSecSkillEntry;
import Common.iSecSkills;
import utils.tracer;
import Constants.*;

/**
 * Secondary skills container.
 */
public class iSecSkillsCtr {

    public iSecSkillsCtr(iFurtSkills furtSkills) {
        this.m_furtSkills = furtSkills;
    }

    public final int SkillLevel(int secSkill) {
        for (int xx = 0; xx < m_secSkills.GetSize(); ++xx)
            if (m_secSkills.get(xx).m_skill == secSkill)
                return m_secSkills.get(xx).m_level;
        return SSLVL.NONE;
    }

    public void Inc(int secSkill) {
        for (int xx=0; xx < m_secSkills.GetSize(); ++xx){
            if (m_secSkills.get(xx).m_skill == secSkill){
                tracer.check(m_secSkills.get(xx).m_level < SSLVL.EXPERT && m_secSkills.get(xx).m_level > SSLVL.NONE);
                m_furtSkills.minusValue(SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][0],
                                        SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][1]);
                m_secSkills.get(xx).m_level++;
                m_furtSkills.plusValue(SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][0],
                                       SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][1]);
                return;
            }
        }

        Set(secSkill,SSLVL.BASIC);
    }

    public void Set(int secSkill, int lvl) {
        tracer.check(SkillLevel(secSkill) == SSLVL.NONE && m_secSkills.GetSize() < 8);
        m_secSkills.Add(new iSecSkillEntry(secSkill,lvl));
        m_furtSkills.plusValue(SEKSKILL.DESC[secSkill][lvl][0],
                               SEKSKILL.DESC[secSkill][lvl][1]);
    }

    public void Set(final iSecSkills secSkills) {
        tracer.check(m_secSkills.GetSize() == 0);
        for (int xx = 0; xx < secSkills.GetSize(); ++xx)
            Set(secSkills.get(xx).m_skill, secSkills.get(xx).m_level);
    }

    public void Reset() {
        for (int xx=0; xx < m_secSkills.GetSize(); ++xx) {
            m_furtSkills.minusValue(SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][0],
                                    SEKSKILL.DESC[m_secSkills.get(xx).m_skill][m_secSkills.get(xx).m_level][1]);
        }

        m_secSkills.RemoveAll();
    }

    public final int Count() {
        return m_secSkills.GetSize();
    }

    public final iSecSkillEntry At(int idx) {
        return m_secSkills.get(idx);
    }
    public final iSecSkills SecSkills() {
        return m_secSkills;
    }


    private iFurtSkills    m_furtSkills;
    private iSecSkills     m_secSkills;
}
