/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.metrics.Metrics;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import com.megacrit.cardcrawl.ui.buttons.DynamicBanner;
import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.AscensionLevelUpTextEffect;
import com.megacrit.cardcrawl.vfx.AscensionUnlockedTextEffect;
import com.megacrit.cardcrawl.vfx.BetaCardArtUnlockedTextEffect;
import com.megacrit.cardcrawl.vfx.scene.*;
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
/*     */ public class VictoryScreen
/*     */   extends GameOverScreen
/*     */ {
/*  51 */   private static final Logger logger = LogManager.getLogger(VictoryScreen.class.getName());
/*  52 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("VictoryScreen");
/*  53 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   private MonsterGroup monsters;
/*  56 */   private ArrayList<AbstractGameEffect> effect = new ArrayList<>();
/*  57 */   private float effectTimer = 0.0F; private float effectTimer2 = 0.0F;
/*  58 */   public static long STINGER_ID = -999L;
/*  59 */   public static String STINGER_KEY = "";
/*     */   
/*  61 */   private int unlockedBetaArt = 0;
/*     */   
/*     */   public VictoryScreen(MonsterGroup m) {
/*  64 */     isVictory = true;
/*  65 */     this.playtime = (long)CardCrawlGame.playtime;
/*     */     
/*  67 */     if (this.playtime < 0L) {
/*  68 */       this.playtime = 0L;
/*     */     }
/*     */     
/*  71 */     AbstractDungeon.getCurrRoom().clearEvent();
/*  72 */     resetScoreChecks();
/*     */     
/*  74 */     AbstractDungeon.is_victory = true;
/*  75 */     AbstractDungeon.player.drawX = Settings.WIDTH / 2.0F;
/*  76 */     AbstractDungeon.dungeonMapScreen.closeInstantly();
/*  77 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.VICTORY;
/*  78 */     AbstractDungeon.overlayMenu.showBlackScreen(1.0F);
/*  79 */     AbstractDungeon.previousScreen = null;
/*  80 */     AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
/*  81 */     AbstractDungeon.isScreenUp = true;
/*  82 */     this.monsters = m;
/*  83 */     logger.info("PLAYTIME: " + this.playtime);
/*     */     
/*  85 */     if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
/*  86 */       ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.showingStats = false;
/*  91 */     this.returnButton = new ReturnToMenuButton();
/*  92 */     this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */     
/*  94 */     AbstractDungeon.dynamicBanner.appear(TEXT[12]);
/*     */     
/*  96 */     if (Settings.isStandardRun()) {
/*  97 */       CardCrawlGame.playerPref.putInteger(AbstractDungeon.player.chosenClass.name() + "_SPIRITS", 1);
/*     */     }
/*     */     
/* 100 */     this.unlockedBetaArt = -1;
/* 101 */     switch (AbstractDungeon.player.chosenClass) {
/*     */       case IRONCLAD:
/* 103 */         if (!UnlockTracker.isAchievementUnlocked("RUBY_PLUS")) {
/* 104 */           this.unlockedBetaArt = 0;
/* 105 */           UnlockTracker.unlockAchievement("RUBY_PLUS");
/*     */         } 
/*     */         break;
/*     */       case THE_SILENT:
/* 109 */         if (!UnlockTracker.isAchievementUnlocked("EMERALD_PLUS")) {
/* 110 */           this.unlockedBetaArt = 1;
/* 111 */           UnlockTracker.unlockAchievement("EMERALD_PLUS");
/* 112 */           CampfireUI.hidden = true;
/*     */         } 
/*     */         break;
/*     */       case DEFECT:
/* 116 */         if (!UnlockTracker.isAchievementUnlocked("SAPPHIRE_PLUS")) {
/* 117 */           this.unlockedBetaArt = 2;
/* 118 */           UnlockTracker.unlockAchievement("SAPPHIRE_PLUS");
/* 119 */           this.effectTimer2 = 5.0F;
/*     */         } 
/*     */         break;
/*     */       case WATCHER:
/* 123 */         if (!UnlockTracker.isAchievementUnlocked("AMETHYST_PLUS")) {
/* 124 */           this.unlockedBetaArt = 4;
/* 125 */           UnlockTracker.unlockAchievement("AMETHYST");
/* 126 */           UnlockTracker.unlockAchievement("AMETHYST_PLUS");
/*     */         } 
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     if (UnlockTracker.isAchievementUnlocked("RUBY_PLUS") && UnlockTracker.isAchievementUnlocked("EMERALD_PLUS") && 
/* 135 */       UnlockTracker.isAchievementUnlocked("SAPPHIRE_PLUS"))
/*     */     {
/* 137 */       UnlockTracker.unlockAchievement("THE_ENDING");
/*     */     }
/*     */     
/* 140 */     submitVictoryMetrics();
/*     */     
/* 142 */     if (this.playtime != 0L) {
/* 143 */       StatsScreen.updateVictoryTime(this.playtime);
/*     */     }
/*     */     
/* 146 */     StatsScreen.incrementVictory(AbstractDungeon.player.getCharStat());
/* 147 */     StatsScreen.incrementAscension(AbstractDungeon.player.getCharStat());
/*     */     
/* 149 */     if (AbstractDungeon.ascensionLevel == 10 && !Settings.isTrial) {
/* 150 */       UnlockTracker.unlockAchievement("ASCEND_10");
/* 151 */     } else if (AbstractDungeon.ascensionLevel == 20 && !Settings.isTrial) {
/* 152 */       UnlockTracker.unlockAchievement("ASCEND_20");
/*     */     } 
/*     */     
/* 155 */     if (this.playtime != 0L) {
/* 156 */       StatsScreen.incrementPlayTime(this.playtime);
/*     */     }
/*     */     
/* 159 */     if (Settings.isStandardRun()) {
/* 160 */       StatsScreen.updateFurthestAscent(AbstractDungeon.floorNum);
/* 161 */     } else if (Settings.isDailyRun) {
/* 162 */       StatsScreen.updateHighestDailyScore(AbstractDungeon.floorNum);
/*     */     } 
/*     */     
/* 165 */     if (SaveHelper.shouldDeleteSave()) {
/* 166 */       SaveAndContinue.deleteSave(AbstractDungeon.player);
/*     */     }
/*     */     
/* 169 */     calculateUnlockProgress();
/* 170 */     if (!Settings.isEndless) {
/* 171 */       uploadToSteamLeaderboards();
/*     */     }
/* 173 */     createGameOverStats();
/* 174 */     CardCrawlGame.playerPref.flush();
/*     */   }
/*     */   
/*     */   private void createGameOverStats() {
/* 178 */     this.stats.clear();
/*     */ 
/*     */     
/* 181 */     this.stats.add(new GameOverStat(TEXT[1] + " (" + AbstractDungeon.floorNum + ")", null, 
/* 182 */           Integer.toString(floorPoints)));
/*     */ 
/*     */     
/* 185 */     this.stats.add(new GameOverStat(TEXT[8] + " (" + CardCrawlGame.monstersSlain + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 189 */           Integer.toString(monsterPoints)));
/*     */ 
/*     */     
/* 192 */     this.stats.add(new GameOverStat(EXORDIUM_ELITE.NAME + " (" + CardCrawlGame.elites1Slain + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 196 */           Integer.toString(elite1Points)));
/*     */     
/* 198 */     if (Settings.isEndless) {
/* 199 */       if (CardCrawlGame.elites2Slain > 0) {
/* 200 */         this.stats.add(new GameOverStat(CITY_ELITE.NAME + " (" + CardCrawlGame.elites2Slain + ")", null, 
/*     */ 
/*     */ 
/*     */               
/* 204 */               Integer.toString(elite2Points)));
/*     */       }
/*     */     }
/* 207 */     else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheCity || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/*     */       
/* 209 */       this.stats.add(new GameOverStat(CITY_ELITE.NAME + " (" + CardCrawlGame.elites2Slain + ")", null, 
/*     */ 
/*     */ 
/*     */             
/* 213 */             Integer.toString(elite2Points)));
/*     */     } 
/*     */ 
/*     */     
/* 217 */     if (Settings.isEndless) {
/* 218 */       if (CardCrawlGame.elites3Slain > 0) {
/* 219 */         this.stats.add(new GameOverStat(BEYOND_ELITE.NAME + " (" + CardCrawlGame.elites3Slain + ")", null, 
/*     */ 
/*     */ 
/*     */               
/* 223 */               Integer.toString(elite3Points)));
/*     */       }
/*     */     }
/* 226 */     else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond || CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/* 227 */       this.stats.add(new GameOverStat(BEYOND_ELITE.NAME + " (" + CardCrawlGame.elites3Slain + ")", null, 
/*     */ 
/*     */ 
/*     */             
/* 231 */             Integer.toString(elite3Points)));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 236 */     this.stats.add(new GameOverStat(BOSSES_SLAIN.NAME + " (" + AbstractDungeon.bossCount + ")", null, 
/*     */ 
/*     */ 
/*     */           
/* 240 */           Integer.toString(bossPoints)));
/*     */ 
/*     */     
/* 243 */     if (IS_POOPY) {
/* 244 */       this.stats.add(new GameOverStat(POOPY.NAME, POOPY.DESCRIPTIONS[0], Integer.toString(-1)));
/*     */     }
/* 246 */     if (IS_SPEEDSTER) {
/* 247 */       this.stats.add(new GameOverStat(SPEEDSTER.NAME, SPEEDSTER.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/* 249 */     if (IS_LIGHT_SPEED) {
/* 250 */       this.stats.add(new GameOverStat(LIGHT_SPEED.NAME, LIGHT_SPEED.DESCRIPTIONS[0], 
/* 251 */             Integer.toString(50)));
/*     */     }
/* 253 */     if (IS_HIGHLANDER) {
/* 254 */       this.stats.add(new GameOverStat(HIGHLANDER.NAME, HIGHLANDER.DESCRIPTIONS[0], 
/* 255 */             Integer.toString(100)));
/*     */     }
/* 257 */     if (IS_SHINY) {
/* 258 */       this.stats.add(new GameOverStat(SHINY.NAME, SHINY.DESCRIPTIONS[0], Integer.toString(50)));
/*     */     }
/*     */     
/* 261 */     if (IS_I_LIKE_GOLD) {
/* 262 */       this.stats.add(new GameOverStat(I_LIKE_GOLD.NAME + " (" + CardCrawlGame.goldGained + ")", I_LIKE_GOLD.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 266 */             Integer.toString(75)));
/* 267 */     } else if (IS_RAINING_MONEY) {
/* 268 */       this.stats.add(new GameOverStat(RAINING_MONEY.NAME + " (" + CardCrawlGame.goldGained + ")", RAINING_MONEY.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 272 */             Integer.toString(50)));
/* 273 */     } else if (IS_MONEY_MONEY) {
/* 274 */       this.stats.add(new GameOverStat(MONEY_MONEY.NAME + " (" + CardCrawlGame.goldGained + ")", MONEY_MONEY.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 278 */             Integer.toString(25)));
/*     */     } 
/*     */     
/* 281 */     if (IS_MYSTERY_MACHINE) {
/* 282 */       this.stats.add(new GameOverStat(MYSTERY_MACHINE.NAME + " (" + CardCrawlGame.mysteryMachine + ")", MYSTERY_MACHINE.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 286 */             Integer.toString(25)));
/*     */     }
/*     */     
/* 289 */     if (IS_FULL_SET > 0) {
/* 290 */       this.stats.add(new GameOverStat(COLLECTOR.NAME + " (" + IS_FULL_SET + ")", COLLECTOR.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 294 */             Integer.toString(25 * IS_FULL_SET)));
/*     */     }
/*     */     
/* 297 */     if (IS_PAUPER) {
/* 298 */       this.stats.add(new GameOverStat(PAUPER.NAME, PAUPER.DESCRIPTIONS[0], Integer.toString(50)));
/*     */     }
/* 300 */     if (IS_LIBRARY) {
/* 301 */       this.stats.add(new GameOverStat(LIBRARIAN.NAME, LIBRARIAN.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/* 303 */     if (IS_ENCYCLOPEDIA) {
/* 304 */       this.stats.add(new GameOverStat(ENCYCLOPEDIAN.NAME, ENCYCLOPEDIAN.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 308 */             Integer.toString(50)));
/*     */     }
/*     */     
/* 311 */     if (IS_STUFFED) {
/* 312 */       this.stats.add(new GameOverStat(STUFFED.NAME, STUFFED.DESCRIPTIONS[0], Integer.toString(50)));
/* 313 */     } else if (IS_WELL_FED) {
/* 314 */       this.stats.add(new GameOverStat(WELL_FED.NAME, WELL_FED.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     } 
/*     */     
/* 317 */     if (IS_CURSES) {
/* 318 */       this.stats.add(new GameOverStat(CURSES.NAME, CURSES.DESCRIPTIONS[0], Integer.toString(100)));
/*     */     }
/*     */     
/* 321 */     if (IS_ON_MY_OWN) {
/* 322 */       this.stats.add(new GameOverStat(ON_MY_OWN_TERMS.NAME, ON_MY_OWN_TERMS.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 326 */             Integer.toString(50)));
/*     */     }
/*     */     
/* 329 */     if (CardCrawlGame.champion > 0) {
/* 330 */       this.stats.add(new GameOverStat(CHAMPION.NAME + " (" + CardCrawlGame.champion + ")", CHAMPION.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 334 */             Integer.toString(25 * CardCrawlGame.champion)));
/*     */     }
/*     */     
/* 337 */     if (CardCrawlGame.perfect >= 3) {
/* 338 */       this.stats.add(new GameOverStat(BEYOND_PERFECT.NAME, BEYOND_PERFECT.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 342 */             Integer.toString(200)));
/* 343 */     } else if (CardCrawlGame.perfect > 0) {
/* 344 */       this.stats.add(new GameOverStat(PERFECT.NAME + " (" + CardCrawlGame.perfect + ")", PERFECT.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 348 */             Integer.toString(50 * CardCrawlGame.perfect)));
/*     */     } 
/*     */     
/* 351 */     if (CardCrawlGame.overkill) {
/* 352 */       this.stats.add(new GameOverStat(OVERKILL.NAME, OVERKILL.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/*     */     
/* 355 */     if (CardCrawlGame.combo) {
/* 356 */       this.stats.add(new GameOverStat(COMBO.NAME, COMBO.DESCRIPTIONS[0], Integer.toString(25)));
/*     */     }
/*     */     
/* 359 */     if (AbstractDungeon.isAscensionMode)
/*     */     {
/* 361 */       this.stats.add(new GameOverStat(ASCENSION.NAME + " (" + AbstractDungeon.ascensionLevel + ")", ASCENSION.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 365 */             Integer.toString(ascensionPoints)));
/*     */     }
/*     */     
/* 368 */     if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/* 369 */       this.stats.add(new GameOverStat(HEARTBREAKER.NAME, HEARTBREAKER.DESCRIPTIONS[0], 
/*     */ 
/*     */ 
/*     */             
/* 373 */             Integer.toString(250)));
/*     */     }
/*     */ 
/*     */     
/* 377 */     this.stats.add(new GameOverStat());
/*     */ 
/*     */     
/* 380 */     this.stats.add(new GameOverStat(TEXT[4], null, Integer.toString(this.score)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void submitVictoryMetrics() {
/* 385 */     Metrics metrics = new Metrics();
/*     */ 
/*     */     
/* 388 */     metrics.gatherAllDataAndSave(false, true, null);
/*     */ 
/*     */     
/* 391 */     if (Settings.UPLOAD_DATA) {
/* 392 */       metrics.setValues(false, true, null, Metrics.MetricRequestType.UPLOAD_METRICS);
/* 393 */       Thread t = new Thread((Runnable)metrics);
/* 394 */       t.start();
/*     */     } 
/*     */     
/* 397 */     if (Settings.isStandardRun()) {
/* 398 */       StatsScreen.updateFurthestAscent(AbstractDungeon.floorNum);
/*     */     }
/*     */     
/* 401 */     if (SaveHelper.shouldDeleteSave()) {
/* 402 */       SaveAndContinue.deleteSave(AbstractDungeon.player);
/*     */     }
/*     */   }
/*     */   
/*     */   public void hide() {
/* 407 */     this.returnButton.hide();
/* 408 */     AbstractDungeon.dynamicBanner.hide();
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 412 */     reopen(false);
/*     */   }
/*     */   
/*     */   public void reopen(boolean fromVictoryUnlock) {
/* 416 */     AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
/* 417 */     this.statsTimer = 0.5F;
/*     */     
/* 419 */     AbstractDungeon.dynamicBanner.appearInstantly(TEXT[12]);
/* 420 */     AbstractDungeon.overlayMenu.showBlackScreen(1.0F);
/*     */     
/* 422 */     if (fromVictoryUnlock) {
/* 423 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */     }
/* 425 */     else if (!this.showingStats) {
/* 426 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */     }
/* 428 */     else if (this.unlockBundle == null) {
/* 429 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */     } else {
/* 431 */       this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[5]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 438 */     if (Settings.isDebug && InputHelper.justClickedRight) {
/* 439 */       UnlockTracker.resetUnlockProgress(AbstractDungeon.player.chosenClass);
/*     */     }
/*     */     
/* 442 */     updateControllerInput();
/* 443 */     this.returnButton.update();
/* 444 */     if (this.returnButton.hb.clicked || (this.returnButton.show && CInputActionSet.select.isJustPressed())) {
/* 445 */       CInputActionSet.topPanel.unpress();
/* 446 */       if (Settings.isControllerMode) {
/* 447 */         Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/*     */       }
/* 449 */       this.returnButton.hb.clicked = false;
/* 450 */       if (!this.showingStats) {
/* 451 */         this.showingStats = true;
/* 452 */         this.statsTimer = 0.5F;
/* 453 */         logger.info("Clicked");
/*     */         
/* 455 */         this.returnButton = new ReturnToMenuButton();
/*     */ 
/*     */         
/* 458 */         updateAscensionAndBetaArtProgress();
/*     */         
/* 460 */         if (this.unlockBundle == null) {
/* 461 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[0]);
/*     */         } else {
/* 463 */           this.returnButton.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[5]);
/*     */         } 
/* 465 */       } else if (this.unlockBundle != null) {
/* 466 */         AbstractDungeon.gUnlockScreen.open(this.unlockBundle, true);
/* 467 */         AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.VICTORY;
/* 468 */         AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NEOW_UNLOCK;
/* 469 */         this.unlockBundle = null;
/* 470 */         this.returnButton.label = TEXT[5];
/*     */       } else {
/* 472 */         this.returnButton.hide();
/*     */         
/* 474 */         if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo) {
/* 475 */           CardCrawlGame.playCreditsBgm = true;
/* 476 */           CardCrawlGame.startOverButShowCredits();
/*     */         } else {
/* 478 */           AbstractDungeon.unlocks.clear();
/* 479 */           Settings.isTrial = false;
/* 480 */           Settings.isDailyRun = false;
/* 481 */           Settings.isEndless = false;
/* 482 */           CardCrawlGame.trial = null;
/*     */           
/* 484 */           if (Settings.isDailyRun) {
/* 485 */             CardCrawlGame.startOver();
/*     */           } else {
/* 487 */             CardCrawlGame.playCreditsBgm = true;
/* 488 */             CardCrawlGame.startOverButShowCredits();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 494 */     updateStatsScreen();
/*     */     
/* 496 */     if (this.monsters != null) {
/* 497 */       this.monsters.update();
/* 498 */       this.monsters.updateAnimations();
/*     */     } 
/*     */     
/* 501 */     updateVfx();
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 505 */     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden || AbstractDungeon.player.viewingRelics) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 510 */     boolean anyHovered = false;
/* 511 */     int index = 0;
/* 512 */     if (this.stats != null) {
/* 513 */       for (GameOverStat s : this.stats) {
/* 514 */         if (s.hb.hovered) {
/* 515 */           anyHovered = true;
/*     */           break;
/*     */         } 
/* 518 */         index++;
/*     */       } 
/*     */     }
/*     */     
/* 522 */     if (!anyHovered) {
/* 523 */       index = -1;
/*     */     }
/*     */     
/* 526 */     if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 527 */       index--;
/* 528 */       if (this.stats.size() > 10) {
/* 529 */         int numItemsInRightColumn = (this.stats.size() - 2) / 2;
/* 530 */         if (this.stats.size() % 2 == 0) {
/* 531 */           numItemsInRightColumn--;
/*     */         }
/* 533 */         if (index == numItemsInRightColumn) {
/* 534 */           index = this.stats.size() - 1;
/* 535 */         } else if (index < 0) {
/* 536 */           index = this.stats.size() - 1;
/* 537 */         } else if (index == this.stats.size() - 2) {
/* 538 */           index--;
/*     */         }
/*     */       
/* 541 */       } else if (index < 0) {
/* 542 */         index = this.stats.size() - 1;
/* 543 */       } else if (index == this.stats.size() - 2) {
/* 544 */         index--;
/*     */       } 
/*     */       
/* 547 */       CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/* 548 */     } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 549 */       if (index == -1) {
/* 550 */         index = 0;
/* 551 */         CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/*     */         
/*     */         return;
/*     */       } 
/* 555 */       index++;
/* 556 */       if (this.stats.size() > 10) {
/* 557 */         int numItemsInLeftColumn = (this.stats.size() - 2) / 2;
/* 558 */         if (this.stats.size() % 2 != 0) {
/* 559 */           numItemsInLeftColumn++;
/*     */         }
/* 561 */         if (index == numItemsInLeftColumn) {
/* 562 */           index = this.stats.size() - 1;
/*     */         }
/*     */       } else {
/* 565 */         if (index > this.stats.size() - 1) {
/* 566 */           index = 0;
/*     */         }
/* 568 */         if (index == this.stats.size() - 2) {
/* 569 */           index++;
/*     */         }
/*     */       } 
/*     */       
/* 573 */       if (index > this.stats.size() - 3) {
/* 574 */         index = this.stats.size() - 1;
/*     */       }
/* 576 */       CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/* 577 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed() || CInputActionSet.right
/* 578 */       .isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 579 */       if (this.stats.size() > 10) {
/* 580 */         int numItemsInLeftColumn = (this.stats.size() - 2) / 2;
/*     */         
/* 582 */         if (this.stats.size() % 2 != 0) {
/* 583 */           numItemsInLeftColumn++;
/*     */         }
/*     */         
/* 586 */         if (index < numItemsInLeftColumn - 1) {
/* 587 */           index += numItemsInLeftColumn;
/* 588 */         } else if (index == numItemsInLeftColumn - 1) {
/* 589 */           if (this.stats.size() % 2 != 0) {
/* 590 */             index += numItemsInLeftColumn - 1;
/*     */           } else {
/* 592 */             index += numItemsInLeftColumn;
/*     */           } 
/* 594 */         } else if (index >= numItemsInLeftColumn && index < this.stats.size() - 2) {
/* 595 */           index -= numItemsInLeftColumn;
/*     */         } 
/*     */       } 
/* 598 */       if (index > this.stats.size() - 1) {
/* 599 */         index = this.stats.size() - 1;
/*     */       }
/* 601 */       if (index != -1) {
/* 602 */         CInputHelper.setCursor(((GameOverStat)this.stats.get(index)).hb);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateAscensionAndBetaArtProgress() {
/* 608 */     if (AbstractDungeon.isAscensionMode && !Settings.seedSet && !Settings.isTrial && AbstractDungeon.ascensionLevel < 20 && 
/*     */       
/* 610 */       StatsScreen.isPlayingHighestAscension(AbstractDungeon.player.getPrefs())) {
/* 611 */       StatsScreen.incrementAscension(AbstractDungeon.player.getCharStat());
/* 612 */       AbstractDungeon.topLevelEffects.add(new AscensionLevelUpTextEffect());
/* 613 */     } else if (!AbstractDungeon.ascensionCheck && UnlockTracker.isAscensionUnlocked(AbstractDungeon.player)) {
/* 614 */       AbstractDungeon.topLevelEffects.add(new AscensionUnlockedTextEffect());
/* 615 */     } else if (this.unlockedBetaArt != -1) {
/* 616 */       AbstractDungeon.topLevelEffects.add(new BetaCardArtUnlockedTextEffect(this.unlockedBetaArt));
/*     */     } 
/*     */   } private void updateVfx() {
/*     */     boolean foundEyeVfx;
/*     */     boolean createdEffect;
/* 621 */     switch (AbstractDungeon.player.chosenClass) {
/*     */       case IRONCLAD:
/* 623 */         this.effectTimer -= Gdx.graphics.getDeltaTime();
/* 624 */         if (this.effectTimer < 0.0F) {
/* 625 */           this.effect.add(new SlowFireParticleEffect());
/* 626 */           this.effect.add(new SlowFireParticleEffect());
/* 627 */           this.effect.add(new IroncladVictoryFlameEffect());
/* 628 */           this.effect.add(new IroncladVictoryFlameEffect());
/* 629 */           this.effect.add(new IroncladVictoryFlameEffect());
/* 630 */           this.effectTimer = 0.1F;
/*     */         } 
/*     */         break;
/*     */       case THE_SILENT:
/* 634 */         this.effectTimer -= Gdx.graphics.getDeltaTime();
/* 635 */         if (this.effectTimer < 0.0F) {
/* 636 */           if (this.effect.size() < 100) {
/* 637 */             this.effect.add(new SilentVictoryStarEffect());
/* 638 */             this.effect.add(new SilentVictoryStarEffect());
/* 639 */             this.effect.add(new SilentVictoryStarEffect());
/* 640 */             this.effect.add(new SilentVictoryStarEffect());
/*     */           } 
/* 642 */           this.effectTimer = 0.1F;
/*     */         } 
/* 644 */         this.effectTimer2 += Gdx.graphics.getDeltaTime();
/* 645 */         if (this.effectTimer2 > 1.0F) {
/* 646 */           this.effectTimer2 = 1.0F;
/*     */         }
/*     */         break;
/*     */       case DEFECT:
/* 650 */         foundEyeVfx = false;
/* 651 */         for (AbstractGameEffect e : this.effect) {
/* 652 */           if (e instanceof DefectVictoryEyesEffect) {
/* 653 */             foundEyeVfx = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 658 */         if (!foundEyeVfx) {
/* 659 */           this.effect.add(new DefectVictoryEyesEffect());
/*     */         }
/*     */         
/* 662 */         if (this.effect.size() < 15) {
/* 663 */           this.effect.add(new DefectVictoryNumberEffect());
/*     */         }
/*     */         break;
/*     */       case WATCHER:
/* 667 */         createdEffect = false;
/* 668 */         for (AbstractGameEffect e : this.effect) {
/* 669 */           if (e instanceof WatcherVictoryEffect) {
/* 670 */             createdEffect = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 674 */         if (!createdEffect) {
/* 675 */           this.effect.add(new WatcherVictoryEffect());
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateStatsScreen() {
/* 684 */     if (this.showingStats) {
/* 685 */       this.progressBarAlpha = MathHelper.slowColorLerpSnap(this.progressBarAlpha, 1.0F);
/*     */       
/* 687 */       this.statsTimer -= Gdx.graphics.getDeltaTime();
/* 688 */       if (this.statsTimer < 0.0F) {
/* 689 */         this.statsTimer = 0.0F;
/*     */       }
/*     */       
/* 692 */       this.returnButton.y = Interpolation.pow3In.apply(Settings.HEIGHT * 0.1F, Settings.HEIGHT * 0.15F, this.statsTimer * 1.0F / 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 697 */       AbstractDungeon.dynamicBanner.y = Interpolation.pow3In.apply(Settings.HEIGHT / 2.0F + 320.0F * Settings.scale, DynamicBanner.Y, this.statsTimer * 1.0F / 0.5F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 702 */       for (GameOverStat i : this.stats) {
/* 703 */         i.update();
/*     */       }
/*     */       
/* 706 */       if (this.statAnimateTimer < 0.0F) {
/* 707 */         boolean allStatsShown = true;
/*     */         
/* 709 */         for (GameOverStat i : this.stats) {
/* 710 */           if (i.hidden) {
/* 711 */             i.hidden = false;
/* 712 */             this.statAnimateTimer = 0.1F;
/* 713 */             allStatsShown = false;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 718 */         if (allStatsShown) {
/* 719 */           animateProgressBar();
/*     */         }
/*     */       } else {
/* 722 */         this.statAnimateTimer -= Gdx.graphics.getDeltaTime();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 728 */     sb.setBlendFunction(770, 1); Iterator<AbstractGameEffect> i;
/* 729 */     for (i = this.effect.iterator(); i.hasNext(); ) {
/* 730 */       AbstractGameEffect e = i.next();
/* 731 */       if (e.renderBehind) {
/* 732 */         e.render(sb);
/*     */       }
/* 734 */       e.update();
/* 735 */       if (e.isDone) {
/* 736 */         i.remove();
/*     */       }
/*     */     } 
/* 739 */     sb.setBlendFunction(770, 771);
/*     */     
/* 741 */     if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
/* 742 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.effectTimer2));
/* 743 */       AbstractDungeon.player.renderShoulderImg(sb);
/*     */     } 
/*     */     
/* 746 */     sb.setBlendFunction(770, 1);
/* 747 */     for (i = this.effect.iterator(); i.hasNext(); ) { AbstractGameEffect e = i.next();
/* 748 */       if (!e.renderBehind) {
/* 749 */         e.render(sb);
/*     */       } }
/*     */     
/* 752 */     sb.setBlendFunction(770, 771);
/*     */     
/* 754 */     renderStatsScreen(sb);
/* 755 */     this.returnButton.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\VictoryScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */