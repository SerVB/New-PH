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

import java.util.ArrayList;
import java.util.Objects;
import newph.core.staticFunction.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * DynamicBuffer Test.
 * @author SerVB
 */
public class iDynamicBufferTest {

    /**
     * Test of set method, of class iDynamicBuffer.
     */
    @Test
    public void testSet() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.set(iDynamicBuffer)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            instance.Rewind();
            
            assertEquals(b0, instance.readByte());
            
            assertEquals(b1, instance.readByte());
            
            assertEquals(b2, instance.readByte());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.set(iDynamicBuffer)", "end.");
    }

    /**
     * Test of Clean method, of class iDynamicBuffer.
     */
    @Test
    public void testClean() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.clean()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            instance.Clean();
            
            assertEquals(0, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.clean()", "end.");
    }

    /**
     * Test of Reset method, of class iDynamicBuffer.
     */
    @Test
    public void testReset() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reset()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            instance.Reset();
            
            assertEquals(0, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reset()", "end.");
    }

    /**
     * Test of ReInit method, of class iDynamicBuffer.
     */
    @Test
    public void testReInit_0args() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            instance.ReInit();
            
            assertEquals(0, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit()", "end.");
    }

    /**
     * Test of ReInit method, of class iDynamicBuffer.
     */
    @Test
    public void testReInit_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit(int)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3, false);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            final int SIZE = 10;
            
            instance.ReInit(SIZE);
            
            assertEquals(0, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit(int)", "end.");
    }

    /**
     * Test of ReInit method, of class iDynamicBuffer.
     */
    @Test
    public void testReInit_int_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit(int, int)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(3, instance.GetSize());
            
            final int SIZE = 10;
            
            instance.ReInit(SIZE, 12);
            
            assertEquals(0, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.reInit(int, int)", "end.");
    }

    /**
     * Test of IncSize method, of class iDynamicBuffer.
     */
    @Test
    public void testIncSize() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IncSize(int)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al, al.size(), false);
            
            assertEquals(al.size(), instance.GetSize());
            
            final int SIZE = 10;
            
            instance.AddLength(SIZE);
            
            instance.IncSize(SIZE);
            
            assertEquals(SIZE + al.size(), instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IncSize(int)", "end.");
    }

    /**
     * Test of IncPos method, of class iDynamicBuffer.
     */
    @Test
    public void testIncPos() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IncPos(int)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al, al.size());
            
            assertEquals(al.size(), instance.GetSize());
            
            instance.IncPos(1);
            
            assertEquals(b1, instance.readByte());
            
            instance.IncPos(-1);
            
            assertEquals(b1, instance.readByte());
            
            instance.IncPos(-2);
            
            assertEquals(b0, instance.readByte());
            
            instance.IncPos(1);
            
            assertEquals(b2, instance.readByte());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IncPos(int)", "end.");
    }

    /**
     * Test of Seek method, of class iDynamicBuffer.
     */
    @Test
    public void testSeek() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.seek(int)", "begin");
        
        {
            ArrayList<Byte> al = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al, al.size());
            
            assertEquals(al.size(), instance.GetSize());
            
            instance.Seek(2);
            
            assertEquals(b2, instance.readByte());
            
            instance.Seek(1);
            
            assertEquals(b1, instance.readByte());
            
            instance.Seek(0);
            
            assertEquals(b0, instance.readByte());
            
            instance.Seek(1);
            
            assertEquals(b1, instance.readByte());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.seek(int)", "end.");
    }

    /**
     * Test of SetSize method, of class iDynamicBuffer.
     */
    @Test
    public void testSetSize() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.SetSize(int)", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al, al.size());
            
            assertEquals(al.size(), instance.GetSize());
            
            instance.SetSize(10);
            
            assertEquals(al.size(), instance.GetSize());
            
            instance.SetSize(1);
            
            assertEquals(1, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.SetSize(int)", "end.");
    }

    /**
     * Test of IsReadOnly method, of class iDynamicBuffer.
     */
    @Test
    public void testIsReadOnly() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IsReadOnly()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al, 3, true);
            
            assertEquals(true, instance.IsReadOnly());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IsReadOnly()", "end.");
    }

    /**
     * Test of GetCapacity method, of class iDynamicBuffer.
     */
    @Test
    public void testGetCapacity() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCapacity()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(al.size(), instance.GetCapacity());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCapacity()", "end.");
    }

    /**
     * Test of GetSize method, of class iDynamicBuffer.
     */
    @Test
    public void testGetSize() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCapacity()", "begin");
        
        {
            ArrayList<Byte> al      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al.add(b0);
            al.add(b1);
            al.add(b2);
            
            iDynamicBuffer buf      = new iDynamicBuffer(al, 3);
            iDynamicBuffer instance = new iDynamicBuffer();
            instance.set(buf);
            
            assertEquals(al.size(), instance.GetCapacity());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCapacity()", "end.");
    }

    /**
     * Test of GetIncrement method, of class iDynamicBuffer.
     */
    @Test
    public void testGetIncrement() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetIncrement()", "begin");
        
        {
            iDynamicBuffer instance = new iDynamicBuffer(4, 5);
            
            assertEquals(5, instance.GetIncrement());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetIncrement()", "end.");
    }

    /**
     * Test of GetData method, of class iDynamicBuffer.
     */
    @Test
    public void testGetData() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetData()", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3);
            
            ArrayList<Byte> al1 = new ArrayList<>(instance.GetData());
            
            assertTrue(
                    al0.size() == al1.size() &&
                    Objects.equals(al0.get(0), al1.get(0)) &&
                    Objects.equals(al0.get(1), al1.get(1)) &&
                    Objects.equals(al0.get(2), al1.get(2)) 
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetData()", "end.");
    }

    /**
     * Test of GetCurrentData method, of class iDynamicBuffer.
     */
    @Test
    public void testGetCurrentData() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCurrentData()", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3);
            
            instance.readByte();
            
            ArrayList<Byte> al1 = new ArrayList<>(instance.GetCurrentData());
            
            assertTrue(
                    al0.size() == al1.size() + 1 &&
                    Objects.equals(al0.get(1), al1.get(0)) &&
                    Objects.equals(al0.get(2), al1.get(1))
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCurrentData()", "end.");
    }

    /**
     * Test of GetCurrentPos method, of class iDynamicBuffer.
     */
    @Test
    public void testGetCurrentPos() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCurrentPos()", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3);
            
            instance.readByte();
            
            assertEquals(1, instance.GetCurrentPos());
            
            instance.readByte();
            
            assertEquals(2, instance.GetCurrentPos());
            
            instance.readByte();
            
            assertEquals(3, instance.GetCurrentPos());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.GetCurrentPos()", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_byte() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(byte)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            final byte b = 0x03;
            
            instance.Write(b);
            
            instance.Rewind();
            
            assertEquals(b, instance.readByte());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(byte)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_short() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(short)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            final short w = 0x03_04;
            
            instance.Write(w);
            
            instance.Rewind();
            
            assertEquals(w, instance.readShort());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(short)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(int)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            final int dw = 0x03_04_05_06;
            
            instance.Write(dw);
            
            instance.Rewind();
            
            final int a = instance.readInt();
            
            assertEquals(dw, a);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(int)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_float() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(float)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            final float f = 3.5f;
            
            instance.Write(f);
            
            instance.Rewind();
            
            assertEquals(f, instance.readFloat(), 0.0f);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(float)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_String() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(String)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            final String s = "";
            
            instance.Write(s);
            
            instance.Rewind();
            
            assertEquals(s, instance.readString());
        }
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            instance.Rewind();
            
            final String s = "asdfghjkl";
            
            instance.Write(s);
            
            instance.Rewind();
            
            assertEquals(s, instance.readString());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(String)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
    @Test
    public void testWrite_ArrayList_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(AL, int)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            instance.Seek(3);
            
            instance.Write(al0, 3);
            
            instance.Seek(3);
            
            ArrayList<Byte> al1 = new ArrayList<>(instance.GetCurrentData());
            
            assertEquals(6, instance.GetSize());
            
            assertTrue(
                    al0.size() == al1.size() &&
                    Objects.equals(al0.get(0), al1.get(0)) &&
                    Objects.equals(al0.get(1), al1.get(1)) &&
                    Objects.equals(al0.get(2), al1.get(2))
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(AL, int)", "end.");
    }

    /**
     * Test of Write method, of class iDynamicBuffer.
     */
//    @Test
//    public void testWrite_3args() {
//        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(AL, int)", "begin");
//        
//        {
//            ArrayList<Byte> al0      = new ArrayList<>();
//            
//            final byte b0 = (byte) 0x10;
//            final byte b1 = (byte) 0x33;
//            final byte b2 = (byte) 0xff;
//            
//            al0.add(b0);
//            al0.add(b1);
//            al0.add(b2);
//            
//            iDynamicBuffer instance = new iDynamicBuffer(al0, 3);
//            
//            instance.Write(al0, 3, 1);
//            
//            instance.Seek(3);
//            
//            ArrayList<Byte> al1 = new ArrayList<>(instance.GetCurrentData());
//            
//            assertEquals(6, instance.GetSize());
//            
//            assertTrue(
//                    al0.size() == al1.size() + 3 &&
//                    Objects.equals(al0.get(3), al1.get(0)) &&
//                    Objects.equals(al0.get(4), al1.get(1)) &&
//                    Objects.equals(al0.get(5), al1.get(2))
//            );
//        }
//        
//        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.Write(AL, int)", "end.");
//    }

//    /**
//     * Test of readByte method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testReadByte() {
//        System.out.println("readByte");
//        iDynamicBuffer instance = new iDynamicBuffer();
//        byte expResult = 0;
//        byte result = instance.readByte();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readShort method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testReadShort() {
//        System.out.println("readShort");
//        iDynamicBuffer instance = new iDynamicBuffer();
//        short expResult = 0;
//        short result = instance.readShort();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readInt method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testReadInt() {
//        System.out.println("readInt");
//        iDynamicBuffer instance = new iDynamicBuffer();
//        int expResult = 0;
//        int result = instance.readInt();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readFloat method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testReadFloat() {
//        System.out.println("readFloat");
//        iDynamicBuffer instance = new iDynamicBuffer();
//        float expResult = 0.0F;
//        float result = instance.readFloat();
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readString method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testReadString() {
//        System.out.println("readString");
//        iDynamicBuffer instance = new iDynamicBuffer();
//        String expResult = "";
//        String result = instance.readString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of Read method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testRead_ArrayList_int() {
//        System.out.println("Read");
//        ArrayList<Byte> buffer = null;
//        int n = 0;
//        iDynamicBuffer instance = new iDynamicBuffer();
//        boolean expResult = false;
//        boolean result = instance.Read(buffer, n);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of Read method, of class iDynamicBuffer.
//     */
//    @Test
//    public void testRead_3args() {
//        System.out.println("Read");
//        ArrayList<Byte> buffer = null;
//        int n = 0;
//        int size = 0;
//        iDynamicBuffer instance = new iDynamicBuffer();
//        boolean expResult = false;
//        boolean result = instance.Read(buffer, n, size);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of AddLength method, of class iDynamicBuffer.
     */
    @Test
    public void testAddLength() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.AddLength(int)", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3, false);
            
            instance.AddLength(4);
            
            assertTrue(7 <= instance.GetCapacity());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.AddLength(int)", "end.");
    }

    /**
     * Test of IsEnd method, of class iDynamicBuffer.
     */
    @Test
    public void testIsEnd() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IsEnd()", "begin");
        
        {
            ArrayList<Byte> al0      = new ArrayList<>();
            
            final byte b0 = (byte) 0x10;
            final byte b1 = (byte) 0x33;
            final byte b2 = (byte) 0xff;
            
            al0.add(b0);
            al0.add(b1);
            al0.add(b2);
            
            iDynamicBuffer instance = new iDynamicBuffer(al0, 3);
            
            instance.readByte();
            instance.readByte();
            instance.readByte();
            
            assertEquals(true, instance.IsEnd());
            
            instance.Rewind();
            
            assertEquals(false, instance.IsEnd());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicBuffer.IsEnd()", "end.");
    }
    
}
