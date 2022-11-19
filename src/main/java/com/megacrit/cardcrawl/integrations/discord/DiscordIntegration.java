/*     */ package com.megacrit.cardcrawl.integrations.discord;
/*     */ 
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.integrations.DistributorFactory;
/*     */ import com.megacrit.cardcrawl.integrations.PublisherIntegration;
/*     */ import com.megacrit.cardcrawl.screens.leaderboards.FilterButton;
/*     */ import java.io.File;
/*     */ import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
/*     */ import net.arikia.dev.drpc.DiscordRichPresence;
import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiscordIntegration
/*     */   implements PublisherIntegration
/*     */ {
/*  21 */   private static final Logger logger = LogManager.getLogger(DiscordIntegration.class.getName());
/*     */   private String[] TEXT;
/*     */   
/*     */   public DiscordIntegration() {
/*  25 */     String appId = "406644123832156160";
/*     */ 
/*     */     
/*  28 */     File unpackPath = new File("discord-rpc");
/*  29 */     unpackPath.deleteOnExit();
/*  30 */     if (!unpackPath.canWrite()) {
/*  31 */       logger.warn("cannot write to dll unpack path: " + unpackPath.getAbsolutePath());
/*     */     }
/*  33 */     if (!unpackPath.exists() && 
/*  34 */       !unpackPath.mkdir()) {
/*  35 */       logger.warn("Failed to create the directory for " + unpackPath.getAbsolutePath());
/*     */     }
/*     */     
/*  38 */     logger.info("Unpacking discord rpc dll to " + unpackPath.getAbsolutePath());
/*  39 */     DiscordRPC.discordInitialize(appId, new DiscordEventHandlers(), true, null, unpackPath);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteAllCloudFiles() {}
/*     */ 
/*     */   
/*     */   public boolean setStat(String id, int value) {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStat(String id) {
/*  62 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean incrementStat(String id, int incrementAmt) {
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getGlobalStat(String id) {
/*  72 */     return 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadDailyLeaderboardScore(String name, int score) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void uploadLeaderboardScore(String name, int score) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlockAchievement(String id) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getLeaderboardEntries(AbstractPlayer.PlayerClass pClass, FilterButton.RegionSetting rSetting, FilterButton.LeaderboardType lType, int start, int end) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getDailyLeaderboard(long date, int start, int end) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichPresenceDisplayPlaying(int floor, String character) {
/* 107 */     if (this.TEXT == null) {
/* 108 */       this.TEXT = (CardCrawlGame.languagePack.getUIString("RichPresence")).TEXT;
/*     */     }
/* 110 */     if (Settings.isDailyRun) {
/* 111 */       String msg = String.format(this.TEXT[0], new Object[] { Integer.valueOf(floor) });
/* 112 */       logger.debug("Setting Rich Presence: " + msg);
/* 113 */       setRichPresenceData("", msg);
/* 114 */     } else if (Settings.isTrial) {
/* 115 */       String msg = String.format(this.TEXT[1], new Object[] { Integer.valueOf(floor) });
/* 116 */       logger.debug("Setting Rich Presence: " + msg);
/* 117 */       setRichPresenceData("", msg);
/*     */     } else {
/* 119 */       String msg = String.format(character + this.TEXT[2], new Object[] { Integer.valueOf(floor) });
/* 120 */       logger.debug("Setting Rich Presence: " + msg);
/* 121 */       setRichPresenceData("", msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRichPresenceDisplayPlaying(int floor, int ascension, String character) {
/* 127 */     if (this.TEXT == null) {
/* 128 */       this.TEXT = (CardCrawlGame.languagePack.getUIString("RichPresence")).TEXT;
/*     */     }
/* 130 */     if (Settings.isDailyRun) {
/* 131 */       String msg = String.format(this.TEXT[0], new Object[] { Integer.valueOf(floor) });
/* 132 */       logger.debug("Setting Rich Presence: " + msg);
/* 133 */       setRichPresenceData("", msg);
/* 134 */     } else if (Settings.isTrial) {
/* 135 */       String msg = String.format(this.TEXT[1], new Object[] { Integer.valueOf(floor) });
/* 136 */       logger.debug("Setting Rich Presence: " + msg);
/* 137 */       setRichPresenceData("", msg);
/*     */     }
/* 139 */     else if (Settings.language == Settings.GameLanguage.ENG || Settings.language == Settings.GameLanguage.DEU || Settings.language == Settings.GameLanguage.TUR || Settings.language == Settings.GameLanguage.KOR || Settings.language == Settings.GameLanguage.RUS || Settings.language == Settings.GameLanguage.SPA || Settings.language == Settings.GameLanguage.DUT) {
/*     */ 
/*     */ 
/*     */       
/* 143 */       String msg = String.format(this.TEXT[4] + character + this.TEXT[2], new Object[] { Integer.valueOf(ascension), Integer.valueOf(floor) });
/* 144 */       logger.debug("Setting Rich Presence: " + msg);
/* 145 */       setRichPresenceData("", msg);
/*     */     } else {
/* 147 */       String msg = String.format(character + this.TEXT[2] + this.TEXT[4], new Object[] { Integer.valueOf(floor), Integer.valueOf(ascension) });
/* 148 */       logger.debug("Setting Rich Presence: " + msg);
/* 149 */       setRichPresenceData("", msg);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichPresenceDisplayInMenu() {
/* 156 */     if (this.TEXT == null) {
/* 157 */       this.TEXT = (CardCrawlGame.languagePack.getUIString("RichPresence")).TEXT;
/*     */     }
/* 159 */     logger.debug("Setting Rich Presence: " + this.TEXT[3]);
/* 160 */     setRichPresenceData(this.TEXT[3], "");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumUnlockedAchievements() {
/* 165 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public DistributorFactory.Distributor getType() {
/* 170 */     return DistributorFactory.Distributor.DISCORD;
/*     */   }
/*     */   
/*     */   private void setRichPresenceData(String state, String details) {
/* 174 */     DiscordRichPresence rich = (new DiscordRichPresence.Builder(state)).setDetails(details).build();
/* 175 */     DiscordRPC.discordUpdatePresence(rich);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\integrations\discord\DiscordIntegration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */