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
package Constants.item;

/**
 *
 */
public class QUNTITY {

    /**
     * RAND_QUANTITY_ITEM.
     */
    public final static class RAND_ITEM {

        public int minVal;
        public int maxVal;

        public RAND_ITEM(int minVal, int maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }

    }

    /**
     * INITIAL_QUNTITY.
     */
    public final static RAND_ITEM[] INITIAL = {
        new RAND_ITEM(750,   1500),
        new RAND_ITEM(1000,  2000),
        new RAND_ITEM(1500,  3000),
        new RAND_ITEM(2000,  4000),
        new RAND_ITEM(3000,  6000),
        new RAND_ITEM(10000, 25000)
    };

}
