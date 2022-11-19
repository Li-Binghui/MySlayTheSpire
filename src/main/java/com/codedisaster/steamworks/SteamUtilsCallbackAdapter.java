/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamUtilsCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamUtilsCallback>
/*    */ {
/*    */   private SteamAPIWarningMessageHook messageHook;
/*    */   
/*    */   SteamUtilsCallbackAdapter(SteamUtilsCallback callback) {
/*  9 */     super(callback);
/*    */   }
/*    */   
/*    */   void setWarningMessageHook(SteamAPIWarningMessageHook messageHook) {
/* 13 */     this.messageHook = messageHook;
/*    */   }
/*    */   
/*    */   void onWarningMessage(int severity, String message) {
/* 17 */     if (this.messageHook != null) {
/* 18 */       this.messageHook.onWarningMessage(severity, message);
/*    */     }
/*    */   }
/*    */   
/*    */   void onSteamShutdown() {
/* 23 */     this.callback.onSteamShutdown();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUtilsCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */