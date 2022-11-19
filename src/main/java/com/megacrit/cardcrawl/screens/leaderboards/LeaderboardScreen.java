/*     */ package com.megacrit.cardcrawl.screens.leaderboards;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LeaderboardScreen
/*     */ {
/*  29 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("LeaderboardsScreen");
/*  30 */   public static final String[] TEXT = uiStrings.TEXT;
/*  31 */   public MenuCancelButton button = new MenuCancelButton();
/*     */   public boolean screenUp = false;
/*  33 */   public ArrayList<LeaderboardEntry> entries = new ArrayList<>(); public boolean waiting = true; public boolean refresh = true;
/*  34 */   public ArrayList<FilterButton> charButtons = new ArrayList<>();
/*  35 */   public ArrayList<FilterButton> regionButtons = new ArrayList<>();
/*  36 */   public ArrayList<FilterButton> typeButtons = new ArrayList<>();
/*  37 */   public static final float RANK_X = 1000.0F * Settings.scale;
/*  38 */   public static final float NAME_X = 1160.0F * Settings.scale;
/*  39 */   public static final float SCORE_X = 1500.0F * Settings.scale;
/*  40 */   public String charLabel = TEXT[2]; public String regionLabel = TEXT[3]; public String typeLabel = TEXT[4]; public int currentStartIndex;
/*     */   public int currentEndIndex;
/*  42 */   private static final float FILTER_SPACING_X = 100.0F * Settings.scale; private Hitbox prevHb;
/*     */   private Hitbox nextHb;
/*  44 */   private Hitbox viewMyScoreHb = new Hitbox(250.0F * Settings.scale, 80.0F * Settings.scale);
/*     */   
/*     */   public boolean viewMyScore = false;
/*  47 */   private float lineFadeInTimer = 0.0F;
/*  48 */   private static final float LINE_THICKNESS = 4.0F * Settings.scale;
/*     */   
/*     */   public LeaderboardScreen() {
/*  51 */     this.prevHb = new Hitbox(110.0F * Settings.scale, 110.0F * Settings.scale);
/*  52 */     this.prevHb.move(880.0F * Settings.scale, 530.0F * Settings.scale);
/*  53 */     this.nextHb = new Hitbox(110.0F * Settings.scale, 110.0F * Settings.scale);
/*  54 */     this.nextHb.move(1740.0F * Settings.scale, 530.0F * Settings.scale);
/*  55 */     this.viewMyScoreHb.move(1300.0F * Settings.scale, 80.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  59 */     updateControllerInput();
/*     */     
/*  61 */     for (FilterButton b : this.charButtons) {
/*  62 */       b.update();
/*     */     }
/*     */     
/*  65 */     for (FilterButton b : this.regionButtons) {
/*  66 */       b.update();
/*     */     }
/*     */     
/*  69 */     for (FilterButton b : this.typeButtons) {
/*  70 */       b.update();
/*     */     }
/*     */     
/*  73 */     this.button.update();
/*     */     
/*  75 */     if (this.button.hb.clicked || InputHelper.pressedEscape) {
/*  76 */       InputHelper.pressedEscape = false;
/*  77 */       hide();
/*     */     } 
/*     */     
/*  80 */     if (!this.entries.isEmpty() && !this.waiting) {
/*  81 */       this.lineFadeInTimer = MathHelper.slowColorLerpSnap(this.lineFadeInTimer, 1.0F);
/*     */     }
/*     */     
/*  84 */     if (this.refresh) {
/*  85 */       this.refresh = false;
/*  86 */       this.waiting = true;
/*  87 */       this.lineFadeInTimer = 0.0F;
/*  88 */       this.currentStartIndex = 1;
/*  89 */       this.currentEndIndex = 20;
/*  90 */       getData(this.currentStartIndex, this.currentEndIndex);
/*     */     } 
/*     */     
/*  93 */     updateViewMyScore();
/*  94 */     updateArrows();
/*     */   }
/*     */   
/*     */   private enum LeaderboardSelectionType {
/*  98 */     NONE, CHAR, REGION, TYPE;
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 102 */     if (!Settings.isControllerMode) {
/*     */       return;
/*     */     }
/*     */     
/* 106 */     LeaderboardSelectionType type = LeaderboardSelectionType.NONE;
/* 107 */     boolean anyHovered = false;
/* 108 */     int index = 0;
/*     */     
/* 110 */     for (FilterButton b : this.charButtons) {
/* 111 */       if (b.hb.hovered) {
/* 112 */         anyHovered = true;
/* 113 */         type = LeaderboardSelectionType.CHAR;
/*     */         break;
/*     */       } 
/* 116 */       index++;
/*     */     } 
/*     */     
/* 119 */     if (!anyHovered) {
/* 120 */       index = 0;
/* 121 */       for (FilterButton b : this.regionButtons) {
/* 122 */         if (b.hb.hovered) {
/* 123 */           anyHovered = true;
/* 124 */           type = LeaderboardSelectionType.REGION;
/*     */           break;
/*     */         } 
/* 127 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (!anyHovered) {
/* 132 */       index = 0;
/* 133 */       for (FilterButton b : this.typeButtons) {
/* 134 */         if (b.hb.hovered) {
/* 135 */           anyHovered = true;
/* 136 */           type = LeaderboardSelectionType.TYPE;
/*     */           break;
/*     */         } 
/* 139 */         index++;
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     if (!anyHovered) {
/* 144 */       Gdx.input.setCursorPosition(
/* 145 */           (int)((FilterButton)this.charButtons.get(0)).hb.cX, Settings.HEIGHT - 
/* 146 */           (int)((FilterButton)this.charButtons.get(0)).hb.cY);
/*     */     } else {
/* 148 */       switch (type) {
/*     */         case CHAR:
/* 150 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 151 */             index--;
/* 152 */             if (index < 0) {
/*     */               return;
/*     */             }
/* 155 */             Gdx.input.setCursorPosition(
/* 156 */                 (int)((FilterButton)this.charButtons.get(index)).hb.cX, Settings.HEIGHT - 
/* 157 */                 (int)((FilterButton)this.charButtons.get(index)).hb.cY); break;
/* 158 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 159 */             index++;
/* 160 */             if (index > this.charButtons.size() - 1) {
/*     */               return;
/*     */             }
/* 163 */             Gdx.input.setCursorPosition(
/* 164 */                 (int)((FilterButton)this.charButtons.get(index)).hb.cX, Settings.HEIGHT - 
/* 165 */                 (int)((FilterButton)this.charButtons.get(index)).hb.cY); break;
/* 166 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 167 */             if (index > this.regionButtons.size() - 1) {
/* 168 */               index = this.regionButtons.size() - 1;
/*     */             }
/* 170 */             Gdx.input.setCursorPosition(
/* 171 */                 (int)((FilterButton)this.regionButtons.get(index)).hb.cX, Settings.HEIGHT - 
/* 172 */                 (int)((FilterButton)this.regionButtons.get(index)).hb.cY);
/*     */           } 
/*     */           break;
/*     */         case REGION:
/* 176 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 177 */             index--;
/* 178 */             if (index < 0) {
/*     */               return;
/*     */             }
/* 181 */             CInputHelper.setCursor(((FilterButton)this.regionButtons.get(index)).hb); break;
/* 182 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 183 */             index++;
/* 184 */             if (index > this.regionButtons.size() - 1) {
/*     */               return;
/*     */             }
/* 187 */             CInputHelper.setCursor(((FilterButton)this.regionButtons.get(index)).hb); break;
/* 188 */           }  if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 189 */             if (index > this.typeButtons.size() - 1) {
/* 190 */               index = this.typeButtons.size() - 1;
/*     */             }
/* 192 */             CInputHelper.setCursor(((FilterButton)this.typeButtons.get(index)).hb); break;
/* 193 */           }  if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 194 */             if (index > this.charButtons.size() - 1) {
/* 195 */               index = this.charButtons.size() - 1;
/*     */             }
/* 197 */             CInputHelper.setCursor(((FilterButton)this.charButtons.get(index)).hb);
/*     */           } 
/*     */           break;
/*     */         case TYPE:
/* 201 */           if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 202 */             index--;
/* 203 */             if (index < 0) {
/*     */               return;
/*     */             }
/* 206 */             CInputHelper.setCursor(((FilterButton)this.typeButtons.get(index)).hb); break;
/* 207 */           }  if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 208 */             index++;
/* 209 */             if (index > this.typeButtons.size() - 1) {
/*     */               return;
/*     */             }
/* 212 */             CInputHelper.setCursor(((FilterButton)this.typeButtons.get(index)).hb); break;
/* 213 */           }  if (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) {
/* 214 */             if (index > this.regionButtons.size() - 1) {
/* 215 */               index = this.regionButtons.size() - 1;
/*     */             }
/* 217 */             CInputHelper.setCursor(((FilterButton)this.regionButtons.get(index)).hb);
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateViewMyScore() {
/* 228 */     if (((FilterButton)this.regionButtons.get(0)).active) {
/*     */       return;
/*     */     }
/*     */     
/* 232 */     this.viewMyScoreHb.update();
/*     */     
/* 234 */     if (this.viewMyScoreHb.justHovered) {
/* 235 */       CardCrawlGame.sound.play("UI_HOVER");
/* 236 */     } else if (this.viewMyScoreHb.hovered && InputHelper.justClickedLeft) {
/* 237 */       this.viewMyScoreHb.clickStarted = true;
/* 238 */       CardCrawlGame.sound.play("UI_CLICK_1");
/* 239 */     } else if (this.viewMyScoreHb.clicked || CInputActionSet.topPanel.isJustPressed()) {
/* 240 */       this.viewMyScoreHb.clicked = false;
/* 241 */       this.viewMyScore = true;
/* 242 */       this.waiting = true;
/* 243 */       getData(this.currentStartIndex, this.currentEndIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateArrows() {
/* 248 */     if (this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 252 */     if (this.entries.size() == 20) {
/* 253 */       this.nextHb.update();
/* 254 */       if (this.nextHb.justHovered) {
/* 255 */         CardCrawlGame.sound.play("UI_HOVER");
/* 256 */       } else if (this.nextHb.hovered && InputHelper.justClickedLeft) {
/* 257 */         this.nextHb.clickStarted = true;
/* 258 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 259 */       } else if (this.nextHb.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
/* 260 */         this.nextHb.clicked = false;
/* 261 */         this.currentStartIndex = this.currentEndIndex + 1;
/* 262 */         this.currentEndIndex = this.currentStartIndex + 19;
/* 263 */         this.waiting = true;
/* 264 */         getData(this.currentStartIndex, this.currentEndIndex);
/*     */       } 
/*     */     } 
/*     */     
/* 268 */     if (this.currentStartIndex != 1) {
/* 269 */       this.prevHb.update();
/* 270 */       if (this.prevHb.justHovered) {
/* 271 */         CardCrawlGame.sound.play("UI_HOVER");
/* 272 */       } else if (this.prevHb.hovered && InputHelper.justClickedLeft) {
/* 273 */         this.prevHb.clickStarted = true;
/* 274 */         CardCrawlGame.sound.play("UI_CLICK_1");
/* 275 */       } else if (this.prevHb.clicked || CInputActionSet.pageLeftViewDeck.isJustPressed()) {
/* 276 */         this.prevHb.clicked = false;
/* 277 */         this.currentStartIndex -= 20;
/* 278 */         if (this.currentStartIndex < 1) {
/* 279 */           this.currentStartIndex = 1;
/*     */         }
/* 281 */         this.currentEndIndex = this.currentStartIndex + 19;
/* 282 */         this.waiting = true;
/* 283 */         getData(this.currentStartIndex, this.currentEndIndex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getData(int startIndex, int endIndex) {
/* 289 */     AbstractPlayer.PlayerClass tmpClass = null;
/* 290 */     FilterButton.RegionSetting rSetting = null;
/* 291 */     FilterButton.LeaderboardType lType = null;
/*     */     
/* 293 */     for (FilterButton b : this.charButtons) {
/* 294 */       if (b.active) {
/* 295 */         tmpClass = b.pClass;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 300 */     for (FilterButton b : this.regionButtons) {
/* 301 */       if (b.active) {
/* 302 */         rSetting = b.rType;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 307 */     for (FilterButton b : this.typeButtons) {
/* 308 */       if (b.active) {
/* 309 */         lType = b.lType;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 314 */     if (tmpClass != null && rSetting != null && lType != null) {
/* 315 */       CardCrawlGame.publisherIntegration.getLeaderboardEntries(tmpClass, rSetting, lType, startIndex, endIndex);
/*     */     }
/*     */   }
/*     */   
/*     */   public void open() {
/* 320 */     CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.LEADERBOARD;
/* 321 */     if (this.charButtons.isEmpty()) {
/* 322 */       this.charButtons.add(new FilterButton("ironclad.png", true, AbstractPlayer.PlayerClass.IRONCLAD));
/* 323 */       this.charButtons.add(new FilterButton("silent.png", false, AbstractPlayer.PlayerClass.THE_SILENT));
/* 324 */       if (!UnlockTracker.isCharacterLocked("Defect")) {
/* 325 */         this.charButtons.add(new FilterButton("defect.png", false, AbstractPlayer.PlayerClass.DEFECT));
/*     */       }
/* 327 */       if (!UnlockTracker.isCharacterLocked("Watcher")) {
/* 328 */         this.charButtons.add(new FilterButton("watcher.png", false, AbstractPlayer.PlayerClass.WATCHER));
/*     */       }
/*     */       
/* 331 */       this.regionButtons.add(new FilterButton("friends.png", true, FilterButton.RegionSetting.FRIEND));
/* 332 */       this.regionButtons.add(new FilterButton("global.png", false, FilterButton.RegionSetting.GLOBAL));
/*     */       
/* 334 */       this.typeButtons.add(new FilterButton("score.png", true, FilterButton.LeaderboardType.HIGH_SCORE));
/* 335 */       this.typeButtons.add(new FilterButton("chain.png", false, FilterButton.LeaderboardType.CONSECUTIVE_WINS));
/* 336 */       this.typeButtons.add(new FilterButton("time.png", false, FilterButton.LeaderboardType.FASTEST_WIN));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     this.button.show(TEXT[0]);
/* 343 */     this.screenUp = true;
/*     */   }
/*     */   
/*     */   public void hide() {
/* 347 */     CardCrawlGame.sound.play("DECK_CLOSE", 0.1F);
/* 348 */     this.button.hide();
/* 349 */     this.screenUp = false;
/* 350 */     FontHelper.ClearLeaderboardFontTextures();
/* 351 */     CardCrawlGame.mainMenuScreen.panelScreen.refresh();
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 355 */     renderScoreHeaders(sb);
/* 356 */     renderScores(sb);
/* 357 */     renderViewMyScoreBox(sb);
/* 358 */     renderFilters(sb);
/* 359 */     renderArrows(sb);
/* 360 */     this.button.render(sb);
/*     */   }
/*     */   
/*     */   private void renderFilters(SpriteBatch sb) {
/* 364 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.charTitleFont, TEXT[1], 240.0F * Settings.scale, 920.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, this.charLabel, 280.0F * Settings.scale, 860.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 380 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, this.regionLabel, 280.0F * Settings.scale, 680.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, this.typeLabel, 280.0F * Settings.scale, 500.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */     
/* 396 */     for (i = 0; i < this.charButtons.size(); i++) {
/* 397 */       ((FilterButton)this.charButtons.get(i)).render(sb, 340.0F * Settings.scale + i * FILTER_SPACING_X, 780.0F * Settings.scale);
/*     */     }
/*     */     
/* 400 */     for (i = 0; i < this.regionButtons.size(); i++) {
/* 401 */       ((FilterButton)this.regionButtons.get(i)).render(sb, 340.0F * Settings.scale + i * FILTER_SPACING_X, 600.0F * Settings.scale);
/*     */     }
/*     */     
/* 404 */     for (i = 0; i < this.typeButtons.size(); i++) {
/* 405 */       ((FilterButton)this.typeButtons.get(i)).render(sb, 340.0F * Settings.scale + i * FILTER_SPACING_X, 420.0F * Settings.scale);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderArrows(SpriteBatch sb) {
/* 411 */     boolean renderLeftArrow = true;
/*     */ 
/*     */     
/* 414 */     for (FilterButton b : this.regionButtons) {
/* 415 */       if (b.rType == FilterButton.RegionSetting.FRIEND && this.entries.size() < 20) {
/* 416 */         renderLeftArrow = false;
/*     */       }
/*     */     } 
/*     */     
/* 420 */     if (this.currentStartIndex != 1 && renderLeftArrow) {
/* 421 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.lineFadeInTimer));
/* 422 */       sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 440 */       if (this.prevHb.hovered) {
/* 441 */         sb.setBlendFunction(770, 1);
/* 442 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.lineFadeInTimer / 2.0F));
/* 443 */         sb.draw(ImageMaster.POPUP_ARROW, this.prevHb.cX - 128.0F, this.prevHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 460 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 463 */       if (Settings.isControllerMode) {
/* 464 */         sb.draw(CInputActionSet.pageLeftViewDeck
/* 465 */             .getKeyImg(), this.prevHb.cX - 32.0F + 0.0F * Settings.scale, this.prevHb.cY - 32.0F - 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 483 */       this.prevHb.render(sb);
/*     */     } 
/*     */ 
/*     */     
/* 487 */     if (this.entries.size() == 20) {
/* 488 */       sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.lineFadeInTimer));
/* 489 */       sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 507 */       if (this.nextHb.hovered) {
/* 508 */         sb.setBlendFunction(770, 1);
/* 509 */         sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.lineFadeInTimer / 2.0F));
/* 510 */         sb.draw(ImageMaster.POPUP_ARROW, this.nextHb.cX - 128.0F, this.nextHb.cY - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale * 0.75F, Settings.scale * 0.75F, 0.0F, 0, 0, 256, 256, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 527 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 530 */       if (Settings.isControllerMode) {
/* 531 */         sb.draw(CInputActionSet.pageRightViewExhaust
/* 532 */             .getKeyImg(), this.nextHb.cX - 32.0F + 0.0F * Settings.scale, this.nextHb.cY - 32.0F - 100.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 550 */       this.nextHb.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderScoreHeaders(SpriteBatch sb) {
/* 555 */     Color creamColor = new Color(1.0F, 0.965F, 0.886F, this.lineFadeInTimer);
/* 556 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.charTitleFont, TEXT[10], 960.0F * Settings.scale, 920.0F * Settings.scale, new Color(0.937F, 0.784F, 0.317F, this.lineFadeInTimer));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 564 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT[6], RANK_X, 860.0F * Settings.scale, creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 572 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT[7], NAME_X, 860.0F * Settings.scale, creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 580 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT[8], SCORE_X, 860.0F * Settings.scale, creamColor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 588 */     sb.setColor(creamColor);
/* 589 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 1138.0F * Settings.scale, 168.0F * Settings.scale, LINE_THICKNESS, 692.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 595 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 1480.0F * Settings.scale, 168.0F * Settings.scale, LINE_THICKNESS, 692.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 601 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.lineFadeInTimer * 0.75F));
/* 602 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 982.0F * Settings.scale, 814.0F * Settings.scale, 630.0F * Settings.scale, 16.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 608 */     sb.setColor(creamColor);
/* 609 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 982.0F * Settings.scale, 820.0F * Settings.scale, 630.0F * Settings.scale, LINE_THICKNESS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderViewMyScoreBox(SpriteBatch sb) {
/* 618 */     if (((FilterButton)this.regionButtons.get(0)).active || this.waiting) {
/*     */       return;
/*     */     }
/*     */     
/* 622 */     if (this.viewMyScoreHb.hovered) {
/* 623 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[5], 1310.0F * Settings.scale, 80.0F * Settings.scale, Settings.GREEN_TEXT_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 631 */       FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, TEXT[5], 1310.0F * Settings.scale, 80.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 640 */     if (Settings.isControllerMode) {
/* 641 */       sb.draw(CInputActionSet.topPanel
/* 642 */           .getKeyImg(), 1270.0F * Settings.scale - 32.0F - 
/* 643 */           FontHelper.getSmartWidth(FontHelper.cardTitleFont, TEXT[5], 9999.0F, 0.0F) / 2.0F, -32.0F + 80.0F * Settings.scale, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 661 */     this.viewMyScoreHb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderScores(SpriteBatch sb) {
/* 665 */     if (!this.waiting) {
/* 666 */       if (this.entries.isEmpty()) {
/* 667 */         FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[12], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 675 */         for (int i = 0; i < this.entries.size(); i++) {
/* 676 */           ((LeaderboardEntry)this.entries.get(i)).render(sb, i);
/*     */         }
/*     */       }
/*     */     
/* 680 */     } else if (CardCrawlGame.publisherIntegration.isInitialized()) {
/* 681 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[9], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 689 */       FontHelper.renderFontCentered(sb, FontHelper.panelNameFont, TEXT[11], 1300.0F * Settings.scale, 540.0F * Settings.scale, Settings.RED_TEXT_COLOR);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\leaderboards\LeaderboardScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */