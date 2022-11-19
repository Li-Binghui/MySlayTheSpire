/*    */ package com.gikk.twirk.types;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ 
/*    */ class TagMapImpl
/*    */   extends HashMap<String, String>
/*    */   implements TagMap
/*    */ {
/*    */   public TagMapImpl(String tag) {
/* 11 */     if (tag.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 15 */     String[] segments = tag.substring(1).split(";");
/*    */     
/* 17 */     int i = 0;
/*    */     
/* 19 */     for (String s : segments) {
/* 20 */       if ((i = s.indexOf('=')) != -1) {
/* 21 */         String key = s.substring(0, i);
/*    */ 
/*    */         
/* 24 */         String value = mightBeEscaped(key) ? replaceEscapes(s.substring(i + 1)) : s.substring(i + 1);
/* 25 */         put(key, value);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getAsString(String identifier) {
/* 37 */     return getOrDefault(identifier, "");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAsInt(String identifier) {
/*    */     try {
/* 45 */       return Integer.decode(getOrDefault(identifier, "-1")).intValue();
/* 46 */     } catch (NumberFormatException e) {
/* 47 */       return -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getAsLong(String identifier) {
/*    */     try {
/* 56 */       return Long.decode(getOrDefault(identifier, "-1")).longValue();
/* 57 */     } catch (NumberFormatException e) {
/* 58 */       return -1L;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getAsBoolean(String identifier) {
/* 66 */     return getOrDefault(identifier, "0").equals("1");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean mightBeEscaped(String segment) {
/* 73 */     return (segment.equals("display-name") || segment
/* 74 */       .equals("ban-reason") || segment
/* 75 */       .equals("system-msg") || segment
/* 76 */       .equals(TwitchTags.PARAM_SUB_PLAN_NAME));
/*    */   }
/*    */   
/*    */   private String replaceEscapes(String segment) {
/* 80 */     segment = segment.replace("\\s", " ");
/* 81 */     segment = segment.replace("\\:", ";");
/* 82 */     return segment;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\TagMapImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */