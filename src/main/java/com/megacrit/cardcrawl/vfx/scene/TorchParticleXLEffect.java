/*     */ package com.megacrit.cardcrawl.vfx.scene;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class TorchParticleXLEffect extends AbstractGameEffect {
/*     */   private float x;
/*  16 */   public static TextureAtlas.AtlasRegion[] imgs = new TextureAtlas.AtlasRegion[3]; private float y; private float vY;
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   public static boolean renderGreen = false;
/*     */   
/*     */   public TorchParticleXLEffect(float x, float y, float scaleMultiplier) {
/*  21 */     if (imgs[0] == null) {
/*     */       
/*  23 */       imgs[0] = ImageMaster.vfxAtlas.findRegion("env/fire1");
/*  24 */       imgs[1] = ImageMaster.vfxAtlas.findRegion("env/fire2");
/*  25 */       imgs[2] = ImageMaster.vfxAtlas.findRegion("env/fire3");
/*     */     } 
/*     */     
/*  28 */     this.duration = MathUtils.random(0.5F, 1.0F);
/*  29 */     this.startingDuration = this.duration;
/*  30 */     this.img = imgs[MathUtils.random(imgs.length - 1)];
/*  31 */     this.x = x - (this.img.packedWidth / 2) + MathUtils.random(-3.0F, 3.0F) * Settings.scale;
/*  32 */     this.y = y - (this.img.packedHeight / 2);
/*  33 */     this.scale = Settings.scale * MathUtils.random(1.0F, 3.0F) * scaleMultiplier;
/*  34 */     this.vY = MathUtils.random(1.0F, 10.0F);
/*  35 */     this.vY *= this.vY * Settings.scale;
/*  36 */     this.rotation = MathUtils.random(-20.0F, 20.0F);
/*     */     
/*  38 */     if (!renderGreen) {
/*  39 */       this
/*     */ 
/*     */         
/*  42 */         .color = new Color(MathUtils.random(0.6F, 1.0F), MathUtils.random(0.3F, 0.6F), MathUtils.random(0.0F, 0.3F), 0.01F);
/*     */     } else {
/*     */       
/*  45 */       this
/*     */ 
/*     */         
/*  48 */         .color = new Color(MathUtils.random(0.1F, 0.3F), MathUtils.random(0.5F, 0.9F), MathUtils.random(0.1F, 0.3F), 0.01F);
/*     */     } 
/*     */     
/*  51 */     this.renderBehind = false;
/*     */   }
/*     */   
/*     */   public TorchParticleXLEffect(float x, float y) {
/*  55 */     if (imgs[0] == null) {
/*     */       
/*  57 */       imgs[0] = ImageMaster.vfxAtlas.findRegion("env/fire1");
/*  58 */       imgs[1] = ImageMaster.vfxAtlas.findRegion("env/fire2");
/*  59 */       imgs[2] = ImageMaster.vfxAtlas.findRegion("env/fire3");
/*     */     } 
/*     */     
/*  62 */     this.duration = MathUtils.random(0.5F, 1.0F);
/*  63 */     this.startingDuration = this.duration;
/*  64 */     this.img = imgs[MathUtils.random(imgs.length - 1)];
/*  65 */     this.x = x - (this.img.packedWidth / 2) + MathUtils.random(-3.0F, 3.0F) * Settings.scale;
/*  66 */     this.y = y - (this.img.packedHeight / 2);
/*  67 */     this.scale = Settings.scale * MathUtils.random(1.0F, 3.0F);
/*  68 */     this.vY = MathUtils.random(1.0F, 10.0F);
/*  69 */     this.vY *= this.vY * Settings.scale;
/*  70 */     this.rotation = MathUtils.random(-20.0F, 20.0F);
/*     */     
/*  72 */     if (!renderGreen) {
/*  73 */       this
/*     */ 
/*     */         
/*  76 */         .color = new Color(MathUtils.random(0.6F, 1.0F), MathUtils.random(0.3F, 0.6F), MathUtils.random(0.0F, 0.3F), 0.01F);
/*     */     } else {
/*     */       
/*  79 */       this
/*     */ 
/*     */         
/*  82 */         .color = new Color(MathUtils.random(0.1F, 0.3F), MathUtils.random(0.5F, 0.9F), MathUtils.random(0.1F, 0.3F), 0.01F);
/*     */     } 
/*     */     
/*  85 */     this.renderBehind = false;
/*     */   }
/*     */   
/*     */   public void update() {
/*  89 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  90 */     if (this.duration < 0.0F) {
/*  91 */       this.isDone = true;
/*     */     }
/*  93 */     this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration);
/*  94 */     this.y += this.vY * Gdx.graphics.getDeltaTime() * 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  99 */     sb.setBlendFunction(770, 1);
/* 100 */     sb.setColor(this.color);
/* 101 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 112 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\TorchParticleXLEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */