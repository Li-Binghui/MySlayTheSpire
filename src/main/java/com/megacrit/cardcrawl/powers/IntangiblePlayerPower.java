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
/*    */ public class IntangiblePlayerPower extends AbstractPower {
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("IntangiblePlayer"); public static final String POWER_ID = "IntangiblePlayer";
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public IntangiblePlayerPower(AbstractCreature owner, int turns) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "IntangiblePlayer";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = turns;
/* 21 */     updateDescription();
/* 22 */     loadRegion("intangible");
/* 23 */     this.priority = 75;
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 28 */     CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 33 */     if (damage > 1.0F) {
/* 34 */       damage = 1.0F;
/*    */     }
/* 36 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 46 */     flash();
/*    */     
/* 48 */     if (this.amount == 0) {
/* 49 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "IntangiblePlayer"));
/*    */     } else {
/* 51 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "IntangiblePlayer", 1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\IntangiblePlayerPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */