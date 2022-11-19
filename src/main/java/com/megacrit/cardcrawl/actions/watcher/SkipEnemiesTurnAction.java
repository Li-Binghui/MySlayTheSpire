/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkipEnemiesTurnAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 13 */     (AbstractDungeon.getCurrRoom()).skipMonsterTurn = true;
/* 14 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\SkipEnemiesTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */