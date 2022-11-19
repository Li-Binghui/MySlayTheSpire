/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamUserCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamUserCallback>
/*    */ {
/*    */   SteamUserCallbackAdapter(SteamUserCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */   
/*    */   void onAuthSessionTicket(long authTicket, int result) {
/* 11 */     this.callback.onAuthSessionTicket(new SteamAuthTicket(authTicket), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onValidateAuthTicket(long steamID, int authSessionResponse, long ownerSteamID) {
/* 15 */     this.callback.onValidateAuthTicket(new SteamID(steamID), 
/* 16 */         SteamAuth.AuthSessionResponse.byOrdinal(authSessionResponse), new SteamID(ownerSteamID));
/*    */   }
/*    */   
/*    */   void onMicroTxnAuthorization(int appID, long orderID, boolean authorized) {
/* 20 */     this.callback.onMicroTxnAuthorization(appID, orderID, authorized);
/*    */   }
/*    */   
/*    */   void onEncryptedAppTicket(int result) {
/* 24 */     this.callback.onEncryptedAppTicket(SteamResult.byValue(result));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */