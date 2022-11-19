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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
/*     */ 
/*     */ public class MindblastEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public MindblastEffect(float x, float y, boolean flipHorizontal) {
/*  23 */     if (img == null) {
/*  24 */       img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
/*     */     }
/*     */     
/*  27 */     this.flipHorizontal = flipHorizontal;
/*  28 */     this.x = x;
/*  29 */     this.y = y;
/*     */     
/*  31 */     this.color = Color.SKY.cpy();
/*  32 */     this.duration = 1.0F;
/*  33 */     this.startingDuration = 1.0F;
/*     */   }
/*     */   private static final float DUR = 1.0F; private static TextureAtlas.AtlasRegion img; private boolean playedSfx = false, flipHorizontal = false;
/*     */   
/*     */   public void update() {
/*  38 */     if (!this.playedSfx) {
/*  39 */       AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
/*  40 */       this.playedSfx = true;
/*  41 */       CardCrawlGame.sound.play("ATTACK_MAGIC_BEAM_SHORT");
/*  42 */       CardCrawlGame.screenShake.rumble(2.0F);
/*     */     } 
/*  44 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  46 */     if (this.duration > this.startingDuration / 2.0F) {
/*  47 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 0.5F);
/*     */     } else {
/*  49 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*     */     } 
/*     */     
/*  52 */     if (this.duration < 0.0F) {
/*  53 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  59 */     sb.setBlendFunction(770, 1);
/*  60 */     sb.setColor(new Color(0.5F, 0.7F, 1.0F, this.color.a));
/*     */     
/*  62 */     if (!this.flipHorizontal) {
/*  63 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  71 */           MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/*  72 */           MathUtils.random(-0.1F, 0.1F), 
/*  73 */           MathUtils.random(-4.0F, 4.0F));
/*  74 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  82 */           MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/*  83 */           MathUtils.random(-0.1F, 0.1F), 
/*  84 */           MathUtils.random(-4.0F, 4.0F));
/*  85 */       sb.setColor(this.color);
/*  86 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  96 */           MathUtils.random(-2.0F, 2.0F));
/*  97 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 107 */           MathUtils.random(-2.0F, 2.0F));
/*     */     } else {
/* 109 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 117 */           MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/* 118 */           MathUtils.random(-0.1F, 0.1F), 
/* 119 */           MathUtils.random(186.0F, 189.0F));
/* 120 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 128 */           MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/* 129 */           MathUtils.random(-0.1F, 0.1F), 
/* 130 */           MathUtils.random(186.0F, 189.0F));
/* 131 */       sb.setColor(this.color);
/* 132 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 142 */           MathUtils.random(187.0F, 188.0F));
/* 143 */       sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 153 */           MathUtils.random(187.0F, 188.0F));
/*     */     } 
/* 155 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\MindblastEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */