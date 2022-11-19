/*    */ package com.esotericsoftware.spine;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.Batch;
/*    */ import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
/*    */ import com.badlogic.gdx.utils.Array;
/*    */ import com.esotericsoftware.spine.attachments.Attachment;
/*    */ import com.esotericsoftware.spine.attachments.MeshAttachment;
/*    */ import com.esotericsoftware.spine.attachments.RegionAttachment;
/*    */ import com.esotericsoftware.spine.attachments.SkeletonAttachment;
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
/*    */ public class SkeletonMeshRenderer
/*    */   extends SkeletonRenderer<PolygonSpriteBatch>
/*    */ {
/* 43 */   private static final short[] quadTriangles = new short[] { 0, 1, 2, 2, 3, 0 };
/*    */ 
/*    */   
/*    */   public void draw(PolygonSpriteBatch batch, Skeleton skeleton) {
/* 47 */     boolean premultipliedAlpha = this.premultipliedAlpha;
/*    */     
/* 49 */     float[] vertices = null;
/* 50 */     short[] triangles = null;
/* 51 */     Array<Slot> drawOrder = skeleton.drawOrder;
/* 52 */     for (int i = 0, n = drawOrder.size; i < n; i++) {
/* 53 */       Slot slot = (Slot)drawOrder.get(i);
/* 54 */       Attachment attachment = slot.attachment;
/* 55 */       Texture texture = null;
/* 56 */       if (attachment instanceof RegionAttachment) {
/* 57 */         RegionAttachment region = (RegionAttachment)attachment;
/* 58 */         vertices = region.updateWorldVertices(slot, premultipliedAlpha);
/* 59 */         triangles = quadTriangles;
/* 60 */         texture = region.getRegion().getTexture();
/*    */       }
/* 62 */       else if (attachment instanceof MeshAttachment) {
/* 63 */         MeshAttachment mesh = (MeshAttachment)attachment;
/* 64 */         vertices = mesh.updateWorldVertices(slot, premultipliedAlpha);
/* 65 */         triangles = mesh.getTriangles();
/* 66 */         texture = mesh.getRegion().getTexture();
/*    */       }
/* 68 */       else if (attachment instanceof SkeletonAttachment) {
/* 69 */         Skeleton attachmentSkeleton = ((SkeletonAttachment)attachment).getSkeleton();
/* 70 */         if (attachmentSkeleton == null)
/* 71 */           continue;  Bone bone = slot.getBone();
/* 72 */         Bone rootBone = attachmentSkeleton.getRootBone();
/* 73 */         float oldScaleX = rootBone.getScaleX();
/* 74 */         float oldScaleY = rootBone.getScaleY();
/* 75 */         float oldRotation = rootBone.getRotation();
/* 76 */         attachmentSkeleton.setPosition(skeleton.getX() + bone.getWorldX(), skeleton.getY() + bone.getWorldY());
/*    */ 
/*    */ 
/*    */         
/* 80 */         rootBone.setRotation(oldRotation + bone.getWorldRotationX());
/* 81 */         attachmentSkeleton.updateWorldTransform();
/*    */         
/* 83 */         draw(batch, attachmentSkeleton);
/*    */         
/* 85 */         attachmentSkeleton.setPosition(0.0F, 0.0F);
/* 86 */         rootBone.setScaleX(oldScaleX);
/* 87 */         rootBone.setScaleY(oldScaleY);
/* 88 */         rootBone.setRotation(oldRotation);
/*    */       } 
/*    */       
/* 91 */       if (texture != null) {
/* 92 */         BlendMode blendMode = slot.data.getBlendMode();
/* 93 */         batch.setBlendFunction(blendMode.getSource(premultipliedAlpha), blendMode.getDest());
/* 94 */         batch.draw(texture, vertices, 0, vertices.length, triangles, 0, triangles.length);
/*    */       } 
/*    */       continue;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SkeletonMeshRenderer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */