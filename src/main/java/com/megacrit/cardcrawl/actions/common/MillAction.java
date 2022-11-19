/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class MillAction
/*    */   extends AbstractGameAction {
/*    */   public MillAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 10 */     setValues(target, source, amount);
/* 11 */     this.actionType = ActionType.CARD_MANIPULATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 16 */     if (this.duration == 0.5F) {
/* 17 */       if (this.amount <= AbstractDungeon.player.drawPile.size()) {
/* 18 */         for (int i = 0; i < this.amount; i++) {
/* 19 */           AbstractDungeon.player.drawPile.moveToDiscardPile(AbstractDungeon.player.drawPile.getTopCard());
/*    */         }
/*    */       } else {
/* 22 */         for (int i = 0; i < AbstractDungeon.player.drawPile.size(); i++) {
/* 23 */           AbstractDungeon.player.drawPile.moveToDiscardPile(AbstractDungeon.player.drawPile.getTopCard());
/*    */         }
/*    */       } 
/*    */     }
/*    */     
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MillAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */