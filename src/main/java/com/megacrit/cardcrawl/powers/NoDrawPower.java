/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class NoDrawPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("No Draw"); public static final String POWER_ID = "No Draw";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public NoDrawPower(AbstractCreature owner) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "No Draw";
/* 17 */     this.owner = owner;
/* 18 */     this.type = PowerType.DEBUFF;
/* 19 */     this.amount = -1;
/* 20 */     this.description = DESCRIPTIONS[0];
/* 21 */     loadRegion("noDraw");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 26 */     if (isPlayer)
/* 27 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "No Draw")); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\NoDrawPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */