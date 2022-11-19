/*     */ package com.megacrit.cardcrawl.vfx.campfire;
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
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class CampfireEndingBurningEffect extends AbstractGameEffect {
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   private float x;
/*     */   private float y;
/*  19 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY2; private float vY; private float startingDuration;
/*  20 */   private float delayTimer = MathUtils.random(0.1F);
/*     */   
/*     */   public CampfireEndingBurningEffect() {
/*  23 */     setImg();
/*  24 */     this.startingDuration = 1.0F;
/*  25 */     this.duration = this.startingDuration;
/*  26 */     this.x = MathUtils.random(0.0F, Settings.WIDTH) - this.img.packedWidth / 2.0F;
/*  27 */     this.y = -this.img.packedHeight / 2.0F - 100.0F * Settings.scale;
/*  28 */     this.vX = MathUtils.random(-120.0F, 120.0F) * Settings.scale;
/*  29 */     this.vY = 0.0F;
/*  30 */     this.vY2 = MathUtils.random(1500.0F, 3000.0F) * Settings.scale;
/*  31 */     this.vY2 -= Math.abs(this.x - 1485.0F * Settings.scale) / 2.0F;
/*     */     
/*  33 */     this.color = new Color(1.0F, MathUtils.random(0.5F, 0.9F), MathUtils.random(0.2F, 0.5F), 0.0F);
/*     */     
/*  35 */     if (this.vX > 0.0F) {
/*  36 */       this.rotation = MathUtils.random(0.0F, -15.0F);
/*     */     } else {
/*  38 */       this.rotation = MathUtils.random(0.0F, 15.0F);
/*     */     } 
/*     */     
/*  41 */     this.scale = MathUtils.random(1.5F, 4.0F) * Settings.scale;
/*     */   }
/*     */   
/*     */   public void update() {
/*  45 */     if (this.delayTimer > 0.0F) {
/*  46 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*     */       return;
/*     */     } 
/*  49 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  50 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*  51 */     this.vY = MathHelper.slowColorLerpSnap(this.vY, this.vY2);
/*  52 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  53 */     if (this.duration < 0.0F) {
/*  54 */       this.isDone = true;
/*     */     } else {
/*  56 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 0.8F, this.duration);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setImg() {
/*  61 */     int roll = MathUtils.random(2);
/*  62 */     if (roll == 0) {
/*  63 */       this.img = ImageMaster.GLOW_SPARK;
/*  64 */     } else if (roll == 1) {
/*  65 */       this.img = ImageMaster.GLOW_SPARK;
/*     */     } else {
/*  67 */       this.img = ImageMaster.GLOW_SPARK_2;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  73 */     sb.setBlendFunction(770, 1);
/*  74 */     sb.setColor(this.color);
/*  75 */     if (this.flipX && !this.img.isFlipX()) {
/*  76 */       this.img.flip(true, false);
/*  77 */     } else if (!this.flipX && this.img.isFlipX()) {
/*  78 */       this.img.flip(true, false);
/*     */     } 
/*     */     
/*  81 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/*  93 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         MathUtils.random(0.8F, 1.2F), this.scale * 
/* 102 */         MathUtils.random(0.8F, 1.2F), this.rotation);
/*     */     
/* 104 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireEndingBurningEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */