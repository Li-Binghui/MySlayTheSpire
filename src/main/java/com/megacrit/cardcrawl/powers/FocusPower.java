/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class FocusPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Focus";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Focus");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public FocusPower(AbstractCreature owner, int amount) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Focus";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amount;
/* 21 */     updateDescription();
/* 22 */     loadRegion("focus");
/* 23 */     this.canGoNegative = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 28 */     CardCrawlGame.sound.play("POWER_FOCUS", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 33 */     this.fontScale = 8.0F;
/* 34 */     this.amount += stackAmount;
/*    */     
/* 36 */     if (this.amount == 0) {
/* 37 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Focus"));
/*    */     }
/*    */     
/* 40 */     if (this.amount >= 25) {
/* 41 */       UnlockTracker.unlockAchievement("FOCUSED");
/*    */     }
/*    */     
/* 44 */     if (this.amount >= 999) {
/* 45 */       this.amount = 999;
/*    */     }
/*    */     
/* 48 */     if (this.amount <= -999) {
/* 49 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void reducePower(int reduceAmount) {
/* 55 */     this.fontScale = 8.0F;
/* 56 */     this.amount -= reduceAmount;
/*    */     
/* 58 */     if (this.amount == 0) {
/* 59 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
/*    */     }
/*    */     
/* 62 */     if (this.amount >= 999) {
/* 63 */       this.amount = 999;
/*    */     }
/*    */     
/* 66 */     if (this.amount <= -999) {
/* 67 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 73 */     if (this.amount > 0) {
/* 74 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/* 75 */       this.type = PowerType.BUFF;
/*    */     } else {
/* 77 */       int tmp = -this.amount;
/* 78 */       this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
/* 79 */       this.type = PowerType.DEBUFF;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FocusPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */