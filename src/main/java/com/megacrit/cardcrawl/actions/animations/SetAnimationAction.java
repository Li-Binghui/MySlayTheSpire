/*    */ package com.megacrit.cardcrawl.actions.animations;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class SetAnimationAction extends AbstractGameAction {
/*    */   private boolean called = false;
/*    */   private String animation;
/*    */   
/*    */   public SetAnimationAction(AbstractCreature owner, String animationName) {
/* 12 */     setValues(null, owner, 0);
/* 13 */     this.duration = Settings.ACTION_DUR_FAST;
/* 14 */     this.actionType = ActionType.WAIT;
/* 15 */     this.animation = animationName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (!this.called) {
/* 21 */       this.source.state.setAnimation(0, this.animation, false);
/* 22 */       this.called = true;
/* 23 */       this.source.state.addAnimation(0, "idle", true, 0.0F);
/*    */     } 
/* 25 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\animations\SetAnimationAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */