/*      */ package com.esotericsoftware.spine;
/*      */ 
/*      */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.VertexAttachment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Animation
/*      */ {
/*      */   final String name;
/*      */   private final Array<Timeline> timelines;
/*      */   private float duration;
/*      */   
/*      */   public Animation(String name, Array<Timeline> timelines, float duration) {
/*   47 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/*   48 */     if (timelines == null) throw new IllegalArgumentException("timelines cannot be null."); 
/*   49 */     this.name = name;
/*   50 */     this.timelines = timelines;
/*   51 */     this.duration = duration;
/*      */   }
/*      */   
/*      */   public Array<Timeline> getTimelines() {
/*   55 */     return this.timelines;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getDuration() {
/*   60 */     return this.duration;
/*      */   }
/*      */   
/*      */   public void setDuration(float duration) {
/*   64 */     this.duration = duration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void apply(Skeleton skeleton, float lastTime, float time, boolean loop, Array<Event> events) {
/*   71 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null.");
/*      */     
/*   73 */     if (loop && this.duration != 0.0F) {
/*   74 */       time %= this.duration;
/*   75 */       if (lastTime > 0.0F) lastTime %= this.duration;
/*      */     
/*      */     } 
/*   78 */     Array<Timeline> timelines = this.timelines;
/*   79 */     for (int i = 0, n = timelines.size; i < n; i++) {
/*   80 */       ((Timeline)timelines.get(i)).apply(skeleton, lastTime, time, events, 1.0F);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mix(Skeleton skeleton, float lastTime, float time, boolean loop, Array<Event> events, float alpha) {
/*   88 */     if (skeleton == null) throw new IllegalArgumentException("skeleton cannot be null.");
/*      */     
/*   90 */     if (loop && this.duration != 0.0F) {
/*   91 */       time %= this.duration;
/*   92 */       if (lastTime > 0.0F) lastTime %= this.duration;
/*      */     
/*      */     } 
/*   95 */     Array<Timeline> timelines = this.timelines;
/*   96 */     for (int i = 0, n = timelines.size; i < n; i++)
/*   97 */       ((Timeline)timelines.get(i)).apply(skeleton, lastTime, time, events, alpha); 
/*      */   }
/*      */   
/*      */   public String getName() {
/*  101 */     return this.name;
/*      */   }
/*      */   
/*      */   public String toString() {
/*  105 */     return this.name;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static int binarySearch(float[] values, float target, int step) {
/*  111 */     int low = 0;
/*  112 */     int high = values.length / step - 2;
/*  113 */     if (high == 0) return step; 
/*  114 */     int current = high >>> 1;
/*      */     while (true) {
/*  116 */       if (values[(current + 1) * step] <= target) {
/*  117 */         low = current + 1;
/*      */       } else {
/*  119 */         high = current;
/*  120 */       }  if (low == high) return (low + 1) * step; 
/*  121 */       current = low + high >>> 1;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static int binarySearch(float[] values, float target) {
/*  128 */     int low = 0;
/*  129 */     int high = values.length - 2;
/*  130 */     if (high == 0) return 1; 
/*  131 */     int current = high >>> 1;
/*      */     while (true) {
/*  133 */       if (values[current + 1] <= target) {
/*  134 */         low = current + 1;
/*      */       } else {
/*  136 */         high = current;
/*  137 */       }  if (low == high) return low + 1; 
/*  138 */       current = low + high >>> 1;
/*      */     } 
/*      */   }
/*      */   
/*      */   static int linearSearch(float[] values, float target, int step) {
/*  143 */     for (int i = 0, last = values.length - step; i <= last; i += step) {
/*  144 */       if (values[i] > target) return i; 
/*  145 */     }  return -1;
/*      */   }
/*      */   
/*      */   public static interface Timeline
/*      */   {
/*      */     void apply(Skeleton param1Skeleton, float param1Float1, float param1Float2, Array<Event> param1Array, float param1Float3);
/*      */   }
/*      */   
/*      */   public static abstract class CurveTimeline
/*      */     implements Timeline {
/*      */     public static final float LINEAR = 0.0F;
/*      */     public static final float STEPPED = 1.0F;
/*      */     public static final float BEZIER = 2.0F;
/*      */     private static final int BEZIER_SIZE = 19;
/*      */     private final float[] curves;
/*      */     
/*      */     public CurveTimeline(int frameCount) {
/*  162 */       if (frameCount <= 0) throw new IllegalArgumentException("frameCount must be > 0: " + frameCount); 
/*  163 */       this.curves = new float[(frameCount - 1) * 19];
/*      */     }
/*      */     
/*      */     public int getFrameCount() {
/*  167 */       return this.curves.length / 19 + 1;
/*      */     }
/*      */     
/*      */     public void setLinear(int frameIndex) {
/*  171 */       this.curves[frameIndex * 19] = 0.0F;
/*      */     }
/*      */     
/*      */     public void setStepped(int frameIndex) {
/*  175 */       this.curves[frameIndex * 19] = 1.0F;
/*      */     }
/*      */     
/*      */     public float getCurveType(int frameIndex) {
/*  179 */       int index = frameIndex * 19;
/*  180 */       if (index == this.curves.length) return 0.0F; 
/*  181 */       float type = this.curves[index];
/*  182 */       if (type == 0.0F) return 0.0F; 
/*  183 */       if (type == 1.0F) return 1.0F; 
/*  184 */       return 2.0F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCurve(int frameIndex, float cx1, float cy1, float cx2, float cy2) {
/*  191 */       float tmpx = (-cx1 * 2.0F + cx2) * 0.03F, tmpy = (-cy1 * 2.0F + cy2) * 0.03F;
/*  192 */       float dddfx = ((cx1 - cx2) * 3.0F + 1.0F) * 0.006F, dddfy = ((cy1 - cy2) * 3.0F + 1.0F) * 0.006F;
/*  193 */       float ddfx = tmpx * 2.0F + dddfx, ddfy = tmpy * 2.0F + dddfy;
/*  194 */       float dfx = cx1 * 0.3F + tmpx + dddfx * 0.16666667F, dfy = cy1 * 0.3F + tmpy + dddfy * 0.16666667F;
/*      */       
/*  196 */       int i = frameIndex * 19;
/*  197 */       float[] curves = this.curves;
/*  198 */       curves[i++] = 2.0F;
/*      */       
/*  200 */       float x = dfx, y = dfy;
/*  201 */       for (int n = i + 19 - 1; i < n; i += 2) {
/*  202 */         curves[i] = x;
/*  203 */         curves[i + 1] = y;
/*  204 */         dfx += ddfx;
/*  205 */         dfy += ddfy;
/*  206 */         ddfx += dddfx;
/*  207 */         ddfy += dddfy;
/*  208 */         x += dfx;
/*  209 */         y += dfy;
/*      */       } 
/*      */     }
/*      */     
/*      */     public float getCurvePercent(int frameIndex, float percent) {
/*  214 */       percent = MathUtils.clamp(percent, 0.0F, 1.0F);
/*  215 */       float[] curves = this.curves;
/*  216 */       int i = frameIndex * 19;
/*  217 */       float type = curves[i];
/*  218 */       if (type == 0.0F) return percent; 
/*  219 */       if (type == 1.0F) return 0.0F; 
/*  220 */       i++;
/*  221 */       float x = 0.0F;
/*  222 */       for (int start = i, n = i + 19 - 1; i < n; i += 2) {
/*  223 */         x = curves[i];
/*  224 */         if (x >= percent) {
/*      */           float prevX; float prevY;
/*  226 */           if (i == start) {
/*  227 */             prevX = 0.0F;
/*  228 */             prevY = 0.0F;
/*      */           } else {
/*  230 */             prevX = curves[i - 2];
/*  231 */             prevY = curves[i - 1];
/*      */           } 
/*  233 */           return prevY + (curves[i + 1] - prevY) * (percent - prevX) / (x - prevX);
/*      */         } 
/*      */       } 
/*  236 */       float y = curves[i - 1];
/*  237 */       return y + (1.0F - y) * (percent - x) / (1.0F - x);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class RotateTimeline extends CurveTimeline {
/*      */     public static final int ENTRIES = 2;
/*      */     private static final int PREV_TIME = -2;
/*      */     private static final int PREV_ROTATION = -1;
/*      */     private static final int ROTATION = 1;
/*      */     int boneIndex;
/*      */     final float[] frames;
/*      */     
/*      */     public RotateTimeline(int frameCount) {
/*  250 */       super(frameCount);
/*  251 */       this.frames = new float[frameCount << 1];
/*      */     }
/*      */     
/*      */     public void setBoneIndex(int index) {
/*  255 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  256 */       this.boneIndex = index;
/*      */     }
/*      */     
/*      */     public int getBoneIndex() {
/*  260 */       return this.boneIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  264 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float degrees) {
/*  269 */       frameIndex <<= 1;
/*  270 */       this.frames[frameIndex] = time;
/*  271 */       this.frames[frameIndex + 1] = degrees;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  275 */       float[] frames = this.frames;
/*  276 */       if (time < frames[0])
/*      */         return; 
/*  278 */       Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
/*      */       
/*  280 */       if (time >= frames[frames.length - 2]) {
/*  281 */         float f = bone.data.rotation + frames[frames.length + -1] - bone.rotation;
/*  282 */         while (f > 180.0F)
/*  283 */           f -= 360.0F; 
/*  284 */         while (f < -180.0F)
/*  285 */           f += 360.0F; 
/*  286 */         bone.rotation += f * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  291 */       int frame = Animation.binarySearch(frames, time, 2);
/*  292 */       float prevRotation = frames[frame + -1];
/*  293 */       float frameTime = frames[frame];
/*  294 */       float percent = getCurvePercent((frame >> 1) - 1, 1.0F - (time - frameTime) / (frames[frame + -2] - frameTime));
/*      */       
/*  296 */       float amount = frames[frame + 1] - prevRotation;
/*  297 */       while (amount > 180.0F)
/*  298 */         amount -= 360.0F; 
/*  299 */       while (amount < -180.0F)
/*  300 */         amount += 360.0F; 
/*  301 */       amount = bone.data.rotation + prevRotation + amount * percent - bone.rotation;
/*  302 */       while (amount > 180.0F)
/*  303 */         amount -= 360.0F; 
/*  304 */       while (amount < -180.0F)
/*  305 */         amount += 360.0F; 
/*  306 */       bone.rotation += amount * alpha;
/*      */     } }
/*      */   
/*      */   public static class TranslateTimeline extends CurveTimeline { public static final int ENTRIES = 3;
/*      */     static final int PREV_TIME = -3;
/*      */     static final int PREV_X = -2;
/*      */     static final int PREV_Y = -1;
/*      */     static final int X = 1;
/*      */     static final int Y = 2;
/*      */     int boneIndex;
/*      */     final float[] frames;
/*      */     
/*      */     public TranslateTimeline(int frameCount) {
/*  319 */       super(frameCount);
/*  320 */       this.frames = new float[frameCount * 3];
/*      */     }
/*      */     
/*      */     public void setBoneIndex(int index) {
/*  324 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  325 */       this.boneIndex = index;
/*      */     }
/*      */     
/*      */     public int getBoneIndex() {
/*  329 */       return this.boneIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  333 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float x, float y) {
/*  338 */       frameIndex *= 3;
/*  339 */       this.frames[frameIndex] = time;
/*  340 */       this.frames[frameIndex + 1] = x;
/*  341 */       this.frames[frameIndex + 2] = y;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  345 */       float[] frames = this.frames;
/*  346 */       if (time < frames[0])
/*      */         return; 
/*  348 */       Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
/*      */       
/*  350 */       if (time >= frames[frames.length - 3]) {
/*  351 */         bone.x += (bone.data.x + frames[frames.length + -2] - bone.x) * alpha;
/*  352 */         bone.y += (bone.data.y + frames[frames.length + -1] - bone.y) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  357 */       int frame = Animation.binarySearch(frames, time, 3);
/*  358 */       float prevX = frames[frame + -2];
/*  359 */       float prevY = frames[frame + -1];
/*  360 */       float frameTime = frames[frame];
/*  361 */       float percent = getCurvePercent(frame / 3 - 1, 1.0F - (time - frameTime) / (frames[frame + -3] - frameTime));
/*      */       
/*  363 */       bone.x += (bone.data.x + prevX + (frames[frame + 1] - prevX) * percent - bone.x) * alpha;
/*  364 */       bone.y += (bone.data.y + prevY + (frames[frame + 2] - prevY) * percent - bone.y) * alpha;
/*      */     } }
/*      */ 
/*      */   
/*      */   public static class ScaleTimeline extends TranslateTimeline {
/*      */     public ScaleTimeline(int frameCount) {
/*  370 */       super(frameCount);
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  374 */       float[] frames = this.frames;
/*  375 */       if (time < frames[0])
/*      */         return; 
/*  377 */       Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
/*  378 */       if (time >= frames[frames.length - 3]) {
/*  379 */         bone.scaleX += (bone.data.scaleX * frames[frames.length + -2] - bone.scaleX) * alpha;
/*  380 */         bone.scaleY += (bone.data.scaleY * frames[frames.length + -1] - bone.scaleY) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  385 */       int frame = Animation.binarySearch(frames, time, 3);
/*  386 */       float prevX = frames[frame + -2];
/*  387 */       float prevY = frames[frame + -1];
/*  388 */       float frameTime = frames[frame];
/*  389 */       float percent = getCurvePercent(frame / 3 - 1, 1.0F - (time - frameTime) / (frames[frame + -3] - frameTime));
/*      */       
/*  391 */       bone.scaleX += (bone.data.scaleX * (prevX + (frames[frame + 1] - prevX) * percent) - bone.scaleX) * alpha;
/*  392 */       bone.scaleY += (bone.data.scaleY * (prevY + (frames[frame + 2] - prevY) * percent) - bone.scaleY) * alpha;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ShearTimeline extends TranslateTimeline {
/*      */     public ShearTimeline(int frameCount) {
/*  398 */       super(frameCount);
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  402 */       float[] frames = this.frames;
/*  403 */       if (time < frames[0])
/*      */         return; 
/*  405 */       Bone bone = (Bone)skeleton.bones.get(this.boneIndex);
/*  406 */       if (time >= frames[frames.length - 3]) {
/*  407 */         bone.shearX += (bone.data.shearX + frames[frames.length + -2] - bone.shearX) * alpha;
/*  408 */         bone.shearY += (bone.data.shearY + frames[frames.length + -1] - bone.shearY) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  413 */       int frame = Animation.binarySearch(frames, time, 3);
/*  414 */       float prevX = frames[frame + -2];
/*  415 */       float prevY = frames[frame + -1];
/*  416 */       float frameTime = frames[frame];
/*  417 */       float percent = getCurvePercent(frame / 3 - 1, 1.0F - (time - frameTime) / (frames[frame + -3] - frameTime));
/*      */       
/*  419 */       bone.shearX += (bone.data.shearX + prevX + (frames[frame + 1] - prevX) * percent - bone.shearX) * alpha;
/*  420 */       bone.shearY += (bone.data.shearY + prevY + (frames[frame + 2] - prevY) * percent - bone.shearY) * alpha;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ColorTimeline extends CurveTimeline {
/*      */     public static final int ENTRIES = 5;
/*      */     private static final int PREV_TIME = -5;
/*      */     private static final int PREV_R = -4;
/*      */     private static final int PREV_G = -3;
/*      */     private static final int PREV_B = -2;
/*      */     private static final int PREV_A = -1;
/*      */     
/*      */     public ColorTimeline(int frameCount) {
/*  433 */       super(frameCount);
/*  434 */       this.frames = new float[frameCount * 5];
/*      */     }
/*      */     private static final int R = 1; private static final int G = 2; private static final int B = 3; private static final int A = 4; int slotIndex; private final float[] frames;
/*      */     public void setSlotIndex(int index) {
/*  438 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  439 */       this.slotIndex = index;
/*      */     }
/*      */     
/*      */     public int getSlotIndex() {
/*  443 */       return this.slotIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  447 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float r, float g, float b, float a) {
/*  452 */       frameIndex *= 5;
/*  453 */       this.frames[frameIndex] = time;
/*  454 */       this.frames[frameIndex + 1] = r;
/*  455 */       this.frames[frameIndex + 2] = g;
/*  456 */       this.frames[frameIndex + 3] = b;
/*  457 */       this.frames[frameIndex + 4] = a;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  461 */       float r, g, b, a, frames[] = this.frames;
/*  462 */       if (time < frames[0]) {
/*      */         return;
/*      */       }
/*  465 */       if (time >= frames[frames.length - 5]) {
/*  466 */         int i = frames.length;
/*  467 */         r = frames[i + -4];
/*  468 */         g = frames[i + -3];
/*  469 */         b = frames[i + -2];
/*  470 */         a = frames[i + -1];
/*      */       } else {
/*      */         
/*  473 */         int frame = Animation.binarySearch(frames, time, 5);
/*  474 */         r = frames[frame + -4];
/*  475 */         g = frames[frame + -3];
/*  476 */         b = frames[frame + -2];
/*  477 */         a = frames[frame + -1];
/*  478 */         float frameTime = frames[frame];
/*  479 */         float percent = getCurvePercent(frame / 5 - 1, 1.0F - (time - frameTime) / (frames[frame + -5] - frameTime));
/*      */ 
/*      */         
/*  482 */         r += (frames[frame + 1] - r) * percent;
/*  483 */         g += (frames[frame + 2] - g) * percent;
/*  484 */         b += (frames[frame + 3] - b) * percent;
/*  485 */         a += (frames[frame + 4] - a) * percent;
/*      */       } 
/*  487 */       Color color = ((Slot)skeleton.slots.get(this.slotIndex)).color;
/*  488 */       if (alpha < 1.0F) {
/*  489 */         color.add((r - color.r) * alpha, (g - color.g) * alpha, (b - color.b) * alpha, (a - color.a) * alpha);
/*      */       } else {
/*  491 */         color.set(r, g, b, a);
/*      */       } 
/*      */     } }
/*      */   
/*      */   public static class AttachmentTimeline implements Timeline {
/*      */     int slotIndex;
/*      */     final float[] frames;
/*      */     final String[] attachmentNames;
/*      */     
/*      */     public AttachmentTimeline(int frameCount) {
/*  501 */       this.frames = new float[frameCount];
/*  502 */       this.attachmentNames = new String[frameCount];
/*      */     }
/*      */     
/*      */     public int getFrameCount() {
/*  506 */       return this.frames.length;
/*      */     }
/*      */     
/*      */     public void setSlotIndex(int index) {
/*  510 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  511 */       this.slotIndex = index;
/*      */     }
/*      */     
/*      */     public int getSlotIndex() {
/*  515 */       return this.slotIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  519 */       return this.frames;
/*      */     }
/*      */     
/*      */     public String[] getAttachmentNames() {
/*  523 */       return this.attachmentNames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, String attachmentName) {
/*  528 */       this.frames[frameIndex] = time;
/*  529 */       this.attachmentNames[frameIndex] = attachmentName;
/*      */     }
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*      */       int frameIndex;
/*  533 */       float[] frames = this.frames;
/*  534 */       if (time < frames[0]) {
/*      */         return;
/*      */       }
/*  537 */       if (time >= frames[frames.length - 1]) {
/*  538 */         frameIndex = frames.length - 1;
/*      */       } else {
/*  540 */         frameIndex = Animation.binarySearch(frames, time, 1) - 1;
/*      */       } 
/*  542 */       String attachmentName = this.attachmentNames[frameIndex];
/*  543 */       ((Slot)skeleton.slots.get(this.slotIndex))
/*  544 */         .setAttachment((attachmentName == null) ? null : skeleton.getAttachment(this.slotIndex, attachmentName));
/*      */     }
/*      */   }
/*      */   
/*      */   public static class EventTimeline implements Timeline {
/*      */     private final float[] frames;
/*      */     private final Event[] events;
/*      */     
/*      */     public EventTimeline(int frameCount) {
/*  553 */       this.frames = new float[frameCount];
/*  554 */       this.events = new Event[frameCount];
/*      */     }
/*      */     
/*      */     public int getFrameCount() {
/*  558 */       return this.frames.length;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  562 */       return this.frames;
/*      */     }
/*      */     
/*      */     public Event[] getEvents() {
/*  566 */       return this.events;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, Event event) {
/*  571 */       this.frames[frameIndex] = event.time;
/*  572 */       this.events[frameIndex] = event;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> firedEvents, float alpha) {
/*      */       int frame;
/*  577 */       if (firedEvents == null)
/*  578 */         return;  float[] frames = this.frames;
/*  579 */       int frameCount = frames.length;
/*      */       
/*  581 */       if (lastTime > time) {
/*  582 */         apply(skeleton, lastTime, 2.14748365E9F, firedEvents, alpha);
/*  583 */         lastTime = -1.0F;
/*  584 */       } else if (lastTime >= frames[frameCount - 1]) {
/*      */         return;
/*  586 */       }  if (time < frames[0]) {
/*      */         return;
/*      */       }
/*  589 */       if (lastTime < frames[0]) {
/*  590 */         frame = 0;
/*      */       } else {
/*  592 */         frame = Animation.binarySearch(frames, lastTime);
/*  593 */         float frameTime = frames[frame];
/*  594 */         while (frame > 0 && 
/*  595 */           frames[frame - 1] == frameTime) {
/*  596 */           frame--;
/*      */         }
/*      */       } 
/*  599 */       for (; frame < frameCount && time >= frames[frame]; frame++)
/*  600 */         firedEvents.add(this.events[frame]); 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DrawOrderTimeline implements Timeline {
/*      */     private final float[] frames;
/*      */     private final int[][] drawOrders;
/*      */     
/*      */     public DrawOrderTimeline(int frameCount) {
/*  609 */       this.frames = new float[frameCount];
/*  610 */       this.drawOrders = new int[frameCount][];
/*      */     }
/*      */     
/*      */     public int getFrameCount() {
/*  614 */       return this.frames.length;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  618 */       return this.frames;
/*      */     }
/*      */     
/*      */     public int[][] getDrawOrders() {
/*  622 */       return this.drawOrders;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, int[] drawOrder) {
/*  628 */       this.frames[frameIndex] = time;
/*  629 */       this.drawOrders[frameIndex] = drawOrder;
/*      */     }
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> firedEvents, float alpha) {
/*      */       int frame;
/*  633 */       float[] frames = this.frames;
/*  634 */       if (time < frames[0]) {
/*      */         return;
/*      */       }
/*  637 */       if (time >= frames[frames.length - 1]) {
/*  638 */         frame = frames.length - 1;
/*      */       } else {
/*  640 */         frame = Animation.binarySearch(frames, time) - 1;
/*      */       } 
/*  642 */       Array<Slot> drawOrder = skeleton.drawOrder;
/*  643 */       Array<Slot> slots = skeleton.slots;
/*  644 */       int[] drawOrderToSetupIndex = this.drawOrders[frame];
/*  645 */       if (drawOrderToSetupIndex == null) {
/*  646 */         System.arraycopy(slots.items, 0, drawOrder.items, 0, slots.size);
/*      */       } else {
/*  648 */         for (int i = 0, n = drawOrderToSetupIndex.length; i < n; i++)
/*  649 */           drawOrder.set(i, slots.get(drawOrderToSetupIndex[i])); 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DeformTimeline extends CurveTimeline {
/*      */     private final float[] frames;
/*      */     private final float[][] frameVertices;
/*      */     int slotIndex;
/*      */     VertexAttachment attachment;
/*      */     
/*      */     public DeformTimeline(int frameCount) {
/*  661 */       super(frameCount);
/*  662 */       this.frames = new float[frameCount];
/*  663 */       this.frameVertices = new float[frameCount][];
/*      */     }
/*      */     
/*      */     public void setSlotIndex(int index) {
/*  667 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  668 */       this.slotIndex = index;
/*      */     }
/*      */     
/*      */     public int getSlotIndex() {
/*  672 */       return this.slotIndex;
/*      */     }
/*      */     
/*      */     public void setAttachment(VertexAttachment attachment) {
/*  676 */       this.attachment = attachment;
/*      */     }
/*      */     
/*      */     public Attachment getAttachment() {
/*  680 */       return (Attachment)this.attachment;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  684 */       return this.frames;
/*      */     }
/*      */     
/*      */     public float[][] getVertices() {
/*  688 */       return this.frameVertices;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float[] vertices) {
/*  693 */       this.frames[frameIndex] = time;
/*  694 */       this.frameVertices[frameIndex] = vertices;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> firedEvents, float alpha) {
/*  698 */       Slot slot = (Slot)skeleton.slots.get(this.slotIndex);
/*  699 */       Attachment slotAttachment = slot.attachment;
/*  700 */       if (!(slotAttachment instanceof VertexAttachment) || !((VertexAttachment)slotAttachment).applyDeform(this.attachment))
/*      */         return; 
/*  702 */       float[] frames = this.frames;
/*  703 */       if (time < frames[0])
/*      */         return; 
/*  705 */       float[][] frameVertices = this.frameVertices;
/*  706 */       int vertexCount = (frameVertices[0]).length;
/*      */       
/*  708 */       FloatArray verticesArray = slot.getAttachmentVertices();
/*  709 */       if (verticesArray.size != vertexCount) alpha = 1.0F; 
/*  710 */       float[] vertices = verticesArray.setSize(vertexCount);
/*      */       
/*  712 */       if (time >= frames[frames.length - 1]) {
/*  713 */         float[] lastVertices = frameVertices[frames.length - 1];
/*  714 */         if (alpha < 1.0F) {
/*  715 */           for (int i = 0; i < vertexCount; i++)
/*  716 */             vertices[i] = vertices[i] + (lastVertices[i] - vertices[i]) * alpha; 
/*      */         } else {
/*  718 */           System.arraycopy(lastVertices, 0, vertices, 0, vertexCount);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*  723 */       int frame = Animation.binarySearch(frames, time);
/*  724 */       float[] prevVertices = frameVertices[frame - 1];
/*  725 */       float[] nextVertices = frameVertices[frame];
/*  726 */       float frameTime = frames[frame];
/*  727 */       float percent = getCurvePercent(frame - 1, 1.0F - (time - frameTime) / (frames[frame - 1] - frameTime));
/*      */       
/*  729 */       if (alpha < 1.0F) {
/*  730 */         for (int i = 0; i < vertexCount; i++) {
/*  731 */           float prev = prevVertices[i];
/*  732 */           vertices[i] = vertices[i] + (prev + (nextVertices[i] - prev) * percent - vertices[i]) * alpha;
/*      */         } 
/*      */       } else {
/*  735 */         for (int i = 0; i < vertexCount; i++) {
/*  736 */           float prev = prevVertices[i];
/*  737 */           vertices[i] = prev + (nextVertices[i] - prev) * percent;
/*      */         } 
/*      */       } 
/*      */     } }
/*      */   
/*      */   public static class IkConstraintTimeline extends CurveTimeline { public static final int ENTRIES = 3;
/*      */     private static final int PREV_TIME = -3;
/*      */     private static final int PREV_MIX = -2;
/*      */     private static final int PREV_BEND_DIRECTION = -1;
/*      */     private static final int MIX = 1;
/*      */     private static final int BEND_DIRECTION = 2;
/*      */     int ikConstraintIndex;
/*      */     private final float[] frames;
/*      */     
/*      */     public IkConstraintTimeline(int frameCount) {
/*  752 */       super(frameCount);
/*  753 */       this.frames = new float[frameCount * 3];
/*      */     }
/*      */     
/*      */     public void setIkConstraintIndex(int index) {
/*  757 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  758 */       this.ikConstraintIndex = index;
/*      */     }
/*      */     
/*      */     public int getIkConstraintIndex() {
/*  762 */       return this.ikConstraintIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  766 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float mix, int bendDirection) {
/*  771 */       frameIndex *= 3;
/*  772 */       this.frames[frameIndex] = time;
/*  773 */       this.frames[frameIndex + 1] = mix;
/*  774 */       this.frames[frameIndex + 2] = bendDirection;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  778 */       float[] frames = this.frames;
/*  779 */       if (time < frames[0])
/*      */         return; 
/*  781 */       IkConstraint constraint = (IkConstraint)skeleton.ikConstraints.get(this.ikConstraintIndex);
/*      */       
/*  783 */       if (time >= frames[frames.length - 3]) {
/*  784 */         constraint.mix += (frames[frames.length + -2] - constraint.mix) * alpha;
/*  785 */         constraint.bendDirection = (int)frames[frames.length + -1];
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  790 */       int frame = Animation.binarySearch(frames, time, 3);
/*  791 */       float mix = frames[frame + -2];
/*  792 */       float frameTime = frames[frame];
/*  793 */       float percent = getCurvePercent(frame / 3 - 1, 1.0F - (time - frameTime) / (frames[frame + -3] - frameTime));
/*      */       
/*  795 */       constraint.mix += (mix + (frames[frame + 1] - mix) * percent - constraint.mix) * alpha;
/*  796 */       constraint.bendDirection = (int)frames[frame + -1];
/*      */     } }
/*      */   public static class TransformConstraintTimeline extends CurveTimeline { public static final int ENTRIES = 5; private static final int PREV_TIME = -5; private static final int PREV_ROTATE = -4; private static final int PREV_TRANSLATE = -3;
/*      */     private static final int PREV_SCALE = -2;
/*      */     private static final int PREV_SHEAR = -1;
/*      */     private static final int ROTATE = 1;
/*      */     private static final int TRANSLATE = 2;
/*      */     private static final int SCALE = 3;
/*      */     private static final int SHEAR = 4;
/*      */     int transformConstraintIndex;
/*      */     private final float[] frames;
/*      */     
/*      */     public TransformConstraintTimeline(int frameCount) {
/*  809 */       super(frameCount);
/*  810 */       this.frames = new float[frameCount * 5];
/*      */     }
/*      */     
/*      */     public void setTransformConstraintIndex(int index) {
/*  814 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  815 */       this.transformConstraintIndex = index;
/*      */     }
/*      */     
/*      */     public int getTransformConstraintIndex() {
/*  819 */       return this.transformConstraintIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  823 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float rotateMix, float translateMix, float scaleMix, float shearMix) {
/*  828 */       frameIndex *= 5;
/*  829 */       this.frames[frameIndex] = time;
/*  830 */       this.frames[frameIndex + 1] = rotateMix;
/*  831 */       this.frames[frameIndex + 2] = translateMix;
/*  832 */       this.frames[frameIndex + 3] = scaleMix;
/*  833 */       this.frames[frameIndex + 4] = shearMix;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  837 */       float[] frames = this.frames;
/*  838 */       if (time < frames[0])
/*      */         return; 
/*  840 */       TransformConstraint constraint = (TransformConstraint)skeleton.transformConstraints.get(this.transformConstraintIndex);
/*      */       
/*  842 */       if (time >= frames[frames.length - 5]) {
/*  843 */         int i = frames.length;
/*  844 */         constraint.rotateMix += (frames[i + -4] - constraint.rotateMix) * alpha;
/*  845 */         constraint.translateMix += (frames[i + -3] - constraint.translateMix) * alpha;
/*  846 */         constraint.scaleMix += (frames[i + -2] - constraint.scaleMix) * alpha;
/*  847 */         constraint.shearMix += (frames[i + -1] - constraint.shearMix) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  852 */       int frame = Animation.binarySearch(frames, time, 5);
/*  853 */       float frameTime = frames[frame];
/*  854 */       float percent = getCurvePercent(frame / 5 - 1, 1.0F - (time - frameTime) / (frames[frame + -5] - frameTime));
/*      */       
/*  856 */       float rotate = frames[frame + -4];
/*  857 */       float translate = frames[frame + -3];
/*  858 */       float scale = frames[frame + -2];
/*  859 */       float shear = frames[frame + -1];
/*  860 */       constraint.rotateMix += (rotate + (frames[frame + 1] - rotate) * percent - constraint.rotateMix) * alpha;
/*  861 */       constraint.translateMix += (translate + (frames[frame + 2] - translate) * percent - constraint.translateMix) * alpha;
/*      */       
/*  863 */       constraint.scaleMix += (scale + (frames[frame + 3] - scale) * percent - constraint.scaleMix) * alpha;
/*  864 */       constraint.shearMix += (shear + (frames[frame + 4] - shear) * percent - constraint.shearMix) * alpha;
/*      */     } }
/*      */ 
/*      */   
/*      */   public static class PathConstraintPositionTimeline
/*      */     extends CurveTimeline {
/*      */     public static final int ENTRIES = 2;
/*      */     static final int PREV_TIME = -2;
/*      */     static final int PREV_VALUE = -1;
/*      */     static final int VALUE = 1;
/*      */     int pathConstraintIndex;
/*      */     final float[] frames;
/*      */     
/*      */     public PathConstraintPositionTimeline(int frameCount) {
/*  878 */       super(frameCount);
/*  879 */       this.frames = new float[frameCount * 2];
/*      */     }
/*      */     
/*      */     public void setPathConstraintIndex(int index) {
/*  883 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  884 */       this.pathConstraintIndex = index;
/*      */     }
/*      */     
/*      */     public int getPathConstraintIndex() {
/*  888 */       return this.pathConstraintIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  892 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float value) {
/*  897 */       frameIndex *= 2;
/*  898 */       this.frames[frameIndex] = time;
/*  899 */       this.frames[frameIndex + 1] = value;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  903 */       float[] frames = this.frames;
/*  904 */       if (time < frames[0])
/*      */         return; 
/*  906 */       PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
/*      */       
/*  908 */       if (time >= frames[frames.length - 2]) {
/*  909 */         int i = frames.length;
/*  910 */         constraint.position += (frames[i + -1] - constraint.position) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  915 */       int frame = Animation.binarySearch(frames, time, 2);
/*  916 */       float position = frames[frame + -1];
/*  917 */       float frameTime = frames[frame];
/*  918 */       float percent = getCurvePercent(frame / 2 - 1, 1.0F - (time - frameTime) / (frames[frame + -2] - frameTime));
/*      */       
/*  920 */       constraint.position += (position + (frames[frame + 1] - position) * percent - constraint.position) * alpha;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class PathConstraintSpacingTimeline extends PathConstraintPositionTimeline {
/*      */     public PathConstraintSpacingTimeline(int frameCount) {
/*  926 */       super(frameCount);
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  930 */       float[] frames = this.frames;
/*  931 */       if (time < frames[0])
/*      */         return; 
/*  933 */       PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
/*      */       
/*  935 */       if (time >= frames[frames.length - 2]) {
/*  936 */         int i = frames.length;
/*  937 */         constraint.spacing += (frames[i + -1] - constraint.spacing) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  942 */       int frame = Animation.binarySearch(frames, time, 2);
/*  943 */       float spacing = frames[frame + -1];
/*  944 */       float frameTime = frames[frame];
/*  945 */       float percent = getCurvePercent(frame / 2 - 1, 1.0F - (time - frameTime) / (frames[frame + -2] - frameTime));
/*      */       
/*  947 */       constraint.spacing += (spacing + (frames[frame + 1] - spacing) * percent - constraint.spacing) * alpha;
/*      */     } }
/*      */   
/*      */   public static class PathConstraintMixTimeline extends CurveTimeline {
/*      */     public static final int ENTRIES = 3;
/*      */     private static final int PREV_TIME = -3;
/*      */     private static final int PREV_ROTATE = -2;
/*      */     private static final int PREV_TRANSLATE = -1;
/*      */     private static final int ROTATE = 1;
/*      */     private static final int TRANSLATE = 2;
/*      */     int pathConstraintIndex;
/*      */     private final float[] frames;
/*      */     
/*      */     public PathConstraintMixTimeline(int frameCount) {
/*  961 */       super(frameCount);
/*  962 */       this.frames = new float[frameCount * 3];
/*      */     }
/*      */     
/*      */     public void setPathConstraintIndex(int index) {
/*  966 */       if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/*  967 */       this.pathConstraintIndex = index;
/*      */     }
/*      */     
/*      */     public int getPathConstraintIndex() {
/*  971 */       return this.pathConstraintIndex;
/*      */     }
/*      */     
/*      */     public float[] getFrames() {
/*  975 */       return this.frames;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setFrame(int frameIndex, float time, float rotateMix, float translateMix) {
/*  980 */       frameIndex *= 3;
/*  981 */       this.frames[frameIndex] = time;
/*  982 */       this.frames[frameIndex + 1] = rotateMix;
/*  983 */       this.frames[frameIndex + 2] = translateMix;
/*      */     }
/*      */     
/*      */     public void apply(Skeleton skeleton, float lastTime, float time, Array<Event> events, float alpha) {
/*  987 */       float[] frames = this.frames;
/*  988 */       if (time < frames[0])
/*      */         return; 
/*  990 */       PathConstraint constraint = (PathConstraint)skeleton.pathConstraints.get(this.pathConstraintIndex);
/*      */       
/*  992 */       if (time >= frames[frames.length - 3]) {
/*  993 */         int i = frames.length;
/*  994 */         constraint.rotateMix += (frames[i + -2] - constraint.rotateMix) * alpha;
/*  995 */         constraint.translateMix += (frames[i + -1] - constraint.translateMix) * alpha;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 1000 */       int frame = Animation.binarySearch(frames, time, 3);
/* 1001 */       float rotate = frames[frame + -2];
/* 1002 */       float translate = frames[frame + -1];
/* 1003 */       float frameTime = frames[frame];
/* 1004 */       float percent = getCurvePercent(frame / 3 - 1, 1.0F - (time - frameTime) / (frames[frame + -3] - frameTime));
/*      */       
/* 1006 */       constraint.rotateMix += (rotate + (frames[frame + 1] - rotate) * percent - constraint.rotateMix) * alpha;
/* 1007 */       constraint.translateMix += (translate + (frames[frame + 2] - translate) * percent - constraint.translateMix) * alpha;
/*      */     }
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\Animation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */