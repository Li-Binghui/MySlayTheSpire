/*    */ package com.esotericsoftware.spine;
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
/*    */ public class EventData
/*    */ {
/*    */   final String name;
/*    */   int intValue;
/*    */   float floatValue;
/*    */   String stringValue;
/*    */   
/*    */   public EventData(String name) {
/* 41 */     if (name == null) throw new IllegalArgumentException("name cannot be null."); 
/* 42 */     this.name = name;
/*    */   }
/*    */   
/*    */   public int getInt() {
/* 46 */     return this.intValue;
/*    */   }
/*    */   
/*    */   public void setInt(int intValue) {
/* 50 */     this.intValue = intValue;
/*    */   }
/*    */   
/*    */   public float getFloat() {
/* 54 */     return this.floatValue;
/*    */   }
/*    */   
/*    */   public void setFloat(float floatValue) {
/* 58 */     this.floatValue = floatValue;
/*    */   }
/*    */   
/*    */   public String getString() {
/* 62 */     return this.stringValue;
/*    */   }
/*    */   
/*    */   public void setString(String stringValue) {
/* 66 */     this.stringValue = stringValue;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 70 */     return this.name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 74 */     return this.name;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\EventData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */