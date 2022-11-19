/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomizeHandCostAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 23 */       for (AbstractCard card : this.p.hand.group) {
/* 24 */         if (card.cost >= 0) {
/* 25 */           int newCost = AbstractDungeon.cardRandomRng.random(3);
/* 26 */           if (card.cost != newCost) {
/* 27 */             card.cost = newCost;
/* 28 */             card.costForTurn = card.cost;
/* 29 */             card.isCostModified = true;
/*    */           } 
/*    */         } 
/*    */       } 
/* 33 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 38 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RandomizeHandCostAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */