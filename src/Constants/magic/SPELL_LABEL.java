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
package Constants.magic;

import Constants.*;

/**
 * Spell label descriptors.
 */
public class SPELL_LABEL {
    
    public final static class DESC_STRUCT {
        public int    dispos;
        public int    trid;
        public int    reserved;

        public DESC_STRUCT(int dispos, int trid) {
            this.dispos = dispos;
            this.trid = trid;
        }
    }
    
    public final static DESC_STRUCT[] DESCRIPTORS = {
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PROTEARTH),       // SLB_PROTEARTH
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_HASTE),           // SLB_HASTE
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_SHIELD),          // SLB_SHIELD
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PRECISION),       // SLB_PRECISION
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_AIRSHIELD),       // SLB_AIRSHIELD
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_COUNTERSTRIKE),   // SLB_COUNTERSTRIKE
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PROTAIR),         // SLB_PROTAIR
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_SLOW),            // SLB_SLOW
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_STONESKIN),       // SLB_STONESKIN
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_SORROW),          // SLB_SORROW
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_BLOODLUST),       // SLB_BLOODLUST
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PROTWATER),       // SLB_PROTWATER
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_CURSE),           // SLB_CURSE
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_BLIND),           // SLB_BLIND
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_WEAKNESS),        // SLB_WEAKNESS
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_MISFORTUNE),      // SLB_MISFORTUNE
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_BLESS),           // SLB_BLESS
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PROTFIRE),        // SLB_PROTFIRE
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_FORTUNE),         // SLB_FORTUNE
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_MIRTH),           // SLB_MIRTH
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_ANTIMAGIC),       // SLB_ANTIMAGIC
        new DESC_STRUCT(SPD.POSITIVE, TRID.SPNAME_PRAYER),          // SLB_PRAYER
        new DESC_STRUCT(SPD.NEGATIVE, TRID.SPNAME_DISRAY)           // SLB_DISRAY
    };
    
}
