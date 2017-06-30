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

/**
 * Fixed point type with the storage of 32 bits.
 * TODO:: Correct division code ( now can lead to overflow / ZERO division in some cases )
 * TODO:: Add diagnostics
 */
public final class Fixed32 {
    
//    /**
//     * Number of bits of storage type (int).
//     */
//    private final static int BITS = 32;
    
    /**
     * Number of bits of fraction.
     */
    private final static int FRAC_BITS = 16;

    /**
     * Constant equals zero.
     */
    public final static Fixed32 ZERO = new Fixed32(0);
    
    /**
     * Value of fixed equals 1.
     */
    private final static int ONE_VALUE = 1 << FRAC_BITS;
    
    /**
     * Mask of fraction bits.
     */
    private final static int FRAC_BITS_MASK = calculateFracBitsMask();
    
    /**
     * Constructs the object by copying the other.
     * @param other Object to copy.
     */
    public Fixed32(final Fixed32 other) {
        set(other);
    }
    
    /**
     * Constructs the object from integer.
     * @param ii Integer.
     */
    public Fixed32(final int ii) {
        set(ii);
    }
    
    /**
     * Constructs the object from double.
     * @param dd Double.
     */
    public Fixed32(final double dd) {
        set(dd);
    }

    /**
     * Copies the other object's value to this object.
     * @param other Other object.
     */
    public final void set(final Fixed32 other) {
        val = other.val;
    }
    
    /**
     * Converts the integer value and sets the converted value to this object.
     * @param ii Integer.
     */
    public final void set(final int ii) {
        val = ONE_VALUE * ii;
    }
    
    /**
     * Converts the double value and sets the converted value to this object.
     * @param dd Double.
     */
    public final void set(final double dd) {
        val = (int)(ONE_VALUE * dd + 0.5);
    }
    
    /**
     * Returns this object's value converted to double.
     * @return Converted value.
     */
    public final double toDouble() {
        return (0.0 + val) / ONE_VALUE;
    }

    /**
     * Changes this object:
     * Adds the other object's value.
     * @param other Other object.
     */
    public final void add(final Fixed32 other) {
        val += other.val;
    }
    
    /**
     * Changes this object:
     * Subtracts the other object's value.
     * @param other Other object.
     */
    public final void subtract(final Fixed32 other) {
        val -= other.val;
    }

    /**
     * Changes this object:
     * Multiplies this object's value and the other object's value.
     * @param other Other object.
     */
    public final void multiply(final Fixed32 other) {
        val = mul_shift(val, other.val);
    }
    
    /**
     * Changes this object:
     * Divides this object's value and the other object's value.
     * @param other Other object.
     */
    public final void divide(final Fixed32 other) {
        val = shift_div(val, other.val);
//        val = val / ( rhs.val >> frac_bits ); // Comment in sources
    }

    /**
     * Changes this object:
     * Adds the integer value.
     * @param ii Integer.
     */
    public final void add(final int ii) {
        add(new Fixed32(ii));
    }
    
    /**
     * Changes this object:
     * Subtracts the integer value.
     * @param ii Integer.
     */
    public final void subtract(final int ii) {
        subtract(new Fixed32(ii));
    }
    
    /**
     * Changes this object:
     * Multiplies this object's value and the integer value.
     * @param ii Integer.
     */
    public final void multiply(final int ii) {
        val *= ii;
    }
    
    /**
     * Changes this object:
     * Divides this object's value and the integer value.
     * @param ii Integer.
     */
    public final void divide(final int ii) {
        val /= ii;
    }
    
    /**
     * Constructs a new object with operated two other objects' values.
     * @param first First object.
     * @param oper Operation ('+' or '-' or '*' or '/' or throws IllegalArgumentException).
     * @param second Second object.
     */
    public Fixed32(final Fixed32 first, final char oper, final Fixed32 second) {
        final Fixed32 tmp;
        
        switch (oper) {
            case '+':
                tmp = new Fixed32(first);
                tmp.add(second); 
                this.val = tmp.val;
                break;
            case '-':
                tmp = new Fixed32(first);
                tmp.subtract(second); 
                this.val = tmp.val;
                break;
            case '*':
                tmp = new Fixed32(first);
                tmp.multiply(second); 
                this.val = tmp.val;
                break;
            case '/':
                tmp = new Fixed32(first);
                tmp.divide(second); 
                this.val = tmp.val;
                break;
            default:
                throw new IllegalArgumentException("fatal > " + "Invalid operator '" + oper + "'!");
        }
    }
    
    /**
     * Constructs a new object with operated object's and integer values.
     * @param fixed Object.
     * @param oper Operation ('+' or '-' or '*' or '/' or throws IllegalArgumentException).
     * @param ii Integer.
     */
    public Fixed32(final Fixed32 fixed, final char oper, final int ii) {
        this(fixed, oper, new Fixed32(ii));
    }
    
    /**
     * Constructs a new object with operated object's and integer values.
     * @param ii Integer.
     * @param oper Operation ('+' or '-' or '*' or '/' or throws IllegalArgumentException).
     * @param fixed Object.
     */
    public Fixed32(final int ii, final char oper, final Fixed32 fixed) {
        this(new Fixed32(ii), oper, fixed);
    }

    /**
     * Returns floored value of the object.
     * @return Floored value.
     */
    public final int floor() {
        return getIntegerPart();
    }
    
    /**
     * Returns ceiled value of the object.
     * @return Ceiled value.
     */
    public final int ceil() {
        if (containsFraction()) {
            return getIntegerPart() + 1;
        } else {
            return getIntegerPart();
        }
    }
    
    /**
     * Returns converted to integer integer part of the object.
     * @return Integer part.
     */
    private int getIntegerPart() {
        return val >> FRAC_BITS;
    }
    
    /**
     * Checks if the object contains fraction or not.
     * @return True if contains, false if not.
     */
    private boolean containsFraction() {
        return getRawFraction() != 0;
    }
    
    /**
     * Returns raw fraction value: clears the integer part bits.
     * @return Raw fraction value.
     */
    private int getRawFraction() {
        return val & FRAC_BITS_MASK;
    }
    
    /**
     * Statically initializes fraction bits mask.
     * @return Fraction bits mask.
     */
    private static int calculateFracBitsMask() {
        int res = 0;
        
        for (int xx = 0; xx < FRAC_BITS; xx++) {
            res |= 1 << xx;
        }
        
        return res;
    }
    
    /**
     * Returns multiplied then shifted value.
     * @param a First integer.
     * @param b Second integer.
     * @return Calculated value.
     */
    private int mul_shift(final int a, final int b) {
        return (a * b) >> FRAC_BITS;
    }
    
    /**
     * Returns shifted then divided value.
     * @param a First integer.
     * @param b Second integer.
     * @return Calculated value.
     */
    private int shift_div(final int a, final int b) {
        return (a << FRAC_BITS) / b;
    }
    
    /**
     * Checks if the object equals the other object.
     * @param other Other object.
     * @return True if equals, false if not.
     */
    public final boolean equals(final Fixed32 other) {
        return this.val == other.val;
    }
    
    /**
     * Checks if the object is greater than the other object.
     * @param other Other object.
     * @return True if greater than, false if not.
     */
    public final boolean greaterThan(final Fixed32 other) {
        return this.val > other.val;
    }
    
    /**
     * Checks if the object is less than the other object.
     * @param other Other object.
     * @return True if less than, false if not.
     */
    public final boolean lessThan(final Fixed32 other) {
        return this.val < other.val;
    }
    
    /**
     * Checks if the object is greater or equal than the other object.
     * @param other Other object.
     * @return True if greater or equal than, false if not.
     */
    public final boolean greaterOrEqualThan(final Fixed32 other) {
        return this.val >= other.val;
    }
    
    /**
     * Checks if the object is less or equal than the other object.
     * @param other Other object.
     * @return True if less or equal than, false if not.
     */
    public final boolean lessOrEqualThan(final Fixed32 other) {
        return this.val <= other.val;
    }
    
    /**
     * Checks if the object's value equals the integer value.
     * @param other Integer.
     * @return True if equals, false if not.
     */
    public final boolean equals(final int other) {
        return this.equals(new Fixed32(other));
    }
    
    /**
     * Checks if the object's value is greater than the integer value.
     * @param other Integer.
     * @return True if greater than, false if not.
     */
    public final boolean greaterThan(final int other) {
        return this.greaterThan(new Fixed32(other));
    }
    
    /**
     * Checks if the object's value is less than the integer value.
     * @param other Integer.
     * @return True if less than, false if not.
     */
    public final boolean lessThan(final int other) {
        return this.lessThan(new Fixed32(other));
    }
    
    /**
     * Checks if the object's value is greater or equal than the integer value.
     * @param other Integer.
     * @return True if greater or equal than, false if not.
     */
    public final boolean greaterOrEqualThan(final int other) {
        return this.greaterOrEqualThan(new Fixed32(other));
    }
    
    /**
     * Checks if the object's value is less or equal than the integer value.
     * @param other Integer.
     * @return True if less or equal than, false if not.
     */
    public final boolean lessOrEqualThan(final int other) {
        return this.lessOrEqualThan(new Fixed32(other));
    }
    
    /**
     * Value storage.
     */
    private int val;

}

////#define FIXED_UNSAFE
//#ifdef FIXED_UNSAFE
//    inline explicit fixed( uint_type i ) : val( value_type(i) << frac_bits ) {}
//    inline this_type& operator=( const uint_type rhs ) { val = value_type(rhs) << frac_bits; return *this; }
//    inline void set( uint_type i ) { val = (value_type(i) << frac_bits); }
//
//    inline this_type& operator+=( const uint_type rhs )
//    { return operator+=( this_type(rhs)); }
//    inline this_type& operator-=( const uint_type rhs )
//    { return operator-=( this_type(rhs)); }
//
//    inline this_type& operator*=( const uint_type rhs )
//    { val *= rhs; return *this; }
//    inline this_type& operator/=( const uint_type rhs )
//    { val /= rhs; return *this; }
//
//    friend inline this_type operator*( const this_type lhs, const uint_type rhs )
//    { this_type tmp(lhs); tmp.val *= rhs; return tmp; }
//    friend inline this_type operator*( const uint_type lhs, const this_type rhs )
//    { this_type tmp(rhs); tmp.val *= lhs; return tmp; }
//    friend inline this_type operator/( const this_type lhs, const uint_type rhs )
//    { this_type tmp(lhs); tmp.val /= rhs; return tmp; }
//    friend inline this_type operator/( const uint_type lhs, const this_type rhs )
//    { this_type tmp(lhs); tmp.val /= rhs; return tmp; }
//#endif //FIXED_UNSAFE

    /*    
    inline int64 mul_shift( int64 a, int64 b ) const
    {
        int64 res = (a >> frac_bits) * b;
        res += ((a & impl.low_bits<frac_bits,uint64>()) * b) >> frac_bits;
        return res;
    }*/

//    private int convert(final int val, final int bits, final int frac) {
//        if (FRAC_BITS < frac) {
//            return val >> (frac - FRAC_BITS);
//        } else if (frac < FRAC_BITS) {
//            return val << (FRAC_BITS-frac);
//        } else {
//            return val;
//        }
//    }