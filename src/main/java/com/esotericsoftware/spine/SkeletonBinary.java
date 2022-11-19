/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.*;
import com.esotericsoftware.spine.attachments.*;

import java.io.EOFException;
import java.io.IOException;
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
/*     */ public class SkeletonBinary
/*     */ {
/*     */   public static final int BONE_ROTATE = 0;
/*     */   public static final int BONE_TRANSLATE = 1;
/*     */   public static final int BONE_SCALE = 2;
/*     */   public static final int BONE_SHEAR = 3;
/*     */   public static final int SLOT_ATTACHMENT = 0;
/*     */   public static final int SLOT_COLOR = 1;
/*     */   public static final int PATH_POSITION = 0;
/*     */   public static final int PATH_SPACING = 1;
/*     */   public static final int PATH_MIX = 2;
/*     */   public static final int CURVE_LINEAR = 0;
/*     */   public static final int CURVE_STEPPED = 1;
/*     */   public static final int CURVE_BEZIER = 2;
/*  92 */   private static final Color tempColor = new Color();
/*     */   
/*     */   private final AttachmentLoader attachmentLoader;
/*  95 */   private float scale = 1.0F;
/*  96 */   private Array<SkeletonJson.LinkedMesh> linkedMeshes = new Array();
/*     */   
/*     */   public SkeletonBinary(TextureAtlas atlas) {
/*  99 */     this.attachmentLoader = (AttachmentLoader)new AtlasAttachmentLoader(atlas);
/*     */   }
/*     */   
/*     */   public SkeletonBinary(AttachmentLoader attachmentLoader) {
/* 103 */     if (attachmentLoader == null) throw new IllegalArgumentException("attachmentLoader cannot be null."); 
/* 104 */     this.attachmentLoader = attachmentLoader;
/*     */   }
/*     */   
/*     */   public float getScale() {
/* 108 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(float scale) {
/* 113 */     this.scale = scale;
/*     */   }
/*     */   
/*     */   public SkeletonData readSkeletonData(FileHandle file) {
/* 117 */     if (file == null) throw new IllegalArgumentException("file cannot be null.");
/*     */     
/* 119 */     float scale = this.scale;
/*     */     
/* 121 */     SkeletonData skeletonData = new SkeletonData();
/* 122 */     skeletonData.name = file.nameWithoutExtension();
/*     */     
/* 124 */     DataInput input = new DataInput(file.read(512)) {
/* 125 */         private char[] chars = new char[32];
/*     */         
/*     */         public String readString() throws IOException {
/* 128 */           int byteCount = readInt(true);
/* 129 */           switch (byteCount) {
/*     */             case 0:
/* 131 */               return null;
/*     */             case 1:
/* 133 */               return "";
/*     */           } 
/* 135 */           byteCount--;
/* 136 */           if (this.chars.length < byteCount) this.chars = new char[byteCount]; 
/* 137 */           char[] chars = this.chars;
/* 138 */           int charCount = 0;
/* 139 */           for (int i = 0; i < byteCount; ) {
/* 140 */             int b = read();
/* 141 */             switch (b >> 4) {
/*     */               case -1:
/* 143 */                 throw new EOFException();
/*     */               case 12:
/*     */               case 13:
/* 146 */                 chars[charCount++] = (char)((b & 0x1F) << 6 | read() & 0x3F);
/* 147 */                 i += 2;
/*     */                 continue;
/*     */               case 14:
/* 150 */                 chars[charCount++] = (char)((b & 0xF) << 12 | (read() & 0x3F) << 6 | read() & 0x3F);
/* 151 */                 i += 3;
/*     */                 continue;
/*     */             } 
/* 154 */             chars[charCount++] = (char)b;
/* 155 */             i++;
/*     */           } 
/*     */           
/* 158 */           return new String(chars, 0, charCount);
/*     */         }
/*     */       };
/*     */     try {
/* 162 */       skeletonData.hash = input.readString();
/* 163 */       if (skeletonData.hash.isEmpty()) skeletonData.hash = null; 
/* 164 */       skeletonData.version = input.readString();
/* 165 */       if (skeletonData.version.isEmpty()) skeletonData.version = null; 
/* 166 */       skeletonData.width = input.readFloat();
/* 167 */       skeletonData.height = input.readFloat();
/*     */       
/* 169 */       boolean nonessential = input.readBoolean();
/*     */       
/* 171 */       if (nonessential) {
/* 172 */         skeletonData.imagesPath = input.readString();
/* 173 */         if (skeletonData.imagesPath.isEmpty()) skeletonData.imagesPath = null;
/*     */       
/*     */       } 
/*     */       int i, n;
/* 177 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 178 */         String name = input.readString();
/* 179 */         BoneData parent = (i == 0) ? null : (BoneData)skeletonData.bones.get(input.readInt(true));
/* 180 */         BoneData data = new BoneData(i, name, parent);
/* 181 */         data.rotation = input.readFloat();
/* 182 */         data.x = input.readFloat() * scale;
/* 183 */         data.y = input.readFloat() * scale;
/* 184 */         data.scaleX = input.readFloat();
/* 185 */         data.scaleY = input.readFloat();
/* 186 */         data.shearX = input.readFloat();
/* 187 */         data.shearY = input.readFloat();
/* 188 */         data.length = input.readFloat() * scale;
/* 189 */         data.inheritRotation = input.readBoolean();
/* 190 */         data.inheritScale = input.readBoolean();
/* 191 */         if (nonessential) Color.rgba8888ToColor(data.color, input.readInt()); 
/* 192 */         skeletonData.bones.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 196 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 197 */         String slotName = input.readString();
/* 198 */         BoneData boneData = (BoneData)skeletonData.bones.get(input.readInt(true));
/* 199 */         SlotData data = new SlotData(i, slotName, boneData);
/* 200 */         Color.rgba8888ToColor(data.color, input.readInt());
/* 201 */         data.attachmentName = input.readString();
/* 202 */         data.blendMode = BlendMode.values[input.readInt(true)];
/* 203 */         skeletonData.slots.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 207 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 208 */         IkConstraintData data = new IkConstraintData(input.readString());
/* 209 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++)
/* 210 */           data.bones.add(skeletonData.bones.get(input.readInt(true))); 
/* 211 */         data.target = (BoneData)skeletonData.bones.get(input.readInt(true));
/* 212 */         data.mix = input.readFloat();
/* 213 */         data.bendDirection = input.readByte();
/* 214 */         skeletonData.ikConstraints.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 218 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 219 */         TransformConstraintData data = new TransformConstraintData(input.readString());
/* 220 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++)
/* 221 */           data.bones.add(skeletonData.bones.get(input.readInt(true))); 
/* 222 */         data.target = (BoneData)skeletonData.bones.get(input.readInt(true));
/* 223 */         data.offsetRotation = input.readFloat();
/* 224 */         data.offsetX = input.readFloat() * scale;
/* 225 */         data.offsetY = input.readFloat() * scale;
/* 226 */         data.offsetScaleX = input.readFloat();
/* 227 */         data.offsetScaleY = input.readFloat();
/* 228 */         data.offsetShearY = input.readFloat();
/* 229 */         data.rotateMix = input.readFloat();
/* 230 */         data.translateMix = input.readFloat();
/* 231 */         data.scaleMix = input.readFloat();
/* 232 */         data.shearMix = input.readFloat();
/* 233 */         skeletonData.transformConstraints.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 237 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 238 */         PathConstraintData data = new PathConstraintData(input.readString());
/* 239 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++)
/* 240 */           data.bones.add(skeletonData.bones.get(input.readInt(true))); 
/* 241 */         data.target = (SlotData)skeletonData.slots.get(input.readInt(true));
/* 242 */         data.positionMode = PathConstraintData.PositionMode.values[input.readInt(true)];
/* 243 */         data.spacingMode = PathConstraintData.SpacingMode.values[input.readInt(true)];
/* 244 */         data.rotateMode = PathConstraintData.RotateMode.values[input.readInt(true)];
/* 245 */         data.offsetRotation = input.readFloat();
/* 246 */         data.position = input.readFloat();
/* 247 */         if (data.positionMode == PathConstraintData.PositionMode.fixed) data.position *= scale; 
/* 248 */         data.spacing = input.readFloat();
/* 249 */         if (data.spacingMode == PathConstraintData.SpacingMode.length || data.spacingMode == PathConstraintData.SpacingMode.fixed) data.spacing *= scale; 
/* 250 */         data.rotateMix = input.readFloat();
/* 251 */         data.translateMix = input.readFloat();
/* 252 */         skeletonData.pathConstraints.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 256 */       Skin defaultSkin = readSkin(input, "default", nonessential);
/* 257 */       if (defaultSkin != null) {
/* 258 */         skeletonData.defaultSkin = defaultSkin;
/* 259 */         skeletonData.skins.add(defaultSkin);
/*     */       } 
/*     */       
/*     */       int j, k;
/* 263 */       for (j = 0, k = input.readInt(true); j < k; j++) {
/* 264 */         skeletonData.skins.add(readSkin(input, input.readString(), nonessential));
/*     */       }
/*     */       
/* 267 */       for (j = 0, k = this.linkedMeshes.size; j < k; j++) {
/* 268 */         SkeletonJson.LinkedMesh linkedMesh = (SkeletonJson.LinkedMesh)this.linkedMeshes.get(j);
/* 269 */         Skin skin = (linkedMesh.skin == null) ? skeletonData.getDefaultSkin() : skeletonData.findSkin(linkedMesh.skin);
/* 270 */         if (skin == null) throw new SerializationException("Skin not found: " + linkedMesh.skin); 
/* 271 */         Attachment parent = skin.getAttachment(linkedMesh.slotIndex, linkedMesh.parent);
/* 272 */         if (parent == null) throw new SerializationException("Parent mesh not found: " + linkedMesh.parent); 
/* 273 */         linkedMesh.mesh.setParentMesh((MeshAttachment)parent);
/* 274 */         linkedMesh.mesh.updateUVs();
/*     */       } 
/* 276 */       this.linkedMeshes.clear();
/*     */ 
/*     */       
/* 279 */       for (j = 0, k = input.readInt(true); j < k; j++) {
/* 280 */         EventData data = new EventData(input.readString());
/* 281 */         data.intValue = input.readInt(false);
/* 282 */         data.floatValue = input.readFloat();
/* 283 */         data.stringValue = input.readString();
/* 284 */         skeletonData.events.add(data);
/*     */       } 
/*     */ 
/*     */       
/* 288 */       for (j = 0, k = input.readInt(true); j < k; j++) {
/* 289 */         readAnimation(input.readString(), input, skeletonData);
/*     */       }
/* 291 */     } catch (IOException ex) {
/* 292 */       throw new SerializationException("Error reading skeleton file.", ex);
/*     */     } finally {
/*     */       try {
/* 295 */         input.close();
/* 296 */       } catch (IOException iOException) {}
/*     */     } 
/*     */ 
/*     */     
/* 300 */     skeletonData.bones.shrink();
/* 301 */     skeletonData.slots.shrink();
/* 302 */     skeletonData.skins.shrink();
/* 303 */     skeletonData.events.shrink();
/* 304 */     skeletonData.animations.shrink();
/* 305 */     skeletonData.ikConstraints.shrink();
/* 306 */     return skeletonData;
/*     */   }
/*     */ 
/*     */   
/*     */   private Skin readSkin(DataInput input, String skinName, boolean nonessential) throws IOException {
/* 311 */     int slotCount = input.readInt(true);
/* 312 */     if (slotCount == 0) return null; 
/* 313 */     Skin skin = new Skin(skinName);
/* 314 */     for (int i = 0; i < slotCount; i++) {
/* 315 */       int slotIndex = input.readInt(true);
/* 316 */       for (int ii = 0, nn = input.readInt(true); ii < nn; ii++) {
/* 317 */         String name = input.readString();
/* 318 */         skin.addAttachment(slotIndex, name, readAttachment(input, skin, slotIndex, name, nonessential));
/*     */       } 
/*     */     } 
/* 321 */     return skin; } private Attachment readAttachment(DataInput input, Skin skin, int slotIndex, String attachmentName, boolean nonessential) throws IOException { String str1; int vertexCount; String path; boolean closed; float rotation; Vertices vertices; int color; boolean constantSpeed; float x; int m, k; String skinName; int j; float y; BoundingBoxAttachment box; float[] uvs; String parent; Vertices vertices1; float scaleX; short[] triangles; boolean inheritDeform; float lengths[], scaleY; Vertices vertices2; float width; int i, i1; float f1; int hullLength; float height; int n; PathAttachment pathAttachment; float f2; short[] edges; MeshAttachment mesh; int i2;
/*     */     float f3;
/*     */     RegionAttachment region;
/*     */     float f4;
/*     */     MeshAttachment meshAttachment1;
/* 326 */     float scale = this.scale;
/*     */     
/* 328 */     String name = input.readString();
/* 329 */     if (name == null) name = attachmentName;
/*     */     
/* 331 */     AttachmentType type = AttachmentType.values[input.readByte()];
/* 332 */     switch (type) {
/*     */       case region:
/* 334 */         str1 = input.readString();
/* 335 */         rotation = input.readFloat();
/* 336 */         x = input.readFloat();
/* 337 */         y = input.readFloat();
/* 338 */         scaleX = input.readFloat();
/* 339 */         scaleY = input.readFloat();
/* 340 */         f1 = input.readFloat();
/* 341 */         f2 = input.readFloat();
/* 342 */         i2 = input.readInt();
/*     */         
/* 344 */         if (str1 == null) str1 = name; 
/* 345 */         region = this.attachmentLoader.newRegionAttachment(skin, name, str1);
/* 346 */         if (region == null) return null; 
/* 347 */         region.setPath(str1);
/* 348 */         region.setX(x * scale);
/* 349 */         region.setY(y * scale);
/* 350 */         region.setScaleX(scaleX);
/* 351 */         region.setScaleY(scaleY);
/* 352 */         region.setRotation(rotation);
/* 353 */         region.setWidth(f1 * scale);
/* 354 */         region.setHeight(f2 * scale);
/* 355 */         Color.rgba8888ToColor(region.getColor(), i2);
/* 356 */         region.updateOffset();
/* 357 */         return (Attachment)region;
/*     */       
/*     */       case boundingbox:
/* 360 */         vertexCount = input.readInt(true);
/* 361 */         vertices = readVertices(input, vertexCount);
/* 362 */         m = nonessential ? input.readInt() : 0;
/*     */         
/* 364 */         box = this.attachmentLoader.newBoundingBoxAttachment(skin, name);
/* 365 */         if (box == null) return null; 
/* 366 */         box.setWorldVerticesLength(vertexCount << 1);
/* 367 */         box.setVertices(vertices.vertices);
/* 368 */         box.setBones(vertices.bones);
/* 369 */         if (nonessential) Color.rgba8888ToColor(box.getColor(), m); 
/* 370 */         return (Attachment)box;
/*     */       
/*     */       case mesh:
/* 373 */         path = input.readString();
/* 374 */         color = input.readInt();
/* 375 */         k = input.readInt(true);
/* 376 */         uvs = readFloatArray(input, k << 1, 1.0F);
/* 377 */         triangles = readShortArray(input);
/* 378 */         vertices2 = readVertices(input, k);
/* 379 */         hullLength = input.readInt(true);
/* 380 */         edges = null;
/* 381 */         f3 = 0.0F; f4 = 0.0F;
/* 382 */         if (nonessential) {
/* 383 */           edges = readShortArray(input);
/* 384 */           f3 = input.readFloat();
/* 385 */           f4 = input.readFloat();
/*     */         } 
/*     */         
/* 388 */         if (path == null) path = name; 
/* 389 */         meshAttachment1 = this.attachmentLoader.newMeshAttachment(skin, name, path);
/* 390 */         if (meshAttachment1 == null) return null; 
/* 391 */         meshAttachment1.setPath(path);
/* 392 */         Color.rgba8888ToColor(meshAttachment1.getColor(), color);
/* 393 */         meshAttachment1.setBones(vertices2.bones);
/* 394 */         meshAttachment1.setVertices(vertices2.vertices);
/* 395 */         meshAttachment1.setWorldVerticesLength(k << 1);
/* 396 */         meshAttachment1.setTriangles(triangles);
/* 397 */         meshAttachment1.setRegionUVs(uvs);
/* 398 */         meshAttachment1.updateUVs();
/* 399 */         meshAttachment1.setHullLength(hullLength << 1);
/* 400 */         if (nonessential) {
/* 401 */           meshAttachment1.setEdges(edges);
/* 402 */           meshAttachment1.setWidth(f3 * scale);
/* 403 */           meshAttachment1.setHeight(f4 * scale);
/*     */         } 
/* 405 */         return (Attachment)meshAttachment1;
/*     */       
/*     */       case linkedmesh:
/* 408 */         path = input.readString();
/* 409 */         color = input.readInt();
/* 410 */         skinName = input.readString();
/* 411 */         parent = input.readString();
/* 412 */         inheritDeform = input.readBoolean();
/* 413 */         width = 0.0F; height = 0.0F;
/* 414 */         if (nonessential) {
/* 415 */           width = input.readFloat();
/* 416 */           height = input.readFloat();
/*     */         } 
/*     */         
/* 419 */         if (path == null) path = name; 
/* 420 */         mesh = this.attachmentLoader.newMeshAttachment(skin, name, path);
/* 421 */         if (mesh == null) return null; 
/* 422 */         mesh.setPath(path);
/* 423 */         Color.rgba8888ToColor(mesh.getColor(), color);
/* 424 */         mesh.setInheritDeform(inheritDeform);
/* 425 */         if (nonessential) {
/* 426 */           mesh.setWidth(width * scale);
/* 427 */           mesh.setHeight(height * scale);
/*     */         } 
/* 429 */         this.linkedMeshes.add(new SkeletonJson.LinkedMesh(mesh, skinName, slotIndex, parent));
/* 430 */         return (Attachment)mesh;
/*     */       
/*     */       case path:
/* 433 */         closed = input.readBoolean();
/* 434 */         constantSpeed = input.readBoolean();
/* 435 */         j = input.readInt(true);
/* 436 */         vertices1 = readVertices(input, j);
/* 437 */         lengths = new float[j / 3];
/* 438 */         for (i = 0, n = lengths.length; i < n; i++)
/* 439 */           lengths[i] = input.readFloat() * scale; 
/* 440 */         i1 = nonessential ? input.readInt() : 0;
/*     */         
/* 442 */         pathAttachment = this.attachmentLoader.newPathAttachment(skin, name);
/* 443 */         if (pathAttachment == null) return null; 
/* 444 */         pathAttachment.setClosed(closed);
/* 445 */         pathAttachment.setConstantSpeed(constantSpeed);
/* 446 */         pathAttachment.setWorldVerticesLength(j << 1);
/* 447 */         pathAttachment.setVertices(vertices1.vertices);
/* 448 */         pathAttachment.setBones(vertices1.bones);
/* 449 */         pathAttachment.setLengths(lengths);
/* 450 */         if (nonessential) Color.rgba8888ToColor(pathAttachment.getColor(), i1); 
/* 451 */         return (Attachment)pathAttachment;
/*     */     } 
/*     */     
/* 454 */     return null; }
/*     */ 
/*     */   
/*     */   private Vertices readVertices(DataInput input, int vertexCount) throws IOException {
/* 458 */     int verticesLength = vertexCount << 1;
/* 459 */     Vertices vertices = new Vertices();
/* 460 */     if (!input.readBoolean()) {
/* 461 */       vertices.vertices = readFloatArray(input, verticesLength, this.scale);
/* 462 */       return vertices;
/*     */     } 
/* 464 */     FloatArray weights = new FloatArray(verticesLength * 3 * 3);
/* 465 */     IntArray bonesArray = new IntArray(verticesLength * 3);
/* 466 */     for (int i = 0; i < vertexCount; i++) {
/* 467 */       int boneCount = input.readInt(true);
/* 468 */       bonesArray.add(boneCount);
/* 469 */       for (int ii = 0; ii < boneCount; ii++) {
/* 470 */         bonesArray.add(input.readInt(true));
/* 471 */         weights.add(input.readFloat() * this.scale);
/* 472 */         weights.add(input.readFloat() * this.scale);
/* 473 */         weights.add(input.readFloat());
/*     */       } 
/*     */     } 
/* 476 */     vertices.vertices = weights.toArray();
/* 477 */     vertices.bones = bonesArray.toArray();
/* 478 */     return vertices;
/*     */   }
/*     */   
/*     */   private float[] readFloatArray(DataInput input, int n, float scale) throws IOException {
/* 482 */     float[] array = new float[n];
/* 483 */     if (scale == 1.0F) {
/* 484 */       for (int i = 0; i < n; i++)
/* 485 */         array[i] = input.readFloat(); 
/*     */     } else {
/* 487 */       for (int i = 0; i < n; i++)
/* 488 */         array[i] = input.readFloat() * scale; 
/*     */     } 
/* 490 */     return array;
/*     */   }
/*     */   
/*     */   private short[] readShortArray(DataInput input) throws IOException {
/* 494 */     int n = input.readInt(true);
/* 495 */     short[] array = new short[n];
/* 496 */     for (int i = 0; i < n; i++)
/* 497 */       array[i] = input.readShort(); 
/* 498 */     return array;
/*     */   }
/*     */   
/*     */   private void readAnimation(String name, DataInput input, SkeletonData skeletonData) {
/* 502 */     Array<Animation.Timeline> timelines = new Array();
/* 503 */     float scale = this.scale;
/* 504 */     float duration = 0.0F;
/*     */     
/*     */     try {
/*     */       int i, n;
/* 508 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 509 */         int slotIndex = input.readInt(true);
/* 510 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++) {
/* 511 */           Animation.ColorTimeline colorTimeline; Animation.AttachmentTimeline timeline; int frameIndex, timelineType = input.readByte();
/* 512 */           int frameCount = input.readInt(true);
/* 513 */           switch (timelineType) {
/*     */             case 1:
/* 515 */               colorTimeline = new Animation.ColorTimeline(frameCount);
/* 516 */               colorTimeline.slotIndex = slotIndex;
/* 517 */               for (frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 518 */                 float time = input.readFloat();
/* 519 */                 Color.rgba8888ToColor(tempColor, input.readInt());
/* 520 */                 colorTimeline.setFrame(frameIndex, time, tempColor.r, tempColor.g, tempColor.b, tempColor.a);
/* 521 */                 if (frameIndex < frameCount - 1) readCurve(input, frameIndex, colorTimeline); 
/*     */               } 
/* 523 */               timelines.add(colorTimeline);
/* 524 */               duration = Math.max(duration, colorTimeline.getFrames()[(frameCount - 1) * 5]);
/*     */               break;
/*     */             
/*     */             case 0:
/* 528 */               timeline = new Animation.AttachmentTimeline(frameCount);
/* 529 */               timeline.slotIndex = slotIndex;
/* 530 */               for (frameIndex = 0; frameIndex < frameCount; frameIndex++)
/* 531 */                 timeline.setFrame(frameIndex, input.readFloat(), input.readString()); 
/* 532 */               timelines.add(timeline);
/* 533 */               duration = Math.max(duration, timeline.getFrames()[frameCount - 1]);
/*     */               break;
/*     */           } 
/*     */ 
/*     */         
/*     */         } 
/*     */       } 
/* 540 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 541 */         int boneIndex = input.readInt(true);
/* 542 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++) {
/* 543 */           Animation.RotateTimeline rotateTimeline; Animation.TranslateTimeline timeline; int frameIndex; float timelineScale; int j, timelineType = input.readByte();
/* 544 */           int frameCount = input.readInt(true);
/* 545 */           switch (timelineType) {
/*     */             case 0:
/* 547 */               rotateTimeline = new Animation.RotateTimeline(frameCount);
/* 548 */               rotateTimeline.boneIndex = boneIndex;
/* 549 */               for (frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 550 */                 rotateTimeline.setFrame(frameIndex, input.readFloat(), input.readFloat());
/* 551 */                 if (frameIndex < frameCount - 1) readCurve(input, frameIndex, rotateTimeline); 
/*     */               } 
/* 553 */               timelines.add(rotateTimeline);
/* 554 */               duration = Math.max(duration, rotateTimeline.getFrames()[(frameCount - 1) * 2]);
/*     */               break;
/*     */ 
/*     */             
/*     */             case 1:
/*     */             case 2:
/*     */             case 3:
/* 561 */               timelineScale = 1.0F;
/* 562 */               if (timelineType == 2) {
/* 563 */                 timeline = new Animation.ScaleTimeline(frameCount);
/* 564 */               } else if (timelineType == 3) {
/* 565 */                 timeline = new Animation.ShearTimeline(frameCount);
/*     */               } else {
/* 567 */                 timeline = new Animation.TranslateTimeline(frameCount);
/* 568 */                 timelineScale = scale;
/*     */               } 
/* 570 */               timeline.boneIndex = boneIndex;
/* 571 */               for (j = 0; j < frameCount; j++) {
/* 572 */                 timeline.setFrame(j, input.readFloat(), input.readFloat() * timelineScale, input
/* 573 */                     .readFloat() * timelineScale);
/* 574 */                 if (j < frameCount - 1) readCurve(input, j, timeline); 
/*     */               } 
/* 576 */               timelines.add(timeline);
/* 577 */               duration = Math.max(duration, timeline.getFrames()[(frameCount - 1) * 3]);
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/*     */       } 
/* 585 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 586 */         int index = input.readInt(true);
/* 587 */         int frameCount = input.readInt(true);
/* 588 */         Animation.IkConstraintTimeline timeline = new Animation.IkConstraintTimeline(frameCount);
/* 589 */         timeline.ikConstraintIndex = index;
/* 590 */         for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 591 */           timeline.setFrame(frameIndex, input.readFloat(), input.readFloat(), input.readByte());
/* 592 */           if (frameIndex < frameCount - 1) readCurve(input, frameIndex, timeline); 
/*     */         } 
/* 594 */         timelines.add(timeline);
/* 595 */         duration = Math.max(duration, timeline.getFrames()[(frameCount - 1) * 3]);
/*     */       } 
/*     */ 
/*     */       
/* 599 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 600 */         int index = input.readInt(true);
/* 601 */         int frameCount = input.readInt(true);
/* 602 */         Animation.TransformConstraintTimeline timeline = new Animation.TransformConstraintTimeline(frameCount);
/* 603 */         timeline.transformConstraintIndex = index;
/* 604 */         for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 605 */           timeline.setFrame(frameIndex, input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat(), input
/* 606 */               .readFloat());
/* 607 */           if (frameIndex < frameCount - 1) readCurve(input, frameIndex, timeline); 
/*     */         } 
/* 609 */         timelines.add(timeline);
/* 610 */         duration = Math.max(duration, timeline.getFrames()[(frameCount - 1) * 5]);
/*     */       } 
/*     */ 
/*     */       
/* 614 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 615 */         int index = input.readInt(true);
/* 616 */         PathConstraintData data = (PathConstraintData)skeletonData.getPathConstraints().get(index);
/* 617 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++) {
/* 618 */           Animation.PathConstraintPositionTimeline pathConstraintPositionTimeline; Animation.PathConstraintMixTimeline timeline; float timelineScale; int frameIndex, j, timelineType = input.readByte();
/* 619 */           int frameCount = input.readInt(true);
/* 620 */           switch (timelineType) {
/*     */             
/*     */             case 0:
/*     */             case 1:
/* 624 */               timelineScale = 1.0F;
/* 625 */               if (timelineType == 1) {
/* 626 */                 pathConstraintPositionTimeline = new Animation.PathConstraintSpacingTimeline(frameCount);
/* 627 */                 if (data.spacingMode == PathConstraintData.SpacingMode.length || data.spacingMode == PathConstraintData.SpacingMode.fixed) timelineScale = scale; 
/*     */               } else {
/* 629 */                 pathConstraintPositionTimeline = new Animation.PathConstraintPositionTimeline(frameCount);
/* 630 */                 if (data.positionMode == PathConstraintData.PositionMode.fixed) timelineScale = scale; 
/*     */               } 
/* 632 */               pathConstraintPositionTimeline.pathConstraintIndex = index;
/* 633 */               for (j = 0; j < frameCount; j++) {
/* 634 */                 pathConstraintPositionTimeline.setFrame(j, input.readFloat(), input.readFloat() * timelineScale);
/* 635 */                 if (j < frameCount - 1) readCurve(input, j, pathConstraintPositionTimeline); 
/*     */               } 
/* 637 */               timelines.add(pathConstraintPositionTimeline);
/* 638 */               duration = Math.max(duration, pathConstraintPositionTimeline.getFrames()[(frameCount - 1) * 2]);
/*     */               break;
/*     */             
/*     */             case 2:
/* 642 */               timeline = new Animation.PathConstraintMixTimeline(frameCount);
/* 643 */               timeline.pathConstraintIndex = index;
/* 644 */               for (frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 645 */                 timeline.setFrame(frameIndex, input.readFloat(), input.readFloat(), input.readFloat());
/* 646 */                 if (frameIndex < frameCount - 1) readCurve(input, frameIndex, timeline); 
/*     */               } 
/* 648 */               timelines.add(timeline);
/* 649 */               duration = Math.max(duration, timeline.getFrames()[(frameCount - 1) * 3]);
/*     */               break;
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/*     */       } 
/* 657 */       for (i = 0, n = input.readInt(true); i < n; i++) {
/* 658 */         Skin skin = (Skin)skeletonData.skins.get(input.readInt(true));
/* 659 */         for (int ii = 0, nn = input.readInt(true); ii < nn; ii++) {
/* 660 */           int slotIndex = input.readInt(true);
/* 661 */           for (int iii = 0, nnn = input.readInt(true); iii < nnn; iii++) {
/* 662 */             VertexAttachment attachment = (VertexAttachment)skin.getAttachment(slotIndex, input.readString());
/* 663 */             boolean weighted = (attachment.getBones() != null);
/* 664 */             float[] vertices = attachment.getVertices();
/* 665 */             int deformLength = weighted ? (vertices.length / 3 * 2) : vertices.length;
/*     */             
/* 667 */             int frameCount = input.readInt(true);
/* 668 */             Animation.DeformTimeline timeline = new Animation.DeformTimeline(frameCount);
/* 669 */             timeline.slotIndex = slotIndex;
/* 670 */             timeline.attachment = attachment;
/*     */             
/* 672 */             for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
/* 673 */               float deform[], time = input.readFloat();
/*     */               
/* 675 */               int end = input.readInt(true);
/* 676 */               if (end == 0) {
/* 677 */                 deform = weighted ? new float[deformLength] : vertices;
/*     */               } else {
/* 679 */                 deform = new float[deformLength];
/* 680 */                 int start = input.readInt(true);
/* 681 */                 end += start;
/* 682 */                 if (scale == 1.0F) {
/* 683 */                   for (int v = start; v < end; v++)
/* 684 */                     deform[v] = input.readFloat(); 
/*     */                 } else {
/* 686 */                   for (int v = start; v < end; v++)
/* 687 */                     deform[v] = input.readFloat() * scale; 
/*     */                 } 
/* 689 */                 if (!weighted) {
/* 690 */                   for (int v = 0, vn = deform.length; v < vn; v++) {
/* 691 */                     deform[v] = deform[v] + vertices[v];
/*     */                   }
/*     */                 }
/*     */               } 
/* 695 */               timeline.setFrame(frameIndex, time, deform);
/* 696 */               if (frameIndex < frameCount - 1) readCurve(input, frameIndex, timeline); 
/*     */             } 
/* 698 */             timelines.add(timeline);
/* 699 */             duration = Math.max(duration, timeline.getFrames()[frameCount - 1]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 705 */       int drawOrderCount = input.readInt(true);
/* 706 */       if (drawOrderCount > 0) {
/* 707 */         Animation.DrawOrderTimeline timeline = new Animation.DrawOrderTimeline(drawOrderCount);
/* 708 */         int slotCount = skeletonData.slots.size;
/* 709 */         for (int j = 0; j < drawOrderCount; j++) {
/* 710 */           float time = input.readFloat();
/* 711 */           int offsetCount = input.readInt(true);
/* 712 */           int[] drawOrder = new int[slotCount];
/* 713 */           for (int ii = slotCount - 1; ii >= 0; ii--)
/* 714 */             drawOrder[ii] = -1; 
/* 715 */           int[] unchanged = new int[slotCount - offsetCount];
/* 716 */           int originalIndex = 0, unchangedIndex = 0; int k;
/* 717 */           for (k = 0; k < offsetCount; k++) {
/* 718 */             int slotIndex = input.readInt(true);
/*     */             
/* 720 */             while (originalIndex != slotIndex) {
/* 721 */               unchanged[unchangedIndex++] = originalIndex++;
/*     */             }
/* 723 */             drawOrder[originalIndex + input.readInt(true)] = originalIndex++;
/*     */           } 
/*     */           
/* 726 */           while (originalIndex < slotCount) {
/* 727 */             unchanged[unchangedIndex++] = originalIndex++;
/*     */           }
/* 729 */           for (k = slotCount - 1; k >= 0; k--) {
/* 730 */             if (drawOrder[k] == -1) drawOrder[k] = unchanged[--unchangedIndex]; 
/* 731 */           }  timeline.setFrame(j, time, drawOrder);
/*     */         } 
/* 733 */         timelines.add(timeline);
/* 734 */         duration = Math.max(duration, timeline.getFrames()[drawOrderCount - 1]);
/*     */       } 
/*     */ 
/*     */       
/* 738 */       int eventCount = input.readInt(true);
/* 739 */       if (eventCount > 0) {
/* 740 */         Animation.EventTimeline timeline = new Animation.EventTimeline(eventCount);
/* 741 */         for (int j = 0; j < eventCount; j++) {
/* 742 */           float time = input.readFloat();
/* 743 */           EventData eventData = (EventData)skeletonData.events.get(input.readInt(true));
/* 744 */           Event event = new Event(time, eventData);
/* 745 */           event.intValue = input.readInt(false);
/* 746 */           event.floatValue = input.readFloat();
/* 747 */           event.stringValue = input.readBoolean() ? input.readString() : eventData.stringValue;
/* 748 */           timeline.setFrame(j, event);
/*     */         } 
/* 750 */         timelines.add(timeline);
/* 751 */         duration = Math.max(duration, timeline.getFrames()[eventCount - 1]);
/*     */       } 
/* 753 */     } catch (IOException ex) {
/* 754 */       throw new SerializationException("Error reading skeleton file.", ex);
/*     */     } 
/*     */     
/* 757 */     timelines.shrink();
/* 758 */     skeletonData.animations.add(new Animation(name, timelines, duration));
/*     */   }
/*     */ 
/*     */   
/*     */   private void readCurve(DataInput input, int frameIndex, Animation.CurveTimeline timeline) throws IOException {
/* 763 */     switch (input.readByte()) {
/*     */       case 1:
/* 765 */         timeline.setStepped(frameIndex);
/*     */         break;
/*     */       case 2:
/* 768 */         setCurve(timeline, frameIndex, input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   void setCurve(Animation.CurveTimeline timeline, int frameIndex, float cx1, float cy1, float cx2, float cy2) {
/* 774 */     timeline.setCurve(frameIndex, cx1, cy1, cx2, cy2);
/*     */   }
/*     */   
/*     */   static class Vertices {
/*     */     int[] bones;
/*     */     float[] vertices;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonBinary.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */