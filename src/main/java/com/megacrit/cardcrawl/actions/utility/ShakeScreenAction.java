/*    */ package com.megacrit.cardcrawl.actions.utility;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ScreenShake;
/*    */ 
/*    */ public class ShakeScreenAction
/*    */   extends AbstractGameAction {
/*    */   private float startDur;
/*    */   ScreenShake.ShakeDur shakeDur;
/*    */   ScreenShake.ShakeIntensity intensity;
/*    */   
/*    */   public ShakeScreenAction(float duration, ScreenShake.ShakeDur dur, ScreenShake.ShakeIntensity intensity) {
/* 14 */     this.duration = duration;
/* 15 */     this.startDur = duration;
/* 16 */     this.shakeDur = dur;
/* 17 */     this.intensity = intensity;
/* 18 */     this.actionType = ActionType.WAIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startDur) {
/* 24 */       CardCrawlGame.screenShake.shake(this.intensity, this.shakeDur, false);
/*    */     }
/* 26 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\action\\utility\ShakeScreenAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */