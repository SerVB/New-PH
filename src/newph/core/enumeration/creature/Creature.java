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
import newph.core.enumeration.Nation;
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

    //<editor-fold desc="Highmen/Knight" defaultstate="collapsed">
    /**
     * Peasant.
     */
    PEASANT(
            Level.FIRST, Nation.HIGHMEN,
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
            Level.SECOND, Nation.HIGHMEN,
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
            Level.THIRD, Nation.HIGHMEN,
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
            Level.FOURTH, Nation.HIGHMEN,
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
            Level.FIFTH, Nation.HIGHMEN,
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
            Level.SIXTH, Nation.HIGHMEN,
            11, 13,
            Speed.SUPERSWIFT, Size.SINGLE, Transportation.WALK, 0,
            80,
            10, 20,
            new iMineralSet.Builder().gold(1200).crystals(1).build(),
            4, 1764,
            Perk.CPERK_DOUBLEATTACK
    ),
    //</editor-fold>

    //<editor-fold desc="Barbarians/Barbarian" defaultstate="collapsed">
    /**
     * Goblin.
     */
    GOBLIN(
            Level.FIRST, Nation.BARBARIANS,
            4, 1,
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
            Level.SECOND, Nation.BARBARIANS,
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
            Level.THIRD, Nation.BARBARIANS,
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
            Level.FOURTH, Nation.BARBARIANS,
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
            Level.FIFTH, Nation.BARBARIANS,
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
            Level.SIXTH, Nation.BARBARIANS,
            12, 9,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.WALK, 0,
            100,
            12, 24,
            new iMineralSet.Builder().gold(1500).crystals(1).build(),
            4, 2036,
            Perk.CPERK_TWOHEXATTACK
    ),
    //</editor-fold>

    //<editor-fold desc="Wizards/Wizard" defaultstate="collapsed">
    /**
     * Young mage.
     */
    YOUNG_MAGE(
            Level.FIRST, Nation.WIZARDS,
            2, 1,
            Speed.VERYSLOW, Size.SINGLE, Transportation.WALK, 5,
            4,
            1, 3,
            new iMineralSet.Builder().gold(60).build(),
            18, 41
    ),

    /**
     * White wolf.
     */
    WHITE_WOLF(
            Level.SECOND, Nation.WIZARDS,
            5, 4,
            Speed.VERYSWIFT, Size.DOUBLE, Transportation.WALK, 0,
            15,
            2, 3,
            new iMineralSet.Builder().gold(150).build(),
            8, 149
    ),

    /**
     * Living armor.
     */
    LIVING_ARMOR(
            Level.THIRD, Nation.WIZARDS,
            6, 9,
            Speed.EXTRASLOW, Size.SINGLE, Transportation.WALK, 0,
            35,
            4, 5,
            new iMineralSet.Builder().gold(300).build(),
            6, 401,
            Perk.CPERK_LIFELESS, Perk.CPERK_QUARTERDMG
    ),

    /**
     * Pegasus.
     */
    PEGASUS(
            Level.FOURTH, Nation.WIZARDS,
            7, 7,
            Speed.ULTRASWIFT, Size.DOUBLE, Transportation.FLY, 0,
            40,
            5, 8,
            new iMineralSet.Builder().gold(400).build(),
            5, 527
    ),

    /**
     * Mage.
     */
    MAGE(
            Level.FIFTH, Nation.WIZARDS,
            12, 8,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.WALK, 15,
            35,
            7, 10,
            new iMineralSet.Builder().gold(700).build(),
            5, 748,
            Perk.CPERK_NOMELEEPENALTY
    ),

    /**
     * Thor.
     */
    THOR(
            Level.SIXTH, Nation.WIZARDS,
            13, 13,
            Speed.ULTRAQUICK, Size.SINGLE, Transportation.WALK, 15,
            300,
            15, 30,
            new iMineralSet.Builder().gold(3500).gems(1).build(),
            3, 6950,
            Perk.CPERK_NOMELEEPENALTY
    ),
    //</editor-fold>

    //<editor-fold desc="Beastmen/Warlock" defaultstate="collapsed">
    /**
     * Centaur.
     */
    CENTAUR(
            Level.FIRST, Nation.BEASTMEN,
            3, 1,
            Speed.SLOW, Size.DOUBLE, Transportation.WALK, 5,
            5,
            1, 2,
            new iMineralSet.Builder().gold(60).build(),
            18, 47
    ),

    /**
     * Gargoyle.
     */
    GARGOYLE(
            Level.SECOND, Nation.BEASTMEN,
            4, 7,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.FLY, 0,
            15,
            2, 3,
            new iMineralSet.Builder().gold(200).build(),
            8, 175,
            Perk.CPERK_LIFELESS
    ),

    /**
     * Griffin.
     */
    GRIFFIN(
            Level.THIRD, Nation.BEASTMEN,
            6, 6,
            Speed.VERYSWIFT, Size.DOUBLE, Transportation.FLY, 0,
            25,
            3, 5,
            new iMineralSet.Builder().gold(300).build(),
            6, 334,
            Perk.CPERK_RETALTOALL
    ),

    /**
     * Minotaur.
     */
    MINOTAUR(
            Level.FOURTH, Nation.BEASTMEN,
            9, 8,
            Speed.VERYSWIFT, Size.SINGLE, Transportation.WALK, 0,
            50,
            5, 10,
            new iMineralSet.Builder().gold(500).build(),
            5, 682
    ),

    /**
     * Hydra.
     */
    HYDRA(
            Level.FIFTH, Nation.BEASTMEN,
            8, 9,
            Speed.EXTRASLOW, Size.DOUBLE, Transportation.WALK, 0,
            60,
            6, 12,
            new iMineralSet.Builder().gold(750).build(),
            4, 872,
            Perk.CPERK_NONRETALATTACK, Perk.CPERK_ADJACENTATTACK
    ),

    /**
     * Red dragon,
     */
    RED_DRAGON(
            Level.SIXTH, Nation.BEASTMEN,
            13, 12,
            Speed.VERYQUICK, Size.DOUBLE, Transportation.FLY, 0,
            250,
            20, 40,
            new iMineralSet.Builder().gold(4000).sulfur(1).build(),
            3, 8528,
            Perk.CPERK_AIRMAGICIMM, Perk.CPERK_EARTHMAGICIMM, Perk.CPERK_FIREMAGICIMM, Perk.CPERK_WATERMAGICIMM,
            Perk.CPERK_TWOHEXATTACK
    ),
    //</editor-fold>

    //<editor-fold desc="Elves/Sorcer" defaultstate="collapsed">
    /**
     * Sprite.
     */
    SPRITE(
            Level.FIRST, Nation.ELVES,
            4, 2,
            Speed.EXTRASWIFT, Size.SINGLE, Transportation.FLY, 0,
            2,
            1, 2,
            new iMineralSet.Builder().gold(60).build(),
            20, 39,
            Perk.CPERK_NONRETALATTACK
    ),

    /**
     * Dwarf.
     */
    DWARF(
            Level.SECOND, Nation.ELVES,
            6, 6,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            20,
            2, 4,
            new iMineralSet.Builder().gold(250).build(),
            8, 190,
            Perk.CPERK_40PROCRESIST
    ),

    /**
     * Elf.
     */
    ELF(
            Level.THIRD, Nation.ELVES,
            5, 5,
            Speed.VERYSWIFT, Size.SINGLE, Transportation.WALK, 20,
            15,
            2, 4,
            new iMineralSet.Builder().gold(300).build(),
            7, 194,
            Perk.CPERK_DOUBLESHOT
    ),

    /**
     * Druid.
     */
    DRUID(
            Level.FOURTH, Nation.ELVES,
            8, 7,
            Speed.VERYSWIFT, Size.SINGLE, Transportation.WALK, 15,
            25,
            5, 8,
            new iMineralSet.Builder().gold(400).build(),
            5, 433,
            Perk.CPERK_NOMELEEPENALTY
    ),

    /**
     * Unicorn.
     */
    UNICORN(
            Level.FIFTH, Nation.ELVES,
            10, 9,
            Speed.ULTRASWIFT, Size.DOUBLE, Transportation.WALK, 0,
            60,
            7, 14,
            new iMineralSet.Builder().gold(650).build(),
            4, 819
    ),

    /**
     * Firebird.
     */
    FIREBIRD(
            Level.SIXTH, Nation.ELVES,
            12, 11,
            Speed.SUPERQUICK, Size.DOUBLE, Transportation.FLY, 0,
            150,
            20, 40,
            new iMineralSet.Builder().gold(1700).mercury(1).build(),
            3, 3064,
            Perk.CPERK_FIREMAGICIMM, Perk.CPERK_TWOHEXATTACK
    ),
    //</editor-fold>

    /* Necromant */

    /**
     * Skeleton.
     */
    SKELETON(
            Level.FIRST, Nation.UNDEADS,
            3, 3,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            4,
            1, 3,
            new iMineralSet.Builder().gold(65).build(),
            18, 62,
            Perk.CPERK_UNDEAD
    ),

    /**
     * Zombie.
     */
    ZOMBIE(
            Level.SECOND, Nation.UNDEADS,
            5, 2,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            20,
            2, 3,
            new iMineralSet.Builder().gold(200).build(),
            8, 153,
            Perk.CPERK_UNDEAD
    ),

    /**
     * Lich.
     */
    LICH(
            Level.THIRD, Nation.UNDEADS,
            7, 6,
            Speed.EXTRASWIFT, Size.SINGLE, Transportation.WALK, 6,
            25,
            2, 3,
            new iMineralSet.Builder().gold(330).build(),
            6, 320,
            Perk.CPERK_UNDEAD, Perk.CPERK_NOMELEEPENALTY, Perk.CPERK_LICHSHOOT
    ),

    /**
     * Vampire.
     */
    VAMPIRE(
            Level.FOURTH, Nation.UNDEADS,
            8, 6,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.FLY, 0,
            40,
            5, 7,
            new iMineralSet.Builder().gold(650).build(),
            5, 746,
            Perk.CPERK_UNDEAD, Perk.CPERK_NONRETALATTACK, Perk.CPERK_DRAINLIFES
    ),

    /**
     * Black Knight.
     */
    BLACK_KNIGHT(
            Level.FIFTH, Nation.UNDEADS,
            8, 12,
            Speed.ULTRASWIFT, Size.SINGLE, Transportation.WALK, 0,
            50,
            8, 14,
            new iMineralSet.Builder().gold(850).build(),
            4, 795,
            Perk.CPERK_UNDEAD
    ),

    /**
     * Plague.
     */
    PLAGUE(
            Level.SIXTH, Nation.UNDEADS,
            11, 9,
            Speed.VERYSWIFT, Size.DOUBLE, Transportation.FLY, 15,
            150,
            20, 35,
            new iMineralSet.Builder().gold(2500).mercury(1).build(),
            3, 3559,
            Perk.CPERK_UNDEAD
    ),

    /* Neutral = 9 */

    /**
     * Rogue.
     */
    ROGUE(
            Level.FIRST, Nation.NEUTRAL,
            6, 1,
            Speed.SWIFT, Size.SINGLE, Transportation.WALK, 0,
            4,
            1, 2,
            new iMineralSet.Builder().gold(50).build(),
            8, 54,
            Perk.CPERK_NONRETALATTACK
    ),

    /**
     * Nomad.
     */
    NOMAD(
            Level.THIRD, Nation.NEUTRAL,
            7, 5,
            Speed.ULTRASWIFT, Size.DOUBLE, Transportation.WALK, 0,
            20,
            2, 5,
            new iMineralSet.Builder().gold(200).build(),
            6, 244
    ),

    /**
     * Ghost.
     */
    GHOST(
            Level.FIFTH, Nation.NEUTRAL,
            8, 6,
            Speed.EXTRASWIFT, Size.SINGLE, Transportation.FLY, 0,
            20,
            4, 6,
            new iMineralSet.Builder().gold(1000).build(),
            4, 468,
            Perk.CPERK_UNDEAD, Perk.CPERK_GHOST
    ),

    /**
     * Genie.
     */
    GENIE(
            Level.SIXTH, Nation.NEUTRAL,
            10, 9,
            Speed.SUPERSWIFT, Size.SINGLE, Transportation.FLY, 0,
            50,
            20, 30,
            new iMineralSet.Builder().gold(750).gems(1).build(),
            3, 1725,
            Perk.CPERK_DOHALF
    ),

    /**
     * Medusa.
     */
    MEDUSA(
            Level.FOURTH, Nation.NEUTRAL,
            8, 9,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            35,
            6, 10,
            new iMineralSet.Builder().gold(350).build(),
            4, 600
    ),

    /**
     * Earth elemental.
     */
    EARTH_ELEMENTAL(
            Level.FOURTH, Nation.NEUTRAL,
            8, 8,
            Speed.EXTRASLOW, Size.SINGLE, Transportation.WALK, 0,
            50,
            4, 5,
            new iMineralSet.Builder().gold(500).build(),
            4, 525,
            Perk.CPERK_EARTHMAGICIMM, Perk.CPERK_LIFELESS
    ),

    /**
     * Air elemental.
     */
    AIR_ELEMENTAL(
            Level.FOURTH, Nation.NEUTRAL,
            7, 7,
            Speed.EXTRASWIFT, Size.SINGLE, Transportation.WALK, 0,
            35,
            2, 8,
            new iMineralSet.Builder().gold(500).build(),
            4, 428,
            Perk.CPERK_AIRMAGICIMM, Perk.CPERK_LIFELESS
    ),

    /**
     * Fire elemental.
     */
    FIRE_ELEMENTAL(
            Level.FOURTH, Nation.NEUTRAL,
            8, 6,
            Speed.SWIFT, Size.SINGLE, Transportation.WALK, 0,
            40,
            4, 5,
            new iMineralSet.Builder().gold(500).build(),
            4, 455,
            Perk.CPERK_FIREMAGICIMM, Perk.CPERK_LIFELESS
    ),

    /**
     * Water elemental.
     */
    WATER_ELEMENTAL(
            Level.FOURTH, Nation.NEUTRAL,
            6, 8,
            Speed.SLOW, Size.SINGLE, Transportation.WALK, 0,
            45,
            3, 7,
            new iMineralSet.Builder().gold(500).build(),
            4, 512,
            Perk.CPERK_WATERMAGICIMM, Perk.CPERK_LIFELESS
    );

    /**
     * Unit level (1-6).
     */
    public final Level level;

    /**
     * Unit alignment.
     */
    public final Nation nation;

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
            final Nation nation,
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
