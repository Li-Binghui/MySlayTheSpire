/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class LockOnPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Lockon"); public static final String POWER_ID = "Lockon";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final float MULTIPLIER = 1.5F;
/*    */   private static final int MULTI_STR = 50;
/*    */   
/*    */   public LockOnPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Lockon";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("lockon");
/* 24 */     this.type = PowerType.DEBUFF;
/* 25 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfRound() {
/* 30 */     if (this.amount == 0) {
/* 31 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Lockon"));
/*    */     } else {
/* 33 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Lockon", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     if (this.owner != null)
/* 40 */       if (this.amount == 1) {
/* 41 */         this.description = DESCRIPTIONS[0] + '2' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */       } else {
/* 43 */         this.description = DESCRIPTIONS[0] + '2' + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
/*    */       }  
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\LockOnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */