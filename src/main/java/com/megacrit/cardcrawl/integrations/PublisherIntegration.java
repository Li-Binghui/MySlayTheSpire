package com.megacrit.cardcrawl.integrations;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.screens.leaderboards.FilterButton;

public interface PublisherIntegration {
  boolean isInitialized();
  
  void dispose();
  
  void deleteAllCloudFiles();
  
  boolean setStat(String paramString, int paramInt);
  
  int getStat(String paramString);
  
  boolean incrementStat(String paramString, int paramInt);
  
  long getGlobalStat(String paramString);
  
  void uploadDailyLeaderboardScore(String paramString, int paramInt);
  
  void uploadLeaderboardScore(String paramString, int paramInt);
  
  void unlockAchievement(String paramString);
  
  void getLeaderboardEntries(AbstractPlayer.PlayerClass paramPlayerClass, FilterButton.RegionSetting paramRegionSetting, FilterButton.LeaderboardType paramLeaderboardType, int paramInt1, int paramInt2);
  
  void getDailyLeaderboard(long paramLong, int paramInt1, int paramInt2);
  
  void setRichPresenceDisplayPlaying(int paramInt, String paramString);
  
  void setRichPresenceDisplayPlaying(int paramInt1, int paramInt2, String paramString);
  
  void setRichPresenceDisplayInMenu();
  
  int getNumUnlockedAchievements();
  
  DistributorFactory.Distributor getType();
}


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\PublisherIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */