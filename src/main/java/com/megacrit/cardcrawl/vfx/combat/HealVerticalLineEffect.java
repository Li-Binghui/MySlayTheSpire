/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class HealVerticalLineEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   private float vY;
/*     */   
/*     */   public HealVerticalLineEffect(float x, float y) {
/*  19 */     this.img = ImageMaster.STRIKE_LINE;
/*  20 */     this.img2 = ImageMaster.STRIKE_LINE_2;
/*  21 */     this.duration = MathUtils.random(0.6F, 1.3F);
/*  22 */     this.startingDuration = this.duration;
/*  23 */     this.x = x;
/*  24 */     this.y = y;
/*  25 */     this.staggerTimer = MathUtils.random(0.0F, 0.5F);
/*  26 */     float tmp = MathUtils.random(5.0F, 20.0F);
/*  27 */     this.vY = tmp * tmp * Settings.scale;
/*  28 */     this.rotation = 90.0F;
/*     */     
/*  30 */     if (MathUtils.randomBoolean()) {
/*  31 */       this.color = Color.CHARTREUSE.cpy();
/*     */     } else {
/*  33 */       this.color = new Color(1.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*  35 */     this.color.a = 0.0F;
/*  36 */     this.renderBehind = MathUtils.randomBoolean(0.3F);
/*     */   }
/*     */   private float staggerTimer; private TextureAtlas.AtlasRegion img; private TextureAtlas.AtlasRegion img2;
/*     */   public void update() {
/*  40 */     if (this.staggerTimer > 0.0F) {
/*  41 */       this.staggerTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/*     */       return;
/*     */     } 
/*  45 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*     */     
/*  47 */     this.scale = Settings.scale * this.duration / this.startingDuration;
/*  48 */     this.duration -= Gdx.graphics.getDeltaTime();
/*     */     
/*  50 */     if (this.duration / this.startingDuration > 0.5F) {
/*  51 */       this.color.a = 1.0F - this.duration / this.startingDuration;
/*  52 */       this.color.a += MathUtils.random(0.0F, 0.2F);
/*     */     } else {
/*  54 */       this.color.a = this.duration / this.startingDuration;
/*  55 */       this.color.a += MathUtils.random(0.0F, 0.2F);
/*     */     } 
/*  57 */     if (this.duration < 0.0F) {
/*  58 */       this.isDone = true;
/*  59 */       this.color.a = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  65 */     if (this.staggerTimer > 0.0F) {
/*     */       return;
/*     */     }
/*  68 */     sb.setBlendFunction(770, 1);
/*  69 */     sb.setColor(this.color);
/*     */     
/*  71 */     sb.draw((TextureRegion)this.img, this.x - this.img.packedWidth / 2.0F, this.y - this.img.packedHeight / 2.0F, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  79 */         MathUtils.random(0.7F, 2.0F), this.scale * 0.8F, this.rotation + 
/*     */         
/*  81 */         MathUtils.random(-2.0F, 2.0F));
/*  82 */     sb.setColor(new Color(1.0F, 1.0F, 0.7F, this.color.a));
/*  83 */     sb.draw((TextureRegion)this.img2, this.x - this.img2.packedWidth / 2.0F, this.y - this.img2.packedHeight / 2.0F, this.img2.packedWidth / 2.0F, this.img2.packedHeight / 2.0F, this.img2.packedWidth, this.img2.packedHeight, this.scale * 1.5F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  92 */         MathUtils.random(1.2F, 2.4F), this.rotation);
/*     */     
/*  94 */     sb.draw((TextureRegion)this.img2, this.x - this.img2.packedWidth / 2.0F, this.y - this.img2.packedHeight / 2.0F, this.img2.packedWidth / 2.0F, this.img2.packedHeight / 2.0F, this.img2.packedWidth, this.img2.packedHeight, this.scale * 1.5F, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 103 */         MathUtils.random(1.2F, 2.4F), this.rotation);
/*     */     
/* 105 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\HealVerticalLineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */