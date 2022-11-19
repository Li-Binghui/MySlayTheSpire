/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class FrailPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Frail"); public static final String POWER_ID = "Frail";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean justApplied = false;
/*    */   
/*    */   public FrailPower(AbstractCreature owner, int amount, boolean isSourceMonster) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Frail";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = amount;
/* 21 */     this.priority = 10;
/* 22 */     updateDescription();
/* 23 */     loadRegion("frail");
/*    */     
/* 25 */     if (isSourceMonster) {
/* 26 */       this.justApplied = true;
/*    */     }
/*    */     
/* 29 */     this.type = PowerType.DEBUFF;
/* 30 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 35 */     if (this.justApplied) {
/* 36 */       this.justApplied = false;
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (this.amount == 0) {
/* 41 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Frail"));
/*    */     } else {
/* 43 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Frail", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 49 */     if (this.amount == 1) {
/* 50 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 52 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float modifyBlock(float blockAmount) {
/* 58 */     return blockAmount * 0.75F;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\FrailPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */