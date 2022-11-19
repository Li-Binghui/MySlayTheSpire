/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class GiantFireEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float brightness;
/*    */   private float x;
/* 18 */   private boolean flipX = MathUtils.randomBoolean(); private float y; private float vX; private float vY; private float startingDuration;
/* 19 */   private float delayTimer = MathUtils.random(0.1F);
/*    */   
/*    */   public GiantFireEffect() {
/* 22 */     setImg();
/* 23 */     this.startingDuration = 1.5F;
/* 24 */     this.duration = this.startingDuration;
/* 25 */     this.x = MathUtils.random(0.0F, Settings.WIDTH) - this.img.packedWidth / 2.0F;
/* 26 */     this.y = MathUtils.random(-200.0F, -400.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 27 */     this.vX = MathUtils.random(-70.0F, 70.0F) * Settings.scale;
/* 28 */     this.vY = MathUtils.random(500.0F, 1700.0F) * Settings.scale;
/* 29 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 30 */     this.color.g -= MathUtils.random(0.5F);
/* 31 */     this.color.b -= this.color.g - MathUtils.random(0.0F, 0.2F);
/* 32 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/* 33 */     this.scale = MathUtils.random(0.5F, 7.0F);
/* 34 */     this.brightness = MathUtils.random(0.2F, 0.6F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     if (this.delayTimer > 0.0F) {
/* 39 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*    */       return;
/*    */     } 
/* 42 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 43 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 44 */     this.scale *= MathUtils.random(0.95F, 1.05F);
/*    */     
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 47 */     if (this.duration < 0.0F) {
/* 48 */       this.isDone = true;
/* 49 */     } else if (this.startingDuration - this.duration < 0.75F) {
/* 50 */       this.color.a = Interpolation.fade.apply(0.0F, this.brightness, (this.startingDuration - this.duration) / 0.75F);
/* 51 */     } else if (this.duration < 1.0F) {
/* 52 */       this.color.a = Interpolation.fade.apply(0.0F, this.brightness, this.duration / 1.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setImg() {
/* 57 */     int roll = MathUtils.random(2);
/* 58 */     if (roll == 0) {
/* 59 */       this.img = ImageMaster.FLAME_1;
/* 60 */     } else if (roll == 1) {
/* 61 */       this.img = ImageMaster.FLAME_2;
/*    */     } else {
/* 63 */       this.img = ImageMaster.FLAME_3;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 69 */     sb.setColor(this.color);
/* 70 */     sb.setBlendFunction(770, 1);
/* 71 */     if (this.flipX && !this.img.isFlipX()) {
/* 72 */       this.img.flip(true, false);
/* 73 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 74 */       this.img.flip(true, false);
/*    */     } 
/* 76 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);
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
/* 87 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GiantFireEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */