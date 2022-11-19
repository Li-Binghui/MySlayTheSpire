/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public class SteamMatchmakingServers
/*    */   extends SteamInterface
/*    */ {
/*    */   public SteamMatchmakingServers() {
/*  7 */     super(-1L);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestInternetServerList(int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse) {
/* 13 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestInternetServerList(appID, filters, filters.length, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestLANServerList(int appID, SteamMatchmakingServerListResponse requestServersResponse) {
/* 20 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestLANServerList(appID, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestFriendsServerList(int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse) {
/* 26 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestFriendsServerList(appID, filters, filters.length, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestFavoritesServerList(int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse) {
/* 33 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestFavoritesServerList(appID, filters, filters.length, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestHistoryServerList(int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse) {
/* 40 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestHistoryServerList(appID, filters, filters.length, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SteamServerListRequest requestSpectatorServerList(int appID, SteamMatchmakingKeyValuePair[] filters, SteamMatchmakingServerListResponse requestServersResponse) {
/* 47 */     return new SteamServerListRequest(SteamMatchmakingServersNative.requestSpectatorServerList(appID, filters, filters.length, requestServersResponse.callback));
/*    */   }
/*    */ 
/*    */   
/*    */   public void releaseRequest(SteamServerListRequest request) {
/* 52 */     SteamMatchmakingServersNative.releaseRequest(request.handle);
/*    */   }
/*    */   
/*    */   public boolean getServerDetails(SteamServerListRequest request, int server, SteamMatchmakingGameServerItem details) {
/* 56 */     return SteamMatchmakingServersNative.getServerDetails(request.handle, server, details);
/*    */   }
/*    */   
/*    */   public void cancelQuery(SteamServerListRequest request) {
/* 60 */     SteamMatchmakingServersNative.cancelQuery(request.handle);
/*    */   }
/*    */   
/*    */   public void refreshQuery(SteamServerListRequest request) {
/* 64 */     SteamMatchmakingServersNative.refreshQuery(request.handle);
/*    */   }
/*    */   
/*    */   public boolean isRefreshing(SteamServerListRequest request) {
/* 68 */     return SteamMatchmakingServersNative.isRefreshing(request.handle);
/*    */   }
/*    */   
/*    */   public int getServerCount(SteamServerListRequest request) {
/* 72 */     return SteamMatchmakingServersNative.getServerCount(request.handle);
/*    */   }
/*    */   
/*    */   public void refreshServer(SteamServerListRequest request, int server) {
/* 76 */     SteamMatchmakingServersNative.refreshServer(request.handle, server);
/*    */   }
/*    */   
/*    */   public SteamServerQuery pingServer(int ip, short port, SteamMatchmakingPingResponse requestServersResponse) {
/* 80 */     return new SteamServerQuery(SteamMatchmakingServersNative.pingServer(ip, port, requestServersResponse.callback));
/*    */   }
/*    */   
/*    */   public SteamServerQuery playerDetails(int ip, short port, SteamMatchmakingPlayersResponse requestServersResponse) {
/* 84 */     return new SteamServerQuery(SteamMatchmakingServersNative.playerDetails(ip, port, requestServersResponse.callback));
/*    */   }
/*    */   
/*    */   public SteamServerQuery serverRules(int ip, short port, SteamMatchmakingRulesResponse requestServersResponse) {
/* 88 */     return new SteamServerQuery(SteamMatchmakingServersNative.serverRules(ip, port, requestServersResponse.callback));
/*    */   }
/*    */   
/*    */   public void cancelServerQuery(SteamServerQuery serverQuery) {
/* 92 */     SteamMatchmakingServersNative.cancelServerQuery(serverQuery.handle);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingServers.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */