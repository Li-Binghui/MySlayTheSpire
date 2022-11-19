/*    */ package com.megacrit.cardcrawl.actions.watcher;
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
/*    */ 
/*    */ public class EmotionalTurmoilAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 17 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/*    */       
/* 19 */       if (AbstractDungeon.player.stance.ID.equals("Calm")) {
/* 20 */         addToBot(new ChangeStanceAction("Wrath"));
/* 21 */       } else if (AbstractDungeon.player.stance.ID.equals("Wrath")) {
/* 22 */         addToBot(new ChangeStanceAction("Calm"));
/*    */       } 
/*    */       
/* 25 */       if (Settings.FAST_MODE) {
/* 26 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 31 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\watcher\EmotionalTurmoilAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */