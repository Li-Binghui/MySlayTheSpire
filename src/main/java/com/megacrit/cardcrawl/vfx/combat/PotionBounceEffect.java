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

import java.util.ArrayList;
/*     */ 
/*     */ public class PotionBounceEffect extends AbstractGameEffect {
/*     */   private static TextureAtlas.AtlasRegion img;
/*     */   private float sX;
/*     */   private float sY;
/*     */   private float cX;
/*     */   private float cY;
/*  23 */   private ArrayList<Vector2> previousPos = new ArrayList<>(); private float dX; private float dY; private float yOffset; private float bounceHeight; private static final float DUR = 0.6F; private boolean playedSfx = false;
/*     */   
/*     */   public PotionBounceEffect(float srcX, float srcY, float destX, float destY) {
/*  26 */     if (img == null) {
/*  27 */       img = ImageMaster.vfxAtlas.findRegion("combat/flask");
/*     */     }
/*     */     
/*  30 */     this.sX = srcX;
/*  31 */     this.sY = srcY;
/*  32 */     this.cX = this.sX;
/*  33 */     this.cY = this.sY;
/*  34 */     this.dX = destX;
/*  35 */     this.dY = destY;
/*  36 */     this.rotation = 0.0F;
/*  37 */     this.duration = 0.6F;
/*     */     
/*  39 */     this.color = new Color(0.4F, 1.0F, 0.0F, 0.0F);
/*     */     
/*  41 */     if (this.sY > this.dY) {
/*  42 */       this.bounceHeight = 400.0F * Settings.scale;
/*     */     } else {
/*  44 */       this.bounceHeight = this.dY - this.sY + 400.0F * Settings.scale;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  51 */     if (!this.playedSfx) {
/*  52 */       this.playedSfx = true;
/*  53 */       if (MathUtils.randomBoolean()) {
/*  54 */         CardCrawlGame.sound.playA("POTION_DROP_1", MathUtils.random(-0.3F, -0.2F));
/*     */       } else {
/*  56 */         CardCrawlGame.sound.playA("POTION_DROP_2", MathUtils.random(-0.3F, -0.2F));
/*     */       } 
/*  58 */       if (MathUtils.randomBoolean()) {
/*  59 */         CardCrawlGame.sound.play("POTION_1");
/*     */       } else {
/*  61 */         CardCrawlGame.sound.play("POTION_2");
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.6F);
/*  66 */     this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.6F);
/*     */     
/*  68 */     this.previousPos.add(new Vector2(this.cX + 
/*     */           
/*  70 */           MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.cY + this.yOffset + 
/*  71 */           MathUtils.random(-30.0F, 30.0F) * Settings.scale));
/*  72 */     if (this.previousPos.size() > 20) {
/*  73 */       this.previousPos.remove(this.previousPos.get(0));
/*     */     }
/*     */     
/*  76 */     if (this.dX > this.sX) {
/*  77 */       this.rotation -= Gdx.graphics.getDeltaTime() * 1000.0F;
/*     */     } else {
/*  79 */       this.rotation += Gdx.graphics.getDeltaTime() * 1000.0F;
/*     */     } 
/*     */     
/*  82 */     if (this.duration > 0.3F) {
/*  83 */       this.color.a = Interpolation.exp5In.apply(1.0F, 0.0F, (this.duration - 0.3F) / 0.3F) * Settings.scale;
/*  84 */       this.yOffset = Interpolation.circleIn.apply(this.bounceHeight, 0.0F, (this.duration - 0.3F) / 0.3F) * Settings.scale;
/*     */     } else {
/*  86 */       this.yOffset = Interpolation.circleOut.apply(0.0F, this.bounceHeight, this.duration / 0.3F) * Settings.scale;
/*     */     } 
/*     */     
/*  89 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  90 */     if (this.duration < 0.0F) {
/*  91 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  98 */     sb.setBlendFunction(770, 1);
/*  99 */     sb.setColor(new Color(0.4F, 1.0F, 1.0F, this.color.a / 3.0F));
/*     */     
/* 101 */     for (int i = 5; i < this.previousPos.size(); i++) {
/* 102 */       sb.draw((TextureRegion)ImageMaster.POWER_UP_2, ((Vector2)this.previousPos
/*     */           
/* 104 */           .get(i)).x - (img.packedWidth / 2), ((Vector2)this.previousPos
/* 105 */           .get(i)).y - (img.packedHeight / 2), img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale / 40.0F / i, this.scale / 40.0F / i, this.rotation);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     sb.setColor(this.color);
/* 116 */     sb.draw((TextureRegion)img, this.cX - (img.packedWidth / 2), this.cY - (img.packedHeight / 2) + this.yOffset, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 127 */     sb.draw((TextureRegion)img, this.cX - (img.packedWidth / 2), this.cY - (img.packedHeight / 2) + this.yOffset, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 138 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\PotionBounceEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */