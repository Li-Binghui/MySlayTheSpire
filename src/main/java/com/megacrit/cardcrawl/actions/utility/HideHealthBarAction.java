/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class HideHealthBarAction extends AbstractGameAction {
/*    */   public HideHealthBarAction(AbstractCreature owner) {
/*  8 */     this.actionType = ActionType.WAIT;
/*  9 */     this.source = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 14 */     this.source.hideHealthBar();
/* 15 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\HideHealthBarAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */