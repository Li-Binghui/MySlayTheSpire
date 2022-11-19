/*    */ package com.esotericsoftware.spine.attachments;
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
/*    */ public abstract class Attachment
/*    */ {
/*    */   String name;
/*    */   
/*    */   public Attachment(String name) {
/* 38 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/* 39 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 47 */     return getName();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\attachments\Attachment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */