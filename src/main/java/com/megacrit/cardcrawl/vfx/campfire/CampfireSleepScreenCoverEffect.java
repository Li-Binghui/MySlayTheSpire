/*    */ package com.megacrit.cardcrawl.vfx.campfire;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class CampfireSleepScreenCoverEffect extends AbstractGameEffect {
/*    */   private float x;
/* 14 */   private float targetAlpha = MathUtils.random(0.4F, 0.7F); private float y; private float aV;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public CampfireSleepScreenCoverEffect() {
/* 19 */     this.duration = MathUtils.random(2.0F, 2.5F);
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
/* 34 */     this.x = MathUtils.random(-100.0F * Settings.scale, Settings.WIDTH + 100.0F * Settings.scale) - this.img.packedWidth / 2.0F;
/* 35 */     this.y = MathUtils.random(-100.0F * Settings.scale, Settings.HEIGHT + 100.0F * Settings.scale) - this.img.packedHeight / 2.0F;
/* 36 */     this.aV = MathUtils.random(-30.0F, 30.0F);
/* 37 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 38 */     float tmp = MathUtils.random(0.5F, 0.7F);
/* 39 */     this.color = new Color();
/* 40 */     this.color.r = tmp;
/* 41 */     this.color.g = tmp - 0.03F;
/* 42 */     this.color.b = tmp - 0.07F;
/* 43 */     this.scale = MathUtils.random(16.0F, 30.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 48 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 50 */     if (this.startingDuration - this.duration < 1.0F) {
/* 51 */       this.color.a = Interpolation.fade.apply(0.0F, this.targetAlpha, this.startingDuration - this.duration);
/* 52 */     } else if (this.duration < 1.0F) {
/* 53 */       this.color.a = Interpolation.fade.apply(this.targetAlpha, 0.0F, 1.0F - this.duration);
/*    */     } 
/* 55 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 56 */     if (this.duration < 0.0F) {
/* 57 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 63 */     sb.setColor(this.color);
/* 64 */     if (this.flipX && !this.img.isFlipX()) {
/* 65 */       this.img.flip(true, false);
/* 66 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 67 */       this.img.flip(true, false);
/*    */     } 
/* 69 */     if (this.flipY && !this.img.isFlipY()) {
/* 70 */       this.img.flip(false, true);
/* 71 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 72 */       this.img.flip(false, true);
/*    */     } 
/* 74 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireSleepScreenCoverEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */