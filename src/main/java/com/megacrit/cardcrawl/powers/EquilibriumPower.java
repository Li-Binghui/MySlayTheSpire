/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class EquilibriumPower extends AbstractPower {
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Equilibrium"); public static final String POWER_ID = "Equilibrium";
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EquilibriumPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Equilibrium";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     this.description = DESCRIPTIONS[0];
/* 23 */     loadRegion("retain");
/* 24 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 29 */     if (this.amount == 1) {
/* 30 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 32 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 38 */     if (isPlayer) {
/* 39 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 40 */         if (!c.isEthereal) {
/* 41 */           c.retain = true;
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 49 */     if (this.amount == 0) {
/* 50 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Equilibrium"));
/*    */     } else {
/* 52 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Equilibrium", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EquilibriumPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */