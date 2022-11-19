/*    */ package com.megacrit.cardcrawl.vfx.campfire;
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
/*    */ public class CampfireBubbleEffect extends AbstractGameEffect {
/*    */   private float x;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private float y; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public CampfireBubbleEffect(boolean isLarge) {
/* 19 */     this.duration = MathUtils.random(10.0F, 20.0F);
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
/* 34 */     this.x = MathUtils.random(-300.0F, 300.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 35 */     if (isLarge) {
/* 36 */       this.y = MathUtils.random(-200.0F, 230.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/*    */     } else {
/* 38 */       this.y = MathUtils.random(0.0F, 230.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/*    */     } 
/* 40 */     this.aV = MathUtils.random(-30.0F, 30.0F);
/*    */     
/* 42 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 43 */     float tmp = MathUtils.random(0.8F, 1.0F);
/* 44 */     this.color = new Color();
/* 45 */     this.color.r = tmp;
/* 46 */     this.color.g = tmp - 0.03F;
/* 47 */     this.color.b = tmp - 0.07F;
/* 48 */     this.scale = MathUtils.random(6.0F, 9.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 53 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 55 */     if (this.startingDuration - this.duration < 3.0F) {
/* 56 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, (this.startingDuration - this.duration) / 3.0F);
/* 57 */     } else if (this.duration < 3.0F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.5F, 0.0F, 1.0F - this.duration / 3.0F);
/*    */     } 
/* 60 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 61 */     if (this.duration < 0.0F) {
/* 62 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, float srcX, float srcY) {
/* 68 */     sb.setColor(this.color);
/* 69 */     if (this.flipX && !this.img.isFlipX()) {
/* 70 */       this.img.flip(true, false);
/* 71 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 72 */       this.img.flip(true, false);
/*    */     } 
/* 74 */     if (this.flipY && !this.img.isFlipY()) {
/* 75 */       this.img.flip(false, true);
/* 76 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 77 */       this.img.flip(false, true);
/*    */     } 
/* 79 */     sb.draw((TextureRegion)this.img, srcX + this.x, srcY + this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\campfire\CampfireBubbleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */