/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ public class SteamUserStats
/*     */   extends SteamInterface
/*     */ {
/*     */   public enum LeaderboardDataRequest {
/*   7 */     Global,
/*   8 */     GlobalAroundUser,
/*   9 */     Friends,
/*  10 */     Users;
/*     */   }
/*     */   
/*     */   public enum LeaderboardDisplayType {
/*  14 */     None,
/*  15 */     Numeric,
/*  16 */     TimeSeconds,
/*  17 */     TimeMilliSeconds;
/*     */   }
/*     */   
/*     */   public enum LeaderboardSortMethod {
/*  21 */     None,
/*  22 */     Ascending,
/*  23 */     Descending;
/*     */   }
/*     */   
/*     */   public enum LeaderboardUploadScoreMethod {
/*  27 */     None,
/*  28 */     KeepBest,
/*  29 */     ForceUpdate;
/*     */   }
/*     */   
/*     */   public SteamUserStats(SteamUserStatsCallback callback) {
/*  33 */     super(SteamUserStatsNative.createCallback(new SteamUserStatsCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   public boolean requestCurrentStats() {
/*  37 */     return SteamUserStatsNative.requestCurrentStats();
/*     */   }
/*     */   
/*     */   public int getStatI(String name, int defaultValue) {
/*  41 */     int[] values = new int[1];
/*  42 */     if (SteamUserStatsNative.getStat(name, values)) {
/*  43 */       return values[0];
/*     */     }
/*  45 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public boolean setStatI(String name, int value) {
/*  49 */     return SteamUserStatsNative.setStat(name, value);
/*     */   }
/*     */   
/*     */   public float getStatF(String name, float defaultValue) {
/*  53 */     float[] values = new float[1];
/*  54 */     if (SteamUserStatsNative.getStat(name, values)) {
/*  55 */       return values[0];
/*     */     }
/*  57 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public boolean setStatF(String name, float value) {
/*  61 */     return SteamUserStatsNative.setStat(name, value);
/*     */   }
/*     */   
/*     */   public boolean isAchieved(String name, boolean defaultValue) {
/*  65 */     boolean[] achieved = new boolean[1];
/*  66 */     if (SteamUserStatsNative.getAchievement(name, achieved)) {
/*  67 */       return achieved[0];
/*     */     }
/*  69 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public boolean setAchievement(String name) {
/*  73 */     return SteamUserStatsNative.setAchievement(name);
/*     */   }
/*     */   
/*     */   public boolean clearAchievement(String name) {
/*  77 */     return SteamUserStatsNative.clearAchievement(name);
/*     */   }
/*     */   
/*     */   public boolean storeStats() {
/*  81 */     return SteamUserStatsNative.storeStats();
/*     */   }
/*     */   
/*     */   public boolean indicateAchievementProgress(String name, int curProgress, int maxProgress) {
/*  85 */     return SteamUserStatsNative.indicateAchievementProgress(name, curProgress, maxProgress);
/*     */   }
/*     */   
/*     */   public int getNumAchievements() {
/*  89 */     return SteamUserStatsNative.getNumAchievements();
/*     */   }
/*     */   
/*     */   public String getAchievementName(int index) {
/*  93 */     return SteamUserStatsNative.getAchievementName(index);
/*     */   }
/*     */   
/*     */   public boolean resetAllStats(boolean achievementsToo) {
/*  97 */     return SteamUserStatsNative.resetAllStats(achievementsToo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamAPICall findOrCreateLeaderboard(String leaderboardName, LeaderboardSortMethod leaderboardSortMethod, LeaderboardDisplayType leaderboardDisplayType) {
/* 104 */     return new SteamAPICall(SteamUserStatsNative.findOrCreateLeaderboard(this.callback, leaderboardName, leaderboardSortMethod
/* 105 */           .ordinal(), leaderboardDisplayType.ordinal()));
/*     */   }
/*     */   
/*     */   public SteamAPICall findLeaderboard(String leaderboardName) {
/* 109 */     return new SteamAPICall(SteamUserStatsNative.findLeaderboard(this.callback, leaderboardName));
/*     */   }
/*     */   
/*     */   public String getLeaderboardName(SteamLeaderboardHandle leaderboard) {
/* 113 */     return SteamUserStatsNative.getLeaderboardName(leaderboard.handle);
/*     */   }
/*     */   
/*     */   public int getLeaderboardEntryCount(SteamLeaderboardHandle leaderboard) {
/* 117 */     return SteamUserStatsNative.getLeaderboardEntryCount(leaderboard.handle);
/*     */   }
/*     */   
/*     */   public LeaderboardSortMethod getLeaderboardSortMethod(SteamLeaderboardHandle leaderboard) {
/* 121 */     return LeaderboardSortMethod.values()[SteamUserStatsNative.getLeaderboardSortMethod(leaderboard.handle)];
/*     */   }
/*     */   
/*     */   public LeaderboardDisplayType getLeaderboardDisplayType(SteamLeaderboardHandle leaderboard) {
/* 125 */     return LeaderboardDisplayType.values()[SteamUserStatsNative.getLeaderboardDisplayType(leaderboard.handle)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamAPICall downloadLeaderboardEntries(SteamLeaderboardHandle leaderboard, LeaderboardDataRequest leaderboardDataRequest, int rangeStart, int rangeEnd) {
/* 133 */     return new SteamAPICall(SteamUserStatsNative.downloadLeaderboardEntries(this.callback, leaderboard.handle, leaderboardDataRequest
/* 134 */           .ordinal(), rangeStart, rangeEnd));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamAPICall downloadLeaderboardEntriesForUsers(SteamLeaderboardHandle leaderboard, SteamID[] users) {
/* 140 */     int count = users.length;
/* 141 */     long[] handles = new long[count];
/*     */     
/* 143 */     for (int i = 0; i < count; i++) {
/* 144 */       handles[i] = (users[i]).handle;
/*     */     }
/*     */     
/* 147 */     return new SteamAPICall(SteamUserStatsNative.downloadLeaderboardEntriesForUsers(this.callback, leaderboard.handle, handles, count));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDownloadedLeaderboardEntry(SteamLeaderboardEntriesHandle leaderboardEntries, int index, SteamLeaderboardEntry entry, int[] details) {
/* 158 */     return (details == null) ? 
/* 159 */       SteamUserStatsNative.getDownloadedLeaderboardEntry(leaderboardEntries.handle, index, entry) : 
/* 160 */       SteamUserStatsNative.getDownloadedLeaderboardEntry(leaderboardEntries.handle, index, entry, details, details.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamAPICall uploadLeaderboardScore(SteamLeaderboardHandle leaderboard, LeaderboardUploadScoreMethod method, int score, int[] scoreDetails) {
/* 170 */     return new SteamAPICall((scoreDetails == null) ? 
/* 171 */         SteamUserStatsNative.uploadLeaderboardScore(this.callback, leaderboard.handle, method.ordinal(), score) : 
/* 172 */         SteamUserStatsNative.uploadLeaderboardScore(this.callback, leaderboard.handle, method.ordinal(), score, scoreDetails, scoreDetails.length));
/*     */   }
/*     */   
/*     */   public SteamAPICall getNumberOfCurrentPlayers() {
/* 176 */     return new SteamAPICall(SteamUserStatsNative.getNumberOfCurrentPlayers(this.callback));
/*     */   }
/*     */   
/*     */   public SteamAPICall requestGlobalStats(int historyDays) {
/* 180 */     return new SteamAPICall(SteamUserStatsNative.requestGlobalStats(this.callback, historyDays));
/*     */   }
/*     */   
/*     */   public long getGlobalStat(String name, long defaultValue) {
/* 184 */     long[] values = new long[1];
/* 185 */     if (SteamUserStatsNative.getGlobalStat(name, values)) {
/* 186 */       return values[0];
/*     */     }
/* 188 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public double getGlobalStat(String name, double defaultValue) {
/* 192 */     double[] values = new double[1];
/* 193 */     if (SteamUserStatsNative.getGlobalStat(name, values)) {
/* 194 */       return values[0];
/*     */     }
/* 196 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public int getGlobalStatHistory(String name, long[] data) {
/* 200 */     return SteamUserStatsNative.getGlobalStatHistory(name, data, data.length);
/*     */   }
/*     */   
/*     */   public int getGlobalStatHistory(String name, double[] data) {
/* 204 */     return SteamUserStatsNative.getGlobalStatHistory(name, data, data.length);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamUserStats.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */