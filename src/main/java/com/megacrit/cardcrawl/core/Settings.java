/*     */ package com.megacrit.cardcrawl.core;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.helpers.Prefs;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.screens.DisplayOption;
/*     */ import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class Settings
/*     */ {
/*  18 */   private static final Logger logger = LogManager.getLogger(Settings.class.getName());
/*     */   
/*     */   public static boolean isDev = false;
/*     */   
/*     */   public static boolean isBeta = false;
/*     */   
/*     */   public static boolean isAlpha = false;
/*     */   
/*     */   public static boolean isModded = false;
/*     */   
/*     */   public static boolean isControllerMode = false;
/*     */   
/*     */   public static boolean isMobile = false;
/*     */   
/*     */   public static boolean testFonts = false;
/*     */   
/*     */   public static boolean isDebug = false;
/*     */   
/*     */   public static boolean isInfo = false;
/*     */   
/*     */   public static boolean isTestingNeow = false;
/*     */   
/*     */   public static boolean usesTrophies = false;
/*     */   
/*     */   public static boolean isConsoleBuild = false;
/*     */   public static boolean usesProfileSaves = false;
/*     */   public static boolean isTouchScreen = false;
/*     */   public static boolean isDemo = false;
/*     */   public static boolean isShowBuild = false;
/*     */   public static boolean isPublisherBuild = false;
/*     */   public static GameLanguage language;
/*     */   public static boolean lineBreakViaCharacter = false;
/*     */   public static boolean usesOrdinal = true;
/*     */   public static boolean leftAlignCards = false;
/*     */   public static boolean manualLineBreak = false;
/*     */   public static boolean removeAtoZSort = false;
/*     */   public static boolean manualAndAutoLineBreak = false;
/*     */   public static Prefs soundPref;
/*     */   public static Prefs dailyPref;
/*     */   public static Prefs gamePref;
/*     */   public static boolean isDailyRun;
/*     */   public static boolean hasDoneDailyToday;
/*  60 */   public static long dailyDate = 0L; public static long totalPlayTime; public static boolean isFinalActAvailable;
/*     */   public static boolean hasRubyKey;
/*     */   public static boolean hasEmeraldKey;
/*     */   public static boolean hasSapphireKey;
/*     */   public static boolean isEndless;
/*     */   public static boolean isTrial;
/*     */   public static Long specialSeed;
/*     */   public static String trialName;
/*     */   public static boolean IS_FULLSCREEN;
/*     */   public static boolean IS_W_FULLSCREEN;
/*     */   public static boolean IS_V_SYNC;
/*     */   public static int MAX_FPS;
/*     */   public static int M_W;
/*     */   public static int M_H;
/*     */   public static int SAVED_WIDTH;
/*     */   public static int SAVED_HEIGHT;
/*     */   public static int WIDTH;
/*     */   public static int HEIGHT;
/*     */   public static boolean isSixteenByTen = false, isFourByThree = false, isTwoSixteen = false;
/*     */   public static boolean isLetterbox = false;
/*  80 */   public static int HORIZ_LETTERBOX_AMT = 0; public static int VERT_LETTERBOX_AMT = 0;
/*  81 */   public static ArrayList<DisplayOption> displayOptions = null;
/*  82 */   public static int displayIndex = 0;
/*     */   
/*     */   public static float scale;
/*     */   
/*     */   public static float renderScale;
/*     */   public static float xScale;
/*     */   public static float yScale;
/*     */   public static float FOUR_BY_THREE_OFFSET_Y;
/*     */   public static float LETTERBOX_OFFSET_Y;
/*     */   public static Long seed;
/*     */   public static boolean seedSet = false;
/*     */   public static long seedSourceTimestamp;
/*     */   public static boolean isBackgrounded = false;
/*  95 */   public static float bgVolume = 0.0F;
/*     */   
/*     */   public static final String MASTER_VOLUME_PREF = "Master Volume";
/*     */   
/*     */   public static final String MUSIC_VOLUME_PREF = "Music Volume";
/*     */   
/*     */   public static final String SOUND_VOLUME_PREF = "Sound Volume";
/*     */   
/*     */   public static final String AMBIENCE_ON_PREF = "Ambience On";
/*     */   
/*     */   public static final String MUTE_IF_BG_PREF = "Mute in Bg";
/*     */   
/*     */   public static final float DEFAULT_MASTER_VOLUME = 0.5F;
/*     */   
/*     */   public static final float DEFAULT_MUSIC_VOLUME = 0.5F;
/*     */   
/*     */   public static final float DEFAULT_SOUND_VOLUME = 0.5F;
/*     */   
/*     */   public static float MASTER_VOLUME;
/*     */   
/*     */   public static float MUSIC_VOLUME;
/*     */   
/*     */   public static float SOUND_VOLUME;
/*     */   
/*     */   public static boolean AMBIANCE_ON;
/*     */   public static final String SCREEN_SHAKE_PREF = "Screen Shake";
/*     */   public static final String SUM_DMG_PREF = "Summed Damage";
/*     */   public static final String BLOCKED_DMG_PREF = "Blocked Damage";
/*     */   public static final String HAND_CONF_PREF = "Hand Confirmation";
/*     */   public static final String EFFECTS_PREF = "Particle Effects";
/*     */   public static final String FAST_MODE_PREF = "Fast Mode";
/*     */   public static final String UPLOAD_PREF = "Upload Data";
/* 127 */   public static final Color CREAM_COLOR = new Color(-597249); public static final String PLAYTESTER_ART = "Playtester Art"; public static final String SHOW_CARD_HOTKEYS_PREF = "Show Card keys"; public static final String BIG_TEXT_PREF = "Bigger Text"; public static final String LONG_PRESS_PREF = "Long-press Enabled"; public static final String CONTROLLER_ENABLED_PREF = "Controller Enabled"; public static final String TOUCHSCREEN_ENABLED_PREF = "Touchscreen Enabled"; public static final String LAST_DAILY = "LAST_DAILY"; public static boolean SHOW_DMG_SUM; public static boolean SHOW_DMG_BLOCK; public static boolean FAST_HAND_CONF; public static boolean FAST_MODE; public static boolean CONTROLLER_ENABLED; public static boolean TOUCHSCREEN_ENABLED; public static boolean DISABLE_EFFECTS; public static boolean UPLOAD_DATA; public static boolean SCREEN_SHAKE; public static boolean PLAYTESTER_ART_MODE; public static boolean SHOW_CARD_HOTKEYS; public static boolean USE_LONG_PRESS = false; public static boolean BIG_TEXT_MODE = false;
/* 128 */   public static final Color LIGHT_YELLOW_COLOR = new Color(-1202177);
/* 129 */   public static final Color RED_TEXT_COLOR = new Color(-10132481);
/* 130 */   public static final Color GREEN_TEXT_COLOR = new Color(2147418367);
/* 131 */   public static final Color BLUE_TEXT_COLOR = new Color(-2016482305);
/* 132 */   public static final Color GOLD_COLOR = new Color(-272084481);
/* 133 */   public static final Color PURPLE_COLOR = new Color(-293409025);
/* 134 */   public static final Color TOP_PANEL_SHADOW_COLOR = new Color(64);
/* 135 */   public static final Color HALF_TRANSPARENT_WHITE_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.5F);
/* 136 */   public static final Color QUARTER_TRANSPARENT_WHITE_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.25F);
/* 137 */   public static final Color TWO_THIRDS_TRANSPARENT_BLACK_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.66F);
/* 138 */   public static final Color HALF_TRANSPARENT_BLACK_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/* 139 */   public static final Color QUARTER_TRANSPARENT_BLACK_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.25F);
/* 140 */   public static final Color RED_RELIC_COLOR = new Color(-10132545);
/* 141 */   public static final Color GREEN_RELIC_COLOR = new Color(2147418303);
/* 142 */   public static final Color BLUE_RELIC_COLOR = new Color(-2016482369);
/* 143 */   public static final Color PURPLE_RELIC_COLOR = new Color(-935526465);
/*     */   
/*     */   public static final float POST_ATTACK_WAIT_DUR = 0.1F;
/*     */   
/*     */   public static final float WAIT_BEFORE_BATTLE_TIME = 1.0F;
/* 148 */   public static float ACTION_DUR_XFAST = 0.1F;
/* 149 */   public static float ACTION_DUR_FASTER = 0.2F;
/* 150 */   public static float ACTION_DUR_FAST = 0.25F;
/* 151 */   public static float ACTION_DUR_MED = 0.5F;
/* 152 */   public static float ACTION_DUR_LONG = 1.0F;
/* 153 */   public static float ACTION_DUR_XLONG = 1.5F;
/*     */   public static float CARD_DROP_END_Y;
/*     */   public static float SCROLL_SPEED;
/*     */   public static float MAP_SCROLL_SPEED;
/*     */   public static final float SCROLL_LERP_SPEED = 12.0F;
/*     */   public static final float SCROLL_SNAP_BACK_SPEED = 10.0F;
/*     */   public static float DEFAULT_SCROLL_LIMIT;
/*     */   public static float MAP_DST_Y;
/*     */   public static final float CLICK_SPEED_THRESHOLD = 0.4F;
/*     */   public static float CLICK_DIST_THRESHOLD;
/*     */   public static float POTION_W;
/*     */   public static float POTION_Y;
/* 165 */   public static final Color DISCARD_COLOR = Color.valueOf("8a769bff");
/* 166 */   public static final Color DISCARD_GLOW_COLOR = Color.valueOf("553a66ff");
/* 167 */   public static final Color SHADOW_COLOR = new Color(0.0F, 0.0F, 0.0F, 0.5F);
/*     */   
/*     */   public static final float CARD_SOUL_SCALE = 0.12F;
/*     */   
/*     */   public static final float CARD_LERP_SPEED = 6.0F;
/*     */   
/*     */   public static float CARD_SNAP_THRESHOLD;
/*     */   
/*     */   public static float UI_SNAP_THRESHOLD;
/*     */   
/*     */   public static final float CARD_SCALE_LERP_SPEED = 7.5F;
/*     */   
/*     */   public static final float CARD_SCALE_SNAP_THRESHOLD = 0.003F;
/*     */   
/*     */   public static final float UI_LERP_SPEED = 9.0F;
/*     */   
/*     */   public static final float ORB_LERP_SPEED = 6.0F;
/*     */   
/*     */   public static final float MOUSE_LERP_SPEED = 20.0F;
/*     */   
/*     */   public static final float POP_LERP_SPEED = 8.0F;
/*     */   public static final float FADE_LERP_SPEED = 12.0F;
/*     */   public static final float SLOW_COLOR_LERP_SPEED = 3.0F;
/*     */   public static final float FADE_SNAP_THRESHOLD = 0.01F;
/*     */   public static final float ROTATE_LERP_SPEED = 12.0F;
/*     */   public static final float SCALE_SNAP_THRESHOLD = 0.003F;
/*     */   public static float HOVER_BUTTON_RISE_AMOUNT;
/*     */   public static final float CARD_VIEW_SCALE = 0.75F;
/*     */   public static float CARD_VIEW_PAD_X;
/*     */   public static float CARD_VIEW_PAD_Y;
/*     */   public static float OPTION_Y;
/*     */   public static float EVENT_Y;
/*     */   public static final int MAX_ASCENSION_LEVEL = 20;
/*     */   public static final float POST_COMBAT_WAIT_TIME = 0.25F;
/*     */   public static final int MAX_HAND_SIZE = 10;
/*     */   public static final int NUM_POTIONS = 3;
/*     */   public static final int NORMAL_POTION_DROP_RATE = 40;
/*     */   public static final int ELITE_POTION_DROP_RATE = 40;
/*     */   public static final int BOSS_GOLD_AMT = 100;
/*     */   public static final int BOSS_GOLD_JITTER = 5;
/*     */   public static final int ACHIEVEMENT_COUNT = 46;
/*     */   public static final int NORMAL_RARE_DROP_RATE = 3;
/*     */   public static final int NORMAL_UNCOMMON_DROP_RATE = 40;
/*     */   public static final int ELITE_RARE_DROP_RATE = 10;
/*     */   public static final int ELITE_UNCOMMON_DROP_RATE = 50;
/*     */   public static final int UNLOCK_PER_CHAR_COUNT = 5;
/*     */   public static boolean hideTopBar = false;
/*     */   public static boolean hidePopupDetails = false;
/*     */   public static boolean hideRelics = false;
/*     */   public static boolean hideLowerElements = false;
/*     */   public static boolean hideCards = false;
/*     */   public static boolean hideEndTurn = false;
/*     */   public static boolean hideCombatElements = false;
/*     */   public static final String SENDTODEVS = "sendToDevs";
/*     */   
/*     */   public enum GameLanguage
/*     */   {
/* 224 */     ENG, DUT, EPO, PTB, ZHS, ZHT, FIN, FRA, DEU, GRE, IND, ITA, JPN, KOR, NOR, POL, RUS, SPA, SRP, SRB, THA, TUR, UKR, VIE, WWW;
/*     */   }
/*     */   
/*     */   public static void initialize(boolean reloaded) {
/* 228 */     if (!reloaded) {
/* 229 */       initializeDisplay();
/*     */     }
/*     */     
/* 232 */     initializeSoundPref();
/* 233 */     initializeGamePref(reloaded);
/*     */   }
/*     */   
/*     */   private static void initializeDisplay() {
/* 237 */     logger.info("Initializing display settings...");
/* 238 */     DisplayConfig displayConf = DisplayConfig.readConfig();
/* 239 */     M_W = Gdx.graphics.getWidth();
/* 240 */     M_H = Gdx.graphics.getHeight();
/* 241 */     WIDTH = displayConf.getWidth();
/* 242 */     HEIGHT = displayConf.getHeight();
/* 243 */     MAX_FPS = displayConf.getMaxFPS();
/* 244 */     SAVED_WIDTH = WIDTH;
/* 245 */     SAVED_HEIGHT = HEIGHT;
/* 246 */     IS_FULLSCREEN = displayConf.getIsFullscreen();
/* 247 */     IS_W_FULLSCREEN = displayConf.getWFS();
/* 248 */     IS_V_SYNC = displayConf.getIsVsync();
/*     */     
/* 250 */     float aspectRatio = WIDTH / HEIGHT;
/* 251 */     boolean isUltrawide = false;
/* 252 */     isLetterbox = (aspectRatio > 2.34F || aspectRatio < 1.3332F);
/*     */     
/* 254 */     if (aspectRatio > 1.32F && aspectRatio < 1.34F) {
/* 255 */       isFourByThree = true;
/* 256 */     } else if (aspectRatio > 1.59F && aspectRatio < 1.61F) {
/* 257 */       isSixteenByTen = true;
/* 258 */     } else if (aspectRatio >= 1.78F) {
/*     */       
/* 260 */       if (aspectRatio > 1.78F) {
/* 261 */         isUltrawide = true;
/*     */       }
/*     */     } 
/* 264 */     if (isLetterbox) {
/* 265 */       if (aspectRatio < 1.333F) {
/* 266 */         HEIGHT = MathUtils.round(WIDTH * 0.75F);
/* 267 */         HORIZ_LETTERBOX_AMT = (M_H - HEIGHT) / 2;
/* 268 */         HORIZ_LETTERBOX_AMT += 2;
/* 269 */         scale = WIDTH / 1920.0F;
/* 270 */         xScale = scale;
/* 271 */         renderScale = scale;
/* 272 */         yScale = HEIGHT / 1080.0F;
/* 273 */         isFourByThree = true;
/* 274 */       } else if (aspectRatio > 2.34F) {
/* 275 */         WIDTH = MathUtils.round(HEIGHT * 2.3333F);
/* 276 */         VERT_LETTERBOX_AMT = (M_W - WIDTH) / 2;
/* 277 */         VERT_LETTERBOX_AMT++;
/* 278 */         scale = (int)(HEIGHT * 1.77778F) / 1920.0F;
/* 279 */         xScale = WIDTH / 1920.0F;
/* 280 */         renderScale = xScale;
/* 281 */         yScale = scale;
/* 282 */         setXOffset();
/*     */       } 
/* 284 */     } else if (isFourByThree) {
/*     */       
/* 286 */       scale = WIDTH / 1920.0F;
/* 287 */       xScale = scale;
/* 288 */       yScale = HEIGHT / 1080.0F;
/* 289 */       renderScale = yScale;
/* 290 */     } else if (isUltrawide) {
/*     */       
/* 292 */       scale = (int)(HEIGHT * 1.7777778F) / 1920.0F;
/* 293 */       xScale = WIDTH / 1920.0F;
/* 294 */       renderScale = xScale;
/* 295 */       yScale = scale;
/* 296 */       setXOffset();
/* 297 */       isLetterbox = true;
/*     */     } else {
/*     */       
/* 300 */       scale = WIDTH / 1920.0F;
/* 301 */       xScale = scale;
/* 302 */       yScale = scale;
/* 303 */       renderScale = scale;
/*     */     } 
/*     */     
/* 306 */     SCROLL_SPEED = 75.0F * scale;
/* 307 */     MAP_SCROLL_SPEED = 75.0F * scale;
/* 308 */     DEFAULT_SCROLL_LIMIT = 50.0F * yScale;
/* 309 */     MAP_DST_Y = 150.0F * scale;
/* 310 */     CLICK_DIST_THRESHOLD = 30.0F * scale;
/* 311 */     CARD_DROP_END_Y = HEIGHT * 0.81F;
/* 312 */     POTION_W = isMobile ? (64.0F * scale) : (56.0F * scale);
/* 313 */     POTION_Y = isMobile ? (HEIGHT - 42.0F * scale) : (HEIGHT - 30.0F * scale);
/* 314 */     OPTION_Y = HEIGHT / 2.0F - 32.0F * yScale;
/* 315 */     EVENT_Y = HEIGHT / 2.0F - 128.0F * scale;
/* 316 */     CARD_VIEW_PAD_X = 40.0F * scale;
/* 317 */     CARD_VIEW_PAD_Y = 40.0F * scale;
/* 318 */     HOVER_BUTTON_RISE_AMOUNT = 8.0F * scale;
/* 319 */     CARD_SNAP_THRESHOLD = 1.0F * scale;
/* 320 */     UI_SNAP_THRESHOLD = 1.0F * scale;
/* 321 */     FOUR_BY_THREE_OFFSET_Y = 140.0F * yScale;
/*     */   }
/*     */   
/*     */   private static void setXOffset() {
/* 325 */     if (scale == 1.0F) {
/* 326 */       LETTERBOX_OFFSET_Y = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 330 */     float offsetScale = xScale - 1.0F;
/* 331 */     if (offsetScale < 0.0F) {
/* 332 */       LETTERBOX_OFFSET_Y = 0.0F;
/*     */       
/*     */       return;
/*     */     } 
/* 336 */     LETTERBOX_OFFSET_Y = (WIDTH - 1920) * offsetScale;
/*     */   }
/*     */   
/*     */   private static void initializeSoundPref() {
/* 340 */     logger.info("Initializing sound settings...");
/* 341 */     soundPref = SaveHelper.getPrefs("STSSound");
/*     */     
/*     */     try {
/* 344 */       soundPref.getBoolean("Ambience On");
/* 345 */       soundPref.getBoolean("Mute in Bg");
/* 346 */     } catch (Exception e) {
/*     */       
/* 348 */       soundPref.putBoolean("Ambience On", soundPref.getBoolean("Ambience On", true));
/* 349 */       soundPref.putBoolean("Mute in Bg", soundPref.getBoolean("Mute in Bg", true));
/* 350 */       soundPref.flush();
/*     */     } 
/*     */     
/* 353 */     AMBIANCE_ON = soundPref.getBoolean("Ambience On", true);
/* 354 */     CardCrawlGame.MUTE_IF_BG = soundPref.getBoolean("Mute in Bg", true);
/*     */   }
/*     */   
/*     */   private static void initializeGamePref(boolean reloaded) {
/* 358 */     logger.info("Initializing game settings...");
/* 359 */     gamePref = SaveHelper.getPrefs("STSGameplaySettings");
/* 360 */     dailyPref = SaveHelper.getPrefs("STSDaily");
/*     */     
/*     */     try {
/* 363 */       gamePref.getBoolean("Summed Damage");
/* 364 */       gamePref.getBoolean("Blocked Damage");
/* 365 */       gamePref.getBoolean("Hand Confirmation");
/* 366 */       gamePref.getBoolean("Upload Data");
/* 367 */       gamePref.getBoolean("Particle Effects");
/* 368 */       gamePref.getBoolean("Fast Mode");
/* 369 */       gamePref.getBoolean("Show Card keys");
/* 370 */       gamePref.getBoolean("Bigger Text");
/* 371 */       gamePref.getBoolean("Long-press Enabled");
/* 372 */       gamePref.getBoolean("Screen Shake");
/* 373 */       gamePref.getBoolean("Playtester Art");
/* 374 */       gamePref.getBoolean("Controller Enabled");
/* 375 */       gamePref.getBoolean("Touchscreen Enabled");
/* 376 */     } catch (Exception e) {
/*     */       
/* 378 */       gamePref.putBoolean("Summed Damage", gamePref.getBoolean("Summed Damage", false));
/* 379 */       gamePref.putBoolean("Blocked Damage", gamePref.getBoolean("Blocked Damage", false));
/* 380 */       gamePref.putBoolean("Hand Confirmation", gamePref.getBoolean("Hand Confirmation", false));
/* 381 */       gamePref.putBoolean("Upload Data", gamePref.getBoolean("Upload Data", true));
/* 382 */       gamePref.putBoolean("Particle Effects", gamePref.getBoolean("Particle Effects", false));
/* 383 */       gamePref.putBoolean("Fast Mode", gamePref.getBoolean("Fast Mode", false));
/* 384 */       gamePref.putBoolean("Show Card keys", gamePref.getBoolean("Show Card keys", false));
/* 385 */       gamePref.putBoolean("Bigger Text", gamePref.getBoolean("Bigger Text", false));
/* 386 */       gamePref.putBoolean("Long-press Enabled", gamePref.getBoolean("Long-press Enabled", false));
/* 387 */       gamePref.putBoolean("Screen Shake", gamePref.getBoolean("Screen Shake", true));
/* 388 */       gamePref.putBoolean("Playtester Art", gamePref.getBoolean("Playtester Art", false));
/* 389 */       gamePref.putBoolean("Controller Enabled", gamePref.getBoolean("Controller Enabled", true));
/* 390 */       gamePref.putBoolean("Touchscreen Enabled", gamePref.getBoolean("Touchscreen Enabled", false));
/* 391 */       if (!reloaded) {
/* 392 */         setLanguage(gamePref.getString("LANGUAGE", GameLanguage.ENG.name()), true);
/*     */       }
/* 394 */       gamePref.flush();
/*     */     } 
/*     */     
/* 397 */     SHOW_DMG_SUM = gamePref.getBoolean("Summed Damage", false);
/* 398 */     SHOW_DMG_BLOCK = gamePref.getBoolean("Blocked Damage", false);
/* 399 */     FAST_HAND_CONF = gamePref.getBoolean("Hand Confirmation", false);
/* 400 */     UPLOAD_DATA = gamePref.getBoolean("Upload Data", true);
/* 401 */     DISABLE_EFFECTS = gamePref.getBoolean("Particle Effects", false);
/* 402 */     FAST_MODE = gamePref.getBoolean("Fast Mode", false);
/* 403 */     SHOW_CARD_HOTKEYS = gamePref.getBoolean("Show Card keys", false);
/* 404 */     BIG_TEXT_MODE = gamePref.getBoolean("Bigger Text", false);
/* 405 */     USE_LONG_PRESS = gamePref.getBoolean("Long-press Enabled", false);
/* 406 */     SCREEN_SHAKE = gamePref.getBoolean("Screen Shake", true);
/* 407 */     PLAYTESTER_ART_MODE = gamePref.getBoolean("Playtester Art", false);
/* 408 */     CONTROLLER_ENABLED = gamePref.getBoolean("Controller Enabled", true);
/* 409 */     TOUCHSCREEN_ENABLED = gamePref.getBoolean("Touchscreen Enabled", false);
/*     */     
/* 411 */     if (TOUCHSCREEN_ENABLED || isConsoleBuild) {
/* 412 */       isTouchScreen = true;
/*     */     }
/*     */     
/* 415 */     if (!reloaded) {
/* 416 */       setLanguage(gamePref.getString("LANGUAGE", GameLanguage.ENG.name()), true);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setLanguage(GameLanguage key, boolean initial) {
/* 421 */     language = key;
/* 422 */     if (initial) {
/* 423 */       switch (language) {
/*     */         case ZHS:
/*     */         case ZHT:
/* 426 */           manualAndAutoLineBreak = true;
/* 427 */           lineBreakViaCharacter = true;
/* 428 */           usesOrdinal = false;
/* 429 */           removeAtoZSort = true;
/*     */           break;
/*     */         case JPN:
/* 432 */           lineBreakViaCharacter = true;
/* 433 */           usesOrdinal = false;
/* 434 */           if (isConsoleBuild) {
/* 435 */             manualLineBreak = true;
/* 436 */             leftAlignCards = true;
/*     */           } else {
/* 438 */             manualAndAutoLineBreak = true;
/* 439 */             manualLineBreak = false;
/* 440 */             leftAlignCards = false;
/*     */           } 
/* 442 */           removeAtoZSort = true;
/*     */           break;
/*     */         case ENG:
/* 445 */           lineBreakViaCharacter = false;
/* 446 */           usesOrdinal = true;
/*     */           break;
/*     */         case DUT:
/*     */         case EPO:
/*     */         case PTB:
/*     */         case FIN:
/*     */         case FRA:
/*     */         case DEU:
/*     */         case GRE:
/*     */         case IND:
/*     */         case ITA:
/*     */         case KOR:
/*     */         case NOR:
/*     */         case POL:
/*     */         case RUS:
/*     */         case SPA:
/*     */         case SRP:
/*     */         case SRB:
/*     */         case THA:
/*     */         case UKR:
/*     */         case TUR:
/*     */         case VIE:
/* 468 */           lineBreakViaCharacter = false;
/* 469 */           usesOrdinal = false;
/*     */           break;
/*     */         default:
/* 472 */           logger.info("[ERROR] Unspecified language: " + key.toString());
/* 473 */           lineBreakViaCharacter = false;
/* 474 */           usesOrdinal = true;
/*     */           break;
/*     */       } 
/*     */     }
/* 478 */     gamePref.putString("LANGUAGE", key.name());
/*     */   }
/*     */   
/*     */   public static void setLanguage(String langStr, boolean initial) {
/*     */     try {
/* 483 */       GameLanguage langKey = GameLanguage.valueOf(langStr);
/* 484 */       setLanguage(langKey, initial);
/* 485 */     } catch (IllegalArgumentException ex) {
/* 486 */       setLanguageLegacy(langStr, initial);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setLanguageLegacy(String key, boolean initial) {
/* 491 */     switch (key) {
/*     */       case "English":
/* 493 */         language = GameLanguage.ENG;
/* 494 */         if (initial) {
/* 495 */           lineBreakViaCharacter = false;
/* 496 */           usesOrdinal = true;
/*     */         } 
/*     */         break;
/*     */       case "Brazilian Portuguese":
/* 500 */         language = GameLanguage.PTB;
/* 501 */         if (initial) {
/* 502 */           lineBreakViaCharacter = false;
/* 503 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Chinese (Simplified)":
/* 507 */         language = GameLanguage.ZHS;
/* 508 */         if (initial) {
/* 509 */           lineBreakViaCharacter = true;
/* 510 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Chinese (Traditional)":
/* 514 */         language = GameLanguage.ZHT;
/* 515 */         if (initial) {
/* 516 */           lineBreakViaCharacter = true;
/* 517 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Finnish":
/* 521 */         language = GameLanguage.FIN;
/* 522 */         if (initial) {
/* 523 */           lineBreakViaCharacter = false;
/* 524 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "French":
/* 528 */         language = GameLanguage.FRA;
/* 529 */         if (initial) {
/* 530 */           lineBreakViaCharacter = false;
/* 531 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "German":
/* 535 */         language = GameLanguage.DEU;
/* 536 */         if (initial) {
/* 537 */           lineBreakViaCharacter = false;
/* 538 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Greek":
/* 542 */         language = GameLanguage.GRE;
/* 543 */         if (initial) {
/* 544 */           lineBreakViaCharacter = false;
/* 545 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Italian":
/* 549 */         language = GameLanguage.ITA;
/* 550 */         if (initial) {
/* 551 */           lineBreakViaCharacter = false;
/* 552 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Indonesian":
/* 556 */         language = GameLanguage.IND;
/* 557 */         if (initial) {
/* 558 */           lineBreakViaCharacter = false;
/* 559 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Japanese":
/* 563 */         language = GameLanguage.JPN;
/* 564 */         if (initial) {
/* 565 */           lineBreakViaCharacter = true;
/* 566 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Korean":
/* 570 */         language = GameLanguage.KOR;
/* 571 */         if (initial) {
/* 572 */           lineBreakViaCharacter = false;
/* 573 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Norwegian":
/* 577 */         language = GameLanguage.NOR;
/* 578 */         if (initial) {
/* 579 */           lineBreakViaCharacter = false;
/* 580 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Polish":
/* 584 */         language = GameLanguage.POL;
/* 585 */         if (initial) {
/* 586 */           lineBreakViaCharacter = false;
/* 587 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Russian":
/* 591 */         language = GameLanguage.RUS;
/* 592 */         if (initial) {
/* 593 */           lineBreakViaCharacter = false;
/* 594 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Spanish":
/* 598 */         language = GameLanguage.SPA;
/* 599 */         if (initial) {
/* 600 */           lineBreakViaCharacter = false;
/* 601 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Serbian-Cyrillic":
/* 605 */         language = GameLanguage.SRP;
/* 606 */         if (initial) {
/* 607 */           lineBreakViaCharacter = false;
/* 608 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Serbian-Latin":
/* 612 */         language = GameLanguage.SRB;
/* 613 */         if (initial) {
/* 614 */           lineBreakViaCharacter = false;
/* 615 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Thai":
/* 619 */         language = GameLanguage.THA;
/* 620 */         if (initial) {
/* 621 */           lineBreakViaCharacter = false;
/* 622 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Turkish":
/* 626 */         language = GameLanguage.TUR;
/* 627 */         if (initial) {
/* 628 */           lineBreakViaCharacter = false;
/* 629 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Ukrainian":
/* 633 */         language = GameLanguage.UKR;
/* 634 */         if (initial) {
/* 635 */           lineBreakViaCharacter = false;
/* 636 */           usesOrdinal = false;
/*     */         } 
/*     */         break;
/*     */       case "Vietnamese":
/* 640 */         language = GameLanguage.VIE;
/* 641 */         if (initial) {
/* 642 */           lineBreakViaCharacter = false;
/* 643 */           usesOrdinal = false;
/*     */         } 
/*     */       default:
/* 646 */         language = GameLanguage.ENG;
/* 647 */         if (initial) {
/* 648 */           lineBreakViaCharacter = false;
/* 649 */           usesOrdinal = true;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */     
/* 654 */     gamePref.putString("LANGUAGE", key);
/*     */   }
/*     */   
/*     */   public static boolean isStandardRun() {
/* 658 */     return (!isDailyRun && !isTrial && !seedSet);
/*     */   }
/*     */   
/*     */   public static boolean treatEverythingAsUnlocked() {
/* 662 */     return (isDailyRun || isTrial);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setFinalActAvailability() {
/* 668 */     isFinalActAvailable = ((CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.IRONCLAD.name() + "_WIN", false) && CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.THE_SILENT.name() + "_WIN", false) && CardCrawlGame.playerPref.getBoolean(AbstractPlayer.PlayerClass.DEFECT.name() + "_WIN", false) && !isDailyRun && !isTrial) || CustomModeScreen.finalActAvailable);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\core\Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */