/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.DisplayConfig;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.vfx.RestartForChangesEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ToggleButton {
/*     */   private static final int W = 32;
/*     */   private float x;
/*     */   private float y;
/*  21 */   private static final Logger logger = LogManager.getLogger(ToggleButton.class.getName());
/*     */   
/*     */   public Hitbox hb;
/*     */   public boolean enabled = true;
/*     */   private ToggleBtnType type;
/*     */   
/*     */   public enum ToggleBtnType
/*     */   {
/*  29 */     FULL_SCREEN, W_FULL_SCREEN, SCREEN_SHAKE, AMBIENCE_ON, MUTE_IF_BG, SUM_DMG, BIG_TEXT, BLOCK_DMG, HAND_CONF, FAST_MODE, UPLOAD_DATA, LONG_PRESS, V_SYNC, PLAYTESTER_ART, SHOW_CARD_HOTKEYS, EFFECTS;
/*     */   }
/*     */   
/*     */   public ToggleButton(float x, float y, float middleY, ToggleBtnType type) {
/*  33 */     this.x = x;
/*  34 */     this.y = middleY + y * Settings.scale;
/*  35 */     this.type = type;
/*  36 */     this.hb = new Hitbox(200.0F * Settings.scale, 32.0F * Settings.scale);
/*  37 */     this.hb.move(x + 74.0F * Settings.scale, this.y);
/*  38 */     this.enabled = getPref();
/*     */   }
/*     */   
/*     */   public ToggleButton(float x, float y, float middleY, ToggleBtnType type, boolean isLarge) {
/*  42 */     this.x = x;
/*  43 */     this.y = middleY + y * Settings.scale;
/*  44 */     this.type = type;
/*  45 */     if (isLarge) {
/*  46 */       this.hb = new Hitbox(480.0F * Settings.scale, 32.0F * Settings.scale);
/*  47 */       this.hb.move(x + 214.0F * Settings.scale, this.y);
/*     */     } else {
/*  49 */       this.hb = new Hitbox(240.0F * Settings.scale, 32.0F * Settings.scale);
/*  50 */       this.hb.move(x + 74.0F * Settings.scale, this.y);
/*     */     } 
/*  52 */     this.enabled = getPref();
/*     */   }
/*     */   
/*     */   private boolean getPref() {
/*  56 */     switch (this.type) {
/*     */       case AMBIENCE_ON:
/*  58 */         return Settings.soundPref.getBoolean("Ambience On", true);
/*     */       case MUTE_IF_BG:
/*  60 */         return Settings.soundPref.getBoolean("Mute in Bg", true);
/*     */       case BIG_TEXT:
/*  62 */         return Settings.gamePref.getBoolean("Bigger Text", false);
/*     */       case BLOCK_DMG:
/*  64 */         return Settings.gamePref.getBoolean("Blocked Damage", false);
/*     */       case EFFECTS:
/*  66 */         return Settings.gamePref.getBoolean("Particle Effects", false);
/*     */       case FAST_MODE:
/*  68 */         return Settings.gamePref.getBoolean("Fast Mode", false);
/*     */       case SHOW_CARD_HOTKEYS:
/*  70 */         return Settings.gamePref.getBoolean("Show Card keys", false);
/*     */       case FULL_SCREEN:
/*  72 */         return Settings.IS_FULLSCREEN;
/*     */       case W_FULL_SCREEN:
/*  74 */         return Settings.IS_W_FULLSCREEN;
/*     */       case V_SYNC:
/*  76 */         return Settings.IS_V_SYNC;
/*     */       case HAND_CONF:
/*  78 */         return Settings.gamePref.getBoolean("Hand Confirmation", false);
/*     */       case SCREEN_SHAKE:
/*  80 */         return Settings.gamePref.getBoolean("Screen Shake", true);
/*     */       case SUM_DMG:
/*  82 */         return Settings.gamePref.getBoolean("Summed Damage", false);
/*     */       case UPLOAD_DATA:
/*  84 */         return Settings.gamePref.getBoolean("Upload Data", true);
/*     */       case LONG_PRESS:
/*  86 */         return Settings.gamePref.getBoolean("Long-press Enabled", Settings.isConsoleBuild);
/*     */       case PLAYTESTER_ART:
/*  88 */         return Settings.gamePref.getBoolean("Playtester Art", false);
/*     */     } 
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  95 */     this.hb.update();
/*  96 */     if (this.hb.hovered && (InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed())) {
/*  97 */       InputHelper.justClickedLeft = false;
/*  98 */       toggle();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void toggle() {
/* 103 */     this.enabled = !this.enabled;
/* 104 */     switch (this.type) {
/*     */       case AMBIENCE_ON:
/* 106 */         Settings.soundPref.putBoolean("Ambience On", this.enabled);
/* 107 */         Settings.soundPref.flush();
/* 108 */         Settings.AMBIANCE_ON = this.enabled;
/* 109 */         if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 110 */           CardCrawlGame.mainMenuScreen.updateAmbienceVolume();
/*     */         } else {
/* 112 */           AbstractDungeon.scene.updateAmbienceVolume();
/*     */         } 
/* 114 */         logger.info("Ambience On: " + this.enabled);
/*     */         break;
/*     */       case MUTE_IF_BG:
/* 117 */         Settings.soundPref.putBoolean("Mute in Bg", this.enabled);
/* 118 */         Settings.soundPref.flush();
/* 119 */         CardCrawlGame.MUTE_IF_BG = this.enabled;
/* 120 */         logger.info("Mute while in Background: " + this.enabled);
/*     */         break;
/*     */       case BIG_TEXT:
/* 123 */         Settings.gamePref.putBoolean("Bigger Text", this.enabled);
/* 124 */         Settings.gamePref.flush();
/* 125 */         Settings.BIG_TEXT_MODE = this.enabled;
/* 126 */         CardCrawlGame.mainMenuScreen.optionPanel.displayRestartRequiredText();
/* 127 */         logger.info("Bigger Text: " + this.enabled);
/*     */         break;
/*     */       case BLOCK_DMG:
/* 130 */         Settings.gamePref.putBoolean("Blocked Damage", this.enabled);
/* 131 */         Settings.gamePref.flush();
/* 132 */         Settings.SHOW_DMG_BLOCK = this.enabled;
/* 133 */         logger.info("Show Blocked Damage: " + this.enabled);
/*     */         break;
/*     */       case EFFECTS:
/* 136 */         Settings.gamePref.putBoolean("Particle Effects", this.enabled);
/* 137 */         Settings.gamePref.flush();
/* 138 */         Settings.DISABLE_EFFECTS = this.enabled;
/* 139 */         logger.info("Particle FX Disabled: " + this.enabled);
/*     */         break;
/*     */       case FAST_MODE:
/* 142 */         Settings.gamePref.putBoolean("Fast Mode", this.enabled);
/* 143 */         Settings.gamePref.flush();
/* 144 */         Settings.FAST_MODE = this.enabled;
/* 145 */         logger.info("Fast Mode: " + this.enabled);
/*     */         break;
/*     */       case SHOW_CARD_HOTKEYS:
/* 148 */         Settings.gamePref.putBoolean("Show Card keys", this.enabled);
/* 149 */         Settings.gamePref.flush();
/* 150 */         Settings.SHOW_CARD_HOTKEYS = this.enabled;
/* 151 */         logger.info("Show Card Hotkeys: " + this.enabled);
/*     */         break;
/*     */       case FULL_SCREEN:
/* 154 */         Settings.IS_FULLSCREEN = !Settings.IS_FULLSCREEN;
/*     */         
/* 156 */         if (Settings.IS_FULLSCREEN) {
/*     */           
/* 158 */           if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 159 */             CardCrawlGame.mainMenuScreen.optionPanel.setFullscreen(false);
/*     */           } else {
/* 161 */             AbstractDungeon.settingsScreen.panel.setFullscreen(false);
/*     */           } 
/*     */ 
/*     */           
/* 165 */           if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 166 */             if (CardCrawlGame.mainMenuScreen.optionPanel.wfsToggle.enabled) {
/* 167 */               CardCrawlGame.mainMenuScreen.optionPanel.wfsToggle.enabled = false;
/*     */             }
/*     */           }
/* 170 */           else if (AbstractDungeon.settingsScreen.panel.wfsToggle.enabled) {
/* 171 */             AbstractDungeon.settingsScreen.panel.wfsToggle.enabled = false;
/*     */           } 
/*     */           
/* 174 */           updateResolutionDropdown(0);
/*     */         } else {
/* 176 */           updateResolutionDropdown(2);
/*     */         } 
/*     */         
/* 179 */         Settings.IS_W_FULLSCREEN = false;
/*     */         
/* 181 */         DisplayConfig.writeDisplayConfigFile(
/* 182 */             (Gdx.graphics.getDisplayMode()).width, 
/* 183 */             (Gdx.graphics.getDisplayMode()).height, Settings.MAX_FPS, Settings.IS_FULLSCREEN, Settings.IS_W_FULLSCREEN, Settings.IS_V_SYNC);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 189 */         logger.info("Fullscreen: " + Settings.IS_FULLSCREEN);
/*     */         break;
/*     */       case W_FULL_SCREEN:
/* 192 */         Settings.IS_W_FULLSCREEN = !Settings.IS_W_FULLSCREEN;
/*     */         
/* 194 */         if (Settings.IS_W_FULLSCREEN) {
/*     */           
/* 196 */           if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 197 */             CardCrawlGame.mainMenuScreen.optionPanel.setFullscreen(true);
/*     */           } else {
/* 199 */             AbstractDungeon.settingsScreen.panel.setFullscreen(true);
/*     */           } 
/*     */ 
/*     */           
/* 203 */           if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 204 */             if (CardCrawlGame.mainMenuScreen.optionPanel.fsToggle.enabled) {
/* 205 */               CardCrawlGame.mainMenuScreen.optionPanel.fsToggle.enabled = false;
/*     */             }
/*     */           }
/* 208 */           else if (AbstractDungeon.settingsScreen.panel.fsToggle.enabled) {
/* 209 */             AbstractDungeon.settingsScreen.panel.fsToggle.enabled = false;
/*     */           } 
/*     */           
/* 212 */           updateResolutionDropdown(1);
/*     */         } else {
/* 214 */           updateResolutionDropdown(2);
/*     */         } 
/*     */         
/* 217 */         Settings.IS_FULLSCREEN = false;
/* 218 */         DisplayConfig.writeDisplayConfigFile(
/* 219 */             (Gdx.graphics.getDisplayMode()).width, 
/* 220 */             (Gdx.graphics.getDisplayMode()).height, Settings.MAX_FPS, Settings.IS_FULLSCREEN, Settings.IS_W_FULLSCREEN, Settings.IS_V_SYNC);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 226 */         logger.info("Borderless Fullscreen: " + Settings.IS_W_FULLSCREEN);
/*     */         break;
/*     */       case V_SYNC:
/* 229 */         Settings.IS_V_SYNC = !Settings.IS_V_SYNC;
/*     */ 
/*     */         
/* 232 */         if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT) {
/* 233 */           CardCrawlGame.mainMenuScreen.optionPanel.vSyncToggle.enabled = Settings.IS_V_SYNC;
/* 234 */           CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();
/* 235 */           CardCrawlGame.mainMenuScreen.optionPanel.effects.add(new RestartForChangesEffect());
/*     */         } else {
/* 237 */           AbstractDungeon.settingsScreen.panel.vSyncToggle.enabled = Settings.IS_V_SYNC;
/* 238 */           AbstractDungeon.settingsScreen.panel.effects.clear();
/* 239 */           AbstractDungeon.settingsScreen.panel.effects.add(new RestartForChangesEffect());
/*     */         } 
/*     */         
/* 242 */         DisplayConfig.writeDisplayConfigFile(Settings.SAVED_WIDTH, Settings.SAVED_HEIGHT, Settings.MAX_FPS, Settings.IS_FULLSCREEN, Settings.IS_W_FULLSCREEN, Settings.IS_V_SYNC);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 250 */         logger.info("V Sync: " + Settings.IS_V_SYNC);
/*     */         break;
/*     */       case HAND_CONF:
/* 253 */         Settings.gamePref.putBoolean("Hand Confirmation", this.enabled);
/* 254 */         Settings.gamePref.flush();
/* 255 */         Settings.FAST_HAND_CONF = this.enabled;
/* 256 */         logger.info("Hand Confirmation: " + this.enabled);
/*     */         break;
/*     */       case SCREEN_SHAKE:
/* 259 */         Settings.gamePref.putBoolean("Screen Shake", this.enabled);
/* 260 */         Settings.gamePref.flush();
/* 261 */         Settings.SCREEN_SHAKE = this.enabled;
/* 262 */         logger.info("Screen Shake: " + this.enabled);
/*     */         break;
/*     */       case SUM_DMG:
/* 265 */         Settings.gamePref.putBoolean("Summed Damage", this.enabled);
/* 266 */         Settings.gamePref.flush();
/* 267 */         Settings.SHOW_DMG_SUM = this.enabled;
/* 268 */         logger.info("Display Summed Damage: " + this.enabled);
/*     */         break;
/*     */       case UPLOAD_DATA:
/* 271 */         Settings.gamePref.putBoolean("Upload Data", this.enabled);
/* 272 */         Settings.gamePref.flush();
/* 273 */         Settings.UPLOAD_DATA = this.enabled;
/* 274 */         logger.info("Upload Data: " + this.enabled);
/*     */         break;
/*     */       case PLAYTESTER_ART:
/* 277 */         Settings.gamePref.putBoolean("Playtester Art", this.enabled);
/* 278 */         Settings.gamePref.flush();
/* 279 */         Settings.PLAYTESTER_ART_MODE = this.enabled;
/* 280 */         logger.info("Playtester Art: " + this.enabled);
/*     */         break;
/*     */       case LONG_PRESS:
/* 283 */         Settings.gamePref.putBoolean("Long-press Enabled", this.enabled);
/* 284 */         Settings.gamePref.flush();
/* 285 */         Settings.USE_LONG_PRESS = this.enabled;
/* 286 */         logger.info("Long-press: " + this.enabled);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateResolutionDropdown(int mode) {
/* 294 */     CardCrawlGame.mainMenuScreen.optionPanel.resoDropdown.updateResolutionLabels(mode);
/* 295 */     AbstractDungeon.settingsScreen.panel.resoDropdown.updateResolutionLabels(mode);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 299 */     if (this.enabled) {
/* 300 */       sb.setColor(Color.LIGHT_GRAY);
/*     */     } else {
/* 302 */       sb.setColor(Color.WHITE);
/*     */     } 
/*     */     
/* 305 */     float scale = Settings.scale;
/* 306 */     if (this.hb.hovered) {
/* 307 */       sb.setColor(Color.SKY);
/* 308 */       scale = Settings.scale * 1.25F;
/*     */     } 
/*     */     
/* 311 */     sb.draw(ImageMaster.OPTION_TOGGLE, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, scale, scale, 0.0F, 0, 0, 32, 32, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (this.enabled) {
/* 330 */       sb.setColor(Color.WHITE);
/* 331 */       sb.draw(ImageMaster.OPTION_TOGGLE_ON, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, scale, scale, 0.0F, 0, 0, 32, 32, false, false);
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
/* 350 */     this.hb.render(sb);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\ToggleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */