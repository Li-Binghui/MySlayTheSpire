/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DexterityPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Dexterity"); public static final String POWER_ID = "Dexterity";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DexterityPower(AbstractCreature owner, int amount) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Dexterity";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = amount;
/* 19 */     if (this.amount >= 999) {
/* 20 */       this.amount = 999;
/*    */     }
/*    */     
/* 23 */     if (this.amount <= -999) {
/* 24 */       this.amount = -999;
/*    */     }
/* 26 */     updateDescription();
/* 27 */     loadRegion("dexterity");
/* 28 */     this.canGoNegative = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 33 */     CardCrawlGame.sound.play("POWER_DEXTERITY", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 38 */     this.fontScale = 8.0F;
/* 39 */     this.amount += stackAmount;
/*    */     
/* 41 */     if (this.amount == 0) {
/* 42 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Dexterity"));
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
/* 60 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Dexterity"));
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
/* 74 */     if (this.amount > 0) {
/* 75 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/* 76 */       this.type = PowerType.BUFF;
/*    */     } else {
/* 78 */       int tmp = -this.amount;
/* 79 */       this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
/* 80 */       this.type = PowerType.DEBUFF;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float modifyBlock(float blockAmount) {
/* 86 */     if ((blockAmount += this.amount) < 0.0F) {
/* 87 */       return 0.0F;
/*    */     }
/* 89 */     return blockAmount;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DexterityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */