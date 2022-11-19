/*    */ package com.esotericsoftware.spine.attachments;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.esotericsoftware.spine.Slot;
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
/*    */ public class PathAttachment
/*    */   extends VertexAttachment
/*    */ {
/*    */   float[] lengths;
/*    */   boolean closed;
/*    */   boolean constantSpeed;
/* 42 */   final Color color = new Color(1.0F, 0.5F, 0.0F, 1.0F);
/*    */   
/*    */   public PathAttachment(String name) {
/* 45 */     super(name);
/*    */   }
/*    */   
/*    */   public void computeWorldVertices(Slot slot, float[] worldVertices) {
/* 49 */     super.computeWorldVertices(slot, worldVertices);
/*    */   }
/*    */   
/*    */   public void computeWorldVertices(Slot slot, int start, int count, float[] worldVertices, int offset) {
/* 53 */     super.computeWorldVertices(slot, start, count, worldVertices, offset);
/*    */   }
/*    */   
/*    */   public boolean getClosed() {
/* 57 */     return this.closed;
/*    */   }
/*    */   
/*    */   public void setClosed(boolean closed) {
/* 61 */     this.closed = closed;
/*    */   }
/*    */   
/*    */   public boolean getConstantSpeed() {
/* 65 */     return this.constantSpeed;
/*    */   }
/*    */   
/*    */   public void setConstantSpeed(boolean constantSpeed) {
/* 69 */     this.constantSpeed = constantSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getLengths() {
/* 74 */     return this.lengths;
/*    */   }
/*    */   
/*    */   public void setLengths(float[] lengths) {
/* 78 */     this.lengths = lengths;
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 82 */     return this.color;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\PathAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */