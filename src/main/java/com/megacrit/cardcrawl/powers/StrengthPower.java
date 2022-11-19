/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class StrengthPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Strength";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Strength");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public StrengthPower(AbstractCreature owner, int amount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Strength";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     if (this.amount >= 999) {
/* 24 */       this.amount = 999;
/*    */     }
/*    */     
/* 27 */     if (this.amount <= -999) {
/* 28 */       this.amount = -999;
/*    */     }
/* 30 */     updateDescription();
/* 31 */     loadRegion("strength");
/* 32 */     this.canGoNegative = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 37 */     CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 42 */     this.fontScale = 8.0F;
/* 43 */     this.amount += stackAmount;
/* 44 */     if (this.amount == 0) {
/* 45 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Strength"));
/*    */     }
/*    */     
/* 48 */     if (this.amount >= 50 && this.owner == AbstractDungeon.player) {
/* 49 */       UnlockTracker.unlockAchievement("JAXXED");
/*    */     }
/*    */     
/* 52 */     if (this.amount >= 999) {
/* 53 */       this.amount = 999;
/*    */     }
/*    */     
/* 56 */     if (this.amount <= -999) {
/* 57 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void reducePower(int reduceAmount) {
/* 63 */     this.fontScale = 8.0F;
/* 64 */     this.amount -= reduceAmount;
/*    */     
/* 66 */     if (this.amount == 0) {
/* 67 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
/*    */     }
/*    */     
/* 70 */     if (this.amount >= 999) {
/* 71 */       this.amount = 999;
/*    */     }
/*    */     
/* 74 */     if (this.amount <= -999) {
/* 75 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 81 */     if (this.amount > 0) {
/* 82 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/* 83 */       this.type = PowerType.BUFF;
/*    */     } else {
/* 85 */       int tmp = -this.amount;
/* 86 */       this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
/* 87 */       this.type = PowerType.DEBUFF;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageGive(float damage, DamageInfo.DamageType type) {
/* 93 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 94 */       return damage + this.amount;
/*    */     }
/* 96 */     return damage;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\StrengthPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */