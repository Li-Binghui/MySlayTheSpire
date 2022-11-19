/*    */ package com.megacrit.cardcrawl.powers;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ 
/*    */ public class CreativeAIPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Creative AI";
/* 13 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Creative AI");
/* 14 */   public static final String NAME = powerStrings.NAME;
/* 15 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public CreativeAIPower(AbstractCreature owner, int amt) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Creative AI";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amt;
/* 22 */     updateDescription();
/* 23 */     loadRegion("ai");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 28 */     for (int i = 0; i < this.amount; i++) {
/* 29 */       AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.POWER).makeCopy();
/*    */       
/* 31 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(card));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 37 */     if (this.amount > 1) {
/* 38 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } else {
/* 40 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\CreativeAIPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */