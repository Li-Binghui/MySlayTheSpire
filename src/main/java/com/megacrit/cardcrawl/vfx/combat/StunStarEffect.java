/*    */ package com.megacrit.cardcrawl.vfx.combat;
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
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class StunStarEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private static final float DURATION = 2.0F;
/*    */   private float x;
/*    */   
/*    */   public StunStarEffect(float x, float y) {
/* 20 */     this.duration = 2.0F;
/* 21 */     this.img = ImageMaster.TINY_STAR;
/*    */     
/* 23 */     this.x = x - this.img.packedWidth / 2.0F;
/* 24 */     this.y = y - this.img.packedWidth / 2.0F;
/* 25 */     this.vX = 128.0F * Settings.scale;
/* 26 */     this.color = new Color(1.0F, 0.9F, 0.3F, 0.0F);
/* 27 */     this.renderBehind = false;
/* 28 */     this.scale = Settings.scale;
/* 29 */     this.rotation = MathUtils.random(360.0F);
/*    */   }
/*    */   private float y; private float vX; private float vY; private float scale;
/*    */   
/*    */   public void update() {
/* 34 */     this.vX = MathUtils.cos(3.1415927F * this.duration);
/* 35 */     this.vY = MathUtils.cos(3.1415927F * this.duration * 2.0F);
/*    */     
/* 37 */     this.rotation -= Gdx.graphics.getDeltaTime() * 60.0F;
/* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.isDone = true;
/* 41 */     } else if (this.duration < 1.0F) {
/* 42 */       this.color.a = Interpolation.pow5Out.apply(this.duration);
/* 43 */       this.color.r = Interpolation.pow2Out.apply(this.duration);
/* 44 */       this.color.g = Interpolation.pow2Out.apply(this.duration) * 0.9F;
/* 45 */       this.color.b = Interpolation.pow2Out.apply(this.duration) * 0.3F;
/* 46 */     } else if (this.duration > 1.0F) {
/* 47 */       this.color.a = Interpolation.pow5Out.apply(1.0F - this.duration - 1.0F);
/* 48 */       this.color.r = Interpolation.pow4Out.apply(1.0F - this.duration - 1.0F);
/* 49 */       this.color.g = Interpolation.pow4Out.apply(1.0F - this.duration - 1.0F) * 0.9F;
/* 50 */       this.color.b = Interpolation.pow4Out.apply(1.0F - this.duration - 1.0F) * 0.3F;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 57 */     sb.setColor(this.color);
/* 58 */     sb.draw((TextureRegion)this.img, this.x - this.vX * 30.0F * Settings.scale, this.y - this.vY * 5.0F * Settings.scale, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\StunStarEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */