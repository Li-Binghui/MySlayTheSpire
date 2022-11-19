/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class BlurWaveNormalEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float speed;
/*    */   
/*    */   public BlurWaveNormalEffect(float x, float y, Color color, float chosenSpeed) {
/* 22 */     this.img = ImageMaster.BLUR_WAVE;
/* 23 */     this.stallTimer = MathUtils.random(0.0F, 0.3F);
/* 24 */     this.rotation = MathUtils.random(360.0F);
/* 25 */     this.scale = MathUtils.random(0.5F, 0.9F);
/* 26 */     this.x = x - this.img.packedWidth / 2.0F;
/* 27 */     this.y = y - this.img.packedHeight / 2.0F;
/* 28 */     this.duration = 2.0F;
/* 29 */     this.color = color;
/* 30 */     this.renderBehind = MathUtils.randomBoolean();
/*    */     
/* 32 */     this.speedStart = chosenSpeed;
/* 33 */     this.speedTarget = 2000.0F * Settings.scale;
/* 34 */     this.speed = this.speedStart;
/*    */     
/* 36 */     this.flipper = 270.0F;
/* 37 */     color.g -= MathUtils.random(0.1F);
/* 38 */     color.b -= MathUtils.random(0.2F);
/* 39 */     color.a = 0.0F;
/*    */   }
/*    */   private float speedStart; private float speedTarget; private float stallTimer; private TextureAtlas.AtlasRegion img; private float flipper;
/*    */   public void update() {
/* 43 */     this.stallTimer -= Gdx.graphics.getDeltaTime();
/* 44 */     if (this.stallTimer < 0.0F) {
/* 45 */       Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 46 */       tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 47 */       tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 48 */       this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / 2.0F);
/* 49 */       this.x += tmp.x;
/* 50 */       this.y += tmp.y;
/*    */       
/* 52 */       this.scale *= 1.0F + Gdx.graphics.getDeltaTime() * 2.0F;
/* 53 */       this.duration -= Gdx.graphics.getDeltaTime();
/* 54 */       if (this.duration < 0.0F) {
/* 55 */         this.isDone = true;
/* 56 */       } else if (this.duration > 1.5F) {
/* 57 */         this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (2.0F - this.duration) * 2.0F);
/* 58 */       } else if (this.duration < 0.5F) {
/* 59 */         this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 75 */         MathUtils.random(-0.08F, 0.08F), this.scale + 
/* 76 */         MathUtils.random(-0.08F, 0.08F), this.rotation + this.flipper + 
/* 77 */         MathUtils.random(-3.0F, 3.0F));
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\BlurWaveNormalEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */