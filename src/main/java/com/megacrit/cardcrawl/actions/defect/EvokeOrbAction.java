/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EvokeOrbAction extends AbstractGameAction {
/*    */   private int orbCount;
/*    */   
/*    */   public EvokeOrbAction(int amount) {
/* 11 */     if (Settings.FAST_MODE) {
/* 12 */       this.duration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 14 */       this.duration = Settings.ACTION_DUR_FAST;
/*    */     } 
/* 16 */     this.duration = this.startDuration;
/* 17 */     this.orbCount = amount;
/* 18 */     this.actionType = ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startDuration) {
/* 24 */       for (int i = 0; i < this.orbCount; i++) {
/* 25 */         AbstractDungeon.player.evokeOrb();
/*    */       }
/*    */     }
/*    */     
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\EvokeOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */