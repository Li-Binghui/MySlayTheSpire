/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.EnemyTurnEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EndTurnAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 14 */     AbstractDungeon.actionManager.endTurn();
/* 15 */     if (!(AbstractDungeon.getCurrRoom()).skipMonsterTurn) {
/* 16 */       AbstractDungeon.topLevelEffects.add(new EnemyTurnEffect());
/*    */     }
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\EndTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */