/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
/*     */ 
/*     */ public class DiscardPileViewScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  25 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardPileViewScreen");
/*  26 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*     */   public boolean isHovered = false;
/*     */   
/*     */   private static final int CARDS_PER_LINE = 5;
/*     */   private boolean grabbedScreen = false;
/*  32 */   private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale; private static float drawStartX; private static float drawStartY; private static float padX; private static float padY;
/*  33 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
/*  34 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*  35 */   private float grabStartY = this.scrollLowerBound; private float currentDiffY = this.scrollLowerBound;
/*  36 */   private static final String HEADER_INFO = TEXT[0];
/*  37 */   private AbstractCard hoveredCard = null;
/*  38 */   private int prevDeckSize = 0;
/*     */   private ScrollBar scrollBar;
/*  40 */   private AbstractCard controllerCard = null;
/*     */   
/*     */   public DiscardPileViewScreen() {
/*  43 */     drawStartX = Settings.WIDTH;
/*  44 */     drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
/*  45 */     drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
/*  46 */     drawStartX /= 2.0F;
/*  47 */     drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
/*     */     
/*  49 */     padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
/*  50 */     padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
/*  51 */     this.scrollBar = new ScrollBar(this);
/*  52 */     this.scrollBar.changeHeight(Settings.HEIGHT - 384.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  56 */     updateControllerInput();
/*  57 */     if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen && 
/*  58 */       !CInputHelper.isTopPanelActive()) {
/*  59 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  60 */         this.currentDiffY += Settings.SCROLL_SPEED;
/*  61 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  62 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*     */     
/*  66 */     boolean isDraggingScrollBar = false;
/*  67 */     if (shouldShowScrollBar()) {
/*  68 */       isDraggingScrollBar = this.scrollBar.update();
/*     */     }
/*  70 */     if (!isDraggingScrollBar) {
/*  71 */       updateScrolling();
/*     */     }
/*  73 */     updatePositions();
/*  74 */     if (Settings.isControllerMode && this.controllerCard != null && !CInputHelper.isTopPanelActive()) {
/*  75 */       CInputHelper.setCursor(this.controllerCard.hb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/*  80 */     if (!Settings.isControllerMode || CInputHelper.isTopPanelActive()) {
/*     */       return;
/*     */     }
/*     */     
/*  84 */     boolean anyHovered = false;
/*  85 */     int index = 0;
/*     */     
/*  87 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/*  88 */       if (c.hb.hovered) {
/*  89 */         anyHovered = true;
/*     */         break;
/*     */       } 
/*  92 */       index++;
/*     */     } 
/*     */     
/*  95 */     if (!anyHovered) {
/*  96 */       Gdx.input.setCursorPosition(
/*  97 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(0)).hb.cX, Settings.HEIGHT - 
/*  98 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(0)).hb.cY);
/*  99 */       this.controllerCard = AbstractDungeon.player.discardPile.group.get(0);
/*     */     }
/* 101 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && AbstractDungeon.player.discardPile
/* 102 */       .size() > 5) {
/*     */       
/* 104 */       index -= 5;
/* 105 */       if (index < 0) {
/* 106 */         int wrap = AbstractDungeon.player.discardPile.size() / 5;
/* 107 */         index += wrap * 5;
/* 108 */         if (index + 5 < AbstractDungeon.player.discardPile.size()) {
/* 109 */           index += 5;
/*     */         }
/*     */       } 
/* 112 */       Gdx.input.setCursorPosition(
/* 113 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 114 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cY);
/* 115 */       this.controllerCard = AbstractDungeon.player.discardPile.group.get(index);
/* 116 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && AbstractDungeon.player.discardPile
/* 117 */       .size() > 5) {
/* 118 */       if (index < AbstractDungeon.player.discardPile.size() - 5) {
/* 119 */         index += 5;
/*     */       } else {
/* 121 */         index %= 5;
/*     */       } 
/* 123 */       Gdx.input.setCursorPosition(
/* 124 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 125 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cY);
/* 126 */       this.controllerCard = AbstractDungeon.player.discardPile.group.get(index);
/* 127 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 128 */       if (index % 5 > 0) {
/* 129 */         index--;
/*     */       } else {
/* 131 */         index += 4;
/* 132 */         if (index > AbstractDungeon.player.discardPile.size() - 1) {
/* 133 */           index = AbstractDungeon.player.discardPile.size() - 1;
/*     */         }
/*     */       } 
/* 136 */       Gdx.input.setCursorPosition(
/* 137 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 138 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cY);
/* 139 */       this.controllerCard = AbstractDungeon.player.discardPile.group.get(index);
/* 140 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 141 */       if (index % 5 < 4) {
/* 142 */         index++;
/* 143 */         if (index > AbstractDungeon.player.discardPile.size() - 1) {
/* 144 */           index -= AbstractDungeon.player.discardPile.size() % 5;
/*     */         }
/*     */       } else {
/* 147 */         index -= 4;
/* 148 */         if (index < 0) {
/* 149 */           index = 0;
/*     */         }
/*     */       } 
/* 152 */       Gdx.input.setCursorPosition(
/* 153 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cX, Settings.HEIGHT - 
/* 154 */           (int)((AbstractCard)AbstractDungeon.player.discardPile.group.get(index)).hb.cY);
/* 155 */       this.controllerCard = AbstractDungeon.player.discardPile.group.get(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePositions() {
/* 164 */     this.hoveredCard = null;
/* 165 */     int lineNum = 0;
/* 166 */     ArrayList<AbstractCard> cards = AbstractDungeon.player.discardPile.group;
/* 167 */     for (int i = 0; i < cards.size(); i++) {
/* 168 */       int mod = i % 5;
/* 169 */       if (mod == 0 && i != 0) {
/* 170 */         lineNum++;
/*     */       }
/* 172 */       ((AbstractCard)cards.get(i)).target_x = drawStartX + mod * padX;
/* 173 */       ((AbstractCard)cards.get(i)).target_y = drawStartY + this.currentDiffY - lineNum * padY;
/* 174 */       ((AbstractCard)cards.get(i)).update();
/*     */       
/* 176 */       if (AbstractDungeon.topPanel.potionUi.isHidden) {
/* 177 */         ((AbstractCard)cards.get(i)).updateHoverLogic();
/* 178 */         if (((AbstractCard)cards.get(i)).hb.hovered) {
/* 179 */           this.hoveredCard = cards.get(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateScrolling() {
/* 186 */     int y = InputHelper.mY;
/*     */     
/* 188 */     if (!this.grabbedScreen) {
/* 189 */       if (InputHelper.scrolledDown) {
/* 190 */         this.currentDiffY += Settings.SCROLL_SPEED;
/* 191 */       } else if (InputHelper.scrolledUp) {
/* 192 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 195 */       if (InputHelper.justClickedLeft) {
/* 196 */         this.grabbedScreen = true;
/* 197 */         this.grabStartY = y - this.currentDiffY;
/*     */       }
/*     */     
/* 200 */     } else if (InputHelper.isMouseDown) {
/* 201 */       this.currentDiffY = y - this.grabStartY;
/*     */     } else {
/* 203 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (this.prevDeckSize != AbstractDungeon.player.discardPile.size()) {
/* 208 */       calculateScrollBounds();
/*     */     }
/* 210 */     resetScrolling();
/* 211 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateScrollBounds() {
/* 218 */     int scrollTmp = 0;
/* 219 */     if (AbstractDungeon.player.discardPile.size() > 10) {
/* 220 */       scrollTmp = AbstractDungeon.player.discardPile.size() / 5 - 2;
/* 221 */       if (AbstractDungeon.player.discardPile.size() % 5 != 0) {
/* 222 */         scrollTmp++;
/*     */       }
/* 224 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
/*     */     } else {
/* 226 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */     } 
/*     */     
/* 229 */     this.prevDeckSize = AbstractDungeon.player.discardPile.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 236 */     if (this.currentDiffY < this.scrollLowerBound) {
/* 237 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
/* 238 */     } else if (this.currentDiffY > this.scrollUpperBound) {
/* 239 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 244 */     if (Settings.isControllerMode) {
/* 245 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 246 */       this.controllerCard = null;
/*     */     } 
/* 248 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */   }
/*     */   
/*     */   public void open() {
/* 252 */     if (Settings.isControllerMode) {
/* 253 */       Gdx.input.setCursorPosition(10, Settings.HEIGHT / 2);
/* 254 */       this.controllerCard = null;
/*     */     } 
/* 256 */     CardCrawlGame.sound.play("DECK_OPEN");
/* 257 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 258 */     this.currentDiffY = this.scrollLowerBound;
/* 259 */     this.grabStartY = this.scrollLowerBound;
/* 260 */     this.grabbedScreen = false;
/* 261 */     AbstractDungeon.isScreenUp = true;
/* 262 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.DISCARD_VIEW;
/* 263 */     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
/* 264 */       c.setAngle(0.0F, true);
/* 265 */       c.targetDrawScale = 0.75F;
/* 266 */       c.drawScale = 0.75F;
/* 267 */       c.lighten(true);
/*     */     } 
/* 269 */     hideCards();
/* 270 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */     
/* 272 */     if (AbstractDungeon.player.discardPile.group.size() <= 5) {
/* 273 */       drawStartY = Settings.HEIGHT * 0.5F;
/*     */     } else {
/* 275 */       drawStartY = Settings.HEIGHT * 0.66F;
/*     */     } 
/*     */     
/* 278 */     calculateScrollBounds();
/*     */   }
/*     */   
/*     */   private void hideCards() {
/* 282 */     int lineNum = 0;
/* 283 */     ArrayList<AbstractCard> cards = AbstractDungeon.player.discardPile.group;
/* 284 */     for (int i = 0; i < cards.size(); i++) {
/* 285 */       int mod = i % 5;
/* 286 */       if (mod == 0 && i != 0) {
/* 287 */         lineNum++;
/*     */       }
/* 289 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 290 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 297 */     if (shouldShowScrollBar()) {
/* 298 */       this.scrollBar.render(sb);
/*     */     }
/*     */     
/* 301 */     if (this.hoveredCard == null) {
/* 302 */       AbstractDungeon.player.discardPile.render(sb);
/*     */     } else {
/* 304 */       AbstractDungeon.player.discardPile.renderExceptOneCard(sb, this.hoveredCard);
/* 305 */       this.hoveredCard.renderHoverShadow(sb);
/* 306 */       this.hoveredCard.render(sb);
/* 307 */       this.hoveredCard.renderCardTip(sb);
/*     */     } 
/*     */     
/* 310 */     sb.setColor(Color.WHITE);
/* 311 */     sb.draw(ImageMaster.DISCARD_PILE_BANNER, 1290.0F * Settings.xScale, 0.0F, 630.0F * Settings.scale, 128.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     FontHelper.renderFontLeftTopAligned(sb, FontHelper.panelNameFont, TEXT[2], 1558.0F * Settings.xScale, 82.0F * Settings.scale, Settings.LIGHT_YELLOW_COLOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     FontHelper.renderDeckViewTip(sb, HEADER_INFO, 96.0F * Settings.scale, Settings.CREAM_COLOR);
/* 327 */     AbstractDungeon.overlayMenu.discardPilePanel.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 332 */     this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 333 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 337 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
/* 338 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 342 */     return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\DiscardPileViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */