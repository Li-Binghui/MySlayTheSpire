/*     */ package com.esotericsoftware.spine;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.spine.attachments.Attachment;
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
/*     */ public class Slot
/*     */ {
/*     */   final SlotData data;
/*     */   final Bone bone;
/*     */   final Color color;
/*     */   Attachment attachment;
/*     */   private float attachmentTime;
/*  44 */   private FloatArray attachmentVertices = new FloatArray();
/*     */   
/*     */   public Slot(SlotData data, Bone bone) {
/*  47 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/*  48 */     if (bone == null) throw new IllegalArgumentException("bone cannot be null."); 
/*  49 */     this.data = data;
/*  50 */     this.bone = bone;
/*  51 */     this.color = new Color();
/*  52 */     setToSetupPose();
/*     */   }
/*     */ 
/*     */   
/*     */   public Slot(Slot slot, Bone bone) {
/*  57 */     if (slot == null) throw new IllegalArgumentException("slot cannot be null."); 
/*  58 */     if (bone == null) throw new IllegalArgumentException("bone cannot be null."); 
/*  59 */     this.data = slot.data;
/*  60 */     this.bone = bone;
/*  61 */     this.color = new Color(slot.color);
/*  62 */     this.attachment = slot.attachment;
/*  63 */     this.attachmentTime = slot.attachmentTime;
/*     */   }
/*     */   
/*     */   public SlotData getData() {
/*  67 */     return this.data;
/*     */   }
/*     */   
/*     */   public Bone getBone() {
/*  71 */     return this.bone;
/*     */   }
/*     */   
/*     */   public Skeleton getSkeleton() {
/*  75 */     return this.bone.skeleton;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/*  79 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public Attachment getAttachment() {
/*  84 */     return this.attachment;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttachment(Attachment attachment) {
/*  90 */     if (this.attachment == attachment)
/*  91 */       return;  this.attachment = attachment;
/*  92 */     this.attachmentTime = this.bone.skeleton.time;
/*  93 */     this.attachmentVertices.clear();
/*     */   }
/*     */   
/*     */   public void setAttachmentTime(float time) {
/*  97 */     this.attachmentTime = this.bone.skeleton.time - time;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAttachmentTime() {
/* 102 */     return this.bone.skeleton.time - this.attachmentTime;
/*     */   }
/*     */   
/*     */   public void setAttachmentVertices(FloatArray attachmentVertices) {
/* 106 */     if (attachmentVertices == null) throw new IllegalArgumentException("attachmentVertices cannot be null."); 
/* 107 */     this.attachmentVertices = attachmentVertices;
/*     */   }
/*     */   
/*     */   public FloatArray getAttachmentVertices() {
/* 111 */     return this.attachmentVertices;
/*     */   }
/*     */   
/*     */   public void setToSetupPose() {
/* 115 */     this.color.set(this.data.color);
/* 116 */     if (this.data.attachmentName == null) {
/* 117 */       setAttachment(null);
/*     */     } else {
/* 119 */       this.attachment = null;
/* 120 */       setAttachment(this.bone.skeleton.getAttachment(this.data.index, this.data.attachmentName));
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 125 */     return this.data.name;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\Slot.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */