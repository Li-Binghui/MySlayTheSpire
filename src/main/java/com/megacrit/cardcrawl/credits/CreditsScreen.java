/*     */ package com.megacrit.cardcrawl.credits;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CreditStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.DoorUnlockScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class CreditsScreen
/*     */ {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CreditsScreen");
/*     */   
/*  27 */   private Color screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  28 */   private float fadeInTimer = 2.0F;
/*     */   private float targetY;
/*  30 */   private static final float scrollSpeed = 50.0F * Settings.scale; private float currentY;
/*     */   private static final int W = 720;
/*  32 */   private ArrayList<CreditLine> lines = new ArrayList<>(); private static final float LINE_SPACING = 45.0F;
/*     */   private static final float SECTION_SPACING = 150.0F;
/*  34 */   private static final float SCROLL_START_Y = 400.0F * Settings.scale;
/*     */   private static final float THANK_YOU_TIME = 3.0F;
/*  36 */   private float thankYouTimer = 3.0F;
/*  37 */   private Color thankYouColor = Settings.CREAM_COLOR.cpy();
/*  38 */   private static float END_OF_CREDITS_Y = 15000.0F * Settings.scale;
/*  39 */   private String THANKS_MSG = (CardCrawlGame.languagePack.getCreditString("THANKS_MSG")).HEADER;
/*  40 */   private static Texture logoImg = null;
/*     */   private float skipTimer;
/*     */   private static final float SKIP_MENU_UP_DUR = 2.0F;
/*     */   private static final float SKIP_APPEAR_TIME = 0.5F;
/*     */   private boolean isSkippable;
/*     */   private boolean closingSkipMenu;
/*     */   private boolean showNeowAfter = false;
/*  47 */   private static final float SKIP_START_X = -300.0F * Settings.scale; private static final float SKIP_END_X = 50.0F * Settings.scale;
/*     */   private float skipX;
/*  49 */   private float tmpY = -400.0F;
/*     */   
/*     */   public CreditsScreen() {
/*  52 */     this.currentY = SCROLL_START_Y;
/*  53 */     this.targetY = this.currentY;
/*  54 */     creditLineHelper("DEV");
/*  55 */     creditLineHelper("OPS");
/*  56 */     creditLineHelper("SOUND");
/*  57 */     creditLineHelper("VOICE");
/*  58 */     creditLineHelper("PORTRAITS");
/*  59 */     creditLineHelper("ILLUSTRATION");
/*  60 */     creditLineHelper("ANIMATION");
/*     */ 
/*     */     
/*  63 */     if (Settings.isConsoleBuild) {
/*  64 */       creditLineHelper("PORTING");
/*  65 */       creditLineHelper("PUBLISHING");
/*  66 */       creditLineHelper("KAKEHASHI");
/*  67 */       END_OF_CREDITS_Y += 1600.0F * Settings.scale;
/*     */     } 
/*     */     
/*  70 */     creditLineHelper("LOC_ZHS");
/*  71 */     creditLineHelper("LOC_ZHT");
/*  72 */     creditLineHelper("LOC_DEU");
/*  73 */     creditLineHelper("LOC_DUT");
/*  74 */     creditLineHelper("LOC_EPO");
/*  75 */     creditLineHelper("LOC_FIN");
/*  76 */     creditLineHelper("LOC_FRA");
/*  77 */     creditLineHelper("LOC_GRE");
/*  78 */     creditLineHelper("LOC_IND");
/*  79 */     creditLineHelper("LOC_ITA");
/*  80 */     creditLineHelper("LOC_JPN");
/*  81 */     creditLineHelper("LOC_KOR");
/*  82 */     creditLineHelper("LOC_NOR");
/*  83 */     creditLineHelper("LOC_POL");
/*  84 */     creditLineHelper("LOC_PTB");
/*  85 */     creditLineHelper("LOC_RUS");
/*  86 */     creditLineHelper("LOC_SPA");
/*  87 */     creditLineHelper("LOC_SRB");
/*  88 */     creditLineHelper("LOC_THA");
/*  89 */     creditLineHelper("LOC_TUR");
/*  90 */     creditLineHelper("LOC_UKR");
/*  91 */     creditLineHelper("LOC_VIE");
/*  92 */     creditLineHelper("LOC_ADDITIONAL");
/*  93 */     creditLineHelper("TEST");
/*  94 */     creditLineHelper("SPECIAL");
/*     */     
/*  96 */     if (logoImg == null) {
/*  97 */       switch (Settings.language) {
/*     */       
/*     */       } 
/*     */ 
/*     */       
/* 102 */       logoImg = ImageMaster.loadImage("images/ui/credits_logo/eng.png");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void creditLineHelper(String id) {
/* 109 */     CreditStrings str = CardCrawlGame.languagePack.getCreditString(id);
/*     */     
/* 111 */     this.lines.add(new CreditLine(str.HEADER, this.tmpY -= 150.0F, true));
/* 112 */     for (int i = 0; i < str.NAMES.length; i++) {
/* 113 */       this.lines.add(new CreditLine(str.NAMES[i], this.tmpY -= 45.0F, false));
/*     */     }
/*     */   }
/*     */   
/*     */   public void open(boolean playCreditsBgm) {
/* 118 */     if (playCreditsBgm) {
/* 119 */       this.showNeowAfter = true;
/* 120 */       CardCrawlGame.playCreditsBgm = false;
/* 121 */       CardCrawlGame.music.playTempBgmInstantly("CREDITS", true);
/*     */     } else {
/* 123 */       this.showNeowAfter = false;
/*     */     } 
/*     */     
/* 126 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CREDITS;
/* 127 */     this.skipTimer = 0.0F;
/* 128 */     this.isSkippable = false;
/* 129 */     this.closingSkipMenu = false;
/* 130 */     this.skipX = SKIP_START_X;
/* 131 */     GameCursor.hidden = true;
/* 132 */     this.thankYouColor.a = 0.0F;
/* 133 */     this.screenColor.a = 0.0F;
/* 134 */     this.thankYouTimer = 3.0F;
/* 135 */     CardCrawlGame.mainMenuScreen.darken();
/* 136 */     this.fadeInTimer = 2.0F;
/* 137 */     this.currentY = SCROLL_START_Y;
/* 138 */     this.targetY = this.currentY;
/*     */   }
/*     */   
/*     */   public void update() {
/* 142 */     if (InputHelper.pressedEscape) {
/* 143 */       InputHelper.pressedEscape = false;
/* 144 */       close();
/*     */     } 
/*     */     
/* 147 */     if (InputHelper.isMouseDown_R) {
/* 148 */       this.targetY -= Gdx.graphics.getDeltaTime() * scrollSpeed * 4.0F;
/*     */     } else {
/* 150 */       this.targetY += Gdx.graphics.getDeltaTime() * scrollSpeed;
/*     */ 
/*     */       
/* 153 */       if (this.currentY > END_OF_CREDITS_Y) {
/* 154 */         this.thankYouTimer -= Gdx.graphics.getDeltaTime();
/* 155 */         if (this.thankYouTimer < 0.0F) {
/* 156 */           this.thankYouTimer = 0.0F;
/*     */         }
/* 158 */         this.thankYouColor.a = Interpolation.fade.apply(1.0F, 0.0F, this.thankYouTimer / 3.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 162 */     if (this.thankYouColor.a == 0.0F) {
/* 163 */       if (Gdx.input.isKeyJustPressed(62)) {
/* 164 */         this.targetY = SCROLL_START_Y;
/*     */       }
/*     */       
/* 167 */       if (InputHelper.scrolledUp) {
/* 168 */         this.targetY -= 100.0F * Settings.scale;
/* 169 */       } else if (InputHelper.scrolledDown) {
/* 170 */         this.targetY += 100.0F * Settings.scale;
/*     */       } 
/*     */ 
/*     */       
/* 174 */       if (CInputActionSet.up.isJustPressed()) {
/* 175 */         this.targetY -= 300.0F * Settings.scale;
/* 176 */       } else if (CInputActionSet.down.isJustPressed()) {
/* 177 */         this.targetY += 300.0F * Settings.scale;
/* 178 */       } else if (CInputActionSet.inspectDown.isJustPressed()) {
/* 179 */         this.targetY -= 1000.0F * Settings.scale;
/* 180 */       } else if (CInputActionSet.inspectUp.isJustPressed()) {
/* 181 */         this.targetY += 1000.0F * Settings.scale;
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     this.currentY = MathHelper.scrollSnapLerpSpeed(this.currentY, this.targetY);
/*     */     
/* 187 */     updateFade();
/* 188 */     skipLogic();
/*     */   }
/*     */   
/*     */   private void skipLogic() {
/* 192 */     if (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) {
/* 193 */       if (this.isSkippable) {
/* 194 */         close();
/* 195 */       } else if (!this.isSkippable && this.skipTimer == 0.0F) {
/* 196 */         this.skipTimer = 0.5F;
/* 197 */         this.skipX = SKIP_END_X;
/*     */       } 
/*     */     }
/*     */     
/* 201 */     if (this.skipTimer != 0.0F) {
/* 202 */       this.skipTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/* 204 */       if (!this.isSkippable && !this.closingSkipMenu) {
/* 205 */         this.skipX = Interpolation.swingIn.apply(SKIP_END_X, SKIP_START_X, this.skipTimer * 2.0F);
/* 206 */       } else if (this.closingSkipMenu) {
/* 207 */         this.skipX = Interpolation.fade.apply(SKIP_START_X, SKIP_END_X, this.skipTimer * 2.0F);
/*     */       } else {
/* 209 */         this.skipX = SKIP_END_X;
/*     */       } 
/*     */       
/* 212 */       if (this.skipTimer < 0.0F) {
/* 213 */         if (!this.isSkippable && !this.closingSkipMenu) {
/* 214 */           this.isSkippable = true;
/* 215 */           this.skipTimer = 2.0F;
/* 216 */         } else if (!this.closingSkipMenu) {
/* 217 */           this.closingSkipMenu = true;
/* 218 */           this.isSkippable = false;
/* 219 */           this.skipTimer = 0.5F;
/*     */         } else {
/* 221 */           this.isSkippable = false;
/* 222 */           this.closingSkipMenu = false;
/* 223 */           this.skipTimer = 0.0F;
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 230 */     if (this.showNeowAfter) {
/* 231 */       CardCrawlGame.music.justFadeOutTempBGM();
/* 232 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.NEOW_SCREEN;
/* 233 */       CardCrawlGame.mainMenuScreen.neowNarrateScreen.open();
/* 234 */     } else if (DoorUnlockScreen.show) {
/* 235 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.DOOR_UNLOCK;
/* 236 */       CardCrawlGame.mainMenuScreen.doorUnlockScreen.open(false);
/* 237 */     } else if (CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.CREDITS) {
/* 238 */       CardCrawlGame.mainMenuScreen.lighten();
/* 239 */       CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 240 */       CardCrawlGame.music.fadeOutTempBGM();
/* 241 */       GameCursor.hidden = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateFade() {
/* 246 */     this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/* 247 */     if (this.fadeInTimer < 0.0F) {
/* 248 */       this.fadeInTimer = 0.0F;
/*     */     }
/* 250 */     this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, this.fadeInTimer / 2.0F);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 254 */     sb.setColor(this.screenColor);
/* 255 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 257 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.screenColor.a));
/* 258 */     sb.draw(logoImg, Settings.WIDTH / 2.0F - 360.0F, this.currentY - 360.0F, 360.0F, 360.0F, 720.0F, 720.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 720, 720, false, false);
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
/* 276 */     for (CreditLine c : this.lines) {
/* 277 */       c.render(sb, this.currentY);
/*     */     }
/*     */     
/* 280 */     FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.THANKS_MSG, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, this.thankYouColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 289 */     if (Settings.isTouchScreen) {
/* 290 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uiStrings.TEXT[2], this.skipX, 50.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 297 */     else if (!Settings.isControllerMode) {
/* 298 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uiStrings.TEXT[0], this.skipX, 50.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 306 */       FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipBodyFont, uiStrings.TEXT[1], this.skipX + 46.0F * Settings.scale, 50.0F * Settings.scale, Settings.BLUE_TEXT_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 313 */       sb.setColor(Color.WHITE);
/* 314 */       sb.draw(CInputActionSet.select
/* 315 */           .getKeyImg(), this.skipX - 32.0F + 10.0F * Settings.scale, -32.0F + 44.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 0.9F, Settings.scale * 0.9F, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\credits\CreditsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */