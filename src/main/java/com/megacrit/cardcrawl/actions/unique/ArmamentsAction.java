/*     */ package com.megacrit.cardcrawl.actions.unique;
/*     */ 
/*     */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
/*     */ 
/*     */ public class ArmamentsAction
/*     */   extends AbstractGameAction {
/*  14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
/*  15 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private AbstractPlayer p;
/*  18 */   private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
/*     */   private boolean upgraded = false;
/*     */   
/*     */   public ArmamentsAction(boolean armamentsPlus) {
/*  22 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  23 */     this.p = AbstractDungeon.player;
/*  24 */     this.duration = Settings.ACTION_DUR_FAST;
/*  25 */     this.upgraded = armamentsPlus;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  30 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*     */ 
/*     */       
/*  33 */       if (this.upgraded) {
/*  34 */         for (AbstractCard c : this.p.hand.group) {
/*  35 */           if (c.canUpgrade()) {
/*  36 */             c.upgrade();
/*  37 */             c.superFlash();
/*  38 */             c.applyPowers();
/*     */           } 
/*     */         } 
/*  41 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  46 */       for (AbstractCard c : this.p.hand.group) {
/*  47 */         if (!c.canUpgrade()) {
/*  48 */           this.cannotUpgrade.add(c);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  53 */       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
/*  54 */         this.isDone = true;
/*     */         
/*     */         return;
/*     */       } 
/*  58 */       if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
/*  59 */         for (AbstractCard c : this.p.hand.group) {
/*  60 */           if (c.canUpgrade()) {
/*  61 */             c.upgrade();
/*  62 */             c.superFlash();
/*  63 */             c.applyPowers();
/*  64 */             this.isDone = true;
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*  72 */       this.p.hand.group.removeAll(this.cannotUpgrade);
/*     */       
/*  74 */       if (this.p.hand.group.size() > 1) {
/*  75 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
/*  76 */         tickDuration(); return;
/*     */       } 
/*  78 */       if (this.p.hand.group.size() == 1) {
/*  79 */         this.p.hand.getTopCard().upgrade();
/*  80 */         this.p.hand.getTopCard().superFlash();
/*  81 */         returnCards();
/*  82 */         this.isDone = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  87 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*  88 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/*  89 */         c.upgrade();
/*  90 */         c.superFlash();
/*  91 */         c.applyPowers();
/*  92 */         this.p.hand.addToTop(c);
/*     */       } 
/*     */       
/*  95 */       returnCards();
/*  96 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*  97 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/*  98 */       this.isDone = true;
/*     */     } 
/*     */     
/* 101 */     tickDuration();
/*     */   }
/*     */   
/*     */   private void returnCards() {
/* 105 */     for (AbstractCard c : this.cannotUpgrade) {
/* 106 */       this.p.hand.addToTop(c);
/*     */     }
/* 108 */     this.p.hand.refreshHandLayout();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ArmamentsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */