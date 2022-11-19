/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*    */ 
/*    */ public class DarkOrbEvokeAction
/*    */   extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.1F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*    */   private boolean muteSfx = false;
/*    */   
/*    */   public DarkOrbEvokeAction(DamageInfo info, AttackEffect effect) {
/* 22 */     AbstractMonster weakestMonster = null;
/* 23 */     for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
/* 24 */       if (!m.isDeadOrEscaped()) {
/* 25 */         if (weakestMonster == null) {
/* 26 */           weakestMonster = m; continue;
/* 27 */         }  if (m.currentHealth < weakestMonster.currentHealth) {
/* 28 */           weakestMonster = m;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     this.info = info;
/* 34 */     setValues((AbstractCreature)weakestMonster, info);
/* 35 */     this.actionType = ActionType.DAMAGE;
/* 36 */     this.attackEffect = effect;
/* 37 */     this.duration = 0.1F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     if ((shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) || this.target == null) {
/* 43 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 47 */     if (this.duration == 0.1F) {
/* 48 */       this.info.output = AbstractOrb.applyLockOn(this.target, this.info.base);
/*    */ 
/*    */       
/* 51 */       if (this.info.type != DamageInfo.DamageType.THORNS && (
/* 52 */         this.info.owner.isDying || this.info.owner.halfDead)) {
/* 53 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 57 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));
/*    */     } 
/*    */     
/* 60 */     tickDuration();
/*    */     
/* 62 */     if (this.isDone) {
/* 63 */       if (this.attackEffect == AttackEffect.POISON) {
/* 64 */         this.target.tint.color = Color.CHARTREUSE.cpy();
/* 65 */         this.target.tint.changeColor(Color.WHITE.cpy());
/* 66 */       } else if (this.attackEffect == AttackEffect.FIRE) {
/* 67 */         this.target.tint.color = Color.RED.cpy();
/* 68 */         this.target.tint.changeColor(Color.WHITE.cpy());
/*    */       } 
/* 70 */       this.target.damage(this.info);
/*    */       
/* 72 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 73 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*    */       }
/*    */       
/* 76 */       if (!Settings.FAST_MODE)
/* 77 */         addToTop((AbstractGameAction)new WaitAction(0.1F)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DarkOrbEvokeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */