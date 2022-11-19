/*    */ package com.esotericsoftware.spine;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.esotericsoftware.spine.attachments.SkeletonAttachment;
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
/*    */ public class SkeletonRenderer<T extends Batch>
/*    */ {
/*    */   boolean premultipliedAlpha;
/*    */   
/*    */   public void draw(T batch, Skeleton skeleton) {
/* 45 */     boolean premultipliedAlpha = this.premultipliedAlpha;
/*    */     
/* 47 */     Array<Slot> drawOrder = skeleton.drawOrder;
/* 48 */     for (int i = 0, n = drawOrder.size; i < n; i++) {
/* 49 */       Slot slot = (Slot)drawOrder.get(i);
/* 50 */       Attachment attachment = slot.attachment;
/* 51 */       if (attachment instanceof RegionAttachment) {
/* 52 */         RegionAttachment regionAttachment = (RegionAttachment)attachment;
/* 53 */         float[] vertices = regionAttachment.updateWorldVertices(slot, premultipliedAlpha);
/* 54 */         BlendMode blendMode = slot.data.getBlendMode();
/* 55 */         batch.setBlendFunction(blendMode.getSource(premultipliedAlpha), blendMode.getDest());
/* 56 */         batch.draw(regionAttachment.getRegion().getTexture(), vertices, 0, 20);
/*    */       } else {
/* 58 */         if (attachment instanceof com.esotericsoftware.spine.attachments.MeshAttachment) {
/* 59 */           throw new RuntimeException("SkeletonMeshRenderer is required to render meshes.");
/*    */         }
/* 61 */         if (attachment instanceof SkeletonAttachment) {
/* 62 */           Skeleton attachmentSkeleton = ((SkeletonAttachment)attachment).getSkeleton();
/* 63 */           if (attachmentSkeleton != null) {
/* 64 */             Bone bone = slot.getBone();
/* 65 */             Bone rootBone = attachmentSkeleton.getRootBone();
/* 66 */             float oldScaleX = rootBone.getScaleX();
/* 67 */             float oldScaleY = rootBone.getScaleY();
/* 68 */             float oldRotation = rootBone.getRotation();
/* 69 */             attachmentSkeleton.setPosition(skeleton.getX() + bone.getWorldX(), skeleton.getY() + bone.getWorldY());
/*    */ 
/*    */ 
/*    */             
/* 73 */             rootBone.setRotation(oldRotation + bone.getWorldRotationX());
/* 74 */             attachmentSkeleton.updateWorldTransform();
/*    */             
/* 76 */             draw(batch, attachmentSkeleton);
/*    */             
/* 78 */             attachmentSkeleton.setX(0.0F);
/* 79 */             attachmentSkeleton.setY(0.0F);
/* 80 */             rootBone.setScaleX(oldScaleX);
/* 81 */             rootBone.setScaleY(oldScaleY);
/* 82 */             rootBone.setRotation(oldRotation);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   } public void setPremultipliedAlpha(boolean premultipliedAlpha) {
/* 88 */     this.premultipliedAlpha = premultipliedAlpha;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */