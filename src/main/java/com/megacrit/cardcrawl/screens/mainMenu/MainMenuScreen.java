/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.GameCursor;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.credits.CreditsScreen;
/*     */ import com.megacrit.cardcrawl.cutscenes.NeowNarrationScreen;
/*     */ import com.megacrit.cardcrawl.daily.DailyScreen;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.DevInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.scenes.TitleBackground;
/*     */ import com.megacrit.cardcrawl.screens.CharSelectInfo;
/*     */ import com.megacrit.cardcrawl.screens.DoorUnlockScreen;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*     */ import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
/*     */ import com.megacrit.cardcrawl.screens.compendium.PotionViewScreen;
/*     */ import com.megacrit.cardcrawl.screens.compendium.RelicViewScreen;
/*     */ import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
/*     */ import com.megacrit.cardcrawl.screens.leaderboards.LeaderboardScreen;
/*     */ import com.megacrit.cardcrawl.screens.options.ConfirmPopup;
/*     */ import com.megacrit.cardcrawl.screens.options.InputSettingsScreen;
/*     */ import com.megacrit.cardcrawl.screens.options.OptionsPanel;
/*     */ import com.megacrit.cardcrawl.screens.runHistory.RunHistoryScreen;
/*     */ import com.megacrit.cardcrawl.screens.stats.StatsScreen;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainMenuScreen
/*     */ {
/*  51 */   private static final Logger logger = LogManager.getLogger(MainMenuScreen.class.getName());
/*  52 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("MainMenuScreen");
/*  53 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  56 */   private static final String VERSION_INFO = CardCrawlGame.VERSION_NUM;
/*  57 */   private Hitbox nameEditHb = Settings.isMobile ? new Hitbox(550.0F * Settings.scale, 180.0F * Settings.scale) : new Hitbox(400.0F * Settings.scale, 100.0F * Settings.scale);
/*     */   public String newName;
/*     */   public boolean darken = false;
/*     */   public boolean superDarken = false;
/*  61 */   public Texture saveSlotImg = null;
/*     */ 
/*     */   
/*  64 */   public Color screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   public static final float OVERLAY_ALPHA = 0.8F;
/*  66 */   private Color overlayColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*     */   public boolean fadedOut = false;
/*  68 */   public long windId = 0L;
/*     */   
/*     */   public boolean isFadingOut = false;
/*  71 */   private CharSelectInfo charInfo = null;
/*     */ 
/*     */   
/*  74 */   public TitleBackground bg = new TitleBackground();
/*     */ 
/*     */ 
/*     */   
/*  78 */   private EarlyAccessPopup eaPopup = null;
/*  79 */   public CurScreen screen = CurScreen.MAIN_MENU;
/*  80 */   public SaveSlotScreen saveSlotScreen = new SaveSlotScreen();
/*  81 */   public MenuPanelScreen panelScreen = new MenuPanelScreen();
/*  82 */   public StatsScreen statsScreen = new StatsScreen();
/*  83 */   public DailyScreen dailyScreen = new DailyScreen();
/*  84 */   public CardLibraryScreen cardLibraryScreen = new CardLibraryScreen();
/*  85 */   public LeaderboardScreen leaderboardsScreen = new LeaderboardScreen();
/*  86 */   public RelicViewScreen relicScreen = new RelicViewScreen();
/*  87 */   public PotionViewScreen potionScreen = new PotionViewScreen();
/*  88 */   public CreditsScreen creditsScreen = new CreditsScreen();
/*  89 */   public DoorUnlockScreen doorUnlockScreen = new DoorUnlockScreen();
/*  90 */   public NeowNarrationScreen neowNarrateScreen = new NeowNarrationScreen();
/*  91 */   public PatchNotesScreen patchNotesScreen = new PatchNotesScreen();
/*     */   public RunHistoryScreen runHistoryScreen;
/*  93 */   public CharacterSelectScreen charSelectScreen = new CharacterSelectScreen();
/*  94 */   public CustomModeScreen customModeScreen = new CustomModeScreen();
/*  95 */   public ConfirmPopup abandonPopup = new ConfirmPopup(ConfirmPopup.ConfirmType.ABANDON_MAIN_MENU);
/*  96 */   public InputSettingsScreen inputSettingsScreen = new InputSettingsScreen();
/*  97 */   public OptionsPanel optionPanel = new OptionsPanel();
/*  98 */   public SyncMessage syncMessage = new SyncMessage();
/*     */   
/*     */   public boolean isSettingsUp = false;
/*     */   
/* 102 */   public ConfirmButton confirmButton = new ConfirmButton(TEXT[1]);
/* 103 */   public MenuCancelButton cancelButton = new MenuCancelButton();
/*     */ 
/*     */   
/* 106 */   private Hitbox deckHb = new Hitbox(-1000.0F, 0.0F, 400.0F * Settings.scale, 80.0F * Settings.scale);
/*     */   
/* 108 */   public ArrayList<MenuButton> buttons = new ArrayList<>();
/*     */   public boolean abandonedRun = false;
/*     */   
/*     */   public enum CurScreen {
/* 112 */     CHAR_SELECT, RELIC_VIEW, POTION_VIEW, BANNER_DECK_VIEW, DAILY, TRIALS, SETTINGS, MAIN_MENU, SAVE_SLOT, STATS, RUN_HISTORY, CARD_LIBRARY, CREDITS, PATCH_NOTES, NONE, LEADERBOARD, ABANDON_CONFIRM, PANEL_MENU, INPUT_SETTINGS, CUSTOM, NEOW_SCREEN, DOOR_UNLOCK;
/*     */   }
/*     */   
/*     */   public MainMenuScreen() {
/* 116 */     this(true);
/*     */   }
/*     */   
/*     */   public MainMenuScreen(boolean playBgm) {
/* 120 */     CardCrawlGame.publisherIntegration.setRichPresenceDisplayInMenu();
/* 121 */     AbstractDungeon.player = null;
/* 122 */     if (Settings.isDemo && Settings.isShowBuild) {
/* 123 */       TipTracker.reset();
/*     */     }
/*     */     
/* 126 */     if (playBgm) {
/* 127 */       CardCrawlGame.music.changeBGM("MENU");
/* 128 */       if (Settings.AMBIANCE_ON) {
/* 129 */         this.windId = CardCrawlGame.sound.playAndLoop("WIND");
/*     */       } else {
/* 131 */         this.windId = CardCrawlGame.sound.playAndLoop("WIND", 0.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     UnlockTracker.refresh();
/* 136 */     this.cardLibraryScreen.initialize();
/* 137 */     this.charSelectScreen.initialize();
/* 138 */     this.confirmButton.hide();
/* 139 */     updateAmbienceVolume();
/* 140 */     this.nameEditHb.move(200.0F * Settings.scale, Settings.HEIGHT - 50.0F * Settings.scale);
/* 141 */     setMainMenuButtons();
/* 142 */     this.runHistoryScreen = new RunHistoryScreen();
/*     */   }
/*     */   
/*     */   private void setMainMenuButtons() {
/* 146 */     this.buttons.clear();
/* 147 */     int index = 0;
/*     */ 
/*     */     
/* 150 */     if (!Settings.isMobile && !Settings.isConsoleBuild) {
/* 151 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.QUIT, index++));
/* 152 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.PATCH_NOTES, index++));
/*     */     } 
/*     */     
/* 155 */     this.buttons.add(new MenuButton(MenuButton.ClickResult.SETTINGS, index++));
/*     */     
/* 157 */     if (!Settings.isShowBuild && 
/* 158 */       this.statsScreen.statScreenUnlocked()) {
/* 159 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.STAT, index++));
/* 160 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.INFO, index++));
/*     */     } 
/*     */ 
/*     */     
/* 164 */     if (CardCrawlGame.characterManager.anySaveFileExists()) {
/* 165 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.ABANDON_RUN, index++));
/* 166 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.RESUME_GAME, index++));
/*     */     } else {
/* 168 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.PLAY, index++));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 174 */     if (this.isFadingOut) {
/* 175 */       InputHelper.justClickedLeft = false;
/* 176 */       InputHelper.justReleasedClickLeft = false;
/* 177 */       InputHelper.justClickedRight = false;
/* 178 */       InputHelper.justReleasedClickRight = false;
/*     */     } 
/*     */     
/* 181 */     this.abandonPopup.update();
/*     */     
/* 183 */     if (this.abandonedRun) {
/* 184 */       this.abandonedRun = false;
/* 185 */       this.buttons.remove(this.buttons.size() - 1);
/* 186 */       this.buttons.remove(this.buttons.size() - 1);
/* 187 */       this.buttons.add(new MenuButton(MenuButton.ClickResult.PLAY, this.buttons.size()));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (Settings.isInfo && DevInputActionSet.deleteSteamCloud.isJustPressed()) {
/* 198 */       CardCrawlGame.publisherIntegration.deleteAllCloudFiles();
/*     */     }
/*     */     
/* 201 */     this.syncMessage.update();
/* 202 */     this.cancelButton.update();
/* 203 */     updateSettings();
/* 204 */     if (this.screen != CurScreen.SAVE_SLOT) {
/* 205 */       for (MenuButton b : this.buttons) {
/* 206 */         b.update();
/*     */       }
/*     */     }
/*     */     
/* 210 */     switch (this.screen) {
/*     */       case CHAR_SELECT:
/* 212 */         updateCharSelectController();
/* 213 */         this.charSelectScreen.update();
/*     */         break;
/*     */       case CARD_LIBRARY:
/* 216 */         this.cardLibraryScreen.update();
/*     */         break;
/*     */       case CUSTOM:
/* 219 */         this.customModeScreen.update();
/*     */         break;
/*     */       case PANEL_MENU:
/* 222 */         updateMenuPanelController();
/* 223 */         this.panelScreen.update();
/*     */         break;
/*     */       case DAILY:
/* 226 */         this.dailyScreen.update();
/*     */         break;
/*     */ 
/*     */       
/*     */       case MAIN_MENU:
/* 231 */         updateMenuButtonController();
/*     */         break;
/*     */       case LEADERBOARD:
/* 234 */         this.leaderboardsScreen.update();
/*     */         break;
/*     */       case RELIC_VIEW:
/* 237 */         this.relicScreen.update();
/*     */         break;
/*     */       case POTION_VIEW:
/* 240 */         this.potionScreen.update();
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case STATS:
/* 249 */         this.statsScreen.update();
/*     */         break;
/*     */       case CREDITS:
/* 252 */         this.creditsScreen.update();
/*     */         break;
/*     */       case DOOR_UNLOCK:
/* 255 */         this.doorUnlockScreen.update();
/*     */         break;
/*     */       case NEOW_SCREEN:
/* 258 */         this.neowNarrateScreen.update();
/*     */         break;
/*     */       case PATCH_NOTES:
/* 261 */         this.patchNotesScreen.update();
/*     */         break;
/*     */       case RUN_HISTORY:
/* 264 */         this.runHistoryScreen.update();
/*     */         break;
/*     */       case INPUT_SETTINGS:
/* 267 */         this.inputSettingsScreen.update();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 273 */     this.saveSlotScreen.update();
/* 274 */     this.bg.update();
/*     */     
/* 276 */     if (this.superDarken) {
/* 277 */       this.screenColor.a = MathHelper.popLerpSnap(this.screenColor.a, 1.0F);
/* 278 */     } else if (this.darken) {
/* 279 */       this.screenColor.a = MathHelper.popLerpSnap(this.screenColor.a, 0.8F);
/*     */     } else {
/* 281 */       this.screenColor.a = MathHelper.popLerpSnap(this.screenColor.a, 0.0F);
/*     */     } 
/*     */     
/* 284 */     if (!this.statsScreen.screenUp) {
/* 285 */       updateRenameArea();
/*     */     }
/*     */     
/* 288 */     if (this.charInfo != null && this.charInfo.resumeGame) {
/* 289 */       this.deckHb.update();
/* 290 */       if (this.deckHb.justHovered) {
/* 291 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */     } 
/* 294 */     if (!this.isFadingOut) {
/* 295 */       handleInput();
/*     */     }
/* 297 */     fadeOut();
/*     */   }
/*     */   
/*     */   private void updateMenuButtonController() {
/* 301 */     if (!Settings.isControllerMode || EarlyAccessPopup.isUp) {
/*     */       return;
/*     */     }
/*     */     
/* 305 */     boolean anyHovered = false;
/* 306 */     int index = 0;
/* 307 */     for (MenuButton b : this.buttons) {
/* 308 */       if (b.hb.hovered) {
/* 309 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 312 */       index++;
/*     */     } 
/*     */ 
/*     */     
/* 316 */     if (anyHovered) {
/* 317 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 318 */         index--;
/* 319 */         if (index < 0) {
/* 320 */           index = this.buttons.size() - 1;
/*     */         }
/* 322 */         CInputHelper.setCursor(((MenuButton)this.buttons.get(index)).hb);
/* 323 */       } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 324 */         index++;
/* 325 */         if (index > this.buttons.size() - 1) {
/* 326 */           index = 0;
/*     */         }
/* 328 */         CInputHelper.setCursor(((MenuButton)this.buttons.get(index)).hb);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 333 */       index = this.buttons.size() - 1;
/* 334 */       CInputHelper.setCursor(((MenuButton)this.buttons.get(index)).hb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateCharSelectController() {
/* 339 */     if (!Settings.isControllerMode || this.isFadingOut) {
/*     */       return;
/*     */     }
/*     */     
/* 343 */     boolean anyHovered = false;
/* 344 */     int index = 0;
/* 345 */     for (CharacterOption b : this.charSelectScreen.options) {
/* 346 */       if (b.hb.hovered) {
/* 347 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 350 */       index++;
/*     */     } 
/*     */     
/* 353 */     if (!anyHovered) {
/* 354 */       index = 0;
/* 355 */       CInputHelper.setCursor(((CharacterOption)this.charSelectScreen.options.get(index)).hb);
/* 356 */       ((CharacterOption)this.charSelectScreen.options.get(index)).hb.clicked = true;
/*     */     } else {
/*     */       
/* 359 */       if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 360 */         index--;
/* 361 */         if (index < 0) {
/* 362 */           index = this.charSelectScreen.options.size() - 1;
/*     */         }
/*     */         
/* 365 */         CInputHelper.setCursor(((CharacterOption)this.charSelectScreen.options.get(index)).hb);
/* 366 */         ((CharacterOption)this.charSelectScreen.options.get(index)).hb.clicked = true;
/* 367 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 368 */         index++;
/* 369 */         if (index > this.charSelectScreen.options.size() - 1) {
/* 370 */           index = 0;
/*     */         }
/*     */         
/* 373 */         CInputHelper.setCursor(((CharacterOption)this.charSelectScreen.options.get(index)).hb);
/* 374 */         ((CharacterOption)this.charSelectScreen.options.get(index)).hb.clicked = true;
/*     */       } 
/*     */       
/* 377 */       if (((CharacterOption)this.charSelectScreen.options.get(index)).locked) {
/* 378 */         this.charSelectScreen.confirmButton.hide();
/*     */       } else {
/* 380 */         this.charSelectScreen.confirmButton.show();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateMenuPanelController() {
/* 386 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 390 */     boolean anyHovered = false;
/* 391 */     int index = 0;
/* 392 */     for (MainMenuPanelButton b : this.panelScreen.panels) {
/* 393 */       if (b.hb.hovered) {
/* 394 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 397 */       index++;
/*     */     } 
/*     */ 
/*     */     
/* 401 */     if (anyHovered) {
/* 402 */       if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 403 */         index--;
/* 404 */         if (index < 0) {
/* 405 */           index = this.panelScreen.panels.size() - 1;
/*     */         }
/*     */         
/* 408 */         if (((MainMenuPanelButton)this.panelScreen.panels.get(index)).pColor == MainMenuPanelButton.PanelColor.GRAY) {
/* 409 */           index--;
/*     */         }
/*     */         
/* 412 */         CInputHelper.setCursor(((MainMenuPanelButton)this.panelScreen.panels.get(index)).hb);
/* 413 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 414 */         index++;
/* 415 */         if (index > this.panelScreen.panels.size() - 1) {
/* 416 */           index = 0;
/*     */         }
/* 418 */         if (((MainMenuPanelButton)this.panelScreen.panels.get(index)).pColor == MainMenuPanelButton.PanelColor.GRAY) {
/* 419 */           index = 0;
/*     */         }
/*     */         
/* 422 */         CInputHelper.setCursor(((MainMenuPanelButton)this.panelScreen.panels.get(index)).hb);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 427 */       index = 0;
/* 428 */       CInputHelper.setCursor(((MainMenuPanelButton)this.panelScreen.panels.get(index)).hb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateSettings() {
/* 433 */     if (this.saveSlotScreen.shown) {
/*     */       return;
/*     */     }
/*     */     
/* 437 */     if (!EarlyAccessPopup.isUp && InputHelper.pressedEscape && this.screen == CurScreen.MAIN_MENU && !this.isFadingOut) {
/* 438 */       if (!this.isSettingsUp) {
/* 439 */         GameCursor.hidden = false;
/* 440 */         CardCrawlGame.sound.play("END_TURN");
/* 441 */         this.isSettingsUp = true;
/* 442 */         darken();
/* 443 */         InputHelper.pressedEscape = false;
/* 444 */         this.statsScreen.hide();
/* 445 */         this.dailyScreen.hide();
/* 446 */         this.cancelButton.hide();
/* 447 */         CardCrawlGame.cancelButton.show(TEXT[2]);
/* 448 */         this.screen = CurScreen.SETTINGS;
/* 449 */         this.panelScreen.panels.clear();
/* 450 */         hideMenuButtons();
/*     */       }
/* 452 */       else if (!EarlyAccessPopup.isUp) {
/* 453 */         this.isSettingsUp = false;
/* 454 */         CardCrawlGame.cancelButton.hide();
/* 455 */         this.screen = CurScreen.MAIN_MENU;
/* 456 */         if (this.screen == CurScreen.MAIN_MENU) {
/* 457 */           this.cancelButton.hide();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 463 */     if (this.isSettingsUp) {
/* 464 */       this.optionPanel.update();
/*     */     }
/*     */     
/* 467 */     CardCrawlGame.cancelButton.update();
/*     */   }
/*     */   
/*     */   private void updateRenameArea() {
/* 471 */     if (this.screen == CurScreen.MAIN_MENU) {
/* 472 */       this.nameEditHb.update();
/*     */     }
/*     */     
/* 475 */     if (this.screen == CurScreen.MAIN_MENU && ((this.nameEditHb.hovered && InputHelper.justClickedLeft) || CInputActionSet.map
/* 476 */       .isJustPressed())) {
/* 477 */       InputHelper.justClickedLeft = false;
/* 478 */       this.nameEditHb.hovered = false;
/* 479 */       this.saveSlotScreen.open(CardCrawlGame.playerName);
/* 480 */       this.screen = CurScreen.SAVE_SLOT;
/*     */     } 
/*     */     
/* 483 */     if (this.bg.slider <= 0.1F && CardCrawlGame.saveSlotPref.getInteger("DEFAULT_SLOT", -1) == -1 && this.screen == CurScreen.MAIN_MENU)
/*     */     {
/* 485 */       if (!setDefaultSlot()) {
/* 486 */         logger.info("No saves detected, opening Save Slot screen automatically.");
/* 487 */         CardCrawlGame.playerPref.putBoolean("ftuePopupShown", true);
/* 488 */         this.saveSlotScreen.open(CardCrawlGame.playerName);
/* 489 */         this.screen = CurScreen.SAVE_SLOT;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean setDefaultSlot() {
/* 495 */     if (!CardCrawlGame.playerPref.getString("name", "").equals("")) {
/* 496 */       logger.info("Migration to Save Slot schema detected, setting DEFAULT_SLOT to 0.");
/* 497 */       CardCrawlGame.saveSlot = 0;
/* 498 */       CardCrawlGame.saveSlotPref.putInteger("DEFAULT_SLOT", 0);
/* 499 */       CardCrawlGame.saveSlotPref.flush();
/* 500 */       return true;
/*     */     } 
/* 502 */     return false;
/*     */   }
/*     */   
/*     */   private void handleInput() {
/* 506 */     this.confirmButton.update();
/*     */   }
/*     */   
/*     */   public void fadeOutMusic() {
/* 510 */     CardCrawlGame.music.fadeOutBGM();
/* 511 */     if (Settings.AMBIANCE_ON) {
/* 512 */       CardCrawlGame.sound.fadeOut("WIND", this.windId);
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 517 */     this.bg.render(sb);
/* 518 */     this.cancelButton.render(sb);
/* 519 */     renderNameEdit(sb);
/*     */     
/* 521 */     for (MenuButton b : this.buttons) {
/* 522 */       b.render(sb);
/*     */     }
/*     */     
/* 525 */     this.abandonPopup.render(sb);
/*     */     
/* 527 */     sb.setColor(this.screenColor);
/* 528 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 530 */     if (this.isFadingOut) {
/* 531 */       this.confirmButton.update();
/*     */     }
/*     */     
/* 534 */     if (this.screen == CurScreen.CHAR_SELECT) {
/* 535 */       this.charSelectScreen.render(sb);
/*     */     }
/*     */     
/* 538 */     sb.setColor(this.overlayColor);
/* 539 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 540 */     renderSettings(sb);
/*     */     
/* 542 */     this.confirmButton.render(sb);
/*     */     
/* 544 */     if (CardCrawlGame.displayVersion) {
/* 545 */       FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, VERSION_INFO, 20.0F * Settings.scale - 700.0F * this.bg.slider, 30.0F * Settings.scale, 10000.0F, 32.0F * Settings.scale, new Color(1.0F, 1.0F, 1.0F, 0.3F));
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
/* 556 */     switch (this.screen) {
/*     */       case CARD_LIBRARY:
/* 558 */         this.cardLibraryScreen.render(sb);
/*     */         break;
/*     */       case CUSTOM:
/* 561 */         this.customModeScreen.render(sb);
/*     */         break;
/*     */       case PANEL_MENU:
/* 564 */         this.panelScreen.render(sb);
/*     */         break;
/*     */       case DAILY:
/* 567 */         this.dailyScreen.render(sb);
/* 568 */         sb.setColor(this.overlayColor);
/* 569 */         sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case RELIC_VIEW:
/* 576 */         this.relicScreen.render(sb);
/*     */         break;
/*     */       case POTION_VIEW:
/* 579 */         this.potionScreen.render(sb);
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case LEADERBOARD:
/* 588 */         this.leaderboardsScreen.render(sb);
/*     */         break;
/*     */       case STATS:
/* 591 */         this.statsScreen.render(sb);
/*     */         break;
/*     */       case RUN_HISTORY:
/* 594 */         this.runHistoryScreen.render(sb);
/*     */         break;
/*     */       case INPUT_SETTINGS:
/* 597 */         this.inputSettingsScreen.render(sb);
/*     */         break;
/*     */       case CREDITS:
/* 600 */         this.creditsScreen.render(sb);
/*     */         break;
/*     */       case DOOR_UNLOCK:
/* 603 */         this.doorUnlockScreen.render(sb);
/*     */         break;
/*     */       case NEOW_SCREEN:
/* 606 */         this.neowNarrateScreen.render(sb);
/*     */         break;
/*     */       case PATCH_NOTES:
/* 609 */         this.patchNotesScreen.render(sb);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 616 */     this.saveSlotScreen.render(sb);
/* 617 */     this.syncMessage.render(sb);
/*     */     
/* 619 */     if (this.eaPopup != null) {
/* 620 */       this.eaPopup.render(sb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void renderSettings(SpriteBatch sb) {
/* 625 */     if (this.isSettingsUp && this.screen == CurScreen.SETTINGS) {
/* 626 */       this.optionPanel.render(sb);
/*     */     }
/* 628 */     CardCrawlGame.cancelButton.render(sb);
/*     */   }
/*     */   
/*     */   private void renderNameEdit(SpriteBatch sb) {
/* 632 */     if (Settings.isMobile) {
/* 633 */       if (!this.nameEditHb.hovered) {
/* 634 */         FontHelper.renderSmartText(sb, FontHelper.cardEnergyFont_L, CardCrawlGame.playerName, 140.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 30.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.GOLD, 0.9F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 645 */         FontHelper.renderSmartText(sb, FontHelper.cardEnergyFont_L, CardCrawlGame.playerName, 140.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 30.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Settings.GREEN_TEXT_COLOR, 0.9F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 657 */     else if (!this.nameEditHb.hovered) {
/* 658 */       FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, CardCrawlGame.playerName, 100.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 24.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.GOLD, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 669 */       FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, CardCrawlGame.playerName, 100.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 24.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Settings.GREEN_TEXT_COLOR, 1.0F);
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
/* 682 */     if (Settings.isTouchScreen || Settings.isMobile) {
/* 683 */       if (!Settings.isMobile) {
/* 684 */         FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, TEXT[5], 100.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 60.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.SKY, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 695 */         FontHelper.renderSmartText(sb, FontHelper.largeDialogOptionFont, TEXT[5], 140.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 80.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.SKY);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 705 */     else if (!Settings.isControllerMode) {
/* 706 */       FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, TEXT[3], 100.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 60.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.SKY, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 717 */       sb.draw(CInputActionSet.map
/* 718 */           .getKeyImg(), -32.0F + 120.0F * Settings.scale - 500.0F * this.bg.slider, -32.0F + Settings.HEIGHT - 78.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.8F, Settings.scale * 0.8F, 0.0F, 0, 0, 64, 64, false, false);
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
/* 734 */       FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, TEXT[4], 150.0F * Settings.scale - 500.0F * this.bg.slider, Settings.HEIGHT - 70.0F * Settings.scale, 1000.0F, 30.0F * Settings.scale, Color.SKY);
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
/* 745 */     if (Settings.isMobile) {
/* 746 */       sb.draw(
/* 747 */           CardCrawlGame.getSaveSlotImg(), 70.0F * Settings.scale - 50.0F - 500.0F * this.bg.slider, Settings.HEIGHT - 70.0F * Settings.scale - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 100, 100, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 764 */       sb.draw(
/* 765 */           CardCrawlGame.getSaveSlotImg(), 50.0F * Settings.scale - 50.0F - 500.0F * this.bg.slider, Settings.HEIGHT - 50.0F * Settings.scale - 50.0F, 50.0F, 50.0F, 100.0F, 100.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 100, 100, false, false);
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
/* 783 */     this.nameEditHb.render(sb);
/*     */   }
/*     */   
/*     */   private void fadeOut() {
/* 787 */     if (this.isFadingOut && !this.fadedOut) {
/* 788 */       this.overlayColor.a += Gdx.graphics.getDeltaTime();
/* 789 */       if (this.overlayColor.a > 1.0F) {
/* 790 */         this.overlayColor.a = 1.0F;
/* 791 */         this.fadedOut = true;
/* 792 */         FontHelper.ClearLeaderboardFontTextures();
/*     */       }
/*     */     
/* 795 */     } else if (this.overlayColor.a != 0.0F) {
/* 796 */       this.overlayColor.a -= Gdx.graphics.getDeltaTime() * 2.0F;
/* 797 */       if (this.overlayColor.a < 0.0F) {
/* 798 */         this.overlayColor.a = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAmbienceVolume() {
/* 805 */     if (Settings.AMBIANCE_ON) {
/* 806 */       CardCrawlGame.sound.adjustVolume("WIND", this.windId);
/*     */     } else {
/* 808 */       CardCrawlGame.sound.adjustVolume("WIND", this.windId, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void muteAmbienceVolume() {
/* 813 */     if (Settings.AMBIANCE_ON) {
/* 814 */       CardCrawlGame.sound.adjustVolume("WIND", this.windId, 0.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void unmuteAmbienceVolume() {
/* 819 */     CardCrawlGame.sound.adjustVolume("WIND", this.windId);
/*     */   }
/*     */   
/*     */   public void darken() {
/* 823 */     this.darken = true;
/*     */   }
/*     */   
/*     */   public void lighten() {
/* 827 */     this.darken = false;
/*     */   }
/*     */   
/*     */   public void hideMenuButtons() {
/* 831 */     for (MenuButton b : this.buttons)
/* 832 */       b.hide(); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\MainMenuScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */