/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BiasPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Bias"); public static final String POWER_ID = "Bias";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BiasPower(AbstractCreature owner, int setAmount) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "Bias";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = setAmount;
/* 19 */     updateDescription();
/* 20 */     loadRegion("bias");
/* 21 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 26 */     flash();
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -this.amount), -this.amount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     this.fontScale = 8.0F;
/* 33 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BiasPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */