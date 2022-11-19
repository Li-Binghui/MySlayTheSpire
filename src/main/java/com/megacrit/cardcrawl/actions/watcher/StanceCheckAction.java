/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class StanceCheckAction extends AbstractGameAction {
/*    */   private AbstractGameAction actionToBuffer;
/*  8 */   private String stanceToCheck = null;
/*    */   
/*    */   public StanceCheckAction(String stanceToCheck, AbstractGameAction actionToCheck) {
/* 11 */     this.actionToBuffer = actionToCheck;
/* 12 */     this.stanceToCheck = stanceToCheck;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 17 */     if (AbstractDungeon.player.stance.ID.equals(this.stanceToCheck)) {
/* 18 */       addToBot(this.actionToBuffer);
/*    */     }
/* 20 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\StanceCheckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */