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
import newph.core.staticFunction.Tracer;

/**
 * Simple Array Class.
 *
 * @author      SerVB
 * @param <E>   Type of the elements.
 * @since       "GitHub New Sources"
 */
public final class iSimpleArray<E> extends ArrayList<E> {

    public boolean SetSize(final int nsize) {
        if (super.size() < nsize) {
            do {
                add(null);
            } while (super.size() < nsize);
        } else {
            do {
                Pop();
            } while (nsize < super.size());
        }

        return true;
    }

    public boolean ValidIdx(final int idx) {
        return 0 <= idx && idx < super.size();
    }

    public boolean AddUnique(final E element) {
        if (super.contains(element)) {
            return false;
        } else {
            super.add(element);
            return true;
        }
    }

    public E Pop() {
        return super.remove(super.size() - 1);
    }

    public E PopFirst() {
        Tracer.check(!super.isEmpty());
        return super.remove(0);
    }

    public boolean InsertAfter(final int nIndex, final E element) {
        super.add(nIndex + 1, element);
        return true;
    }

    public E GetLast() {
        return super.get(super.size() - 1);
    }

    public void Swap(final iSimpleArray<E> other) {
        final ArrayList<E> tmp = new ArrayList<>(other);

        other.clear();
        other.addAll(this);

        super.clear();
        super.addAll(tmp);
    }

}
