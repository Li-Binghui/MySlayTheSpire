/*    */ package com.megacrit.cardcrawl.relics;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class QuestionCard extends AbstractRelic {
/*    */   private static final int CARDS_ADDED = 1;
/*    */   public static final String ID = "Question Card";
/*    */   
/*    */   public QuestionCard() {
/* 11 */     super("Question Card", "questionCard.png", RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSpawn() {
/* 21 */     return (Settings.isEndless || AbstractDungeon.floorNum <= 48);
/*    */   }
/*    */ 
/*    */   
/*    */   public int changeNumberOfCardsInReward(int numberOfCards) {
/* 26 */     return numberOfCards + 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 31 */     return new QuestionCard();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\relics\QuestionCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */