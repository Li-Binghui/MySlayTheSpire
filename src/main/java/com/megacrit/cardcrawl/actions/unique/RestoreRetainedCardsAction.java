/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;
/*    */ 
/*    */ public class RestoreRetainedCardsAction extends AbstractGameAction {
/*    */   private CardGroup group;
/*    */   
/*    */   public RestoreRetainedCardsAction(CardGroup group) {
/* 14 */     setValues((AbstractCreature)AbstractDungeon.player, this.source, -1);
/* 15 */     this.group = group;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     this.isDone = true;
/*    */     
/* 22 */     for (Iterator<AbstractCard> c = this.group.group.iterator(); c.hasNext(); ) {
/* 23 */       AbstractCard e = c.next();
/* 24 */       if (e.retain || e.selfRetain) {
/* 25 */         e.onRetained();
/* 26 */         AbstractDungeon.player.hand.addToTop(e);
/* 27 */         e.retain = false;
/* 28 */         c.remove();
/*    */       } 
/*    */     } 
/* 31 */     AbstractDungeon.player.hand.refreshHandLayout();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\RestoreRetainedCardsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */