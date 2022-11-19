/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DemonFormPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Demon Form"); public static final String POWER_ID = "Demon Form";
/*    */   
/*    */   public DemonFormPower(AbstractCreature owner, int strengthAmount) {
/* 13 */     this.name = powerStrings.NAME;
/* 14 */     this.ID = "Demon Form";
/* 15 */     this.owner = owner;
/* 16 */     this.amount = strengthAmount;
/* 17 */     updateDescription();
/* 18 */     loadRegion("demonForm");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 23 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 28 */     flash();
/* 29 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DemonFormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */