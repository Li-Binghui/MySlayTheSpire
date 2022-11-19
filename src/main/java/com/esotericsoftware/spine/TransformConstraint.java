/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.badlogic.gdx.utils.Array;
/*     */ 
/*     */ 
/*     */ public class TransformConstraint
/*     */   implements Updatable
/*     */ {
/*     */   final TransformConstraintData data;
/*     */   final Array<Bone> bones;
/*     */   Bone target;
/*  14 */   final Vector2 temp = new Vector2(); float rotateMix; float translateMix; float scaleMix; float shearMix;
/*     */   
/*     */   public TransformConstraint(TransformConstraintData data, Skeleton skeleton) {
/*  17 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  18 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  19 */     this.data = data;
/*  20 */     this.rotateMix = data.rotateMix;
/*  21 */     this.translateMix = data.translateMix;
/*  22 */     this.scaleMix = data.scaleMix;
/*  23 */     this.shearMix = data.shearMix;
/*  24 */     this.bones = new Array(data.bones.size);
/*  25 */     for (BoneData boneData : data.bones)
/*  26 */       this.bones.add(skeleton.findBone(boneData.name)); 
/*  27 */     this.target = skeleton.findBone(data.target.name);
/*     */   }
/*     */ 
/*     */   
/*     */   public TransformConstraint(TransformConstraint constraint, Skeleton skeleton) {
/*  32 */     if (constraint == null) throw new IllegalArgumentException("constraint cannot be null."); 
/*  33 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  34 */     this.data = constraint.data;
/*  35 */     this.bones = new Array(constraint.bones.size);
/*  36 */     for (Bone bone : constraint.bones)
/*  37 */       this.bones.add(skeleton.bones.get(bone.data.index)); 
/*  38 */     this.target = (Bone)skeleton.bones.get(constraint.target.data.index);
/*  39 */     this.rotateMix = constraint.rotateMix;
/*  40 */     this.translateMix = constraint.translateMix;
/*  41 */     this.scaleMix = constraint.scaleMix;
/*  42 */     this.shearMix = constraint.shearMix;
/*     */   }
/*     */   
/*     */   public void apply() {
/*  46 */     update();
/*     */   }
/*     */   
/*     */   public void update() {
/*  50 */     float rotateMix = this.rotateMix, translateMix = this.translateMix, scaleMix = this.scaleMix, shearMix = this.shearMix;
/*  51 */     Bone target = this.target;
/*  52 */     float ta = target.a, tb = target.b, tc = target.c, td = target.d;
/*  53 */     Array<Bone> bones = this.bones;
/*  54 */     for (int i = 0, n = bones.size; i < n; i++) {
/*  55 */       Bone bone = (Bone)bones.get(i);
/*     */       
/*  57 */       if (rotateMix > 0.0F) {
/*  58 */         float a = bone.a, b = bone.b, c = bone.c, d = bone.d;
/*  59 */         float r = MathUtils.atan2(tc, ta) - MathUtils.atan2(c, a) + this.data.offsetRotation * 0.017453292F;
/*  60 */         if (r > 3.1415927F)
/*  61 */         { r -= 6.2831855F; }
/*  62 */         else if (r < -3.1415927F) { r += 6.2831855F; }
/*  63 */          r *= rotateMix;
/*  64 */         float cos = MathUtils.cos(r), sin = MathUtils.sin(r);
/*  65 */         bone.a = cos * a - sin * c;
/*  66 */         bone.b = cos * b - sin * d;
/*  67 */         bone.c = sin * a + cos * c;
/*  68 */         bone.d = sin * b + cos * d;
/*     */       } 
/*     */       
/*  71 */       if (translateMix > 0.0F) {
/*  72 */         Vector2 temp = this.temp;
/*  73 */         target.localToWorld(temp.set(this.data.offsetX, this.data.offsetY));
/*  74 */         bone.worldX += (temp.x - bone.worldX) * translateMix;
/*  75 */         bone.worldY += (temp.y - bone.worldY) * translateMix;
/*     */       } 
/*     */       
/*  78 */       if (scaleMix > 0.0F) {
/*  79 */         float bs = (float)Math.sqrt((bone.a * bone.a + bone.c * bone.c));
/*  80 */         float ts = (float)Math.sqrt((ta * ta + tc * tc));
/*  81 */         float s = (bs > 1.0E-5F) ? ((bs + (ts - bs + this.data.offsetScaleX) * scaleMix) / bs) : 0.0F;
/*  82 */         bone.a *= s;
/*  83 */         bone.c *= s;
/*  84 */         bs = (float)Math.sqrt((bone.b * bone.b + bone.d * bone.d));
/*  85 */         ts = (float)Math.sqrt((tb * tb + td * td));
/*  86 */         s = (bs > 1.0E-5F) ? ((bs + (ts - bs + this.data.offsetScaleY) * scaleMix) / bs) : 0.0F;
/*  87 */         bone.b *= s;
/*  88 */         bone.d *= s;
/*     */       } 
/*     */       
/*  91 */       if (shearMix > 0.0F) {
/*  92 */         float b = bone.b, d = bone.d;
/*  93 */         float by = MathUtils.atan2(d, b);
/*  94 */         float r = MathUtils.atan2(td, tb) - MathUtils.atan2(tc, ta) - by - MathUtils.atan2(bone.c, bone.a);
/*  95 */         if (r > 3.1415927F)
/*  96 */         { r -= 6.2831855F; }
/*  97 */         else if (r < -3.1415927F) { r += 6.2831855F; }
/*  98 */          r = by + (r + this.data.offsetShearY * 0.017453292F) * shearMix;
/*  99 */         float s = (float)Math.sqrt((b * b + d * d));
/* 100 */         bone.b = MathUtils.cos(r) * s;
/* 101 */         bone.d = MathUtils.sin(r) * s;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Array<Bone> getBones() {
/* 107 */     return this.bones;
/*     */   }
/*     */   
/*     */   public Bone getTarget() {
/* 111 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(Bone target) {
/* 115 */     this.target = target;
/*     */   }
/*     */   
/*     */   public float getRotateMix() {
/* 119 */     return this.rotateMix;
/*     */   }
/*     */   
/*     */   public void setRotateMix(float rotateMix) {
/* 123 */     this.rotateMix = rotateMix;
/*     */   }
/*     */   
/*     */   public float getTranslateMix() {
/* 127 */     return this.translateMix;
/*     */   }
/*     */   
/*     */   public void setTranslateMix(float translateMix) {
/* 131 */     this.translateMix = translateMix;
/*     */   }
/*     */   
/*     */   public float getScaleMix() {
/* 135 */     return this.scaleMix;
/*     */   }
/*     */   
/*     */   public void setScaleMix(float scaleMix) {
/* 139 */     this.scaleMix = scaleMix;
/*     */   }
/*     */   
/*     */   public float getShearMix() {
/* 143 */     return this.shearMix;
/*     */   }
/*     */   
/*     */   public void setShearMix(float shearMix) {
/* 147 */     this.shearMix = shearMix;
/*     */   }
/*     */   
/*     */   public TransformConstraintData getData() {
/* 151 */     return this.data;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     return this.data.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\TransformConstraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */