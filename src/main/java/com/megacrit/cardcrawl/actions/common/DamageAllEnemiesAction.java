/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
/*     */ public class DamageAllEnemiesAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   public int[] damage;
/*     */   private int baseDamage;
/*     */   private boolean firstFrame = true, utilizeBaseDamage = false;
/*     */   
/*     */   public DamageAllEnemiesAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect, boolean isFast) {
/*  26 */     this.source = source;
/*  27 */     this.damage = amount;
/*  28 */     this.actionType = ActionType.DAMAGE;
/*  29 */     this.damageType = type;
/*  30 */     this.attackEffect = effect;
/*  31 */     if (isFast) {
/*  32 */       this.duration = Settings.ACTION_DUR_XFAST;
/*     */     } else {
/*  34 */       this.duration = Settings.ACTION_DUR_FAST;
/*     */     } 
/*     */   }
/*     */   
/*     */   public DamageAllEnemiesAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect) {
/*  39 */     this(source, amount, type, effect, false);
/*     */   }
/*     */   
/*     */   public DamageAllEnemiesAction(AbstractPlayer player, int baseDamage, DamageInfo.DamageType type, AttackEffect effect) {
/*  43 */     this((AbstractCreature)player, (int[])null, type, effect, false);
/*  44 */     this.baseDamage = baseDamage;
/*  45 */     this.utilizeBaseDamage = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  50 */     if (this.firstFrame) {
/*  51 */       boolean playedMusic = false;
/*  52 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/*  53 */       if (this.utilizeBaseDamage) {
/*  54 */         this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
/*     */       }
/*     */       
/*  57 */       for (int i = 0; i < temp; i++) {
/*  58 */         if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDying && 
/*  59 */           ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).currentHealth > 0 && 
/*  60 */           !((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isEscaping) {
/*  61 */           if (playedMusic) {
/*  62 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                   
/*  64 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  65 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect, true));
/*     */           }
/*     */           else {
/*     */             
/*  69 */             playedMusic = true;
/*  70 */             AbstractDungeon.effectList.add(new FlashAtkImgEffect(
/*     */                   
/*  72 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cX, 
/*  73 */                   ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).hb.cY, this.attackEffect));
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/*  78 */       this.firstFrame = false;
/*     */     } 
/*     */     
/*  81 */     tickDuration();
/*     */     
/*  83 */     if (this.isDone) {
/*  84 */       for (AbstractPower p : AbstractDungeon.player.powers) {
/*  85 */         p.onDamageAllEnemies(this.damage);
/*     */       }
/*     */       
/*  88 */       int temp = (AbstractDungeon.getCurrRoom()).monsters.monsters.size();
/*  89 */       for (int i = 0; i < temp; i++) {
/*  90 */         if (!((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).isDeadOrEscaped()) {
/*  91 */           if (this.attackEffect == AttackEffect.POISON) {
/*  92 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.color.set(Color.CHARTREUSE);
/*  93 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
/*  94 */           } else if (this.attackEffect == AttackEffect.FIRE) {
/*  95 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.color.set(Color.RED);
/*  96 */             ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
/*     */           } 
/*  98 */           ((AbstractMonster)(AbstractDungeon.getCurrRoom()).monsters.monsters.get(i)).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 103 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/* 104 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*     */       }
/* 106 */       if (!Settings.FAST_MODE)
/* 107 */         addToTop((AbstractGameAction)new WaitAction(0.1F)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DamageAllEnemiesAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */