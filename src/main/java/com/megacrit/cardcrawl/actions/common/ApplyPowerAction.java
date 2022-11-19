/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;
/*     */ import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplyPowerAction
/*     */   extends AbstractGameAction
/*     */ {
/*  40 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ApplyPowerAction");
/*  41 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractPower powerToApply;
/*     */ 
/*     */ 
/*     */   
/*     */   private float startingDuration;
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AttackEffect effect) {
/*  54 */     if (Settings.FAST_MODE) {
/*  55 */       this.startingDuration = 0.1F;
/*     */     }
/*  57 */     else if (isFast) {
/*  58 */       this.startingDuration = Settings.ACTION_DUR_FASTER;
/*     */     } else {
/*  60 */       this.startingDuration = Settings.ACTION_DUR_FAST;
/*     */     } 
/*     */ 
/*     */     
/*  64 */     setValues(target, source, stackAmount);
/*     */     
/*  66 */     this.duration = this.startingDuration;
/*  67 */     this.powerToApply = powerToApply;
/*     */ 
/*     */     
/*  70 */     if (AbstractDungeon.player.hasRelic("Snake Skull") && source != null && source.isPlayer && target != source && powerToApply.ID
/*  71 */       .equals("Poison")) {
/*  72 */       AbstractDungeon.player.getRelic("Snake Skull").flash();
/*  73 */       this.powerToApply.amount++;
/*  74 */       this.amount++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (powerToApply.ID.equals("Corruption")) {
/*  80 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/*  81 */         if (c.type == AbstractCard.CardType.SKILL)
/*  82 */           c.modifyCostForCombat(-9); 
/*     */       } 
/*  84 */       for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/*  85 */         if (c.type == AbstractCard.CardType.SKILL)
/*  86 */           c.modifyCostForCombat(-9); 
/*     */       } 
/*  88 */       for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/*  89 */         if (c.type == AbstractCard.CardType.SKILL)
/*  90 */           c.modifyCostForCombat(-9); 
/*     */       } 
/*  92 */       for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/*  93 */         if (c.type == AbstractCard.CardType.SKILL) {
/*  94 */           c.modifyCostForCombat(-9);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     this.actionType = ActionType.POWER;
/* 100 */     this.attackEffect = effect;
/*     */     
/* 102 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 103 */       this.duration = 0.0F;
/* 104 */       this.startingDuration = 0.0F;
/* 105 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast) {
/* 115 */     this(target, source, powerToApply, stackAmount, isFast, AttackEffect.NONE);
/*     */   }
/*     */   
/*     */   public ApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply) {
/* 119 */     this(target, source, powerToApply, powerToApply.amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount) {
/* 127 */     this(target, source, powerToApply, stackAmount, false, AttackEffect.NONE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, AttackEffect effect) {
/* 136 */     this(target, source, powerToApply, stackAmount, false, effect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 141 */     if (this.target == null || this.target.isDeadOrEscaped()) {
/* 142 */       this.isDone = true;
/*     */       
/*     */       return;
/*     */     } 
/* 146 */     if (this.duration == this.startingDuration) {
/* 147 */       if (this.powerToApply instanceof com.megacrit.cardcrawl.powers.NoDrawPower && this.target.hasPower(this.powerToApply.ID)) {
/* 148 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/* 152 */       if (this.source != null) {
/* 153 */         for (AbstractPower pow : this.source.powers) {
/* 154 */           pow.onApplyPower(this.powerToApply, this.target, this.source);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 159 */       if (AbstractDungeon.player.hasRelic("Champion Belt") && this.source != null && this.source.isPlayer && this.target != this.source && this.powerToApply.ID
/* 160 */         .equals("Vulnerable") && !this.target.hasPower("Artifact"))
/*     */       {
/* 162 */         AbstractDungeon.player.getRelic("Champion Belt").onTrigger(this.target);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 167 */       if (this.target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster && 
/* 168 */         this.target.isDeadOrEscaped()) {
/* 169 */         this.duration = 0.0F;
/* 170 */         this.isDone = true;
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 176 */       if (AbstractDungeon.player.hasRelic("Ginger") && this.target.isPlayer && this.powerToApply.ID.equals("Weakened")) {
/*     */         
/* 178 */         AbstractDungeon.player.getRelic("Ginger").flash();
/* 179 */         addToTop((AbstractGameAction)new TextAboveCreatureAction(this.target, TEXT[1]));
/* 180 */         this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 186 */       if (AbstractDungeon.player.hasRelic("Turnip") && this.target.isPlayer && this.powerToApply.ID.equals("Frail")) {
/*     */         
/* 188 */         AbstractDungeon.player.getRelic("Turnip").flash();
/* 189 */         addToTop((AbstractGameAction)new TextAboveCreatureAction(this.target, TEXT[1]));
/* 190 */         this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 196 */       if (this.target.hasPower("Artifact") && 
/* 197 */         this.powerToApply.type == AbstractPower.PowerType.DEBUFF) {
/* 198 */         addToTop((AbstractGameAction)new TextAboveCreatureAction(this.target, TEXT[0]));
/* 199 */         this.duration -= Gdx.graphics.getDeltaTime();
/* 200 */         CardCrawlGame.sound.play("NULLIFY_SFX");
/* 201 */         this.target.getPower("Artifact").flashWithoutSound();
/* 202 */         this.target.getPower("Artifact").onSpecificTrigger();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 209 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*     */ 
/*     */ 
/*     */       
/* 213 */       boolean hasBuffAlready = false;
/* 214 */       for (AbstractPower p : this.target.powers) {
/* 215 */         if (p.ID.equals(this.powerToApply.ID) && !p.ID.equals("Night Terror")) {
/* 216 */           p.stackPower(this.amount);
/* 217 */           p.flash();
/*     */           
/* 219 */           if ((p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) && this.amount <= 0) {
/* 220 */             AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 225 */           else if (this.amount > 0) {
/* 226 */             if (p.type == AbstractPower.PowerType.BUFF || p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) {
/* 227 */               AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + 
/*     */ 
/*     */ 
/*     */                     
/* 231 */                     Integer.toString(this.amount) + " " + this.powerToApply.name));
/*     */             } else {
/* 233 */               AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + 
/*     */ 
/*     */ 
/*     */                     
/* 237 */                     Integer.toString(this.amount) + " " + this.powerToApply.name));
/*     */             }
/*     */           
/* 240 */           } else if (p.type == AbstractPower.PowerType.BUFF) {
/* 241 */             AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 247 */             AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 255 */           p.updateDescription();
/* 256 */           hasBuffAlready = true;
/* 257 */           AbstractDungeon.onModifyPower();
/*     */         } 
/*     */       } 
/*     */       
/* 261 */       if (this.powerToApply.type == AbstractPower.PowerType.DEBUFF) {
/* 262 */         this.target.useFastShakeAnimation(0.5F);
/*     */       }
/*     */       
/* 265 */       if (!hasBuffAlready) {
/* 266 */         this.target.powers.add(this.powerToApply);
/* 267 */         Collections.sort(this.target.powers);
/* 268 */         this.powerToApply.onInitialApplication();
/* 269 */         this.powerToApply.flash();
/*     */         
/* 271 */         if (this.amount < 0 && (this.powerToApply.ID.equals("Strength") || this.powerToApply.ID.equals("Dexterity") || this.powerToApply.ID
/* 272 */           .equals("Focus"))) {
/* 273 */           AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 279 */         else if (this.powerToApply.type == AbstractPower.PowerType.BUFF) {
/* 280 */           AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 286 */           AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 294 */         AbstractDungeon.onModifyPower();
/*     */ 
/*     */         
/* 297 */         if (this.target.isPlayer) {
/* 298 */           int buffCount = 0;
/* 299 */           for (AbstractPower p : this.target.powers) {
/* 300 */             if (p.type == AbstractPower.PowerType.BUFF) {
/* 301 */               buffCount++;
/*     */             }
/*     */           } 
/* 304 */           if (buffCount >= 10) {
/* 305 */             UnlockTracker.unlockAchievement("POWERFUL");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 311 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ApplyPowerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */