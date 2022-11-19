/*     */ package com.megacrit.cardcrawl.screens.stats;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.localization.AchievementStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class CharStat
/*     */ {
/*  23 */   private static final Logger logger = LogManager.getLogger(CharStat.class.getName());
/*  24 */   private static final AchievementStrings achievementStrings = CardCrawlGame.languagePack.getAchievementString("CharStat");
/*     */   
/*  26 */   public static final String[] NAMES = achievementStrings.NAMES;
/*  27 */   public static final String[] TEXT = achievementStrings.TEXT;
/*     */   private Prefs pref;
/*     */   private String info;
/*     */   private String info2;
/*  31 */   private static Gson gson = new Gson();
/*     */   
/*     */   private int cardsUnlocked;
/*     */   
/*     */   private int relicsUnlocked;
/*     */   
/*     */   private int cardsDiscovered;
/*     */   private int cardsToDiscover;
/*     */   public int furthestAscent;
/*     */   public int highestScore;
/*     */   public int highestDaily;
/*     */   private int totalFloorsClimbed;
/*  43 */   private ArrayList<RunData> runs = new ArrayList<>(); private int numVictory; private int numDeath; public int winStreak; public int bestWinStreak; public int enemyKilled; public int bossKilled;
/*     */   public long playTime;
/*     */   public long fastestTime;
/*     */   public static final String CARD_UNLOCK = "CARD_UNLOCK";
/*     */   public static final String RELIC_UNLOCK = "RELIC_UNLOCK";
/*     */   public static final String HIGHEST_FLOOR = "HIGHEST_FLOOR";
/*     */   public static final String HIGHEST_SCORE = "HIGHEST_SCORE";
/*     */   public static final String HIGHEST_DAILY = "HIGHEST_DAILY";
/*     */   public static final String TOTAL_FLOORS = "TOTAL_FLOORS";
/*     */   public static final String TOTAL_CRYSTALS_FED = "TOTAL_CRYSTALS_FED";
/*     */   public static final String WIN_COUNT = "WIN_COUNT";
/*     */   public static final String LOSE_COUNT = "LOSE_COUNT";
/*     */   public static final String WIN_STREAK = "WIN_STREAK";
/*     */   public static final String BEST_WIN_STREAK = "BEST_WIN_STREAK";
/*     */   public static final String ASCENSION_LEVEL = "ASCENSION_LEVEL";
/*     */   public static final String LAST_ASCENSION_LEVEL = "LAST_ASCENSION_LEVEL";
/*     */   public static final String ENEMY_KILL = "ENEMY_KILL";
/*     */   public static final String BOSS_KILL = "BOSS_KILL";
/*     */   public static final String PLAYTIME = "PLAYTIME";
/*     */   public static final String FASTEST_VICTORY = "FAST_VICTORY";
/*     */   
/*     */   public CharStat(ArrayList<CharStat> allChars) {
/*  65 */     this.cardsUnlocked = 0;
/*  66 */     this.relicsUnlocked = 0;
/*  67 */     this.furthestAscent = 0;
/*  68 */     this.highestScore = 0;
/*  69 */     this.totalFloorsClimbed = 0;
/*  70 */     this.numVictory = 0;
/*  71 */     this.numDeath = 0;
/*  72 */     this.enemyKilled = 0;
/*  73 */     this.bossKilled = 0;
/*  74 */     this.playTime = 0L;
/*  75 */     this.fastestTime = 999999999999L;
/*     */     
/*  77 */     int highestFloorTmp = 0;
/*  78 */     int highestDailyTmp = 0;
/*     */     
/*  80 */     for (CharStat stat : allChars) {
/*  81 */       this.cardsUnlocked += stat.cardsUnlocked;
/*  82 */       this.relicsUnlocked += stat.relicsUnlocked;
/*     */       
/*  84 */       if (stat.furthestAscent > highestFloorTmp) {
/*  85 */         this.furthestAscent = stat.furthestAscent;
/*  86 */         highestFloorTmp = this.furthestAscent;
/*     */       } 
/*     */       
/*  89 */       if (stat.highestDaily > highestDailyTmp) {
/*  90 */         this.highestDaily = stat.highestDaily;
/*  91 */         highestDailyTmp = this.highestDaily;
/*     */       } 
/*     */       
/*  94 */       if (stat.fastestTime < this.fastestTime && stat.fastestTime != 0L) {
/*  95 */         this.fastestTime = stat.fastestTime;
/*     */       }
/*     */       
/*  98 */       this.totalFloorsClimbed += stat.totalFloorsClimbed;
/*  99 */       this.numVictory += stat.numVictory;
/* 100 */       this.numDeath += stat.numDeath;
/* 101 */       this.enemyKilled += stat.enemyKilled;
/* 102 */       this.bossKilled += stat.bossKilled;
/* 103 */       this.playTime += stat.playTime;
/*     */     } 
/*     */     
/* 106 */     this.info = TEXT[0] + formatHMSM(this.playTime) + " NL ";
/* 107 */     this.info += TEXT[1] + this.numVictory + " NL ";
/* 108 */     this.info += TEXT[2] + this.numDeath + " NL ";
/* 109 */     this.info += TEXT[3] + this.totalFloorsClimbed + " NL ";
/* 110 */     this.info += TEXT[4] + this.bossKilled + " NL ";
/* 111 */     this.info += TEXT[5] + this.enemyKilled + " NL ";
/*     */     
/* 113 */     this.info2 = TEXT[7] + UnlockTracker.getCardsSeenString() + " NL ";
/*     */     
/* 115 */     int unlockedCardCount = UnlockTracker.unlockedRedCardCount + UnlockTracker.unlockedGreenCardCount + UnlockTracker.unlockedBlueCardCount + UnlockTracker.unlockedPurpleCardCount;
/*     */     
/* 117 */     int lockedCardCount = UnlockTracker.lockedRedCardCount + UnlockTracker.lockedGreenCardCount + UnlockTracker.lockedBlueCardCount + UnlockTracker.lockedPurpleCardCount;
/*     */ 
/*     */     
/* 120 */     this.info2 += TEXT[8] + unlockedCardCount + "/" + lockedCardCount + " NL ";
/* 121 */     this.info2 += TEXT[9] + UnlockTracker.getRelicsSeenString() + " NL ";
/* 122 */     this.info2 += TEXT[10] + UnlockTracker.unlockedRelicCount + "/" + UnlockTracker.lockedRelicCount + " NL ";
/*     */     
/* 124 */     if (this.fastestTime != 999999999999L) {
/* 125 */       this.info2 += TEXT[13] + formatHMSM(this.fastestTime) + " NL ";
/*     */     }
/*     */   }
/*     */   
/*     */   public CharStat(AbstractPlayer c) {
/* 130 */     this.pref = c.getPrefs();
/*     */     
/* 132 */     this.cardsUnlocked = calculateCardsUnlocked(c);
/* 133 */     this.cardsDiscovered = getSeenCardCount(c);
/* 134 */     this.cardsToDiscover = getCardCountForChar(c);
/* 135 */     this.relicsUnlocked = this.pref.getInteger("RELIC_UNLOCK", 0);
/* 136 */     this.furthestAscent = this.pref.getInteger("HIGHEST_FLOOR", 0);
/* 137 */     this.highestDaily = this.pref.getInteger("HIGHEST_DAILY", 0);
/* 138 */     this.totalFloorsClimbed = this.pref.getInteger("TOTAL_FLOORS", 0);
/* 139 */     this.numVictory = this.pref.getInteger("WIN_COUNT", 0);
/* 140 */     this.numDeath = this.pref.getInteger("LOSE_COUNT", 0);
/* 141 */     this.winStreak = this.pref.getInteger("WIN_STREAK", 0);
/* 142 */     this.bestWinStreak = this.pref.getInteger("BEST_WIN_STREAK", 0);
/* 143 */     this.enemyKilled = this.pref.getInteger("ENEMY_KILL", 0);
/* 144 */     this.bossKilled = this.pref.getInteger("BOSS_KILL", 0);
/* 145 */     this.playTime = this.pref.getLong("PLAYTIME", 0L);
/* 146 */     this.fastestTime = this.pref.getLong("FAST_VICTORY", 0L);
/* 147 */     this.highestScore = this.pref.getInteger("HIGHEST_SCORE", 0);
/*     */     
/* 149 */     this.info = TEXT[0] + formatHMSM(this.playTime) + " NL ";
/* 150 */     this.info += TEXT[7] + this.cardsDiscovered + "/" + this.cardsToDiscover + " NL ";
/* 151 */     this.info += TEXT[8] + this.cardsUnlocked + "/" + UnlockTracker.lockedRedCardCount + " NL ";
/*     */     
/* 153 */     if (this.fastestTime != 0L) {
/* 154 */       this.info += TEXT[13] + formatHMSM(this.fastestTime) + " NL ";
/*     */     }
/* 156 */     this.info += TEXT[23] + this.highestScore + " NL ";
/* 157 */     if (this.bestWinStreak > 0) {
/* 158 */       this.info += TEXT[22] + this.bestWinStreak + " NL ";
/*     */     }
/*     */     
/* 161 */     this.info2 = TEXT[17] + this.numVictory + " NL ";
/* 162 */     this.info2 += TEXT[18] + this.numDeath + " NL ";
/* 163 */     this.info2 += TEXT[19] + this.totalFloorsClimbed + " NL ";
/* 164 */     this.info2 += TEXT[20] + this.bossKilled + " NL ";
/* 165 */     this.info2 += TEXT[21] + this.enemyKilled + " NL ";
/*     */     
/* 167 */     StringBuilder sb = new StringBuilder();
/* 168 */     sb.append("runs").append(File.separator);
/*     */     
/* 170 */     if (CardCrawlGame.saveSlot != 0) {
/* 171 */       sb.append(CardCrawlGame.saveSlot).append("_");
/*     */     }
/*     */     
/* 174 */     sb.append(c.chosenClass.name()).append(File.separator);
/*     */     
/* 176 */     FileHandle[] files = Gdx.files.local(sb.toString()).list();
/* 177 */     for (FileHandle file : files) {
/*     */       try {
/* 179 */         this.runs.add(gson.fromJson(file.readString(), RunData.class));
/* 180 */       } catch (Exception e) {
/* 181 */         file.delete();
/* 182 */         logger.warn("Deleted corrupt .run file, preventing crash!", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int calculateCardsUnlocked(AbstractPlayer c) {
/* 188 */     return c.getUnlockedCardCount();
/*     */   }
/*     */   
/*     */   private int getSeenCardCount(AbstractPlayer c) {
/* 192 */     return c.getSeenCardCount();
/*     */   }
/*     */   
/*     */   private int getCardCountForChar(AbstractPlayer c) {
/* 196 */     return c.getCardCount();
/*     */   }
/*     */   
/*     */   public void highestScore(int score) {
/* 200 */     if (score > this.highestScore) {
/* 201 */       this.highestScore = score;
/* 202 */       this.pref.putInteger("HIGHEST_SCORE", this.highestScore);
/* 203 */       this.pref.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void furthestAscent(int floor) {
/* 208 */     if (floor > this.furthestAscent) {
/* 209 */       this.furthestAscent = floor;
/* 210 */       this.pref.putInteger("HIGHEST_FLOOR", this.furthestAscent);
/* 211 */       this.pref.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void highestDaily(int score) {
/* 216 */     if (score > this.highestDaily) {
/* 217 */       this.highestDaily = score;
/* 218 */       this.pref.putInteger("HIGHEST_DAILY", this.highestDaily);
/* 219 */       this.pref.flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void incrementFloorClimbed() {
/* 224 */     this.totalFloorsClimbed++;
/* 225 */     this.pref.putInteger("TOTAL_FLOORS", this.totalFloorsClimbed);
/* 226 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public void incrementDeath() {
/* 230 */     this.numDeath++;
/*     */     
/* 232 */     if (!AbstractDungeon.isAscensionMode) {
/* 233 */       this.winStreak = 0;
/* 234 */       this.pref.putInteger("WIN_STREAK", this.winStreak);
/*     */     } 
/*     */     
/* 237 */     this.pref.putInteger("LOSE_COUNT", this.numDeath);
/* 238 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public int getVictoryCount() {
/* 242 */     return this.numVictory;
/*     */   }
/*     */   
/*     */   public int getDeathCount() {
/* 246 */     return this.numDeath;
/*     */   }
/*     */   
/*     */   public void unlockAscension() {
/* 250 */     this.pref.putInteger("ASCENSION_LEVEL", 1);
/* 251 */     this.pref.putInteger("LAST_ASCENSION_LEVEL", 1);
/*     */   }
/*     */   
/*     */   public void incrementAscension() {
/* 255 */     if (!Settings.isTrial) {
/* 256 */       int derp = this.pref.getInteger("ASCENSION_LEVEL", 1);
/* 257 */       if (derp == AbstractDungeon.ascensionLevel) {
/* 258 */         derp++;
/*     */         
/* 260 */         if (derp <= 20) {
/* 261 */           this.pref.putInteger("ASCENSION_LEVEL", derp);
/* 262 */           this.pref.putInteger("LAST_ASCENSION_LEVEL", derp);
/* 263 */           this.pref.flush();
/* 264 */           logger.info("ASCENSION LEVEL IS NOW: " + derp);
/*     */         } else {
/*     */           
/* 267 */           this.pref.putInteger("ASCENSION_LEVEL", 20);
/* 268 */           this.pref.putInteger("LAST_ASCENSION_LEVEL", 20);
/* 269 */           this.pref.flush();
/* 270 */           logger.info("MAX ASCENSION");
/*     */         } 
/*     */       } else {
/* 273 */         logger.info("Played Ascension that wasn't Max");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void incrementVictory() {
/* 279 */     this.numVictory++;
/*     */     
/* 281 */     if (!AbstractDungeon.isAscensionMode) {
/* 282 */       this.winStreak++;
/* 283 */       this.pref.putInteger("WIN_STREAK", this.winStreak);
/*     */       
/* 285 */       if (this.winStreak > this.pref.getInteger("BEST_WIN_STREAK", 0)) {
/* 286 */         this.pref.putInteger("BEST_WIN_STREAK", this.winStreak);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 292 */     this.pref.putInteger("WIN_COUNT", this.numVictory);
/* 293 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public void incrementBossSlain() {
/* 297 */     this.bossKilled++;
/* 298 */     this.pref.putInteger("BOSS_KILL", this.bossKilled);
/* 299 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public void incrementEnemySlain() {
/* 303 */     this.enemyKilled++;
/* 304 */     this.pref.putInteger("ENEMY_KILL", this.enemyKilled);
/* 305 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public void incrementPlayTime(long time) {
/* 309 */     this.playTime += time;
/* 310 */     this.pref.putLong("PLAYTIME", this.playTime);
/* 311 */     this.pref.flush();
/*     */   }
/*     */   
/*     */   public static String formatHMSM(float t) {
/* 315 */     String res = "";
/* 316 */     long duration = (long)t;
/* 317 */     int seconds = (int)(duration % 60L);
/* 318 */     duration /= 60L;
/* 319 */     int minutes = (int)(duration % 60L);
/* 320 */     int hours = (int)t / 3600;
/*     */     
/* 322 */     if (hours > 0) {
/* 323 */       res = String.format(TEXT[24], new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds) });
/*     */     } else {
/* 325 */       res = String.format(TEXT[25], new Object[] { Integer.valueOf(minutes), Integer.valueOf(seconds) });
/*     */     } 
/* 327 */     return res;
/*     */   }
/*     */   
/*     */   public static String formatHMSM(long t) {
/* 331 */     String res = "";
/* 332 */     long duration = t;
/* 333 */     int seconds = (int)(duration % 60L);
/* 334 */     duration /= 60L;
/* 335 */     int minutes = (int)(duration % 60L);
/* 336 */     int hours = (int)t / 3600;
/*     */     
/* 338 */     if (hours > 0) {
/* 339 */       res = String.format(TEXT[26], new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds) });
/*     */     } else {
/* 341 */       res = String.format(TEXT[27], new Object[] { Integer.valueOf(minutes), Integer.valueOf(seconds) });
/*     */     } 
/* 343 */     return res;
/*     */   }
/*     */   
/*     */   public static String formatHMSM(int t) {
/* 347 */     String res = "";
/* 348 */     long duration = t;
/* 349 */     int seconds = (int)(duration % 60L);
/* 350 */     duration /= 60L;
/* 351 */     int minutes = (int)(duration % 60L);
/* 352 */     int hours = t / 3600;
/*     */     
/* 354 */     res = String.format(TEXT[28], new Object[] { Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds) });
/* 355 */     return res;
/*     */   }
/*     */   
/*     */   public void updateFastestVictory(long newTime) {
/* 359 */     if (newTime < this.fastestTime || this.fastestTime == 0L) {
/* 360 */       this.fastestTime = newTime;
/* 361 */       this.pref.putLong("FAST_VICTORY", this.fastestTime);
/* 362 */       this.pref.flush();
/* 363 */       logger.info("Fastest victory time updated to: " + this.fastestTime);
/*     */     } else {
/* 365 */       logger.info("Did not save fastest victory.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float screenX, float renderY) {
/* 370 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, this.info, screenX + 75.0F * Settings.scale, renderY + 766.0F * Settings.yScale, 9999.0F, 38.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     if (this.info2 != null)
/* 381 */       FontHelper.renderSmartText(sb, FontHelper.panelNameFont, this.info2, screenX + 675.0F * Settings.scale, renderY + 766.0F * Settings.yScale, 9999.0F, 38.0F * Settings.scale, Settings.CREAM_COLOR); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\stats\CharStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */