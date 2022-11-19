/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class AccuracyPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Accuracy";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Accuracy");
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public AccuracyPower(AbstractCreature owner, int amt) {
/* 16 */     this.name = powerStrings.NAME;
/* 17 */     this.ID = "Accuracy";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = amt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("accuracy");
/* 22 */     updateExistingShivs();
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     this.fontScale = 8.0F;
/* 33 */     this.amount += stackAmount;
/* 34 */     updateExistingShivs();
/*    */   }
/*    */   
/*    */   private void updateExistingShivs() {
/* 38 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 39 */       if (c instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 40 */         if (!c.upgraded) {
/* 41 */           c.baseDamage = 4 + this.amount; continue;
/*    */         } 
/* 43 */         c.baseDamage = 6 + this.amount;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 48 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 49 */       if (c instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 50 */         if (!c.upgraded) {
/* 51 */           c.baseDamage = 4 + this.amount; continue;
/*    */         } 
/* 53 */         c.baseDamage = 6 + this.amount;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 58 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 59 */       if (c instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 60 */         if (!c.upgraded) {
/* 61 */           c.baseDamage = 4 + this.amount; continue;
/*    */         } 
/* 63 */         c.baseDamage = 6 + this.amount;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 68 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/* 69 */       if (c instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 70 */         if (!c.upgraded) {
/* 71 */           c.baseDamage = 4 + this.amount; continue;
/*    */         } 
/* 73 */         c.baseDamage = 6 + this.amount;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDrawOrDiscard() {
/* 81 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 82 */       if (c instanceof com.megacrit.cardcrawl.cards.tempCards.Shiv) {
/* 83 */         if (!c.upgraded) {
/* 84 */           c.baseDamage = 4 + this.amount; continue;
/*    */         } 
/* 86 */         c.baseDamage = 6 + this.amount;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\AccuracyPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */