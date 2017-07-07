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
package newph.core.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * Dynamic byte buffer.
 */
public class iDynamicBuffer extends iDynamicPtr<Byte> {
    
    /**
     * The default increment of the buffer on overflow.
     */
    private final static int DYNAMIC_BUFFER_INC = 512;

    /**
     * Constructs the empty buffer with the default parameters.
     */
    public iDynamicBuffer() {
        this(0);
    }
    
    /**
     * Constructs the empty buffer with the default parameters except the capacity.
     * @param cap The initial capacity.
     */
    public iDynamicBuffer(final int cap) {
        this(cap, DYNAMIC_BUFFER_INC);
    }
    
    /**
     * Constructs the empty buffer with the specified parameters.
     * @param cap   The initial capacity.
     * @param incr  The increment of the buffer on overflow.
     */
    public iDynamicBuffer(final int cap, final int incr) {
        m_Capacity    = cap;
        m_Incr        = 0 < incr ? incr : DYNAMIC_BUFFER_INC;
        m_Size        = 0;
        m_ReadOnly    = false;
        m_Data        = new ArrayList<>();
        if (m_Capacity != 0) { //new uint8[m_Capacity];
            for (int idx = 0; idx < m_Capacity; idx++) {
                m_Data.add((byte) 0);
            }
        }
    }

    /**
     * Constructs the buffer: copies the other buffer.
     * @param buf The other buffer to be copied.
     */
    public iDynamicBuffer(final iDynamicBuffer buf) {
        m_Pos       = buf.m_Pos;
        m_Capacity  = buf.m_Capacity;
        m_Size      = buf.m_Size;
        m_Incr      = buf.m_Incr;
        m_ReadOnly  = buf.m_ReadOnly;
        if (!m_ReadOnly && buf.m_Data != null && m_Capacity != 0) {
            m_Data = new ArrayList<>(buf.m_Data);
        } else {
            m_Data = buf.m_Data;
        }
    }
    
    /**
     * Constructs the buffer from the {@code data} list. The buffer is read/only.
     * @param data  The list.
     * @param len   The number of the necessary starting bytes in the list.
     */
    public iDynamicBuffer(final ArrayList<Byte> data, final int len) {
        this(data, len, true);
    }
    
    /**
     * Constructs the buffer from the {@code data} list.
     * @param data      The list.
     * @param len       The number of the necessary starting bytes in the list.
     * @param rdonly    If the buffer is read/only.
     */
    public iDynamicBuffer(final ArrayList<Byte> data, final int len, final boolean rdonly) {
        m_Pos      = 0;
        m_Capacity = len;
        m_Size     = len;
        m_Incr     = DYNAMIC_BUFFER_INC;
        m_ReadOnly = rdonly;
        if (!m_ReadOnly && data != null && m_Capacity != 0) {
            m_Data = new ArrayList<>(data);
        } else {
            m_Data = data;
        }
    }

    /**
     * Copies the other buffer.
     * (Ex. operator=)
     * @param buf The other buffer to be copied.
     */
    public void set(final iDynamicBuffer buf) {
        if (!m_ReadOnly) {
            m_Data.clear(); //delete[] m_Data;
        }
        m_Pos      = buf.m_Pos;
        m_Capacity = buf.m_Capacity;
        m_Size     = buf.m_Size;
        m_Incr     = buf.m_Incr;
        m_ReadOnly = buf.m_ReadOnly;
        if (!m_ReadOnly && buf.m_Data != null && m_Capacity != 0) {
            m_Data = new ArrayList<>(buf.m_Data);
        } else {
            m_Data = buf.m_Data;
        }
    }

    /**
     * Cleans the buffer.
     */
    public void Clean() {
        if ( !m_ReadOnly ) {
            m_Data.clear(); //delete[] m_Data;
        } 
        m_Data     = new ArrayList<>();
        m_Capacity = 0;
        m_Size     = 0;
        m_ReadOnly = false;
        Rewind();
    }

    /**
     * Cleans the buffer fast (w/o cleaning each byte, only resets the size).
     */
    public void Reset() {
        m_Size = 0;
        Rewind();
    }
    
    /**
     * Reinits the buffer (same as the constructor
     * {@link #iDynamicBuffer()}).
     */
    public void ReInit() {
        ReInit(0);
    }
    
    /**
     * Reinits the buffer (same as the constructor
     * {@link #iDynamicBuffer(int)}).
     * @param cap The initial capacity.
     */
    public void ReInit(final int cap) {
        ReInit(cap, DYNAMIC_BUFFER_INC);
    }
    
    /**
     * Reinits the buffer (same as the constructor
     * {@link #iDynamicBuffer(int, int)}).
     * @param cap   The initial capacity.
     * @param incr  The increment of the buffer on overflow.
     */
    public void ReInit(final int cap, final int incr) {
        Clean();
        m_Capacity = cap;
        for (int idx = 0; idx < m_Capacity; idx++) { //new uint8[m_Capacity];
            m_Data.add((byte) 0);
        }
        m_Incr = incr > 0 ? incr : DYNAMIC_BUFFER_INC;
        m_ReadOnly = false;
    }

    /**
     * Increments the size of the buffer:
     * if the incremented size doesn't reach the capacity,
     * the size increments (1),
     * else the size is set to the capacity (2).
     * @param offs The increment.
     * @return True if (1), false if (2).
     */
    public boolean IncSize(final int offs) {
        if (m_Capacity < m_Size + offs) {
            m_Size = m_Capacity;
            return false;
        } else {
            m_Size += offs;
            return true;
        }
    }

    /**
     * Increments the position of the buffer:
     * if the incremented position doesn't reach the capacity,
     * the position increments (1),
     * else the position is set to the capacity (2).
     * @param offs The increment.
     * @return The difference between the initial position
     * and the resulting position.
     */
    public int IncPos(final int offs) {
        int oldPos = m_Pos;
        if (m_Capacity < offs + m_Pos) {
            m_Pos = m_Capacity;
        } else {
            m_Pos += offs;
        }
        return m_Pos - oldPos;
    }

    /**
     * Seeks the position to the specified.
     * If the specified position is greater than the capacity,
     * the position seeks to the capacity (1),
     * else the position seeks to the specified position (2).
     * @param pos   The specified position.
     * @return      False if (1), true if (2).
     */
    public boolean Seek(final int pos) {
        if (m_Capacity < pos) {
            m_Pos = m_Capacity;
            return false;
        } else {
            m_Pos = pos;
            return true;
        }
    }

    /**
     * Updates the size of the buffer.
     * If the specified size is greater than the capacity,
     * the size sets to the capacity (1),
     * else the size sets to the specified size (2).
     * @param size  The specified size.
     * @return      False if (1), true if (2).
     */
    public boolean SetSize(final int size) {
        if (m_Capacity < size) {
            m_Size = m_Capacity;
            return false;
        } else {
            m_Size = size;
            return true;
        }
    }
    
    /* Appears only in the single file (this): */
//    public void SetData(final ArrayList<Byte> data, final int length) {
//        SetData(data, length, true);
//    }
//
//    /**
//     * @deprecated Check the code! The developer was asked.
//     * @param data
//     * @param length
//     * @param rdonly 
//     */
//    public void SetData(final ArrayList<Byte> data, final int length, final boolean rdonly) {
//        if (!m_ReadOnly) { 
//            data.clear(); //delete[] data; // <==???
//        }
//        if (!m_ReadOnly && data != null && length != 0) {
//            m_Data = malloc(length);
//            memcpy( m_Data, data, length );
//        } else  {
//            m_Data = data;
//        }
//        m_Capacity    = length;
//        m_Size        = length;
//        m_Incr        = DYNAMIC_BUFFER_INC;
//        m_ReadOnly    = rdonly;
//        Rewind();
//    }

    /**
     * Checks if the buffer is read/only.
     * @return True if the buffer is r/o, false if not.
     */
    public boolean IsReadOnly() {
        return m_ReadOnly;
    }

    /**
     * Returns the capacity of the buffer.
     * @return The capacity.
     */
    public int GetCapacity() {
        return m_Capacity;
    }

    /**
     * Returns the size of the buffer.
     * @return The size.
     */
    public int GetSize() {
        return m_Size;
    }

    /**
     * Returns the increment of the buffer.
     * @return The increment.
     */
    public int GetIncrement() {
        return m_Incr;
    }

    /**
     * Returns the data of the buffer.
     * @return The data.
     */
    public ArrayList<Byte> GetData() {
        return m_Data;
    }

    /**
     * Returns the current data of the buffer (sublisted data from the
     * current position to the end).
     * @return The current data.
     */
    public ArrayList<Byte> GetCurrentData() {
        return new ArrayList<>(m_Data.subList(m_Pos, m_Data.size()));
    }

    /**
     * Returns the current position of the buffer.
     * @return The current position.
     */
    public int GetCurrentPos() {
        return m_Pos;
    }

    /**
     * Writes the byte to the buffer, increments the position by one byte.
     * @param b The byte to be written.
     * @return  True if the writing is successful, false if not.
     */
    public boolean Write(final byte b) {
        final int SIZE = 1;
        
        final byte[] arPrim = ByteBuffer.allocate(SIZE).put(b).array();
        final ArrayList<Byte> al = new ArrayList<>();
        for (byte val : arPrim) {
            al.add(val);
        }
        
        return Write(al, 1, SIZE) > 0;
    }

    /**
     * Writes the short to the buffer, increments the position by two bytes.
     * @param s The short ("word") to be written.
     * @return  True if the writing is successful, false if not.
     */
    public boolean Write(final short s) {
        final int SIZE = 2;
        
        final byte[] arPrim = ByteBuffer.allocate(SIZE).putShort(s).array();
        final ArrayList<Byte> al = new ArrayList<>();
        for (byte val : arPrim) {
            al.add(val);
        }
        
        return Write(al, 1, SIZE) > 0;
    }

    /**
     * Writes the int to the buffer, increments the position by four bytes.
     * @param i The int ("double word") to be written.
     * @return  True if the writing is successful, false if not.
     */
    public boolean Write(final int i) {
        final int SIZE = 4;
        
        final byte[] arPrim = ByteBuffer.allocate(SIZE).putInt(i).array();
        final ArrayList<Byte> al = new ArrayList<>();
        for (byte val : arPrim) {
            al.add(val);
        }
        
        return Write(al, 1, SIZE) > 0;
    }

    /**
     * Writes the float to the buffer, increments the position by four bytes.
     * @param f The float to be written.
     * @return  True if the writing is successful, false if not.
     */
    public boolean Write(final float f) {
        final int SIZE = 4;
        
        final byte[] arPrim = ByteBuffer.allocate(SIZE).putFloat(f).array();
        final ArrayList<Byte> al = new ArrayList<>();
        for (byte val : arPrim) {
            al.add(val);
        }
        
        return Write(al, 1, SIZE) > 0;
    }

    /**
     * Writes the string to the buffer, increments the position by some bytes.
     * @param s The string to be written.
     * @return  True if the writing is successful, false if not.
     */
    public boolean Write(final String s) {
        boolean res;
        
        final int len = s.length();
        res = Write(len);
        
        if (len != 0) {
            for (int idx = 0; idx < len; idx++) {
                res &= Write((short)s.charAt(idx));
            }
        }
        
        return res;
    }
    
    /**
     * Writes the list to the buffer, increments the position by some bytes.
     * @param buffer    The list.
     * @param n         The number of first bytes in the list to write.
     * @return          0 if the writing wasn't successful,
     *                  {@code #n} if it was.
     */
    @Override
    public int Write(final ArrayList<Byte> buffer, final int n) {
        return Write(buffer, n, 1);
    }
    
    /**
     * Writes the list to the buffer, increments the position by some bytes.
     * @param buffer    The list.
     * @param n         The number of first elements in the list to write.
     * @param size      Size of one element in bytes.
     * @return          0 if the writing wasn't successful,
     *                  {@code #n} if it was.
     */
    @Override
    public int Write(final ArrayList<Byte> buffer, final int n, final int size) {
        final int len = n * size;
        if (buffer != null && len != 0 && !m_ReadOnly) {
            AddLength(len);
            if (super.Write(buffer, 0, len) == 1) {
                if (m_Size < m_Pos) {
                    m_Size = m_Pos;
                }
                return len / size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * Reads byte from the buffer, increments the position by one byte.
     * @return Read byte.
     */
    public byte readByte() {
        final int SIZE = 1;
        
        ArrayList<Byte> al = new ArrayList<>();
        Read(al, 1, SIZE);
        
        final byte b = al.get(0);
        
        return b;
    }

    /**
     * Reads short from the buffer, increments the position by two bytes.
     * @return Read short.
     */
    public short readShort() {
        final int SIZE = 2;
        
        ArrayList<Byte> al = new ArrayList<>();
        Read(al, 1, SIZE);
        
        final short w = (short) (
                ((0xFF & al.get(0)) << 8) +
                (0xFF & al.get(1))
        );
        
        return w;
    }

    /**
     * Reads int from the buffer, increments the position by four bytes.
     * @return Read int.
     */
    public int readInt() {
        final int SIZE = 4;
        
        ArrayList<Byte> al = new ArrayList<>();
        Read(al, 1, SIZE);
        
        final int d =
                ((0xFF & al.get(0)) << 24) +
                ((0xFF & al.get(1)) << 16) +
                ((0xFF & al.get(2)) << 8) +
                (0xFF & al.get(3));
        
        return d;
    }

    /**
     * Reads float from the buffer, increments the position by four bytes.
     * @return Read float.
     */
    public float readFloat() {
        final int SIZE = 4;
        
        ArrayList<Byte> al = new ArrayList<>();
        Read(al, 1, SIZE);
        
        byte[] bytes = {al.get(0), al.get(1), al.get(2), al.get(3)};
        float f = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
        
        return f;
    }

    /**
     * Reads string from the buffer, increments the position by some bytes.
     * @return Read string.
     */
    public String readString() {
        String s = "";
        
        final int len = readInt();
        if (len == 0 || m_Capacity - m_Pos < len) {
            return s;
        }
        
        for (int idx = 0; idx < len; idx++) {
            s += (char) readShort();
        }
        m_Pos += len * 2; // 2 is size of char
        

        return s;
    }

    /**
     * Reads list from the buffer, increments the position by some bytes.
     * @param buffer    List to be written.
     * @param n         Number of bytes to be written.
     * @return          True if the reading was successful, false if not.
     */
    @Override
    public boolean Read(ArrayList<Byte> buffer, final int n) {
        return Read(buffer, n, 1);
    }
    
    /**
     * Reads list from the buffer, increments the position by some bytes.
     * @param buffer    List to be written.
     * @param n         Number of elements to be written.
     * @param size      Size of element.
     * @return          True if the reading was successful, false if not.
     */
    @Override
    public boolean Read(ArrayList<Byte> buffer, final int n, final int size) {
        final int len = n * size;
        
        return 
                buffer != null &&
                m_Pos < m_Capacity &&
                len <= (m_Capacity - m_Pos) &&
                super.Read(buffer, 0, len);
    }

    /**
     * Adds the capacity.
     * @param ns New size.
     */
    public void AddLength(final int ns) {
        if (!m_ReadOnly && ((m_Capacity - m_Pos) < ns))  {
            final int newlength = ((m_Pos + ns + m_Incr - 1) / m_Incr) * m_Incr;
            
            m_Data.ensureCapacity(newlength);
            m_Capacity = newlength;
        }
    }

    /**
     * Checks if the position is at the end of the buffer.
     * @return True if it is, false if not.
     */
    public boolean IsEnd() {
        return m_Size <= m_Pos;
    }

    /**
     * The allocated capacity.
     */
    private int     m_Capacity;
    
    /**
     * The current useful size.
     */
    private int     m_Size;
    
    /**
     * The increment of the buffer on overflow.
     */
    private int     m_Incr;
    
    /**
     * If the buffer is read/only.
     */
    private boolean m_ReadOnly;
    
}
