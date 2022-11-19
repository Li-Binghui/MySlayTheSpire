/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BetterDrawPileToHandAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
/*    */   private AbstractPlayer player;
/*    */   private int numberOfCards;
/*    */   private boolean optional;
/*    */   
/*    */   public BetterDrawPileToHandAction(int numberOfCards, boolean optional) {
/* 21 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 22 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/* 23 */     this.player = AbstractDungeon.player;
/* 24 */     this.numberOfCards = numberOfCards;
/* 25 */     this.optional = optional;
/*    */   }
/*    */   
/*    */   public BetterDrawPileToHandAction(int numberOfCards) {
/* 29 */     this(numberOfCards, false);
/*    */   }
/*    */   
/*    */   public void update() {
/* 33 */     if (this.duration == this.startDuration) {
/* 34 */       if (this.player.drawPile.isEmpty() || this.numberOfCards <= 0) {
/* 35 */         this.isDone = true; return;
/*    */       } 
/* 37 */       if (this.player.drawPile.size() <= this.numberOfCards && !this.optional) {
/* 38 */         ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
/* 39 */         for (AbstractCard c : this.player.drawPile.group) {
/* 40 */           cardsToMove.add(c);
/*    */         }
/* 42 */         for (AbstractCard c : cardsToMove) {
/* 43 */           if (this.player.hand.size() == 10) {
/* 44 */             this.player.drawPile.moveToDiscardPile(c);
/* 45 */             this.player.createHandIsFullDialog(); continue;
/*    */           } 
/* 47 */           this.player.drawPile.moveToHand(c, this.player.drawPile);
/*    */         } 
/*    */         
/* 50 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 53 */       CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 54 */       for (AbstractCard c : this.player.drawPile.group) {
/* 55 */         temp.addToTop(c);
/*    */       }
/* 57 */       temp.sortAlphabetically(true);
/* 58 */       temp.sortByRarityPlusStatusCardType(false);
/* 59 */       if (this.numberOfCards == 1) {
/* 60 */         if (this.optional) {
/* 61 */           AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, TEXT[0]);
/*    */         } else {
/* 63 */           AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], false);
/*    */         }
/*    */       
/* 66 */       } else if (this.optional) {
/* 67 */         AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */         
/* 73 */         AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 80 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 84 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 85 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 86 */         if (this.player.hand.size() == 10) {
/* 87 */           this.player.drawPile.moveToDiscardPile(c);
/* 88 */           this.player.createHandIsFullDialog(); continue;
/*    */         } 
/* 90 */         this.player.drawPile.moveToHand(c, this.player.drawPile);
/*    */       } 
/*    */       
/* 93 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 94 */       AbstractDungeon.player.hand.refreshHandLayout();
/*    */     } 
/* 96 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\BetterDrawPileToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */