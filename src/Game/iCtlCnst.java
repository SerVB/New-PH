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
import Common.iMineralSet;
import utils.tracer;
import Constants.*;
import utils.ChangeableString;

/**
 *
 */
public class iCtlCnst implements TypeAware {

//    public IMPL_TYPEAWARE( iCtlCnst );


    public iCtlCnst(iCastle _pCastle, int _cnst) {

        this.pCastle = _pCastle;
        this.cnst = _cnst;
        this.state = ConstructionState.Unavailable;
        this.bInited = false;

    }


    public static String Name(int _cnst) {
        return gTextMgr[TRID.DWEL_CITD_L1 + _cnst];
    }

    public final String Name() {
        return Name(cnst);
    }

    public String Desc() {

        switch (cnst){
            case CTLCNST.MAGICNODE:         return gTextMgr[TRID.CTLCNST_COMMON_MNODE_DESC];
            case CTLCNST.TAVERN:            return gTextMgr[TRID.CTLCNST_COMMON_TAVERN_DESC];
            case CTLCNST.MARKETPLACE:       return gTextMgr[TRID.CTLCNST_COMMON_MARKET_DESC];
            case CTLCNST.MOAT:              return gTextMgr[TRID.CTLCNST_COMMON_MOAT_DESC];
            case CTLCNST.MTURRET:           return gTextMgr[TRID.CTLCNST_COMMON_MTURRET_DESC];
            case CTLCNST.RTURRET:           return gTextMgr[TRID.CTLCNST_COMMON_RTURRET_DESC];
            case CTLCNST.LTURRET:           return gTextMgr[TRID.CTLCNST_COMMON_LTURRET_DESC];

            case CTLCNST.TOWNHALL:          return gTextMgr[TRID.CTLCNST_COMMON_TOWNHALL_DESC];
            case CTLCNST.CITYHALL:          return gTextMgr[TRID.CTLCNST_COMMON_CITYHALL_DESC];
            case CTLCNST.OREMINE:           return gTextMgr[TRID.CTLCNST_COMMON_OREMINE_DESC];
            case CTLCNST.SAWMILL:           return gTextMgr[TRID.CTLCNST_COMMON_SAWMILL_DESC];
            case CTLCNST.ALCHLAB:           return gTextMgr[TRID.CTLCNST_COMMON_ALCHLAB_DESC];
            case CTLCNST.GEMSMINE:          return gTextMgr[TRID.CTLCNST_COMMON_GEMSMINE_DESC];
            case CTLCNST.CRYSTALMINE:       return gTextMgr[TRID.CTLCNST_COMMON_CRYSTALMINE_DESC];
            case CTLCNST.SULFURMINE:        return gTextMgr[TRID.CTLCNST_COMMON_SULFURMINE_DESC];
            case CTLCNST.ALTAR:             return gTextMgr[TRID.CTLCNST_ALTAR_DESC];

            case CTLCNST.OBSERVPOST:        return gTextMgr[TRID.CTLCNST_OBSERVPOST_DESC];
            case CTLCNST.FORTIFICATION:     return gTextMgr[TRID.CTLCNST_FORTIFICATION_DESC];
            case CTLCNST.HALLOFVALHALLA:    return gTextMgr[TRID.CTLCNST_HALLOFVALHALLA_DESC];
            case CTLCNST.WALLOFKNOWLEDGE:   return gTextMgr[TRID.CTLCNST_WALLOFKNOWLEDGE_DESC];
            case CTLCNST.LIBRARY:           return gTextMgr[TRID.CTLCNST_LIBRARY_DESC];
            case CTLCNST.MANAVORTEX:        return gTextMgr[TRID.CTLCNST_MANAVORTEX_DESC];
            case CTLCNST.TREASURY:          return gTextMgr[TRID.CTLCNST_TREASURY_DESC];
            case CTLCNST.MYSTICPOUND:       return gTextMgr[TRID.CTLCNST_MYSTICPOUND_DESC];
            case CTLCNST.NECRAMPLIFIER:     return gTextMgr[TRID.CTLCNST_NECRAMPLIFIER_DESC];
            case CTLCNST.COVEROFDARKNESS:   return gTextMgr[TRID.CTLCNST_COVEROFDARKNESS_DESC];
        };

        tracer.check("Unknown construction!");
        return gTextMgr[TRID.VOID];
    }

    public final iMineralSet Price() {
        return CTLCNSTS.DESC[cnst].price;
    }

    public iCastle Owner() {
        return pCastle;
    }

    public final iDepItem Depends() {
        return new iDepItem(CTLCNSTS.DESC[cnst].depend);
    }

    public final int Type() {
        return cnst;
    }

    public void Init() {
        tracer.check(state == ConstructionState.Built && !bInited);
        OnInit();
        if (pCastle.Visitor()) {
            OnVisitorEnter(pCastle.Visitor());
        }
        bInited = true;
    }

    public void Build() {
        tracer.check(state != ConstructionState.Built);
        state = ConstructionState.Built;
        OnBuild();
        Init();
    }

    public final boolean IsBuilt() {
        return state == ConstructionState.Built;
    }

    public final int GetState() {
        return state;
    }

    public void SetState(int nState) {
        state = nState;
    }

    public final boolean IsInited() {
        return bInited;
    }

    // virtuals
    public void Serialize(iDynamicBuffer buff) {

    }

    public void Unserialize(iDynamicBuffer buff) {
        bInited = true;
    }

    public abstract void OnInit();
    public abstract void OnBuild();
    public abstract void NewDay();
    public abstract void NewWeek(final iWeekDesc weekDesk);
    public abstract void OnVisitorEnter(iHero pHero);
    public abstract void OnVisitorLeave(iHero pHero);
    public abstract void OnOwnerChanged(int newOwner, int oldOwner);

    public boolean Visited(final iHero pHero) {
        return true;
    }

    public boolean GetActivityText(ChangeableString text) {
        return false;
    }


    protected boolean        bInited;
    protected iCastle    pCastle;
    protected int        state;
    protected int    cnst;
}
