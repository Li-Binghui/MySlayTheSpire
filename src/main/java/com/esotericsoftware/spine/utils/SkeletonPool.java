/*    */ package com.esotericsoftware.spine.utils;
/*    */ 
/*    */

import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
/*    */ 
/*    */ public class SkeletonPool
/*    */   extends Pool<Skeleton> {
/*    */   private SkeletonData skeletonData;
/*    */   
/*    */   public SkeletonPool(SkeletonData skeletonData) {
/* 12 */     this.skeletonData = skeletonData;
/*    */   }
/*    */   
/*    */   public SkeletonPool(SkeletonData skeletonData, int initialCapacity) {
/* 16 */     super(initialCapacity);
/* 17 */     this.skeletonData = skeletonData;
/*    */   }
/*    */   
/*    */   public SkeletonPool(SkeletonData skeletonData, int initialCapacity, int max) {
/* 21 */     super(initialCapacity, max);
/* 22 */     this.skeletonData = skeletonData;
/*    */   }
/*    */   
/*    */   protected Skeleton newObject() {
/* 26 */     return new Skeleton(this.skeletonData);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spin\\utils\SkeletonPool.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */