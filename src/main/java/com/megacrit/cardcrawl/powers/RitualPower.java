/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class RitualPower extends AbstractPower {
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Ritual"); public static final String POWER_ID = "Ritual";
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   private boolean skipFirst = true;
/*    */   private boolean onPlayer;
/*    */   
/*    */   public RitualPower(AbstractCreature owner, int strAmt, boolean playerControlled) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Ritual";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = strAmt;
/* 22 */     this.onPlayer = playerControlled;
/* 23 */     updateDescription();
/* 24 */     loadRegion("ritual");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 29 */     if (!this.onPlayer) {
/* 30 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 32 */       this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 38 */     if (isPlayer) {
/* 39 */       flash();
/* 40 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 46 */     if (!this.onPlayer)
/* 47 */       if (!this.skipFirst) {
/* 48 */         flash();
/* 49 */         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
/*    */       } else {
/* 51 */         this.skipFirst = false;
/*    */       }  
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\RitualPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */