/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RandomCardFromDiscardPileToHandAction
/*    */   extends AbstractGameAction {
/*    */   public RandomCardFromDiscardPileToHandAction() {
/* 12 */     this.p = AbstractDungeon.player;
/* 13 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, this.amount);
/* 14 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */   }
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public void update() {
/* 19 */     if (this.p.discardPile.size() > 0) {
/* 20 */       AbstractCard card = this.p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng);
/* 21 */       this.p.hand.addToHand(card);
/* 22 */       card.lighten(false);
/* 23 */       this.p.discardPile.removeCard(card);
/* 24 */       this.p.hand.refreshHandLayout();
/*    */     } 
/* 26 */     tickDuration();
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RandomCardFromDiscardPileToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */