/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ public abstract class SteamMatchmakingServerListResponse
/*    */   extends SteamInterface {
/*    */   public enum Response {
/*  6 */     ServerResponded,
/*  7 */     ServerFailedToRespond,
/*  8 */     NoServersListedOnMasterServer;
/*    */     
/* 10 */     private static final Response[] values = values();
/*    */     
/*    */     static Response byOrdinal(int ordinal) {
/* 13 */       return values[ordinal];
/*    */     } static {
/*    */     
/*    */     } }
/*    */   protected SteamMatchmakingServerListResponse() {
/* 18 */     super(-1L);
/* 19 */     this.callback = createProxy(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void serverResponded(long request, int server) {
/* 25 */     serverResponded(new SteamServerListRequest(request), server);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void serverFailedToRespond(long request, int server) {
/* 31 */     serverFailedToRespond(new SteamServerListRequest(request), server);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void refreshComplete(long request, int response) {
/* 37 */     refreshComplete(new SteamServerListRequest(request), Response.byOrdinal(response));
/*    */   }
/*    */   
/*    */   public abstract void serverResponded(SteamServerListRequest paramSteamServerListRequest, int paramInt);
/*    */   
/*    */   public abstract void serverFailedToRespond(SteamServerListRequest paramSteamServerListRequest, int paramInt);
/*    */   
/*    */   public abstract void refreshComplete(SteamServerListRequest paramSteamServerListRequest, Response paramResponse);
/*    */   
/*    */   private static native long createProxy(SteamMatchmakingServerListResponse paramSteamMatchmakingServerListResponse);
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingServerListResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */