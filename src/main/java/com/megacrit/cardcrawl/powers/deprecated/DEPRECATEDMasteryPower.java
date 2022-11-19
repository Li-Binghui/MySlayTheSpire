/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ 
/*    */ public class DEPRECATEDMasteryPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Mastery";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Mastery");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DEPRECATEDMasteryPower(AbstractCreature owner, int amount) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Mastery";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amount;
/* 22 */     updateDescription();
/* 23 */     loadRegion("corruption");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 28 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
/* 33 */     if (oldStance.ID.equals(newStance.ID) && !newStance.ID.equals("Neutral")) {
/* 34 */       flash();
/* 35 */       addToBot((AbstractGameAction)new GainEnergyAction(this.amount));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDMasteryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */