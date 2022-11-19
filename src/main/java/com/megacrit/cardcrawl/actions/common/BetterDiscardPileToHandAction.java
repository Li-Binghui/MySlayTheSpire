/*     */ package com.megacrit.cardcrawl.actions.common;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class BetterDiscardPileToHandAction
/*     */   extends AbstractGameAction
/*     */ {
/*  15 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;
/*     */   private AbstractPlayer player;
/*     */   private int numberOfCards;
/*     */   private boolean optional;
/*  19 */   private int newCost = 0;
/*     */   private boolean setCost;
/*     */   
/*     */   public BetterDiscardPileToHandAction(int numberOfCards, boolean optional) {
/*  23 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  24 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*  25 */     this.player = AbstractDungeon.player;
/*  26 */     this.numberOfCards = numberOfCards;
/*  27 */     this.optional = optional;
/*  28 */     this.setCost = false;
/*     */   }
/*     */   
/*     */   public BetterDiscardPileToHandAction(int numberOfCards) {
/*  32 */     this(numberOfCards, false);
/*     */   }
/*     */   
/*     */   public BetterDiscardPileToHandAction(int numberOfCards, int newCost) {
/*  36 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  37 */     this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
/*  38 */     this.player = AbstractDungeon.player;
/*  39 */     this.numberOfCards = numberOfCards;
/*  40 */     this.optional = false;
/*  41 */     this.setCost = true;
/*  42 */     this.newCost = newCost;
/*     */   }
/*     */   
/*     */   public void update() {
/*  46 */     if (this.duration == this.startDuration) {
/*  47 */       if (this.player.discardPile.isEmpty() || this.numberOfCards <= 0) {
/*  48 */         this.isDone = true; return;
/*     */       } 
/*  50 */       if (this.player.discardPile.size() <= this.numberOfCards && !this.optional) {
/*  51 */         ArrayList<AbstractCard> cardsToMove = new ArrayList<>();
/*  52 */         for (AbstractCard c : this.player.discardPile.group) {
/*  53 */           cardsToMove.add(c);
/*     */         }
/*  55 */         for (AbstractCard c : cardsToMove) {
/*  56 */           if (this.player.hand.size() < 10) {
/*  57 */             this.player.hand.addToHand(c);
/*  58 */             if (this.setCost) {
/*  59 */               c.setCostForTurn(this.newCost);
/*     */             }
/*  61 */             this.player.discardPile.removeCard(c);
/*     */           } 
/*  63 */           c.lighten(false);
/*  64 */           c.applyPowers();
/*     */         } 
/*     */         
/*  67 */         this.isDone = true;
/*     */         return;
/*     */       } 
/*  70 */       if (this.numberOfCards == 1) {
/*  71 */         if (this.optional) {
/*  72 */           AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[0]);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/*  78 */           AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[0], false);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*  85 */       else if (this.optional) {
/*  86 */         AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, true, TEXT[1] + this.numberOfCards + TEXT[2]);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  92 */         AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       tickDuration();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 104 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 105 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 106 */         if (this.player.hand.size() < 10) {
/* 107 */           this.player.hand.addToHand(c);
/* 108 */           if (this.setCost) {
/* 109 */             c.setCostForTurn(this.newCost);
/*     */           }
/* 111 */           this.player.discardPile.removeCard(c);
/*     */         } 
/* 113 */         c.lighten(false);
/* 114 */         c.unhover();
/* 115 */         c.applyPowers();
/*     */       } 
/* 117 */       for (AbstractCard c : this.player.discardPile.group) {
/* 118 */         c.unhover();
/* 119 */         c.target_x = CardGroup.DISCARD_PILE_X;
/* 120 */         c.target_y = 0.0F;
/*     */       } 
/* 122 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 123 */       AbstractDungeon.player.hand.refreshHandLayout();
/*     */     } 
/* 125 */     tickDuration();
/*     */     
/* 127 */     if (this.isDone)
/* 128 */       for (AbstractCard c : this.player.hand.group)
/* 129 */         c.applyPowers();  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\BetterDiscardPileToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */