/*     */ package com.megacrit.cardcrawl.vfx.combat;
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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect;
/*     */ 
/*     */ public class FlickCoinEffect extends AbstractGameEffect {
/*     */   private static TextureAtlas.AtlasRegion img;
/*     */   private float sX;
/*     */   private float sY;
/*     */   private float cX;
/*     */   private float cY;
/*  22 */   private float sparkleTimer = 0.0F; private float dX; private float dY; private float yOffset; private float bounceHeight; private static final float DUR = 0.5F; private boolean playedSfx = false;
/*     */   
/*     */   public FlickCoinEffect(float srcX, float srcY, float destX, float destY) {
/*  25 */     if (img == null) {
/*  26 */       img = ImageMaster.vfxAtlas.findRegion("combat/empowerCircle1");
/*     */     }
/*     */     
/*  29 */     this.sX = srcX;
/*  30 */     this.sY = srcY;
/*  31 */     this.cX = this.sX;
/*  32 */     this.cY = this.sY;
/*  33 */     this.dX = destX;
/*  34 */     this.dY = destY - 100.0F * Settings.scale;
/*  35 */     this.rotation = 0.0F;
/*  36 */     this.duration = 0.5F;
/*     */     
/*  38 */     this.color = new Color(1.0F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  40 */     if (this.sY > this.dY) {
/*  41 */       this.bounceHeight = 600.0F * Settings.scale;
/*     */     } else {
/*  43 */       this.bounceHeight = this.dY - this.sY + 600.0F * Settings.scale;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  48 */     if (!this.playedSfx) {
/*  49 */       this.playedSfx = true;
/*  50 */       CardCrawlGame.sound.playA("ATTACK_WHIFF_2", MathUtils.random(0.7F, 0.8F));
/*     */     } 
/*     */     
/*  53 */     this.sparkleTimer -= Gdx.graphics.getDeltaTime();
/*  54 */     if (this.duration < 0.4F && this.sparkleTimer < 0.0F) {
/*  55 */       for (int i = 0; i < MathUtils.random(2, 5); i++) {
/*  56 */         AbstractDungeon.effectsQueue.add(new ShineSparkleEffect(this.cX, this.cY + this.yOffset));
/*     */       }
/*  58 */       this.sparkleTimer = MathUtils.random(0.05F, 0.1F);
/*     */     } 
/*     */     
/*  61 */     this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.5F);
/*  62 */     this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.5F);
/*     */     
/*  64 */     if (this.dX > this.sX) {
/*  65 */       this.rotation -= Gdx.graphics.getDeltaTime() * 1000.0F;
/*     */     } else {
/*  67 */       this.rotation += Gdx.graphics.getDeltaTime() * 1000.0F;
/*     */     } 
/*     */     
/*  70 */     if (this.duration > 0.25F) {
/*  71 */       this.color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 0.25F) / 0.2F) * Settings.scale;
/*  72 */       this.yOffset = Interpolation.circleIn.apply(this.bounceHeight, 0.0F, (this.duration - 0.25F) / 0.25F) * Settings.scale;
/*     */     } else {
/*  74 */       this.yOffset = Interpolation.circleOut.apply(0.0F, this.bounceHeight, this.duration / 0.25F) * Settings.scale;
/*     */     } 
/*     */     
/*  77 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  78 */     if (this.duration < 0.0F) {
/*  79 */       this.isDone = true;
/*  80 */       CardCrawlGame.sound.playA("GOLD_GAIN", MathUtils.random(0.0F, 0.1F));
/*  81 */       AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(this.dX, this.dY + 100.0F * Settings.scale, Color.GOLD
/*  82 */             .cpy()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  89 */     sb.setBlendFunction(770, 1);
/*  90 */     sb.setColor(new Color(0.4F, 1.0F, 1.0F, this.color.a / 5.0F));
/*     */     
/*  92 */     sb.setColor(this.color);
/*  93 */     sb.draw((TextureRegion)img, this.cX - (img.packedWidth / 2), this.cY - (img.packedHeight / 2) + this.yOffset, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 0.7F, this.scale * 0.4F, this.rotation);
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
/* 104 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlickCoinEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */