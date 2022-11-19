/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EmptyBodyAction
/*    */   extends AbstractGameAction {
/*    */   private int additionalDraw;
/*    */   
/*    */   public EmptyBodyAction(int additionalDraw) {
/* 12 */     this.additionalDraw = additionalDraw;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (AbstractDungeon.player.stance.ID.equals("Neutral")) {
/* 18 */       addToBot(new ChangeStanceAction("Neutral"));
/* 19 */       addToBot((AbstractGameAction)new DrawCardAction(1 + this.additionalDraw));
/*    */     } else {
/* 21 */       addToBot((AbstractGameAction)new DrawCardAction(1));
/*    */     } 
/* 23 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\EmptyBodyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */