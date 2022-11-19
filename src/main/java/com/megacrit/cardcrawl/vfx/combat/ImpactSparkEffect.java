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
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ImpactSparkEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private static final float DUR = 1.0F;
/*    */   private float x;
/* 19 */   private static final float GRAVITY = 20.0F * Settings.scale;
/*    */   private float y;
/*    */   private float vX;
/*    */   private float vY;
/*    */   private float floor;
/*    */   
/*    */   public ImpactSparkEffect(float x, float y) {
/* 26 */     this.img = ImageMaster.GLOW_SPARK_2;
/*    */     
/* 28 */     this.duration = MathUtils.random(0.5F, 1.0F);
/* 29 */     this.x = x - (this.img.packedWidth / 2);
/* 30 */     this.y = y - (this.img.packedHeight / 2);
/* 31 */     this.color = Color.WHITE.cpy();
/* 32 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 33 */     this.scale = MathUtils.random(0.2F, 1.0F) * Settings.scale;
/* 34 */     this.vX = MathUtils.random(-1500.0F, 1500.0F) * Settings.scale;
/* 35 */     this.vY = MathUtils.random(-0.0F, 1000.0F) * Settings.scale;
/* 36 */     this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 40 */     this.vY -= GRAVITY / this.scale;
/* 41 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 42 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 43 */     Vector2 test = new Vector2(this.vX, this.vY);
/* 44 */     this.rotation = test.angle();
/*    */     
/* 46 */     if (this.y < this.floor) {
/* 47 */       this.vY = -this.vY * 0.75F;
/* 48 */       this.y = this.floor + 0.1F * Settings.scale;
/* 49 */       this.vX *= 1.1F;
/*    */     } 
/*    */     
/* 52 */     if (1.0F - this.duration < 0.1F) {
/* 53 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 55 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 0.5F, this.duration);
/*    */     } 
/*    */     
/* 58 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 59 */     if (this.duration < 0.0F) {
/* 60 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 66 */     sb.setBlendFunction(770, 1);
/* 67 */     sb.setColor(this.color);
/* 68 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 76 */         MathUtils.random(0.7F, 1.3F), this.scale * 
/* 77 */         MathUtils.random(0.7F, 1.3F), this.rotation);
/*    */     
/* 79 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 87 */         MathUtils.random(0.7F, 1.3F), this.scale * 
/* 88 */         MathUtils.random(0.7F, 1.3F), this.rotation);
/*    */     
/* 90 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ImpactSparkEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */