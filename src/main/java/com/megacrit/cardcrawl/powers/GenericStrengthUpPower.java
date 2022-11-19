/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class GenericStrengthUpPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Generic Strength Up Power"); public static final String POWER_ID = "Generic Strength Up Power";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public GenericStrengthUpPower(AbstractCreature owner, String newName, int strAmt) {
/* 15 */     this.name = newName;
/* 16 */     this.ID = "Generic Strength Up Power";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = strAmt;
/* 19 */     updateDescription();
/* 20 */     loadRegion("stasis");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 30 */     flash();
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\GenericStrengthUpPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */