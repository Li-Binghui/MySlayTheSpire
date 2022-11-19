/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScrapeFollowUpAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 18 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(0.4F));
/*    */     
/* 20 */     tickDuration();
/*    */     
/* 22 */     if (this.isDone)
/* 23 */       for (AbstractCard c : DrawCardAction.drawnCards) {
/* 24 */         if (c.costForTurn != 0 && !c.freeToPlayOnce) {
/* 25 */           AbstractDungeon.player.hand.moveToDiscardPile(c);
/* 26 */           c.triggerOnManualDiscard();
/* 27 */           GameActionManager.incrementDiscard(false);
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\ScrapeFollowUpAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */