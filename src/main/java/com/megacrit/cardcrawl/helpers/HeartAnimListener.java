/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeartAnimListener
/*    */   implements AnimationState.AnimationStateListener
/*    */ {
/*    */   public void event(int trackIndex, Event event) {
/* 15 */     if (!AbstractDungeon.isScreenUp && event.getData().getName().equals("maxbeat")) {
/* 16 */       CardCrawlGame.sound.playAV("HEART_SIMPLE", MathUtils.random(-0.05F, 0.05F), 0.75F);
/* 17 */       CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void complete(int trackIndex, int loopCount) {}
/*    */   
/*    */   public void start(int trackIndex) {}
/*    */   
/*    */   public void end(int trackIndex) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\HeartAnimListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */