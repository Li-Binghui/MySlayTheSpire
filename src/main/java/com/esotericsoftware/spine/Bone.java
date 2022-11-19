/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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
/*     */ public class Bone
/*     */   implements Updatable
/*     */ {
/*     */   final BoneData data;
/*     */   final Skeleton skeleton;
/*     */   final Bone parent;
/*  45 */   final Array<Bone> children = new Array();
/*     */   
/*     */   float x;
/*     */   float y;
/*     */   float rotation;
/*     */   float scaleX;
/*     */   float scaleY;
/*     */   float shearX;
/*     */   float shearY;
/*     */   float appliedRotation;
/*     */   
/*     */   public Bone(BoneData data, Skeleton skeleton, Bone parent) {
/*  57 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  58 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  59 */     this.data = data;
/*  60 */     this.skeleton = skeleton;
/*  61 */     this.parent = parent;
/*  62 */     setToSetupPose();
/*     */   }
/*     */   float a; float b; float worldX; float c; float d; float worldY; float worldSignX; float worldSignY;
/*     */   boolean sorted;
/*     */   
/*     */   public Bone(Bone bone, Skeleton skeleton, Bone parent) {
/*  68 */     if (bone == null) throw new IllegalArgumentException("bone cannot be null."); 
/*  69 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  70 */     this.skeleton = skeleton;
/*  71 */     this.parent = parent;
/*  72 */     this.data = bone.data;
/*  73 */     this.x = bone.x;
/*  74 */     this.y = bone.y;
/*  75 */     this.rotation = bone.rotation;
/*  76 */     this.scaleX = bone.scaleX;
/*  77 */     this.scaleY = bone.scaleY;
/*  78 */     this.shearX = bone.shearX;
/*  79 */     this.shearY = bone.shearY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  84 */     updateWorldTransform(this.x, this.y, this.rotation, this.scaleX, this.scaleY, this.shearX, this.shearY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWorldTransform() {
/*  89 */     updateWorldTransform(this.x, this.y, this.rotation, this.scaleX, this.scaleY, this.shearX, this.shearY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWorldTransform(float x, float y, float rotation, float scaleX, float scaleY, float shearX, float shearY) {
/*  94 */     this.appliedRotation = rotation;
/*     */     
/*  96 */     float rotationY = rotation + 90.0F + shearY;
/*  97 */     float la = MathUtils.cosDeg(rotation + shearX) * scaleX, lb = MathUtils.cosDeg(rotationY) * scaleY;
/*  98 */     float lc = MathUtils.sinDeg(rotation + shearX) * scaleX, ld = MathUtils.sinDeg(rotationY) * scaleY;
/*     */     
/* 100 */     Bone parent = this.parent;
/* 101 */     if (parent == null) {
/* 102 */       Skeleton skeleton = this.skeleton;
/* 103 */       if (skeleton.flipX) {
/* 104 */         x = -x;
/* 105 */         la = -la;
/* 106 */         lb = -lb;
/*     */       } 
/* 108 */       if (skeleton.flipY) {
/* 109 */         y = -y;
/* 110 */         lc = -lc;
/* 111 */         ld = -ld;
/*     */       } 
/* 113 */       this.a = la;
/* 114 */       this.b = lb;
/* 115 */       this.c = lc;
/* 116 */       this.d = ld;
/* 117 */       this.worldX = x;
/* 118 */       this.worldY = y;
/* 119 */       this.worldSignX = Math.signum(scaleX);
/* 120 */       this.worldSignY = Math.signum(scaleY);
/*     */       
/*     */       return;
/*     */     } 
/* 124 */     float pa = parent.a, pb = parent.b, pc = parent.c, pd = parent.d;
/* 125 */     this.worldX = pa * x + pb * y + parent.worldX;
/* 126 */     this.worldY = pc * x + pd * y + parent.worldY;
/* 127 */     parent.worldSignX *= Math.signum(scaleX);
/* 128 */     parent.worldSignY *= Math.signum(scaleY);
/*     */     
/* 130 */     if (this.data.inheritRotation && this.data.inheritScale) {
/* 131 */       this.a = pa * la + pb * lc;
/* 132 */       this.b = pa * lb + pb * ld;
/* 133 */       this.c = pc * la + pd * lc;
/* 134 */       this.d = pc * lb + pd * ld;
/*     */     } else {
/* 136 */       if (this.data.inheritRotation) {
/* 137 */         pa = 1.0F;
/* 138 */         pb = 0.0F;
/* 139 */         pc = 0.0F;
/* 140 */         pd = 1.0F;
/*     */         do {
/* 142 */           float cos = MathUtils.cosDeg(parent.appliedRotation), sin = MathUtils.sinDeg(parent.appliedRotation);
/* 143 */           float temp = pa * cos + pb * sin;
/* 144 */           pb = pb * cos - pa * sin;
/* 145 */           pa = temp;
/* 146 */           temp = pc * cos + pd * sin;
/* 147 */           pd = pd * cos - pc * sin;
/* 148 */           pc = temp;
/*     */           
/* 150 */           if (!parent.data.inheritRotation)
/* 151 */             break;  parent = parent.parent;
/* 152 */         } while (parent != null);
/* 153 */         this.a = pa * la + pb * lc;
/* 154 */         this.b = pa * lb + pb * ld;
/* 155 */         this.c = pc * la + pd * lc;
/* 156 */         this.d = pc * lb + pd * ld;
/* 157 */       } else if (this.data.inheritScale) {
/* 158 */         pa = 1.0F;
/* 159 */         pb = 0.0F;
/* 160 */         pc = 0.0F;
/* 161 */         pd = 1.0F;
/*     */         do {
/* 163 */           float cos = MathUtils.cosDeg(parent.appliedRotation), sin = MathUtils.sinDeg(parent.appliedRotation);
/* 164 */           float psx = parent.scaleX, psy = parent.scaleY;
/* 165 */           float za = cos * psx, zb = sin * psy, zc = sin * psx, zd = cos * psy;
/* 166 */           float temp = pa * za + pb * zc;
/* 167 */           pb = pb * zd - pa * zb;
/* 168 */           pa = temp;
/* 169 */           temp = pc * za + pd * zc;
/* 170 */           pd = pd * zd - pc * zb;
/* 171 */           pc = temp;
/*     */           
/* 173 */           if (psx >= 0.0F) sin = -sin; 
/* 174 */           temp = pa * cos + pb * sin;
/* 175 */           pb = pb * cos - pa * sin;
/* 176 */           pa = temp;
/* 177 */           temp = pc * cos + pd * sin;
/* 178 */           pd = pd * cos - pc * sin;
/* 179 */           pc = temp;
/*     */           
/* 181 */           if (!parent.data.inheritScale)
/* 182 */             break;  parent = parent.parent;
/* 183 */         } while (parent != null);
/* 184 */         this.a = pa * la + pb * lc;
/* 185 */         this.b = pa * lb + pb * ld;
/* 186 */         this.c = pc * la + pd * lc;
/* 187 */         this.d = pc * lb + pd * ld;
/*     */       } else {
/* 189 */         this.a = la;
/* 190 */         this.b = lb;
/* 191 */         this.c = lc;
/* 192 */         this.d = ld;
/*     */       } 
/* 194 */       if (this.skeleton.flipX) {
/* 195 */         this.a = -this.a;
/* 196 */         this.b = -this.b;
/*     */       } 
/* 198 */       if (this.skeleton.flipY) {
/* 199 */         this.c = -this.c;
/* 200 */         this.d = -this.d;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setToSetupPose() {
/* 206 */     BoneData data = this.data;
/* 207 */     this.x = data.x;
/* 208 */     this.y = data.y;
/* 209 */     this.rotation = data.rotation;
/* 210 */     this.scaleX = data.scaleX;
/* 211 */     this.scaleY = data.scaleY;
/* 212 */     this.shearX = data.shearX;
/* 213 */     this.shearY = data.shearY;
/*     */   }
/*     */   
/*     */   public BoneData getData() {
/* 217 */     return this.data;
/*     */   }
/*     */   
/*     */   public Skeleton getSkeleton() {
/* 221 */     return this.skeleton;
/*     */   }
/*     */   
/*     */   public Bone getParent() {
/* 225 */     return this.parent;
/*     */   }
/*     */   
/*     */   public Array<Bone> getChildren() {
/* 229 */     return this.children;
/*     */   }
/*     */   
/*     */   public float getX() {
/* 233 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/* 237 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 241 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/* 245 */     this.y = y;
/*     */   }
/*     */   
/*     */   public void setPosition(float x, float y) {
/* 249 */     this.x = x;
/* 250 */     this.y = y;
/*     */   }
/*     */   
/*     */   public float getRotation() {
/* 254 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void setRotation(float rotation) {
/* 258 */     this.rotation = rotation;
/*     */   }
/*     */   
/*     */   public float getScaleX() {
/* 262 */     return this.scaleX;
/*     */   }
/*     */   
/*     */   public void setScaleX(float scaleX) {
/* 266 */     this.scaleX = scaleX;
/*     */   }
/*     */   
/*     */   public float getScaleY() {
/* 270 */     return this.scaleY;
/*     */   }
/*     */   
/*     */   public void setScaleY(float scaleY) {
/* 274 */     this.scaleY = scaleY;
/*     */   }
/*     */   
/*     */   public void setScale(float scaleX, float scaleY) {
/* 278 */     this.scaleX = scaleX;
/* 279 */     this.scaleY = scaleY;
/*     */   }
/*     */   
/*     */   public void setScale(float scale) {
/* 283 */     this.scaleX = scale;
/* 284 */     this.scaleY = scale;
/*     */   }
/*     */   
/*     */   public float getShearX() {
/* 288 */     return this.shearX;
/*     */   }
/*     */   
/*     */   public void setShearX(float shearX) {
/* 292 */     this.shearX = shearX;
/*     */   }
/*     */   
/*     */   public float getShearY() {
/* 296 */     return this.shearY;
/*     */   }
/*     */   
/*     */   public void setShearY(float shearY) {
/* 300 */     this.shearY = shearY;
/*     */   }
/*     */   
/*     */   public float getA() {
/* 304 */     return this.a;
/*     */   }
/*     */   
/*     */   public float getB() {
/* 308 */     return this.b;
/*     */   }
/*     */   
/*     */   public float getC() {
/* 312 */     return this.c;
/*     */   }
/*     */   
/*     */   public float getD() {
/* 316 */     return this.d;
/*     */   }
/*     */   
/*     */   public float getWorldX() {
/* 320 */     return this.worldX;
/*     */   }
/*     */   
/*     */   public float getWorldY() {
/* 324 */     return this.worldY;
/*     */   }
/*     */   
/*     */   public float getWorldSignX() {
/* 328 */     return this.worldSignX;
/*     */   }
/*     */   
/*     */   public float getWorldSignY() {
/* 332 */     return this.worldSignY;
/*     */   }
/*     */   
/*     */   public float getWorldRotationX() {
/* 336 */     return MathUtils.atan2(this.c, this.a) * 57.295776F;
/*     */   }
/*     */   
/*     */   public float getWorldRotationY() {
/* 340 */     return MathUtils.atan2(this.d, this.b) * 57.295776F;
/*     */   }
/*     */   
/*     */   public float getWorldScaleX() {
/* 344 */     return (float)Math.sqrt((this.a * this.a + this.b * this.b)) * this.worldSignX;
/*     */   }
/*     */   
/*     */   public float getWorldScaleY() {
/* 348 */     return (float)Math.sqrt((this.c * this.c + this.d * this.d)) * this.worldSignY;
/*     */   }
/*     */   
/*     */   public float worldToLocalRotationX() {
/* 352 */     Bone parent = this.parent;
/* 353 */     if (parent == null) return this.rotation; 
/* 354 */     float pa = parent.a, pb = parent.b, pc = parent.c, pd = parent.d, a = this.a, c = this.c;
/* 355 */     return MathUtils.atan2(pa * c - pc * a, pd * a - pb * c) * 57.295776F;
/*     */   }
/*     */   
/*     */   public float worldToLocalRotationY() {
/* 359 */     Bone parent = this.parent;
/* 360 */     if (parent == null) return this.rotation; 
/* 361 */     float pa = parent.a, pb = parent.b, pc = parent.c, pd = parent.d, b = this.b, d = this.d;
/* 362 */     return MathUtils.atan2(pa * d - pc * b, pd * b - pb * d) * 57.295776F;
/*     */   }
/*     */   
/*     */   public void rotateWorld(float degrees) {
/* 366 */     float a = this.a, b = this.b, c = this.c, d = this.d;
/* 367 */     float cos = MathUtils.cosDeg(degrees), sin = MathUtils.sinDeg(degrees);
/* 368 */     this.a = cos * a - sin * c;
/* 369 */     this.b = cos * b - sin * d;
/* 370 */     this.c = sin * a + cos * c;
/* 371 */     this.d = sin * b + cos * d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateLocalTransform() {
/* 380 */     Bone parent = this.parent;
/* 381 */     if (parent == null) {
/* 382 */       this.x = this.worldX;
/* 383 */       this.y = this.worldY;
/* 384 */       this.rotation = MathUtils.atan2(this.c, this.a) * 57.295776F;
/* 385 */       this.scaleX = (float)Math.sqrt((this.a * this.a + this.c * this.c));
/* 386 */       this.scaleY = (float)Math.sqrt((this.b * this.b + this.d * this.d));
/* 387 */       float det = this.a * this.d - this.b * this.c;
/* 388 */       this.shearX = 0.0F;
/* 389 */       this.shearY = MathUtils.atan2(this.a * this.b + this.c * this.d, det) * 57.295776F;
/*     */       return;
/*     */     } 
/* 392 */     float pa = parent.a, pb = parent.b, pc = parent.c, pd = parent.d;
/* 393 */     float pid = 1.0F / (pa * pd - pb * pc);
/* 394 */     float dx = this.worldX - parent.worldX, dy = this.worldY - parent.worldY;
/* 395 */     this.x = dx * pd * pid - dy * pb * pid;
/* 396 */     this.y = dy * pa * pid - dx * pc * pid;
/* 397 */     float ia = pid * pd;
/* 398 */     float id = pid * pa;
/* 399 */     float ib = pid * pb;
/* 400 */     float ic = pid * pc;
/* 401 */     float ra = ia * this.a - ib * this.c;
/* 402 */     float rb = ia * this.b - ib * this.d;
/* 403 */     float rc = id * this.c - ic * this.a;
/* 404 */     float rd = id * this.d - ic * this.b;
/* 405 */     this.shearX = 0.0F;
/* 406 */     this.scaleX = (float)Math.sqrt((ra * ra + rc * rc));
/* 407 */     if (this.scaleX > 1.0E-4F) {
/* 408 */       float det = ra * rd - rb * rc;
/* 409 */       this.scaleY = det / this.scaleX;
/* 410 */       this.shearY = MathUtils.atan2(ra * rb + rc * rd, det) * 57.295776F;
/* 411 */       this.rotation = MathUtils.atan2(rc, ra) * 57.295776F;
/*     */     } else {
/* 413 */       this.scaleX = 0.0F;
/* 414 */       this.scaleY = (float)Math.sqrt((rb * rb + rd * rd));
/* 415 */       this.shearY = 0.0F;
/* 416 */       this.rotation = 90.0F - MathUtils.atan2(rd, rb) * 57.295776F;
/*     */     } 
/* 418 */     this.appliedRotation = this.rotation;
/*     */   }
/*     */   
/*     */   public Matrix3 getWorldTransform(Matrix3 worldTransform) {
/* 422 */     if (worldTransform == null) throw new IllegalArgumentException("worldTransform cannot be null."); 
/* 423 */     float[] val = worldTransform.val;
/* 424 */     val[0] = this.a;
/* 425 */     val[3] = this.b;
/* 426 */     val[1] = this.c;
/* 427 */     val[4] = this.d;
/* 428 */     val[6] = this.worldX;
/* 429 */     val[7] = this.worldY;
/* 430 */     val[2] = 0.0F;
/* 431 */     val[5] = 0.0F;
/* 432 */     val[8] = 1.0F;
/* 433 */     return worldTransform;
/*     */   }
/*     */   
/*     */   public Vector2 worldToLocal(Vector2 world) {
/* 437 */     float a = this.a, b = this.b, c = this.c, d = this.d;
/* 438 */     float invDet = 1.0F / (a * d - b * c);
/* 439 */     float x = world.x - this.worldX, y = world.y - this.worldY;
/* 440 */     world.x = x * d * invDet - y * b * invDet;
/* 441 */     world.y = y * a * invDet - x * c * invDet;
/* 442 */     return world;
/*     */   }
/*     */   
/*     */   public Vector2 localToWorld(Vector2 local) {
/* 446 */     float x = local.x, y = local.y;
/* 447 */     local.x = x * this.a + y * this.b + this.worldX;
/* 448 */     local.y = x * this.c + y * this.d + this.worldY;
/* 449 */     return local;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 453 */     return this.data.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\Bone.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */