/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class HeatsinkPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Heatsink";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Heatsink");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public HeatsinkPower(AbstractCreature owner, int drawAmt) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Heatsink";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = drawAmt;
/* 22 */     updateDescription();
/* 23 */     loadRegion("heatsink");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 28 */     if (card.type == AbstractCard.CardType.POWER) {
/* 29 */       flash();
/* 30 */       addToTop((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 36 */     this.fontScale = 8.0F;
/* 37 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 42 */     if (this.amount <= 1) {
/* 43 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 45 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\HeatsinkPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */