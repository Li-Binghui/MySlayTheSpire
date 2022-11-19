/*     */ package com.megacrit.cardcrawl.screens.splash;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class SplashScreen {
/*     */   private Texture img;
/*  16 */   private float timer = 0.0F; private static final float BOUNCE_DUR = 1.2F;
/*     */   private static final float FADE_DUR = 3.0F;
/*     */   private static final float WAIT_DUR = 1.5F;
/*  19 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F), bgColor = new Color(0.0F, 0.0F, 0.0F, 1.0F); private static final float FADE_OUT_DUR = 1.0F; private static final int W = 512; private static final int H = 512;
/*  20 */   private Color shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  21 */   private Phase phase = Phase.INIT;
/*     */   
/*     */   public boolean isDone = false;
/*  24 */   private static final float OFFSET_Y = 8.0F * Settings.scale; private static final float OFFSET_X = 12.0F * Settings.scale;
/*  25 */   private float x = Settings.WIDTH / 2.0F, y = Settings.HEIGHT / 2.0F - OFFSET_Y;
/*  26 */   private float sX = Settings.WIDTH / 2.0F; private float sY = Settings.HEIGHT / 2.0F;
/*  27 */   private Color cream = Color.valueOf("ffffdbff");
/*  28 */   private Color bgBlue = Color.valueOf("074254ff");
/*     */   private boolean playSfx = false;
/*  30 */   private long sfxId = -1L;
/*  31 */   private String sfxKey = null;
/*     */   
/*     */   private enum Phase {
/*  34 */     INIT, BOUNCE, FADE, WAIT, FADE_OUT;
/*     */   }
/*     */   
/*     */   public SplashScreen() {
/*  38 */     this.img = ImageMaster.loadImage("images/megaCrit.png");
/*     */   }
/*     */   
/*     */   public void update() {
/*  42 */     if ((InputHelper.justClickedLeft || CInputActionSet.select.isJustPressed()) && 
/*  43 */       this.phase != Phase.FADE_OUT) {
/*  44 */       this.phase = Phase.FADE_OUT;
/*  45 */       this.timer = 1.0F;
/*     */       
/*  47 */       if (this.sfxKey != null) {
/*  48 */         CardCrawlGame.sound.fadeOut(this.sfxKey, this.sfxId);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  53 */     switch (this.phase) {
/*     */       case INIT:
/*  55 */         this.timer -= Gdx.graphics.getDeltaTime();
/*  56 */         if (this.timer < 0.0F) {
/*  57 */           this.phase = Phase.BOUNCE;
/*  58 */           this.timer = 1.2F;
/*     */         } 
/*     */         break;
/*     */       case BOUNCE:
/*  62 */         this.timer -= Gdx.graphics.getDeltaTime();
/*  63 */         this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.timer / 1.2F);
/*  64 */         this.y = Interpolation.elasticIn.apply(Settings.HEIGHT / 2.0F, Settings.HEIGHT / 2.0F - 200.0F * Settings.scale, this.timer / 1.2F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  69 */         if (this.timer < 0.96000004F && !this.playSfx) {
/*  70 */           this.playSfx = true;
/*  71 */           this.sfxId = CardCrawlGame.sound.play("SPLASH");
/*     */         } 
/*     */         
/*  74 */         if (this.timer < 0.0F) {
/*  75 */           this.phase = Phase.FADE;
/*  76 */           this.timer = 3.0F;
/*     */         } 
/*     */         break;
/*     */       case FADE:
/*  80 */         this.timer -= Gdx.graphics.getDeltaTime();
/*  81 */         this.color.r = Interpolation.fade.apply(this.cream.r, 1.0F, this.timer / 3.0F);
/*  82 */         this.color.g = Interpolation.fade.apply(this.cream.g, 1.0F, this.timer / 3.0F);
/*  83 */         this.color.b = Interpolation.fade.apply(this.cream.b, 1.0F, this.timer / 3.0F);
/*  84 */         this.bgColor.r = Interpolation.fade.apply(this.bgBlue.r, 0.0F, this.timer / 3.0F);
/*  85 */         this.bgColor.g = Interpolation.fade.apply(this.bgBlue.g, 0.0F, this.timer / 3.0F);
/*  86 */         this.bgColor.b = Interpolation.fade.apply(this.bgBlue.b, 0.0F, this.timer / 3.0F);
/*  87 */         this.sX = Interpolation.exp5Out.apply(Settings.WIDTH / 2.0F + OFFSET_X, Settings.WIDTH / 2.0F, this.timer / 3.0F);
/*  88 */         this.sY = Interpolation.exp5Out.apply(Settings.HEIGHT / 2.0F - OFFSET_Y, Settings.HEIGHT / 2.0F, this.timer / 3.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         if (this.timer < 0.0F) {
/*  94 */           this.phase = Phase.WAIT;
/*  95 */           this.timer = 1.5F;
/*     */         } 
/*     */         break;
/*     */       case WAIT:
/*  99 */         this.timer -= Gdx.graphics.getDeltaTime();
/* 100 */         if (this.timer < 0.0F) {
/* 101 */           this.phase = Phase.FADE_OUT;
/* 102 */           this.timer = 1.0F;
/*     */         } 
/*     */         break;
/*     */       case FADE_OUT:
/* 106 */         this.bgColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.timer / 1.0F);
/* 107 */         this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.timer / 1.0F);
/* 108 */         this.timer -= Gdx.graphics.getDeltaTime();
/* 109 */         if (this.timer < 0.0F) {
/* 110 */           this.img.dispose();
/* 111 */           this.isDone = true;
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   public void render(SpriteBatch sb) {
/* 117 */     sb.setColor(this.bgColor);
/* 118 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 119 */     sb.setColor(this.shadowColor);
/* 120 */     sb.draw(this.img, this.sX - 256.0F, this.sY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
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
/* 138 */     Color s = new Color(0.0F, 0.0F, 0.0F, this.color.a / 5.0F);
/* 139 */     sb.setColor(s);
/* 140 */     sb.draw(this.img, this.x - 256.0F + 14.0F * Settings.scale, this.y - 256.0F - 14.0F * Settings.scale, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
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
/* 158 */     sb.setColor(this.color);
/* 159 */     sb.draw(this.img, this.x - 256.0F, this.y - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 512, 512, false, false);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\screens\splash\SplashScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */