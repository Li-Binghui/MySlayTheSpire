/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class GainStrengthPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Shackled"); public static final String POWER_ID = "Shackled";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public GainStrengthPower(AbstractCreature owner, int newAmount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Shackled";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = newAmount;
/* 20 */     this.type = PowerType.DEBUFF;
/* 21 */     updateDescription();
/* 22 */     loadRegion("shackle");
/* 23 */     if (this.amount >= 999) {
/* 24 */       this.amount = 999;
/*    */     }
/*    */     
/* 27 */     if (this.amount <= -999) {
/* 28 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 34 */     CardCrawlGame.sound.play("POWER_SHACKLE", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 39 */     this.fontScale = 8.0F;
/* 40 */     this.amount += stackAmount;
/* 41 */     if (this.amount == 0) {
/* 42 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Shackled"));
/*    */     }
/*    */     
/* 45 */     if (this.amount >= 999) {
/* 46 */       this.amount = 999;
/*    */     }
/*    */     
/* 49 */     if (this.amount <= -999) {
/* 50 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void reducePower(int reduceAmount) {
/* 56 */     this.fontScale = 8.0F;
/* 57 */     this.amount -= reduceAmount;
/*    */     
/* 59 */     if (this.amount == 0) {
/* 60 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
/*    */     }
/*    */     
/* 63 */     if (this.amount >= 999) {
/* 64 */       this.amount = 999;
/*    */     }
/*    */     
/* 67 */     if (this.amount <= -999) {
/* 68 */       this.amount = -999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 74 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 79 */     flash();
/* 80 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/* 81 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Shackled"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\GainStrengthPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */