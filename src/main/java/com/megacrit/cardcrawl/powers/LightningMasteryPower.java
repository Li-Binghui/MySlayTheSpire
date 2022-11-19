/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class LightningMasteryPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Lightning Mastery";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Lightning Mastery");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public LightningMasteryPower(AbstractCreature owner, int amount) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Lightning Mastery";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = amount;
/* 18 */     updateDescription();
/* 19 */     loadRegion("mastery");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\LightningMasteryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */