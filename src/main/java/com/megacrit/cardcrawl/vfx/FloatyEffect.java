/*    */ package com.megacrit.cardcrawl.vfx;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
/*    */ 
/*    */ public class FloatyEffect {
/*    */   public float x;
/*    */   public float y;
/*    */   public float vX;
/*    */   public float vY;
/*    */   private float scale;
/*    */   private float minV;
/*    */   
/*    */   public FloatyEffect(float distanceScale, float speedScale) {
/* 14 */     this.scale = distanceScale;
/* 15 */     this.speedScale = speedScale;
/* 16 */     this.minV = 0.4F * this.scale;
/* 17 */     this.maxV = 3.0F * this.scale;
/* 18 */     this.threshold = 0.95F * distanceScale;
/*    */     
/* 20 */     this.vX = MathUtils.random(-this.maxV * speedScale, this.maxV * speedScale);
/* 21 */     this.vY = MathUtils.random(-this.maxV * speedScale, this.maxV * speedScale);
/*    */   }
/*    */   private float maxV; private float threshold; private static final float SPEED_MIN = 0.4F; private static final float SPEED_MAX = 3.0F; private static final float EDGE_THRESHOLD = 0.95F; private float speedScale;
/*    */   public void update() {
/* 25 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 26 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 28 */     if (this.y > this.threshold) {
/* 29 */       this.vY = -MathUtils.random(this.minV * this.speedScale, this.maxV * this.speedScale);
/* 30 */     } else if (this.y < -this.threshold) {
/* 31 */       this.vY = MathUtils.random(this.minV * this.speedScale, this.maxV * this.speedScale);
/*    */     } 
/*    */     
/* 34 */     if (this.x > this.threshold) {
/* 35 */       this.vX = -MathUtils.random(this.minV * this.speedScale, this.maxV * this.speedScale);
/* 36 */     } else if (this.x < -this.threshold) {
/* 37 */       this.vX = MathUtils.random(this.minV * this.speedScale, this.maxV * this.speedScale);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FloatyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */