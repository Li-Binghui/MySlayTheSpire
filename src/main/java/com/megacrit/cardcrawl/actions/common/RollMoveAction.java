/*    */ package com.megacrit.cardcrawl.actions.common;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class RollMoveAction extends AbstractGameAction {
/*    */   private AbstractMonster monster;
/*    */   
/*    */   public RollMoveAction(AbstractMonster monster) {
/* 10 */     this.monster = monster;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     this.monster.rollMove();
/* 16 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\RollMoveAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */