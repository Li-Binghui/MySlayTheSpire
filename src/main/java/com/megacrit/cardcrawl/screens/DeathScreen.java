/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.integrations.PublisherIntegration;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.buttons.DynamicBanner;
import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.unlock.misc.DefectUnlock;
import com.megacrit.cardcrawl.unlock.misc.TheSilentUnlock;
import com.megacrit.cardcrawl.unlock.misc.WatcherUnlock;
import com.megacrit.cardcrawl.vfx.AscensionLevelUpTextEffect;
import com.megacrit.cardcrawl.vfx.AscensionUnlockedTextEffect;
import com.megacrit.cardcrawl.vfx.DeathScreenFloatyEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeathScreen
/*     */   extends GameOverScreen
/*     */ {
/*  51 */   private static final Logger logger = LogManager.getLogger(DeathScreen.class.getName());
/*  52 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DeathScreen");
/*  53 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private MonsterGroup monsters;
/*     */   private String deathText;
/*  57 */   private ArrayList<DeathScreenFloatyEffect> particles = new ArrayList<>();
/*     */   private static final float NUM_PARTICLES = 50.0F;
/*  59 */   private float deathAnimWaitTimer = 1.0F;
/*     */   private static final float DEATH_TEXT_TIME = 5.0F;
/*  61 */   private float deathTextTimer = 5.0F;
/*  62 */   private Color defeatTextColor = Color.WHITE.cpy();
/*  63 */   private Color deathTextColor = Settings.BLUE_TEXT_COLOR.cpy();
/*  64 */   private static final float DEATH_TEXT_Y = Settings.HEIGHT - 360.0F * Settings.scale;
/*     */   private boolean defectUnlockedThisRun = false;
/*     */   
/*     */   public DeathScreen(MonsterGroup m) {
/*  68 */     this.playtime = (long)CardCrawlGame.playtime;
/*     */     
/*  70 */     if (this.playtime < 0L) {
/*  71 */       this.playtime = 0L;
/*     */     }
/*     */     
/*  74 */     AbstractDungeon.getCurrRoom().clearEvent();
/*  75 */     resetScoreChecks();
/*     */     
/*  77 */     AbstractDungeon.is_victory = false;
/*  78 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/*  79 */       c.unhover();
/*     */     }
/*     */     
/*  82 */     if (AbstractDungeon.player.stance != null) {
/*  83 */       AbstractDungeon.player.stance.stopIdleSfx();
/*     */     }
/*  85 */     AbstractDungeon.dungeonMapScreen.closeInstantly();
/*  86 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.DEATH;
/*  87 */     AbstractDungeon.overlayMenu.showBlackScreen(1.0F);
/*  88 */     AbstractDungeon.previousScreen = null;
/*  89 */     AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
/*  90 */     AbstractDungeon.isScreenUp = true;
/*  91 */     this.deathText = getDeathText();
/*  92 */     this.monsters = m;
/*  93 */     logger.info("PLAYTIME: " + this.playtime);
/*     */     
/*  95 */     if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
/*  96 */       ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*     */     }
/*     */     
/*  99 */     isVictory = AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.VictoryRoom;
/*     */     
/* 101 */     if (!isVictory) {
/*     */       String winStreakStatId;
/* 103 */       PublisherIntegration pubInteg = CardCrawlGame.publisherIntegration;
/*     */       
/* 105 */       if (Settings.isBeta) {
/* 106 */         winStreakStatId = AbstractDungeon.player.getWinStreakKey() + "_BETA";
/*     */       } else {
/* 108 */         winStreakStatId = AbstractDungeon.player.getWinStreakKey();
/*     */       } 
/* 110 */       pubInteg.setStat(winStreakStatId, 0);
/* 111 */       logger.info("WIN STREAK  " + pubInteg.getStat(winStreakStatId));
/*     */     } 
/*     */ 
/*     */     
/* 115 */     this.showingStats = false;
/* 116 */     this.returnButton = new ReturnToMenuButton();
/* 117 */     this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */     
/* 119 */     if (AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.VictoryRoom) {
/* 120 */       AbstractDungeon.dynamicBanner.appear(TEXT[1]);
/*     */     } else {
/* 122 */       AbstractDungeon.dynamicBanner.appear(getDeathBannerText());
/*     */     } 
/*     */     
/* 125 */     if (Settings.isStandardRun()) {
/* 126 */       if (AbstractDungeon.floorNum >= 16) {
/* 127 */         logger.info("Neow available");
/* 128 */         CardCrawlGame.playerPref.putInteger(AbstractDungeon.player.chosenClass.name() + "_SPIRITS", 1);
/*     */       } else {
/* 130 */         logger.info("No Neow for you");
/* 131 */         CardCrawlGame.playerPref.putInteger(AbstractDungeon.player.chosenClass.name() + "_SPIRITS", 0);
/* 132 */         AbstractDungeon.bossCount = 0;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 137 */     CardCrawlGame.music.dispose();
/*     */ 
/*     */     
/* 140 */     CardCrawlGame.sound.play("DEATH_STINGER", true);
/*     */ 
/*     */     
/* 143 */     String bgmKey = null;
/* 144 */     switch (MathUtils.random(0, 3)) {
/*     */       case 0:
/* 146 */         bgmKey = "STS_DeathStinger_1_v3_MUSIC.ogg";
/*     */         break;
/*     */       case 1:
/* 149 */         bgmKey = "STS_DeathStinger_2_v3_MUSIC.ogg";
/*     */         break;
/*     */       case 2:
/* 152 */         bgmKey = "STS_DeathStinger_3_v3_MUSIC.ogg";
/*     */         break;
/*     */       case 3:
/* 155 */         bgmKey = "STS_DeathStinger_4_v3_MUSIC.ogg";
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 160 */     CardCrawlGame.music.playTempBgmInstantly(bgmKey, false);
/*     */     
/* 162 */     if (isVictory) {
/* 163 */       UnlockTracker.unlockAchievement(AbstractDungeon.player.getAchievementKey());
/* 164 */       submitVictoryMetrics();
/*     */       
/* 166 */       if (this.playtime != 0L) {
/* 167 */         StatsScreen.updateVictoryTime(this.playtime);
/*     */       }
/*     */       
/* 170 */       StatsScreen.incrementVictory(AbstractDungeon.player.getCharStat());
/*     */       
/* 172 */       if (AbstractDungeon.ascensionLevel == 10 && !Settings.isTrial) {
/* 173 */         UnlockTracker.unlockAchievement("ASCEND_10");
/* 174 */       } else if (AbstractDungeon.ascensionLevel == 20 && !Settings.isTrial) {
/* 175 */         UnlockTracker.unlockAchievement("ASCEND_20");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 180 */       if (AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.actNum == 4) {
/* 181 */         UnlockTracker.unlockAchievement("ASCEND_20");
/*     */       }
/*     */       
/* 184 */       submitDefeatMetrics(m);
/* 185 */       StatsScreen.incrementDeath(AbstractDungeon.player.getCharStat());
/*     */     } 
/*     */     
/* 188 */     if (Settings.isStandardRun() && AbstractDungeon.actNum > 3) {
/* 189 */       StatsScreen.incrementVictoryIfZero(AbstractDungeon.player.getCharStat());
/*     */     }
/*     */     
/* 192 */     this.defeatTextColor.a = 0.0F;
/* 193 */     this.deathTextColor.a = 0.0F;
/*     */     
/* 195 */     if (this.playtime != 0L) {
/* 196 */       StatsScreen.incrementPlayTime(this.playtime);
/*     */     }
/*     */     
/* 199 */     if (Settings.isStandardRun()) {
/* 200 */       StatsScreen.updateFurthestAscent(AbstractDungeon.floorNum);
/* 201 */     } else if (Settings.isDailyRun) {
/* 202 */       StatsScreen.updateHighestDailyScore(AbstractDungeon.floorNum);
/*     */     } 
/*     */     
/* 205 */     if (SaveHelper.shouldDeleteSave()) {
/* 206 */       SaveAndContinue.deleteSave(AbstractDungeon.player);
/*     */     }
/*     */     
/* 209 */     calculateUnlockProgress();
/* 210 */     if (!Settings.isEndless) {
/* 211 */       uploadToSteamLeaderboards();
/*     */     }
/* 213 */     createGameOverStats();
/* 214 */     CardCrawlGame.playerPref.flush();
/*     */   }
/*     */   
/*     */   private void createGameOverStats() {
/* 218 */     this.stats.clear();
/*     */ 
/*     */     
/* 221 */     this.stats.add(new GameOverStat(TEXT[2] + " (" + AbstractDungeon.floorNum + ")", null, 
/* 222 */           Integer.toString(floorPoints)));
/*     */ 
/*     */     
/* 225 */     this.stats.add(new GameOverStat(TEXT[43] + " (" + CardCrawlGame.monstersSlain + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 229 */           Integer.toString(monsterPoints)));
/*     */ 
/*     */     
/* 232 */     this.stats.add(new GameOverStat(EXORDIUM_ELITE.NAME + " (" + CardCrawlGame.elites1Slain + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 236 */           Integer.toString(elite1Points)));
/*     */     
/* 238 */     if (Settings.isEndless) {
/* 239 */       if (CardCrawlGame.elites2Slain > 0) {
/* 240 */         this.stats.add(new GameOverStat(CITY_ELITE.NAME + " (" + CardCrawlGame.elites2Slain + ")", null, 
/*     */ 
/*     */ 
/*     */               
/* 244 */               Integer.toString(elite2Points)));
/*     */       }
/*     */     }
/* 247 */     else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheCity || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/*     */       
/* 249 */       this.stats.add(new GameOverStat(CITY_ELITE.NAME + " (" + CardCrawlGame.elites2Slain + ")", null, 
/*     */ 
/*     */ 
/*     */             
/* 253 */             Integer.toString(elite2Points)));
/*     */     } 
/*     */ 
/*     */     
/* 257 */     if (Settings.isEndless) {
/* 258 */       if (CardCrawlGame.elites3Slain > 0) {
/* 259 */         this.stats.add(new GameOverStat(BEYOND_ELITE.NAME + " (" + CardCrawlGame.elites3Slain + ")", null, 
/*     */ 
/*     */ 
/*     */               
/* 263 */               Integer.toString(elite3Points)));
/*     */       }
/*     */     }
/* 266 */     else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/* 267 */       this.stats.add(new GameOverStat(BEYOND_ELITE.NAME + " (" + CardCrawlGame.elites3Slain + ")", null, 
/*     */ 
/*     */ 
/*     */             
/* 271 */             Integer.toString(elite3Points)));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 276 */     this.stats.add(new GameOverStat(BOSSES_SLAIN.NAME + " (" + AbstractDungeon.bossCount + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 280 */           Integer.toString(bossPoints)));
/*     */ 
/*     */     
/* 283 */     if (IS_POOPY) {
/* 284 */       this.stats.add(new GameOverStat(POOPY.NAME, POOPY.DESCRIPTIONS[0], Integer.toString(-1)));
/*     */     }
/* 286 */     if (IS_SPEEDSTER) {
/* 287 */       this.stats.add(new GameOverStat(SPEEDSTER.NAME, SPEEDSTER.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/* 289 */     if (IS_LIGHT_SPEED) {
/* 290 */       this.stats.add(new GameOverStat(LIGHT_SPEED.NAME, LIGHT_SPEED.DESCRIPTIONS[0], 
/* 291 */             Integer.toString(50)));
/*     */     }
/* 293 */     if (IS_HIGHLANDER) {
/* 294 */       this.stats.add(new GameOverStat(HIGHLANDER.NAME, HIGHLANDER.DESCRIPTIONS[0], 
/* 295 */             Integer.toString(100)));
/*     */     }
/* 297 */     if (IS_SHINY) {
/* 298 */       this.stats.add(new GameOverStat(SHINY.NAME, SHINY.DESCRIPTIONS[0], Integer.toString(50)));
/*     */     }
/*     */     
/* 301 */     if (IS_I_LIKE_GOLD) {
/* 302 */       this.stats.add(new GameOverStat(I_LIKE_GOLD.NAME + " (" + CardCrawlGame.goldGained + ")", I_LIKE_GOLD.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 306 */             Integer.toString(75)));
/* 307 */     } else if (IS_RAINING_MONEY) {
/* 308 */       this.stats.add(new GameOverStat(RAINING_MONEY.NAME + " (" + CardCrawlGame.goldGained + ")", RAINING_MONEY.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 312 */             Integer.toString(50)));
/* 313 */     } else if (IS_MONEY_MONEY) {
/* 314 */       this.stats.add(new GameOverStat(MONEY_MONEY.NAME + " (" + CardCrawlGame.goldGained + ")", MONEY_MONEY.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 318 */             Integer.toString(25)));
/*     */     } 
/*     */     
/* 321 */     if (IS_MYSTERY_MACHINE) {
/* 322 */       this.stats.add(new GameOverStat(MYSTERY_MACHINE.NAME + " (" + CardCrawlGame.mysteryMachine + ")", MYSTERY_MACHINE.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 326 */             Integer.toString(25)));
/*     */     }
/*     */     
/* 329 */     if (IS_FULL_SET > 0) {
/* 330 */       this.stats.add(new GameOverStat(COLLECTOR.NAME + " (" + IS_FULL_SET + ")", COLLECTOR.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 334 */             Integer.toString(25 * IS_FULL_SET)));
/*     */     }
/*     */     
/* 337 */     if (IS_PAUPER) {
/* 338 */       this.stats.add(new GameOverStat(PAUPER.NAME, PAUPER.DESCRIPTIONS[0], Integer.toString(50)));
/*     */     }
/* 340 */     if (IS_LIBRARY) {
/* 341 */       this.stats.add(new GameOverStat(LIBRARIAN.NAME, LIBRARIAN.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/* 343 */     if (IS_ENCYCLOPEDIA) {
/* 344 */       this.stats.add(new GameOverStat(ENCYCLOPEDIAN.NAME, ENCYCLOPEDIAN.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 348 */             Integer.toString(50)));
/*     */     }
/*     */     
/* 351 */     if (IS_STUFFED) {
/* 352 */       this.stats.add(new GameOverStat(STUFFED.NAME, STUFFED.DESCRIPTIONS[0], Integer.toString(50)));
/* 353 */     } else if (IS_WELL_FED) {
/* 354 */       this.stats.add(new GameOverStat(WELL_FED.NAME, WELL_FED.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     } 
/*     */     
/* 357 */     if (IS_CURSES) {
/* 358 */       this.stats.add(new GameOverStat(CURSES.NAME, CURSES.DESCRIPTIONS[0], Integer.toString(100)));
/*     */     }
/*     */     
/* 361 */     if (IS_ON_MY_OWN) {
/* 362 */       this.stats.add(new GameOverStat(ON_MY_OWN_TERMS.NAME, ON_MY_OWN_TERMS.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 366 */             Integer.toString(50)));
/*     */     }
/*     */     
/* 369 */     if (CardCrawlGame.champion > 0) {
/* 370 */       this.stats.add(new GameOverStat(CHAMPION.NAME + " (" + CardCrawlGame.champion + ")", CHAMPION.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 374 */             Integer.toString(25 * CardCrawlGame.champion)));
/*     */     }
/*     */     
/* 377 */     if (CardCrawlGame.perfect >= 3) {
/* 378 */       this.stats.add(new GameOverStat(BEYOND_PERFECT.NAME, BEYOND_PERFECT.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 382 */             Integer.toString(200)));
/* 383 */     } else if (CardCrawlGame.perfect > 0) {
/* 384 */       this.stats.add(new GameOverStat(PERFECT.NAME + " (" + CardCrawlGame.perfect + ")", PERFECT.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 388 */             Integer.toString(50 * CardCrawlGame.perfect)));
/*     */     } 
/*     */     
/* 391 */     if (CardCrawlGame.overkill) {
/* 392 */       this.stats.add(new GameOverStat(OVERKILL.NAME, OVERKILL.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/*     */     
/* 395 */     if (CardCrawlGame.combo) {
/* 396 */       this.stats.add(new GameOverStat(COMBO.NAME, COMBO.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/*     */     
/* 399 */     if (AbstractDungeon.isAscensionMode)
/*     */     {
/* 401 */       this.stats.add(new GameOverStat(ASCENSION.NAME + " (" + AbstractDungeon.ascensionLevel + ")", ASCENSION.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 405 */             Integer.toString(ascensionPoints)));
/*     */     }
/*     */ 
/*     */     
/* 409 */     this.stats.add(new GameOverStat());
/*     */ 
/*     */     
/* 412 */     this.stats.add(new GameOverStat(TEXT[6], null, Integer.toString(this.score)));
/*     */   }
/*     */   
/*     */   private void submitDefeatMetrics(MonsterGroup m) {
/* 416 */     if (m != null && !m.areMonstersDead() && !m.areMonstersBasicallyDead()) {
/* 417 */       CardCrawlGame.metricData.addEncounterData();
/*     */     }
/*     */     
/* 420 */     Metrics metrics = new Metrics();
/*     */ 
/*     */     
/* 423 */     metrics.gatherAllDataAndSave(true, false, m);
/*     */     
/* 425 */     if (shouldUploadMetricData()) {
/* 426 */       metrics.setValues(true, false, m, Metrics.MetricRequestType.UPLOAD_METRICS);
/* 427 */       Thread t = new Thread((Runnable)metrics);
/* 428 */       t.setName("Metrics");
/* 429 */       t.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void submitVictoryMetrics() {
/* 435 */     Metrics metrics = new Metrics();
/*     */ 
/*     */     
/* 438 */     metrics.gatherAllDataAndSave(false, false, null);
/*     */     
/* 440 */     if (shouldUploadMetricData()) {
/* 441 */       metrics.setValues(false, false, null, Metrics.MetricRequestType.UPLOAD_METRICS);
/* 442 */       Thread t = new Thread((Runnable)metrics);
/* 443 */       t.start();
/*     */     } 
/*     */     
/* 446 */     if (Settings.isStandardRun()) {
/* 447 */       StatsScreen.updateFurthestAscent(AbstractDungeon.floorNum);
/*     */     }
/*     */     
/* 450 */     if (SaveHelper.shouldDeleteSave()) {
/* 451 */       SaveAndContinue.deleteSave(AbstractDungeon.player);
/*     */     }
/*     */   }
/*     */   
/*     */   private String getDeathBannerText() {
/* 456 */     ArrayList<String> list = new ArrayList<>();
/* 457 */     list.add(TEXT[7]);
/* 458 */     list.add(TEXT[8]);
/* 459 */     list.add(TEXT[9]);
/* 460 */     list.add(TEXT[10]);
/* 461 */     list.add(TEXT[11]);
/* 462 */     list.add(TEXT[12]);
/* 463 */     list.add(TEXT[13]);
/* 464 */     list.add(TEXT[14]);
/*     */     
/* 466 */     return list.get(MathUtils.random(list.size() - 1));
/*     */   }
/*     */   
/*     */   private String getDeathText() {
/* 470 */     ArrayList<String> list = new ArrayList<>();
/* 471 */     list.add(TEXT[15]);
/* 472 */     list.add(TEXT[16]);
/* 473 */     list.add(TEXT[17]);
/* 474 */     list.add(TEXT[18]);
/* 475 */     list.add(TEXT[19]);
/* 476 */     list.add(TEXT[20]);
/* 477 */     list.add(TEXT[21]);
/* 478 */     list.add(TEXT[22]);
/* 479 */     list.add(TEXT[23]);
/* 480 */     list.add(TEXT[24]);
/* 481 */     list.add(TEXT[25]);
/* 482 */     list.add(TEXT[26]);
/* 483 */     list.add(TEXT[27]);
/* 484 */     list.add(TEXT[28]);
/* 485 */     list.add(TEXT[29]);
/*     */     
/* 487 */     if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/* 488 */       list.add("...");
/*     */     }
/*     */     
/* 491 */     return list.get(MathUtils.random(list.size() - 1));
/*     */   }
/*     */   
/*     */   public void hide() {
/* 495 */     this.returnButton.hide();
/* 496 */     AbstractDungeon.dynamicBanner.hide();
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 500 */     reopen(false);
/*     */   }
/*     */   
/*     */   public void reopen(boolean fromVictoryUnlock) {
/* 504 */     AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/* 505 */     this.statsTimer = 0.5F;
/* 506 */     if (isVictory) {
/* 507 */       AbstractDungeon.dynamicBanner.appearInstantly(TEXT[1]);
/*     */     } else {
/* 509 */       AbstractDungeon.dynamicBanner.appearInstantly(TEXT[30]);
/*     */     } 
/* 511 */     AbstractDungeon.overlayMenu.showBlackScreen(1.0F);
/*     */     
/* 513 */     if (fromVictoryUnlock) {
/* 514 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[31]);
/*     */     }
/* 516 */     else if (!this.showingStats) {
/* 517 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[32]);
/*     */     }
/* 519 */     else if (this.unlockBundle == null) {
/* 520 */       if (!isVictory) {
/* 521 */         if (UnlockTracker.isCharacterLocked("The Silent") || (UnlockTracker.isCharacterLocked("Defect") && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) || 
/*     */           
/* 523 */           willWatcherUnlock()) {
/* 524 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/*     */         } else {
/* 526 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[34]);
/*     */         } 
/*     */       } else {
/* 529 */         this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[35]);
/*     */       } 
/*     */     } else {
/* 532 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[36]);
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
/*     */   private boolean willWatcherUnlock() {
/* 545 */     if (this.defectUnlockedThisRun || !UnlockTracker.isCharacterLocked("Watcher")) {
/* 546 */       return false;
/*     */     }
/*     */     
/* 549 */     return (!UnlockTracker.isCharacterLocked("Defect") && (UnlockTracker.isAchievementUnlocked("RUBY") || 
/* 550 */       UnlockTracker.isAchievementUnlocked("EMERALD") || 
/* 551 */       UnlockTracker.isAchievementUnlocked("SAPPHIRE")));
/*     */   }
/*     */   
/*     */   public void update() {
/* 555 */     if (Settings.isDebug && InputHelper.justClickedRight) {
/* 556 */       UnlockTracker.resetUnlockProgress(AbstractDungeon.player.chosenClass);
/*     */     }
/*     */     
/* 559 */     updateControllerInput();
/* 560 */     this.returnButton.update();
/* 561 */     if (this.returnButton.hb.clicked || (this.returnButton.show && CInputActionSet.select.isJustPressed())) {
/* 562 */       CInputActionSet.topPanel.unpress();
/* 563 */       if (Settings.isControllerMode) {
/* 564 */         Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/*     */       }
/* 566 */       this.returnButton.hb.clicked = false;
/* 567 */       if (!this.showingStats) {
/* 568 */         this.showingStats = true;
/* 569 */         this.statsTimer = 0.5F;
/* 570 */         logger.info("Clicked");
/*     */ 
/*     */         
/* 573 */         this.returnButton = new ReturnToMenuButton();
/*     */ 
/*     */         
/* 576 */         updateAscensionProgress();
/*     */         
/* 578 */         if (this.unlockBundle == null) {
/* 579 */           if (!isVictory) {
/* 580 */             if (UnlockTracker.isCharacterLocked("The Silent") || (UnlockTracker.isCharacterLocked("Defect") && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) || 
/*     */               
/* 582 */               willWatcherUnlock()) {
/* 583 */               this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/*     */             } else {
/* 585 */               this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[37]);
/*     */             } 
/*     */           } else {
/* 588 */             this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[39]);
/*     */           } 
/*     */         } else {
/* 591 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/*     */         } 
/* 593 */       } else if (this.unlockBundle != null) {
/* 594 */         AbstractDungeon.gUnlockScreen.open(this.unlockBundle, false);
/* 595 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/* 596 */         AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NEOW_UNLOCK;
/* 597 */         this.unlockBundle = null;
/* 598 */         if (UnlockTracker.isCharacterLocked("The Silent")) {
/* 599 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/*     */         } else {
/* 601 */           this.returnButton.label = TEXT[37];
/*     */         }
/*     */       
/* 604 */       } else if (isVictory) {
/* 605 */         this.returnButton.hide();
/*     */         
/* 607 */         if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo) {
/* 608 */           if (Settings.isDemo || Settings.isDailyRun) {
/* 609 */             CardCrawlGame.startOver();
/*     */           }
/* 611 */           else if (UnlockTracker.isCharacterLocked("The Silent")) {
/* 612 */             AbstractDungeon.unlocks.add(new TheSilentUnlock());
/* 613 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 614 */           } else if (UnlockTracker.isCharacterLocked("Defect") && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/*     */             
/* 616 */             AbstractDungeon.unlocks.add(new DefectUnlock());
/* 617 */             this.defectUnlockedThisRun = true;
/* 618 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 619 */           } else if (willWatcherUnlock()) {
/* 620 */             AbstractDungeon.unlocks.add(new WatcherUnlock());
/* 621 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*     */           } else {
/* 623 */             CardCrawlGame.playCreditsBgm = false;
/* 624 */             CardCrawlGame.startOverButShowCredits();
/*     */           } 
/*     */         } else {
/*     */           
/* 628 */           AbstractDungeon.unlocks.clear();
/* 629 */           Settings.isTrial = false;
/* 630 */           Settings.isDailyRun = false;
/* 631 */           Settings.isEndless = false;
/* 632 */           CardCrawlGame.trial = null;
/* 633 */           if (Settings.isDailyRun) {
/* 634 */             CardCrawlGame.startOver();
/*     */           } else {
/* 636 */             CardCrawlGame.playCreditsBgm = false;
/* 637 */             CardCrawlGame.startOverButShowCredits();
/*     */           } 
/*     */         } 
/*     */       } else {
/* 641 */         this.returnButton.hide();
/*     */         
/* 643 */         if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo || Settings.isDailyRun || Settings.isTrial) {
/*     */ 
/*     */           
/* 646 */           if (UnlockTracker.isCharacterLocked("The Silent")) {
/* 647 */             AbstractDungeon.unlocks.add(new TheSilentUnlock());
/* 648 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 649 */           } else if (UnlockTracker.isCharacterLocked("Defect") && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/*     */             
/* 651 */             AbstractDungeon.unlocks.add(new DefectUnlock());
/* 652 */             this.defectUnlockedThisRun = true;
/* 653 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 654 */           } else if (willWatcherUnlock()) {
/* 655 */             AbstractDungeon.unlocks.add(new WatcherUnlock());
/* 656 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*     */           } else {
/* 658 */             Settings.isTrial = false;
/* 659 */             Settings.isDailyRun = false;
/* 660 */             Settings.isEndless = false;
/* 661 */             CardCrawlGame.trial = null;
/* 662 */             CardCrawlGame.startOver();
/*     */           } 
/*     */         } else {
/* 665 */           AbstractDungeon.unlocks.clear();
/* 666 */           Settings.isTrial = false;
/* 667 */           Settings.isDailyRun = false;
/* 668 */           Settings.isEndless = false;
/* 669 */           CardCrawlGame.trial = null;
/* 670 */           CardCrawlGame.playCreditsBgm = false;
/* 671 */           CardCrawlGame.startOverButShowCredits();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 677 */     updateStatsScreen();
/*     */     
/* 679 */     if (this.deathAnimWaitTimer != 0.0F) {
/* 680 */       this.deathAnimWaitTimer -= Gdx.graphics.getDeltaTime();
/* 681 */       if (this.deathAnimWaitTimer < 0.0F) {
/* 682 */         this.deathAnimWaitTimer = 0.0F;
/* 683 */         AbstractDungeon.player.playDeathAnimation();
/*     */       } 
/*     */     } else {
/* 686 */       this.deathTextTimer -= Gdx.graphics.getDeltaTime();
/* 687 */       if (this.deathTextTimer < 0.0F) {
/* 688 */         this.deathTextTimer = 0.0F;
/*     */       }
/*     */       
/* 691 */       this.deathTextColor.a = Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.deathTextTimer / 5.0F);
/* 692 */       this.defeatTextColor.a = Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.deathTextTimer / 5.0F);
/*     */     } 
/*     */     
/* 695 */     if (this.monsters != null) {
/* 696 */       this.monsters.update();
/* 697 */       this.monsters.updateAnimations();
/*     */     } 
/*     */     
/* 700 */     if (this.particles.size() < 50.0F) {
/* 701 */       this.particles.add(new DeathScreenFloatyEffect());
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 706 */     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.player.viewingRelics) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 711 */     boolean anyHovered = false;
/* 712 */     int index = 0;
/* 713 */     if (this.stats != null) {
/* 714 */       for (GameOverStat s : this.stats) {
/* 715 */         if (s.hb.hovered) {
/* 716 */           anyHovered = true;
/*     */           break;
/*     */         } 
/* 719 */         index++;
/*     */       } 
/*     */     }
/*     */     
/* 723 */     if (!anyHovered) {
/* 724 */       index = -1;
/*     */     }
/*     */     
/* 727 */     if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 728 */       index--;
/* 729 */       if (this.stats.size() > 10) {
/* 730 */         int numItemsInRightColumn = (this.stats.size() - 2) / 2;
/* 731 */         if (this.stats.size() % 2 == 0) {
/* 732 */           numItemsInRightColumn--;
/*     */         }
/* 734 */         if (index == numItemsInRightColumn) {
/* 735 */           index = this.stats.size() - 1;
/* 736 */         } else if (index < 0) {
/* 737 */           index = this.stats.size() - 1;
/* 738 */         } else if (index == this.stats.size() - 2) {
/* 739 */           index--;
/*     */         }
/*     */       
/* 742 */       } else if (index < 0) {
/* 743 */         index = this.stats.size() - 1;
/* 744 */       } else if (index == this.stats.size() - 2) {
/* 745 */         index--;
/*     */       } 
/*     */       
/* 748 */       CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/* 749 */     } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 750 */       if (index == -1) {
/* 751 */         index = 0;
/* 752 */         CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/*     */         
/*     */         return;
/*     */       } 
/* 756 */       index++;
/* 757 */       if (this.stats.size() > 10) {
/* 758 */         int numItemsInLeftColumn = (this.stats.size() - 2) / 2;
/* 759 */         if (this.stats.size() % 2 != 0) {
/* 760 */           numItemsInLeftColumn++;
/*     */         }
/* 762 */         if (index == numItemsInLeftColumn) {
/* 763 */           index = this.stats.size() - 1;
/*     */         }
/*     */       } else {
/* 766 */         if (index > this.stats.size() - 1) {
/* 767 */           index = 0;
/*     */         }
/* 769 */         if (index == this.stats.size() - 2) {
/* 770 */           index++;
/*     */         }
/*     */       } 
/*     */       
/* 774 */       if (index > this.stats.size() - 3) {
/* 775 */         index = this.stats.size() - 1;
/*     */       }
/* 777 */       CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/* 778 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed() || CInputActionSet.right
/* 779 */       .isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 780 */       if (this.stats.size() > 10) {
/* 781 */         int numItemsInLeftColumn = (this.stats.size() - 2) / 2;
/*     */         
/* 783 */         if (this.stats.size() % 2 != 0) {
/* 784 */           numItemsInLeftColumn++;
/*     */         }
/*     */         
/* 787 */         if (index < numItemsInLeftColumn - 1) {
/* 788 */           index += numItemsInLeftColumn;
/* 789 */         } else if (index == numItemsInLeftColumn - 1) {
/* 790 */           if (this.stats.size() % 2 != 0) {
/* 791 */             index += numItemsInLeftColumn - 1;
/*     */           } else {
/* 793 */             index += numItemsInLeftColumn;
/*     */           } 
/* 795 */         } else if (index >= numItemsInLeftColumn && index < this.stats.size() - 2) {
/* 796 */           index -= numItemsInLeftColumn;
/*     */         } 
/*     */       } 
/* 799 */       if (index > this.stats.size() - 1) {
/* 800 */         index = this.stats.size() - 1;
/*     */       }
/* 802 */       if (index != -1) {
/* 803 */         CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateAscensionProgress() {
/* 809 */     if ((isVictory || AbstractDungeon.actNum >= 4) && AbstractDungeon.isAscensionMode && Settings.isStandardRun() && AbstractDungeon.ascensionLevel < 20 && 
/*     */       
/* 811 */       StatsScreen.isPlayingHighestAscension(AbstractDungeon.player.getPrefs())) {
/* 812 */       StatsScreen.incrementAscension(AbstractDungeon.player.getCharStat());
/* 813 */       AbstractDungeon.topLevelEffects.add(new AscensionLevelUpTextEffect());
/* 814 */     } else if (!AbstractDungeon.ascensionCheck && UnlockTracker.isAscensionUnlocked(AbstractDungeon.player) && !Settings.seedSet) {
/*     */       
/* 816 */       AbstractDungeon.topLevelEffects.add(new AscensionUnlockedTextEffect());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateStatsScreen() {
/* 821 */     if (this.showingStats) {
/* 822 */       this.progressBarAlpha = MathHelper.slowColorLerpSnap(this.progressBarAlpha, 1.0F);
/*     */       
/* 824 */       this.statsTimer -= Gdx.graphics.getDeltaTime();
/* 825 */       if (this.statsTimer < 0.0F) {
/* 826 */         this.statsTimer = 0.0F;
/*     */       }
/*     */       
/* 829 */       this.returnButton.y = Interpolation.pow3In.apply(Settings.HEIGHT * 0.1F, Settings.HEIGHT * 0.15F, this.statsTimer * 1.0F / 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 834 */       AbstractDungeon.dynamicBanner.y = Interpolation.pow3In.apply(Settings.HEIGHT / 2.0F + 320.0F * Settings.scale, DynamicBanner.Y, this.statsTimer * 1.0F / 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 839 */       for (GameOverStat i : this.stats) {
/* 840 */         i.update();
/*     */       }
/*     */       
/* 843 */       if (this.statAnimateTimer < 0.0F) {
/* 844 */         boolean allStatsShown = true;
/*     */         
/* 846 */         for (GameOverStat i : this.stats) {
/* 847 */           if (i.hidden) {
/* 848 */             i.hidden = false;
/* 849 */             this.statAnimateTimer = 0.1F;
/* 850 */             allStatsShown = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 856 */         if (allStatsShown) {
/* 857 */           animateProgressBar();
/*     */         }
/*     */       } else {
/* 860 */         this.statAnimateTimer -= Gdx.graphics.getDeltaTime();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void render(SpriteBatch sb) {
/*     */     Iterator<DeathScreenFloatyEffect> i;
/* 866 */     for (i = this.particles.iterator(); i.hasNext(); ) {
/* 867 */       DeathScreenFloatyEffect e = i.next();
/* 868 */       if (e.renderBehind) {
/* 869 */         e.render(sb);
/*     */       }
/* 871 */       e.update();
/* 872 */       if (e.isDone) {
/* 873 */         i.remove();
/*     */       }
/*     */     } 
/*     */     
/* 877 */     AbstractDungeon.player.render(sb);
/* 878 */     if (this.monsters != null) {
/* 879 */       this.monsters.render(sb);
/*     */     }
/*     */     
/* 882 */     sb.setBlendFunction(770, 1);
/* 883 */     for (i = this.particles.iterator(); i.hasNext(); ) { DeathScreenFloatyEffect e = i.next();
/* 884 */       if (!e.renderBehind) {
/* 885 */         e.render(sb);
/*     */       } }
/*     */     
/* 888 */     sb.setBlendFunction(770, 771);
/*     */     
/* 890 */     renderStatsScreen(sb);
/*     */     
/* 892 */     if (!this.showingStats && !isVictory) {
/* 893 */       FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, this.deathText, Settings.WIDTH / 2.0F, DEATH_TEXT_Y, this.deathTextColor);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 901 */     this.returnButton.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DeathScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */