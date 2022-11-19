/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class StrikeUpPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "StrikeUp";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("StrikeUp");
/*    */   
/*    */   public StrikeUpPower(AbstractCreature owner, int amt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "StrikeUp";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("accuracy");
/* 22 */     updateExistingStrikes();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     this.fontScale = 8.0F;
/* 33 */     this.amount += stackAmount;
/* 34 */     updateExistingStrikes();
/*    */   }
/*    */   
/*    */   private void updateExistingStrikes() {
/* 38 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 39 */       if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 40 */         (CardLibrary.getCard(c.cardID)).baseDamage += this.amount;
/*    */       }
/*    */     } 
/*    */     
/* 44 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 45 */       if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 46 */         (CardLibrary.getCard(c.cardID)).baseDamage += this.amount;
/*    */       }
/*    */     } 
/*    */     
/* 50 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 51 */       if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 52 */         (CardLibrary.getCard(c.cardID)).baseDamage += this.amount;
/*    */       }
/*    */     } 
/*    */     
/* 56 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/* 57 */       if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
/* 58 */         (CardLibrary.getCard(c.cardID)).baseDamage += this.amount;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDrawOrDiscard() {
/* 65 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 66 */       if (c.hasTag(AbstractCard.CardTags.STRIKE))
/* 67 */         (CardLibrary.getCard(c.cardID)).baseDamage += this.amount; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\StrikeUpPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */