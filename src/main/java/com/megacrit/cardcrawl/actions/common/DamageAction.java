/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.GainPennyEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ 
/*     */ public class DamageAction
/*     */   extends AbstractGameAction {
/*     */   private DamageInfo info;
/*  17 */   private int goldAmount = 0;
/*     */   private static final float DURATION = 0.1F;
/*     */   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*     */   private boolean skipWait = false, muteSfx = false;
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect) {
/*  23 */     this.info = info;
/*  24 */     setValues(target, info);
/*  25 */     this.actionType = ActionType.DAMAGE;
/*  26 */     this.attackEffect = effect;
/*  27 */     this.duration = 0.1F;
/*     */   }
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info, int stealGoldAmount) {
/*  31 */     this(target, info, AttackEffect.SLASH_DIAGONAL);
/*  32 */     this.goldAmount = stealGoldAmount;
/*     */   }
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info) {
/*  36 */     this(target, info, AttackEffect.NONE);
/*     */   }
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info, boolean superFast) {
/*  40 */     this(target, info, AttackEffect.NONE);
/*  41 */     this.skipWait = superFast;
/*     */   }
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean superFast) {
/*  45 */     this(target, info, effect);
/*  46 */     this.skipWait = superFast;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageAction(AbstractCreature target, DamageInfo info, AttackEffect effect, boolean superFast, boolean muteSfx) {
/*  55 */     this(target, info, effect, superFast);
/*  56 */     this.muteSfx = muteSfx;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  61 */     if (shouldCancelAction() && this.info.type != DamageInfo.DamageType.THORNS) {
/*  62 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     if (this.duration == 0.1F) {
/*     */       
/*  68 */       if (this.info.type != DamageInfo.DamageType.THORNS && (
/*  69 */         this.info.owner.isDying || this.info.owner.halfDead)) {
/*  70 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  74 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, this.muteSfx));
/*     */       
/*  76 */       if (this.goldAmount != 0) {
/*  77 */         stealGold();
/*     */       }
/*     */     } 
/*     */     
/*  81 */     tickDuration();
/*     */     
/*  83 */     if (this.isDone) {
/*  84 */       if (this.attackEffect == AttackEffect.POISON) {
/*  85 */         this.target.tint.color.set(Color.CHARTREUSE.cpy());
/*  86 */         this.target.tint.changeColor(Color.WHITE.cpy());
/*  87 */       } else if (this.attackEffect == AttackEffect.FIRE) {
/*  88 */         this.target.tint.color.set(Color.RED);
/*  89 */         this.target.tint.changeColor(Color.WHITE.cpy());
/*     */       } 
/*  91 */       this.target.damage(this.info);
/*     */       
/*  93 */       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
/*  94 */         AbstractDungeon.actionManager.clearPostCombatActions();
/*     */       }
/*  96 */       if (!this.skipWait && !Settings.FAST_MODE) {
/*  97 */         addToTop((AbstractGameAction)new WaitAction(0.1F));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stealGold() {
/* 106 */     if (this.target.gold == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 111 */     CardCrawlGame.sound.play("GOLD_JINGLE");
/* 112 */     if (this.target.gold < this.goldAmount) {
/* 113 */       this.goldAmount = this.target.gold;
/*     */     }
/*     */     
/* 116 */     this.target.gold -= this.goldAmount;
/* 117 */     for (int i = 0; i < this.goldAmount; i++) {
/* 118 */       if (this.source.isPlayer) {
/* 119 */         AbstractDungeon.effectList.add(new GainPennyEffect(this.target.hb.cX, this.target.hb.cY));
/*     */       } else {
/* 121 */         AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, false));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\DamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */