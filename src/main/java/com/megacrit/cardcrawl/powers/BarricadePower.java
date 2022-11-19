/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class BarricadePower extends AbstractPower {
/*    */   public static final String POWER_ID = "Barricade";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Barricade");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public BarricadePower(AbstractCreature owner) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Barricade";
/* 16 */     this.owner = owner;
/* 17 */     this.amount = -1;
/* 18 */     updateDescription();
/* 19 */     loadRegion("barricade");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     if (this.owner.isPlayer) {
/* 25 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 27 */       this.description = DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\BarricadePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */