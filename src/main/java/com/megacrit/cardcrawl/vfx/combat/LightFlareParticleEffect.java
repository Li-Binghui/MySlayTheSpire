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
/*    */ public class LightFlareParticleEffect extends AbstractGameEffect {
/* 16 */   private Vector2 pos = new Vector2(); private float speed;
/*    */   private float speedStart;
/*    */   private float speedTarget;
/*    */   
/*    */   public LightFlareParticleEffect(float x, float y, Color color) {
/* 21 */     this.img = ImageMaster.STRIKE_BLUR;
/* 22 */     this.duration = MathUtils.random(0.5F, 1.1F);
/* 23 */     this.startingDuration = this.duration;
/* 24 */     this.pos.x = x - this.img.packedWidth / 2.0F;
/* 25 */     this.pos.y = y - this.img.packedHeight / 2.0F;
/* 26 */     this.speed = MathUtils.random(200.0F, 300.0F) * Settings.scale;
/* 27 */     this.speedStart = this.speed;
/* 28 */     this.speedTarget = MathUtils.random(20.0F, 30.0F) * Settings.scale;
/* 29 */     this.color = color.cpy();
/* 30 */     this.color.a = 0.0F;
/* 31 */     this.renderBehind = true;
/* 32 */     this.rotation = MathUtils.random(360.0F);
/* 33 */     this.waveIntensity = MathUtils.random(5.0F, 10.0F);
/* 34 */     this.waveSpeed = MathUtils.random(-20.0F, 20.0F);
/* 35 */     this.speedTarget = MathUtils.random(0.1F, 0.5F);
/* 36 */     this.scale = MathUtils.random(0.2F, 1.0F) * Settings.scale;
/*    */   }
/*    */   private float waveIntensity; private float waveSpeed; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 40 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 41 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 42 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 43 */     this.speed = Interpolation.pow2OutInverse.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / this.startingDuration);
/* 44 */     this.pos.x += tmp.x;
/* 45 */     this.pos.y += tmp.y;
/* 46 */     this.rotation += MathUtils.cos(this.duration * this.waveSpeed) * this.waveIntensity;
/* 47 */     if (this.duration < 0.5F) {
/* 48 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } else {
/* 50 */       this.color.a = 1.0F;
/*    */     } 
/* 52 */     super.update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 57 */     sb.setBlendFunction(770, 1);
/* 58 */     sb.setColor(new Color(this.color.r, this.color.g, this.color.b, this.color.a / 4.0F));
/* 59 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 4.0F, this.scale * 4.0F, this.rotation);
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
/* 70 */     sb.setColor(this.color);
/* 71 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 82 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LightFlareParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */