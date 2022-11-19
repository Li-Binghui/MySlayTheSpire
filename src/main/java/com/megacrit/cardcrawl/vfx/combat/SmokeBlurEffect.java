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
/*    */ public class SmokeBlurEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   
/*    */   public SmokeBlurEffect(float x, float y) {
/* 19 */     this.color = new Color(0.0F, 0.0F, 0.0F, 1.0F);
/* 20 */     this.color.r = MathUtils.random(0.5F, 0.6F);
/* 21 */     this.color.g = this.color.r + MathUtils.random(0.0F, 0.2F);
/* 22 */     this.color.b = 0.2F;
/*    */     
/* 24 */     if (MathUtils.randomBoolean()) {
/* 25 */       this.img = ImageMaster.EXHAUST_L;
/* 26 */       this.duration = MathUtils.random(2.0F, 2.5F);
/* 27 */       this.targetScale = MathUtils.random(0.8F, 2.2F);
/*    */     } else {
/* 29 */       this.img = ImageMaster.EXHAUST_S;
/* 30 */       this.duration = MathUtils.random(2.0F, 2.5F);
/* 31 */       this.targetScale = MathUtils.random(0.8F, 1.2F);
/*    */     } 
/*    */     
/* 34 */     this.startDur = this.duration;
/*    */     
/* 36 */     this.x = x + MathUtils.random(-180.0F * Settings.scale, 150.0F * Settings.scale) - this.img.packedWidth / 2.0F;
/* 37 */     this.y = y + MathUtils.random(-240.0F * Settings.scale, 150.0F * Settings.scale) - this.img.packedHeight / 2.0F;
/* 38 */     this.scale = 0.01F;
/* 39 */     this.rotation = MathUtils.random(360.0F);
/* 40 */     this.aV = MathUtils.random(-250.0F, 250.0F);
/* 41 */     this.vY = MathUtils.random(1.0F * Settings.scale, 5.0F * Settings.scale);
/*    */   }
/*    */   private float aV; private float startDur; private float targetScale; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 45 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/*    */     }
/* 49 */     this.x += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 50 */     this.x += this.vY;
/* 51 */     this.y += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 52 */     this.y += this.vY;
/* 53 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 54 */     this.scale = Interpolation.exp10Out.apply(0.01F, this.targetScale, 1.0F - this.duration / this.startDur);
/*    */     
/* 56 */     if (this.duration < 0.33F) {
/* 57 */       this.color.a = this.duration * 3.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 63 */     sb.setColor(this.color);
/* 64 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SmokeBlurEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */