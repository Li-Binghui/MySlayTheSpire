/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ 
/*    */ class SteamMatchmakingCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamMatchmakingCallback>
/*    */ {
/*  7 */   private static final SteamMatchmaking.ChatMemberStateChange[] stateChangeValues = SteamMatchmaking.ChatMemberStateChange.values();
/*    */   
/*    */   SteamMatchmakingCallbackAdapter(SteamMatchmakingCallback callback) {
/* 10 */     super(callback);
/*    */   }
/*    */   
/*    */   void onFavoritesListChanged(int ip, int queryPort, int connPort, int appID, int flags, boolean add, int accountID) {
/* 14 */     this.callback.onFavoritesListChanged(ip, queryPort, connPort, appID, flags, add, accountID);
/*    */   }
/*    */   
/*    */   void onLobbyInvite(long steamIDUser, long steamIDLobby, long gameID) {
/* 18 */     this.callback.onLobbyInvite(new SteamID(steamIDUser), new SteamID(steamIDLobby), gameID);
/*    */   }
/*    */   
/*    */   void onLobbyEnter(long steamIDLobby, int chatPermissions, boolean blocked, int response) {
/* 22 */     this.callback.onLobbyEnter(new SteamID(steamIDLobby), chatPermissions, blocked, 
/* 23 */         SteamMatchmaking.ChatRoomEnterResponse.byValue(response));
/*    */   }
/*    */   
/*    */   void onLobbyDataUpdate(long steamIDLobby, long steamIDMember, boolean success) {
/* 27 */     this.callback.onLobbyDataUpdate(new SteamID(steamIDLobby), new SteamID(steamIDMember), success);
/*    */   }
/*    */   
/*    */   void onLobbyChatUpdate(long steamIDLobby, long steamIDUserChanged, long steamIDMakingChange, int stateChange) {
/* 31 */     SteamID lobby = new SteamID(steamIDLobby);
/* 32 */     SteamID userChanged = new SteamID(steamIDUserChanged);
/* 33 */     SteamID makingChange = new SteamID(steamIDMakingChange);
/* 34 */     for (SteamMatchmaking.ChatMemberStateChange value : stateChangeValues) {
/* 35 */       if (SteamMatchmaking.ChatMemberStateChange.isSet(value, stateChange)) {
/* 36 */         this.callback.onLobbyChatUpdate(lobby, userChanged, makingChange, value);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   void onLobbyChatMessage(long steamIDLobby, long steamIDUser, int entryType, int chatID) {
/* 42 */     this.callback.onLobbyChatMessage(new SteamID(steamIDLobby), new SteamID(steamIDUser), 
/* 43 */         SteamMatchmaking.ChatEntryType.byValue(entryType), chatID);
/*    */   }
/*    */   
/*    */   void onLobbyGameCreated(long steamIDLobby, long steamIDGameServer, int ip, short port) {
/* 47 */     this.callback.onLobbyGameCreated(new SteamID(steamIDLobby), new SteamID(steamIDGameServer), ip, port);
/*    */   }
/*    */   
/*    */   void onLobbyMatchList(int lobbiesMatching) {
/* 51 */     this.callback.onLobbyMatchList(lobbiesMatching);
/*    */   }
/*    */   
/*    */   void onLobbyKicked(long steamIDLobby, long steamIDAdmin, boolean kickedDueToDisconnect) {
/* 55 */     this.callback.onLobbyKicked(new SteamID(steamIDLobby), new SteamID(steamIDAdmin), kickedDueToDisconnect);
/*    */   }
/*    */   
/*    */   void onLobbyCreated(int result, long steamIDLobby) {
/* 59 */     this.callback.onLobbyCreated(SteamResult.byValue(result), new SteamID(steamIDLobby));
/*    */   }
/*    */   
/*    */   void onFavoritesListAccountsUpdated(int result) {
/* 63 */     this.callback.onFavoritesListAccountsUpdated(SteamResult.byValue(result));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */