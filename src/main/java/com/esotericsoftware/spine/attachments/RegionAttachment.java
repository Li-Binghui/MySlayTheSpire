/*     */ package com.esotericsoftware.spine.attachments;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.MathUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RegionAttachment
/*     */   extends Attachment
/*     */ {
/*     */   public static final int BLX = 0;
/*     */   public static final int BLY = 1;
/*     */   public static final int ULX = 2;
/*     */   public static final int ULY = 3;
/*     */   public static final int URX = 4;
/*     */   public static final int URY = 5;
/*     */   public static final int BRX = 6;
/*     */   public static final int BRY = 7;
/*     */   private TextureRegion region;
/*     */   private String path;
/*     */   private float x;
/*     */   private float y;
/*  59 */   private float scaleX = 1.0F; private float scaleY = 1.0F; private float rotation;
/*  60 */   private final float[] vertices = new float[20]; private float width; private float height;
/*  61 */   private final float[] offset = new float[8];
/*  62 */   private final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   
/*     */   public RegionAttachment(String name) {
/*  65 */     super(name);
/*     */   }
/*     */   
/*     */   public void updateOffset() {
/*  69 */     float width = getWidth();
/*  70 */     float height = getHeight();
/*  71 */     float localX2 = width / 2.0F;
/*  72 */     float localY2 = height / 2.0F;
/*  73 */     float localX = -localX2;
/*  74 */     float localY = -localY2;
/*  75 */     if (this.region instanceof TextureAtlas.AtlasRegion) {
/*  76 */       TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.region;
/*  77 */       if (region.rotate) {
/*  78 */         localX += region.offsetX / region.originalWidth * width;
/*  79 */         localY += region.offsetY / region.originalHeight * height;
/*  80 */         localX2 -= (region.originalWidth - region.offsetX - region.packedHeight) / region.originalWidth * width;
/*  81 */         localY2 -= (region.originalHeight - region.offsetY - region.packedWidth) / region.originalHeight * height;
/*     */       } else {
/*  83 */         localX += region.offsetX / region.originalWidth * width;
/*  84 */         localY += region.offsetY / region.originalHeight * height;
/*  85 */         localX2 -= (region.originalWidth - region.offsetX - region.packedWidth) / region.originalWidth * width;
/*  86 */         localY2 -= (region.originalHeight - region.offsetY - region.packedHeight) / region.originalHeight * height;
/*     */       } 
/*     */     } 
/*  89 */     float scaleX = getScaleX();
/*  90 */     float scaleY = getScaleY();
/*  91 */     localX *= scaleX;
/*  92 */     localY *= scaleY;
/*  93 */     localX2 *= scaleX;
/*  94 */     localY2 *= scaleY;
/*  95 */     float rotation = getRotation();
/*  96 */     float cos = MathUtils.cosDeg(rotation);
/*  97 */     float sin = MathUtils.sinDeg(rotation);
/*  98 */     float x = getX();
/*  99 */     float y = getY();
/* 100 */     float localXCos = localX * cos + x;
/* 101 */     float localXSin = localX * sin;
/* 102 */     float localYCos = localY * cos + y;
/* 103 */     float localYSin = localY * sin;
/* 104 */     float localX2Cos = localX2 * cos + x;
/* 105 */     float localX2Sin = localX2 * sin;
/* 106 */     float localY2Cos = localY2 * cos + y;
/* 107 */     float localY2Sin = localY2 * sin;
/* 108 */     float[] offset = this.offset;
/* 109 */     offset[0] = localXCos - localYSin;
/* 110 */     offset[1] = localYCos + localXSin;
/* 111 */     offset[2] = localXCos - localY2Sin;
/* 112 */     offset[3] = localY2Cos + localXSin;
/* 113 */     offset[4] = localX2Cos - localY2Sin;
/* 114 */     offset[5] = localY2Cos + localX2Sin;
/* 115 */     offset[6] = localX2Cos - localYSin;
/* 116 */     offset[7] = localYCos + localX2Sin;
/*     */   }
/*     */   
/*     */   public void setRegion(TextureRegion region) {
/* 120 */     if (region == null) throw new IllegalArgumentException("region cannot be null."); 
/* 121 */     this.region = region;
/* 122 */     float[] vertices = this.vertices;
/* 123 */     if (region instanceof TextureAtlas.AtlasRegion && ((TextureAtlas.AtlasRegion)region).rotate) {
/* 124 */       vertices[13] = region.getU();
/* 125 */       vertices[14] = region.getV2();
/* 126 */       vertices[18] = region.getU();
/* 127 */       vertices[19] = region.getV();
/* 128 */       vertices[3] = region.getU2();
/* 129 */       vertices[4] = region.getV();
/* 130 */       vertices[8] = region.getU2();
/* 131 */       vertices[9] = region.getV2();
/*     */     } else {
/* 133 */       vertices[8] = region.getU();
/* 134 */       vertices[9] = region.getV2();
/* 135 */       vertices[13] = region.getU();
/* 136 */       vertices[14] = region.getV();
/* 137 */       vertices[18] = region.getU2();
/* 138 */       vertices[19] = region.getV();
/* 139 */       vertices[3] = region.getU2();
/* 140 */       vertices[4] = region.getV2();
/*     */     } 
/*     */   }
/*     */   
/*     */   public TextureRegion getRegion() {
/* 145 */     if (this.region == null) throw new IllegalStateException("Region has not been set: " + this); 
/* 146 */     return this.region;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] updateWorldVertices(Slot slot, boolean premultipliedAlpha) {
/* 151 */     Skeleton skeleton = slot.getSkeleton();
/* 152 */     Color skeletonColor = skeleton.getColor();
/* 153 */     Color slotColor = slot.getColor();
/* 154 */     Color regionColor = this.color;
/* 155 */     float alpha = skeletonColor.a * slotColor.a * regionColor.a * 255.0F;
/* 156 */     float multiplier = premultipliedAlpha ? alpha : 255.0F;
/* 157 */     float color = NumberUtils.intToFloatColor((int)alpha << 24 | (int)(skeletonColor.b * slotColor.b * regionColor.b * multiplier) << 16 | (int)(skeletonColor.g * slotColor.g * regionColor.g * multiplier) << 8 | (int)(skeletonColor.r * slotColor.r * regionColor.r * multiplier));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     float[] vertices = this.vertices;
/* 164 */     float[] offset = this.offset;
/* 165 */     Bone bone = slot.getBone();
/* 166 */     float x = skeleton.getX() + bone.getWorldX(), y = skeleton.getY() + bone.getWorldY();
/* 167 */     float a = bone.getA(), b = bone.getB(), c = bone.getC(), d = bone.getD();
/*     */ 
/*     */     
/* 170 */     float offsetX = offset[6];
/* 171 */     float offsetY = offset[7];
/* 172 */     vertices[0] = offsetX * a + offsetY * b + x;
/* 173 */     vertices[1] = offsetX * c + offsetY * d + y;
/* 174 */     vertices[2] = color;
/*     */     
/* 176 */     offsetX = offset[0];
/* 177 */     offsetY = offset[1];
/* 178 */     vertices[5] = offsetX * a + offsetY * b + x;
/* 179 */     vertices[6] = offsetX * c + offsetY * d + y;
/* 180 */     vertices[7] = color;
/*     */     
/* 182 */     offsetX = offset[2];
/* 183 */     offsetY = offset[3];
/* 184 */     vertices[10] = offsetX * a + offsetY * b + x;
/* 185 */     vertices[11] = offsetX * c + offsetY * d + y;
/* 186 */     vertices[12] = color;
/*     */     
/* 188 */     offsetX = offset[4];
/* 189 */     offsetY = offset[5];
/* 190 */     vertices[15] = offsetX * a + offsetY * b + x;
/* 191 */     vertices[16] = offsetX * c + offsetY * d + y;
/* 192 */     vertices[17] = color;
/* 193 */     return vertices;
/*     */   }
/*     */   
/*     */   public float[] getWorldVertices() {
/* 197 */     return this.vertices;
/*     */   }
/*     */   
/*     */   public float[] getOffset() {
/* 201 */     return this.offset;
/*     */   }
/*     */   
/*     */   public float getX() {
/* 205 */     return this.x;
/*     */   }
/*     */   
/*     */   public void setX(float x) {
/* 209 */     this.x = x;
/*     */   }
/*     */   
/*     */   public float getY() {
/* 213 */     return this.y;
/*     */   }
/*     */   
/*     */   public void setY(float y) {
/* 217 */     this.y = y;
/*     */   }
/*     */   
/*     */   public float getScaleX() {
/* 221 */     return this.scaleX;
/*     */   }
/*     */   
/*     */   public void setScaleX(float scaleX) {
/* 225 */     this.scaleX = scaleX;
/*     */   }
/*     */   
/*     */   public float getScaleY() {
/* 229 */     return this.scaleY;
/*     */   }
/*     */   
/*     */   public void setScaleY(float scaleY) {
/* 233 */     this.scaleY = scaleY;
/*     */   }
/*     */   
/*     */   public float getRotation() {
/* 237 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void setRotation(float rotation) {
/* 241 */     this.rotation = rotation;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 245 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/* 249 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 253 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 257 */     this.height = height;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 261 */     return this.color;
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 265 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/* 269 */     this.path = path;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\RegionAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */