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

/**
 * ???
 */
class iGameMap extends iMapT<iCell> {

    /**
     * Получить поверхность клетки
     */
    public boolean GetCellSurf(iPoint pos, iSurfCell cell) {
        cell.reset();
        iCell surf = super.GetAt(pos.x, pos.y);

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