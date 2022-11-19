/*     */ package com.megacrit.cardcrawl.screens;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExhaustPileViewScreen
/*     */   implements ScrollBarListener
/*     */ {
/*  26 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustViewScreen");
/*  27 */   public static final String[] TEXT = uiStrings.TEXT;
/*     */   
/*  29 */   private CardGroup exhaustPileCopy = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */   public boolean isHovered = false;
/*     */   private static final int CARDS_PER_LINE = 5;
/*     */   private boolean grabbedScreen = false;
/*  33 */   private float grabStartY = 0.0F; private float currentDiffY = 0.0F; private static float drawStartX;
/*     */   private static float drawStartY;
/*  35 */   private float scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT; private static float padX; private static float padY;
/*  36 */   private float scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*  37 */   private static final String DESC = TEXT[0];
/*  38 */   private AbstractCard hoveredCard = null;
/*  39 */   private int prevDeckSize = 0;
/*  40 */   private static final float SCROLL_BAR_THRESHOLD = 500.0F * Settings.scale;
/*     */   
/*     */   private ScrollBar scrollBar;
/*  43 */   private AbstractCard controllerCard = null;
/*     */   
/*     */   public ExhaustPileViewScreen() {
/*  46 */     drawStartX = Settings.WIDTH;
/*  47 */     drawStartX -= 5.0F * AbstractCard.IMG_WIDTH * 0.75F;
/*  48 */     drawStartX -= 4.0F * Settings.CARD_VIEW_PAD_X;
/*  49 */     drawStartX /= 2.0F;
/*  50 */     drawStartX += AbstractCard.IMG_WIDTH * 0.75F / 2.0F;
/*     */     
/*  52 */     padX = AbstractCard.IMG_WIDTH * 0.75F + Settings.CARD_VIEW_PAD_X;
/*  53 */     padY = AbstractCard.IMG_HEIGHT * 0.75F + Settings.CARD_VIEW_PAD_Y;
/*     */     
/*  55 */     this.scrollBar = new ScrollBar(this);
/*  56 */     this.scrollBar.move(0.0F, -30.0F * Settings.scale);
/*     */   }
/*     */   
/*     */   public void update() {
/*  60 */     boolean isDraggingScrollBar = false;
/*  61 */     if (shouldShowScrollBar()) {
/*  62 */       isDraggingScrollBar = this.scrollBar.update();
/*     */     }
/*  64 */     if (!isDraggingScrollBar) {
/*  65 */       updateScrolling();
/*     */     }
/*  67 */     if (this.exhaustPileCopy.group.size() > 0) {
/*  68 */       updateControllerInput();
/*     */     }
/*  70 */     if (Settings.isControllerMode && this.controllerCard != null && !CardCrawlGame.isPopupOpen && 
/*  71 */       !CInputHelper.isTopPanelActive()) {
/*  72 */       if (Gdx.input.getY() > Settings.HEIGHT * 0.7F) {
/*  73 */         this.currentDiffY += Settings.SCROLL_SPEED;
/*  74 */       } else if (Gdx.input.getY() < Settings.HEIGHT * 0.3F) {
/*  75 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */     }
/*     */     
/*  79 */     updatePositions();
/*     */     
/*  81 */     if (Settings.isControllerMode && this.controllerCard != null && !CInputHelper.isTopPanelActive()) {
/*  82 */       CInputHelper.setCursor(this.controllerCard.hb);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateControllerInput() {
/*  87 */     if (!Settings.isControllerMode || CInputHelper.isTopPanelActive()) {
/*     */       return;
/*     */     }
/*     */     
/*  91 */     boolean anyHovered = false;
/*  92 */     int index = 0;
/*     */     
/*  94 */     for (AbstractCard c : this.exhaustPileCopy.group) {
/*  95 */       if (c.hb.hovered) {
/*  96 */         anyHovered = true;
/*     */         break;
/*     */       } 
/*  99 */       index++;
/*     */     } 
/*     */     
/* 102 */     if (!anyHovered) {
/* 103 */       CInputHelper.setCursor(((AbstractCard)this.exhaustPileCopy.group.get(0)).hb);
/* 104 */       this.controllerCard = this.exhaustPileCopy.group.get(0);
/*     */     }
/* 106 */     else if ((CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed()) && this.exhaustPileCopy
/* 107 */       .size() > 5) {
/*     */       
/* 109 */       index -= 5;
/* 110 */       if (index < 0) {
/* 111 */         int wrap = this.exhaustPileCopy.size() / 5;
/* 112 */         index += wrap * 5;
/* 113 */         if (index + 5 < this.exhaustPileCopy.size()) {
/* 114 */           index += 5;
/*     */         }
/*     */       } 
/* 117 */       CInputHelper.setCursor(((AbstractCard)this.exhaustPileCopy.group.get(index)).hb);
/* 118 */       this.controllerCard = this.exhaustPileCopy.group.get(index);
/* 119 */     } else if ((CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed()) && this.exhaustPileCopy
/* 120 */       .size() > 5) {
/* 121 */       if (index < this.exhaustPileCopy.size() - 5) {
/* 122 */         index += 5;
/*     */       } else {
/* 124 */         index %= 5;
/*     */       } 
/* 126 */       CInputHelper.setCursor(((AbstractCard)this.exhaustPileCopy.group.get(index)).hb);
/* 127 */       this.controllerCard = this.exhaustPileCopy.group.get(index);
/* 128 */     } else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
/* 129 */       if (index % 5 > 0) {
/* 130 */         index--;
/*     */       } else {
/* 132 */         index += 4;
/* 133 */         if (index > this.exhaustPileCopy.size() - 1) {
/* 134 */           index = this.exhaustPileCopy.size() - 1;
/*     */         }
/*     */       } 
/* 137 */       CInputHelper.setCursor(((AbstractCard)this.exhaustPileCopy.group.get(index)).hb);
/* 138 */       this.controllerCard = this.exhaustPileCopy.group.get(index);
/* 139 */     } else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
/* 140 */       if (index % 5 < 4) {
/* 141 */         index++;
/* 142 */         if (index > this.exhaustPileCopy.size() - 1) {
/* 143 */           index -= this.exhaustPileCopy.size() % 5;
/*     */         }
/*     */       } else {
/* 146 */         index -= 4;
/* 147 */         if (index < 0) {
/* 148 */           index = 0;
/*     */         }
/*     */       } 
/* 151 */       CInputHelper.setCursor(((AbstractCard)this.exhaustPileCopy.group.get(index)).hb);
/* 152 */       this.controllerCard = this.exhaustPileCopy.group.get(index);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateScrolling() {
/* 158 */     int y = InputHelper.mY;
/*     */     
/* 160 */     if (!this.grabbedScreen) {
/* 161 */       if (InputHelper.scrolledDown) {
/* 162 */         this.currentDiffY += Settings.SCROLL_SPEED;
/* 163 */       } else if (InputHelper.scrolledUp) {
/* 164 */         this.currentDiffY -= Settings.SCROLL_SPEED;
/*     */       } 
/*     */       
/* 167 */       if (InputHelper.justClickedLeft) {
/* 168 */         this.grabbedScreen = true;
/* 169 */         this.grabStartY = y - this.currentDiffY;
/*     */       }
/*     */     
/* 172 */     } else if (InputHelper.isMouseDown) {
/* 173 */       this.currentDiffY = y - this.grabStartY;
/*     */     } else {
/* 175 */       this.grabbedScreen = false;
/*     */     } 
/*     */ 
/*     */     
/* 179 */     if (this.prevDeckSize != this.exhaustPileCopy.size()) {
/* 180 */       calculateScrollBounds();
/*     */     }
/* 182 */     resetScrolling();
/* 183 */     updateBarPosition();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateScrollBounds() {
/* 191 */     if (this.exhaustPileCopy.size() > 10) {
/* 192 */       int scrollTmp = this.exhaustPileCopy.size() / 5 - 2;
/* 193 */       if (this.exhaustPileCopy.size() % 5 != 0) {
/* 194 */         scrollTmp++;
/*     */       }
/* 196 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + scrollTmp * padY;
/*     */     } else {
/* 198 */       this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
/*     */     } 
/*     */     
/* 201 */     this.prevDeckSize = this.exhaustPileCopy.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetScrolling() {
/* 208 */     if (this.currentDiffY < this.scrollLowerBound) {
/* 209 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
/* 210 */     } else if (this.currentDiffY > this.scrollUpperBound) {
/* 211 */       this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePositions() {
/* 219 */     this.hoveredCard = null;
/* 220 */     int lineNum = 0;
/* 221 */     ArrayList<AbstractCard> cards = this.exhaustPileCopy.group;
/* 222 */     for (int i = 0; i < cards.size(); i++) {
/* 223 */       int mod = i % 5;
/* 224 */       if (mod == 0 && i != 0) {
/* 225 */         lineNum++;
/*     */       }
/* 227 */       ((AbstractCard)cards.get(i)).target_x = drawStartX + mod * padX;
/* 228 */       ((AbstractCard)cards.get(i)).target_y = drawStartY + this.currentDiffY - lineNum * padY;
/* 229 */       ((AbstractCard)cards.get(i)).update();
/*     */       
/* 231 */       if (AbstractDungeon.topPanel.potionUi.isHidden) {
/* 232 */         ((AbstractCard)cards.get(i)).updateHoverLogic();
/* 233 */         if (((AbstractCard)cards.get(i)).hb.hovered) {
/* 234 */           this.hoveredCard = cards.get(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reopen() {
/* 241 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/*     */   }
/*     */   
/*     */   public void open() {
/* 245 */     CardCrawlGame.sound.play("DECK_OPEN");
/* 246 */     AbstractDungeon.overlayMenu.showBlackScreen();
/* 247 */     AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
/* 248 */     this.currentDiffY = 0.0F;
/* 249 */     this.grabStartY = 0.0F;
/* 250 */     this.grabbedScreen = false;
/* 251 */     AbstractDungeon.isScreenUp = true;
/* 252 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.EXHAUST_VIEW;
/* 253 */     this.exhaustPileCopy.clear();
/*     */     
/* 255 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/* 256 */       AbstractCard toAdd = c.makeStatEquivalentCopy();
/* 257 */       toAdd.setAngle(0.0F, true);
/* 258 */       toAdd.targetDrawScale = 0.75F;
/* 259 */       toAdd.targetDrawScale = 0.75F;
/* 260 */       toAdd.drawScale = 0.75F;
/* 261 */       toAdd.lighten(true);
/* 262 */       this.exhaustPileCopy.addToBottom(toAdd);
/*     */     } 
/*     */     
/* 265 */     if (!AbstractDungeon.player.hasRelic("Frozen Eye")) {
/* 266 */       this.exhaustPileCopy.sortAlphabetically(true);
/* 267 */       this.exhaustPileCopy.sortByRarityPlusStatusCardType(true);
/*     */     } 
/* 269 */     hideCards();
/*     */     
/* 271 */     if (this.exhaustPileCopy.group.size() <= 5) {
/* 272 */       drawStartY = Settings.HEIGHT * 0.5F;
/*     */     } else {
/* 274 */       drawStartY = Settings.HEIGHT * 0.66F;
/*     */     } 
/*     */     
/* 277 */     calculateScrollBounds();
/*     */   }
/*     */   
/*     */   private void hideCards() {
/* 281 */     int lineNum = 0;
/* 282 */     ArrayList<AbstractCard> cards = this.exhaustPileCopy.group;
/* 283 */     for (int i = 0; i < cards.size(); i++) {
/* 284 */       int mod = i % 5;
/* 285 */       if (mod == 0 && i != 0) {
/* 286 */         lineNum++;
/*     */       }
/* 288 */       ((AbstractCard)cards.get(i)).current_x = drawStartX + mod * padX;
/* 289 */       ((AbstractCard)cards.get(i)).current_y = drawStartY + this.currentDiffY - lineNum * padY - MathUtils.random(100.0F * Settings.scale, 200.0F * Settings.scale);
/*     */ 
/*     */       
/* 292 */       ((AbstractCard)cards.get(i)).targetDrawScale = 0.75F;
/* 293 */       ((AbstractCard)cards.get(i)).drawScale = 0.75F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 298 */     if (shouldShowScrollBar()) {
/* 299 */       this.scrollBar.render(sb);
/*     */     }
/*     */     
/* 302 */     if (this.hoveredCard == null) {
/* 303 */       this.exhaustPileCopy.render(sb);
/*     */     } else {
/* 305 */       this.exhaustPileCopy.renderExceptOneCard(sb, this.hoveredCard);
/* 306 */       this.hoveredCard.renderHoverShadow(sb);
/* 307 */       this.hoveredCard.render(sb);
/* 308 */       this.hoveredCard.renderCardTip(sb);
/*     */     } 
/*     */     
/* 311 */     FontHelper.renderDeckViewTip(sb, DESC, 96.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 316 */     this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
/* 317 */     updateBarPosition();
/*     */   }
/*     */   
/*     */   private void updateBarPosition() {
/* 321 */     float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
/* 322 */     this.scrollBar.parentScrolledToPercent(percent);
/*     */   }
/*     */   
/*     */   private boolean shouldShowScrollBar() {
/* 326 */     return (this.scrollUpperBound > SCROLL_BAR_THRESHOLD);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\ExhaustPileViewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */