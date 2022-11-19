/*    */ package com.esotericsoftware.spine.attachments;
/*    */ 
/*    */ import com.esotericsoftware.spine.Skeleton;
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
/*    */ public class SkeletonAttachment
/*    */   extends Attachment
/*    */ {
/*    */   private Skeleton skeleton;
/*    */   
/*    */   public SkeletonAttachment(String name) {
/* 41 */     super(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public Skeleton getSkeleton() {
/* 46 */     return this.skeleton;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSkeleton(Skeleton skeleton) {
/* 51 */     this.skeleton = skeleton;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\SkeletonAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */