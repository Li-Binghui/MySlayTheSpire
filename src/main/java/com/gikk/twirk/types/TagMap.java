/*    */ package com.gikk.twirk.types;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface TagMap
/*    */   extends Map<String, String>
/*    */ {
/*    */   String getAsString(String paramString);
/*    */   
/*    */   int getAsInt(String paramString);
/*    */   
/*    */   long getAsLong(String paramString);
/*    */   
/*    */   boolean getAsBoolean(String paramString);
/*    */   
/*    */   static TagMap getDefault(String tag) {
/* 56 */     return new TagMapImpl(tag);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\TagMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */