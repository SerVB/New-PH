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
 * Описатель заклинания.
 * Spell descriptor.
 * ( 8 + 8 x 4 = 40 bytes )
 */
public class SPELL_DESC_STRUCT {

    /**
     * Тип.
     */
    public long type;

    /**
     * Уровень.
     */
    public long level;

    /**
     * Школа.
     */
    public long school;

    /**
     *
     */
    public long spClass;

    /**
     * Стоимость.
     */
    public long bcost;

    /**
     *
     */
    public long label;

    /**
     *
     */
    public long reserved;

    /**
     * Хранилище эффектов.
     */
    public SPELL_EFFECT_DESC[] eff = new SPELL_EFFECT_DESC[cm_magic.MSL_COUNT];

    /**
     * Конструктор.
     * @param type Тип.
     * @param level Уровень.
     * @param school Школа.
     * @param spClass
     * @param bcost Стоимость.
     * @param label
     * @param reserved
     * @param eff0 Эффект 0.
     * @param eff1 Эффект 1.
     * @param eff2 Эффект 2.
     * @param eff3 Эффект 3.
     */
    public SPELL_DESC_STRUCT(
            long type,
            long level,
            long school,
            long spClass,
            long bcost,
            long label,
            long reserved,

            SPELL_EFFECT_DESC eff0,
            SPELL_EFFECT_DESC eff1,
            SPELL_EFFECT_DESC eff2,
            SPELL_EFFECT_DESC eff3
    ) {
        this.type = type;
        this.level = level;
        this.school = school;
        this.spClass = spClass;
        this.bcost = bcost;
        this.label = label;
        this.reserved = reserved;

        this.eff[0] = eff0;
        this.eff[1] = eff1;
        this.eff[2] = eff2;
        this.eff[3] = eff3;
    }

}
