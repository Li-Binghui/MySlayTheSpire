/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class RetreatingHandAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public RetreatingHandAction(AbstractCard card) {
/* 12 */     this.card = card;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     this.card
/* 18 */       .returnToHand = (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat
/* 19 */         .size() - 2)).type == AbstractCard.CardType.ATTACK);
/* 20 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\RetreatingHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */