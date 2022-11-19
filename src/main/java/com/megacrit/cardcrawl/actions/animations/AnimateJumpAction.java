/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class AnimateJumpAction extends AbstractGameAction {
/*    */   private boolean called = false;
/*    */   
/*    */   public AnimateJumpAction(AbstractCreature owner) {
/* 11 */     setValues(null, owner, 0);
/* 12 */     this.duration = Settings.ACTION_DUR_FAST;
/* 13 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (!this.called) {
/* 19 */       this.source.useJumpAnimation();
/* 20 */       this.called = true;
/*    */     } 
/* 22 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\AnimateJumpAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */