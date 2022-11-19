/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ public class ForethoughtAction extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ForethoughtAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   private boolean chooseAny;
/*    */   
/*    */   public ForethoughtAction(boolean upgraded) {
/* 18 */     this.p = AbstractDungeon.player;
/* 19 */     this.duration = Settings.ACTION_DUR_FAST;
/* 20 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 21 */     this.chooseAny = upgraded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 27 */       if (this.p.hand.isEmpty()) {
/* 28 */         this.isDone = true; return;
/*    */       } 
/* 30 */       if (this.p.hand.size() == 1 && !this.chooseAny) {
/* 31 */         AbstractCard c = this.p.hand.getTopCard();
/* 32 */         if (c.cost > 0) {
/* 33 */           c.freeToPlayOnce = true;
/*    */         }
/* 35 */         this.p.hand.moveToBottomOfDeck(c);
/* 36 */         AbstractDungeon.player.hand.refreshHandLayout();
/* 37 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 40 */       if (!this.chooseAny) {
/* 41 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
/*    */       } else {
/* 43 */         AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
/*    */       } 
/* 45 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 52 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 53 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 54 */         if (c.cost > 0) {
/* 55 */           c.freeToPlayOnce = true;
/*    */         }
/* 57 */         this.p.hand.moveToBottomOfDeck(c);
/*    */       } 
/* 59 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 60 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 63 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ForethoughtAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */