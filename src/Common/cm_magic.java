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

/**
 * Магия
 */
public class cm_magic {

    // Spell type SPELL_TYPE
    public final static int SPT_OVERLAND = 0;
    public final static int SPT_COMBAT   = 1;
    public final static int SPT_COUNT    = 2;

    // SPELL_TYPE_MASK
    public final static int SPT_MASK_OVERLAND = 0b01;
    public final static int SPT_MASK_COMBAT   = 0b10;
    public final static int SPT_MASK_ALL      = SPT_MASK_OVERLAND | SPT_MASK_COMBAT;

    // Spell level SPELL_LEVEL
    public final static int SPL_FIRST  = 0;
    public final static int SPL_SECOND = 1;
    public final static int SPL_THIRD  = 2;
    public final static int SPL_FOURTH = 3;
    public final static int SPL_FIFTH  = 4;
    public final static int SPL_COUNT  = 5;

    // SPELL_LEVEL_MASK
    public final static int SPL_MASK_NONE   = 0b00000;
    public final static int SPL_MASK_FIRST  = 0b00001;
    public final static int SPL_MASK_SECOND = 0b00010;
    public final static int SPL_MASK_THIRD  = 0b00100;
    public final static int SPL_MASK_FOURTH = 0b01000;
    public final static int SPL_MASK_FIFTH  = 0b10000;
    public final static int SPL_MASK_ALL    = SPL_MASK_FIRST | SPL_MASK_SECOND | SPL_MASK_THIRD | SPL_MASK_FOURTH | SPL_MASK_FIFTH;

    public final int[] SPELL_LEVEL_MASKS = {
        SPL_MASK_FIRST, SPL_MASK_SECOND, SPL_MASK_THIRD, SPL_MASK_FOURTH, SPL_MASK_FIFTH
    };

    // Magic school type MAGIC_SCHOOL
    public final static int MST_AIR   = 0;
    public final static int MST_EARTH = 1;
    public final static int MST_FIRE  = 2;
    public final static int MST_WATER = 3;
    public final static int MST_COUNT = 4;

    // Magic school secondary skills (Air, Earth, Fire, Water)
    public final int[] MSCH_SECSKILLS = {
        SECSK_AIRMAGIC, SECSK_EARTHMAGIC, SECSK_FIREMAGIC, SECSK_WATERMAGIC
    };

    // MAGIC_SCHOOL_MASK
    public final static int MST_MASK_AIR   = 0b0001;
    public final static int MST_MASK_EARTH = 0b0010;
    public final static int MST_MASK_FIRE  = 0b0100;
    public final static int MST_MASK_WATER = 0b1000;
    public final static int MST_MASK_GOOD  = MST_MASK_AIR | MST_MASK_EARTH | MST_MASK_WATER;
    public final static int MST_MASK_EVIL  = MST_MASK_AIR | MST_MASK_EARTH | MST_MASK_FIRE;
    public final static int MST_MASK_ALL   = MST_MASK_AIR | MST_MASK_EARTH | MST_MASK_WATER | MST_MASK_FIRE;


    // Spell classes SPELL_CLASS
    public final static int SPC_DAMAGE     = 0;  // fparam = damage
    public final static int SPC_FURTSKILL  = 1;  // fparam = skill, sparam = modifier
    public final static int SPC_DISRAY     = 2;  // fparam = defence modifier
    public final static int SPC_RESURRECT  = 3;  // fparam = hit points, sparam = true/not
    public final static int SPC_SUMMON     = 4;  // fparam = creature type, sparam = quantity
    public final static int SPC_BLESS      = 5;  // fparam = modifier (+1, 0, -1), sparam = anti spell
    public final static int SPC_BLIND      = 6;  // fparam = retail power (%), sparam = not used
    public final static int SPC_EARTHQUAKE = 7;  // fparam = walls ammount
    public final static int SPC_DISPEL     = 8;  // - none -
    public final static int SPC_CURE       = 9;  // fparam = hit points per spell power
    public final static int SPC_TOWNPORTAL = 10; // fparam = (_?_)
    public final static int SPC_ANTIMAGIC  = 11; // fparam = maxLevel
    public final static int SPC_PRAYER     = 12; // fparam = value
    public final static int SPC_VISION     = 13; // fparam = radius
    public final static int SPC_COUNT      = 14;

    // Target type mask (used for combat spells) SPELL_TARGET_TYPE_MASK
    public final static int STT_NONE        = 0b0000000;
    public final static int STT_FRIENDLY    = 0b0000001;
    public final static int STT_ENEMY       = 0b0000010;
    public final static int STT_UNDEAD      = 0b0000100;
    public final static int STT_LIFELESS    = 0b0001000;
    public final static int STT_NORMAL      = 0b0010000;
    public final static int STT_TROOPS      = 0b0100000;
    public final static int STT_SHOOTERS    = 0b1000000;
    public final static int STT_FRUNDEADS   = STT_FRIENDLY | STT_UNDEAD   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_FRNUNDEADS  = STT_FRIENDLY | STT_LIFELESS | STT_NORMAL   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_ENNUNDEADS  = STT_ENEMY    | STT_LIFELESS | STT_NORMAL   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_ENNORMALS   = STT_ENEMY    | STT_NORMAL   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_ALLUNDEADS  = STT_FRIENDLY | STT_ENEMY    | STT_UNDEAD   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_ALLNUNDEADS = STT_FRIENDLY | STT_ENEMY    | STT_NORMAL   | STT_LIFELESS | STT_TROOPS | STT_SHOOTERS;
    public final static int STT_ALLNORMALS  = STT_FRIENDLY | STT_ENEMY    | STT_NORMAL   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_FRNORMALS   = STT_FRIENDLY | STT_NORMAL   | STT_TROOPS   | STT_SHOOTERS;
    public final static int STT_FRTROOPS    = STT_FRIENDLY | STT_UNDEAD   | STT_LIFELESS | STT_NORMAL   | STT_TROOPS;
    public final static int STT_FRSHOOTERS  = STT_FRIENDLY | STT_UNDEAD   | STT_LIFELESS | STT_NORMAL   | STT_SHOOTERS;
    public final static int STT_ALLFRIENDLY = STT_FRIENDLY | STT_UNDEAD   | STT_LIFELESS | STT_NORMAL   | STT_TROOPS | STT_SHOOTERS;
    public final static int STT_ALLENEMY    = STT_ENEMY    | STT_UNDEAD   | STT_LIFELESS | STT_NORMAL   | STT_TROOPS | STT_SHOOTERS;
    public final static int STT_ALL         = STT_FRIENDLY | STT_ENEMY    | STT_UNDEAD   | STT_LIFELESS | STT_NORMAL | STT_TROOPS | STT_SHOOTERS;

    // Target mode (used for combat spells) SPELL_TARGET_MODE
    public final static int STM_NONE        = 0;
    public final static int STM_SUMMON      = 1;
    public final static int STM_EARTHQUAKE  = 2;
    public final static int STM_CREAT_GROUP = 3;
    public final static int STM_BALL_SET    = 4;
    public final static int STM_BLAST_SET   = 5;
    public final static int STM_RING_SET    = 6;
    public final static int STM_ALL         = 7;

//////////////////////////////////////////////////////
    // Spell list MAGIC_SPELL
    public final static int MSP_INVALID = -1;

    // Air magic 15-3 = 12
    public final static int MSP_MAGICARROW     = 0;
    public final static int MSP_PROTEARTH      = 1;
    public final static int MSP_HASTE          = 2;
    public final static int MSP_SHIELD         = 3;
    public final static int MSP_DISRAY         = 4;
    public final static int MSP_LIGHTNINGBOLT  = 5;
    public final static int MSP_PRECISION      = 6;
    public final static int MSP_AIRSHIELD      = 7;
    public final static int MSP_HOLYWORD       = 8;
    public final static int MSP_COUNTERSTRIKE  = 9;
    public final static int MSP_RESURRECT      = 10;
    public final static int MSP_AIRELEMENTAL   = 11;

    // Earth magic 13-3 = 10
    public final static int MSP_PROTAIR        = 12;
    public final static int MSP_SLOW           = 13;
    public final static int MSP_STONESKIN      = 14;
    public final static int MSP_VISIONS        = 15;
    public final static int MSP_EARTHQUAKE     = 16;
    public final static int MSP_SORROW         = 17;
    public final static int MSP_METEORSHOWER   = 18;
    public final static int MSP_TOWNPORTAL     = 19;
    public final static int MSP_IMPLOSION      = 20;
    public final static int MSP_EARTHELEMENTAL = 21;

    // Fire magic 15-3 = 12
    public final static int MSP_BLOODLUST      = 22;
    public final static int MSP_PROTWATER      = 23;
    public final static int MSP_CURSE          = 24;
    public final static int MSP_BLIND          = 25;
    public final static int MSP_WEAKNESS       = 26;
    public final static int MSP_DEATHRIPPLE    = 27;
    public final static int MSP_FIREBALL       = 28;
    public final static int MSP_MISFORTUNE     = 29;
    public final static int MSP_ANIMATEDEAD    = 30;
    public final static int MSP_FIREBLAST      = 31;
    public final static int MSP_ARMAGEDDON     = 32;
    public final static int MSP_FIREELEMENTAL  = 33;

    // Water magic 15-4 = 11
    public final static int MSP_BLESS          = 34;
    public final static int MSP_PROTFIRE       = 35;
    public final static int MSP_DISPEL         = 36;
    public final static int MSP_CURE           = 37;
    public final static int MSP_COLDRAY        = 38;
    public final static int MSP_FORTUNE        = 39;
    public final static int MSP_MIRTH          = 40;
    public final static int MSP_COLDRING       = 41;
    public final static int MSP_ANTIMAGIC      = 42;
    public final static int MSP_PRAYER         = 43;
    public final static int MSP_WATERELEMENTAL = 44;

    // New spells
    public final static int MSP_SUMMONSPRITES  = 45;

    // Количество магических заклинаний
    public final static int MSP_COUNT = 46;
///////////////////////////////////////////////////////

    // Spell disposition SPELL_DISPOS
    public final static int SPD_NONE     = -1;
    public final static int SPD_NEUTRAL  = 0;
    public final static int SPD_POSITIVE = 1;
    public final static int SPD_NEGATIVE = 2;
    public final static int SPD_COUNT    = 3;

    // Spell Labels SPELL_LABEL
    public final static int SLB_NONE          = 0xFF;
    public final static int SLB_PROTEARTH     = 0;
    public final static int SLB_HASTE         = 1;
    public final static int SLB_SHIELD        = 2;
    public final static int SLB_PRECISION     = 3;
    public final static int SLB_AIRSHIELD     = 4;
    public final static int SLB_COUNTERSTRIKE = 5;
    public final static int SLB_PROTAIR       = 6;
    public final static int SLB_SLOW          = 7;
    public final static int SLB_STONESKIN     = 8;
    public final static int SLB_SORROW        = 9;
    public final static int SLB_BLOODLUST     = 10;
    public final static int SLB_PROTWATER     = 11;
    public final static int SLB_CURSE         = 12;
    public final static int SLB_BLIND         = 13;
    public final static int SLB_WEAKNESS      = 14;
    public final static int SLB_MISFORTUNE    = 15;
    public final static int SLB_BLESS         = 16;
    public final static int SLB_PROTFIRE      = 17;
    public final static int SLB_FORTUNE       = 18;
    public final static int SLB_MIRTH         = 19;
    public final static int SLB_ANTIMAGIC     = 20;
    public final static int SLB_PRAYER        = 21;
    public final static int SLB_DISRAY        = 22;
    public final static int SLB_COUNT         = 23;

    // Enum spell level MAGIC_SCHOOL_LEVEL
    public final static int MSL_NONE     = 0;
    public final static int MSL_BASIC    = 1;
    public final static int MSL_ADVANCED = 2;
    public final static int MSL_EXPERT   = 3;
    public final static int MSL_COUNT    = 4;


    /**
     *
     */
    public final static SPELL_DESC_STRUCT[] SPELL_DESCRIPTORS = {
        new SPELL_DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Air magic
            //////////////////////////////////////////////////////////////////////////

            // Magic arrow
            SPT_COMBAT, SPL_FIRST, MST_AIR, SPC_DAMAGE, 5, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // None (pow*10)
                STT_ALLENEMY, STM_CREAT_GROUP, 10
            ),
            new SPELL_EFFECT_DESC(
                // Basic (pow*10)
                STT_ALLENEMY, STM_CREAT_GROUP, 10
            ),
            new SPELL_EFFECT_DESC(
                // Advanced (pow*12)
                STT_ALLENEMY, STM_CREAT_GROUP, 12
            ),
            new SPELL_EFFECT_DESC(
                // Expert (pow*15)
                STT_ALLENEMY, STM_CREAT_GROUP, 15
            )
        ),
        new SPELL_DESC_STRUCT(
            // Protection from earth
            SPT_COMBAT, SPL_FIRST, MST_AIR, SPC_FURTSKILL, 5, SLB_PROTEARTH, 0,

            new SPELL_EFFECT_DESC(
                // none (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_EARTH, 30
            ),
            new SPELL_EFFECT_DESC(
                // basic (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_EARTH, 30
            ),
            new SPELL_EFFECT_DESC(
                // advanced (50%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_EARTH, 50
            ),
            new SPELL_EFFECT_DESC(
                // expert (50% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_RES_EARTH, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Haste
            SPT_COMBAT, SPL_FIRST, MST_AIR, SPC_FURTSKILL, 6, SLB_HASTE, 0,

            new SPELL_EFFECT_DESC(
                // none (3 hexes)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SPEED, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (3 hexes)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SPEED, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (5 hexes)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SPEED, 5
            ),
            new SPELL_EFFECT_DESC(
                // expert (5 hexes + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_SPEED, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            // Shield
            SPT_COMBAT, SPL_FIRST, MST_AIR, SPC_FURTSKILL, 5, SLB_SHIELD, 0,

            new SPELL_EFFECT_DESC(
                // none (15%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SHIELD, 15
            ),
            new SPELL_EFFECT_DESC(
                // basic (15%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SHIELD, 15
            ),
            new SPELL_EFFECT_DESC(
                // advanced (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_SHIELD, 30
            ),
            new SPELL_EFFECT_DESC(
                // expert (30% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_SHIELD, 30
            )
        ),
        new SPELL_DESC_STRUCT(
            // Disrupting ray
            SPT_COMBAT, SPL_SECOND, MST_AIR, SPC_DISRAY, 10, SLB_DISRAY, 0,

            new SPELL_EFFECT_DESC(
                // none (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, 3
            ),
            new SPELL_EFFECT_DESC(
                // none (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, 3
            ),
            new SPELL_EFFECT_DESC(
                // none (-4)
                STT_ALLENEMY, STM_CREAT_GROUP, 4
            ),
            new SPELL_EFFECT_DESC(
                // none (-5)
                STT_ALLENEMY, STM_CREAT_GROUP, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            // Lightning bolt
            SPT_COMBAT, SPL_SECOND, MST_AIR, SPC_DAMAGE, 10, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*25)
                STT_ALLENEMY, STM_CREAT_GROUP, 25
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*25)
                STT_ALLENEMY, STM_CREAT_GROUP, 25
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*27)
                STT_ALLENEMY, STM_CREAT_GROUP, 27
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*30)
                STT_ALLENEMY, STM_CREAT_GROUP, 30
            )
        ),
        new SPELL_DESC_STRUCT(
            // Precision
            SPT_COMBAT, SPL_SECOND, MST_AIR, SPC_FURTSKILL, 8, SLB_PRECISION, 0,

            new SPELL_EFFECT_DESC(
                // none (+3)
                STT_FRSHOOTERS, STM_CREAT_GROUP, FSK_RANGEATTACK, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (+3)
                STT_FRSHOOTERS, STM_CREAT_GROUP, FSK_RANGEATTACK, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+5)
                STT_FRSHOOTERS, STM_CREAT_GROUP, FSK_RANGEATTACK, 5
            ),
            new SPELL_EFFECT_DESC(
                // expert (+5 + all)
                STT_FRSHOOTERS, STM_ALL, FSK_RANGEATTACK, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            // Air shield
            SPT_COMBAT, SPL_SECOND, MST_AIR, SPC_FURTSKILL, 12, SLB_AIRSHIELD, 0,

            new SPELL_EFFECT_DESC(
                // none (25%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_AIRSHIELD, 25
            ),
            new SPELL_EFFECT_DESC(
                // basic (25%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_AIRSHIELD, 25
            ),
            new SPELL_EFFECT_DESC(
                // advanced (50%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_AIRSHIELD, 50
            ),
            new SPELL_EFFECT_DESC(
                // expert (50% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_AIRSHIELD, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Holy word
            SPT_COMBAT, SPL_THIRD, MST_AIR, SPC_DAMAGE, 15, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*10)
                STT_ALLUNDEADS, STM_ALL, 10
            ),
            new SPELL_EFFECT_DESC(
                // none (pow*10)
                STT_ALLUNDEADS, STM_ALL, 10
            ),
            new SPELL_EFFECT_DESC(
                // none (pow*12)
                STT_ALLUNDEADS, STM_ALL, 12
            ),
            new SPELL_EFFECT_DESC(
                // none (pow*15)
                STT_ALLUNDEADS, STM_ALL, 15
            )
        ),
        new SPELL_DESC_STRUCT(
            // Counterstrike
            SPT_COMBAT, SPL_FOURTH, MST_AIR, SPC_FURTSKILL, 20, SLB_COUNTERSTRIKE, 0,

            new SPELL_EFFECT_DESC(
                // none (1 counterstrike)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_COUNTERSTRIKE, 1
            ),
            new SPELL_EFFECT_DESC(
                // basic (1 counterstrike)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_COUNTERSTRIKE, 1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (2 counterstrike)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_COUNTERSTRIKE, 2
            ),
            new SPELL_EFFECT_DESC(
                // expert (3 counterstrike)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_COUNTERSTRIKE, 3
            )
        ),
        new SPELL_DESC_STRUCT(
            // Resurrect
            SPT_COMBAT, SPL_FIFTH, MST_AIR, SPC_RESURRECT, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*40)
                STT_FRNORMALS, STM_CREAT_GROUP, 40, 1
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*40)
                STT_FRNORMALS, STM_CREAT_GROUP, 40, 1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*50)
                STT_FRNORMALS, STM_CREAT_GROUP, 45, 1
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*60)
                STT_FRNORMALS, STM_CREAT_GROUP, 50, 1
            )
        ),
        new SPELL_DESC_STRUCT(
            // Summon air elemental
            SPT_COMBAT, SPL_FIFTH, MST_AIR, SPC_SUMMON, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*3)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_AIR_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*3)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_AIR_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*4)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_AIR_ELEMENTAL, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*5)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_AIR_ELEMENTAL, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Earth magic
            //////////////////////////////////////////////////////////////////////////

            // Protection from air
            SPT_COMBAT, SPL_FIRST, MST_EARTH, SPC_FURTSKILL, 5, SLB_PROTAIR, 0,

            new SPELL_EFFECT_DESC(
                // none (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_AIR, 30
            ),
            new SPELL_EFFECT_DESC(
                // basic (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_AIR, 30
            ),
            new SPELL_EFFECT_DESC(
                // advanced (50%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_AIR, 50
            ),
            new SPELL_EFFECT_DESC(
                // expert (50% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_RES_AIR, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Slow
            SPT_COMBAT, SPL_FIRST, MST_EARTH, SPC_FURTSKILL, 6, SLB_SLOW, 0,

            new SPELL_EFFECT_DESC(
                // none (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_SPEED, -3
            ),
            new SPELL_EFFECT_DESC(
                // basic (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_SPEED, -3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (-6)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_SPEED, -6
            ),
            new SPELL_EFFECT_DESC(
                // expert (-6 + all)
                STT_ALLENEMY, STM_ALL, FSK_SPEED, -6
            )
        ),
        new SPELL_DESC_STRUCT(
            // Stone skin
            SPT_COMBAT, SPL_FIRST, MST_EARTH, SPC_FURTSKILL, 5, SLB_STONESKIN, 0,

            new SPELL_EFFECT_DESC(
                // none (+3)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_DEFENCE, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (+3)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_DEFENCE, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+6)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_DEFENCE, 6
            ),
            new SPELL_EFFECT_DESC(
                // expert (+6 + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_DEFENCE, 6
            )
        ),
        new SPELL_DESC_STRUCT(
            // Visions
            SPT_OVERLAND, SPL_SECOND, MST_EARTH, SPC_VISION, 10, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (5 cells)
                STT_NONE, STM_NONE, 5
            ),
            new SPELL_EFFECT_DESC(
                // none (5 cells)
                STT_NONE, STM_NONE, 5
            ),
            new SPELL_EFFECT_DESC(
                // none (10 cells)
                STT_NONE, STM_NONE, 10
            ),
            new SPELL_EFFECT_DESC(
                // none (15 cells)
                STT_NONE, STM_NONE, 15
            )
        ),
        new SPELL_DESC_STRUCT(
            // Earthquake
            SPT_COMBAT, SPL_THIRD, MST_EARTH, SPC_EARTHQUAKE, 15, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (2 walls)
                STT_ALL, STM_EARTHQUAKE, 2
            ),
            new SPELL_EFFECT_DESC(
                // basic (2 walls)
                STT_ALL, STM_EARTHQUAKE, 2
            ),
            new SPELL_EFFECT_DESC(
                // advanced (3 walls)
                STT_ALL, STM_EARTHQUAKE, 3
            ),
            new SPELL_EFFECT_DESC(
                // expert (4 walls)
                STT_ALL, STM_EARTHQUAKE, 4
            )
        ),
        new SPELL_DESC_STRUCT(
            // Sorrow
            SPT_COMBAT, SPL_FOURTH, MST_EARTH, SPC_FURTSKILL, 16, SLB_SORROW, 0,

            new SPELL_EFFECT_DESC(
                // none (-1)
                STT_ENNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, -1
            ),
            new SPELL_EFFECT_DESC(
                // basic (-1)
                STT_ENNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, -1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (-2)
                STT_ENNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, -2
            ),
            new SPELL_EFFECT_DESC(
                // expert (-2 + all)
                STT_ENNUNDEADS, STM_ALL, FSK_MORALE, -2
            )
        ),
        new SPELL_DESC_STRUCT(
            // Meteor shower
            SPT_COMBAT, SPL_FOURTH, MST_EARTH, SPC_DAMAGE, 18, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*25)
                STT_ALL, STM_BALL_SET, 25
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*25)
                STT_ALL, STM_BALL_SET, 25
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*27)
                STT_ALL, STM_BALL_SET, 27
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*30)
                STT_ALL, STM_BALL_SET, 30
            )
        ),
        new SPELL_DESC_STRUCT(
            // Town portal
            SPT_OVERLAND, SPL_FIFTH, MST_EARTH, SPC_TOWNPORTAL, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (?)
                STT_NONE, STM_NONE, 0
            ),
            new SPELL_EFFECT_DESC(
                // basic (?)
                STT_NONE, STM_NONE, 0
            ),
            new SPELL_EFFECT_DESC(
                // advanced (?)
                STT_NONE, STM_NONE, 1
            ),
            new SPELL_EFFECT_DESC(
                // expert (?)
                STT_NONE, STM_NONE, 1
            )
        ),
        new SPELL_DESC_STRUCT(
            // Implosion
            SPT_COMBAT, SPL_FIFTH, MST_EARTH, SPC_DAMAGE, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*70)
                STT_ALLENEMY, STM_CREAT_GROUP, 60
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*70)
                STT_ALLENEMY, STM_CREAT_GROUP, 60
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*75)
                STT_ALLENEMY, STM_CREAT_GROUP, 70
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*80)
                STT_ALLENEMY, STM_CREAT_GROUP, 80
            )
        ),
        new SPELL_DESC_STRUCT(
            // Summon earth elemental
            SPT_COMBAT, SPL_FIFTH, MST_EARTH, SPC_SUMMON, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*3)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_EARTH_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*3)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_EARTH_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*4)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_EARTH_ELEMENTAL, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*5)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_EARTH_ELEMENTAL, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Fire magic
            //////////////////////////////////////////////////////////////////////////

            // Bloodlust
            SPT_COMBAT, SPL_FIRST, MST_FIRE, SPC_FURTSKILL, 5, SLB_BLOODLUST, 0,

            new SPELL_EFFECT_DESC(
                // none (+3)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_MELEEATTACK, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (+3)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_MELEEATTACK, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+6)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_MELEEATTACK, 6
            ),
            new SPELL_EFFECT_DESC(
                // expert (+6 + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_MELEEATTACK, 6
            )
        ),
        new SPELL_DESC_STRUCT(
            // Protection from water
            SPT_COMBAT, SPL_FIRST, MST_FIRE, SPC_FURTSKILL, 5, SLB_PROTWATER, 0,

            new SPELL_EFFECT_DESC(
                // none (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_WATER, 30
            ),
            new SPELL_EFFECT_DESC(
                // basic (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_WATER, 30
            ),
            new SPELL_EFFECT_DESC(
                // advanced (50%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_WATER, 50
            ),
            new SPELL_EFFECT_DESC(
                // expert (50% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_RES_WATER, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Curse
            SPT_COMBAT, SPL_FIRST, MST_FIRE, SPC_BLESS, 6, SLB_CURSE, 0,

            new SPELL_EFFECT_DESC(
                // none (?)
                STT_ENNUNDEADS, STM_CREAT_GROUP, -1, MSP_BLESS
            ),
            new SPELL_EFFECT_DESC(
                // basic (?)
                STT_ENNUNDEADS, STM_CREAT_GROUP, -1, MSP_BLESS
            ),
            new SPELL_EFFECT_DESC(
                // advanced (?)
                STT_ENNUNDEADS, STM_CREAT_GROUP, -1, MSP_BLESS
            ),
            new SPELL_EFFECT_DESC(
                // expert (? + all)
                STT_ENNUNDEADS, STM_ALL, -1, MSP_BLESS
            )
        ),
        new SPELL_DESC_STRUCT(
            // Blind
            SPT_COMBAT, SPL_SECOND, MST_FIRE, SPC_BLIND, 10, SLB_BLIND, 0,

            new SPELL_EFFECT_DESC(
                // none (50% retail)
                STT_ENNORMALS, STM_CREAT_GROUP, 2
            ),
            new SPELL_EFFECT_DESC(
                // basic (50% retail)
                STT_ENNORMALS, STM_CREAT_GROUP, 2
            ),
            new SPELL_EFFECT_DESC(
                // advanced (25% retail)
                STT_ENNORMALS, STM_CREAT_GROUP, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (no retail)
                STT_ENNORMALS, STM_CREAT_GROUP, 0
            )
        ),
        new SPELL_DESC_STRUCT(
            // Weakness
            SPT_COMBAT, SPL_SECOND, MST_FIRE, SPC_FURTSKILL, 8, SLB_WEAKNESS, 0,

            new SPELL_EFFECT_DESC(
                // none (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_ATTACK, -3
            ),
            new SPELL_EFFECT_DESC(
                // basic (-3)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_ATTACK, -3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (-6)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_ATTACK, -6
            ),
            new SPELL_EFFECT_DESC(
                // expert (-6 + all)
                STT_ALLENEMY, STM_ALL, FSK_ATTACK, -6
            )
        ),
        new SPELL_DESC_STRUCT(
            // Death ripple
            SPT_COMBAT, SPL_SECOND, MST_FIRE, SPC_DAMAGE, 10, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*5)
                STT_ALLNORMALS, STM_ALL, 5
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*5)
                STT_ALLNORMALS, STM_ALL, 5
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*7)
                STT_ALLNORMALS, STM_ALL, 7
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*10)
                STT_ALLNORMALS, STM_ALL, 10
            )
        ),
        new SPELL_DESC_STRUCT(
            // Fireball
            SPT_COMBAT, SPL_THIRD, MST_FIRE, SPC_DAMAGE, 15, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*15)
                STT_ALL, STM_BALL_SET, 15
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*15)
                STT_ALL, STM_BALL_SET, 15
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*17)
                STT_ALL, STM_BALL_SET, 17
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*20)
                STT_ALL, STM_BALL_SET, 20
            )
        ),
        new SPELL_DESC_STRUCT(
            // Misfortune
            SPT_COMBAT, SPL_THIRD, MST_FIRE, SPC_FURTSKILL, 12, SLB_MISFORTUNE, 0,

            new SPELL_EFFECT_DESC(
                // none (-1)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_LUCK, -1
            ),
            new SPELL_EFFECT_DESC(
                // basic (-1)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_LUCK, -1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (-2)
                STT_ALLENEMY, STM_CREAT_GROUP, FSK_LUCK, -2
            ),
            new SPELL_EFFECT_DESC(
                // expert (-2 + all)
                STT_ALLENEMY, STM_ALL, FSK_LUCK, -2
            )
        ),
        new SPELL_DESC_STRUCT(
            // Animate dead
            SPT_COMBAT, SPL_THIRD, MST_FIRE, SPC_RESURRECT, 15, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*40)
                STT_FRUNDEADS, STM_CREAT_GROUP, 40, 1
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*40)
                STT_FRUNDEADS, STM_CREAT_GROUP, 40, 1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*45)
                STT_FRUNDEADS, STM_CREAT_GROUP, 45, 1
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*50)
                STT_FRUNDEADS, STM_CREAT_GROUP, 50, 1
            )
        ),
        new SPELL_DESC_STRUCT(
            // Fireblast
            SPT_COMBAT, SPL_FOURTH, MST_FIRE, SPC_DAMAGE, 16, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*15)
                STT_ALL, STM_BLAST_SET, 15
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*15)
                STT_ALL, STM_BLAST_SET, 15
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*17)
                STT_ALL, STM_BLAST_SET, 17
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*20)
                STT_ALL, STM_BLAST_SET, 20
            )
        ),
        new SPELL_DESC_STRUCT(
            // Armageddon
            SPT_COMBAT, SPL_FIFTH, MST_FIRE, SPC_DAMAGE, 30, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*40)
                STT_ALL, STM_ALL, 40
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*40)
                STT_ALL, STM_ALL, 40
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*45)
                STT_ALL, STM_ALL, 45
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*50)
                STT_ALL, STM_ALL, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Summon fire elemental
            SPT_COMBAT, SPL_FIFTH, MST_FIRE, SPC_SUMMON, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*3)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_FIRE_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*3)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_FIRE_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*4)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_FIRE_ELEMENTAL, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*5)
                STT_ALL, STM_SUMMON, cm_creatures.CREAT_FIRE_ELEMENTAL, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Water magic
            //////////////////////////////////////////////////////////////////////////

            // Bless
            SPT_COMBAT, SPL_FIRST, MST_WATER, SPC_BLESS, 5, SLB_BLESS, 0,

            new SPELL_EFFECT_DESC(
                // none (?)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 1, MSP_CURSE
            ),
            new SPELL_EFFECT_DESC(
                // basic (?)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 1, MSP_CURSE
            ),
            new SPELL_EFFECT_DESC(
                // advanced (?)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 1, MSP_CURSE
            ),
            new SPELL_EFFECT_DESC(
                // expert (? + all)
                STT_FRNUNDEADS, STM_ALL, 1, MSP_CURSE
            )
        ),
        new SPELL_DESC_STRUCT(
            // Protection from fire
            SPT_COMBAT, SPL_FIRST, MST_WATER, SPC_FURTSKILL, 5, SLB_PROTFIRE, 0,

            new SPELL_EFFECT_DESC(
                // none (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_FIRE, 30
            ),
            new SPELL_EFFECT_DESC(
                // basic (30%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_FIRE, 30
            ),
            new SPELL_EFFECT_DESC(
                // advanced (50%)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_RES_FIRE, 50
            ),
            new SPELL_EFFECT_DESC(
                // expert (50% + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_RES_FIRE, 50
            )
        ),
        new SPELL_DESC_STRUCT(
            // Dispel
            SPT_COMBAT, SPL_FIRST, MST_WATER, SPC_DISPEL, 5, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (friendly)
                STT_ALLFRIENDLY, STM_CREAT_GROUP
            ),
            new SPELL_EFFECT_DESC(
                // basic (friendly)
                STT_ALLFRIENDLY, STM_CREAT_GROUP
            ),
            new SPELL_EFFECT_DESC(
                // advanced (any)
                STT_ALL, STM_CREAT_GROUP
            ),
            new SPELL_EFFECT_DESC(
                // expert (all)
                STT_ALL, STM_ALL
            )
        ),
        new SPELL_DESC_STRUCT(
            // Cure
            SPT_COMBAT, SPL_FIRST, MST_WATER, SPC_CURE, 6, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*5)
                STT_FRNORMALS, STM_CREAT_GROUP, 5
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*5)
                STT_FRNORMALS, STM_CREAT_GROUP, 5
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*7)
                STT_FRNORMALS, STM_CREAT_GROUP, 7
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*7 + all)
                STT_FRNORMALS, STM_ALL, 7
            )
        ),
        new SPELL_DESC_STRUCT(
            // Cold ray
            SPT_COMBAT, SPL_SECOND, MST_WATER, SPC_DAMAGE, 8, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*20)
                STT_ALLENEMY, STM_CREAT_GROUP, 20
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*20)
                STT_ALLENEMY, STM_CREAT_GROUP, 20
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*22)
                STT_ALLENEMY, STM_CREAT_GROUP, 22
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*25)
                STT_ALLENEMY, STM_CREAT_GROUP, 25
            )
        ),
        new SPELL_DESC_STRUCT(
            // Fortune
            SPT_COMBAT, SPL_SECOND, MST_WATER, SPC_FURTSKILL, 7, SLB_FORTUNE, 0,

            new SPELL_EFFECT_DESC(
                // none (+1)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_LUCK, 1
            ),
            new SPELL_EFFECT_DESC(
                // basic (+1)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_LUCK, 1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+2)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, FSK_LUCK, 2
            ),
            new SPELL_EFFECT_DESC(
                // expert (+2 + all)
                STT_ALLFRIENDLY, STM_ALL, FSK_LUCK, 2
            )
        ),
        new SPELL_DESC_STRUCT(
            // Mirth
            SPT_COMBAT, SPL_THIRD, MST_WATER, SPC_FURTSKILL, 12, SLB_MIRTH, 0,

            new SPELL_EFFECT_DESC(
                // none (+1)
                STT_FRNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, 1
            ),
            new SPELL_EFFECT_DESC(
                // basic (+1)
                STT_FRNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, 1
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+2)
                STT_FRNUNDEADS, STM_CREAT_GROUP, FSK_MORALE, 2
            ),
            new SPELL_EFFECT_DESC(
                // expert (+2 + all)
                STT_FRNUNDEADS, STM_ALL, FSK_MORALE, 2
            )
        ),
        new SPELL_DESC_STRUCT(
            // Cold ring
            SPT_COMBAT, SPL_THIRD, MST_WATER, SPC_DAMAGE, 12, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*10)
                STT_ALLENEMY, STM_RING_SET, 10
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*10)
                STT_ALLENEMY, STM_RING_SET, 10
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*12)
                STT_ALLENEMY, STM_RING_SET, 12
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*15)
                STT_ALLENEMY, STM_RING_SET, 15
            )
        ),
        new SPELL_DESC_STRUCT(
            // Anti-magic
            SPT_COMBAT, SPL_THIRD, MST_WATER, SPC_ANTIMAGIC, 15, SLB_ANTIMAGIC, 0,

            new SPELL_EFFECT_DESC(
                // none (1-3 level)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, SPL_MASK_FIRST | SPL_MASK_SECOND | SPL_MASK_THIRD
            ),
            new SPELL_EFFECT_DESC(
                // basic (1-3 level)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, SPL_MASK_FIRST | SPL_MASK_SECOND | SPL_MASK_THIRD
            ),
            new SPELL_EFFECT_DESC(
                // advanced (1-4 level)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, SPL_MASK_FIRST | SPL_MASK_SECOND | SPL_MASK_THIRD | SPL_MASK_FOURTH
            ),
            new SPELL_EFFECT_DESC(
                // expert (all spells)
                STT_ALLFRIENDLY, STM_CREAT_GROUP, SPL_MASK_ALL
            )
        ),
        new SPELL_DESC_STRUCT(
            // Prayer
            SPT_COMBAT, SPL_FOURTH, MST_WATER, SPC_PRAYER, 18, SLB_PRAYER, 0,

            new SPELL_EFFECT_DESC(
                // none (+2)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 2
            ),
            new SPELL_EFFECT_DESC(
                // basic (+2)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 2
            ),
            new SPELL_EFFECT_DESC(
                // advanced (+4)
                STT_FRNUNDEADS, STM_CREAT_GROUP, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (+4 + all)
                STT_FRNUNDEADS, STM_ALL, 4
            )
        ),
        new SPELL_DESC_STRUCT(
            // Summon water elemental
            SPT_COMBAT, SPL_FIFTH, MST_WATER, SPC_SUMMON, 25, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*3)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_WATER_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*3)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_WATER_ELEMENTAL, 3
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*4)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_WATER_ELEMENTAL, 4
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*5)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_WATER_ELEMENTAL, 5
            )
        ),
        new SPELL_DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // New spells
            //////////////////////////////////////////////////////////////////////////

            // Summon Sprites
            SPT_COMBAT, SPL_SECOND, MST_WATER, SPC_SUMMON, 8, SLB_NONE, 0,

            new SPELL_EFFECT_DESC(
                // none (pow*5)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_SPRITE, 5
            ),
            new SPELL_EFFECT_DESC(
                // basic (pow*5)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_SPRITE, 5
            ),
            new SPELL_EFFECT_DESC(
                // advanced (pow*7)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_SPRITE, 7
            ),
            new SPELL_EFFECT_DESC(
                // expert (pow*10)
                STT_NONE, STM_SUMMON, cm_creatures.CREAT_SPRITE, 10
            )
        )
    };
}
