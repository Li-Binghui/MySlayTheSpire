/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class DamageImpactBlurEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/*    */   
/*    */   public DamageImpactBlurEffect(float x, float y) {
/* 17 */     this.img = ImageMaster.STRIKE_BLUR;
/* 18 */     this.duration = MathUtils.random(0.5F, 0.75F);
/* 19 */     this.startingDuration = this.duration;
/* 20 */     this.x = x - this.img.packedWidth / 2.0F;
/* 21 */     this.y = y - this.img.packedHeight / 2.0F;
/* 22 */     this.rotation = 0.0F;
/* 23 */     this.vX = MathUtils.random(-42000.0F * Settings.scale, 42000.0F * Settings.scale);
/* 24 */     this.vY = MathUtils.random(-42000.0F * Settings.scale, 42000.0F * Settings.scale);
/* 25 */     this.startScale = MathUtils.random(4.0F, 8.0F);
/* 26 */     this.renderBehind = true;
/*    */     
/* 28 */     float tmp = MathUtils.random(0.1F, 0.3F);
/* 29 */     this.color = new Color(tmp, tmp, tmp, 1.0F);
/*    */   }
/*    */   private float vY; private float startScale; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 33 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 34 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 35 */     this.vX *= 56.0F * Gdx.graphics.getDeltaTime();
/* 36 */     this.vY *= 56.0F * Gdx.graphics.getDeltaTime();
/*    */     
/* 38 */     this.scale = Settings.scale * (this.duration / this.startingDuration * 2.0F + this.startScale);
/* 39 */     this.color.a = this.duration;
/*    */     
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 49 */     sb.setColor(this.color);
/* 50 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DamageImpactBlurEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */