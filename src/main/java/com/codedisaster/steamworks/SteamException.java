/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SteamException
/*    */   extends Exception
/*    */ {
/*    */   public SteamException() {}
/*    */   
/*    */   public SteamException(String message) {
/* 11 */     super(message);
/*    */   }
/*    */   
/*    */   public SteamException(String message, Throwable throwable) {
/* 15 */     super(message, throwable);
/*    */   }
/*    */   
/*    */   public SteamException(Throwable throwable) {
/* 19 */     super(throwable);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */