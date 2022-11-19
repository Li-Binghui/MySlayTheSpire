/*     */ package com.megacrit.cardcrawl.screens.charSelect;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SeedHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TrialHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.metrics.BotDataUploader;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*     */ import com.megacrit.cardcrawl.ui.buttons.ConfirmButton;
/*     */ import com.megacrit.cardcrawl.ui.panels.SeedPanel;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharacterSelectScreen
/*     */ {
/*  37 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CharacterSelectScreen");
/*  38 */   public static final String[] TEXT = uiStrings.TEXT;
/*  39 */   private static final UIStrings uiStrings2 = CardCrawlGame.languagePack.getUIString("AscensionModeDescriptions");
/*  40 */   public static final String[] A_TEXT = uiStrings2.TEXT;
/*     */   
/*     */   private static float ASC_LEFT_W;
/*     */   private static float ASC_RIGHT_W;
/*  44 */   private SeedPanel seedPanel = new SeedPanel();
/*  45 */   private static final float SEED_X = 60.0F * Settings.scale;
/*  46 */   private static final float SEED_Y = 90.0F * Settings.scale;
/*     */   
/*  48 */   private static final String CHOOSE_CHAR_MSG = TEXT[0];
/*  49 */   public ConfirmButton confirmButton = new ConfirmButton(TEXT[1]);
/*  50 */   public MenuCancelButton cancelButton = new MenuCancelButton();
/*  51 */   public ArrayList<CharacterOption> options = new ArrayList<>();
/*     */   
/*     */   private boolean anySelected = false;
/*     */   
/*  55 */   public Texture bgCharImg = null;
/*  56 */   private Color bgCharColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   private static final float BG_Y_OFFSET_TARGET = 0.0F;
/*  58 */   private float bg_y_offset = 0.0F;
/*     */   
/*     */   public boolean isAscensionMode = false;
/*     */   
/*     */   private boolean isAscensionModeUnlocked = false;
/*     */   private Hitbox ascensionModeHb;
/*  64 */   public int ascensionLevel = 0; private Hitbox ascLeftHb; private Hitbox ascRightHb; private Hitbox seedHb;
/*  65 */   public String ascLevelInfoString = "";
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  69 */     this.options.add(new CharacterOption(TEXT[2], CardCrawlGame.characterManager
/*     */ 
/*     */           
/*  72 */           .recreateCharacter(AbstractPlayer.PlayerClass.IRONCLAD), ImageMaster.CHAR_SELECT_IRONCLAD, ImageMaster.CHAR_SELECT_BG_IRONCLAD));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     if (!UnlockTracker.isCharacterLocked("The Silent")) {
/*  78 */       this.options.add(new CharacterOption(TEXT[3], CardCrawlGame.characterManager
/*     */ 
/*     */             
/*  81 */             .recreateCharacter(AbstractPlayer.PlayerClass.THE_SILENT), ImageMaster.CHAR_SELECT_SILENT, ImageMaster.CHAR_SELECT_BG_SILENT));
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.options.add(new CharacterOption(CardCrawlGame.characterManager.recreateCharacter(AbstractPlayer.PlayerClass.THE_SILENT)));
/*     */     } 
/*     */ 
/*     */     
/*  89 */     if (!UnlockTracker.isCharacterLocked("Defect")) {
/*  90 */       this.options.add(new CharacterOption(TEXT[4], CardCrawlGame.characterManager
/*     */ 
/*     */             
/*  93 */             .recreateCharacter(AbstractPlayer.PlayerClass.DEFECT), ImageMaster.CHAR_SELECT_DEFECT, ImageMaster.CHAR_SELECT_BG_DEFECT));
/*     */     }
/*     */     else {
/*     */       
/*  97 */       this.options.add(new CharacterOption(CardCrawlGame.characterManager.recreateCharacter(AbstractPlayer.PlayerClass.DEFECT)));
/*     */     } 
/*     */ 
/*     */     
/* 101 */     if (!UnlockTracker.isCharacterLocked("Watcher")) {
/* 102 */       addCharacterOption(AbstractPlayer.PlayerClass.WATCHER);
/*     */     } else {
/* 104 */       this.options.add(new CharacterOption(CardCrawlGame.characterManager.recreateCharacter(AbstractPlayer.PlayerClass.WATCHER)));
/*     */     } 
/*     */     
/* 107 */     positionButtons();
/*     */     
/* 109 */     this.isAscensionMode = Settings.gamePref.getBoolean("Ascension Mode Default", false);
/* 110 */     FontHelper.cardTitleFont.getData().setScale(1.0F);
/* 111 */     ASC_LEFT_W = FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[6], 9999.0F, 0.0F);
/* 112 */     ASC_RIGHT_W = FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[7] + "22", 9999.0F, 0.0F);
/* 113 */     this.ascensionModeHb = new Hitbox(ASC_LEFT_W + 100.0F * Settings.scale, 70.0F * Settings.scale);
/*     */     
/* 115 */     if (!Settings.isMobile) {
/* 116 */       this.ascensionModeHb.move(Settings.WIDTH / 2.0F - ASC_LEFT_W / 2.0F - 50.0F * Settings.scale, 70.0F * Settings.scale);
/*     */     } else {
/* 118 */       this.ascensionModeHb.move(Settings.WIDTH / 2.0F - ASC_LEFT_W / 2.0F - 50.0F * Settings.scale, 100.0F * Settings.scale);
/*     */     } 
/*     */     
/* 121 */     this.ascLeftHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
/* 122 */     this.ascRightHb = new Hitbox(70.0F * Settings.scale, 70.0F * Settings.scale);
/* 123 */     this.seedHb = new Hitbox(700.0F * Settings.scale, 60.0F * Settings.scale);
/* 124 */     this.seedHb.move(90.0F * Settings.scale, 70.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   private void addCharacterOption(AbstractPlayer.PlayerClass c) {
/* 128 */     AbstractPlayer p = CardCrawlGame.characterManager.recreateCharacter(c);
/* 129 */     this.options.add(p.getCharacterSelectOption());
/*     */   }
/*     */ 
/*     */   
/*     */   private void positionButtons() {
/* 134 */     int count = this.options.size();
/* 135 */     float offsetX = Settings.WIDTH / 2.0F - 330.0F * Settings.scale;
/*     */     
/* 137 */     for (int i = 0; i < count; i++) {
/* 138 */       if (Settings.isMobile) {
/* 139 */         ((CharacterOption)this.options.get(i)).hb.move(offsetX + i * 220.0F * Settings.scale, 230.0F * Settings.yScale);
/*     */       } else {
/* 141 */         ((CharacterOption)this.options.get(i)).hb.move(offsetX + i * 220.0F * Settings.scale, 190.0F * Settings.yScale);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void open(boolean isEndless) {
/* 147 */     Settings.isEndless = isEndless;
/* 148 */     Settings.seedSet = false;
/* 149 */     Settings.seed = null;
/* 150 */     Settings.specialSeed = null;
/* 151 */     Settings.isTrial = false;
/* 152 */     CardCrawlGame.trial = null;
/* 153 */     this.cancelButton.show(TEXT[5]);
/* 154 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CHAR_SELECT;
/*     */   }
/*     */   
/*     */   private void setRandomSeed() {
/* 158 */     long sourceTime = System.nanoTime();
/* 159 */     Random rng = new Random(Long.valueOf(sourceTime));
/* 160 */     Settings.seedSourceTimestamp = sourceTime;
/* 161 */     Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(rng));
/* 162 */     Settings.seedSet = false;
/*     */   }
/*     */   
/*     */   public void update() {
/* 166 */     if (this.ascLeftHb != null) {
/* 167 */       if (!Settings.isMobile) {
/* 168 */         this.ascLeftHb.move(Settings.WIDTH / 2.0F + 200.0F * Settings.scale - ASC_RIGHT_W * 0.25F, 70.0F * Settings.scale);
/* 169 */         this.ascRightHb.move(Settings.WIDTH / 2.0F + 200.0F * Settings.scale + ASC_RIGHT_W * 1.25F, 70.0F * Settings.scale);
/*     */       }
/*     */       else {
/*     */         
/* 173 */         this.ascLeftHb.move(Settings.WIDTH / 2.0F + 200.0F * Settings.scale - ASC_RIGHT_W * 0.25F, 100.0F * Settings.scale);
/*     */ 
/*     */         
/* 176 */         this.ascRightHb.move(Settings.WIDTH / 2.0F + 200.0F * Settings.scale + ASC_RIGHT_W * 1.25F, 100.0F * Settings.scale);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 182 */     this.anySelected = false;
/*     */     
/* 184 */     if (!this.seedPanel.shown) {
/* 185 */       for (CharacterOption o : this.options) {
/* 186 */         o.update();
/*     */         
/* 188 */         if (o.selected) {
/* 189 */           this.anySelected = true;
/* 190 */           this.isAscensionModeUnlocked = UnlockTracker.isAscensionUnlocked(o.c);
/* 191 */           if (!this.isAscensionModeUnlocked) {
/* 192 */             this.isAscensionMode = false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 197 */       updateButtons();
/*     */       
/* 199 */       if (InputHelper.justReleasedClickLeft && !this.anySelected) {
/* 200 */         this.confirmButton.isDisabled = true;
/* 201 */         this.confirmButton.hide();
/*     */       } 
/*     */       
/* 204 */       if (this.anySelected) {
/* 205 */         this.bgCharColor.a = MathHelper.fadeLerpSnap(this.bgCharColor.a, 1.0F);
/* 206 */         this.bg_y_offset = MathHelper.fadeLerpSnap(this.bg_y_offset, -0.0F);
/*     */       } else {
/* 208 */         this.bgCharColor.a = MathHelper.fadeLerpSnap(this.bgCharColor.a, 0.0F);
/*     */       } 
/*     */       
/* 211 */       updateAscensionToggle();
/*     */       
/* 213 */       if (this.anySelected) {
/* 214 */         this.seedHb.update();
/*     */       }
/*     */     } 
/* 217 */     this.seedPanel.update();
/*     */     
/* 219 */     if ((this.seedHb.hovered && InputHelper.justClickedLeft) || CInputActionSet.drawPile.isJustPressed()) {
/* 220 */       InputHelper.justClickedLeft = false;
/* 221 */       this.seedHb.hovered = false;
/* 222 */       this.seedPanel.show();
/*     */     } 
/*     */     
/* 225 */     CardCrawlGame.mainMenuScreen.superDarken = this.anySelected;
/*     */   }
/*     */   
/*     */   private void updateAscensionToggle() {
/* 229 */     if (this.isAscensionModeUnlocked) {
/* 230 */       if (this.anySelected) {
/* 231 */         this.ascensionModeHb.update();
/* 232 */         this.ascRightHb.update();
/* 233 */         this.ascLeftHb.update();
/*     */       } 
/*     */       
/* 236 */       if (InputHelper.justClickedLeft) {
/* 237 */         if (this.ascensionModeHb.hovered) {
/* 238 */           this.ascensionModeHb.clickStarted = true;
/* 239 */         } else if (this.ascRightHb.hovered) {
/* 240 */           this.ascRightHb.clickStarted = true;
/* 241 */         } else if (this.ascLeftHb.hovered) {
/* 242 */           this.ascLeftHb.clickStarted = true;
/*     */         } 
/*     */       }
/*     */       
/* 246 */       if (this.ascensionModeHb.clicked || CInputActionSet.proceed.isJustPressed()) {
/* 247 */         this.ascensionModeHb.clicked = false;
/* 248 */         this.isAscensionMode = !this.isAscensionMode;
/* 249 */         Settings.gamePref.putBoolean("Ascension Mode Default", this.isAscensionMode);
/* 250 */         Settings.gamePref.flush();
/*     */       } 
/*     */       
/* 253 */       if (this.ascLeftHb.clicked || CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/* 254 */         this.ascLeftHb.clicked = false;
/* 255 */         for (CharacterOption o : this.options) {
/* 256 */           if (o.selected) {
/* 257 */             o.decrementAscensionLevel(this.ascensionLevel - 1);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 263 */       if (this.ascRightHb.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/* 264 */         this.ascRightHb.clicked = false;
/* 265 */         for (CharacterOption o : this.options) {
/* 266 */           if (o.selected) {
/* 267 */             o.incrementAscensionLevel(this.ascensionLevel + 1);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void justSelected() {
/* 276 */     this.bg_y_offset = 0.0F;
/*     */   }
/*     */   
/*     */   public void updateButtons() {
/* 280 */     this.cancelButton.update();
/* 281 */     this.confirmButton.update();
/*     */     
/* 283 */     if (this.cancelButton.hb.clicked || InputHelper.pressedEscape) {
/* 284 */       CardCrawlGame.mainMenuScreen.superDarken = false;
/* 285 */       InputHelper.pressedEscape = false;
/* 286 */       this.cancelButton.hb.clicked = false;
/* 287 */       this.cancelButton.hide();
/* 288 */       CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/* 289 */       for (CharacterOption o : this.options) {
/* 290 */         o.selected = false;
/*     */       }
/* 292 */       this.bgCharColor.a = 0.0F;
/* 293 */       this.anySelected = false;
/*     */     } 
/*     */ 
/*     */     
/* 297 */     if (this.confirmButton.hb.clicked) {
/* 298 */       this.confirmButton.hb.clicked = false;
/* 299 */       this.confirmButton.isDisabled = true;
/* 300 */       this.confirmButton.hide();
/*     */       
/* 302 */       if (Settings.seed == null) {
/* 303 */         setRandomSeed();
/*     */       } else {
/* 305 */         Settings.seedSet = true;
/*     */       } 
/*     */       
/* 308 */       CardCrawlGame.mainMenuScreen.isFadingOut = true;
/* 309 */       CardCrawlGame.mainMenuScreen.fadeOutMusic();
/*     */       
/* 311 */       Settings.isDailyRun = false;
/* 312 */       boolean isTrialSeed = TrialHelper.isTrialSeed(SeedHelper.getString(Settings.seed.longValue()));
/*     */       
/* 314 */       if (isTrialSeed) {
/* 315 */         Settings.specialSeed = Settings.seed;
/* 316 */         long sourceTime = System.nanoTime();
/* 317 */         Random rng = new Random(Long.valueOf(sourceTime));
/* 318 */         Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(rng));
/* 319 */         Settings.isTrial = true;
/*     */       } 
/*     */       
/* 322 */       ModHelper.setModsFalse();
/* 323 */       AbstractDungeon.generateSeeds();
/*     */       
/* 325 */       AbstractDungeon.isAscensionMode = this.isAscensionMode;
/* 326 */       if (this.isAscensionMode) {
/* 327 */         AbstractDungeon.ascensionLevel = this.ascensionLevel;
/*     */       } else {
/* 329 */         AbstractDungeon.ascensionLevel = 0;
/*     */       } 
/*     */       
/* 332 */       this.confirmButton.hb.clicked = false;
/* 333 */       this.confirmButton.hide();
/*     */       
/* 335 */       CharacterOption selected = null;
/* 336 */       for (CharacterOption o : this.options) {
/* 337 */         if (o.selected) {
/* 338 */           selected = o;
/*     */         }
/*     */       } 
/* 341 */       if (selected != null && 
/* 342 */         CardCrawlGame.steelSeries.isEnabled.booleanValue()) {
/* 343 */         CardCrawlGame.steelSeries.event_character_chosen(selected.c.chosenClass);
/*     */       }
/*     */ 
/*     */       
/* 347 */       if (Settings.isDemo || Settings.isPublisherBuild) {
/* 348 */         BotDataUploader poster = new BotDataUploader();
/* 349 */         poster.setValues(BotDataUploader.GameDataType.DEMO_EMBARK, null, null);
/* 350 */         Thread t = new Thread((Runnable)poster);
/* 351 */         t.setName("LeaderboardPoster");
/* 352 */         t.run();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 358 */     sb.setColor(this.bgCharColor);
/* 359 */     if (this.bgCharImg != null)
/*     */     {
/* 361 */       if (Settings.isSixteenByTen) {
/* 362 */         sb.draw(this.bgCharImg, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 600.0F, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1200, false, false);
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
/*     */       }
/* 379 */       else if (Settings.isFourByThree) {
/* 380 */         sb.draw(this.bgCharImg, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 600.0F + this.bg_y_offset, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.yScale, Settings.yScale, 0.0F, 0, 0, 1920, 1200, false, false);
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
/*     */       }
/* 397 */       else if (Settings.isLetterbox) {
/* 398 */         sb.draw(this.bgCharImg, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 600.0F + this.bg_y_offset, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.xScale, Settings.xScale, 0.0F, 0, 0, 1920, 1200, false, false);
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 416 */         sb.draw(this.bgCharImg, Settings.WIDTH / 2.0F - 960.0F, Settings.HEIGHT / 2.0F - 600.0F + this.bg_y_offset, 960.0F, 600.0F, 1920.0F, 1200.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1200, false, false);
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
/*     */ 
/*     */     
/* 436 */     this.cancelButton.render(sb);
/* 437 */     this.confirmButton.render(sb);
/* 438 */     renderAscensionMode(sb);
/* 439 */     renderSeedSettings(sb);
/* 440 */     this.seedPanel.render(sb);
/*     */     
/* 442 */     boolean anythingSelected = false;
/*     */     
/* 444 */     if (!this.seedPanel.shown) {
/* 445 */       for (CharacterOption o : this.options) {
/* 446 */         if (o.selected) {
/* 447 */           anythingSelected = true;
/*     */         }
/* 449 */         o.render(sb);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 454 */     if (!this.seedPanel.shown && 
/* 455 */       !anythingSelected) {
/* 456 */       if (!Settings.isMobile) {
/* 457 */         FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, CHOOSE_CHAR_MSG, Settings.WIDTH / 2.0F, 340.0F * Settings.yScale, Settings.CREAM_COLOR, 1.2F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 466 */         FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, CHOOSE_CHAR_MSG, Settings.WIDTH / 2.0F, 380.0F * Settings.yScale, Settings.CREAM_COLOR, 1.2F);
/*     */       } 
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
/*     */   private void renderSeedSettings(SpriteBatch sb) {
/* 480 */     if (!this.anySelected) {
/*     */       return;
/*     */     }
/*     */     
/* 484 */     Color textColor = Settings.GOLD_COLOR;
/*     */     
/* 486 */     if (this.seedHb.hovered) {
/* 487 */       textColor = Settings.GREEN_TEXT_COLOR;
/* 488 */       TipHelper.renderGenericTip(InputHelper.mX + 50.0F * Settings.scale, InputHelper.mY + 100.0F * Settings.scale, TEXT[11], TEXT[12]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 495 */     if (!Settings.isControllerMode) {
/* 496 */       if (Settings.seedSet) {
/* 497 */         FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, TEXT[10], SEED_X, SEED_Y, 9999.0F, 0.0F, textColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 507 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.cardTitleFont, 
/*     */ 
/*     */             
/* 510 */             SeedHelper.getUserFacingSeedString(), SEED_X - 30.0F * Settings.scale + 
/* 511 */             FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[13], 9999.0F, 0.0F), 90.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 519 */         FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, TEXT[13], SEED_X, SEED_Y, 9999.0F, 0.0F, textColor);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 530 */       if (Settings.seedSet) {
/* 531 */         FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, TEXT[10], SEED_X + 100.0F * Settings.scale, SEED_Y, 9999.0F, 0.0F, textColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 540 */         FontHelper.renderFontLeftTopAligned(sb, FontHelper.cardTitleFont, 
/*     */ 
/*     */             
/* 543 */             SeedHelper.getUserFacingSeedString(), SEED_X - 30.0F * Settings.scale + 
/* 544 */             FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[13], 9999.0F, 0.0F) + 100.0F * Settings.scale, 90.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 552 */         FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, TEXT[13], SEED_X + 100.0F * Settings.scale, SEED_Y, 9999.0F, 0.0F, textColor);
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
/* 563 */       sb.draw(ImageMaster.CONTROLLER_LT, 80.0F * Settings.scale - 32.0F, 80.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */     
/* 582 */     this.seedHb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderAscensionMode(SpriteBatch sb) {
/* 586 */     if (!this.anySelected) {
/*     */       return;
/*     */     }
/*     */     
/* 590 */     if (this.isAscensionModeUnlocked) {
/* 591 */       if (!Settings.isMobile) {
/* 592 */         sb.draw(ImageMaster.OPTION_TOGGLE, Settings.WIDTH / 2.0F - ASC_LEFT_W - 16.0F - 30.0F * Settings.scale, this.ascensionModeHb.cY - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 32, 32, false, false);
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 610 */         sb.draw(ImageMaster.CHECKBOX, Settings.WIDTH / 2.0F - ASC_LEFT_W - 36.0F * Settings.scale - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.9F, Settings.scale * 0.9F, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */       
/* 629 */       if (this.ascensionModeHb.hovered) {
/* 630 */         FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[6], Settings.WIDTH / 2.0F - ASC_LEFT_W / 2.0F, this.ascensionModeHb.cY, Settings.GREEN_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 638 */         TipHelper.renderGenericTip(InputHelper.mX - 140.0F * Settings.scale, InputHelper.mY + 340.0F * Settings.scale, TEXT[8], TEXT[9]);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 644 */         FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[6], Settings.WIDTH / 2.0F - ASC_LEFT_W / 2.0F, this.ascensionModeHb.cY, Settings.GOLD_COLOR);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 653 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[7] + this.ascensionLevel, Settings.WIDTH / 2.0F + ASC_RIGHT_W / 2.0F + 200.0F * Settings.scale, this.ascensionModeHb.cY, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 661 */       if (this.isAscensionMode) {
/* 662 */         sb.setColor(Color.WHITE);
/* 663 */         if (!Settings.isMobile) {
/* 664 */           sb.draw(ImageMaster.OPTION_TOGGLE_ON, Settings.WIDTH / 2.0F - ASC_LEFT_W - 16.0F - 30.0F * Settings.scale, this.ascensionModeHb.cY - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 32, 32, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 682 */           sb.draw(ImageMaster.TICK, Settings.WIDTH / 2.0F - ASC_LEFT_W - 36.0F * Settings.scale - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.9F, Settings.scale * 0.9F, 0.0F, 0, 0, 64, 64, false, false);
/*     */         } 
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
/* 701 */         if (Settings.isMobile) {
/* 702 */           FontHelper.renderFontCentered(sb, FontHelper.smallDialogOptionFont, this.ascLevelInfoString, Settings.WIDTH / 2.0F, 60.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */           
/* 710 */           FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.ascLevelInfoString, Settings.WIDTH / 2.0F, 35.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 721 */       if (this.ascLeftHb.hovered || Settings.isControllerMode) {
/* 722 */         sb.setColor(Color.WHITE);
/*     */       } else {
/* 724 */         sb.setColor(Color.LIGHT_GRAY);
/*     */       } 
/* 726 */       sb.draw(ImageMaster.CF_LEFT_ARROW, this.ascLeftHb.cX - 24.0F, this.ascLeftHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
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
/* 744 */       if (this.ascRightHb.hovered || Settings.isControllerMode) {
/* 745 */         sb.setColor(Color.WHITE);
/*     */       } else {
/* 747 */         sb.setColor(Color.LIGHT_GRAY);
/*     */       } 
/*     */       
/* 750 */       sb.draw(ImageMaster.CF_RIGHT_ARROW, this.ascRightHb.cX - 24.0F, this.ascRightHb.cY - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);
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
/* 768 */       if (Settings.isControllerMode) {
/* 769 */         sb.draw(CInputActionSet.proceed
/* 770 */             .getKeyImg(), this.ascensionModeHb.cX - 100.0F * Settings.scale - 32.0F, this.ascensionModeHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 786 */         sb.draw(CInputActionSet.pageLeftViewDeck
/* 787 */             .getKeyImg(), this.ascLeftHb.cX - 60.0F * Settings.scale - 32.0F, this.ascLeftHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 803 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 804 */             .getKeyImg(), this.ascRightHb.cX + 60.0F * Settings.scale - 32.0F, this.ascRightHb.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 822 */       this.ascensionModeHb.render(sb);
/* 823 */       this.ascLeftHb.render(sb);
/* 824 */       this.ascRightHb.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deselectOtherOptions(CharacterOption characterOption) {
/* 829 */     for (CharacterOption o : this.options) {
/* 830 */       if (o != characterOption)
/* 831 */         o.selected = false; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\charSelect\CharacterSelectScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */