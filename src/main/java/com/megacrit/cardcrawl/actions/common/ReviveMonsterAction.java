/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.monsters.beyond.SnakeDagger;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.vfx.TintEffect;
/*    */ 
/*    */ public class ReviveMonsterAction
/*    */   extends AbstractGameAction {
/*    */   private boolean healingEffect;
/*    */   
/*    */   public ReviveMonsterAction(AbstractMonster target, AbstractCreature source, boolean healEffect) {
/* 18 */     setValues((AbstractCreature)target, source, 0);
/* 19 */     this.actionType = ActionType.SPECIAL;
/* 20 */     if (AbstractDungeon.player.hasRelic("Philosopher's Stone")) {
/* 21 */       target.addPower((AbstractPower)new StrengthPower((AbstractCreature)target, 1));
/*    */     }
/*    */     
/* 24 */     this.healingEffect = healEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == 0.5F && 
/* 30 */       this.target instanceof AbstractMonster) {
/* 31 */       this.target.isDying = false;
/* 32 */       this.target.heal(this.target.maxHealth, this.healingEffect);
/* 33 */       this.target.healthBarRevivedEvent();
/* 34 */       ((AbstractMonster)this.target).deathTimer = 0.0F;
/* 35 */       ((AbstractMonster)this.target).tint = new TintEffect();
/* 36 */       ((AbstractMonster)this.target).tintFadeOutCalled = false;
/* 37 */       ((AbstractMonster)this.target).isDead = false;
/* 38 */       this.target.powers.clear();
/*    */       
/* 40 */       if (this.target instanceof SnakeDagger) {
/* 41 */         ((SnakeDagger)this.target).firstMove = true;
/* 42 */         ((SnakeDagger)this.target).initializeAnimation();
/*    */       } 
/*    */       
/* 45 */       if (this.target instanceof AbstractMonster) {
/* 46 */         for (AbstractRelic r : AbstractDungeon.player.relics) {
/* 47 */           r.onSpawnMonster((AbstractMonster)this.target);
/*    */         }
/*    */       }
/*    */       
/* 51 */       ((AbstractMonster)this.target).intent = AbstractMonster.Intent.NONE;
/* 52 */       ((AbstractMonster)this.target).rollMove();
/*    */     } 
/*    */ 
/*    */     
/* 56 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ReviveMonsterAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */