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
import newph.core.staticFunction.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Buff Test.
 * @author SerVB
 */
public class iBuffTest {

    /**
     * Test of IsClean method, of class iBuff.
     */
    @Test
    public void testIsClean() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.IsClean()", "begin");
        
        {
            iBuff instance = new iBuff() {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            boolean expResult = true;
            boolean result = instance.IsClean();
            assertEquals(expResult, result);
        }
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            boolean expResult = false;
            boolean result = instance.IsClean();
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.IsClean()", "end.");
    }

    /**
     * Test of Allocate method, of class iBuff.
     */
    @Test
    public void testAllocate_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(int)", "begin");
        
        {
            int siz = 0;
            iBuff instance = new iBuff() {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            instance.Allocate(siz);
            
            assertEquals(instance.GetSize(), siz);
        }
        
        {
            int siz = 11;
            iBuff instance = new iBuff() {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            instance.Allocate(siz);
            
            assertEquals(instance.GetSize(), siz);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(int)", "end.");
    }

    /**
     * Test of Allocate method, of class iBuff.
     */
    @Test
    public void testAllocate_ArrayList_int() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(ArrayList, int)", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            boolean expResult = false;
            boolean result = instance.IsClean();
            assertEquals(expResult, result);
            assertEquals(3, instance.GetSize());
            
            assertTrue(
                    (int) instance.GetPtr().get(0) == 1 &&
                    (int) instance.GetPtr().get(1) == 2 &&
                    (int) instance.GetPtr().get(2) == 3
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(ArrayList, int)", "end.");
    }

    /**
     * Test of Allocate method, of class iBuff.
     */
    @Test
    public void testAllocate_iBuff() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(iBuff)", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff tmp = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            iBuff instance = new iBuff(tmp) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            boolean expResult = false;
            boolean result = instance.IsClean();
            assertEquals(expResult, result);
            assertEquals(3, instance.GetSize());
            
            assertTrue(
                    (int) instance.GetPtr().get(0) == 1 &&
                    (int) instance.GetPtr().get(1) == 2 &&
                    (int) instance.GetPtr().get(2) == 3
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Allocate(iBuff)", "end.");
    }

    /**
     * Test of Clean method, of class iBuff.
     */
    @Test
    public void testClean() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Clean()", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            instance.Clean();
            
            assertEquals(0, instance.GetPtr().size());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.Clean()", "end.");
    }

    /**
     * Test of GetSize method, of class iBuff.
     */
    @Test
    public void testGetSize() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.GetSize()", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            assertEquals(3, instance.GetSize());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.GetSize()", "end.");
    }

    /**
     * Test of GetPtr method, of class iBuff.
     */
    @Test
    public void testGetPtr() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.GetPtr()", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            assertEquals(al, instance.GetPtr());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.GetPtr()", "end.");
    }

    /**
     * Test of set method, of class iBuff.
     */
    @Test
    public void testSet() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.set(int, T)", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            al.set(0, 0);
            al.set(1, 10);
            al.set(2, 11);
            instance.set(0, 0);
            instance.set(1, 10);
            instance.set(2, 11);
            
            assertEquals(al, instance.GetPtr());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.set(int, T)", "end.");
    }

    /**
     * Test of at method, of class iBuff.
     */
    @Test
    public void testAt() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.at(int)", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuff instance = new iBuff(al, 3) {
                @Override
                public Object[] getAr() {
                    throw new UnsupportedOperationException("Testing the abstract class!");
                }
            };
            
            assertEquals(1, instance.at(0));
            assertEquals(2, instance.at(1));
            assertEquals(3, instance.at(2));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuff.at(int)", "end.");
    }
    
}
