/*     */ package com.esotericsoftware.spine.attachments;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.utils.FloatArray;
/*     */ import com.badlogic.gdx.utils.NumberUtils;
/*     */ import com.esotericsoftware.spine.Bone;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.esotericsoftware.spine.Slot;
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
/*     */ public class MeshAttachment
/*     */   extends VertexAttachment
/*     */ {
/*     */   private TextureRegion region;
/*     */   private String path;
/*     */   private float[] regionUVs;
/*     */   private float[] worldVertices;
/*     */   private short[] triangles;
/*  49 */   private final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   
/*     */   private int hullLength;
/*     */   private MeshAttachment parentMesh;
/*     */   private boolean inheritDeform;
/*     */   private short[] edges;
/*     */   private float width;
/*     */   private float height;
/*     */   
/*     */   public MeshAttachment(String name) {
/*  59 */     super(name);
/*     */   }
/*     */   
/*     */   public void setRegion(TextureRegion region) {
/*  63 */     if (region == null) throw new IllegalArgumentException("region cannot be null."); 
/*  64 */     this.region = region;
/*     */   }
/*     */   
/*     */   public TextureRegion getRegion() {
/*  68 */     if (this.region == null) throw new IllegalStateException("Region has not been set: " + this); 
/*  69 */     return this.region;
/*     */   }
/*     */   
/*     */   public void updateUVs() {
/*  73 */     float u, v, width, height, regionUVs[] = this.regionUVs;
/*  74 */     int verticesLength = regionUVs.length;
/*  75 */     int worldVerticesLength = (verticesLength >> 1) * 5;
/*  76 */     if (this.worldVertices == null || this.worldVertices.length != worldVerticesLength) this.worldVertices = new float[worldVerticesLength];
/*     */ 
/*     */     
/*  79 */     if (this.region == null) {
/*  80 */       u = v = 0.0F;
/*  81 */       width = height = 1.0F;
/*     */     } else {
/*  83 */       u = this.region.getU();
/*  84 */       v = this.region.getV();
/*  85 */       width = this.region.getU2() - u;
/*  86 */       height = this.region.getV2() - v;
/*     */     } 
/*  88 */     if (this.region instanceof TextureAtlas.AtlasRegion && ((TextureAtlas.AtlasRegion)this.region).rotate) {
/*  89 */       for (int i = 0, w = 3; i < verticesLength; i += 2, w += 5) {
/*  90 */         this.worldVertices[w] = u + regionUVs[i + 1] * width;
/*  91 */         this.worldVertices[w + 1] = v + height - regionUVs[i] * height;
/*     */       } 
/*     */     } else {
/*  94 */       for (int i = 0, w = 3; i < verticesLength; i += 2, w += 5) {
/*  95 */         this.worldVertices[w] = u + regionUVs[i] * width;
/*  96 */         this.worldVertices[w + 1] = v + regionUVs[i + 1] * height;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] updateWorldVertices(Slot slot, boolean premultipliedAlpha) {
/* 103 */     Skeleton skeleton = slot.getSkeleton();
/* 104 */     Color skeletonColor = skeleton.getColor(), slotColor = slot.getColor(), meshColor = this.color;
/* 105 */     float alpha = skeletonColor.a * slotColor.a * meshColor.a * 255.0F;
/* 106 */     float multiplier = premultipliedAlpha ? alpha : 255.0F;
/* 107 */     float color = NumberUtils.intToFloatColor((int)alpha << 24 | (int)(skeletonColor.b * slotColor.b * meshColor.b * multiplier) << 16 | (int)(skeletonColor.g * slotColor.g * meshColor.g * multiplier) << 8 | (int)(skeletonColor.r * slotColor.r * meshColor.r * multiplier));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     float x = skeleton.getX(), y = skeleton.getY();
/* 114 */     FloatArray deformArray = slot.getAttachmentVertices();
/* 115 */     float[] vertices = this.vertices, worldVertices = this.worldVertices;
/* 116 */     int[] bones = this.bones;
/* 117 */     if (bones == null) {
/* 118 */       int verticesLength = vertices.length;
/* 119 */       if (deformArray.size > 0) vertices = deformArray.items; 
/* 120 */       Bone bone = slot.getBone();
/* 121 */       x += bone.getWorldX();
/* 122 */       y += bone.getWorldY();
/* 123 */       float a = bone.getA(), b = bone.getB(), c = bone.getC(), d = bone.getD();
/* 124 */       for (int v = 0, w = 0; v < verticesLength; v += 2, w += 5) {
/* 125 */         float vx = vertices[v], vy = vertices[v + 1];
/* 126 */         worldVertices[w] = vx * a + vy * b + x;
/* 127 */         worldVertices[w + 1] = vx * c + vy * d + y;
/* 128 */         worldVertices[w + 2] = color;
/*     */       } 
/* 130 */       return worldVertices;
/*     */     } 
/* 132 */     Object[] skeletonBones = (skeleton.getBones()).items;
/* 133 */     if (deformArray.size == 0) {
/* 134 */       for (int w = 0, v = 0, b = 0, n = bones.length; v < n; w += 5) {
/* 135 */         float wx = x, wy = y;
/* 136 */         int nn = bones[v++] + v;
/* 137 */         for (; v < nn; v++, b += 3) {
/* 138 */           Bone bone = (Bone)skeletonBones[bones[v]];
/* 139 */           float vx = vertices[b], vy = vertices[b + 1], weight = vertices[b + 2];
/* 140 */           wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
/* 141 */           wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
/*     */         } 
/* 143 */         worldVertices[w] = wx;
/* 144 */         worldVertices[w + 1] = wy;
/* 145 */         worldVertices[w + 2] = color;
/*     */       } 
/*     */     } else {
/* 148 */       float[] deform = deformArray.items;
/* 149 */       for (int w = 0, v = 0, b = 0, f = 0, n = bones.length; v < n; w += 5) {
/* 150 */         float wx = x, wy = y;
/* 151 */         int nn = bones[v++] + v;
/* 152 */         for (; v < nn; v++, b += 3, f += 2) {
/* 153 */           Bone bone = (Bone)skeletonBones[bones[v]];
/* 154 */           float vx = vertices[b] + deform[f], vy = vertices[b + 1] + deform[f + 1], weight = vertices[b + 2];
/* 155 */           wx += (vx * bone.getA() + vy * bone.getB() + bone.getWorldX()) * weight;
/* 156 */           wy += (vx * bone.getC() + vy * bone.getD() + bone.getWorldY()) * weight;
/*     */         } 
/* 158 */         worldVertices[w] = wx;
/* 159 */         worldVertices[w + 1] = wy;
/* 160 */         worldVertices[w + 2] = color;
/*     */       } 
/*     */     } 
/* 163 */     return worldVertices;
/*     */   }
/*     */   
/*     */   public boolean applyDeform(VertexAttachment sourceAttachment) {
/* 167 */     return (this == sourceAttachment || (this.inheritDeform && this.parentMesh == sourceAttachment));
/*     */   }
/*     */   
/*     */   public float[] getWorldVertices() {
/* 171 */     return this.worldVertices;
/*     */   }
/*     */   
/*     */   public short[] getTriangles() {
/* 175 */     return this.triangles;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTriangles(short[] triangles) {
/* 180 */     this.triangles = triangles;
/*     */   }
/*     */   
/*     */   public float[] getRegionUVs() {
/* 184 */     return this.regionUVs;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegionUVs(float[] regionUVs) {
/* 189 */     this.regionUVs = regionUVs;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 193 */     return this.color;
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 197 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/* 201 */     this.path = path;
/*     */   }
/*     */   
/*     */   public int getHullLength() {
/* 205 */     return this.hullLength;
/*     */   }
/*     */   
/*     */   public void setHullLength(int hullLength) {
/* 209 */     this.hullLength = hullLength;
/*     */   }
/*     */   
/*     */   public void setEdges(short[] edges) {
/* 213 */     this.edges = edges;
/*     */   }
/*     */   
/*     */   public short[] getEdges() {
/* 217 */     return this.edges;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 221 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/* 225 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 229 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 233 */     this.height = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public MeshAttachment getParentMesh() {
/* 238 */     return this.parentMesh;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParentMesh(MeshAttachment parentMesh) {
/* 243 */     this.parentMesh = parentMesh;
/* 244 */     if (parentMesh != null) {
/* 245 */       this.bones = parentMesh.bones;
/* 246 */       this.vertices = parentMesh.vertices;
/* 247 */       this.regionUVs = parentMesh.regionUVs;
/* 248 */       this.triangles = parentMesh.triangles;
/* 249 */       this.hullLength = parentMesh.hullLength;
/* 250 */       this.edges = parentMesh.edges;
/* 251 */       this.width = parentMesh.width;
/* 252 */       this.height = parentMesh.height;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getInheritDeform() {
/* 257 */     return this.inheritDeform;
/*     */   }
/*     */   
/*     */   public void setInheritDeform(boolean inheritDeform) {
/* 261 */     this.inheritDeform = inheritDeform;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\MeshAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */