/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MasterDeckViewScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  33 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("MasterDeckViewScreen");
/*  34 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   private static float drawStartX;
/*  36 */   private static float drawStartY = Settings.HEIGHT * 0.66F; private static float padX;
/*  37 */   private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale; private static float padY;
/*     */   private static final int CARDS_PER_LINE = 5;
/*     */   private boolean grabbedScreen = false;
/*  40 */   private float grabStartY = 0.0F, currentDiffY = 0.0F;
/*  41 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
/*  42 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*  43 */   private static final String HEADER_INFO = TEXT[0];
/*  44 */   private AbstractCard hoveredCard = null; private AbstractCard clickStartedCard = null;
/*  45 */   private int prevDeckSize = 0;
/*     */   
/*     */   private ScrollBar scrollBar;
/*  48 */   private AbstractCard controllerCard = null;
/*     */   
/*     */   private MasterDeckSortHeader sortHeader;
/*  51 */   private int headerIndex = -1;
/*  52 */   private Comparator<AbstractCard> sortOrder = null;
/*  53 */   private ArrayList<AbstractCard> tmpSortedDeck = null;
/*  54 */   private float tmpHeaderPosition = Float.NEGATIVE_INFINITY;
/*  55 */   private int headerScrollLockRemainingFrames = 0;
/*     */   private boolean justSorted = false;
/*     */   
/*     */   public MasterDeckViewScreen() {
/*  59 */     drawStartX = Settings.WIDTH;
/*  60 */     drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
/*  61 */     drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
/*  62 */     drawStartX /= 2.0F;
/*  63 */     drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
/*     */     
/*  65 */     padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
/*  66 */     padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
/*     */     
/*  68 */     this.scrollBar = new ScrollBar(this);
/*  69 */     this.scrollBar.move(0.0F, -30.0F * Settings.scale);
/*     */     
/*  71 */     this.sortHeader = new MasterDeckSortHeader(this);
/*     */   }
/*     */   
/*     */   public void update() {
/*  75 */     updateControllerInput();
/*  76 */     if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen && !AbstractDungeon.topPanel.selectPotionMode)
/*     */     {
/*  78 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  79 */         this.currentDiffY += Settings.SCROLL_SPEED;
/*  80 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  81 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*     */     
/*  85 */     boolean isDraggingScrollBar = false;
/*  86 */     if (shouldShowScrollBar()) {
/*  87 */       isDraggingScrollBar = this.scrollBar.update();
/*     */     }
/*  89 */     if (!isDraggingScrollBar) {
/*  90 */       updateScrolling();
/*     */     }
/*  92 */     updatePositions();
/*  93 */     this.sortHeader.update();
/*  94 */     updateClicking();
/*     */     
/*  96 */     if (Settings.isControllerMode && this.controllerCard != null) {
/*  97 */       Gdx.input.setCursorPosition((int)this.controllerCard.hb.cX, (int)(Settings.HEIGHT - this.controllerCard.hb.cY));
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/* 102 */     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode) {
/*     */       return;
/*     */     }
/*     */     
/* 106 */     CardGroup deck = AbstractDungeon.player.masterDeck;
/* 107 */     boolean anyHovered = false;
/* 108 */     int index = 0;
/*     */     
/* 110 */     if (this.tmpSortedDeck == null) {
/* 111 */       this.tmpSortedDeck = deck.group;
/*     */     }
/* 113 */     for (AbstractCard c : this.tmpSortedDeck) {
/* 114 */       if (c.hb.hovered) {
/* 115 */         anyHovered = true;
/*     */         break;
/*     */       } 
/* 118 */       index++;
/*     */     } 
/* 120 */     anyHovered = (anyHovered || this.headerIndex >= 0);
/*     */     
/* 122 */     if (!anyHovered) {
/* 123 */       if (this.tmpSortedDeck.size() > 0) {
/* 124 */         Gdx.input.setCursorPosition((int)((AbstractCard)this.tmpSortedDeck.get(0)).hb.cX, (int)((AbstractCard)this.tmpSortedDeck.get(0)).hb.cY);
/* 125 */         this.controllerCard = this.tmpSortedDeck.get(0);
/*     */       }
/*     */     
/* 128 */     } else if (this.headerIndex >= 0) {
/* 129 */       if (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) {
/* 130 */         this.sortHeader.selectionIndex = this.headerIndex = -1;
/* 131 */         this.controllerCard = this.tmpSortedDeck.get(0);
/* 132 */         Gdx.input.setCursorPosition(
/* 133 */             (int)((AbstractCard)this.tmpSortedDeck.get(0)).hb.cX, Settings.HEIGHT - 
/* 134 */             (int)((AbstractCard)this.tmpSortedDeck.get(0)).hb.cY);
/* 135 */       } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 136 */         if (this.headerIndex > 0) {
/* 137 */           selectSortButton(--this.headerIndex);
/*     */         }
/* 139 */       } else if ((CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) && 
/* 140 */         this.headerIndex < this.sortHeader.buttons.length - 1) {
/* 141 */         selectSortButton(++this.headerIndex);
/*     */       }
/*     */     
/*     */     }
/* 145 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && deck
/* 146 */       .size() > 5) {
/* 147 */       index -= 5;
/* 148 */       if (index < 0) {
/* 149 */         selectSortButton(this.headerIndex = 0);
/*     */         return;
/*     */       } 
/* 152 */       Gdx.input.setCursorPosition(
/* 153 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cX, Settings.HEIGHT - 
/* 154 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cY);
/* 155 */       this.controllerCard = this.tmpSortedDeck.get(index);
/* 156 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && deck
/* 157 */       .size() > 5) {
/* 158 */       if (index < deck.size() - 5) {
/* 159 */         index += 5;
/*     */       } else {
/* 161 */         index %= 5;
/*     */       } 
/* 163 */       Gdx.input.setCursorPosition(
/* 164 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cX, Settings.HEIGHT - 
/* 165 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cY);
/* 166 */       this.controllerCard = this.tmpSortedDeck.get(index);
/* 167 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 168 */       if (index % 5 > 0) {
/* 169 */         index--;
/*     */       } else {
/* 171 */         index += 4;
/* 172 */         if (index > deck.size() - 1) {
/* 173 */           index = deck.size() - 1;
/*     */         }
/*     */       } 
/* 176 */       Gdx.input.setCursorPosition(
/* 177 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cX, Settings.HEIGHT - 
/* 178 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cY);
/* 179 */       this.controllerCard = this.tmpSortedDeck.get(index);
/* 180 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 181 */       if (index % 5 < 4) {
/* 182 */         index++;
/* 183 */         if (index > deck.size() - 1) {
/* 184 */           index -= deck.size() % 5;
/*     */         }
/*     */       } else {
/* 187 */         index -= 4;
/* 188 */         if (index < 0) {
/* 189 */           index = 0;
/*     */         }
/*     */       } 
/* 192 */       Gdx.input.setCursorPosition(
/* 193 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cX, Settings.HEIGHT - 
/* 194 */           (int)((AbstractCard)this.tmpSortedDeck.get(index)).hb.cY);
/* 195 */       this.controllerCard = this.tmpSortedDeck.get(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void open() {
/* 202 */     if (Settings.isControllerMode) {
/* 203 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 204 */       this.controllerCard = null;
/*     */     } 
/* 206 */     AbstractDungeon.player.releaseCard();
/* 207 */     CardCrawlGame.sound.play("DECK_OPEN");
/* 208 */     this.currentDiffY = this.scrollLowerBound;
/* 209 */     this.grabStartY = this.scrollLowerBound;
/* 210 */     this.grabbedScreen = false;
/* 211 */     hideCards();
/* 212 */     AbstractDungeon.dynamicBanner.hide();
/* 213 */     AbstractDungeon.isScreenUp = true;
/* 214 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW;
/* 215 */     AbstractDungeon.overlayMenu.proceedButton.hide();
/* 216 */     AbstractDungeon.overlayMenu.hideCombatPanels();
/* 217 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 218 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/* 219 */     calculateScrollBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePositions() {
/* 226 */     this.hoveredCard = null;
/* 227 */     int lineNum = 0;
/* 228 */     ArrayList<AbstractCard> cards = AbstractDungeon.player.masterDeck.group;
/* 229 */     if (this.sortOrder != null) {
/* 230 */       cards = new ArrayList<>(cards);
/* 231 */       Collections.sort(cards, this.sortOrder);
/* 232 */       this.tmpSortedDeck = cards;
/*     */     } else {
/* 234 */       this.tmpSortedDeck = null;
/*     */     } 
/*     */     
/* 237 */     if (this.justSorted && this.headerScrollLockRemainingFrames <= 0) {
/* 238 */       AbstractCard abstractCard = highestYPosition(cards);
/* 239 */       if (abstractCard != null) {
/* 240 */         this.tmpHeaderPosition = abstractCard.current_y;
/*     */       }
/*     */     } 
/* 243 */     for (int i = 0; i < cards.size(); i++) {
/* 244 */       int mod = i % 5;
/* 245 */       if (mod == 0 && i != 0) {
/* 246 */         lineNum++;
/*     */       }
/* 248 */       ((AbstractCard)cards.get(i)).target_x = drawStartX + mod * padX;
/* 249 */       ((AbstractCard)cards.get(i)).target_y = drawStartY + this.currentDiffY - lineNum * padY;
/* 250 */       ((AbstractCard)cards.get(i)).update();
/* 251 */       ((AbstractCard)cards.get(i)).updateHoverLogic();
/*     */       
/* 253 */       if (((AbstractCard)cards.get(i)).hb.hovered) {
/* 254 */         this.hoveredCard = cards.get(i);
/*     */       }
/*     */     } 
/*     */     
/* 258 */     AbstractCard c = highestYPosition(cards);
/*     */     
/* 260 */     if (this.justSorted && c != null) {
/*     */ 
/*     */       
/* 263 */       int lerps = 0;
/* 264 */       float lerpY = c.current_y, lerpTarget = c.target_y;
/* 265 */       while (lerpY != lerpTarget) {
/* 266 */         lerpY = MathHelper.cardLerpSnap(lerpY, lerpTarget);
/* 267 */         lerps++;
/*     */       } 
/* 269 */       this.headerScrollLockRemainingFrames = lerps;
/*     */     } 
/*     */     
/* 272 */     this.headerScrollLockRemainingFrames -= Settings.FAST_MODE ? 2 : 1;
/*     */     
/* 274 */     if (cards.size() > 0 && this.sortHeader != null && c != null) {
/* 275 */       this.sortHeader.updateScrollPosition((this.headerScrollLockRemainingFrames <= 0) ? c.current_y : this.tmpHeaderPosition);
/* 276 */       this.justSorted = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private AbstractCard highestYPosition(List<AbstractCard> cards) {
/* 281 */     if (cards == null)
/* 282 */       return null; 
/* 283 */     float highestY = 0.0F;
/* 284 */     AbstractCard retVal = null;
/* 285 */     for (AbstractCard card : cards) {
/* 286 */       if (card.current_y > highestY) {
/* 287 */         highestY = card.current_y;
/* 288 */         retVal = card;
/*     */       } 
/*     */     } 
/* 291 */     return retVal;
/*     */   }
/*     */   
/*     */   private void updateScrolling() {
/* 295 */     int y = InputHelper.mY;
/*     */     
/* 297 */     if (!this.grabbedScreen) {
/* 298 */       if (InputHelper.scrolledDown) {
/* 299 */         this.currentDiffY += Settings.SCROLL_SPEED;
/* 300 */       } else if (InputHelper.scrolledUp) {
/* 301 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 304 */       if (InputHelper.justClickedLeft) {
/* 305 */         this.grabbedScreen = true;
/* 306 */         this.grabStartY = y - this.currentDiffY;
/*     */       }
/*     */     
/* 309 */     } else if (InputHelper.isMouseDown) {
/* 310 */       this.currentDiffY = y - this.grabStartY;
/*     */     } else {
/* 312 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 316 */     if (this.prevDeckSize != AbstractDungeon.player.masterDeck.size()) {
/* 317 */       calculateScrollBounds();
/*     */     }
/* 319 */     resetScrolling();
/* 320 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateClicking() {
/* 324 */     if (this.hoveredCard != null) {
/* 325 */       CardCrawlGame.cursor.changeType(GameCursor.CursorType.INSPECT);
/* 326 */       if (InputHelper.justClickedLeft) {
/* 327 */         this.clickStartedCard = this.hoveredCard;
/*     */       }
/* 329 */       if (((InputHelper.justReleasedClickLeft && this.hoveredCard == this.clickStartedCard) || CInputActionSet.select
/* 330 */         .isJustPressed()) && 
/* 331 */         this.headerIndex < 0) {
/* 332 */         InputHelper.justReleasedClickLeft = false;
/* 333 */         CardCrawlGame.cardPopup.open(this.hoveredCard, AbstractDungeon.player.masterDeck);
/* 334 */         this.clickStartedCard = null;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 339 */       this.clickStartedCard = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateScrollBounds() {
/* 348 */     if (AbstractDungeon.player.masterDeck.size() > 10) {
/* 349 */       int scrollTmp = AbstractDungeon.player.masterDeck.size() / 5 - 2;
/* 350 */       if (AbstractDungeon.player.masterDeck.size() % 5 != 0) {
/* 351 */         scrollTmp++;
/*     */       }
/* 353 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
/*     */     } else {
/* 355 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */     } 
/* 357 */     this.prevDeckSize = AbstractDungeon.player.masterDeck.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 364 */     if (this.currentDiffY < this.scrollLowerBound) {
/* 365 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
/* 366 */     } else if (this.currentDiffY > this.scrollUpperBound) {
/* 367 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void hideCards() {
/* 372 */     int lineNum = 0;
/* 373 */     ArrayList<AbstractCard> cards = AbstractDungeon.player.masterDeck.group;
/* 374 */     for (int i = 0; i < cards.size(); i++) {
/* 375 */       int mod = i % 5;
/* 376 */       if (mod == 0 && i != 0) {
/* 377 */         lineNum++;
/*     */       }
/* 379 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 380 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
/*     */ 
/*     */       
/* 383 */       ((AbstractCard)cards.get(i)).targetDrawScale = 0.75F;
/* 384 */       ((AbstractCard)cards.get(i)).drawScale = 0.75F;
/* 385 */       ((AbstractCard)cards.get(i)).setAngle(0.0F, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 390 */     if (shouldShowScrollBar()) {
/* 391 */       this.scrollBar.render(sb);
/*     */     }
/*     */     
/* 394 */     if (this.hoveredCard == null) {
/* 395 */       AbstractDungeon.player.masterDeck.renderMasterDeck(sb);
/*     */     } else {
/* 397 */       AbstractDungeon.player.masterDeck.renderMasterDeckExceptOneCard(sb, this.hoveredCard);
/* 398 */       this.hoveredCard.renderHoverShadow(sb);
/* 399 */       this.hoveredCard.render(sb);
/*     */       
/* 401 */       if (this.hoveredCard.inBottleFlame) {
/* 402 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Flame");
/* 403 */         float prevX = tmp.currentX;
/* 404 */         float prevY = tmp.currentY;
/* 405 */         tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 406 */         tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 407 */         tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 408 */         tmp.render(sb);
/* 409 */         tmp.currentX = prevX;
/* 410 */         tmp.currentY = prevY;
/* 411 */         tmp = null;
/* 412 */       } else if (this.hoveredCard.inBottleLightning) {
/* 413 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Lightning");
/* 414 */         float prevX = tmp.currentX;
/* 415 */         float prevY = tmp.currentY;
/* 416 */         tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 417 */         tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 418 */         tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 419 */         tmp.render(sb);
/* 420 */         tmp.currentX = prevX;
/* 421 */         tmp.currentY = prevY;
/* 422 */         tmp = null;
/* 423 */       } else if (this.hoveredCard.inBottleTornado) {
/* 424 */         AbstractRelic tmp = RelicLibrary.getRelic("Bottled Tornado");
/* 425 */         float prevX = tmp.currentX;
/* 426 */         float prevY = tmp.currentY;
/* 427 */         tmp.currentX = this.hoveredCard.current_x + 130.0F * Settings.scale;
/* 428 */         tmp.currentY = this.hoveredCard.current_y + 182.0F * Settings.scale;
/* 429 */         tmp.scale = this.hoveredCard.drawScale * Settings.scale * 1.5F;
/* 430 */         tmp.render(sb);
/* 431 */         tmp.currentX = prevX;
/* 432 */         tmp.currentY = prevY;
/* 433 */         tmp = null;
/*     */       } 
/*     */     } 
/* 436 */     AbstractDungeon.player.masterDeck.renderTip(sb);
/* 437 */     FontHelper.renderDeckViewTip(sb, HEADER_INFO, 96.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 439 */     this.sortHeader.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 444 */     this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 445 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 449 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
/* 450 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 454 */     return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
/*     */   }
/*     */   
/*     */   public void setSortOrder(Comparator<AbstractCard> sortOrder) {
/* 458 */     if (this.sortOrder != sortOrder)
/* 459 */       this.justSorted = true; 
/* 460 */     this.sortOrder = sortOrder;
/*     */   }
/*     */   
/*     */   private void selectSortButton(int index) {
/* 464 */     Hitbox hb = (this.sortHeader.buttons[this.headerIndex]).hb;
/* 465 */     Gdx.input.setCursorPosition((int)hb.cX, Settings.HEIGHT - (int)hb.cY);
/* 466 */     this.controllerCard = null;
/* 467 */     this.sortHeader.selectionIndex = this.headerIndex;
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\MasterDeckViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */