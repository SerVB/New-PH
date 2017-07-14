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

import java.util.Objects;

/**
 * Changeable object to be passed to functions
 * and to be changed in them.
 * @param <T> Object's Type.
 * @author SerVB
 * @since PPH 0
 */
public class Changeable<T> {

    /**
     * Changeable value.
     */
    public T value;

    /**
     * Constructs the changeable object.
     * @param value The initial value of the object.
     */
    public Changeable(final T value) {
        this.value = value;
    }

//    /**
//     * Returns the hash code of the object.
//     * @return The hash code.
//     */
    // Inherited Javadoc
    @Override
    public int hashCode() {
        final Hash hash = Hash.std();
        hash.insert(value);
        return hash.getResult();
    }

//    /**
//     * Checks if the other object is equal to this object.
//     * @param obj   The other object.
//     * @return      {@code True} if the objects are equal, {@code false} otherwise.
//     */
    // Inherited Javadoc
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Changeable<?> other = (Changeable<?>) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

//    /**
//     * Returns the String representation of the object.
//     * @return The String representation.
//     */
    // Inherited Javadoc
    @Override
    public String toString() {
        return String.format(
                "Changeable<%s>{value=%s}", 
                value.getClass().getName(), 
                value.toString()
        );
    }

}
