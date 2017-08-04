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

package newph.core.common;

import java.util.Arrays;
import newph.core.enumeration.FurtherSkill;
import newph.core.enumeration.PrSkillType;
import newph.core.staticFunction.Tracer;

/**
 * Furt Skills.
 *
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iFurtSkills {

    public iFurtSkills() {
        values = new int[FurtherSkill.FSK_COUNT.getValue()];

        Reset();
    }

    public iFurtSkills(final iFurtSkills other) {
        this();

        System.arraycopy(other.values, 0, this.values, 0, other.values.length);
    }

    public void Reset() {
        Arrays.fill(values, 0);
    }

    public int Value(final FurtherSkill type) {
        Tracer.check(type.getValue() >= FurtherSkill.FSK_INVALID.getValue() && type.getValue() < FurtherSkill.FSK_COUNT.getValue());

        return values[type.getValue()];
    }

    public boolean Empty() {
        for (int xx=0; xx < FurtherSkill.FSK_COUNT.getValue(); ++xx) {
            if (values[xx] != 0) {
                return false;
            }
        }
        return true;
    }

    public void add(final iFurtSkills fs) {
        for (int xx = 0; xx < FurtherSkill.FSK_COUNT.getValue(); ++xx) {
            values[xx] += fs.values[xx];
        }
    }

    public void subtract(final iFurtSkills fs) {
        for (int xx = 0; xx < FurtherSkill.FSK_COUNT.getValue(); ++xx) {
            values[xx] -= fs.values[xx];
        }
    }

    public static iFurtSkills add(final iFurtSkills first, final iFurtSkills second) {
        final iFurtSkills result = new iFurtSkills(first);
        result.add(second);

        return result;
    }

    public static iFurtSkills subtract(final iFurtSkills first, final iFurtSkills second) {
        final iFurtSkills result = new iFurtSkills(first);
        result.subtract(second);

        return result;
    }

    public void add(final iPrSkills ps) {
        for (int xx = 0; xx < PrSkillType.PRSKILL_COUNT.getValue(); ++xx) {
            values[xx+FurtherSkill.FSK_ATTACK.getValue()] += ps.val[xx];
        }
    }

    public void subtract(final iPrSkills ps) {
        for (int xx = 0; xx < PrSkillType.PRSKILL_COUNT.getValue(); ++xx) {
            values[xx+FurtherSkill.FSK_ATTACK.getValue()] -= ps.val[xx];
        }
    }

    private int[] values;

}
