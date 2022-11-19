/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class AllCostToHandAction extends AbstractGameAction {
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public AllCostToHandAction(int costToTarget) {
/* 14 */     this.p = AbstractDungeon.player;
/* 15 */     setValues((AbstractCreature)this.p, (AbstractCreature)AbstractDungeon.player, this.amount);
/* 16 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 17 */     this.costTarget = costToTarget;
/*    */   }
/*    */   private int costTarget;
/*    */   
/*    */   public void update() {
/* 22 */     if (this.p.discardPile.size() > 0) {
/* 23 */       for (AbstractCard card : this.p.discardPile.group) {
/* 24 */         if (card.cost == this.costTarget || card.freeToPlayOnce) {
/* 25 */           addToBot((AbstractGameAction)new DiscardToHandAction(card));
/*    */         }
/*    */       } 
/*    */     }
/* 29 */     tickDuration();
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\AllCostToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */