/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.ScoreBonusStrings;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.stats.StatsScreen;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
/*     */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.UnlockTextEffect;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GameOverScreen
/*     */ {
/*  30 */   private static final Logger logger = LogManager.getLogger(GameOverScreen.class.getName());
/*  31 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DeathScreen");
/*  32 */   private static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   protected ReturnToMenuButton returnButton;
/*     */   public static boolean isVictory;
/*  36 */   protected ArrayList<AbstractUnlock> unlockBundle = null;
/*     */ 
/*     */   
/*  39 */   protected ArrayList<GameOverStat> stats = new ArrayList<>();
/*  40 */   protected Color fadeBgColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  41 */   protected Color whiteUiColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  42 */   protected Color creamUiColor = Settings.CREAM_COLOR.cpy();
/*  43 */   private float progressBarX = 576.0F * Settings.xScale;
/*  44 */   private float progressBarWidth = 768.0F * Settings.xScale;
/*     */   protected boolean showingStats;
/*  46 */   protected float statsTimer = 0.0F; protected float statAnimateTimer = 0.0F; protected float progressBarTimer = 2.0F; protected float progressBarAlpha = 0.0F;
/*  47 */   protected static final float STAT_OFFSET_Y = 36.0F * Settings.scale; protected boolean maxLevel = false; protected float progressPercent; protected float unlockTargetProgress;
/*  48 */   protected static final float STAT_START_Y = Settings.HEIGHT / 2.0F - 20.0F * Settings.scale;
/*     */   protected float unlockTargetStart;
/*     */   protected float unlockProgress;
/*     */   protected long playtime;
/*  52 */   protected int score = 0; protected int unlockCost; protected int nextUnlockCost; protected int unlockLevel = 0;
/*     */   
/*     */   protected static final float STATS_TRANSITION_TIME = 0.5F;
/*     */   
/*     */   protected static final float STAT_ANIM_INTERVAL = 0.1F;
/*     */   protected static final float PROGRESS_BAR_ANIM_TIME = 2.0F;
/*  58 */   protected static final ScoreBonusStrings EXORDIUM_ELITE = CardCrawlGame.languagePack.getScoreString("Exordium Elites Killed");
/*     */   
/*  60 */   protected static final ScoreBonusStrings CITY_ELITE = CardCrawlGame.languagePack.getScoreString("City Elites Killed");
/*     */   
/*  62 */   protected static final ScoreBonusStrings BEYOND_ELITE = CardCrawlGame.languagePack.getScoreString("Beyond Elites Killed");
/*     */   
/*  64 */   protected static final ScoreBonusStrings BOSSES_SLAIN = CardCrawlGame.languagePack.getScoreString("Bosses Slain");
/*  65 */   protected static final ScoreBonusStrings ASCENSION = CardCrawlGame.languagePack.getScoreString("Ascension");
/*  66 */   protected static final ScoreBonusStrings CHAMPION = CardCrawlGame.languagePack.getScoreString("Champion");
/*  67 */   protected static final ScoreBonusStrings PERFECT = CardCrawlGame.languagePack.getScoreString("Perfect");
/*  68 */   protected static final ScoreBonusStrings BEYOND_PERFECT = CardCrawlGame.languagePack.getScoreString("Beyond Perfect");
/*     */   
/*  70 */   protected static final ScoreBonusStrings OVERKILL = CardCrawlGame.languagePack.getScoreString("Overkill");
/*  71 */   protected static final ScoreBonusStrings COMBO = CardCrawlGame.languagePack.getScoreString("Combo");
/*  72 */   protected static final ScoreBonusStrings POOPY = CardCrawlGame.languagePack.getScoreString("Poopy");
/*  73 */   protected static final ScoreBonusStrings SPEEDSTER = CardCrawlGame.languagePack.getScoreString("Speedster");
/*  74 */   protected static final ScoreBonusStrings LIGHT_SPEED = CardCrawlGame.languagePack.getScoreString("Light Speed");
/*  75 */   protected static final ScoreBonusStrings MONEY_MONEY = CardCrawlGame.languagePack.getScoreString("Money Money");
/*  76 */   protected static final ScoreBonusStrings RAINING_MONEY = CardCrawlGame.languagePack.getScoreString("Raining Money");
/*  77 */   protected static final ScoreBonusStrings I_LIKE_GOLD = CardCrawlGame.languagePack.getScoreString("I Like Gold");
/*  78 */   protected static final ScoreBonusStrings HIGHLANDER = CardCrawlGame.languagePack.getScoreString("Highlander");
/*  79 */   protected static final ScoreBonusStrings SHINY = CardCrawlGame.languagePack.getScoreString("Shiny");
/*  80 */   protected static final ScoreBonusStrings COLLECTOR = CardCrawlGame.languagePack.getScoreString("Collector");
/*  81 */   protected static final ScoreBonusStrings PAUPER = CardCrawlGame.languagePack.getScoreString("Pauper");
/*  82 */   protected static final ScoreBonusStrings LIBRARIAN = CardCrawlGame.languagePack.getScoreString("Librarian");
/*  83 */   protected static final ScoreBonusStrings ENCYCLOPEDIAN = CardCrawlGame.languagePack.getScoreString("Encyclopedian");
/*  84 */   protected static final ScoreBonusStrings WELL_FED = CardCrawlGame.languagePack.getScoreString("Well Fed");
/*  85 */   protected static final ScoreBonusStrings STUFFED = CardCrawlGame.languagePack.getScoreString("Stuffed");
/*  86 */   protected static final ScoreBonusStrings CURSES = CardCrawlGame.languagePack.getScoreString("Curses");
/*  87 */   protected static final ScoreBonusStrings MYSTERY_MACHINE = CardCrawlGame.languagePack.getScoreString("Mystery Machine");
/*     */   
/*  89 */   protected static final ScoreBonusStrings ON_MY_OWN_TERMS = CardCrawlGame.languagePack.getScoreString("On My Own Terms");
/*     */   
/*  91 */   protected static final ScoreBonusStrings HEARTBREAKER = CardCrawlGame.languagePack.getScoreString("Heartbreaker");
/*     */   
/*     */   protected static boolean IS_POOPY = false;
/*     */   
/*     */   protected static boolean IS_SPEEDSTER = false;
/*     */   protected static boolean IS_LIGHT_SPEED = false;
/*     */   protected static boolean IS_HIGHLANDER = false;
/*  98 */   protected static int IS_FULL_SET = 0;
/*     */   
/*     */   protected static boolean IS_SHINY = false;
/*     */   
/*     */   protected static boolean IS_PAUPER = false;
/*     */   protected static boolean IS_LIBRARY = false;
/*     */   protected static boolean IS_ENCYCLOPEDIA = false;
/*     */   protected static boolean IS_WELL_FED = false;
/*     */   protected static boolean IS_STUFFED = false;
/*     */   protected static boolean IS_CURSES = false;
/*     */   protected static boolean IS_ON_MY_OWN = false;
/*     */   protected static boolean IS_MONEY_MONEY = false;
/*     */   protected static boolean IS_RAINING_MONEY = false;
/*     */   protected static boolean IS_I_LIKE_GOLD = false;
/*     */   protected static boolean IS_MYSTERY_MACHINE = false;
/*     */   protected static final int POOPY_SCORE = -1;
/*     */   protected static final int SPEEDER_SCORE = 25;
/*     */   protected static final int LIGHT_SPEED_SCORE = 50;
/*     */   protected static final int HIGHLANDER_SCORE = 100;
/*     */   protected static final int FULL_SET_SCORE = 25;
/*     */   protected static final int SHINY_SCORE = 50;
/*     */   protected static final int PAUPER_SCORE = 50;
/*     */   protected static final int LIBRARY_SCORE = 25;
/*     */   protected static final int ENCYCLOPEDIA_SCORE = 50;
/*     */   protected static final int WELL_FED_SCORE = 25;
/*     */   protected static final int STUFFED_SCORE = 50;
/*     */   protected static final int CURSES_SCORE = 100;
/*     */   protected static final int ON_MY_OWN_SCORE = 50;
/*     */   protected static final int MONEY_MONEY_SCORE = 25;
/*     */   protected static final int RAINING_MONEY_SCORE = 50;
/*     */   protected static final int I_LIKE_GOLD_SCORE = 75;
/*     */   protected static final int CHAMPION_SCORE = 25;
/*     */   protected static final int PERFECT_SCORE = 50;
/*     */   protected static final int BEYOND_PERFECT_SCORE = 200;
/*     */   protected static final int OVERKILL_SCORE = 25;
/*     */   protected static final int COMBO_SCORE = 25;
/*     */   protected static final int MYSTERY_MACHINE_SCORE = 25;
/*     */   protected static final int HEARTBREAKER_SCORE = 250;
/*     */   protected static int floorPoints;
/*     */   protected static int monsterPoints;
/*     */   protected static int elite1Points;
/*     */   protected static int elite2Points;
/*     */   protected static int elite3Points;
/*     */   protected static int bossPoints;
/*     */   protected static int ascensionPoints;
/*     */   protected static final int FLOOR_MULTIPLIER = 5;
/*     */   protected static final int ENEMY_MULTIPLIER = 2;
/*     */   protected static final int ELITE_MULTIPLIER_1 = 10;
/*     */   protected static final int ELITE_MULTIPLIER_2 = 20;
/*     */   protected static final int ELITE_MULTIPLIER_3 = 30;
/*     */   protected static final int BOSS_MULTIPLIER = 50;
/*     */   protected static final float ASCENSION_MULTIPLIER = 0.05F;
/*     */   protected boolean playedWhir = false;
/*     */   protected long whirId;
/*     */   
/*     */   public static void resetScoreChecks() {
/* 154 */     IS_POOPY = false;
/* 155 */     IS_SPEEDSTER = false;
/* 156 */     IS_LIGHT_SPEED = false;
/* 157 */     IS_HIGHLANDER = false;
/* 158 */     IS_FULL_SET = 0;
/* 159 */     IS_SHINY = false;
/* 160 */     IS_PAUPER = false;
/* 161 */     IS_LIBRARY = false;
/* 162 */     IS_ENCYCLOPEDIA = false;
/* 163 */     IS_WELL_FED = false;
/* 164 */     IS_STUFFED = false;
/* 165 */     IS_CURSES = false;
/* 166 */     IS_ON_MY_OWN = false;
/* 167 */     IS_MONEY_MONEY = false;
/* 168 */     IS_RAINING_MONEY = false;
/* 169 */     IS_I_LIKE_GOLD = false;
/* 170 */     IS_MYSTERY_MACHINE = false;
/*     */   }
/*     */   
/*     */   protected void animateProgressBar() {
/* 174 */     if (this.maxLevel) {
/*     */       return;
/*     */     }
/*     */     
/* 178 */     this.progressBarTimer -= Gdx.graphics.getDeltaTime();
/* 179 */     if (this.progressBarTimer < 0.0F) {
/* 180 */       this.progressBarTimer = 0.0F;
/*     */     }
/*     */     
/* 183 */     if (this.progressBarTimer > 2.0F) {
/*     */       return;
/*     */     }
/*     */     
/* 187 */     if (!this.playedWhir) {
/* 188 */       this.playedWhir = true;
/* 189 */       this.whirId = CardCrawlGame.sound.play("UNLOCK_WHIR");
/*     */     } 
/*     */     
/* 192 */     this.unlockProgress = Interpolation.pow2In.apply(this.unlockTargetProgress, this.unlockTargetStart, this.progressBarTimer / 2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (this.unlockProgress >= this.unlockCost && this.unlockLevel != 5) {
/* 198 */       if (this.unlockLevel == 4) {
/* 199 */         this.unlockProgress = this.unlockCost;
/* 200 */         this.unlockLevel++;
/* 201 */         AbstractDungeon.topLevelEffects.add(new UnlockTextEffect());
/*     */       } else {
/* 203 */         this.unlockTargetProgress = this.score - this.unlockCost - this.unlockTargetStart;
/* 204 */         this.progressBarTimer = 3.0F;
/* 205 */         AbstractDungeon.topLevelEffects.add(new UnlockTextEffect());
/* 206 */         CardCrawlGame.sound.stop("UNLOCK_WHIR", this.whirId);
/* 207 */         this.playedWhir = false;
/* 208 */         this.unlockProgress = 0.0F;
/* 209 */         this.unlockTargetStart = 0.0F;
/*     */ 
/*     */         
/* 212 */         if (this.unlockTargetProgress > (this.nextUnlockCost - 1)) {
/* 213 */           this.unlockTargetProgress = (this.nextUnlockCost - 1);
/*     */         }
/*     */         
/* 216 */         this.unlockCost = this.nextUnlockCost;
/* 217 */         this.unlockLevel++;
/*     */       } 
/*     */     }
/*     */     
/* 221 */     this.progressPercent = this.unlockProgress / this.unlockCost;
/*     */   }
/*     */   
/*     */   protected void calculateUnlockProgress() {
/* 225 */     this.score = calcScore(isVictory);
/*     */     
/* 227 */     this.unlockLevel = UnlockTracker.getUnlockLevel(AbstractDungeon.player.chosenClass);
/* 228 */     if (this.unlockLevel >= 5) {
/* 229 */       this.maxLevel = true;
/*     */       
/*     */       return;
/*     */     } 
/* 233 */     if (this.score == 0) {
/* 234 */       this.playedWhir = true;
/*     */     }
/*     */     
/* 237 */     this.unlockProgress = UnlockTracker.getCurrentProgress(AbstractDungeon.player.chosenClass);
/* 238 */     this.unlockTargetStart = this.unlockProgress;
/* 239 */     this.unlockCost = UnlockTracker.getCurrentScoreCost(AbstractDungeon.player.chosenClass);
/* 240 */     this.unlockTargetProgress = this.unlockProgress + this.score;
/*     */     
/* 242 */     this.nextUnlockCost = UnlockTracker.incrementUnlockRamp(this.unlockCost);
/*     */     
/* 244 */     if (this.unlockTargetProgress >= this.unlockCost) {
/* 245 */       this.unlockBundle = UnlockTracker.getUnlockBundle(AbstractDungeon.player.chosenClass, this.unlockLevel);
/*     */ 
/*     */       
/* 248 */       if (this.unlockLevel == 4) {
/* 249 */         this.unlockTargetProgress = this.unlockCost;
/* 250 */       } else if (this.unlockTargetProgress > this.unlockCost - this.unlockProgress + this.nextUnlockCost - 1.0F) {
/*     */         
/* 252 */         this.unlockTargetProgress = this.unlockCost - this.unlockProgress + this.nextUnlockCost - 1.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 256 */     logger.info("SCOR: " + this.score);
/* 257 */     logger.info("PROG: " + this.unlockProgress);
/* 258 */     logger.info("STRT: " + this.unlockTargetStart);
/* 259 */     logger.info("TRGT: " + this.unlockTargetProgress);
/* 260 */     logger.info("COST: " + this.unlockCost);
/*     */ 
/*     */     
/* 263 */     UnlockTracker.addScore(AbstractDungeon.player.chosenClass, this.score);
/* 264 */     this.progressPercent = this.unlockTargetStart / this.unlockCost;
/*     */   }
/*     */   
/*     */   public static boolean shouldUploadMetricData() {
/* 268 */     return (Settings.UPLOAD_DATA && CardCrawlGame.publisherIntegration.isInitialized() && Settings.isStandardRun());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void submitVictoryMetrics() {}
/*     */   
/*     */   protected boolean canUploadLeaderboards() {
/* 275 */     if (Settings.isModded || Settings.isTrial || Settings.seedSet) {
/* 276 */       return false;
/*     */     }
/*     */     
/* 279 */     return true;
/*     */   }
/*     */   
/*     */   protected void uploadToSteamLeaderboards() {
/* 283 */     if (!canUploadLeaderboards()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 288 */     uploadScoreHelper(AbstractDungeon.player.getLeaderboardCharacterName());
/* 289 */     StatsScreen.updateHighestScore(this.score);
/*     */   }
/*     */   
/*     */   protected void uploadScoreHelper(String characterString) {
/* 293 */     StringBuilder highScoreString = new StringBuilder();
/* 294 */     StringBuilder fastestWinString = new StringBuilder(characterString);
/*     */     
/* 296 */     if (Settings.isDailyRun) {
/* 297 */       highScoreString.append("DAILY_" + Long.toString(Settings.dailyDate));
/* 298 */       long lastDaily = Settings.dailyPref.getLong("LAST_DAILY", 0L);
/*     */ 
/*     */       
/* 301 */       Settings.hasDoneDailyToday = (lastDaily == Settings.dailyDate);
/* 302 */       if (Settings.hasDoneDailyToday) {
/* 303 */         logger.info("Player has already done the daily for: " + Settings.dailyDate);
/*     */       }
/*     */     } else {
/* 306 */       highScoreString.append(characterString);
/* 307 */       highScoreString.append("_HIGH_SCORE");
/*     */     } 
/* 309 */     fastestWinString.append("_FASTEST_WIN");
/*     */     
/* 311 */     if (Settings.isBeta) {
/* 312 */       highScoreString.append("_BETA");
/* 313 */       fastestWinString.append("_BETA");
/*     */     } 
/*     */     
/* 316 */     if (Settings.isDailyRun && !Settings.hasDoneDailyToday) {
/* 317 */       logger.info("Uploading score for day: " + Settings.dailyDate + "\nScore is: " + this.score);
/* 318 */       Settings.dailyPref.putLong("LAST_DAILY", Settings.dailyDate);
/* 319 */       Settings.dailyPref.flush();
/* 320 */       CardCrawlGame.publisherIntegration.uploadDailyLeaderboardScore(highScoreString.toString(), this.score);
/* 321 */     } else if (!Settings.isDailyRun) {
/* 322 */       CardCrawlGame.publisherIntegration.uploadLeaderboardScore(highScoreString.toString(), this.score);
/*     */     } 
/*     */     
/* 325 */     if (isVictory && this.playtime < 18000L && this.playtime > 280L && !Settings.isDailyRun) {
/* 326 */       CardCrawlGame.publisherIntegration.uploadLeaderboardScore(fastestWinString
/* 327 */           .toString(), 
/* 328 */           Math.toIntExact(this.playtime));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calcScore(boolean victory) {
/* 335 */     floorPoints = 0;
/* 336 */     monsterPoints = 0;
/* 337 */     elite1Points = 0;
/* 338 */     elite2Points = 0;
/* 339 */     elite3Points = 0;
/* 340 */     bossPoints = 0;
/* 341 */     ascensionPoints = 0;
/*     */     
/* 343 */     int tmp = AbstractDungeon.floorNum * 5;
/* 344 */     floorPoints = AbstractDungeon.floorNum * 5;
/* 345 */     monsterPoints = CardCrawlGame.monstersSlain * 2;
/* 346 */     elite1Points = CardCrawlGame.elites1Slain * 10;
/* 347 */     elite2Points = CardCrawlGame.elites2Slain * 20;
/* 348 */     elite3Points = CardCrawlGame.elites3Slain * 30;
/*     */ 
/*     */     
/* 351 */     bossPoints = 0;
/* 352 */     int bossMultiplier = 50;
/* 353 */     for (int i = 0; i < AbstractDungeon.bossCount; i++) {
/* 354 */       bossPoints += bossMultiplier;
/* 355 */       bossMultiplier += 50;
/*     */     } 
/*     */     
/* 358 */     tmp += monsterPoints;
/* 359 */     tmp += elite1Points;
/* 360 */     tmp += elite2Points;
/* 361 */     tmp += elite3Points;
/* 362 */     tmp += bossPoints;
/*     */     
/* 364 */     tmp += CardCrawlGame.champion * 25;
/* 365 */     if (CardCrawlGame.perfect >= 3) {
/* 366 */       tmp += 200;
/*     */     } else {
/* 368 */       tmp += CardCrawlGame.perfect * 50;
/*     */     } 
/*     */     
/* 371 */     if (CardCrawlGame.overkill) {
/* 372 */       tmp += 25;
/*     */     }
/*     */     
/* 375 */     if (CardCrawlGame.combo) {
/* 376 */       tmp += 25;
/*     */     }
/*     */     
/* 379 */     if (AbstractDungeon.isAscensionMode) {
/* 380 */       ascensionPoints = MathUtils.round(tmp * 0.05F * AbstractDungeon.ascensionLevel);
/* 381 */       tmp += ascensionPoints;
/*     */     } 
/*     */     
/* 384 */     tmp += checkScoreBonus(victory);
/*     */     
/* 386 */     return tmp;
/*     */   }
/*     */   
/*     */   protected static int checkScoreBonus(boolean victory) {
/* 390 */     int points = 0;
/*     */ 
/*     */     
/* 393 */     if (AbstractDungeon.player.hasRelic("Spirit Poop")) {
/* 394 */       IS_POOPY = true;
/* 395 */       points--;
/*     */     } 
/*     */ 
/*     */     
/* 399 */     IS_FULL_SET = AbstractDungeon.player.masterDeck.fullSetCheck();
/* 400 */     if (IS_FULL_SET > 0) {
/* 401 */       points += 25 * IS_FULL_SET;
/*     */     }
/*     */ 
/*     */     
/* 405 */     if (AbstractDungeon.player.relics.size() >= 25) {
/* 406 */       IS_SHINY = true;
/* 407 */       points += 50;
/*     */     } 
/*     */ 
/*     */     
/* 411 */     if (AbstractDungeon.player.masterDeck.size() >= 50) {
/* 412 */       IS_ENCYCLOPEDIA = true;
/* 413 */       points += 50;
/* 414 */     } else if (AbstractDungeon.player.masterDeck.size() >= 35) {
/* 415 */       IS_LIBRARY = true;
/* 416 */       points += 25;
/*     */     } 
/*     */ 
/*     */     
/* 420 */     int tmpDiff = AbstractDungeon.player.maxHealth - AbstractDungeon.player.startingMaxHP;
/* 421 */     if (tmpDiff >= 30) {
/* 422 */       IS_STUFFED = true;
/* 423 */       points += 50;
/* 424 */     } else if (tmpDiff >= 15) {
/* 425 */       IS_WELL_FED = true;
/* 426 */       points += 25;
/*     */     } 
/*     */ 
/*     */     
/* 430 */     if (AbstractDungeon.player.masterDeck.cursedCheck()) {
/* 431 */       IS_CURSES = true;
/* 432 */       points += 100;
/*     */     } 
/*     */ 
/*     */     
/* 436 */     if (CardCrawlGame.goldGained >= 3000) {
/* 437 */       IS_I_LIKE_GOLD = true;
/* 438 */       points += 75;
/* 439 */     } else if (CardCrawlGame.goldGained >= 2000) {
/* 440 */       IS_RAINING_MONEY = true;
/* 441 */       points += 50;
/* 442 */     } else if (CardCrawlGame.goldGained >= 1000) {
/* 443 */       IS_MONEY_MONEY = true;
/* 444 */       points += 25;
/*     */     } 
/*     */     
/* 447 */     if (CardCrawlGame.mysteryMachine >= 15) {
/* 448 */       IS_MYSTERY_MACHINE = true;
/* 449 */       points += 25;
/*     */     } 
/*     */     
/* 452 */     if (victory) {
/*     */ 
/*     */ 
/*     */       
/* 456 */       logger.info("PLAYTIME: " + CardCrawlGame.playtime);
/* 457 */       if ((long)CardCrawlGame.playtime <= 2700L) {
/* 458 */         IS_LIGHT_SPEED = true;
/* 459 */         points += 50;
/* 460 */       } else if ((long)CardCrawlGame.playtime <= 3600L) {
/* 461 */         IS_SPEEDSTER = true;
/* 462 */         points += 25;
/*     */       } 
/*     */ 
/*     */       
/* 466 */       if (AbstractDungeon.player.masterDeck.highlanderCheck()) {
/* 467 */         IS_HIGHLANDER = true;
/* 468 */         points += 100;
/*     */       } 
/*     */ 
/*     */       
/* 472 */       if (AbstractDungeon.player.masterDeck.pauperCheck()) {
/* 473 */         IS_PAUPER = true;
/* 474 */         points += 50;
/*     */       } 
/*     */ 
/*     */       
/* 478 */       if (isVictory && CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheEnding) {
/* 479 */         points += 250;
/*     */       }
/*     */     } 
/*     */     
/* 483 */     return points;
/*     */   }
/*     */   
/*     */   protected void renderStatsScreen(SpriteBatch sb) {
/* 487 */     if (this.showingStats) {
/* 488 */       this.fadeBgColor.a = (1.0F - this.statsTimer) * 0.6F;
/* 489 */       sb.setColor(this.fadeBgColor);
/* 490 */       sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */ 
/*     */       
/* 493 */       float y = STAT_START_Y + this.stats.size() * STAT_OFFSET_Y / 2.0F;
/* 494 */       if (this.stats.size() >= 10) {
/* 495 */         y = STAT_START_Y + (this.stats.size() / 2) * STAT_OFFSET_Y / 2.0F;
/*     */       }
/*     */ 
/*     */       
/* 499 */       for (int i = 0; i < this.stats.size(); i++) {
/*     */ 
/*     */         
/* 502 */         if (this.stats.size() <= 10) {
/* 503 */           if (i == this.stats.size() - 2) {
/* 504 */             ((GameOverStat)this.stats.get(i)).renderLine(sb, false, y);
/*     */           } else {
/* 506 */             ((GameOverStat)this.stats.get(i)).render(sb, Settings.WIDTH / 2.0F - 220.0F * Settings.scale, y);
/*     */           }
/*     */         
/*     */         }
/* 510 */         else if (i != this.stats.size() - 1) {
/* 511 */           if (i < (this.stats.size() - 1) / 2) {
/* 512 */             ((GameOverStat)this.stats.get(i)).render(sb, 440.0F * Settings.xScale, y);
/*     */           } else {
/* 514 */             ((GameOverStat)this.stats.get(i)).render(sb, 1050.0F * Settings.xScale, y + STAT_OFFSET_Y * ((this.stats
/*     */ 
/*     */                 
/* 517 */                 .size() - 1) / 2));
/*     */           } 
/*     */         } else {
/*     */           
/* 521 */           ((GameOverStat)this.stats.get(i)).renderLine(sb, true, y + STAT_OFFSET_Y * (this.stats.size() / 2));
/*     */ 
/*     */           
/* 524 */           ((GameOverStat)this.stats.get(i)).render(sb, 740.0F * Settings.xScale, y + STAT_OFFSET_Y * (this.stats.size() / 2 - 1));
/*     */         } 
/* 526 */         y -= STAT_OFFSET_Y;
/*     */       } 
/*     */       
/* 529 */       renderProgressBar(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void renderProgressBar(SpriteBatch sb) {
/* 534 */     if (this.maxLevel) {
/*     */       return;
/*     */     }
/*     */     
/* 538 */     this.whiteUiColor.a = this.progressBarAlpha * 0.3F;
/* 539 */     sb.setColor(this.whiteUiColor);
/* 540 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.progressBarX, Settings.HEIGHT * 0.2F, this.progressBarWidth, 14.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 547 */     sb.setColor(new Color(1.0F, 0.8F, 0.3F, this.progressBarAlpha * 0.9F));
/* 548 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.progressBarX, Settings.HEIGHT * 0.2F, this.progressBarWidth * this.progressPercent, 14.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.progressBarAlpha * 0.25F));
/* 556 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.progressBarX, Settings.HEIGHT * 0.2F, this.progressBarWidth * this.progressPercent, 4.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 563 */     String derp = "[" + (int)this.unlockProgress + "/" + this.unlockCost + "]";
/* 564 */     this.creamUiColor.a = this.progressBarAlpha * 0.9F;
/*     */     
/* 566 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, derp, 576.0F * Settings.xScale, Settings.HEIGHT * 0.2F - 12.0F * Settings.scale, this.creamUiColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 574 */     if (5 - this.unlockLevel == 1) {
/* 575 */       derp = TEXT[42] + (5 - this.unlockLevel);
/*     */     } else {
/* 577 */       derp = TEXT[41] + (5 - this.unlockLevel);
/*     */     } 
/*     */     
/* 580 */     FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, derp, 1344.0F * Settings.xScale, Settings.HEIGHT * 0.2F - 12.0F * Settings.scale, this.creamUiColor);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\GameOverScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */