/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LogoFlameEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float offsetX;
/*    */   private float offsetY;
/*    */   
/*    */   public LogoFlameEffect() {
/* 18 */     int roll = MathUtils.random(2);
/* 19 */     switch (roll) {
/*    */       case 0:
/* 21 */         this.img = ImageMaster.vfxAtlas.findRegion("buffVFX1");
/*    */         break;
/*    */       case 1:
/* 24 */         this.img = ImageMaster.vfxAtlas.findRegion("buffVFX2");
/*    */         break;
/*    */       default:
/* 27 */         this.img = ImageMaster.vfxAtlas.findRegion("buffVFX3");
/*    */         break;
/*    */     } 
/*    */     
/* 31 */     this.offsetX = MathUtils.random(10.0F, 30.0F) * Settings.scale;
/* 32 */     this.offsetY = MathUtils.random(230.0F, 240.0F) * Settings.scale;
/* 33 */     this.vX = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 34 */     this.vY = MathUtils.random(30.0F, 70.0F) * Settings.scale;
/* 35 */     this.duration = MathUtils.random(1.0F, 1.5F);
/* 36 */     this.startingDuration = this.duration;
/* 37 */     this.color = Color.WHITE.cpy();
/* 38 */     this.color.r = MathUtils.random(1.0F);
/* 39 */     this.scale = Settings.scale;
/*    */   }
/*    */   private float vX; private float vY; private float startingDuration;
/*    */   
/*    */   public void update() {
/* 44 */     this.offsetX += this.vX * Gdx.graphics.getDeltaTime();
/* 45 */     this.offsetY += this.vY * Gdx.graphics.getDeltaTime();
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 48 */     if (this.duration < 0.0F) {
/* 49 */       this.isDone = true;
/* 50 */     } else if (this.duration < 0.5F) {
/* 51 */       this.color.a = this.duration;
/* 52 */       this.scale = this.duration * 2.0F * Settings.scale;
/* 53 */     } else if (this.startingDuration - this.duration < 0.5F) {
/* 54 */       this.color.a = this.startingDuration - this.duration;
/*    */     } else {
/* 56 */       this.color.a = 0.5F;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, float x, float y) {
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw((TextureRegion)this.img, x + this.offsetX, y + this.offsetY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\LogoFlameEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */