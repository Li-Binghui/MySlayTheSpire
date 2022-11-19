/*    */ package com.megacrit.cardcrawl.powers.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DevaPower extends AbstractPower {
/*    */   public static final String POWER_ID = "DevaForm";
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DevaForm");
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/* 14 */   private int energyGainAmount = 1;
/*    */   
/*    */   public DevaPower(AbstractCreature owner) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "DevaForm";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = 1;
/* 21 */     this.energyGainAmount = 1;
/* 22 */     updateDescription();
/* 23 */     loadRegion("deva2");
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 28 */     super.stackPower(stackAmount);
/* 29 */     this.energyGainAmount++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 34 */     if (this.energyGainAmount == 1) {
/* 35 */       this.description = DESCRIPTIONS[0] + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
/*    */     } else {
/* 37 */       this.description = DESCRIPTIONS[1] + this.energyGainAmount + DESCRIPTIONS[2] + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 44 */     flash();
/* 45 */     AbstractDungeon.player.gainEnergy(this.energyGainAmount);
/* 46 */     this.energyGainAmount += this.amount;
/* 47 */     updateDescription();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\watcher\DevaPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */