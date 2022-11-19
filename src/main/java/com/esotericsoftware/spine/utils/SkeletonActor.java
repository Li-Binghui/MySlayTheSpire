/*    */ package com.esotericsoftware.spine.utils;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SkeletonActor
/*    */   extends Actor
/*    */ {
/*    */   private SkeletonRenderer renderer;
/*    */   private Skeleton skeleton;
/*    */   AnimationState state;
/*    */   
/*    */   public SkeletonActor() {}
/*    */   
/*    */   public SkeletonActor(SkeletonRenderer renderer, Skeleton skeleton, AnimationState state) {
/* 22 */     this.renderer = renderer;
/* 23 */     this.skeleton = skeleton;
/* 24 */     this.state = state;
/*    */   }
/*    */   
/*    */   public void act(float delta) {
/* 28 */     this.state.update(delta);
/* 29 */     this.state.apply(this.skeleton);
/* 30 */     this.skeleton.updateWorldTransform();
/* 31 */     super.act(delta);
/*    */   }
/*    */   
/*    */   public void draw(Batch batch, float parentAlpha) {
/* 35 */     Color color = this.skeleton.getColor();
/* 36 */     float oldAlpha = color.a;
/* 37 */     (this.skeleton.getColor()).a *= parentAlpha;
/*    */     
/* 39 */     this.skeleton.setPosition(getX(), getY());
/* 40 */     this.renderer.draw(batch, this.skeleton);
/*    */     
/* 42 */     color.a = oldAlpha;
/*    */   }
/*    */   
/*    */   public SkeletonRenderer getRenderer() {
/* 46 */     return this.renderer;
/*    */   }
/*    */   
/*    */   public void setRenderer(SkeletonRenderer renderer) {
/* 50 */     this.renderer = renderer;
/*    */   }
/*    */   
/*    */   public Skeleton getSkeleton() {
/* 54 */     return this.skeleton;
/*    */   }
/*    */   
/*    */   public void setSkeleton(Skeleton skeleton) {
/* 58 */     this.skeleton = skeleton;
/*    */   }
/*    */   
/*    */   public AnimationState getAnimationState() {
/* 62 */     return this.state;
/*    */   }
/*    */   
/*    */   public void setAnimationState(AnimationState state) {
/* 66 */     this.state = state;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spin\\utils\SkeletonActor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */