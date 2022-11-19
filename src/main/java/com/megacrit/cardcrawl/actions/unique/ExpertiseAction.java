/*    */ package com.megacrit.cardcrawl.actions.unique;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class ExpertiseAction
/*    */   extends AbstractGameAction {
/*    */   public ExpertiseAction(AbstractCreature source, int amount) {
/* 11 */     setValues(this.target, source, amount);
/* 12 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     int toDraw = this.amount - AbstractDungeon.player.hand.size();
/* 18 */     if (toDraw > 0) {
/* 19 */       addToTop((AbstractGameAction)new DrawCardAction(this.source, toDraw));
/*    */     }
/*    */ 
/*    */     
/* 23 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\unique\ExpertiseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */