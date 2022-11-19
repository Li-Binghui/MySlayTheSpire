/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class DiscardToHandAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public DiscardToHandAction(AbstractCard card) {
/* 12 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 13 */     this.card = card;
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 20 */       if (AbstractDungeon.player.discardPile.contains(this.card) && AbstractDungeon.player.hand
/* 21 */         .size() < 10) {
/* 22 */         AbstractDungeon.player.hand.addToHand(this.card);
/* 23 */         this.card.unhover();
/* 24 */         this.card.setAngle(0.0F, true);
/* 25 */         this.card.lighten(false);
/* 26 */         this.card.drawScale = 0.12F;
/* 27 */         this.card.targetDrawScale = 0.75F;
/* 28 */         this.card.applyPowers();
/* 29 */         AbstractDungeon.player.discardPile.removeCard(this.card);
/*    */       } 
/*    */       
/* 32 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 33 */       AbstractDungeon.player.hand.glowCheck();
/*    */     } 
/*    */     
/* 36 */     tickDuration();
/* 37 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\DiscardToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */