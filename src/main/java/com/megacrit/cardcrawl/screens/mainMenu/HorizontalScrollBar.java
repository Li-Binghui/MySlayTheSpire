/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HorizontalScrollBar
/*     */ {
/*     */   public ScrollBarListener sliderListener;
/*     */   public boolean isBackgroundVisible = true;
/*     */   private Hitbox sliderHb;
/*     */   private float currentScrollPercent;
/*     */   private boolean isDragging;
/*  20 */   public static final float TRACK_H = 54.0F * Settings.scale;
/*  21 */   private final float TRACK_CAP_WIDTH = TRACK_H;
/*  22 */   private final float CURSOR_W = 60.0F * Settings.scale;
/*  23 */   private final float CURSOR_H = 38.0F * Settings.scale;
/*  24 */   private final float DRAW_BORDER = this.CURSOR_W / 4.0F;
/*     */   
/*  26 */   private float cursorDrawPosition = 0.0F;
/*     */   
/*     */   public HorizontalScrollBar(ScrollBarListener listener) {
/*  29 */     this(listener, Settings.WIDTH / 2.0F, Settings.HEIGHT - 150.0F * Settings.scale - TRACK_H / 2.0F, Settings.WIDTH - 256.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  34 */     this.cursorDrawPosition = getXPositionForPercent(0.0F);
/*     */   }
/*     */   
/*     */   public HorizontalScrollBar(ScrollBarListener listener, float x, float y, float width) {
/*  38 */     this.sliderListener = listener;
/*  39 */     this.currentScrollPercent = 0.0F;
/*  40 */     this.isDragging = false;
/*     */     
/*  42 */     this.sliderHb = new Hitbox(width, TRACK_H);
/*  43 */     this.sliderHb.move(x, y);
/*  44 */     reset();
/*     */   }
/*     */   
/*     */   public void setCenter(float x, float y) {
/*  48 */     this.sliderHb.move(x, y);
/*  49 */     reset();
/*     */   }
/*     */   
/*     */   public void move(float xOffset, float yOffset) {
/*  53 */     this.sliderHb.move(this.sliderHb.cX + xOffset, this.sliderHb.cY + yOffset);
/*  54 */     reset();
/*     */   }
/*     */   
/*     */   public void changeWidth(float newWidth) {
/*  58 */     this.sliderHb.width = newWidth;
/*  59 */     this.sliderHb.move(this.sliderHb.cX, this.sliderHb.cY);
/*  60 */     reset();
/*     */   }
/*     */   
/*     */   public float width() {
/*  64 */     return this.sliderHb.width;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  68 */     this.cursorDrawPosition = getXPositionForPercent(0.0F);
/*     */   }
/*     */   
/*     */   public boolean update() {
/*  72 */     this.sliderHb.update();
/*  73 */     if (this.sliderHb.hovered && InputHelper.isMouseDown) {
/*  74 */       this.isDragging = true;
/*     */     }
/*  76 */     if (this.isDragging && InputHelper.justReleasedClickLeft) {
/*  77 */       this.isDragging = false;
/*  78 */       return true;
/*     */     } 
/*  80 */     if (this.isDragging) {
/*  81 */       float newPercent = getPercentFromX(InputHelper.mX);
/*  82 */       this.sliderListener.scrolledUsingBar(newPercent);
/*  83 */       return true;
/*     */     } 
/*     */     
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public void parentScrolledToPercent(float percent) {
/*  90 */     this.currentScrollPercent = boundedPercent(percent);
/*     */   }
/*     */   
/*     */   private float getPercentFromX(float x) {
/*  94 */     float minX = this.sliderHb.x + this.DRAW_BORDER;
/*  95 */     float maxX = this.sliderHb.x + this.sliderHb.width - this.DRAW_BORDER;
/*  96 */     return boundedPercent(MathHelper.percentFromValueBetween(minX, maxX, x));
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 100 */     Color previousColor = sb.getColor();
/* 101 */     sb.setColor(Color.WHITE);
/*     */     
/* 103 */     if (this.isBackgroundVisible) {
/* 104 */       renderTrack(sb);
/*     */     }
/* 106 */     renderCursor(sb);
/* 107 */     this.sliderHb.render(sb);
/* 108 */     sb.setColor(previousColor);
/*     */   }
/*     */   
/*     */   private float getXPositionForPercent(float percent) {
/* 112 */     float topX = this.sliderHb.x - this.DRAW_BORDER;
/* 113 */     float bottomX = this.sliderHb.x + this.sliderHb.width - this.CURSOR_W + this.DRAW_BORDER;
/* 114 */     return MathHelper.valueFromPercentBetween(topX, bottomX, boundedPercent(percent));
/*     */   }
/*     */   
/*     */   private void renderCursor(SpriteBatch sb) {
/* 118 */     float y = this.sliderHb.cY - this.CURSOR_H / 2.0F;
/* 119 */     float xForPercent = getXPositionForPercent(this.currentScrollPercent);
/*     */     
/* 121 */     this.cursorDrawPosition = MathHelper.scrollSnapLerpSpeed(this.cursorDrawPosition, xForPercent);
/* 122 */     sb.draw(ImageMaster.SCROLL_BAR_HORIZONTAL_TRAIN, this.cursorDrawPosition, y, this.CURSOR_W, this.CURSOR_H);
/*     */   }
/*     */   
/*     */   private void renderTrack(SpriteBatch sb) {
/* 126 */     sb.draw(ImageMaster.SCROLL_BAR_HORIZONTAL_MIDDLE, this.sliderHb.x, this.sliderHb.y, this.sliderHb.width, this.sliderHb.height);
/* 127 */     sb.draw(ImageMaster.SCROLL_BAR_RIGHT, this.sliderHb.x + this.sliderHb.width, this.sliderHb.y, this.TRACK_CAP_WIDTH, this.sliderHb.height);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     sb.draw(ImageMaster.SCROLL_BAR_LEFT, this.sliderHb.x - this.TRACK_CAP_WIDTH, this.sliderHb.y, this.TRACK_CAP_WIDTH, this.sliderHb.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float boundedPercent(float percent) {
/* 142 */     return Math.max(0.0F, Math.min(percent, 1.0F));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\HorizontalScrollBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */