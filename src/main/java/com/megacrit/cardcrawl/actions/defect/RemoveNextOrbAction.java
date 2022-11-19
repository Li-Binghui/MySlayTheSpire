/*    */ package com.megacrit.cardcrawl.actions.defect;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RemoveNextOrbAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 17 */       AbstractDungeon.player.removeNextOrb();
/*    */     }
/*    */     
/* 20 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\RemoveNextOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */