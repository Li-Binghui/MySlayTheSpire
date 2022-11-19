/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
/*    */ 
/*    */ public class ContemplateAction
/*    */   extends AbstractGameAction {
/* 14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
/* 15 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   private boolean upgraded;
/*    */   private AbstractPlayer p;
/* 19 */   private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
/*    */   
/*    */   public ContemplateAction(boolean upgraded) {
/* 22 */     this.duration = Settings.ACTION_DUR_FAST;
/* 23 */     this.upgraded = upgraded;
/* 24 */     this.p = AbstractDungeon.player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */ 
/*    */       
/* 32 */       for (AbstractCard c : this.p.hand.group) {
/* 33 */         if (!c.canUpgrade()) {
/* 34 */           this.cannotUpgrade.add(c);
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 39 */       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
/* 40 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 44 */       if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
/* 45 */         for (AbstractCard c : this.p.hand.group) {
/* 46 */           if (c.canUpgrade()) {
/* 47 */             c.upgrade();
/* 48 */             c.superFlash();
/* 49 */             this.isDone = true;
/*    */             
/*    */             return;
/*    */           } 
/*    */         } 
/*    */       }
/* 55 */       if (this.upgraded) {
/*    */ 
/*    */         
/* 58 */         this.p.hand.group.removeAll(this.cannotUpgrade);
/*    */         
/* 60 */         if (this.p.hand.group.size() > 1) {
/* 61 */           AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, true);
/* 62 */           tickDuration(); return;
/*    */         } 
/* 64 */         if (this.p.hand.group.size() == 1) {
/* 65 */           this.p.hand.getTopCard().upgrade();
/* 66 */           this.p.hand.getTopCard().superFlash();
/* 67 */           returnCards();
/* 68 */           this.isDone = true;
/*    */         } 
/*    */       } else {
/* 71 */         AbstractCard c = this.p.hand.group.get(AbstractDungeon.cardRandomRng.random(0, this.p.hand.group.size() - 1));
/* 72 */         c.upgrade();
/* 73 */         c.superFlash();
/* 74 */         this.isDone = true;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 79 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 80 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 81 */         c.upgrade();
/* 82 */         c.superFlash();
/* 83 */         this.p.hand.addToTop(c);
/*    */       } 
/*    */       
/* 86 */       returnCards();
/* 87 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/* 88 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/* 89 */       this.isDone = true;
/*    */     } 
/*    */     
/* 92 */     tickDuration();
/*    */   }
/*    */   
/*    */   private void returnCards() {
/* 96 */     for (AbstractCard c : this.cannotUpgrade) {
/* 97 */       this.p.hand.addToTop(c);
/*    */     }
/* 99 */     this.p.hand.refreshHandLayout();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\ContemplateAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */