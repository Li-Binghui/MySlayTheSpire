/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.DrawMaster;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.vfx.TintEffect;
/*     */ 
/*     */ public class RetryButton {
/*     */   public static final int RAW_W = 512;
/*  16 */   private static final float BUTTON_W = 240.0F * Settings.scale;
/*  17 */   private static final float BUTTON_H = 160.0F * Settings.scale;
/*     */   private static final float LERP_SPEED = 9.0F;
/*  19 */   private static final Color TEXT_SHOW_COLOR = new Color(0.9F, 0.9F, 0.9F, 1.0F);
/*  20 */   private static final Color HIGHLIGHT_COLOR = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  21 */   private static final Color IDLE_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
/*  22 */   private static final Color FADE_COLOR = new Color(0.3F, 0.3F, 0.3F, 1.0F);
/*     */   public String label;
/*     */   public float x;
/*     */   public float y;
/*     */   public Hitbox hb;
/*  27 */   protected TintEffect tint = new TintEffect();
/*  28 */   protected TintEffect textTint = new TintEffect();
/*     */   
/*     */   public boolean pressed = false;
/*     */   public boolean isMoving = false;
/*     */   
/*     */   public RetryButton() {
/*  34 */     this.tint.color.a = 0.0F;
/*  35 */     this.textTint.color.a = 0.0F;
/*  36 */     this.hb = new Hitbox(-10000.0F, -10000.0F, BUTTON_W, BUTTON_H);
/*     */   }
/*     */   public boolean show = false; public int height; public int width;
/*     */   public void appear(float x, float y, String label) {
/*  40 */     this.x = x;
/*  41 */     this.y = y;
/*  42 */     this.label = label;
/*  43 */     this.pressed = false;
/*  44 */     this.isMoving = true;
/*  45 */     this.show = true;
/*  46 */     this.tint.changeColor(IDLE_COLOR, 9.0F);
/*  47 */     this.textTint.changeColor(TEXT_SHOW_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void hide() {
/*  51 */     this.show = false;
/*  52 */     this.isMoving = false;
/*  53 */     this.tint.changeColor(FADE_COLOR, 9.0F);
/*  54 */     this.textTint.changeColor(FADE_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  58 */     this.tint.update();
/*  59 */     this.textTint.update();
/*     */     
/*  61 */     if (this.show) {
/*  62 */       this.hb.move(this.x, this.y);
/*  63 */       this.hb.update();
/*     */       
/*  65 */       if (InputHelper.justClickedLeft && this.hb.hovered) {
/*  66 */         this.hb.clickStarted = true;
/*  67 */         CardCrawlGame.sound.play("UI_CLICK_1");
/*     */       } 
/*  69 */       if (this.hb.justHovered) {
/*  70 */         CardCrawlGame.sound.play("UI_HOVER");
/*     */       }
/*     */       
/*  73 */       if (this.hb.hovered) {
/*  74 */         this.tint.changeColor(HIGHLIGHT_COLOR, 18.0F);
/*     */       } else {
/*  76 */         this.tint.changeColor(IDLE_COLOR, 9.0F);
/*     */       } 
/*     */       
/*  79 */       if (this.hb.clicked) {
/*  80 */         this.hb.clicked = false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     if (this.textTint.color.a != 0.0F && this.label != null) {
/*  88 */       if (this.hb.clickStarted) {
/*  89 */         DrawMaster.queue(FontHelper.panelEndTurnFont, this.label, this.x, this.y, 650, 1.0F, Color.LIGHT_GRAY);
/*     */       } else {
/*  91 */         DrawMaster.queue(FontHelper.panelEndTurnFont, this.label, this.x, this.y, 650, 1.0F, this.textTint.color);
/*     */       } 
/*  93 */       if (this.hb.clickStarted) {
/*  94 */         DrawMaster.queue(ImageMaster.DYNAMIC_BTN_IMG3, this.x, this.y, 600, Color.LIGHT_GRAY);
/*     */       } else {
/*  96 */         DrawMaster.queue(ImageMaster.DYNAMIC_BTN_IMG3, this.x, this.y, 600, this.tint.color);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 102 */     if (!this.pressed && this.show)
/* 103 */       this.hb.render(sb); 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\RetryButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */