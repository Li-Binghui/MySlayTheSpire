/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.utils.Array;
/*     */ import com.badlogic.gdx.utils.FloatArray;
/*     */ import com.esotericsoftware.spine.attachments.Attachment;
/*     */ import com.esotericsoftware.spine.attachments.PathAttachment;
/*     */ 
/*     */ public class PathConstraint
/*     */   implements Updatable
/*     */ {
/*     */   private static final int NONE = -1;
/*     */   private static final int BEFORE = -2;
/*     */   private static final int AFTER = -3;
/*     */   final PathConstraintData data;
/*     */   final Array<Bone> bones;
/*     */   Slot target;
/*     */   float position;
/*     */   float spacing;
/*     */   float rotateMix;
/*     */   float translateMix;
/*  22 */   private final FloatArray spaces = new FloatArray(), positions = new FloatArray();
/*  23 */   private final FloatArray world = new FloatArray(); private final FloatArray curves = new FloatArray(); private final FloatArray lengths = new FloatArray();
/*  24 */   private final float[] segments = new float[10];
/*     */   
/*     */   public PathConstraint(PathConstraintData data, Skeleton skeleton) {
/*  27 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  28 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  29 */     this.data = data;
/*  30 */     this.bones = new Array(data.bones.size);
/*  31 */     for (BoneData boneData : data.bones)
/*  32 */       this.bones.add(skeleton.findBone(boneData.name)); 
/*  33 */     this.target = skeleton.findSlot(data.target.name);
/*  34 */     this.position = data.position;
/*  35 */     this.spacing = data.spacing;
/*  36 */     this.rotateMix = data.rotateMix;
/*  37 */     this.translateMix = data.translateMix;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathConstraint(PathConstraint constraint, Skeleton skeleton) {
/*  42 */     if (constraint == null) throw new IllegalArgumentException("constraint cannot be null."); 
/*  43 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null."); 
/*  44 */     this.data = constraint.data;
/*  45 */     this.bones = new Array(constraint.bones.size);
/*  46 */     for (Bone bone : constraint.bones)
/*  47 */       this.bones.add(skeleton.bones.get(bone.data.index)); 
/*  48 */     this.target = (Slot)skeleton.slots.get(constraint.target.data.index);
/*  49 */     this.position = constraint.position;
/*  50 */     this.spacing = constraint.spacing;
/*  51 */     this.rotateMix = constraint.rotateMix;
/*  52 */     this.translateMix = constraint.translateMix;
/*     */   }
/*     */   
/*     */   public void apply() {
/*  56 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  61 */     Attachment attachment = this.target.attachment;
/*  62 */     if (!(attachment instanceof PathAttachment))
/*     */       return; 
/*  64 */     float rotateMix = this.rotateMix, translateMix = this.translateMix;
/*  65 */     boolean translate = (translateMix > 0.0F), rotate = (rotateMix > 0.0F);
/*  66 */     if (!translate && !rotate)
/*     */       return; 
/*  68 */     PathConstraintData data = this.data;
/*  69 */     PathConstraintData.SpacingMode spacingMode = data.spacingMode;
/*  70 */     boolean lengthSpacing = (spacingMode == PathConstraintData.SpacingMode.length);
/*  71 */     PathConstraintData.RotateMode rotateMode = data.rotateMode;
/*  72 */     boolean tangents = (rotateMode == PathConstraintData.RotateMode.tangent), scale = (rotateMode == PathConstraintData.RotateMode.chainScale);
/*  73 */     int boneCount = this.bones.size, spacesCount = tangents ? boneCount : (boneCount + 1);
/*  74 */     Object[] bones = this.bones.items;
/*  75 */     float[] spaces = this.spaces.setSize(spacesCount), lengths = null;
/*  76 */     float spacing = this.spacing;
/*  77 */     if (scale || lengthSpacing) {
/*  78 */       if (scale) lengths = this.lengths.setSize(boneCount); 
/*  79 */       for (int j = 0, n = spacesCount - 1; j < n; ) {
/*  80 */         Bone bone = (Bone)bones[j];
/*  81 */         float length = bone.data.length, x = length * bone.a, y = length * bone.c;
/*  82 */         length = (float)Math.sqrt((x * x + y * y));
/*  83 */         if (scale) lengths[j] = length; 
/*  84 */         spaces[++j] = lengthSpacing ? Math.max(0.0F, length + spacing) : spacing;
/*     */       } 
/*     */     } else {
/*  87 */       for (int j = 1; j < spacesCount; j++) {
/*  88 */         spaces[j] = spacing;
/*     */       }
/*     */     } 
/*  91 */     float[] positions = computeWorldPositions((PathAttachment)attachment, spacesCount, tangents, (data.positionMode == PathConstraintData.PositionMode.percent), (spacingMode == PathConstraintData.SpacingMode.percent));
/*     */     
/*  93 */     Skeleton skeleton = this.target.getSkeleton();
/*  94 */     float skeletonX = skeleton.x, skeletonY = skeleton.y;
/*  95 */     float boneX = positions[0], boneY = positions[1], offsetRotation = data.offsetRotation;
/*  96 */     boolean tip = (rotateMode == PathConstraintData.RotateMode.chain && offsetRotation == 0.0F);
/*  97 */     for (int i = 0, p = 3; i < boneCount; i++, p += 3) {
/*  98 */       Bone bone = (Bone)bones[i];
/*  99 */       bone.worldX += (boneX - skeletonX - bone.worldX) * translateMix;
/* 100 */       bone.worldY += (boneY - skeletonY - bone.worldY) * translateMix;
/* 101 */       float x = positions[p], y = positions[p + 1], dx = x - boneX, dy = y - boneY;
/* 102 */       if (scale) {
/* 103 */         float length = lengths[i];
/* 104 */         if (length != 0.0F) {
/* 105 */           float s = ((float)Math.sqrt((dx * dx + dy * dy)) / length - 1.0F) * rotateMix + 1.0F;
/* 106 */           bone.a *= s;
/* 107 */           bone.c *= s;
/*     */         } 
/*     */       } 
/* 110 */       boneX = x;
/* 111 */       boneY = y;
/* 112 */       if (rotate) {
/* 113 */         float r, a = bone.a, b = bone.b, c = bone.c, d = bone.d;
/* 114 */         if (tangents) {
/* 115 */           r = positions[p - 1];
/* 116 */         } else if (spaces[i + 1] == 0.0F) {
/* 117 */           r = positions[p + 2];
/*     */         } else {
/* 119 */           r = MathUtils.atan2(dy, dx);
/* 120 */         }  r -= MathUtils.atan2(c, a) - offsetRotation * 0.017453292F;
/* 121 */         if (tip) {
/* 122 */           float f1 = MathUtils.cos(r);
/* 123 */           float f2 = MathUtils.sin(r);
/* 124 */           float length = bone.data.length;
/* 125 */           boneX += (length * (f1 * a - f2 * c) - dx) * rotateMix;
/* 126 */           boneY += (length * (f2 * a + f1 * c) - dy) * rotateMix;
/*     */         } 
/* 128 */         if (r > 3.1415927F) {
/* 129 */           r -= 6.2831855F;
/* 130 */         } else if (r < -3.1415927F) {
/* 131 */           r += 6.2831855F;
/* 132 */         }  r *= rotateMix;
/* 133 */         float cos = MathUtils.cos(r);
/* 134 */         float sin = MathUtils.sin(r);
/* 135 */         bone.a = cos * a - sin * c;
/* 136 */         bone.b = cos * b - sin * d;
/* 137 */         bone.c = sin * a + cos * c;
/* 138 */         bone.d = sin * b + cos * d;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   float[] computeWorldPositions(PathAttachment path, int spacesCount, boolean tangents, boolean percentPosition, boolean percentSpacing) {
/*     */     float[] world;
/* 145 */     Slot target = this.target;
/* 146 */     float position = this.position;
/* 147 */     float[] spaces = this.spaces.items, out = this.positions.setSize(spacesCount * 3 + 2);
/* 148 */     boolean closed = path.getClosed();
/* 149 */     int verticesLength = path.getWorldVerticesLength(), curveCount = verticesLength / 6, prevCurve = -1;
/*     */     
/* 151 */     if (!path.getConstantSpeed()) {
/* 152 */       float[] lengths = path.getLengths();
/* 153 */       curveCount -= closed ? 1 : 2;
/* 154 */       float f = lengths[curveCount];
/* 155 */       if (percentPosition) position *= f; 
/* 156 */       if (percentSpacing)
/* 157 */         for (int i1 = 0; i1 < spacesCount; i1++) {
/* 158 */           spaces[i1] = spaces[i1] * f;
/*     */         } 
/* 160 */       world = this.world.setSize(8);
/* 161 */       for (int k = 0, m = 0, n = 0; k < spacesCount; k++, m += 3) {
/* 162 */         float space = spaces[k];
/* 163 */         position += space;
/* 164 */         float p = position;
/*     */         
/* 166 */         if (closed)
/* 167 */         { p %= f;
/* 168 */           if (p < 0.0F) p += f; 
/* 169 */           n = 0; }
/* 170 */         else { if (p < 0.0F) {
/* 171 */             if (prevCurve != -2) {
/* 172 */               prevCurve = -2;
/* 173 */               path.computeWorldVertices(target, 2, 4, world, 0);
/*     */             } 
/* 175 */             addBeforePosition(p, world, 0, out, m); continue;
/*     */           } 
/* 177 */           if (p > f) {
/* 178 */             if (prevCurve != -3) {
/* 179 */               prevCurve = -3;
/* 180 */               path.computeWorldVertices(target, verticesLength - 6, 4, world, 0);
/*     */             } 
/* 182 */             addAfterPosition(p - f, world, 0, out, m);
/*     */             
/*     */             continue;
/*     */           }  }
/*     */         
/*     */         while (true) {
/* 188 */           float length = lengths[n];
/* 189 */           if (p > length) { n++; continue; }
/* 190 */            if (n == 0) {
/* 191 */             p /= length; break;
/*     */           } 
/* 193 */           float prev = lengths[n - 1];
/* 194 */           p = (p - prev) / (length - prev);
/*     */           
/*     */           break;
/*     */         } 
/* 198 */         if (n != prevCurve) {
/* 199 */           prevCurve = n;
/* 200 */           if (closed && n == curveCount) {
/* 201 */             path.computeWorldVertices(target, verticesLength - 4, 4, world, 0);
/* 202 */             path.computeWorldVertices(target, 0, 4, world, 4);
/*     */           } else {
/* 204 */             path.computeWorldVertices(target, n * 6 + 2, 8, world, 0);
/*     */           } 
/* 206 */         }  addCurvePosition(p, world[0], world[1], world[2], world[3], world[4], world[5], world[6], world[7], out, m, (tangents || (k > 0 && space == 0.0F)));
/*     */         continue;
/*     */       } 
/* 209 */       return out;
/*     */     } 
/*     */ 
/*     */     
/* 213 */     if (closed) {
/* 214 */       verticesLength += 2;
/* 215 */       world = this.world.setSize(verticesLength);
/* 216 */       path.computeWorldVertices(target, 2, verticesLength - 4, world, 0);
/* 217 */       path.computeWorldVertices(target, 0, 2, world, verticesLength - 4);
/* 218 */       world[verticesLength - 2] = world[0];
/* 219 */       world[verticesLength - 1] = world[1];
/*     */     } else {
/* 221 */       curveCount--;
/* 222 */       verticesLength -= 4;
/* 223 */       world = this.world.setSize(verticesLength);
/* 224 */       path.computeWorldVertices(target, 2, verticesLength, world, 0);
/*     */     } 
/*     */ 
/*     */     
/* 228 */     float[] curves = this.curves.setSize(curveCount);
/* 229 */     float pathLength = 0.0F;
/* 230 */     float x1 = world[0], y1 = world[1], cx1 = 0.0F, cy1 = 0.0F, cx2 = 0.0F, cy2 = 0.0F, x2 = 0.0F, y2 = 0.0F;
/*     */     int i, w;
/* 232 */     for (i = 0, w = 2; i < curveCount; i++, w += 6) {
/* 233 */       cx1 = world[w];
/* 234 */       cy1 = world[w + 1];
/* 235 */       cx2 = world[w + 2];
/* 236 */       cy2 = world[w + 3];
/* 237 */       x2 = world[w + 4];
/* 238 */       y2 = world[w + 5];
/* 239 */       float tmpx = (x1 - cx1 * 2.0F + cx2) * 0.1875F;
/* 240 */       float tmpy = (y1 - cy1 * 2.0F + cy2) * 0.1875F;
/* 241 */       float dddfx = ((cx1 - cx2) * 3.0F - x1 + x2) * 0.09375F;
/* 242 */       float dddfy = ((cy1 - cy2) * 3.0F - y1 + y2) * 0.09375F;
/* 243 */       float ddfx = tmpx * 2.0F + dddfx;
/* 244 */       float ddfy = tmpy * 2.0F + dddfy;
/* 245 */       float dfx = (cx1 - x1) * 0.75F + tmpx + dddfx * 0.16666667F;
/* 246 */       float dfy = (cy1 - y1) * 0.75F + tmpy + dddfy * 0.16666667F;
/* 247 */       pathLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 248 */       dfx += ddfx;
/* 249 */       dfy += ddfy;
/* 250 */       ddfx += dddfx;
/* 251 */       ddfy += dddfy;
/* 252 */       pathLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 253 */       dfx += ddfx;
/* 254 */       dfy += ddfy;
/* 255 */       pathLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 256 */       dfx += ddfx + dddfx;
/* 257 */       dfy += ddfy + dddfy;
/* 258 */       pathLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 259 */       curves[i] = pathLength;
/* 260 */       x1 = x2;
/* 261 */       y1 = y2;
/*     */     } 
/* 263 */     if (percentPosition) position *= pathLength; 
/* 264 */     if (percentSpacing) {
/* 265 */       for (i = 0; i < spacesCount; i++) {
/* 266 */         spaces[i] = spaces[i] * pathLength;
/*     */       }
/*     */     }
/* 269 */     float[] segments = this.segments;
/* 270 */     float curveLength = 0.0F;
/* 271 */     for (int j = 0, o = 0, curve = 0, segment = 0; j < spacesCount; j++, o += 3) {
/* 272 */       float space = spaces[j];
/* 273 */       position += space;
/* 274 */       float p = position;
/*     */       
/* 276 */       if (closed)
/* 277 */       { p %= pathLength;
/* 278 */         if (p < 0.0F) p += pathLength; 
/* 279 */         curve = 0; }
/* 280 */       else { if (p < 0.0F) {
/* 281 */           addBeforePosition(p, world, 0, out, o); continue;
/*     */         } 
/* 283 */         if (p > pathLength) {
/* 284 */           addAfterPosition(p - pathLength, world, verticesLength - 4, out, o);
/*     */           
/*     */           continue;
/*     */         }  }
/*     */       
/*     */       while (true) {
/* 290 */         float length = curves[curve];
/* 291 */         if (p > length) { curve++; continue; }
/* 292 */          if (curve == 0) {
/* 293 */           p /= length; break;
/*     */         } 
/* 295 */         float prev = curves[curve - 1];
/* 296 */         p = (p - prev) / (length - prev);
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 302 */       if (curve != prevCurve) {
/* 303 */         prevCurve = curve;
/* 304 */         int ii = curve * 6;
/* 305 */         x1 = world[ii];
/* 306 */         y1 = world[ii + 1];
/* 307 */         cx1 = world[ii + 2];
/* 308 */         cy1 = world[ii + 3];
/* 309 */         cx2 = world[ii + 4];
/* 310 */         cy2 = world[ii + 5];
/* 311 */         x2 = world[ii + 6];
/* 312 */         y2 = world[ii + 7];
/* 313 */         float tmpx = (x1 - cx1 * 2.0F + cx2) * 0.03F;
/* 314 */         float tmpy = (y1 - cy1 * 2.0F + cy2) * 0.03F;
/* 315 */         float dddfx = ((cx1 - cx2) * 3.0F - x1 + x2) * 0.006F;
/* 316 */         float dddfy = ((cy1 - cy2) * 3.0F - y1 + y2) * 0.006F;
/* 317 */         float ddfx = tmpx * 2.0F + dddfx;
/* 318 */         float ddfy = tmpy * 2.0F + dddfy;
/* 319 */         float dfx = (cx1 - x1) * 0.3F + tmpx + dddfx * 0.16666667F;
/* 320 */         float dfy = (cy1 - y1) * 0.3F + tmpy + dddfy * 0.16666667F;
/* 321 */         curveLength = (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 322 */         segments[0] = curveLength;
/* 323 */         for (ii = 1; ii < 8; ii++) {
/* 324 */           dfx += ddfx;
/* 325 */           dfy += ddfy;
/* 326 */           ddfx += dddfx;
/* 327 */           ddfy += dddfy;
/* 328 */           curveLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 329 */           segments[ii] = curveLength;
/*     */         } 
/* 331 */         dfx += ddfx;
/* 332 */         dfy += ddfy;
/* 333 */         curveLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 334 */         segments[8] = curveLength;
/* 335 */         dfx += ddfx + dddfx;
/* 336 */         dfy += ddfy + dddfy;
/* 337 */         curveLength += (float)Math.sqrt((dfx * dfx + dfy * dfy));
/* 338 */         segments[9] = curveLength;
/* 339 */         segment = 0;
/*     */       } 
/*     */ 
/*     */       
/* 343 */       p *= curveLength;
/*     */       while (true) {
/* 345 */         float length = segments[segment];
/* 346 */         if (p > length) { segment++; continue; }
/* 347 */          if (segment == 0) {
/* 348 */           p /= length; break;
/*     */         } 
/* 350 */         float prev = segments[segment - 1];
/* 351 */         p = segment + (p - prev) / (length - prev);
/*     */         
/*     */         break;
/*     */       } 
/* 355 */       addCurvePosition(p * 0.1F, x1, y1, cx1, cy1, cx2, cy2, x2, y2, out, o, (tangents || (j > 0 && space == 0.0F))); continue;
/*     */     } 
/* 357 */     return out;
/*     */   }
/*     */   
/*     */   private void addBeforePosition(float p, float[] temp, int i, float[] out, int o) {
/* 361 */     float x1 = temp[i], y1 = temp[i + 1], dx = temp[i + 2] - x1, dy = temp[i + 3] - y1, r = MathUtils.atan2(dy, dx);
/* 362 */     out[o] = x1 + p * MathUtils.cos(r);
/* 363 */     out[o + 1] = y1 + p * MathUtils.sin(r);
/* 364 */     out[o + 2] = r;
/*     */   }
/*     */   
/*     */   private void addAfterPosition(float p, float[] temp, int i, float[] out, int o) {
/* 368 */     float x1 = temp[i + 2], y1 = temp[i + 3], dx = x1 - temp[i], dy = y1 - temp[i + 1], r = MathUtils.atan2(dy, dx);
/* 369 */     out[o] = x1 + p * MathUtils.cos(r);
/* 370 */     out[o + 1] = y1 + p * MathUtils.sin(r);
/* 371 */     out[o + 2] = r;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addCurvePosition(float p, float x1, float y1, float cx1, float cy1, float cx2, float cy2, float x2, float y2, float[] out, int o, boolean tangents) {
/* 376 */     if (p == 0.0F) p = 1.0E-4F; 
/* 377 */     float tt = p * p, ttt = tt * p, u = 1.0F - p, uu = u * u, uuu = uu * u;
/* 378 */     float ut = u * p, ut3 = ut * 3.0F, uut3 = u * ut3, utt3 = ut3 * p;
/* 379 */     float x = x1 * uuu + cx1 * uut3 + cx2 * utt3 + x2 * ttt, y = y1 * uuu + cy1 * uut3 + cy2 * utt3 + y2 * ttt;
/* 380 */     out[o] = x;
/* 381 */     out[o + 1] = y;
/* 382 */     if (tangents) out[o + 2] = MathUtils.atan2(y - y1 * uu + cy1 * ut * 2.0F + cy2 * tt, x - x1 * uu + cx1 * ut * 2.0F + cx2 * tt); 
/*     */   }
/*     */   
/*     */   public float getPosition() {
/* 386 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(float position) {
/* 390 */     this.position = position;
/*     */   }
/*     */   
/*     */   public float getSpacing() {
/* 394 */     return this.spacing;
/*     */   }
/*     */   
/*     */   public void setSpacing(float spacing) {
/* 398 */     this.spacing = spacing;
/*     */   }
/*     */   
/*     */   public float getRotateMix() {
/* 402 */     return this.rotateMix;
/*     */   }
/*     */   
/*     */   public void setRotateMix(float rotateMix) {
/* 406 */     this.rotateMix = rotateMix;
/*     */   }
/*     */   
/*     */   public float getTranslateMix() {
/* 410 */     return this.translateMix;
/*     */   }
/*     */   
/*     */   public void setTranslateMix(float translateMix) {
/* 414 */     this.translateMix = translateMix;
/*     */   }
/*     */   
/*     */   public Array<Bone> getBones() {
/* 418 */     return this.bones;
/*     */   }
/*     */   
/*     */   public Slot getTarget() {
/* 422 */     return this.target;
/*     */   }
/*     */   
/*     */   public void setTarget(Slot target) {
/* 426 */     this.target = target;
/*     */   }
/*     */   
/*     */   public PathConstraintData getData() {
/* 430 */     return this.data;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 434 */     return this.data.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\PathConstraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */