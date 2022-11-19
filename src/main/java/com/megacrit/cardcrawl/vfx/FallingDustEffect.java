/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class FallingDustEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   private float vX;
/*    */   
/*    */   public FallingDustEffect(float x, float y) {
/* 17 */     this.x = x + MathUtils.random(-6.0F, 6.0F) * Settings.scale;
/* 18 */     this.y = y;
/* 19 */     float randY = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 20 */     y += randY;
/* 21 */     this.renderBehind = (randY < 0.0F);
/*    */     
/* 23 */     this.vY = MathUtils.random(0.0F, 140.0F) * Settings.scale;
/* 24 */     if (MathUtils.randomBoolean()) {
/* 25 */       this.vX = MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/*    */     } else {
/* 27 */       this.vX = 0.0F;
/*    */     } 
/* 29 */     this.vYAccel = MathUtils.random(4.0F, 9.0F) * Settings.scale;
/* 30 */     this.duration = MathUtils.random(3.0F, 7.0F);
/* 31 */     this.img = setImg();
/* 32 */     this.scale = Settings.scale * MathUtils.random(0.5F, 0.7F);
/* 33 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 34 */     float c = MathUtils.random(0.1F, 0.3F);
/* 35 */     this.color = new Color(c + 0.1F, c, c, 0.0F);
/* 36 */     this.color.a = MathUtils.random(0.8F, 0.9F);
/* 37 */     this.startingAlpha = this.color.a;
/* 38 */     this.aV = MathUtils.random(-1.0F, 1.0F);
/*    */   }
/*    */   private float vYAccel; private float aV; private float startingAlpha; private TextureAtlas.AtlasRegion img;
/*    */   private TextureAtlas.AtlasRegion setImg() {
/* 42 */     switch (MathUtils.random(0, 5)) {
/*    */       case 0:
/* 44 */         return ImageMaster.DUST_1;
/*    */       case 1:
/* 46 */         return ImageMaster.DUST_2;
/*    */       case 2:
/* 48 */         return ImageMaster.DUST_3;
/*    */       case 3:
/* 50 */         return ImageMaster.DUST_4;
/*    */       case 4:
/* 52 */         return ImageMaster.DUST_5;
/*    */     } 
/* 54 */     return ImageMaster.DUST_6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 59 */     this.rotation += this.aV;
/* 60 */     this.y -= this.vY * Gdx.graphics.getDeltaTime();
/* 61 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 62 */     this.vY += this.vYAccel * Gdx.graphics.getDeltaTime();
/* 63 */     this.vX *= 0.99F;
/*    */     
/* 65 */     if (this.duration < 3.0F) {
/* 66 */       this.color.a = Interpolation.fade.apply(this.startingAlpha, 0.0F, 1.0F - this.duration / 3.0F);
/*    */     }
/*    */     
/* 69 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 70 */     if (this.duration < 0.0F) {
/* 71 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 77 */     sb.setColor(this.color);
/* 78 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.offsetX, this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FallingDustEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */