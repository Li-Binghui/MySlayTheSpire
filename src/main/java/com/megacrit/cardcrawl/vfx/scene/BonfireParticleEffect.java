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
/*     */ public class BonfireParticleEffect
/*     */   extends AbstractGameEffect {
/*     */   private float effectDuration;
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public BonfireParticleEffect(boolean isAbove, boolean isBlue) {
/*  21 */     this.img = getImg();
/*  22 */     this.x = 170.0F * Settings.scale + MathUtils.random(-25.0F, 25.0F) * Settings.scale;
/*  23 */     this.y = 44.0F * Settings.scale;
/*  24 */     this.effectDuration = MathUtils.random(1.0F, 2.0F);
/*  25 */     this.duration = this.effectDuration;
/*  26 */     this.startingDuration = this.effectDuration;
/*  27 */     this.vY = MathUtils.random(0.0F, 200.0F) * Settings.scale;
/*  28 */     this.vX = MathUtils.random(-30.0F, 30.0F) * Settings.scale;
/*     */     
/*  30 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/*  31 */     this.scale = MathUtils.random(0.8F, 2.5F);
/*     */     
/*  33 */     this.vY /= this.scale;
/*  34 */     this.vX /= this.scale * 2.0F;
/*  35 */     int roll = MathUtils.random(2);
/*     */     
/*  37 */     if (!isBlue) {
/*  38 */       if (roll == 0) {
/*  39 */         this.color = Color.ORANGE.cpy();
/*  40 */       } else if (roll == 1) {
/*  41 */         this.color = Color.GOLDENROD.cpy();
/*     */       } else {
/*  43 */         this.color = Color.CORAL.cpy();
/*     */       }
/*     */     
/*  46 */     } else if (roll == 0) {
/*  47 */       this.color = Color.CYAN.cpy();
/*  48 */     } else if (roll == 1) {
/*  49 */       this.color = Color.SKY.cpy();
/*     */     } else {
/*  51 */       this.color = Color.TEAL.cpy();
/*     */     } 
/*     */ 
/*     */     
/*  55 */     this.rotator = MathUtils.random(-10.0F, 10.0F);
/*     */   }
/*     */   private float vY; private float vX; private float rotator; private TextureAtlas.AtlasRegion img;
/*     */   private TextureAtlas.AtlasRegion getImg() {
/*  59 */     switch (MathUtils.random(0, 2)) {
/*     */       case 0:
/*  61 */         return ImageMaster.TORCH_FIRE_1;
/*     */       case 1:
/*  63 */         return ImageMaster.TORCH_FIRE_2;
/*     */     } 
/*  65 */     return ImageMaster.TORCH_FIRE_3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  70 */     this.rotation += this.rotator * Gdx.graphics.getDeltaTime();
/*  71 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*  72 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  73 */     this.vX *= 0.995F;
/*     */     
/*  75 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  77 */     if (this.duration > this.startingDuration / 2.0F) {
/*  78 */       this.color.a = Interpolation.exp10In.apply(0.4F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  83 */       this.color.a = Interpolation.pow2In.apply(0.0F, 0.4F, this.duration / this.startingDuration / 2.0F);
/*     */     } 
/*     */     
/*  86 */     if (this.duration < 0.0F) {
/*  87 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, float x2, float y2) {
/*  92 */     sb.setColor(this.color);
/*  93 */     sb.setBlendFunction(770, 1);
/*  94 */     sb.draw((TextureRegion)this.img, this.x + x2, this.y + y2, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);
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
/* 105 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */   
/*     */   public void render(SpriteBatch sb) {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\BonfireParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */