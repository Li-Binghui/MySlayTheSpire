/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DrawCardNextTurnPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw Card"); public static final String POWER_ID = "Draw Card";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DrawCardNextTurnPower(AbstractCreature owner, int drawAmount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Draw Card";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = drawAmount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("carddraw");
/* 22 */     this.priority = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     if (this.amount > 1) {
/* 28 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 30 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 36 */     flash();
/* 37 */     addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
/* 38 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Draw Card"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DrawCardNextTurnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */