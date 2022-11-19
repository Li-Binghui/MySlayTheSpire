/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class RecycleAction extends AbstractGameAction {
/* 14 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RecycleAction");
/* 15 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 27 */       if (this.p.hand.isEmpty()) {
/* 28 */         this.isDone = true; return;
/*    */       } 
/* 30 */       if (this.p.hand.size() == 1) {
/* 31 */         if ((this.p.hand.getBottomCard()).costForTurn == -1) {
/* 32 */           addToTop((AbstractGameAction)new GainEnergyAction(EnergyPanel.getCurrentEnergy()));
/* 33 */         } else if ((this.p.hand.getBottomCard()).costForTurn > 0) {
/* 34 */           addToTop((AbstractGameAction)new GainEnergyAction((this.p.hand.getBottomCard()).costForTurn));
/*    */         } 
/* 36 */         this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
/* 37 */         tickDuration();
/*    */         return;
/*    */       } 
/* 40 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
/* 41 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 47 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 48 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 49 */         if (c.costForTurn == -1) {
/* 50 */           addToTop((AbstractGameAction)new GainEnergyAction(EnergyPanel.getCurrentEnergy()));
/* 51 */         } else if (c.costForTurn > 0) {
/* 52 */           addToTop((AbstractGameAction)new GainEnergyAction(c.costForTurn));
/*    */         } 
/* 54 */         this.p.hand.moveToExhaustPile(c);
/*    */       } 
/* 56 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/* 57 */       AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
/*    */     } 
/*    */     
/* 60 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\RecycleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */