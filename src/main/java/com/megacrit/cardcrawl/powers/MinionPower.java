/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class MinionPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Minion";
/*  9 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Minion");
/* 10 */   public static final String NAME = powerStrings.NAME;
/* 11 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MinionPower(AbstractCreature owner) {
/* 14 */     this.name = NAME;
/* 15 */     this.ID = "Minion";
/* 16 */     this.owner = owner;
/* 17 */     updateDescription();
/* 18 */     loadRegion("minion");
/* 19 */     this.type = PowerType.BUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 24 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\MinionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */