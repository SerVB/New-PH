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
package newph.dib;

import Common.metrics.iRect;
import Common.metrics.iSize;
import helperFunction.MathOperations;
import helperFunction.iClipper;
import newph.constant.dib.COLOR;
import newph.constant.dib.COLOR_MASK;
import newph.finalant.dib.COLOR_TYPE;
import newph.memory.iBuff;
import newph.metric.iPoint;
import newph.servb.Changeable;
import newph.staticFunction.RGB;
import newph.staticFunction.Tracer;

/**
 * Contains BMP image.
 */
public class iDib {

    /**
     * Buffer contains pixels.
     */
    private iBuff<Integer>  m_RGB;
    
    /**
     * Type of pixels.
     */
    private int             m_dibType;
    
    /**
     * Size of image.
     */
    private iSize           m_Siz;

    /**
     * Constructs the empty object with the default pixel type (RGB16).
     */
    public iDib() {
        this.m_dibType = COLOR_TYPE.RGB;
    }
    
    /**
     * Constructs the object: copies the other object.
     * @param dib The other object.
     */
    public iDib(final iDib dib) {
        Init(dib);
    }
    
    /**
     * Constructs the object: copies the rectangle of the other object.
     * @param dib   The other object.
     * @param rect  The rectangle.
     */
    public iDib(final iDib dib, final iRect rect) {
        Init(dib, rect);
    }
    
    /**
     * Constructs the object with known Size and pixel type.
     * @param siz   Size.
     * @param dType Pixel type.
     */
    public iDib(final iSize siz, final int dType) {
        Init(siz, dType);
    }
    
    /**
     * Initializes the image: copies the other object.
     * @param dib The other object.
     */
    public final void Init(final iDib dib) {
        m_dibType = dib.GetType();
        Allocate(dib);
    }
    
    /**
     * Initializes the image: copies the rectangle of the other object.
     * @param dib   The other object.
     * @param rect  The rectangle.
     */
    public final void Init(final iDib dib, final iRect rect) {
        Allocate(rect.getSize());
        
        m_dibType = dib.m_dibType;
        int src_ptr = dib.m_Siz.w*rect.y + rect.x;
        int dst_ptr = 0;
        
        for (int yy=0; yy<rect.h; ++yy){
            System.arraycopy(dib.GetPtr(), src_ptr, this.GetPtr(), dst_ptr, rect.w);
//            memcpy(dst_ptr,src_ptr,rect.w*sizeof(pixel));
            dst_ptr += rect.w;
            src_ptr += dib.m_Siz.w;
        }
    }
    
    /**
     * Initializes the blank image.
     * @param siz   Image size.
     * @param dType Type of pixels.
     */
    public final void Init(final iSize siz, final int dType) {
        m_dibType = dType;
        Allocate(siz);
    }
    
    /**
     * Resets the image.
     */
    public void Cleanup() {
        m_RGB.Clean();
        m_Siz.toZero();
        m_dibType = COLOR_TYPE.RGB;
    }
    
    /**
     * Resizes the image.
     * @param siz 
     */
    public void Resize(final iSize siz) {
        Cleanup();
        Allocate(siz);
    }

    /**
     * (Ex. operator=.)
     * @param other Other object.
     */
    public final void set(final iDib other) {
        iDib clone = new iDib(other);
        Swap(clone);
    }
    
    /**
     * Checks if the position is valid.
     * @param x "x" coordinate of the position.
     * @param y "y" coordinate of the position.
     * @return True if the position is valid, false if not.
     */
    public final boolean IsValidPos(final int x, final int y) {
        return  0 <= x && x <  m_Siz.w &&
                0 <= y && y <  m_Siz.h;
    }

    /**
     * Puts pixel to the specified position if the position is valid.
     * @param x     "x" coordinate of the position.
     * @param y     "y" coordinate of the position.
     * @param color Pixel color.
     */
    public final void PutPixelSafe(final int x, final int y, final int color) {
        if (IsValidPos(x, y)) {
            PutPixel(x, y, color);
        }
    }

    /**
     * Puts pixel to the specified position.
     * @deprecated  Use {@link #PutPixelSafe(int, int, int)} instead.
     * TODO: Make private and remove deprecated status.
     * @param x     "x" coordinate of the position.
     * @param y     "y" coordinate of the position.
     * @param color Pixel color.
     */
    public final void PutPixel(final int x, final int y, final int color) {
        m_RGB.set(y*m_Siz.w + x, color);
    }

    /**
     * Puts pixel to the specified position if the position is valid.
     * @param pos   Position.
     * @param color Pixel color.
     */
    public final void PutPixelSafe(final iPoint pos, final int color) {
        PutPixelSafe(pos.x, pos.y, color);
    }

    /**
     * Puts pixel to the specified position.
     * @deprecated  Use {@link #PutPixelSafe(newph.metric.iPoint, int)} instead.
     * @param pos   Position.
     * @param color Pixel color.
     */
    public final void PutPixel(final iPoint pos, final int color) {
        PutPixel(pos.x, pos.y, color);
    }

    /**
     * Returns the pixel of the image at the specified position.
     * @param x The "x" coordinate of the position.
     * @param y The "y" coordinate of the position.
     * @return  Pixel code.
     */
    public final int GetPixel(final int x, final int y) {
        return m_RGB.get(y*m_Siz.w + x);
    }

    /**
     * Returns the pixel of the image at the specified position.
     * @param pos   Position.
     * @return      Pixel code.
     */
    public final int GetPixel(final iPoint pos) {
        return GetPixel(pos.x, pos.y);
    }

    /**
     * Returns buffer length in bytes.
     * @deprecated Strange method, I don't like it.
     * TODO: Remove the method.
     * @return Buffer length.
     */
    public final int GetBuffLen() {
        return m_Siz.w * m_Siz.h * 4;
    }

    /**
     * Returns pointer to the pixel at the specified position.
     * @deprecated Pointers are not available in Java!
     * TODO: Remove the method, create "setPixel" method.
     * @param pos Position.
     * @return    Nothing.
     */
    public final int GetPtr(final iPoint pos) {
        throw new UnsupportedOperationException("Pointers are not available in Java!");
//        return m_RGB + (pos.y*m_Siz.w)+pos.x;
    }

    /**
     * Returns image container.
     * @deprecated Low level method.
     * TODO: Remove the method, create "setPixel" method.
     * @return Image container.
     */
    public final iBuff<Integer> GetPtr() {
        return m_RGB;
    }

    /**
     * Returns type of the image pixels.
     * @return Type of pixel.
     */
    public final int GetType() {
        return m_dibType;
    }

    /**
     * Checks if the picture metrics are equal to zero.
     * @return True if size is zero, false if not.
     */
    public final boolean IsEmpty() {
        return m_Siz.isZero();
    }

    /**
     * Returns Size of the image.
     * @return Size.
     */
    public final iSize GetSize() {
        return m_Siz;
    }

    /**
     * Returns width of the image.
     * @return Width.
     */
    public final int GetWidth() {
        return m_Siz.w;
    }

    /**
     * Returns height of the image.
     * @return Height.
     */
    public final int GetHeight() {
        return m_Siz.h;
    }
    
    /**
     * Fades all the image.
     * @param alpha Alpha.
     */
    public void Fade(final int alpha) {
        if (alpha == 0){
            Fill(COLOR.cColor_Black);
        } else if (alpha != 255) {
            Fill(COLOR.cColor_Black, 255 - alpha);
        }
    }
    
    /**
     * Fills all the image. Alpha = 255.
     * @param color Color to fill.
     */
    public void Fill(final int color) {
        Fill(color, 255);
    }
    
    /**
     * Fills all the image.
     * @param color Color to fill.
     * @param alpha Alpha of color.
     */
    public void Fill(final int color, final int alpha) {
        if (alpha==255) {
            FillDibBlock(m_RGB, color, m_Siz.w*m_Siz.h);
        } else {
            FillDibBlockAlpha(m_RGB, color, alpha, m_Siz.w*m_Siz.h);
        }
    }
    
    /**
     * Fills the specified rectangle. Alpha = 255.
     * @param rc    Rectangle.
     * @param color Color.
     */
    public void FillRect(final iRect rc, final int color) {
        FillRect(rc, color, 255);
    }
    
    /**
     * Fills the specified rectangle.
     * @param rc    Rectangle.
     * @param color Color.
     * @param alpha Alpha.
     */
    public void FillRect(final iRect rc, final int color, final int alpha) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int dstPtr = drect.y*m_Siz.w+drect.x;
        int h = drect.h;
        while (h-- > 0) {
            if (alpha==255) {
                FillDibBlock(dstPtr, color, drect.w);
            } else {
                FillDibBlockAlpha(dstPtr, color, alpha, drect.w);
            }
            dstPtr += m_Siz.w;
        }
    }
    
    /**
     * Darken the specified rectangle.
     * @param rc Rectangle.
     */
    public void DarkenBWRect(final iRect rc) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int dstPtr = drect.y*m_Siz.w+drect.x;
        int h = drect.h;
        while (h-- > 0) {
            int pptr = dstPtr;
            
            for (int xx = 0; xx < drect.w; ++xx) {
                m_RGB.set(pptr, RGB.TintedShadow(m_RGB.get(pptr)));
                pptr++;
            }
            
            dstPtr += m_Siz.w;
        }
    }
    
    /**
     * Darken the specified rectangle.
     * @param rc Rectangle.
     */
    public void Darken50Rect(final iRect rc) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int dstPtr = drect.y*m_Siz.w+drect.x;
        int h = drect.h;
        while (h-- > 0) {
            int pptr = dstPtr;
            for (int xx = 0; xx < drect.w; ++xx) {
                m_RGB.set(pptr, RGB.Darken50(m_RGB.get(pptr)));
                pptr++;
            }
            dstPtr += m_Siz.w;
        }
    }
    
    /**
     * Darken the specified rectangle.
     * @param rc Rectangle.
     */
    public void Darken25Rect(final iRect rc) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) return;
        int dstPtr = drect.y*m_Siz.w+drect.x;
        int h = drect.h;
        while (h-- > 0) {
            int pptr = dstPtr;
            for (int xx=0; xx<drect.w; ++xx) {
                m_RGB.set(pptr, RGB.Darken25(m_RGB.get(pptr)));
                ++pptr;
            }
            dstPtr += m_Siz.w;
        }
    }

    /**
     * Horizontally gradients the specified rectangle.
     * @param rc Rectangle.
     * @param c1 Color 1.
     * @param c2 Color 2.
     */
    public void HGradientRect(final iRect rc, final int c1, final int c2) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect,iRect.sizeToRect(m_Siz))) {
            return;
        }
        int dstPtr = drect.y*m_Siz.w+drect.x;
        int tw = rc.w - 1;
        if (tw <= 0){
            return;
        }

        int[] gr = new int[320];
        int pgr = 0;
        int tp = drect.x - rc.x;
        int cnt = Math.min(drect.w, 320);
        int w = cnt;
        while (w-- > 0) {
            int dr = ((c2&0xF800)>>11) - ((c1&0xF800)>>11);
            int nr = MathOperations.clamp(0,0x1F,  ((c1&0xF800)>>11) + ((dr*tp)/tw));
            int dg = ((c2&0x7E0)>>5) - ((c1&0x7E0)>>5);
            int ng = MathOperations.clamp(0,0x3F,  ((c1&0x7E0)>>5) + ((dg*tp)/tw));
            int db = (c2&0x1F) - (c1&0x1F);
            int nb = MathOperations.clamp(0,0x1F,  (c1&0x1F) + ((db*tp)/tw));
            gr[pgr] = (nr<<11)|(ng<<5)|nb;
            pgr++;
            tp++;
        }

        int h = drect.h;
        while (h-- > 0) { // Sets gradient to each line.
//            memcpy(dstPtr, gr, cnt * sizeof(pixel));
            System.arraycopy(gr, 0, m_RGB, dstPtr, cnt);
            dstPtr += m_Siz.w;
        }
    }

    /**
     * Vertically gradients the specified rectangle.
     * @param rc Rectangle.
     * @param c1 Color 1.
     * @param c2 Color 2.
     */
    public void VGradientRect(final iRect rc, final int c1, final int c2) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int dstPtr = drect.y*m_Siz.w + drect.x;
        int th = rc.h - 1;
        if (th <= 0){
            FillDibBlock(dstPtr, c1, drect.w);
            return;
        }
        int tp = drect.y-rc.y;
        int h = drect.h;
        while (h-- > 0) {
            int dr = ((c2&0xF800)>>11) - ((c1&0xF800)>>11);
            int nr  = MathOperations.clamp(0,0x1F,  ((c1&0xF800)>>11) + ((dr*tp)/th));
            int dg = ((c2&0x7E0)>>5) - ((c1&0x7E0)>>5);
            int ng  = MathOperations.clamp(0,0x3F,  ((c1&0x7E0)>>5) + ((dg*tp)/th));
            int db = (c2&0x1F) - (c1&0x1F);
            int nb  = MathOperations.clamp(0,0x1F,  (c1&0x1F) + ((db*tp)/th));
            FillDibBlock(dstPtr, (nr<<11) | (ng<<5) | nb , drect.w);
            dstPtr += m_Siz.w;
            tp++;
        }
    }

    /**
     * Draws a frame. (Alpha = 255)
     * @param rc    Rectangle of frame.
     * @param clr   Frame color.
     */
    public void FrameRect(final iRect rc, final int clr) {
        FrameRect(rc, clr, 255);
    }

    /**
     * Draws a frame.
     * @param rc    Rectangle of frame.
     * @param clr   Frame color.
     * @param a     Color alpha.
     */
    public void FrameRect(final iRect rc, final int clr, final int a) {
        iRect drect = new iRect(rc);
        if (!iClipper.clipRect(drect, iRect.sizeToRect(m_Siz))) {
            return;
        }

        HLine(rc.getTopLeft(),    rc.x2(), clr, a);
        HLine(rc.getBottomLeft(), rc.x2(), clr, a);
        VLine(rc.getTopLeft(),    rc.y2(), clr, a);
        VLine(rc.getTopRight(),   rc.y2(), clr, a);
    }
    
    /**
     * Draws a horizontal line. (Alpha = 255)
     * @param pos Position of line start.
     * @param x2  The "x" coordinate of line end.
     * @param clr Color.
     */
    public void HLine(final iPoint pos, final int x2, final int clr) {
        HLine(pos, x2, clr, 255);
    }
    
    /**
     * Draws a horizontal line.
     * @param pos Position of line start.
     * @param x2  The "x" coordinate of line end.
     * @param clr Color.
     * @param a   Alpha.
     */
    public void HLine(final iPoint pos, final int x2, final int clr, final int a) {
        iPoint dpos1 = new iPoint(Math.min(pos.x, x2), pos.y);
        Changeable<Integer> dx2 = new Changeable(Math.max(pos.x,x2));
        if (!iClipper.clipHLine(dpos1, dx2, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int len = dx2.value - dpos1.x + 1;
        int dstPtr = dpos1.y*m_Siz.w + dpos1.x;
        if (a==255) {
            FillDibBlock(dstPtr, clr, len);
        } else {
            FillDibBlockAlpha(dstPtr, clr, a, len);
        }
    }
    
    /**
     * Draws a vertical line. (Alpha = 255)
     * @param pos Position of line start.
     * @param y2  The "y" coordinate of line end.
     * @param clr Color.
     */
    public void VLine(final iPoint pos, final int y2, final int clr) {
        VLine(pos, y2, clr, 255);
    }
    
    /**
     * Draws a vertical line.
     * @param pos Position of line start.
     * @param y2  The "y" coordinate of line end.
     * @param clr Color.
     * @param a   Alpha.
     */
    public void VLine(final iPoint pos, final int y2, final int clr, final int a) {
        iPoint dpos1 = new iPoint(pos.x, Math.min(pos.y,y2));
        Changeable<Integer> dy2 = new Changeable(Math.max(pos.y, y2));
        if (!iClipper.clipVLine(dpos1, dy2, iRect.sizeToRect(m_Siz))) {
            return;
        }
        int len = dy2.value - dpos1.y + 1;
        int dstPtr = dpos1.y*m_Siz.w + dpos1.x;
        int h = dy2.value - dpos1.y;
        while (h-- > 0) {
            if (a==255) m_RGB.set(dstPtr, clr);
            else {
                SetDibPixelAlpha(dstPtr,clr,a);
            }
            dstPtr += m_Siz.w;
        }
    }
    
    /**
     * Draws a line.
     * @param p1  Point 1.
     * @param p2  Point 2.
     * @param clr Color.
     */
    public void Line(final iPoint p1, final iPoint p2, final int clr) {
        Line(p1, p2, clr, 255);
    }
    
    /**
     * Draws a line.
     * @param p1  Point 1.
     * @param p2  Point 2.
     * @param clr Color.
     * @param a   Alpha.
     */
    public void Line(final iPoint p1, final iPoint p2, final int clr, final int a) {
        iPoint p = new iPoint(p1);
        iPoint d = new iPoint(
                Math.abs(p2.x - p1.x),
                Math.abs(p2.y - p1.y)
        );
        iPoint s = new iPoint(
                MathOperations.sign(p2.x - p1.x ),
                MathOperations.sign(p2.y - p1.y)
        );
        int d_, d1, d2;
        if ( d.y <= d.x ) {
            d_    = d.y * 2 - d.x;
            d1    = d.y * 2;
            d2    = ( d.y - d.x ) * 2;
            PutPixel(p, clr);
            p = new iPoint(p1.x + s.x, p1.y);
            for (int i = 0; i < d.x; i++) {
                if ( d_ > 0 ){
                    d_    += d2;
                    p.y    += s.y;
                } else {
                    d_    += d1;
                }
                PutPixel(p, clr);
                p.x += s.x;
            }
        } else {
            d_    = d.x * 2 - d.y;
            d1    = d.x * 2;
            d2    = ( d.x - d.y ) * 2;
            PutPixel(p, clr);
            p    = new iPoint( p1.x, p1.y + s.y );
            for(int i = 0; i < d.y; i++) {
                if ( d_ > 0 ) {
                    d_    += d2;
                    p.x    += s.x;
                } else {
                    d_    += d1;
                }
                PutPixel(p, clr);
                p.y += s.y;
            }
        }
    }

    /**
     * Draws triangle.
     * @param p1    Point 1.
     * @param p2    Point 2.
     * @param p3    Point 3.
     * @param clr   Color.
     */
    public void Triangle(
            final iPoint p1,
            final iPoint p2,
            final iPoint p3,
            final int clr
    ) {
        iPoint s, e;
        int dx1, dx2, dx3;
        if (p2.y-p1.y > 0) dx1=(p2.x-p1.x)/(p2.y-p1.y); else dx1=0;
        if (p3.y-p1.y > 0) dx2=(p3.x-p1.x)/(p2.y-p1.y); else dx2=0;
        if (p3.y-p2.y > 0) dx3=(p3.x-p2.x)/(p3.y-p2.y); else dx3=0;

        s = e = p1;
        if(dx1 > dx2) {
            for(;s.y<=p2.y;s.y++,e.y++,s.x+=dx2,e.x+=dx1)
                HLine(new iPoint(s.x,s.y), e.x, clr);
            e = p2;
            for(;s.y<=p3.y;s.y++,e.y++,s.x+=dx2,e.x+=dx3)
                HLine(new iPoint(s.x,s.y), e.x, clr);
        } else {
            for(;s.y<=p2.y;s.y++,e.y++,s.x+=dx1,e.x+=dx2)
                HLine(new iPoint(s.x,s.y), e.x, clr);
            s=p2;
            for(;s.y<=p3.y;s.y++,e.y++,s.x+=dx3,e.x+=dx2)
                HLine(new iPoint(s.x,s.y), e.x, clr);
        }
    }

    // Copy/Blend
    public void CopyToDibXY(final iDib dib, final iPoint pos, final int a) {
        if ((pos.x + m_Siz.w) <= 0 || (pos.y + m_Siz.w) <= 0) {
            return;
        }

        Tracer.check(m_dibType == COLOR_TYPE.RGB);
        iRect src_rect = new iRect(GetSize());
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (!iClipper.iClipRectRect(
                dst_rect,
                new iRect(
                        0,
                        0,
                        dib.GetWidth(),
                        dib.GetHeight()
                ),
                src_rect,
                iRect.sizeToRect(GetSize())
        )) {
            return;
        }

        int src_clr = src_rect.y*m_Siz.w + src_rect.x;
        int dst_clr = dst_rect.y*dib.GetWidth() + dst_rect.x;

        for (int yy = 0; yy < dst_rect.h; yy++) {
            BlitDibBlockAlpha(dib.GetPtr(), dst_clr, this.GetPtr(), src_clr, a, dst_rect.w);
            src_clr += m_Siz.w;
            dst_clr += dib.GetWidth();
        }
    }
    
    public void CopyToDibXY(final iDib dib, final iPoint pos) {
        if ((pos.x + m_Siz.w) <= 0 || (pos.y + m_Siz.w) <= 0) {
            return;
        }

        iRect src_rect = new iRect(GetSize());
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (!iClipper.iClipRectRect(
                dst_rect,
                new iRect(
                        0,
                        0,
                        dib.GetWidth(),
                        dib.GetHeight()
                ),
                src_rect,
                iRect.sizeToRect(GetSize())
        )) {
            return;
        }

        int src_clr = src_rect.y*m_Siz.w + src_rect.x;
        int dst_clr = dst_rect.y*dib.GetWidth() + dst_rect.x;

        for (int yy = 0; yy < dst_rect.h; yy++) {
            if (m_dibType == COLOR_TYPE.RGB) {
                RGB.BlitDibBlock_RGB(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                );
            } else if (m_dibType == COLOR_TYPE.RGBA) {
                RGB.BlitDibBlock_RGBA(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                );
            } else if (m_dibType == COLOR_TYPE.RGBCK) {
                RGB.BlitDibBlock_RGBCK(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                );
            }
            src_clr += m_Siz.w;
            dst_clr += dib.GetWidth();
        }
    }
    
    public void CopyRectToDibXY(
            final iDib dib,
            final iRect srect,
            final iPoint pos
    ) {
        CopyRectToDibXY(dib, srect, pos, 255);
    }
    
    public void CopyRectToDibXY(
            final iDib dib,
            final iRect srect,
            final iPoint pos,
            final int a
    ) {
        iRect src_rect = new iRect(srect);
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (!iClipper.iClipRectRect(
                dst_rect,
                iRect.sizeToRect(dib.GetSize()),
                src_rect,
                iRect.sizeToRect(GetSize())
        )) {
            return;
        }

        int src_clr = src_rect.y*m_Siz.w + src_rect.x;
        int dst_clr = dst_rect.y*dib.GetWidth() + dst_rect.x;

        if ( a == 255 ) {
            for (int yy = 0; yy < dst_rect.h; yy++) {
                if (m_dibType == COLOR_TYPE.RGB) {
                    RGB.BlitDibBlock_RGB(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                    );
                } else if (m_dibType == COLOR_TYPE.RGBA) {
                    RGB.BlitDibBlock_RGBA(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                    );
                } else if (m_dibType == COLOR_TYPE.RGBCK) {
                    RGB.BlitDibBlock_RGBCK(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        dst_rect.w
                    );
                }
                src_clr += m_Siz.w;
                dst_clr += dib.GetWidth();
            }
        } else {
            for (int yy = 0; yy < dst_rect.h; yy++) {
                RGB.BlitDibBlockAlpha(
                        dib.GetPtr().getAr(),
                        dst_clr,
                        this.GetPtr().getAr(),
                        src_clr,
                        a,
                        dst_rect.w
                    );
                src_clr += m_Siz.w;
                dst_clr += dib.GetWidth();
            }
        }
    }
    
    /**
     * 
     * @param dib
     * @param pos
     * @param ck    Color.
     * @param bval 
     */
    public void BlendToDibXY(
            final iDib dib,
            final iPoint pos,
            final int ck,
            final int bval
    ) {
        if ( (pos.x + m_Siz.w) <= 0 || (pos.y + m_Siz.w) <= 0) {
            return;
        }

        iRect src_rect = new iRect(GetSize());
        iSize siz = new iSize(dib.GetWidth() - pos.x, dib.GetHeight() - pos.y);
        iRect dst_rect = new iRect(pos, siz);
        if (!iClipper.iClipRectRect(
                dst_rect,
                new iRect(
                        0,
                        0,
                        dib.GetWidth(),
                        dib.GetHeight()
                ),
                src_rect,
                iRect.sizeToRect(GetSize())
        )) {
            return;
        }

        int src_clr = src_rect.y*m_Siz.w + src_rect.x;        // this
        int dst_clr = dst_rect.y*dib.GetWidth() + dst_rect.x; // other

        for (int yy = 0; yy < dst_rect.h; ++yy) {
            int scl = src_clr;
            int dcl = dst_clr;
            for (int xx = 0; xx < dst_rect.w; ++xx) {
                int colorAtScl = this.m_RGB.at(scl);
                if (colorAtScl != ck) {
                    int inv_a = 255 - bval;
                    int sr = bval  * ((colorAtScl & COLOR_MASK.RED  [COLOR_TYPE.RGB]) >> 11);
                    int sg = bval  * ((colorAtScl & COLOR_MASK.GREEN[COLOR_TYPE.RGB]) >> 5);
                    int sb = bval  * ((colorAtScl & COLOR_MASK.BLUE [COLOR_TYPE.RGB]));
                    int dr = inv_a * ((colorAtScl & COLOR_MASK.RED  [COLOR_TYPE.RGB]) >> 11);
                    int dg = inv_a * ((colorAtScl & COLOR_MASK.GREEN[COLOR_TYPE.RGB]) >> 5);
                    int db = inv_a * ((colorAtScl & COLOR_MASK.BLUE [COLOR_TYPE.RGB]));
                    dib.m_RGB.set(dcl, ((sr+dr)>>8)<<11 | ((sg+dg)>>8)<<5 | ((sb+db)>>8));
                }
                scl++;
                dcl++;
            }
            //if (m_dibType == RGB) BlitDibBlock_RGB(dst_clr,src_clr,dst_rect.w);
            //else if (m_dibType        == RGBA) BlitDibBlock_RGBA(dst_clr,src_clr,dst_rect.w);
            //else if (m_dibType == RGBCK) BlitDibBlock_RGBCK(dst_clr,src_clr,dst_rect.w);
            src_clr += m_Siz.w;
            dst_clr += dib.GetWidth();
        }
    }

    /* ifndef UNDER_CE */
//    public void BlitToDCXY(
//            HDC hdc,
//            final iPoint& pos,
//            bool bDoubleSize=false
//    ) {
//        uint32 bi_siz = sizeof ( BITMAPINFOHEADER ) + 3*sizeof(DWORD);
//        BITMAPINFO bi;
//        memset(&bi,0,sizeof(BITMAPINFOHEADER));
//        DWORD* bmask = (DWORD*)bi.bmiColors;
//        bmask[0] = 0x1F<<11;
//        bmask[1] = 0x3F<<5;
//        bmask[2] = 0x1F;
//        bi.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
//        bi.bmiHeader.biWidth = m_Siz.w;
//        bi.bmiHeader.biHeight = -(sint32)m_Siz.h;
//        bi.bmiHeader.biPlanes = 1;
//        bi.bmiHeader.biBitCount = 16; 
//        bi.bmiHeader.biCompression = BI_BITFIELDS;
//        bi.bmiHeader.biSizeImage = m_Siz.w * m_Siz.h * 2;
//        if (bDoubleSize) {
//            StretchDIBits(hdc,pos.x,pos.y,m_Siz.w*2,m_Siz.h*2,0,0,m_Siz.w,m_Siz.h,m_RGB,&bi,DIB_RGB_COLORS,SRCCOPY);
//        } else {
//            SetDIBitsToDevice(hdc,pos.x,pos.y,m_Siz.w,m_Siz.h,0,0,0,m_Siz.h,m_RGB,&bi,DIB_RGB_COLORS);
//        }
//    }

    private void Swap(final iDib other) {
        iBuff<Integer> tmp_buff = new iBuff(m_RGB);
        m_RGB.Allocate(other.m_RGB);
        other.m_RGB.Allocate(tmp_buff);

//        iSwap(m_Siz, other.m_Siz);
        final iSize tmpSize = this.m_Siz;
        this.m_Siz = other.m_Siz;
        other.m_Siz = tmpSize;
        
//        iSwap(m_dibType, other.m_dibType);
        final int tmpDibType = this.m_dibType;
        this.m_dibType = other.m_dibType;
        other.m_dibType = tmpDibType;
    }
    
    private void Allocate(final iSize siz) {
        Tracer.check(m_RGB.IsClean());
        
        m_Siz = siz;
        m_RGB.Allocate(siz.w*siz.h);
    }
    
    private void Allocate(final iDib dib) {
        Tracer.check(m_RGB.IsClean());
        
        m_Siz = dib.GetSize();
        m_RGB.Allocate(dib.m_RGB.GetPtr(), dib.m_Siz.w*dib.m_Siz.h);
    }
    
}
