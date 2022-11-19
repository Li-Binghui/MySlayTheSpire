/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.utils.Array;
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
/*     */ public class SkeletonData
/*     */ {
/*     */   String name;
/*  38 */   final Array<BoneData> bones = new Array();
/*  39 */   final Array<SlotData> slots = new Array();
/*  40 */   final Array<Skin> skins = new Array();
/*     */   Skin defaultSkin;
/*  42 */   final Array<EventData> events = new Array();
/*  43 */   final Array<Animation> animations = new Array();
/*  44 */   final Array<IkConstraintData> ikConstraints = new Array();
/*  45 */   final Array<TransformConstraintData> transformConstraints = new Array();
/*  46 */   final Array<PathConstraintData> pathConstraints = new Array();
/*     */   
/*     */   float width;
/*     */   
/*     */   float height;
/*     */   
/*     */   public Array<BoneData> getBones() {
/*  53 */     return this.bones;
/*     */   }
/*     */   String version; String hash; String imagesPath;
/*     */   
/*     */   public BoneData findBone(String boneName) {
/*  58 */     if (boneName == null) throw new IllegalArgumentException("boneName cannot be null."); 
/*  59 */     Array<BoneData> bones = this.bones;
/*  60 */     for (int i = 0, n = bones.size; i < n; i++) {
/*  61 */       BoneData bone = (BoneData)bones.get(i);
/*  62 */       if (bone.name.equals(boneName)) return bone; 
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findBoneIndex(String boneName) {
/*  69 */     if (boneName == null) throw new IllegalArgumentException("boneName cannot be null."); 
/*  70 */     Array<BoneData> bones = this.bones;
/*  71 */     for (int i = 0, n = bones.size; i < n; i++) {
/*  72 */       if (((BoneData)bones.get(i)).name.equals(boneName)) return i; 
/*  73 */     }  return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Array<SlotData> getSlots() {
/*  79 */     return this.slots;
/*     */   }
/*     */ 
/*     */   
/*     */   public SlotData findSlot(String slotName) {
/*  84 */     if (slotName == null) throw new IllegalArgumentException("slotName cannot be null."); 
/*  85 */     Array<SlotData> slots = this.slots;
/*  86 */     for (int i = 0, n = slots.size; i < n; i++) {
/*  87 */       SlotData slot = (SlotData)slots.get(i);
/*  88 */       if (slot.name.equals(slotName)) return slot; 
/*     */     } 
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findSlotIndex(String slotName) {
/*  95 */     if (slotName == null) throw new IllegalArgumentException("slotName cannot be null."); 
/*  96 */     Array<SlotData> slots = this.slots;
/*  97 */     for (int i = 0, n = slots.size; i < n; i++) {
/*  98 */       if (((SlotData)slots.get(i)).name.equals(slotName)) return i; 
/*  99 */     }  return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skin getDefaultSkin() {
/* 106 */     return this.defaultSkin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultSkin(Skin defaultSkin) {
/* 111 */     this.defaultSkin = defaultSkin;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin findSkin(String skinName) {
/* 116 */     if (skinName == null) throw new IllegalArgumentException("skinName cannot be null."); 
/* 117 */     for (Skin skin : this.skins) {
/* 118 */       if (skin.name.equals(skinName)) return skin; 
/* 119 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Array<Skin> getSkins() {
/* 124 */     return this.skins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventData findEvent(String eventDataName) {
/* 131 */     if (eventDataName == null) throw new IllegalArgumentException("eventDataName cannot be null."); 
/* 132 */     for (EventData eventData : this.events) {
/* 133 */       if (eventData.name.equals(eventDataName)) return eventData; 
/* 134 */     }  return null;
/*     */   }
/*     */   
/*     */   public Array<EventData> getEvents() {
/* 138 */     return this.events;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Array<Animation> getAnimations() {
/* 144 */     return this.animations;
/*     */   }
/*     */ 
/*     */   
/*     */   public Animation findAnimation(String animationName) {
/* 149 */     if (animationName == null) throw new IllegalArgumentException("animationName cannot be null."); 
/* 150 */     Array<Animation> animations = this.animations;
/* 151 */     for (int i = 0, n = animations.size; i < n; i++) {
/* 152 */       Animation animation = (Animation)animations.get(i);
/* 153 */       if (animation.name.equals(animationName)) return animation; 
/*     */     } 
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Array<IkConstraintData> getIkConstraints() {
/* 161 */     return this.ikConstraints;
/*     */   }
/*     */ 
/*     */   
/*     */   public IkConstraintData findIkConstraint(String constraintName) {
/* 166 */     if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null."); 
/* 167 */     Array<IkConstraintData> ikConstraints = this.ikConstraints;
/* 168 */     for (int i = 0, n = ikConstraints.size; i < n; i++) {
/* 169 */       IkConstraintData constraint = (IkConstraintData)ikConstraints.get(i);
/* 170 */       if (constraint.name.equals(constraintName)) return constraint; 
/*     */     } 
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Array<TransformConstraintData> getTransformConstraints() {
/* 178 */     return this.transformConstraints;
/*     */   }
/*     */ 
/*     */   
/*     */   public TransformConstraintData findTransformConstraint(String constraintName) {
/* 183 */     if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null."); 
/* 184 */     Array<TransformConstraintData> transformConstraints = this.transformConstraints;
/* 185 */     for (int i = 0, n = transformConstraints.size; i < n; i++) {
/* 186 */       TransformConstraintData constraint = (TransformConstraintData)transformConstraints.get(i);
/* 187 */       if (constraint.name.equals(constraintName)) return constraint; 
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Array<PathConstraintData> getPathConstraints() {
/* 195 */     return this.pathConstraints;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathConstraintData findPathConstraint(String constraintName) {
/* 200 */     if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null."); 
/* 201 */     Array<PathConstraintData> pathConstraints = this.pathConstraints;
/* 202 */     for (int i = 0, n = pathConstraints.size; i < n; i++) {
/* 203 */       PathConstraintData constraint = (PathConstraintData)pathConstraints.get(i);
/* 204 */       if (constraint.name.equals(constraintName)) return constraint; 
/*     */     } 
/* 206 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findPathConstraintIndex(String pathConstraintName) {
/* 211 */     if (pathConstraintName == null) throw new IllegalArgumentException("pathConstraintName cannot be null."); 
/* 212 */     Array<PathConstraintData> pathConstraints = this.pathConstraints;
/* 213 */     for (int i = 0, n = pathConstraints.size; i < n; i++) {
/* 214 */       if (((PathConstraintData)pathConstraints.get(i)).name.equals(pathConstraintName)) return i; 
/* 215 */     }  return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 222 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 227 */     this.name = name;
/*     */   }
/*     */   
/*     */   public float getWidth() {
/* 231 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(float width) {
/* 235 */     this.width = width;
/*     */   }
/*     */   
/*     */   public float getHeight() {
/* 239 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(float height) {
/* 243 */     this.height = height;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 248 */     return this.version;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/* 253 */     this.version = version;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHash() {
/* 258 */     return this.hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHash(String hash) {
/* 263 */     this.hash = hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getImagesPath() {
/* 268 */     return this.imagesPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setImagesPath(String imagesPath) {
/* 273 */     this.imagesPath = imagesPath;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 277 */     return (this.name != null) ? this.name : super.toString();
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */