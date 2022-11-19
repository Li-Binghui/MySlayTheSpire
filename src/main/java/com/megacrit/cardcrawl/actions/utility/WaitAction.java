/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class WaitAction
/*    */   extends AbstractGameAction {
/*    */   public WaitAction(float setDur) {
/*  9 */     setValues(null, null, 0);
/* 10 */     if (Settings.FAST_MODE && setDur > 0.1F) {
/* 11 */       this.duration = 0.1F;
/*    */     } else {
/* 13 */       this.duration = setDur;
/*    */     } 
/* 15 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\WaitAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */