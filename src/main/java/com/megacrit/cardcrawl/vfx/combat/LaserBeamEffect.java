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
/*     */ public class LaserBeamEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public LaserBeamEffect(float x, float y) {
/*  23 */     if (img == null) {
/*  24 */       img = ImageMaster.vfxAtlas.findRegion("combat/laserThick");
/*     */     }
/*     */     
/*  27 */     this.x = x;
/*  28 */     this.y = y;
/*     */     
/*  30 */     this.color = Color.CYAN.cpy();
/*  31 */     this.duration = 2.0F;
/*  32 */     this.startingDuration = 2.0F;
/*     */   }
/*     */   private static final float DUR = 2.0F; private static TextureAtlas.AtlasRegion img; private boolean playedSfx = false;
/*     */   
/*     */   public void update() {
/*  37 */     if (!this.playedSfx) {
/*  38 */       AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.SKY));
/*  39 */       this.playedSfx = true;
/*  40 */       CardCrawlGame.sound.play("ATTACK_MAGIC_BEAM");
/*  41 */       CardCrawlGame.screenShake.rumble(2.0F);
/*     */     } 
/*  43 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  45 */     if (this.duration > this.startingDuration / 2.0F) {
/*  46 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, this.duration - 1.0F);
/*     */     } else {
/*  48 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*     */     } 
/*     */     
/*  51 */     if (this.duration < 0.0F) {
/*  52 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  58 */     sb.setBlendFunction(770, 1);
/*  59 */     sb.setColor(new Color(0.5F, 0.7F, 1.0F, this.color.a));
/*  60 */     sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  68 */         MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/*  69 */         MathUtils.random(-0.1F, 0.1F), 
/*  70 */         MathUtils.random(186.0F, 189.0F));
/*  71 */     sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F + 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  79 */         MathUtils.random(-0.05F, 0.05F), this.scale * 1.5F + 
/*  80 */         MathUtils.random(-0.1F, 0.1F), 
/*  81 */         MathUtils.random(186.0F, 189.0F));
/*  82 */     sb.setColor(this.color);
/*  83 */     sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         MathUtils.random(187.0F, 188.0F));
/*  94 */     sb.draw((TextureRegion)img, this.x, this.y - (img.packedHeight / 2), 0.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale * 2.0F, this.scale / 2.0F, 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 104 */         MathUtils.random(187.0F, 188.0F));
/* 105 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LaserBeamEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */