/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ThieveryPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Thievery";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Thievery");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ThieveryPower(AbstractCreature owner, int stealAmount) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Thievery";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = stealAmount;
/* 18 */     updateDescription();
/* 19 */     loadRegion("thievery");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     this.description = this.owner.name + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ThieveryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */