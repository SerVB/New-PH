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
package Game.player;

/**
 *
 */
public class iPlayer {
    
    typedef iIList<iHero>                iHeroList;
    typedef iHeroList.Iterator            iHLIt;
    typedef iHeroList.ConstIterator    iCHLIt;
    typedef iIList<iOwnCnst>            iCnstList;
    typedef iCnstList.Iterator            iCLIt;
    typedef iIList<iCastle>                iCastleList;
    typedef iCastleList.Iterator        iCtLIt;
    typedef iCastleList.ConstIterator    iCCtLIt;

    typedef iMapT<sint8> iPassMap;

    public iPlayer(PLAYER_TYPE_MASK playerTypeMask, PLAYER_ID playerId, NATION_TYPE nation, const iSize& map_siz);
    public virtual ~iPlayer();

    public friend class iGameWorld;
    
    // virtuals
    public virtual void Process(fix32 t);
    public virtual inline PLAYER_TYPE PlayerType() const { return PT_HUMAN; }

    //
    public void NewDay();
    public void NewWeek(const iWeekDesc& weekDesk);
    public virtual void OnSelect(bool bNewWeek, bool bAfterLoad);
    public virtual void OnCaptureCastle(iCastle* pNewCastle);

    public iOwnCnst* AddCnst(iOwnCnst* pCnst, bool bUpdateFog = true);

    public inline PLAYER_TYPE_MASK PlayerTypeMask() const { return m_playerTypeMask; }
    public inline NATION_TYPE Nation() const { return m_Nation; }
    public inline PLAYER_ID PlayerId() const { return m_PlayerId; }
    public inline const iMineralSet& Minerals() const { return m_Minerals; }
    public inline iMineralSet& Minerals() { return m_Minerals; }
    public inline sint32 Mineral(MINERAL_TYPE mtype) const    { return m_Minerals.quant[mtype]; }
    public sint32 AddMineral(MINERAL_TYPE mtype, sint32 count, bool bShowMessage=false);
    public inline const iFogMap& FogMap()  const { return m_FogMap; }
    public inline iFogMap& FogMap()  { return m_FogMap; }
    public inline iPassMap& PassMap() { return m_passMap; }
    public inline const iVisItemList& VisItems() const { return m_visItems; }
    public inline const iVisItemList& NewVisItems() const { return m_newVisItems; }
    public inline const iVisItemList& OldVisItems() const { return m_oldVisItems; }

    public sint32 GetMarketIdx();
    public void UpdateFogMap();
    public bool UpdateVisItems();
    public void ResetVistItemChanges();
    public void UpdatePassMap();
    public virtual bool NeedStopHero() const;

    // Tavern Visitor
    public void UpdateTavernVisitor(iHero* pNewVisitor = NULL);
    public iHero* RecruitTavernVisitor(iCastle* pTarget);
    public inline const iHero* TavernVisitor() const { return m_pTavernVisitor; }

    // Keys
    public bool HasKey(uint8 key) const { check(key<6); return (m_keys & (1<<key)) > 0; }
    public void AddKey(uint8 key) { check(key<6); m_keys  |= (1<<key); }
    public uint8 GetKeys() const { return m_keys; }
    public void SetKeys(uint8 keys) { m_keys = keys; }

    // 
    public inline bool DefeatState() { return HeroCount()>0 || CastleCount()>0; }

    // Castles
    public iCastle* NextCastle();
    public iCastle* PrevCastle();
    public void SetCurCastle(iCastle* pCastle);
    public iCastle* AddCastle(iCastle* pCastle, bool bUpdateFog = true);
    public iCastle* RemoveCastle(iCastle* pCastle);
    public uint32 CurCastleIdx() const;

    public inline iCastle* CurCastle() { return m_pCurCastle; }
    public inline uint32 CastleCount() const { return m_Castles.Count(); }
    public inline iCtLIt CastleFirst() { return m_Castles.First(); }
    public inline iCtLIt CastleEnd() { return m_Castles.End(); }

    // Heroes
    public iHero* NextHero();
    public iHero* PrevHero();
    public void PrvSetCurHero(uint16 hid);
    public void SetCurHero(iHero* pHero);
    public iHero* AddHero(iHero* pHero, bool bUpdateFog = true);
    public iHero* RemoveHero(iHero* pHero);
    public uint32 CurHeroIdx() const;

    public inline iHero* CurHero()    { return m_pCurHero; }
    public inline const iHero* CurHero() const { return m_pCurHero; }
    public inline uint32 HeroCount() const    { return m_Heroes.Count(); }
    public inline iCHLIt HeroFirst() const { return m_Heroes.First(); }
    public inline iHLIt HeroFirst() { return m_Heroes.First(); }
    public inline iCHLIt HeroEnd() const { return m_Heroes.End(); }
    public inline iHLIt HeroEnd() { return m_Heroes.End(); }

    // FurtSkills
    public inline const iFurtSkills& FurtSkills() const { return m_furtSkills; }
    public inline iFurtSkills& FurtSkills() { return m_furtSkills; }

    // Puzzle
    public uint16 OpenObelisk( );
    public uint8 OpenedPuzzles() const;
    public uint8 TotalPuzzles() const { return 45; }
    public uint8 PuzzledCard(uint8 idx) const;
    
    public iCnstList    m_Cnsts;


    protected PLAYER_TYPE_MASK    m_playerTypeMask;
    protected iVisItemList        m_visItems;
    protected iVisItemList        m_newVisItems;
    protected iVisItemList        m_oldVisItems;
    protected iPassMap            m_passMap;
    protected bool                m_bDirtPassMap;
    protected iFurtSkills            m_furtSkills;

    protected iHeroList            m_Heroes;
    protected iHero*                m_pCurHero;

    protected iHero*                m_pTavernVisitor;
                        
    protected iCastleList            m_Castles;
    protected iCastle*            m_pCurCastle;
    protected iMineralSet            m_Minerals;
    protected PLAYER_ID            m_PlayerId;
    protected iFogMap                m_FogMap;
    protected NATION_TYPE            m_Nation;

    protected uint8                m_keys;

    protected uint16                m_openedObelisks;
    protected uint8                m_puzzleCards[45];
    
}
