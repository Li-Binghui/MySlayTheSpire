/*     */ package com.megacrit.cardcrawl.ui.panels;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.codedisaster.steamworks.SteamUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.helpers.input.ScrollInputProcessor;
import com.megacrit.cardcrawl.helpers.steamInput.SteamInputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.SaveSlotScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenamePopup
/*     */ {
/*  31 */   private static final Logger logger = LogManager.getLogger(RenamePopup.class.getName());
/*     */ 
/*     */   
/*  34 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RenamePanel");
/*  35 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  38 */   private int slot = 0;
/*     */   private boolean newSave = false;
/*  40 */   public static String textField = "";
/*     */   
/*     */   private boolean shown = false;
/*  43 */   public Hitbox yesHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale); public Hitbox noHb = new Hitbox(160.0F * Settings.scale, 70.0F * Settings.scale); private static final int CONFIRM_W = 360;
/*     */   private static final int CONFIRM_H = 414;
/*     */   private static final int YES_W = 173;
/*     */   private static final int NO_W = 161;
/*     */   private static final int BUTTON_H = 74;
/*  48 */   private Color faderColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  49 */   private Color uiColor = new Color(1.0F, 0.965F, 0.886F, 0.0F);
/*  50 */   private float waitTimer = 0.0F;
/*     */   
/*     */   public RenamePopup() {
/*  53 */     this.yesHb.move(854.0F * Settings.scale, Settings.OPTION_Y - 120.0F * Settings.scale);
/*  54 */     this.noHb.move(1066.0F * Settings.scale, Settings.OPTION_Y - 120.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  58 */     if (Gdx.input.isKeyPressed(67) && !textField.equals("") && this.waitTimer <= 0.0F) {
/*  59 */       textField = textField.substring(0, textField.length() - 1);
/*  60 */       this.waitTimer = 0.09F;
/*     */     } 
/*     */     
/*  63 */     if (this.waitTimer > 0.0F) {
/*  64 */       this.waitTimer -= Gdx.graphics.getDeltaTime();
/*     */     }
/*     */     
/*  67 */     if (this.shown) {
/*  68 */       this.faderColor.a = MathHelper.fadeLerpSnap(this.faderColor.a, 0.75F);
/*  69 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 1.0F);
/*  70 */       updateButtons();
/*     */ 
/*     */       
/*  73 */       if (Gdx.input.isKeyJustPressed(66)) {
/*  74 */         confirm();
/*  75 */       } else if (InputHelper.pressedEscape) {
/*  76 */         InputHelper.pressedEscape = false;
/*  77 */         cancel();
/*     */       } 
/*     */     } else {
/*  80 */       this.faderColor.a = MathHelper.fadeLerpSnap(this.faderColor.a, 0.0F);
/*  81 */       this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtons() {
/*  87 */     this.yesHb.update();
/*  88 */     if (this.yesHb.justHovered) {
/*  89 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/*  92 */     if (this.yesHb.hovered && InputHelper.justClickedLeft) {
/*  93 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*  94 */       this.yesHb.clickStarted = true;
/*  95 */     } else if (this.yesHb.clicked) {
/*  96 */       confirm();
/*  97 */       this.yesHb.clicked = false;
/*     */     } 
/*     */ 
/*     */     
/* 101 */     this.noHb.update();
/* 102 */     if (this.noHb.justHovered) {
/* 103 */       CardCrawlGame.sound.play("UI_HOVER");
/*     */     }
/*     */     
/* 106 */     if (this.noHb.hovered && InputHelper.justClickedLeft) {
/* 107 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 108 */       this.noHb.clickStarted = true;
/* 109 */     } else if (this.noHb.clicked) {
/* 110 */       cancel();
/* 111 */       this.noHb.clicked = false;
/*     */     } 
/*     */ 
/*     */     
/* 115 */     if (CInputActionSet.proceed.isJustPressed()) {
/* 116 */       CInputActionSet.proceed.unpress();
/* 117 */       confirm();
/* 118 */     } else if (CInputActionSet.cancel.isJustPressed() || InputActionSet.cancel.isJustPressed()) {
/* 119 */       CInputActionSet.cancel.unpress();
/* 120 */       cancel();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void confirm() {
/* 125 */     textField = textField.trim();
/* 126 */     if (textField.equals("")) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     CardCrawlGame.mainMenuScreen.saveSlotScreen.curPop = SaveSlotScreen.CurrentPopup.NONE;
/* 131 */     this.shown = false;
/* 132 */     Gdx.input.setInputProcessor((InputProcessor)new ScrollInputProcessor());
/*     */ 
/*     */     
/* 135 */     if (this.newSave) {
/* 136 */       boolean saveSlotPrefSave = false;
/*     */ 
/*     */       
/* 139 */       logger.info("UPDATING DEFAULT SLOT: ", Integer.valueOf(this.slot));
/* 140 */       CardCrawlGame.saveSlotPref.putInteger("DEFAULT_SLOT", this.slot);
/* 141 */       saveSlotPrefSave = true;
/* 142 */       CardCrawlGame.reloadPrefs();
/*     */ 
/*     */       
/* 145 */       if (!CardCrawlGame.saveSlotPref.getString(SaveHelper.slotName("PROFILE_NAME", this.slot), "").equals(textField)) {
/*     */         
/* 147 */         logger.info("NAME CHANGE IN SLOT " + this.slot + ": " + textField);
/* 148 */         CardCrawlGame.saveSlotPref.putString(SaveHelper.slotName("PROFILE_NAME", this.slot), textField);
/* 149 */         saveSlotPrefSave = true;
/*     */       } 
/*     */       
/* 152 */       if (saveSlotPrefSave) {
/* 153 */         CardCrawlGame.saveSlotPref.flush();
/*     */       }
/*     */ 
/*     */       
/* 157 */       if (CardCrawlGame.playerPref.getString("alias", "").equals("")) {
/* 158 */         CardCrawlGame.playerPref.putString("alias", CardCrawlGame.generateRandomAlias());
/*     */       }
/*     */       
/* 161 */       CardCrawlGame.alias = CardCrawlGame.playerPref.getString("alias", "");
/* 162 */       CardCrawlGame.playerPref.putString("name", textField);
/* 163 */       CardCrawlGame.playerPref.flush();
/* 164 */       CardCrawlGame.playerName = textField;
/*     */ 
/*     */     
/*     */     }
/* 168 */     else if (!CardCrawlGame.saveSlotPref.getString(SaveHelper.slotName("PROFILE_NAME", this.slot), "").equals(textField)) {
/*     */       
/* 170 */       logger.info("RENAMING " + this.slot + ": " + textField);
/* 171 */       CardCrawlGame.saveSlotPref.putString(SaveHelper.slotName("PROFILE_NAME", this.slot), textField);
/* 172 */       CardCrawlGame.saveSlotPref.flush();
/* 173 */       CardCrawlGame.mainMenuScreen.saveSlotScreen.rename(this.slot, textField);
/* 174 */       CardCrawlGame.playerName = textField;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancel() {
/* 180 */     CardCrawlGame.mainMenuScreen.saveSlotScreen.curPop = SaveSlotScreen.CurrentPopup.NONE;
/* 181 */     this.shown = false;
/* 182 */     Gdx.input.setInputProcessor((InputProcessor)new ScrollInputProcessor());
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 186 */     sb.setColor(this.faderColor);
/* 187 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 188 */     renderPopupBg(sb);
/* 189 */     renderTextbox(sb);
/* 190 */     renderHeader(sb);
/* 191 */     renderButtons(sb);
/*     */   }
/*     */   
/*     */   private void renderHeader(SpriteBatch sb) {
/* 195 */     Color c = Settings.GOLD_COLOR.cpy();
/* 196 */     c.a = this.uiColor.a;
/* 197 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[1], Settings.WIDTH / 2.0F, Settings.OPTION_Y + 150.0F * Settings.scale, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPopupBg(SpriteBatch sb) {
/* 208 */     sb.setColor(this.uiColor);
/* 209 */     sb.draw(ImageMaster.OPTION_CONFIRM, Settings.WIDTH / 2.0F - 180.0F, Settings.OPTION_Y - 207.0F, 180.0F, 207.0F, 360.0F, 414.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 360, 414, false, false);
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
/*     */   
/*     */   private void renderTextbox(SpriteBatch sb) {
/* 230 */     sb.draw(ImageMaster.RENAME_BOX, Settings.WIDTH / 2.0F - 160.0F, Settings.OPTION_Y + 20.0F * Settings.scale - 160.0F, 160.0F, 160.0F, 320.0F, 320.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 320, 320, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, textField, Settings.WIDTH / 2.0F - 120.0F * Settings.scale, Settings.OPTION_Y + 24.0F * Settings.scale, 100000.0F, 0.0F, this.uiColor, 0.82F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     float tmpAlpha = (MathUtils.cosDeg((float)(System.currentTimeMillis() / 3L % 360L)) + 1.25F) / 3.0F * this.uiColor.a;
/* 261 */     FontHelper.renderSmartText(sb, FontHelper.cardTitleFont, "_", Settings.WIDTH / 2.0F - 122.0F * Settings.scale + 
/*     */ 
/*     */ 
/*     */         
/* 265 */         FontHelper.getSmartWidth(FontHelper.cardTitleFont, textField, 1000000.0F, 0.0F, 0.82F), Settings.OPTION_Y + 24.0F * Settings.scale, 100000.0F, 0.0F, new Color(1.0F, 1.0F, 1.0F, tmpAlpha));
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
/*     */   private void renderButtons(SpriteBatch sb) {
/* 277 */     sb.setColor(this.uiColor);
/* 278 */     Color c = Settings.GOLD_COLOR.cpy();
/* 279 */     c.a = this.uiColor.a;
/*     */ 
/*     */     
/* 282 */     if (this.yesHb.clickStarted) {
/* 283 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.9F));
/* 284 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 301 */       sb.setColor(new Color(this.uiColor));
/*     */     } else {
/* 303 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
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
/* 322 */     if (!this.yesHb.clickStarted && this.yesHb.hovered) {
/* 323 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 324 */       sb.setBlendFunction(770, 1);
/* 325 */       sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0F - 86.5F - 100.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 86.5F, 37.0F, 173.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 173, 74, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 342 */       sb.setBlendFunction(770, 771);
/* 343 */       sb.setColor(this.uiColor);
/*     */     } 
/*     */     
/* 346 */     if (this.yesHb.clickStarted || textField.trim().equals("")) {
/* 347 */       c = Color.LIGHT_GRAY.cpy();
/* 348 */     } else if (this.yesHb.hovered) {
/* 349 */       c = Settings.CREAM_COLOR.cpy();
/*     */     } else {
/* 351 */       c = Settings.GOLD_COLOR.cpy();
/*     */     } 
/* 353 */     c.a = this.uiColor.a;
/*     */     
/* 355 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[2], Settings.WIDTH / 2.0F - 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, c, 0.82F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 365 */     sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 383 */     if (!this.noHb.clickStarted && this.noHb.hovered) {
/* 384 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.uiColor.a * 0.25F));
/* 385 */       sb.setBlendFunction(770, 1);
/* 386 */       sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0F - 80.5F + 106.0F * Settings.scale, Settings.OPTION_Y - 37.0F - 120.0F * Settings.scale, 80.5F, 37.0F, 161.0F, 74.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 161, 74, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 403 */       sb.setBlendFunction(770, 771);
/* 404 */       sb.setColor(this.uiColor);
/*     */     } 
/*     */     
/* 407 */     if (this.noHb.clickStarted) {
/* 408 */       c = Color.LIGHT_GRAY.cpy();
/* 409 */     } else if (this.noHb.hovered) {
/* 410 */       c = Settings.CREAM_COLOR.cpy();
/*     */     } else {
/* 412 */       c = Settings.GOLD_COLOR.cpy();
/*     */     } 
/*     */     
/* 415 */     c.a = this.uiColor.a;
/* 416 */     FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[3], Settings.WIDTH / 2.0F + 110.0F * Settings.scale, Settings.OPTION_Y - 118.0F * Settings.scale, c, 0.82F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 425 */     if (Settings.isControllerMode) {
/* 426 */       sb.draw(CInputActionSet.proceed
/* 427 */           .getKeyImg(), 770.0F * Settings.xScale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 443 */       sb.draw(CInputActionSet.cancel
/* 444 */           .getKeyImg(), 1150.0F * Settings.xScale - 32.0F, Settings.OPTION_Y - 32.0F - 140.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 462 */     if (this.shown) {
/* 463 */       this.yesHb.render(sb);
/* 464 */       this.noHb.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void open(int slot, boolean isNewSave) {
/* 469 */     Gdx.input.setInputProcessor((InputProcessor)new TypeHelper());
/*     */     
/* 471 */     if (SteamInputHelper.numControllers == 1 && CardCrawlGame.clientUtils != null && CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck()) {
/* 472 */       CardCrawlGame.clientUtils.showFloatingGamepadTextInput(SteamUtils.FloatingGamepadTextInputMode.ModeSingleLine, 0, 0, Settings.WIDTH, (int)(Settings.HEIGHT * 0.25F));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 480 */     this.slot = slot;
/* 481 */     this.newSave = isNewSave;
/* 482 */     this.shown = true;
/* 483 */     textField = "";
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\panels\RenamePopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */