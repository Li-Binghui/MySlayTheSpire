/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FeedAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int increaseHpAmount;
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.1F;
/*    */   
/*    */   public FeedAction(AbstractCreature target, DamageInfo info, int maxHPAmount) {
/* 23 */     this.info = info;
/* 24 */     setValues(target, info);
/* 25 */     this.increaseHpAmount = maxHPAmount;
/* 26 */     this.actionType = ActionType.DAMAGE;
/* 27 */     this.duration = 0.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     if (this.duration == 0.1F && 
/* 33 */       this.target != null) {
/* 34 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
/* 35 */       this.target.damage(this.info);
/*    */       
/* 37 */       if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && 
/* 38 */         !this.target.hasPower("Minion")) {
/* 39 */         AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);
/*    */ 
/*    */         
/* 42 */         if (this.target instanceof com.megacrit.cardcrawl.monsters.beyond.Donu) {
/* 43 */           UnlockTracker.unlockAchievement("DONUT");
/*    */         }
/*    */       } 
/*    */       
/* 47 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 48 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 53 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\FeedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */