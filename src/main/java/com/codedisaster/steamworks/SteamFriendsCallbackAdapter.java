/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamFriendsCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamFriendsCallback>
/*    */ {
/*  6 */   private static final SteamFriends.PersonaChange[] personaChangeValues = SteamFriends.PersonaChange.values();
/*    */   
/*    */   SteamFriendsCallbackAdapter(SteamFriendsCallback callback) {
/*  9 */     super(callback);
/*    */   }
/*    */   
/*    */   void onSetPersonaNameResponse(boolean success, boolean localSuccess, int result) {
/* 13 */     this.callback.onSetPersonaNameResponse(success, localSuccess, SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onPersonaStateChange(long steamID, int changeFlags) {
/* 17 */     SteamID id = new SteamID(steamID);
/* 18 */     for (SteamFriends.PersonaChange value : personaChangeValues) {
/* 19 */       if (SteamFriends.PersonaChange.isSet(value, changeFlags)) {
/* 20 */         this.callback.onPersonaStateChange(id, value);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   void onGameOverlayActivated(boolean active) {
/* 26 */     this.callback.onGameOverlayActivated(active);
/*    */   }
/*    */   
/*    */   void onGameLobbyJoinRequested(long steamIDLobby, long steamIDFriend) {
/* 30 */     this.callback.onGameLobbyJoinRequested(new SteamID(steamIDLobby), new SteamID(steamIDFriend));
/*    */   }
/*    */   
/*    */   void onAvatarImageLoaded(long steamID, int image, int width, int height) {
/* 34 */     this.callback.onAvatarImageLoaded(new SteamID(steamID), image, width, height);
/*    */   }
/*    */   
/*    */   void onFriendRichPresenceUpdate(long steamIDFriend, int appID) {
/* 38 */     this.callback.onFriendRichPresenceUpdate(new SteamID(steamIDFriend), appID);
/*    */   }
/*    */   
/*    */   void onGameRichPresenceJoinRequested(long steamIDFriend, String connect) {
/* 42 */     this.callback.onGameRichPresenceJoinRequested(new SteamID(steamIDFriend), connect);
/*    */   }
/*    */   
/*    */   void onGameServerChangeRequested(String server, String password) {
/* 46 */     this.callback.onGameServerChangeRequested(server, password);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamFriendsCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */