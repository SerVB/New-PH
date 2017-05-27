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

package Constants;

/**
 *
 */
public class SPELL {

    public final int[] LEVEL_MASKS = {
        SPL.MASK_FIRST, SPL.MASK_SECOND, SPL.MASK_THIRD, SPL.MASK_FOURTH, SPL.MASK_FIFTH
    };

    public final static DESC_STRUCT[] DESCRIPTORS = {
        new DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Air magic
            //////////////////////////////////////////////////////////////////////////

            // Magic arrow
            SPT.COMBAT, SPL.FIRST, MST.AIR, SPC.DAMAGE, 5, SLB.NONE, 0,

            new EFFECT_DESC(
                // None (pow*10)
                STT.ALLENEMY, STM.CREAT_GROUP, 10
            ),
            new EFFECT_DESC(
                // Basic (pow*10)
                STT.ALLENEMY, STM.CREAT_GROUP, 10
            ),
            new EFFECT_DESC(
                // Advanced (pow*12)
                STT.ALLENEMY, STM.CREAT_GROUP, 12
            ),
            new EFFECT_DESC(
                // Expert (pow*15)
                STT.ALLENEMY, STM.CREAT_GROUP, 15
            )
        ),
        new DESC_STRUCT(
            // Protection from earth
            SPT.COMBAT, SPL.FIRST, MST.AIR, SPC.FURTSKILL, 5, SLB.PROTEARTH, 0,

            new EFFECT_DESC(
                // none (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_EARTH, 30
            ),
            new EFFECT_DESC(
                // basic (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_EARTH, 30
            ),
            new EFFECT_DESC(
                // advanced (50%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_EARTH, 50
            ),
            new EFFECT_DESC(
                // expert (50% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.RES_EARTH, 50
            )
        ),
        new DESC_STRUCT(
            // Haste
            SPT.COMBAT, SPL.FIRST, MST.AIR, SPC.FURTSKILL, 6, SLB.HASTE, 0,

            new EFFECT_DESC(
                // none (3 hexes)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SPEED, 3
            ),
            new EFFECT_DESC(
                // basic (3 hexes)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SPEED, 3
            ),
            new EFFECT_DESC(
                // advanced (5 hexes)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SPEED, 5
            ),
            new EFFECT_DESC(
                // expert (5 hexes + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.SPEED, 5
            )
        ),
        new DESC_STRUCT(
            // Shield
            SPT.COMBAT, SPL.FIRST, MST.AIR, SPC.FURTSKILL, 5, SLB.SHIELD, 0,

            new EFFECT_DESC(
                // none (15%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SHIELD, 15
            ),
            new EFFECT_DESC(
                // basic (15%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SHIELD, 15
            ),
            new EFFECT_DESC(
                // advanced (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.SHIELD, 30
            ),
            new EFFECT_DESC(
                // expert (30% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.SHIELD, 30
            )
        ),
        new DESC_STRUCT(
            // Disrupting ray
            SPT.COMBAT, SPL.SECOND, MST.AIR, SPC.DISRAY, 10, SLB.DISRAY, 0,

            new EFFECT_DESC(
                // none (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, 3
            ),
            new EFFECT_DESC(
                // none (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, 3
            ),
            new EFFECT_DESC(
                // none (-4)
                STT.ALLENEMY, STM.CREAT_GROUP, 4
            ),
            new EFFECT_DESC(
                // none (-5)
                STT.ALLENEMY, STM.CREAT_GROUP, 5
            )
        ),
        new DESC_STRUCT(
            // Lightning bolt
            SPT.COMBAT, SPL.SECOND, MST.AIR, SPC.DAMAGE, 10, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*25)
                STT.ALLENEMY, STM.CREAT_GROUP, 25
            ),
            new EFFECT_DESC(
                // basic (pow*25)
                STT.ALLENEMY, STM.CREAT_GROUP, 25
            ),
            new EFFECT_DESC(
                // advanced (pow*27)
                STT.ALLENEMY, STM.CREAT_GROUP, 27
            ),
            new EFFECT_DESC(
                // expert (pow*30)
                STT.ALLENEMY, STM.CREAT_GROUP, 30
            )
        ),
        new DESC_STRUCT(
            // Precision
            SPT.COMBAT, SPL.SECOND, MST.AIR, SPC.FURTSKILL, 8, SLB.PRECISION, 0,

            new EFFECT_DESC(
                // none (+3)
                STT.FRSHOOTERS, STM.CREAT_GROUP, FSK.RANGEATTACK, 3
            ),
            new EFFECT_DESC(
                // basic (+3)
                STT.FRSHOOTERS, STM.CREAT_GROUP, FSK.RANGEATTACK, 3
            ),
            new EFFECT_DESC(
                // advanced (+5)
                STT.FRSHOOTERS, STM.CREAT_GROUP, FSK.RANGEATTACK, 5
            ),
            new EFFECT_DESC(
                // expert (+5 + all)
                STT.FRSHOOTERS, STM.ALL, FSK.RANGEATTACK, 5
            )
        ),
        new DESC_STRUCT(
            // Air shield
            SPT.COMBAT, SPL.SECOND, MST.AIR, SPC.FURTSKILL, 12, SLB.AIRSHIELD, 0,

            new EFFECT_DESC(
                // none (25%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.AIRSHIELD, 25
            ),
            new EFFECT_DESC(
                // basic (25%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.AIRSHIELD, 25
            ),
            new EFFECT_DESC(
                // advanced (50%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.AIRSHIELD, 50
            ),
            new EFFECT_DESC(
                // expert (50% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.AIRSHIELD, 50
            )
        ),
        new DESC_STRUCT(
            // Holy word
            SPT.COMBAT, SPL.THIRD, MST.AIR, SPC.DAMAGE, 15, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*10)
                STT.ALLUNDEADS, STM.ALL, 10
            ),
            new EFFECT_DESC(
                // none (pow*10)
                STT.ALLUNDEADS, STM.ALL, 10
            ),
            new EFFECT_DESC(
                // none (pow*12)
                STT.ALLUNDEADS, STM.ALL, 12
            ),
            new EFFECT_DESC(
                // none (pow*15)
                STT.ALLUNDEADS, STM.ALL, 15
            )
        ),
        new DESC_STRUCT(
            // Counterstrike
            SPT.COMBAT, SPL.FOURTH, MST.AIR, SPC.FURTSKILL, 20, SLB.COUNTERSTRIKE, 0,

            new EFFECT_DESC(
                // none (1 counterstrike)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.COUNTERSTRIKE, 1
            ),
            new EFFECT_DESC(
                // basic (1 counterstrike)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.COUNTERSTRIKE, 1
            ),
            new EFFECT_DESC(
                // advanced (2 counterstrike)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.COUNTERSTRIKE, 2
            ),
            new EFFECT_DESC(
                // expert (3 counterstrike)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.COUNTERSTRIKE, 3
            )
        ),
        new DESC_STRUCT(
            // Resurrect
            SPT.COMBAT, SPL.FIFTH, MST.AIR, SPC.RESURRECT, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*40)
                STT.FRNORMALS, STM.CREAT_GROUP, 40, 1
            ),
            new EFFECT_DESC(
                // basic (pow*40)
                STT.FRNORMALS, STM.CREAT_GROUP, 40, 1
            ),
            new EFFECT_DESC(
                // advanced (pow*50)
                STT.FRNORMALS, STM.CREAT_GROUP, 45, 1
            ),
            new EFFECT_DESC(
                // expert (pow*60)
                STT.FRNORMALS, STM.CREAT_GROUP, 50, 1
            )
        ),
        new DESC_STRUCT(
            // Summon air elemental
            SPT.COMBAT, SPL.FIFTH, MST.AIR, SPC.SUMMON, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*3)
                STT.ALL, STM.SUMMON, CREAT.AIR_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // basic (pow*3)
                STT.ALL, STM.SUMMON, CREAT.AIR_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // advanced (pow*4)
                STT.ALL, STM.SUMMON, CREAT.AIR_ELEMENTAL, 4
            ),
            new EFFECT_DESC(
                // expert (pow*5)
                STT.ALL, STM.SUMMON, CREAT.AIR_ELEMENTAL, 5
            )
        ),
        new DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Earth magic
            //////////////////////////////////////////////////////////////////////////

            // Protection from air
            SPT.COMBAT, SPL.FIRST, MST.EARTH, SPC.FURTSKILL, 5, SLB.PROTAIR, 0,

            new EFFECT_DESC(
                // none (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_AIR, 30
            ),
            new EFFECT_DESC(
                // basic (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_AIR, 30
            ),
            new EFFECT_DESC(
                // advanced (50%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_AIR, 50
            ),
            new EFFECT_DESC(
                // expert (50% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.RES_AIR, 50
            )
        ),
        new DESC_STRUCT(
            // Slow
            SPT.COMBAT, SPL.FIRST, MST.EARTH, SPC.FURTSKILL, 6, SLB.SLOW, 0,

            new EFFECT_DESC(
                // none (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.SPEED, -3
            ),
            new EFFECT_DESC(
                // basic (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.SPEED, -3
            ),
            new EFFECT_DESC(
                // advanced (-6)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.SPEED, -6
            ),
            new EFFECT_DESC(
                // expert (-6 + all)
                STT.ALLENEMY, STM.ALL, FSK.SPEED, -6
            )
        ),
        new DESC_STRUCT(
            // Stone skin
            SPT.COMBAT, SPL.FIRST, MST.EARTH, SPC.FURTSKILL, 5, SLB.STONESKIN, 0,

            new EFFECT_DESC(
                // none (+3)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.DEFENCE, 3
            ),
            new EFFECT_DESC(
                // basic (+3)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.DEFENCE, 3
            ),
            new EFFECT_DESC(
                // advanced (+6)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.DEFENCE, 6
            ),
            new EFFECT_DESC(
                // expert (+6 + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.DEFENCE, 6
            )
        ),
        new DESC_STRUCT(
            // Visions
            SPT.OVERLAND, SPL.SECOND, MST.EARTH, SPC.VISION, 10, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (5 cells)
                STT.NONE, STM.NONE, 5
            ),
            new EFFECT_DESC(
                // none (5 cells)
                STT.NONE, STM.NONE, 5
            ),
            new EFFECT_DESC(
                // none (10 cells)
                STT.NONE, STM.NONE, 10
            ),
            new EFFECT_DESC(
                // none (15 cells)
                STT.NONE, STM.NONE, 15
            )
        ),
        new DESC_STRUCT(
            // Earthquake
            SPT.COMBAT, SPL.THIRD, MST.EARTH, SPC.EARTHQUAKE, 15, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (2 walls)
                STT.ALL, STM.EARTHQUAKE, 2
            ),
            new EFFECT_DESC(
                // basic (2 walls)
                STT.ALL, STM.EARTHQUAKE, 2
            ),
            new EFFECT_DESC(
                // advanced (3 walls)
                STT.ALL, STM.EARTHQUAKE, 3
            ),
            new EFFECT_DESC(
                // expert (4 walls)
                STT.ALL, STM.EARTHQUAKE, 4
            )
        ),
        new DESC_STRUCT(
            // Sorrow
            SPT.COMBAT, SPL.FOURTH, MST.EARTH, SPC.FURTSKILL, 16, SLB.SORROW, 0,

            new EFFECT_DESC(
                // none (-1)
                STT.ENNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, -1
            ),
            new EFFECT_DESC(
                // basic (-1)
                STT.ENNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, -1
            ),
            new EFFECT_DESC(
                // advanced (-2)
                STT.ENNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, -2
            ),
            new EFFECT_DESC(
                // expert (-2 + all)
                STT.ENNUNDEADS, STM.ALL, FSK.MORALE, -2
            )
        ),
        new DESC_STRUCT(
            // Meteor shower
            SPT.COMBAT, SPL.FOURTH, MST.EARTH, SPC.DAMAGE, 18, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*25)
                STT.ALL, STM.BALL_SET, 25
            ),
            new EFFECT_DESC(
                // basic (pow*25)
                STT.ALL, STM.BALL_SET, 25
            ),
            new EFFECT_DESC(
                // advanced (pow*27)
                STT.ALL, STM.BALL_SET, 27
            ),
            new EFFECT_DESC(
                // expert (pow*30)
                STT.ALL, STM.BALL_SET, 30
            )
        ),
        new DESC_STRUCT(
            // Town portal
            SPT.OVERLAND, SPL.FIFTH, MST.EARTH, SPC.TOWNPORTAL, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (?)
                STT.NONE, STM.NONE, 0
            ),
            new EFFECT_DESC(
                // basic (?)
                STT.NONE, STM.NONE, 0
            ),
            new EFFECT_DESC(
                // advanced (?)
                STT.NONE, STM.NONE, 1
            ),
            new EFFECT_DESC(
                // expert (?)
                STT.NONE, STM.NONE, 1
            )
        ),
        new DESC_STRUCT(
            // Implosion
            SPT.COMBAT, SPL.FIFTH, MST.EARTH, SPC.DAMAGE, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*70)
                STT.ALLENEMY, STM.CREAT_GROUP, 60
            ),
            new EFFECT_DESC(
                // basic (pow*70)
                STT.ALLENEMY, STM.CREAT_GROUP, 60
            ),
            new EFFECT_DESC(
                // advanced (pow*75)
                STT.ALLENEMY, STM.CREAT_GROUP, 70
            ),
            new EFFECT_DESC(
                // expert (pow*80)
                STT.ALLENEMY, STM.CREAT_GROUP, 80
            )
        ),
        new DESC_STRUCT(
            // Summon earth elemental
            SPT.COMBAT, SPL.FIFTH, MST.EARTH, SPC.SUMMON, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*3)
                STT.NONE, STM.SUMMON, CREAT.EARTH_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // basic (pow*3)
                STT.NONE, STM.SUMMON, CREAT.EARTH_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // advanced (pow*4)
                STT.NONE, STM.SUMMON, CREAT.EARTH_ELEMENTAL, 4
            ),
            new EFFECT_DESC(
                // expert (pow*5)
                STT.NONE, STM.SUMMON, CREAT.EARTH_ELEMENTAL, 5
            )
        ),
        new DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Fire magic
            //////////////////////////////////////////////////////////////////////////

            // Bloodlust
            SPT.COMBAT, SPL.FIRST, MST.FIRE, SPC.FURTSKILL, 5, SLB.BLOODLUST, 0,

            new EFFECT_DESC(
                // none (+3)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.MELEEATTACK, 3
            ),
            new EFFECT_DESC(
                // basic (+3)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.MELEEATTACK, 3
            ),
            new EFFECT_DESC(
                // advanced (+6)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.MELEEATTACK, 6
            ),
            new EFFECT_DESC(
                // expert (+6 + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.MELEEATTACK, 6
            )
        ),
        new DESC_STRUCT(
            // Protection from water
            SPT.COMBAT, SPL.FIRST, MST.FIRE, SPC.FURTSKILL, 5, SLB.PROTWATER, 0,

            new EFFECT_DESC(
                // none (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_WATER, 30
            ),
            new EFFECT_DESC(
                // basic (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_WATER, 30
            ),
            new EFFECT_DESC(
                // advanced (50%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_WATER, 50
            ),
            new EFFECT_DESC(
                // expert (50% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.RES_WATER, 50
            )
        ),
        new DESC_STRUCT(
            // Curse
            SPT.COMBAT, SPL.FIRST, MST.FIRE, SPC.BLESS, 6, SLB.CURSE, 0,

            new EFFECT_DESC(
                // none (?)
                STT.ENNUNDEADS, STM.CREAT_GROUP, -1, MSP.BLESS
            ),
            new EFFECT_DESC(
                // basic (?)
                STT.ENNUNDEADS, STM.CREAT_GROUP, -1, MSP.BLESS
            ),
            new EFFECT_DESC(
                // advanced (?)
                STT.ENNUNDEADS, STM.CREAT_GROUP, -1, MSP.BLESS
            ),
            new EFFECT_DESC(
                // expert (? + all)
                STT.ENNUNDEADS, STM.ALL, -1, MSP.BLESS
            )
        ),
        new DESC_STRUCT(
            // Blind
            SPT.COMBAT, SPL.SECOND, MST.FIRE, SPC.BLIND, 10, SLB.BLIND, 0,

            new EFFECT_DESC(
                // none (50% retail)
                STT.ENNORMALS, STM.CREAT_GROUP, 2
            ),
            new EFFECT_DESC(
                // basic (50% retail)
                STT.ENNORMALS, STM.CREAT_GROUP, 2
            ),
            new EFFECT_DESC(
                // advanced (25% retail)
                STT.ENNORMALS, STM.CREAT_GROUP, 4
            ),
            new EFFECT_DESC(
                // expert (no retail)
                STT.ENNORMALS, STM.CREAT_GROUP, 0
            )
        ),
        new DESC_STRUCT(
            // Weakness
            SPT.COMBAT, SPL.SECOND, MST.FIRE, SPC.FURTSKILL, 8, SLB.WEAKNESS, 0,

            new EFFECT_DESC(
                // none (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.ATTACK, -3
            ),
            new EFFECT_DESC(
                // basic (-3)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.ATTACK, -3
            ),
            new EFFECT_DESC(
                // advanced (-6)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.ATTACK, -6
            ),
            new EFFECT_DESC(
                // expert (-6 + all)
                STT.ALLENEMY, STM.ALL, FSK.ATTACK, -6
            )
        ),
        new DESC_STRUCT(
            // Death ripple
            SPT.COMBAT, SPL.SECOND, MST.FIRE, SPC.DAMAGE, 10, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*5)
                STT.ALLNORMALS, STM.ALL, 5
            ),
            new EFFECT_DESC(
                // basic (pow*5)
                STT.ALLNORMALS, STM.ALL, 5
            ),
            new EFFECT_DESC(
                // advanced (pow*7)
                STT.ALLNORMALS, STM.ALL, 7
            ),
            new EFFECT_DESC(
                // expert (pow*10)
                STT.ALLNORMALS, STM.ALL, 10
            )
        ),
        new DESC_STRUCT(
            // Fireball
            SPT.COMBAT, SPL.THIRD, MST.FIRE, SPC.DAMAGE, 15, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*15)
                STT.ALL, STM.BALL_SET, 15
            ),
            new EFFECT_DESC(
                // basic (pow*15)
                STT.ALL, STM.BALL_SET, 15
            ),
            new EFFECT_DESC(
                // advanced (pow*17)
                STT.ALL, STM.BALL_SET, 17
            ),
            new EFFECT_DESC(
                // expert (pow*20)
                STT.ALL, STM.BALL_SET, 20
            )
        ),
        new DESC_STRUCT(
            // Misfortune
            SPT.COMBAT, SPL.THIRD, MST.FIRE, SPC.FURTSKILL, 12, SLB.MISFORTUNE, 0,

            new EFFECT_DESC(
                // none (-1)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.LUCK, -1
            ),
            new EFFECT_DESC(
                // basic (-1)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.LUCK, -1
            ),
            new EFFECT_DESC(
                // advanced (-2)
                STT.ALLENEMY, STM.CREAT_GROUP, FSK.LUCK, -2
            ),
            new EFFECT_DESC(
                // expert (-2 + all)
                STT.ALLENEMY, STM.ALL, FSK.LUCK, -2
            )
        ),
        new DESC_STRUCT(
            // Animate dead
            SPT.COMBAT, SPL.THIRD, MST.FIRE, SPC.RESURRECT, 15, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*40)
                STT.FRUNDEADS, STM.CREAT_GROUP, 40, 1
            ),
            new EFFECT_DESC(
                // basic (pow*40)
                STT.FRUNDEADS, STM.CREAT_GROUP, 40, 1
            ),
            new EFFECT_DESC(
                // advanced (pow*45)
                STT.FRUNDEADS, STM.CREAT_GROUP, 45, 1
            ),
            new EFFECT_DESC(
                // expert (pow*50)
                STT.FRUNDEADS, STM.CREAT_GROUP, 50, 1
            )
        ),
        new DESC_STRUCT(
            // Fireblast
            SPT.COMBAT, SPL.FOURTH, MST.FIRE, SPC.DAMAGE, 16, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*15)
                STT.ALL, STM.BLAST_SET, 15
            ),
            new EFFECT_DESC(
                // basic (pow*15)
                STT.ALL, STM.BLAST_SET, 15
            ),
            new EFFECT_DESC(
                // advanced (pow*17)
                STT.ALL, STM.BLAST_SET, 17
            ),
            new EFFECT_DESC(
                // expert (pow*20)
                STT.ALL, STM.BLAST_SET, 20
            )
        ),
        new DESC_STRUCT(
            // Armageddon
            SPT.COMBAT, SPL.FIFTH, MST.FIRE, SPC.DAMAGE, 30, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*40)
                STT.ALL, STM.ALL, 40
            ),
            new EFFECT_DESC(
                // basic (pow*40)
                STT.ALL, STM.ALL, 40
            ),
            new EFFECT_DESC(
                // advanced (pow*45)
                STT.ALL, STM.ALL, 45
            ),
            new EFFECT_DESC(
                // expert (pow*50)
                STT.ALL, STM.ALL, 50
            )
        ),
        new DESC_STRUCT(
            // Summon fire elemental
            SPT.COMBAT, SPL.FIFTH, MST.FIRE, SPC.SUMMON, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*3)
                STT.ALL, STM.SUMMON, CREAT.FIRE_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // basic (pow*3)
                STT.ALL, STM.SUMMON, CREAT.FIRE_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // advanced (pow*4)
                STT.ALL, STM.SUMMON, CREAT.FIRE_ELEMENTAL, 4
            ),
            new EFFECT_DESC(
                // expert (pow*5)
                STT.ALL, STM.SUMMON, CREAT.FIRE_ELEMENTAL, 5
            )
        ),
        new DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // Water magic
            //////////////////////////////////////////////////////////////////////////

            // Bless
            SPT.COMBAT, SPL.FIRST, MST.WATER, SPC.BLESS, 5, SLB.BLESS, 0,

            new EFFECT_DESC(
                // none (?)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 1, MSP.CURSE
            ),
            new EFFECT_DESC(
                // basic (?)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 1, MSP.CURSE
            ),
            new EFFECT_DESC(
                // advanced (?)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 1, MSP.CURSE
            ),
            new EFFECT_DESC(
                // expert (? + all)
                STT.FRNUNDEADS, STM.ALL, 1, MSP.CURSE
            )
        ),
        new DESC_STRUCT(
            // Protection from fire
            SPT.COMBAT, SPL.FIRST, MST.WATER, SPC.FURTSKILL, 5, SLB.PROTFIRE, 0,

            new EFFECT_DESC(
                // none (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_FIRE, 30
            ),
            new EFFECT_DESC(
                // basic (30%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_FIRE, 30
            ),
            new EFFECT_DESC(
                // advanced (50%)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.RES_FIRE, 50
            ),
            new EFFECT_DESC(
                // expert (50% + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.RES_FIRE, 50
            )
        ),
        new DESC_STRUCT(
            // Dispel
            SPT.COMBAT, SPL.FIRST, MST.WATER, SPC.DISPEL, 5, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (friendly)
                STT.ALLFRIENDLY, STM.CREAT_GROUP
            ),
            new EFFECT_DESC(
                // basic (friendly)
                STT.ALLFRIENDLY, STM.CREAT_GROUP
            ),
            new EFFECT_DESC(
                // advanced (any)
                STT.ALL, STM.CREAT_GROUP
            ),
            new EFFECT_DESC(
                // expert (all)
                STT.ALL, STM.ALL
            )
        ),
        new DESC_STRUCT(
            // Cure
            SPT.COMBAT, SPL.FIRST, MST.WATER, SPC.CURE, 6, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*5)
                STT.FRNORMALS, STM.CREAT_GROUP, 5
            ),
            new EFFECT_DESC(
                // basic (pow*5)
                STT.FRNORMALS, STM.CREAT_GROUP, 5
            ),
            new EFFECT_DESC(
                // advanced (pow*7)
                STT.FRNORMALS, STM.CREAT_GROUP, 7
            ),
            new EFFECT_DESC(
                // expert (pow*7 + all)
                STT.FRNORMALS, STM.ALL, 7
            )
        ),
        new DESC_STRUCT(
            // Cold ray
            SPT.COMBAT, SPL.SECOND, MST.WATER, SPC.DAMAGE, 8, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*20)
                STT.ALLENEMY, STM.CREAT_GROUP, 20
            ),
            new EFFECT_DESC(
                // basic (pow*20)
                STT.ALLENEMY, STM.CREAT_GROUP, 20
            ),
            new EFFECT_DESC(
                // advanced (pow*22)
                STT.ALLENEMY, STM.CREAT_GROUP, 22
            ),
            new EFFECT_DESC(
                // expert (pow*25)
                STT.ALLENEMY, STM.CREAT_GROUP, 25
            )
        ),
        new DESC_STRUCT(
            // Fortune
            SPT.COMBAT, SPL.SECOND, MST.WATER, SPC.FURTSKILL, 7, SLB.FORTUNE, 0,

            new EFFECT_DESC(
                // none (+1)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.LUCK, 1
            ),
            new EFFECT_DESC(
                // basic (+1)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.LUCK, 1
            ),
            new EFFECT_DESC(
                // advanced (+2)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, FSK.LUCK, 2
            ),
            new EFFECT_DESC(
                // expert (+2 + all)
                STT.ALLFRIENDLY, STM.ALL, FSK.LUCK, 2
            )
        ),
        new DESC_STRUCT(
            // Mirth
            SPT.COMBAT, SPL.THIRD, MST.WATER, SPC.FURTSKILL, 12, SLB.MIRTH, 0,

            new EFFECT_DESC(
                // none (+1)
                STT.FRNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, 1
            ),
            new EFFECT_DESC(
                // basic (+1)
                STT.FRNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, 1
            ),
            new EFFECT_DESC(
                // advanced (+2)
                STT.FRNUNDEADS, STM.CREAT_GROUP, FSK.MORALE, 2
            ),
            new EFFECT_DESC(
                // expert (+2 + all)
                STT.FRNUNDEADS, STM.ALL, FSK.MORALE, 2
            )
        ),
        new DESC_STRUCT(
            // Cold ring
            SPT.COMBAT, SPL.THIRD, MST.WATER, SPC.DAMAGE, 12, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*10)
                STT.ALLENEMY, STM.RING_SET, 10
            ),
            new EFFECT_DESC(
                // basic (pow*10)
                STT.ALLENEMY, STM.RING_SET, 10
            ),
            new EFFECT_DESC(
                // advanced (pow*12)
                STT.ALLENEMY, STM.RING_SET, 12
            ),
            new EFFECT_DESC(
                // expert (pow*15)
                STT.ALLENEMY, STM.RING_SET, 15
            )
        ),
        new DESC_STRUCT(
            // Anti-magic
            SPT.COMBAT, SPL.THIRD, MST.WATER, SPC.ANTIMAGIC, 15, SLB.ANTIMAGIC, 0,

            new EFFECT_DESC(
                // none (1-3 level)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, SPL.MASK_FIRST | SPL.MASK_SECOND | SPL.MASK_THIRD
            ),
            new EFFECT_DESC(
                // basic (1-3 level)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, SPL.MASK_FIRST | SPL.MASK_SECOND | SPL.MASK_THIRD
            ),
            new EFFECT_DESC(
                // advanced (1-4 level)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, SPL.MASK_FIRST | SPL.MASK_SECOND | SPL.MASK_THIRD | SPL.MASK_FOURTH
            ),
            new EFFECT_DESC(
                // expert (all spells)
                STT.ALLFRIENDLY, STM.CREAT_GROUP, SPL.MASK_ALL
            )
        ),
        new DESC_STRUCT(
            // Prayer
            SPT.COMBAT, SPL.FOURTH, MST.WATER, SPC.PRAYER, 18, SLB.PRAYER, 0,

            new EFFECT_DESC(
                // none (+2)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 2
            ),
            new EFFECT_DESC(
                // basic (+2)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 2
            ),
            new EFFECT_DESC(
                // advanced (+4)
                STT.FRNUNDEADS, STM.CREAT_GROUP, 4
            ),
            new EFFECT_DESC(
                // expert (+4 + all)
                STT.FRNUNDEADS, STM.ALL, 4
            )
        ),
        new DESC_STRUCT(
            // Summon water elemental
            SPT.COMBAT, SPL.FIFTH, MST.WATER, SPC.SUMMON, 25, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*3)
                STT.NONE, STM.SUMMON, CREAT.WATER_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // basic (pow*3)
                STT.NONE, STM.SUMMON, CREAT.WATER_ELEMENTAL, 3
            ),
            new EFFECT_DESC(
                // advanced (pow*4)
                STT.NONE, STM.SUMMON, CREAT.WATER_ELEMENTAL, 4
            ),
            new EFFECT_DESC(
                // expert (pow*5)
                STT.NONE, STM.SUMMON, CREAT.WATER_ELEMENTAL, 5
            )
        ),
        new DESC_STRUCT(
            //////////////////////////////////////////////////////////////////////////
            // New spells
            //////////////////////////////////////////////////////////////////////////

            // Summon Sprites
            SPT.COMBAT, SPL.SECOND, MST.WATER, SPC.SUMMON, 8, SLB.NONE, 0,

            new EFFECT_DESC(
                // none (pow*5)
                STT.NONE, STM.SUMMON, CREAT.SPRITE, 5
            ),
            new EFFECT_DESC(
                // basic (pow*5)
                STT.NONE, STM.SUMMON, CREAT.SPRITE, 5
            ),
            new EFFECT_DESC(
                // advanced (pow*7)
                STT.NONE, STM.SUMMON, CREAT.SPRITE, 7
            ),
            new EFFECT_DESC(
                // expert (pow*10)
                STT.NONE, STM.SUMMON, CREAT.SPRITE, 10
            )
        )
    };


    /**
     * Описатель заклинания.
     * Spell descriptor.
     * ( 8 + 8 x 4 = 40 bytes )
     */
    public static class DESC_STRUCT {

        /**
         * Тип.
         */
        public long type;

        /**
         * Уровень.
         */
        public long level;

        /**
         * Школа.
         */
        public long school;

        /**
         *
         */
        public long spClass;

        /**
         * Стоимость.
         */
        public long bcost;

        /**
         *
         */
        public long label;

        /**
         *
         */
        public long reserved;

        /**
         * Хранилище эффектов.
         */
        public EFFECT_DESC[] eff = new EFFECT_DESC[MSL.COUNT];

        /**
         * Конструктор.
         * @param type Тип.
         * @param level Уровень.
         * @param school Школа.
         * @param spClass
         * @param bcost Стоимость.
         * @param label
         * @param reserved
         * @param eff0 Эффект 0.
         * @param eff1 Эффект 1.
         * @param eff2 Эффект 2.
         * @param eff3 Эффект 3.
         */
        public DESC_STRUCT(
                long type,
                long level,
                long school,
                long spClass,
                long bcost,
                long label,
                long reserved,

                EFFECT_DESC eff0,
                EFFECT_DESC eff1,
                EFFECT_DESC eff2,
                EFFECT_DESC eff3
        ) {
            this.type = type;
            this.level = level;
            this.school = school;
            this.spClass = spClass;
            this.bcost = bcost;
            this.label = label;
            this.reserved = reserved;

            this.eff[0] = eff0;
            this.eff[1] = eff1;
            this.eff[2] = eff2;
            this.eff[3] = eff3;
        }

    }


    /**
     * Описатель эффекта заклинания.
     * Spell effect descriptor.
     * ( 4 x 2 = 8 bytes )
     */
    public static class EFFECT_DESC {

        /**
         * Target type.
         */
        public int tgtType;

        /**
         * Target mode.
         */
        public int tgtMode;

        /**
         * First parameter.
         */
        public int fparam;

        /**
         * Second parameter.
         */
        public long sparam;

        /**
         * Конструктор без двух параметров.
         * @param tgtType Target type.
         * @param tgtMode Target mode.
         */
        public EFFECT_DESC(int tgtType, int tgtMode) {
            this.tgtType = tgtType;
            this.tgtMode = tgtMode;
        }

        /**
         * Конструктор без одного параметра.
         * @param tgtType Target type.
         * @param tgtMode Target mode.
         * @param fparam First parameter.
         */
        public EFFECT_DESC(int tgtType, int tgtMode, int fparam) {
            this.tgtType = tgtType;
            this.tgtMode = tgtMode;
            this.fparam = fparam;
        }

        /**
         * Конструктор.
         * @param tgtType Target type.
         * @param tgtMode Target mode.
         * @param fparam First parameter.
         * @param sparam Second parameter.
         */
        public EFFECT_DESC(int tgtType, int tgtMode, int fparam, long sparam) {
            this.tgtType = tgtType;
            this.tgtMode = tgtMode;
            this.fparam = fparam;
            this.sparam = sparam;
        }

    }

}
