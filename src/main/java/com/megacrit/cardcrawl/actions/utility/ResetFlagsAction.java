/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ResetFlagsAction
/*    */   extends AbstractGameAction {
/* 11 */   private static final Logger logger = LogManager.getLogger(ResetFlagsAction.class.getName());
/*    */   private AbstractCard card;
/*    */   
/*    */   public ResetFlagsAction(AbstractCard card) {
/* 15 */     this.duration = Settings.ACTION_DUR_FAST;
/* 16 */     this.card = card;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 22 */       logger.info("Resetting flags");
/* 23 */       this.card = this.card.makeStatEquivalentCopy();
/* 24 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ResetFlagsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */