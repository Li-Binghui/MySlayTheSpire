/*    */ package com.megacrit.cardcrawl.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class IntentFlashAction extends AbstractGameAction {
/*    */   private AbstractMonster m;
/*    */   
/*    */   public IntentFlashAction(AbstractMonster m) {
/* 10 */     if (Settings.FAST_MODE) {
/* 11 */       this.startDuration = Settings.ACTION_DUR_MED;
/*    */     } else {
/* 13 */       this.startDuration = Settings.ACTION_DUR_XLONG;
/*    */     } 
/*    */     
/* 16 */     this.duration = this.startDuration;
/* 17 */     this.m = m;
/* 18 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startDuration) {
/* 24 */       this.m.flashIntent();
/*    */     }
/*    */     
/* 27 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\IntentFlashAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */