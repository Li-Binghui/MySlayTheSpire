/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DrawPileViewScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  28 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DrawPileViewScreen");
/*  29 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  31 */   private CardGroup drawPileCopy = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */   public boolean isHovered = false;
/*     */   private static final int CARDS_PER_LINE = 5;
/*  34 */   private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale;
/*     */   private boolean grabbedScreen = false;
/*     */   private static float drawStartX;
/*  37 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT; private static float drawStartY; private static float padX; private static float padY;
/*  38 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*  39 */   private float grabStartY = this.scrollLowerBound; private float currentDiffY = this.scrollLowerBound;
/*  40 */   private static final String HEADER_INFO = TEXT[0];
/*  41 */   private static final String BODY_INFO = TEXT[1];
/*  42 */   private AbstractCard hoveredCard = null;
/*  43 */   private int prevDeckSize = 0;
/*     */   private ScrollBar scrollBar;
/*  45 */   private AbstractCard controllerCard = null;
/*     */   
/*     */   public DrawPileViewScreen() {
/*  48 */     drawStartX = Settings.WIDTH;
/*  49 */     drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
/*  50 */     drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
/*  51 */     drawStartX /= 2.0F;
/*  52 */     drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
/*     */     
/*  54 */     padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
/*  55 */     padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
/*  56 */     this.scrollBar = new ScrollBar(this);
/*  57 */     this.scrollBar.move(0.0F, -30.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  61 */     boolean isDraggingScrollBar = false;
/*  62 */     if (shouldShowScrollBar()) {
/*  63 */       isDraggingScrollBar = this.scrollBar.update();
/*     */     }
/*  65 */     if (!isDraggingScrollBar) {
/*  66 */       updateScrolling();
/*     */     }
/*  68 */     updateControllerInput();
/*  69 */     if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen && 
/*  70 */       !CInputHelper.isTopPanelActive()) {
/*  71 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  72 */         this.currentDiffY += Settings.SCROLL_SPEED;
/*  73 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  74 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*     */     
/*  78 */     updatePositions();
/*     */     
/*  80 */     if (Settings.isControllerMode && this.controllerCard != null && !CInputHelper.isTopPanelActive()) {
/*  81 */       CInputHelper.setCursor(this.controllerCard.hb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/*  86 */     if (!Settings.isControllerMode || CInputHelper.isTopPanelActive()) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     boolean anyHovered = false;
/*  91 */     int index = 0;
/*  92 */     for (AbstractCard c : this.drawPileCopy.group) {
/*  93 */       if (c.hb.hovered) {
/*  94 */         anyHovered = true;
/*     */         break;
/*     */       } 
/*  97 */       index++;
/*     */     } 
/*     */     
/* 100 */     if (!anyHovered) {
/* 101 */       Gdx.input.setCursorPosition(
/* 102 */           (int)((AbstractCard)this.drawPileCopy.group.get(0)).hb.cX, Settings.HEIGHT - 
/* 103 */           (int)((AbstractCard)this.drawPileCopy.group.get(0)).hb.cY);
/* 104 */       this.controllerCard = this.drawPileCopy.group.get(0);
/*     */     }
/* 106 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && this.drawPileCopy
/* 107 */       .size() > 5) {
/*     */       
/* 109 */       index -= 5;
/* 110 */       if (index < 0) {
/* 111 */         int wrap = this.drawPileCopy.size() / 5;
/* 112 */         index += wrap * 5;
/* 113 */         if (index + 5 < this.drawPileCopy.size()) {
/* 114 */           index += 5;
/*     */         }
/*     */       } 
/* 117 */       Gdx.input.setCursorPosition(
/* 118 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 119 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cY);
/* 120 */       this.controllerCard = this.drawPileCopy.group.get(index);
/* 121 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.drawPileCopy
/* 122 */       .size() > 5) {
/* 123 */       if (index < this.drawPileCopy.size() - 5) {
/* 124 */         index += 5;
/*     */       } else {
/* 126 */         index %= 5;
/*     */       } 
/* 128 */       Gdx.input.setCursorPosition(
/* 129 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 130 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cY);
/* 131 */       this.controllerCard = this.drawPileCopy.group.get(index);
/* 132 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 133 */       if (index % 5 > 0) {
/* 134 */         index--;
/*     */       } else {
/* 136 */         index += 4;
/* 137 */         if (index > this.drawPileCopy.size() - 1) {
/* 138 */           index = this.drawPileCopy.size() - 1;
/*     */         }
/*     */       } 
/* 141 */       Gdx.input.setCursorPosition(
/* 142 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 143 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cY);
/* 144 */       this.controllerCard = this.drawPileCopy.group.get(index);
/* 145 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 146 */       if (index % 5 < 4) {
/* 147 */         index++;
/* 148 */         if (index > this.drawPileCopy.size() - 1) {
/* 149 */           index -= this.drawPileCopy.size() % 5;
/*     */         }
/*     */       } else {
/* 152 */         index -= 4;
/* 153 */         if (index < 0) {
/* 154 */           index = 0;
/*     */         }
/*     */       } 
/* 157 */       Gdx.input.setCursorPosition(
/* 158 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 159 */           (int)((AbstractCard)this.drawPileCopy.group.get(index)).hb.cY);
/* 160 */       this.controllerCard = this.drawPileCopy.group.get(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 166 */     int y = InputHelper.mY;
/*     */     
/* 168 */     if (!this.grabbedScreen) {
/* 169 */       if (InputHelper.scrolledDown) {
/* 170 */         this.currentDiffY += Settings.SCROLL_SPEED;
/* 171 */       } else if (InputHelper.scrolledUp) {
/* 172 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 175 */       if (InputHelper.justClickedLeft) {
/* 176 */         this.grabbedScreen = true;
/* 177 */         this.grabStartY = y - this.currentDiffY;
/*     */       }
/*     */     
/* 180 */     } else if (InputHelper.isMouseDown) {
/* 181 */       this.currentDiffY = y - this.grabStartY;
/*     */     } else {
/* 183 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 187 */     if (this.prevDeckSize != this.drawPileCopy.size()) {
/* 188 */       calculateScrollBounds();
/*     */     }
/* 190 */     resetScrolling();
/* 191 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateScrollBounds() {
/* 199 */     if (this.drawPileCopy.size() > 10) {
/* 200 */       int scrollTmp = this.drawPileCopy.size() / 5 - 2;
/* 201 */       if (this.drawPileCopy.size() % 5 != 0) {
/* 202 */         scrollTmp++;
/*     */       }
/* 204 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
/*     */     } else {
/* 206 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */     } 
/*     */     
/* 209 */     this.prevDeckSize = this.drawPileCopy.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 216 */     if (this.currentDiffY < this.scrollLowerBound) {
/* 217 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
/* 218 */     } else if (this.currentDiffY > this.scrollUpperBound) {
/* 219 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePositions() {
/* 227 */     this.hoveredCard = null;
/* 228 */     int lineNum = 0;
/* 229 */     ArrayList<AbstractCard> cards = this.drawPileCopy.group;
/*     */     
/* 231 */     for (int i = 0; i < cards.size(); i++) {
/* 232 */       int mod = i % 5;
/* 233 */       if (mod == 0 && i != 0) {
/* 234 */         lineNum++;
/*     */       }
/* 236 */       ((AbstractCard)cards.get(i)).target_x = drawStartX + mod * padX;
/* 237 */       ((AbstractCard)cards.get(i)).target_y = drawStartY + this.currentDiffY - lineNum * padY;
/* 238 */       ((AbstractCard)cards.get(i)).update();
/*     */       
/* 240 */       if (AbstractDungeon.topPanel.potionUi.isHidden) {
/* 241 */         ((AbstractCard)cards.get(i)).updateHoverLogic();
/* 242 */         if (((AbstractCard)cards.get(i)).hb.hovered) {
/* 243 */           this.hoveredCard = cards.get(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 250 */     if (Settings.isControllerMode) {
/* 251 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 252 */       this.controllerCard = null;
/*     */     } 
/* 254 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[2]);
/*     */   }
/*     */   
/*     */   public void open() {
/* 258 */     if (Settings.isControllerMode) {
/* 259 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 260 */       this.controllerCard = null;
/*     */     } 
/* 262 */     CardCrawlGame.sound.play("DECK_OPEN");
/* 263 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 264 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[2]);
/* 265 */     this.currentDiffY = this.scrollLowerBound;
/* 266 */     this.grabStartY = this.scrollLowerBound;
/* 267 */     this.grabbedScreen = false;
/* 268 */     AbstractDungeon.isScreenUp = true;
/* 269 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.GAME_DECK_VIEW;
/* 270 */     this.drawPileCopy.clear();
/*     */     
/* 272 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 273 */       c.setAngle(0.0F, true);
/* 274 */       c.targetDrawScale = 0.75F;
/* 275 */       c.targetDrawScale = 0.75F;
/* 276 */       c.drawScale = 0.75F;
/*     */       
/* 278 */       c.lighten(true);
/* 279 */       this.drawPileCopy.addToBottom(c);
/*     */     } 
/*     */     
/* 282 */     if (!AbstractDungeon.player.hasRelic("Frozen Eye")) {
/* 283 */       this.drawPileCopy.sortAlphabetically(true);
/* 284 */       this.drawPileCopy.sortByRarityPlusStatusCardType(true);
/*     */     } 
/* 286 */     hideCards();
/*     */     
/* 288 */     if (this.drawPileCopy.group.size() <= 5) {
/* 289 */       drawStartY = Settings.HEIGHT * 0.5F;
/*     */     } else {
/* 291 */       drawStartY = Settings.HEIGHT * 0.66F;
/*     */     } 
/*     */     
/* 294 */     calculateScrollBounds();
/*     */   }
/*     */   
/*     */   private void hideCards() {
/* 298 */     int lineNum = 0;
/* 299 */     ArrayList<AbstractCard> cards = this.drawPileCopy.group;
/* 300 */     for (int i = 0; i < cards.size(); i++) {
/* 301 */       int mod = i % 5;
/* 302 */       if (mod == 0 && i != 0) {
/* 303 */         lineNum++;
/*     */       }
/* 305 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 306 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
/*     */ 
/*     */       
/* 309 */       ((AbstractCard)cards.get(i)).targetDrawScale = 0.75F;
/* 310 */       ((AbstractCard)cards.get(i)).drawScale = 0.75F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 315 */     if (shouldShowScrollBar()) {
/* 316 */       this.scrollBar.render(sb);
/*     */     }
/*     */     
/* 319 */     if (this.hoveredCard == null) {
/* 320 */       this.drawPileCopy.render(sb);
/*     */     } else {
/* 322 */       this.drawPileCopy.renderExceptOneCard(sb, this.hoveredCard);
/* 323 */       this.hoveredCard.renderHoverShadow(sb);
/* 324 */       this.hoveredCard.render(sb);
/* 325 */       this.hoveredCard.renderCardTip(sb);
/*     */     } 
/*     */     
/* 328 */     sb.setColor(Color.WHITE);
/* 329 */     sb.draw(ImageMaster.DRAW_PILE_BANNER, 0.0F, 0.0F, 630.0F * Settings.scale, 128.0F * Settings.scale);
/* 330 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT[3], 166.0F * Settings.scale, 82.0F * Settings.scale, Settings.LIGHT_YELLOW_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 338 */     if (!AbstractDungeon.player.hasRelic("Frozen Eye")) {
/* 339 */       FontHelper.renderDeckViewTip(sb, BODY_INFO, 48.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */     }
/* 341 */     FontHelper.renderDeckViewTip(sb, HEADER_INFO, 96.0F * Settings.scale, Settings.CREAM_COLOR);
/* 342 */     AbstractDungeon.overlayMenu.combatDeckPanel.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 347 */     this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 348 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 352 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
/* 353 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 357 */     return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DrawPileViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */