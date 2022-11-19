/*    */ package com.esotericsoftware.spine;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
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
/*    */ public class SlotData
/*    */ {
/*    */   final int index;
/*    */   final String name;
/*    */   final BoneData boneData;
/* 40 */   final Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   String attachmentName;
/*    */   BlendMode blendMode;
/*    */   
/*    */   public SlotData(int index, String name, BoneData boneData) {
/* 45 */     if (index < 0) throw new IllegalArgumentException("index must be >= 0."); 
/* 46 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/* 47 */     if (boneData == null) throw new IllegalArgumentException("boneData cannot be null."); 
/* 48 */     this.index = index;
/* 49 */     this.name = name;
/* 50 */     this.boneData = boneData;
/*    */   }
/*    */   
/*    */   public int getIndex() {
/* 54 */     return this.index;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 58 */     return this.name;
/*    */   }
/*    */   
/*    */   public BoneData getBoneData() {
/* 62 */     return this.boneData;
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 66 */     return this.color;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttachmentName(String attachmentName) {
/* 71 */     this.attachmentName = attachmentName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAttachmentName() {
/* 76 */     return this.attachmentName;
/*    */   }
/*    */   
/*    */   public BlendMode getBlendMode() {
/* 80 */     return this.blendMode;
/*    */   }
/*    */   
/*    */   public void setBlendMode(BlendMode blendMode) {
/* 84 */     this.blendMode = blendMode;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 88 */     return this.name;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\SlotData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */