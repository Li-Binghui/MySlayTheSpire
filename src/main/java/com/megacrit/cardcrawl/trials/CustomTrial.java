/*     */ package com.megacrit.cardcrawl.trials;
/*     */ 
/*     */

import com.megacrit.cardcrawl.characters.AbstractPlayer;

import java.util.ArrayList;
import java.util.List;
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
/*     */ public class CustomTrial
/*     */   extends AbstractTrial
/*     */ {
/*     */   private boolean isKeepingStarterRelic = true;
/*  23 */   private ArrayList<String> relicIds = new ArrayList<>();
/*     */   private boolean isKeepingStarterCards = true;
/*  25 */   private ArrayList<String> cardIds = new ArrayList<>();
/*  26 */   private ArrayList<String> dailyModIds = new ArrayList<>();
/*     */   private boolean useRandomDailyMods;
/*  28 */   private Integer maxHpOverride = null;
/*     */ 
/*     */   
/*     */   public void setMaxHpOverride(int maxHp) {
/*  32 */     this.maxHpOverride = Integer.valueOf(maxHp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStarterCards(List<String> moreCardIds) {
/*  42 */     this.cardIds.addAll(moreCardIds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStarterCards(List<String> starterCards) {
/*  52 */     this.cardIds.clear();
/*  53 */     this.cardIds.addAll(starterCards);
/*  54 */     this.isKeepingStarterCards = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStarterRelic(String relicId) {
/*  63 */     this.relicIds.add(relicId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStarterRelics(List<String> moreRelics) {
/*  73 */     this.relicIds.addAll(moreRelics);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStarterRelics(List<String> starterRelics) {
/*  83 */     this.relicIds.clear();
/*  84 */     this.relicIds.addAll(starterRelics);
/*  85 */     this.isKeepingStarterRelic = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldKeepStarterRelic(boolean shouldKeep) {
/*  94 */     this.isKeepingStarterRelic = shouldKeep;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDailyMod(String modId) {
/* 103 */     this.dailyModIds.add(modId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDailyMods(List<String> moreDailyMods) {
/* 113 */     this.dailyModIds.addAll(moreDailyMods);
/*     */   }
/*     */   
/*     */   public void setRandomDailyMods() {
/* 117 */     this.useRandomDailyMods = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractPlayer setupPlayer(AbstractPlayer player) {
/* 124 */     if (this.maxHpOverride != null) {
/* 125 */       player.maxHealth = this.maxHpOverride.intValue();
/* 126 */       player.currentHealth = this.maxHpOverride.intValue();
/*     */     } 
/* 128 */     return player;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean keepStarterRelic() {
/* 133 */     return this.isKeepingStarterRelic;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> extraStartingRelicIDs() {
/* 138 */     return this.relicIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean keepsStarterCards() {
/* 143 */     return this.isKeepingStarterCards;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> extraStartingCardIDs() {
/* 148 */     return this.cardIds;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean useRandomDailyMods() {
/* 153 */     return this.useRandomDailyMods;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> dailyModIDs() {
/* 158 */     return this.dailyModIds;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\trials\CustomTrial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */