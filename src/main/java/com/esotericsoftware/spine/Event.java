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
/*    */ public class Event
/*    */ {
/*    */   private final EventData data;
/*    */   int intValue;
/*    */   float floatValue;
/*    */   String stringValue;
/*    */   final float time;
/*    */   
/*    */   public Event(float time, EventData data) {
/* 42 */     if (data == null) throw new IllegalArgumentException("data cannot be null."); 
/* 43 */     this.time = time;
/* 44 */     this.data = data;
/*    */   }
/*    */   
/*    */   public int getInt() {
/* 48 */     return this.intValue;
/*    */   }
/*    */   
/*    */   public void setInt(int intValue) {
/* 52 */     this.intValue = intValue;
/*    */   }
/*    */   
/*    */   public float getFloat() {
/* 56 */     return this.floatValue;
/*    */   }
/*    */   
/*    */   public void setFloat(float floatValue) {
/* 60 */     this.floatValue = floatValue;
/*    */   }
/*    */   
/*    */   public String getString() {
/* 64 */     return this.stringValue;
/*    */   }
/*    */   
/*    */   public void setString(String stringValue) {
/* 68 */     this.stringValue = stringValue;
/*    */   }
/*    */   
/*    */   public float getTime() {
/* 72 */     return this.time;
/*    */   }
/*    */   
/*    */   public EventData getData() {
/* 76 */     return this.data;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 80 */     return this.data.name;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\esotericsoftware\spine\Event.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */