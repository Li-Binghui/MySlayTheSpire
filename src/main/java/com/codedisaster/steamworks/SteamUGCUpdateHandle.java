/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public class SteamUGCUpdateHandle
/*    */   extends SteamNativeHandle {
/*    */   SteamUGCUpdateHandle(long handle) {
/*  6 */     super(handle);
/*    */   }
/*    */   
/*    */   public boolean isValid() {
/* 10 */     return (this.handle != -1L);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUGCUpdateHandle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */