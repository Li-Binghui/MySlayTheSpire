/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
/*     */ 
/*     */ public class InputSettingsScreen
/*     */   implements RemapInputElementListener, HitboxListener, ScrollBarListener
/*     */ {
/*  30 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("InputSettingsScreen");
/*  31 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */ 
/*     */   
/*  34 */   private static final String TAB_HEADER = TEXT[0];
/*  35 */   private static final String GAME_SETTINNGS_TAB_HEADER = TEXT[1];
/*  36 */   private static final String RETURN_BUTTON_TEXT = TEXT[2];
/*     */   
/*     */   private static final boolean ALLOW_OVERSCROLL = false;
/*     */   private static final int BG_RAW_WIDTH = 1920;
/*     */   private static final int BG_RAW_HEIGHT = 1080;
/*     */   private static final float SHOW_X = 0.0F;
/*  42 */   private static final float HIDE_X = -1100.0F * Settings.scale;
/*     */   
/*  44 */   private static final float GAME_SETTINGS_BUTTON_WIDTH = 360.0F * Settings.scale;
/*  45 */   private static final float ROW_X_POSITION = Settings.WIDTH / 2.0F - 37.0F * Settings.scale;
/*  46 */   private static final float ROW_TABLE_VERTICAL_EXTRA_PADDING = 10.0F * Settings.scale;
/*  47 */   private static final float ROW_PADDING = 0.0F * Settings.scale;
/*  48 */   private static final float ROW_Y_DIFF = RemapInputElement.ROW_HEIGHT + ROW_PADDING;
/*     */   
/*  50 */   private static final float SCROLL_CONTAINER_VISIBLE_HEIGHT = 651.0F * Settings.scale;
/*  51 */   private static final float SCROLL_CONTAINER_BOTTOM = Settings.OPTION_Y - 360.0F * Settings.scale;
/*  52 */   private static final float SCROLL_CONTAINER_TOP = SCROLL_CONTAINER_BOTTOM + SCROLL_CONTAINER_VISIBLE_HEIGHT;
/*     */   
/*  54 */   private static final float SCROLLBAR_X = Settings.WIDTH / 2.0F + 576.0F * Settings.scale;
/*  55 */   private static final float SCROLLBAR_Y = (Settings.HEIGHT / 2) - 61.0F * Settings.scale;
/*  56 */   private static final float SCROLLBAR_HEIGHT = 584.0F * Settings.scale;
/*     */   
/*  58 */   private static final float RESET_BUTTON_CY = (Settings.isSixteenByTen ? 100.0F : 70.0F) * Settings.scale;
/*     */ 
/*     */   
/*  61 */   public MenuCancelButton button = new MenuCancelButton();
/*  62 */   private ScrollBar scrollBar = new ScrollBar(this, SCROLLBAR_X, SCROLLBAR_Y, SCROLLBAR_HEIGHT);
/*  63 */   private Hitbox resetButtonHb = new Hitbox(300.0F * Settings.scale, 72.0F * Settings.scale);
/*  64 */   private ArrayList<RemapInputElement> elements = new ArrayList<>();
/*  65 */   private Hitbox gameSettingsHb = new Hitbox(GAME_SETTINGS_BUTTON_WIDTH, Settings.scale * 72.0F);
/*     */   
/*     */   private GiantToggleButton controllerEnabledToggleButton;
/*  68 */   private GiantToggleButton touchscreenModeButton = null;
/*     */ 
/*     */   
/*  71 */   private float screenX = HIDE_X, targetX = HIDE_X;
/*     */   private float maxScrollAmount;
/*  73 */   private float targetScrollOffset = 0.0F;
/*  74 */   private float visibleScrollOffset = 0.0F;
/*     */   
/*     */   public boolean screenUp = false;
/*     */   
/*  78 */   private Hitbox elementHb = null;
/*     */   
/*     */   public InputSettingsScreen() {
/*  81 */     if (Settings.isConsoleBuild) {
/*  82 */       this.controllerEnabledToggleButton = new GiantToggleButton(GiantToggleButton.ToggleType.CONTROLLER_ENABLED, Settings.WIDTH * 0.6F, RESET_BUTTON_CY, TEXT[48]);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       this.resetButtonHb.move(Settings.WIDTH * 0.35F, RESET_BUTTON_CY);
/*     */     } else {
/*  89 */       this.controllerEnabledToggleButton = new GiantToggleButton(GiantToggleButton.ToggleType.CONTROLLER_ENABLED, Settings.WIDTH * 0.42F, RESET_BUTTON_CY, TEXT[48]);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       this.touchscreenModeButton = new GiantToggleButton(GiantToggleButton.ToggleType.TOUCHSCREEN_ENABLED, Settings.WIDTH * 0.65F, RESET_BUTTON_CY, TEXT[49]);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       this.resetButtonHb.move(Settings.WIDTH * 0.25F, RESET_BUTTON_CY);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void open() {
/* 104 */     open(true);
/*     */   }
/*     */   
/*     */   public void open(boolean animated) {
/* 108 */     this.targetX = 0.0F;
/* 109 */     this.targetScrollOffset = 0.0F;
/* 110 */     this.visibleScrollOffset = 0.0F;
/*     */     
/* 112 */     if (animated) {
/* 113 */       this.button.show(RETURN_BUTTON_TEXT);
/*     */     } else {
/* 115 */       this.button.showInstantly(RETURN_BUTTON_TEXT);
/*     */     } 
/*     */     
/* 118 */     this.screenUp = true;
/* 119 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.INPUT_SETTINGS;
/* 120 */     refreshData();
/* 121 */     this.gameSettingsHb.move(Settings.WIDTH / 2.0F - 438.0F * Settings.scale, Settings.OPTION_Y + 382.0F * Settings.scale);
/* 122 */     this.scrollBar.isBackgroundVisible = false;
/* 123 */     this.scrollBar.setCenter(SCROLLBAR_X, SCROLLBAR_Y);
/*     */     
/* 125 */     if (CardCrawlGame.isInARun()) {
/* 126 */       AbstractDungeon.screen = AbstractDungeon.CurrentScreen.INPUT_SETTINGS;
/*     */     }
/*     */   }
/*     */   
/*     */   private void refreshData() {
/* 131 */     this.elementHb = null;
/* 132 */     this.elements.clear();
/* 133 */     this.elements.add(new RemapInputElementHeader(TEXT[3], TEXT[4], TEXT[5]));
/*     */     
/* 135 */     if (Settings.isControllerMode) {
/* 136 */       this.elements.add(new RemapInputElement(this, TEXT[28], InputActionSet.confirm, CInputActionSet.select));
/* 137 */       this.elements.add(new RemapInputElement(this, TEXT[29], InputActionSet.cancel, CInputActionSet.cancel));
/* 138 */       this.elements.add(new RemapInputElement(this, TEXT[30], null, CInputActionSet.topPanel));
/* 139 */       this.elements.add(new RemapInputElement(this, TEXT[31], InputActionSet.endTurn, CInputActionSet.proceed));
/*     */       
/* 141 */       this.elements.add(new RemapInputElement(this, TEXT[32], InputActionSet.masterDeck, CInputActionSet.pageLeftViewDeck));
/*     */       
/* 143 */       this.elements.add(new RemapInputElement(this, TEXT[33], InputActionSet.exhaustPile, CInputActionSet.pageRightViewExhaust));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       this.elements.add(new RemapInputElement(this, TEXT[34], InputActionSet.map, CInputActionSet.map));
/* 150 */       this.elements.add(new RemapInputElement(this, TEXT[35], null, CInputActionSet.settings));
/* 151 */       this.elements.add(new RemapInputElement(this, TEXT[36], InputActionSet.drawPile, CInputActionSet.drawPile));
/* 152 */       this.elements.add(new RemapInputElement(this, TEXT[37], InputActionSet.discardPile, CInputActionSet.discardPile));
/*     */       
/* 154 */       this.elements.add(new RemapInputElement(this, TEXT[38], InputActionSet.up, CInputActionSet.up));
/* 155 */       this.elements.add(new RemapInputElement(this, TEXT[39], InputActionSet.down, CInputActionSet.down));
/* 156 */       this.elements.add(new RemapInputElement(this, TEXT[40], InputActionSet.left, CInputActionSet.left));
/* 157 */       this.elements.add(new RemapInputElement(this, TEXT[41], InputActionSet.right, CInputActionSet.right));
/* 158 */       this.elements.add(new RemapInputElement(this, TEXT[42], null, CInputActionSet.altUp));
/* 159 */       this.elements.add(new RemapInputElement(this, TEXT[43], null, CInputActionSet.altDown));
/* 160 */       this.elements.add(new RemapInputElement(this, TEXT[44], null, CInputActionSet.altLeft));
/* 161 */       this.elements.add(new RemapInputElement(this, TEXT[45], null, CInputActionSet.altRight));
/*     */     } else {
/* 163 */       this.elements.add(new RemapInputElement(this, TEXT[7], InputActionSet.confirm, CInputActionSet.select));
/* 164 */       this.elements.add(new RemapInputElement(this, TEXT[8], InputActionSet.cancel, CInputActionSet.cancel));
/*     */ 
/*     */       
/* 167 */       this.elements.add(new RemapInputElement(this, TEXT[9], InputActionSet.map, CInputActionSet.map));
/* 168 */       this.elements.add(new RemapInputElement(this, TEXT[10], InputActionSet.masterDeck, CInputActionSet.pageLeftViewDeck));
/*     */       
/* 170 */       this.elements.add(new RemapInputElement(this, TEXT[11], InputActionSet.drawPile, CInputActionSet.drawPile));
/* 171 */       this.elements.add(new RemapInputElement(this, TEXT[12], InputActionSet.discardPile, CInputActionSet.discardPile));
/*     */       
/* 173 */       this.elements.add(new RemapInputElement(this, TEXT[13], InputActionSet.exhaustPile, CInputActionSet.pageRightViewExhaust));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       this.elements.add(new RemapInputElement(this, TEXT[14], InputActionSet.endTurn, CInputActionSet.proceed));
/* 180 */       this.elements.add(new RemapInputElement(this, TEXT[50], InputActionSet.peek, CInputActionSet.drawPile));
/*     */       
/* 182 */       this.elements.add(new RemapInputElement(this, TEXT[38], InputActionSet.up, CInputActionSet.up));
/* 183 */       this.elements.add(new RemapInputElement(this, TEXT[39], InputActionSet.down, CInputActionSet.down));
/* 184 */       this.elements.add(new RemapInputElement(this, TEXT[15], InputActionSet.left, CInputActionSet.left));
/* 185 */       this.elements.add(new RemapInputElement(this, TEXT[16], InputActionSet.right, CInputActionSet.right));
/* 186 */       this.elements.add(new RemapInputElement(this, TEXT[17], InputActionSet.selectCard_1));
/* 187 */       this.elements.add(new RemapInputElement(this, TEXT[18], InputActionSet.selectCard_2));
/* 188 */       this.elements.add(new RemapInputElement(this, TEXT[19], InputActionSet.selectCard_3));
/* 189 */       this.elements.add(new RemapInputElement(this, TEXT[20], InputActionSet.selectCard_4));
/* 190 */       this.elements.add(new RemapInputElement(this, TEXT[21], InputActionSet.selectCard_5));
/* 191 */       this.elements.add(new RemapInputElement(this, TEXT[22], InputActionSet.selectCard_6));
/* 192 */       this.elements.add(new RemapInputElement(this, TEXT[23], InputActionSet.selectCard_7));
/* 193 */       this.elements.add(new RemapInputElement(this, TEXT[24], InputActionSet.selectCard_8));
/* 194 */       this.elements.add(new RemapInputElement(this, TEXT[25], InputActionSet.selectCard_9));
/* 195 */       this.elements.add(new RemapInputElement(this, TEXT[26], InputActionSet.selectCard_10));
/* 196 */       this.elements.add(new RemapInputElement(this, TEXT[27], InputActionSet.releaseCard));
/*     */     } 
/*     */     
/* 199 */     this.maxScrollAmount = ROW_Y_DIFF * this.elements.size() + 2.0F * ROW_TABLE_VERTICAL_EXTRA_PADDING - SCROLL_CONTAINER_VISIBLE_HEIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 204 */     updateControllerInput();
/* 205 */     this.button.update();
/* 206 */     this.controllerEnabledToggleButton.update();
/* 207 */     if (!Settings.isConsoleBuild) {
/* 208 */       this.touchscreenModeButton.update();
/*     */     }
/* 210 */     updateScrolling();
/*     */     
/* 212 */     if (this.button.hb.clicked || InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) {
/* 213 */       if (CardCrawlGame.isInARun()) {
/* 214 */         AbstractDungeon.closeCurrentScreen();
/*     */       } else {
/* 216 */         InputHelper.pressedEscape = false;
/* 217 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
/*     */       } 
/* 219 */       hide();
/*     */     } 
/*     */     
/* 222 */     updateKeyPositions();
/* 223 */     for (RemapInputElement element : this.elements) {
/* 224 */       element.update();
/*     */     }
/*     */     
/* 227 */     if (Settings.isControllerMode && 
/* 228 */       CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/* 229 */       clicked(this.gameSettingsHb);
/*     */     }
/*     */ 
/*     */     
/* 233 */     this.resetButtonHb.encapsulatedUpdate(this);
/* 234 */     this.gameSettingsHb.encapsulatedUpdate(this);
/* 235 */     this.screenX = MathHelper.uiLerpSnap(this.screenX, this.targetX);
/* 236 */     if (this.elementHb != null && Settings.isControllerMode) {
/* 237 */       CInputHelper.setCursor(this.elementHb);
/*     */     }
/* 239 */     this.scrollBar.update();
/*     */   }
/*     */   
/*     */   private enum HighlightType {
/* 243 */     INPUT, RESET, CONTROLLER_ON_TOGGLE, TOUCHSCREEN_ON_TOGGLE;
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 247 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 251 */     HighlightType type = HighlightType.INPUT;
/* 252 */     boolean anyHovered = false;
/* 253 */     int index = 0;
/* 254 */     for (RemapInputElement e : this.elements) {
/* 255 */       e.hb.update();
/* 256 */       if (e.hb.hovered) {
/* 257 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 260 */       index++;
/*     */     } 
/*     */     
/* 263 */     if (this.resetButtonHb.hovered) {
/* 264 */       type = HighlightType.RESET;
/* 265 */       anyHovered = true;
/* 266 */     } else if (this.controllerEnabledToggleButton.hb.hovered) {
/* 267 */       type = HighlightType.CONTROLLER_ON_TOGGLE;
/* 268 */       anyHovered = true;
/* 269 */     } else if (this.touchscreenModeButton.hb.hovered) {
/* 270 */       type = HighlightType.TOUCHSCREEN_ON_TOGGLE;
/* 271 */       anyHovered = true;
/*     */     } 
/*     */     
/* 274 */     if (!anyHovered) {
/* 275 */       index = 1;
/* 276 */       CInputHelper.setCursor(((RemapInputElement)this.elements.get(1)).hb);
/*     */     } else {
/* 278 */       switch (type) {
/*     */         case INPUT:
/* 280 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 281 */             index--;
/* 282 */             if (index < 1) {
/* 283 */               index = 1;
/*     */             }
/* 285 */             CInputHelper.setCursor(((RemapInputElement)this.elements.get(index)).hb);
/* 286 */             this.elementHb = ((RemapInputElement)this.elements.get(index)).hb; break;
/* 287 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 288 */             index++;
/* 289 */             if (index > this.elements.size() - 1) {
/* 290 */               CInputHelper.setCursor(this.resetButtonHb);
/* 291 */               this.elementHb = this.resetButtonHb; break;
/*     */             } 
/* 293 */             CInputHelper.setCursor(((RemapInputElement)this.elements.get(index)).hb);
/* 294 */             this.elementHb = ((RemapInputElement)this.elements.get(index)).hb;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case RESET:
/* 299 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 300 */             CInputHelper.setCursor(((RemapInputElement)this.elements.get(this.elements.size() - 1)).hb);
/* 301 */             this.elementHb = ((RemapInputElement)this.elements.get(this.elements.size() - 1)).hb; break;
/* 302 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 303 */             CInputHelper.setCursor(this.controllerEnabledToggleButton.hb);
/* 304 */             this.elementHb = this.controllerEnabledToggleButton.hb; break;
/* 305 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 306 */             CInputHelper.setCursor(this.touchscreenModeButton.hb);
/* 307 */             this.elementHb = this.touchscreenModeButton.hb;
/*     */           } 
/*     */           break;
/*     */         case CONTROLLER_ON_TOGGLE:
/* 311 */           if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 312 */             CInputHelper.setCursor(((RemapInputElement)this.elements.get(this.elements.size() - 1)).hb);
/* 313 */             this.elementHb = ((RemapInputElement)this.elements.get(this.elements.size() - 1)).hb; break;
/* 314 */           }  if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 315 */             CInputHelper.setCursor(this.resetButtonHb);
/* 316 */             this.elementHb = this.resetButtonHb;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 324 */     updateControllerScrolling();
/*     */   }
/*     */   
/*     */   private void updateControllerScrolling() {
/* 328 */     if (Gdx.input.getY() > Settings.HEIGHT * 0.65F) {
/* 329 */       this.targetScrollOffset += Settings.SCROLL_SPEED / 3.0F;
/* 330 */       if (this.targetScrollOffset > this.maxScrollAmount) {
/* 331 */         this.targetScrollOffset = this.maxScrollAmount;
/*     */       }
/* 333 */     } else if (Gdx.input.getY() < Settings.HEIGHT * 0.35F) {
/* 334 */       this.targetScrollOffset -= Settings.SCROLL_SPEED / 3.0F;
/* 335 */       if (this.targetScrollOffset < 0.0F) {
/* 336 */         this.targetScrollOffset = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 343 */     float targetScrollOffset = this.targetScrollOffset;
/* 344 */     if (InputHelper.scrolledDown) {
/* 345 */       targetScrollOffset += Settings.SCROLL_SPEED * 3.0F;
/* 346 */     } else if (InputHelper.scrolledUp) {
/* 347 */       targetScrollOffset -= Settings.SCROLL_SPEED * 3.0F;
/*     */     } 
/*     */     
/* 350 */     if (targetScrollOffset != this.targetScrollOffset) {
/* 351 */       this.targetScrollOffset = MathHelper.scrollSnapLerpSpeed(this.targetScrollOffset, targetScrollOffset);
/*     */       
/* 353 */       this.targetScrollOffset = MathUtils.clamp(this.targetScrollOffset, 0.0F, this.maxScrollAmount);
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
/* 364 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   public void hide() {
/* 368 */     CardCrawlGame.sound.play("DECK_CLOSE", 0.1F);
/* 369 */     this.targetX = HIDE_X;
/* 370 */     this.button.hide();
/* 371 */     this.screenUp = false;
/* 372 */     InputActionSet.save();
/* 373 */     CInputActionSet.save();
/* 374 */     CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 378 */     sb.setColor(Color.WHITE);
/* 379 */     renderFullscreenBackground(sb, ImageMaster.INPUT_SETTINGS_BG);
/*     */     
/* 381 */     for (RemapInputElement element : this.elements) {
/* 382 */       element.render(sb);
/*     */     }
/*     */     
/* 385 */     renderResetDefaultButton(sb);
/* 386 */     renderFullscreenBackground(sb, ImageMaster.INPUT_SETTINGS_EDGES);
/*     */     
/* 388 */     this.scrollBar.render(sb);
/* 389 */     renderHeader(sb);
/*     */     
/* 391 */     if (Settings.isControllerMode) {
/* 392 */       sb.draw(CInputActionSet.pageLeftViewDeck
/* 393 */           .getKeyImg(), this.gameSettingsHb.cX - 32.0F - 
/* 394 */           FontHelper.getSmartWidth(FontHelper.panelNameFont, GAME_SETTINNGS_TAB_HEADER, 99999.0F, 2.0F) / 2.0F - 42.0F * Settings.scale, Settings.OPTION_Y - 32.0F + 379.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/*     */ 
/*     */     
/* 415 */     this.controllerEnabledToggleButton.render(sb);
/* 416 */     if (!Settings.isConsoleBuild) {
/* 417 */       this.touchscreenModeButton.render(sb);
/*     */     }
/* 419 */     this.button.render(sb);
/*     */   }
/*     */   
/*     */   private void updateKeyPositions() {
/* 423 */     float x = ROW_X_POSITION;
/* 424 */     float y = SCROLL_CONTAINER_TOP - ROW_TABLE_VERTICAL_EXTRA_PADDING - ROW_Y_DIFF / 2.0F;
/* 425 */     if (Settings.isSixteenByTen) {
/* 426 */       y -= Settings.scale * 4.0F;
/*     */     }
/*     */     
/* 429 */     this.visibleScrollOffset = MathHelper.scrollSnapLerpSpeed(this.visibleScrollOffset, this.targetScrollOffset);
/* 430 */     y += this.visibleScrollOffset;
/*     */     
/* 432 */     float maxVisibleY = SCROLL_CONTAINER_TOP - Settings.scale * 10.0F;
/* 433 */     float minVisibleY = SCROLL_CONTAINER_BOTTOM - RemapInputElement.ROW_HEIGHT - Settings.scale * 10.0F;
/*     */     
/* 435 */     for (RemapInputElement element : this.elements) {
/* 436 */       element.move(x, y);
/* 437 */       boolean isInView = (minVisibleY < element.hb.y && element.hb.y < maxVisibleY);
/* 438 */       element.isHidden = !isInView;
/* 439 */       y -= ROW_Y_DIFF;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderHeader(SpriteBatch sb) {
/* 444 */     Color textColor = this.gameSettingsHb.hovered ? Settings.GOLD_COLOR : Color.LIGHT_GRAY;
/* 445 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, GAME_SETTINNGS_TAB_HEADER, this.gameSettingsHb.cX, this.gameSettingsHb.cY, textColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 452 */     this.gameSettingsHb.render(sb);
/*     */     
/* 454 */     FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TAB_HEADER, this.gameSettingsHb.cX + 396.0F * Settings.scale, this.gameSettingsHb.cY, Settings.GOLD_COLOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFullscreenBackground(SpriteBatch sb, Texture image) {
/* 464 */     sb.draw(image, Settings.WIDTH / 2.0F - 960.0F, Settings.OPTION_Y - 540.0F, 960.0F, 540.0F, 1920.0F, 1080.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1920, 1080, false, false);
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
/*     */   private void renderResetDefaultButton(SpriteBatch sb) {
/* 485 */     Color color = this.resetButtonHb.hovered ? Settings.GREEN_TEXT_COLOR : Settings.CREAM_COLOR;
/* 486 */     FontHelper.renderFontCentered(sb, FontHelper.panelEndTurnFont, TEXT[6], this.resetButtonHb.cX, this.resetButtonHb.cY, color);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     this.resetButtonHb.render(sb);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void didStartRemapping(RemapInputElement element) {
/* 504 */     for (RemapInputElement e : this.elements) {
/* 505 */       e.hasInputFocus = (e == element);
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
/*     */   public boolean willRemap(RemapInputElement element, int oldKey, int newKey) {
/* 522 */     if (oldKey == newKey)
/*     */     {
/* 524 */       return true;
/*     */     }
/* 526 */     for (RemapInputElement e : this.elements) {
/*     */       
/* 528 */       if (e.action != null && e.action.getKey() == newKey) {
/* 529 */         e.action.remap(oldKey);
/*     */       }
/*     */     } 
/* 532 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean willRemapController(RemapInputElement element, int oldCode, int newCode) {
/* 537 */     if (oldCode == newCode) {
/* 538 */       return true;
/*     */     }
/* 540 */     for (RemapInputElement e : this.elements) {
/* 541 */       if (e.cAction != null && e.cAction.getKey() == newCode) {
/* 542 */         e.cAction.remap(oldCode);
/*     */       }
/*     */     } 
/* 545 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void hoverStarted(Hitbox hitbox) {
/* 551 */     CardCrawlGame.sound.play("UI_HOVER");
/*     */   }
/*     */ 
/*     */   
/*     */   public void startClicking(Hitbox hitbox) {
/* 556 */     CardCrawlGame.sound.play("UI_CLICK_1");
/*     */   }
/*     */ 
/*     */   
/*     */   public void clicked(Hitbox hb) {
/* 561 */     if (hb == this.resetButtonHb) {
/* 562 */       CardCrawlGame.sound.play("END_TURN");
/* 563 */       InputActionSet.resetToDefaults();
/* 564 */       CInputActionSet.resetToDefaults();
/* 565 */       refreshData();
/* 566 */       updateKeyPositions();
/* 567 */     } else if (hb == this.gameSettingsHb) {
/* 568 */       if (CardCrawlGame.isInARun()) {
/* 569 */         AbstractDungeon.settingsScreen.open(false);
/*     */       } else {
/*     */         
/* 572 */         CardCrawlGame.sound.play("END_TURN");
/* 573 */         CardCrawlGame.mainMenuScreen.isSettingsUp = true;
/* 574 */         InputHelper.pressedEscape = false;
/* 575 */         CardCrawlGame.mainMenuScreen.statsScreen.hide();
/* 576 */         CardCrawlGame.mainMenuScreen.cancelButton.hide();
/* 577 */         CardCrawlGame.cancelButton.showInstantly(MainMenuScreen.TEXT[2]);
/* 578 */         CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.SETTINGS;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 587 */     this.targetScrollOffset = MathHelper.valueFromPercentBetween(0.0F, this.maxScrollAmount, newPercent);
/* 588 */     this.scrollBar.parentScrolledToPercent(newPercent);
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 592 */     float percent = MathHelper.percentFromValueBetween(0.0F, this.maxScrollAmount, this.targetScrollOffset);
/* 593 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\InputSettingsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */