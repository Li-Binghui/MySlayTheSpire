/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
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
/*     */ public class BoneData
/*     */ {
/*     */   final int index;
/*     */   final String name;
/*     */   final BoneData parent;
/*     */   float length;
/*     */   float x;
/*     */   float y;
/*     */   float rotation;
/*  41 */   float scaleX = 1.0F; float scaleY = 1.0F; float shearX;
/*     */   float shearY;
/*     */   boolean inheritRotation = true;
/*     */   boolean inheritScale = true;
/*  45 */   final Color color = new Color(0.61F, 0.61F, 0.61F, 1.0F);
/*     */ 
/*     */   
/*     */   public BoneData(int index, String name, BoneData parent) {
/*  49 */     if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  50 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/*  51 */     this.index = index;
/*  52 */     this.name = name;
/*  53 */     this.parent = parent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoneData(BoneData bone, BoneData parent) {
/*  59 */     if (bone == null) throw new IllegalArgumentException("bone cannot be null."); 
/*  60 */     this.index = bone.index;
/*  61 */     this.name = bone.name;
/*  62 */     this.parent = parent;
/*  63 */     this.length = bone.length;
/*  64 */     this.x = bone.x;
/*  65 */     this.y = bone.y;
/*  66 */     this.rotation = bone.rotation;
/*  67 */     this.scaleX = bone.scaleX;
/*  68 */     this.scaleY = bone.scaleY;
/*  69 */     this.shearX = bone.shearX;
/*  70 */     this.shearY = bone.shearY;
/*     */   }
/*     */   
/*     */   public int getIndex() {
/*  74 */     return this.index;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  78 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public BoneData getParent() {
/*  83 */     return this.parent;
/*     */   }
/*     */   
/*     */   public float getLength() {
/*  87 */     return this.length;
/*     */   }
/*     */   
/*     */   public void setLength(float length) {
/*  91 */     this.length = length;
/*     */   }
/*     */   
/*     */   public float getX() {
/*  95 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/*  99 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 103 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/* 107 */     this.y = y;
/*     */   }
/*     */   
/*     */   public void setPosition(float x, float y) {
/* 111 */     this.x = x;
/* 112 */     this.y = y;
/*     */   }
/*     */   
/*     */   public float getRotation() {
/* 116 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void setRotation(float rotation) {
/* 120 */     this.rotation = rotation;
/*     */   }
/*     */   
/*     */   public float getScaleX() {
/* 124 */     return this.scaleX;
/*     */   }
/*     */   
/*     */   public void setScaleX(float scaleX) {
/* 128 */     this.scaleX = scaleX;
/*     */   }
/*     */   
/*     */   public float getScaleY() {
/* 132 */     return this.scaleY;
/*     */   }
/*     */   
/*     */   public void setScaleY(float scaleY) {
/* 136 */     this.scaleY = scaleY;
/*     */   }
/*     */   
/*     */   public void setScale(float scaleX, float scaleY) {
/* 140 */     this.scaleX = scaleX;
/* 141 */     this.scaleY = scaleY;
/*     */   }
/*     */   
/*     */   public float getShearX() {
/* 145 */     return this.shearX;
/*     */   }
/*     */   
/*     */   public void setShearX(float shearX) {
/* 149 */     this.shearX = shearX;
/*     */   }
/*     */   
/*     */   public float getShearY() {
/* 153 */     return this.shearY;
/*     */   }
/*     */   
/*     */   public void setShearY(float shearY) {
/* 157 */     this.shearY = shearY;
/*     */   }
/*     */   
/*     */   public boolean getInheritRotation() {
/* 161 */     return this.inheritRotation;
/*     */   }
/*     */   
/*     */   public void setInheritRotation(boolean inheritRotation) {
/* 165 */     this.inheritRotation = inheritRotation;
/*     */   }
/*     */   
/*     */   public boolean getInheritScale() {
/* 169 */     return this.inheritScale;
/*     */   }
/*     */   
/*     */   public void setInheritScale(boolean inheritScale) {
/* 173 */     this.inheritScale = inheritScale;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 177 */     return this.color;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 181 */     return this.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\BoneData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */