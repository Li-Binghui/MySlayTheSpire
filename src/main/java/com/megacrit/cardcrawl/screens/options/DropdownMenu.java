/*     */ package com.megacrit.cardcrawl.screens.options;
/*     */ 
/*     */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.controller.CInputHelper;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

import java.util.ArrayList;
import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ public class DropdownMenu
/*     */   implements ScrollBarListener
/*     */ {
/*     */   private DropdownMenuListener listener;
/*     */   public ArrayList<DropdownRow> rows;
/*     */   public boolean isOpen = false;
/*  28 */   public int topVisibleRowIndex = 0;
/*     */   
/*     */   private int maxRows;
/*     */   
/*     */   private Color textColor;
/*     */   
/*     */   private BitmapFont textFont;
/*     */   
/*     */   private DropdownRow selectionBox;
/*     */   private ScrollBar scrollBar;
/*     */   private static final int DEFAULT_MAX_ROWS = 15;
/*  39 */   private static final float PADDING_FROM_SOURCE_HITBOX = 10.0F * Settings.scale;
/*  40 */   private static final float SCROLLBAR_RIGHT_PADDING = 2.0F * Settings.scale;
/*     */ 
/*     */   
/*  43 */   private static final float BOX_EDGE_H = 32.0F * Settings.scale;
/*  44 */   private static final float BOX_BODY_H = 64.0F * Settings.scale;
/*     */   
/*     */   private boolean shouldSnapCursorToSelectedIndex = false;
/*     */   private boolean rowsHaveBeenPositioned = false;
/*     */   private float cachedMaxWidth;
/*     */   
/*     */   public DropdownMenu(DropdownMenuListener listener, String[] options, BitmapFont font, Color textColor) {
/*  51 */     this(listener, new ArrayList<>(Arrays.asList(options)), font, textColor);
/*     */   }
/*     */   
/*     */   public DropdownMenu(DropdownMenuListener listener, ArrayList<String> options, BitmapFont font, Color textColor) {
/*  55 */     this(listener, options, font, textColor, 15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DropdownMenu(DropdownMenuListener listener, String[] options, BitmapFont font, Color textColor, int maxRows) {
/*  64 */     this(listener, new ArrayList<>(Arrays.asList(options)), font, textColor, maxRows);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateResolutionLabels(int mode) {
/*  93 */     this.rows.clear();
/*  94 */     ArrayList<String> options = CardCrawlGame.mainMenuScreen.optionPanel.getResolutionLabels(mode);
/*  95 */     float width = approximateOverallWidth(options) - this.scrollBar.width();
/*     */     
/*  97 */     if (options.size() > 0) {
/*  98 */       this.selectionBox = new DropdownRow(options.get(0), this.cachedMaxWidth, approximateRowHeight(), 0);
/*  99 */       this.selectionBox.isSelected = true;
/*     */       
/* 101 */       for (String option : options) {
/* 102 */         this.rows.add(new DropdownRow(option, width, approximateRowHeight(), this.rows.size()));
/*     */       }
/*     */       
/* 105 */       ((DropdownRow)this.rows.get(this.rows.size() - 1)).isSelected = true;
/* 106 */       CardCrawlGame.mainMenuScreen.optionPanel.resetResolutionDropdownSelection();
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean shouldShowSlider() {
/* 111 */     return (this.rows.size() > this.maxRows);
/*     */   }
/*     */   
/*     */   public Hitbox getHitbox() {
/* 115 */     return this.selectionBox.hb;
/*     */   }
/*     */   
/*     */   private int visibleRowCount() {
/* 119 */     return Math.min(this.rows.size(), this.maxRows);
/*     */   }
/*     */   
/*     */   void layoutRowsBelow(float originX, float originY) {
/* 123 */     this.selectionBox.position(originX, yPositionForRowBelow(originY, 0, 0.0F));
/*     */     
/* 125 */     for (int i = 0; i < visibleRowCount(); i++) {
/* 126 */       ((DropdownRow)this.rows.get(this.topVisibleRowIndex + i)).position(originX, yPositionForRowBelow(originY, i + 1, 0.0F));
/*     */     }
/*     */     
/* 129 */     if (shouldShowSlider()) {
/* 130 */       float top = yPositionForRowBelow(originY, 1, 0.0F);
/* 131 */       float bottom = yPositionForRowBelow(originY, visibleRowCount() + 1, 0.0F);
/* 132 */       float right = originX + this.cachedMaxWidth - SCROLLBAR_RIGHT_PADDING;
/* 133 */       this.scrollBar.positionWithinOnRight(right, top, bottom);
/*     */     } 
/* 135 */     this.rowsHaveBeenPositioned = true;
/*     */   }
/*     */   
/*     */   public float approximateRowHeight() {
/* 139 */     float extraSpace = Math.min(Math.max(this.textFont.getCapHeight(), 15.0F) * Settings.scale, 15.0F);
/* 140 */     return this.textFont.getCapHeight() + extraSpace;
/*     */   }
/*     */   
/*     */   private float approximateWidthForText(String option) {
/* 144 */     return FontHelper.getSmartWidth(this.textFont, option, Float.MAX_VALUE, Float.MAX_VALUE);
/*     */   }
/*     */   
/* 147 */   public DropdownMenu(DropdownMenuListener listener, ArrayList<String> options, BitmapFont font, Color textColor, int maxRows) { this.cachedMaxWidth = -1.0F; this.listener = listener; this.textFont = font; this.textColor = textColor; this.maxRows = maxRows; this.rows = new ArrayList<>(options.size()); this.scrollBar = new ScrollBar(this); float width = approximateOverallWidth(options) - this.scrollBar.width(); if (options.size() > 0) { this.selectionBox = new DropdownRow(options.get(0), this.cachedMaxWidth, approximateRowHeight(), 0); this.selectionBox.isSelected = true; for (String option : options)
/* 148 */         this.rows.add(new DropdownRow(option, width, approximateRowHeight(), this.rows.size()));  ((DropdownRow)this.rows.get(0)).isSelected = true; }  } private static final float ICON_WIDTH = 68.0F * Settings.scale;
/*     */   
/*     */   public float approximateOverallWidth() {
/* 151 */     if (this.cachedMaxWidth > 0.0F) {
/* 152 */       return this.cachedMaxWidth;
/*     */     }
/*     */     
/* 155 */     ArrayList<String> options = new ArrayList<>();
/* 156 */     for (DropdownRow row : this.rows) {
/* 157 */       options.add(row.text);
/*     */     }
/* 159 */     return approximateOverallWidth(options);
/*     */   }
/*     */   
/*     */   private float approximateOverallWidth(ArrayList<String> options) {
/* 163 */     if (this.cachedMaxWidth > 0.0F) {
/* 164 */       return this.cachedMaxWidth;
/*     */     }
/*     */     
/* 167 */     for (String option : options) {
/* 168 */       this.cachedMaxWidth = Math.max(this.cachedMaxWidth, approximateWidthForText(option) + ICON_WIDTH);
/*     */     }
/* 170 */     return this.cachedMaxWidth;
/*     */   }
/*     */   
/*     */   private float yPositionForRowBelow(float originY, int rowIndex, float scrollAmount) {
/* 174 */     float rowHeight = approximateRowHeight();
/* 175 */     float extraHeight = (rowIndex > 0) ? PADDING_FROM_SOURCE_HITBOX : 0.0F;
/* 176 */     return originY - rowHeight * rowIndex - extraHeight;
/*     */   }
/*     */   
/*     */   public void setSelectedIndex(int i) {
/* 180 */     if (this.selectionBox.index == i) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 185 */     if (i < this.rows.size()) {
/* 186 */       changeSelectionToRow(this.rows.get(i));
/*     */     }
/*     */     
/* 189 */     this.topVisibleRowIndex = Math.min(i, this.rows.size() - visibleRowCount());
/* 190 */     float scrollPercent = scrollPercentForTopVisibleRowIndex(this.topVisibleRowIndex);
/* 191 */     this.scrollBar.parentScrolledToPercent(scrollPercent);
/*     */   }
/*     */   
/*     */   public float scrollPercentForTopVisibleRowIndex(int topIndex) {
/* 195 */     int maxRow = this.rows.size() - visibleRowCount();
              maxRow= maxRow==0?1:maxRow;
/* 196 */     return topIndex / maxRow;
/*     */   }
/*     */   
/*     */   public int topVisibleRowIndexForScrollPercent(float percent) {
/* 200 */     int maxRow = this.rows.size() - visibleRowCount();
/* 201 */     return (int)(maxRow * percent);
/*     */   }
/*     */   
/*     */   public int getSelectedIndex() {
/* 205 */     return this.selectionBox.index;
/*     */   }
/*     */   
/*     */   private void changeSelectionToRow(DropdownRow newSelection) {
/* 209 */     this.selectionBox.text = newSelection.text;
/* 210 */     this.selectionBox.index = newSelection.index;
/*     */     
/* 212 */     for (DropdownRow row : this.rows) {
/* 213 */       row.isSelected = (row == newSelection);
/*     */     }
/*     */     
/* 216 */     if (this.listener != null) {
/* 217 */       this.listener.changedSelectionTo(this, newSelection.index, newSelection.text);
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/* 222 */     if (this.rows.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 226 */     if (this.isOpen) {
/* 227 */       updateNonMouseInput();
/*     */       
/* 229 */       for (int i = 0; i < visibleRowCount(); i++) {
/* 230 */         DropdownRow row = this.rows.get(i + this.topVisibleRowIndex);
/* 231 */         if (row.update()) {
/* 232 */           changeSelectionToRow(row);
/* 233 */           this.isOpen = false;
/* 234 */           CardCrawlGame.sound.play("UI_CLICK_2");
/*     */         } 
/*     */       } 
/*     */       
/* 238 */       if (InputHelper.scrolledDown) {
/* 239 */         this.topVisibleRowIndex = Integer.min(this.topVisibleRowIndex + 1, this.rows.size() - visibleRowCount());
/* 240 */         this.scrollBar.parentScrolledToPercent(scrollPercentForTopVisibleRowIndex(this.topVisibleRowIndex));
/* 241 */       } else if (InputHelper.scrolledUp) {
/* 242 */         this.topVisibleRowIndex = Integer.max(0, this.topVisibleRowIndex - 1);
/* 243 */         this.scrollBar.parentScrolledToPercent(scrollPercentForTopVisibleRowIndex(this.topVisibleRowIndex));
/*     */       } 
/*     */       
/* 246 */       boolean sliderClicked = false;
/*     */       
/* 248 */       if (shouldShowSlider()) {
/* 249 */         sliderClicked = this.scrollBar.update();
/*     */       }
/*     */ 
/*     */       
/* 253 */       boolean shouldCloseMenu = ((InputHelper.justReleasedClickLeft && !sliderClicked) || CInputActionSet.cancel.isJustPressed());
/*     */       
/* 255 */       if (shouldCloseMenu) {
/* 256 */         if (Settings.isControllerMode) {
/* 257 */           CInputActionSet.cancel.unpress();
/* 258 */           CInputHelper.setCursor(getHitbox());
/*     */         } 
/* 260 */         this.isOpen = false;
/*     */       }
/*     */     
/* 263 */     } else if (this.selectionBox.update()) {
/* 264 */       this.isOpen = true;
/* 265 */       updateNonMouseStartPosition();
/* 266 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isUsingNonMouseControl() {
/* 272 */     return (Settings.isControllerMode || InputActionSet.up.isJustPressed() || InputActionSet.down.isJustPressed());
/*     */   }
/*     */   
/*     */   private void updateNonMouseStartPosition() {
/* 276 */     if (!isUsingNonMouseControl()) {
/*     */       return;
/*     */     }
/* 279 */     this.shouldSnapCursorToSelectedIndex = true;
/*     */   }
/*     */   
/*     */   private void updateNonMouseInput() {
/* 283 */     if (!isUsingNonMouseControl()) {
/*     */       return;
/*     */     }
/* 286 */     if (this.shouldSnapCursorToSelectedIndex && this.rowsHaveBeenPositioned) {
/* 287 */       CInputHelper.setCursor(((DropdownRow)this.rows.get(this.selectionBox.index)).hb);
/* 288 */       this.shouldSnapCursorToSelectedIndex = false;
/*     */       
/*     */       return;
/*     */     } 
/* 292 */     int hoveredIndex = -1;
/*     */     
/* 294 */     for (int i = this.topVisibleRowIndex; i < this.topVisibleRowIndex + visibleRowCount(); i++) {
/* 295 */       if (((DropdownRow)this.rows.get(i)).hb.hovered) {
/* 296 */         hoveredIndex = i;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 301 */     if (hoveredIndex < 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 306 */     boolean didInputUp = (CInputActionSet.up.isJustPressed() || CInputActionSet.altUp.isJustPressed() || InputActionSet.up.isJustPressed());
/*     */     
/* 308 */     boolean didInputDown = (CInputActionSet.down.isJustPressed() || CInputActionSet.altDown.isJustPressed() || InputActionSet.down.isJustPressed());
/* 309 */     boolean isMoving = (didInputUp || didInputDown);
/* 310 */     if (!isMoving) {
/*     */       return;
/*     */     }
/* 313 */     int targetHoverIndexOffset = didInputDown ? 1 : -1;
/*     */     
/* 315 */     int targetHoverIndex = (hoveredIndex + targetHoverIndexOffset + this.rows.size()) % this.rows.size();
/*     */ 
/*     */     
/* 318 */     boolean isAboveTheTop = (targetHoverIndex < this.topVisibleRowIndex);
/* 319 */     boolean isBelowTheBottom = (targetHoverIndex >= this.topVisibleRowIndex + visibleRowCount());
/* 320 */     if (isAboveTheTop) {
/* 321 */       if (didInputDown)
/*     */       {
/* 323 */         CInputHelper.setCursor(((DropdownRow)this.rows.get(this.topVisibleRowIndex)).hb);
/*     */       }
/* 325 */       this.topVisibleRowIndex = targetHoverIndex;
/* 326 */     } else if (isBelowTheBottom) {
/* 327 */       if (didInputUp) {
/*     */         
/* 329 */         CInputHelper.setCursor(((DropdownRow)this.rows.get(this.topVisibleRowIndex + visibleRowCount() - 1)).hb);
/* 330 */         ((DropdownRow)this.rows.get(targetHoverIndex)).hb.hovered = true;
/*     */       } 
/* 332 */       this.topVisibleRowIndex = targetHoverIndex - visibleRowCount() + 1;
/*     */     } else {
/* 334 */       CInputHelper.setCursor(((DropdownRow)this.rows.get(targetHoverIndex)).hb);
/*     */     } 
/*     */     
/* 337 */     if (shouldShowSlider()) {
/* 338 */       this.scrollBar.parentScrolledToPercent(scrollPercentForTopVisibleRowIndex(this.topVisibleRowIndex));
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float x, float y) {
/* 343 */     if (this.rows.size() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 347 */     int rowCount = this.isOpen ? visibleRowCount() : 0;
/* 348 */     float heightOfSelector = approximateRowHeight() * 1.0F - BOX_EDGE_H * 2.5F;
/*     */     
/* 350 */     layoutRowsBelow(x, y);
/* 351 */     float topY = yPositionForRowBelow(y, 0, 0.0F);
/* 352 */     float bottomY = yPositionForRowBelow(y, rowCount + 1, 0.0F);
/* 353 */     if (this.isOpen) {
/* 354 */       renderBorder(sb, x, bottomY, this.cachedMaxWidth, topY - bottomY);
/*     */     }
/* 356 */     renderBorderFromTop(sb, x, y, this.cachedMaxWidth, heightOfSelector);
/*     */     
/* 358 */     this.selectionBox.renderRow(sb);
/* 359 */     if (this.isOpen) {
/*     */ 
/*     */ 
/*     */       
/* 363 */       for (int i = 0; i < visibleRowCount(); i++) {
/* 364 */         ((DropdownRow)this.rows.get(i + this.topVisibleRowIndex)).renderRow(sb);
/*     */       }
/* 366 */       if (shouldShowSlider()) {
/* 367 */         this.scrollBar.render(sb);
/*     */       }
/*     */     } 
/*     */     
/* 371 */     float ARROW_ICON_W = 30.0F * Settings.scale;
/* 372 */     float ARROW_ICON_H = 30.0F * Settings.scale;
/* 373 */     float arrowIconX = x + this.cachedMaxWidth - ARROW_ICON_W - Settings.scale * 10.0F;
/* 374 */     float arrowIconY = y - ARROW_ICON_H;
/* 375 */     Texture dropdownArrowIcon = this.isOpen ? ImageMaster.OPTION_TOGGLE_ON : ImageMaster.FILTER_ARROW;
/* 376 */     sb.draw(dropdownArrowIcon, arrowIconX, arrowIconY, ARROW_ICON_W, ARROW_ICON_H);
/*     */   }
/*     */   
/*     */   private void renderBorder(SpriteBatch sb, float x, float bottom, float width, float height) {
/* 380 */     float border = Settings.scale * 10.0F;
/*     */     
/* 382 */     float BOX_W = width + 2.0F * border;
/* 383 */     float FRAME_X = x - border;
/*     */     
/* 385 */     float rowHeight = approximateRowHeight();
/*     */     
/* 387 */     sb.setColor(Color.WHITE);
/* 388 */     float bottomY = bottom - border;
/* 389 */     sb.draw(ImageMaster.KEYWORD_BOT, FRAME_X, bottomY, BOX_W, rowHeight);
/* 390 */     float middleHeight = height - 2.0F * rowHeight - border;
/* 391 */     sb.draw(ImageMaster.KEYWORD_BODY, FRAME_X, bottomY + rowHeight, BOX_W, middleHeight);
/* 392 */     sb.draw(ImageMaster.KEYWORD_TOP, FRAME_X, bottom + middleHeight + border, BOX_W, rowHeight);
/*     */   }
/*     */   
/*     */   private void renderBorderFromTop(SpriteBatch sb, float x, float top, float width, float height) {
/* 396 */     float border = Settings.scale * 10.0F;
/*     */     
/* 398 */     float BORDER_TOP_Y = top - BOX_EDGE_H + border;
/* 399 */     float BOX_W = width + 2.0F * border;
/* 400 */     float FRAME_X = x - border;
/*     */ 
/*     */     
/* 403 */     sb.setColor(Color.WHITE);
/* 404 */     sb.draw(ImageMaster.KEYWORD_TOP, FRAME_X, BORDER_TOP_Y, BOX_W, BOX_EDGE_H);
/* 405 */     sb.draw(ImageMaster.KEYWORD_BODY, FRAME_X, BORDER_TOP_Y - height - BOX_EDGE_H, BOX_W, height + BOX_EDGE_H);
/* 406 */     sb.draw(ImageMaster.KEYWORD_BOT, FRAME_X, BORDER_TOP_Y - height - BOX_BODY_H, BOX_W, BOX_EDGE_H);
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrolledUsingBar(float newPercent) {
/* 411 */     this.topVisibleRowIndex = topVisibleRowIndexForScrollPercent(newPercent);
/* 412 */     this.scrollBar.parentScrolledToPercent(newPercent);
/*     */   }
/*     */   
/*     */   private class DropdownRow {
/*     */     String text;
/*     */     Hitbox hb;
/*     */     boolean isSelected = false;
/*     */     int index;
/*     */     
/*     */     DropdownRow(String text, float width, float height, int index) {
/* 422 */       this.text = text;
/* 423 */       this.hb = new Hitbox(width, height);
/* 424 */       this.index = index;
/*     */     }
/*     */     
/*     */     private void position(float x, float y) {
/* 428 */       this.hb.x = x;
/* 429 */       this.hb.y = y - this.hb.height;
/* 430 */       this.hb.cX = this.hb.x + this.hb.width / 2.0F;
/* 431 */       this.hb.cY = this.hb.y + this.hb.height / 2.0F;
/*     */     }
/*     */     
/*     */     private boolean update() {
/* 435 */       this.hb.update();
/*     */ 
/*     */       
/* 438 */       if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 439 */         this.hb.clickStarted = true;
/*     */       }
/*     */       
/* 442 */       if (this.hb.clicked || (this.hb.hovered && CInputActionSet.select.isJustPressed())) {
/* 443 */         this.hb.clicked = false;
/* 444 */         return true;
/*     */       } 
/* 446 */       return false;
/*     */     }
/*     */     
/*     */     private void renderRow(SpriteBatch sb) {
/* 450 */       this.hb.render(sb);
/*     */       
/* 452 */       Color renderTextColor = DropdownMenu.this.textColor;
/* 453 */       if (this.hb.hovered) {
/* 454 */         renderTextColor = Settings.GREEN_TEXT_COLOR;
/* 455 */       } else if (this.isSelected) {
/* 456 */         renderTextColor = Settings.GOLD_COLOR;
/*     */       } 
/*     */       
/* 459 */       float textX = this.hb.x + Settings.scale * 10.0F;
/* 460 */       float a = DropdownMenu.this.approximateRowHeight();
/* 461 */       float b = DropdownMenu.this.textFont.getCapHeight();
/* 462 */       float yOffset = (a - b) / 2.0F;
/* 463 */       float textY = this.hb.y - yOffset;
/*     */       
/* 465 */       FontHelper.renderSmartText(sb, DropdownMenu.this
/*     */           
/* 467 */           .textFont, this.text, textX, textY + this.hb.height, Float.MAX_VALUE, Float.MAX_VALUE, renderTextColor);
/*     */     }
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\options\DropdownMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */