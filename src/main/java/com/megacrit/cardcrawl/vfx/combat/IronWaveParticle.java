/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.ScreenShake;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class IronWaveParticle
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 0.5F;
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public IronWaveParticle(float x, float y) {
/*  24 */     if (img == null) {
/*  25 */       img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
/*     */     }
/*     */ 
/*     */     
/*  29 */     this.scale = Settings.scale;
/*  30 */     this.x = x - img.packedWidth / 2.0F;
/*  31 */     this.y = Settings.HEIGHT - img.packedHeight / 2.0F;
/*  32 */     this.duration = 0.5F;
/*  33 */     this.targetY = y - 180.0F * Settings.scale;
/*     */     
/*  35 */     this.rotation = 0.0F;
/*  36 */     this.color = new Color(1.0F, 1.0F, 0.1F, 0.0F);
/*     */   }
/*     */   private float targetY; private static TextureAtlas.AtlasRegion img; private boolean impactHook = false;
/*     */   public void update() {
/*  40 */     this.y = Interpolation.fade.apply(Settings.HEIGHT, this.targetY, 1.0F - this.duration / 0.5F);
/*     */     
/*  42 */     this.scale += Gdx.graphics.getDeltaTime();
/*  43 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  45 */     if (this.duration < 0.0F) {
/*  46 */       this.isDone = true;
/*  47 */     } else if (this.duration < 0.2F) {
/*  48 */       if (!this.impactHook) {
/*  49 */         this.impactHook = true;
/*     */         
/*  51 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
/*     */       } 
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
/*  67 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration * 5.0F);
/*     */     } else {
/*  69 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration / 0.5F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  75 */     sb.setBlendFunction(770, 1);
/*  76 */     this.color.g = 1.0F;
/*  77 */     sb.setColor(this.color);
/*  78 */     sb.draw((TextureRegion)img, this.x, this.y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth / 2.0F, img.packedHeight * (this.duration + 0.2F) * 3.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  86 */         MathUtils.random(0.99F, 1.01F) * 0.5F, this.scale * 
/*  87 */         MathUtils.random(0.99F, 1.01F) * 2.0F * (this.duration + 0.8F), this.rotation);
/*     */     
/*  89 */     this.color.g = 0.7F;
/*  90 */     sb.setColor(this.color);
/*  91 */     sb.draw((TextureRegion)img, this.x - 50.0F * Settings.scale, this.y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth / 2.0F, img.packedHeight * (this.duration + 0.2F) * 2.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  99 */         MathUtils.random(0.99F, 1.01F) * 0.6F, this.scale * 
/* 100 */         MathUtils.random(0.99F, 1.01F) * 2.0F * (this.duration + 0.8F), this.rotation);
/*     */     
/* 102 */     this.color.g = 0.5F;
/* 103 */     sb.setColor(this.color);
/* 104 */     sb.draw((TextureRegion)img, this.x - 100.0F * Settings.scale, this.y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (this.duration + 0.2F) * 1.0F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         MathUtils.random(0.99F, 1.01F) * 0.75F, this.scale * 
/* 113 */         MathUtils.random(0.99F, 1.01F) * 2.0F * (this.duration + 0.8F), this.rotation);
/*     */     
/* 115 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IronWaveParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */