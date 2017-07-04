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
package Game.magic.combatSpell;

import Constants.*;
import Constants.magic.SPELL_LABEL;
import Game.iBattleGroup;
import Game.iBattleUnit_Hero;
import Game.iHero;
import Game.magic.iBaseSpell;
import newph.core.staticFunction.Tracer;

/**
 * Combat spell.
 */
public class iCombatSpell extends iBaseSpell {
  
    public iCombatSpell(final int spellId) {
        super(spellId) ;
        Tracer.check(SPELL.DESCRIPTORS[spellId].type == SPT.COMBAT); 
    }

    //
    public int TargetMode(final int level) {
        return SPELL.DESCRIPTORS[m_spellId].eff[level].tgtMode;
    }
    
    public int TargetType(final int level) {
        return SPELL.DESCRIPTORS[m_spellId].eff[level].tgtType;
    }
    
    public boolean NeedTarget(final int level) {
        return 
                TargetMode(level) == STM.CREAT_GROUP ||
                TargetMode(level) == STM.BALL_SET ||
                TargetMode(level) == STM.BLAST_SET ||
                TargetMode(level) == STM.RING_SET;
    }
    
    public boolean HasCoverArea(final int level) {
        return 
                TargetMode(level) == STM.BALL_SET ||
                TargetMode(level) == STM.BLAST_SET ||
                TargetMode(level) == STM.RING_SET;
    }
    
    public int SpellDispos() {
        return SPELL_LABEL.DESCRIPTORS[SPELL.DESCRIPTORS[m_spellId].label].dispos;
    }
    
    public int SpellLabel() {
        Tracer.check(SPELL.DESCRIPTORS[m_spellId].label != SLB.NONE);
        
        return SPELL_LABEL.DESCRIPTORS[SPELL.DESCRIPTORS[m_spellId].label].trid;
    }

    // Virtuals
    public boolean CanCast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        Tracer.check(pCaster != null);
        int msl = GetSchoolLevel(pCaster.GetHero());
        int tt = TargetType(msl);
        boolean bFriendly = pCaster.Owner().GetSide() == pTarget.Owner().GetSide();
        
        if ( !AffectsToDead() && !pTarget.IsAlive() ) {
            return false;
        } else if ( (pTarget.Perks() & (1 << (School() + 12))) != 0 ) {
            return false;
        } else if ( pTarget.SpellImmunity(Level()) ) {
            return false;
        } else if ( (tt & STT.FRIENDLY) == 0 && bFriendly ) {
            return false;
        } else if ( (tt & STT.ENEMY) == 0 && !bFriendly ) {
            return false;
        } else if ( (tt & STT.UNDEAD) == 0 && (pTarget.Perks() & CPERK.UNDEAD) != 0 ) {
            return false;
        } else if ( (tt & STT.LIFELESS) == 0 && (pTarget.Perks() & CPERK.LIFELESS) != 0 ) {
            return false;
        } else if ( (tt & STT.NORMAL) == 0 && (pTarget.Perks() & CPERK.LIFELESS) == 0 && (pTarget.Perks() & CPERK.UNDEAD) == 0) {
            return false;
        } else if ( (tt & STT.TROOPS) == 0 && !pTarget.CanShoot() ) {
            return false;
        } else if ( (tt & STT.SHOOTERS) == 0 && pTarget.CanShoot() ) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean Cast(final iBattleUnit_Hero pCaster, final iBattleGroup pTarget) {
        return true;
    }
    
    public void OnRemove(final iBattleGroup pTarget, final int param) {}
    
    public String ActionText(final iHero pCaster) {
        return "";
    }
    
    public boolean AffectsToDead() {
        return false;
    }  
    
}
