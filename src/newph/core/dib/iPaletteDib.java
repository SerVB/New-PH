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

package newph.core.dib;

import newph.core.memory.iBuffColor;
import newph.core.metric.iPoint;
import newph.core.staticFunction.Tracer;

/**
 * iPalette Dib i.e. 8bit dib with palette.
 * @author SerVB
 * @since "GitHub new sources"
 */
public final class iPaletteDib {


    private iBuffColor m_RGB;
    private iSize      m_Siz;

    public iPaletteDib() {}
    
    public iPaletteDib(final iSize siz) {
        Init(siz);
    }
    
    public void Init(final iSize siz) {
        Allocate(siz);
    }
    
    public void CopyToDibXY(
            final iDib dib,
            final iPoint pos,
            final iPalette pal
    ) {
        CopyToDibXY(dib, pos, pal, 255);
    }
    
    public void CopyToDibXY(
            final iDib dib,
            final iPoint pos,
            final iPalette pal,
            final int alpha
    ) {
        if ( (pos.x + m_Siz.w) <= 0 || (pos.y + m_Siz.w) <= 0) {
            return;
        }
        iRect src_rect = new iRect(GetSize());
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (
                !iClipper.iClipRectRect(
                        dst_rect,
                        new iRect(
                                0,
                                0,
                                dib.GetWidth(),
                                dib.GetHeight()
                        ),
                        src_rect,
                        iRect.sizeToRect(GetSize())
                )
        ) {
            return;
        }
        int src_clr = src_rect.y*m_Siz.w + src_rect.x;        // this
        int dst_clr = dst_rect.y*dib.GetWidth() + dst_rect.x; // other
        for (int yy = 0; yy < dst_rect.h; yy++) {
            for (int xx = 0; xx < dst_rect.w; ++xx) {
                if (this.m_RGB.at(src_clr + xx) != 0) {
                    if (alpha == 255) {
                        dib.GetPtr().set(dst_clr + xx, pal.GetPtr()[src_clr + xx]);
                    } else {
                        SetDibPixelAlpha(dst_clr[xx], pal[src_clr[xx]],alpha);
                    }
                }
            }
            src_clr += m_Siz.w;
            dst_clr += dib.GetWidth();
        }
    }
    
    public void CopyRectToDibXY(
            final iDib dib,
            final iRect srect,
            final iPoint pos,
            final iPalette pal
    ) {
        CopyRectToDibXY(dib, srect, pos, pal, 255);
    }
    
    public void CopyRectToDibXY(
            final iDib dib,
            final iRect srect,
            final iPoint pos,
            final iPalette pal,
            final int alpha
    ) {
        iRect src_rect = new iRect(srect);
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (
                !iClipper.iClipRectRect(
                        dst_rect,
                        iRect.sizeToRect(dib.GetSize()),
                        src_rect,
                        iRect.sizeToRect(GetSize())
                )
        ) {
            return;
        }
        const uint8* src_clr = m_RGB+src_rect.y*m_Siz.w+src_rect.x;
        uint16* dst_clr=dib.GetPtr()+dst_rect.y*dib.GetWidth()+dst_rect.x;

        for (uint32 yy=0; yy<dst_rect.h; yy++) {
            for (uint32 xx=0; xx<dst_rect.w; ++xx) {
                if (src_clr[xx] && pal[src_clr[xx]] != RGB16(0xF0,0,0xF0)) {
                    if (alpha == 255) dst_clr[xx] = pal[src_clr[xx]];
                    else SetDibPixelAlpha(&dst_clr[xx], pal[src_clr[xx]],alpha);
                }
            }
            src_clr+=m_Siz.w;
            dst_clr+=dib.GetWidth();
        }
    }

    /**
     * Returns buffer length in bytes.
     * @deprecated Strange method, I don't like it.
     * TODO: Remove the method.
     * @return Buffer length.
     */
    public int GetBuffLen() {
        return m_Siz.w * m_Siz.h * 1;
    }

    /**
     * Returns image container.
     * @deprecated Low level method.
     * TODO: Remove the method, create "setPixel" method.
     * @return Image container
     */
    public iBuffColor GetPtr() {
        return m_RGB;
    }

    public boolean IsEmpty() {
        return m_Siz.isZero();
    }

    public iSize GetSize() {
        return m_Siz;
    }

    public int GetWidth() {
        return m_Siz.w;
    }

    public int GetHeight() {
        return m_Siz.h;
    }

    private void Allocate(final iSize siz) {
        Tracer.check(m_RGB.IsClean());
        m_Siz = siz;
        m_RGB.Allocate(siz.w*siz.h);
    }
    
    private void Cleanup() {
        m_RGB.Clean();
        m_Siz.toZero();
    }
    
}
