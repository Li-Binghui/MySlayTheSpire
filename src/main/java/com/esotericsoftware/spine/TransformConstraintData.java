/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.utils.Array;
/*     */ 
/*     */ public class TransformConstraintData
/*     */ {
/*     */   final String name;
/*   8 */   final Array<BoneData> bones = new Array(); BoneData target; float rotateMix;
/*     */   float translateMix;
/*     */   float scaleMix;
/*     */   float shearMix;
/*     */   
/*     */   public TransformConstraintData(String name) {
/*  14 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/*  15 */     this.name = name;
/*     */   }
/*     */   float offsetRotation; float offsetX; float offsetY; float offsetScaleX; float offsetScaleY; float offsetShearY;
/*     */   public String getName() {
/*  19 */     return this.name;
/*     */   }
/*     */   
/*     */   public Array<BoneData> getBones() {
/*  23 */     return this.bones;
/*     */   }
/*     */   
/*     */   public BoneData getTarget() {
/*  27 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(BoneData target) {
/*  31 */     if (target == null) throw new IllegalArgumentException("target cannot be null."); 
/*  32 */     this.target = target;
/*     */   }
/*     */   
/*     */   public float getRotateMix() {
/*  36 */     return this.rotateMix;
/*     */   }
/*     */   
/*     */   public void setRotateMix(float rotateMix) {
/*  40 */     this.rotateMix = rotateMix;
/*     */   }
/*     */   
/*     */   public float getTranslateMix() {
/*  44 */     return this.translateMix;
/*     */   }
/*     */   
/*     */   public void setTranslateMix(float translateMix) {
/*  48 */     this.translateMix = translateMix;
/*     */   }
/*     */   
/*     */   public float getScaleMix() {
/*  52 */     return this.scaleMix;
/*     */   }
/*     */   
/*     */   public void setScaleMix(float scaleMix) {
/*  56 */     this.scaleMix = scaleMix;
/*     */   }
/*     */   
/*     */   public float getShearMix() {
/*  60 */     return this.shearMix;
/*     */   }
/*     */   
/*     */   public void setShearMix(float shearMix) {
/*  64 */     this.shearMix = shearMix;
/*     */   }
/*     */   
/*     */   public float getOffsetRotation() {
/*  68 */     return this.offsetRotation;
/*     */   }
/*     */   
/*     */   public void setOffsetRotation(float offsetRotation) {
/*  72 */     this.offsetRotation = offsetRotation;
/*     */   }
/*     */   
/*     */   public float getOffsetX() {
/*  76 */     return this.offsetX;
/*     */   }
/*     */   
/*     */   public void setOffsetX(float offsetX) {
/*  80 */     this.offsetX = offsetX;
/*     */   }
/*     */   
/*     */   public float getOffsetY() {
/*  84 */     return this.offsetY;
/*     */   }
/*     */   
/*     */   public void setOffsetY(float offsetY) {
/*  88 */     this.offsetY = offsetY;
/*     */   }
/*     */   
/*     */   public float getOffsetScaleX() {
/*  92 */     return this.offsetScaleX;
/*     */   }
/*     */   
/*     */   public void setOffsetScaleX(float offsetScaleX) {
/*  96 */     this.offsetScaleX = offsetScaleX;
/*     */   }
/*     */   
/*     */   public float getOffsetScaleY() {
/* 100 */     return this.offsetScaleY;
/*     */   }
/*     */   
/*     */   public void setOffsetScaleY(float offsetScaleY) {
/* 104 */     this.offsetScaleY = offsetScaleY;
/*     */   }
/*     */   
/*     */   public float getOffsetShearY() {
/* 108 */     return this.offsetShearY;
/*     */   }
/*     */   
/*     */   public void setOffsetShearY(float offsetShearY) {
/* 112 */     this.offsetShearY = offsetShearY;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     return this.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\TransformConstraintData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */