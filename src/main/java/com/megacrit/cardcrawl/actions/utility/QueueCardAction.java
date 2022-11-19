/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ @Deprecated
/*    */ public class QueueCardAction extends AbstractGameAction {
/*    */   private AbstractCard card;
/*    */   
/*    */   public QueueCardAction() {
/* 16 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   
/*    */   public QueueCardAction(AbstractCard card, AbstractCreature target) {
/* 20 */     this.duration = Settings.ACTION_DUR_FAST;
/* 21 */     this.card = card;
/* 22 */     this.target = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 28 */       if (this.card == null) {
/*    */         
/* 30 */         AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem());
/*    */       
/*    */       }
/* 33 */       else if (!queueContains(this.card)) {
/* 34 */         AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this.card, (AbstractMonster)this.target));
/*    */       } 
/*    */       
/* 37 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean queueContains(AbstractCard card) {
/* 42 */     for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
/* 43 */       if (i.card == card) {
/* 44 */         return true;
/*    */       }
/*    */     } 
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\QueueCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */