/*    */ package com.megacrit.cardcrawl.vfx.combat;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/*    */
/*    */ public class DeckPoofParticle extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 14 */   private float scale = Settings.scale; private float vY; private float vA; private float delay;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public DeckPoofParticle(float x, float y, boolean isDeck) {
/* 19 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 21 */         this.img = ImageMaster.SMOKE_1;
/*    */         break;
/*    */       case 1:
/* 24 */         this.img = ImageMaster.SMOKE_2;
/*    */         break;
/*    */       default:
/* 27 */         this.img = ImageMaster.SMOKE_3;
/*    */         break;
/*    */     } 
/*    */     
/* 31 */     this.duration = 0.8F;
/* 32 */     this.startingDuration = this.duration;
/* 33 */     this.delay = MathUtils.random(0.0F, 0.2F);
/* 34 */     float t = MathUtils.random(-10.0F, 10.0F) * MathUtils.random(-10.0F, 10.0F);
/* 35 */     this.x = x + t * Settings.scale - this.img.packedWidth / 2.0F;
/* 36 */     t = MathUtils.random(-10.0F, 10.0F) * MathUtils.random(-10.0F, 10.0F);
/* 37 */     this.y = y + t * Settings.scale - this.img.packedHeight / 2.0F;
/* 38 */     if (isDeck) {
/* 39 */       float rg = MathUtils.random(0.4F, 0.8F);
/* 40 */       this.color = new Color(rg + 0.1F, rg, rg - 0.2F, 0.0F);
/* 41 */       this.vA = MathUtils.random(-400.0F, 400.0F) * Settings.scale;
/*    */     } else {
/* 43 */       float rb = MathUtils.random(0.3F, 0.5F);
/* 44 */       this.color = new Color(rb, 0.35F, rb + 0.1F, 0.0F);
/* 45 */       this.vA = MathUtils.random(-70.0F, 70.0F) * MathUtils.random(-70.0F, 70.0F) * Settings.scale;
/*    */     } 
/* 47 */     this.vX = MathUtils.random(-70.0F, 70.0F) * Settings.scale;
/* 48 */     this.vY = MathUtils.random(-100.0F, 300.0F) * Settings.scale;
/* 49 */     this.scale = MathUtils.random(0.3F, 1.8F) * Settings.scale;
/* 50 */     this.rotation = MathUtils.random(360.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 54 */     if (this.delay > 0.0F) {
/* 55 */       this.delay -= Gdx.graphics.getDeltaTime();
/*    */       return;
/*    */     } 
/* 58 */     this.rotation += this.vA * Gdx.graphics.getDeltaTime();
/* 59 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 60 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 61 */     this.vY *= 0.99F;
/* 62 */     this.vX *= 0.99F;
/* 63 */     this.scale += Gdx.graphics.getDeltaTime() / 2.0F;
/*    */     
/* 65 */     if (this.duration > this.startingDuration / 2.0F) {
/* 66 */       this.color.a = Interpolation.pow3Out.apply(0.0F, 1.0F, 1.0F - this.duration);
/*    */     } else {
/* 68 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } 
/*    */     
/* 71 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 72 */     if (this.duration < 0.0F) {
/* 73 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 79 */     sb.setColor(this.color);
/* 80 */     if (this.flipX && !this.img.isFlipX()) {
/* 81 */       this.img.flip(true, false);
/* 82 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 83 */       this.img.flip(true, false);
/*    */     } 
/* 85 */     if (this.flipY && !this.img.isFlipY()) {
/* 86 */       this.img.flip(false, true);
/* 87 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 88 */       this.img.flip(false, true);
/*    */     } 
/* 90 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DeckPoofParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */