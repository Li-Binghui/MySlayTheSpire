/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharSelectInfo
/*     */ {
/*     */   public String name;
/*     */   public String flavorText;
/*     */   public String hp;
/*     */   public int gold;
/*     */   public int currentHp;
/*     */   public int maxHp;
/*     */   public int maxOrbs;
/*     */   public int cardDraw;
/*     */   public int floorNum;
/*     */   public String levelName;
/*     */   public long saveDate;
/*     */   public AbstractPlayer player;
/*     */   public String deckString;
/*     */   public ArrayList<String> relics;
/*     */   public ArrayList<String> deck;
/*     */   public boolean resumeGame;
/*     */   public boolean isHardMode;
/*     */   
/*     */   public CharSelectInfo(String name, String flavorText, int currentHp, int maxHp, int maxOrbs, int gold, int cardDraw, AbstractPlayer player, ArrayList<String> relics, ArrayList<String> deck, boolean resumeGame) {
/*  54 */     this.name = name;
/*  55 */     this.flavorText = flavorText;
/*  56 */     this.currentHp = currentHp;
/*  57 */     this.maxHp = maxHp;
/*  58 */     this.maxOrbs = maxOrbs;
/*  59 */     this.hp = Integer.toString(currentHp) + "/" + Integer.toString(maxHp);
/*  60 */     this.gold = gold;
/*  61 */     this.cardDraw = cardDraw;
/*  62 */     this.relics = relics;
/*  63 */     this.deck = deck;
/*  64 */     this.player = player;
/*  65 */     this.resumeGame = resumeGame;
/*     */     
/*  67 */     if (!resumeGame) {
/*  68 */       setDeck();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CharSelectInfo(String name, String fText, int currentHp, int maxHp, int maxOrbs, int gold, int cardDraw, AbstractPlayer player, ArrayList<String> relics, ArrayList<String> deck, long saveDate, int floorNum, String levelName, boolean isHardMode) {
/* 106 */     this(name, fText, currentHp, maxHp, maxOrbs, gold, cardDraw, player, relics, deck, true);
/* 107 */     this.isHardMode = isHardMode;
/* 108 */     this.saveDate = saveDate;
/* 109 */     this.floorNum = floorNum;
/* 110 */     this.levelName = levelName;
/*     */   }
/*     */   
/*     */   private void setDeck() {
/* 114 */     this.deckString = createDeckInfoString(this.player.getStartingDeck());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String createDeckInfoString(ArrayList<String> deck) {
/* 125 */     HashMap<String, Integer> cards = new HashMap<>();
/*     */     
/* 127 */     for (String s : deck) {
/* 128 */       if (!cards.containsKey(s)) {
/* 129 */         cards.put(s, Integer.valueOf(1)); continue;
/*     */       } 
/* 131 */       cards.put(s, Integer.valueOf(((Integer)cards.get(s)).intValue() + 1));
/*     */     } 
/*     */ 
/*     */     
/* 135 */     StringBuilder sb = new StringBuilder();
/* 136 */     for (Map.Entry<String, Integer> c : cards.entrySet()) {
/* 137 */       sb.append("#b").append(c.getValue()).append(" ").append(c.getKey());
/* 138 */       if (((Integer)c.getValue()).intValue() > 1) {
/* 139 */         sb.append("s");
/*     */       }
/* 141 */       sb.append(", ");
/*     */     } 
/* 143 */     String retVal = sb.toString();
/*     */     
/* 145 */     if (retVal.length() > 80) {
/* 146 */       return "Click the deck icon to view starting cards.";
/*     */     }
/* 148 */     return retVal.substring(0, retVal.length() - 2);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\CharSelectInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */