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
package Game;

import Constants.WeekDesc;
import Constants.*;

/**
 * Week descriptor.
 */
public class iWeekDesc {

    public void Init(boolean _bNewMonth, int _wkType, int _crType, int _fparam) {
        this.bNewMonth = _bNewMonth;
        this.wkType = _wkType;
        this.crType = _crType;
        this.fparam = _fparam;
    }

    public void Init(boolean _bNewMonth, int _wkType, int _crType) {
        Init(_bNewMonth, _wkType, _crType, 0);
    }

    public void Init(boolean _bNewMonth, int _wkType) {
        Init(_bNewMonth, _wkType, CREAT.UNKNOWN);
    }

    public void Init(boolean _bNewMonth) {
        Init(_bNewMonth, WeekDesc.Normal);
    }

    public void Init() {
        Init(false);
    }

    public boolean  bNewMonth;
    public int      wkType;
    public int      crType;
    public int      fparam;
}
