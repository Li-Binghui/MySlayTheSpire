/*     */ package com.megacrit.cardcrawl.daily;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.BadWordChecker;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SeedHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.integrations.DistributorFactory;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.screens.leaderboards.LeaderboardEntry;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DailyScreen
/*     */ {
/*  38 */   private static final Logger logger = LogManager.getLogger(DailyScreen.class.getName());
/*  39 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("DailyScreen")).TEXT;
/*  40 */   public static final String[] TEXT_2 = (CardCrawlGame.languagePack.getUIString("LeaderboardsScreen")).TEXT;
/*  41 */   private long lastDaily = 0L;
/*  42 */   public AbstractPlayer todaysChar = null;
/*     */ 
/*     */   
/*  45 */   private Color screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  46 */   private Color fadeColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  47 */   private Color redTextColor = new Color(1.0F, 0.3F, 0.3F, 0.0F);
/*  48 */   private Color creamColor = new Color(1.0F, 0.965F, 0.886F, 0.0F);
/*  49 */   private float alphaTarget = 0.0F;
/*  50 */   private MenuCancelButton cancelButton = new MenuCancelButton();
/*  51 */   private ConfirmButton confirmButton = new ConfirmButton(TEXT[4]);
/*  52 */   private float screenX = -1100.0F * Settings.scale, targetX = -1100.0F * Settings.scale;
/*     */ 
/*     */   
/*  55 */   private float header_x = 186.0F * Settings.scale; private float date_x;
/*  56 */   private float body_x = 208.0F * Settings.scale;
/*  57 */   private float char_x = 304.0F * Settings.scale;
/*  58 */   private float mode_desc_x = 264.0F * Settings.scale, mod_icon_x = 200.0F * Settings.scale;
/*  59 */   private float center_mod_offset_x = 500.0F * Settings.scale;
/*     */   private static final int CHAR_W = 128;
/*     */   private static final int MOD_W = 64;
/*  62 */   private static final float TITLE_Y = Settings.HEIGHT / 2.0F + 350.0F * Settings.scale;
/*  63 */   private static final float TIME_LEFT_Y = Settings.HEIGHT / 2.0F + 310.0F * Settings.scale;
/*  64 */   private static final float CHAR_IMAGE_Y = Settings.HEIGHT / 2.0F + 160.0F * Settings.scale;
/*  65 */   private static final float CHAR_HEADER_Y = Settings.HEIGHT / 2.0F + 250.0F * Settings.scale;
/*  66 */   private static final float MOD_HEADER_Y = Settings.HEIGHT / 2.0F + 136.0F * Settings.scale;
/*  67 */   private static final float MOD_LINE_W = 500.0F * Settings.scale;
/*  68 */   private static final float MOD_LINE_SPACING = 30.0F * Settings.scale;
/*  69 */   private static final float MOD_SECTION_SPACING = 60.0F * Settings.scale;
/*     */   
/*     */   private DateFormat dFormat;
/*     */   
/*     */   private boolean timeLookupActive = false;
/*     */   
/*     */   private boolean timeUpdated = false;
/*     */   private Random todayRng;
/*  77 */   private long currentDay = 0L; public boolean waiting = true;
/*     */   public boolean viewMyScore = false;
/*  79 */   public int currentStartIndex = 1; public int currentEndIndex = 20; private Hitbox prevHb;
/*  80 */   public ArrayList<LeaderboardEntry> entries = new ArrayList<>(); private Hitbox nextHb;
/*  81 */   private Hitbox viewMyScoreHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale); private Hitbox prevDayHb;
/*     */   private Hitbox nextDayHb;
/*  83 */   public static final float RANK_X = 1000.0F * Settings.scale; public static final float NAME_X = 1160.0F * Settings.scale; public static final float SCORE_X = 1500.0F * Settings.scale;
/*     */   
/*  85 */   private float lineFadeInTimer = 0.0F;
/*  86 */   private static final float LINE_THICKNESS = 4.0F * Settings.scale;
/*     */   
/*     */   public DailyScreen() {
/*  89 */     if (!DistributorFactory.isLeaderboardEnabled()) {
/*  90 */       this.header_x += this.center_mod_offset_x;
/*  91 */       this.body_x += this.center_mod_offset_x;
/*  92 */       this.char_x += this.center_mod_offset_x;
/*  93 */       this.mode_desc_x += this.center_mod_offset_x;
/*  94 */       this.mod_icon_x += this.center_mod_offset_x;
/*     */     } 
/*     */     
/*  97 */     this.date_x = this.header_x + FontHelper.getWidth(FontHelper.charTitleFont, TEXT[0], 1.0F) + 12.0F * Settings.scale;
/*  98 */     this.prevHb = new Hitbox(110.0F * Settings.scale, 110.0F * Settings.scale);
/*  99 */     this.prevHb.move(880.0F * Settings.scale, 530.0F * Settings.scale);
/* 100 */     this.nextHb = new Hitbox(110.0F * Settings.scale, 110.0F * Settings.scale);
/* 101 */     this.nextHb.move(1740.0F * Settings.scale, 530.0F * Settings.scale);
/* 102 */     this.prevDayHb = new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale);
/* 103 */     this.prevDayHb.move(1320.0F * Settings.scale, 900.0F * Settings.scale);
/* 104 */     this.nextDayHb = new Hitbox(80.0F * Settings.scale, 80.0F * Settings.scale);
/* 105 */     this.nextDayHb.move(1600.0F * Settings.scale, 900.0F * Settings.scale);
/* 106 */     this.viewMyScoreHb.move(1300.0F * Settings.scale, 80.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/* 110 */     this.cancelButton.update();
/* 111 */     if (this.cancelButton.hb.clicked) {
/* 112 */       FontHelper.ClearLeaderboardFontTextures();
/* 113 */       hide();
/* 114 */       CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */     } 
/*     */     
/* 117 */     this.confirmButton.update();
/*     */     
/* 119 */     this.screenColor.a = MathHelper.popLerpSnap(this.screenColor.a, this.alphaTarget);
/* 120 */     this.screenX = MathHelper.uiLerpSnap(this.screenX, this.targetX);
/* 121 */     pingTimeServer();
/*     */     
/* 123 */     if (this.confirmButton.hb.clicked) {
/* 124 */       this.confirmButton.hb.clicked = false;
/* 125 */       CardCrawlGame.chosenCharacter = this.todaysChar.chosenClass;
/* 126 */       CardCrawlGame.mainMenuScreen.isFadingOut = true;
/* 127 */       hide();
/* 128 */       Settings.isTrial = false;
/* 129 */       Settings.isDailyRun = true;
/* 130 */       Settings.dailyDate = TimeHelper.daySince1970();
/* 131 */       Settings.isEndless = false;
/* 132 */       CardCrawlGame.mainMenuScreen.fadeOutMusic();
/*     */     } 
/*     */     
/* 135 */     updateLeaderboardSection();
/*     */   }
/*     */   
/*     */   private void updateLeaderboardSection() {
/* 139 */     if (this.waiting && this.currentDay == 0L && 
/* 140 */       TimeHelper.daySince1970() != 0L) {
/* 141 */       this.currentDay = TimeHelper.daySince1970();
/* 142 */       getData(1, 20);
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (!this.entries.isEmpty() && !this.waiting) {
/* 147 */       this.lineFadeInTimer = MathHelper.slowColorLerpSnap(this.lineFadeInTimer, 1.0F);
/*     */     }
/*     */     
/* 150 */     updateDateChangeArrows();
/* 151 */     updateArrows();
/* 152 */     updateViewMyScore();
/*     */   }
/*     */   
/*     */   private void updateDateChangeArrows() {
/* 156 */     if (this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 160 */     this.prevDayHb.update();
/* 161 */     if (this.prevDayHb.justHovered) {
/* 162 */       CardCrawlGame.sound.play("UI_HOVER");
/* 163 */     } else if (this.prevDayHb.hovered && InputHelper.justClickedLeft) {
/* 164 */       this.prevDayHb.clickStarted = true;
/* 165 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 166 */     } else if (this.prevDayHb.clicked || CInputActionSet.drawPile.isJustPressed()) {
/* 167 */       if (this.currentDay == 0L) {
/* 168 */         this.currentDay = TimeHelper.daySince1970();
/*     */       }
/* 170 */       this.currentDay--;
/* 171 */       this.prevDayHb.clicked = false;
/* 172 */       this.waiting = true;
/*     */       
/* 174 */       float xOffset = FontHelper.getSmartWidth(FontHelper.panelNameFont, 
/*     */           
/* 176 */           TimeHelper.getDate(this.currentDay), 9999.0F, 0.0F);
/*     */ 
/*     */       
/* 179 */       this.nextDayHb.move(this.prevDayHb.cX + xOffset + 76.0F * Settings.scale, this.nextDayHb.cY);
/* 180 */       getData(1, 20);
/*     */     } 
/*     */     
/* 183 */     if (this.currentDay != 0L && this.currentDay < TimeHelper.daySince1970()) {
/* 184 */       this.nextDayHb.update();
/*     */     }
/* 186 */     if (this.nextDayHb.justHovered) {
/* 187 */       CardCrawlGame.sound.play("UI_HOVER");
/* 188 */     } else if (this.nextDayHb.hovered && InputHelper.justClickedLeft) {
/* 189 */       this.nextDayHb.clickStarted = true;
/* 190 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 191 */     } else if (this.nextDayHb.clicked || (CInputActionSet.discardPile.isJustPressed() && this.currentDay != 0L && this.currentDay < 
/* 192 */       TimeHelper.daySince1970())) {
/* 193 */       if (this.currentDay == 0L) {
/* 194 */         this.currentDay = TimeHelper.daySince1970();
/*     */       }
/* 196 */       this.currentDay++;
/* 197 */       this.nextDayHb.clicked = false;
/* 198 */       this.waiting = true;
/*     */       
/* 200 */       float xOffset = FontHelper.getSmartWidth(FontHelper.panelNameFont, 
/*     */           
/* 202 */           TimeHelper.getDate(this.currentDay), 9999.0F, 0.0F);
/*     */ 
/*     */ 
/*     */       
/* 206 */       this.nextDayHb.move(this.prevDayHb.cX + xOffset + 76.0F * Settings.scale, this.nextDayHb.cY);
/* 207 */       getData(1, 20);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateArrows() {
/* 212 */     if (this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 216 */     if (this.entries.size() == 20) {
/* 217 */       this.nextHb.update();
/* 218 */       if (this.nextHb.justHovered) {
/* 219 */         CardCrawlGame.sound.play("UI_HOVER");
/* 220 */       } else if (this.nextHb.hovered && InputHelper.justClickedLeft) {
/* 221 */         this.nextHb.clickStarted = true;
/* 222 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 223 */       } else if (this.nextHb.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/* 224 */         this.nextHb.clicked = false;
/* 225 */         this.currentStartIndex = this.currentEndIndex + 1;
/* 226 */         this.currentEndIndex = this.currentStartIndex + 19;
/* 227 */         this.waiting = true;
/* 228 */         getData(this.currentStartIndex, this.currentEndIndex);
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     if (this.currentStartIndex != 1) {
/* 233 */       this.prevHb.update();
/* 234 */       if (this.prevHb.justHovered) {
/* 235 */         CardCrawlGame.sound.play("UI_HOVER");
/* 236 */       } else if (this.prevHb.hovered && InputHelper.justClickedLeft) {
/* 237 */         this.prevHb.clickStarted = true;
/* 238 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 239 */       } else if (this.prevHb.clicked || CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/* 240 */         this.prevHb.clicked = false;
/* 241 */         this.currentStartIndex -= 20;
/* 242 */         if (this.currentStartIndex < 1) {
/* 243 */           this.currentStartIndex = 1;
/*     */         }
/* 245 */         this.currentEndIndex = this.currentStartIndex + 19;
/* 246 */         this.waiting = true;
/* 247 */         getData(this.currentStartIndex, this.currentEndIndex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateViewMyScore() {
/* 253 */     if (this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 257 */     this.viewMyScoreHb.update();
/*     */     
/* 259 */     if (this.viewMyScoreHb.justHovered) {
/* 260 */       CardCrawlGame.sound.play("UI_HOVER");
/* 261 */     } else if (this.viewMyScoreHb.hovered && InputHelper.justClickedLeft) {
/* 262 */       this.viewMyScoreHb.clickStarted = true;
/* 263 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 264 */     } else if (this.viewMyScoreHb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/* 265 */       this.viewMyScoreHb.clicked = false;
/* 266 */       this.viewMyScore = true;
/* 267 */       this.waiting = true;
/* 268 */       getData(this.currentStartIndex, this.currentEndIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getData(int startIndex, int endIndex) {
/* 273 */     if (this.currentDay != 0L) {
/* 274 */       CardCrawlGame.publisherIntegration.getDailyLeaderboard(this.currentDay, startIndex, endIndex);
/*     */     }
/*     */   }
/*     */   
/*     */   private void pingTimeServer() {
/* 279 */     if (TimeHelper.isTimeSet && !this.timeUpdated) {
/* 280 */       this.timeUpdated = true;
/* 281 */       this.dFormat = new SimpleDateFormat(TEXT[6]);
/* 282 */       this.dFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
/* 283 */       determineLoadout();
/* 284 */       getData(1, 20);
/* 285 */     } else if (!this.timeLookupActive) {
/* 286 */       TimeLookup.fetchDailyTimeAsync();
/* 287 */       this.timeLookupActive = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void determineLoadout() {
/* 292 */     long todaySeed = TimeHelper.daySince1970();
/*     */     
/* 294 */     Settings.specialSeed = Long.valueOf(todaySeed);
/* 295 */     logger.info("Today's mods: " + ModHelper.getEnabledModIDs().toString());
/*     */     
/* 297 */     AbstractDungeon.isAscensionMode = false;
/*     */     
/* 299 */     this.todayRng = new Random(Long.valueOf(todaySeed));
/* 300 */     this.todaysChar = CardCrawlGame.characterManager.getRandomCharacter(this.todayRng);
/* 301 */     Settings.seed = Long.valueOf(this.todayRng.randomLong());
/*     */     
/* 303 */     String seedText = SeedHelper.getString(Settings.seed.longValue());
/* 304 */     if (BadWordChecker.containsBadWord(seedText)) {
/* 305 */       Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(this.todayRng));
/*     */     }
/* 307 */     AbstractDungeon.generateSeeds();
/*     */     
/* 309 */     ModHelper.setTodaysMods(todaySeed, this.todaysChar.chosenClass);
/*     */     
/* 311 */     this.confirmButton.isDisabled = false;
/* 312 */     this.confirmButton.show();
/*     */     
/* 314 */     logger.info(TEXT[5] + this.todaysChar.chosenClass.name());
/*     */   }
/*     */   
/*     */   public void open() {
/* 318 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.DAILY;
/* 319 */     CardCrawlGame.mainMenuScreen.darken();
/* 320 */     this.alphaTarget = 0.8F;
/* 321 */     this.cancelButton.show(TEXT[3]);
/* 322 */     this.targetX = 100.0F * Settings.scale;
/*     */     
/* 324 */     this.confirmButton.isDisabled = true;
/* 325 */     this.confirmButton.hide();
/*     */ 
/*     */     
/* 328 */     this.waiting = true;
/* 329 */     this.timeUpdated = false;
/* 330 */     this.viewMyScore = false;
/* 331 */     this.currentStartIndex = 1;
/* 332 */     this.currentEndIndex = 20;
/* 333 */     this.entries.clear();
/*     */   }
/*     */   
/*     */   public void hide() {
/* 337 */     this.alphaTarget = 0.0F;
/* 338 */     this.cancelButton.hide();
/* 339 */     this.targetX = -1100.0F * Settings.scale;
/* 340 */     this.confirmButton.hide();
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 344 */     renderTitle(sb);
/*     */     
/* 346 */     if (TimeHelper.isTimeSet) {
/* 347 */       renderTimeLeft(sb);
/* 348 */       renderCharacterAndNotice(sb);
/* 349 */       renderMods(sb);
/* 350 */       this.confirmButton.render(sb);
/*     */     } else {
/* 352 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, TEXT[1], this.screenX + 50.0F * Settings.scale, 786.0F * Settings.scale, 9999.0F, MOD_LINE_SPACING, Settings.CREAM_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 363 */     renderScoreHeaders(sb);
/* 364 */     renderScoreDateToggler(sb);
/* 365 */     renderViewMyScoreBox(sb);
/* 366 */     renderArrows(sb);
/* 367 */     renderScores(sb);
/* 368 */     renderDateToggleArrows(sb);
/* 369 */     renderDisclaimer(sb);
/*     */     
/* 371 */     this.cancelButton.render(sb);
/*     */   }
/*     */   
/*     */   private void renderDateToggleArrows(SpriteBatch sb) {
/* 375 */     sb.draw(ImageMaster.CF_LEFT_ARROW, this.prevDayHb.cX - 24.0F, this.prevDayHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 393 */     if (Settings.isControllerMode) {
/* 394 */       sb.draw(CInputActionSet.drawPile
/* 395 */           .getKeyImg(), this.prevDayHb.cX - 32.0F - 60.0F * Settings.scale, this.prevDayHb.cY - 32.0F + 0.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 413 */     if (this.currentDay != 0L && this.currentDay < TimeHelper.daySince1970()) {
/* 414 */       sb.draw(ImageMaster.CF_RIGHT_ARROW, this.nextDayHb.cX - 24.0F, this.nextDayHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 431 */       this.nextDayHb.render(sb);
/* 432 */       if (Settings.isControllerMode) {
/* 433 */         sb.draw(CInputActionSet.discardPile
/* 434 */             .getKeyImg(), this.nextDayHb.cX - 32.0F + 60.0F * Settings.scale, this.nextDayHb.cY - 32.0F + 0.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       }
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 452 */     this.prevDayHb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderScoreDateToggler(SpriteBatch sb) {
/* 457 */     if (this.currentDay == 0L) {
/* 458 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, 
/*     */ 
/*     */           
/* 461 */           TimeHelper.getTodayDate(), 1360.0F * Settings.scale, 910.0F * Settings.scale, new Color(0.53F, 0.808F, 0.92F, this.lineFadeInTimer));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 466 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, 
/*     */ 
/*     */           
/* 469 */           TimeHelper.getDate(this.currentDay), 1360.0F * Settings.scale, 910.0F * Settings.scale, new Color(0.53F, 0.808F, 0.92F, this.lineFadeInTimer));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderTitle(SpriteBatch sb) {
/* 478 */     FontHelper.renderFontLeftDownAligned(sb, FontHelper.charTitleFont, TEXT[0], this.header_x, TITLE_Y, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 486 */     String offlineModeNotice = "";
/* 487 */     if (TimeHelper.isOfflineMode()) {
/* 488 */       offlineModeNotice = TEXT[16];
/*     */     }
/*     */     
/* 491 */     if (TimeHelper.isTimeSet)
/*     */     {
/* 493 */       FontHelper.renderFontLeftDownAligned(sb, FontHelper.charDescFont, 
/*     */ 
/*     */           
/* 496 */           TimeHelper.getTodayDate() + offlineModeNotice, this.date_x, TITLE_Y, Color.SKY);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderTimeLeft(SpriteBatch sb) {
/* 504 */     FontHelper.renderFontLeftDownAligned(sb, FontHelper.charDescFont, TEXT[7] + 
/*     */ 
/*     */         
/* 507 */         TimeHelper.getTimeLeft(), this.body_x, TIME_LEFT_Y, Settings.CREAM_COLOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderCharacterAndNotice(SpriteBatch sb) {
/* 514 */     if (this.todaysChar == null) {
/*     */       return;
/*     */     }
/*     */     
/* 518 */     Texture img = null;
/* 519 */     StringBuilder builder = new StringBuilder("#y");
/* 520 */     builder.append(TEXT_2[2]);
/* 521 */     builder.append(" NL ");
/*     */     
/* 523 */     img = this.todaysChar.getCustomModeCharacterButtonImage();
/* 524 */     if (this.lastDaily != TimeHelper.daySince1970()) {
/* 525 */       builder.append(this.todaysChar.getLocalizedCharacterName());
/*     */     }
/*     */     
/* 528 */     if (img != null) {
/* 529 */       sb.draw(img, this.header_x, CHAR_IMAGE_Y, 128.0F * Settings.scale, 128.0F * Settings.scale);
/*     */     }
/*     */     
/* 532 */     if (this.lastDaily == TimeHelper.daySince1970()) {
/* 533 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, TEXT[2], this.char_x, CHAR_HEADER_Y, 9999.0F, MOD_LINE_SPACING, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 543 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, builder
/*     */ 
/*     */           
/* 546 */           .toString(), this.char_x, CHAR_HEADER_Y, 9999.0F, MOD_LINE_SPACING, Settings.CREAM_COLOR);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderMods(SpriteBatch sb) {
/* 556 */     FontHelper.renderFont(sb, FontHelper.charDescFont, TEXT[13], this.header_x, MOD_HEADER_Y, Settings.GOLD_COLOR);
/* 557 */     float y = MOD_HEADER_Y - MOD_SECTION_SPACING;
/*     */     
/* 559 */     for (AbstractDailyMod mod : ModHelper.enabledMods) {
/* 560 */       StringBuilder builder = new StringBuilder();
/*     */       
/* 562 */       if (mod.positive) {
/* 563 */         builder.append(FontHelper.colorString(mod.name, "g"));
/*     */       } else {
/* 565 */         builder.append(FontHelper.colorString(mod.name, "r"));
/*     */       } 
/*     */       
/* 568 */       builder.append(": ");
/* 569 */       builder.append(mod.description);
/*     */       
/* 571 */       FontHelper.renderSmartText(sb, FontHelper.charDescFont, builder
/*     */ 
/*     */           
/* 574 */           .toString(), this.mode_desc_x, y, MOD_LINE_W, MOD_LINE_SPACING, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 582 */       sb.draw(mod.img, this.mod_icon_x, y - 44.0F * Settings.scale, 64.0F * Settings.scale, 64.0F * Settings.scale);
/*     */       
/* 584 */       y += FontHelper.getSmartHeight(FontHelper.charDescFont, builder.toString(), MOD_LINE_W, MOD_LINE_SPACING) - MOD_SECTION_SPACING;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderArrows(SpriteBatch sb) {
/* 590 */     boolean renderLeftArrow = true;
/*     */     
/* 592 */     if (this.currentStartIndex != 1 && renderLeftArrow) {
/* 593 */       this.fadeColor.a = this.lineFadeInTimer;
/* 594 */       sb.setColor(this.fadeColor);
/* 595 */       sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 613 */       if (this.prevHb.hovered) {
/* 614 */         sb.setBlendFunction(770, 1);
/* 615 */         this.fadeColor.a = this.lineFadeInTimer / 2.0F;
/* 616 */         sb.setColor(this.fadeColor);
/* 617 */         sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 634 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 637 */       if (Settings.isControllerMode) {
/* 638 */         sb.draw(CInputActionSet.pageLeftViewDeck
/* 639 */             .getKeyImg(), this.prevHb.cX - 32.0F + 0.0F * Settings.scale, this.prevHb.cY - 32.0F - 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 657 */       this.prevHb.render(sb);
/*     */     } 
/*     */ 
/*     */     
/* 661 */     if (this.entries.size() == 20) {
/* 662 */       this.fadeColor.a = this.lineFadeInTimer;
/* 663 */       sb.setColor(this.fadeColor);
/* 664 */       sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, true, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 682 */       if (this.nextHb.hovered) {
/* 683 */         sb.setBlendFunction(770, 1);
/* 684 */         this.fadeColor.a = this.lineFadeInTimer / 2.0F;
/* 685 */         sb.setColor(this.fadeColor);
/* 686 */         sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, true, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 703 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 706 */       if (Settings.isControllerMode) {
/* 707 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 708 */             .getKeyImg(), this.nextHb.cX - 32.0F + 0.0F * Settings.scale, this.nextHb.cY - 32.0F - 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 726 */       this.nextHb.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderDisclaimer(SpriteBatch sb) {
/* 731 */     this.redTextColor.a = this.lineFadeInTimer;
/* 732 */     if (!Settings.usesTrophies) {
/* 733 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[15], Settings.WIDTH * 0.26F, 80.0F * Settings.scale * this.lineFadeInTimer, this.redTextColor);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 741 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[18], Settings.WIDTH * 0.26F, 80.0F * Settings.scale * this.lineFadeInTimer, this.redTextColor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderScoreHeaders(SpriteBatch sb) {
/* 752 */     this.creamColor.a = this.lineFadeInTimer;
/*     */ 
/*     */     
/* 755 */     FontHelper.renderFontRightTopAligned(sb, FontHelper.charTitleFont, TEXT[14], 1570.0F * Settings.scale, 980.0F * Settings.scale, new Color(0.937F, 0.784F, 0.317F, this.lineFadeInTimer));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 764 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT_2[6], RANK_X, 860.0F * Settings.scale, this.creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 773 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT_2[7], NAME_X, 860.0F * Settings.scale, this.creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 782 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT_2[8], SCORE_X, 860.0F * Settings.scale, this.creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 790 */     sb.setColor(this.creamColor);
/* 791 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 1138.0F * Settings.scale, 168.0F * Settings.scale, LINE_THICKNESS, 692.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 797 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 1480.0F * Settings.scale, 168.0F * Settings.scale, LINE_THICKNESS, 692.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 803 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.lineFadeInTimer * 0.75F));
/* 804 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 982.0F * Settings.scale, 814.0F * Settings.scale, 630.0F * Settings.scale, 16.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 810 */     sb.setColor(this.creamColor);
/* 811 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 982.0F * Settings.scale, 820.0F * Settings.scale, 630.0F * Settings.scale, LINE_THICKNESS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderViewMyScoreBox(SpriteBatch sb) {
/* 820 */     if (this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 824 */     FontHelper.cardTitleFont.getData().setScale(1.0F);
/* 825 */     if (this.viewMyScoreHb.hovered) {
/* 826 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT_2[5], 1310.0F * Settings.scale, 80.0F * Settings.scale, Settings.GREEN_TEXT_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 834 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT_2[5], 1310.0F * Settings.scale, 80.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 843 */     if (Settings.isControllerMode) {
/* 844 */       sb.draw(CInputActionSet.topPanel
/* 845 */           .getKeyImg(), 1270.0F * Settings.scale - 32.0F - 
/* 846 */           FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT_2[5], 9999.0F, 0.0F) / 2.0F, -32.0F + 80.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 864 */     this.viewMyScoreHb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderScores(SpriteBatch sb) {
/* 868 */     if (DistributorFactory.isLeaderboardEnabled())
/* 869 */       if (!this.waiting) {
/* 870 */         if (this.entries.isEmpty()) {
/* 871 */           FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT_2[12], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 879 */           for (int i = 0; i < this.entries.size(); i++) {
/* 880 */             ((LeaderboardEntry)this.entries.get(i)).render(sb, i);
/*     */           }
/*     */         }
/*     */       
/* 884 */       } else if (CardCrawlGame.publisherIntegration.isInitialized()) {
/* 885 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT_2[9], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 893 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT_2[11], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\daily\DailyScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */