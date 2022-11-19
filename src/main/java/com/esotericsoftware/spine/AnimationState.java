/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */ import com.badlogic.gdx.utils.Array;
/*     */ import com.badlogic.gdx.utils.Pool;
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
/*     */ public class AnimationState
/*     */ {
/*     */   private AnimationStateData data;
/*  41 */   private Array<TrackEntry> tracks = new Array();
/*  42 */   private final Array<Event> events = new Array();
/*  43 */   private final Array<AnimationStateListener> listeners = new Array();
/*  44 */   private float timeScale = 1.0F;
/*     */   
/*  46 */   private Pool<TrackEntry> trackEntryPool = new Pool() {
/*     */       protected Object newObject() {
/*  48 */         return new TrackEntry();
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public AnimationState() {}
/*     */ 
/*     */   
/*     */   public AnimationState(AnimationStateData data) {
/*  57 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  58 */     this.data = data;
/*     */   }
/*     */   
/*     */   public void update(float delta) {
/*  62 */     delta *= this.timeScale;
/*  63 */     for (int i = 0; i < this.tracks.size; i++) {
/*  64 */       TrackEntry current = (TrackEntry)this.tracks.get(i);
/*  65 */       if (current == null)
/*     */         continue; 
/*  67 */       TrackEntry next = current.next;
/*  68 */       if (next != null) {
/*  69 */         float nextTime = current.lastTime - next.delay;
/*  70 */         if (nextTime >= 0.0F) {
/*  71 */           float nextDelta = delta * next.timeScale;
/*  72 */           next.time = nextTime + nextDelta;
/*  73 */           current.time += delta * current.timeScale;
/*  74 */           setCurrent(i, next);
/*  75 */           next.time -= nextDelta;
/*  76 */           current = next;
/*     */         } 
/*  78 */       } else if (!current.loop && current.lastTime >= current.endTime) {
/*     */         
/*  80 */         clearTrack(i);
/*     */         
/*     */         continue;
/*     */       } 
/*  84 */       current.time += delta * current.timeScale;
/*  85 */       if (current.previous != null) {
/*  86 */         float previousDelta = delta * current.previous.timeScale;
/*  87 */         current.previous.time += previousDelta;
/*  88 */         current.mixTime += previousDelta;
/*     */       } 
/*     */       continue;
/*     */     } 
/*     */   }
/*     */   public void apply(Skeleton skeleton) {
/*  94 */     Array<Event> events = this.events;
/*  95 */     int listenerCount = this.listeners.size;
/*     */     
/*  97 */     for (int i = 0; i < this.tracks.size; i++) {
/*  98 */       TrackEntry current = (TrackEntry)this.tracks.get(i);
/*  99 */       if (current != null) {
/*     */         
/* 101 */         events.size = 0;
/*     */         
/* 103 */         float time = current.time;
/* 104 */         float lastTime = current.lastTime;
/* 105 */         float endTime = current.endTime;
/* 106 */         boolean loop = current.loop;
/* 107 */         if (!loop && time > endTime) time = endTime;
/*     */         
/* 109 */         TrackEntry previous = current.previous;
/* 110 */         if (previous == null) {
/* 111 */           current.animation.mix(skeleton, lastTime, time, loop, events, current.mix);
/*     */         } else {
/* 113 */           float previousTime = previous.time;
/* 114 */           if (!previous.loop && previousTime > previous.endTime) previousTime = previous.endTime; 
/* 115 */           previous.animation.apply(skeleton, previousTime, previousTime, previous.loop, null);
/*     */           
/* 117 */           float alpha = current.mixTime / current.mixDuration * current.mix;
/* 118 */           if (alpha >= 1.0F) {
/* 119 */             alpha = 1.0F;
/* 120 */             this.trackEntryPool.free(previous);
/* 121 */             current.previous = null;
/*     */           } 
/* 123 */           current.animation.mix(skeleton, lastTime, time, loop, events, alpha);
/*     */         } 
/*     */         
/* 126 */         for (int ii = 0, nn = events.size; ii < nn; ii++) {
/* 127 */           Event event = (Event)events.get(ii);
/* 128 */           if (current.listener != null) current.listener.event(i, event); 
/* 129 */           for (int iii = 0; iii < listenerCount; iii++) {
/* 130 */             ((AnimationStateListener)this.listeners.get(iii)).event(i, event);
/*     */           }
/*     */         } 
/*     */         
/* 134 */         if (loop ? (lastTime % endTime > time % endTime) : (lastTime < endTime && time >= endTime)) {
/* 135 */           int count = (int)(time / endTime);
/* 136 */           if (current.listener != null) current.listener.complete(i, count); 
/* 137 */           for (int j = 0, k = this.listeners.size; j < k; j++) {
/* 138 */             ((AnimationStateListener)this.listeners.get(j)).complete(i, count);
/*     */           }
/*     */         } 
/* 141 */         current.lastTime = current.time;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void clearTracks() {
/* 146 */     for (int i = 0, n = this.tracks.size; i < n; i++)
/* 147 */       clearTrack(i); 
/* 148 */     this.tracks.clear();
/*     */   }
/*     */   
/*     */   public void clearTrack(int trackIndex) {
/* 152 */     if (trackIndex >= this.tracks.size)
/* 153 */       return;  TrackEntry current = (TrackEntry)this.tracks.get(trackIndex);
/* 154 */     if (current == null)
/*     */       return; 
/* 156 */     if (current.listener != null) current.listener.end(trackIndex); 
/* 157 */     for (int i = 0, n = this.listeners.size; i < n; i++) {
/* 158 */       ((AnimationStateListener)this.listeners.get(i)).end(trackIndex);
/*     */     }
/* 160 */     this.tracks.set(trackIndex, null);
/*     */     
/* 162 */     freeAll(current);
/* 163 */     if (current.previous != null) this.trackEntryPool.free(current.previous); 
/*     */   }
/*     */   
/*     */   private void freeAll(TrackEntry entry) {
/* 167 */     while (entry != null) {
/* 168 */       TrackEntry next = entry.next;
/* 169 */       this.trackEntryPool.free(entry);
/* 170 */       entry = next;
/*     */     } 
/*     */   }
/*     */   
/*     */   private TrackEntry expandToIndex(int index) {
/* 175 */     if (index < this.tracks.size) return (TrackEntry)this.tracks.get(index); 
/* 176 */     this.tracks.ensureCapacity(index - this.tracks.size + 1);
/* 177 */     this.tracks.size = index + 1;
/* 178 */     return null;
/*     */   }
/*     */   
/*     */   private void setCurrent(int index, TrackEntry entry) {
/* 182 */     TrackEntry current = expandToIndex(index);
/* 183 */     if (current != null) {
/* 184 */       TrackEntry previous = current.previous;
/* 185 */       current.previous = null;
/*     */       
/* 187 */       if (current.listener != null) current.listener.end(index); 
/* 188 */       for (int j = 0, k = this.listeners.size; j < k; j++) {
/* 189 */         ((AnimationStateListener)this.listeners.get(j)).end(index);
/*     */       }
/* 191 */       entry.mixDuration = this.data.getMix(current.animation, entry.animation);
/* 192 */       if (entry.mixDuration > 0.0F)
/* 193 */       { entry.mixTime = 0.0F;
/*     */         
/* 195 */         if (previous != null && current.mixTime / current.mixDuration < 0.5F) {
/* 196 */           entry.previous = previous;
/* 197 */           previous = current;
/*     */         } else {
/* 199 */           entry.previous = current;
/*     */         }  }
/* 201 */       else { this.trackEntryPool.free(current); }
/*     */       
/* 203 */       if (previous != null) this.trackEntryPool.free(previous);
/*     */     
/*     */     } 
/* 206 */     this.tracks.set(index, entry);
/*     */     
/* 208 */     if (entry.listener != null) entry.listener.start(index); 
/* 209 */     for (int i = 0, n = this.listeners.size; i < n; i++) {
/* 210 */       ((AnimationStateListener)this.listeners.get(i)).start(index);
/*     */     }
/*     */   }
/*     */   
/*     */   public TrackEntry setAnimation(int trackIndex, String animationName, boolean loop) {
/* 215 */     Animation animation = this.data.getSkeletonData().findAnimation(animationName);
/* 216 */     if (animation == null) throw new IllegalArgumentException("Animation not found: " + animationName); 
/* 217 */     return setAnimation(trackIndex, animation, loop);
/*     */   }
/*     */ 
/*     */   
/*     */   public TrackEntry setAnimation(int trackIndex, Animation animation, boolean loop) {
/* 222 */     TrackEntry current = expandToIndex(trackIndex);
/* 223 */     if (current != null) freeAll(current.next);
/*     */     
/* 225 */     TrackEntry entry = (TrackEntry)this.trackEntryPool.obtain();
/* 226 */     entry.animation = animation;
/* 227 */     entry.loop = loop;
/* 228 */     entry.endTime = animation.getDuration();
/* 229 */     setCurrent(trackIndex, entry);
/* 230 */     return entry;
/*     */   }
/*     */ 
/*     */   
/*     */   public TrackEntry addAnimation(int trackIndex, String animationName, boolean loop, float delay) {
/* 235 */     Animation animation = this.data.getSkeletonData().findAnimation(animationName);
/* 236 */     if (animation == null) throw new IllegalArgumentException("Animation not found: " + animationName); 
/* 237 */     return addAnimation(trackIndex, animation, loop, delay);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TrackEntry addAnimation(int trackIndex, Animation animation, boolean loop, float delay) {
/* 243 */     TrackEntry entry = (TrackEntry)this.trackEntryPool.obtain();
/* 244 */     entry.animation = animation;
/* 245 */     entry.loop = loop;
/* 246 */     entry.endTime = animation.getDuration();
/*     */     
/* 248 */     TrackEntry last = expandToIndex(trackIndex);
/* 249 */     if (last != null) {
/* 250 */       while (last.next != null)
/* 251 */         last = last.next; 
/* 252 */       last.next = entry;
/*     */     } else {
/* 254 */       this.tracks.set(trackIndex, entry);
/*     */     } 
/* 256 */     if (delay <= 0.0F)
/* 257 */       if (last != null) {
/* 258 */         delay += last.endTime - this.data.getMix(last.animation, animation);
/*     */       } else {
/* 260 */         delay = 0.0F;
/*     */       }  
/* 262 */     entry.delay = delay;
/*     */     
/* 264 */     return entry;
/*     */   }
/*     */ 
/*     */   
/*     */   public TrackEntry getCurrent(int trackIndex) {
/* 269 */     if (trackIndex >= this.tracks.size) return null; 
/* 270 */     return (TrackEntry)this.tracks.get(trackIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addListener(AnimationStateListener listener) {
/* 275 */     if (listener == null) throw new IllegalArgumentException("listener cannot be null."); 
/* 276 */     this.listeners.add(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeListener(AnimationStateListener listener) {
/* 281 */     this.listeners.removeValue(listener, true);
/*     */   }
/*     */   
/*     */   public void clearListeners() {
/* 285 */     this.listeners.clear();
/*     */   }
/*     */   
/*     */   public float getTimeScale() {
/* 289 */     return this.timeScale;
/*     */   }
/*     */   
/*     */   public void setTimeScale(float timeScale) {
/* 293 */     this.timeScale = timeScale;
/*     */   }
/*     */   
/*     */   public AnimationStateData getData() {
/* 297 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(AnimationStateData data) {
/* 301 */     this.data = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Array<TrackEntry> getTracks() {
/* 306 */     return this.tracks;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 310 */     StringBuilder buffer = new StringBuilder(64);
/* 311 */     for (int i = 0, n = this.tracks.size; i < n; i++) {
/* 312 */       TrackEntry entry = (TrackEntry)this.tracks.get(i);
/* 313 */       if (entry != null) {
/* 314 */         if (buffer.length() > 0) buffer.append(", "); 
/* 315 */         buffer.append(entry.toString());
/*     */       } 
/* 317 */     }  if (buffer.length() == 0) return "<none>"; 
/* 318 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public static class TrackEntry implements Pool.Poolable {
/*     */     TrackEntry next;
/*     */     TrackEntry previous;
/*     */     Animation animation;
/* 325 */     float lastTime = -1.0F; boolean loop; float delay; float time; float endTime; float timeScale = 1.0F; float mixTime;
/*     */     float mixDuration;
/*     */     AnimationStateListener listener;
/* 328 */     float mix = 1.0F;
/*     */     
/*     */     public void reset() {
/* 331 */       this.next = null;
/* 332 */       this.previous = null;
/* 333 */       this.animation = null;
/* 334 */       this.listener = null;
/* 335 */       this.timeScale = 1.0F;
/* 336 */       this.lastTime = -1.0F;
/* 337 */       this.time = 0.0F;
/*     */     }
/*     */     
/*     */     public Animation getAnimation() {
/* 341 */       return this.animation;
/*     */     }
/*     */     
/*     */     public void setAnimation(Animation animation) {
/* 345 */       this.animation = animation;
/*     */     }
/*     */     
/*     */     public boolean getLoop() {
/* 349 */       return this.loop;
/*     */     }
/*     */     
/*     */     public void setLoop(boolean loop) {
/* 353 */       this.loop = loop;
/*     */     }
/*     */     
/*     */     public float getDelay() {
/* 357 */       return this.delay;
/*     */     }
/*     */     
/*     */     public void setDelay(float delay) {
/* 361 */       this.delay = delay;
/*     */     }
/*     */     
/*     */     public float getTime() {
/* 365 */       return this.time;
/*     */     }
/*     */     
/*     */     public void setTime(float time) {
/* 369 */       this.time = time;
/*     */     }
/*     */     
/*     */     public float getEndTime() {
/* 373 */       return this.endTime;
/*     */     }
/*     */     
/*     */     public void setEndTime(float endTime) {
/* 377 */       this.endTime = endTime;
/*     */     }
/*     */     
/*     */     public AnimationStateListener getListener() {
/* 381 */       return this.listener;
/*     */     }
/*     */     
/*     */     public void setListener(AnimationStateListener listener) {
/* 385 */       this.listener = listener;
/*     */     }
/*     */     
/*     */     public float getLastTime() {
/* 389 */       return this.lastTime;
/*     */     }
/*     */     
/*     */     public void setLastTime(float lastTime) {
/* 393 */       this.lastTime = lastTime;
/*     */     }
/*     */     
/*     */     public float getMix() {
/* 397 */       return this.mix;
/*     */     }
/*     */     
/*     */     public void setMix(float mix) {
/* 401 */       this.mix = mix;
/*     */     }
/*     */     
/*     */     public float getTimeScale() {
/* 405 */       return this.timeScale;
/*     */     }
/*     */     
/*     */     public void setTimeScale(float timeScale) {
/* 409 */       this.timeScale = timeScale;
/*     */     }
/*     */     
/*     */     public TrackEntry getNext() {
/* 413 */       return this.next;
/*     */     }
/*     */     
/*     */     public void setNext(TrackEntry next) {
/* 417 */       this.next = next;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isComplete() {
/* 422 */       return (this.time >= this.endTime);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 426 */       return (this.animation == null) ? "<none>" : this.animation.name;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface AnimationStateListener {
/*     */     void event(int param1Int, Event param1Event);
/*     */     
/*     */     void complete(int param1Int1, int param1Int2);
/*     */     
/*     */     void start(int param1Int);
/*     */     
/*     */     void end(int param1Int);
/*     */   }
/*     */   
/*     */   public static abstract class AnimationStateAdapter implements AnimationStateListener {
/*     */     public void event(int trackIndex, Event event) {}
/*     */     
/*     */     public void complete(int trackIndex, int loopCount) {}
/*     */     
/*     */     public void start(int trackIndex) {}
/*     */     
/*     */     public void end(int trackIndex) {}
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\AnimationState.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */