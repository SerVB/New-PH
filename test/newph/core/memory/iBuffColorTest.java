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
 * BuffColor Test.
 * @author SerVB
 */
public class iBuffColorTest {

    /**
     * Test of getAr method, of class iBuffColor.
     */
    @Test
    public void testGetAr() {
        Logger.printLog(Logger.LogLevel.TEST, "iBuffColor.getAr()", "begin");
        
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(1);
            al.add(2);
            al.add(3);
            iBuffColor instance = new iBuffColor(al, 3);
            
            assertEquals(false, instance.IsClean());
            
            assertEquals(3, instance.GetSize());
            
            assertTrue(
                    instance.getAr()[0] == 1 &&
                    instance.getAr()[1] == 2 &&
                    instance.getAr()[2] == 3
            );
        }
        
        Logger.printLog(Logger.LogLevel.TEST, "iBuffColor.getAr()", "end.");
    
    }
    
}
