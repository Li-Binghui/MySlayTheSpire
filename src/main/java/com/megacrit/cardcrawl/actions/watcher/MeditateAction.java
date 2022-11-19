/*    */ package com.megacrit.cardcrawl.actions.watcher;
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
/*    */ public class MeditateAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
/*    */   private AbstractPlayer player;
/*    */   private int numberOfCards;
/*    */   private boolean optional = false;
/*    */   
/*    */   public MeditateAction(int numberOfCards) {
/* 21 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 22 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/* 23 */     this.player = AbstractDungeon.player;
/* 24 */     this.numberOfCards = numberOfCards;
/*    */   }
/*    */   
/*    */   public void update() {
/* 28 */     if (this.duration == this.startDuration) {
/* 29 */       if (this.player.discardPile.isEmpty() || this.numberOfCards <= 0) {
/* 30 */         this.isDone = true; return;
/*    */       } 
/* 32 */       if (this.player.discardPile.size() <= this.numberOfCards && !this.optional) {
/* 33 */         ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
/* 34 */         for (AbstractCard c : this.player.discardPile.group) {
/* 35 */           cardsToMove.add(c);
/* 36 */           c.retain = true;
/*    */         } 
/* 38 */         for (AbstractCard c : cardsToMove) {
/* 39 */           if (this.player.hand.size() < 10) {
/* 40 */             this.player.hand.addToHand(c);
/* 41 */             this.player.discardPile.removeCard(c);
/*    */           } 
/* 43 */           c.lighten(false);
/*    */         } 
/* 45 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 48 */       if (this.numberOfCards == 1) {
/* 49 */         if (this.optional) {
/* 50 */           AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[0]);
/*    */         
/*    */         }
/*    */         else {
/*    */ 
/*    */           
/* 56 */           AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
/*    */ 
/*    */         
/*    */         }
/*    */ 
/*    */       
/*    */       }
/* 63 */       else if (this.optional) {
/* 64 */         AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */         
/* 70 */         AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 77 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/* 81 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 82 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 83 */         if (this.player.hand.size() < 10) {
/* 84 */           this.player.hand.addToHand(c);
/* 85 */           c.retain = true;
/* 86 */           this.player.discardPile.removeCard(c);
/*    */         } 
/* 88 */         c.lighten(false);
/* 89 */         c.unhover();
/*    */       } 
/* 91 */       for (AbstractCard c : this.player.discardPile.group) {
/* 92 */         c.unhover();
/* 93 */         c.target_x = CardGroup.DISCARD_PILE_X;
/* 94 */         c.target_y = 0.0F;
/*    */       } 
/* 96 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 97 */       AbstractDungeon.player.hand.refreshHandLayout();
/*    */     } 
/* 99 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\MeditateAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */