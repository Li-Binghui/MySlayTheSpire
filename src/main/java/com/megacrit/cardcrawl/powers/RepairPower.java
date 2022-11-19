/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RepairPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Repair";
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Repair");
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public RepairPower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Repair";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("repair");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 26 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 31 */     AbstractPlayer p = AbstractDungeon.player;
/* 32 */     if (p.currentHealth > 0)
/* 33 */       p.heal(this.amount); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RepairPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */