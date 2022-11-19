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
/*    */ public class SetupAction
/*    */   extends AbstractGameAction {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SetupAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */ 
/*    */   
/* 17 */   private AbstractPlayer p = AbstractDungeon.player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 25 */       if (this.p.hand.isEmpty()) {
/* 26 */         this.isDone = true; return;
/*    */       } 
/* 28 */       if (this.p.hand.size() == 1) {
/* 29 */         AbstractCard c = this.p.hand.getTopCard();
/* 30 */         if (c.cost > 0) {
/* 31 */           c.freeToPlayOnce = true;
/*    */         }
/* 33 */         this.p.hand.moveToDeck(c, false);
/* 34 */         AbstractDungeon.player.hand.refreshHandLayout();
/* 35 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 38 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
/* 39 */       tickDuration();
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 46 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
/* 47 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 48 */         if (c.cost > 0) {
/* 49 */           c.freeToPlayOnce = true;
/*    */         }
/* 51 */         this.p.hand.moveToDeck(c, false);
/*    */       } 
/* 53 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 54 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     } 
/*    */     
/* 57 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\SetupAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */