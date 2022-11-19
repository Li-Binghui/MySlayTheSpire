/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ public class UpdateCardDescriptionAction extends AbstractGameAction {
/*    */   private AbstractCard targetCard;
/*    */   
/*    */   public UpdateCardDescriptionAction(AbstractCard targetCard) {
/* 10 */     this.targetCard = targetCard;
/* 11 */     this.actionType = ActionType.TEXT;
/* 12 */     this.duration = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == 0.5F) {
/* 18 */       this.targetCard.initializeDescription();
/*    */     }
/*    */     
/* 21 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\UpdateCardDescriptionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */