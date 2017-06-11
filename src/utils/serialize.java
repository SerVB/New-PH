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

package utils;

import Common.iArmy;
import Common.iArtifactList;
import Common.iCreatGroup;
import Common.iDynamicBuffer;
import Common.iFurtSkills;
import Common.iMineralSet;
import Common.metrics.iPoint;
import Common.iPrSkills;
import Common.iRewardItem;
import Common.iSecSkills;
import entries.iSecSkillEntry;
import Constants.*;
import collections.simple.iRewardsCtr;
import collections.extended.iSpellList;
import entries.iSpellEntry;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class serialize {

    // Point
    public static void Serialize(iDynamicBuffer dbuff, iPoint pos) {
        dbuff.Write(pos.x);
        dbuff.Write(pos.y);
    }

    public static void Unserialize(iDynamicBuffer dbuff, iPoint pos) {
        int x = (int) dbuff.Read();
        int y = (int) dbuff.Read();

        pos.x = x;
        pos.y = y;
    }

    public static void Unserialize(FileInputStream pFile, iPoint pos) {
        try {
            int x = (pFile.read() << 8) + pFile.read(); // Считать два байта
            int y = (pFile.read() << 8) + pFile.read(); // Считать два байта

            pos.x = x;
            pos.y = y;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Creature group and army
    public static void Serialize(iDynamicBuffer dbuff, iCreatGroup cg) {
        dbuff.Write(cg.Type());
        dbuff.Write(cg.Count());
    }

    public static void Unserialize(iDynamicBuffer dbuff, iCreatGroup cg) {
        int ct = (int)dbuff.Read();
        long cnt = dbuff.Read();

        cg.setType(ct);
        cg.setCount(cnt);
    }

    public static void Serialize(iDynamicBuffer dbuff, iArmy army) {
        for (int xx = 0; xx < ARMY.PLACES_COUNT; ++xx)
            Serialize(dbuff, army.At(xx));
    }

    public static void Unserialize(iDynamicBuffer dbuff, iArmy army) {
        for (int xx = 0; xx < ARMY.PLACES_COUNT; ++xx)
            Unserialize(dbuff, army.At(xx));
    }


    // String of text
    public static void Serialize(iDynamicBuffer dbuff, String text) {
        dbuff.Write(text);
    }

    public static void Unserialize(iDynamicBuffer dbuff, String text) {
        text = dbuff.ReadString();
    }

    public static void Unserialize(FileInputStream pFile, String text) {
        try {
            int len = (pFile.read() << 24) + (pFile.read() << 16) +
                      (pFile.read() << 8) + pFile.read(); // Считать четыре байта
            if ( len > 0 ) {
                char[] buff = new char[len];

                for (int idx = 0; idx < len; idx++) {
                    buff[idx] = (char) (pFile.read()); // Считать байт
                }

                text = String.valueOf(buff);
            } else {
                text = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Further skills
    public static void Serialize(iDynamicBuffer dbuff, iFurtSkills frSkills) {
        int cnt = 0;
        int xx;
        for (xx = 0; xx < FSK.COUNT; ++xx)
            if (frSkills.Value(xx) != 0)
                ++cnt;

        dbuff.Write(cnt);

        for (xx=0; xx < FSK.COUNT; ++xx) {
            int sk = xx ;
            if (frSkills.Value(sk) != 0) {
                dbuff.Write(xx);
                dbuff.Write(frSkills.Value(sk));
            }
        }
    }

    public static void Unserialize(iDynamicBuffer dbuff, iFurtSkills frSkills) {
        int cnt = (int) dbuff.Read();
        if (cnt <= 0)
            return;

        frSkills.Reset();

        while (cnt-- > 0) {
            int sk = (int) dbuff.Read();
            int mod = (int) dbuff.Read();

            tracer.check(frSkills.Value(sk) == 0);
            frSkills.SetValue(sk, mod);
        }
    }

    // Primary skills
    public static void Serialize(iDynamicBuffer dbuff, iPrSkills prSkills) {
        for (int xx = 0; xx < PRSKILL.COUNT; ++xx)
            dbuff.Write(prSkills.val[xx]);
    }

    public static void Unserialize(iDynamicBuffer dbuff, iPrSkills prSkills) {
        for (int xx = 0; xx < PRSKILL.COUNT; ++xx) {
            prSkills.val[xx] = dbuff.Read();
        }
    }

    // Mineral Set
    // NOTE: Minerals now must be encrypted to:
    // a) prevent modification
    // b) checking real values to detect protection
    public static void Serialize(iDynamicBuffer dbuff, iMineralSet minSet) {
        for (int xx=0; xx < iMineralSet.MINERAL_COUNT; ++xx)
            dbuff.Write(minSet.quant(xx));
    }

    public static void Unserialize(iDynamicBuffer dbuff, iMineralSet minSet) {
        for (int xx = 0; xx < iMineralSet.MINERAL_COUNT; ++xx) {
            minSet.setQuant(xx, dbuff.Read());
        }
    }


    public static void Unserialize(FileInputStream pFile, iMineralSet minSet) {
        try {
            System.err.println("Сколько возращает sizeof(minSet.quant[xx])? Пусть пока четыре байта.");

            for (int xx = 0; xx < iMineralSet.MINERAL_COUNT; ++xx) {
                    long quant = (pFile.read() << 24) + (pFile.read() << 16) +
                                 (pFile.read() << 8) + pFile.read(); // Считать четыре байта
                    minSet.setQuant(xx, quant);
            }
        } catch (IOException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Secondary Skills
    public static void Serialize(iDynamicBuffer dbuff, iSecSkills secSkills) {
        dbuff.Write(secSkills.GetSize());

        for (int ssid = 0; ssid < secSkills.GetSize(); ++ssid) {
            dbuff.Write(secSkills.get(ssid).m_skill);
            dbuff.Write(secSkills.get(ssid).m_level);
        }
    }

    public static void Unserialize(iDynamicBuffer dbuff, iSecSkills secSkills) {
        // ss = secondary skill
        int sscnt;
        int ssType, ssLevel;

        sscnt = (int)dbuff.Read();

        if (sscnt <= 0)
            return;

        secSkills.RemoveAll();

        for (int ssid = 0; ssid < sscnt; ++ssid) {
            ssType = (int) dbuff.Read();
            ssLevel = (int) dbuff.Read();
            secSkills.Add(new iSecSkillEntry(ssType, ssLevel));
        }
    }


    // Artifact list
    public static void Serialize(iDynamicBuffer dbuff, iArtifactList artifacts) {
        dbuff.Write(artifacts.Count());

        for (int artIdx = 0; artIdx < artifacts.Count(); ++artIdx) {
            dbuff.Write(artifacts.At(artIdx).id);
            dbuff.Write(artifacts.At(artIdx).assign);
        }
    }

    public static void Unserialize(iDynamicBuffer dbuff, iArtifactList artifacts) {
        long artCnt = dbuff.Read();

        while (artCnt-- > 0) {
            int artId = (int) dbuff.Read();
            int assign = (int) dbuff.Read();

            artifacts.Add(artId, assign);
        }
    }

    // Spells
    public static void Serialize(iDynamicBuffer dbuff, iSpellList spells) {
        dbuff.Write(spells.size());

        for (int spIdx = 0; spIdx < spells.size(); ++spIdx)
            dbuff.Write(spells.get(spIdx).id);
    }

    public static void Unserialize(iDynamicBuffer dbuff, iSpellList spells) {
        int spCnt = (int) dbuff.Read();

        if (spCnt <= 0)
            return;

        spells.clear();

        while (spCnt-- > 0) {
            int spId = (int) dbuff.Read();
            if (spId < MSP.COUNT)
                spells.add(new iSpellEntry(spId));
        }
    }

    // Rewards
    public static void Serialize(iDynamicBuffer dbuff, iRewardsCtr rewards) {
        int quant = rewards.size();

        dbuff.Write(quant);

        for (int xx = 0; xx < quant; ++xx) {
            dbuff.Write(rewards.get(xx).m_type);
            dbuff.Write(rewards.get(xx).m_fParam);
            dbuff.Write(rewards.get(xx).m_sParam);
        }
    }

    public static void Unserialize(iDynamicBuffer dbuff, iRewardsCtr rewards) {
        rewards.clear();

        int quant = (int) dbuff.Read();
        while (quant-- > 0) {
            int rtype = (int) dbuff.Read();

            int fparam = (int) dbuff.Read();
            int sparam = (int) dbuff.Read();

            rewards.add(new iRewardItem(rtype, fparam, sparam));
        }
    }
}
