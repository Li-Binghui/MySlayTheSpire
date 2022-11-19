/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.InputProcessor;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.utils.Clipboard;
/*     */ import com.codedisaster.steamworks.SteamUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.SeedHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TypeHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.ScrollInputProcessor;
/*     */ import com.megacrit.cardcrawl.helpers.steamInput.SteamInputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ 
/*     */ public class SeedPanel
/*     */ {
/*  28 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SeedPanel");
/*  29 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public String title;
/*  32 */   public static String textField = "";
/*  33 */   public Hitbox yesHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale); public Hitbox noHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale); private static final int CONFIRM_W = 360;
/*     */   private static final int CONFIRM_H = 414;
/*     */   private static final int YES_W = 173;
/*     */   private static final int NO_W = 161;
/*     */   private static final int BUTTON_H = 74;
/*  38 */   private Color screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  39 */   private Color uiColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  40 */   private float animTimer = 0.0F; private float waitTimer = 0.0F;
/*     */   private static final float ANIM_TIME = 0.25F;
/*     */   public boolean shown = false;
/*     */   private static final float SCREEN_DARKNESS = 0.8F;
/*  44 */   public MainMenuScreen.CurScreen sourceScreen = null;
/*     */   
/*     */   public void update() {
/*  47 */     if (Gdx.input.isKeyPressed(67) && !textField.equals("") && this.waitTimer <= 0.0F) {
/*  48 */       textField = textField.substring(0, textField.length() - 1);
/*  49 */       this.waitTimer = 0.09F;
/*     */     } 
/*  51 */     if (this.waitTimer > 0.0F) {
/*  52 */       this.waitTimer -= Gdx.graphics.getDeltaTime();
/*     */     }
/*  54 */     if (this.shown) {
/*  55 */       if (this.animTimer != 0.0F) {
/*  56 */         this.animTimer -= Gdx.graphics.getDeltaTime();
/*  57 */         if (this.animTimer < 0.0F) {
/*  58 */           this.animTimer = 0.0F;
/*     */         }
/*  60 */         this.screenColor.a = Interpolation.fade.apply(0.8F, 0.0F, this.animTimer * 1.0F / 0.25F);
/*  61 */         this.uiColor.a = Interpolation.fade.apply(1.0F, 0.0F, this.animTimer * 1.0F / 0.25F);
/*     */       } else {
/*  63 */         updateYes();
/*  64 */         updateNo();
/*     */ 
/*     */         
/*  67 */         if (InputActionSet.confirm.isJustPressed()) {
/*  68 */           confirm();
/*  69 */         } else if (InputHelper.pressedEscape) {
/*  70 */           InputHelper.pressedEscape = false;
/*  71 */           cancel();
/*  72 */         } else if (InputHelper.isPasteJustPressed()) {
/*  73 */           Clipboard clipBoard = Gdx.app.getClipboard();
/*  74 */           String pasteText = clipBoard.getContents();
/*  75 */           String seedText = SeedHelper.sterilizeString(pasteText);
/*  76 */           if (!seedText.isEmpty()) {
/*  77 */             textField = seedText;
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*  82 */     } else if (this.animTimer != 0.0F) {
/*  83 */       this.animTimer -= Gdx.graphics.getDeltaTime();
/*  84 */       if (this.animTimer < 0.0F) {
/*  85 */         this.animTimer = 0.0F;
/*     */       }
/*  87 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 0.8F, this.animTimer * 1.0F / 0.25F);
/*  88 */       this.uiColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.animTimer * 1.0F / 0.25F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateYes() {
/*  94 */     this.yesHb.update();
/*  95 */     if (this.yesHb.justHovered) {
/*  96 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  99 */     if (InputHelper.justClickedLeft && this.yesHb.hovered) {
/* 100 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 101 */       this.yesHb.clickStarted = true;
/*     */     } 
/*     */     
/* 104 */     if (CInputActionSet.proceed.isJustPressed()) {
/* 105 */       CInputActionSet.proceed.unpress();
/* 106 */       this.yesHb.clicked = true;
/*     */     } 
/*     */     
/* 109 */     if (this.yesHb.clicked) {
/* 110 */       this.yesHb.clicked = false;
/* 111 */       confirm();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateNo() {
/* 116 */     this.noHb.update();
/* 117 */     if (this.noHb.justHovered) {
/* 118 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/* 121 */     if (InputHelper.justClickedLeft && this.noHb.hovered) {
/* 122 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 123 */       this.noHb.clickStarted = true;
/*     */     } 
/*     */     
/* 126 */     if (CInputActionSet.cancel.isJustPressed()) {
/* 127 */       this.noHb.clicked = true;
/*     */     }
/*     */     
/* 130 */     if (this.noHb.clicked) {
/* 131 */       this.noHb.clicked = false;
/* 132 */       cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void show() {
/* 137 */     Gdx.input.setInputProcessor((InputProcessor)new TypeHelper(true));
/*     */     
/* 139 */     if (SteamInputHelper.numControllers == 1 && CardCrawlGame.clientUtils != null && CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck()) {
/* 140 */       CardCrawlGame.clientUtils.showFloatingGamepadTextInput(SteamUtils.FloatingGamepadTextInputMode.ModeSingleLine, 0, 0, Settings.WIDTH, (int)(Settings.HEIGHT * 0.25F));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.yesHb.move(860.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale);
/* 149 */     this.noHb.move(1062.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale);
/* 150 */     this.shown = true;
/* 151 */     this.animTimer = 0.25F;
/* 152 */     textField = SeedHelper.getUserFacingSeedString();
/*     */   }
/*     */   
/*     */   public void show(MainMenuScreen.CurScreen sourceScreen) {
/* 156 */     show();
/* 157 */     this.sourceScreen = sourceScreen;
/*     */   }
/*     */   
/*     */   public void confirm() {
/* 161 */     textField = textField.trim();
/*     */     try {
/* 163 */       SeedHelper.setSeed(textField);
/* 164 */     } catch (NumberFormatException e) {
/*     */       
/* 166 */       Settings.seed = Long.valueOf(Long.MAX_VALUE);
/*     */     } 
/* 168 */     close();
/*     */   }
/*     */   
/*     */   public void cancel() {
/* 172 */     close();
/*     */   }
/*     */   
/*     */   public void close() {
/* 176 */     this.yesHb.move(-1000.0F, -1000.0F);
/* 177 */     this.noHb.move(-1000.0F, -1000.0F);
/* 178 */     this.shown = false;
/* 179 */     this.animTimer = 0.25F;
/* 180 */     Gdx.input.setInputProcessor((InputProcessor)new ScrollInputProcessor());
/*     */     
/* 182 */     if (this.sourceScreen == null) {
/* 183 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CHAR_SELECT;
/*     */     } else {
/* 185 */       CardCrawlGame.mainMenuScreen.screen = this.sourceScreen;
/* 186 */       this.sourceScreen = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isFull() {
/* 191 */     return (textField.length() >= SeedHelper.SEED_DEFAULT_LENGTH);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 195 */     sb.setColor(this.screenColor);
/* 196 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 198 */     sb.setColor(this.uiColor);
/* 199 */     sb.draw(ImageMaster.OPTION_CONFIRM, Settings.WIDTH / 2.0F - 180.0F, Settings.OPTION_Y - 207.0F, 180.0F, 207.0F, 360.0F, 414.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 360, 414, false, false);
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
/*     */     
/* 218 */     sb.draw(ImageMaster.RENAME_BOX, Settings.WIDTH / 2.0F - 160.0F, Settings.OPTION_Y - 160.0F, 160.0F, 160.0F, 320.0F, 320.0F, Settings.scale * 1.1F, Settings.scale * 1.1F, 0.0F, 0, 0, 320, 320, false, false);
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
/* 236 */     FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, textField, Settings.WIDTH / 2.0F - 120.0F * Settings.scale, Settings.OPTION_Y + 4.0F * Settings.scale, 100000.0F, 0.0F, this.uiColor, 0.82F);
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
/* 248 */     if (!isFull()) {
/* 249 */       float tmpAlpha = (MathUtils.cosDeg((float)(System.currentTimeMillis() / 3L % 360L)) + 1.25F) / 3.0F * this.uiColor.a;
/* 250 */       FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, "_", Settings.WIDTH / 2.0F - 122.0F * Settings.scale + 
/*     */ 
/*     */ 
/*     */           
/* 254 */           FontHelper.getSmartWidth(FontHelper.cardTitleFont, textField, 1000000.0F, 0.0F, 0.82F), Settings.OPTION_Y + 4.0F * Settings.scale, 100000.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, tmpAlpha), 0.82F);
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
/* 267 */     Color c = Settings.GOLD_COLOR.cpy();
/* 268 */     c.a = this.uiColor.a;
/* 269 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[1], Settings.WIDTH / 2.0F, Settings.OPTION_Y + 126.0F * Settings.scale, c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     if (this.yesHb.clickStarted) {
/* 279 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.9F));
/* 280 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 297 */       sb.setColor(new Color(this.uiColor));
/*     */     } else {
/* 299 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 318 */     if (!this.yesHb.clickStarted && this.yesHb.hovered) {
/* 319 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 320 */       sb.setBlendFunction(770, 1);
/* 321 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 338 */       sb.setBlendFunction(770, 771);
/* 339 */       sb.setColor(this.uiColor);
/*     */     } 
/*     */     
/* 342 */     if (this.yesHb.clickStarted) {
/* 343 */       c = Color.LIGHT_GRAY.cpy();
/* 344 */     } else if (this.yesHb.hovered) {
/* 345 */       c = Settings.CREAM_COLOR.cpy();
/*     */     } else {
/* 347 */       c = Settings.GOLD_COLOR.cpy();
/*     */     } 
/* 349 */     c.a = this.uiColor.a;
/* 350 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[2], Settings.WIDTH / 2.0F - 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, c, 0.82F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 360 */     sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
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
/* 378 */     if (!this.noHb.clickStarted && this.noHb.hovered) {
/* 379 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 380 */       sb.setBlendFunction(770, 1);
/* 381 */       sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
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
/* 398 */       sb.setBlendFunction(770, 771);
/* 399 */       sb.setColor(this.uiColor);
/*     */     } 
/*     */     
/* 402 */     if (this.noHb.clickStarted) {
/* 403 */       c = Color.LIGHT_GRAY.cpy();
/* 404 */     } else if (this.noHb.hovered) {
/* 405 */       c = Settings.CREAM_COLOR.cpy();
/*     */     } else {
/* 407 */       c = Settings.GOLD_COLOR.cpy();
/*     */     } 
/* 409 */     c.a = this.uiColor.a;
/* 410 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[3], Settings.WIDTH / 2.0F + 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, c, 0.82F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 419 */     if (this.shown) {
/* 420 */       if (Settings.isControllerMode) {
/* 421 */         sb.draw(CInputActionSet.proceed
/* 422 */             .getKeyImg(), 770.0F * Settings.scale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 438 */         sb.draw(CInputActionSet.cancel
/* 439 */             .getKeyImg(), 1150.0F * Settings.scale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 456 */       this.yesHb.render(sb);
/* 457 */       this.noHb.render(sb);
/*     */       
/* 459 */       if (!Settings.usesTrophies) {
/* 460 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[4], Settings.WIDTH / 2.0F, 100.0F * Settings.scale, new Color(1.0F, 0.3F, 0.3F, c.a));
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 468 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[5], Settings.WIDTH / 2.0F, 100.0F * Settings.scale, new Color(1.0F, 0.3F, 0.3F, c.a));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\SeedPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */