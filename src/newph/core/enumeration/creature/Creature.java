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

package newph.core.enumeration.creature;

import java.util.EnumSet;
import newph.core.common.iMineralSet;
import newph.core.enumeration.NationType;
import newph.core.enumeration.creature.property.Level;
import newph.core.enumeration.creature.property.Perk;
import newph.core.enumeration.creature.property.Size;
import newph.core.enumeration.creature.property.Speed;
import newph.core.enumeration.creature.property.Transportation;

/**
 * Creature Type.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public enum Creature implements ICreature {

    /* Knight */

    /**
     * Peasant.
     */
    PEASANT(
            Level.FIRST, NationType.HIGHMEN,
            1, 2,
            Speed.EXTRASLOW, Size.SINGLE, Transportation.WALK, 0,
            2,
            1, 1,
            new iMineralSet.Builder().gold(25).build(),
            22, 15
    ),

    /**
     * Archer.
     */
    ARCHER(
            Level.SECOND, NationType.HIGHMEN,
            5, 4,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 24,
            10,
            2, 3,
            new iMineralSet.Builder().gold(200).build(),
            10, 140,
            Perk.CPERK_DOUBLESHOT
    ),

    /**
     * Pikeman.
     */
    PIKEMAN(
            Level.THIRD, NationType.HIGHMEN,
            5, 9,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            25,
            3, 4,
            new iMineralSet.Builder().gold(250).build(),
            7, 250
    ),

    /**
     * Monk.
     */
    MONK(
            Level.FOURTH, NationType.HIGHMEN,
            7, 9,
            Speed.EXTRASWIFT, Size.SINGLE, Transportation.WALK, 15,
            30,
            4, 6,
            new iMineralSet.Builder().gold(325).build(),
            6, 409,
            Perk.CPERK_NOMELEEPENALTY
    ),

    /**
     * Cavalry.
     */
    CAVALRY(
            Level.FIFTH, NationType.HIGHMEN,
            10, 9,
            Speed.SUPERSWIFT, Size.DOUBLE, Transportation.WALK, 0,
            40,
            5, 10,
            new iMineralSet.Builder().gold(450).build(),
            5, 689,
            Perk.CPERK_JOUSTING
    ),

    /**
     * Paladin.
     */
    PALADIN(
            Level.SIXTH, NationType.HIGHMEN,
            11, 13,
            Speed.SUPERSWIFT, Size.SINGLE, Transportation.WALK, 0,
            80,
            10, 20,
            new iMineralSet.Builder().gold(1200).crystals(1).build(),
            4, 1764,
            Perk.CPERK_DOUBLEATTACK
    ),

    /* Barbarian */

    /**
     * Goblin.
     */
    GOBLIN(
            Level.FIRST, NationType.BARBARIANS,
            4,1,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            3,
            1, 2,
            new iMineralSet.Builder().gold(40).build(),
            20, 33
    ),

    /**
     * Orc.
     */
    ORC(
            Level.SECOND, NationType.BARBARIANS,
            3, 4,
            Speed.EXTRASLOW, Size.SINGLE, Transportation.WALK, 5,
            15,
            3, 4,
            new iMineralSet.Builder().gold(185).build(),
            10, 155
    ),

    /**
     * Warg Rider.
     */
    WARG_RIDER(
            Level.THIRD, NationType.BARBARIANS,
            6, 4,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.WALK, 0,
            20,
            3, 5,
            new iMineralSet.Builder().gold(250).build(),
            8, 262,
            Perk.CPERK_DOUBLEATTACK
    ),

    /**
     * Ogre.
     */
    OGRE(
            Level.FOURTH, NationType.BARBARIANS,
            9, 7,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            55,
            5, 7,
            new iMineralSet.Builder().gold(500).build(),
            6, 627
    ),

    /**
     * Troll.
     */
    TROLL(
            Level.FIFTH, NationType.BARBARIANS,
            10, 6,
            Speed.VERYSWIFT, Size.SINGLE, Transportation.WALK, 15,
            40,
            7, 12,
            new iMineralSet.Builder().gold(700).build(),
            5, 807,
            Perk.CPERK_REGENERATES
    ),

    /**
     * Cyclops.
     */
    CYCLOPS(
            Level.SIXTH, NationType.BARBARIANS,
            12, 9,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.WALK, 0,
            100,
            12, 24,
            new iMineralSet.Builder().gold(1500).crystals(1).build(),
            4, 2036,
            Perk.CPERK_TWOHEXATTACK
    ),

    /* Wizard */
    YOUNG_MAGE(),
    WHITE_WOLF(),
    LIVING_ARMOR(),
    PEGASUS(),
    MAGE(),
    THOR(),

    /* Warlock */
    CENTAUR(),
    GARGOYLE(),
    GRIFFIN(),
    MINOTAUR(),
    HYDRA(),
    RED_DRAGON(),

    /* Sorcer */
    SPRITE(),
    DWARF(),
    ELF(),
    DRUID(),
    UNICORN(),
    FIREBIRD(),

    /* Necromant */
    SKELETON(),
    ZOMBIE(),
    LICH(),
    VAMPIRE(),
    BLACK_KNIGHT(),
    PLAGUE(),

    /* Neutral = 9 */
    ROGUE(),
    NOMAD(),
    GHOST(),
    GENIE(),
    MEDUSA(),
    EARTH_ELEMENTAL(),
    AIR_ELEMENTAL(),
    FIRE_ELEMENTAL(),
    WATER_ELEMENTAL();

    /**
     * Unit level (1-6).
     */
    public final Level level;

    /**
     * Unit alignment.
     */
    public final NationType nation;

    /**
     * Attack skill.
     */
    public final int attack;

    /**
     * Defence skill.
     */
    public final int defence;

    /**
     * Unit speed.
     */
    public final Speed speed;

    /**
     * Unit size.
     */
    public final Size size;

    /**
     * Transportation method.
     */
    public final Transportation transType;

    /**
     * Number of shots. Null means no range attack.
     */
    public final int shots;

    /**
     * Hit points (health).
     */
    public final int hits;

    /**
     * Minimum damage.
     */
    public final int damage_min;

    /**
     * Maximum damage.
     */
    public final int damage_max;

    /**
     * Cost per unit.
     */
    public final iMineralSet cost;

    /**
     * Growth rate.
     */
    public final int growth;

    /**
     * Power index (used for AI).
     */
    public final int pidx;

    /**
     * Creature perks.
     */
    public final EnumSet<Perk> perks;

    private Creature(
            final Level level,
            final NationType nation,
            final int attack,
            final int defence,
            final Speed speed,
            final Size size,
            final Transportation transType,
            final int shots,
            final int hits,
            final int damage_min,
            final int damage_max,
            final iMineralSet cost,
            final int growth,
            final int pidx,
            final Perk...perks
    ) {
        this.level = level;
        this.nation = nation;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.size = size;
        this.transType = transType;
        this.shots = shots;
        this.hits = hits;
        this.damage_min = damage_min;
        this.damage_max = damage_max;
        this.cost = cost;
        this.growth = growth;
        this.pidx = pidx;
        this.perks = EnumSet.of(Perk.CPERK_VOID, perks);
    }

}
