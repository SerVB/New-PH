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

import java.util.ArrayList;

/**
 *
 */
public class iFurtSkills {

    // Integer написан для наличия, нас самом деле скорее всего нужен другой тип.
    ArrayList<Integer> skills = new ArrayList<>();

    public int Value(int idx) {
        return skills.get(idx);
    }

    public void minusValue(int idx, int minusVal) {
        Integer tmp = skills.get(idx);
        tmp -= minusVal;
        skills.set(idx, tmp);
    }

    public void plusValue(int idx, int plusVal) {
        Integer tmp = skills.get(idx);
        tmp += plusVal;
        skills.set(idx, tmp);
    }

    public void Reset() {
        skills.clear();
    }

    public void SetValue(int idx, int val) {
        skills.set(idx, val);
    }

    public iFurtSkills() {

    }

    public iFurtSkills(iFurtSkills fs1, iFurtSkills fs2) {
        this.skills.addAll(fs1.skills);
        this.skills.addAll(fs2.skills);
    }
}
