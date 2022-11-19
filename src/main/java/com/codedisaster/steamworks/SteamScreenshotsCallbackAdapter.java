/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public class SteamScreenshotsCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamScreenshotsCallback>
/*    */ {
/*    */   SteamScreenshotsCallbackAdapter(SteamScreenshotsCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */   
/*    */   void onScreenshotReady(int local, int result) {
/* 11 */     this.callback.onScreenshotReady(new SteamScreenshotHandle(local), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onScreenshotRequested() {
/* 15 */     this.callback.onScreenshotRequested();
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamScreenshotsCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */