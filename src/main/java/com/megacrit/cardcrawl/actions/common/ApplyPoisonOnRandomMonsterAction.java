/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;

import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class ApplyPoisonOnRandomMonsterAction
/*     */   extends AbstractGameAction
/*     */ {
/*  29 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ApplyPowerAction");
/*  30 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*     */   private float startingDuration;
/*     */ 
/*     */   
/*     */   private AbstractPower powerToApply;
/*     */ 
/*     */   
/*     */   public ApplyPoisonOnRandomMonsterAction(AbstractCreature source, int stackAmount, boolean isFast, AttackEffect effect) {
/*  40 */     if (Settings.FAST_MODE) {
/*  41 */       this.startingDuration = 0.1F;
/*     */     }
/*  43 */     else if (isFast) {
/*  44 */       this.startingDuration = Settings.ACTION_DUR_FASTER;
/*     */     } else {
/*  46 */       this.startingDuration = Settings.ACTION_DUR_FAST;
/*     */     } 
/*     */ 
/*     */     
/*  50 */     this.duration = this.startingDuration;
/*  51 */     this.target = null;
/*  52 */     this.source = source;
/*  53 */     this.amount = stackAmount;
/*  54 */     this.actionType = ActionType.POWER;
/*  55 */     this.attackEffect = effect;
/*     */     
/*  57 */     if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/*  58 */       this.duration = 0.0F;
/*  59 */       this.startingDuration = 0.0F;
/*  60 */       this.isDone = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  66 */     if (this.duration == this.startingDuration) {
/*  67 */       this.target = (AbstractCreature)AbstractDungeon.getRandomMonster();
/*     */       
/*  69 */       if (this.target == null) {
/*  70 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  74 */       this.powerToApply = (AbstractPower)new PoisonPower(this.target, this.source, this.amount);
/*     */       
/*  76 */       if (this.source != null) {
/*  77 */         for (AbstractPower pow : this.source.powers) {
/*  78 */           pow.onApplyPower(this.powerToApply, this.target, this.source);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*  83 */       if (this.target.hasPower("Artifact")) {
/*  84 */         addToTop((AbstractGameAction)new TextAboveCreatureAction(this.target, TEXT[0]));
/*  85 */         this.duration -= Gdx.graphics.getDeltaTime();
/*  86 */         CardCrawlGame.sound.play("NULLIFY_SFX");
/*  87 */         this.target.getPower("Artifact").flashWithoutSound();
/*  88 */         this.target.getPower("Artifact").onSpecificTrigger();
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  94 */       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
/*     */ 
/*     */ 
/*     */       
/*  98 */       boolean hasBuffAlready = false;
/*  99 */       for (AbstractPower p : this.target.powers) {
/* 100 */         if (p.ID.equals(this.powerToApply.ID) && !p.ID.equals("Night Terror")) {
/* 101 */           p.stackPower(this.amount);
/* 102 */           p.flash();
/*     */           
/* 104 */           if ((p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) && this.amount <= 0) {
/* 105 */             AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 110 */           else if (this.amount > 0) {
/* 111 */             if (p.type == AbstractPower.PowerType.BUFF || p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) {
/* 112 */               AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + 
/*     */ 
/*     */ 
/*     */                     
/* 116 */                     Integer.toString(this.amount) + " " + this.powerToApply.name));
/*     */             } else {
/* 118 */               AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + 
/*     */ 
/*     */ 
/*     */                     
/* 122 */                     Integer.toString(this.amount) + " " + this.powerToApply.name));
/*     */             }
/*     */           
/* 125 */           } else if (p.type == AbstractPower.PowerType.BUFF) {
/* 126 */             AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */             
/* 132 */             AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 140 */           p.updateDescription();
/* 141 */           hasBuffAlready = true;
/* 142 */           AbstractDungeon.onModifyPower();
/*     */         } 
/*     */       } 
/*     */       
/* 146 */       if (this.powerToApply.type == AbstractPower.PowerType.DEBUFF) {
/* 147 */         this.target.useFastShakeAnimation(0.5F);
/*     */       }
/*     */       
/* 150 */       if (!hasBuffAlready) {
/* 151 */         this.target.powers.add(this.powerToApply);
/* 152 */         Collections.sort(this.target.powers);
/* 153 */         this.powerToApply.onInitialApplication();
/* 154 */         this.powerToApply.flash();
/*     */         
/* 156 */         if (this.amount < 0 && (this.powerToApply.ID.equals("Strength") || this.powerToApply.ID.equals("Dexterity") || this.powerToApply.ID
/* 157 */           .equals("Focus"))) {
/* 158 */           AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 164 */         else if (this.powerToApply.type == AbstractPower.PowerType.BUFF) {
/* 165 */           AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 171 */           AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 179 */         AbstractDungeon.onModifyPower();
/*     */ 
/*     */         
/* 182 */         if (this.target.isPlayer) {
/* 183 */           int buffCount = 0;
/* 184 */           for (AbstractPower p : this.target.powers) {
/* 185 */             if (p.type == AbstractPower.PowerType.BUFF) {
/* 186 */               buffCount++;
/*     */             }
/*     */           } 
/* 189 */           if (buffCount >= 10) {
/* 190 */             UnlockTracker.unlockAchievement("POWERFUL");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\ApplyPoisonOnRandomMonsterAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */