/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class ReduceCostForTurnAction extends AbstractGameAction {
/*    */   private AbstractCard targetCard;
/*    */   
/*    */   public ReduceCostForTurnAction(AbstractCard card, int amt) {
/* 11 */     this.targetCard = card;
/* 12 */     this.amount = amt;
/* 13 */     this.startDuration = Settings.ACTION_DUR_FASTER;
/* 14 */     this.duration = this.startDuration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (this.duration == this.startDuration && 
/* 20 */       this.targetCard.costForTurn > 0) {
/* 21 */       this.targetCard.setCostForTurn(this.targetCard.costForTurn - this.amount);
/*    */     }
/*    */ 
/*    */     
/* 25 */     tickDuration();
/* 26 */     if (Settings.FAST_MODE)
/* 27 */       this.isDone = true; 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ReduceCostForTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */