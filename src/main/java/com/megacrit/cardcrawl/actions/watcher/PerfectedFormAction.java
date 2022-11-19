/*    */ package com.megacrit.cardcrawl.actions.watcher;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerfectedFormAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 12 */     this.isDone = true;
/* 13 */     boolean hadCalm = false;
/* 14 */     boolean hadCourage = false;
/* 15 */     boolean hadWrath = false;
/*    */     
/* 17 */     if (!AbstractDungeon.player.stance.ID.equals("Divinity"));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\PerfectedFormAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */