/*    */ package com.megacrit.cardcrawl.blights;
/*    */ 
/*    */

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class TimeMaze extends AbstractBlight {
/*    */   public static final String ID = "TimeMaze";
/* 10 */   private static final BlightStrings blightStrings = CardCrawlGame.languagePack.getBlightString("TimeMaze");
/* 11 */   public static final String NAME = blightStrings.NAME; public static final String[] DESC = blightStrings.DESCRIPTION;
/*    */   private static final int CARD_AMT = 15;
/*    */   
/*    */   public TimeMaze() {
/* 15 */     super("TimeMaze", NAME, DESC[0] + '\017' + DESC[1], "maze.png", true);
/* 16 */     this.counter = 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayCard(AbstractCard card, AbstractMonster m) {
/* 21 */     if (this.counter < 15 && card.type != AbstractCard.CardType.CURSE) {
/* 22 */       this.counter++;
/* 23 */       if (this.counter >= 15) {
/* 24 */         flash();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlay(AbstractCard card) {
/* 31 */     if (this.counter >= 15 && card.type != AbstractCard.CardType.CURSE) {
/* 32 */       card.cantUseMessage = DESC[2] + '\017' + DESC[1];
/* 33 */       return false;
/*    */     } 
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 40 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 45 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 50 */     this.counter = 0;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\blights\TimeMaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */