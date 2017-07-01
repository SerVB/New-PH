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
package newph.type;

import newph.staticFunction.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class Fixed32Test {
    
    public Fixed32Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of set method, of class fix32.
     */
    @Test
    public void testSet_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(5);
            fix32 instance = new fix32(6);
            instance.set(other);
            
            assertTrue(instance.equals(other));
        }
        
        {
            fix32 other    = new fix32(0);
            fix32 instance = new fix32(0);
            instance.set(other);
            
            assertTrue(instance.equals(other));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(Fixed32)", "end");
    }

    /**
     * Test of set method, of class fix32.
     */
    @Test
    public void testSet_int() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(int)", "begin");
        
        {
            int other        = 5;
            fix32 instance = new fix32(6);
            instance.set(other);
            
            assertTrue(instance.equals(new fix32(other)));
        }
        
        {
            int other        = 0;
            fix32 instance = new fix32(0);
            instance.set(other);
            
            assertTrue(instance.equals(new fix32(other)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(int)", "end");
    }

    /**
     * Test of set method, of class fix32.
     */
    @Test
    public void testSet_double() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(double)", "begin");
        
        {
            double other     = 5.0;
            fix32 instance = new fix32(6);
            instance.set(other);
            
            assertEquals(other, instance.toDouble(), 0.0);
        }
        
        {
            double other     = 0.0;
            fix32 instance = new fix32(0);
            instance.set(other);
            
            assertEquals(other, instance.toDouble(), 0.0);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.set(double)", "end");
    }

    /**
     * Test of toDouble method, of class fix32.
     */
    @Test
    public void testToDouble() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.toDouble()", "begin");
        
        {
            fix32 instance = new fix32(0);
            double expResult = 0.0;
            double result = instance.toDouble();
            assertEquals(expResult, result, 0.0);
        }
        
        {
            fix32 instance = new fix32(10);
            double expResult = 10.0;
            double result = instance.toDouble();
            assertEquals(expResult, result, 0.0);
        }
        
        {
            fix32 instance = new fix32(10.5);
            double expResult = 10.5;
            double result = instance.toDouble();
            assertEquals(expResult, result, 0.0);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.toDouble()", "end");
    }

    /**
     * Test of add method, of class fix32.
     */
    @Test
    public void testAdd_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.add(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(0);
            fix32 instance = new fix32(0);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(0)));
        }
        
        {
            fix32 other    = new fix32(2);
            fix32 instance = new fix32(2);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(4)));
        }
        
        {
            fix32 other    = new fix32(3.5);
            fix32 instance = new fix32(1.5);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(5)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.add(Fixed32)", "end");
    }

    /**
     * Test of subtract method, of class fix32.
     */
    @Test
    public void testSubtract_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.subtract(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(1.5);
            fix32 instance = new fix32(3.5);
            instance.subtract(other);
            
            assertTrue(instance.equals(new fix32(2)));
        }
        
        {
            fix32 other    = new fix32(1);
            fix32 instance = new fix32(1);
            instance.subtract(other);
            
            assertTrue(instance.equals(new fix32(0)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.subtract(Fixed32)", "end");
    }

    /**
     * Test of multiply method, of class fix32.
     */
    @Test
    public void testMultiply_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.multiply(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(1);
            fix32 instance = new fix32(1);
            instance.multiply(other);
            
            assertTrue(instance.equals(new fix32(1)));
        }
        
        {
            fix32 other    = new fix32(1.5);
            fix32 instance = new fix32(0.5);
            instance.multiply(other);
            
            assertTrue(instance.equals(new fix32(0.75)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.multiply(Fixed32)", "end");
    }

    /**
     * Test of divide method, of class fix32.
     */
    @Test
    public void testDivide_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.divide(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(1);
            fix32 instance = new fix32(1);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(1)));
        }
        
        {
            fix32 other    = new fix32(2);
            fix32 instance = new fix32(1);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(0.5)));
        }
        
        {
            fix32 other    = new fix32(2);
            fix32 instance = new fix32(0.5);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(0.25)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.divide(Fixed32)", "end");
    }

    /**
     * Test of add method, of class fix32.
     */
    @Test
    public void testAdd_int() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.add(int)", "begin");
        
        {
            int other        = 0;
            fix32 instance = new fix32(0);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(0)));
        }
        
        {
            int other        = 2;
            fix32 instance = new fix32(2);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(4)));
        }
        
        {
            int other        = 2;
            fix32 instance = new fix32(1.5);
            instance.add(other);
            
            assertTrue(instance.equals(new fix32(3.5)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.add(int)", "end");
    }

    /**
     * Test of subtract method, of class fix32.
     */
    @Test
    public void testSubtract_int() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.subtract(int)", "begin");
        
        {
            int other        = 1;
            fix32 instance = new fix32(1.5);
            instance.subtract(other);
            
            assertTrue(instance.equals(new fix32(0.5)));
        }
        
        {
            int other        = 0;
            fix32 instance = new fix32(1);
            instance.subtract(other);
            
            assertTrue(instance.equals(new fix32(1)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.subtract(int)", "end");
    }

    /**
     * Test of multiply method, of class fix32.
     */
    @Test
    public void testMultiply_int() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.multiply(int)", "begin");
        
        {
            int other        = 1;
            fix32 instance = new fix32(1);
            instance.multiply(other);
            
            assertTrue(instance.equals(new fix32(1)));
        }
        
        {
            int other        = 0;
            fix32 instance = new fix32(0.5);
            instance.multiply(other);
            
            assertTrue(instance.equals(new fix32(0)));
        }
        
        {
            int other        = 2;
            fix32 instance = new fix32(1.5);
            instance.multiply(other);
            
            assertTrue(instance.equals(new fix32(3)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.multiply(int)", "end");
    }

    /**
     * Test of divide method, of class fix32.
     */
    @Test
    public void testDivide_int() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.divide(int)", "begin");
        
        {
            int other        = 1;
            fix32 instance = new fix32(1);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(1)));
        }
        
        {
            int other        = 2;
            fix32 instance = new fix32(1);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(0.5)));
        }
        
        {
            int other        = 2;
            fix32 instance = new fix32(0.5);
            instance.divide(other);
            
            assertTrue(instance.equals(new fix32(0.25)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.divide(int)", "end");
    }

    /**
     * Test of floor method, of class fix32.
     */
    @Test
    public void testFloor() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.floor()", "begin");
        
        {
            int expected     = 1;
            fix32 instance = new fix32(1.5);
            
            assertEquals(expected, instance.floor());
        }
        
        {
            int expected     = 1;
            fix32 instance = new fix32(1);
            
            assertEquals(expected, instance.floor());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.floor()", "end");
    }

    /**
     * Test of ceil method, of class fix32.
     */
    @Test
    public void testCeil() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.ceil()", "begin");
        
        {
            int expected     = 2;
            fix32 instance = new fix32(1.5);
            
            assertEquals(expected, instance.ceil());
        }
        
        {
            int expected     = 1;
            fix32 instance = new fix32(1);
            
            assertEquals(expected, instance.ceil());
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.ceil()", "end");
    }

    /**
     * Test of equals method, of class fix32.
     */
    @Test
    public void testEquals_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.equals(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(32);
            boolean expResult = true;
            boolean result = instance.equals(other);
            
            assertEquals(expResult, result);
        }
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(3);
            boolean expResult = false;
            boolean result = instance.equals(other);
            
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.equals(Fixed32)", "end");
    }

    /**
     * Test of greaterThan method, of class fix32.
     */
    @Test
    public void testGreaterThan_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.greaterThan(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(32);
            boolean expResult = false;
            boolean result = instance.greaterThan(other);
            
            assertEquals(expResult, result);
        }
        
        {
            fix32 other    = new fix32(3);
            fix32 instance = new fix32(32);
            boolean expResult = true;
            boolean result = instance.greaterThan(other);
            
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.greaterThan(Fixed32)", "end");
    }

    /**
     * Test of lessThan method, of class fix32.
     */
    @Test
    public void testLessThan_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.lessThan(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(32);
            boolean expResult = false;
            boolean result = instance.lessThan(other);
            
            assertEquals(expResult, result);
        }
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(3);
            boolean expResult = true;
            boolean result = instance.lessThan(other);
            
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.lessThan(Fixed32)", "end");
    }

    /**
     * Test of greaterOrEqualThan method, of class fix32.
     */
    @Test
    public void testGreaterOrEqualThan_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.greaterOrEqualThan(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(32);
            boolean expResult = true;
            boolean result = instance.greaterOrEqualThan(other);
            
            assertEquals(expResult, result);
        }
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(3);
            boolean expResult = false;
            boolean result = instance.greaterOrEqualThan(other);
            
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.greaterOrEqualThan(Fixed32)", "end");
    }

    /**
     * Test of lessOrEqualThan method, of class fix32.
     */
    @Test
    public void testLessOrEqualThan_Fixed32() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.lessOrEqualThan(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(32);
            fix32 instance = new fix32(32);
            boolean expResult = true;
            boolean result = instance.lessOrEqualThan(other);
            
            assertEquals(expResult, result);
        }
        
        {
            fix32 other    = new fix32(3);
            fix32 instance = new fix32(32);
            boolean expResult = false;
            boolean result = instance.lessOrEqualThan(other);
            
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32.lessOrEqualThan(Fixed32)", "end");
    }
    
    @Test
    public void testConstructorCopy() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(Fixed32)", "begin");
        
        {
            fix32 other    = new fix32(4);
            fix32 instance = new fix32(other);
            
            assertTrue(instance.equals(other));
        }
        
        {
            fix32 other    = new fix32(0);
            fix32 instance = new fix32(other);
            
            assertTrue(instance.equals(other));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(Fixed32)", "end");
    }
    
    @Test
    public void testConstructorInt() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(int)", "begin");
        
        {
            int other        = 4;
            fix32 instance = new fix32(other);
            
            assertTrue(instance.equals(new fix32(other)));
        }
        
        {
            int other        = 0;
            fix32 instance = new fix32(other);
            
            assertTrue(instance.equals(new fix32(other)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(int)", "end");
    }
    
    @Test
    public void testConstructorDouble() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(double)", "begin");
        
        {
            double other     = 4.0;
            fix32 instance = new fix32(other);
            
            assertTrue(instance.toDouble() == other);
        }
        
        {
            double other     = 0.0;
            fix32 instance = new fix32(other);
            
            assertTrue(instance.toDouble() == other);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(double)", "end");
    }
    
    @Test
    public void testConstructorCombo0() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo0)", "begin");
        
        {
            fix32 first  = new fix32(1);
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '+', second);
            
            assertTrue(result.equals(new fix32(3)));
        }
        
        {
            fix32 first  = new fix32(3);
            fix32 second = new fix32(1);
            
            fix32 result = new fix32(first, '-', second);
            
            assertTrue(result.equals(new fix32(2)));
        }
        
        {
            fix32 first  = new fix32(3);
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '*', second);
            
            assertTrue(result.equals(new fix32(6)));
        }
        
        {
            fix32 first  = new fix32(3);
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '/', second);
            
            assertTrue(result.equals(new fix32(1.5)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo0)", "end");
    }
    
    @Test
    public void testConstructorCombo1() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo1)", "begin");
        
        {
            fix32 first  = new fix32(1);
            int second     = 2;
            
            fix32 result = new fix32(first, '+', second);
            
            assertTrue(result.equals(new fix32(3)));
        }
        
        {
            fix32 first  = new fix32(3);
            int second     = 1;
            
            fix32 result = new fix32(first, '-', second);
            
            assertTrue(result.equals(new fix32(2)));
        }
        
        {
            fix32 first  = new fix32(3);
            int second     = 2;
            
            fix32 result = new fix32(first, '*', second);
            
            assertTrue(result.equals(new fix32(6)));
        }
        
        {
            fix32 first  = new fix32(3);
            int second     = 2;
            
            fix32 result = new fix32(first, '/', second);
            
            assertTrue(result.equals(new fix32(1.5)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo1)", "end");
    }
    
    @Test
    public void testConstructorCombo2() {
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo2)", "begin");
        
        {
            int first      = 1;
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '+', second);
            
            assertTrue(result.equals(new fix32(3)));
        }
        
        {
            int first      = 3;
            fix32 second = new fix32(1);
            
            fix32 result = new fix32(first, '-', second);
            
            assertTrue(result.equals(new fix32(2)));
        }
        
        {
            int first      = 3;
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '*', second);
            
            assertTrue(result.equals(new fix32(6)));
        }
        
        {
            int first      = 3;
            fix32 second = new fix32(2);
            
            fix32 result = new fix32(first, '/', second);
            
            assertTrue(result.equals(new fix32(1.5)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Fixed32(combo2)", "end");
    }
    
}
