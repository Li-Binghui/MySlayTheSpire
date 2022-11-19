/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class CollectPower extends AbstractPower {
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Collect"); public static final String POWER_ID = "Collect";
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public CollectPower(AbstractCreature owner, int numTurns) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Collect";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = numTurns;
/* 23 */     this.isTurnBased = true;
/*    */     
/* 25 */     if (this.amount >= 999) {
/* 26 */       this.amount = 999;
/*    */     }
/* 28 */     updateDescription();
/* 29 */     loadRegion("energized_blue");
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 34 */     super.stackPower(stackAmount);
/* 35 */     if (this.amount >= 999) {
/* 36 */       this.amount = 999;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 42 */     if (this.amount == 1) {
/* 43 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 45 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 51 */     flash();
/* 52 */     Miracle miracle = new Miracle();
/* 53 */     miracle.upgrade();
/* 54 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)miracle));
/* 55 */     if (this.amount <= 1) {
/* 56 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Collect"));
/*    */     } else {
/* 58 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Collect", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\CollectPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */