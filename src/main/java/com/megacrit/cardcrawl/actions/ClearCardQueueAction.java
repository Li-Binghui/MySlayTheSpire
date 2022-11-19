/*    */ package com.megacrit.cardcrawl.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.CardQueueItem;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ public class ClearCardQueueAction
/*    */   extends AbstractGameAction {
/*    */   public void update() {
/* 10 */     for (CardQueueItem c : AbstractDungeon.actionManager.cardQueue) {
/* 11 */       if (AbstractDungeon.player.limbo.contains(c.card)) {
/* 12 */         AbstractDungeon.effectList.add(new ExhaustCardEffect(c.card));
/* 13 */         AbstractDungeon.player.limbo.group.remove(c.card);
/*    */       } 
/*    */     } 
/*    */     
/* 17 */     AbstractDungeon.actionManager.cardQueue.clear();
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\ClearCardQueueAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */