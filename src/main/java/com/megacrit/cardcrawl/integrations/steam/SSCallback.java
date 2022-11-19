/*     */ package com.megacrit.cardcrawl.integrations.steam;
/*     */ 
/*     */

import com.codedisaster.steamworks.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.leaderboards.LeaderboardEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SSCallback
/*     */   implements SteamUserStatsCallback
/*     */ {
/*  17 */   private static final Logger logger = LogManager.getLogger(SSCallback.class.getName());
/*     */   private SteamIntegration steamIntegration;
/*     */   
/*     */   public SSCallback(SteamIntegration steamIntegration) {
/*  21 */     this.steamIntegration = steamIntegration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGlobalStatsReceived(long arg0, SteamResult arg1) {
/*  26 */     logger.info("1Bloop: " + arg0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLeaderboardFindResult(SteamLeaderboardHandle handle, boolean found) {
/*  32 */     logger.info("onLeaderboardFindResult");
/*  33 */     if (found) {
/*  34 */       switch (SteamIntegration.task) {
/*     */         case UPLOAD_DAILY:
/*  36 */           SteamIntegration.lbHandle = handle;
/*  37 */           SteamIntegration.uploadDailyLeaderboardHelper();
/*     */           break;
/*     */         case UPLOAD:
/*  40 */           SteamIntegration.lbHandle = handle;
/*  41 */           SteamIntegration.uploadLeaderboardHelper();
/*     */           break;
/*     */         case RETRIEVE_DAILY:
/*  44 */           SteamIntegration.lbHandle = handle;
/*  45 */           SteamIntegration.getLeaderboardEntryHelper();
/*     */           break;
/*     */         case RETRIEVE:
/*  48 */           SteamIntegration.lbHandle = handle;
/*  49 */           SteamIntegration.getLeaderboardEntryHelper();
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLeaderboardScoreUploaded(boolean success, SteamLeaderboardHandle handle, int score, boolean changed, int globalRankNew, int globalRankPrevious) {
/*  66 */     if (!success) {
/*  67 */       logger.info("Failed to upload leaderboard data: " + score);
/*  68 */     } else if (!changed) {
/*  69 */       logger.info("Leaderboard data not changed for data: " + score);
/*     */     } else {
/*  71 */       logger.info("Successfully uploaded leaderboard data: " + score);
/*     */     } 
/*  73 */     this.steamIntegration.didCompleteCallback(success);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNumberOfCurrentPlayersReceived(boolean success, int players) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle handle, SteamLeaderboardEntriesHandle entries, int numEntries) {
/*  85 */     if (SteamIntegration.task == SteamIntegration.LeaderboardTask.RETRIEVE) {
/*  86 */       logger.info("Downloaded " + numEntries + " entries");
/*  87 */       int[] details = new int[16];
/*  88 */       CardCrawlGame.mainMenuScreen.leaderboardsScreen.entries.clear();
/*     */       
/*  90 */       for (int i = 0; i < numEntries; i++) {
/*  91 */         SteamLeaderboardEntry entry = new SteamLeaderboardEntry();
/*  92 */         if (SteamIntegration.steamStats.getDownloadedLeaderboardEntry(entries, i, entry, details)) {
/*     */           
/*  94 */           int rTemp = entry.getGlobalRank();
/*     */           
/*  96 */           if (i == 0) {
/*  97 */             CardCrawlGame.mainMenuScreen.leaderboardsScreen.currentStartIndex = rTemp;
/*  98 */           } else if (i == numEntries) {
/*  99 */             CardCrawlGame.mainMenuScreen.leaderboardsScreen.currentEndIndex = rTemp;
/*     */           } 
/*     */           
/* 102 */           CardCrawlGame.mainMenuScreen.leaderboardsScreen.entries.add(new LeaderboardEntry(rTemp, SteamIntegration.steamFriends
/*     */ 
/*     */                 
/* 105 */                 .getFriendPersonaName(entry.getSteamIDUser()), entry
/* 106 */                 .getScore(), SteamIntegration.gettingTime, (SteamIntegration.accountId != -1 && SteamIntegration.accountId == entry
/*     */                 
/* 108 */                 .getSteamIDUser()
/* 109 */                 .getAccountID())));
/*     */         } else {
/* 111 */           logger.info("FAILED TO GET LEADERBOARD ENTRY: " + i);
/*     */         } 
/*     */       } 
/* 114 */       CardCrawlGame.mainMenuScreen.leaderboardsScreen.waiting = false;
/* 115 */     } else if (SteamIntegration.task == SteamIntegration.LeaderboardTask.RETRIEVE_DAILY) {
/* 116 */       logger.info("[DAILY] Downloaded " + numEntries + " entries");
/* 117 */       int[] details = new int[16];
/* 118 */       CardCrawlGame.mainMenuScreen.dailyScreen.entries.clear();
/*     */       
/* 120 */       for (int i = 0; i < numEntries; i++) {
/* 121 */         SteamLeaderboardEntry entry = new SteamLeaderboardEntry();
/* 122 */         if (SteamIntegration.steamStats.getDownloadedLeaderboardEntry(entries, i, entry, details)) {
/*     */           
/* 124 */           int rTemp = entry.getGlobalRank();
/*     */           
/* 126 */           if (i == 0) {
/* 127 */             CardCrawlGame.mainMenuScreen.dailyScreen.currentStartIndex = rTemp;
/* 128 */           } else if (i == numEntries) {
/* 129 */             CardCrawlGame.mainMenuScreen.dailyScreen.currentEndIndex = rTemp;
/*     */           } 
/*     */           
/* 132 */           CardCrawlGame.mainMenuScreen.dailyScreen.entries.add(new LeaderboardEntry(rTemp, SteamIntegration.steamFriends
/*     */ 
/*     */                 
/* 135 */                 .getFriendPersonaName(entry.getSteamIDUser()), entry
/* 136 */                 .getScore(), SteamIntegration.gettingTime, (SteamIntegration.accountId != -1 && SteamIntegration.accountId == entry
/*     */                 
/* 138 */                 .getSteamIDUser()
/* 139 */                 .getAccountID())));
/*     */         } else {
/* 141 */           logger.info("FAILED TO GET LEADERBOARD ENTRY: " + i);
/*     */         } 
/*     */       } 
/* 144 */       CardCrawlGame.mainMenuScreen.dailyScreen.waiting = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUserAchievementStored(long arg0, boolean arg1, String arg2, int arg3, int arg4) {
/* 150 */     logger.info("Achievement Stored");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUserStatsReceived(long arg0, SteamID arg1, SteamResult arg2) {
/* 156 */     logger.info("SteamID: " + arg1.getAccountID());
/* 157 */     logger.info("APPID: " + arg0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUserStatsStored(long arg0, SteamResult arg1) {
/* 162 */     logger.info("Stat Stored");
/*     */   }
/*     */   
/*     */   public void onUserStatsUnloaded(SteamID arg0) {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\steam\SSCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */