/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ public class SetDontTriggerAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   private boolean trigger;
/*    */   
/*    */   public SetDontTriggerAction(AbstractCard card, boolean dontTrigger) {
/* 11 */     this.card = card;
/* 12 */     this.trigger = dontTrigger;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     this.card.dontTriggerOnUseCard = this.trigger;
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\SetDontTriggerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */