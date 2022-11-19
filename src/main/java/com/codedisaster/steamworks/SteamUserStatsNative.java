package com.codedisaster.steamworks;

final class SteamUserStatsNative {
  static native long createCallback(SteamUserStatsCallbackAdapter paramSteamUserStatsCallbackAdapter);
  
  static native boolean requestCurrentStats();
  
  static native boolean getStat(String paramString, int[] paramArrayOfint);
  
  static native boolean setStat(String paramString, int paramInt);
  
  static native boolean getStat(String paramString, float[] paramArrayOffloat);
  
  static native boolean setStat(String paramString, float paramFloat);
  
  static native boolean getAchievement(String paramString, boolean[] paramArrayOfboolean);
  
  static native boolean setAchievement(String paramString);
  
  static native boolean clearAchievement(String paramString);
  
  static native boolean storeStats();
  
  static native boolean indicateAchievementProgress(String paramString, int paramInt1, int paramInt2);
  
  static native int getNumAchievements();
  
  static native String getAchievementName(int paramInt);
  
  static native boolean resetAllStats(boolean paramBoolean);
  
  static native long findOrCreateLeaderboard(long paramLong, String paramString, int paramInt1, int paramInt2);
  
  static native long findLeaderboard(long paramLong, String paramString);
  
  static native String getLeaderboardName(long paramLong);
  
  static native int getLeaderboardEntryCount(long paramLong);
  
  static native int getLeaderboardSortMethod(long paramLong);
  
  static native int getLeaderboardDisplayType(long paramLong);
  
  static native long downloadLeaderboardEntries(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int paramInt3);
  
  static native long downloadLeaderboardEntriesForUsers(long paramLong1, long paramLong2, long[] paramArrayOflong, int paramInt);
  
  static native boolean getDownloadedLeaderboardEntry(long paramLong, int paramInt1, SteamLeaderboardEntry paramSteamLeaderboardEntry, int[] paramArrayOfint, int paramInt2);
  
  static native boolean getDownloadedLeaderboardEntry(long paramLong, int paramInt, SteamLeaderboardEntry paramSteamLeaderboardEntry);
  
  static native long uploadLeaderboardScore(long paramLong1, long paramLong2, int paramInt1, int paramInt2, int[] paramArrayOfint, int paramInt3);
  
  static native long uploadLeaderboardScore(long paramLong1, long paramLong2, int paramInt1, int paramInt2);
  
  static native long getNumberOfCurrentPlayers(long paramLong);
  
  static native long requestGlobalStats(long paramLong, int paramInt);
  
  static native boolean getGlobalStat(String paramString, long[] paramArrayOflong);
  
  static native boolean getGlobalStat(String paramString, double[] paramArrayOfdouble);
  
  static native int getGlobalStatHistory(String paramString, long[] paramArrayOflong, int paramInt);
  
  static native int getGlobalStatHistory(String paramString, double[] paramArrayOfdouble, int paramInt);
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserStatsNative.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */