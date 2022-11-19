/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.DoorUnlockScreen;
/*     */ 
/*     */ 
/*     */ public class MainMenuPanelButton
/*     */ {
/*  22 */   public Hitbox hb = new Hitbox(400.0F * Settings.scale, 700.0F * Settings.scale);
/*     */   private PanelClickResult result;
/*     */   public PanelColor pColor;
/*  25 */   private Color gColor = Settings.GOLD_COLOR.cpy();
/*  26 */   private Color cColor = Settings.CREAM_COLOR.cpy();
/*  27 */   private Color wColor = Color.WHITE.cpy();
/*  28 */   private Color grColor = Color.GRAY.cpy();
/*  29 */   private Texture portraitImg = null; private static final int W = 512; private static final int H = 800;
/*  30 */   private Texture panelImg = ImageMaster.MENU_PANEL_BG_BLUE; private static final int P_W = 317;
/*     */   private static final int P_H = 206;
/*  32 */   private String header = null; private String description = null; private float yMod; private float animTimer;
/*     */   private float animTime;
/*  34 */   private static final float START_Y = -100.0F * Settings.scale;
/*  35 */   private float uiScale = 1.0F;
/*     */   
/*     */   public enum PanelClickResult {
/*  38 */     PLAY_NORMAL, PLAY_DAILY, PLAY_CUSTOM, INFO_CARD, INFO_RELIC, INFO_POTION, STAT_CHAR, STAT_LEADERBOARDS, STAT_HISTORY, SETTINGS_GAME, SETTINGS_INPUT, SETTINGS_CREDITS;
/*     */   }
/*     */   
/*     */   public enum PanelColor {
/*  42 */     RED, BLUE, BEIGE, GRAY;
/*     */   }
/*     */   
/*     */   public MainMenuPanelButton(PanelClickResult setResult, PanelColor setColor, float x, float y) {
/*  46 */     this.result = setResult;
/*  47 */     this.pColor = setColor;
/*  48 */     this.hb.move(x, y);
/*  49 */     setLabel();
/*  50 */     this.animTime = MathUtils.random(0.2F, 0.35F);
/*  51 */     this.animTimer = this.animTime;
/*     */   }
/*     */   
/*     */   private void setLabel() {
/*  55 */     this.panelImg = ImageMaster.MENU_PANEL_BG_BEIGE;
/*  56 */     switch (this.result) {
/*     */       case PLAY_CUSTOM:
/*  58 */         this.header = MenuPanelScreen.TEXT[39];
/*  59 */         if (this.pColor == PanelColor.GRAY) {
/*  60 */           this.description = MenuPanelScreen.TEXT[37];
/*  61 */           this.panelImg = ImageMaster.MENU_PANEL_BG_GRAY;
/*     */         } else {
/*  63 */           this.description = MenuPanelScreen.TEXT[40];
/*  64 */           this.panelImg = ImageMaster.MENU_PANEL_BG_RED;
/*     */         } 
/*  66 */         this.portraitImg = ImageMaster.P_LOOP;
/*     */         break;
/*     */       case PLAY_DAILY:
/*  69 */         this.header = MenuPanelScreen.TEXT[3];
/*  70 */         this.description = MenuPanelScreen.TEXT[5];
/*  71 */         this.portraitImg = ImageMaster.P_DAILY;
/*  72 */         if (this.pColor == PanelColor.GRAY) {
/*  73 */           this.panelImg = ImageMaster.MENU_PANEL_BG_GRAY; break;
/*     */         } 
/*  75 */         this.panelImg = ImageMaster.MENU_PANEL_BG_BLUE;
/*     */         break;
/*     */       
/*     */       case PLAY_NORMAL:
/*  79 */         this.header = MenuPanelScreen.TEXT[0];
/*  80 */         this.description = MenuPanelScreen.TEXT[2];
/*  81 */         this.portraitImg = ImageMaster.P_STANDARD;
/*     */         break;
/*     */       case INFO_CARD:
/*  84 */         this.header = MenuPanelScreen.TEXT[9];
/*  85 */         this.description = MenuPanelScreen.TEXT[11];
/*  86 */         this.portraitImg = ImageMaster.P_INFO_CARD;
/*     */         break;
/*     */       case INFO_RELIC:
/*  89 */         this.header = MenuPanelScreen.TEXT[12];
/*  90 */         this.description = MenuPanelScreen.TEXT[14];
/*  91 */         this.portraitImg = ImageMaster.P_INFO_RELIC;
/*  92 */         this.panelImg = ImageMaster.MENU_PANEL_BG_BLUE;
/*     */         break;
/*     */       case INFO_POTION:
/*  95 */         this.header = MenuPanelScreen.TEXT[43];
/*  96 */         this.description = MenuPanelScreen.TEXT[44];
/*  97 */         this.portraitImg = ImageMaster.P_INFO_POTION;
/*  98 */         this.panelImg = ImageMaster.MENU_PANEL_BG_RED;
/*     */         break;
/*     */       case STAT_CHAR:
/* 101 */         this.header = MenuPanelScreen.TEXT[18];
/* 102 */         this.description = MenuPanelScreen.TEXT[20];
/* 103 */         this.portraitImg = ImageMaster.P_STAT_CHAR;
/*     */         break;
/*     */       case STAT_HISTORY:
/* 106 */         this.header = MenuPanelScreen.TEXT[24];
/* 107 */         this.description = MenuPanelScreen.TEXT[26];
/* 108 */         this.portraitImg = ImageMaster.P_STAT_HISTORY;
/* 109 */         this.panelImg = ImageMaster.MENU_PANEL_BG_RED;
/*     */         break;
/*     */       case STAT_LEADERBOARDS:
/* 112 */         this.header = MenuPanelScreen.TEXT[21];
/* 113 */         this.description = MenuPanelScreen.TEXT[23];
/* 114 */         this.portraitImg = ImageMaster.P_STAT_LEADERBOARD;
/* 115 */         this.panelImg = ImageMaster.MENU_PANEL_BG_BLUE;
/*     */         break;
/*     */       case SETTINGS_CREDITS:
/* 118 */         this.header = MenuPanelScreen.TEXT[33];
/* 119 */         this.description = MenuPanelScreen.TEXT[35];
/* 120 */         this.portraitImg = ImageMaster.P_SETTING_CREDITS;
/* 121 */         this.panelImg = ImageMaster.MENU_PANEL_BG_RED;
/*     */         break;
/*     */       case SETTINGS_GAME:
/* 124 */         this.header = MenuPanelScreen.TEXT[27];
/* 125 */         if (!Settings.isConsoleBuild) {
/* 126 */           this.description = MenuPanelScreen.TEXT[29];
/*     */         } else {
/* 128 */           this.description = MenuPanelScreen.TEXT[42];
/*     */         } 
/* 130 */         this.portraitImg = ImageMaster.P_SETTING_GAME;
/*     */         break;
/*     */       case SETTINGS_INPUT:
/* 133 */         this.header = MenuPanelScreen.TEXT[30];
/* 134 */         if (!Settings.isConsoleBuild) {
/* 135 */           this.description = MenuPanelScreen.TEXT[32];
/*     */         } else {
/* 137 */           this.description = MenuPanelScreen.TEXT[41];
/*     */         } 
/* 139 */         this.portraitImg = ImageMaster.P_SETTING_INPUT;
/* 140 */         this.panelImg = ImageMaster.MENU_PANEL_BG_BLUE;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 148 */     if (this.pColor != PanelColor.GRAY) {
/* 149 */       this.hb.update();
/*     */     }
/*     */     
/* 152 */     if (this.hb.justHovered) {
/* 153 */       CardCrawlGame.sound.playV("UI_HOVER", 0.5F);
/*     */     }
/*     */     
/* 156 */     if (this.hb.hovered) {
/* 157 */       this.uiScale = MathHelper.fadeLerpSnap(this.uiScale, 1.025F);
/* 158 */       if (InputHelper.justClickedLeft) {
/* 159 */         this.hb.clickStarted = true;
/*     */       }
/*     */     } else {
/* 162 */       this.uiScale = MathHelper.cardScaleLerpSnap(this.uiScale, 1.0F);
/*     */     } 
/*     */     
/* 165 */     if (this.hb.hovered && CInputActionSet.select.isJustPressed()) {
/* 166 */       this.hb.clicked = true;
/*     */     }
/*     */     
/* 169 */     if (this.hb.clicked) {
/* 170 */       this.hb.clicked = false;
/* 171 */       CardCrawlGame.sound.play("DECK_OPEN");
/* 172 */       CardCrawlGame.mainMenuScreen.panelScreen.hide();
/* 173 */       buttonEffect();
/*     */     } 
/*     */     
/* 176 */     animatePanelIn();
/*     */   }
/*     */   
/*     */   private void animatePanelIn() {
/* 180 */     this.animTimer -= Gdx.graphics.getDeltaTime();
/* 181 */     if (this.animTimer < 0.0F) {
/* 182 */       this.animTimer = 0.0F;
/*     */     }
/* 184 */     this.yMod = Interpolation.swingIn.apply(0.0F, START_Y, this.animTimer / this.animTime);
/* 185 */     this.wColor.a = 1.0F - this.animTimer / this.animTime;
/* 186 */     this.cColor.a = this.wColor.a;
/* 187 */     this.gColor.a = this.wColor.a;
/* 188 */     this.grColor.a = this.wColor.a;
/*     */   }
/*     */   
/*     */   private void buttonEffect() {
/* 192 */     switch (this.result) {
/*     */       case INFO_CARD:
/* 194 */         CardCrawlGame.mainMenuScreen.cardLibraryScreen.open();
/*     */         break;
/*     */       case INFO_RELIC:
/* 197 */         CardCrawlGame.mainMenuScreen.relicScreen.open();
/*     */         break;
/*     */       case INFO_POTION:
/* 200 */         CardCrawlGame.mainMenuScreen.potionScreen.open();
/*     */         break;
/*     */       case PLAY_CUSTOM:
/* 203 */         CardCrawlGame.mainMenuScreen.customModeScreen.open();
/*     */         break;
/*     */       case PLAY_DAILY:
/* 206 */         CardCrawlGame.mainMenuScreen.dailyScreen.open();
/*     */         break;
/*     */       case PLAY_NORMAL:
/* 209 */         CardCrawlGame.mainMenuScreen.charSelectScreen.open(false);
/*     */         break;
/*     */       case SETTINGS_CREDITS:
/* 212 */         DoorUnlockScreen.show = false;
/* 213 */         CardCrawlGame.mainMenuScreen.creditsScreen.open(false);
/*     */         break;
/*     */       case SETTINGS_GAME:
/* 216 */         CardCrawlGame.sound.play("END_TURN");
/* 217 */         CardCrawlGame.mainMenuScreen.isSettingsUp = true;
/* 218 */         InputHelper.pressedEscape = false;
/* 219 */         CardCrawlGame.mainMenuScreen.statsScreen.hide();
/* 220 */         CardCrawlGame.mainMenuScreen.cancelButton.hide();
/* 221 */         CardCrawlGame.cancelButton.show(MainMenuScreen.TEXT[2]);
/* 222 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.SETTINGS;
/*     */         break;
/*     */       case SETTINGS_INPUT:
/* 225 */         CardCrawlGame.mainMenuScreen.inputSettingsScreen.open();
/*     */         break;
/*     */       case STAT_CHAR:
/* 228 */         CardCrawlGame.mainMenuScreen.statsScreen.open();
/*     */         break;
/*     */       case STAT_HISTORY:
/* 231 */         CardCrawlGame.mainMenuScreen.runHistoryScreen.open();
/*     */         break;
/*     */       case STAT_LEADERBOARDS:
/* 234 */         CardCrawlGame.mainMenuScreen.leaderboardsScreen.open();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 242 */     sb.setColor(this.wColor);
/* 243 */     sb.draw(this.panelImg, this.hb.cX - 256.0F, this.hb.cY + this.yMod - 400.0F, 256.0F, 400.0F, 512.0F, 800.0F, this.uiScale * Settings.scale, this.uiScale * Settings.scale, 0.0F, 0, 0, 512, 800, false, false);
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
/* 261 */     if (this.hb.hovered) {
/* 262 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, (this.uiScale - 1.0F) * 16.0F));
/* 263 */       sb.setBlendFunction(770, 1);
/* 264 */       sb.draw(ImageMaster.MENU_PANEL_BG_BLUE, this.hb.cX - 256.0F, this.hb.cY + this.yMod - 400.0F, 256.0F, 400.0F, 512.0F, 800.0F, this.uiScale * Settings.scale, this.uiScale * Settings.scale, 0.0F, 0, 0, 512, 800, false, false);
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
/* 281 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */     
/* 284 */     if (this.pColor == PanelColor.GRAY) {
/* 285 */       sb.setColor(this.grColor);
/*     */     } else {
/* 287 */       sb.setColor(this.wColor);
/*     */     } 
/* 289 */     sb.draw(this.portraitImg, this.hb.cX - 158.5F, this.hb.cY + this.yMod - 103.0F + 140.0F * Settings.scale, 158.5F, 103.0F, 317.0F, 206.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 317, 206, false, false);
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
/* 306 */     if (this.pColor == PanelColor.GRAY) {
/* 307 */       sb.setColor(this.wColor);
/* 308 */       sb.draw(ImageMaster.P_LOCK, this.hb.cX - 158.5F, this.hb.cY + this.yMod - 103.0F + 140.0F * Settings.scale, 158.5F, 103.0F, 317.0F, 206.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 317, 206, false, false);
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
/* 327 */     sb.draw(ImageMaster.MENU_PANEL_FRAME, this.hb.cX - 256.0F, this.hb.cY + this.yMod - 400.0F, 256.0F, 400.0F, 512.0F, 800.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 800, false, false);
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
/* 346 */     if (FontHelper.getWidth(FontHelper.damageNumberFont, this.header, 0.8F) > 310.0F * Settings.scale) {
/* 347 */       FontHelper.renderFontCenteredHeight(sb, FontHelper.damageNumberFont, this.header, this.hb.cX - 138.0F * Settings.scale, this.hb.cY + this.yMod + 294.0F * Settings.scale, 280.0F * Settings.scale, this.gColor, 0.7F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 357 */       FontHelper.renderFontCenteredHeight(sb, FontHelper.damageNumberFont, this.header, this.hb.cX - 153.0F * Settings.scale, this.hb.cY + this.yMod + 294.0F * Settings.scale, 310.0F * Settings.scale, this.gColor, 0.8F);
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
/* 369 */     FontHelper.renderFontCenteredHeight(sb, FontHelper.charDescFont, this.description, this.hb.cX - 153.0F * Settings.scale, this.hb.cY + this.yMod - 130.0F * Settings.scale, 330.0F * Settings.scale, this.cColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\MainMenuPanelButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */