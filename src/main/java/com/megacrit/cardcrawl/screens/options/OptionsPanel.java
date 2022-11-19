/*      */ package com.megacrit.cardcrawl.screens.options;
/*      */ 
/*      */ import com.badlogic.gdx.Gdx;
/*      */ import com.badlogic.gdx.Graphics;
/*      */ import com.badlogic.gdx.graphics.Color;
/*      */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*      */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*      */ import com.megacrit.cardcrawl.core.DisplayConfig;
/*      */ import com.megacrit.cardcrawl.core.Settings;
/*      */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*      */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*      */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*      */ import com.megacrit.cardcrawl.helpers.HitboxListener;
/*      */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*      */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*      */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*      */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*      */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*      */ import com.megacrit.cardcrawl.localization.UIStrings;
/*      */ import com.megacrit.cardcrawl.screens.DisplayOption;
/*      */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*      */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*      */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*      */ import com.megacrit.cardcrawl.vfx.RestartForChangesEffect;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OptionsPanel
/*      */   implements DropdownMenuListener, HitboxListener
/*      */ {
/*   37 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Options Tip");
/*   38 */   public static final String[] MSG = tutorialStrings.TEXT;
/*   39 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*   40 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OptionsPanel");
/*   41 */   public static final String[] TEXT = uiStrings.TEXT;
/*      */   private static final int RAW_W = 1920;
/*      */   private static final int RAW_H = 1080;
/*   44 */   private static final float SCREEN_CENTER_Y = Settings.HEIGHT / 2.0F - 64.0F * Settings.scale;
/*      */   
/*      */   private AbandonRunButton abandonBtn;
/*      */   
/*      */   private ExitGameButton exitBtn;
/*      */   
/*      */   private Hitbox inputSettingsHb;
/*      */   
/*      */   public DropdownMenu fpsDropdown;
/*      */   
/*      */   public DropdownMenu resoDropdown;
/*      */   
/*      */   private Slider masterSlider;
/*      */   private Slider bgmSlider;
/*      */   private Slider sfxSlider;
/*   59 */   private static final float TOGGLE_X_LEFT = 430.0F * Settings.xScale;
/*   60 */   private static final float TOGGLE_X_LEFT_2 = 660.0F * Settings.xScale;
/*   61 */   private static final float TOGGLE_X_RIGHT = 1030.0F * Settings.xScale;
/*      */   public ToggleButton fsToggle;
/*      */   public ToggleButton wfsToggle;
/*      */   public ToggleButton vSyncToggle;
/*      */   private ToggleButton ssToggle;
/*      */   private ToggleButton ambienceToggle;
/*      */   private ToggleButton muteBgToggle;
/*      */   private ToggleButton sumToggle;
/*   69 */   private ToggleButton playtesterToggle = null; private ToggleButton blockToggle;
/*      */   private ToggleButton confirmToggle;
/*      */   private ToggleButton effectsToggle;
/*      */   private ToggleButton fastToggle;
/*      */   private ToggleButton cardKeyOverlayToggle;
/*   74 */   public ArrayList<AbstractGameEffect> effects = new ArrayList<>(); private ToggleButton uploadToggle; private ToggleButton longPressToggle; private ToggleButton bigTextToggle; private DropdownMenu languageDropdown; private String[] languageLabels;
/*   75 */   private Hitbox currentHb = null;
/*      */ 
/*      */   
/*   78 */   public static final String[] LOCALIZED_LANGUAGE_LABELS = (CardCrawlGame.languagePack.getUIString("LanguageDropdown")).TEXT;
/*      */ 
/*      */   
/*   81 */   private String[] FRAMERATE_LABELS = new String[] { "24", "30", "60", "120", "240" };
/*   82 */   private int[] FRAMERATE_OPTIONS = new int[] { 24, 30, 60, 120, 240 };
/*      */ 
/*      */   
/*   85 */   private static final float LEFT_TOGGLE_X = 670.0F * Settings.xScale;
/*   86 */   private static final float LEFT_TEXT_X = 410.0F * Settings.xScale;
/*   87 */   private static final float LEFT_TOGGLE_TEXT_X = 456.0F * Settings.xScale;
/*      */ 
/*      */ 
/*      */   
/*   91 */   private static final String HEADER_TEXT = TEXT[1];
/*   92 */   private static final String GRAPHICS_PANEL_HEADER_TEXT = TEXT[2];
/*   93 */   private static final String RESOLUTION_TEXTS = TEXT[3];
/*   94 */   private static final String FULLSCREEN_TEXTS = TEXT[4];
/*   95 */   private static final String SOUND_PANEL_HEADER_TEXT = TEXT[5];
/*   96 */   private static final String VOLUME_TEXTS = TEXT[6];
/*   97 */   private static final String OTHER_SOUND_TEXTS = TEXT[7];
/*   98 */   private static final String PREF_PANEL_HEADER_TEXT = TEXT[8];
/*   99 */   private static final String PREF_TEXTS = TEXT[9];
/*  100 */   private static final String FAST_MODE_TEXT = TEXT[10];
/*      */   
/*  102 */   private static final String MISC_PANEL_HEADER_TEXT = TEXT[12];
/*  103 */   private static final String LANGUAGE_TEXT = TEXT[13];
/*  104 */   private static final String UPLOAD_TEXT = TEXT[14];
/*  105 */   private static final String EXIT_TEXT = TEXT[15];
/*  106 */   private static final String SAVE_TEXT = TEXT[16];
/*  107 */   private static final String VSYNC_TEXT = TEXT[17];
/*  108 */   private static final String PLAYTESTER_ART_TEXT = TEXT[18];
/*  109 */   private static final String SHOW_CARD_QUICK_SELECT_TEXT = TEXT[19];
/*  110 */   private static final String DISABLE_EFFECTS_TEXT = TEXT[21];
/*  111 */   private static final String LONGPRESS_TEXT = TEXT[25];
/*      */ 
/*      */   
/*      */   public OptionsPanel() {
/*  115 */     this.fsToggle = new ToggleButton(TOGGLE_X_LEFT, 98.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.FULL_SCREEN, true);
/*  116 */     this.wfsToggle = new ToggleButton(TOGGLE_X_LEFT, 64.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.W_FULL_SCREEN, true);
/*  117 */     this.ssToggle = new ToggleButton(TOGGLE_X_LEFT, 30.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SCREEN_SHAKE);
/*  118 */     this.vSyncToggle = new ToggleButton(TOGGLE_X_LEFT_2, 30.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.V_SYNC);
/*      */     
/*  120 */     this.resoDropdown = new DropdownMenu(this, getResolutionLabels(), FontHelper.tipBodyFont, Settings.CREAM_COLOR);
/*  121 */     resetResolutionDropdownSelection();
/*      */     
/*  123 */     this.fpsDropdown = new DropdownMenu(this, this.FRAMERATE_LABELS, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
/*  124 */     resetFpsDropdownSelection();
/*      */ 
/*      */     
/*  127 */     this.sumToggle = new ToggleButton(TOGGLE_X_LEFT, -122.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SUM_DMG, true);
/*  128 */     this.blockToggle = new ToggleButton(TOGGLE_X_LEFT, -156.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.BLOCK_DMG, true);
/*  129 */     this.confirmToggle = new ToggleButton(TOGGLE_X_LEFT, -190.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.HAND_CONF, true);
/*  130 */     this.effectsToggle = new ToggleButton(TOGGLE_X_LEFT, -224.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.EFFECTS, true);
/*  131 */     this.fastToggle = new ToggleButton(TOGGLE_X_LEFT, -258.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.FAST_MODE, true);
/*  132 */     this.cardKeyOverlayToggle = new ToggleButton(TOGGLE_X_LEFT, -292.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SHOW_CARD_HOTKEYS, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  140 */     this.ambienceToggle = new ToggleButton(TOGGLE_X_RIGHT, 58.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.AMBIENCE_ON, true);
/*  141 */     this.muteBgToggle = new ToggleButton(TOGGLE_X_RIGHT, 24.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.MUTE_IF_BG, true);
/*      */     
/*  143 */     this.masterSlider = new Slider(SCREEN_CENTER_Y + 186.0F * Settings.scale, Settings.MASTER_VOLUME, Slider.SliderType.MASTER);
/*  144 */     this.bgmSlider = new Slider(SCREEN_CENTER_Y + 142.0F * Settings.scale, Settings.MUSIC_VOLUME, Slider.SliderType.BGM);
/*  145 */     this.sfxSlider = new Slider(SCREEN_CENTER_Y + 98.0F * Settings.scale, Settings.SOUND_VOLUME, Slider.SliderType.SFX);
/*      */ 
/*      */     
/*  148 */     if (canTogglePlaytesterArt()) {
/*  149 */       this.playtesterToggle = new ToggleButton(TOGGLE_X_RIGHT, -190.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.PLAYTESTER_ART, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  156 */     this.uploadToggle = new ToggleButton(TOGGLE_X_RIGHT, -224.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.UPLOAD_DATA, true);
/*  157 */     this.longPressToggle = new ToggleButton(TOGGLE_X_RIGHT, -258.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.LONG_PRESS, true);
/*  158 */     this.bigTextToggle = new ToggleButton(TOGGLE_X_RIGHT, -292.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.BIG_TEXT, true);
/*      */     
/*  160 */     this.languageLabels = languageLabels();
/*  161 */     this.languageDropdown = new DropdownMenu(this, this.languageLabels, FontHelper.tipBodyFont, Settings.CREAM_COLOR, 9);
/*  162 */     resetLanguageDropdownSelection();
/*      */ 
/*      */     
/*  165 */     this.exitBtn = new ExitGameButton();
/*  166 */     this.abandonBtn = new AbandonRunButton();
/*  167 */     this.inputSettingsHb = new Hitbox(360.0F * Settings.scale, 70.0F * Settings.scale);
/*  168 */     this.inputSettingsHb.move(918.0F * Settings.xScale, Settings.OPTION_Y + 382.0F * Settings.scale);
/*      */   }
/*      */   
/*      */   public void update() {
/*  172 */     updateControllerInput();
/*      */     
/*  174 */     if (CardCrawlGame.isInARun()) {
/*  175 */       this.abandonBtn.update();
/*      */     }
/*  177 */     this.exitBtn.update();
/*      */     
/*  179 */     if (Settings.isControllerMode && CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/*  180 */       clicked(this.inputSettingsHb);
/*      */     }
/*      */     
/*  183 */     this.inputSettingsHb.encapsulatedUpdate(this);
/*      */ 
/*      */     
/*  186 */     if (this.fpsDropdown.isOpen) {
/*  187 */       this.fpsDropdown.update();
/*  188 */     } else if (this.resoDropdown.isOpen) {
/*  189 */       this.resoDropdown.update();
/*  190 */     } else if (this.languageDropdown.isOpen) {
/*  191 */       this.languageDropdown.update();
/*      */     } else {
/*  193 */       updateEffects();
/*  194 */       updateGraphics();
/*  195 */       updateSound();
/*  196 */       updatePreferences();
/*  197 */       updateMiscellaneous();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateControllerInput() {
/*  202 */     if (!Settings.isControllerMode || this.resoDropdown.isOpen || this.fpsDropdown.isOpen || this.languageDropdown.isOpen) {
/*      */       return;
/*      */     }
/*      */     
/*  206 */     if (AbstractDungeon.player != null && 
/*  207 */       AbstractDungeon.player.viewingRelics) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  213 */     if ((this.resoDropdown.getHitbox()).hovered) {
/*  214 */       if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  215 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  216 */         CInputHelper.setCursor(this.masterSlider.bgHb);
/*  217 */         this.currentHb = this.masterSlider.bgHb;
/*  218 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  219 */         CInputHelper.setCursor(this.fpsDropdown.getHitbox());
/*  220 */         this.currentHb = this.fpsDropdown.getHitbox();
/*      */       }
/*      */     
/*      */     }
/*  224 */     else if ((this.fpsDropdown.getHitbox()).hovered) {
/*  225 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  226 */         CInputHelper.setCursor(this.resoDropdown.getHitbox());
/*  227 */         this.currentHb = this.resoDropdown.getHitbox();
/*  228 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  229 */         CInputHelper.setCursor(this.fsToggle.hb);
/*  230 */         this.currentHb = this.fsToggle.hb;
/*  231 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  232 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  233 */         CInputHelper.setCursor(this.bgmSlider.bgHb);
/*  234 */         this.currentHb = this.bgmSlider.bgHb;
/*      */       }
/*      */     
/*      */     }
/*  238 */     else if (this.fsToggle.hb.hovered) {
/*  239 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  240 */         CInputHelper.setCursor(this.resoDropdown.getHitbox());
/*  241 */         this.currentHb = this.resoDropdown.getHitbox();
/*  242 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  243 */         CInputHelper.setCursor(this.wfsToggle.hb);
/*  244 */         this.currentHb = this.wfsToggle.hb;
/*  245 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  246 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  247 */         CInputHelper.setCursor(this.sfxSlider.bgHb);
/*  248 */         this.currentHb = this.sfxSlider.bgHb;
/*      */       }
/*      */     
/*      */     }
/*  252 */     else if (this.wfsToggle.hb.hovered) {
/*  253 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  254 */         CInputHelper.setCursor(this.fsToggle.hb);
/*  255 */         this.currentHb = this.fsToggle.hb;
/*  256 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  257 */         CInputHelper.setCursor(this.ssToggle.hb);
/*  258 */         this.currentHb = this.ssToggle.hb;
/*  259 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  260 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  261 */         CInputHelper.setCursor(this.ambienceToggle.hb);
/*  262 */         this.currentHb = this.ambienceToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  266 */     else if (this.ssToggle.hb.hovered) {
/*  267 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  268 */         CInputHelper.setCursor(this.wfsToggle.hb);
/*  269 */         this.currentHb = this.wfsToggle.hb;
/*  270 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  271 */         CInputHelper.setCursor(this.sumToggle.hb);
/*  272 */         this.currentHb = this.sumToggle.hb;
/*  273 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  274 */         CInputHelper.setCursor(this.vSyncToggle.hb);
/*  275 */         this.currentHb = this.vSyncToggle.hb;
/*  276 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  277 */         CInputHelper.setCursor(this.muteBgToggle.hb);
/*  278 */         this.currentHb = this.muteBgToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  282 */     else if (this.vSyncToggle.hb.hovered) {
/*  283 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  284 */         CInputHelper.setCursor(this.wfsToggle.hb);
/*  285 */         this.currentHb = this.wfsToggle.hb;
/*  286 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  287 */         CInputHelper.setCursor(this.sumToggle.hb);
/*  288 */         this.currentHb = this.sumToggle.hb;
/*  289 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  290 */         CInputHelper.setCursor(this.ssToggle.hb);
/*  291 */         this.currentHb = this.ssToggle.hb;
/*  292 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/*  293 */         CInputHelper.setCursor(this.muteBgToggle.hb);
/*  294 */         this.currentHb = this.muteBgToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  298 */     else if (this.sumToggle.hb.hovered) {
/*  299 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  300 */         CInputHelper.setCursor(this.ssToggle.hb);
/*  301 */         this.currentHb = this.ssToggle.hb;
/*  302 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  303 */         CInputHelper.setCursor(this.blockToggle.hb);
/*  304 */         this.currentHb = this.blockToggle.hb;
/*  305 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  306 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  307 */         CInputHelper.setCursor(this.languageDropdown.getHitbox());
/*  308 */         this.currentHb = this.languageDropdown.getHitbox();
/*      */       }
/*      */     
/*      */     }
/*  312 */     else if (this.blockToggle.hb.hovered) {
/*  313 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  314 */         CInputHelper.setCursor(this.sumToggle.hb);
/*  315 */         this.currentHb = this.sumToggle.hb;
/*  316 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  317 */         CInputHelper.setCursor(this.confirmToggle.hb);
/*  318 */         this.currentHb = this.confirmToggle.hb;
/*  319 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  320 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  321 */         CInputHelper.setCursor(this.languageDropdown.getHitbox());
/*  322 */         this.currentHb = this.languageDropdown.getHitbox();
/*      */       }
/*      */     
/*      */     }
/*  326 */     else if (this.confirmToggle.hb.hovered) {
/*  327 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  328 */         CInputHelper.setCursor(this.blockToggle.hb);
/*  329 */         this.currentHb = this.blockToggle.hb;
/*  330 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  331 */         CInputHelper.setCursor(this.effectsToggle.hb);
/*  332 */         this.currentHb = this.effectsToggle.hb;
/*  333 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  334 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  335 */         if (this.playtesterToggle != null) {
/*  336 */           CInputHelper.setCursor(this.playtesterToggle.hb);
/*  337 */           this.currentHb = this.playtesterToggle.hb;
/*      */         } else {
/*  339 */           CInputHelper.setCursor(this.uploadToggle.hb);
/*  340 */           this.currentHb = this.uploadToggle.hb;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  345 */     } else if (this.effectsToggle.hb.hovered) {
/*  346 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  347 */         CInputHelper.setCursor(this.confirmToggle.hb);
/*  348 */         this.currentHb = this.confirmToggle.hb;
/*  349 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  350 */         CInputHelper.setCursor(this.fastToggle.hb);
/*  351 */         this.currentHb = this.fastToggle.hb;
/*  352 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  353 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  354 */         CInputHelper.setCursor(this.uploadToggle.hb);
/*  355 */         this.currentHb = this.uploadToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  359 */     else if (this.fastToggle.hb.hovered) {
/*  360 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  361 */         CInputHelper.setCursor(this.effectsToggle.hb);
/*  362 */         this.currentHb = this.effectsToggle.hb;
/*  363 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  364 */         CInputHelper.setCursor(this.cardKeyOverlayToggle.hb);
/*  365 */         this.currentHb = this.cardKeyOverlayToggle.hb;
/*  366 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  367 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  368 */         CInputHelper.setCursor(this.longPressToggle.hb);
/*  369 */         this.currentHb = this.longPressToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  373 */     else if (this.cardKeyOverlayToggle.hb.hovered) {
/*  374 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  375 */         CInputHelper.setCursor(this.fastToggle.hb);
/*  376 */         this.currentHb = this.fastToggle.hb;
/*  377 */       } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed() || CInputActionSet.left
/*  378 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  379 */         CInputHelper.setCursor(this.bigTextToggle.hb);
/*  380 */         this.currentHb = this.bigTextToggle.hb;
/*  381 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  382 */         CInputHelper.setCursor(this.masterSlider.bgHb);
/*  383 */         this.currentHb = this.masterSlider.bgHb;
/*      */       }
/*      */     
/*      */     }
/*  387 */     else if (this.masterSlider.bgHb.hovered) {
/*  388 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  389 */         CInputHelper.setCursor(this.bgmSlider.bgHb);
/*  390 */         this.currentHb = this.bgmSlider.bgHb;
/*  391 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  392 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  393 */         CInputHelper.setCursor(this.resoDropdown.getHitbox());
/*  394 */         this.currentHb = this.resoDropdown.getHitbox();
/*      */       }
/*      */     
/*      */     }
/*  398 */     else if (this.bgmSlider.bgHb.hovered) {
/*  399 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  400 */         CInputHelper.setCursor(this.sfxSlider.bgHb);
/*  401 */         this.currentHb = this.sfxSlider.bgHb;
/*  402 */       } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  403 */         CInputHelper.setCursor(this.masterSlider.bgHb);
/*  404 */         this.currentHb = this.masterSlider.bgHb;
/*  405 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  406 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  407 */         CInputHelper.setCursor(this.fpsDropdown.getHitbox());
/*  408 */         this.currentHb = this.fpsDropdown.getHitbox();
/*      */       }
/*      */     
/*      */     }
/*  412 */     else if (this.sfxSlider.bgHb.hovered) {
/*  413 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  414 */         CInputHelper.setCursor(this.ambienceToggle.hb);
/*  415 */         this.currentHb = this.ambienceToggle.hb;
/*  416 */       } else if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  417 */         CInputHelper.setCursor(this.bgmSlider.bgHb);
/*  418 */         this.currentHb = this.bgmSlider.bgHb;
/*  419 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  420 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  421 */         CInputHelper.setCursor(this.fsToggle.hb);
/*  422 */         this.currentHb = this.fsToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  426 */     else if (this.ambienceToggle.hb.hovered) {
/*  427 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  428 */         CInputHelper.setCursor(this.sfxSlider.bgHb);
/*  429 */         this.currentHb = this.sfxSlider.bgHb;
/*  430 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  431 */         CInputHelper.setCursor(this.muteBgToggle.hb);
/*  432 */         this.currentHb = this.muteBgToggle.hb;
/*  433 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  434 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  435 */         CInputHelper.setCursor(this.wfsToggle.hb);
/*  436 */         this.currentHb = this.wfsToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  440 */     else if (this.muteBgToggle.hb.hovered) {
/*  441 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  442 */         CInputHelper.setCursor(this.ambienceToggle.hb);
/*  443 */         this.currentHb = this.ambienceToggle.hb;
/*  444 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  445 */         CInputHelper.setCursor(this.languageDropdown.getHitbox());
/*  446 */         this.currentHb = this.languageDropdown.getHitbox();
/*  447 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  448 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  449 */         CInputHelper.setCursor(this.vSyncToggle.hb);
/*  450 */         this.currentHb = this.vSyncToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  454 */     else if ((this.languageDropdown.getHitbox()).hovered) {
/*  455 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  456 */         CInputHelper.setCursor(this.muteBgToggle.hb);
/*  457 */         this.currentHb = this.muteBgToggle.hb;
/*  458 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  459 */         if (canTogglePlaytesterArt()) {
/*  460 */           CInputHelper.setCursor(this.playtesterToggle.hb);
/*  461 */           this.currentHb = this.playtesterToggle.hb;
/*      */         } else {
/*  463 */           CInputHelper.setCursor(this.uploadToggle.hb);
/*  464 */           this.currentHb = this.uploadToggle.hb;
/*      */         } 
/*  466 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  467 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  468 */         CInputHelper.setCursor(this.sumToggle.hb);
/*  469 */         this.currentHb = this.sumToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  473 */     else if (this.playtesterToggle != null && this.playtesterToggle.hb.hovered) {
/*  474 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  475 */         CInputHelper.setCursor(this.languageDropdown.getHitbox());
/*  476 */         this.currentHb = this.languageDropdown.getHitbox();
/*  477 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  478 */         CInputHelper.setCursor(this.uploadToggle.hb);
/*  479 */         this.currentHb = this.uploadToggle.hb;
/*  480 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  481 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  482 */         CInputHelper.setCursor(this.confirmToggle.hb);
/*  483 */         this.currentHb = this.confirmToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  487 */     else if (this.uploadToggle.hb.hovered) {
/*  488 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  489 */         if (this.playtesterToggle != null) {
/*  490 */           CInputHelper.setCursor(this.playtesterToggle.hb);
/*  491 */           this.currentHb = this.playtesterToggle.hb;
/*      */         } else {
/*  493 */           CInputHelper.setCursor(this.languageDropdown.getHitbox());
/*  494 */           this.currentHb = this.languageDropdown.getHitbox();
/*      */         } 
/*  496 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  497 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  498 */         CInputHelper.setCursor(this.effectsToggle.hb);
/*  499 */         this.currentHb = this.effectsToggle.hb;
/*  500 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  501 */         CInputHelper.setCursor(this.longPressToggle.hb);
/*  502 */         this.currentHb = this.longPressToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  506 */     else if (this.longPressToggle.hb.hovered) {
/*  507 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  508 */         CInputHelper.setCursor(this.uploadToggle.hb);
/*  509 */         this.currentHb = this.uploadToggle.hb;
/*  510 */       } else if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/*  511 */         CInputHelper.setCursor(this.bigTextToggle.hb);
/*  512 */         this.currentHb = this.bigTextToggle.hb;
/*  513 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  514 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  515 */         CInputHelper.setCursor(this.fastToggle.hb);
/*  516 */         this.currentHb = this.fastToggle.hb;
/*      */       }
/*      */     
/*      */     }
/*  520 */     else if (this.bigTextToggle.hb.hovered) {
/*  521 */       if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/*  522 */         CInputHelper.setCursor(this.longPressToggle.hb);
/*  523 */         this.currentHb = this.longPressToggle.hb;
/*  524 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.right.isJustPressed() || CInputActionSet.altRight
/*  525 */         .isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/*  526 */         CInputHelper.setCursor(this.cardKeyOverlayToggle.hb);
/*  527 */         this.currentHb = this.cardKeyOverlayToggle.hb;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  532 */       CInputHelper.setCursor(this.resoDropdown.getHitbox());
/*  533 */       this.currentHb = this.resoDropdown.getHitbox();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateEffects() {
/*  538 */     for (Iterator<AbstractGameEffect> c = this.effects.iterator(); c.hasNext(); ) {
/*  539 */       AbstractGameEffect e = c.next();
/*  540 */       e.update();
/*  541 */       if (e.isDone) {
/*  542 */         c.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateGraphics() {
/*  548 */     this.fsToggle.update();
/*  549 */     this.wfsToggle.update();
/*  550 */     this.ssToggle.update();
/*  551 */     this.vSyncToggle.update();
/*  552 */     this.resoDropdown.update();
/*  553 */     this.fpsDropdown.update();
/*      */     
/*  555 */     if (this.fsToggle.hb.hovered) {
/*  556 */       TipHelper.renderGenericTip(Settings.WIDTH * 0.03F, this.fsToggle.hb.cY + 50.0F * Settings.scale, LABEL[1], MSG[1]);
/*  557 */     } else if (this.wfsToggle.hb.hovered) {
/*  558 */       TipHelper.renderGenericTip(Settings.WIDTH * 0.03F, this.wfsToggle.hb.cY + 50.0F * Settings.scale, LABEL[2], MSG[2]);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  563 */     else if ((this.fpsDropdown.getHitbox()).hovered) {
/*  564 */       TipHelper.renderGenericTip(Settings.WIDTH * 0.03F, 
/*      */           
/*  566 */           (this.fpsDropdown.getHitbox()).cY + 30.0F * Settings.scale, LABEL[3], MSG[3]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateSound() {
/*  573 */     this.ambienceToggle.update();
/*  574 */     this.muteBgToggle.update();
/*  575 */     this.masterSlider.update();
/*  576 */     this.bgmSlider.update();
/*  577 */     this.sfxSlider.update();
/*      */   }
/*      */   
/*      */   private void updatePreferences() {
/*  581 */     this.sumToggle.update();
/*  582 */     this.blockToggle.update();
/*  583 */     this.confirmToggle.update();
/*  584 */     this.effectsToggle.update();
/*  585 */     this.fastToggle.update();
/*  586 */     this.cardKeyOverlayToggle.update();
/*      */   }
/*      */   
/*      */   private void updateMiscellaneous() {
/*  590 */     this.uploadToggle.update();
/*  591 */     if (this.playtesterToggle != null) {
/*  592 */       this.playtesterToggle.update();
/*      */     }
/*  594 */     if (this.uploadToggle.hb.hovered) {
/*  595 */       TipHelper.renderGenericTip(Settings.WIDTH * 0.03F, Settings.HEIGHT / 2.0F, LABEL[0], MSG[0]);
/*      */     }
/*      */     
/*  598 */     this.longPressToggle.update();
/*  599 */     this.bigTextToggle.update();
/*  600 */     this.languageDropdown.update();
/*      */   }
/*      */   
/*      */   public void render(SpriteBatch sb) {
/*  604 */     renderBg(sb);
/*  605 */     renderBanner(sb);
/*  606 */     renderGraphics(sb);
/*  607 */     renderSound(sb);
/*  608 */     renderPreferences(sb);
/*  609 */     renderMiscellaneous(sb);
/*      */     
/*  611 */     if (CardCrawlGame.isInARun()) {
/*  612 */       this.abandonBtn.render(sb);
/*      */     }
/*  614 */     this.exitBtn.render(sb);
/*      */     
/*  616 */     if (!Settings.isConsoleBuild && !Settings.isMobile) {
/*  617 */       this.languageDropdown.render(sb, 1150.0F * Settings.xScale, SCREEN_CENTER_Y - 115.0F * Settings.scale);
/*      */     } else {
/*  619 */       this.languageDropdown.render(sb, 1180.0F * Settings.xScale, SCREEN_CENTER_Y - 100.0F * Settings.scale);
/*      */     } 
/*      */ 
/*      */     
/*  623 */     if (this.resoDropdown.isOpen) {
/*  624 */       this.fpsDropdown.render(sb, LEFT_TOGGLE_X, SCREEN_CENTER_Y + 160.0F * Settings.scale);
/*  625 */       this.resoDropdown.render(sb, LEFT_TOGGLE_X, SCREEN_CENTER_Y + 206.0F * Settings.scale);
/*      */     } else {
/*  627 */       this.resoDropdown.render(sb, LEFT_TOGGLE_X, SCREEN_CENTER_Y + 206.0F * Settings.scale);
/*  628 */       this.fpsDropdown.render(sb, LEFT_TOGGLE_X, SCREEN_CENTER_Y + 160.0F * Settings.scale);
/*      */     } 
/*      */     
/*  631 */     for (AbstractGameEffect e : this.effects) {
/*  632 */       e.render(sb);
/*      */     }
/*      */     
/*  635 */     renderControllerUi(sb, this.currentHb);
/*      */   }
/*      */   
/*      */   private void renderControllerUi(SpriteBatch sb, Hitbox hb) {
/*  639 */     if (!Settings.isControllerMode) {
/*      */       return;
/*      */     }
/*      */     
/*  643 */     if (hb != null) {
/*  644 */       sb.setBlendFunction(770, 1);
/*  645 */       sb.setColor(new Color(0.7F, 0.9F, 1.0F, 0.25F));
/*  646 */       sb.draw(ImageMaster.CONTROLLER_HB_HIGHLIGHT, hb.cX - hb.width / 2.0F, hb.cY - hb.height / 2.0F, hb.width, hb.height);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  652 */       sb.setBlendFunction(770, 771);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void renderBg(SpriteBatch sb) {
/*  657 */     sb.setColor(Color.WHITE);
/*      */ 
/*      */     
/*  660 */     sb.draw(ImageMaster.INPUT_SETTINGS_EDGES, Settings.WIDTH / 2.0F - 960.0F, Settings.OPTION_Y - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 1920, 1080, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  678 */     sb.draw(ImageMaster.SETTINGS_BACKGROUND, Settings.WIDTH / 2.0F - 960.0F, Settings.OPTION_Y - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 1920, 1080, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  696 */     if (Settings.isControllerMode) {
/*  697 */       sb.draw(CInputActionSet.pageRightViewExhaust
/*  698 */           .getKeyImg(), this.inputSettingsHb.cX - 32.0F + 
/*  699 */           FontHelper.getSmartWidth(FontHelper.panelNameFont, TEXT[20], 99999.0F, 0.0F) / 2.0F + 42.0F * Settings.scale, Settings.OPTION_Y - 32.0F + 379.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.xScale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderBanner(SpriteBatch sb) {
/*  720 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, HEADER_TEXT, this.inputSettingsHb.cX - 396.0F * Settings.xScale, this.inputSettingsHb.cY, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  729 */     Color textColor = this.inputSettingsHb.hovered ? Settings.GOLD_COLOR : Color.LIGHT_GRAY;
/*  730 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[20], this.inputSettingsHb.cX, this.inputSettingsHb.cY, textColor);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  737 */     this.inputSettingsHb.render(sb);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderGraphics(SpriteBatch sb) {
/*  742 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, GRAPHICS_PANEL_HEADER_TEXT, 636.0F * Settings.xScale, SCREEN_CENTER_Y + 256.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  751 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, RESOLUTION_TEXTS, LEFT_TEXT_X, SCREEN_CENTER_Y + 196.0F * Settings.scale, 10000.0F, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  761 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, FULLSCREEN_TEXTS, LEFT_TOGGLE_TEXT_X, SCREEN_CENTER_Y + 106.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  772 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, VSYNC_TEXT, 686.0F * Settings.xScale, SCREEN_CENTER_Y + 106.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  782 */     this.fsToggle.render(sb);
/*  783 */     this.wfsToggle.render(sb);
/*  784 */     this.ssToggle.render(sb);
/*  785 */     this.vSyncToggle.render(sb);
/*      */   }
/*      */   
/*      */   private void renderSound(SpriteBatch sb) {
/*  789 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, SOUND_PANEL_HEADER_TEXT, 1264.0F * Settings.xScale, SCREEN_CENTER_Y + 256.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  797 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, VOLUME_TEXTS, 1020.0F * Settings.xScale, SCREEN_CENTER_Y + 196.0F * Settings.scale, 10000.0F, 44.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  807 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, OTHER_SOUND_TEXTS, 1056.0F * Settings.xScale, SCREEN_CENTER_Y + 66.0F * Settings.scale, 10000.0F, 34.8F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  817 */     this.masterSlider.render(sb);
/*  818 */     this.bgmSlider.render(sb);
/*  819 */     this.sfxSlider.render(sb);
/*  820 */     this.ambienceToggle.render(sb);
/*  821 */     this.muteBgToggle.render(sb);
/*      */   }
/*      */   
/*      */   private void renderPreferences(SpriteBatch sb) {
/*  825 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, PREF_PANEL_HEADER_TEXT, 636.0F * Settings.xScale, SCREEN_CENTER_Y - 60.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  833 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, PREF_TEXTS + " NL " + DISABLE_EFFECTS_TEXT + " NL " + FAST_MODE_TEXT + " NL " + SHOW_CARD_QUICK_SELECT_TEXT, 456.0F * Settings.xScale, SCREEN_CENTER_Y - 112.0F * Settings.scale, 10000.0F, 34.8F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  843 */     this.sumToggle.render(sb);
/*  844 */     this.blockToggle.render(sb);
/*  845 */     this.confirmToggle.render(sb);
/*  846 */     this.effectsToggle.render(sb);
/*  847 */     this.fastToggle.render(sb);
/*  848 */     this.cardKeyOverlayToggle.render(sb);
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderMiscellaneous(SpriteBatch sb) {
/*  853 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, MISC_PANEL_HEADER_TEXT, 1264.0F * Settings.xScale, SCREEN_CENTER_Y - 60.0F * Settings.scale, Settings.GOLD_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  861 */     FontHelper.renderSmartText(sb, FontHelper.cardDescFont_N, LANGUAGE_TEXT, 1020.0F * Settings.xScale, SCREEN_CENTER_Y - 114.0F * Settings.scale, 10000.0F, 44.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  871 */     if (this.playtesterToggle != null) {
/*  872 */       FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, PLAYTESTER_ART_TEXT, 1056.0F * Settings.xScale, SCREEN_CENTER_Y - 182.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  883 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, UPLOAD_TEXT, 1056.0F * Settings.xScale, SCREEN_CENTER_Y - 216.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  893 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, LONGPRESS_TEXT, 1056.0F * Settings.xScale, SCREEN_CENTER_Y - 250.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  903 */     FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, TEXT[26], 1056.0F * Settings.xScale, SCREEN_CENTER_Y - 284.0F * Settings.scale, 10000.0F, 34.0F * Settings.scale, Settings.CREAM_COLOR);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  913 */     this.uploadToggle.render(sb);
/*  914 */     if (this.playtesterToggle != null) {
/*  915 */       this.playtesterToggle.render(sb);
/*      */     }
/*  917 */     this.longPressToggle.render(sb);
/*  918 */     this.bigTextToggle.render(sb);
/*      */   }
/*      */   
/*      */   private boolean canTogglePlaytesterArt() {
/*  922 */     return (UnlockTracker.isAchievementUnlocked("THE_ENDING") || Settings.isBeta);
/*      */   }
/*      */   
/*      */   public void refresh() {
/*  926 */     this.currentHb = null;
/*  927 */     this.fsToggle = new ToggleButton(TOGGLE_X_LEFT, 98.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.FULL_SCREEN);
/*  928 */     this.wfsToggle = new ToggleButton(TOGGLE_X_LEFT, 64.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.W_FULL_SCREEN);
/*  929 */     this.ssToggle = new ToggleButton(TOGGLE_X_LEFT, 30.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SCREEN_SHAKE);
/*  930 */     this.vSyncToggle = new ToggleButton(TOGGLE_X_LEFT_2, 30.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.V_SYNC);
/*  931 */     this.sumToggle = new ToggleButton(TOGGLE_X_LEFT, -122.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SUM_DMG);
/*  932 */     this.blockToggle = new ToggleButton(TOGGLE_X_LEFT, -156.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.BLOCK_DMG);
/*  933 */     this.confirmToggle = new ToggleButton(TOGGLE_X_LEFT, -190.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.HAND_CONF);
/*  934 */     this.effectsToggle = new ToggleButton(TOGGLE_X_LEFT, -224.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.EFFECTS);
/*  935 */     this.fastToggle = new ToggleButton(TOGGLE_X_LEFT, -258.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.FAST_MODE);
/*  936 */     this.cardKeyOverlayToggle = new ToggleButton(TOGGLE_X_LEFT, -292.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.SHOW_CARD_HOTKEYS);
/*  937 */     this.ambienceToggle = new ToggleButton(TOGGLE_X_RIGHT, 58.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.AMBIENCE_ON);
/*  938 */     this.muteBgToggle = new ToggleButton(TOGGLE_X_RIGHT, 24.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.MUTE_IF_BG);
/*  939 */     if (canTogglePlaytesterArt()) {
/*  940 */       this.playtesterToggle = new ToggleButton(TOGGLE_X_RIGHT, -190.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.PLAYTESTER_ART);
/*      */     }
/*  942 */     this.uploadToggle = new ToggleButton(TOGGLE_X_RIGHT, -224.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.UPLOAD_DATA);
/*  943 */     this.longPressToggle = new ToggleButton(TOGGLE_X_RIGHT, -258.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.LONG_PRESS);
/*  944 */     this.bigTextToggle = new ToggleButton(TOGGLE_X_RIGHT, -292.0F, SCREEN_CENTER_Y, ToggleButton.ToggleBtnType.BIG_TEXT);
/*      */     
/*  946 */     this.masterSlider = new Slider(SCREEN_CENTER_Y + 186.0F * Settings.scale, Settings.MASTER_VOLUME, Slider.SliderType.MASTER);
/*  947 */     this.bgmSlider = new Slider(SCREEN_CENTER_Y + 142.0F * Settings.scale, Settings.MUSIC_VOLUME, Slider.SliderType.BGM);
/*  948 */     this.sfxSlider = new Slider(SCREEN_CENTER_Y + 98.0F * Settings.scale, Settings.SOUND_VOLUME, Slider.SliderType.SFX);
/*      */     
/*  950 */     this.resoDropdown = new DropdownMenu(this, getResolutionLabels(), FontHelper.tipBodyFont, Settings.CREAM_COLOR);
/*  951 */     resetResolutionDropdownSelection();
/*      */     
/*  953 */     this.fpsDropdown = new DropdownMenu(this, this.FRAMERATE_LABELS, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
/*  954 */     resetFpsDropdownSelection();
/*      */     
/*  956 */     this.languageDropdown = new DropdownMenu(this, this.languageLabels, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
/*  957 */     resetLanguageDropdownSelection();
/*      */     
/*  959 */     this.exitBtn.updateLabel(SAVE_TEXT);
/*      */     
/*  961 */     if (!Gdx.files.local(AbstractDungeon.player.getSaveFilePath()).exists()) {
/*  962 */       this.exitBtn.updateLabel(EXIT_TEXT);
/*      */     }
/*      */   }
/*      */   
/*      */   public void displayRestartRequiredText() {
/*  967 */     if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/*  968 */       if (CardCrawlGame.mainMenuScreen != null) {
/*  969 */         CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();
/*  970 */         CardCrawlGame.mainMenuScreen.optionPanel.effects.add(new RestartForChangesEffect());
/*      */       } 
/*      */     } else {
/*  973 */       AbstractDungeon.topLevelEffects.add(new RestartForChangesEffect());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void changedSelectionTo(DropdownMenu dropdownMenu, int index, String optionText) {
/*  981 */     if (dropdownMenu == this.languageDropdown) {
/*  982 */       changeLanguageToIndex(index);
/*  983 */       if (Settings.isControllerMode) {
/*  984 */         Gdx.input.setCursorPosition(
/*  985 */             (int)(this.languageDropdown.getHitbox()).cX, Settings.HEIGHT - 
/*  986 */             (int)(this.languageDropdown.getHitbox()).cY);
/*  987 */         this.currentHb = this.languageDropdown.getHitbox();
/*      */       } 
/*  989 */     } else if (dropdownMenu == this.resoDropdown) {
/*  990 */       changeResolutionToIndex(index);
/*  991 */       if (Settings.isControllerMode) {
/*  992 */         Gdx.input.setCursorPosition(
/*  993 */             (int)(this.resoDropdown.getHitbox()).cX, Settings.HEIGHT - 
/*  994 */             (int)(this.resoDropdown.getHitbox()).cY);
/*  995 */         this.currentHb = this.resoDropdown.getHitbox();
/*      */       } 
/*  997 */     } else if (dropdownMenu == this.fpsDropdown) {
/*  998 */       changeFrameRateToIndex(index);
/*  999 */       if (Settings.isControllerMode) {
/* 1000 */         Gdx.input.setCursorPosition(
/* 1001 */             (int)(this.fpsDropdown.getHitbox()).cX, Settings.HEIGHT - 
/* 1002 */             (int)(this.fpsDropdown.getHitbox()).cY);
/* 1003 */         this.currentHb = this.fpsDropdown.getHitbox();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetLanguageDropdownSelection() {
/* 1011 */     Settings.GameLanguage[] languageOptions = LanguageOptions();
/* 1012 */     for (int i = 0; i < languageOptions.length; i++) {
/* 1013 */       if (Settings.language == languageOptions[i]) {
/* 1014 */         this.languageDropdown.setSelectedIndex(i);
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void changeLanguageToIndex(int index) {
/* 1021 */     if (index >= LOCALIZED_LANGUAGE_LABELS.length) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1026 */     Settings.GameLanguage[] languageOptions = LanguageOptions();
/* 1027 */     for (int i = 0; i < languageOptions.length; i++) {
/* 1028 */       if (Settings.language == languageOptions[i] && 
/* 1029 */         i == index) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1035 */     Settings.setLanguage(LanguageOptions()[index], false);
/* 1036 */     Settings.gamePref.flush();
/* 1037 */     displayRestartRequiredText();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] languageLabels() {
/* 1046 */     if (Settings.isConsoleBuild) {
/* 1047 */       return new String[] { LOCALIZED_LANGUAGE_LABELS[3], LOCALIZED_LANGUAGE_LABELS[0], LOCALIZED_LANGUAGE_LABELS[1], LOCALIZED_LANGUAGE_LABELS[2], LOCALIZED_LANGUAGE_LABELS[26], LOCALIZED_LANGUAGE_LABELS[4], LOCALIZED_LANGUAGE_LABELS[5], LOCALIZED_LANGUAGE_LABELS[24], LOCALIZED_LANGUAGE_LABELS[6], LOCALIZED_LANGUAGE_LABELS[7], LOCALIZED_LANGUAGE_LABELS[8], LOCALIZED_LANGUAGE_LABELS[9], LOCALIZED_LANGUAGE_LABELS[10], LOCALIZED_LANGUAGE_LABELS[11], LOCALIZED_LANGUAGE_LABELS[16], LOCALIZED_LANGUAGE_LABELS[13], LOCALIZED_LANGUAGE_LABELS[18], LOCALIZED_LANGUAGE_LABELS[19] };
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1066 */     if (!Settings.isBeta) {
/* 1067 */       return new String[] { LOCALIZED_LANGUAGE_LABELS[3], LOCALIZED_LANGUAGE_LABELS[0], LOCALIZED_LANGUAGE_LABELS[1], LOCALIZED_LANGUAGE_LABELS[2], LOCALIZED_LANGUAGE_LABELS[26], LOCALIZED_LANGUAGE_LABELS[25], LOCALIZED_LANGUAGE_LABELS[27], LOCALIZED_LANGUAGE_LABELS[4], LOCALIZED_LANGUAGE_LABELS[5], LOCALIZED_LANGUAGE_LABELS[24], LOCALIZED_LANGUAGE_LABELS[6], LOCALIZED_LANGUAGE_LABELS[7], LOCALIZED_LANGUAGE_LABELS[8], LOCALIZED_LANGUAGE_LABELS[9], LOCALIZED_LANGUAGE_LABELS[10], LOCALIZED_LANGUAGE_LABELS[17], LOCALIZED_LANGUAGE_LABELS[21], LOCALIZED_LANGUAGE_LABELS[11], LOCALIZED_LANGUAGE_LABELS[16], LOCALIZED_LANGUAGE_LABELS[13], LOCALIZED_LANGUAGE_LABELS[18], LOCALIZED_LANGUAGE_LABELS[19] };
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1092 */     return new String[] { LOCALIZED_LANGUAGE_LABELS[3], LOCALIZED_LANGUAGE_LABELS[0], LOCALIZED_LANGUAGE_LABELS[1], LOCALIZED_LANGUAGE_LABELS[2], LOCALIZED_LANGUAGE_LABELS[26], LOCALIZED_LANGUAGE_LABELS[25], LOCALIZED_LANGUAGE_LABELS[27], LOCALIZED_LANGUAGE_LABELS[4], LOCALIZED_LANGUAGE_LABELS[5], LOCALIZED_LANGUAGE_LABELS[23], LOCALIZED_LANGUAGE_LABELS[24], LOCALIZED_LANGUAGE_LABELS[6], LOCALIZED_LANGUAGE_LABELS[7], LOCALIZED_LANGUAGE_LABELS[8], LOCALIZED_LANGUAGE_LABELS[15], LOCALIZED_LANGUAGE_LABELS[9], LOCALIZED_LANGUAGE_LABELS[10], LOCALIZED_LANGUAGE_LABELS[17], LOCALIZED_LANGUAGE_LABELS[21], LOCALIZED_LANGUAGE_LABELS[11], LOCALIZED_LANGUAGE_LABELS[16], LOCALIZED_LANGUAGE_LABELS[13], LOCALIZED_LANGUAGE_LABELS[18], LOCALIZED_LANGUAGE_LABELS[19] };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Settings.GameLanguage[] LanguageOptions() {
/* 1126 */     if (Settings.isConsoleBuild) {
/* 1127 */       return new Settings.GameLanguage[] { Settings.GameLanguage.ENG, Settings.GameLanguage.PTB, Settings.GameLanguage.ZHS, Settings.GameLanguage.ZHT, Settings.GameLanguage.DUT, Settings.GameLanguage.FRA, Settings.GameLanguage.DEU, Settings.GameLanguage.ITA, Settings.GameLanguage.ITA, Settings.GameLanguage.JPN, Settings.GameLanguage.KOR, Settings.GameLanguage.POL, Settings.GameLanguage.RUS, Settings.GameLanguage.SPA, Settings.GameLanguage.THA, Settings.GameLanguage.TUR, Settings.GameLanguage.UKR, Settings.GameLanguage.VIE };
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1134 */     if (!Settings.isBeta) {
/* 1135 */       return new Settings.GameLanguage[] { Settings.GameLanguage.ENG, Settings.GameLanguage.PTB, Settings.GameLanguage.ZHS, Settings.GameLanguage.ZHT, Settings.GameLanguage.DUT, Settings.GameLanguage.EPO, Settings.GameLanguage.FIN, Settings.GameLanguage.FRA, Settings.GameLanguage.DEU, Settings.GameLanguage.IND, Settings.GameLanguage.ITA, Settings.GameLanguage.JPN, Settings.GameLanguage.KOR, Settings.GameLanguage.POL, Settings.GameLanguage.RUS, Settings.GameLanguage.SRP, Settings.GameLanguage.SRB, Settings.GameLanguage.SPA, Settings.GameLanguage.THA, Settings.GameLanguage.TUR, Settings.GameLanguage.UKR, Settings.GameLanguage.VIE };
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1144 */     return new Settings.GameLanguage[] { Settings.GameLanguage.ENG, Settings.GameLanguage.PTB, Settings.GameLanguage.ZHS, Settings.GameLanguage.ZHT, Settings.GameLanguage.DUT, Settings.GameLanguage.EPO, Settings.GameLanguage.FIN, Settings.GameLanguage.FRA, Settings.GameLanguage.DEU, Settings.GameLanguage.GRE, Settings.GameLanguage.IND, Settings.GameLanguage.ITA, Settings.GameLanguage.JPN, Settings.GameLanguage.KOR, Settings.GameLanguage.NOR, Settings.GameLanguage.POL, Settings.GameLanguage.RUS, Settings.GameLanguage.SRP, Settings.GameLanguage.SRB, Settings.GameLanguage.SPA, Settings.GameLanguage.THA, Settings.GameLanguage.TUR, Settings.GameLanguage.UKR, Settings.GameLanguage.VIE };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void changeFrameRateToIndex(int index) {
/* 1160 */     if (Settings.MAX_FPS != this.FRAMERATE_OPTIONS[index]) {
/* 1161 */       this.fpsDropdown.setSelectedIndex(index);
/* 1162 */       Settings.MAX_FPS = this.FRAMERATE_OPTIONS[index];
/* 1163 */       DisplayConfig.writeDisplayConfigFile(Settings.SAVED_WIDTH, Settings.SAVED_HEIGHT, Settings.MAX_FPS, Settings.IS_FULLSCREEN, Settings.IS_W_FULLSCREEN, Settings.IS_V_SYNC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1170 */       displayRestartRequiredText();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void resetFpsDropdownSelection() {
/* 1175 */     boolean found = false;
/* 1176 */     for (int i = 0; i < this.FRAMERATE_OPTIONS.length; i++) {
/* 1177 */       if (Settings.MAX_FPS == this.FRAMERATE_OPTIONS[i]) {
/* 1178 */         found = true;
/* 1179 */         changeFrameRateToIndex(i);
/* 1180 */         this.fpsDropdown.setSelectedIndex(i);
/*      */       } 
/*      */     } 
/* 1183 */     if (!found) {
/* 1184 */       Settings.MAX_FPS = 60;
/* 1185 */       changeFrameRateToIndex(2);
/* 1186 */       this.fpsDropdown.setSelectedIndex(2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void changeResolutionToIndex(int index) {
/* 1194 */     if (Settings.displayIndex == index) {
/*      */       return;
/*      */     }
/*      */     
/* 1198 */     this.resoDropdown.setSelectedIndex(index);
/* 1199 */     Settings.displayIndex = index;
/* 1200 */     displayRestartRequiredText();
/*      */     
/* 1202 */     if (index > Settings.displayOptions.size() - 1) {
/* 1203 */       index = 0;
/*      */     }
/*      */     
/* 1206 */     int TMP_WIDTH = ((DisplayOption)Settings.displayOptions.get(index)).width;
/* 1207 */     int TMP_HEIGHT = ((DisplayOption)Settings.displayOptions.get(index)).height;
/*      */     
/* 1209 */     DisplayConfig.writeDisplayConfigFile(TMP_WIDTH, TMP_HEIGHT, Settings.MAX_FPS, Settings.IS_FULLSCREEN, Settings.IS_W_FULLSCREEN, Settings.IS_V_SYNC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1217 */     Settings.SAVED_WIDTH = TMP_WIDTH;
/* 1218 */     Settings.SAVED_HEIGHT = TMP_HEIGHT;
/*      */   }
/*      */   
/*      */   public void resetResolutionDropdownSelection() {
/* 1222 */     DisplayConfig dConfig = DisplayConfig.readConfig();
/*      */     
/* 1224 */     for (int i = 0; i < Settings.displayOptions.size(); i++) {
/*      */       
/* 1226 */       if (dConfig.getWidth() == ((DisplayOption)Settings.displayOptions.get(i)).width && dConfig
/* 1227 */         .getHeight() == ((DisplayOption)Settings.displayOptions.get(i)).height) {
/* 1228 */         Settings.displayIndex = i;
/* 1229 */         this.resoDropdown.setSelectedIndex(i);
/* 1230 */         this.resoDropdown.topVisibleRowIndex = 0;
/*      */         return;
/*      */       } 
/*      */     } 
/* 1234 */     this.resoDropdown.setSelectedIndex(Settings.displayIndex);
/*      */   }
/*      */   
/*      */   private ArrayList<String> getResolutionLabels() {
/* 1238 */     initalizeDisplayOptionsIfNull();
/* 1239 */     ArrayList<String> labels = new ArrayList<>();
/*      */     
/* 1241 */     for (DisplayOption option : Settings.displayOptions) {
/* 1242 */       labels.add(option.uiString());
/*      */     }
/* 1244 */     return labels;
/*      */   }
/*      */   
/*      */   public ArrayList<String> getResolutionLabels(int mode) {
/* 1248 */     switch (mode) {
/*      */       case 0:
/* 1250 */         setDisplayOptionsToFullscreen();
/*      */         break;
/*      */       case 1:
/* 1253 */         setDisplayOptionsToBfs();
/*      */         break;
/*      */       default:
/* 1256 */         setDisplayOptionsToAllResolutions();
/*      */         break;
/*      */     } 
/*      */     
/* 1260 */     ArrayList<String> labels = new ArrayList<>();
/* 1261 */     for (DisplayOption option : Settings.displayOptions) {
/* 1262 */       labels.add(option.uiString());
/*      */     }
/* 1264 */     return labels;
/*      */   }
/*      */   
/*      */   private void initalizeDisplayOptionsIfNull() {
/* 1268 */     if (Settings.displayOptions == null) {
/* 1269 */       if (Settings.IS_FULLSCREEN) {
/* 1270 */         Settings.displayOptions = getFullScreenOnlyResolutions();
/* 1271 */       } else if (Settings.IS_W_FULLSCREEN) {
/* 1272 */         Settings.displayOptions = getBfsOnlyResolutions();
/*      */       } else {
/* 1274 */         Settings.displayOptions = getWindowedAndFullscreenResolutions();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void setDisplayOptionsToBfs() {
/* 1280 */     Settings.displayOptions.clear();
/* 1281 */     Settings.displayOptions = null;
/* 1282 */     Settings.displayOptions = getBfsOnlyResolutions();
/*      */   }
/*      */   
/*      */   public void setDisplayOptionsToFullscreen() {
/* 1286 */     Settings.displayOptions.clear();
/* 1287 */     Settings.displayOptions = null;
/* 1288 */     Settings.displayOptions = getFullScreenOnlyResolutions();
/*      */   }
/*      */   
/*      */   public void setDisplayOptionsToAllResolutions() {
/* 1292 */     Settings.displayOptions.clear();
/* 1293 */     Settings.displayOptions = null;
/* 1294 */     Settings.displayOptions = getWindowedAndFullscreenResolutions();
/*      */   }
/*      */   
/*      */   private ArrayList<DisplayOption> getBfsOnlyResolutions() {
/* 1298 */     ArrayList<DisplayOption> retVal = new ArrayList<>();
/*      */ 
/*      */     
/* 1301 */     Graphics.DisplayMode[] modes = Gdx.graphics.getDisplayModes(Gdx.graphics.getPrimaryMonitor());
/*      */     
/* 1303 */     List<DisplayOption> modesList = new ArrayList<>();
/* 1304 */     for (int i = 0; i < modes.length; i++) {
/* 1305 */       modesList.add(new DisplayOption((modes[i]).width, (modes[i]).height));
/*      */     }
/*      */     
/* 1308 */     Collections.sort(modesList);
/* 1309 */     retVal.add(modesList.get(modesList.size() - 1));
/*      */ 
/*      */     
/* 1312 */     return retVal;
/*      */   }
/*      */   
/*      */   private ArrayList<DisplayOption> getFullScreenOnlyResolutions() {
/* 1316 */     ArrayList<DisplayOption> retVal = new ArrayList<>();
/* 1317 */     ArrayList<DisplayOption> allowedResolutions = getAllowedResolutions();
/*      */ 
/*      */     
/* 1320 */     Graphics.DisplayMode[] modes = Gdx.graphics.getDisplayModes(Gdx.graphics.getPrimaryMonitor());
/*      */     
/* 1322 */     for (Graphics.DisplayMode m : modes) {
/* 1323 */       DisplayOption o = new DisplayOption(m.width, m.height, true);
/*      */       
/* 1325 */       if (!retVal.contains(o) && allowedResolutions.contains(o)) {
/* 1326 */         retVal.add(o);
/*      */       }
/*      */     } 
/*      */     
/* 1330 */     Collections.sort(retVal);
/*      */     
/* 1332 */     return retVal;
/*      */   }
/*      */   
/*      */   private ArrayList<DisplayOption> getAllowedResolutions() {
/* 1336 */     ArrayList<DisplayOption> retVal = new ArrayList<>();
/*      */     
/* 1338 */     retVal.add(new DisplayOption(1680, 720));
/* 1339 */     retVal.add(new DisplayOption(2560, 1080));
/* 1340 */     retVal.add(new DisplayOption(3440, 1440));
/*      */ 
/*      */     
/* 1343 */     retVal.add(new DisplayOption(1024, 576));
/* 1344 */     retVal.add(new DisplayOption(1280, 720));
/* 1345 */     retVal.add(new DisplayOption(1366, 768));
/* 1346 */     retVal.add(new DisplayOption(1536, 864));
/* 1347 */     retVal.add(new DisplayOption(1600, 900));
/* 1348 */     retVal.add(new DisplayOption(1920, 1080));
/* 1349 */     retVal.add(new DisplayOption(2560, 1440));
/* 1350 */     retVal.add(new DisplayOption(3840, 2160));
/*      */ 
/*      */     
/* 1353 */     retVal.add(new DisplayOption(1024, 640));
/* 1354 */     retVal.add(new DisplayOption(1280, 800));
/* 1355 */     retVal.add(new DisplayOption(1680, 1050));
/* 1356 */     retVal.add(new DisplayOption(1920, 1200));
/* 1357 */     retVal.add(new DisplayOption(2560, 1600));
/*      */ 
/*      */     
/* 1360 */     retVal.add(new DisplayOption(1024, 768));
/* 1361 */     retVal.add(new DisplayOption(1280, 960));
/* 1362 */     retVal.add(new DisplayOption(1400, 1050));
/* 1363 */     retVal.add(new DisplayOption(1440, 1080));
/* 1364 */     retVal.add(new DisplayOption(1600, 1200));
/* 1365 */     retVal.add(new DisplayOption(2048, 1536));
/* 1366 */     retVal.add(new DisplayOption(2224, 1668));
/* 1367 */     retVal.add(new DisplayOption(2732, 2048));
/*      */     
/* 1369 */     return retVal;
/*      */   }
/*      */   
/*      */   private ArrayList<DisplayOption> getWindowedAndFullscreenResolutions() {
/* 1373 */     ArrayList<DisplayOption> retVal = new ArrayList<>();
/* 1374 */     ArrayList<DisplayOption> availableResos = getAllowedResolutions();
/*      */ 
/*      */ 
/*      */     
/* 1378 */     DisplayOption screenRes = new DisplayOption((Gdx.graphics.getDisplayMode()).width, (Gdx.graphics.getDisplayMode()).height);
/*      */     
/* 1380 */     if (!retVal.contains(screenRes)) {
/* 1381 */       availableResos.add(screenRes);
/*      */     }
/*      */     
/* 1384 */     for (DisplayOption o : availableResos) {
/* 1385 */       if (o.width <= (Gdx.graphics.getDisplayMode()).width && o.height <= (Gdx.graphics.getDisplayMode()).height && 
/* 1386 */         !retVal.contains(o)) {
/* 1387 */         retVal.add(o);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1392 */     Graphics.DisplayMode[] modes = Gdx.graphics.getDisplayModes(Gdx.graphics.getPrimaryMonitor());
/*      */     
/* 1394 */     for (Graphics.DisplayMode m : modes) {
/* 1395 */       DisplayOption o = new DisplayOption(m.width, m.height);
/*      */       
/* 1397 */       if (!retVal.contains(o) && o.width >= 1024 && o.height >= 576) {
/* 1398 */         retVal.add(o);
/*      */       }
/*      */     } 
/*      */     
/* 1402 */     Collections.sort(retVal);
/* 1403 */     return retVal;
/*      */   }
/*      */   
/*      */   public void setFullscreen(boolean borderless) {
/* 1407 */     int TMP_WIDTH = (Gdx.graphics.getDisplayMode()).width;
/* 1408 */     int TMP_HEIGHT = (Gdx.graphics.getDisplayMode()).height;
/*      */     
/* 1410 */     DisplayConfig.writeDisplayConfigFile(TMP_WIDTH, TMP_HEIGHT, Settings.MAX_FPS, !borderless, borderless, Settings.IS_V_SYNC);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1418 */     Settings.SAVED_WIDTH = TMP_WIDTH;
/* 1419 */     Settings.SAVED_HEIGHT = TMP_HEIGHT;
/*      */     
/* 1421 */     for (int i = 0; i < Settings.displayOptions.size(); i++) {
/* 1422 */       if (((DisplayOption)Settings.displayOptions.get(i)).equals(new DisplayOption(TMP_WIDTH, TMP_HEIGHT))) {
/* 1423 */         changeResolutionToIndex(i);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void hoverStarted(Hitbox hitbox) {
/* 1434 */     CardCrawlGame.sound.play("UI_HOVER");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void startClicking(Hitbox hitbox) {
/* 1442 */     CardCrawlGame.sound.play("UI_CLICK_1");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clicked(Hitbox hitbox) {
/* 1450 */     if (hitbox == this.inputSettingsHb)
/* 1451 */       if (CardCrawlGame.isInARun()) {
/* 1452 */         AbstractDungeon.inputSettingsScreen.open(false);
/*      */       } else {
/*      */         
/* 1455 */         CardCrawlGame.cancelButton.hideInstantly();
/* 1456 */         CardCrawlGame.mainMenuScreen.inputSettingsScreen.open(false);
/* 1457 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.INPUT_SETTINGS;
/* 1458 */         CardCrawlGame.mainMenuScreen.isSettingsUp = false;
/*      */       }  
/*      */   }
/*      */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\OptionsPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */