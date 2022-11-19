/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class GainGoldAction
/*    */   extends AbstractGameAction {
/*    */   public GainGoldAction(int amount) {
/*  9 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   public void update() {
/* 13 */     AbstractDungeon.player.gainGold(this.amount);
/* 14 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\GainGoldAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */