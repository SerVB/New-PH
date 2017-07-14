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

package newph.core.staticFunction;

import newph.core.dib.iDib;
import newph.core.metric.iPoint;
import newph.core.metric.iSize;

/**
 * Helper functions to transform images.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class iDibTransform {

    public static void Rotate(final iDib src, final iDib dst, final int angle) {
    //    check(src.GetSize() == dst.GetSize());
    //    check(src.GetType() == dst.GetType());
        Tracer.check(angle < 256);

        double rad      = (2 * Math.PI * angle) / 256.0;
        double deg      = (angle * 360) / 256.0;
        double cosine   = Math.cos(rad);
        double sine     = Math.sin(rad);
        int srcw        = src.GetWidth();
        int srch        = src.GetHeight();

        int x1 = (int)(-srch * sine);
        int y1 = (int)( srch * cosine);
        int x2 = (int)( srcw * cosine - srch * sine);
        int y2 = (int)( srch * cosine + srcw * sine);
        int x3 = (int)( srcw * cosine);
        int y3 = (int)( srcw * sine);

        int minx = Math.min(0,Math.min(x1, Math.min(x2,x3)));
        int miny = Math.min(0,Math.min(y1, Math.min(y2,y3)));
        int maxx = Math.max(0,Math.max(x1, Math.max(x2,x3)));
        int maxy = Math.max(0,Math.max(y1, Math.max(y2,y3)));

        int w = maxx - minx;
        int h = maxy - miny;
        dst.Init(new iSize(w, h), src.GetType());

    /*
        WCHAR buff[128] = {0};
        wsprintf(buff,L"Angle: %.2f deg --- (%d,%d), %d,%d,%d -- %d,%d,%d\n",deg,w,h,x1,x2,x3,y1,y2,y3);
        ODS(buff);
    */
//        iDib.pixel* dst_ptr = dst.GetPtr();
        int dst_ptr = 0;
        for( int y = 0; y < h; y++ ){
            for( int x = 0; x < w; x++ ){
                int sourcex = (int)((x+minx)*cosine + (y+miny)*sine);
                int sourcey = (int)((y+miny)*cosine - (x+minx)*sine);
                if( sourcex >= 0 && sourcex < srcw && sourcey >= 0 && sourcey < srch ){
                    dst.setPixelAt(
                            dst_ptr,
                            src.GetPixel(new iPoint(sourcex, sourcey))
                    );
                } else {
                    dst.setPixelAt(dst_ptr, 0);
                }
                dst_ptr++;
            }
        }
    }
    
    public static void FastStretch(final iDib src, final iDib dst, final int boffs) {
        int pDst = 0;
        for (int yy = 0; yy < dst.GetHeight(); ++yy){
            int pSrc = src.GetPtr(new iPoint(0, yy << boffs));
            for (int xx = 0; xx < dst.GetWidth(); ++xx) {
                dst.setPixelAt(pDst, src.getPixelAt(pSrc));
                pSrc += 1 << boffs;
                pDst ++;
            }
        }
    }
    
    public static void PyramidalStretch(final iDib src, final iDib dst, final int boffs) {
        int pDst = 0;
        for (int yy = 0; yy < dst.GetHeight(); ++yy){
            int pSrc = src.GetPtr(new iPoint(0, yy << boffs));
            for (int xx=0; xx<dst.GetWidth(); ++xx) {
                int rval = 0;
                int gval = 0;
                int bval = 0;
                for (int cy = 0; cy < (1<<boffs); ++cy){
                    for (int cx = 0; cx < (1<<boffs); ++cx){
                        int pix = src.getPixelAt(pSrc + cy*src.GetWidth() + cx);
                        rval += (pix & (0x1F<<11))>>11;
                        gval += (pix & (0x3F<<5))>>5;
                        bval += pix & (0x1F);
                    }
                }
                dst.setPixelAt(
                        pDst,
                        ((rval>>(boffs<<1)) << 11) | ((gval>>(boffs<<1)) << 5) | (bval>>(boffs<<1))
                );
                pDst++;
                pSrc += (1<<boffs);
            }
        }
    }
    
}
