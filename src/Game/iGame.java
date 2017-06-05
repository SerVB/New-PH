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

import Common.iPoint;
import utils.tracer;
import Constants.CV;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game
 */
public class iGame extends IGame {

    /**
     * Конструктор.
     */
    public iGame() {
        m_bInited = false;
        m_bStarted = false;
        m_tActView = CV.UNDEFINED;
        m_pMainView = null;
        m_pBattle = null;
        m_bGoToMainMenu = false;
        m_hmChannel = -1;
        m_hmSound = 0xFFFF;
        m_pChildView.clear(); // memset(m_pChildView,0,sizeof(m_pChildView));
    }

//   В Java нет деструкторов!
//    public ~iGame() {
//        Cleanup();
//    }

    /**
     * Init game resources.
     * @param fname
     * @return
     */
    public boolean Init(final String fname) {
        tracer.check(!m_bInited);
        long localyRegistered = 182;

        // Load items
        if (!LoadResources(m_itemMgr))
            return false;

        // check last session
        File fLastSave = new File(gSavePath + "lastses.phs");
        if ( (localyRegistered > 256) && fLastSave.exists()) {
            FileInputStream pSaveFile = null;

            try {

                pSaveFile = new FileInputStream(fLastSave);

//            if (HMM_COMPOVERSION) {
//                    pSaveFile = xxc.OpenXFile(gSavePath + "lastses.phs", HMM_COMPOCODE);
//            } else {
//                    pSaveFile = OpenWin32File(gSavePath + "lastses.phs");
//            }

                int fourcc = (pSaveFile.read() << 24) + (pSaveFile.read() << 16) +
                             (pSaveFile.read() <<  8) +  pSaveFile.read(); // Считать четыре байта

                iMapInfo mapInfo;
                mapInfo.m_FileName = gSavePath + "lastses.phs";
                mapInfo.m_bNewGame = false;
//                mapInfo.ReadMapInfo(pSaveFile.get());
                mapInfo.ReorderPlayers();

                pSaveFile.reset();
                fLastSave.delete();

                if (StartNewGame(mapInfo, false)) {

                    return m_bInited = true;

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(iGame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(iGame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    pSaveFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(iGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        // Init main menu
        initMainMenu();

        return m_bInited = true;
    }

    private void initMainMenu() {
        ShowView(CV.MENU);
        iMenuView mv = m_pChildView[CV.MENU];
        mv.Start();
    }

    /**
     * Cleanup game resources.
     */
    public void Cleanup() {
        for (int xx = 0; xx < CV.COUNT; ++xx){
            if (m_pChildView[xx] != null) {
                m_pChildView[xx] = null;
            }
        }

        m_tActView = CV.UNDEFINED;

        if (m_bInited) {
            m_itemMgr.Cleanup();
            m_bInited = false;
        }
    }

    // Game control
    public void Quit() {
        System.exit(0);
//        gApp.Exit(0);
    }

    public void MainMenu() {
        m_bGoToMainMenu = true;
    }

    /**
     * Показывавает прогресс.
     * @param curProg текущий прогресс для показа.
     * @param initial
     */
    void ShowProgressReport( long curProg, boolean initial ) {

    }

    /**
     * Показывавает прогресс.
     * @param curProg текущий прогресс для показа.
     */
    void ShowProgressReport( long curProg ) {
        ShowProgressReport(curProg, true);
    }

    public boolean StartNewGame(final iMapInfo mapInfo, boolean bNewGame) {
        FileInputStream pFile = null;
        try {

            ShowProgressReport( 0, false ); // 0%

            // Load map info
            iMapInfo _mapInfo = new iMapInfo();
            pFile = new FileInputStream(new File(mapInfo.m_FileName));

//        if (HMM_COMPOVERSION) {
//            iFileI* nakedFile;
//            //if ( bNewGame ) nakedFile = OpenWin32File( mapInfo.m_FileName ); else
//            nakedFile = xxc::OpenXFile( mapInfo.m_FileName, HMM_COMPOCODE );
//            iFilePtr pFile( nakedFile );
//        } else {
//            iFilePtr pFile( OpenWin32File( mapInfo.m_FileName ));
//        }

//            tracer.check(pFile);

            ShowProgressReport( 10, false ); // 10%

            int fourcc = (pFile.read() << 24) + (pFile.read() << 16) +
                         (pFile.read() <<  8) +  pFile.read(); // Считать четыре байта

            if (fourcc != GMAP_FILE_HDR_KEY || !_mapInfo.ReadMapInfo(pFile.get())) {
                return false;
            }

            ShowProgressReport( 25, false ); // 25%

            // Show Main view
            ShowView(CV.OVERLAND);

            ShowProgressReport( 30, false ); // 30%

            // Load map
            m_Map.LoadFromFile(mapInfo, pFile.get());

            ShowProgressReport( 50, false ); // 50%

            // Init Pass map
            m_Map.InitPassMap();

            ShowProgressReport( 60, false ); // 60%

            // Setup ultimate artifact position
            m_Map.InitUltimateArt();

            ShowProgressReport( 75, false ); // 75%

            // Init Sound map
            m_soundMap.Init(m_Map);

            ShowProgressReport( 85, false ); // 85%

            // Start the game
            m_Map.StartGame(mapInfo.m_curPlayerId, !bNewGame);
            ShowProgressReport( 100, false ); // 100%

            return true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(iGame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pFile.close();
            } catch (IOException ex) {
                Logger.getLogger(iGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public void ExitGame(boolean bChangeView) {
        m_soundMap.Cleanup();
        m_Map.CleanupGameWorld();
        m_itemMgr.OnGameEnd();

        if (bChangeView) {
            initMainMenu();
        }
    }

    // inlines
    public boolean Inited() {
        return m_bInited;
    }

    public boolean Started() {
        return m_bStarted;
    }

    public iGameWorld Map() {
        return m_Map;
    }

    public iItemMgr ItemMgr() {
        return m_itemMgr;
    }

    public iSoundMap SoundMap() {
        return m_soundMap;
    }

    // Child views
    public void ShowView(int cv) {
        xxc.sec_shuffle();

        tracer.check (cv != CV.UNDEFINED);
        if (cv == m_tActView)
            return;

        // Delete/Hide old active view
        if (m_tActView != CV.UNDEFINED) {
            m_pChildView[m_tActView].OnActivate(false);
            m_pChildView[m_tActView].SetVisible(false);
            gApp.ViewMgr().SetCurView(null);
            if (cv == CV.HERO && m_tActView != CV.OVERLAND) {
                // do not delete parent view
            } else {
                if (m_tActView == CV.MINIMAP) {
                    iMinimapView mmv = m_pChildView[m_tActView];
                    m_pMainView.Composer().CenterView(mmv.GetCenterCell());
                }

                if (!m_pChildView[m_tActView].Eternal() || cv == CV.MENU) {
//                    delete m_pChildView[m_tActView];
                    m_pChildView[m_tActView] = null;

                    if (m_tActView == CV.OVERLAND)
                        m_pMainView = null;
                }
            }
        }

        // Create/Show new active view
        switch (cv) {
        case CV.MENU:
            tracer.check(m_pChildView[cv] == null);

            m_pChildView[cv] = new iMenuView();
            break;

        case CV.OVERLAND:
            if (m_pMainView != null) {
                m_pMainView = new iMainView();

                m_pChildView[CV.OVERLAND] = m_pMainView;
            } else {
                m_pMainView.SetVisible(true);
            }
            break;

        case CV.BATTLE:
            tracer.check(m_pChildView[cv] == null);

            m_pChildView[cv] = new iBattleView();
            break;

        case CV.CASTLE:
            if (m_tActView != CV.HERO || m_pChildView[cv] != null) {
                tracer.check(m_pChildView[cv] == null);

                m_pChildView[cv] = new iCastleView();
            } else {
                m_pChildView[cv].SetVisible(true);
            }
            break;

        case CV.HERO:
            tracer.check(m_pChildView[cv] == null);

            m_pChildView[cv] = new iHeroView(m_tActView);
            break;

        case CV.MEET:
            if (m_tActView != CV.HERO || m_pChildView[cv] != null) {
                tracer.check(m_pChildView[cv] == null);

                m_pChildView[cv] = new iMeetView();
            } else {
                m_pChildView[cv].SetVisible(true);
            }
            break;

        case CV.MINIMAP:
            tracer.check(m_pChildView[cv] == null);

            iMinimapView pMMView = new iMinimapView();
            pMMView.SetCenterCell(m_pMainView.Composer().GetCenterCell());
            m_pChildView[cv] = pMMView;
            break;
        };

        m_pChildView[cv].OnActivate(true);
        gApp.ViewMgr().SetCurView(m_pChildView[cv]);
        m_tActView = cv;
    }

    public void HideView(int cv) {
        tracer.check(cv != CV.UNDEFINED);
        tracer.check(cv != CV.OVERLAND);
        tracer.check(m_pChildView[cv] != null);

        ShowView(m_pChildView[cv].cvParentView());
    }

    public iChildGameView View(int view) {
        return ( view == CV.UNDEFINED ) ? null : m_pChildView[view];
    }

    public iChildGameView ActView() {
        return View(m_tActView);
    }

    public iMainView MainView() {
        return m_pMainView;
    }

    ///////////////////////// Game process: /////////////////////////

    public void BeginBattle(final iBattleInfo bi) {
        tracer.check(!m_pBattle);

        tracer.check(bi.m_pAssaulter.Owner() != PID_NEUTRAL); // Assaulter cannot be neutral

        iPlayer pAssaulter = m_Map.FindPlayer(bi.m_pAssaulter.Owner());
        iPlayer pDefender = (bi.m_pDefender.Owner() == PID_NEUTRAL) ? NULL  :m_Map.FindPlayer(bi.m_pDefender.Owner());

        tracer.check(pAssaulter != null && !pAssaulter.equals(pDefender)); // Assaulter and Defender can't be owned by one player or side

        // Reset env sounds
        if (pAssaulter.PlayerType() == PT_HUMAN)
            m_soundMap.ResetEnvSounds();

        // If one of side is controlled by human, the battle must be interactive
        boolean bInt = pAssaulter.PlayerType() == PT_HUMAN || (pDefender != null && pDefender.PlayerType() == PT_HUMAN);

        if (bInt) {
            // Computer player attack the not active human player, so, change the active player first
            if (pDefender != null && pDefender.PlayerType() == PT_HUMAN && pAssaulter.PlayerType() != PT_HUMAN && pDefender != m_Map.ActPlayer()) {
                m_Map.SetNewActor(pDefender, true);
            }

            OnHeroStopMoving(gGame.Map().CurHero());
            if (gSettings.GetEntryValue(CET_QUICKCOMBAT)) {
                // Autobattle with result
                tracer.check(gGame.Map().CurHero() == bi.m_pAssaulter.SpellCaster());
                m_pBattle = new iAutoBattle(true);
            } else {
                // Interactive battle
                m_pBattle = new iInteractBattle();
            }
        } else {
            // Auto battle
            m_pBattle = new iAutoBattle();
        }

        m_pBattle.BeginBattle(bi);
    }

    /**
     * Заканчивает битву.
     */
    public void EndBattle() {
        tracer.check(m_pBattle);
//        delete m_pBattle;
        m_pBattle = null;
    }

    public void MeetHeroes(iHero pHero1, iHero pHero2, boolean bAct) {
        if (bAct) {
            ShowView(CV.MEET);

            iMeetView mv = m_pChildView[CV.MEET];
            mv.SetHeroes(pHero1, pHero2);
        }
    }

    public long Process(long t) {
        if (m_bGoToMainMenu) {

            m_bGoToMainMenu = false;
            ExitGame(true);

        } else if (ActView() != null && ActView().Process(t) && m_tActView == CV.OVERLAND ) {
            m_Map.Process(t);
        }

        return 0;
    }

    public void OnKeyDown(long key) {

    }

    /**
     * Обработчик нажатия на хардовые кнопки.
     * @param key ID кнопки.
     */
    public void OnKeyUp(long key) {
        xxc.sec_shuffle();

        if (key == gSettings.ActionKey(BAT_MINIMIZE_APP)) {
            gApp.Minimize();
        } else if (key == gSettings.ActionKey(BAT_MAKE_SCREENSHOT)) {
            String fname = gRootPath + "screenshot.bmp";
            SaveDibBitmap16(gApp.Surface(), fname);
            AddMsg("#F4B4" + gTextMgr[TRID_MSG_SCREENSHOT_SAVED]);
        }
    }

    public void OnSuspend() {
        m_soundMap.ResetEnvSounds();
    }

    public void OnResume() {
        xxc.sec_shuffle();

        if (m_Map.CurHero())
            SoundMap().SetupEnvSounds(m_Map.CurHero().Pos());
    }

    // Msgs
    public void AddMsg(final String msg) {
        if (m_pMainView != null)
            m_pMainView.AddMsg(msg);
    }

    public void AddCellMsg(final String msg, final iPoint pos) {
        if (m_pMainView != null)
            m_pMainView.AddCellMsg(msg, pos);
    }

    // Animation
    public void OnDisapObject(SpriteId sid, final iPoint pos, final iPoint offset) {
        if (!gGame.Map().ActPlayer().FogMap().IsHidden(pos)) {
            m_pMainView.Composer().SetAniObj(new iAniObj_FadeOut(m_pMainView.Composer(), sid,pos));
        }
    }

    public void OnDisapObject(SpriteId sid, final iPoint pos) {
        OnDisapObject(sid, pos, new iPoint(0,0));
    }

    // Game notifications:

    public void OnVictory() {
        // Show victory dialog
        iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), gTextMgr[TRID_VICTORY], gTextMgr[TRID_MSG_VICTORY], PID_NEUTRAL);
        tdlg.DoModal();

        // Show Hall of Fame
        SYSTEMTIME st;
        FILETIME ft;
        GetLocalTime(st);
        SystemTimeToFileTime(st, ft);
        long ut = (ft.dwLowDateTime + (ft.dwHighDateTime << 32)) / (600 * 1000 * 1000);
        iHighScore.iEntry entry = new iHighScore.iEntry(m_Map.MapName(), ut, m_Map.m_CurDay, m_Map.CalcGameScore());
        iDlg_HallOfFame dlg = new iDlg_HallOfFame(gApp.ViewMgr(), gRootPath + "PalmHeroes.hsc", entry);
        dlg.DoModal();

        MainMenu();
    }

    public void OnDefeat(boolean bExitGame) {
        iTextDlg tdlg = new iTextDlg(gApp.ViewMgr(), gTextMgr[TRID_DEFEAT], gTextMgr[TRID_MSG_DEFEAT],PID_NEUTRAL);
        tdlg.DoModal();

        if (bExitGame) {
            MainMenu();
        }
    }

    public void OnActorChanged(iPlayer pNewActor, boolean bAttack) {
        m_pChildView[m_tActView].SetVisible(false);
        iBlackBackView bbv;
        gApp.ViewMgr().SetCurView(bbv);
        String msg;
        if (bAttack) {
            msg = iFormat(gTextMgr[TRID_MSG_PLAYER_UNDER_ATTACK], gTextMgr[TRID_PLAYER_RED + pNewActor.PlayerId()]);
        } else {
            msg = iFormat(gTextMgr[TRID_MSG_PLAYER_TURN], gTextMgr[TRID_PLAYER_RED + pNewActor.PlayerId()]);
        }

        iIconDlg dlg = new iIconDlg(gApp.ViewMgr(), "", msg, PDGG_EMBLEMS + pNewActor.PlayerId(), pNewActor.PlayerId());
        dlg.DoModal();

        m_pChildView[m_tActView].SetVisible(true);

        gApp.ViewMgr().SetCurView(m_pChildView[m_tActView]);
    }

    public void OnPlayerChanged(iPlayer pNewPlayer, boolean bAct) {
        m_soundMap.ResetEnvSounds();
        m_pMainView.OnPlayerChanged(pNewPlayer, bAct);
    }

    public void OnPlayerVanquished(int pid) {
        iTextDlg tdlg = new iTextDlg(
                gApp.ViewMgr(),
                "",
                iFormat(gTextMgr[TRID_MSG_PLAYER_VANQUISHED], gTextMgr[TRID_PLAYER_RED + pid]),
                pid
        );
        tdlg.DoModal();
    }

    public void OnAddCastle(iCastle pCastle) {
        m_pMainView.OnCastleListChanged();
    }

    public void OnDelCastle(iCastle pCastle) {
        m_pMainView.OnCastleListChanged();
    }

    public void OnCastleChanged(iCastle pCastle) {
        if (!gGame.Map().ActPlayer().FogMap().IsHidden(pCastle.Pos())) {
            m_pMainView.Composer().CenterView(pCastle.Pos(), new iPoint());
        }

        if (m_tActView == CV.CASTLE) {
            iCastle pCurCastle = gGame.Map().ActPlayer().CurCastle();
            if (pCurCastle != null) {
                iCastleView cv = m_pChildView[CV.CASTLE];
                cv.SetCastle(pCurCastle);
            }
        }
    }

    public void OnAddHero(iHero pHero) {
        m_pMainView.OnHeroListChanged();
    }

    public void OnDelHero(iHero pHero) {
        if (m_tActView == CV.HERO) {
            if (iHero pCurHero = gGame.Map().ActPlayer().CurHero()) {
                iHeroView hv = m_pChildView[CV.HERO];
                hv.SetHero(pCurHero);
            } else {
                HideView(CV.HERO);
            }
        }

        m_pMainView.OnHeroListChanged();
        if (!gGame.Map().ActPlayer().FogMap().IsHidden(pHero.Pos()) && m_tActView == CV.OVERLAND) {
            m_pMainView.Composer().SetAniObj(new iAniObj_FadeOut(m_pMainView.Composer(), GetHeroSprite(pHero),pHero.Pos()));
            if (gGame.Map().CurPlayer().PlayerType() == PT_HUMAN) {
                if (gGame.Map().ActPlayer().CurHero() == null) {
                    m_soundMap.ResetEnvSounds();
                }
                gSfxMgr.PlaySound(CSND_DEL_GUARD);
            }
        }

        iAI_Player pAIPlayer = new DynamicCast<iAI_Player>(gGame.Map().CurPlayer());
        if (pAIPlayer != null) {
            pAIPlayer.OnHeroDead(pHero);
        }
    }

    public void OnHeroChanged(iHero pHero) {
        if (!gGame.Map().ActPlayer().FogMap().IsHidden(pHero.Pos()) &&
            gGame.Map().CurPlayer().PlayerId() == pHero.Owner().PlayerId()) {

            m_pMainView.Composer().CenterView(pHero.Pos());

        }

        if (gGame.Map().ActPlayer().PlayerId() == pHero.Owner().PlayerId()) {
            m_soundMap.SetupEnvSounds(pHero.Pos());
        }

        if (m_tActView == CV.HERO) {
            iHero pCurHero = gGame.Map().ActPlayer().CurHero();
            if (pCurHero != null) {
                iHeroView hv = m_pChildView[CV.HERO];
                hv.SetHero(pCurHero);
            }
        }
    }

    public void OnHeroLevel(iHero pHero, int level, final iNewLevelInfo linfo) {

    }

    public void OnHeroMoveTo(iHero pHero, final iPath.iStep step) {
        if (gGame.Map().ActPlayer().PlayerId() == pHero.Owner().PlayerId()) {
            if (step.m_action == iPath.MoveTo || step.m_action == iPath.Enter) {
                long px = step.m_Pos.x;
                long py = step.m_Pos.y;
                int st = m_Map.GetAt(px, py).SurfNode(0); // SURF_TYPE
                long avatar = m_Map.GetAt(px, py).avatar;
                long snd  = gSfxMgr.GetMovSound(
                        st,
                        m_Map.m_CoverMap.GetAt(px,py) != null  || (avatar != 0xFFFF && ( avatar & 0xFF00) == 0xFF00)
                );
                if (m_hmChannel == -1) {
                    m_hmSound = snd;
                    m_hmChannel = gSfxMgr.PlaySound(m_hmSound, true);
                } else {
                    if (m_hmSound != snd) {
                        m_hmSound = snd;
                        gSfxMgr.ReplaceSound(m_hmChannel, m_hmSound);
                    }
                }
                if (m_tActView == CV.OVERLAND) {
                    m_pMainView.EnableToolBar(false);
                }
            }
        }

        if (!gGame.Map().ActPlayer().FogMap().IsHidden(pHero.Pos()) &&
            !gGame.Map().ActPlayer().FogMap().IsHidden(step.m_Pos)) {

            if (step.m_action == iPath.MoveTo || step.m_action == iPath.Enter) {
                if (m_pMainView.Composer().AniObj()) {
                    m_pMainView.Composer().RemoveAniObj();
                }
                m_pMainView.Composer().SetAniObj(new iAniObj_MovHero(m_pMainView.Composer(), pHero));
                tracer.check(pHero.Moving());
            } else {
                pHero.Step();
            }

        } else {
            pHero.Step();
        }
    }

    public void OnHeroStopMoving(iHero pHero) {
        if (gGame.Map().ActPlayer().PlayerId() == pHero.Owner().PlayerId()) {
            if (m_hmChannel != -1) {
                gApp.SndPlayer().Stop(m_hmChannel);
                m_hmChannel = -1;
            }
            if (m_tActView == CV.OVERLAND) {
                m_pMainView.EnableToolBar(true);
            }
        }
    }

    public void OnHeroPosChanged(iHero pHero, final iPoint npos) {
        if (pHero.Owner().PlayerType() == PT_HUMAN) {
            m_soundMap.SetupEnvSounds(pHero.Pos());
        }
    }

    public void OnHeroTeleport(iHero pHero, final iPoint src, final iPoint dst) {
        if (pHero.Owner().PlayerType() == PT_HUMAN ||
            (!gGame.Map().ActPlayer().FogMap().IsHidden(src) && !gGame.Map().ActPlayer().FogMap().IsHidden(dst))) {

            if (m_pMainView.Composer().AniObj())
                m_pMainView.Composer().RemoveAniObj();
            m_pMainView.Composer().SetAniObj(new iAniObj_Teleport(m_pMainView.Composer()));

            if (gGame.Map().ActPlayer().PlayerId() == pHero.Owner().PlayerId()) {
                gSfxMgr.PlaySound(CSND_TELEPORT_OUT);
            }

        }
    }

    public void OnAttackHero(iHero pHero1, iHero pHero2) {

    }

    private boolean            m_bGoToMainMenu;
    private iItemMgr        m_itemMgr;
    private iGameWorld        m_Map;
    private iSoundMap        m_soundMap;
    private iBattleWrapper    m_pBattle;
    private boolean            m_bInited;
    private boolean           m_bStarted;

    private long            m_hmChannel;
    private long            m_hmSound;
    private iMainView        m_pMainView;
    private iChildGameView[]    m_pChildView = new iChildGameView[CV.COUNT];
    private int        m_tActView;
}
