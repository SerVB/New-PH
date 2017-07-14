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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import newph.core.dib.iDib;
import newph.core.enumeration.ColorType;
import newph.core.metric.iSize;

/**
 * Helper class to manipulate with images.
 * @author SerVB
 * @since PPH 0
 */
public final class ImageInOut {
    
    public static BufferedImage imageFromDib(final iDib dib) {
        switch (dib.GetType()) {
            case RGB:
                return imageFromDib(dib, BufferedImage.TYPE_USHORT_565_RGB);
            default:
                throw new UnsupportedOperationException("Unknown ImageType: " + dib.GetType().toString());
        }
    }
    
    private static BufferedImage imageFromDib(final iDib dib, final int biType) {
        final int width  = dib.GetWidth();
        final int height = dib.GetHeight();
        BufferedImage imageOut = new BufferedImage(
                width, 
                height, 
                biType
        );
        
        int[] imageInPixels = dib.getCopiedPixels();
        imageOut.setRGB(0, 0, width, height, imageInPixels, 0, width);
        
        return imageOut;
    }
    
    public static iDib dibFromImage(final String pathToFile) {
        File bmpFile = new File(pathToFile);
        
        iDib res = null;
        
        try {
            BufferedImage image = ImageIO.read(bmpFile);
            
            final int w = image.getWidth();
            final int h = image.getHeight();
            
            final ColorType ct;
            switch (image.getType()) {
                case BufferedImage.TYPE_USHORT_565_RGB:
                    ct = ColorType.RGB;
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown BufferedImageType: " + image.getType());
            }
            
            res = new iDib(new iSize(w, h), ct);
            for (int i = 0; i < w * h; i++) {
                res.setPixelAt(
                        i,
                        image.getRGB(i % w, i / w)
                );
            }
        } catch (IOException ex) {
            Logger.getLogger(ImageInOut.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
}
