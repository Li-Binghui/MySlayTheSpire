/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnergyDownPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("EnergyDownPower"); public static final String POWER_ID = "EnergyDownPower";
/*    */   
/*    */   public EnergyDownPower(AbstractCreature owner, int amount, boolean isFasting) {
/* 15 */     this.name = powerStrings.NAME;
/* 16 */     this.ID = "EnergyDownPower";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = amount;
/* 19 */     updateDescription();
/*    */     
/* 21 */     if (isFasting) {
/* 22 */       loadRegion("fasting");
/*    */     } else {
/* 24 */       loadRegion("energized_blue");
/*    */     } 
/*    */ 
/*    */     
/* 28 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */   
/*    */   public EnergyDownPower(AbstractCreature owner, int amount) {
/* 32 */     this(owner, amount, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 37 */     StringBuilder sb = new StringBuilder();
/* 38 */     sb.append(powerStrings.DESCRIPTIONS[0]);
/* 39 */     for (int i = 0; i < this.amount; i++) {
/* 40 */       sb.append("[E] ");
/*    */     }
/* 42 */     if (powerStrings.DESCRIPTIONS[1].isEmpty()) {
/* 43 */       sb.append(LocalizedStrings.PERIOD);
/*    */     } else {
/* 45 */       sb.append(powerStrings.DESCRIPTIONS[1]);
/*    */     } 
/* 47 */     this.description = sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 52 */     addToBot((AbstractGameAction)new LoseEnergyAction(this.amount));
/* 53 */     flash();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\EnergyDownPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */