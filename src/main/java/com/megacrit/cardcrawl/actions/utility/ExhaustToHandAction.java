/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ExhaustToHandAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public ExhaustToHandAction(AbstractCard card) {
/* 12 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 13 */     this.card = card;
/* 14 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 20 */       if (AbstractDungeon.player.exhaustPile.contains(this.card) && AbstractDungeon.player.hand
/* 21 */         .size() < 10) {
/* 22 */         AbstractDungeon.player.hand.addToHand(this.card);
/* 23 */         this.card.stopGlowing();
/* 24 */         this.card.unhover();
/* 25 */         this.card.unfadeOut();
/* 26 */         this.card.setAngle(0.0F, true);
/* 27 */         this.card.lighten(false);
/* 28 */         this.card.drawScale = 0.12F;
/* 29 */         this.card.targetDrawScale = 0.75F;
/* 30 */         this.card.applyPowers();
/* 31 */         AbstractDungeon.player.exhaustPile.removeCard(this.card);
/*    */       } 
/*    */       
/* 34 */       AbstractDungeon.player.hand.refreshHandLayout();
/* 35 */       AbstractDungeon.player.hand.glowCheck();
/*    */     } 
/*    */     
/* 38 */     tickDuration();
/* 39 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ExhaustToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */