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

package newph.core.constant;

/**
 * File public final constants.
 *
 * @author  SerVB
 * @since   "GitHub new sources"
 */
public class FILE {

    /**
     * Prevents from creating the instance of the class.
     */
    private FILE() {}

    public static final int EMAP_FILE_HDR_KEY = 0x76235278;
    public static final int EMAP_FILE_VERSION = 0x19;

    public static final int GMAP_FILE_HDR_KEY = 'G' | ('M' << 8) | ('A' << 16) | ('P' << 24);
    public static final int GMAP_FILE_VERSION = 0x39;

    public static final int GOBJ_FILE_HDR_KEY = 'G' | ('O' << 8) | ('B' << 16) | ('J' << 24);
    public static final int GOBJ_FILE_VERSION = 0x07;

    public static final int GFNT_FILE_HDR_KEY = 'G' | ('F' << 8) | ('N' << 16) | ('T' << 24);
    public static final int GFNT_FILE_VERSION = 0x01;

}
