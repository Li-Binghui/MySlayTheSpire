/*    */ package com.megacrit.cardcrawl.vfx;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class DeathScreenFloatyEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/*    */   
/*    */   public DeathScreenFloatyEffect() {
/* 19 */     int roll = MathUtils.random(5);
/* 20 */     if (roll == 0) {
/* 21 */       this.img = ImageMaster.DEATH_VFX_1;
/* 22 */     } else if (roll == 1) {
/* 23 */       this.img = ImageMaster.DEATH_VFX_2;
/* 24 */     } else if (roll == 2) {
/* 25 */       this.img = ImageMaster.DEATH_VFX_3;
/* 26 */     } else if (roll == 3) {
/* 27 */       this.img = ImageMaster.DEATH_VFX_4;
/* 28 */     } else if (roll == 4) {
/* 29 */       this.img = ImageMaster.DEATH_VFX_5;
/*    */     } else {
/* 31 */       this.img = ImageMaster.DEATH_VFX_6;
/*    */     } 
/*    */     
/* 34 */     this.x = MathUtils.random(0.0F, Settings.WIDTH) - this.img.packedWidth / 2.0F;
/* 35 */     this.y = MathUtils.random(0.0F, Settings.HEIGHT) - this.img.packedHeight / 2.0F;
/* 36 */     this.vX = MathUtils.random(-20.0F, 20.0F) * Settings.scale * this.scale;
/* 37 */     this.vY = MathUtils.random(-60.0F, 60.0F) * Settings.scale * this.scale;
/* 38 */     this.vX2 = MathUtils.random(-20.0F, 20.0F) * Settings.scale * this.scale;
/* 39 */     this.vY2 = MathUtils.random(-60.0F, 60.0F) * Settings.scale * this.scale;
/* 40 */     this.aV = MathUtils.random(-50.0F, 50.0F);
/*    */     
/* 42 */     float tmp = MathUtils.random(0.2F, 0.4F);
/* 43 */     this.color = new Color();
/* 44 */     this.color.r = tmp + MathUtils.random(0.0F, 0.2F);
/* 45 */     this.color.g = tmp;
/* 46 */     this.color.b = tmp + MathUtils.random(0.0F, 0.2F);
/* 47 */     this.renderBehind = MathUtils.randomBoolean(0.8F);
/* 48 */     this.scale = MathUtils.random(12.0F, 20.0F) * Settings.scale;
/*    */   }
/*    */   private float vY; private float vX2; private float vY2; private float aV;
/*    */   
/*    */   public void update() {
/* 53 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 54 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 55 */     this.vX += this.vX2 * Gdx.graphics.getDeltaTime();
/* 56 */     this.vY += this.vY2 * Gdx.graphics.getDeltaTime();
/* 57 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 59 */     if (this.startingDuration - this.duration < 1.5F) {
/* 60 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, (this.startingDuration - this.duration) / 1.5F);
/* 61 */     } else if (this.duration < 1.5F) {
/* 62 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, 1.0F - this.duration / 1.5F);
/*    */     } 
/* 64 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 66 */     if (this.duration < 0.0F) {
/* 67 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 73 */     sb.setColor(this.color);
/* 74 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DeathScreenFloatyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */