/*    */ package com.megacrit.cardcrawl.integrations.wegame;
/*    */ 
/*    */

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.integrations.DistributorFactory;
import com.megacrit.cardcrawl.integrations.PublisherIntegration;
import com.megacrit.cardcrawl.screens.leaderboards.FilterButton;
/*    */ 
/*    */ 
/*    */ public class WeGameIntegration
/*    */   implements PublisherIntegration
/*    */ {
/*    */   public boolean isInitialized() {
/* 13 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void deleteAllCloudFiles() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean setStat(String id, int value) {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStat(String id) {
/* 32 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean incrementStat(String id, int incrementAmt) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getGlobalStat(String id) {
/* 42 */     return -1L;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void uploadDailyLeaderboardScore(String name, int score) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void uploadLeaderboardScore(String name, int score) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void unlockAchievement(String id) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getLeaderboardEntries(AbstractPlayer.PlayerClass pClass, FilterButton.RegionSetting rSetting, FilterButton.LeaderboardType lType, int start, int end) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getDailyLeaderboard(long date, int start, int end) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRichPresenceDisplayPlaying(int floor, String character) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRichPresenceDisplayPlaying(int floor, int ascension, String character) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DistributorFactory.Distributor getType() {
/* 85 */     return DistributorFactory.Distributor.WEGAME;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRichPresenceDisplayInMenu() {}
/*    */ 
/*    */   
/*    */   public int getNumUnlockedAchievements() {
/* 94 */     return 0;
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\wegame\WeGameIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */