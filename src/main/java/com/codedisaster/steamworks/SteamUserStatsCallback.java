package com.codedisaster.steamworks;

public interface SteamUserStatsCallback {
  void onUserStatsReceived(long paramLong, SteamID paramSteamID, SteamResult paramSteamResult);
  
  void onUserStatsStored(long paramLong, SteamResult paramSteamResult);
  
  void onUserStatsUnloaded(SteamID paramSteamID);
  
  void onUserAchievementStored(long paramLong, boolean paramBoolean, String paramString, int paramInt1, int paramInt2);
  
  void onLeaderboardFindResult(SteamLeaderboardHandle paramSteamLeaderboardHandle, boolean paramBoolean);
  
  void onLeaderboardScoresDownloaded(SteamLeaderboardHandle paramSteamLeaderboardHandle, SteamLeaderboardEntriesHandle paramSteamLeaderboardEntriesHandle, int paramInt);
  
  void onLeaderboardScoreUploaded(boolean paramBoolean1, SteamLeaderboardHandle paramSteamLeaderboardHandle, int paramInt1, boolean paramBoolean2, int paramInt2, int paramInt3);
  
  void onNumberOfCurrentPlayersReceived(boolean paramBoolean, int paramInt);
  
  void onGlobalStatsReceived(long paramLong, SteamResult paramSteamResult);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserStatsCallback.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */