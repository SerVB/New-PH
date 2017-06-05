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
package Game.mapConstruction;

import Common.iArmy;
import Common.iDynamicBuffer;
import Common.iPoint;
import Game.iHero;
import Game.iVisitors;
import Game.iWeekDesc;
import utils.ChangeableString;

/**
 * Visitable constuction.
 */
public class iVisCnst extends iMapCnst {

//    public IMPL_TYPEAWARE( iVisCnst );

    public iVisCnst(iVisCnstT pProto, final iPoint pos, boolean bCustomGUI) {
        super(pos);

        this.m_pProto = pProto;
        this.m_bCustomGUI = bCustomGUI;
    }

    public iVisCnstT Proto() {
        return m_pProto;
    }

    public iArmy Guard() {
        return m_Guard;
    }

    public String CustomMessage() {
        return m_customMsg;
    }

    public void NewDay();
    public void NewWeek(final iWeekDesc weekDesk);
    public boolean Activate(iHero pVisitor, boolean bActive);
    public void OnActivate(iHero pVisitor, boolean bActive);
    public boolean Visited(final iHero pVisitor);
    public void Serialize(iDynamicBuffer dbuff);
    public int Unserialize(iDynamicBuffer dbuff);

    public boolean CustomPopupText(final iHero pVisitor, ChangeableString ctext) {
        return false;
    }

    public boolean ShowVisitedLabel() {
        return true;
    }

    public iVisitors Visitors() {
        return m_Visitors;
    }


    protected boolean        m_bCustomGUI;
    protected iArmy        m_Guard;
    protected String    m_customMsg;
    protected iVisitors    m_Visitors;
    protected iVisCnstT    m_pProto;
}
