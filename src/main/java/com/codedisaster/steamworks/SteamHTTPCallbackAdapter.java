/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamHTTPCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamHTTPCallback>
/*    */ {
/*    */   SteamHTTPCallbackAdapter(SteamHTTPCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void onHTTPRequestCompleted(long request, long contextValue, boolean requestSuccessful, int statusCode, int bodySize) {
/* 13 */     this.callback.onHTTPRequestCompleted(new SteamHTTPRequestHandle(request), contextValue, requestSuccessful, 
/* 14 */         SteamHTTP.HTTPStatusCode.byValue(statusCode), bodySize);
/*    */   }
/*    */   
/*    */   void onHTTPRequestHeadersReceived(long request, long contextValue) {
/* 18 */     this.callback.onHTTPRequestHeadersReceived(new SteamHTTPRequestHandle(request), contextValue);
/*    */   }
/*    */   
/*    */   void onHTTPRequestDataReceived(long request, long contextValue, int offset, int bytesReceived) {
/* 22 */     this.callback.onHTTPRequestDataReceived(new SteamHTTPRequestHandle(request), contextValue, offset, bytesReceived);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamHTTPCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */