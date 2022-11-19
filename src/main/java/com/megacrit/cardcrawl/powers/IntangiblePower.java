/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class IntangiblePower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Intangible"); public static final String POWER_ID = "Intangible";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean justApplied;
/*    */   
/*    */   public IntangiblePower(AbstractCreature owner, int turns) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Intangible";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = turns;
/* 22 */     updateDescription();
/* 23 */     loadRegion("intangible");
/* 24 */     this.priority = 75;
/* 25 */     this.justApplied = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 30 */     CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 35 */     if (damage > 1.0F) {
/* 36 */       damage = 1.0F;
/*    */     }
/* 38 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 43 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 48 */     if (this.justApplied) {
/* 49 */       this.justApplied = false;
/*    */       return;
/*    */     } 
/* 52 */     flash();
/*    */     
/* 54 */     if (this.amount == 0) {
/* 55 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Intangible"));
/*    */     } else {
/* 57 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Intangible", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\IntangiblePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */