/*     */ package com.megacrit.cardcrawl.vfx.campfire;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class CampfireBurningEffect
/*     */   extends AbstractGameEffect {
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   private float brightness;
/*     */   private float x;
/*     */   private float y;
/*  22 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY2; private float vY; private float startingDuration;
/*  23 */   private float delayTimer = MathUtils.random(0.1F);
/*     */   
/*     */   public CampfireBurningEffect() {
/*  26 */     setImg();
/*  27 */     this.startingDuration = 1.75F;
/*  28 */     this.duration = this.startingDuration;
/*  29 */     this.x = MathUtils.random(1200.0F, 1770.0F) * Settings.xScale - this.img.packedWidth / 2.0F;
/*  30 */     this.y = Settings.HEIGHT / 2.0F - 200.0F * Settings.yScale - this.img.packedHeight / 2.0F;
/*  31 */     this.vX = MathUtils.random(-70.0F, 70.0F) * Settings.xScale;
/*  32 */     this.vY = 0.0F;
/*  33 */     this.vY2 = MathUtils.random(250.0F, 450.0F) * Settings.scale;
/*  34 */     this.vY2 -= Math.abs(this.x - 1485.0F * Settings.scale) / 2.0F;
/*     */     
/*  36 */     if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/*  37 */       this.color = new Color(0.0F, 1.0F, 1.0F, 0.0F);
/*  38 */       this.color.g -= MathUtils.random(0.4F);
/*  39 */       this.color.b -= MathUtils.random(0.4F);
/*  40 */     } else if (CardCrawlGame.dungeon instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/*  41 */       this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  42 */       this.color.r -= MathUtils.random(0.5F);
/*  43 */       this.color.b -= this.color.r - MathUtils.random(0.0F, 0.2F);
/*     */     } else {
/*  45 */       this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*  46 */       this.color.g -= MathUtils.random(0.5F);
/*  47 */       this.color.b -= this.color.g - MathUtils.random(0.0F, 0.2F);
/*     */     } 
/*     */     
/*  50 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/*  51 */     this.scale = MathUtils.random(3.0F, 4.0F);
/*  52 */     this.brightness = MathUtils.random(0.2F, 0.6F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  56 */     if (this.delayTimer > 0.0F) {
/*  57 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*     */       return;
/*     */     } 
/*  60 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/*  61 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*  62 */     this.vY = MathHelper.slowColorLerpSnap(this.vY, this.vY2);
/*  63 */     this.scale *= 1.0F - Gdx.graphics.getDeltaTime() / 10.0F;
/*     */     
/*  65 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  66 */     if (this.duration < 0.0F) {
/*  67 */       this.isDone = true;
/*  68 */     } else if (this.startingDuration - this.duration < 0.75F) {
/*  69 */       this.color.a = Interpolation.fade.apply(0.0F, this.brightness, (this.startingDuration - this.duration) / 0.75F);
/*  70 */     } else if (this.duration < 1.0F) {
/*  71 */       this.color.a = Interpolation.fade.apply(0.0F, this.brightness, this.duration / 1.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setImg() {
/*  76 */     int roll = MathUtils.random(2);
/*  77 */     if (roll == 0) {
/*  78 */       this.img = ImageMaster.FLAME_1;
/*  79 */     } else if (roll == 1) {
/*  80 */       this.img = ImageMaster.FLAME_2;
/*     */     } else {
/*  82 */       this.img = ImageMaster.FLAME_3;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  88 */     sb.setBlendFunction(770, 1);
/*  89 */     sb.setColor(this.color);
/*  90 */     if (this.flipX && !this.img.isFlipX()) {
/*  91 */       this.img.flip(true, false);
/*  92 */     } else if (!this.flipX && this.img.isFlipX()) {
/*  93 */       this.img.flip(true, false);
/*     */     } 
/*  95 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);
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
/* 106 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireBurningEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */