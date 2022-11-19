/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
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
/*     */ public class SkeletonBounds
/*     */ {
/*     */   private float minX;
/*     */   private float minY;
/*     */   private float maxX;
/*     */   private float maxY;
/*  43 */   private Array<BoundingBoxAttachment> boundingBoxes = new Array();
/*  44 */   private Array<FloatArray> polygons = new Array();
/*  45 */   private Pool<FloatArray> polygonPool = new Pool() {
/*     */       protected Object newObject() {
/*  47 */         return new FloatArray();
/*     */       }
/*     */     };
/*     */   
/*     */   public void update(Skeleton skeleton, boolean updateAabb) {
/*  52 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  53 */     Array<BoundingBoxAttachment> boundingBoxes = this.boundingBoxes;
/*  54 */     Array<FloatArray> polygons = this.polygons;
/*  55 */     Array<Slot> slots = skeleton.slots;
/*  56 */     int slotCount = slots.size;
/*     */     
/*  58 */     boundingBoxes.clear();
/*  59 */     this.polygonPool.freeAll(polygons);
/*  60 */     polygons.clear();
/*     */     
/*  62 */     for (int i = 0; i < slotCount; i++) {
/*  63 */       Slot slot = (Slot)slots.get(i);
/*  64 */       Attachment attachment = slot.attachment;
/*  65 */       if (attachment instanceof BoundingBoxAttachment) {
/*  66 */         BoundingBoxAttachment boundingBox = (BoundingBoxAttachment)attachment;
/*  67 */         boundingBoxes.add(boundingBox);
/*     */         
/*  69 */         FloatArray polygon = (FloatArray)this.polygonPool.obtain();
/*  70 */         polygons.add(polygon);
/*  71 */         boundingBox.computeWorldVertices(slot, polygon.setSize(boundingBox.getWorldVerticesLength()));
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     if (updateAabb) aabbCompute(); 
/*     */   }
/*     */   
/*     */   private void aabbCompute() {
/*  79 */     float minX = 2.14748365E9F, minY = 2.14748365E9F, maxX = -2.14748365E9F, maxY = -2.14748365E9F;
/*  80 */     Array<FloatArray> polygons = this.polygons;
/*  81 */     for (int i = 0, n = polygons.size; i < n; i++) {
/*  82 */       FloatArray polygon = (FloatArray)polygons.get(i);
/*  83 */       float[] vertices = polygon.items;
/*  84 */       for (int ii = 0, nn = polygon.size; ii < nn; ii += 2) {
/*  85 */         float x = vertices[ii];
/*  86 */         float y = vertices[ii + 1];
/*  87 */         minX = Math.min(minX, x);
/*  88 */         minY = Math.min(minY, y);
/*  89 */         maxX = Math.max(maxX, x);
/*  90 */         maxY = Math.max(maxY, y);
/*     */       } 
/*     */     } 
/*  93 */     this.minX = minX;
/*  94 */     this.minY = minY;
/*  95 */     this.maxX = maxX;
/*  96 */     this.maxY = maxY;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aabbContainsPoint(float x, float y) {
/* 101 */     return (x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aabbIntersectsSegment(float x1, float y1, float x2, float y2) {
/* 106 */     float minX = this.minX;
/* 107 */     float minY = this.minY;
/* 108 */     float maxX = this.maxX;
/* 109 */     float maxY = this.maxY;
/* 110 */     if ((x1 <= minX && x2 <= minX) || (y1 <= minY && y2 <= minY) || (x1 >= maxX && x2 >= maxX) || (y1 >= maxY && y2 >= maxY))
/* 111 */       return false; 
/* 112 */     float m = (y2 - y1) / (x2 - x1);
/* 113 */     float y = m * (minX - x1) + y1;
/* 114 */     if (y > minY && y < maxY) return true; 
/* 115 */     y = m * (maxX - x1) + y1;
/* 116 */     if (y > minY && y < maxY) return true; 
/* 117 */     float x = (minY - y1) / m + x1;
/* 118 */     if (x > minX && x < maxX) return true; 
/* 119 */     x = (maxY - y1) / m + x1;
/* 120 */     if (x > minX && x < maxX) return true; 
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean aabbIntersectsSkeleton(SkeletonBounds bounds) {
/* 126 */     return (this.minX < bounds.maxX && this.maxX > bounds.minX && this.minY < bounds.maxY && this.maxY > bounds.minY);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBoxAttachment containsPoint(float x, float y) {
/* 132 */     Array<FloatArray> polygons = this.polygons;
/* 133 */     for (int i = 0, n = polygons.size; i < n; i++) {
/* 134 */       if (containsPoint((FloatArray)polygons.get(i), x, y)) return (BoundingBoxAttachment)this.boundingBoxes.get(i); 
/* 135 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsPoint(FloatArray polygon, float x, float y) {
/* 140 */     float[] vertices = polygon.items;
/* 141 */     int nn = polygon.size;
/*     */     
/* 143 */     int prevIndex = nn - 2;
/* 144 */     boolean inside = false;
/* 145 */     for (int ii = 0; ii < nn; ii += 2) {
/* 146 */       float vertexY = vertices[ii + 1];
/* 147 */       float prevY = vertices[prevIndex + 1];
/* 148 */       if ((vertexY < y && prevY >= y) || (prevY < y && vertexY >= y)) {
/* 149 */         float vertexX = vertices[ii];
/* 150 */         if (vertexX + (y - vertexY) / (prevY - vertexY) * (vertices[prevIndex] - vertexX) < x) inside = !inside; 
/*     */       } 
/* 152 */       prevIndex = ii;
/*     */     } 
/* 154 */     return inside;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBoxAttachment intersectsSegment(float x1, float y1, float x2, float y2) {
/* 161 */     Array<FloatArray> polygons = this.polygons;
/* 162 */     for (int i = 0, n = polygons.size; i < n; i++) {
/* 163 */       if (intersectsSegment((FloatArray)polygons.get(i), x1, y1, x2, y2)) return (BoundingBoxAttachment)this.boundingBoxes.get(i); 
/* 164 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean intersectsSegment(FloatArray polygon, float x1, float y1, float x2, float y2) {
/* 169 */     float[] vertices = polygon.items;
/* 170 */     int nn = polygon.size;
/*     */     
/* 172 */     float width12 = x1 - x2, height12 = y1 - y2;
/* 173 */     float det1 = x1 * y2 - y1 * x2;
/* 174 */     float x3 = vertices[nn - 2], y3 = vertices[nn - 1];
/* 175 */     for (int ii = 0; ii < nn; ii += 2) {
/* 176 */       float x4 = vertices[ii], y4 = vertices[ii + 1];
/* 177 */       float det2 = x3 * y4 - y3 * x4;
/* 178 */       float width34 = x3 - x4, height34 = y3 - y4;
/* 179 */       float det3 = width12 * height34 - height12 * width34;
/* 180 */       float x = (det1 * width34 - width12 * det2) / det3;
/* 181 */       if (((x >= x3 && x <= x4) || (x >= x4 && x <= x3)) && ((x >= x1 && x <= x2) || (x >= x2 && x <= x1))) {
/* 182 */         float y = (det1 * height34 - height12 * det2) / det3;
/* 183 */         if (((y >= y3 && y <= y4) || (y >= y4 && y <= y3)) && ((y >= y1 && y <= y2) || (y >= y2 && y <= y1))) return true; 
/*     */       } 
/* 185 */       x3 = x4;
/* 186 */       y3 = y4;
/*     */     } 
/* 188 */     return false;
/*     */   }
/*     */   
/*     */   public float getMinX() {
/* 192 */     return this.minX;
/*     */   }
/*     */   
/*     */   public float getMinY() {
/* 196 */     return this.minY;
/*     */   }
/*     */   
/*     */   public float getMaxX() {
/* 200 */     return this.maxX;
/*     */   }
/*     */   
/*     */   public float getMaxY() {
/* 204 */     return this.maxY;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 208 */     return this.maxX - this.minX;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 212 */     return this.maxY - this.minY;
/*     */   }
/*     */   
/*     */   public Array<BoundingBoxAttachment> getBoundingBoxes() {
/* 216 */     return this.boundingBoxes;
/*     */   }
/*     */   
/*     */   public Array<FloatArray> getPolygons() {
/* 220 */     return this.polygons;
/*     */   }
/*     */ 
/*     */   
/*     */   public FloatArray getPolygon(BoundingBoxAttachment boundingBox) {
/* 225 */     if (boundingBox == null) throw new IllegalArgumentException("boundingBox cannot be null."); 
/* 226 */     int index = this.boundingBoxes.indexOf(boundingBox, true);
/* 227 */     return (index == -1) ? null : (FloatArray)this.polygons.get(index);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonBounds.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */