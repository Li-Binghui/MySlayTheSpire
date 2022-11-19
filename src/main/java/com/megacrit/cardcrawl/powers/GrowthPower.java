/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class GrowthPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("GrowthPower"); public static final String POWER_ID = "GrowthPower";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private boolean skipFirst = true;
/*    */   
/*    */   public GrowthPower(AbstractCreature owner, int strAmt) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "GrowthPower";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = strAmt;
/* 21 */     updateDescription();
/* 22 */     loadRegion("ritual");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 32 */     if (!this.skipFirst) {
/* 33 */       flash();
/* 34 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */     } else {
/* 36 */       this.skipFirst = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\GrowthPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */