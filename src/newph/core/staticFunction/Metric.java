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

import newph.core.metric.iRect;
import newph.core.metric.iSize;

/**
 * Metrics static functions.
 * @author SerVB
 * @since "GitHub new sources"
 */
public class Metric {
    
    /**
     * Scales specified size according to another (preserve aspect ratio).
     * Changes metrics of Size to be scaled.
     * @param sizeToScale   Size to be scaled.
     * @param sizeWdestAr   Size with given (destination) aspect ratio.
     * @return              False if the scaled Size's at least one metric is zero,
     *                      true if not.
     */
    public boolean ScaleSize(final iSize sizeToScale, final iSize sizeWdestAr) {
        double srcAspect = sizeToScale.getAspectRatio();
        double dstAspect = sizeWdestAr.getAspectRatio();

        // test weather dstAspect is bigger than srcAspect (video)
        if (srcAspect < dstAspect) {
            // correct width
            sizeToScale.w = Math.min((int)(sizeWdestAr.h * srcAspect), sizeWdestAr.h);
            sizeToScale.h = sizeWdestAr.h;
        } else {
            // correct height;
            sizeToScale.w = sizeWdestAr.w;
            sizeToScale.h = Math.min((int)(sizeWdestAr.w / srcAspect), sizeWdestAr.w);

        }

        return sizeToScale.w > 0 && sizeToScale.h > 0;
    }

    /**
     * Scale specified Rect to another circumscribed Rect (preserve aspect ratio).
     * Changes out Rect.
     * @param src
     * @param dst
     * @param out
     * @return 
     */
    public boolean ScaleRect2Rect(final iRect src, final iRect dst, final iRect out) {
        out.set(src);
        if (!ScaleSize(new iSize(out), new iSize(dst))) {
            return false;
        }
        out.x = dst.w/2 - out.w/2;
        out.y = dst.h/2 - out.h/2;
        return true;
    }
    
}
