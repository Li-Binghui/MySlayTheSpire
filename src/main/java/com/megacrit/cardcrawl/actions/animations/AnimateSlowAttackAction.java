/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class AnimateSlowAttackAction extends AbstractGameAction {
/*    */   private boolean called = false;
/*    */   
/*    */   public AnimateSlowAttackAction(AbstractCreature owner) {
/* 11 */     setValues(null, owner, 0);
/* 12 */     this.startDuration = 0.5F;
/* 13 */     this.duration = this.startDuration;
/* 14 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (!this.called) {
/* 20 */       if (Settings.FAST_MODE) {
/* 21 */         this.source.useFastAttackAnimation();
/* 22 */         this.duration = Settings.ACTION_DUR_FAST;
/*    */       } else {
/* 24 */         this.source.useSlowAttackAnimation();
/*    */       } 
/* 26 */       this.called = true;
/*    */     } 
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\AnimateSlowAttackAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */