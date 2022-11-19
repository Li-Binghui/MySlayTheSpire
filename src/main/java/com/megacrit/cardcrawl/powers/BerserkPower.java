/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BerserkPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Berserk"); public static final String POWER_ID = "Berserk";
/*    */   
/*    */   public BerserkPower(AbstractCreature owner, int amount) {
/* 14 */     this.name = powerStrings.NAME;
/* 15 */     this.ID = "Berserk";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = amount;
/* 18 */     updateDescription();
/* 19 */     loadRegion("berserk");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     StringBuilder sb = new StringBuilder();
/* 25 */     sb.append(powerStrings.DESCRIPTIONS[0]);
/* 26 */     for (int i = 0; i < this.amount; i++) {
/* 27 */       sb.append("[R] ");
/*    */     }
/* 29 */     sb.append(LocalizedStrings.PERIOD);
/* 30 */     this.description = sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 35 */     addToBot((AbstractGameAction)new GainEnergyAction(this.amount));
/* 36 */     flash();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BerserkPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */