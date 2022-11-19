/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class HealAction
/*    */   extends AbstractGameAction {
/*    */   public HealAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 10 */     setValues(target, source, amount);
/* 11 */     this.startDuration = this.duration;
/* 12 */     if (Settings.FAST_MODE) {
/* 13 */       this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*    */     }
/* 15 */     this.actionType = ActionType.HEAL;
/*    */   }
/*    */   
/*    */   public HealAction(AbstractCreature target, AbstractCreature source, int amount, float duration) {
/* 19 */     this(target, source, amount);
/* 20 */     this.duration = this.startDuration = duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == this.startDuration) {
/* 26 */       this.target.heal(this.amount);
/*    */     }
/*    */     
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\HealAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */