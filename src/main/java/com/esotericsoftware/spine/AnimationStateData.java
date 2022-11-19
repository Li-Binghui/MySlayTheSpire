/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.utils.ObjectFloatMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimationStateData
/*     */ {
/*     */   private final SkeletonData skeletonData;
/*  39 */   final ObjectFloatMap<Key> animationToMixTime = new ObjectFloatMap();
/*  40 */   final Key tempKey = new Key();
/*     */   float defaultMix;
/*     */   
/*     */   public AnimationStateData(SkeletonData skeletonData) {
/*  44 */     if (skeletonData == null) throw new IllegalArgumentException("skeletonData cannot be null."); 
/*  45 */     this.skeletonData = skeletonData;
/*     */   }
/*     */   
/*     */   public SkeletonData getSkeletonData() {
/*  49 */     return this.skeletonData;
/*     */   }
/*     */   
/*     */   public void setMix(String fromName, String toName, float duration) {
/*  53 */     Animation from = this.skeletonData.findAnimation(fromName);
/*  54 */     if (from == null) throw new IllegalArgumentException("Animation not found: " + fromName); 
/*  55 */     Animation to = this.skeletonData.findAnimation(toName);
/*  56 */     if (to == null) throw new IllegalArgumentException("Animation not found: " + toName); 
/*  57 */     setMix(from, to, duration);
/*     */   }
/*     */   
/*     */   public void setMix(Animation from, Animation to, float duration) {
/*  61 */     if (from == null) throw new IllegalArgumentException("from cannot be null."); 
/*  62 */     if (to == null) throw new IllegalArgumentException("to cannot be null."); 
/*  63 */     Key key = new Key();
/*  64 */     key.a1 = from;
/*  65 */     key.a2 = to;
/*  66 */     this.animationToMixTime.put(key, duration);
/*     */   }
/*     */   
/*     */   public float getMix(Animation from, Animation to) {
/*  70 */     this.tempKey.a1 = from;
/*  71 */     this.tempKey.a2 = to;
/*  72 */     return this.animationToMixTime.get(this.tempKey, this.defaultMix);
/*     */   }
/*     */   
/*     */   public float getDefaultMix() {
/*  76 */     return this.defaultMix;
/*     */   }
/*     */   
/*     */   public void setDefaultMix(float defaultMix) {
/*  80 */     this.defaultMix = defaultMix;
/*     */   }
/*     */   
/*     */   static class Key { Animation a1;
/*     */     Animation a2;
/*     */     
/*     */     public int hashCode() {
/*  87 */       return 31 * (31 + this.a1.hashCode()) + this.a2.hashCode();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/*  91 */       if (this == obj) return true; 
/*  92 */       if (obj == null) return false; 
/*  93 */       Key other = (Key)obj;
/*  94 */       if (this.a1 == null)
/*  95 */       { if (other.a1 != null) return false;  }
/*  96 */       else if (!this.a1.equals(other.a1)) { return false; }
/*  97 */        if (this.a2 == null)
/*  98 */       { if (other.a2 != null) return false;  }
/*  99 */       else if (!this.a2.equals(other.a2)) { return false; }
/* 100 */        return true;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\AnimationStateData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */