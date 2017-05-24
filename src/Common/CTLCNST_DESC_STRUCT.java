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

package Common;

/**
 *
 */
public class CTLCNST_DESC_STRUCT {
    int         type;
    iMineralSet price;
    int[]       depend = new int[2];
    CTCNSTCAP   caps;
    long        fparam;
    long        sparam;

    CTLCNST_DESC_STRUCT(
            int         _type,
            iMineralSet _price,
            int         _depend0,
            int         _depend1,
            CTCNSTCAP   _caps,
            long        _fparam,
            long        _sparam) {
        this.type       = _type;
        this.price      = _price;
        this.depend[0]  = _depend0;
        this.depend[1]  = _depend1;
        this.caps       = _caps;
        this.fparam     = _fparam;
        this.sparam     = _sparam;
    }
}
