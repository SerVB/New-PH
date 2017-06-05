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

/**
 * Battle Unit.
 */
public class iBattleUnit implements TypeAware {

//    public IMPL_TYPEAWARE( iBattleUnit );

    /**
     * C-tor.
     * @param ut Unit Type.
     * @param pOwner
     */
    public iBattleUnit(int ut, iBattleMember pOwner) {
        this.m_ut = ut;
        this.m_pOwner = pOwner;
    }

    public final int GetUnitType() {
        return m_ut;
    }

    public iBattleMember Owner() {
        return m_pOwner;
    }

    public abstract boolean IsAlive();
    public abstract boolean CanWait();
    public abstract int Speed();

    protected iBattleMember    m_pOwner;
    protected int        m_ut;

}
