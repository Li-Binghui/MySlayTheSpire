/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RechargingCorePower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("RechargingCore"); public static final String POWER_ID = "RechargingCore";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private int turnTimer;
/*    */   
/*    */   public RechargingCorePower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "RechargingCore";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     this.turnTimer = 3;
/* 21 */     updateDescription();
/* 22 */     loadRegion("conserve");
/* 23 */     this.type = PowerType.BUFF;
/* 24 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = DESCRIPTIONS[0] + this.turnTimer;
/* 31 */     if (this.turnTimer == 1) {
/* 32 */       this.description += DESCRIPTIONS[1];
/*    */     } else {
/* 34 */       this.description += DESCRIPTIONS[2];
/*    */     } 
/* 36 */     for (int i = 0; i < this.amount; i++) {
/* 37 */       this.description += DESCRIPTIONS[3];
/*    */     }
/* 39 */     this.description += " .";
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 44 */     updateDescription();
/* 45 */     if (this.turnTimer == 1) {
/* 46 */       flash();
/* 47 */       this.turnTimer = 3;
/* 48 */       addToBot((AbstractGameAction)new GainEnergyAction(this.amount));
/*    */     } else {
/* 50 */       this.turnTimer--;
/*    */     } 
/* 52 */     updateDescription();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RechargingCorePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */