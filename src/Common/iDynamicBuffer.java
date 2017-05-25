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

package Common;

import java.nio.ByteBuffer;

/**
 *
 */
public class iDynamicBuffer {
    /**
     *
     */
    ByteBuffer bb;

    /**
     * Конструктор.
     * @param size размер буфера в байтах.
     */
    public iDynamicBuffer(int size) {
        bb = ByteBuffer.allocate(size);
    }

    /**
     * Помещает long в буфер.
     * @param value помещаемый long.
     */
    public void Write(long value) {
        bb.putLong(value);
    }

    /**
     * Помещает строку в буфер.
     * @param str помещаемая строка.
     */
    public void Write(String str) {
        for(char ch : str.toCharArray())
            bb.putChar(ch);
    }

    /**
     * Возвращает long из буфера.
     * @return запрошенный long.
     */
    public long Read() {
        return bb.getLong();
    }

    /**
     * Возвращает строку из буфера.
     * @return запрошенную строку.
     */
    public String ReadString() {
        char ch;
        String s = "";
        while((ch = bb.getChar()) != 0)
            s += ch;
        return s;
    }
}
