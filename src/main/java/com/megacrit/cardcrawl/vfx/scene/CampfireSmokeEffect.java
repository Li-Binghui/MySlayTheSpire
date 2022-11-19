/*    */ package com.megacrit.cardcrawl.vfx.scene;
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
/*    */ public class CampfireSmokeEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float vY; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public CampfireSmokeEffect() {
/* 19 */     this.duration = MathUtils.random(7.0F, 11.0F);
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
/* 34 */     this.x = 188.0F * Settings.scale - this.img.packedWidth / 2.0F;
/* 35 */     this.y = 60.0F * Settings.scale - this.img.packedHeight / 2.0F;
/* 36 */     this.vX = MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 37 */     this.vY = MathUtils.random(10.0F, 60.0F) * Settings.scale;
/* 38 */     this.aV = MathUtils.random(-50.0F, 50.0F);
/*    */     
/* 40 */     float tmp = MathUtils.random(0.2F, 0.35F);
/* 41 */     this.color = new Color();
/* 42 */     this.color.r = tmp;
/* 43 */     this.color.g = tmp;
/* 44 */     this.color.b = tmp;
/* 45 */     this.scale = MathUtils.random(0.8F, 1.2F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 50 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 51 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 52 */     this.vX *= 0.99F;
/* 53 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 55 */     if (this.startingDuration - this.duration < 1.5F) {
/* 56 */       this.color.a = Interpolation.fade.apply(0.0F, 0.4F, (this.startingDuration - this.duration) / 1.5F);
/* 57 */     } else if (this.duration < 4.0F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.4F, 0.0F, 1.0F - this.duration / 4.0F);
/*    */     } 
/* 60 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 61 */     this.scale += Gdx.graphics.getDeltaTime() / 3.0F;
/* 62 */     if (this.duration < 0.0F) {
/* 63 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, float srcX, float srcY) {
/* 69 */     sb.setColor(this.color);
/* 70 */     if (this.flipX && !this.img.isFlipX()) {
/* 71 */       this.img.flip(true, false);
/* 72 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 73 */       this.img.flip(true, false);
/*    */     } 
/* 75 */     if (this.flipY && !this.img.isFlipY()) {
/* 76 */       this.img.flip(false, true);
/* 77 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 78 */       this.img.flip(false, true);
/*    */     } 
/*    */     
/* 81 */     sb.draw((TextureRegion)this.img, srcX + this.x, srcY + this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\CampfireSmokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */