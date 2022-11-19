/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ModeShiftPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Mode Shift";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Mode Shift");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ModeShiftPower(AbstractCreature owner, int newAmount) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Mode Shift";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = newAmount;
/* 18 */     updateDescription();
/* 19 */     loadRegion("modeShift");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ModeShiftPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */