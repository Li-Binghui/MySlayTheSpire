/*    */ package com.megacrit.cardcrawl.cards;
/*    */ 
/*    */

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class CardQueueItem {
/*    */   public AbstractCard card;
/*    */   public AbstractMonster monster;
/*  9 */   public int energyOnUse = 0;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean ignoreEnergyTotal = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean autoplayCard = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean randomTarget = false;
/*    */ 
/*    */   
/*    */   public boolean isEndTurnAutoPlay = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public CardQueueItem() {
/* 29 */     this.card = null;
/* 30 */     this.monster = null;
/*    */   }
/*    */   
/*    */   public CardQueueItem(AbstractCard card, boolean isEndTurnAutoPlay) {
/* 34 */     this(card, (AbstractMonster)null);
/* 35 */     this.isEndTurnAutoPlay = isEndTurnAutoPlay;
/*    */   }
/*    */   
/*    */   public CardQueueItem(AbstractCard card, AbstractMonster monster) {
/* 39 */     this(card, monster, EnergyPanel.getCurrentEnergy(), false);
/*    */   }
/*    */   
/*    */   public CardQueueItem(AbstractCard card, AbstractMonster monster, int setEnergyOnUse) {
/* 43 */     this(card, monster, setEnergyOnUse, false);
/*    */   }
/*    */   
/*    */   public CardQueueItem(AbstractCard card, AbstractMonster monster, int setEnergyOnUse, boolean ignoreEnergyTotal) {
/* 47 */     this(card, monster, setEnergyOnUse, ignoreEnergyTotal, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CardQueueItem(AbstractCard card, AbstractMonster monster, int setEnergyOnUse, boolean ignoreEnergyTotal, boolean autoplayCard) {
/* 56 */     this.card = card;
/* 57 */     this.monster = monster;
/* 58 */     this.energyOnUse = setEnergyOnUse;
/* 59 */     this.ignoreEnergyTotal = ignoreEnergyTotal;
/* 60 */     this.autoplayCard = autoplayCard;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CardQueueItem(AbstractCard card, boolean randomTarget, int setEnergyOnUse, boolean ignoreEnergyTotal, boolean autoplayCard) {
/* 69 */     this(card, (AbstractMonster)null, setEnergyOnUse, ignoreEnergyTotal, autoplayCard);
/* 70 */     this.randomTarget = randomTarget;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\cards\CardQueueItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */