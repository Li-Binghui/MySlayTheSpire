/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class PhantasmalPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Phantasmal"); public static final String POWER_ID = "Phantasmal";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public PhantasmalPower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Phantasmal";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("phantasmal");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     if (this.amount == 1) {
/* 27 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 29 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 35 */     flash();
/* 36 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new DoubleDamagePower(this.owner, 1, false), this.amount));
/* 37 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Phantasmal", 1));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\PhantasmalPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */