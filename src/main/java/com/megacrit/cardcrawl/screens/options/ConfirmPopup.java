/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.SaveSlot;
import com.megacrit.cardcrawl.screens.mainMenu.SaveSlotScreen;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfirmPopup
/*     */ {
/*  34 */   protected static final Logger logger = LogManager.getLogger(ConfirmPopup.class.getName());
/*  35 */   protected static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ConfirmPopup")).TEXT;
/*     */   public String title;
/*     */   public String desc;
/*     */   public ConfirmType type;
/*     */   public Hitbox yesHb;
/*     */   public Hitbox noHb;
/*     */   public boolean shown;
/*     */   protected int slot;
/*     */   protected Color screenColor;
/*     */   protected Color uiColor;
/*     */   protected Color headerColor;
/*     */   protected Color descriptionColor;
/*     */   protected float targetAlpha;
/*     */   protected float targetAlpha2;
/*     */   protected static final int CONFIRM_W = 360;
/*     */   protected static final int CONFIRM_H = 414;
/*     */   protected static final int YES_W = 173;
/*     */   protected static final int NO_W = 161;
/*     */   protected static final int BUTTON_H = 74;
/*     */   protected static final float SCREEN_DARKNESS = 0.75F;
/*     */   
/*  56 */   public enum ConfirmType { EXIT, ABANDON_MID_RUN, DELETE_SAVE, SKIP_FTUE, ABANDON_MAIN_MENU; } public ConfirmPopup(ConfirmType type) { String[] TMP; this.shown = false; this.slot = -1; this.screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); this.uiColor = new Color(1.0F, 1.0F, 1.0F, 0.0F); this.headerColor = Settings.GOLD_COLOR.cpy();
/*     */     this.descriptionColor = Settings.CREAM_COLOR.cpy();
/*     */     this.targetAlpha = 0.0F;
/*     */     this.targetAlpha2 = 0.0F;
/*  60 */     switch (type) {
/*     */       case ABANDON_MAIN_MENU:
/*  62 */         TMP = (CardCrawlGame.languagePack.getUIString("SettingsScreen")).TEXT;
/*  63 */         this.type = type;
/*  64 */         this.title = TMP[0];
/*  65 */         this.desc = TMP[2];
/*  66 */         initializeButtons();
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeButtons() {
/*  74 */     if (Settings.isMobile) {
/*  75 */       this.yesHb = new Hitbox(240.0F * Settings.scale, 110.0F * Settings.scale);
/*  76 */       this.noHb = new Hitbox(240.0F * Settings.scale, 110.0F * Settings.scale);
/*  77 */       this.yesHb.move(810.0F * Settings.xScale, Settings.OPTION_Y - 162.0F * Settings.scale);
/*  78 */       this.noHb.move(1112.0F * Settings.xScale, Settings.OPTION_Y - 162.0F * Settings.scale);
/*     */     } else {
/*  80 */       this.yesHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale);
/*  81 */       this.noHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale);
/*  82 */       this.yesHb.move(860.0F * Settings.xScale, Settings.OPTION_Y - 118.0F * Settings.scale);
/*  83 */       this.noHb.move(1062.0F * Settings.xScale, Settings.OPTION_Y - 118.0F * Settings.scale);
/*     */     }  } public ConfirmPopup() { this.shown = false; this.slot = -1; this.screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); this.uiColor = new Color(1.0F, 1.0F, 1.0F, 0.0F); this.headerColor = Settings.GOLD_COLOR.cpy();
/*     */     this.descriptionColor = Settings.CREAM_COLOR.cpy();
/*     */     this.targetAlpha = 0.0F;
/*     */     this.targetAlpha2 = 0.0F;
/*  88 */     initializeButtons(); } public ConfirmPopup(String title, String desc, ConfirmType type) { this.shown = false; this.slot = -1; this.screenColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); this.uiColor = new Color(1.0F, 1.0F, 1.0F, 0.0F); this.headerColor = Settings.GOLD_COLOR.cpy();
/*     */     this.descriptionColor = Settings.CREAM_COLOR.cpy();
/*     */     this.targetAlpha = 0.0F;
/*     */     this.targetAlpha2 = 0.0F;
/*  92 */     this.type = type;
/*  93 */     this.title = title;
/*  94 */     this.desc = desc;
/*  95 */     initializeButtons(); }
/*     */ 
/*     */   
/*     */   public void show() {
/*  99 */     if (!this.shown) {
/* 100 */       this.shown = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void hide() {
/* 105 */     if (this.shown) {
/* 106 */       this.shown = false;
/* 107 */       if (this.type == ConfirmType.ABANDON_MID_RUN || this.type == ConfirmType.ABANDON_MAIN_MENU) {
/* 108 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 109 */         if (AbstractDungeon.overlayMenu != null) {
/* 110 */           AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
/*     */         
/*     */         }
/*     */       }
/* 114 */       else if (AbstractDungeon.overlayMenu != null) {
/* 115 */         AbstractDungeon.overlayMenu.cancelButton.show(TEXT[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateTransparency() {
/* 122 */     if (this.shown) {
/* 123 */       this.screenColor.a = MathHelper.fadeLerpSnap(this.screenColor.a, 0.75F);
/* 124 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 1.0F);
/*     */     } else {
/* 126 */       this.screenColor.a = MathHelper.fadeLerpSnap(this.screenColor.a, 0.0F);
/* 127 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/* 132 */     updateTransparency();
/*     */     
/* 134 */     if (this.shown) {
/* 135 */       updateYes();
/* 136 */       updateNo();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateYes() {
/* 141 */     this.yesHb.update();
/* 142 */     if (this.yesHb.justHovered) {
/* 143 */       CardCrawlGame.sound.play("UI_HOVER");
/* 144 */     } else if (InputHelper.justClickedLeft && this.yesHb.hovered) {
/* 145 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 146 */       this.yesHb.clickStarted = true;
/* 147 */     } else if (this.yesHb.clicked) {
/* 148 */       this.yesHb.clicked = false;
/* 149 */       yesButtonEffect();
/*     */     } 
/*     */ 
/*     */     
/* 153 */     if (CInputActionSet.proceed.isJustPressed()) {
/* 154 */       CInputActionSet.proceed.unpress();
/* 155 */       this.yesHb.clicked = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void updateNo() {
/* 160 */     this.noHb.update();
/* 161 */     if (this.noHb.justHovered) {
/* 162 */       CardCrawlGame.sound.play("UI_HOVER");
/* 163 */     } else if (this.noHb.hovered && InputHelper.justClickedLeft) {
/* 164 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 165 */       this.noHb.clickStarted = true;
/* 166 */     } else if (this.noHb.clicked) {
/* 167 */       this.noHb.clicked = false;
/* 168 */       noButtonEffect();
/* 169 */       hide();
/*     */     } 
/*     */     
/* 172 */     if (CInputActionSet.cancel.isJustPressed() || InputActionSet.cancel.isJustPressed()) {
/* 173 */       CInputActionSet.cancel.unpress();
/* 174 */       noButtonEffect();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void noButtonEffect() {
/* 179 */     switch (this.type) {
/*     */       case DELETE_SAVE:
/* 181 */         CardCrawlGame.mainMenuScreen.saveSlotScreen.curPop = SaveSlotScreen.CurrentPopup.NONE;
/* 182 */         this.shown = false;
/*     */         return;
/*     */       case SKIP_FTUE:
/* 185 */         TipTracker.disableAllFtues();
/* 186 */         hide();
/*     */         return;
/*     */       case ABANDON_MAIN_MENU:
/* 189 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/* 190 */         this.shown = false;
/* 191 */         this.targetAlpha = 0.0F;
/* 192 */         this.targetAlpha2 = 0.0F;
/* 193 */         if (AbstractDungeon.overlayMenu != null) {
/* 194 */           AbstractDungeon.overlayMenu.cancelButton.show(
/* 195 */               (CardCrawlGame.languagePack.getUIString("SettingsScreen")).TEXT[0]);
/*     */         }
/*     */         return;
/*     */     } 
/* 199 */     hide();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void abandonRunFromMainMenu(AbstractPlayer player) {
/* 205 */     AbstractPlayer.PlayerClass pClass = player.chosenClass;
/* 206 */     logger.info("Abandoning run with " + pClass.name());
/* 207 */     SaveFile file = SaveAndContinue.loadSaveFile(pClass);
/* 208 */     if (Settings.isStandardRun()) {
/* 209 */       if (file.floor_num >= 16) {
/* 210 */         CardCrawlGame.playerPref.putInteger(pClass.name() + "_SPIRITS", 1);
/* 211 */         CardCrawlGame.playerPref.flush();
/*     */       } else {
/* 213 */         CardCrawlGame.playerPref.putInteger(pClass.name() + "_SPIRITS", 0);
/* 214 */         CardCrawlGame.playerPref.flush();
/*     */       } 
/*     */     }
/* 217 */     SaveAndContinue.deleteSave(player);
/*     */     
/* 219 */     if (!file.is_ascension_mode)
/* 220 */       StatsScreen.incrementDeath(player.getCharStat()); 
/*     */   }
/*     */   protected void yesButtonEffect() {
/*     */     AbstractPlayer playerWithSave;
/*     */     boolean allSlotsEmpty;
/* 225 */     switch (this.type) {
/*     */       
/*     */       case EXIT:
/* 228 */         CardCrawlGame.music.fadeAll();
/*     */         
/* 230 */         hide();
/* 231 */         AbstractDungeon.getCurrRoom().clearEvent();
/* 232 */         AbstractDungeon.closeCurrentScreen();
/* 233 */         CardCrawlGame.startOver();
/*     */ 
/*     */         
/* 236 */         if (RestRoom.lastFireSoundId != 0L) {
/* 237 */           CardCrawlGame.sound.fadeOut("REST_FIRE_WET", RestRoom.lastFireSoundId);
/*     */         }
/* 239 */         if (!AbstractDungeon.player.stance.ID.equals("Neutral") && AbstractDungeon.player.stance != null)
/*     */         {
/* 241 */           AbstractDungeon.player.stance.stopIdleSfx();
/*     */         }
/*     */         break;
/*     */       case ABANDON_MAIN_MENU:
/* 245 */         playerWithSave = CardCrawlGame.characterManager.loadChosenCharacter();
/* 246 */         if (playerWithSave != null) {
/*     */           String statId;
/* 248 */           if (Settings.isBeta) {
/* 249 */             statId = playerWithSave.getWinStreakKey() + "_BETA";
/*     */           } else {
/* 251 */             statId = playerWithSave.getWinStreakKey();
/*     */           } 
/* 253 */           CardCrawlGame.publisherIntegration.setStat(statId, 0);
/* 254 */           logger.info("WIN STREAK  " + CardCrawlGame.publisherIntegration.getStat(statId));
/* 255 */           abandonRunFromMainMenu(playerWithSave);
/*     */         } 
/* 257 */         CardCrawlGame.mainMenuScreen.abandonedRun = true;
/* 258 */         hide();
/*     */         break;
/*     */       case ABANDON_MID_RUN:
/* 261 */         hide();
/* 262 */         AbstractDungeon.closeCurrentScreen();
/* 263 */         AbstractDungeon.player.isDead = true;
/* 264 */         AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
/*     */         break;
/*     */       case DELETE_SAVE:
/* 267 */         SaveHelper.deletePrefs(this.slot);
/* 268 */         CardCrawlGame.mainMenuScreen.saveSlotScreen.deleteSlot(this.slot);
/* 269 */         CardCrawlGame.mainMenuScreen.saveSlotScreen.curPop = SaveSlotScreen.CurrentPopup.NONE;
/* 270 */         CardCrawlGame.playerName = "";
/* 271 */         this.shown = false;
/*     */ 
/*     */         
/* 274 */         allSlotsEmpty = true;
/* 275 */         for (SaveSlot s : CardCrawlGame.mainMenuScreen.saveSlotScreen.slots) {
/* 276 */           if (!s.emptySlot) {
/* 277 */             allSlotsEmpty = false;
/*     */             break;
/*     */           } 
/*     */         } 
/* 281 */         if (allSlotsEmpty) {
/* 282 */           CardCrawlGame.mainMenuScreen.saveSlotScreen.cancelButton.hide();
/*     */         }
/*     */         break;
/*     */       case SKIP_FTUE:
/* 286 */         TipTracker.neverShowAgain("NO_FTUE");
/* 287 */         hide();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 295 */     renderPopupBg(sb);
/* 296 */     renderText(sb);
/* 297 */     if (this.shown) {
/* 298 */       renderButtons(sb);
/*     */     }
/* 300 */     renderControllerUi(sb);
/*     */   }
/*     */   
/*     */   protected void renderPopupBg(SpriteBatch sb) {
/* 304 */     sb.setColor(this.screenColor);
/* 305 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 307 */     sb.setColor(this.uiColor);
/* 308 */     if (!Settings.isMobile) {
/* 309 */       sb.draw(ImageMaster.OPTION_CONFIRM, Settings.WIDTH / 2.0F - 180.0F, Settings.OPTION_Y - 207.0F, 180.0F, 207.0F, 360.0F, 414.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 360, 414, false, false);
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
/*     */       
/* 327 */       sb.draw(ImageMaster.OPTION_CONFIRM, Settings.WIDTH / 2.0F - 180.0F, Settings.OPTION_Y - 207.0F, 180.0F, 207.0F, 360.0F, 414.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 360, 414, false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderButtons(SpriteBatch sb) {
/* 348 */     if (Settings.isMobile) {
/* 349 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 150.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 170.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 173, 74, false, false);
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
/*     */       
/* 367 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 386 */     if (this.yesHb.hovered) {
/* 387 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 388 */       sb.setBlendFunction(770, 1);
/*     */       
/* 390 */       if (Settings.isMobile) {
/* 391 */         sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 150.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 170.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 173, 74, false, false);
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
/* 409 */         sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 428 */       sb.setBlendFunction(770, 771);
/* 429 */       sb.setColor(this.uiColor);
/*     */       
/* 431 */       if (Settings.isMobile) {
/* 432 */         FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[2], Settings.WIDTH / 2.0F - 165.0F * Settings.scale, Settings.OPTION_Y - 162.0F * Settings.scale, this.uiColor, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 441 */         FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[2], Settings.WIDTH / 2.0F - 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, this.uiColor, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 451 */     else if (Settings.isMobile) {
/* 452 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[2], Settings.WIDTH / 2.0F - 165.0F * Settings.scale, Settings.OPTION_Y - 162.0F * Settings.scale, this.headerColor, 1.0F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 461 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[2], Settings.WIDTH / 2.0F - 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, this.headerColor, 1.0F);
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
/* 472 */     if (Settings.isMobile) {
/* 473 */       sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 160.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 170.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 161, 74, false, false);
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
/*     */       
/* 491 */       sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
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
/* 510 */     if (this.noHb.hovered) {
/* 511 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 512 */       sb.setBlendFunction(770, 1);
/*     */       
/* 514 */       if (Settings.isMobile) {
/* 515 */         sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 160.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 170.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale * 1.5F, Settings.scale * 1.5F, 0.0F, 0, 0, 161, 74, false, false);
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
/* 533 */         sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
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
/* 552 */       sb.setBlendFunction(770, 771);
/* 553 */       sb.setColor(this.uiColor);
/*     */       
/* 555 */       if (Settings.isMobile) {
/* 556 */         FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[3], Settings.WIDTH / 2.0F + 165.0F * Settings.scale, Settings.OPTION_Y - 162.0F * Settings.scale, this.uiColor, 1.0F);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 565 */         FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[3], Settings.WIDTH / 2.0F + 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, this.uiColor, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 576 */     else if (Settings.isMobile) {
/* 577 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, TEXT[3], Settings.WIDTH / 2.0F + 165.0F * Settings.scale, Settings.OPTION_Y - 162.0F * Settings.scale, this.headerColor, 1.0F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 586 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[3], Settings.WIDTH / 2.0F + 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, this.headerColor, 1.0F);
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
/* 597 */     this.yesHb.render(sb);
/* 598 */     this.noHb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderText(SpriteBatch sb) {
/* 602 */     this.headerColor.a = this.uiColor.a;
/*     */     
/* 604 */     if (Settings.isMobile) {
/* 605 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.title, Settings.WIDTH / 2.0F, Settings.OPTION_Y + 200.0F * Settings.scale, this.headerColor, 1.2F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 614 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.title, Settings.WIDTH / 2.0F, Settings.OPTION_Y + 126.0F * Settings.scale, this.headerColor);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 623 */     this.descriptionColor.a = this.uiColor.a;
/*     */     
/* 625 */     if (Settings.isMobile) {
/* 626 */       FontHelper.renderWrappedText(sb, FontHelper.panelNameFont, this.desc, Settings.WIDTH / 2.0F, Settings.OPTION_Y + 30.0F * Settings.scale, 380.0F * Settings.scale, this.descriptionColor, 1.0F);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 636 */       FontHelper.renderWrappedText(sb, FontHelper.tipBodyFont, this.desc, Settings.WIDTH / 2.0F, Settings.OPTION_Y + 20.0F * Settings.scale, 240.0F * Settings.scale, this.descriptionColor, 1.0F);
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
/*     */   private void renderControllerUi(SpriteBatch sb) {
/* 649 */     if (Settings.isControllerMode) {
/* 650 */       sb.draw(CInputActionSet.proceed
/* 651 */           .getKeyImg(), 770.0F * Settings.xScale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 668 */       sb.draw(CInputActionSet.cancel
/* 669 */           .getKeyImg(), 1150.0F * Settings.xScale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\ConfirmPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */