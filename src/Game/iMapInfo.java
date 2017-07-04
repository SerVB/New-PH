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

import Common.metrics.iSize;
import utils.serialize;
import newph.core.staticFunction.Tracer;
import Constants.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */

/**
 * Информация об игровой карте
 */
public class iMapInfo {

    /**
     * Считать информацию карты.
     */
    boolean ReadMapInfo(FileInputStream pFile) {
        try {

            long ver; // Версия карты
            ver = (pFile.read() << 8) + pFile.read(); // Считать два байта
            if (ver != GMAP.FILE_VERSION)
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
            int mapSiz;
            mapSiz = pFile.read(); // Считать один байт
            m_Size = MAP.SIZ_SIZE[mapSiz];

            // Map name and description (string of text)
            serialize.Unserialize(pFile, m_Name);
            serialize.Unserialize(pFile, m_Description);

            // Map version and author (string of text)
            serialize.Unserialize(pFile, m_Version);
            serialize.Unserialize(pFile, m_Author);

            // Current date (1 is default value for new game)
            m_curDay = (pFile.read() << 24) + (pFile.read() << 16) +
                       (pFile.read() << 8) + pFile.read(); // Считать четыре байта

            // Game mode (GM_UNDEFINED for new map)
            int gameMode;
            gameMode = (pFile.read() << 8) + pFile.read(); // Считать два байта
            m_gameMode = gameMode;

            // Difficulty level (DFC_UNDEFINED for new game)
            int gameDifLvl;
            gameDifLvl = pFile.read(); // Считать один байт
            m_Difficulty = gameDifLvl;

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
                int playerTypeMask = pFile.read(); // Считать один байт
                // Player Type (PT_UNDEFINED for new game)
                long playerType = pFile.read(); // Считать один байт
                // Create playerInfo descriptor
                iPlayerInfo playerInfo = new iPlayerInfo(
                                        playerId,
                                        playerTypeMask,
                                        playerType,
                                        nation
                );
                if (playerType == PT.UNDEFINED) {
                    if (!bHumanDefined && (playerTypeMask == PTM.HUMAN_ONLY || playerTypeMask == PTM.HUMAN_OR_COMPUTER)) {
                        bHumanDefined = true;
                        playerInfo.m_Type = PT.HUMAN;
                    } else {
                        playerInfo.m_Type = PT.COMPUTER;
                    }
                }
                // Player resources (undefined for new game)
                serialize.Unserialize(pFile, playerInfo.m_Minerals);
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
            int curPlayerId = (pFile.read() << 8) + pFile.read(); // Считать два байта
            if(curPlayerId == 0xFFFF)
                m_curPlayerId = PID.NEUTRAL;
            else
                m_curPlayerId = curPlayerId;

            // Map metrics
            int w = (pFile.read() << 8) + pFile.read(); // Считать два байта
            int h = (pFile.read() << 8) + pFile.read(); // Считать два байта
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
            Tracer.check(m_Players.get(pidx).m_Type != PT.UNDEFINED);

            if (m_Players.get(pidx).m_Type == PT.HUMAN) {
                defPl.add(m_Players.get(pidx));
                m_Players.remove(pidx);
            }
            else
                ++pidx;
        }
        // Продолжить, если PT_HUMAN существуют
        Tracer.check(defPl.size() > 0);
        // Если один PT_HUMAN, то игровой режим GM_SPLAYER, иначе GM_HOTSEAT
        m_gameMode = (defPl.size() == 1) ? GM.SPLAYER : GM.HOTSEAT;
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
            if(m_Players.get(xx).m_TypeMask == PTM.HUMAN_ONLY ||
               m_Players.get(xx).m_TypeMask == PTM.HUMAN_OR_COMPUTER)
                res++;
        return res;
    }


    /**
     * ???
     */
    boolean Supported() {
        if(OS.WINCE || (OS.WIN32 && DEV.VER)) {
            if(DEV.VER && m_encType == MET.MAPDES)
                return true;
            else
                return (m_encType == MET.PUBLIC) ||
                       (m_encType == MET.PRIVATE);
        }
        else
            return m_encType == MET.MAPDES;
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
    int m_gameMode;

    /**
     * Имя файла?
     */
    String m_FileName;

    /**
     * Уровень сложности
     */
    int m_Difficulty;

    /**
     * Список игроков?
     */
    ArrayList<iPlayerInfo> m_Players;

    /**
     * Размер карты
     */
    int m_Size;

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
    int m_curPlayerId;

    /**
     * Рандомное зерно?
     */
    long m_rseed;

    /**
     * Размер карты?
     */
    iSize m_metrics;
}