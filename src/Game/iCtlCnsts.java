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

import Constants.ConstructionState;
import Common.iDynamicBuffer;
import Constants.*;
import java.util.ArrayList;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iCtlCnsts {


    public iCtlCnsts(iCastle pCastle) {
        this.m_pCastle = pCastle;
    }

    public void Init() {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt() && !item.IsInited()) {
                item.Init();
            }
        }
    }

    public iCtlCnst Add(iCtlCnst pCnst) {
        m_items.add(pCnst);
        return pCnst;
    }

    public int Find(final iCtlCnst pCnst) {
        return m_items.indexOf(pCnst);
    }

    public void NewDay() {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                item.NewDay();
            }
        }
    }

    public void NewWeek(final iWeekDesc weekDesk) {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                item.NewWeek(weekDesk);
            }
        }
    }

    public void OnVisitorEnter(iHero pHero) {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                item.OnVisitorEnter(pHero);
            }
        }
    }

    public void OnVisitorLeave(iHero pHero) {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                item.OnVisitorLeave(pHero);
            }
        }
    }

    public void OnOwnerChanged(int newOwner, int oldOwner) {
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                item.OnOwnerChanged(newOwner, oldOwner);
            }
        }
    }

    public void Serialize(iDynamicBuffer buff) {
        buff.Write(2); // constructions initialization flag (0 - default, 1 - customized, 2 - customized with serialized data)

        int cnt=0;
        for (iCtlCnst item : m_items) {
            if (item.IsBuilt()) {
                cnt++;
            }
        }

        buff.Write(cnt);

        for (int xx=0; xx < m_items.size(); ++xx) {
            if (m_items.get(xx).IsBuilt()) {
                buff.Write(xx);
                buff.Write(1); // Built
                m_items.get(xx).Serialize(buff);
            }
        }
    }

    public void Unserialize(iDynamicBuffer buff) {
        int flag = (int) buff.Read();
        switch (flag) {
            case 0:
                // init default constructions
                GetCnst(CTLCNST.DEFAULT_CONSTRUCTIONS[m_pCastle.Proto().Type()][0]).SetState(ConstructionState.Built);
                GetCnst(CTLCNST.DEFAULT_CONSTRUCTIONS[m_pCastle.Proto().Type()][1]).SetState(ConstructionState.Built);
                break;
            case 1:
            case 2:
                int cnt = (int) buff.Read();
                while (cnt-- > 0){
                    int idx = (int) buff.Read();
                    int state = (int) buff.Read();
                    m_items.get(idx).SetState(ConstructionState.Built);
                    if (flag == 2) {
                        m_items.get(idx).Unserialize(buff);
                    }
                }
                break;
            default:
                Tracer.check(0);
                break;
        }
    }

    public final void DependsList(final iCtlCnst pCnst, ArrayList<Integer> depList) {
        iDepItem dep = pCnst.Depends();
        // check first
        for (int xx = 0; xx < 2; ++xx) {
            if (dep.dep[xx] != CTLCNST.INVALID && depList.indexOf(dep.dep[xx]) == -1) {
                int ctlc = dep.dep[xx];
                final iCtlCnst pDepCnst = GetCnst(ctlc);
                Tracer.check(pDepCnst != null);
                if (!pDepCnst.IsBuilt()){
                    depList.add(dep.dep[xx]);
                }
                DependsList(pDepCnst, depList);
            }
        }
    }

    public void UpdateState(boolean bCanBuild) {
        iPlayer pOwner = null;
        if (m_pCastle.Owner() != PID.NEUTRAL) {
            pOwner = gGame.Map().FindPlayer(m_pCastle.Owner());
        }
        if (pOwner == null) {
            return;
        }
        for (iCtlCnst item : m_items) {
            if (item.GetState() != ConstructionState.Built) {
                if (!bCanBuild) {
                    item.SetState(ConstructionState.Unavailable);
                } else if ( !item.Depends().Empty() )    {
                    ArrayList<Integer> depList = new ArrayList();
                    DependsList(item,depList);
                    if (depList.isEmpty()) {
                        if (pOwner.Minerals().Has(item.Price())) {
                            item.SetState(ConstructionState.Available);
                        } else {
                            item.SetState(ConstructionState.NotEnoughRes);
                        }
                    } else {
                        item.SetState(ConstructionState.Unavailable);
                    }
                } else {
                    if (pOwner && pOwner.Minerals().Has(item.Price())) {
                        item.SetState(ConstructionState.Available);
                    } else {
                        item.SetState(ConstructionState.NotEnoughRes);
                    }
                }
            }
        }
    }

    public final int Count() {
        return m_items.size();
    }

    public iCtlCnst At(int idx) {
        return m_items.get(idx);
    }

    public final iCtlCnst GetCnst(int cnst) {
        for (int xx=0; xx < m_items.size(); ++xx)
            if(m_items.get(xx).Type() == cnst)
                return m_items.get(xx);
        return null;
    }

    public final iCastle Owner() {
        return m_pCastle;
    }


    private void Cleanup() {
        m_items.clear();
    }

    private final iCastle          m_pCastle;
    private ArrayList<iCtlCnst>    m_items;
}
