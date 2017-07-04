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

import Game.map.construction.iMapCnst;
import Common.iArmy;
import Constants.*;
import newph.core.staticFunction.Tracer;

/**
 *
 */
public class iCastle extends iMapCnst {

//    public IMPL_TYPEAWARE( iCastle );

    public iCastle(iCastleT pProto, final iPoint pos, final String cname, boolean bCreateHero) {
        super(pos);

        this.m_pProto = pProto;
        this.m_Owner = PID.NEUTRAL;
        this.m_bCanBuild = true;
        this.m_Cnsts = (iCtlCnsts) this;
        this.m_bCreateHero = bCreateHero;
        this.m_scoutMod = 0;

        // Castle's name
        if (!cname.isEmpty()) {
            m_Name = cname;
        }
        else {
            m_Name = gTextMgr[gGame.ItemMgr().m_CastleRepos.GetCastleName(m_pProto.Type())];
        }

        // Init constructions
        for (int xx=0; xx<CTLCNST.COUNT; ++xx){
            if (CTLCNSTS.DESC[xx].caps.Support(m_pProto.Type(), m_pProto.Size())) {
                iCtlCnst pCtlCnst = null;
                int cnstId = xx;
                switch (CTLCNSTS.DESC[xx].type) {
                case CCT.GENERIC:
                    pCtlCnst = new iCtlCnst(this, cnstId);
                    break;
                case CCT.DWELLING:
                    pCtlCnst = new iDwellCtlCnst(this, cnstId, CTLCNSTS.DESC[cnstId].fparam);
                    break;
                case CCT.MAGEGUILD:
                    pCtlCnst = new iMGuildCtlCnst(this, cnstId,CTLCNSTS.DESC[cnstId].fparam);
                    break;
                case CCT.TAVERN:
                    pCtlCnst = new iTavernCtlCnst(this, cnstId);
                    break;
                case CCT.MAGICNODE:
                    pCtlCnst = new iMagicNodeCtlCnst(this, cnstId);
                    break;
                case CCT.MINE:
                    pCtlCnst = new iMineCtlCnst(this, cnstId, CTLCNSTS.DESC[cnstId].fparam, CTLCNSTS.DESC[cnstId].sparam);
                    break;
                case CCT.PERM_FSK_MOD:
                    pCtlCnst = new iPermFSKModCtlCnst(this, cnstId, CTLCNSTS.DESC[cnstId].fparam, CTLCNSTS.DESC[cnstId].sparam);
                    break;
                case CCT.DWELL_ENC:
                    pCtlCnst = new iDwellEncCtlCnst(this, cnstId, CTLCNSTS.DESC[cnstId].fparam, CTLCNSTS.DESC[cnstId].sparam);
                    break;
                case CCT.OBSERVPOST:
                    pCtlCnst = new iObsPostCtlCnst(this, cnstId);
                    break;
                case CCT.LIBRARY:
                    pCtlCnst = new iLibraryCtlCnst(this, cnstId);
                    break;
                case CCT.MANAVORTEX:
                    pCtlCnst = new iManaVtxCtlCnst(this, cnstId);
                    break;
                case CCT.TREASURY:
                    pCtlCnst = new iTreasuryCtlCnst(this, cnstId);
                    break;
                case CCT.MYSTICPOUND:
                    pCtlCnst = new iMystPoundCtlCnst(this, cnstId);
                    break;
                case CCT.NECRAMPLIFIER:
                    pCtlCnst = new iNecrAmpCtlCnst(this, cnstId);
                    break;
                case CCT.COVEROFDARKNESS:
                    pCtlCnst = new iCovOfDarkCtlCnst(this, cnstId);
                    break;
                default:
                    Tracer.check("Unknown construction type!");
                    break;
                }

                m_Cnsts.Add(pCtlCnst);
            }
        }

        UpdateCnsts();
    }

    public iCastleT Proto() {
        return m_pProto;
    }

    /**
     *
     * @return Player ID.
     */
    public int Owner() {
        return m_Owner;
    }

    /**
     *
     * @param newOwner Player ID.
     */
    public void SetOwner(int newOwner) {
        if (newOwner == m_Owner) return;

        int oldOwner = m_Owner; // Player ID
        m_Owner = newOwner;

        if (oldOwner != PID.NEUTRAL) {
            gGame.Map().FindPlayer(oldOwner).FurtSkills().minusValue(FSK.MIN_GOLD, CTL.INCOME);
        }
        if (newOwner != PID.NEUTRAL) {
            gGame.Map().FindPlayer(newOwner).FurtSkills().plusValue(FSK.MIN_GOLD, CTL.INCOME);
        }

        m_Cnsts.OnOwnerChanged(newOwner, oldOwner);
    }


    public String Name() {
        return m_Name;
    }

    public void Init() {
        // Init garrison
        if (m_Owner == PID.NEUTRAL && m_Garrison.Empty()) {
            GenerateGuards();
        }

        // Pre build constructions for debug
        //if (iCtlCnst* pMoat = m_Cnsts.GetCnst(CTLCNST_MOAT)) pMoat.Build();
        //if (iCtlCnst* pLTurret = m_Cnsts.GetCnst(CTLCNST_LTURRET)) pLTurret.Build();
        //if (iCtlCnst* pMTurret = m_Cnsts.GetCnst(CTLCNST_MTURRET)) pMTurret.Build();
        m_Cnsts.Init();
        UpdateCnsts();

        InitArmy(m_Garrison);

        // Create Hero
        if (m_bCreateHero) {
            int selhid = gGame.ItemMgr().HeroesMgr().Select(CTL.HEROES[m_pProto.Type()]);
            if (selhid == iHeroesMgr.INVALID_HERO_ID) {
                selhid = gGame.ItemMgr().HeroesMgr().Select(HTM.ALL);
            }
            if (selhid != iHeroesMgr.INVALID_HERO_ID) {
                iHero pHero = gGame.ItemMgr().HeroesMgr().Get(selhid);
                iPlayer pOwner = gGame.Map().FindPlayer(m_Owner);
                Tracer.check(pHero != null && pOwner != null);
                gGame.Map().AddHero(pHero, pOwner, m_Pos, HERO.ORIENT[m_pProto.Orient()]);
            } else {
                Tracer.check(0);
            }
        }
    }

    public void OnSelect() {
        gGame.OnCastleChanged(this);
    }

    public void OnVisitorEnter(iHero pHero) {
        m_Cnsts.OnVisitorEnter(pHero);
    }

    public void OnVisitorLeave(iHero pHero) {
        m_Cnsts.OnVisitorLeave(pHero);
    }

    public void NewDay() {
        m_bCanBuild = true;
        m_Cnsts.NewDay();
        m_Cnsts.UpdateState(m_bCanBuild);
    }

    public void NewWeek(final iWeekDesc weekDesk) {
        m_Cnsts.NewWeek(weekDesk);
        if (m_Owner == PID.NEUTRAL) {
            GenerateGuards();
        }
    }

    public int GetConstrCaps() {
        return 0;
    }

    /**
     *
     * @param cnst Castle construction ID.
     * @return
     */
    public iCtlCnst FindCnst(int cnst) {
        return m_Cnsts.GetCnst(cnst);
    }

    public iCtlCnst Cnst(int idx) {
        return m_Cnsts.At(idx);
    }

    public iCtlCnsts Cnsts() {
        return m_Cnsts;
    }

    public iTavernCtlCnst GetTavern(boolean bOnlyBuilt) {
        iTavernCtlCnst pTavern = DynamicCast<iTavernCtlCnst>(m_Cnsts.GetCnst(CTLCNST.TAVERN));
        if (pTavern != null) {
            if (!bOnlyBuilt || pTavern.IsBuilt()) {
                return pTavern;
            }
        }
        return null;
    }

    public iArmy Garrison() {
        return m_Garrison;
    }

    public int GarrisonPower(boolean bOnlyFort) {
        int result = 0;

        if (!bOnlyFort){
            result += Garrison().ArmyPower();
            if (result == 0) {
                return 0;
            }
        }

        iCtlCnst pCnst;

        if ((pCnst = m_Cnsts.GetCnst(CTLCNST.LTURRET)) != null)
            if (pCnst.IsBuilt())
                result += 1000;

        if ((pCnst = m_Cnsts.GetCnst(CTLCNST.RTURRET)) != null)
            if (pCnst.IsBuilt())
                result += 1000;

        if ((pCnst = m_Cnsts.GetCnst(CTLCNST.MTURRET)) != null)
            if (pCnst.IsBuilt())
                result += 1000;

        if ((pCnst = m_Cnsts.GetCnst(CTLCNST.MOAT)) != null)
            if (pCnst.IsBuilt())
                result += 1000;

        return result;
    }

    public int GarrisonPower() {
        return GarrisonPower(false);
    }

    public int Scouting() {
        return m_pProto.Scouting() + m_scoutMod;
    }

    public int ScoutMod() {
        return m_scoutMod;
    }

    public boolean CanBuild() {
        return m_bCanBuild;
    }

    public void SetBuilt(boolean val) {
        m_bCanBuild = val;
    }

    public void Build(iCtlCnst pCnst) {
        Tracer.check(m_bCanBuild);

        gGame.Map().FindPlayer(Owner()).subtractMinerals(pCnst.Price());
        pCnst.Build();
        m_bCanBuild = false;
        UpdateCnsts();
    }

    public void Build(int cnstIdx) {
        Build(m_Cnsts.At(cnstIdx));
    }

    public void UpdateCnsts() {
        m_Cnsts.UpdateState(m_bCanBuild);
    }

//    public friend class iGameWorld;

    /**
     * Добавляет воинов к нейтральному замку (каждую неделю).
     */
    private void GenerateGuards() {
        for (int xx = 0; xx < 3; ++xx) {
            if ( gGame.Map().Rand(3) == 0 ) {
                int ct = CREAT.PEASANT + m_pProto.Type()*6 + xx;
                m_Garrison.AddGroup(ct, CREAT.DESC[ct].growth);
            }
        }
    }

    protected int        m_scoutMod;
    protected iArmy        m_Garrison;
    protected String    m_Name;
    protected iCtlCnsts    m_Cnsts;
    protected boolean        m_bCanBuild;
    protected iCastleT    m_pProto;

    /**
     * Player ID.
     */
    protected int    m_Owner;
    protected boolean       m_bCreateHero;
}
