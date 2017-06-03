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

import Common.iArmy;
import Common.iArtifactList;
import Common.iCreatGroup;
import Common.iDynamicBuffer;
import Common.iFurtSkills;
import Common.iPoint;
import Common.iRewardItem;
import Common.iSecSkills;
import Common.iSpellList;
import Common.serialize;
import Common.tracer;
import Constants.*;
import ConstantsGame.*;
import java.util.ArrayList;

/**
 *
 */
public class iHero extends iBaseMapObject implements iIListNode {

    private class iCharmEntry {
        public iCharmEntry(int _spellId, int _flag) {
            this.spellId = _spellId;
            this.flag = _flag;
        }

        public iCharmEntry(int _spellId) {
            this.spellId = _spellId;
            this.flag = 0;
        }

        public int spellId;
        public int flag;
    };


    private iHeroT            m_pProto;
    private iSecSkillsCtr    m_SecSkills;
    private iFurtSkills        m_constFurtSkills;
    private iFurtSkills        m_varFurtSkills;
    private iSpellBook        m_spellBook;
    private int    m_spellSetMode;

    private iArmy        m_Army;
    private iArtCtr        m_Artifacts;
    private iEnchHolder    m_Enchs;

    private iPlayer    m_pOwner;
    private int        m_Level;
    private long        m_Experience;
    private long        m_manaPts;
    private String    m_CustomName;
    private int        m_Angle;
    private long        m_Moves;
    //iPoint        m_NextAnchor;
    private iPath        m_Path;
    private iPoint        m_NextPoint;
    private ArrayList<iCharmEntry> m_charms = new ArrayList();

    private boolean        m_bCanDig;
    private boolean        m_bStop;
    private boolean        m_bMoving;

    private iMapCnst    m_pLocation;
    private boolean        m_bDead;
    private IMPL_TYPEAWARE( iHero );


    protected void ProcessItemChanges() {
        iAI_Player pAIPlayer =  DynamicCast<iAI_Player>(m_pOwner);
        if (pAIPlayer != null){
            Stop();
            m_Path.SetEmpty(m_Pos);
            pAIPlayer.ProcessItemChanges();
        } else {
            for (int xx=0; xx < m_pOwner.NewVisItems().Size(); ++xx){
                if (m_Path.BelongToPath(m_pOwner.NewVisItems()[xx].value.Pos())) {
                    // Stop hero if new items belong to path
                    Stop();
                    m_Path.SetEmpty(m_Pos);
                }
            }
        }
    }

    protected iNewLevelInfo NextLevelInfo() {
        iNewLevelInfo result = new iNewLevelInfo();

        // Primary skill
        int pskill = CalcRandValue(HERO.PRIM_SKILL[Type()],PRSKILL.COUNT);
        tracer.check(pskill != -1);
        result.prSkillType = pskill;

        // Secondary skill
        int[] frSkills = HERO.SEC_SKILL[Type()];
        int[] upSkills = new int[SECSK.COUNT];

        int frCnt = 8 - m_SecSkills.Count(); // Free count.
        int upCnt = 0;

        for (int xx=0; xx < m_SecSkills.Count(); ++xx) {
            if (m_SecSkills.At(xx).m_level < SSLVL.EXPERT) {
                upSkills[m_SecSkills.At(xx).m_skill] = Math.max(1, frSkills[m_SecSkills.At(xx).m_skill]);
                upCnt++;
            }
            frSkills[m_SecSkills.At(xx).m_skill] = 0;
        }

        if (upCnt != 0) {
            int sskill = CalcRandValue(upSkills, SECSK.COUNT);
            result.secSkill[0] = sskill;
            upSkills[result.secSkill[0]] = 0;
            upCnt--;
        } else {
            if (frCnt != 0) {
                result.secSkill[0] = CalcRandValue(frSkills, SECSK.COUNT);
                frSkills[result.secSkill[0]] = 0;
            } else {
                // nothing to learn
                result.secSkill[0] = result.secSkill[1] = SECSK.NONE;
                return result;
            }
        }

        if (frCnt != 0){
            result.secSkill[1] = CalcRandValue(frSkills, SECSK.COUNT);
            frSkills[result.secSkill[1]] = 0;
        } else {
            if (upCnt != 0){
                result.secSkill[1] = CalcRandValue(upSkills, SECSK.COUNT);
                upSkills[result.secSkill[1]] = 0;
                upCnt--;
            } else {
                result.secSkill[1] = SECSK.NONE;
            }
        }

        return result;
    }


    // Construct hero object from proto
    public iHero(iHeroT pProto, int fake) {
        super(cInvalidPoint, true);

        m_pProto = pProto;
        m_pOwner = null;
        m_Path = cInvalidPoint;
        m_bStop = false;
        m_bMoving = false;
        m_pLocation = null;
        m_Artifacts = this;
        m_bDead = true;
        m_Enchs = m_varFurtSkills;
        m_SecSkills = m_varFurtSkills;
        m_Experience = InitialExpPts();
        m_Level = 1;
        m_spellSetMode = SSM.ALL;
        m_bCanDig = true;

        // Init FurtSkills
        m_constFurtSkills = pProto.m_furtSkills;
        // Init secondary skills
        m_SecSkills.Set(pProto.m_secSkills);
        // Init Army
        ResetArmy();
        InitArmy(m_Army);
        // Init Spellbook
        m_spellBook.Add(pProto.m_spells);
        // Mana points
        m_manaPts = MaxManaPts();
        // Action points
        m_Moves = TotalMoves();
        // Angle
        m_Angle = 0;
    }

    // Serialize / Deserialize hero object
    public final void Serialize(iDynamicBuffer dbuff)
    {
        // Proto Id
        dbuff.Write(m_pProto.m_protoId);
        // Owner Id
        if (m_pOwner != null)
            dbuff.Write(m_pOwner.PlayerId());
        else
            dbuff.Write(PID.NEUTRAL);
        // Position
        serialize.Serialize(dbuff, m_Pos);
        // Angle
        dbuff.Write(m_Angle);
        // Target
        if (m_Path.IsEmpty())
            serialize.Serialize(dbuff, m_Pos);
        else
            serialize.Serialize(dbuff, m_Path.DestPoint());
        // Custom Name
        serialize.Serialize(dbuff, m_CustomName);
        // Level
        dbuff.Write(m_Level);
        // Experience
        dbuff.Write(m_Experience);
        //Secondary skills
        serialize.Serialize(dbuff, m_SecSkills.SecSkills());
        // Artifact list
        iArtifactList artList = new iArtifactList();
        for (int artIdx=0; artIdx < m_Artifacts.BackPackCount(); ++artIdx)
            artList.Add(m_Artifacts.BackPackItem(artIdx), AC.UNDEFINED);
        for (int artIdx=0; artIdx < AC.COUNT; ++artIdx)
            if (!m_Artifacts.DressedItem(artIdx).Empty())
                artList.Add(m_Artifacts.DressedItem(artIdx).artId, artIdx);
        serialize.Serialize(dbuff, artList);
        // Enchancement list
        dbuff.Write(m_Enchs.Count());
        for (int xx = 0; xx < m_Enchs.Count(); ++xx) {
            // provider
            int a1=m_Enchs.get(xx).provider;
            dbuff.Write(m_Enchs.get(xx).provider);
            // type (FURTHER_SKILLS)
            int a2=m_Enchs.get(xx).type;
            dbuff.Write(m_Enchs.get(xx).type);
            // duration
            dbuff.Write(m_Enchs.get(xx).dur);
            // mod
            dbuff.Write(m_Enchs.get(xx).mod);
        }
        // FurtSkills
        serialize.Serialize(dbuff, m_constFurtSkills);
        // Army
        serialize.Serialize(dbuff, m_Army);
        // Spellbook
        dbuff.Write( 1 );
        serialize.Serialize(dbuff, m_spellBook.Spells());
        dbuff.Write(m_spellBook.FavoritesCount());
        for (int xx=0; xx < m_spellBook.FavoritesCount(); ++xx)
            dbuff.Write(m_spellBook.Favorite(xx));
        // Mana points
        dbuff.Write(m_manaPts);
        // Action points
        dbuff.Write(m_Moves);
        // Can dig flag
        dbuff.Write(m_bCanDig ? 1 : 0);
    }

    public void Unserialize(iDynamicBuffer dbuff, boolean bInit) {
        //DbgDumpFurtSkills(Name() + _T(" (initial constant)"), m_constFurtSkills);
        //DbgDumpFurtSkills(Name() + _T(" (initial var)"), m_varFurtSkills);

        tracer.check(m_bDead);
        if (bInit)
            m_bDead = false;

        // Owner
        int owner = (int)dbuff.Read();
        if (owner != PID.NEUTRAL) {
            m_pOwner = gGame.Map().FindPlayer(owner);
            tracer.check(m_pOwner);
            m_pOwner.AddHero(this, false);
        }

        // Position
        iPoint pos = null;
        serialize.Unserialize(dbuff, pos);
        SetPos(pos);
        // Angle
        int angle = (int) dbuff.Read();
        if (angle < 0)
            m_Angle = gGame.Map().Rand(8);
        else
            m_Angle = angle;
        // Target
        iPoint target = new iPoint();
        serialize.Unserialize(dbuff, target);
        if (target != pos)
            SetPath(target);
        // Custom name
        serialize.Unserialize(dbuff, m_CustomName);
        // Level
        int level = (int) dbuff.Read();
        if (level > 0)
            m_Level = level;
        // Experience
        long exp = dbuff.Read();
        if (exp > 0)
            m_Experience = exp;
        // Secondary skills
        iSecSkills secSkills = new iSecSkills();
        serialize.Unserialize(dbuff, secSkills);
        m_SecSkills.Reset();
        m_SecSkills.Set(secSkills);
        // Artifact list
        iArtifactList artList = new iArtifactList();
        serialize.Unserialize(dbuff, artList);
        for (int artIdx = 0; artIdx < artList.Count(); ++artIdx) {
            if (artList.At(artIdx).assign == AC.UNDEFINED)
                m_Artifacts.PushToBackPack(artList.At(artIdx).id);
            else
                m_Artifacts.PushDressed(artList.At(artIdx).id, artList.At(artIdx).assign);
        }
        // Enchancement list
        int enCnt = (int) dbuff.Read();
        for (int xx=0; xx < enCnt; ++xx) {
            // provider
            int provider = (int) dbuff.Read();
            // type (FURTHER_SKILLS)
            int type = (int) dbuff.Read();
            // duration
            int dur = (int) dbuff.Read();
            // mod
            int mod = (int) dbuff.Read();
            // Add enchancement item
            m_Enchs.Add(provider, type, dur, mod);
        }
        // FurtSkills
        iFurtSkills furtSkills = new iFurtSkills();
        serialize.Unserialize(dbuff, furtSkills);
        m_constFurtSkills = furtSkills;
        // Army
        iArmy army = new iArmy();
        serialize.Unserialize(dbuff, army);
        if (!army.Empty()) {
            m_Army = army;
            InitArmy(m_Army);
        }
        // Spellbook
        int spflag = (int) dbuff.Read();
        if (spflag != 0) {
            ArrayList<iBattleGroup.iSpellEntry> spellList = new ArrayList<>();
            serialize.Unserialize(dbuff, spellList);
            m_spellBook.Reset();
            if (spellList.size() > 0)
                m_spellBook.Add(spellList);
        }
        // Favorite spells
        int fspcnt = (int) dbuff.Read();
        while (fspcnt-- > 0) {
            int spellId = (int) dbuff.Read();
            m_spellBook.AddFavorite(spellId);
        }

        // Mana points
        long mana = dbuff.Read();
        if (mana >= 0)
            m_manaPts = mana;
        else
            m_manaPts = MaxManaPts();

        // Action points
        int action = (int) dbuff.Read();
        if (action >= 0)
            m_Moves = action;
        else
            m_Moves = TotalMoves();

        // Can dig flag
        int bCanDig = (int) dbuff.Read();
        m_bCanDig = bCanDig != 0;

        //DbgDumpFurtSkills(Name() + _T(" (unserialized constant)"), m_constFurtSkills);
        //DbgDumpFurtSkills(Name() + _T(" (unserialized var)"), m_varFurtSkills);
    }

    public void Deinit(boolean bResetArmy) {
        tracer.check(!m_bDead);
        m_bDead = true;
        m_pOwner = null;
        SetPos(cInvalidPoint);
        m_Path.SetEmpty(m_Pos);
        if (m_pLocation)
            SetLocation(null);
        if (bResetArmy) {
            ResetArmy();
            InitArmy(m_Army);
        }
    }

    public void Init(iPlayer pOwner, final iPoint pos, int angle) {
        tracer.check(m_bDead && m_pOwner == null && m_Pos == cInvalidPoint);
        m_pOwner = pOwner;
        m_spellSetMode = SSM.ALL;
        SetPos(pos);
        if (angle < 0)
            m_Angle = gGame.Map().Rand(8);
        else
            m_Angle = angle;
        m_bDead = false;
    }

    public boolean CheckPath();
    public boolean Step() {
        if (m_bDead || m_Path.IsEmpty() || m_Moves < m_Path.GetStepCost()) {
            StopMoving();
            return false;
        }

        boolean bNeedAction = false;

        if (m_NextPoint == m_Path.GetStepPos()) {
            m_bCanDig = false;
            m_Moves -= m_Path.GetStepCost();
            iPath.iStep step = m_Path.GetStep();
            if (step.m_action == iPath.MoveTo || step.m_action == iPath.Enter) {
                iPoint oldPos = Pos();
                iPath.iStep nStep = m_Path.Step();
                SetPos(nStep.m_Pos);
                gGame.Map().MoveHero(oldPos,nStep.m_Pos);
                m_pOwner.UpdateFogMap();
                gGame.OnHeroPosChanged(this, nStep.m_Pos);

                if (!m_Path.IsEmpty()){
                    // Check target
                    if (m_Path.TargetAction() != iPath::MoveTo) {
                        int act = GetTarget(m_Path.DestPoint().x, m_Path.DestPoint().y).action;
                        if (act != m_Path.TargetAction()) {
                            StopMoving();
                            m_Path.SetEmpty(m_Pos);
                            return false;
                        }
                    }
                } else if (step.m_action == iPath.Enter) {
                    // process action (if end point)
                    tracer.check(m_Path.IsEmpty() && m_pLocation);
                    StopMoving();
                    iCastle pCastle = DynamicCast<iCastle>(m_pLocation);
                    if (pCastle != null) {
                        if (pCastle.Owner() == m_pOwner.PlayerId())
                            EnterCastle(pCastle);
                        else
                            CaptureCastle(pCastle, true);
                    } else
                        ActivateConstruction(m_pLocation);
                }
            } else if (step.m_action == iPath.Touch || step.m_action == iPath.Attack || step.m_action == iPath.Meet){
                iPath.iStep nStep = m_Path.Step();
                //
                iBaseMapObject pMapObj = gGame.Map().m_CoverMap.GetAt(nStep.m_Pos.x, nStep.m_Pos.y);
                tracer.check(pMapObj);

                iHero pHero = DynamicCast<iHero*>(pMapObj);
                if (pHero != null) {
                    if (step.m_action == iPath.Meet) {
                        tracer.check(pHero.Owner() == Owner());
                        StopMoving();
                        bNeedAction = true;
                        MeetHero(pHero);
                    } else if (step.m_action == iPath.Attack){
                        tracer.check(pHero.Owner() != Owner());
                        iCastle pCastle = DynamicCast<iCastle>(pHero.GetLocation());
                        if (pCastle != null){
                            StopMoving();
                            bNeedAction = true;
                            SiegeCastle(pCastle);
                        } else {
                            StopMoving();
                            bNeedAction = true;
                            AttackHero(pHero);
                        }
                    } else {
                        tracer.check(0);
                    }
                } else if ((iMapGuard pGuard = DynamicCast<iMapGuard>(pMapObj)) != null) {
                    tracer.check(step.m_action == iPath.Attack);
                    StopMoving();
                    bNeedAction = true;
                    TouchMapGuard(pGuard, false);
                } else if (DynamicCast<iMapItem_Artifact*>(pMapObj) || DynamicCast<iMapItem_CampFire*>(pMapObj) || DynamicCast<iMapItem_Chest*>(pMapObj) || DynamicCast<iMapItem_Lamp*>(pMapObj) || DynamicCast<iMapItem_ManaCrystal*>(pMapObj) || DynamicCast<iMapItem_Mineral*>(pMapObj) || DynamicCast<iMapItem_SpellScroll*>(pMapObj) || DynamicCast<iMapItem_KeyGuard*>(pMapObj)) {
                    tracer.check(step.m_action == iPath.Touch);
                    StopMoving();
                    bNeedAction = true;
                    iMapItem pMapItem = pMapObj;
                    TouchMapItem(pMapItem, false);
                } else if (iCastle pCastle = DynamicCast<iCastle>(pMapObj)) {
                    StopMoving();
                    bNeedAction = true;
                    SiegeCastle(pCastle);
                } else {
                    // unknown object
                    tracer.check(0);
                }
            } else {
                // unknown action
                tracer.check(0);
            }

            if (m_bDead)
                return false;

            if (bNeedAction)
                return false;

            iMapEvent pEvent = gGame.Map().FindMapEvent(m_NextPoint, m_pOwner.PlayerId());
            if (pEvent != null) {
                StopMoving();
                if (pEvent.Activate(this, m_pOwner.PlayerType() == PT.HUMAN, true)) {
                    gGame.Map().RemoveMapEvent(pEvent);
                }
                return false;
            } else if (NeedStop()) {
                m_bStop = false;
                StopMoving();
                return false;
            }
        } else {
            StartMoving();
        }

        m_Angle = m_Path.GetAngle();
        m_NextPoint = m_Path.GetStepPos();


        gGame.OnHeroMoveTo(this, m_Path.GetStep());
        m_bStop = false;

        return true;
    }

    public void Stop() {
        m_bStop = true;
    }

    public boolean NeedStop() {
        if (m_pOwner.NeedStopHero()) {
            ProcessItemChanges();
            m_pOwner.ResetVistItemChanges();
        }
        return m_bStop || m_Path.IsEmpty() || m_Moves < m_Path.GetStepCost();
    }

    public final boolean Moving() {
        return m_bMoving;
    }

    public void Process(double t) {

    }

    public iPath.Target GetTarget(int x, int y, boolean bIgnoreFog) {
        iPath.Target res = new iPath.Target();
        iBaseMapObject pObj = gGame.Map().m_CoverMap.GetAt(x,y);
        tracer.check(pObj != null);
        if (DynamicCast<iHero*>(pObj) || DynamicCast<iMapGuard*>(pObj) || DynamicCast<iMapItem_Mineral*>(pObj) || DynamicCast<iMapItem_ManaCrystal*>(pObj) || DynamicCast<iMapItem_CampFire*>(pObj) || DynamicCast<iMapItem_Chest*>(pObj) || DynamicCast<iMapItem_Lamp*>(pObj) || DynamicCast<iMapItem_Artifact*>(pObj) || DynamicCast<iMapItem_SpellScroll*>(pObj)  || DynamicCast<iMapItem_KeyGuard*>(pObj)) {
            res.epmask = 0xFF;
            if (bIgnoreFog || !Owner().FogMap().IsHidden(iPoint(x,y))) {
                if (iHero* pHero = DynamicCast<iHero*>(pObj)) {
                    if (pHero.Owner() == Owner())
                        res.action = iPath::Meet;
                    else
                        res.action = iPath.Attack;
                    if (iMapCnst* pCnst = pHero.GetLocation()) {
                        if (DynamicCast<iCastle*>(pCnst)) {
                            res.epmask = 0x82;
                        } else {
                            res.epmask = 0x83;
                        }
                    }
                } else if (DynamicCast<iMapGuard*>(pObj)) {
                    res.action = iPath.Attack;
                } else {
                    res.action = iPath.Touch;
                }
            }
        } else if (iVisCnst* pVisCnst = DynamicCast<iVisCnst*>(pObj) ){
            res.action = iPath.Enter;
            res.epmask = 0x83;
        } else if (iOwnCnst* pOwnCnst = DynamicCast<iOwnCnst*>(pObj)) {
            res.action = iPath.Enter;
            res.epmask = 0x83;
        } else if (iCastle* pCastle = DynamicCast<iCastle*>(pObj)) {
            if (pCastle.Owner() != Owner().PlayerId() && !pCastle.Garrison().Empty())
                res.action = iPath.Attack;
            else
                res.action = iPath.Enter;
            res.epmask = 0x82;
        } else {
            tracer.check(0);
        }

        return res;
    }

    public iPath.Target GetTarget(int x, int y) {
        GetTarget(x, y, false);
    }

    public boolean MakePath(iPath path, int x, int y) {
        m_pOwner.UpdatePassMap();

        if (m_Pos.x == x && m_Pos.y == y) {
            // inline activation
            tracer.check(m_pLocation);
            ActivateConstruction(m_pLocation);
            return true;
        }

        int oval = m_pOwner.PassMap().GetAt(x,y);
        if (oval == -1)
            return false;

        iPath.Target tgt = new iPath.Target();
        if (oval == -2) {
            tgt    = GetTarget(x,y);
            m_pOwner.PassMap().GetAt(x,y) = 5;
        }

        int spMask = 0xFF;
        if (m_pLocation) {
            if (DynamicCast<iVisCnst*>(m_pLocation) || DynamicCast<iOwnCnst*>(m_pLocation))
                spMask = CNST.PASS_MASK;
            else if (DynamicCast<iCastle*>(m_pLocation))
                spMask = CSTL.PASS_MASK;
        }

        boolean bRes = false;
        iPathFinder pf = new iPathFinder(m_pOwner.PassMap());
        path.SetEmpty(Pos());
        if (pf.FindPath(Pos(), new iPoint(x,y), path, spMask, tgt.epmask) != -1) {
            path.SetTargetAction((iPath.Action)tgt.action);
            m_NextPoint = Pos();
            bRes = true;
        }
        m_pOwner.PassMap().GetAt(x,y) = oval;
        return bRes;
    }

    public void SetPath() {
        if (!m_Path.m_Path.IsEmpty()) {
            SetPath( m_Path.DestPoint() );
        } else if (m_pLocation) {
            ActivateConstruction(m_pLocation);
        }
    }

    public void SetPath(final iPoint cell) {
        if (m_bMoving) {
            Stop();
        } else if (m_Path.IsDestPoint(cell)) {
            Step();
        } else if (gGame.Map().IsValidPos(cell.x,cell.y) && Pos() != cell) {
            //
            MakePath(m_Path, cell.x, cell.y);
        }
    }
    public void SetPath(final iPath path) {
        tracer.check(!m_bMoving);
        m_Path = path;
        m_NextPoint = Pos();
    }

    public void ResetPath() {
        m_Path.SetEmpty(Pos());
    }

    public final int VisionLevel(Integer radius) {
        radius = 0;
        if (CharmedBy(MSP.VISIONS, radius)) {
            return VL.EXPERT;
        } else if (m_Army.HasCreatures(CREAT.ROGUE)) {
            radius = 5;
            return VL.EXPERT;
        } else if (m_pOwner.CastleCount() > 0) {
            return VL.BASIC;
        }
        return VL.NONE;
    }

    public final iArmy Army() {
        return m_Army;
    }

    public final iEnchHolder Enchs() {
        return m_Enchs;
    }
    public final iPath Path() {
        return m_Path;
    }

    public int Angle() {
        return m_Angle;
    }

    public final iHeroT Proto() {
        return m_pProto;
    }

    public final iPlayer Owner() {
        return m_pOwner;
    }

    public final iSpellBook SpellBook() {
        return m_spellBook;
    }

    public final boolean HasSpell(int spellId) {
        return m_spellBook.HasSpell(spellId);
    }

    public final boolean CanLearnSpell(int spellId) {
        return SPELL.DESCRIPTORS[spellId].level < (FurtSkill(FSK.WISDOM)+2);
    }

    public void LearnSpell(int spellId, boolean bForce) {
        tracer.check(bForce || CanLearnSpell(spellId));
        m_spellBook.Add(spellId);
    }

    public void LearnSpell(int spellId) {
        LearnSpell(spellId, false);
    }

    public void AddFavoriteSpell(int spellId) {
        m_spellBook.AddFavorite(spellId);
    }

    public final int GetSpellSetMode() {
        return m_spellSetMode;
    }

    public void SetSpellSetMode(int ssm) {
        m_spellSetMode = ssm;
    }

    public boolean Dig() {
        if (m_pOwner.PlayerType() == PT.HUMAN) {
            if (!m_bCanDig) {
                iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), "", gTextMgr[TRID.MSG_DIG_TRY_TOMORROW], m_pOwner.PlayerId());
                tdlg.DoModal();
            } else if (!gGame.Map().CanDig(m_Pos)) {
                iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), "", gTextMgr[TRID.MSG_DIG_TRY_ON_CLEAR_LAND], m_pOwner.PlayerId());
                tdlg.DoModal();
            } else {
                // dig here
                gSfxMgr.PlaySound(CSND.DIG);
                m_Moves = 0;
                if (gGame.Map().Dig(m_Pos)) {
                    m_Artifacts.SmartPush(ARTT.ULTIMATE);
                    iIconDlg idlg = new iIconDlg(gApp.ViewMgr(), "", gTextMgr[TRID.MSG_DIG_FOUND], PDGG.ULTIMATE_ART, m_pOwner.PlayerId());
                    idlg.DoModal();
                    return true;
                } else {
                    iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), "", gTextMgr[TRID.MSG_DIG_NOTHING], m_pOwner.PlayerId());
                    tdlg.DoModal();
                }
            }
        } else {
            if (!m_bCanDig || !gGame.Map().CanDig(m_Pos)) {
                tracer.check(0);
            } else {
                // dig here
                if (gGame.Map().Dig(m_Pos)) {
                    m_Artifacts.SmartPush(ARTT.ULTIMATE);
                    m_Moves = 0;
                    return true;
                }
                m_Moves = 0;
            }
        }
        return false;
    }

    public final boolean CanDig() {
        return m_bCanDig;
    }

    public final String Name() {
        if (!m_CustomName.isEmpty())
            return m_CustomName;
        return gTextMgr[m_pProto.m_NameKey];
    }

    public final int Type() {
        return m_pProto.m_hType;
    }

    public final int Level() {
        return m_Level;
    }

    public final long Experience() {
        return m_Experience;
    }

    public final int Scouting() {
//        return (uint8)iCLAMP<sint32>(1,128,DEF_HERO_SCOUTING+FurtSkill(FSK_SCOUTING));
        return Math.max(1, Math.min(128, DEF.HERO_SCOUTING + FurtSkill(FSK.SCOUTING)));
    }

    public final int GetValueIndex() {
        return m_constFurtSkills.Value(FSK.ATTACK)*10 +
               m_constFurtSkills.Value(FSK.DEFENCE)*8 +
               m_constFurtSkills.Value(FSK.POWER)*8 +
               m_constFurtSkills.Value(FSK.KNOWLEDGE)*6;
    }

    public final long Moves() {
        return m_Moves;
    }

    public void AddMoves(long quant) {
        m_Moves += quant;
    }

    public final long TotalMoves() {
    // initial speed according to slowest creature
    int tmoves = (DEF.HERO_MOVES + (m_Army.SlowestSpeed()*2));
    tmoves += (tmoves * FurtSkill(FSK.LOGISTICS)) / 100;
    tmoves += FurtSkill(FSK.ACTPTS);

//    Усложнение жизни для некупивших игру...
//    // TRICK!
//    // make twice as much move points for computer
//    if (NEED_REGISTRATION && UNDER_CE) {
//        iAI_Player pOwner = DynamicCast<iAI_Player>( m_pOwner );
//        if ( pOwner && (pOwner.PlayerType() == PT.COMPUTER) && (pOwner.m_HackedTricks & 0x0f) ) {
//            tmoves = ((tmoves<<1) + (tmoves<<2)) / 3;
//        }
//    }

    return tmoves;
}

    public final int MoveCostBonus() {
        // 0 - 100
        return FurtSkill(FSK.PATHFINDING);
    }

    public static int LevelBound(int level) {
        // old formula: return (level-1)*(level-1)*500;
        if (level < 2)
            return 0;

        if (level <= 12)
            return DEF.LEVEL_BOUNDS[level-1];

        int cdif = (DEF.LEVEL_BOUNDS[11] - DEF.LEVEL_BOUNDS[10]);
        int val = DEF.LEVEL_BOUNDS[11];

        for (int nn = 12; nn < level; ++nn) {
            cdif += cdif / 5;
            val += cdif;
        }
        return val;
    }

    public final boolean ReachNextLevel() {
        return LevelBound(m_Level + 1) <= m_Experience;
    }


    // Secondary skills
    public final iSecSkillsCtr SecSkills() {
        return m_SecSkills;
    }

    // Further skills
    public final iFurtSkills ConstFurtSkills() {
        return m_constFurtSkills;
    }

    public final iFurtSkills VarFurtSkills() {
        return m_varFurtSkills;
    }

    public final int FurtSkill(int furtSkill) {
        int res = m_constFurtSkills.Value(furtSkill) + m_varFurtSkills.Value(furtSkill);
        if (m_pOwner != null)
            res += m_pOwner.FurtSkills().Value(furtSkill);
        return res;
    }
    public final iFurtSkills GetFullFurtSkills() {
        iFurtSkills res = new iFurtSkills(m_constFurtSkills, m_varFurtSkills);
        if (m_pOwner != null)
            res += m_pOwner.FurtSkills();
        return res;
    }

    // Artifacts
    public final iArtCtr Artifacts() {
        return m_Artifacts;
    }
    public final boolean NeedArtifact(int artId) {
        // Process negative case for special artifacts
        if (gGame.ItemMgr().m_artMgr[artId].Type() == ARTT.NEGSPHERE && Owner().PlayerType() == PT.COMPUTER) {
            int mval = FurtSkill(FSK.POWER) + FurtSkill(FSK.KNOWLEDGE);
            int sval = FurtSkill(FSK.ATTACK) + FurtSkill(FSK.DEFENCE);
            return mval > 20 || (mval > 3 && mval > (sval/2));
        }
        return true;
    }

    public void CaptureArtifacts(final iArtList artList) {
        // show dialog with captured artifacts (for active player)
        if (m_pOwner.PlayerType() == PT.HUMAN) {
            iDlg_CapArt cadlg = new iDlg_CapArt(gApp.ViewMgr(), this, artList);
            cadlg.DoModal();
            // Add artifacts to heroe's inventory
            for (int xx = 0; xx < artList.GetSize(); ++xx)
                m_Artifacts.Push(artList[xx]);
        } else {
            // Add artifacts to heroes' inventory using SmartPush method
            for (int xx = 0; xx < artList.GetSize(); ++xx)
                m_Artifacts.SmartPush(artList[xx]);
        }

    }

    public final boolean HasSpecFlag(int flag) {
        return m_Artifacts.HasArtType(ARTT.ULTIMATE) && ULTART.SPECFLAGS[m_pProto.m_hType] == flag;
    }

    // Opertaions
    public final long ConvExpPts(long exppts) {
        exppts += (exppts * FurtSkill(FSK.LEARNING))/100;
        return exppts;
    }

    public long ReceiveExperience(long points) {
        m_Experience += points;

        while (ReachNextLevel()) {
            m_Level++;
            iNewLevelInfo nli = NextLevelInfo();
            m_constFurtSkills.plusValue(FSK.ATTACK + nli.prSkillType, 1);
            if (m_pOwner == gGame.Map().ActPlayer()) {
                gSfxMgr.PlaySound(CSND.NEW_LEVEL_DLG);
                iDlg_NewLevel dlg = new iDlg_NewLevel(gApp.ViewMgr(), this, nli);
                dlg.DoModal();
                int ss = dlg.SecSkill();
                if (ss != SECSK.NONE)
                    m_SecSkills.Inc(ss);
            } else {
                tracer.check(Owner().PlayerType() == PT.COMPUTER);
                if (nli.secSkill[0] != SECSK.NONE && nli.secSkill[1] != SECSK.NONE)
                    m_SecSkills.Inc(nli.secSkill[gGame.Map().Rand(2)]);
                else if (nli.secSkill[0] != SECSK.NONE)
                    m_SecSkills.Inc(nli.secSkill[0]);
                else if (nli.secSkill[1] != SECSK.NONE)
                    m_SecSkills.Inc(nli.secSkill[1]);
            }
        }

        if (m_pOwner == gGame.Map().ActPlayer()) {
            System.err.println("in ReceiveExperience possible format error!");
            gGame.AddCellMsg(String.format("+%d#I%04X", points, PDGG.ICN_EXPERIENCE), m_Pos);
        }

        return 0;
    }

    public long RiseSkeletons(long enCas) {
        int val = FurtSkill(FSK.NECROMANCY);
        if (val == 0 || enCas == 0 || m_SecSkills.SkillLevel(SECSK.NECROMANCY) == SSLVL.NONE)
            return 0;

//        sint32 quant = iCLAMP<sint32>(1,enCas,(enCas * val) / 100);
        long quant = Math.max(1, Math.min(enCas, (enCas * val) / 100)); // Ограничевает третий параметр в диапазоне от первого до второго параметров.

        int ct = HasSpecFlag(SHF.NECRBONUS) ? CREAT.MUMMY : CREAT.SKELETON;

        // show dialog with raised skeletons
        if (m_pOwner == gGame.Map().ActPlayer()) {
            iDlg_RiseSkeletons rsdlg = new iDlg_RiseSkeletons(gApp.ViewMgr(), m_pOwner.PlayerId(), ct, quant);
            rsdlg.DoModal();
        }

        // Add Skeletons to army
        m_Army.AddGroup(ct, quant);

        return quant;
    }

    public void ReceiveReward(final iRewardItem reward) {
        switch(reward.m_type) {
        case RIT.MINERAL:
            m_pOwner.AddMineral(reward.m_fParam, reward.m_sParam);
            break;
        case RIT.EXPERIENCE:
            ReceiveExperience(reward.m_sParam);
            break;
        case RIT.MANAPTS:
            m_manaPts = Math.max(0, m_manaPts + reward.m_sParam);
            break;
        case RIT.TRAVELPTS:
            m_Moves = Math.max(0, m_Moves + reward.m_sParam);
            break;
        case RIT.MORALE:
            m_Enchs.Add(reward.m_type, FSK.MORALE, ENDUR.NEXTBATTLE, reward.m_sParam);
            break;
        case RIT.LUCK:
            m_Enchs.Add(reward.m_type, FSK.LUCK, ENDUR.NEXTBATTLE, reward.m_sParam);
            break;
        case RIT.FURTSKILL:
            tracer.check(reward.m_fParam >= FSK.ATTACK && reward.m_fParam <= FSK.KNOWLEDGE);
            m_constFurtSkills.plusValue(reward.m_fParam, reward.m_sParam);
            break;
        case RIT.ARTIFACT:
            if (m_pOwner.PlayerType() == PT.HUMAN) {
                m_Artifacts.Push(reward.m_fParam);
            } else {
                m_Artifacts.SmartPush(reward.m_fParam);
            }
            break;
        case RIT.MAGICSPELL:
            if (CanLearnSpell(reward.m_fParam)) {
                LearnSpell(reward.m_fParam);
            }
            break;
        case RIT.CREATGROUP:
            iCreatGroup cg = new iCreatGroup(reward.m_fParam, reward.m_sParam);
            if (m_pOwner.PlayerType() == PT.COMPUTER) {
                iAI_Player pAIOwner = DynamicCast<iAI_Player>(m_pOwner);
                tracer.check(pAIOwner);
                pAIOwner.ProcessJoinCreatures(m_Army, cg);
            } else {
                if (!m_Army.AddGroup(cg.Type(), cg.Count())) {
                    iDlg_ArmyRoom arDlg = new iDlg_ArmyRoom(gApp.ViewMgr(), this, cg);
                    arDlg.DoModal();
                }
            }
            break;
        default:
            tracer.check(0);
        }
    }

    // Mana
    public final long ManaPts() {
        return m_manaPts;
    }

    public final long MaxManaPts() {
        return (FurtSkill(FSK.KNOWLEDGE) * 10) + ((FurtSkill(FSK.KNOWLEDGE) * 10 * FurtSkill(FSK.INTELLIGENCE))/100);
    }

    // Heroes' location (map construction)
    public final iMapCnst GetLocation() {
        return m_pLocation;
    }

    public void SetLocation(iMapCnst pLocation) {
        if (m_pLocation == pLocation)
            return;

        iCastle pCastle = DynamicCast<iCastle>(m_pLocation);

        if (pCastle != null) {
            pCastle.OnVisitorLeave(this);
        }

        m_pLocation = pLocation;

        pCastle = DynamicCast<iCastle>(m_pLocation);
        if (pCastle != null) {
            pCastle.OnVisitorEnter(this);
        }
    }

    // Charms
    public void CastSpell(int spell) {
        tracer.check(spell != MSP.INVALID && SPELL.DESCRIPTORS[spell].type == SPT.OVERLAND);

//        iOverlandSpell pSpell =  (iOverlandSpell*)gGame.ItemMgr().m_spellMgr.Spell(spell);
        iOverlandSpell pSpell = gGame.ItemMgr().m_spellMgr.Spell(spell);

        int cost = iBaseSpell.GetSpellCost(pSpell.Id(), this);
        if (m_Moves < cost) {
            iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), "", gTextMgr[TRID.MSG_TIRED_TO_CAST], m_pOwner.PlayerId());
            tdlg.DoModal();
            return;
        }

        if (pSpell.Cast(this)) {
            m_manaPts -= cost;
            m_Moves -= cost;
        }
    }

    public void AddCharm(int spell, int flag) {
        m_charms.add(new iCharmEntry(spell,flag));
    }

    public final boolean CharmedBy(int spell, int flag) {
        for (int xx = 0; xx < m_charms.size(); ++xx) {
            if (m_charms.get(xx).spellId == spell) {
                flag = m_charms.get(xx).flag;
                return true;
            }
        }
        return false;
    }

    //
    public void NewDay() {
        // Remove "next day" enchancers
        m_Enchs.RemoveDur(ENDUR.NEXTDAY);

        // Restore moves
        m_Moves = TotalMoves();

        // Reset "can dig" flag
        m_bCanDig = true;

        // Restore mana points
        if (m_manaPts < MaxManaPts()) {
            if (HasSpecFlag(SHF.MANARESTORE)) {
                m_manaPts = MaxManaPts();
            } else {
                m_manaPts = Math.min(MaxManaPts(), m_manaPts + DEF.HERO_MYSTICISM + FurtSkill(FSK.MANAPTS));
            }
        }

        // Increase minerals without owner's income
        for (int xx = 0; xx < MINERAL.COUNT; ++xx)
            m_pOwner.Minerals().quant[xx] += m_constFurtSkills.Value(FSK.MIN_GOLD + xx) +
                                             m_varFurtSkills.Value(FSK.MIN_GOLD + xx);

        // Remove all charms
        for (int xx=0; xx < m_charms.size(); ++xx) {
            int spell = m_charms.get(xx).spellId;

            tracer.check(spell != MSP.INVALID && SPELL.DESCRIPTORS[spell].type == SPT.OVERLAND);

            iOverlandSpell pSpell = gGame.ItemMgr().m_spellMgr.Spell(spell);
            pSpell.OnRemove(this, m_charms.get(xx).flag);
        }
        m_charms.clear();
    }

    public void NewWeek(final iWeekDesc weekDesk) {
        m_Enchs.RemoveDur(ENDUR.NEXTWEEK);
    }

    public void OnEndBattle(boolean bWin) {
        m_Enchs.RemoveDur(ENDUR.NEXTBATTLE);
        if (m_Army.Empty()) {
            m_Army.AddGroup(HERO.INITIAL_ARMY[Proto().m_hType][0][0],1);
        }
        if (bWin && m_pOwner.PlayerType() == PT.HUMAN) {
            m_pOwner.SetCurHero(this);
            gGame.OnHeroChanged(this);
        }
    }

    public void OnSelect() {
        //
        //if (m_pOwner == gGame.Map().ActPlayer()) gGame.AddMsg(iFormat(gTextMgr[TRID_MSG_HEROSELECTED].CStr(),Name().CStr(),gTextMgr[TRID_HERO_TYPE_KNIGHT+Type()].CStr(),Level()));
        gGame.OnHeroChanged(this);
        // Recalculate path
        if (m_pOwner.PlayerType() == PT.COMPUTER) {
            m_Path.SetEmpty(Pos());
        } else if (!m_Path.IsEmpty()) {
            iPoint dpos = m_Path.DestPoint();
            m_Path.SetEmpty(Pos());
            SetPath(dpos);
        }
    }

    private void ResetArmy() {
        m_Army.ResetArmy();

        int ht = m_pProto.m_hType; // Hero Type
        // Random count:
        int rcnt = HERO.INITIAL_ARMY[ht][0][1] + gGame.Map().Rand(HERO.INITIAL_ARMY[ht][0][2]-HERO.INITIAL_ARMY[ht][0][1]);
        m_Army.AddGroup(HERO.INITIAL_ARMY[ht][0][0], rcnt);
        rcnt = HERO.INITIAL_ARMY[ht][1][1] + gGame.Map().Rand(HERO.INITIAL_ARMY[ht][1][2]-HERO.INITIAL_ARMY[ht][1][1]);
        if (rcnt > 0)
            m_Army.AddGroup(HERO.INITIAL_ARMY[ht][1][0], rcnt);
    }

    private void StartMoving() {
        m_bMoving = true;
    }

    private void StopMoving() {
        if (m_bMoving) {
            m_bMoving = false;
            gGame.OnHeroStopMoving(this);
        }
    }


    public void ActivateConstruction(iMapCnst pCnst) {
        pCnst.Activate(this, m_pOwner == gGame.Map().ActPlayer());
        if (m_bDead)
            return;
        m_pOwner.UpdateVisItems();
    }

    public void SiegeCastle(iCastle pCastle) {
        tracer.check(pCastle.Owner() != m_pOwner.PlayerId());
        tracer.check(!pCastle.Garrison().Empty() || pCastle.Visitor());
        iBattleInfo bi = new iBattleInfo(this, pCastle);
        gGame.BeginBattle(bi);
    }

    public void CaptureCastle(iCastle pCastle, boolean bEnter) {
        iPlayer oldPlayer = null;
        if (pCastle.Owner() != PID.NEUTRAL) {
            oldPlayer = gGame.Map().FindPlayer(pCastle.Owner());
            tracer.check(oldPlayer != null);
            oldPlayer.RemoveCastle(pCastle);
        } else {
            gGame.Map().m_Castles.Remove(iGameWorld.iCtIt(pCastle));
        }

        pCastle.SetOwner(m_pOwner.PlayerId());
        m_pOwner.AddCastle(pCastle);
        m_pOwner.OnCaptureCastle(pCastle);

        if (oldPlayer && !oldPlayer.DefeatState()) {
            gGame.Map().RemovePlayer(oldPlayer);
        }

        if (bEnter) {
            EnterCastle(pCastle);
        }
    }

    public void EnterCastle(iCastle pCastle) {
        if (Owner() == gGame.Map().ActPlayer()) {
            if (m_pOwner.CurCastle() != pCastle) {
                m_pOwner.SetCurCastle(pCastle);
            }

            gGame.ShowView(CV.CASTLE);
        }
    }

    public void TouchMapItem(iMapItem pMapItem, boolean bPostBattle) {
        if (pMapItem.Activate(this, m_pOwner == gGame.Map().ActPlayer(), !bPostBattle)){
            gGame.OnDisapObject(pMapItem.Sprite(), pMapItem.Pos());
            gGame.Map().RemoveMapItem(pMapItem);
            m_pOwner.UpdateVisItems();
        }
    }

    public void TouchMapGuard(iMapGuard pMapGuard, boolean bPostBattle) {
        if (bPostBattle || pMapGuard.Activate(this, m_pOwner == gGame.Map().ActPlayer())){
            if (m_pOwner == gGame.Map().ActPlayer()) {
                gSfxMgr.PlaySound(CSND.DEL_GUARD);
            }
            gGame.OnDisapObject(pMapGuard.Sprite(),pMapGuard.Pos());
            gGame.Map().RemoveMapGuard(pMapGuard);
            m_pOwner.UpdateVisItems();
        }
    }

    public void AttackHero(iHero pHero) {
        iBattleInfo bi = new iBattleInfo(this, pHero);
        gGame.BeginBattle(bi);
    }

    public void MeetHero(iHero pHero) {
        tracer.check(m_pOwner.PlayerId() == pHero.Owner().PlayerId());
        if (m_pOwner.PlayerType() == PT.HUMAN) {
            gGame.MeetHeroes(this, pHero, true);
        } else {
            iAI_Player pAIPlyer = DynamicCast<iAI_Player>(m_pOwner);
            tracer.check(pAIPlyer != null && pAIPlyer.NeedMeeting(this, pHero));
            pAIPlyer.MeetHeroes(this, pHero);
        }
    }
}
