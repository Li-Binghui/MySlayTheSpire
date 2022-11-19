/*     */ package com.megacrit.cardcrawl.actions.defect;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DamageAllButOneEnemyAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   public int[] damage;
/*     */   private boolean firstFrame = true;
/*     */   
/*     */   public DamageAllButOneEnemyAction(AbstractCreature source, AbstractCreature target, int[] amount, DamageInfo.DamageType type, AttackEffect effect, boolean isFast) {
/*  25 */     setValues(target, source, amount[0]);
/*  26 */     this.damage = amount;
/*  27 */     this.actionType = ActionType.DAMAGE;
/*  28 */     this.damageType = type;
/*  29 */     this.attackEffect = effect;
/*  30 */     if (isFast) {
/*  31 */       this.duration = Settings.ACTION_DUR_XFAST;
/*     */     } else {
/*  33 */       this.duration = Settings.ACTION_DUR_FAST;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageAllButOneEnemyAction(AbstractCreature source, AbstractCreature target, int[] amount, DamageInfo.DamageType type, AttackEffect effect) {
/*  43 */     this(source, target, amount, type, effect, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  48 */     if (this.firstFrame) {
/*  49 */       boolean playedMusic = false;
/*  50 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/*     */       
/*  52 */       for (int i = 0; i < temp; i++) {
/*  53 */         if ((AbstractDungeon.getCurrRoom()).monsters.monsters.get(i) != this.target && 
/*  54 */           !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDying && ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters
/*  55 */           .get(i)).currentHealth > 0 && !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isEscaping)
/*     */         {
/*  57 */           if (playedMusic) {
/*  58 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                   
/*  60 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  61 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
/*     */           }
/*     */           else {
/*     */             
/*  65 */             playedMusic = true;
/*  66 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                   
/*  68 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  69 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect));
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/*  74 */       this.firstFrame = false;
/*     */     } 
/*     */     
/*  77 */     tickDuration();
/*     */     
/*  79 */     if (this.isDone) {
/*  80 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/*  81 */         p.onDamageAllEnemies(this.damage);
/*     */       }
/*     */       
/*  84 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/*  85 */       for (int i = 0; i < temp; i++) {
/*  86 */         if ((AbstractDungeon.getCurrRoom()).monsters.monsters.get(i) != this.target && 
/*  87 */           !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDeadOrEscaped()) {
/*  88 */           if (this.attackEffect == AttackEffect.POISON) {
/*  89 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.color = Color.CHARTREUSE.cpy();
/*  90 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
/*  91 */           } else if (this.attackEffect == AttackEffect.FIRE) {
/*  92 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.color = Color.RED.cpy();
/*  93 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
/*     */           } 
/*  95 */           DamageInfo info = new DamageInfo(this.source, this.damage[i], this.damageType);
/*  96 */           info.applyPowers(this.source, (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i));
/*  97 */           ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).damage(info);
/*     */         } 
/*     */ 
/*     */         
/* 101 */         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 102 */           AbstractDungeon.actionManager.clearPostCombatActions();
/*     */         }
/* 104 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\DamageAllButOneEnemyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */