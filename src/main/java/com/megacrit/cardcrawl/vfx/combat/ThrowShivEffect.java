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
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class ThrowShivEffect
/*     */   extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   private float destY;
/*     */   
/*     */   public ThrowShivEffect(float x, float y) {
/*  22 */     this.img = ImageMaster.DAGGER_STREAK;
/*  23 */     this.x = x - MathUtils.random(320.0F, 360.0F) - this.img.packedWidth / 2.0F;
/*  24 */     this.destY = y;
/*  25 */     this.y = this.destY + MathUtils.random(-25.0F, 25.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/*  26 */     this.startingDuration = 0.4F;
/*  27 */     this.duration = 0.4F;
/*  28 */     this.scale = Settings.scale * MathUtils.random(0.5F, 2.0F);
/*  29 */     this.rotation = MathUtils.random(-30.0F, 30.0F);
/*     */     
/*  31 */     float darkness = MathUtils.random(0.5F, 0.8F);
/*  32 */     this.color = new Color(darkness, darkness, darkness, 1.0F);
/*     */   }
/*     */   private static final float DUR = 0.4F; private TextureAtlas.AtlasRegion img; private boolean playedSound = false;
/*     */   private void playRandomSfX() {
/*  36 */     int roll = MathUtils.random(5);
/*  37 */     switch (roll) {
/*     */       case 0:
/*  39 */         CardCrawlGame.sound.play("ATTACK_DAGGER_1");
/*     */         return;
/*     */       case 1:
/*  42 */         CardCrawlGame.sound.play("ATTACK_DAGGER_2");
/*     */         return;
/*     */       case 2:
/*  45 */         CardCrawlGame.sound.play("ATTACK_DAGGER_3");
/*     */         return;
/*     */       case 3:
/*  48 */         CardCrawlGame.sound.play("ATTACK_DAGGER_4");
/*     */         return;
/*     */       case 4:
/*  51 */         CardCrawlGame.sound.play("ATTACK_DAGGER_5");
/*     */         return;
/*     */     } 
/*  54 */     CardCrawlGame.sound.play("ATTACK_DAGGER_6");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  60 */     if (!this.playedSound) {
/*  61 */       playRandomSfX();
/*  62 */       this.playedSound = true;
/*     */     } 
/*  64 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  65 */     if (this.duration < 0.0F) {
/*  66 */       this.isDone = true;
/*     */     }
/*  68 */     if (this.duration > 0.2F) {
/*  69 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.2F) * 5.0F);
/*     */     } else {
/*  71 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
/*     */     } 
/*  73 */     this.scale = Interpolation.bounceIn.apply(Settings.scale * 0.5F, Settings.scale * 1.5F, this.duration / 0.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  78 */     sb.setColor(this.color);
/*  79 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 1.5F, this.rotation);
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
/*  91 */     sb.setBlendFunction(770, 1);
/*  92 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth * 0.85F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.75F, this.scale * 0.75F, this.rotation);
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
/* 103 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ThrowShivEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */