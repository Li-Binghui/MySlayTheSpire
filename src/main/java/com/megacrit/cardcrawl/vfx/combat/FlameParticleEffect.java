/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class FlameParticleEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 18 */   private boolean flipX = MathUtils.randomBoolean(); private float vY; private float vY2; private float vS; private float startingDuration;
/* 19 */   private float delayTimer = MathUtils.random(0.15F);
/*    */   
/*    */   public FlameParticleEffect(float x, float y) {
/* 22 */     setImg();
/* 23 */     this.startingDuration = MathUtils.random(0.6F, 1.5F);
/* 24 */     this.duration = this.startingDuration;
/* 25 */     float r = MathUtils.random(-13.0F, 13.0F) * MathUtils.random(-13.0F, 13.0F);
/* 26 */     this.x = x + r * Settings.scale - this.img.packedWidth / 2.0F;
/* 27 */     this.y = y + MathUtils.random(-180.0F, 0.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 28 */     this.vX = MathUtils.random(-25.0F, 25.0F) * Settings.scale;
/* 29 */     r = MathUtils.random(3.0F, 30.0F);
/* 30 */     this.vY = r * r / this.startingDuration * Settings.scale;
/* 31 */     this.vY2 = MathUtils.random(-100.0F, 100.0F) * Settings.scale;
/* 32 */     this.vS = MathUtils.random(-0.5F, 0.5F) * Settings.scale;
/* 33 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 34 */     this.color.g -= MathUtils.random(0.5F);
/* 35 */     this.color.b -= this.color.g - MathUtils.random(0.5F);
/* 36 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/* 37 */     this.scale = Settings.scale * MathUtils.random(0.2F, 1.5F);
/* 38 */     this.renderBehind = MathUtils.randomBoolean(0.5F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 42 */     if (this.delayTimer > 0.0F) {
/* 43 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*    */       return;
/*    */     } 
/* 46 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 47 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 48 */     this.vY += this.vY2 * Gdx.graphics.getDeltaTime();
/* 49 */     this.vY *= 59.0F * Gdx.graphics.getDeltaTime();
/* 50 */     this.scale += this.vS * Gdx.graphics.getDeltaTime();
/*    */     
/* 52 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/* 55 */     } else if (this.duration > this.startingDuration / 2.0F) {
/* 56 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, (this.startingDuration - this.duration) / this.startingDuration / 2.0F);
/* 57 */     } else if (this.duration < this.startingDuration / 2.0F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration / this.startingDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setImg() {
/* 63 */     int roll = MathUtils.random(2);
/* 64 */     if (roll == 0) {
/* 65 */       this.img = ImageMaster.FLAME_1;
/* 66 */     } else if (roll == 1) {
/* 67 */       this.img = ImageMaster.FLAME_2;
/*    */     } else {
/* 69 */       this.img = ImageMaster.FLAME_3;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 75 */     sb.setColor(this.color);
/* 76 */     sb.setBlendFunction(770, 1);
/* 77 */     if (this.flipX && !this.img.isFlipX()) {
/* 78 */       this.img.flip(true, false);
/* 79 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 80 */       this.img.flip(true, false);
/*    */     } 
/* 82 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 93 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FlameParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */