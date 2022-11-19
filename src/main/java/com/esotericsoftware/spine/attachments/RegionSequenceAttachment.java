/*    */ package com.esotericsoftware.spine.attachments;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.Slot;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegionSequenceAttachment
/*    */   extends RegionAttachment
/*    */ {
/*    */   private Mode mode;
/*    */   private float frameTime;
/*    */   private TextureRegion[] regions;
/*    */   
/*    */   public RegionSequenceAttachment(String name) {
/* 46 */     super(name);
/*    */   }
/*    */   
/*    */   public float[] updateWorldVertices(Slot slot, boolean premultipliedAlpha) {
/* 50 */     if (this.regions == null) throw new IllegalStateException("Regions have not been set: " + this);
/*    */     
/* 52 */     int frameIndex = (int)(slot.getAttachmentTime() / this.frameTime);
/* 53 */     switch (this.mode) {
/*    */       case forward:
/* 55 */         frameIndex = Math.min(this.regions.length - 1, frameIndex);
/*    */         break;
/*    */       case forwardLoop:
/* 58 */         frameIndex %= this.regions.length;
/*    */         break;
/*    */       case pingPong:
/* 61 */         frameIndex %= this.regions.length << 1;
/* 62 */         if (frameIndex >= this.regions.length) frameIndex = this.regions.length - 1 - frameIndex - this.regions.length; 
/*    */         break;
/*    */       case random:
/* 65 */         frameIndex = MathUtils.random(this.regions.length - 1);
/*    */         break;
/*    */       case backward:
/* 68 */         frameIndex = Math.max(this.regions.length - frameIndex - 1, 0);
/*    */         break;
/*    */       case backwardLoop:
/* 71 */         frameIndex %= this.regions.length;
/* 72 */         frameIndex = this.regions.length - frameIndex - 1;
/*    */         break;
/*    */     } 
/* 75 */     setRegion(this.regions[frameIndex]);
/*    */     
/* 77 */     return super.updateWorldVertices(slot, premultipliedAlpha);
/*    */   }
/*    */   
/*    */   public TextureRegion[] getRegions() {
/* 81 */     if (this.regions == null) throw new IllegalStateException("Regions have not been set: " + this); 
/* 82 */     return this.regions;
/*    */   }
/*    */   
/*    */   public void setRegions(TextureRegion[] regions) {
/* 86 */     this.regions = regions;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFrameTime(float frameTime) {
/* 91 */     this.frameTime = frameTime;
/*    */   }
/*    */   
/*    */   public void setMode(Mode mode) {
/* 95 */     this.mode = mode;
/*    */   }
/*    */   
/*    */   public enum Mode {
/* 99 */     forward, backward, forwardLoop, backwardLoop, pingPong, random;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\RegionSequenceAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */