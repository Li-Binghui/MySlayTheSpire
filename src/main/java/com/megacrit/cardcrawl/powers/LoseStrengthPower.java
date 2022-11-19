/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class LoseStrengthPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flex"); public static final String POWER_ID = "Flex";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public LoseStrengthPower(AbstractCreature owner, int newAmount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Flex";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = newAmount;
/* 20 */     this.type = PowerType.DEBUFF;
/* 21 */     updateDescription();
/* 22 */     loadRegion("flex");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 32 */     flash();
/* 33 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.amount), -this.amount));
/* 34 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Flex"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\LoseStrengthPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */