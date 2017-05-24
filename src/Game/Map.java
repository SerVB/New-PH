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

//#include "stdafx.h"

import Common.iMineralSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//#include "serialize.h"

/**
 * Информация об игровой карте
 */
class iMapInfo {

    /**
     * Игровой режим GameMode
     */

    final static int GM_UNDEFINED = 0; // Неопределенный
    final static int GM_SPLAYER   = 1; // Single player
    final static int GM_HOTSEAT   = 2; // Hotseat

    /**
     *
     */
    class iPlayerInfo {
        /**
         * Конструктор.
         */
        iPlayerInfo(int pid, PLAYER_TYPE_MASK ptypemask, PLAYER_TYPE ptype, CTL_TYPE ntype) {
            this.m_Id = pid;
            this.m_TypeMask = ptypemask;
            this.m_Type = ptype;
            this.m_Nation = ntype;
        }

        /**
         * Минералы.
         */
        iMineralSet m_Minerals;

        /**
         * ID игрока.
         */
        int m_Id;

        /**
         * ???
         */
        PLAYER_TYPE_MASK m_TypeMask;

        /**
         * ???
         */
        PLAYER_TYPE m_Type;

        /**
         * Нация.
         */
        CTL_TYPE m_Nation;

        /**
         * ???
         */
        long m_curHeroId;

        /**
         * ???
         */
        long m_curCastleIdx;

        /**
         * ???
         */
        long m_keys;
    }

    /**
     * Считать инофрмацию карты
     */
    boolean ReadMapInfo(FileInputStream pFile) {
        try {

            long ver; // Версия карты
            ver = (pFile.read() << 8) + pFile.read(); // Считать два байта
            if (ver != GMAP_FILE_VERSION)
                return false;

            // Encoding type
            m_encType = (pFile.read() << 8) + pFile.read(); // Считать два байта

            // Save timestamp
            m_saveTime = (pFile.read() << 24) + (pFile.read() << 16) +
                         (pFile.read() << 8) + pFile.read(); // Считать четыре байта

            // Random seed
            m_rseed = (pFile.read() << 24) + (pFile.read() << 16) +
                      (pFile.read() << 8) + pFile.read(); // Считать четыре байта

            // Map size
            long mapSiz;
            mapSiz = pFile.read(); // Считать один байт
            m_Size = new MAP_SIZE(mapSiz);

            // Map name and description (string of text)
            Unserialize(pFile, m_Name);
            Unserialize(pFile, m_Description);

            // Map version and author (string of text)
            Unserialize(pFile, m_Version);
            Unserialize(pFile, m_Author);

            // Current date (1 is default value for new game)
            m_curDay = (pFile.read() << 24) + (pFile.read() << 16) +
                       (pFile.read() << 8) + pFile.read(); // Считать четыре байта

            // Game mode (GM_UNDEFINED for new map)
            int gameMode;
            gameMode = (pFile.read() << 8) + pFile.read(); // Считать два байта
            m_gameMode = GameMode.values()[gameMode];

            // Difficulty level (DFC_UNDEFINED for new game)
            int gameDifLvl;
            gameDifLvl = pFile.read(); // Считать один байт
            m_Difficulty = (DIFFICULTY_LEVEL)gameDifLvl;

            // Read Player config
            long pCount;
            pCount = (pFile.read() << 8) + pFile.read(); // Считать два байта
            boolean bHumanDefined = false;
            for (long xx = 0; xx < pCount; ++xx){
                // Player Id
                long playerId = pFile.read(); // Считать один байт
                // Nation type
                long nation = pFile.read(); // Считать один байт
                // Player Type Mask
                long playerTypeMask = pFile.read(); // Считать один байт
                // Player Type (PT_UNDEFINED for new game)
                long playerType = pFile.read(); // Считать один байт
                // Create playerInfo descriptor
                iPlayerInfo playerInfo = new iPlayerInfo(
                                        (PLAYER_ID)playerId,
                                        (PLAYER_TYPE_MASK)playerTypeMask,
                                        (PLAYER_TYPE)playerType,
                                        (CTL_TYPE)nation );
                if (playerType == PT_UNDEFINED) {
                    if (!bHumanDefined && (playerTypeMask == PTM_HUMAN_ONLY || playerTypeMask == PTM_HUMAN_OR_COMPUTER)) {
                        bHumanDefined = true;
                        playerInfo.m_Type = PT_HUMAN;
                    } else {
                        playerInfo.m_Type = PT_COMPUTER;
                    }
                }
                // Player resources (undefined for new game)
                Unserialize(pFilet, playerInfo.m_Minerals);
                // Current Hero idx (0xFFFF = undefined for new game)
                playerInfo.m_curHeroId = (pFile.read() << 8) + pFile.read(); // Считать два байта
                // Current Castle idx (0xFFFF = undefined for new game)
                playerInfo.m_curCastleIdx = (pFile.read() << 8) + pFile.read(); // Считать два байта
                // Keys state
                playerInfo.m_keys = pFile.read(); // Считать один байт
                // Insert Player descriptor
                m_Players.add(playerInfo);
            }

            // Current player Id (PID_NEUTRAL = undefined for new game)
            long curPlayerId = (pFile.read() << 8) + pFile.read(); // Считать два байта
            if(curPlayerId == 0xFFFF)
                m_curPlayerId = PID_NEUTRAL;
            else
                m_curPlayerId = (PLAYER_ID)curPlayerId;

            // Map metrics
            long w = (pFile.read() << 8) + pFile.read(); // Считать два байта
            long h = (pFile.read() << 8) + pFile.read(); // Считать два байта
            m_metrics.w = w;
            m_metrics.h = h;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(iMapInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(iMapInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * Должно возвращать true только в случае успеха, как понимаю.
         * Но после переноса в Java возращает true еще и если исключение.
         * Нужно исправить!
         */
        return true;
    }

    /**
     * Упорядочить список игроков:
     * сначала PT_HUMAN игроки,
     * потом PT_COMPUTER игроки.
     */
    void ReorderPlayers() {
        ArrayList<iPlayerInfo> defPl = new ArrayList();
        // Переместить PT_HUMAN из m_Players в defPl
        for(int pidx = 0; pidx < m_Players.size(); /**/) {
            check(m_Players.get(pidx).m_Type != PT_UNDEFINED);

            if (m_Players.get(pidx).m_Type == PT_HUMAN) {
                defPl.add(m_Players.get(pidx));
                m_Players.remove(pidx);
            }
            else
                ++pidx;
        }
        // Продолжить, если PT_HUMAN существуют
        check(defPl.size() > 0);
        // Если один PT_HUMAN, то игровой режим GM_SPLAYER, иначе GM_HOTSEAT
        m_gameMode = (defPl.size() == 1) ? GameMode.GM_SPLAYER : GameMode.GM_HOTSEAT;
        // Добавить оставшихся игроков
        defPl.addAll(m_Players);
        // Записать порядок игроков в основной список
        m_Players = defPl;
    }

    /**
     * Количество игроков
     */
    long TotalPlayers() {
        return m_Players.size();
    }

    /**
     * Максимальное количество людей
     */
    long HumanPlayers() {
        long res = 0;
        for(int xx = 0; xx < m_Players.size(); ++xx)
            if(m_Players.get(xx).m_TypeMask == PTM_HUMAN_ONLY ||
               m_Players.get(xx).m_TypeMask == PTM_HUMAN_OR_COMPUTER)
                res++;
        return res;
    }


    /**
     * ???
     */
    boolean Supported() {
        if(OS_WINCE || (OS_WIN32 && DEV_VER)) {
            if(DEV_VER)
                if (m_encType == MET_MAPDES)
                    return true;
            boolean registered = IS_REGISTERED();
            return (m_encType == MET_PUBLIC) ||
                   (m_encType == MET_PRIVATE && registered);
        }
        else
            return m_encType == MET_MAPDES;
    }


    /**
     * Тип кодировки
     */
    long m_encType;

    /**
     * ???
     */
    boolean m_bNewGame;

    /**
     * Время сохранения (в чем измеряется?)
     */
    long m_saveTime;

    /**
     * Игровой режим
     */
    GameMode m_gameMode;

    /**
     * Имя файла?
     */
    String m_FileName;

    /**
     * Уровень сложности
     */
    DIFFICULTY_LEVEL m_Difficulty;

    /**
     * Список игроков?
     */
    ArrayList<iPlayerInfo> m_Players;

    /**
     * Размер карты
     */
    MAP_SIZE  m_Size;

    /**
     * Название карты?
     */
    String m_Name;

    /**
     * Описание карты
     */
    String m_Description;

    /**
     * Версия карты
     */
    String m_Version;

    /**
     * Автор карты
     */
    String m_Author;

    /**
     * Текущий день?
     */
    long m_curDay;

    /**
     * ???
     */
    PLAYER_ID m_curPlayerId;

    /**
     * Рандомное зерно?
     */
    long m_rseed;

    /**
     * Размер карты?
     */
    iSize m_metrics;
}

/**
 * Клетка чего-то?
 */
class iCell {

    /**
     * Конструктор
     */
    iCell() {
        this.surf = 0;
        this.avatar = 0;
    }

    /**
     * ???
     */
    int SurfNode(int idx) {
        check(idx<4);
        return (surf >> ((3-idx)*4)) & 0xF;
    }

    /**
     * ???
     */
    boolean Solid() {
        return (SurfNode(0) == SurfNode(1)) &&
               (SurfNode(1) == SurfNode(2)) &&
               (SurfNode(2) == SurfNode(3));
    }

    /**
     * ???
     */
    int surf;

    /**
     * ???
     */
    long avatar;
}

/**
 * ???
 */
class iGameMap extends iMapT<iCell> {
//public:
//    typedef iIList<iVisCnst>        iVisCnstList;
//    typedef iVisCnstList::Iterator    iVCIt;
//
//    typedef iIList<iOwnCnst>        iOwnCnstList;
//    typedef iOwnCnstList::Iterator    iOCIt;
//
//    typedef iIList<iCastle>            iCastleList;
//    typedef iCastleList::Iterator    iCtIt;
//
//    typedef iIList<iMapItem>        iMapItemList;
//    typedef iMapItemList::Iterator    iMIt;
//
//    typedef iIList<iMapEvent>        iMapEventList;
//    typedef iMapEventList::Iterator    iEIt;
//
//    typedef iIList<iMapGuard>        iMapGuardList;
//    typedef iMapGuardList::Iterator    iGIt;
//
//    typedef iIList<iPlayer>            iPlayerList;
//    typedef iPlayerList::Iterator    iPLIt;
//
//    typedef iIList<iHero>            iHeroList;
//    typedef iHeroList::Iterator        iHLIt;

    /**
     * Ячейка поверхности (какой?)
     */
    public class iSurfCell {

        /**
         * Нижний слой (чего?)
         */
        long lowestLayer;

        /**
         * Слои (чего?)
         */
        long[] layers = new long[STYPE_COUNT];

        /**
         * Сброс, заполнение нулями
         */
        public void reset() {
            lowestLayer = 0;
            layers = new long[STYPE_COUNT];
        }
    }


    /**
     * Получить поверхность клетки
     */
    public boolean GetCellSurf(iPoint pos, iSurfCell cell) {
        cell.reset();
        iCell surf = GetAt(pos.x,pos.y);

        // solid
        if( surf.SurfNode(0) == surf.SurfNode(1) &&
            surf.SurfNode(1) == surf.SurfNode(2) &&
            surf.SurfNode(2) == surf.SurfNode(3) ) {
            cell.lowestLayer = surf.SurfNode(0);
            return true;
        }

        // calculate underlying surface type
        cell.lowestLayer = Math.min(surf.SurfNode(0),
                           Math.min(surf.SurfNode(1),
                           Math.min(surf.SurfNode(2), surf.SurfNode(3)) ));

        // setup config masks for all type of surfaces
        for (int xx=0; xx<4; ++xx)
            if (surf.SurfNode(xx) != cell.lowestLayer)
                cell.layers[surf.SurfNode(xx)] |= (1 << xx);

        return false;

        /*
        //  1 bit    : solid surface  flag
        //  3 bits   : underlying surface type
        //  28 bits  : 4 bits per surface transition config 4*7=28 bits
        iCell cell = GetAt(pos.x,pos.y);
        uint32 ccfg = 0;

        if ( cell.Solid() ) {
            // setup solid flag and surface type
            ccfg |= (1<<31) | ( cell.SurfNode(0) << 28);
            return ccfg;
        }

        uint8 uls = (uint8)iMIN(cell.SurfNode(0),iMIN(cell.SurfNode(1),iMIN(cell.SurfNode(2),cell.SurfNode(3))));
        ccfg |= (uls<<28);
        for (uint32 xx=0; xx<4; ++xx){
            if (cell.SurfNode(xx) != uls) ccfg |= (1 << (cell.SurfNode(xx)*4+xx));
        }

        return ccfg;
        */
    }

}