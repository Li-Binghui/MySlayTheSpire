/*     */ package com.megacrit.cardcrawl.actions.unique;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
/*     */ 
/*     */ public class DualWieldAction
/*     */   extends AbstractGameAction {
/*  16 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DualWieldAction");
/*  17 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private static final float DURATION_PER_CARD = 0.25F;
/*     */   private AbstractPlayer p;
/*  21 */   private int dupeAmount = 1;
/*  22 */   private ArrayList<AbstractCard> cannotDuplicate = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public DualWieldAction(AbstractCreature source, int amount) {
/*  26 */     setValues((AbstractCreature)AbstractDungeon.player, source, amount);
/*  27 */     this.actionType = ActionType.DRAW;
/*  28 */     this.duration = 0.25F;
/*  29 */     this.p = AbstractDungeon.player;
/*  30 */     this.dupeAmount = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  36 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*     */       
/*  38 */       for (AbstractCard c : this.p.hand.group) {
/*  39 */         if (!isDualWieldable(c)) {
/*  40 */           this.cannotDuplicate.add(c);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  45 */       if (this.cannotDuplicate.size() == this.p.hand.group.size()) {
/*  46 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  50 */       if (this.p.hand.group.size() - this.cannotDuplicate.size() == 1) {
/*  51 */         for (AbstractCard c : this.p.hand.group) {
/*  52 */           if (isDualWieldable(c)) {
/*  53 */             for (int i = 0; i < this.dupeAmount; i++) {
/*  54 */               addToTop((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
/*     */             }
/*  56 */             this.isDone = true;
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*  64 */       this.p.hand.group.removeAll(this.cannotDuplicate);
/*     */       
/*  66 */       if (this.p.hand.group.size() > 1) {
/*  67 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
/*  68 */         tickDuration(); return;
/*     */       } 
/*  70 */       if (this.p.hand.group.size() == 1) {
/*  71 */         for (int i = 0; i < this.dupeAmount; i++) {
/*  72 */           addToTop((AbstractGameAction)new MakeTempCardInHandAction(this.p.hand.getTopCard().makeStatEquivalentCopy()));
/*     */         }
/*  74 */         returnCards();
/*  75 */         this.isDone = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  80 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*  81 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/*  82 */         addToTop((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
/*  83 */         for (int i = 0; i < this.dupeAmount; i++) {
/*  84 */           addToTop((AbstractGameAction)new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
/*     */         }
/*     */       } 
/*     */       
/*  88 */       returnCards();
/*     */       
/*  90 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*  91 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/*  92 */       this.isDone = true;
/*     */     } 
/*     */     
/*  95 */     tickDuration();
/*     */   }
/*     */   
/*     */   private void returnCards() {
/*  99 */     for (AbstractCard c : this.cannotDuplicate) {
/* 100 */       this.p.hand.addToTop(c);
/*     */     }
/* 102 */     this.p.hand.refreshHandLayout();
/*     */   }
/*     */   
/*     */   private boolean isDualWieldable(AbstractCard card) {
/* 106 */     return (card.type.equals(AbstractCard.CardType.ATTACK) || card.type.equals(AbstractCard.CardType.POWER));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\DualWieldAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */