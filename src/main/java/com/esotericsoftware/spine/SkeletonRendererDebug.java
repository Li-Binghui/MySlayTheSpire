/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.attachments.*;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkeletonRendererDebug
/*     */ {
/*  50 */   private static final Color boneLineColor = Color.RED;
/*  51 */   private static final Color boneOriginColor = Color.GREEN;
/*  52 */   private static final Color attachmentLineColor = new Color(0.0F, 0.0F, 1.0F, 0.5F);
/*  53 */   private static final Color triangleLineColor = new Color(1.0F, 0.64F, 0.0F, 0.5F);
/*  54 */   private static final Color aabbColor = new Color(0.0F, 1.0F, 0.0F, 0.5F);
/*     */   
/*     */   private final ShapeRenderer shapes;
/*     */   
/*     */   private boolean drawBones = true, drawRegionAttachments = true, drawBoundingBoxes = true;
/*  59 */   private final SkeletonBounds bounds = new SkeletonBounds(); private boolean drawMeshHull = true; private boolean drawMeshTriangles = true; private boolean drawPaths = true;
/*  60 */   private final FloatArray temp = new FloatArray();
/*  61 */   private float scale = 1.0F;
/*  62 */   private float boneWidth = 2.0F;
/*     */   private boolean premultipliedAlpha;
/*     */   
/*     */   public SkeletonRendererDebug() {
/*  66 */     this.shapes = new ShapeRenderer();
/*     */   }
/*     */   
/*     */   public SkeletonRendererDebug(ShapeRenderer shapes) {
/*  70 */     this.shapes = shapes;
/*     */   }
/*     */   
/*     */   public void draw(Skeleton skeleton) {
/*  74 */     float skeletonX = skeleton.getX();
/*  75 */     float skeletonY = skeleton.getY();
/*     */     
/*  77 */     Gdx.gl.glEnable(3042);
/*  78 */     int srcFunc = this.premultipliedAlpha ? 1 : 770;
/*  79 */     Gdx.gl.glBlendFunc(srcFunc, 771);
/*     */     
/*  81 */     ShapeRenderer shapes = this.shapes;
/*     */     
/*  83 */     Array<Bone> bones = skeleton.getBones();
/*  84 */     if (this.drawBones) {
/*  85 */       shapes.setColor(boneLineColor);
/*  86 */       shapes.begin(ShapeRenderer.ShapeType.Filled);
/*  87 */       for (int i = 0, n = bones.size; i < n; i++) {
/*  88 */         Bone bone = (Bone)bones.get(i);
/*  89 */         if (bone.parent != null) {
/*  90 */           float x = skeletonX + bone.data.length * bone.a + bone.worldX;
/*  91 */           float y = skeletonY + bone.data.length * bone.c + bone.worldY;
/*  92 */           shapes.rectLine(skeletonX + bone.worldX, skeletonY + bone.worldY, x, y, this.boneWidth * this.scale);
/*     */         } 
/*  94 */       }  shapes.end();
/*  95 */       shapes.begin(ShapeRenderer.ShapeType.Line);
/*  96 */       shapes.x(skeletonX, skeletonY, 4.0F * this.scale);
/*     */     } else {
/*  98 */       shapes.begin(ShapeRenderer.ShapeType.Line);
/*     */     } 
/* 100 */     if (this.drawRegionAttachments) {
/* 101 */       shapes.setColor(attachmentLineColor);
/* 102 */       Array<Slot> slots = skeleton.getSlots();
/* 103 */       for (int i = 0, n = slots.size; i < n; i++) {
/* 104 */         Slot slot = (Slot)slots.get(i);
/* 105 */         Attachment attachment = slot.attachment;
/* 106 */         if (attachment instanceof RegionAttachment) {
/* 107 */           RegionAttachment regionAttachment = (RegionAttachment)attachment;
/* 108 */           float[] vertices = regionAttachment.updateWorldVertices(slot, false);
/* 109 */           shapes.line(vertices[0], vertices[1], vertices[5], vertices[6]);
/* 110 */           shapes.line(vertices[5], vertices[6], vertices[10], vertices[11]);
/* 111 */           shapes.line(vertices[10], vertices[11], vertices[15], vertices[16]);
/* 112 */           shapes.line(vertices[15], vertices[16], vertices[0], vertices[1]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     if (this.drawMeshHull || this.drawMeshTriangles) {
/* 118 */       Array<Slot> slots = skeleton.getSlots();
/* 119 */       for (int i = 0, n = slots.size; i < n; i++) {
/* 120 */         Slot slot = (Slot)slots.get(i);
/* 121 */         Attachment attachment = slot.attachment;
/* 122 */         if (attachment instanceof MeshAttachment) {
/* 123 */           MeshAttachment mesh = (MeshAttachment)attachment;
/* 124 */           mesh.updateWorldVertices(slot, false);
/* 125 */           float[] vertices = mesh.getWorldVertices();
/* 126 */           short[] triangles = mesh.getTriangles();
/* 127 */           int hullLength = mesh.getHullLength();
/* 128 */           if (this.drawMeshTriangles) {
/* 129 */             shapes.setColor(triangleLineColor);
/* 130 */             for (int ii = 0, nn = triangles.length; ii < nn; ii += 3) {
/* 131 */               int v1 = triangles[ii] * 5, v2 = triangles[ii + 1] * 5, v3 = triangles[ii + 2] * 5;
/* 132 */               shapes.triangle(vertices[v1], vertices[v1 + 1], vertices[v2], vertices[v2 + 1], vertices[v3], vertices[v3 + 1]);
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 138 */           if (this.drawMeshHull && hullLength > 0) {
/* 139 */             shapes.setColor(attachmentLineColor);
/* 140 */             hullLength = (hullLength >> 1) * 5;
/* 141 */             float lastX = vertices[hullLength - 5], lastY = vertices[hullLength - 4];
/* 142 */             for (int ii = 0, nn = hullLength; ii < nn; ii += 5) {
/* 143 */               float x = vertices[ii], y = vertices[ii + 1];
/* 144 */               shapes.line(x, y, lastX, lastY);
/* 145 */               lastX = x;
/* 146 */               lastY = y;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 152 */     if (this.drawBoundingBoxes) {
/* 153 */       SkeletonBounds bounds = this.bounds;
/* 154 */       bounds.update(skeleton, true);
/* 155 */       shapes.setColor(aabbColor);
/* 156 */       shapes.rect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
/* 157 */       Array<FloatArray> polygons = bounds.getPolygons();
/* 158 */       Array<BoundingBoxAttachment> boxes = bounds.getBoundingBoxes();
/* 159 */       for (int i = 0, n = polygons.size; i < n; i++) {
/* 160 */         FloatArray polygon = (FloatArray)polygons.get(i);
/* 161 */         shapes.setColor(((BoundingBoxAttachment)boxes.get(i)).getColor());
/* 162 */         shapes.polygon(polygon.items, 0, polygon.size);
/*     */       } 
/*     */     } 
/*     */     
/* 166 */     if (this.drawPaths) {
/* 167 */       Array<Slot> slots = skeleton.getSlots();
/* 168 */       for (int i = 0, n = slots.size; i < n; i++) {
/* 169 */         Slot slot = (Slot)slots.get(i);
/* 170 */         Attachment attachment = slot.attachment;
/* 171 */         if (attachment instanceof PathAttachment) {
/* 172 */           PathAttachment path = (PathAttachment)attachment;
/* 173 */           int nn = path.getWorldVerticesLength();
/* 174 */           float[] world = this.temp.setSize(nn);
/* 175 */           path.computeWorldVertices(slot, world);
/* 176 */           Color color = path.getColor();
/* 177 */           float x1 = world[2], y1 = world[3], x2 = 0.0F, y2 = 0.0F;
/* 178 */           if (path.getClosed()) {
/* 179 */             shapes.setColor(color);
/* 180 */             float cx1 = world[0], cy1 = world[1], cx2 = world[nn - 2], cy2 = world[nn - 1];
/* 181 */             x2 = world[nn - 4];
/* 182 */             y2 = world[nn - 3];
/* 183 */             shapes.curve(x1, y1, cx1, cy1, cx2, cy2, x2, y2, 32);
/* 184 */             shapes.setColor(Color.LIGHT_GRAY);
/* 185 */             shapes.line(x1, y1, cx1, cy1);
/* 186 */             shapes.line(x2, y2, cx2, cy2);
/*     */           } 
/* 188 */           nn -= 4;
/* 189 */           for (int ii = 4; ii < nn; ii += 6) {
/* 190 */             float cx1 = world[ii], cy1 = world[ii + 1], cx2 = world[ii + 2], cy2 = world[ii + 3];
/* 191 */             x2 = world[ii + 4];
/* 192 */             y2 = world[ii + 5];
/* 193 */             shapes.setColor(color);
/* 194 */             shapes.curve(x1, y1, cx1, cy1, cx2, cy2, x2, y2, 32);
/* 195 */             shapes.setColor(Color.LIGHT_GRAY);
/* 196 */             shapes.line(x1, y1, cx1, cy1);
/* 197 */             shapes.line(x2, y2, cx2, cy2);
/* 198 */             x1 = x2;
/* 199 */             y1 = y2;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 204 */     shapes.end();
/* 205 */     shapes.begin(ShapeRenderer.ShapeType.Filled);
/*     */     
/* 207 */     if (this.drawBones) {
/* 208 */       shapes.setColor(boneOriginColor);
/* 209 */       for (int i = 0, n = bones.size; i < n; i++) {
/* 210 */         Bone bone = (Bone)bones.get(i);
/* 211 */         shapes.setColor(Color.GREEN);
/* 212 */         shapes.circle(skeletonX + bone.worldX, skeletonY + bone.worldY, 3.0F * this.scale, 8);
/*     */       } 
/*     */     } 
/*     */     
/* 216 */     shapes.end();
/*     */   }
/*     */ 
/*     */   
/*     */   public ShapeRenderer getShapeRenderer() {
/* 221 */     return this.shapes;
/*     */   }
/*     */   
/*     */   public void setBones(boolean bones) {
/* 225 */     this.drawBones = bones;
/*     */   }
/*     */   
/*     */   public void setScale(float scale) {
/* 229 */     this.scale = scale;
/*     */   }
/*     */   
/*     */   public void setRegionAttachments(boolean regionAttachments) {
/* 233 */     this.drawRegionAttachments = regionAttachments;
/*     */   }
/*     */   
/*     */   public void setBoundingBoxes(boolean boundingBoxes) {
/* 237 */     this.drawBoundingBoxes = boundingBoxes;
/*     */   }
/*     */   
/*     */   public void setMeshHull(boolean meshHull) {
/* 241 */     this.drawMeshHull = meshHull;
/*     */   }
/*     */   
/*     */   public void setMeshTriangles(boolean meshTriangles) {
/* 245 */     this.drawMeshTriangles = meshTriangles;
/*     */   }
/*     */   
/*     */   public void setPaths(boolean paths) {
/* 249 */     this.drawPaths = paths;
/*     */   }
/*     */   
/*     */   public void setPremultipliedAlpha(boolean premultipliedAlpha) {
/* 253 */     this.premultipliedAlpha = premultipliedAlpha;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonRendererDebug.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */