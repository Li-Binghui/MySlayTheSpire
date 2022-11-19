/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class SurroundedPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Surrounded";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Surrounded");
/*    */   
/*    */   public SurroundedPower(AbstractCreature owner) {
/* 12 */     this.name = powerStrings.NAME;
/* 13 */     this.ID = "Surrounded";
/* 14 */     this.owner = owner;
/* 15 */     this.amount = -1;
/* 16 */     updateDescription();
/* 17 */     loadRegion("surrounded");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 22 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SurroundedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */