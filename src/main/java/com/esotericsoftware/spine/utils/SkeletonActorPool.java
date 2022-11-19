/*     */ package com.esotericsoftware.spine.utils;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.*;
/*     */ 
/*     */ public class SkeletonActorPool
/*     */   extends Pool<SkeletonActor>
/*     */ {
/*     */   private SkeletonRenderer renderer;
/*     */   SkeletonData skeletonData;
/*     */   AnimationStateData stateData;
/*     */   private final Pool<Skeleton> skeletonPool;
/*     */   private final Pool<AnimationState> statePool;
/*     */   private final Array<SkeletonActor> obtained;
/*     */   
/*     */   public SkeletonActorPool(SkeletonRenderer renderer, SkeletonData skeletonData, AnimationStateData stateData) {
/*  24 */     this(renderer, skeletonData, stateData, 16, 2147483647);
/*     */   }
/*     */ 
/*     */   
/*     */   public SkeletonActorPool(SkeletonRenderer renderer, SkeletonData skeletonData, AnimationStateData stateData, int initialCapacity, int max) {
/*  29 */     super(initialCapacity, max);
/*     */     
/*  31 */     this.renderer = renderer;
/*  32 */     this.skeletonData = skeletonData;
/*  33 */     this.stateData = stateData;
/*     */     
/*  35 */     this.obtained = new Array(false, initialCapacity);
/*     */     
/*  37 */     this.skeletonPool = new Pool<Skeleton>(initialCapacity, max) {
/*     */         protected Skeleton newObject() {
/*  39 */           return new Skeleton(SkeletonActorPool.this.skeletonData);
/*     */         }
/*     */         
/*     */         protected void reset(Skeleton skeleton) {
/*  43 */           skeleton.setColor(Color.WHITE);
/*  44 */           skeleton.setFlip(false, false);
/*  45 */           skeleton.setSkin((Skin)null);
/*  46 */           skeleton.setSkin(SkeletonActorPool.this.skeletonData.getDefaultSkin());
/*  47 */           skeleton.setToSetupPose();
/*     */         }
/*     */       };
/*     */     
/*  51 */     this.statePool = new Pool<AnimationState>(initialCapacity, max) {
/*     */         protected AnimationState newObject() {
/*  53 */           return new AnimationState(SkeletonActorPool.this.stateData);
/*     */         }
/*     */         
/*     */         protected void reset(AnimationState state) {
/*  57 */           state.clearTracks();
/*  58 */           state.clearListeners();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public void freeComplete() {
/*  65 */     Array<SkeletonActor> obtained = this.obtained;
/*     */     
/*  67 */     for (int i = obtained.size - 1; i >= 0; i--) {
/*  68 */       SkeletonActor actor = (SkeletonActor)obtained.get(i);
/*  69 */       Array<AnimationState.TrackEntry> tracks = actor.state.getTracks();
/*  70 */       int ii = 0, nn = tracks.size; while (true) { if (ii < nn)
/*  71 */         { if (tracks.get(ii) != null)
/*  72 */             break;  ii++; continue; }  free(actor);
/*     */         break; }
/*     */     
/*     */     } 
/*     */   } protected SkeletonActor newObject() {
/*  77 */     SkeletonActor actor = new SkeletonActor();
/*  78 */     actor.setRenderer(this.renderer);
/*  79 */     return actor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SkeletonActor obtain() {
/*  85 */     SkeletonActor actor = (SkeletonActor)super.obtain();
/*  86 */     actor.setSkeleton((Skeleton)this.skeletonPool.obtain());
/*  87 */     actor.setAnimationState((AnimationState)this.statePool.obtain());
/*  88 */     this.obtained.add(actor);
/*  89 */     return actor;
/*     */   }
/*     */   
/*     */   protected void reset(SkeletonActor actor) {
/*  93 */     actor.remove();
/*  94 */     this.obtained.removeValue(actor, true);
/*  95 */     this.skeletonPool.free(actor.getSkeleton());
/*  96 */     this.statePool.free(actor.getAnimationState());
/*     */   }
/*     */   
/*     */   public Array<SkeletonActor> getObtained() {
/* 100 */     return this.obtained;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spin\\utils\SkeletonActorPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */