/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/*    */
/*    */ public class CardPoofParticle extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 14 */   private float scale = Settings.scale; private float vY; private float vA; private float delay;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public CardPoofParticle(float x, float y) {
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
/* 31 */     this.duration = 0.6F;
/* 32 */     this.startingDuration = this.duration;
/* 33 */     this.delay = MathUtils.random(0.0F, 0.1F);
/* 34 */     float t = MathUtils.random(-160.0F, 160.0F);
/* 35 */     this.x = x + t * Settings.scale - this.img.packedWidth / 2.0F;
/* 36 */     t = MathUtils.random(-180.0F, 180.0F);
/* 37 */     this.y = y + t * Settings.scale - this.img.packedHeight / 2.0F;
/* 38 */     float rg = MathUtils.random(0.4F, 0.8F);
/* 39 */     this.color = new Color(rg + 0.05F, rg, rg + 0.05F, 0.0F);
/* 40 */     this.vA = MathUtils.random(-400.0F, 400.0F) * Settings.scale;
/* 41 */     this.vX = MathUtils.random(-170.0F, 170.0F) * Settings.scale;
/* 42 */     this.vY = MathUtils.random(-170.0F, 170.0F) * Settings.scale;
/* 43 */     this.scale = MathUtils.random(0.8F, 2.5F) * Settings.scale;
/* 44 */     this.rotation = MathUtils.random(360.0F);
/* 45 */     this.renderBehind = true;
/*    */   }
/*    */   
/*    */   public void update() {
/* 49 */     if (this.delay > 0.0F) {
/* 50 */       this.delay -= Gdx.graphics.getDeltaTime();
/*    */       return;
/*    */     } 
/* 53 */     this.rotation += this.vA * Gdx.graphics.getDeltaTime();
/* 54 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 55 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 56 */     this.scale += Gdx.graphics.getDeltaTime() * 5.0F;
/*    */     
/* 58 */     if (this.duration > this.startingDuration / 2.0F) {
/* 59 */       this.color.a = Interpolation.pow3Out.apply(0.0F, 0.7F, 1.0F - this.duration);
/*    */     } else {
/* 61 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
/*    */     } 
/*    */     
/* 64 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 65 */     if (this.duration < 0.0F) {
/* 66 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 72 */     sb.setColor(this.color);
/* 73 */     if (this.flipX && !this.img.isFlipX()) {
/* 74 */       this.img.flip(true, false);
/* 75 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 76 */       this.img.flip(true, false);
/*    */     } 
/* 78 */     if (this.flipY && !this.img.isFlipY()) {
/* 79 */       this.img.flip(false, true);
/* 80 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 81 */       this.img.flip(false, true);
/*    */     } 
/* 83 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\CardPoofParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */