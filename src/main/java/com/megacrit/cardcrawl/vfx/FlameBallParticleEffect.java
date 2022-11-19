/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class FlameBallParticleEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   
/*    */   public FlameBallParticleEffect(float x, float y, int intensity) {
/* 18 */     int roll = MathUtils.random(0, 2);
/* 19 */     if (roll == 0) {
/* 20 */       this.img = ImageMaster.SMOKE_1;
/* 21 */     } else if (roll == 1) {
/* 22 */       this.img = ImageMaster.SMOKE_2;
/*    */     } else {
/* 24 */       this.img = ImageMaster.SMOKE_3;
/*    */     } 
/*    */     
/* 27 */     this.scale = (1.0F + intensity * 0.1F) * Settings.scale;
/* 28 */     this.duration = MathUtils.random(1.0F, 1.6F);
/* 29 */     this.x = x - (this.img.packedWidth / 2);
/* 30 */     this.y = y - (this.img.packedHeight / 2) + intensity * 3.0F * Settings.scale;
/*    */     
/* 32 */     this
/*    */ 
/*    */       
/* 35 */       .color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.2F, 0.8F), MathUtils.random(0.0F, 0.4F), 0.0F);
/*    */ 
/*    */     
/* 38 */     this.color.a = 0.0F;
/* 39 */     this.rotation = MathUtils.random(-5.0F, 5.0F);
/* 40 */     this.vY = MathUtils.random(10.0F, 30.0F) * Settings.scale;
/* 41 */     this.renderBehind = MathUtils.randomBoolean();
/* 42 */     this.startingDuration = 1.0F;
/*    */   }
/*    */   private float y; private float vY;
/*    */   public void update() {
/* 46 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 47 */     if (this.duration > this.startingDuration / 2.0F) {
/* 48 */       this.color.a = Interpolation.fade.apply(0.7F, 0.0F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 50 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 53 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 54 */     if (this.duration < 0.0F) {
/* 55 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 61 */     sb.setBlendFunction(770, 1);
/* 62 */     sb.setColor(this.color);
/* 63 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F + 20.0F * Settings.scale, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/* 74 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FlameBallParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */