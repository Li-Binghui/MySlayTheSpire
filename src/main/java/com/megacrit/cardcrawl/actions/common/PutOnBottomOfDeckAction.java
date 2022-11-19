/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class PutOnBottomOfDeckAction extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/*    */   private boolean isRandom;
/*    */   public static int numPlaced;
/*    */   
/*    */   public PutOnBottomOfDeckAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
/* 16 */     this.target = target;
/* 17 */     this.p = (AbstractPlayer)target;
/* 18 */     setValues(target, source, amount);
/* 19 */     this.duration = Settings.ACTION_DUR_FAST;
/* 20 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 26 */       if (this.p.hand.size() < this.amount) {
/* 27 */         this.amount = this.p.hand.size();
/*    */       }
/*    */       
/* 30 */       if (this.isRandom) {
/* 31 */         for (int i = 0; i < this.amount; i++) {
/* 32 */           this.p.hand.moveToBottomOfDeck(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
/*    */         }
/*    */       } else {
/* 35 */         if (this.p.hand.group.size() > this.amount) {
/* 36 */           numPlaced = this.amount;
/* 37 */           AbstractDungeon.handCardSelectScreen.open("put on the bottom of your draw pile", this.amount, false);
/* 38 */           tickDuration();
/*    */           return;
/*    */         } 
/* 41 */         for (int i = 0; i < this.p.hand.size(); i++) {
/* 42 */           this.p.hand.moveToBottomOfDeck(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 51 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 52 */         this.p.hand.moveToBottomOfDeck(c);
/*    */       }
/* 54 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 55 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 58 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\PutOnBottomOfDeckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */