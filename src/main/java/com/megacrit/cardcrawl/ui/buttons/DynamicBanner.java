/*     */ package com.megacrit.cardcrawl.ui.buttons;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.TintEffect;
/*     */ 
/*     */ public class DynamicBanner {
/*     */   public static final int RAW_W = 1112;
/*     */   public static final int RAW_H = 238;
/*  14 */   private static final float Y_OFFSET = -50.0F * Settings.scale;
/*     */   private static final float ANIM_TIME = 0.5F;
/*     */   private static final float LERP_SPEED = 9.0F;
/*  17 */   private static final Color TEXT_SHOW_COLOR = new Color(0.9F, 0.9F, 0.9F, 1.0F);
/*  18 */   private static final Color IDLE_COLOR = new Color(0.7F, 0.7F, 0.7F, 1.0F);
/*  19 */   private static final Color FADE_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   
/*     */   private String label;
/*  22 */   private float animateTimer = 0.0F; public float y;
/*     */   public float targetY;
/*  24 */   public static final float Y = Settings.HEIGHT / 2.0F + 260.0F * Settings.scale; public float startY; public float scale;
/*  25 */   protected TintEffect tint = new TintEffect();
/*  26 */   protected TintEffect textTint = new TintEffect(); public boolean pressed = false; public boolean isMoving = false; public boolean show = false;
/*     */   public boolean isLarge = false;
/*     */   public int height;
/*     */   public int width;
/*     */   
/*     */   public DynamicBanner() {
/*  32 */     this.tint.color.a = 0.0F;
/*  33 */     this.textTint.color.a = 0.0F;
/*     */   }
/*     */   
/*     */   public void appear() {
/*  37 */     appear(Y, this.label);
/*     */   }
/*     */   
/*     */   public void appear(String label) {
/*  41 */     appear(Y, label);
/*     */   }
/*     */   
/*     */   public void appearInstantly(String label) {
/*  45 */     appearInstantly(Y, label);
/*     */   }
/*     */   
/*     */   public void appear(float y, String label) {
/*  49 */     this.startY = y + Y_OFFSET;
/*  50 */     this.y = y + Y_OFFSET;
/*  51 */     this.targetY = y;
/*  52 */     this.label = label;
/*  53 */     this.scale = 0.25F;
/*  54 */     this.pressed = false;
/*  55 */     this.isMoving = true;
/*  56 */     this.show = true;
/*  57 */     this.animateTimer = 0.5F;
/*  58 */     this.tint.changeColor(IDLE_COLOR, 9.0F);
/*  59 */     this.textTint.changeColor(TEXT_SHOW_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void appearInstantly(float y, String label) {
/*  63 */     this.isMoving = false;
/*  64 */     this.animateTimer = 0.0F;
/*  65 */     this.y = y;
/*  66 */     this.targetY = y;
/*  67 */     this.scale = 1.2F;
/*  68 */     this.label = label;
/*  69 */     this.pressed = false;
/*  70 */     this.show = true;
/*  71 */     this.tint.changeColor(IDLE_COLOR, 9.0F);
/*  72 */     this.textTint.changeColor(TEXT_SHOW_COLOR, 9.0F);
/*     */   }
/*     */   
/*     */   public void hide() {
/*  76 */     this.show = false;
/*  77 */     this.isMoving = false;
/*  78 */     this.tint.changeColor(FADE_COLOR, 18.0F);
/*  79 */     this.textTint.changeColor(FADE_COLOR, 18.0F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  83 */     this.tint.update();
/*  84 */     this.textTint.update();
/*     */     
/*  86 */     if (this.show) {
/*     */       
/*  88 */       this.animateTimer -= Gdx.graphics.getDeltaTime();
/*  89 */       if (this.animateTimer < 0.0F) {
/*  90 */         this.animateTimer = 0.0F;
/*  91 */         this.isMoving = false;
/*     */       } else {
/*  93 */         this.y = Interpolation.swingOut.apply(this.startY, this.targetY, (0.5F - this.animateTimer) * 2.0F);
/*  94 */         this.scale = Interpolation.swingOut.apply(0.0F, 1.2F, (0.5F - this.animateTimer) * 2.0F);
/*  95 */         if (this.scale <= 0.0F) {
/*  96 */           this.scale = 0.01F;
/*     */         }
/*     */       } 
/*     */       
/* 100 */       this.tint.changeColor(IDLE_COLOR, 9.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 105 */     if (this.textTint.color.a != 0.0F && this.label != null) {
/* 106 */       sb.setColor(this.tint.color);
/* 107 */       sb.draw(ImageMaster.VICTORY_BANNER, Settings.WIDTH / 2.0F - 556.0F, this.y - 119.0F, 556.0F, 119.0F, 1112.0F, 238.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 1112, 238, false, false);
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
/* 125 */       FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, this.label, Settings.WIDTH / 2.0F, this.y + 22.0F * Settings.scale, this.textTint.color, this.scale);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcraw\\ui\buttons\DynamicBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */