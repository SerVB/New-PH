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
package helperFunction;

import Common.iArmy;
import Common.iCreatGroup;
import Common.iFormat;
import Common.iSpellFilter;
import Constants.*;
import Constants.item.QUNTITY;
import collections.simple.iSpellList;
import entries.iSpellEntry;
import java.io.File;
import utils.tracer;

/**
 *
 */
public class Misc {

    public static int InitialTroopQuantity(int ct) {
        int minVal = QUNTITY.INITIAL[CREAT.DESC[ct].level-1].minVal / CREAT.DESC[ct].pidx;
        int maxVal = QUNTITY.INITIAL[CREAT.DESC[ct].level-1].maxVal / CREAT.DESC[ct].pidx;
        return  minVal + gGame.Map().Rand(maxVal-minVal);
    }

    public static void InitCreatGroup(iCreatGroup cg) {
        if (cg.IsValid() && cg.Count() == RANDOM.QUANTITY) {
            cg.setCount(InitialTroopQuantity(cg.Type()));
        }
    }

    public static void InitArmy(iArmy army) {
        for (int xx = 0; xx < 7; ++xx) {
            InitCreatGroup(army.At(xx));
        }
    }

    public static int SelectSpells(iSpellList spIds, iSpellFilter filter, int count, int reqSpell) {
        tracer.check(count > 0 && spIds.isEmpty());

        int[] tsp = new int[MSP.COUNT];
        int tsp_cnt = 0;
        for (int xx = 0; xx < MSP.COUNT; ++xx) {
            if (
                    ((filter.typeMask   & (1<<SPELL.DESCRIPTORS[xx].type  )) != 0) &&
                    ((filter.levelMask  & (1<<SPELL.DESCRIPTORS[xx].level )) != 0) &&
                    ((filter.schoolMask & (1<<SPELL.DESCRIPTORS[xx].school)) != 0) &&
                    xx != reqSpell
            ) {
                tsp[tsp_cnt++] = xx;
            }
        }


        if (reqSpell != MSP.INVALID) {
            count--;
        }
        while (count > 0) {
            int rval = gGame.Map().Rand(tsp_cnt);
            spIds.add(new iSpellEntry(tsp[rval]));
            tsp[rval] = tsp[--tsp_cnt];
            count--;
        }

        if (reqSpell != MSP.INVALID) {
            spIds.add(gGame.Map().Rand(spIds.size()+1), reqSpell);
        }

        tracer.check(count == 0);

        return spIds.size();
    }

    public static int CalcRandValue(final int[] pVals, int siz) {
        tracer.check(siz>0);

        int tot_vals = 0;

        for (int xx=0; xx<siz; ++xx) {
            tot_vals += pVals[xx];
        }

        if (tot_vals == 0) {
            return -1;
        }

        int rval = gGame.Map().Rand(tot_vals);

        int ridx = 0;
        int sval = pVals[ridx];
        while (sval<=rval) {
            ridx++;
            sval += pVals[ridx];
        }

        tracer.check(pVals[ridx] != 0);

        return ridx;
    }

    public static int InitialExpPts() {
        return 50 + gGame.Map().Rand(100);
    }

    public static String FormatDate(int timestamp, boolean bShowTime) {
        FILETIME ft;
        SYSTEMTIME st;
        LONGLONG ll;
        ll = Int32x32To64(timestamp, 600000000);
        ft.dwLowDateTime = (DWORD)ll;
        ft.dwHighDateTime = ll >> 32;
        FileTimeToSystemTime(&ft, &st);
        if (bShowTime) {
            return iFormat.format(
                    "%d %s %d (%d:%02d)",
                    st.wDay,
                    gTextMgr[TRID.MONTH_JAN+st.wMonth-1],
                    st.wYear,
                    st.wHour,
                    st.wMinute
            );
        } else {
            return iFormat.format(
                    "%d %s %d",
                    st.wDay,
                    gTextMgr[TRID.MONTH_JAN+st.wMonth-1],
                    st.wYear
            );
        }
    }

    // helper to save game to a file
    public static boolean SaveGameFile( final String fname, iGameWorld gmap ) {
        // Check and create save directory (if not exists)
        String saveDir = gSavePath.Left(gSavePath.Length()-1);
        boolean dirIsOk = new File(saveDir).exists();
        if ( !dirIsOk ) {
            dirIsOk = new File(saveDir).mkdirs();
        }
        if (!dirIsOk) {
            DWORD dwErr = GetLastError();
            iTextDlg tdlg = new iTextDlg(
                    gApp.ViewMgr(),
                    "",
                    iFormat.format(
                            "Unable to create '%s' folder: ErrorCode: 0x%08X",
                            saveDir.CStr(),
                            dwErr
                    ),
                    PID.NEUTRAL
            );
            tdlg.DoModal();
        }

        // normally we should create it in the save directory
        String tempSaveName = gSavePath + "tempsave.tmp";
        iFilePtr pFile;
        if (HMM.COMPOVERSION != 0) {
            pFile = new iFilePtr( xxc.CreateXFile( tempSaveName, HMM.COMPOCODE ) );
        } else {
            pFile = new iFilePtr( CreateWin32File( tempSaveName ) );
        }

        if ( pFile == null ) {
            DWORD dwErr = GetLastError();
            iTextDlg dlg = new iTextDlg(
                    gApp.ViewMgr(),
                    "Failure",
                    iFormat.format(
                            "Unable to save [%s], ErrorCode: 0x%08X",
                            fname.CStr(),
                            dwErr
                    ),
                    PID.NEUTRAL
            );
            dlg.DoModal();
            return false;
        }

        if ( !gGame.Map().SaveToFile( pFile.get() ) ) {
            iTextDlg dlg = new iTextDlg(
                    gApp.ViewMgr(),
                    "Failure",
                    "Unable to save game!",
                    PID.NEUTRAL
            );
            dlg.DoModal();
            return false;
        }
        // close file
        pFile.reset();
        // rename file to the destination
        new File(fname).delete();
        new File(tempSaveName).renameTo(new File(fname));
        new File(tempSaveName).delete();
        return true;
    }

}
