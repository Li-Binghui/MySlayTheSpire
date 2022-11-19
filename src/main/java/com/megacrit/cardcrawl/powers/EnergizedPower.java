/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class EnergizedPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Energized"); public static final String POWER_ID = "Energized";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EnergizedPower(AbstractCreature owner, int energyAmt) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Energized";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = energyAmt;
/* 20 */     if (this.amount >= 999) {
/* 21 */       this.amount = 999;
/*    */     }
/* 23 */     updateDescription();
/* 24 */     loadRegion("energized_green");
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 29 */     super.stackPower(stackAmount);
/* 30 */     if (this.amount >= 999) {
/* 31 */       this.amount = 999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 37 */     if (this.amount == 1) {
/* 38 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 40 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 46 */     flash();
/* 47 */     AbstractDungeon.player.gainEnergy(this.amount);
/* 48 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Energized"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EnergizedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */