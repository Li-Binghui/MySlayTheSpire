/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExhaustAllEtherealAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 17 */       if (c.isEthereal) {
/* 18 */         addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
/*    */       }
/*    */     } 
/* 21 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ExhaustAllEtherealAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */