/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class CorruptionPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Corruption";
/* 12 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Corruption");
/* 13 */   public static final String NAME = powerStrings.NAME;
/* 14 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public CorruptionPower(AbstractCreature owner) {
/* 17 */     this.name = NAME;
/* 18 */     this.ID = "Corruption";
/* 19 */     this.owner = owner;
/* 20 */     this.amount = -1;
/* 21 */     this.description = DESCRIPTIONS[0];
/* 22 */     loadRegion("corruption");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 27 */     this.description = DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCardDraw(AbstractCard card) {
/* 32 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 33 */       card.setCostForTurn(-9);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 39 */     if (card.type == AbstractCard.CardType.SKILL) {
/* 40 */       flash();
/* 41 */       action.exhaustCard = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\CorruptionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */