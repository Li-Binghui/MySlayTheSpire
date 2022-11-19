/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public class SteamAuthTicket
/*    */   extends SteamNativeHandle {
/*    */   static final long AuthTicketInvalid = 0L;
/*    */   
/*    */   SteamAuthTicket(long handle) {
/*  8 */     super(handle);
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 12 */     return (this.handle != 0L);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamAuthTicket.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */