/*     */ package com.megacrit.cardcrawl.screens.mainMenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ShaderHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScrollBar
/*     */ {
/*     */   public ScrollBarListener sliderListener;
/*     */   public boolean isBackgroundVisible = true;
/*     */   private Hitbox sliderHb;
/*     */   private float currentScrollPercent;
/*     */   private boolean isDragging;
/*     */   private boolean isInteractable = true;
/*  23 */   public static final float TRACK_W = 54.0F * Settings.scale;
/*  24 */   private final float TRACK_CAP_HEIGHT = TRACK_W;
/*  25 */   private final float TRACK_CAP_VISIBLE_HEIGHT = 22.0F * Settings.scale;
/*  26 */   private final float CURSOR_W = 38.0F * Settings.scale;
/*  27 */   private final float CURSOR_H = 60.0F * Settings.scale;
/*  28 */   private final float DRAW_BORDER = this.CURSOR_H / 4.0F;
/*     */   
/*  30 */   private float cursorDrawPosition = 0.0F;
/*     */   
/*     */   public ScrollBar(ScrollBarListener listener) {
/*  33 */     this(listener, Settings.WIDTH - 150.0F * Settings.scale - TRACK_W / 2.0F, Settings.HEIGHT / 2.0F, Settings.HEIGHT - 256.0F * Settings.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.cursorDrawPosition = getYPositionForPercent(0.0F);
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
/*     */   public ScrollBar(ScrollBarListener listener, float x, float y, float height) {
/*  54 */     this.sliderListener = listener;
/*  55 */     this.currentScrollPercent = 0.0F;
/*  56 */     this.isDragging = false;
/*     */     
/*  58 */     this.sliderHb = new Hitbox(TRACK_W, height);
/*  59 */     this.sliderHb.move(x, y);
/*  60 */     reset();
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
/*     */   public ScrollBar(ScrollBarListener listener, float x, float y, float height, boolean isProgressBar) {
/*  74 */     this.isInteractable = !isProgressBar;
/*  75 */     this.sliderListener = listener;
/*  76 */     this.currentScrollPercent = 0.0F;
/*  77 */     this.isDragging = false;
/*  78 */     this.sliderHb = new Hitbox(TRACK_W / 2.0F, height);
/*  79 */     this.sliderHb.move(x, y);
/*  80 */     reset();
/*     */   }
/*     */   
/*     */   public void setCenter(float x, float y) {
/*  84 */     this.sliderHb.move(x, y);
/*  85 */     reset();
/*     */   }
/*     */   
/*     */   public void move(float xOffset, float yOffset) {
/*  89 */     this.sliderHb.move(this.sliderHb.cX + xOffset, this.sliderHb.cY + yOffset);
/*  90 */     reset();
/*     */   }
/*     */   
/*     */   public void changeHeight(float newHeight) {
/*  94 */     this.sliderHb.height = newHeight;
/*  95 */     this.sliderHb.move(this.sliderHb.cX, this.sliderHb.cY);
/*  96 */     reset();
/*     */   }
/*     */   
/*     */   public void positionWithinOnRight(float right, float top, float bottom) {
/* 100 */     this.sliderHb.x = right - TRACK_W;
/* 101 */     setCenter(right - TRACK_W / 2.0F, (bottom + top) / 2.0F);
/* 102 */     changeHeight(top - bottom - 2.0F * this.TRACK_CAP_VISIBLE_HEIGHT);
/* 103 */     reset();
/*     */   }
/*     */   
/*     */   public float width() {
/* 107 */     return this.sliderHb.width;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 111 */     this.cursorDrawPosition = getYPositionForPercent(this.currentScrollPercent);
/*     */   }
/*     */   
/*     */   public boolean update() {
/* 115 */     if (!this.isInteractable) {
/* 116 */       return false;
/*     */     }
/*     */     
/* 119 */     this.sliderHb.update();
/* 120 */     if (this.sliderHb.hovered && InputHelper.isMouseDown) {
/* 121 */       this.isDragging = true;
/*     */     }
/* 123 */     if (this.isDragging && InputHelper.justReleasedClickLeft) {
/* 124 */       this.isDragging = false;
/* 125 */       return true;
/*     */     } 
/* 127 */     if (this.isDragging) {
/* 128 */       float newPercent = getPercentFromY(InputHelper.mY);
/* 129 */       this.sliderListener.scrolledUsingBar(newPercent);
/* 130 */       return true;
/*     */     } 
/*     */     
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   public void parentScrolledToPercent(float percent) {
/* 137 */     this.currentScrollPercent = boundedPercent(percent);
/*     */   }
/*     */   
/*     */   private float getPercentFromY(float y) {
/* 141 */     float minY = this.sliderHb.y + this.sliderHb.height - this.DRAW_BORDER;
/* 142 */     float maxY = this.sliderHb.y + this.DRAW_BORDER;
/* 143 */     return boundedPercent(MathHelper.percentFromValueBetween(minY, maxY, y));
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 147 */     if (!this.isInteractable) {
/*     */       return;
/*     */     }
/*     */     
/* 151 */     sb.setColor(Color.WHITE);
/* 152 */     if (this.isBackgroundVisible) {
/* 153 */       renderTrack(sb);
/*     */     }
/* 155 */     renderCursor(sb);
/* 156 */     this.sliderHb.render(sb);
/*     */   }
/*     */   
/*     */   private float getYPositionForPercent(float percent) {
/* 160 */     float topY = this.sliderHb.y + this.sliderHb.height - this.CURSOR_H + this.DRAW_BORDER;
/* 161 */     float bottomY = this.sliderHb.y - this.DRAW_BORDER;
/* 162 */     return MathHelper.valueFromPercentBetween(topY, bottomY, boundedPercent(percent));
/*     */   }
/*     */   
/*     */   private void renderCursor(SpriteBatch sb) {
/* 166 */     float x = this.sliderHb.cX - this.CURSOR_W / 2.0F;
/* 167 */     float yForPercent = getYPositionForPercent(this.currentScrollPercent);
/*     */     
/* 169 */     this.cursorDrawPosition = MathHelper.scrollSnapLerpSpeed(this.cursorDrawPosition, yForPercent);
/*     */     
/* 171 */     if (this.isInteractable) {
/* 172 */       sb.draw(ImageMaster.SCROLL_BAR_TRAIN, x, this.cursorDrawPosition, this.CURSOR_W, this.CURSOR_H);
/*     */     } else {
/* 174 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.GRAYSCALE);
/* 175 */       sb.draw(ImageMaster.SCROLL_BAR_TRAIN, x + this.CURSOR_W / 4.0F, this.cursorDrawPosition, this.CURSOR_W / 2.0F, this.CURSOR_H);
/* 176 */       ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderTrack(SpriteBatch sb) {
/* 181 */     sb.draw(ImageMaster.SCROLL_BAR_MIDDLE, this.sliderHb.x, this.sliderHb.y, this.sliderHb.width, this.sliderHb.height);
/* 182 */     sb.draw(ImageMaster.SCROLL_BAR_TOP, this.sliderHb.x, this.sliderHb.y + this.sliderHb.height, this.sliderHb.width, this.TRACK_CAP_HEIGHT);
/* 183 */     sb.draw(ImageMaster.SCROLL_BAR_BOTTOM, this.sliderHb.x, this.sliderHb.y - this.TRACK_CAP_HEIGHT, this.sliderHb.width, this.TRACK_CAP_HEIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float boundedPercent(float percent) {
/* 192 */     return Math.max(0.0F, Math.min(percent, 1.0F));
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\mainMenu\ScrollBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */