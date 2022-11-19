/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ReboundPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Rebound";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rebound");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean justEvoked = true;
/*    */   
/*    */   public ReboundPower(AbstractCreature owner) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "Rebound";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = 1;
/* 24 */     updateDescription();
/* 25 */     loadRegion("rebound");
/* 26 */     this.isTurnBased = true;
/* 27 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 32 */     if (this.amount > 1) {
/* 33 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } else {
/* 35 */       this.description = DESCRIPTIONS[0];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
/* 41 */     if (this.justEvoked) {
/* 42 */       this.justEvoked = false;
/*    */       
/*    */       return;
/*    */     } 
/* 46 */     if (card.type != AbstractCard.CardType.POWER) {
/* 47 */       flash();
/* 48 */       action.reboundCard = true;
/*    */     } 
/*    */     
/* 51 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Rebound", 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 56 */     if (isPlayer)
/* 57 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Rebound")); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ReboundPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */