/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplyPowerToRandomEnemyAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractPower powerToApply;
/*    */   private boolean isFast;
/*    */   private AttackEffect effect;
/*    */   
/*    */   public ApplyPowerToRandomEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AttackEffect effect) {
/* 20 */     setValues(null, source, stackAmount);
/* 21 */     this.powerToApply = powerToApply;
/* 22 */     this.isFast = isFast;
/* 23 */     this.effect = effect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ApplyPowerToRandomEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast) {
/* 31 */     this(source, powerToApply, stackAmount, isFast, AttackEffect.NONE);
/*    */   }
/*    */   
/*    */   public ApplyPowerToRandomEnemyAction(AbstractCreature source, AbstractPower powerToApply, int stackAmount) {
/* 35 */     this(source, powerToApply, stackAmount, false);
/*    */   }
/*    */   
/*    */   public ApplyPowerToRandomEnemyAction(AbstractCreature source, AbstractPower powerToApply) {
/* 39 */     this(source, powerToApply, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     this.target = (AbstractCreature)AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
/* 45 */     this.powerToApply.owner = this.target;
/* 46 */     if (this.target != null) {
/* 47 */       addToTop(new ApplyPowerAction(this.target, this.source, this.powerToApply, this.amount, this.isFast, this.effect));
/*    */     }
/* 49 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ApplyPowerToRandomEnemyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */