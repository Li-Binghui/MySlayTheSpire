/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ public class VengeanceAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 12 */     if (GameActionManager.playerHpLastTurn > AbstractDungeon.player.currentHealth) {
/* 13 */       addToBot(new ChangeStanceAction("Wrath"));
/*    */     }
/* 15 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\VengeanceAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */