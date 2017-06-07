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
package Common.metrics;

/**
 * Fixed point based iPoint.
 */
public class iPointFix {
public:
    iPointFix() {}

    iPointFix(fix32 _x, fix32 _y)
    : x(_x), y(_y) {}

    iPointFix(sint32 _x, sint32 _y)
    : x(_x), y(_y) {}

    iPointFix(float _x, float _y)
    : x(_x), y(_y) {}

    iPointFix(const iPoint& pos)
    : x(pos.x), y(pos.y) {}

    inline void operator += (const iPointFix &pos)
    {
        x=x+pos.x;
        y=y+pos.y;
    }

    inline void operator -= (const iPointFix &pos)
    {
        x=x-pos.x;
        y=y-pos.y;
    }

    inline iPointFix operator + (const iPointFix &pos) const
    { return (iPointFix(x+pos.x,y+pos.y)); }

    inline iPointFix operator - (const iPointFix &pos) const
    { return (iPointFix(x-pos.x,y-pos.y)); }

    inline bool operator== (const iPointFix &pos) const
    { return ((x==pos.x) && (y==pos.y)); }

    inline bool operator!= (const iPointFix &pos) const
    { return ! operator == ( pos ); }

    inline iPoint IntPoint() const
    { return iPoint(x.ceil(),y.ceil()); }

    fix32    x;
    fix32    y;
}
