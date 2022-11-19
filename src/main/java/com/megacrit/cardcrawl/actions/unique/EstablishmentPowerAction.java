/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EstablishmentPowerAction
/*    */   extends AbstractGameAction {
/*    */   private int discountAmount;
/*    */   
/*    */   public EstablishmentPowerAction(int discountAmount) {
/* 12 */     this.discountAmount = discountAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 18 */       if (c.selfRetain || c.retain) {
/* 19 */         c.modifyCostForCombat(-this.discountAmount);
/*    */       }
/*    */     } 
/* 22 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\EstablishmentPowerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */