/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class DrawPower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw"); public static final String POWER_ID = "Draw";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public DrawPower(AbstractCreature owner, int amount) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Draw";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amount;
/* 20 */     updateDescription();
/* 21 */     loadRegion("draw");
/*    */     
/* 23 */     if (amount < 0) {
/* 24 */       this.type = PowerType.DEBUFF;
/* 25 */       loadRegion("draw2");
/*    */     } else {
/* 27 */       this.type = PowerType.BUFF;
/* 28 */       loadRegion("draw");
/*    */     } 
/*    */     
/* 31 */     this.isTurnBased = false;
/* 32 */     AbstractDungeon.player.gameHandSize += amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onRemove() {
/* 37 */     AbstractDungeon.player.gameHandSize -= this.amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reducePower(int reduceAmount) {
/* 42 */     this.fontScale = 8.0F;
/* 43 */     this.amount -= reduceAmount;
/*    */     
/* 45 */     if (this.amount == 0) {
/* 46 */       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Draw"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 52 */     if (this.amount > 0) {
/* 53 */       if (this.amount == 1) {
/* 54 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */       } else {
/* 56 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3];
/*    */       } 
/* 58 */       this.type = PowerType.BUFF;
/*    */     } else {
/* 60 */       if (this.amount == -1) {
/* 61 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */       } else {
/* 63 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[4];
/*    */       } 
/* 65 */       this.type = PowerType.DEBUFF;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\DrawPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */