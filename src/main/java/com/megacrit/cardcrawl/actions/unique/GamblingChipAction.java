/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class GamblingChipAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/* 17 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("GamblingChipAction");
/* 18 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private boolean notchip;
/*    */   
/*    */   public GamblingChipAction(AbstractCreature source) {
/* 22 */     setValues((AbstractCreature)AbstractDungeon.player, source, -1);
/* 23 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 24 */     this.notchip = false;
/*    */   }
/*    */   
/*    */   public GamblingChipAction(AbstractCreature source, boolean notChip) {
/* 28 */     setValues((AbstractCreature)AbstractDungeon.player, source, -1);
/* 29 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 30 */     this.notchip = notChip;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 35 */     if (this.duration == 0.5F) {
/* 36 */       if (this.notchip) {
/* 37 */         AbstractDungeon.handCardSelectScreen.open(TEXT[1], 99, true, true);
/*    */       } else {
/* 39 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
/*    */       } 
/* 41 */       addToBot((AbstractGameAction)new WaitAction(0.25F));
/* 42 */       tickDuration();
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 47 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/*    */ 
/*    */       
/* 50 */       if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
/* 51 */         addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature)this.p, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
/*    */ 
/*    */         
/* 54 */         for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 55 */           AbstractDungeon.player.hand.moveToDiscardPile(c);
/* 56 */           GameActionManager.incrementDiscard(false);
/* 57 */           c.triggerOnManualDiscard();
/*    */         } 
/*    */       } 
/*    */       
/* 61 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 64 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\GamblingChipAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */