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

import newph.core.metric.iSize;

/**
 * Metrics static functions.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class Metric {
    
    /// Scale specified size according to another (preserve aspect ratio)
    public boolean ScaleSize(final iSize src_siz, final iSize bbox) {
        float srcAspect = src_siz.GetAspectRatio();
        float bbxAspect = bbox.GetAspectRatio();

        // test weather wndAspect is bigger than video
        if ( bbxAspect > srcAspect ) {
            // correct width
            src_siz.w = iMIN((uint32)((float)(bbox.h) * srcAspect),bbox.h);
            src_siz.h = bbox.h;
        } else {
            // correct height;
            src_siz.w = bbox.w;
            src_siz.h = iMIN((uint32)((float)(bbox.w) / srcAspect),bbox.w);

        }

        return (src_siz.w && src_siz.h);
    }

    /// Scale specified rect to another circumscribed rect (preserve aspect ratio)
    public inline bool ScaleRect2Rect(const iRect& src, const iRect& dst, iRect& out)
    {
        out = src;
        if (!ScaleSize(out,dst)) return false;
        out.x = dst.w/2 - out.w/2;
        out.y = dst.h/2 - out.h/2;
        return true;
    }
    
}
