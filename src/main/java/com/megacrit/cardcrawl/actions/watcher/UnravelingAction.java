/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UnravelingAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 18 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 19 */       for (AbstractCard c : AbstractDungeon.player.hand.group) {
/*    */         
/* 21 */         for (CardQueueItem q : AbstractDungeon.actionManager.cardQueue) {
/* 22 */           if (q.card == c);
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 27 */         c.freeToPlayOnce = true;
/* 28 */         switch (c.target) {
/*    */           case SELF_AND_ENEMY:
/*    */           case ENEMY:
/* 31 */             AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, 
/* 32 */                   AbstractDungeon.getRandomMonster()));
/*    */             continue;
/*    */         } 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 39 */         AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(c, null));
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 45 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\UnravelingAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */