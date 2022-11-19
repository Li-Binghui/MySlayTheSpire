/*    */ package com.codedisaster.steamworks;
/*    */ 
/*    */ class SteamUserStatsCallbackAdapter
/*    */   extends SteamCallbackAdapter<SteamUserStatsCallback>
/*    */ {
/*    */   SteamUserStatsCallbackAdapter(SteamUserStatsCallback callback) {
/*  7 */     super(callback);
/*    */   }
/*    */   
/*    */   void onUserStatsReceived(long gameId, long steamIDUser, int result) {
/* 11 */     this.callback.onUserStatsReceived(gameId, new SteamID(steamIDUser), SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onUserStatsStored(long gameId, int result) {
/* 15 */     this.callback.onUserStatsStored(gameId, SteamResult.byValue(result));
/*    */   }
/*    */   
/*    */   void onUserStatsUnloaded(long steamIDUser) {
/* 19 */     this.callback.onUserStatsUnloaded(new SteamID(steamIDUser));
/*    */   }
/*    */ 
/*    */   
/*    */   void onUserAchievementStored(long gameId, boolean isGroupAchievement, String achievementName, int curProgress, int maxProgress) {
/* 24 */     this.callback.onUserAchievementStored(gameId, isGroupAchievement, achievementName, curProgress, maxProgress);
/*    */   }
/*    */   
/*    */   void onLeaderboardFindResult(long handle, boolean found) {
/* 28 */     this.callback.onLeaderboardFindResult(new SteamLeaderboardHandle(handle), found);
/*    */   }
/*    */   
/*    */   void onLeaderboardScoresDownloaded(long handle, long entries, int numEntries) {
/* 32 */     this.callback.onLeaderboardScoresDownloaded(new SteamLeaderboardHandle(handle), new SteamLeaderboardEntriesHandle(entries), numEntries);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   void onLeaderboardScoreUploaded(boolean success, long handle, int score, boolean changed, int globalRankNew, int globalRankPrevious) {
/* 38 */     this.callback.onLeaderboardScoreUploaded(success, new SteamLeaderboardHandle(handle), score, changed, globalRankNew, globalRankPrevious);
/*    */   }
/*    */ 
/*    */   
/*    */   void onNumberOfCurrentPlayersReceived(boolean success, int players) {
/* 43 */     this.callback.onNumberOfCurrentPlayersReceived(success, players);
/*    */   }
/*    */   
/*    */   void onGlobalStatsReceived(long gameId, int result) {
/* 47 */     this.callback.onGlobalStatsReceived(gameId, SteamResult.byValue(result));
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserStatsCallbackAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */