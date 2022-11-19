/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.utils.Array;
/*     */ 
/*     */ public class PathConstraintData
/*     */ {
/*     */   final String name;
/*   8 */   final Array<BoneData> bones = new Array(); SlotData target; PositionMode positionMode; SpacingMode spacingMode;
/*     */   RotateMode rotateMode;
/*     */   float offsetRotation;
/*     */   float position;
/*     */   float spacing;
/*     */   float rotateMix;
/*     */   float translateMix;
/*     */   
/*     */   public PathConstraintData(String name) {
/*  17 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/*  18 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Array<BoneData> getBones() {
/*  22 */     return this.bones;
/*     */   }
/*     */   
/*     */   public SlotData getTarget() {
/*  26 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(SlotData target) {
/*  30 */     this.target = target;
/*     */   }
/*     */   
/*     */   public PositionMode getPositionMode() {
/*  34 */     return this.positionMode;
/*     */   }
/*     */   
/*     */   public void setPositionMode(PositionMode positionMode) {
/*  38 */     this.positionMode = positionMode;
/*     */   }
/*     */   
/*     */   public SpacingMode getSpacingMode() {
/*  42 */     return this.spacingMode;
/*     */   }
/*     */   
/*     */   public void setSpacingMode(SpacingMode spacingMode) {
/*  46 */     this.spacingMode = spacingMode;
/*     */   }
/*     */   
/*     */   public RotateMode getRotateMode() {
/*  50 */     return this.rotateMode;
/*     */   }
/*     */   
/*     */   public void setRotateMode(RotateMode rotateMode) {
/*  54 */     this.rotateMode = rotateMode;
/*     */   }
/*     */   
/*     */   public float getOffsetRotation() {
/*  58 */     return this.offsetRotation;
/*     */   }
/*     */   
/*     */   public void setOffsetRotation(float offsetRotation) {
/*  62 */     this.offsetRotation = offsetRotation;
/*     */   }
/*     */   
/*     */   public float getPosition() {
/*  66 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(float position) {
/*  70 */     this.position = position;
/*     */   }
/*     */   
/*     */   public float getSpacing() {
/*  74 */     return this.spacing;
/*     */   }
/*     */   
/*     */   public void setSpacing(float spacing) {
/*  78 */     this.spacing = spacing;
/*     */   }
/*     */   
/*     */   public float getRotateMix() {
/*  82 */     return this.rotateMix;
/*     */   }
/*     */   
/*     */   public void setRotateMix(float rotateMix) {
/*  86 */     this.rotateMix = rotateMix;
/*     */   }
/*     */   
/*     */   public float getTranslateMix() {
/*  90 */     return this.translateMix;
/*     */   }
/*     */   
/*     */   public void setTranslateMix(float translateMix) {
/*  94 */     this.translateMix = translateMix;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  98 */     return this.name;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 102 */     return this.name;
/*     */   }
/*     */   
/*     */   public enum PositionMode {
/* 106 */     fixed, percent; static {
/*     */     
/* 108 */     } public static final PositionMode[] values = values();
/*     */   }
/*     */   
/*     */   public enum SpacingMode {
/* 112 */     length, fixed, percent;
/*     */     
/* 114 */     public static final SpacingMode[] values = values();
/*     */     static {
/*     */     
/*     */     } }
/* 118 */   public enum RotateMode { tangent, chain, chainScale;
/*     */     
/* 120 */     public static final RotateMode[] values = values();
/*     */     
/*     */     static {
/*     */     
/*     */     } }
/*     */ 
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\PathConstraintData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */