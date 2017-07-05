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
import java.util.Arrays;
import newph.core.staticFunction.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Win7
 */
public class iDynamicPtrTest {
    
    public iDynamicPtrTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}

    /**
     * Test of Write and Read methods, of class iDynamicPtr.
     */
    @Test
    public void testWriteRead_ArrayList_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Write,Read(AL, int)", "begin");
        
        {
            Byte[] ar = {10, 1, 2};
            ArrayList<Byte> al = new ArrayList<>(Arrays.asList(ar));
            iDynamicPtr<Byte> instance = new iDynamicPtr();
            boolean result = instance.Write(al, 0);
            
            assertEquals(true, result);
            
            ArrayList<Byte> newAl = new ArrayList<>();
            
            instance.Rewind();
            
            result = instance.Read(newAl, 0);
            
            assertEquals(true, result);
            
            assertTrue(newAl.get(0) == 10);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Write,Read(AL, int)", "end.");
    }

    /**
     * Test of Write and Write methods, of class iDynamicPtr.
     */
    @Test
    public void testWrite_3args() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Write,Read(AL, int, int)", "begin");
        
        {
            Byte[] ar = {0, 1, 2};
            ArrayList<Byte> al = new ArrayList<>(Arrays.asList(ar));
            iDynamicPtr<Byte> instance = new iDynamicPtr();
            boolean result = instance.Write(al, 0, 3);
            
            assertEquals(true, result);
            
            ArrayList<Byte> newAl = new ArrayList<>(Arrays.asList(ar));
            
            instance.Rewind();
            
            result = instance.Read(newAl, 2, 3);
            
            assertEquals(true, result);
            
            assertTrue(
                    newAl.get(0) == 0 && 
                    newAl.get(1) == 1 && 
                    newAl.get(2) == 0 && 
                    newAl.get(3) == 1 && 
                    newAl.get(4) == 2
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Write,Read(AL, int, int)", "end.");
    }
    
    /**
     * Test of Rewind method, of class iDynamicPtr.
     */
    @Test
    public void testRewind() {
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Rewind()", "begin");
        
        {
            Byte[] ar = {0, 1, 2};
            ArrayList<Byte> al = new ArrayList<>(Arrays.asList(ar));
            iDynamicPtr<Byte> instance = new iDynamicPtr();
            instance.Write(al, 0, 3);
            
            instance.Rewind();
            
            ar = new Byte[] {10, 11, 12};
            al = new ArrayList<>(Arrays.asList(ar));
            
            instance.Write(al, 0, 3);
            
            instance.Rewind();
            
            ArrayList<Byte> newAl = new ArrayList<>();
            
            instance.Read(newAl, 0, 3);
            
            assertTrue(
                    newAl.get(0) == 10 && 
                    newAl.get(1) == 11 && 
                    newAl.get(2) == 12
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iDynamicPtr.Rewind()", "end.");
    }
    
}
