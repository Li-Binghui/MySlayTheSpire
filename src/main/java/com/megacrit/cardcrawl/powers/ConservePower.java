/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ConservePower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Conserve"); public static final String POWER_ID = "Conserve";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ConservePower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Conserve";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     this.description = DESCRIPTIONS[0];
/* 21 */     loadRegion("conserve");
/* 22 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     if (this.amount == 1) {
/* 28 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 30 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 36 */     if (this.amount == 0) {
/* 37 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Conserve"));
/*    */     } else {
/* 39 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Conserve", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ConservePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */