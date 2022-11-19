/*    */ package com.gikk.twirk.types.cheer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum CheerType
/*    */ {
/*  8 */   ANIMATED("animated"), STATIC("static");
/*    */   private final String value;
/*    */   
/*    */   CheerType(String s) {
/* 12 */     this.value = s;
/*    */   }
/*    */   
/*    */   String getValue() {
/* 16 */     return this.value;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\gikk\twirk\types\cheer\CheerType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */