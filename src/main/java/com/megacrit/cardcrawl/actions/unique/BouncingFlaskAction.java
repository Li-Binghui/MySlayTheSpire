/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PoisonPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
/*    */ 
/*    */ public class BouncingFlaskAction extends AbstractGameAction {
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   
/*    */   public BouncingFlaskAction(AbstractCreature target, int amount, int numTimes) {
/* 19 */     this.target = target;
/* 20 */     this.actionType = ActionType.DEBUFF;
/* 21 */     this.duration = 0.01F;
/* 22 */     this.numTimes = numTimes;
/* 23 */     this.amount = amount;
/*    */   }
/*    */   private int numTimes; private int amount;
/*    */   
/*    */   public void update() {
/* 28 */     if (this.target == null) {
/* 29 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 33 */     if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 34 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 35 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 40 */     if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 41 */       this.numTimes--;
/* 42 */       AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
/*    */ 
/*    */ 
/*    */       
/* 46 */       addToTop(new BouncingFlaskAction((AbstractCreature)randomMonster, this.amount, this.numTimes));
/* 47 */       addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionBounceEffect(this.target.hb.cX, this.target.hb.cY, randomMonster.hb.cX, randomMonster.hb.cY), 0.4F));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 53 */     if (this.target.currentHealth > 0) {
/* 54 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new PoisonPower(this.target, (AbstractCreature)AbstractDungeon.player, this.amount), this.amount, true, AttackEffect.POISON));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 62 */       addToTop((AbstractGameAction)new WaitAction(0.1F));
/*    */     } 
/*    */     
/* 65 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\BouncingFlaskAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */