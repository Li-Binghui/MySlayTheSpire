/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*     */ 
/*     */ public class ViceCrushEffect extends AbstractGameEffect {
/*     */   private static TextureAtlas.AtlasRegion img;
/*     */   private boolean impactHook = false;
/*     */   private float x;
/*     */   private float x2;
/*     */   
/*     */   public ViceCrushEffect(float x, float y) {
/*  24 */     if (img == null) {
/*  25 */       img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
/*     */     }
/*     */     
/*  28 */     this.startX = x - 300.0F * Settings.scale - img.packedWidth / 2.0F;
/*  29 */     this.startX2 = x + 300.0F * Settings.scale - img.packedWidth / 2.0F;
/*  30 */     this.targetX = x - 120.0F * Settings.scale - img.packedWidth / 2.0F;
/*  31 */     this.targetX2 = x + 120.0F * Settings.scale - img.packedWidth / 2.0F;
/*  32 */     this.x = this.startX;
/*  33 */     this.x2 = this.startX2;
/*     */     
/*  35 */     this.y = y - img.packedHeight / 2.0F;
/*  36 */     this.scale = 1.1F;
/*  37 */     this.duration = 0.7F;
/*  38 */     this.startingDuration = 0.7F;
/*  39 */     this.rotation = 90.0F;
/*  40 */     this.color = Color.PURPLE.cpy();
/*  41 */     this.color.a = 0.0F;
/*     */   }
/*     */   private float y; private float startX; private float startX2; private float targetX; private float targetX2;
/*     */   public void update() {
/*  45 */     if (this.duration == this.startingDuration) {
/*  46 */       CardCrawlGame.sound.playA("ATTACK_MAGIC_FAST_3", -0.4F);
/*     */     }
/*     */     
/*  49 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  51 */     if (this.duration < 0.0F) {
/*  52 */       this.isDone = true;
/*  53 */     } else if (this.duration < 0.2F) {
/*  54 */       if (!this.impactHook) {
/*  55 */         this.impactHook = true;
/*  56 */         AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.PURPLE));
/*  57 */         CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
/*     */       } 
/*  59 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 5.0F);
/*     */     } else {
/*  61 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration / this.startingDuration);
/*     */     } 
/*     */     
/*  64 */     this.scale += 1.1F * Gdx.graphics.getDeltaTime();
/*  65 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.duration / this.startingDuration);
/*  66 */     this.x2 = Interpolation.fade.apply(this.targetX2, this.startX2, this.duration / this.startingDuration);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  71 */     sb.setBlendFunction(770, 1);
/*     */ 
/*     */     
/*  74 */     sb.setColor(new Color(0.5F, 0.5F, 0.9F, this.color.a));
/*  75 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale * 0.5F, this.scale * Settings.scale * (this.duration + 0.8F), this.rotation);
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
/*  87 */     sb.draw((TextureRegion)img, this.x2, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale * 0.5F, this.scale * Settings.scale * (this.duration + 0.8F), this.rotation - 180.0F);
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
/* 100 */     sb.setColor(new Color(0.7F, 0.5F, 0.9F, this.color.a));
/* 101 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale * 0.75F, this.scale * Settings.scale * (this.duration + 0.8F), this.rotation);
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
/* 112 */     sb.draw((TextureRegion)img, this.x2, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale * 0.75F, this.scale * Settings.scale * (this.duration + 0.8F), this.rotation - 180.0F);
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
/* 125 */     sb.setColor(this.color);
/* 126 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale, this.scale * Settings.scale * (this.duration + 1.0F), this.rotation);
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
/* 138 */     sb.draw((TextureRegion)img, this.x2, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * this.scale * Settings.scale, this.scale * Settings.scale * (this.duration + 1.0F), this.rotation - 180.0F);
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
/* 149 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ViceCrushEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */