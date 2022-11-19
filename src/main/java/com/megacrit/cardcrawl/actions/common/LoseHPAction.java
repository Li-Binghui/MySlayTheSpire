/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class LoseHPAction
/*    */   extends AbstractGameAction {
/*    */   private static final float DURATION = 0.33F;
/*    */   
/*    */   public LoseHPAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 16 */     this(target, source, amount, AttackEffect.NONE);
/*    */   }
/*    */   
/*    */   public LoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect) {
/* 20 */     setValues(target, source, amount);
/* 21 */     this.actionType = ActionType.DAMAGE;
/* 22 */     this.attackEffect = effect;
/* 23 */     this.duration = 0.33F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (this.duration == 0.33F && this.target.currentHealth > 0) {
/* 29 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*    */     }
/*    */     
/* 32 */     tickDuration();
/*    */     
/* 34 */     if (this.isDone) {
/* 35 */       this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));
/*    */       
/* 37 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 38 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/* 40 */       if (!Settings.FAST_MODE)
/* 41 */         addToTop((AbstractGameAction)new WaitAction(0.1F)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\LoseHPAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */