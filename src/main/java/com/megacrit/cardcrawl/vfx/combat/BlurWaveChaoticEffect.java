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
/*    */ public class BlurWaveChaoticEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float speed;
/*    */   
/*    */   public BlurWaveChaoticEffect(float x, float y, Color color, float chosenSpeed) {
/* 23 */     this.img = ImageMaster.BLUR_WAVE;
/* 24 */     this.stallTimer = MathUtils.random(0.0F, 0.3F);
/* 25 */     this.rotation = MathUtils.random(360.0F);
/* 26 */     this.scale = MathUtils.random(0.5F, 0.9F);
/* 27 */     this.x = x - this.img.packedWidth / 2.0F;
/* 28 */     this.y = y - this.img.packedHeight / 2.0F;
/* 29 */     this.duration = 2.0F;
/* 30 */     this.color = color;
/* 31 */     this.renderBehind = MathUtils.randomBoolean();
/*    */     
/* 33 */     this.speedStart = chosenSpeed;
/* 34 */     this.speedTarget = 2000.0F * Settings.scale;
/* 35 */     this.speed = this.speedStart;
/*    */     
/* 37 */     this.flipper = 270.0F;
/* 38 */     color.a = 0.0F;
/*    */   }
/*    */   private float speedStart; private float speedTarget; private float stallTimer; private TextureAtlas.AtlasRegion img; private float flipper;
/*    */   public void update() {
/* 42 */     this.stallTimer -= Gdx.graphics.getDeltaTime();
/* 43 */     if (this.stallTimer < 0.0F) {
/* 44 */       Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 45 */       tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 46 */       tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 47 */       this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / 2.0F);
/* 48 */       this.x += tmp.x;
/* 49 */       this.y += tmp.y;
/*    */       
/* 51 */       this.scale *= 1.0F + Gdx.graphics.getDeltaTime() * 2.0F;
/* 52 */       this.duration -= Gdx.graphics.getDeltaTime();
/* 53 */       if (this.duration < 0.0F) {
/* 54 */         this.isDone = true;
/* 55 */       } else if (this.duration > 1.5F) {
/* 56 */         this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (2.0F - this.duration) * 2.0F);
/* 57 */       } else if (this.duration < 0.5F) {
/* 58 */         this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 75 */         MathUtils.random(-0.1F, 0.1F), this.scale + 
/* 76 */         MathUtils.random(-0.1F, 0.1F), this.rotation + this.flipper + 
/* 77 */         MathUtils.random(-30.0F, 30.0F));
/* 78 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BlurWaveChaoticEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */