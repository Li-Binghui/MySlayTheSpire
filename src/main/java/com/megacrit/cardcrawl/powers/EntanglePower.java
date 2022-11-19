/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class EntanglePower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Entangled"); public static final String POWER_ID = "Entangled";
/*    */   
/*    */   public EntanglePower(AbstractCreature owner) {
/* 13 */     this.name = powerStrings.NAME;
/* 14 */     this.ID = "Entangled";
/* 15 */     this.owner = owner;
/* 16 */     this.amount = 1;
/* 17 */     updateDescription();
/* 18 */     loadRegion("entangle");
/* 19 */     this.isTurnBased = true;
/* 20 */     this.type = PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 25 */     CardCrawlGame.sound.play("POWER_ENTANGLED", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 35 */     if (isPlayer)
/* 36 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Entangled")); 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\EntanglePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */