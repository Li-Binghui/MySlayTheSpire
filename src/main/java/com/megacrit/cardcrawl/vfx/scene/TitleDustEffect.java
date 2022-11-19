/*    */ package com.megacrit.cardcrawl.vfx.scene;
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
/*    */ public class TitleDustEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public TitleDustEffect() {
/* 19 */     this.duration = MathUtils.random(3.0F, 4.0F);
/* 20 */     this.startingDuration = this.duration;
/*    */     
/* 22 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 24 */         this.img = ImageMaster.SMOKE_1;
/*    */         break;
/*    */       case 1:
/* 27 */         this.img = ImageMaster.SMOKE_2;
/*    */         break;
/*    */       default:
/* 30 */         this.img = ImageMaster.SMOKE_3;
/*    */         break;
/*    */     } 
/*    */     
/* 34 */     this.x = -600.0F * Settings.scale - this.img.packedWidth / 2.0F;
/* 35 */     this.y = MathUtils.random(1.0F, 20.0F);
/* 36 */     this.y *= this.y * Settings.scale;
/* 37 */     this.y -= this.img.packedHeight / 2.0F;
/* 38 */     this.vX = MathUtils.random(400.0F, 1200.0F) * Settings.scale;
/* 39 */     this.vY = MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 40 */     this.aV = MathUtils.random(-50.0F, 50.0F);
/*    */     
/* 42 */     float tmp = MathUtils.random(0.2F, 0.3F);
/* 43 */     this.color = new Color();
/* 44 */     this.color.g = tmp + MathUtils.random(0.1F);
/* 45 */     this.color.r = this.color.g + MathUtils.random(0.1F);
/* 46 */     this.color.b = tmp;
/* 47 */     this.scale = MathUtils.random(6.0F, 8.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 52 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 53 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 54 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 56 */     if (this.startingDuration - this.duration < 1.0F) {
/* 57 */       this.color.a = Interpolation.fade.apply(0.2F, 0.2F, (this.startingDuration - this.duration) / 1.0F);
/* 58 */     } else if (this.duration < 1.0F) {
/* 59 */       this.color.a = Interpolation.fade.apply(0.2F, 0.0F, 1.0F - this.duration / 1.0F);
/*    */     } 
/* 61 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 62 */     this.scale += Gdx.graphics.getDeltaTime() / 3.0F;
/* 63 */     if (this.duration < 0.0F) {
/* 64 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, float srcX, float srcY) {
/* 70 */     sb.setColor(this.color);
/* 71 */     if (this.flipX && !this.img.isFlipX()) {
/* 72 */       this.img.flip(true, false);
/* 73 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 74 */       this.img.flip(true, false);
/*    */     } 
/* 76 */     if (this.flipY && !this.img.isFlipY()) {
/* 77 */       this.img.flip(false, true);
/* 78 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 79 */       this.img.flip(false, true);
/*    */     } 
/*    */     
/* 82 */     sb.draw((TextureRegion)this.img, this.x, this.y + srcY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\TitleDustEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */