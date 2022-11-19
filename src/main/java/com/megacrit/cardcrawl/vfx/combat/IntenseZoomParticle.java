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
/*    */ public class IntenseZoomParticle
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/* 19 */   private float flickerDuration = 0.0F; private TextureAtlas.AtlasRegion img; private Color color; private float offsetX; private float lengthX;
/*    */   private float lengthY;
/*    */   private boolean isBlack = false;
/*    */   
/*    */   public IntenseZoomParticle(float x, float y, boolean isBlack) {
/* 24 */     int i = MathUtils.random(2);
/* 25 */     if (i == 0) {
/* 26 */       this.img = ImageMaster.CONE_2;
/* 27 */     } else if (i == 1) {
/* 28 */       this.img = ImageMaster.CONE_4;
/*    */     } else {
/* 30 */       this.img = ImageMaster.CONE_5;
/*    */     } 
/*    */     
/* 33 */     this.duration = 1.5F;
/*    */     
/* 35 */     this.isBlack = isBlack;
/* 36 */     if (isBlack) {
/* 37 */       this.color = Color.BLACK.cpy();
/*    */     } else {
/* 39 */       this.color = Settings.GOLD_COLOR.cpy();
/*    */     } 
/*    */     
/* 42 */     this.x = x;
/* 43 */     this.y = y - this.img.packedHeight / 2.0F;
/* 44 */     randomize();
/*    */   }
/*    */   
/*    */   public void update() {
/* 48 */     this.flickerDuration -= Gdx.graphics.getDeltaTime();
/* 49 */     if (this.flickerDuration < 0.0F) {
/* 50 */       randomize();
/* 51 */       this.flickerDuration = MathUtils.random(0.0F, 0.05F);
/*    */     } 
/*    */     
/* 54 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 55 */     if (this.duration < 0.0F) {
/* 56 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void randomize() {
/* 61 */     this.rotation = MathUtils.random(360.0F);
/* 62 */     this.offsetX = MathUtils.random(200.0F, 600.0F) * Settings.scale * (2.0F - this.duration);
/* 63 */     this.lengthX = MathUtils.random(1.0F, 1.3F);
/* 64 */     this.lengthY = MathUtils.random(0.9F, 1.2F);
/* 65 */     if (this.isBlack) {
/* 66 */       this.color.a = MathUtils.random(0.5F, 1.0F) * Interpolation.pow2Out.apply(this.duration / 1.5F);
/*    */     } else {
/* 68 */       this.color.a = MathUtils.random(0.2F, 0.7F) * Interpolation.pow2Out.apply(this.duration / 1.5F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 74 */     if (!this.isBlack) {
/* 75 */       sb.setBlendFunction(770, 1);
/*    */     }
/*    */     
/* 78 */     sb.setColor(this.color);
/* 79 */     sb.draw((TextureRegion)this.img, this.x + this.offsetX, this.y, -this.offsetX, this.img.packedHeight / 2.0F, this.img.packedWidth * this.lengthX, this.img.packedHeight * this.lengthY, this.scale, this.scale, this.rotation);
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
/*    */     
/* 91 */     if (!this.isBlack)
/* 92 */       sb.setBlendFunction(770, 771); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IntenseZoomParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */