/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class AnimateOrbAction extends AbstractGameAction {
/*    */   private int orbCount;
/*    */   
/*    */   public AnimateOrbAction(int amount) {
/* 10 */     this.orbCount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     for (int i = 0; i < this.orbCount; i++) {
/* 16 */       AbstractDungeon.player.triggerEvokeAnimation(i);
/*    */     }
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\AnimateOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */