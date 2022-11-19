/*     */ package com.megacrit.cardcrawl.vfx;
/*     */ 
/*     */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AwakenedWingParticle
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   private float tScale;
/*  26 */   private TextureAtlas.AtlasRegion img = ImageMaster.THICK_3D_LINE_2;
/*     */ 
/*     */   
/*     */   public AwakenedWingParticle() {
/*  30 */     if (this.renderBehind) {
/*  31 */       this.tScale = MathUtils.random(0.8F, 1.2F);
/*     */     }
/*  33 */     this.color = new Color(0.3F, 0.3F, MathUtils.random(0.3F, 0.35F), MathUtils.random(0.5F, 0.9F));
/*     */     
/*  35 */     int roll = MathUtils.random(0, 2);
/*     */     
/*  37 */     if (roll == 0) {
/*     */       
/*  39 */       this.x = MathUtils.random(-340.0F, -170.0F) * Settings.scale;
/*  40 */       this.y = MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/*  41 */       this.tScale = MathUtils.random(0.4F, 0.5F);
/*  42 */     } else if (roll == 1) {
/*     */       
/*  44 */       this.x = MathUtils.random(-220.0F, -20.0F) * Settings.scale;
/*  45 */       this.y = MathUtils.random(-40.0F, -10.0F) * Settings.scale;
/*  46 */       this.tScale = MathUtils.random(0.4F, 0.5F);
/*     */     } else {
/*     */       
/*  49 */       this.x = MathUtils.random(-270.0F, -60.0F) * Settings.scale;
/*  50 */       this.y = MathUtils.random(-30.0F, -0.0F) * Settings.scale;
/*  51 */       this.tScale = MathUtils.random(0.4F, 0.7F);
/*     */     } 
/*     */     
/*  54 */     this.x += 155.0F * Settings.scale;
/*  55 */     this.y += 30.0F * Settings.scale;
/*  56 */     this.x -= (this.img.packedWidth / 2);
/*  57 */     this.y -= (this.img.packedHeight / 2);
/*     */   }
/*     */   
/*     */   public void update() {
/*  61 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  62 */     if (this.duration < 0.0F) {
/*  63 */       this.isDone = true;
/*     */     }
/*  65 */     if (this.duration > 1.0F) {
/*  66 */       this.scale = Interpolation.bounceIn.apply(this.tScale, 0.01F, this.duration - 1.0F) * Settings.scale;
/*     */     }
/*  68 */     if (this.duration < 0.2F) {
/*  69 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration * 5.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb, float x, float y) {
/*  80 */     float derp = MathUtils.random(3.0F, 5.0F);
/*     */     
/*  82 */     sb.setColor(new Color(0.4F, 1.0F, 1.0F, this.color.a / 2.0F));
/*  83 */     sb.setBlendFunction(770, 1);
/*  84 */     sb.draw((TextureRegion)this.img, this.x + x, this.y + y, this.img.packedWidth * 0.08F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  92 */         MathUtils.random(1.1F, 1.25F), this.scale, this.rotation + derp);
/*     */ 
/*     */     
/*  95 */     sb.setBlendFunction(770, 771);
/*     */     
/*  97 */     sb.setColor(this.color);
/*  98 */     sb.draw((TextureRegion)this.img, this.x + x, this.y + y, this.img.packedWidth * 0.08F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation + derp);
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
/* 110 */     sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 5.0F));
/* 111 */     sb.draw((TextureRegion)this.img, this.x + x, this.y + y, this.img.packedWidth * 0.08F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.7F, this.scale * 0.7F, this.rotation + derp - 40.0F);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\AwakenedWingParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */