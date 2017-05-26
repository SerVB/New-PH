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

package Common;

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
    void Serialize(iDynamicBuffer dbuff, iPoint pos) {
        dbuff.Write(pos.x);
        dbuff.Write(pos.y);
    }

    void Unserialize(iDynamicBuffer dbuff, iPoint pos) {
        long x = dbuff.Read();
        long y = dbuff.Read();

        pos.x=x;
        pos.y=y;
    }

    void Unserialize(FileInputStream pFile, iPoint pos) {
        try {
            long x = (pFile.read() << 8) + pFile.read(); // Считать два байта
            long y = (pFile.read() << 8) + pFile.read(); // Считать два байта

            pos.x = x;
            pos.y = y;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(serialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Creature group and army
    void Serialize(iDynamicBuffer dbuff, iCreatGroup cg) {
        dbuff.Write(cg.Type());
        dbuff.Write(cg.Count());
    }

    void Unserialize(iDynamicBuffer dbuff, iCreatGroup cg) {
        int ct = (int)dbuff.Read();
        long cnt = dbuff.Read();

        cg.setType(ct);
        cg.setCount(cnt);
    }

    void Serialize(iDynamicBuffer dbuff, iArmy army) {
        for (int xx = 0; xx < iArmy.ARMY_PLACES_COUNT; ++xx)
            Serialize(dbuff, army.At(xx));
    }

    void Unserialize(iDynamicBuffer dbuff, iArmy army) {
        for (int xx = 0; xx < iArmy.ARMY_PLACES_COUNT; ++xx)
            Unserialize(dbuff, army.At(xx));
    }


    // String of text
    void Serialize(iDynamicBuffer dbuff, String text) {
        dbuff.Write(text);
    }

    void Unserialize(iDynamicBuffer dbuff, String text) {
        text = dbuff.ReadString();
    }

    void Unserialize(FileInputStream pFile, String text) {
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
    void Serialize(iDynamicBuffer dbuff, iFurtSkills frSkills) {
        int cnt = 0;
        int xx;
        for (xx = 0; xx < FSK_COUNT; ++xx)
            if (frSkills.Value(xx))
                ++cnt;

        dbuff.Write(cnt);

        for (xx=0; xx < FSK_COUNT; ++xx) {
            int sk = xx ;
            if (frSkills.Value(sk)) {
                dbuff.Write(xx);
                dbuff.Write(frSkills.Value(sk));
            }
        }
    }

    void Unserialize(iDynamicBuffer dbuff, iFurtSkills frSkills) {
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
    void Serialize(iDynamicBuffer dbuff, iPrSkills prSkills) {
        for (int xx = 0; xx < PRSKILL_COUNT; ++xx)
            dbuff.Write(prSkills.val[xx]);
    }

    void Unserialize(iDynamicBuffer dbuff, iPrSkills prSkills) {
        for (int xx = 0; xx < PRSKILL_COUNT; ++xx) {
            prSkills.val[xx] = dbuff.Read();
        }
    }

    // Mineral Set
    // NOTE: Minerals now must be encrypted to:
    // a) prevent modification
    // b) checking real values to detect protection
    void Serialize(iDynamicBuffer dbuff, iMineralSet minSet) {
        for (int xx=0; xx < iMineralSet.MINERAL_COUNT; ++xx)
            dbuff.Write(minSet.quant(xx));
    }

    void Unserialize(iDynamicBuffer dbuff, iMineralSet minSet) {
        for (int xx = 0; xx < iMineralSet.MINERAL_COUNT; ++xx) {
            minSet.setQuant(xx, dbuff.Read());
        }
    }


    void Unserialize(FileInputStream pFile, iMineralSet minSet) {
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
    void Serialize(iDynamicBuffer dbuff, iSecSkills secSkills) {
        dbuff.Write(secSkills.GetSize());

        for (int ssid = 0; ssid < secSkills.GetSize(); ++ssid) {
            dbuff.Write(secSkills[ssid].m_skill);
            dbuff.Write(secSkills[ssid].m_level);
        }
    }

    void Unserialize(iDynamicBuffer dbuff, iSecSkills secSkills) {
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
    void Serialize(iDynamicBuffer dbuff, iArtifactList artifacts) {
        dbuff.Write(artifacts.Count());

        for (int artIdx = 0; artIdx < artifacts.Count(); ++artIdx) {
            dbuff.Write(artifacts.At(artIdx).id);
            dbuff.Write(artifacts.At(artIdx).assign);
        }
    }

    void Unserialize(iDynamicBuffer dbuff, iArtifactList artifacts) {
        long artCnt = dbuff.Read();

        while (artCnt-- > 0) {
            int artId = (int) dbuff.Read();
            int assign = (int) dbuff.Read();

            artifacts.Add(artId, assign);
        }
    }

    // Spells
    void Serialize(iDynamicBuffer dbuff, iSpellList spells) {
        dbuff.Write(spells.GetSize());

        for (int spIdx = 0; spIdx < spells.GetSize(); ++spIdx)
            dbuff.Write(spells.get(spIdx));
    }

    void Unserialize(iDynamicBuffer dbuff, iSpellList spells) {
        int spCnt = (int) dbuff.Read();

        if (spCnt <= 0)
            return;

        spells.RemoveAll();

        while (spCnt-- > 0) {
            int spId = (int) dbuff.Read();
            if (spId < cm_magic.MSP_COUNT)
                spells.Add(spId);
        }
    }

    // Rewards
    void Serialize(iDynamicBuffer dbuff, iRewardsCtr rewards) {
        int quant = rewards.GetSize();

        dbuff.Write(quant);

        for (int xx = 0; xx < quant; ++xx) {
            dbuff.Write(rewards.get(xx).m_type);
            dbuff.Write(rewards.get(xx).m_fParam);
            dbuff.Write(rewards.get(xx).m_sParam);
        }
    }

    void Unserialize(iDynamicBuffer dbuff, iRewardsCtr rewards) {
        rewards.RemoveAll();

        int quant = (int) dbuff.Read();
        while (quant-- > 0) {
            int rtype = (int) dbuff.Read();

            long fparam = dbuff.Read();
            long sparam = dbuff.Read();

            rewards.Add(new iRewardItem(rtype, fparam, sparam));
        }
    }
}
