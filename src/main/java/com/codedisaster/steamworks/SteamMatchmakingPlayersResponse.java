/*   */ package com.codedisaster.steamworks;
/*   */ 
/*   */ public abstract class SteamMatchmakingPlayersResponse
/*   */   extends SteamInterface {
/*   */   protected SteamMatchmakingPlayersResponse() {
/* 6 */     super(-1L);
/* 7 */     this.callback = createProxy(this);
/*   */   }
/*   */   
/*   */   private static native long createProxy(SteamMatchmakingPlayersResponse paramSteamMatchmakingPlayersResponse);
/*   */   
/*   */   public abstract void playersRefreshComplete();
/*   */   
/*   */   public abstract void playersFailedToRespond();
/*   */   
/*   */   public abstract void addPlayerToList(String paramString, int paramInt, float paramFloat);
/*   */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingPlayersResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */