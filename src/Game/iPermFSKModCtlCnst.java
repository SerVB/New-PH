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

import Common.iDynamicBuffer;
import java.util.ArrayList;
import newph.staticFunction.Tracer;

/**
 *
 */
public class iPermFSKModCtlCnst extends iCtlCnst {

//    public IMPL_TYPEAWARE( iPermFSKModCtlCnst );

    public iPermFSKModCtlCnst(iCastle _pCastle, int _cnst, int _skill, int _mod) {
        super(_pCastle, _cnst);

        this.skill = _skill;
        this.mod = _mod;
    }

    @Override
    public void OnVisitorEnter(iHero pHero) {
        Tracer.check(pHero != null);
        if (!Visited(pHero)) {
            pHero.ConstFurtSkills().plusValue(skill, mod);
            visitors.add(pHero.Proto().m_protoId);
        }
    }

    @Override
    public final boolean Visited(final iHero pHero) {
        return (visitors.contains(pHero.Proto().m_protoId));
    }

    @Override
    public void Serialize(iDynamicBuffer buff) {
        super.Serialize(buff);
        // Visitors
        buff.Write(visitors.size());
        for (int xx = 0; xx < visitors.size(); ++xx) {
            buff.Write(visitors.get(xx));
        }
    }

    @Override
    public void Unserialize(iDynamicBuffer buff) {
        super.Unserialize(buff);
        // Visitors
        int viscnt = (int) buff.Read();
        visitors.clear();
        while (viscnt-- > 0) {
            int visitor = (int)buff.Read();
            visitors.add(visitor);
        }
    }

    public int                  skill;
    public int                  mod;
    public ArrayList<Integer>   visitors;
}
