/*     */ package com.megacrit.cardcrawl.actions.defect;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ public class SeekAction extends AbstractGameAction {
/*  14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("AnyCardFromDeckToHandAction");
/*  15 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private AbstractPlayer p;
/*     */   
/*     */   public SeekAction(int amount) {
/*  20 */     this.p = AbstractDungeon.player;
/*  21 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, amount);
/*  22 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  23 */     this.duration = Settings.ACTION_DUR_MED;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  28 */     if (this.duration == Settings.ACTION_DUR_MED) {
/*  29 */       CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*  30 */       for (AbstractCard c : this.p.drawPile.group) {
/*  31 */         tmp.addToRandomSpot(c);
/*     */       }
/*     */       
/*  34 */       if (tmp.size() == 0) {
/*  35 */         this.isDone = true; return;
/*     */       } 
/*  37 */       if (tmp.size() == 1) {
/*  38 */         AbstractCard card = tmp.getTopCard();
/*  39 */         if (this.p.hand.size() == 10) {
/*  40 */           this.p.drawPile.moveToDiscardPile(card);
/*  41 */           this.p.createHandIsFullDialog();
/*     */         } else {
/*  43 */           card.unhover();
/*  44 */           card.lighten(true);
/*  45 */           card.setAngle(0.0F);
/*  46 */           card.drawScale = 0.12F;
/*  47 */           card.targetDrawScale = 0.75F;
/*  48 */           card.current_x = CardGroup.DRAW_PILE_X;
/*  49 */           card.current_y = CardGroup.DRAW_PILE_Y;
/*  50 */           this.p.drawPile.removeCard(card);
/*  51 */           AbstractDungeon.player.hand.addToTop(card);
/*  52 */           AbstractDungeon.player.hand.refreshHandLayout();
/*  53 */           AbstractDungeon.player.hand.applyPowers();
/*     */         } 
/*  55 */         this.isDone = true; return;
/*     */       } 
/*  57 */       if (tmp.size() <= this.amount) {
/*  58 */         for (int i = 0; i < tmp.size(); i++) {
/*  59 */           AbstractCard card = tmp.getNCardFromTop(i);
/*  60 */           if (this.p.hand.size() == 10) {
/*  61 */             this.p.drawPile.moveToDiscardPile(card);
/*  62 */             this.p.createHandIsFullDialog();
/*     */           } else {
/*  64 */             card.unhover();
/*  65 */             card.lighten(true);
/*  66 */             card.setAngle(0.0F);
/*  67 */             card.drawScale = 0.12F;
/*  68 */             card.targetDrawScale = 0.75F;
/*  69 */             card.current_x = CardGroup.DRAW_PILE_X;
/*  70 */             card.current_y = CardGroup.DRAW_PILE_Y;
/*  71 */             this.p.drawPile.removeCard(card);
/*  72 */             AbstractDungeon.player.hand.addToTop(card);
/*  73 */             AbstractDungeon.player.hand.refreshHandLayout();
/*  74 */             AbstractDungeon.player.hand.applyPowers();
/*     */           } 
/*     */         } 
/*  77 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  81 */       if (this.amount == 1) {
/*  82 */         AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
/*     */       } else {
/*  84 */         AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
/*     */       } 
/*  86 */       tickDuration();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  92 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/*  93 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/*  94 */         c.unhover();
/*     */         
/*  96 */         if (this.p.hand.size() == 10) {
/*  97 */           this.p.drawPile.moveToDiscardPile(c);
/*  98 */           this.p.createHandIsFullDialog();
/*     */         } else {
/* 100 */           this.p.drawPile.removeCard(c);
/* 101 */           this.p.hand.addToTop(c);
/*     */         } 
/* 103 */         this.p.hand.refreshHandLayout();
/* 104 */         this.p.hand.applyPowers();
/*     */       } 
/* 106 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 107 */       this.p.hand.refreshHandLayout();
/*     */     } 
/*     */     
/* 110 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\SeekAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */