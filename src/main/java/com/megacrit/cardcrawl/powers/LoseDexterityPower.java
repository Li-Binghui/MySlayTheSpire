/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class LoseDexterityPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DexLoss"); public static final String POWER_ID = "DexLoss";
/*    */   
/*    */   public LoseDexterityPower(AbstractCreature owner, int newAmount) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "DexLoss";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = newAmount;
/* 18 */     this.type = PowerType.DEBUFF;
/* 19 */     updateDescription();
/* 20 */     loadRegion("flex");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 30 */     flash();
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, -this.amount), -this.amount));
/* 32 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "DexLoss"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\LoseDexterityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */