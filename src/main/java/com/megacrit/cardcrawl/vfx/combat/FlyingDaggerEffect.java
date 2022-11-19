/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class FlyingDaggerEffect
/*     */   extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   private float destY;
/*     */   
/*     */   public FlyingDaggerEffect(float x, float y, float fAngle, boolean shouldFlip) {
/*  23 */     this.img = ImageMaster.DAGGER_STREAK;
/*  24 */     this.x = x - this.img.packedWidth / 2.0F;
/*  25 */     this.destY = y;
/*  26 */     this.y = this.destY - this.img.packedHeight / 2.0F;
/*  27 */     this.startingDuration = 0.5F;
/*  28 */     this.duration = 0.5F;
/*  29 */     this.scaleMultiplier = MathUtils.random(1.2F, 1.5F);
/*  30 */     this.scale = 0.25F * Settings.scale;
/*     */     
/*  32 */     if (shouldFlip) {
/*  33 */       this.rotation = fAngle - 180.0F;
/*     */     } else {
/*  35 */       this.rotation = fAngle;
/*     */     } 
/*     */     
/*  38 */     this.color = Color.CHARTREUSE.cpy();
/*  39 */     this.color.a = 0.0F;
/*     */   }
/*     */   private float scaleMultiplier; private static final float DUR = 0.5F; private TextureAtlas.AtlasRegion img; private boolean playedSound = false;
/*     */   private void playRandomSfX() {
/*  43 */     int roll = MathUtils.random(5);
/*  44 */     switch (roll) {
/*     */       case 0:
/*  46 */         CardCrawlGame.sound.play("ATTACK_DAGGER_1");
/*     */         return;
/*     */       case 1:
/*  49 */         CardCrawlGame.sound.play("ATTACK_DAGGER_2");
/*     */         return;
/*     */       case 2:
/*  52 */         CardCrawlGame.sound.play("ATTACK_DAGGER_3");
/*     */         return;
/*     */       case 3:
/*  55 */         CardCrawlGame.sound.play("ATTACK_DAGGER_4");
/*     */         return;
/*     */       case 4:
/*  58 */         CardCrawlGame.sound.play("ATTACK_DAGGER_5");
/*     */         return;
/*     */     } 
/*  61 */     CardCrawlGame.sound.play("ATTACK_DAGGER_6");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  67 */     if (!this.playedSound) {
/*  68 */       playRandomSfX();
/*  69 */       this.playedSound = true;
/*     */     } 
/*     */     
/*  72 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */ 
/*     */ 
/*     */     
/*  76 */     Vector2 derp = new Vector2(MathUtils.cos(0.017453292F * this.rotation), MathUtils.sin(0.017453292F * this.rotation));
/*     */     
/*  78 */     this.x += derp.x * Gdx.graphics.getDeltaTime() * 3500.0F * this.scaleMultiplier * Settings.scale;
/*  79 */     this.y += derp.y * Gdx.graphics.getDeltaTime() * 3500.0F * this.scaleMultiplier * Settings.scale;
/*     */     
/*  81 */     if (this.duration < 0.0F) {
/*  82 */       this.isDone = true;
/*     */     }
/*  84 */     if (this.duration > 0.25F) {
/*  85 */       this.color.a = Interpolation.pow5In.apply(1.0F, 0.0F, (this.duration - 0.25F) * 4.0F);
/*     */     } else {
/*  87 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 4.0F);
/*     */     } 
/*     */     
/*  90 */     this.scale += Gdx.graphics.getDeltaTime() * this.scaleMultiplier;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  95 */     sb.setColor(this.color);
/*  96 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
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
/* 108 */     sb.setBlendFunction(770, 1);
/* 109 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
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
/* 120 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlyingDaggerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */