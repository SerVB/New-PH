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

package utils;

import newph.core.staticFunction.Tracer;
import Common.iDynamicBuffer;

/**
 *
 */
public class lzo {

    final int LZO_BLOCK_HDR = 'L' | ('Z' << 8);
    final int LZO_MAX_BUF_LEN = 4 * 1024 * 1024;

    boolean Init() {
        return (lzo_init() == LZO_E_OK);
    }

    long Decompress(final byte[] lzoBuff, int lzoBuffLen, iDynamicBuffer rawBuff) {
        int lzoBuffIdx = 0;

        int hdr = lzoBuff[lzoBuffIdx] << 8 + lzoBuff[lzoBuffIdx + 1];
        lzoBuffLen -=  2;
        lzoBuffIdx += 2;
        if (hdr != LZO_BLOCK_HDR) {
            Tracer.check(0);
            return 0;
        }

        int raw_buf_len = lzoBuff[lzoBuffIdx]     << 24 + lzoBuff[lzoBuffIdx + 1] << 16 +
                          lzoBuff[lzoBuffIdx + 2] << 8  + lzoBuff[lzoBuffIdx + 3];
        lzoBuffLen -=  4;
        lzoBuffIdx += 4;
        if (raw_buf_len <= 0 || raw_buf_len > LZO_MAX_BUF_LEN) {
            Tracer.check(0);
            return 0;
        }

        rawBuff.ReInit(raw_buf_len);
        rawBuff.IncSize( raw_buf_len );
        int res = lzo1x_decompress(lzoBuff, lzoBuffLen, rawBuff.GetData(), raw_buf_len, null);
        if (res != LZO_E_OK) {
            Tracer.check(0);
            return 0;
        }
        
        return raw_buf_len;
    }

    int Compress(final byte[] rawBuff, int rawBuffLen, iDynamicBuffer lzoBuff) {
        Tracer.check(rawBuffLen > 0 && rawBuffLen <= LZO_MAX_BUF_LEN);
        int out_len = (rawBuffLen + rawBuffLen / 16 + 64 + 3);
        char[] buff = new char[out_len];

        // compress from `in' to `out' with LZO1X-1
        int res = lzo1x_1_compress(rawBuff,rawBuffLen,buff,out_len,wrkmem);
        if (res != LZO_E_OK) {
            // this should NEVER happen
            Tracer.check(0);
            return 0;
        }

        lzoBuff.Write(LZO_BLOCK_HDR);
        lzoBuff.Write(rawBuffLen);
        lzoBuff.Write(buff, out_len);
        return lzoBuff.GetSize();
    }




//#define HEAP_ALLOC(var,size) lzo_align_t __LZO_MMODEL var [ ((size) + (sizeof(lzo_align_t) - 1)) / sizeof(lzo_align_t) ]
//static HEAP_ALLOC(wrkmem,LZO1X_1_MEM_COMPRESS);


}
