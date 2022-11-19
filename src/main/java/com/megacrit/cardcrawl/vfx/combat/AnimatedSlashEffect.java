/*     */ package com.megacrit.cardcrawl.vfx.combat;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class AnimatedSlashEffect
/*     */   extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   
/*     */   public AnimatedSlashEffect(float x, float y, float dX, float dY, float angle, Color color1, Color color2) {
/*  18 */     this(x, y, dX, dY, angle, 2.0F, color1, color2);
/*     */   }
/*     */   
/*     */   private float sX;
/*     */   private float sY;
/*     */   private float tX;
/*     */   private float tY;
/*     */   private float scaleX;
/*     */   private float scaleY;
/*     */   private float targetScale;
/*     */   private Color color2;
/*     */   
/*     */   public AnimatedSlashEffect(float x, float y, float dX, float dY, float angle, float targetScale, Color color1, Color color2) {
/*  31 */     this.x = x - 64.0F - dX / 2.0F * Settings.scale;
/*  32 */     this.y = y - 64.0F - dY / 2.0F * Settings.scale;
/*  33 */     this.sX = this.x;
/*  34 */     this.sY = this.y;
/*  35 */     this.tX = this.x + dX / 2.0F * Settings.scale;
/*  36 */     this.tY = this.y + dY / 2.0F * Settings.scale;
/*     */     
/*  38 */     this.color = color1.cpy();
/*  39 */     this.color2 = color2.cpy();
/*  40 */     this.color.a = 0.0F;
/*  41 */     this.startingDuration = 0.4F;
/*  42 */     this.duration = this.startingDuration;
/*  43 */     this.targetScale = targetScale;
/*  44 */     this.scaleX = 0.01F;
/*  45 */     this.scaleY = 0.01F;
/*     */     
/*  47 */     this.rotation = -135.0F;
/*  48 */     this.rotation = angle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  53 */     if (this.duration > this.startingDuration / 2.0F) {
/*  54 */       this.color.a = Interpolation.exp10In.apply(0.8F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  59 */       this.scaleX = Interpolation.exp10In.apply(this.targetScale, 0.1F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*     */ 
/*     */ 
/*     */       
/*  63 */       this.scaleY = this.scaleX;
/*  64 */       this.x = Interpolation.fade.apply(this.tX, this.sX, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*  65 */       this.y = Interpolation.fade.apply(this.tY, this.sY, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*     */     } else {
/*  67 */       this.scaleX = Interpolation.pow2In.apply(0.5F, this.targetScale, this.duration / this.startingDuration / 2.0F);
/*  68 */       this.color.a = Interpolation.pow5In.apply(0.0F, 0.8F, this.duration / this.startingDuration / 2.0F);
/*     */     } 
/*     */     
/*  71 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  72 */     if (this.duration < 0.0F) {
/*  73 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  79 */     sb.setColor(this.color2);
/*  80 */     sb.setBlendFunction(770, 1);
/*  81 */     sb.draw(ImageMaster.ANIMATED_SLASH_VFX, this.x, this.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scaleX * 0.4F * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  89 */         MathUtils.random(0.95F, 1.05F) * Settings.scale, this.scaleY * 0.7F * 
/*  90 */         MathUtils.random(0.95F, 1.05F) * Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     sb.setColor(this.color);
/* 100 */     sb.draw(ImageMaster.ANIMATED_SLASH_VFX, this.x, this.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scaleX * 0.7F * 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 108 */         MathUtils.random(0.95F, 1.05F) * Settings.scale, this.scaleY * 
/* 109 */         MathUtils.random(0.95F, 1.05F) * Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\AnimatedSlashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */