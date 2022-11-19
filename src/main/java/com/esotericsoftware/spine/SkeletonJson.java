/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.*;
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
/*     */ public class SkeletonJson
/*     */ {
/*     */   private final AttachmentLoader attachmentLoader;
/*  74 */   private float scale = 1.0F;
/*  75 */   private Array<LinkedMesh> linkedMeshes = new Array();
/*     */   
/*     */   public SkeletonJson(TextureAtlas atlas) {
/*  78 */     this.attachmentLoader = (AttachmentLoader)new AtlasAttachmentLoader(atlas);
/*     */   }
/*     */   
/*     */   public SkeletonJson(AttachmentLoader attachmentLoader) {
/*  82 */     if (attachmentLoader == null) throw new IllegalArgumentException("attachmentLoader cannot be null."); 
/*  83 */     this.attachmentLoader = attachmentLoader;
/*     */   }
/*     */   
/*     */   public float getScale() {
/*  87 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScale(float scale) {
/*  92 */     this.scale = scale;
/*     */   }
/*     */   
/*     */   public SkeletonData readSkeletonData(FileHandle file) {
/*  96 */     if (file == null) throw new IllegalArgumentException("file cannot be null.");
/*     */     
/*  98 */     float scale = this.scale;
/*     */     
/* 100 */     SkeletonData skeletonData = new SkeletonData();
/* 101 */     skeletonData.name = file.nameWithoutExtension();
/*     */     
/* 103 */     JsonValue root = (new JsonReader()).parse(file);
/*     */ 
/*     */     
/* 106 */     JsonValue skeletonMap = root.get("skeleton");
/* 107 */     if (skeletonMap != null) {
/* 108 */       skeletonData.hash = skeletonMap.getString("hash", null);
/* 109 */       skeletonData.version = skeletonMap.getString("spine", null);
/* 110 */       skeletonData.width = skeletonMap.getFloat("width", 0.0F);
/* 111 */       skeletonData.height = skeletonMap.getFloat("height", 0.0F);
/* 112 */       skeletonData.imagesPath = skeletonMap.getString("images", null);
/*     */     } 
/*     */ 
/*     */     
/* 116 */     for (JsonValue boneMap = root.getChild("bones"); boneMap != null; boneMap = boneMap.next) {
/* 117 */       BoneData parent = null;
/* 118 */       String parentName = boneMap.getString("parent", null);
/* 119 */       if (parentName != null) {
/* 120 */         parent = skeletonData.findBone(parentName);
/* 121 */         if (parent == null) throw new SerializationException("Parent bone not found: " + parentName); 
/*     */       } 
/* 123 */       BoneData data = new BoneData(skeletonData.bones.size, boneMap.getString("name"), parent);
/* 124 */       data.length = boneMap.getFloat("length", 0.0F) * scale;
/* 125 */       data.x = boneMap.getFloat("x", 0.0F) * scale;
/* 126 */       data.y = boneMap.getFloat("y", 0.0F) * scale;
/* 127 */       data.rotation = boneMap.getFloat("rotation", 0.0F);
/* 128 */       data.scaleX = boneMap.getFloat("scaleX", 1.0F);
/* 129 */       data.scaleY = boneMap.getFloat("scaleY", 1.0F);
/* 130 */       data.shearX = boneMap.getFloat("shearX", 0.0F);
/* 131 */       data.shearY = boneMap.getFloat("shearY", 0.0F);
/* 132 */       data.inheritRotation = boneMap.getBoolean("inheritRotation", true);
/* 133 */       data.inheritScale = boneMap.getBoolean("inheritScale", true);
/*     */       
/* 135 */       String color = boneMap.getString("color", null);
/* 136 */       if (color != null) data.getColor().set(Color.valueOf(color));
/*     */       
/* 138 */       skeletonData.bones.add(data);
/*     */     } 
/*     */ 
/*     */     
/* 142 */     for (JsonValue slotMap = root.getChild("slots"); slotMap != null; slotMap = slotMap.next) {
/* 143 */       String slotName = slotMap.getString("name");
/* 144 */       String boneName = slotMap.getString("bone");
/* 145 */       BoneData boneData = skeletonData.findBone(boneName);
/* 146 */       if (boneData == null) throw new SerializationException("Slot bone not found: " + boneName); 
/* 147 */       SlotData data = new SlotData(skeletonData.slots.size, slotName, boneData);
/*     */       
/* 149 */       String color = slotMap.getString("color", null);
/* 150 */       if (color != null) data.getColor().set(Color.valueOf(color));
/*     */       
/* 152 */       data.attachmentName = slotMap.getString("attachment", null);
/* 153 */       data.blendMode = BlendMode.valueOf(slotMap.getString("blend", BlendMode.normal.name()));
/* 154 */       skeletonData.slots.add(data);
/*     */     } 
/*     */     
/*     */     JsonValue constraintMap;
/* 158 */     for (constraintMap = root.getChild("ik"); constraintMap != null; constraintMap = constraintMap.next) {
/* 159 */       IkConstraintData data = new IkConstraintData(constraintMap.getString("name"));
/*     */       
/* 161 */       for (JsonValue jsonValue = constraintMap.getChild("bones"); jsonValue != null; jsonValue = jsonValue.next) {
/* 162 */         String boneName = jsonValue.asString();
/* 163 */         BoneData bone = skeletonData.findBone(boneName);
/* 164 */         if (bone == null) throw new SerializationException("IK bone not found: " + boneName); 
/* 165 */         data.bones.add(bone);
/*     */       } 
/*     */       
/* 168 */       String targetName = constraintMap.getString("target");
/* 169 */       data.target = skeletonData.findBone(targetName);
/* 170 */       if (data.target == null) throw new SerializationException("IK target bone not found: " + targetName);
/*     */       
/* 172 */       data.bendDirection = constraintMap.getBoolean("bendPositive", true) ? 1 : -1;
/* 173 */       data.mix = constraintMap.getFloat("mix", 1.0F);
/*     */       
/* 175 */       skeletonData.ikConstraints.add(data);
/*     */     } 
/*     */ 
/*     */     
/* 179 */     for (constraintMap = root.getChild("transform"); constraintMap != null; constraintMap = constraintMap.next) {
/* 180 */       TransformConstraintData data = new TransformConstraintData(constraintMap.getString("name"));
/*     */       
/* 182 */       for (JsonValue jsonValue = constraintMap.getChild("bones"); jsonValue != null; jsonValue = jsonValue.next) {
/* 183 */         String boneName = jsonValue.asString();
/* 184 */         BoneData bone = skeletonData.findBone(boneName);
/* 185 */         if (bone == null) throw new SerializationException("Transform constraint bone not found: " + boneName); 
/* 186 */         data.bones.add(bone);
/*     */       } 
/*     */       
/* 189 */       String targetName = constraintMap.getString("target");
/* 190 */       data.target = skeletonData.findBone(targetName);
/* 191 */       if (data.target == null) throw new SerializationException("Transform constraint target bone not found: " + targetName);
/*     */       
/* 193 */       data.offsetRotation = constraintMap.getFloat("rotation", 0.0F);
/* 194 */       data.offsetX = constraintMap.getFloat("x", 0.0F) * scale;
/* 195 */       data.offsetY = constraintMap.getFloat("y", 0.0F) * scale;
/* 196 */       data.offsetScaleX = constraintMap.getFloat("scaleX", 0.0F);
/* 197 */       data.offsetScaleY = constraintMap.getFloat("scaleY", 0.0F);
/* 198 */       data.offsetShearY = constraintMap.getFloat("shearY", 0.0F);
/*     */       
/* 200 */       data.rotateMix = constraintMap.getFloat("rotateMix", 1.0F);
/* 201 */       data.translateMix = constraintMap.getFloat("translateMix", 1.0F);
/* 202 */       data.scaleMix = constraintMap.getFloat("scaleMix", 1.0F);
/* 203 */       data.shearMix = constraintMap.getFloat("shearMix", 1.0F);
/*     */       
/* 205 */       skeletonData.transformConstraints.add(data);
/*     */     } 
/*     */ 
/*     */     
/* 209 */     for (constraintMap = root.getChild("path"); constraintMap != null; constraintMap = constraintMap.next) {
/* 210 */       PathConstraintData data = new PathConstraintData(constraintMap.getString("name"));
/*     */       
/* 212 */       for (JsonValue jsonValue = constraintMap.getChild("bones"); jsonValue != null; jsonValue = jsonValue.next) {
/* 213 */         String boneName = jsonValue.asString();
/* 214 */         BoneData bone = skeletonData.findBone(boneName);
/* 215 */         if (bone == null) throw new SerializationException("Path bone not found: " + boneName); 
/* 216 */         data.bones.add(bone);
/*     */       } 
/*     */       
/* 219 */       String targetName = constraintMap.getString("target");
/* 220 */       data.target = skeletonData.findSlot(targetName);
/* 221 */       if (data.target == null) throw new SerializationException("Path target slot not found: " + targetName);
/*     */       
/* 223 */       data.positionMode = PathConstraintData.PositionMode.valueOf(constraintMap.getString("positionMode", "percent"));
/* 224 */       data.spacingMode = PathConstraintData.SpacingMode.valueOf(constraintMap.getString("spacingMode", "length"));
/* 225 */       data.rotateMode = PathConstraintData.RotateMode.valueOf(constraintMap.getString("rotateMode", "tangent"));
/* 226 */       data.offsetRotation = constraintMap.getFloat("rotation", 0.0F);
/* 227 */       data.position = constraintMap.getFloat("position", 0.0F);
/* 228 */       if (data.positionMode == PathConstraintData.PositionMode.fixed) data.position *= scale; 
/* 229 */       data.spacing = constraintMap.getFloat("spacing", 0.0F);
/* 230 */       if (data.spacingMode == PathConstraintData.SpacingMode.length || data.spacingMode == PathConstraintData.SpacingMode.fixed) data.spacing *= scale; 
/* 231 */       data.rotateMix = constraintMap.getFloat("rotateMix", 1.0F);
/* 232 */       data.translateMix = constraintMap.getFloat("translateMix", 1.0F);
/*     */       
/* 234 */       skeletonData.pathConstraints.add(data);
/*     */     } 
/*     */ 
/*     */     
/* 238 */     for (JsonValue skinMap = root.getChild("skins"); skinMap != null; skinMap = skinMap.next) {
/* 239 */       Skin skin = new Skin(skinMap.name);
/* 240 */       for (JsonValue slotEntry = skinMap.child; slotEntry != null; slotEntry = slotEntry.next) {
/* 241 */         int slotIndex = skeletonData.findSlotIndex(slotEntry.name);
/* 242 */         if (slotIndex == -1) throw new SerializationException("Slot not found: " + slotEntry.name); 
/* 243 */         for (JsonValue entry = slotEntry.child; entry != null; entry = entry.next) {
/*     */           try {
/* 245 */             Attachment attachment = readAttachment(entry, skin, slotIndex, entry.name);
/* 246 */             if (attachment != null) skin.addAttachment(slotIndex, entry.name, attachment); 
/* 247 */           } catch (Exception ex) {
/* 248 */             throw new SerializationException("Error reading attachment: " + entry.name + ", skin: " + skin, ex);
/*     */           } 
/*     */         } 
/*     */       } 
/* 252 */       skeletonData.skins.add(skin);
/* 253 */       if (skin.name.equals("default")) skeletonData.defaultSkin = skin;
/*     */     
/*     */     } 
/*     */     
/* 257 */     for (int i = 0, n = this.linkedMeshes.size; i < n; i++) {
/* 258 */       LinkedMesh linkedMesh = (LinkedMesh)this.linkedMeshes.get(i);
/* 259 */       Skin skin = (linkedMesh.skin == null) ? skeletonData.getDefaultSkin() : skeletonData.findSkin(linkedMesh.skin);
/* 260 */       if (skin == null) throw new SerializationException("Skin not found: " + linkedMesh.skin); 
/* 261 */       Attachment parent = skin.getAttachment(linkedMesh.slotIndex, linkedMesh.parent);
/* 262 */       if (parent == null) throw new SerializationException("Parent mesh not found: " + linkedMesh.parent); 
/* 263 */       linkedMesh.mesh.setParentMesh((MeshAttachment)parent);
/* 264 */       linkedMesh.mesh.updateUVs();
/*     */     } 
/* 266 */     this.linkedMeshes.clear();
/*     */ 
/*     */     
/* 269 */     for (JsonValue eventMap = root.getChild("events"); eventMap != null; eventMap = eventMap.next) {
/* 270 */       EventData data = new EventData(eventMap.name);
/* 271 */       data.intValue = eventMap.getInt("int", 0);
/* 272 */       data.floatValue = eventMap.getFloat("float", 0.0F);
/* 273 */       data.stringValue = eventMap.getString("string", null);
/* 274 */       skeletonData.events.add(data);
/*     */     } 
/*     */ 
/*     */     
/* 278 */     for (JsonValue animationMap = root.getChild("animations"); animationMap != null; animationMap = animationMap.next) {
/*     */       try {
/* 280 */         readAnimation(animationMap, animationMap.name, skeletonData);
/* 281 */       } catch (Exception ex) {
/* 282 */         throw new SerializationException("Error reading animation: " + animationMap.name, ex);
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     skeletonData.bones.shrink();
/* 287 */     skeletonData.slots.shrink();
/* 288 */     skeletonData.skins.shrink();
/* 289 */     skeletonData.events.shrink();
/* 290 */     skeletonData.animations.shrink();
/* 291 */     skeletonData.ikConstraints.shrink();
/* 292 */     return skeletonData; } private Attachment readAttachment(JsonValue map, Skin skin, int slotIndex, String name) { String str2; BoundingBoxAttachment box; String str1; PathAttachment path; RegionAttachment region; String color; MeshAttachment mesh; int vertexCount; String str3; float[] lengths; String parent; int i;
/*     */     float[] uvs;
/*     */     JsonValue curves;
/*     */     String str4;
/* 296 */     float scale = this.scale;
/* 297 */     name = map.getString("name", name);
/*     */     
/* 299 */     String type = map.getString("type", AttachmentType.region.name());
/*     */     
/* 301 */     switch (AttachmentType.valueOf(type)) {
/*     */       case region:
/* 303 */         str2 = map.getString("path", name);
/* 304 */         region = this.attachmentLoader.newRegionAttachment(skin, name, str2);
/* 305 */         if (region == null) return null; 
/* 306 */         region.setPath(str2);
/* 307 */         region.setX(map.getFloat("x", 0.0F) * scale);
/* 308 */         region.setY(map.getFloat("y", 0.0F) * scale);
/* 309 */         region.setScaleX(map.getFloat("scaleX", 1.0F));
/* 310 */         region.setScaleY(map.getFloat("scaleY", 1.0F));
/* 311 */         region.setRotation(map.getFloat("rotation", 0.0F));
/* 312 */         region.setWidth(map.getFloat("width") * scale);
/* 313 */         region.setHeight(map.getFloat("height") * scale);
/*     */         
/* 315 */         str3 = map.getString("color", null);
/* 316 */         if (str3 != null) region.getColor().set(Color.valueOf(str3));
/*     */         
/* 318 */         region.updateOffset();
/* 319 */         return (Attachment)region;
/*     */       
/*     */       case boundingbox:
/* 322 */         box = this.attachmentLoader.newBoundingBoxAttachment(skin, name);
/* 323 */         if (box == null) return null; 
/* 324 */         readVertices(map, (VertexAttachment)box, map.getInt("vertexCount") << 1);
/*     */         
/* 326 */         color = map.getString("color", null);
/* 327 */         if (color != null) box.getColor().set(Color.valueOf(color)); 
/* 328 */         return (Attachment)box;
/*     */       
/*     */       case mesh:
/*     */       case linkedmesh:
/* 332 */         str1 = map.getString("path", name);
/* 333 */         mesh = this.attachmentLoader.newMeshAttachment(skin, name, str1);
/* 334 */         if (mesh == null) return null; 
/* 335 */         mesh.setPath(str1);
/*     */         
/* 337 */         str3 = map.getString("color", null);
/* 338 */         if (str3 != null) mesh.getColor().set(Color.valueOf(str3));
/*     */         
/* 340 */         mesh.setWidth(map.getFloat("width", 0.0F) * scale);
/* 341 */         mesh.setHeight(map.getFloat("height", 0.0F) * scale);
/*     */         
/* 343 */         parent = map.getString("parent", null);
/* 344 */         if (parent != null) {
/* 345 */           mesh.setInheritDeform(map.getBoolean("deform", true));
/* 346 */           this.linkedMeshes.add(new LinkedMesh(mesh, map.getString("skin", null), slotIndex, parent));
/* 347 */           return (Attachment)mesh;
/*     */         } 
/*     */         
/* 350 */         uvs = map.require("uvs").asFloatArray();
/* 351 */         readVertices(map, (VertexAttachment)mesh, uvs.length);
/* 352 */         mesh.setTriangles(map.require("triangles").asShortArray());
/* 353 */         mesh.setRegionUVs(uvs);
/* 354 */         mesh.updateUVs();
/*     */         
/* 356 */         if (map.has("hull")) mesh.setHullLength(map.require("hull").asInt() * 2); 
/* 357 */         if (map.has("edges")) mesh.setEdges(map.require("edges").asShortArray()); 
/* 358 */         return (Attachment)mesh;
/*     */       
/*     */       case path:
/* 361 */         path = this.attachmentLoader.newPathAttachment(skin, name);
/* 362 */         if (path == null) return null; 
/* 363 */         path.setClosed(map.getBoolean("closed", false));
/* 364 */         path.setConstantSpeed(map.getBoolean("constantSpeed", true));
/*     */         
/* 366 */         vertexCount = map.getInt("vertexCount");
/* 367 */         readVertices(map, (VertexAttachment)path, vertexCount << 1);
/*     */         
/* 369 */         lengths = new float[vertexCount / 3];
/* 370 */         i = 0;
/* 371 */         for (curves = (map.require("lengths")).child; curves != null; curves = curves.next)
/* 372 */           lengths[i++] = curves.asFloat() * scale; 
/* 373 */         path.setLengths(lengths);
/*     */         
/* 375 */         str4 = map.getString("color", null);
/* 376 */         if (str4 != null) path.getColor().set(Color.valueOf(str4)); 
/* 377 */         return (Attachment)path;
/*     */     } 
/*     */     
/* 380 */     return null; }
/*     */ 
/*     */   
/*     */   private void readVertices(JsonValue map, VertexAttachment attachment, int verticesLength) {
/* 384 */     attachment.setWorldVerticesLength(verticesLength);
/* 385 */     float[] vertices = map.require("vertices").asFloatArray();
/* 386 */     if (verticesLength == vertices.length) {
/* 387 */       if (this.scale != 1.0F)
/* 388 */         for (int j = 0, k = vertices.length; j < k; j++) {
/* 389 */           vertices[j] = vertices[j] * this.scale;
/*     */         } 
/* 391 */       attachment.setVertices(vertices);
/*     */       return;
/*     */     } 
/* 394 */     FloatArray weights = new FloatArray(verticesLength * 3 * 3);
/* 395 */     IntArray bones = new IntArray(verticesLength * 3);
/* 396 */     for (int i = 0, n = vertices.length; i < n; ) {
/* 397 */       int boneCount = (int)vertices[i++];
/* 398 */       bones.add(boneCount);
/* 399 */       for (int nn = i + boneCount * 4; i < nn; i += 4) {
/* 400 */         bones.add((int)vertices[i]);
/* 401 */         weights.add(vertices[i + 1] * this.scale);
/* 402 */         weights.add(vertices[i + 2] * this.scale);
/* 403 */         weights.add(vertices[i + 3]);
/*     */       } 
/*     */     } 
/* 406 */     attachment.setBones(bones.toArray());
/* 407 */     attachment.setVertices(weights.toArray());
/*     */   }
/*     */   
/*     */   private void readAnimation(JsonValue map, String name, SkeletonData skeletonData) {
/* 411 */     float scale = this.scale;
/* 412 */     Array<Animation.Timeline> timelines = new Array();
/* 413 */     float duration = 0.0F;
/*     */ 
/*     */     
/* 416 */     for (JsonValue slotMap = map.getChild("slots"); slotMap != null; slotMap = slotMap.next) {
/* 417 */       int slotIndex = skeletonData.findSlotIndex(slotMap.name);
/* 418 */       if (slotIndex == -1) throw new SerializationException("Slot not found: " + slotMap.name); 
/* 419 */       for (JsonValue timelineMap = slotMap.child; timelineMap != null; timelineMap = timelineMap.next) {
/* 420 */         String timelineName = timelineMap.name;
/* 421 */         if (timelineName.equals("color")) {
/* 422 */           Animation.ColorTimeline timeline = new Animation.ColorTimeline(timelineMap.size);
/* 423 */           timeline.slotIndex = slotIndex;
/*     */           
/* 425 */           int frameIndex = 0;
/* 426 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/* 427 */             Color color = Color.valueOf(valueMap.getString("color"));
/* 428 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), color.r, color.g, color.b, color.a);
/* 429 */             readCurve(valueMap, timeline, frameIndex);
/* 430 */             frameIndex++;
/*     */           } 
/* 432 */           timelines.add(timeline);
/* 433 */           duration = Math.max(duration, timeline.getFrames()[(timeline.getFrameCount() - 1) * 5]);
/*     */         }
/* 435 */         else if (timelineName.equals("attachment")) {
/* 436 */           Animation.AttachmentTimeline timeline = new Animation.AttachmentTimeline(timelineMap.size);
/* 437 */           timeline.slotIndex = slotIndex;
/*     */           
/* 439 */           int frameIndex = 0;
/* 440 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next)
/* 441 */             timeline.setFrame(frameIndex++, valueMap.getFloat("time"), valueMap.getString("name")); 
/* 442 */           timelines.add(timeline);
/* 443 */           duration = Math.max(duration, timeline.getFrames()[timeline.getFrameCount() - 1]);
/*     */         } else {
/* 445 */           throw new RuntimeException("Invalid timeline type for a slot: " + timelineName + " (" + slotMap.name + ")");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 450 */     for (JsonValue boneMap = map.getChild("bones"); boneMap != null; boneMap = boneMap.next) {
/* 451 */       int boneIndex = skeletonData.findBoneIndex(boneMap.name);
/* 452 */       if (boneIndex == -1) throw new SerializationException("Bone not found: " + boneMap.name); 
/* 453 */       for (JsonValue timelineMap = boneMap.child; timelineMap != null; timelineMap = timelineMap.next) {
/* 454 */         String timelineName = timelineMap.name;
/* 455 */         if (timelineName.equals("rotate")) {
/* 456 */           Animation.RotateTimeline timeline = new Animation.RotateTimeline(timelineMap.size);
/* 457 */           timeline.boneIndex = boneIndex;
/*     */           
/* 459 */           int frameIndex = 0;
/* 460 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/* 461 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), valueMap.getFloat("angle"));
/* 462 */             readCurve(valueMap, timeline, frameIndex);
/* 463 */             frameIndex++;
/*     */           } 
/* 465 */           timelines.add(timeline);
/* 466 */           duration = Math.max(duration, timeline.getFrames()[(timeline.getFrameCount() - 1) * 2]);
/*     */         }
/* 468 */         else if (timelineName.equals("translate") || timelineName.equals("scale") || timelineName.equals("shear")) {
/*     */           Animation.TranslateTimeline timeline;
/* 470 */           float timelineScale = 1.0F;
/* 471 */           if (timelineName.equals("scale")) {
/* 472 */             timeline = new Animation.ScaleTimeline(timelineMap.size);
/* 473 */           } else if (timelineName.equals("shear")) {
/* 474 */             timeline = new Animation.ShearTimeline(timelineMap.size);
/*     */           } else {
/* 476 */             timeline = new Animation.TranslateTimeline(timelineMap.size);
/* 477 */             timelineScale = scale;
/*     */           } 
/* 479 */           timeline.boneIndex = boneIndex;
/*     */           
/* 481 */           int frameIndex = 0;
/* 482 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/* 483 */             float x = valueMap.getFloat("x", 0.0F), y = valueMap.getFloat("y", 0.0F);
/* 484 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), x * timelineScale, y * timelineScale);
/* 485 */             readCurve(valueMap, timeline, frameIndex);
/* 486 */             frameIndex++;
/*     */           } 
/* 488 */           timelines.add(timeline);
/* 489 */           duration = Math.max(duration, timeline.getFrames()[(timeline.getFrameCount() - 1) * 3]);
/*     */         } else {
/*     */           
/* 492 */           throw new RuntimeException("Invalid timeline type for a bone: " + timelineName + " (" + boneMap.name + ")");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     JsonValue constraintMap;
/* 497 */     for (constraintMap = map.getChild("ik"); constraintMap != null; constraintMap = constraintMap.next) {
/* 498 */       IkConstraintData constraint = skeletonData.findIkConstraint(constraintMap.name);
/* 499 */       Animation.IkConstraintTimeline timeline = new Animation.IkConstraintTimeline(constraintMap.size);
/* 500 */       timeline.ikConstraintIndex = skeletonData.getIkConstraints().indexOf(constraint, true);
/* 501 */       int frameIndex = 0;
/* 502 */       for (JsonValue valueMap = constraintMap.child; valueMap != null; valueMap = valueMap.next) {
/* 503 */         timeline.setFrame(frameIndex, valueMap.getFloat("time"), valueMap.getFloat("mix", 1.0F), 
/* 504 */             valueMap.getBoolean("bendPositive", true) ? 1 : -1);
/* 505 */         readCurve(valueMap, timeline, frameIndex);
/* 506 */         frameIndex++;
/*     */       } 
/* 508 */       timelines.add(timeline);
/* 509 */       duration = Math.max(duration, timeline.getFrames()[(timeline.getFrameCount() - 1) * 3]);
/*     */     } 
/*     */ 
/*     */     
/* 513 */     for (constraintMap = map.getChild("transform"); constraintMap != null; constraintMap = constraintMap.next) {
/* 514 */       TransformConstraintData constraint = skeletonData.findTransformConstraint(constraintMap.name);
/* 515 */       Animation.TransformConstraintTimeline timeline = new Animation.TransformConstraintTimeline(constraintMap.size);
/* 516 */       timeline.transformConstraintIndex = skeletonData.getTransformConstraints().indexOf(constraint, true);
/* 517 */       int frameIndex = 0;
/* 518 */       for (JsonValue valueMap = constraintMap.child; valueMap != null; valueMap = valueMap.next) {
/* 519 */         timeline.setFrame(frameIndex, valueMap.getFloat("time"), valueMap.getFloat("rotateMix", 1.0F), valueMap
/* 520 */             .getFloat("translateMix", 1.0F), valueMap.getFloat("scaleMix", 1.0F), valueMap.getFloat("shearMix", 1.0F));
/* 521 */         readCurve(valueMap, timeline, frameIndex);
/* 522 */         frameIndex++;
/*     */       } 
/* 524 */       timelines.add(timeline);
/* 525 */       duration = Math.max(duration, timeline
/* 526 */           .getFrames()[(timeline.getFrameCount() - 1) * 5]);
/*     */     } 
/*     */ 
/*     */     
/* 530 */     for (constraintMap = map.getChild("paths"); constraintMap != null; constraintMap = constraintMap.next) {
/* 531 */       int index = skeletonData.findPathConstraintIndex(constraintMap.name);
/* 532 */       if (index == -1) throw new SerializationException("Path constraint not found: " + constraintMap.name); 
/* 533 */       PathConstraintData data = (PathConstraintData)skeletonData.getPathConstraints().get(index);
/* 534 */       for (JsonValue timelineMap = constraintMap.child; timelineMap != null; timelineMap = timelineMap.next) {
/* 535 */         String timelineName = timelineMap.name;
/* 536 */         if (timelineName.equals("position") || timelineName.equals("spacing")) {
/*     */           Animation.PathConstraintPositionTimeline timeline;
/* 538 */           float timelineScale = 1.0F;
/* 539 */           if (timelineName.equals("spacing")) {
/* 540 */             timeline = new Animation.PathConstraintSpacingTimeline(timelineMap.size);
/* 541 */             if (data.spacingMode == PathConstraintData.SpacingMode.length || data.spacingMode == PathConstraintData.SpacingMode.fixed) timelineScale = scale; 
/*     */           } else {
/* 543 */             timeline = new Animation.PathConstraintPositionTimeline(timelineMap.size);
/* 544 */             if (data.positionMode == PathConstraintData.PositionMode.fixed) timelineScale = scale; 
/*     */           } 
/* 546 */           timeline.pathConstraintIndex = index;
/* 547 */           int frameIndex = 0;
/* 548 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/* 549 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), valueMap.getFloat(timelineName, 0.0F) * timelineScale);
/* 550 */             readCurve(valueMap, timeline, frameIndex);
/* 551 */             frameIndex++;
/*     */           } 
/* 553 */           timelines.add(timeline);
/* 554 */           duration = Math.max(duration, timeline
/* 555 */               .getFrames()[(timeline.getFrameCount() - 1) * 2]);
/* 556 */         } else if (timelineName.equals("mix")) {
/* 557 */           Animation.PathConstraintMixTimeline timeline = new Animation.PathConstraintMixTimeline(timelineMap.size);
/* 558 */           timeline.pathConstraintIndex = index;
/* 559 */           int frameIndex = 0;
/* 560 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/* 561 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), valueMap.getFloat("rotateMix", 1.0F), valueMap
/* 562 */                 .getFloat("translateMix", 1.0F));
/* 563 */             readCurve(valueMap, timeline, frameIndex);
/* 564 */             frameIndex++;
/*     */           } 
/* 566 */           timelines.add(timeline);
/* 567 */           duration = Math.max(duration, timeline
/* 568 */               .getFrames()[(timeline.getFrameCount() - 1) * 3]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 574 */     for (JsonValue deformMap = map.getChild("deform"); deformMap != null; deformMap = deformMap.next) {
/* 575 */       Skin skin = skeletonData.findSkin(deformMap.name);
/* 576 */       if (skin == null) throw new SerializationException("Skin not found: " + deformMap.name); 
/* 577 */       for (JsonValue jsonValue = deformMap.child; jsonValue != null; jsonValue = jsonValue.next) {
/* 578 */         int slotIndex = skeletonData.findSlotIndex(jsonValue.name);
/* 579 */         if (slotIndex == -1) throw new SerializationException("Slot not found: " + jsonValue.name); 
/* 580 */         for (JsonValue timelineMap = jsonValue.child; timelineMap != null; timelineMap = timelineMap.next) {
/* 581 */           VertexAttachment attachment = (VertexAttachment)skin.getAttachment(slotIndex, timelineMap.name);
/* 582 */           if (attachment == null) throw new SerializationException("Deform attachment not found: " + timelineMap.name); 
/* 583 */           boolean weighted = (attachment.getBones() != null);
/* 584 */           float[] vertices = attachment.getVertices();
/* 585 */           int deformLength = weighted ? (vertices.length / 3 * 2) : vertices.length;
/*     */           
/* 587 */           Animation.DeformTimeline timeline = new Animation.DeformTimeline(timelineMap.size);
/* 588 */           timeline.slotIndex = slotIndex;
/* 589 */           timeline.attachment = attachment;
/*     */           
/* 591 */           int frameIndex = 0;
/* 592 */           for (JsonValue valueMap = timelineMap.child; valueMap != null; valueMap = valueMap.next) {
/*     */             float[] deform;
/* 594 */             JsonValue verticesValue = valueMap.get("vertices");
/* 595 */             if (verticesValue == null) {
/* 596 */               deform = weighted ? new float[deformLength] : vertices;
/*     */             } else {
/* 598 */               deform = new float[deformLength];
/* 599 */               int start = valueMap.getInt("offset", 0);
/* 600 */               System.arraycopy(verticesValue.asFloatArray(), 0, deform, start, verticesValue.size);
/* 601 */               if (scale != 1.0F)
/* 602 */                 for (int i = start, n = i + verticesValue.size; i < n; i++) {
/* 603 */                   deform[i] = deform[i] * scale;
/*     */                 } 
/* 605 */               if (!weighted) {
/* 606 */                 for (int i = 0; i < deformLength; i++) {
/* 607 */                   deform[i] = deform[i] + vertices[i];
/*     */                 }
/*     */               }
/*     */             } 
/* 611 */             timeline.setFrame(frameIndex, valueMap.getFloat("time"), deform);
/* 612 */             readCurve(valueMap, timeline, frameIndex);
/* 613 */             frameIndex++;
/*     */           } 
/* 615 */           timelines.add(timeline);
/* 616 */           duration = Math.max(duration, timeline.getFrames()[timeline.getFrameCount() - 1]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 622 */     JsonValue drawOrdersMap = map.get("drawOrder");
/* 623 */     if (drawOrdersMap == null) drawOrdersMap = map.get("draworder"); 
/* 624 */     if (drawOrdersMap != null) {
/* 625 */       Animation.DrawOrderTimeline timeline = new Animation.DrawOrderTimeline(drawOrdersMap.size);
/* 626 */       int slotCount = skeletonData.slots.size;
/* 627 */       int frameIndex = 0;
/* 628 */       for (JsonValue drawOrderMap = drawOrdersMap.child; drawOrderMap != null; drawOrderMap = drawOrderMap.next) {
/* 629 */         int[] drawOrder = null;
/* 630 */         JsonValue offsets = drawOrderMap.get("offsets");
/* 631 */         if (offsets != null) {
/* 632 */           drawOrder = new int[slotCount];
/* 633 */           for (int i = slotCount - 1; i >= 0; i--)
/* 634 */             drawOrder[i] = -1; 
/* 635 */           int[] unchanged = new int[slotCount - offsets.size];
/* 636 */           int originalIndex = 0, unchangedIndex = 0;
/* 637 */           for (JsonValue offsetMap = offsets.child; offsetMap != null; offsetMap = offsetMap.next) {
/* 638 */             int slotIndex = skeletonData.findSlotIndex(offsetMap.getString("slot"));
/* 639 */             if (slotIndex == -1) throw new SerializationException("Slot not found: " + offsetMap.getString("slot"));
/*     */             
/* 641 */             while (originalIndex != slotIndex) {
/* 642 */               unchanged[unchangedIndex++] = originalIndex++;
/*     */             }
/* 644 */             drawOrder[originalIndex + offsetMap.getInt("offset")] = originalIndex++;
/*     */           } 
/*     */           
/* 647 */           while (originalIndex < slotCount) {
/* 648 */             unchanged[unchangedIndex++] = originalIndex++;
/*     */           }
/* 650 */           for (int j = slotCount - 1; j >= 0; j--) {
/* 651 */             if (drawOrder[j] == -1) drawOrder[j] = unchanged[--unchangedIndex]; 
/*     */           } 
/* 653 */         }  timeline.setFrame(frameIndex++, drawOrderMap.getFloat("time"), drawOrder);
/*     */       } 
/* 655 */       timelines.add(timeline);
/* 656 */       duration = Math.max(duration, timeline.getFrames()[timeline.getFrameCount() - 1]);
/*     */     } 
/*     */ 
/*     */     
/* 660 */     JsonValue eventsMap = map.get("events");
/* 661 */     if (eventsMap != null) {
/* 662 */       Animation.EventTimeline timeline = new Animation.EventTimeline(eventsMap.size);
/* 663 */       int frameIndex = 0;
/* 664 */       for (JsonValue eventMap = eventsMap.child; eventMap != null; eventMap = eventMap.next) {
/* 665 */         EventData eventData = skeletonData.findEvent(eventMap.getString("name"));
/* 666 */         if (eventData == null) throw new SerializationException("Event not found: " + eventMap.getString("name")); 
/* 667 */         Event event = new Event(eventMap.getFloat("time"), eventData);
/* 668 */         event.intValue = eventMap.getInt("int", eventData.getInt());
/* 669 */         event.floatValue = eventMap.getFloat("float", eventData.getFloat());
/* 670 */         event.stringValue = eventMap.getString("string", eventData.getString());
/* 671 */         timeline.setFrame(frameIndex++, event);
/*     */       } 
/* 673 */       timelines.add(timeline);
/* 674 */       duration = Math.max(duration, timeline.getFrames()[timeline.getFrameCount() - 1]);
/*     */     } 
/*     */     
/* 677 */     timelines.shrink();
/* 678 */     skeletonData.animations.add(new Animation(name, timelines, duration));
/*     */   }
/*     */   
/*     */   void readCurve(JsonValue map, Animation.CurveTimeline timeline, int frameIndex) {
/* 682 */     JsonValue curve = map.get("curve");
/* 683 */     if (curve == null)
/* 684 */       return;  if (curve.isString() && curve.asString().equals("stepped")) {
/* 685 */       timeline.setStepped(frameIndex);
/* 686 */     } else if (curve.isArray()) {
/* 687 */       timeline.setCurve(frameIndex, curve.getFloat(0), curve.getFloat(1), curve.getFloat(2), curve.getFloat(3));
/*     */     } 
/*     */   }
/*     */   
/*     */   static class LinkedMesh { String parent;
/*     */     String skin;
/*     */     int slotIndex;
/*     */     MeshAttachment mesh;
/*     */     
/*     */     public LinkedMesh(MeshAttachment mesh, String skin, int slotIndex, String parent) {
/* 697 */       this.mesh = mesh;
/* 698 */       this.skin = skin;
/* 699 */       this.slotIndex = slotIndex;
/* 700 */       this.parent = parent;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonJson.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */