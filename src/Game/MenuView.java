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

import utils.RGB;
import newph.core.metric.iPoint;
import Common.metrics.iRect;
import Common.metrics.iSize;

//#include "stdafx.h"
//#include "Credits.h"
//#include "MenuView.h"
//#include "Dlg_ScenList.h"
//#include "Dlg_Save.h"
//#include "Dlg_ScenProps.h"
//#include "Dlg_HallOfFame.h"

class MenuView {
    /**
     * Хранилище цвета текста кнопок, плавно изменяющиеся оттенки желтого.
     * От серо-желтого до яркого желтого.
     */
    iDib.pixel[] menuBtnText = {
        new RGB(210,190,115), new RGB(214,192,110), new RGB(216,192,102), new RGB(219,193,96), new RGB(221,193,85),
        new RGB(224,194,76),  new RGB(228,196,67),  new RGB(231,195,59),  new RGB(233,196,49), new RGB(236,196,40),
        new RGB(239,198,31),  new RGB(242,198,23),  new RGB(224,198,16),  new RGB(247,199,0),  new RGB(248,200,0)
    };

    /**
     * Активити главного меню
     */
    class iMenuView extends iChildGameView {

        /**
         * Конструктор
         */
        public iMenuView() {
            super(false, CV_UNDEFINED);
            m_crComposer.Init();
        }

        /**
         * ???
         */
        public void Start() {
            /*
             * Если игре нужен новый ключ активации, выполняются какие-то действия
             */
            if(gEnterNewKey) {
                boolean registered = IS_REGISTERED();
                if (registered) {
                    iTextDlg tdlg(
                            gApp.ViewMgr(),
                            gTextMgr[TRID_REG_SUCCEDED],
                            gTextMgr[TRID_REG_DONE],
                            PID_GREEN
                                    );
                    tdlg.DoModal();
                }
                else {
                    gSettings.SetActivationKey("");
                    iTextDlg tdlg(
                            gApp.ViewMgr(),
                            gTextMgr[TRID_REG_FAILED],
                            gTextMgr[TRID_REG_INVALIDKEY],
                            PID_RED
                                    );
                    tdlg.DoModal();
                }
                gEnterNewKey = false;
            }
            /*
             * Пока открыто окно. Как мне кажется, плохое решение
             * по отслеживанию нажатия по одной из кнопок.
             * Отслеживание нажатой кнопки и выполнение нужного действия.
             */
            while(true) {
                iMainMenuDlg mdlg(gApp.ViewMgr());
                long res = mdlg.DoModal();


                if (res == 100) {
                    // Start new game
                    iScenListDlg sldlg(&gApp.ViewMgr());
                    res = sldlg.DoModal();
                    if (res == DRC_OK) {
                        iMapInfo scenProps = sldlg.SelScen();
                        iScenPropsDlg spdlg(gApp.ViewMgr(), scenProps, false);
                        if (spdlg.DoModal() == DRC_OK)    {
                            scenProps.ReorderPlayers();
                            gGame.StartNewGame(scenProps, true);
                            break;
                        }
                    }
                }
                else if (res == 101) {
                    // Load saved game
                    iSaveDlg saveDlg(gApp.ViewMgr(), false);
                    res = saveDlg.DoModal();
                    if (res == DRC_OK) {
                        iMapInfo scenProps = saveDlg.SelScenario();
                        iScenPropsDlg spdlg(&gApp.ViewMgr(), scenProps, true);
                        if (spdlg.DoModal() == DRC_OK)    {
                            scenProps.ReorderPlayers();
                            gGame.StartNewGame(scenProps, false);
                            break;
                        }
                    }
                }
                else if (res == 102) {
                    iDlg_HallOfFame dlg(&gApp.ViewMgr(), gRootPath + _T("PalmHeroes.hsc"));
                    dlg.DoModal();
                }
                else if (res == 103) {
                    StartCredits();
                    break;
                }
                else if (res == 104) {
                    // Quit to WM200x
                    gGame.Quit();
                    break;
                }
            }
        }

        /**
         * По созданию активити
         */
        public void OnCompose() {
            // Отрисовка поверхности-фоновой картинки?
            m_crComposer.Compose(gApp.Surface(), new iPoint(0,0));

            // Устаревший код отрисовки
            //gGfxMgr.Blit( PDGG_LOGO, gApp.Surface(), iPoint( 44,2));
            //gGfxMgr.Blit(PDGG_LOGO2, gApp.Surface(), iPoint(174,3));

        }

        /**
         * Обработчик титров?
         */
        @Override
        public boolean Process(long t) {
            // Если титры были начаты и закончились, то остановить титры
            if (m_crComposer.IsCreaditsStarted() && m_crComposer.IsCreaditsEnd())
                StopCredits();
            Invalidate();
            return true;
        }

        /**
         * По нажатию
         * Остановить титры
         */
        public void OnMouseClick(iPoint pos) {
            StopCredits();
        }

        /**
         * Начать отображение титров
         */
        public void StartCredits() {
            m_crComposer.StartCredits();
        }

        /**
         * Остановить отображение титров
         */
        public void StopCredits() {
            m_crComposer.StopCredits();
            Start();
        }

        /**
         * ???
         */
        private void iCMDH_ControlCommand(iView pView, CTRL_CMD_ID cmd, long param) {
            int uid = pView.GetUID();
        }

        /**
         * ???
         */
        private iCreditsComposer m_crComposer;

        //#define BLOOMBITS_HASH 0x7a89324b -- Устаревшая версия
        //#define BLOOMBITS_HASH 0xe31751e0 -- Устаревшая версия
        /**
         * Hash updated to reflect current 'dumpbits.dat' file version!
         * TODO:: move const somewhere away OR otherwise automate!
         */
        private final long BLOOMBITS_HASH = 0x955884d1;

    }

    /**
     * Java не поддерживает множественное наследование! С эти нужно что-то делать...
     * Главный класс диалога -- размером с 5 кнопок,
     * не включает логотипа и фона, только кнопки
     */
    class iMainMenuDlg extends iDialog {
    //class iMainMenuDlg extends iDialog, IViewCmdHandler {
    //public:
        /**
         * Кнопка меню
        */
        class iMainMenuBtn extends iButton {
            /**
             * Конструктор
             */
            public iMainMenuBtn(iViewMgr pViewMgr,
                                IViewCmdHandler pCmdHandler,
                                iRect rect,
                                TextResId textKey,
                                long uid,
                                long state) {
                super(pViewMgr, pCmdHandler, rect, uid, state);
                this.m_TextKey = textKey;
            }

            /**
             * Конструктор без последнего параметра
             */
            public iMainMenuBtn(iViewMgr pViewMgr,
                                IViewCmdHandler pCmdHandler,
                                iRect rect,
                                TextResId textKey,
                                long uid) {
                iMainMenuBtn(pViewMgr, pCmdHandler, rect, textKey, uid, Visible | Enabled);
            }

            /**
             * По нажатию
             */
            public void OnBtnDown() {
                //gSfxMgr.PlaySound(CSND_BUTTON);
            }

            /**
             * По созданию
             */
            public void OnCompose()
            {
                gApp.Surface().Darken50Rect(GetScrRect());
                // Compose outer frame
                iRect rect = GetScrRect();
                rect.InflateRect(1);
                long cColor_Grey = RGB16(32,32,32);
                gApp.Surface().HLine(iPoint(rect.x+2, rect.y), rect.x+rect.w-3, cColor_Grey);
                gApp.Surface().HLine(iPoint(rect.x+2, rect.y+rect.h-1), rect.x+rect.w-3, cColor_Grey);
                gApp.Surface().VLine(iPoint(rect.x,rect.y+2), rect.y+rect.h-2, cColor_Grey);
                gApp.Surface().VLine(iPoint(rect.x+rect.w-1,rect.y+2), rect.y+rect.h-2, cColor_Grey);

                iDibFont.ComposeProps props =
                    iDibFont.ComposeProps(iGradient(menuBtnText,15), RGB.cColor_Black, iDibFont.DecBorder);
                long state = GetButtonState();
                if ( state & iButton.Disabled ) {
                    props = iDibFont.ComposeProps(new RGB(128,100,0), RGB.cColor_Black, iDibFont.DecBorder);
                } else if ( state & iButton.Pressed ) {
                    props = iDibFont.ComposeProps(new RGB(255,255,255), RGB.cColor_Black, iDibFont.DecBorder);
                    gApp.Surface().Darken50Rect(GetScrRect());
                }
                iTextComposer.FontConfig fc(iTextComposer.FS_LARGE, props );
                gTextComposer.TextOut(
                        fc,
                        gApp.Surface(),
                        iPoint(),
                        gTextMgr[m_TextKey],
                        GetScrRect(),
                        AlignCenter,
                        (state&iButton.Pressed ? iPoint(1,1) : iPoint(0,0))
                );
            }

            /**
             * ID текста, отображаемого в кнопке.
             */
            private int m_TextKey;
        }

        /**
         * Конструктор
         */
        public iMainMenuDlg(iViewMgr pViewMgr) {
            iDialog(pViewMgr);
        }

        /**
         * Колисчество кнопок?
         */
        public final int BTN_DIST = 5;

        /**
         * По созданию активити
         * Создание пяти кнопок
         */
        private void OnCreateDlg() {
            iRect rc = GetDlgMetrics();

            rc.h = DEF_BTN_HEIGHT + 2;
            AddChild(new iMainMenuBtn(m_pMgr, this, rc, TRID_MENU_NEWGAME, 100, Visible|Enabled));

            rc.y += DEF_BTN_HEIGHT + BTN_DIST;
            AddChild(new iMainMenuBtn(m_pMgr, this, rc, TRID_MENU_LOADGAME, 101, Visible|Enabled));

            rc.y += DEF_BTN_HEIGHT + BTN_DIST;
            AddChild(new iMainMenuBtn(m_pMgr, this, rc, TRID_MENU_HIGHSCORE, 102, Visible|Enabled));

            rc.y += DEF_BTN_HEIGHT + BTN_DIST;
            AddChild(new iMainMenuBtn(m_pMgr, this, rc, TRID_MENU_CREDITS, 103, Visible|Enabled));

            rc.y += DEF_BTN_HEIGHT + BTN_DIST;
            AddChild(new iMainMenuBtn(m_pMgr, this, rc, TRID_MENU_EXITGAME, 104, Visible|Enabled));
        }

        /**
         * ???
         */
        private void OnPlace(iRect rect) {
            rect.y += 40;
        }

        /**
         * Получить размеры панели с кнопками.
         * 150 пикселей в ширину и 5 кнопок с хвостиком в высоту.
         */
        private iSize GetDlgMetrics() {
            return iSize(150, 5 * (DEF_BTN_HEIGHT + 2) + 12);
        }

        /**
         * ???
         */
        private void iCMDH_ControlCommand(iView pView, CTRL_CMD_ID cmd, long param) {
            EndDialog(pView.GetUID());
        }
    }
}