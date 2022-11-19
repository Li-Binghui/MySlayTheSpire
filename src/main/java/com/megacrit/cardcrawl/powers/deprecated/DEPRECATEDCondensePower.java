/*    */ package com.megacrit.cardcrawl.powers.deprecated;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class DEPRECATEDCondensePower extends AbstractPower {
/*    */   public static final String POWER_ID = "DEPRECATEDCondense";
/* 10 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DEPRECATEDCondense");
/* 11 */   public static final String NAME = powerStrings.NAME;
/* 12 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DEPRECATEDCondensePower(AbstractCreature owner, int bufferAmt) {
/* 15 */     this.name = NAME;
/* 16 */     this.ID = "DEPRECATEDCondense";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = bufferAmt;
/* 19 */     updateDescription();
/* 20 */     loadRegion("buffer");
/*    */   }
/*    */ 
/*    */   
/*    */   public int onLoseHp(int damageAmount) {
/* 25 */     if (damageAmount > this.amount) {
/* 26 */       flash();
/* 27 */       return this.amount;
/*    */     } 
/* 29 */     return damageAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\deprecated\DEPRECATEDCondensePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */