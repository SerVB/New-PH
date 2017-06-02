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

import java.util.ArrayList;

/**
 * Treasury variants container.
 */
public class iTrVarCtr {

    private ArrayList<Item> m_items = new ArrayList();

    public void AddVariant(long probability) {
        m_items.add(new Item(probability));
    }

    public Item GetLastVariant() {
        return m_items.get(m_items.size() - 1);
    }

    public Item GetVariant(int idx) {
        return m_items.get(idx);
    }

    public int VariantsCount() {
        return m_items.size();
    }

    public void Serialize(iDynamicBuffer dbuff) {
        int quant = m_items.size();
        dbuff.Write(quant);
        for (Item item : m_items) {
            dbuff.Write((int) item.probability);
            serialize.Serialize(dbuff, item.guards);
            serialize.Serialize(dbuff, item.rewards);
        }
    }

    public void Unserialize(iDynamicBuffer dbuff) {
        m_items.clear();
        int quant;
        quant = (int)dbuff.Read();
        while (quant-- > 0) {
            long prob;
            prob = dbuff.Read();
            m_items.add(new Item(prob));
            serialize.Unserialize(dbuff, m_items.get(m_items.size()-1).guards);
            serialize.Unserialize(dbuff, m_items.get(m_items.size()-1).rewards);
        }
    }
}
