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
package newph.core.enumeration;

import newph.core.common.SkillEntry;

/**
 * Secondary Skill.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum SecondarySkill {
//  Basic       Advanced    Expert     type(DayStart    OnDemand    OnlyBattle)
    SECSK_NONE(null, null, null, -1),

    SECSK_ESTATES(
            new SkillEntry(FurtherSkill.FSK_MIN_GOLD, 250),
            new SkillEntry(FurtherSkill.FSK_MIN_GOLD, 500),
            new SkillEntry(FurtherSkill.FSK_MIN_GOLD, 1000),
            0
    ),

    SECSK_LEADERSHIP(
            new SkillEntry(FurtherSkill.FSK_MORALE, 1),
            new SkillEntry(FurtherSkill.FSK_MORALE, 2),
            new SkillEntry(FurtherSkill.FSK_MORALE, 3),
            2
    ),

    SECSK_LUCK(
            new SkillEntry(FurtherSkill.FSK_LUCK, 1),
            new SkillEntry(FurtherSkill.FSK_LUCK, 2),
            new SkillEntry(FurtherSkill.FSK_LUCK, 3),
            2
    ),

    SECSK_DIPLOMACY(
            new SkillEntry(FurtherSkill.FSK_DIPLOMACY, 20),
            new SkillEntry(FurtherSkill.FSK_DIPLOMACY, 40),
            new SkillEntry(FurtherSkill.FSK_DIPLOMACY, 60),
            1
    ),

    SECSK_AIRMAGIC(
            new SkillEntry(FurtherSkill.FSK_MAG_AIR, 1),
            new SkillEntry(FurtherSkill.FSK_MAG_AIR, 2),
            new SkillEntry(FurtherSkill.FSK_MAG_AIR, 3),
            1
    ),
    SECSK_EARTHMAGIC(
            new SkillEntry(FurtherSkill.FSK_MAG_EARTH, 1),
            new SkillEntry(FurtherSkill.FSK_MAG_EARTH, 2),
            new SkillEntry(FurtherSkill.FSK_MAG_EARTH, 3),
            1
    ),
    SECSK_FIREMAGIC(
            new SkillEntry(FurtherSkill.FSK_MAG_FIRE, 1),
            new SkillEntry(FurtherSkill.FSK_MAG_FIRE, 2),
            new SkillEntry(FurtherSkill.FSK_MAG_FIRE, 3),
            1
    ),
    SECSK_WATERMAGIC(
            new SkillEntry(FurtherSkill.FSK_MAG_WATER, 1),
            new SkillEntry(FurtherSkill.FSK_MAG_WATER, 2),
            new SkillEntry(FurtherSkill.FSK_MAG_WATER, 3),
            1
    ),
    SECSK_WISDOM(
            new SkillEntry(FurtherSkill.FSK_WISDOM, 1),
            new SkillEntry(FurtherSkill.FSK_WISDOM, 2),
            new SkillEntry(FurtherSkill.FSK_WISDOM, 3),
            1
    ),
    SECSK_NECROMANCY(
            new SkillEntry(FurtherSkill.FSK_NECROMANCY, 10),
            new SkillEntry(FurtherSkill.FSK_NECROMANCY, 20),
            new SkillEntry(FurtherSkill.FSK_NECROMANCY, 30),
            1
    ),
    SECSK_MYSTICISM(
            new SkillEntry(FurtherSkill.FSK_MANAPTS, 2),
            new SkillEntry(FurtherSkill.FSK_MANAPTS, 4),
            new SkillEntry(FurtherSkill.FSK_MANAPTS, 6),
            0
    ),
    SECSK_INTELLIGENCE(
            new SkillEntry(FurtherSkill.FSK_INTELLIGENCE, 25),
            new SkillEntry(FurtherSkill.FSK_INTELLIGENCE, 50),
            new SkillEntry(FurtherSkill.FSK_INTELLIGENCE, 100),
            -1
    ),
    SECSK_RESISTANCE(
            new SkillEntry(FurtherSkill.FSK_RESIST, 5),
            new SkillEntry(FurtherSkill.FSK_RESIST, 10),
            new SkillEntry(FurtherSkill.FSK_RESIST, 20),
            -1
    ),
    SECSK_SORCERY(
            new SkillEntry(FurtherSkill.FSK_SORCERY, 5),
            new SkillEntry(FurtherSkill.FSK_SORCERY, 10),
            new SkillEntry(FurtherSkill.FSK_SORCERY, 15),
            1
    ),
    SECSK_LEARNING(
            new SkillEntry(FurtherSkill.FSK_LEARNING, 5),
            new SkillEntry(FurtherSkill.FSK_LEARNING, 10),
            new SkillEntry(FurtherSkill.FSK_LEARNING, 15),
            1
    ),
    SECSK_SCOUTING(
            new SkillEntry(FurtherSkill.FSK_SCOUTING, 2),
            new SkillEntry(FurtherSkill.FSK_SCOUTING, 4),
            new SkillEntry(FurtherSkill.FSK_SCOUTING, 6),
            1
    ),
    SECSK_LOGISTICS(
            new SkillEntry(FurtherSkill.FSK_LOGISTICS, 10),
            new SkillEntry(FurtherSkill.FSK_LOGISTICS, 20),
            new SkillEntry(FurtherSkill.FSK_LOGISTICS, 30),
            0
    ),
    SECSK_PATHFINDING(
            new SkillEntry(FurtherSkill.FSK_PATHFINDING, 25),
            new SkillEntry(FurtherSkill.FSK_PATHFINDING, 50),
            new SkillEntry(FurtherSkill.FSK_PATHFINDING, 75),
            1
    ),
    SECSK_ARCHERY(
            new SkillEntry(FurtherSkill.FSK_ARCHERY, 10),
            new SkillEntry(FurtherSkill.FSK_ARCHERY, 25),
            new SkillEntry(FurtherSkill.FSK_ARCHERY, 50),
            2
    ),
    SESCK_BALLISTICS(
            new SkillEntry(FurtherSkill.FSK_BALLISTICS, 1),
            new SkillEntry(FurtherSkill.FSK_BALLISTICS, 2),
            new SkillEntry(FurtherSkill.FSK_BALLISTICS, 3),
            2
    ),
    SECSK_OFFENCE(
            new SkillEntry(FurtherSkill.FSK_OFFENCE, 10),
            new SkillEntry(FurtherSkill.FSK_OFFENCE, 20),
            new SkillEntry(FurtherSkill.FSK_OFFENCE, 30),
            2
    ),
    SECSK_ARMORER(
            new SkillEntry(FurtherSkill.FSK_ARMORER, 5),
            new SkillEntry(FurtherSkill.FSK_ARMORER, 10),
            new SkillEntry(FurtherSkill.FSK_ARMORER, 15),
            2
    );

    public final SkillEntry basic;
    public final SkillEntry advanced;
    public final SkillEntry expert;

    public final int type;

    private SecondarySkill(
            final SkillEntry basic,
            final SkillEntry advanced,
            final SkillEntry expert,
            final int type
    ) {
        this.basic = basic;
        this.advanced = advanced;
        this.expert = expert;
        this.type = type;
    }

}
