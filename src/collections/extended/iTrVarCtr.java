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

package collections.extended;

import Common.Item;
import Common.iDynamicBuffer;
import utils.serialize;
import java.util.ArrayList;

/**
 * Treasury variants container.
 */
public class iTrVarCtr extends ArrayList<Item> {

    public void AddVariant(long probability) {
        super.add(new Item(probability));
    }

    public Item GetLastVariant() {
        return super.get(super.size() - 1);
    }

    public Item GetVariant(int idx) {
        return super.get(idx);
    }

    public int VariantsCount() {
        return super.size();
    }

    public void Serialize(iDynamicBuffer dbuff) {
        int quant = super.size();
        dbuff.Write(quant);
        for (Item item : super.subList(0, super.size())) {
            dbuff.Write((int) item.probability);
            serialize.Serialize(dbuff, item.guards);
            serialize.Serialize(dbuff, item.rewards);
        }
    }

    public void Unserialize(iDynamicBuffer dbuff) {
        super.clear();
        int quant;
        quant = (int)dbuff.Read();
        while (quant-- > 0) {
            long prob;
            prob = dbuff.Read();
            super.add(new Item(prob));
            serialize.Unserialize(dbuff, super.get(super.size()-1).guards);
            serialize.Unserialize(dbuff, super.get(super.size()-1).rewards);
        }
    }
}
