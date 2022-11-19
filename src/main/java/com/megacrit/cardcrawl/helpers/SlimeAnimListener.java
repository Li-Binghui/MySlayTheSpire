/*    */ package com.megacrit.cardcrawl.helpers;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.esotericsoftware.spine.AnimationState;
/*    */ import com.esotericsoftware.spine.Event;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class SlimeAnimListener
/*    */   implements AnimationState.AnimationStateListener
/*    */ {
/*    */   public void event(int trackIndex, Event event) {
/* 13 */     if (!AbstractDungeon.isScreenUp) {
/* 14 */       int roll = MathUtils.random(3);
/* 15 */       if (roll == 0) {
/* 16 */         CardCrawlGame.sound.play("SLIME_BLINK_1");
/* 17 */       } else if (roll == 1) {
/* 18 */         CardCrawlGame.sound.play("SLIME_BLINK_2");
/* 19 */       } else if (roll == 2) {
/* 20 */         CardCrawlGame.sound.play("SLIME_BLINK_3");
/*    */       } else {
/* 22 */         CardCrawlGame.sound.play("SLIME_BLINK_4");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void complete(int trackIndex, int loopCount) {}
/*    */   
/*    */   public void start(int trackIndex) {}
/*    */   
/*    */   public void end(int trackIndex) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\helpers\SlimeAnimListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */