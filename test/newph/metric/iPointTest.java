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
package newph.metric;

import Common.metrics.iSize;
import newph.util.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public final class iPointTest {
    
    public iPointTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}

    /**
     * Test of add method, of class iPoint.
     */
    @Test
    public void testAdd_iPoint() {
        Logger.printLog(Logger.LogLevel.TEST, "add(Point)", "begin");
        
        {
            iPoint pos      = null;
            iPoint instance = new iPoint();
            instance.add(pos);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            iPoint pos      = new iPoint(1, 0);
            iPoint instance = new iPoint(1, 0);
            instance.add(pos);
            assertTrue(instance.equals(new iPoint(2, 0)));
        }
        
        {
            iPoint pos      = new iPoint(-100, 10);
            iPoint instance = new iPoint(1000, 30);
            instance.add(pos);
            assertTrue(instance.equals(new iPoint(900, 40)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "add(Point)", "end");
    }

    /**
     * Test of subtract method, of class iPoint.
     */
    @Test
    public void testSubtract_iPoint() {
        Logger.printLog(Logger.LogLevel.TEST, "subtract(Point)", "begin");
        
        {
            iPoint pos      = null;
            iPoint instance = new iPoint();
            instance.subtract(pos);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            iPoint pos      = new iPoint(1, 0);
            iPoint instance = new iPoint(1, 0);
            instance.subtract(pos);
            assertTrue(instance.equals(new iPoint(0, 0)));
        }
        
        {
            iPoint pos      = new iPoint(10, 20);
            iPoint instance = new iPoint(-1, 30);
            instance.subtract(pos);
            assertTrue(instance.equals(new iPoint(-11, 10)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "subtract(Point)", "end");
    }

    /**
     * Test of add method, of class iPoint.
     */
    @Test
    public void testAdd_iSize() {
        Logger.printLog(Logger.LogLevel.TEST, "add(Size)", "begin");
        
        {
            iSize size      = null;
            iPoint instance = new iPoint();
            instance.add(size);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            iSize size      = new iSize(1, 0);
            iPoint instance = new iPoint(1, 0);
            instance.add(size);
            assertTrue(instance.equals(new iPoint(2, 0)));
        }
        
        {
            iSize size      = new iSize(-100, 10);
            iPoint instance = new iPoint(1000, 30);
            instance.add(size);
            assertTrue(instance.equals(new iPoint(900, 40)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "add(Size)", "end");
    }

    /**
     * Test of subtract method, of class iPoint.
     */
    @Test
    public void testSubtract_iSize() {
        Logger.printLog(Logger.LogLevel.TEST, "subtract(Size)", "begin");
        
        {
            iSize size      = null;
            iPoint instance = new iPoint();
            instance.subtract(size);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            iSize size      = new iSize(1, 0);
            iPoint instance = new iPoint(1, 0);
            instance.subtract(size);
            assertTrue(instance.equals(new iPoint(0, 0)));
        }
        
        {
            iSize size      = new iSize(10, 20);
            iPoint instance = new iPoint(-1, 30);
            instance.subtract(size);
            assertTrue(instance.equals(new iPoint(-11, 10)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "subtract(Size)", "end");
    }

    /**
     * Test of add method, of class iPoint.
     */
    @Test
    public void testAdd_int() {
        Logger.printLog(Logger.LogLevel.TEST, "add(int)", "begin");
        
        {
            int offs        = 0;
            iPoint instance = new iPoint();
            instance.add(offs);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            int offs        = 1;
            iPoint instance = new iPoint(1, 0);
            instance.add(offs);
            assertTrue(instance.equals(new iPoint(2, 1)));
        }
        
        {
            int offs        = -100;
            iPoint instance = new iPoint(1000, 30);
            instance.add(offs);
            assertTrue(instance.equals(new iPoint(900, -70)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "add(int)", "end");
    }

    /**
     * Test of subtract method, of class iPoint.
     */
    @Test
    public void testSubtract_int() {
        Logger.printLog(Logger.LogLevel.TEST, "subtract(int)", "begin");
        
        {
            int offs        = 0;
            iPoint instance = new iPoint();
            instance.subtract(offs);
            assertTrue(instance.equals(new iPoint()));
        }
        
        {
            int offs        = 1;
            iPoint instance = new iPoint(1, 0);
            instance.subtract(offs);
            assertTrue(instance.equals(new iPoint(0, -1)));
        }
        
        {
            int offs        = 10;
            iPoint instance = new iPoint(-1, 30);
            instance.subtract(offs);
            assertTrue(instance.equals(new iPoint(-11, 20)));
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "subtract(int)", "end");
    }

    /**
     * Test of equals method, of class iPoint.
     */
    @Test
    public void testEquals() {
        Logger.printLog(Logger.LogLevel.TEST, "equals(Point)", "begin");
        
        {
            iPoint other    = null;
            iPoint instance = new iPoint();
            boolean expectedResult = false;
            boolean result = instance.equals(other);
            assertEquals(expectedResult, result);
        }
        
        {
            iPoint other    = new iPoint();
            iPoint instance = new iPoint();
            boolean expectedResult = true;
            boolean result = instance.equals(other);
            assertEquals(expectedResult, result);
        }
        
        {
            iPoint other    = new iPoint(1, 0);
            iPoint instance = new iPoint(2, 0);
            boolean expectedResult = false;
            boolean result = instance.equals(other);
            assertEquals(expectedResult, result);
        }
        
        {
            iPoint other    = new iPoint(-1, 2);
            iPoint instance = new iPoint(-1, 3);
            boolean expectedResult = false;
            boolean result = instance.equals(other);
            assertEquals(expectedResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "equals(Point)", "end");
    }

    /**
     * Test of GetSqDelta method, of class iPoint.
     */
//    @Test
//    public void testGetSqDelta() {
//        System.out.println("test " + "GetSqDelta");
//        iPoint pnt = null;
//        iPoint instance = new iPoint();
//        int expResult = 0;
//        int result = instance.GetSqDelta(pnt);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of GetDelta method, of class iPoint.
     */
    @Test
    public void testGetDelta() {
        Logger.printLog(Logger.LogLevel.TEST, "getDelta(Point)", "begin");
        
        {
            iPoint other    = null;
            iPoint instance = new iPoint();
            int expResult = 0;
            int result = instance.GetDelta(other);
            assertEquals(expResult, result);
        }
        
        {
            iPoint other    = new iPoint();
            iPoint instance = new iPoint();
            int expResult = 0;
            int result = instance.GetDelta(other);
            assertEquals(expResult, result);
        }
        
        {
            iPoint other    = new iPoint(1, 0);
            iPoint instance = new iPoint(1, 1);
            int expResult = 1;
            int result = instance.GetDelta(other);
            assertEquals(expResult, result);
        }
        
        {
            iPoint other    = new iPoint(0+1, 0+2);
            iPoint instance = new iPoint(3+1, 4+2);
            int expResult = 5;
            int result = instance.GetDelta(other);
            assertEquals(expResult, result);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "getDelta(Point)", "end");
    }

    /**
     * Test of MoveX method, of class iPoint.
     */
    @Test
    public void testMoveX() {
        Logger.printLog(Logger.LogLevel.TEST, "moveX(int)", "begin");
        
        {
            int offset_x    = 0;
            iPoint instance = new iPoint();
            instance.MoveX(offset_x);
            assertEquals(0, instance.x);
        }
        
        {
            int offset_x    = 10;
            iPoint instance = new iPoint();
            instance.MoveX(offset_x);
            assertEquals(10, instance.x);
        }
        
        {
            int offset_x    = -10;
            iPoint instance = new iPoint(1, 123);
            instance.MoveX(offset_x);
            assertEquals(-9, instance.x);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "moveX(int)", "end");
    }

    /**
     * Test of MoveY method, of class iPoint.
     */
    @Test
    public void testMoveY() {
        Logger.printLog(Logger.LogLevel.TEST, "moveY(int)", "begin");
        
        {
            int offset_y    = 0;
            iPoint instance = new iPoint();
            instance.MoveY(offset_y);
            assertEquals(0, instance.y);
        }
        
        {
            int offset_y    = 10;
            iPoint instance = new iPoint();
            instance.MoveY(offset_y);
            assertEquals(10, instance.y);
        }
        
        {
            int offset_y    = -10;
            iPoint instance = new iPoint(123, 1);
            instance.MoveY(offset_y);
            assertEquals(-9, instance.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "moveY(int)", "end");
    }

    /**
     * Test of Move method, of class iPoint.
     */
    @Test
    public void testMove() {
        Logger.printLog(Logger.LogLevel.TEST, "move(int)", "begin");
        
        {
            int offset_x = 0;
            int offset_y = 0;
            iPoint instance = new iPoint();
            instance.Move(offset_x, offset_y);
            assertTrue(0 == instance.x && 0 == instance.y);
        }
        
        {
            int offset_x = 10;
            int offset_y = 20;
            iPoint instance = new iPoint();
            instance.Move(offset_x, offset_y);
            assertTrue(10 == instance.x && 20 == instance.y);
        }
        
        {
            int offset_x = -10;
            int offset_y = -20;
            iPoint instance = new iPoint(100, 200);
            instance.Move(offset_x, offset_y);
            assertTrue(90 == instance.x && 180 == instance.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "move(int)", "end");
    }
    
    /**
     * Test default constructor.
     */
    @Test
    public void testConstructorDefault() {
        Logger.printLog(Logger.LogLevel.TEST, "Point()", "begin");
        
        {
            iPoint pnt = new iPoint();
            assertEquals(0, pnt.x);
            assertEquals(0, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point()", "end");
    }
    
    /**
     * Test copy constructor.
     */
    @Test
    public void testConstructorCopy() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point)", "begin");
        
        {
            iPoint pnt = new iPoint(new iPoint(1, -10));
            assertEquals(1, pnt.x);
            assertEquals(-10, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(-1, 100));
            assertEquals(-1, pnt.x);
            assertEquals(100, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point)", "end");
    }
    
    /**
     * Test Size constructor.
     */
    @Test
    public void testConstructorSize() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(Size)", "begin");
        
        {
            iPoint pnt = new iPoint(new iSize(4, 7));
            assertEquals(4, pnt.x);
            assertEquals(7, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iSize(-40, -77));
            assertEquals(-40, pnt.x);
            assertEquals(-77, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(Size)", "end");
    }
    
    /**
     * Test coordinates constructor.
     */
    @Test
    public void testConstructorCoordinates() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(int, int)", "begin");
        
        {
            iPoint pnt = new iPoint(1, 2);
            assertEquals(1, pnt.x);
            assertEquals(2, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(-100, 26);
            assertEquals(-100, pnt.x);
            assertEquals(26, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(int, int)", "end");
    }
    
    /**
     * Test operation points constructor.
     */
    @Test
    public void testConstructorOperPoint() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, Point)", "begin");
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '+', new iPoint(1, 2));
            assertEquals(1, pnt.x);
            assertEquals(2, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '+', new iPoint(11, 22));
            assertEquals(31, pnt.x);
            assertEquals(52, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '-', new iPoint(1, 2));
            assertEquals(-1, pnt.x);
            assertEquals(-2, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '-', new iPoint(11, 22));
            assertEquals(9, pnt.x);
            assertEquals(8, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, Point)", "end");
    }
    
    /**
     * Test operation point and size constructor.
     */
    @Test
    public void testConstructorOperSize() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, Size)", "begin");
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '+', new iSize(1, 2));
            assertEquals(1, pnt.x);
            assertEquals(2, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '+', new iSize(11, 22));
            assertEquals(31, pnt.x);
            assertEquals(52, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '-', new iSize(1, 2));
            assertEquals(-1, pnt.x);
            assertEquals(-2, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '-', new iSize(11, 22));
            assertEquals(9, pnt.x);
            assertEquals(8, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, Size)", "end");
    }
    
    /**
     * Test operation point and int constructor.
     */
    @Test
    public void testConstructorOperInt() {
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, int)", "begin");
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '+', 1);
            assertEquals(1, pnt.x);
            assertEquals(1, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '+', 11);
            assertEquals(31, pnt.x);
            assertEquals(41, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(0, 0), '-', 1);
            assertEquals(-1, pnt.x);
            assertEquals(-1, pnt.y);
        }
        
        {
            iPoint pnt = new iPoint(new iPoint(20, 30), '-', 11);
            assertEquals(9, pnt.x);
            assertEquals(19, pnt.y);
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "Point(Point, char, int)", "end");
    }
    
}
