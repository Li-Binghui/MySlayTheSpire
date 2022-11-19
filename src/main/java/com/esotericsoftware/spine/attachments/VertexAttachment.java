/*     */ package com.esotericsoftware.spine.attachments;
/*     */ 
/*     */

import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.Slot;
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
/*     */ public class VertexAttachment
/*     */   extends Attachment
/*     */ {
/*     */   int[] bones;
/*     */   float[] vertices;
/*     */   int worldVerticesLength;
/*     */   
/*     */   public VertexAttachment(String name) {
/*  46 */     super(name);
/*     */   }
/*     */   
/*     */   protected void computeWorldVertices(Slot slot, float[] worldVertices) {
/*  50 */     computeWorldVertices(slot, 0, this.worldVerticesLength, worldVertices, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void computeWorldVertices(Slot slot, int start, int count, float[] worldVertices, int offset) {
/*  59 */     count += offset;
/*  60 */     Skeleton skeleton = slot.getSkeleton();
/*  61 */     float x = skeleton.getX(), y = skeleton.getY();
/*  62 */     FloatArray deformArray = slot.getAttachmentVertices();
/*  63 */     float[] vertices = this.vertices;
/*  64 */     int[] bones = this.bones;
/*  65 */     if (bones == null) {
/*  66 */       if (deformArray.size > 0) vertices = deformArray.items; 
/*  67 */       Bone bone = slot.getBone();
/*  68 */       x += bone.getWorldX();
/*  69 */       y += bone.getWorldY();
/*  70 */       float a = bone.getA(), b = bone.getB(), c = bone.getC(), d = bone.getD();
/*  71 */       for (int j = start, w = offset; w < count; j += 2, w += 2) {
/*  72 */         float vx = vertices[j], vy = vertices[j + 1];
/*  73 */         worldVertices[w] = vx * a + vy * b + x;
/*  74 */         worldVertices[w + 1] = vx * c + vy * d + y;
/*     */       } 
/*     */       return;
/*     */     } 
/*  78 */     int v = 0, skip = 0;
/*  79 */     for (int i = 0; i < start; i += 2) {
/*  80 */       int n = bones[v];
/*  81 */       v += n + 1;
/*  82 */       skip += n;
/*     */     } 
/*  84 */     Object[] skeletonBones = (skeleton.getBones()).items;
/*  85 */     if (deformArray.size == 0) {
/*  86 */       for (int w = offset, b = skip * 3; w < count; w += 2) {
/*  87 */         float wx = x, wy = y;
/*  88 */         int n = bones[v++];
/*  89 */         n += v;
/*  90 */         for (; v < n; v++, b += 3) {
/*  91 */           Bone bone = (Bone)skeletonBones[bones[v]];
/*  92 */           float vx = vertices[b], vy = vertices[b + 1], weight = vertices[b + 2];
/*  93 */           wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
/*  94 */           wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
/*     */         } 
/*  96 */         worldVertices[w] = wx;
/*  97 */         worldVertices[w + 1] = wy;
/*     */       } 
/*     */     } else {
/* 100 */       float[] deform = deformArray.items;
/* 101 */       for (int w = offset, b = skip * 3, f = skip << 1; w < count; w += 2) {
/* 102 */         float wx = x, wy = y;
/* 103 */         int n = bones[v++];
/* 104 */         n += v;
/* 105 */         for (; v < n; v++, b += 3, f += 2) {
/* 106 */           Bone bone = (Bone)skeletonBones[bones[v]];
/* 107 */           float vx = vertices[b] + deform[f], vy = vertices[b + 1] + deform[f + 1], weight = vertices[b + 2];
/* 108 */           wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
/* 109 */           wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
/*     */         } 
/* 111 */         worldVertices[w] = wx;
/* 112 */         worldVertices[w + 1] = wy;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean applyDeform(VertexAttachment sourceAttachment) {
/* 119 */     return (this == sourceAttachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getBones() {
/* 124 */     return this.bones;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBones(int[] bones) {
/* 130 */     this.bones = bones;
/*     */   }
/*     */   
/*     */   public float[] getVertices() {
/* 134 */     return this.vertices;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVertices(float[] vertices) {
/* 140 */     this.vertices = vertices;
/*     */   }
/*     */   
/*     */   public int getWorldVerticesLength() {
/* 144 */     return this.worldVerticesLength;
/*     */   }
/*     */   
/*     */   public void setWorldVerticesLength(int worldVerticesLength) {
/* 148 */     this.worldVerticesLength = worldVerticesLength;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\VertexAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */