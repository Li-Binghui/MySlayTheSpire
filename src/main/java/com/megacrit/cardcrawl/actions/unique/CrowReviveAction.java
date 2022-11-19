/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.TintEffect;
/*    */ 
/*    */ public class CrowReviveAction
/*    */   extends AbstractGameAction {
/*    */   public CrowReviveAction(AbstractMonster target, AbstractCreature source) {
/* 11 */     setValues((AbstractCreature)target, source, 0);
/* 12 */     this.actionType = ActionType.SPECIAL;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (this.duration == 0.5F && 
/* 18 */       this.target instanceof AbstractMonster) {
/* 19 */       this.target.isDying = false;
/* 20 */       this.target.heal(this.target.maxHealth);
/* 21 */       this.target.healthBarRevivedEvent();
/* 22 */       ((AbstractMonster)this.target).deathTimer = 0.0F;
/* 23 */       ((AbstractMonster)this.target).tint = new TintEffect();
/* 24 */       ((AbstractMonster)this.target).tintFadeOutCalled = false;
/* 25 */       ((AbstractMonster)this.target).isDead = false;
/* 26 */       this.target.powers.clear();
/*    */     } 
/*    */ 
/*    */     
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\CrowReviveAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */