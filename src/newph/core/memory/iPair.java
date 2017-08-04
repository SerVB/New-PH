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

import java.util.Objects;

/**
 * The template class which stores a pair of objects, first, of type {@code T}, and second, of type {@code U}. 
 * The type definition first_type is the same as the template parameter {@code T}, 
 * while second_type is the same as the template parameter {@code U}.
 *
 * @author      SerVB
 * @param <T>   First object's type.
 * @param <U>   Second object's type.
 * @since       "GitHub new sources"
 */
public final class iPair<T, U> {
    
    /**
     * Initializes first to T() and second to U().
     * 
     * @param f
     * @param s 
     */
    public iPair(final T f, final U s) {
        first = f;
        second = s;
    }

    /**
     * Constructs an empty pair.
     */
    public iPair() {}

    /**
     * Constructs the pair: copies the other pair's objects to this.
     * 
     * @param p The other pair.
     */
    public iPair(final iPair<T, U> p) {
        first = p.first;
        second = p.second;
    }
    
    public static <T, U> iPair MakePair(final T first, final U second) {
        return new iPair<>(first, second);
    }

    /**
     * Copies the other pair's objects to this.
     * 
     * @param other The other pair.
     */
    public void setTo(final iPair<T, U> other) {
        first  = other.first;
        second = other.second;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.first);
        hash = 79 * hash + Objects.hashCode(this.second);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final iPair<?, ?> other = (iPair<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }

    /**
     * The first object.
     */
    public T first;
    
    /**
     * The second object.
     */
    public U second;
    
}
