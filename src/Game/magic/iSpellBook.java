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
package Game.magic;

import collections.extended.iSpellList;
import entries.iSpellEntry;
import utils.tracer;

/**
 * Spell Book.
 */
public class iSpellBook {
    
    public iSpellBook() {}
    
    public void Add(final int spellId){
        m_spells.AddUnique(spellId);
    }
    
    public void Add(final iSpellList spells){
        for (iSpellEntry spell : spells) {
            Add(spell.id);
        }
    }
    
    public void AddFavorite(final int spellId){
        m_favorites.AddUnique(spellId);
    }
    
    public iBaseSpell SpellByIdx(final int idx) {
        tracer.check(idx < m_spells.size());
        tracer.check(m_spells.get(idx) < gGame.ItemMgr().m_spellMgr.SpellsCount());
        
        return gGame.ItemMgr().m_spellMgr.Spell(m_spells.get(idx).id);
    }
    
    public iBaseSpell FavoriteByIdx(final int idx) {
        tracer.check(idx < m_favorites.size());
        tracer.check(m_favorites.get(idx) < gGame.ItemMgr().m_spellMgr.SpellsCount());
        
        return gGame.ItemMgr().m_spellMgr.Spell(m_favorites.get(idx).id);
    }
    
    public int Spell(final int idx) {
        return m_spells.get(idx).id;
    }
    
    public int Favorite(final int idx) {
        return m_favorites.get(idx).id;
    }
    
    public void Reset() {
        m_spells.clear();
    }

    public int SpellsCount() {
        return m_spells.size();
    }
    
    public iSpellList Spells() {
        return m_spells;
    }

    public int FavoritesCount() {
        return m_favorites.size();
    }
    
    public iSpellList Favorites() {
        return m_favorites;
    }

    public boolean HasSpell(final int spellId) {
        return m_spells.Find(spellId) != -1;
    }
    
    private iSpellList    m_spells;
    private iSpellList    m_favorites;
    
}
