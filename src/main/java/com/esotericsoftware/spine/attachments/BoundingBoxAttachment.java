/*    */ package com.esotericsoftware.spine.attachments;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.Color;
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
/*    */ public class BoundingBoxAttachment
/*    */   extends VertexAttachment
/*    */ {
/* 39 */   final Color color = new Color(0.38F, 0.94F, 0.0F, 1.0F);
/*    */   
/*    */   public BoundingBoxAttachment(String name) {
/* 42 */     super(name);
/*    */   }
/*    */   
/*    */   public void computeWorldVertices(Slot slot, float[] worldVertices) {
/* 46 */     computeWorldVertices(slot, 0, this.worldVerticesLength, worldVertices, 0);
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 50 */     return this.color;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\BoundingBoxAttachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */