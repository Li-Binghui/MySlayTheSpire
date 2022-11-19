/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class SuicideAction extends AbstractGameAction {
/*    */   private AbstractMonster m;
/*    */   private boolean relicTrigger;
/*    */   
/*    */   public SuicideAction(AbstractMonster target) {
/* 11 */     this(target, true);
/*    */   }
/*    */   
/*    */   public SuicideAction(AbstractMonster target, boolean triggerRelics) {
/* 15 */     this.duration = 0.0F;
/* 16 */     this.actionType = ActionType.DAMAGE;
/* 17 */     this.m = target;
/* 18 */     this.relicTrigger = triggerRelics;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == 0.0F) {
/* 24 */       this.m.gold = 0;
/* 25 */       this.m.currentHealth = 0;
/* 26 */       this.m.die(this.relicTrigger);
/* 27 */       this.m.healthBarUpdatedEvent();
/*    */     } 
/*    */     
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\SuicideAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */