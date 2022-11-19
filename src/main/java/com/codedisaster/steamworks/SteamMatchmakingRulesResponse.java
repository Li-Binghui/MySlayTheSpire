/*   */ package com.codedisaster.steamworks;
/*   */ 
/*   */ public abstract class SteamMatchmakingRulesResponse
/*   */   extends SteamInterface {
/*   */   protected SteamMatchmakingRulesResponse() {
/* 6 */     super(-1L);
/* 7 */     this.callback = createProxy(this);
/*   */   }
/*   */   
/*   */   private static native long createProxy(SteamMatchmakingRulesResponse paramSteamMatchmakingRulesResponse);
/*   */   
/*   */   public abstract void rulesRefreshComplete();
/*   */   
/*   */   public abstract void rulesFailedToRespond();
/*   */   
/*   */   public abstract void rulesResponded(String paramString1, String paramString2);
/*   */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamMatchmakingRulesResponse.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */