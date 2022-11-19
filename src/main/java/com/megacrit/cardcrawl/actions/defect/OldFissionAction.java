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
/*    */ public class OldFissionAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 17 */       int orbCount = AbstractDungeon.player.filledOrbCount();
/*    */       
/* 19 */       for (int i = 0; i < orbCount; i++) {
/* 20 */         addToBot(new AnimateOrbAction(1));
/* 21 */         addToBot(new EvokeOrbAction(1));
/*    */       } 
/*    */       
/* 24 */       addToBot(new IncreaseMaxOrbAction(orbCount));
/*    */     } 
/*    */     
/* 27 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\defect\OldFissionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */