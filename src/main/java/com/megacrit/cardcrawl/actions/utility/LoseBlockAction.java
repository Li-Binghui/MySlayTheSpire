/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class LoseBlockAction
/*    */   extends AbstractGameAction {
/*    */   public LoseBlockAction(AbstractCreature target, AbstractCreature source, int amount) {
/*  9 */     setValues(target, source, amount);
/* 10 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     if (this.duration == 0.5F) {
/* 16 */       if (this.target.currentBlock == 0) {
/* 17 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 21 */       this.target.loseBlock(this.amount);
/*    */     } 
/*    */     
/* 24 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\LoseBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */