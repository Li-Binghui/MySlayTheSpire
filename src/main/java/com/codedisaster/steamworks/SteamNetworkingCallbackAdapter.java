/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamNetworkingCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamNetworkingCallback>
/*    */ {
/*    */   SteamNetworkingCallbackAdapter(SteamNetworkingCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */   
/*    */   void onP2PSessionConnectFail(long steamIDRemote, int sessionError) {
/* 11 */     SteamID id = new SteamID(steamIDRemote);
/* 12 */     this.callback.onP2PSessionConnectFail(id, SteamNetworking.P2PSessionError.byOrdinal(sessionError));
/*    */   }
/*    */   
/*    */   void onP2PSessionRequest(long steamIDRemote) {
/* 16 */     SteamID id = new SteamID(steamIDRemote);
/* 17 */     this.callback.onP2PSessionRequest(id);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamNetworkingCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */