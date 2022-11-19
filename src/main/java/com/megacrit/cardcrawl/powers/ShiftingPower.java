/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ShiftingPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Shifting"); public static final String POWER_ID = "Shifting";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ShiftingPower(AbstractCreature owner) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Shifting";
/* 18 */     this.owner = owner;
/* 19 */     updateDescription();
/* 20 */     this.isPostActionPower = true;
/* 21 */     loadRegion("shift");
/*    */   }
/*    */ 
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 26 */     if (damageAmount > 0) {
/* 27 */       addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -damageAmount), -damageAmount));
/*    */       
/* 29 */       if (!this.owner.hasPower("Artifact")) {
/* 30 */         addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new GainStrengthPower(this.owner, damageAmount), damageAmount));
/*    */       }
/* 32 */       flash();
/*    */     } 
/*    */     
/* 35 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 40 */     this.description = DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ShiftingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */