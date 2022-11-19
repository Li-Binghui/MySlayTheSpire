/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class InstantKillAction
/*    */   extends AbstractGameAction {
/*    */   public InstantKillAction(AbstractCreature target) {
/* 10 */     this.source = null;
/* 11 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 16 */     this.target.currentHealth = 0;
/* 17 */     this.target.healthBarUpdatedEvent();
/* 18 */     this.target.damage(new DamageInfo(null, 0, DamageInfo.DamageType.HP_LOSS));
/* 19 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\InstantKillAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */