/*    */ package com.megacrit.cardcrawl.vfx.scene;
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
/*    */ public class SlowFireParticleEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public SlowFireParticleEffect() {
/* 19 */     setImg();
/* 20 */     this.renderBehind = true;
/* 21 */     this.startingDuration = 2.0F;
/* 22 */     this.duration = this.startingDuration;
/* 23 */     this.x = MathUtils.random(0.0F, Settings.WIDTH) - this.img.packedWidth / 2.0F;
/* 24 */     this.y = -this.img.packedHeight / 2.0F - 100.0F * Settings.scale;
/* 25 */     this.vX = MathUtils.random(-120.0F, 120.0F) * Settings.scale;
/* 26 */     this.vY2 = MathUtils.random(5.0F, 30.0F);
/* 27 */     this.vY2 *= this.vY2;
/* 28 */     this.vY2 *= Settings.scale;
/*    */     
/* 30 */     this.color = new Color(MathUtils.random(0.3F, 0.4F), MathUtils.random(0.3F, 0.7F), MathUtils.random(0.8F, 1.0F), 0.0F);
/*    */     
/* 32 */     if (this.vX > 0.0F) {
/* 33 */       this.rotation = MathUtils.random(0.0F, -15.0F);
/*    */     } else {
/* 35 */       this.rotation = MathUtils.random(0.0F, 15.0F);
/*    */     } 
/*    */     
/* 38 */     this.scale = MathUtils.random(0.3F, 3.0F) * Settings.scale;
/*    */   }
/*    */   private float vX; private float vY2; private float startingDuration;
/*    */   public void update() {
/* 42 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 43 */     this.y += this.vY2 * Gdx.graphics.getDeltaTime();
/*    */     
/* 45 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/*    */     } else {
/* 49 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 0.7F, this.duration);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void setImg() {
/* 54 */     if (MathUtils.randomBoolean()) {
/* 55 */       this.img = ImageMaster.GLOW_SPARK_2;
/*    */     } else {
/* 57 */       this.img = ImageMaster.GLOW_SPARK;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 63 */     sb.setColor(this.color);
/* 64 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 72 */         MathUtils.random(0.8F, 1.2F), this.scale * 
/* 73 */         MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */ 
/*    */     
/* 76 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 84 */         MathUtils.random(0.8F, 1.2F), this.scale * 
/* 85 */         MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\SlowFireParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */