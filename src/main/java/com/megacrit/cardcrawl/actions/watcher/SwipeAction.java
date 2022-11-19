/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class SwipeAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   private boolean skipWait = false;
/*    */   
/*    */   public SwipeAction(AbstractCreature target, DamageInfo info) {
/* 19 */     this.info = info;
/* 20 */     setValues(target, info);
/* 21 */     this.actionType = ActionType.DAMAGE;
/* 22 */     this.attackEffect = AttackEffect.SLASH_VERTICAL;
/* 23 */     this.duration = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 28 */     if (shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
/* 29 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 33 */     if (this.duration == Settings.ACTION_DUR_XFAST) {
/* 34 */       if (this.info.type != DamageInfo.DamageType.THORNS && (
/* 35 */         this.info.owner.isDying || this.info.owner.halfDead)) {
/* 36 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 40 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, false));
/*    */     } 
/*    */     
/* 43 */     tickDuration();
/*    */     
/* 45 */     if (this.isDone) {
/* 46 */       if (this.attackEffect == AttackEffect.POISON) {
/* 47 */         this.target.tint.color.set(Color.CHARTREUSE.cpy());
/* 48 */         this.target.tint.changeColor(Color.WHITE.cpy());
/* 49 */       } else if (this.attackEffect == AttackEffect.FIRE) {
/* 50 */         this.target.tint.color.set(Color.RED);
/* 51 */         this.target.tint.changeColor(Color.WHITE.cpy());
/*    */       } 
/* 53 */       this.target.damage(this.info);
/*    */       
/* 55 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 56 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */       
/* 59 */       if (!this.skipWait && !Settings.FAST_MODE)
/* 60 */         addToTop((AbstractGameAction)new WaitAction(0.1F)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\SwipeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */