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

package newph.core.type;

/**
 * Hash Calculator. From "Effective Java".
 * @author SerVB
 * @since PPH 0
 */
public final class Hash {

    /**
     * Default initial value of the hash.
     */
    private final static int INITIAL_VALUE = 17;
    
    /**
     * Default multiplication value for calculating the hash.
     */
    private final static int MUL_VALUE = 31;
    
    /**
     * The result of calculations.
     */
    private int result;
    
    /**
     * The multiplication value for calculating the hash.
     */
    private final int mul;
    
    /**
     * Initializes the Hash with the given values.
     * @param initial   The initial value of the hash.
     * @param mul       The multiplication value for calculating the hash.
     */
    private Hash(final int initial, final int mul) {
        result = initial;
        this.mul = mul;
    }
    
    /**
     * Constructs the Hash object:
     * Initializes the Hash with the standard values.
     * @return The new Hash object.
     */
    public static Hash std() {
        return from(INITIAL_VALUE);
    }
    
    /**
     * Constructs the Hash object:
     * Initializes the Hash with the given initial value
     * and the standard multiplication value.
     * @param initial   The initial value of the Hash.
     * @return          The new Hash object.
     */
    public static Hash from(final int initial) {
        return fromAndMul(initial, MUL_VALUE);
    }
    
    /**
     * Constructs the Hash object:
     * Initializes the Hash with the given values.
     * @param initial   The initial value of the hash.
     * @param mul       The multiplication value for calculating the hash.
     * @return          The new Hash object.
     */
    public static Hash fromAndMul(final int initial, final int mul) {
        return new Hash(initial, mul);
    }
    
    /**
     * Returns the result of Hash calculations.
     * @return The result.
     */
    public int getResult() {
        return result;
    }
    
    /**
     * Inserts int value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final int f) {
        result = mul * result + f;
    }
    
    /**
     * Inserts boolean value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final boolean f) {
        insert(f ? 1 : 0);
    }
    
    /**
     * Inserts byte value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final byte f) {
        insert((int) f);
    }
    
    /**
     * Inserts char value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final char f) {
        insert((int) f);
    }
    
    /**
     * Inserts short value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final short f) {
        insert((int) f);
    }
    
    /**
     * Inserts long value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final long f) {
        insert((int)(f ^ (f >>> 32)));
    }
    
    /**
     * Inserts float value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final float f) {
        insert(Float.floatToIntBits(f));
    }
    
    /**
     * Inserts double value to the Hash.
     * @param f Value to be inserted.
     */
    public void insert(final double f) {
        insert(Double.doubleToLongBits(f));
    }
    
    /**
     * Inserts Object to the Hash.
     * @param f Object to be inserted.
     */
    public void insert(final Object f) {
        if (f == null) {
            insert(0);
        } else {
            insert(f.hashCode());
        }
    }
    
}
