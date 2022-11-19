/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DrawReductionPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw Reduction"); public static final String POWER_ID = "Draw Reduction";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private boolean justApplied = true;
/*    */   
/*    */   public DrawReductionPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Draw Reduction";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("lessdraw");
/* 24 */     this.type = PowerType.DEBUFF;
/* 25 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onInitialApplication() {
/* 30 */     AbstractDungeon.player.gameHandSize--;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 35 */     if (this.justApplied) {
/* 36 */       this.justApplied = false;
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Draw Reduction", 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRemove() {
/* 45 */     AbstractDungeon.player.gameHandSize++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 50 */     if (this.amount == 1) {
/* 51 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 53 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DrawReductionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */