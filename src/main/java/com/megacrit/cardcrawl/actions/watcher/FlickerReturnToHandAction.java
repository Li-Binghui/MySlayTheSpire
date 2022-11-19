/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ public class FlickerReturnToHandAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractCard card;
/*    */   
/*    */   public FlickerReturnToHandAction(AbstractCard card) {
/* 15 */     this.card = card;
/* 16 */     this.duration = Settings.ACTION_DUR_FASTER;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_FASTER && 
/* 22 */       AbstractDungeon.player.discardPile.contains(this.card) && AbstractDungeon.player.hand
/* 23 */       .size() < 10) {
/* 24 */       this.card.returnToHand = true;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\FlickerReturnToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */