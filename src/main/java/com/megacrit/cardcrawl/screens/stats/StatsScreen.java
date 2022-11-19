/*     */ package com.megacrit.cardcrawl.screens.stats;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Prefs;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.AchievementStrings;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatsScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  33 */   private static final Logger logger = LogManager.getLogger(StatsScreen.class.getName());
/*  34 */   private static final AchievementStrings achievementStrings = CardCrawlGame.languagePack.getAchievementString("StatsScreen");
/*     */   
/*  36 */   public static final String[] NAMES = achievementStrings.NAMES;
/*  37 */   public static final String[] TEXT = achievementStrings.TEXT;
/*  38 */   public MenuCancelButton button = new MenuCancelButton();
/*     */ 
/*     */   
/*  41 */   public Hitbox allCharsHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale); public Hitbox ironcladHb = new Hitbox(150.0F * Settings.scale, 150.0F * Settings.scale);
/*     */   public Hitbox silentHb;
/*     */   public Hitbox defectHb;
/*     */   public Hitbox watcherHb;
/*     */   public Hitbox controllerHb;
/*     */   public boolean screenUp = false;
/*  47 */   private static final float SHOW_X = 300.0F * Settings.scale; private static final float HIDE_X = -800.0F * Settings.scale;
/*  48 */   private float screenX = HIDE_X; private float targetX = HIDE_X;
/*     */   
/*     */   private boolean grabbedScreen = false;
/*     */   
/*  52 */   private float grabStartY = 0.0F;
/*  53 */   private float scrollTargetY = 0.0F;
/*  54 */   private float scrollY = 0.0F;
/*  55 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
/*  56 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*  57 */   private ScrollBar scrollBar = null;
/*     */ 
/*     */   
/*     */   public static CharStat all;
/*     */   
/*     */   public static AchievementGrid achievements;
/*     */   
/*  64 */   public static TextureAtlas atlas = null;
/*     */   
/*     */   public StatsScreen() {
/*  67 */     logger.info("Loading character stats.");
/*  68 */     CardCrawlGame.characterManager.refreshAllCharStats();
/*  69 */     all = new CharStat(CardCrawlGame.characterManager.getAllCharacterStats());
/*  70 */     achievements = new AchievementGrid();
/*  71 */     Settings.totalPlayTime = all.playTime;
/*     */   }
/*     */   
/*     */   public void refreshData() {
/*  75 */     logger.info("Refreshing stats screen data.");
/*  76 */     CardCrawlGame.characterManager.refreshAllCharStats();
/*  77 */     all = new CharStat(CardCrawlGame.characterManager.getAllCharacterStats());
/*  78 */     achievements = new AchievementGrid();
/*  79 */     Settings.totalPlayTime = all.playTime;
/*     */   }
/*     */   
/*     */   public void update() {
/*  83 */     updateControllerInput();
/*  84 */     if (Settings.isControllerMode && this.controllerHb != null) {
/*  85 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.75F) {
/*  86 */         this.scrollTargetY += Settings.SCROLL_SPEED;
/*  87 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.25F) {
/*  88 */         this.scrollTargetY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*  91 */     if (Settings.isControllerMode && this.controllerHb != null) {
/*  92 */       Gdx.input.setCursorPosition((int)this.controllerHb.cX, (int)(Settings.HEIGHT - this.controllerHb.cY));
/*     */     }
/*     */     
/*  95 */     this.button.update();
/*     */     
/*  97 */     if (this.button.hb.clicked || InputHelper.pressedEscape) {
/*  98 */       InputHelper.pressedEscape = false;
/*  99 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 100 */       hide();
/*     */     } 
/*     */     
/* 103 */     this.screenX = MathHelper.uiLerpSnap(this.screenX, this.targetX);
/* 104 */     boolean isDraggingScrollBar = this.scrollBar.update();
/* 105 */     if (!isDraggingScrollBar) {
/* 106 */       updateScrolling();
/*     */     }
/* 108 */     achievements.update();
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 112 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 116 */     boolean anyHovered = false;
/* 117 */     int index = 0;
/*     */     
/* 119 */     this.allCharsHb.update();
/*     */     
/* 121 */     this.ironcladHb.update();
/* 122 */     if (this.silentHb != null) {
/* 123 */       this.silentHb.update();
/*     */     }
/* 125 */     if (this.defectHb != null) {
/* 126 */       this.defectHb.update();
/*     */     }
/* 128 */     if (this.watcherHb != null) {
/* 129 */       this.watcherHb.update();
/*     */     }
/*     */ 
/*     */     
/* 133 */     if (this.allCharsHb != null && 
/* 134 */       this.allCharsHb.hovered) {
/* 135 */       anyHovered = true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 140 */     index = 0;
/* 141 */     if (!anyHovered) {
/* 142 */       for (AchievementItem a : achievements.items) {
/* 143 */         a.hb.update();
/* 144 */         if (a.hb.hovered) {
/* 145 */           anyHovered = true;
/*     */           break;
/*     */         } 
/* 148 */         index++;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 153 */     if (!anyHovered) {
/* 154 */       if (this.ironcladHb != null && this.ironcladHb.hovered) {
/* 155 */         anyHovered = true;
/*     */       }
/* 157 */       if (this.silentHb != null && this.silentHb.hovered) {
/* 158 */         anyHovered = true;
/*     */       }
/* 160 */       if (this.defectHb != null && this.defectHb.hovered) {
/* 161 */         anyHovered = true;
/*     */       }
/* 163 */       if (this.watcherHb != null && this.watcherHb.hovered) {
/* 164 */         anyHovered = true;
/*     */       }
/*     */     } 
/*     */     
/* 168 */     if (!anyHovered) {
/* 169 */       CInputHelper.setCursor(this.allCharsHb);
/* 170 */       this.controllerHb = this.allCharsHb;
/*     */     }
/* 172 */     else if (this.allCharsHb.hovered) {
/* 173 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 174 */         CInputHelper.setCursor(((AchievementItem)achievements.items.get(0)).hb);
/* 175 */         this.controllerHb = ((AchievementItem)achievements.items.get(0)).hb;
/*     */       } 
/* 177 */     } else if (this.ironcladHb.hovered) {
/* 178 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 179 */         index = achievements.items.size() - achievements.items.size() % 5;
/* 180 */         CInputHelper.setCursor(((AchievementItem)achievements.items.get(index)).hb);
/* 181 */         this.controllerHb = ((AchievementItem)achievements.items.get(index)).hb;
/* 182 */       } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.silentHb != null) {
/*     */         
/* 184 */         CInputHelper.setCursor(this.silentHb);
/* 185 */         this.controllerHb = this.silentHb;
/*     */       } 
/* 187 */     } else if (this.silentHb != null && this.silentHb.hovered) {
/* 188 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 189 */         CInputHelper.setCursor(this.ironcladHb);
/* 190 */         this.controllerHb = this.ironcladHb;
/* 191 */       } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.defectHb != null) {
/*     */         
/* 193 */         CInputHelper.setCursor(this.defectHb);
/* 194 */         this.controllerHb = this.defectHb;
/*     */       } 
/* 196 */     } else if (this.defectHb != null && this.defectHb.hovered) {
/* 197 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 198 */         CInputHelper.setCursor(this.silentHb);
/* 199 */         this.controllerHb = this.silentHb;
/* 200 */       } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.watcherHb != null) {
/*     */         
/* 202 */         CInputHelper.setCursor(this.watcherHb);
/* 203 */         this.controllerHb = this.watcherHb;
/*     */       } 
/* 205 */     } else if (this.watcherHb != null && this.watcherHb.hovered) {
/* 206 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 207 */         CInputHelper.setCursor(this.defectHb);
/* 208 */         this.controllerHb = this.defectHb;
/*     */       }
/*     */     
/* 211 */     } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 212 */       index -= 5;
/* 213 */       if (index < 0) {
/* 214 */         CInputHelper.setCursor(this.allCharsHb);
/* 215 */         this.controllerHb = this.allCharsHb;
/*     */       } else {
/* 217 */         CInputHelper.setCursor(((AchievementItem)achievements.items.get(index)).hb);
/* 218 */         this.controllerHb = ((AchievementItem)achievements.items.get(index)).hb;
/*     */       } 
/* 220 */     } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 221 */       index += 5;
/* 222 */       if (index > achievements.items.size() - 1) {
/* 223 */         CInputHelper.setCursor(this.ironcladHb);
/* 224 */         this.controllerHb = this.ironcladHb;
/*     */       } else {
/* 226 */         CInputHelper.setCursor(((AchievementItem)achievements.items.get(index)).hb);
/* 227 */         this.controllerHb = ((AchievementItem)achievements.items.get(index)).hb;
/*     */       } 
/* 229 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 230 */       index--;
/*     */       
/* 232 */       index += 5;
/* 233 */       if ((index % 5 == 4 || index == -1) && index > achievements.items.size() - 1) {
/* 234 */         index = achievements.items.size() - 1;
/*     */       }
/*     */       
/* 237 */       CInputHelper.setCursor(((AchievementItem)achievements.items.get(index)).hb);
/* 238 */       this.controllerHb = ((AchievementItem)achievements.items.get(index)).hb;
/* 239 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 240 */       index++;
/* 241 */       if (index % 5 == 0) {
/* 242 */         index -= 5;
/*     */       }
/* 244 */       if (index > achievements.items.size() - 1) {
/* 245 */         index = achievements.items.size() - achievements.items.size() % 5;
/*     */       }
/* 247 */       CInputHelper.setCursor(((AchievementItem)achievements.items.get(index)).hb);
/* 248 */       this.controllerHb = ((AchievementItem)achievements.items.get(index)).hb;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 255 */     int y = InputHelper.mY;
/*     */     
/* 257 */     if (!this.grabbedScreen) {
/* 258 */       if (InputHelper.scrolledDown) {
/* 259 */         this.scrollTargetY += Settings.SCROLL_SPEED;
/* 260 */       } else if (InputHelper.scrolledUp) {
/* 261 */         this.scrollTargetY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 264 */       if (InputHelper.justClickedLeft) {
/* 265 */         this.grabbedScreen = true;
/* 266 */         this.grabStartY = y - this.scrollTargetY;
/*     */       }
/*     */     
/* 269 */     } else if (InputHelper.isMouseDown) {
/* 270 */       this.scrollTargetY = y - this.grabStartY;
/*     */     } else {
/* 272 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 276 */     this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.scrollTargetY);
/* 277 */     resetScrolling();
/* 278 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void calculateScrollBounds() {
/* 282 */     if (!UnlockTracker.isCharacterLocked("Watcher")) {
/* 283 */       this.scrollUpperBound = 3400.0F * Settings.scale;
/* 284 */     } else if (!UnlockTracker.isCharacterLocked("Defect")) {
/* 285 */       this.scrollUpperBound = 3000.0F * Settings.scale;
/* 286 */     } else if (!UnlockTracker.isCharacterLocked("The Silent")) {
/* 287 */       this.scrollUpperBound = 2550.0F * Settings.scale;
/*     */     } else {
/* 289 */       this.scrollUpperBound = 2250.0F * Settings.scale;
/*     */     } 
/* 291 */     this.scrollLowerBound = 100.0F * Settings.yScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 298 */     if (this.scrollTargetY < this.scrollLowerBound) {
/* 299 */       this.scrollTargetY = MathHelper.scrollSnapLerpSpeed(this.scrollTargetY, this.scrollLowerBound);
/* 300 */     } else if (this.scrollTargetY > this.scrollUpperBound) {
/* 301 */       this.scrollTargetY = MathHelper.scrollSnapLerpSpeed(this.scrollTargetY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void open() {
/* 306 */     if (!Settings.isConsoleBuild) {
/* 307 */       if (CardCrawlGame.publisherIntegration.isInitialized() && CardCrawlGame.publisherIntegration
/* 308 */         .getNumUnlockedAchievements() >= 45) {
/* 309 */         CardCrawlGame.publisherIntegration.unlockAchievement("ETERNAL_ONE");
/*     */       }
/*     */       
/* 312 */       retroactiveAmethystUnlock();
/*     */     } 
/*     */ 
/*     */     
/* 316 */     if (UnlockTracker.isAchievementUnlocked("RUBY_PLUS") && UnlockTracker.isAchievementUnlocked("EMERALD_PLUS") && 
/* 317 */       UnlockTracker.isAchievementUnlocked("SAPPHIRE_PLUS"))
/*     */     {
/* 319 */       UnlockTracker.unlockAchievement("THE_ENDING");
/*     */     }
/*     */     
/* 322 */     if (atlas == null) {
/* 323 */       atlas = new TextureAtlas(Gdx.files.internal("achievements/achievements.atlas"));
/* 324 */       logger.info("Loaded texture Achievement texture atlas.");
/*     */     } 
/*     */     
/* 327 */     this.controllerHb = null;
/* 328 */     this.scrollTargetY = 0.0F;
/* 329 */     debugCharacterUnlock();
/*     */     
/* 331 */     this.targetX = SHOW_X;
/* 332 */     this.button.show(TEXT[0]);
/* 333 */     this.screenUp = true;
/* 334 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.STATS;
/*     */     
/* 336 */     if (this.scrollBar == null) {
/* 337 */       refreshData();
/* 338 */       calculateScrollBounds();
/* 339 */       this.scrollBar = new ScrollBar(this);
/* 340 */       logger.info("Creating new scrollbar for Stats Screen.");
/*     */     } else {
/* 342 */       achievements.refreshImg();
/*     */     } 
/*     */ 
/*     */     
/* 346 */     if (Settings.isControllerMode) {
/* 347 */       Gdx.input.setCursorPosition((int)this.allCharsHb.cX, Settings.HEIGHT - (int)this.allCharsHb.cY);
/* 348 */       this.controllerHb = this.allCharsHb;
/*     */     } 
/*     */     
/* 351 */     debugAchievementUnlock();
/*     */   }
/*     */   
/*     */   private void debugAchievementUnlock() {
/* 355 */     if (Settings.isInfo) {
/* 356 */       for (AchievementItem i : achievements.items) {
/* 357 */         UnlockTracker.unlockAchievement(i.key);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void debugCharacterUnlock() {
/* 363 */     if (Settings.isInfo) {
/* 364 */       for (String s : UnlockTracker.lockedCharacters) {
/* 365 */         UnlockTracker.hardUnlockOverride(s);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void hide() {
/* 371 */     if (atlas != null) {
/* 372 */       atlas.dispose();
/* 373 */       atlas = null;
/*     */     } 
/* 375 */     CardCrawlGame.sound.play("DECK_CLOSE", 0.1F);
/* 376 */     this.targetX = HIDE_X;
/* 377 */     this.button.hide();
/* 378 */     this.screenUp = false;
/* 379 */     CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */   }
/*     */   
/*     */   public static void updateFurthestAscent(int floor) {
/* 383 */     AbstractDungeon.player.getCharStat().furthestAscent(floor);
/*     */   }
/*     */   
/*     */   public static void updateHighestScore(int score) {
/* 387 */     AbstractDungeon.player.getCharStat().highestScore(score);
/*     */   }
/*     */   
/*     */   public static void updateHighestDailyScore(int score) {
/* 391 */     AbstractDungeon.player.getCharStat().highestDaily(score);
/*     */   }
/*     */   
/*     */   public static void updateVictoryTime(long time) {
/* 395 */     logger.info("Saving fastest victory...");
/* 396 */     AbstractDungeon.player.getCharStat().updateFastestVictory(time);
/*     */   }
/*     */   
/*     */   public static void incrementFloorClimbed() {
/* 400 */     AbstractDungeon.player.getCharStat().incrementFloorClimbed();
/*     */   }
/*     */   
/*     */   public static boolean isPlayingHighestAscension(Prefs p) {
/* 404 */     return (AbstractDungeon.ascensionLevel == p.getInteger("ASCENSION_LEVEL", 1));
/*     */   }
/*     */   
/*     */   public static void retroactiveAscend10Unlock(Prefs pref) {
/* 408 */     if (pref.getInteger("ASCENSION_LEVEL", 0) >= 11) {
/* 409 */       UnlockTracker.unlockAchievement("ASCEND_10");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void retroactiveAscend20Unlock(Prefs pref) {
/* 414 */     if (pref.getInteger("ASCENSION_LEVEL", 0) >= 21) {
/* 415 */       UnlockTracker.unlockAchievement("ASCEND_20");
/*     */     }
/*     */   }
/*     */   
/*     */   public static void retroactiveAmethystUnlock() {
/* 420 */     if (UnlockTracker.isAchievementUnlocked("AMETHYST_PLUS")) {
/* 421 */       UnlockTracker.unlockAchievement("AMETHYST");
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getVictory(CharStat c) {
/* 426 */     return c.getVictoryCount();
/*     */   }
/*     */   
/*     */   public static void unlockAscension(CharStat c) {
/* 430 */     c.unlockAscension();
/*     */   }
/*     */   
/*     */   public static void incrementVictory(CharStat c) {
/* 434 */     c.incrementVictory();
/*     */   }
/*     */   
/*     */   public static void incrementAscension(CharStat c) {
/* 438 */     c.incrementAscension();
/*     */   }
/*     */   
/*     */   public static void incrementDeath(CharStat c) {
/* 442 */     c.incrementDeath();
/*     */   }
/*     */   
/*     */   public static void incrementVictoryIfZero(CharStat c) {
/* 446 */     if (c.getVictoryCount() == 0) {
/* 447 */       c.incrementVictory();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void incrementEnemySlain() {
/* 452 */     AbstractDungeon.player.getCharStat().incrementEnemySlain();
/*     */   }
/*     */   
/*     */   public static void incrementBossSlain() {
/* 456 */     AbstractDungeon.player.getCharStat().incrementBossSlain();
/*     */   }
/*     */   
/*     */   public static void incrementPlayTime(long time) {
/* 460 */     AbstractDungeon.player.getCharStat().incrementPlayTime(time);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 464 */     renderStatScreen(sb);
/* 465 */     this.button.render(sb);
/* 466 */     this.scrollBar.render(sb);
/*     */   }
/*     */   
/*     */   private void renderStatScreen(SpriteBatch sb) {
/* 470 */     float renderY = this.scrollY;
/* 471 */     renderHeader(sb, NAMES[0], this.screenX, renderY);
/* 472 */     all.render(sb, this.screenX, renderY);
/* 473 */     renderY -= 400.0F * Settings.scale;
/*     */ 
/*     */     
/* 476 */     renderHeader(sb, NAMES[1], this.screenX, renderY);
/* 477 */     achievements.render(sb, renderY);
/* 478 */     renderY -= 2200.0F * Settings.scale;
/*     */ 
/*     */     
/* 481 */     for (AbstractPlayer c : CardCrawlGame.characterManager.getAllCharacters()) {
/* 482 */       c.renderStatScreen(sb, this.screenX, renderY);
/* 483 */       renderY -= 400.0F * Settings.scale;
/*     */     } 
/*     */     
/* 486 */     if (Settings.isControllerMode) {
/* 487 */       this.allCharsHb.move(300.0F * Settings.scale, this.scrollY + 600.0F * Settings.scale);
/* 488 */       this.ironcladHb.move(300.0F * Settings.scale, this.scrollY - 1600.0F * Settings.scale);
/*     */       
/* 490 */       if (this.silentHb != null) {
/* 491 */         this.silentHb.move(300.0F * Settings.scale, this.scrollY - 2000.0F * Settings.scale);
/*     */       }
/* 493 */       if (this.defectHb != null) {
/* 494 */         this.defectHb.move(300.0F * Settings.scale, this.scrollY - 2400.0F * Settings.scale);
/*     */       }
/* 496 */       if (this.watcherHb != null) {
/* 497 */         this.watcherHb.move(300.0F * Settings.scale, this.scrollY - 2800.0F * Settings.scale);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void renderHeader(SpriteBatch sb, String text, float screenX, float renderY) {
/* 503 */     FontHelper.renderSmartText(sb, FontHelper.charTitleFont, text, screenX + 50.0F * Settings.scale, renderY + 850.0F * Settings.yScale, 9999.0F, 32.0F * Settings.scale, Settings.CREAM_COLOR);
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
/*     */   public boolean statScreenUnlocked() {
/* 516 */     ArrayList<CharStat> allCharStats = CardCrawlGame.characterManager.getAllCharacterStats();
/* 517 */     for (CharStat cs : allCharStats) {
/* 518 */       if (cs.bossKilled > 0 || cs.getDeathCount() > 0) {
/* 519 */         return true;
/*     */       }
/*     */     } 
/* 522 */     return false;
/*     */   }
/*     */   
/*     */   public boolean dailiesUnlocked() {
/* 526 */     if (Settings.isDemo) {
/* 527 */       return false;
/*     */     }
/* 529 */     return ((AbstractDungeon.player.getCharStat()).furthestAscent > 17);
/*     */   }
/*     */   
/*     */   public boolean trialsUnlocked() {
/* 533 */     return (AbstractDungeon.player.getCharStat().getVictoryCount() > 0);
/*     */   }
/*     */   
/*     */   public int getTotalVictories() {
/* 537 */     ArrayList<CharStat> allCharStats = CardCrawlGame.characterManager.getAllCharacterStats();
/* 538 */     int victoryTotal = 0;
/* 539 */     for (CharStat cs : allCharStats) {
/* 540 */       victoryTotal += cs.getVictoryCount();
/*     */     }
/* 542 */     return victoryTotal;
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 547 */     this.scrollY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 548 */     this.scrollTargetY = this.scrollY;
/* 549 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 553 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
/* 554 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\stats\StatsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */