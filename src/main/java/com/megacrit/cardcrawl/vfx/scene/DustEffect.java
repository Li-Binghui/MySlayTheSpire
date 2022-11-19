/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DustEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 24 */   private TextureAtlas.AtlasRegion img = getImg();
/*    */ 
/*    */ 
/*    */   
/* 28 */   private float x = MathUtils.random(0, Settings.WIDTH);
/* 29 */   private float y = MathUtils.random(-100.0F, 400.0F) * Settings.scale + AbstractDungeon.floorY;
/* 30 */   private float vX = MathUtils.random(-12.0F, 12.0F) * Settings.scale;
/* 31 */   private float vY = MathUtils.random(-12.0F, 30.0F) * Settings.scale; private float aV;
/*    */   
/*    */   public DustEffect() {
/* 34 */     float colorTmp = MathUtils.random(0.1F, 0.7F);
/* 35 */     this.color = new Color(colorTmp, colorTmp, colorTmp, 0.0F);
/* 36 */     this.baseAlpha = 1.0F - colorTmp;
/* 37 */     this.color.a = 0.0F;
/*    */     
/* 39 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 40 */     this.aV = MathUtils.random(-120.0F, 120.0F);
/*    */   }
/*    */   private float baseAlpha;
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 44 */     switch (MathUtils.random(0, 5)) {
/*    */       case 0:
/* 46 */         return ImageMaster.DUST_1;
/*    */       case 1:
/* 48 */         return ImageMaster.DUST_2;
/*    */       case 2:
/* 50 */         return ImageMaster.DUST_3;
/*    */       case 3:
/* 52 */         return ImageMaster.DUST_4;
/*    */       case 4:
/* 54 */         return ImageMaster.DUST_5;
/*    */     } 
/* 56 */     return ImageMaster.DUST_6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 61 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 62 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 63 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 64 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 66 */     if (this.duration < 0.0F) {
/* 67 */       this.isDone = true;
/*    */     }
/*    */     
/* 70 */     if (this.duration > this.startingDuration / 2.0F) {
/* 71 */       float tmp = this.duration - this.startingDuration / 2.0F;
/* 72 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration / 2.0F - tmp) * this.baseAlpha;
/*    */     } else {
/* 74 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration / 2.0F) * this.baseAlpha;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 80 */     sb.setColor(this.color);
/* 81 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.offsetX, this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\DustEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */