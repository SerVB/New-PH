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
package newph.staticFunction;

import java.io.File;
import newph.constant.dib.COLOR;
import newph.dib.iDib;

/**
 * Static RGB functions.
 */
public final class RGB {

    /**
     * Returns int value of RGB16.
     * @param r Red color (0..255).
     * @param g Green color (0..255).
     * @param b Blue color (0..255).
     * @return Color code.
     */
    public final static int RGB16(
            final int r, final int g, final int b
    ) {
        return (r & 0xF8)<<8 | (g & 0xFC)<<3 | (b & 0xF8)>>3;
    }
    
    public final static int Darken50(final int pixel) {
        return (pixel & 0xf7de) >> 1;
    }

    public final static int Darken25(int pixel) {
        pixel = (pixel & 0xf7de) >> 1;
        pixel += (pixel & 0xf7de) >> 1;
        return pixel;
    }

    public final static int DarkenBWBlend(final int a, final int b) {
        return ( (a & 0xf7de) >> 1 ) + ( (b & 0xf7de) >> 1 );
    }

    public final static int DarkenBW(final int pixel) { 
//        static uint8 rpt = 31; // Useless line in sources???
        int chnl = (pixel >> 6) & 0x1f;
        return DarkenBWBlend(COLOR.BWPAL[chnl], 0x39e7 );
    } 
    
    public final static int BlendPixels(
            final int dst, final int src, final int a
    ) {
        final int inv_a = 255 - a;
        final int sr = a * ((src & (0x1F<<11)) >> 11);
        final int sg = a * ((src & (0x3F<<5)) >> 5);
        final int sb = a * ((src & 0x1F));
        final int dr = inv_a * ((dst & (0x1F<<11)) >> 11);
        final int dg = inv_a * ((dst & (0x3F<<5)) >> 5);
        final int db = inv_a * ((dst & 0x1F));
        return ((sr+dr)>>8)<<11 | ((sg+dg)>>8)<<5 | ((sb+db)>>8);
    }
    
    /**
     * Copies src array to dst array. (Blit function)
     * @param dst       Destination array of pixels.
     * @param dst_idx   Index of the first element to copy in dst.
     * @param src       Source array of pixels.
     * @param src_idx   Index of the first element to copy in src.
     * @param size      Elements to copy number.
     */
    public final static void BlitDibBlock_RGB(
            final int[] dst,
            final int dst_idx,
            final int[] src,
            final int src_idx,
            final int size
    ) {
        System.arraycopy(src, dst_idx, dst, src_idx, size);
//        memcpy(dst,src,size*sizeof(iDib.pixel));
    }
    
    /*
     * Save bitmap
     */
    public final static boolean SaveDibBitmap32(
            final iDib dib,
            final String fname
    ) {
        // Check this:
        // http://www.javaworld.com/article/2077561/learn-java/java-tip-60--saving-bitmap-files-in-java.html
        throw new UnsupportedOperationException("Can't make a bmp file");
        
        /*
        File pFile = new File(fname);
        if (pFile.canWrite()) {
            return false;
        }

        int pix_siz = dib.GetWidth() * dib.GetHeight();
        Tracer.check(pix_siz);
        final int[] buff = dib.GetPtr().getAr();

        // Prepare BITMAPFILEHEADER
        BITMAPFILEHEADER    bmfHeader;
        bmfHeader.bfType = ((WORD) ('M' << 8) | 'B');
        bmfHeader.bfReserved1 = 0;
        bmfHeader.bfReserved2 = 0;
        bmfHeader.bfSize = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER) + (dib.GetWidth() * dib.GetHeight() * 4);
        bmfHeader.bfOffBits = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER);

        // Prepare BITMAPINFOHEADER
        BITMAPINFOHEADER bmihdr;
        bmihdr.biSize = sizeof(BITMAPINFOHEADER);
        bmihdr.biWidth = dib.GetWidth();
        bmihdr.biHeight = -((sint32)dib.GetHeight());
        bmihdr.biPlanes = 1;
        bmihdr.biBitCount = 32;
        bmihdr.biCompression = BI_RGB;
        bmihdr.biSizeImage = 0;
        bmihdr.biXPelsPerMeter = 600;
        bmihdr.biYPelsPerMeter = 600;
        bmihdr.biClrUsed = 0;
        bmihdr.biClrImportant = 0;

        pFile.Write(&bmfHeader,sizeof(BITMAPFILEHEADER));
        pFile.Write(&bmihdr,sizeof(BITMAPINFOHEADER));

        for (uint32 yy=0; yy<dib.GetHeight(); ++yy) {
            for (uint32 xx=0; xx<dib.GetWidth(); ++xx) {
                uint32 clr = ((*buff)>>11)<<19 | (((*buff)>>5) & 0x3f)<<10 | ((*buff)&0x1f)<<3;
                pFile.Write(&clr,sizeof(clr));
                buff++;
            }
        }

        return true;
        */
    }
    
    public final static boolean SaveDibBitmap16(
            final iDib dib,
            final String fname
    ) {
        // Check this:
        // http://www.javaworld.com/article/2077561/learn-java/java-tip-60--saving-bitmap-files-in-java.html
        throw new UnsupportedOperationException("Can't make a bmp file");
        /*
        iFilePtr pFile = CreateWin32File(fname);
        if (!pFile) return false;

        uint32 pix_siz = dib.GetWidth()*dib.GetHeight();
        check(pix_siz);
        const iDib.pixel* buff = dib.GetPtr();

        // Prepare BITMAPFILEHEADER
        BITMAPFILEHEADER    bmfHeader;
        bmfHeader.bfType = ((WORD) ('M' << 8) | 'B');
        bmfHeader.bfReserved1 = 0;
        bmfHeader.bfReserved2 = 0;
        bmfHeader.bfSize = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER) + (dib.GetWidth() * dib.GetHeight() * 2);
        bmfHeader.bfOffBits = sizeof(BITMAPFILEHEADER) + sizeof(BITMAPINFOHEADER);

        // Prepare BITMAPINFOHEADER
        BITMAPINFOHEADER bmihdr;
        bmihdr.biSize = sizeof(BITMAPINFOHEADER);
        bmihdr.biWidth = dib.GetWidth();
        bmihdr.biHeight = -((sint32)dib.GetHeight());
        bmihdr.biPlanes = 1;
        bmihdr.biBitCount = 16;
        bmihdr.biCompression = BI_RGB;
        bmihdr.biSizeImage = 0;
        bmihdr.biXPelsPerMeter = 600;
        bmihdr.biYPelsPerMeter = 600;
        bmihdr.biClrUsed = 0;
        bmihdr.biClrImportant = 0;

        pFile.Write(&bmfHeader,sizeof(BITMAPFILEHEADER));
        pFile.Write(&bmihdr,sizeof(BITMAPINFOHEADER));

        uint16* obuff = new uint16[dib.GetWidth()];

        for (uint32 yy=0; yy<dib.GetHeight(); ++yy) {
            for (uint32 xx=0; xx<dib.GetWidth(); ++xx) {
                obuff[xx] = (*buff & 0x1F) | ((*buff & 0xFFC0)>>1);
                buff++;
            }
            pFile.Write(obuff,dib.GetWidth() * sizeof(uint16));
        }

        delete[] obuff;
        return true;
    }

    public final static int TintedShadow(final int pixel) { 
        //static uint8 rpt = 31;
        int chnl = (pixel >> 6) & 0x1f;
        return ((COLOR.BWPAL[chnl] & 0xf7de) >> 1) + ((0x39e7 & 0xf7de) >> 1);
    
    */
    } 
}
