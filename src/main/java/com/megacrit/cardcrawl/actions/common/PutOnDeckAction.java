/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class PutOnDeckAction extends AbstractGameAction {
/* 12 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PutOnDeckAction");
/* 13 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private AbstractPlayer p;
/*    */   private boolean isRandom;
/*    */   public static int numPlaced;
/*    */   
/*    */   public PutOnDeckAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
/* 20 */     this.target = target;
/* 21 */     this.p = (AbstractPlayer)target;
/* 22 */     setValues(target, source, amount);
/* 23 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 24 */     this.isRandom = isRandom;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == 0.5F) {
/* 30 */       if (this.p.hand.size() < this.amount) {
/* 31 */         this.amount = this.p.hand.size();
/*    */       }
/*    */       
/* 34 */       if (this.isRandom) {
/* 35 */         for (int i = 0; i < this.amount; i++) {
/* 36 */           this.p.hand.moveToDeck(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng), false);
/*    */         }
/*    */       } else {
/* 39 */         if (this.p.hand.group.size() > this.amount) {
/* 40 */           numPlaced = this.amount;
/* 41 */           AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
/* 42 */           tickDuration();
/*    */           return;
/*    */         } 
/* 45 */         for (int i = 0; i < this.p.hand.size(); i++) {
/* 46 */           this.p.hand.moveToDeck(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng), this.isRandom);
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 55 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 56 */         this.p.hand.moveToDeck(c, false);
/*    */       }
/* 58 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 59 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 62 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\PutOnDeckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */