/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class ElectroPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Electro";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Electro");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public ElectroPower(AbstractCreature owner) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Electro";
/* 16 */     this.owner = owner;
/* 17 */     updateDescription();
/* 18 */     loadRegion("mastery");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 23 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\ElectroPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */