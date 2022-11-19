/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ExhaustEmberEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 17 */   private float rotateSpeed = 0.0F; private float vY; private float startDur; private float targetScale; private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public ExhaustEmberEffect(float x, float y) {
/* 20 */     switch (MathUtils.random(2)) {
/*    */       case 0:
/* 22 */         this.color = Color.CORAL.cpy();
/*    */         break;
/*    */       case 1:
/* 25 */         this.color = Color.ORANGE.cpy();
/*    */         break;
/*    */       case 2:
/* 28 */         this.color = Color.SCARLET.cpy();
/*    */         break;
/*    */     } 
/*    */     
/* 32 */     this.duration = MathUtils.random(0.6F, 1.4F);
/* 33 */     this.duration *= this.duration;
/* 34 */     this.targetScale = MathUtils.random(0.4F, 0.8F);
/* 35 */     this.startDur = this.duration;
/*    */     
/* 37 */     this.vX = MathUtils.random(-150.0F * Settings.scale, 150.0F * Settings.scale);
/* 38 */     this.vY = MathUtils.random(-100.0F * Settings.scale, 300.0F * Settings.scale);
/* 39 */     this.x = x + MathUtils.random(-170.0F * Settings.scale, 170.0F * Settings.scale);
/* 40 */     this.y = y + MathUtils.random(-220.0F * Settings.scale, 150.0F * Settings.scale);
/* 41 */     this.scale = 0.01F;
/* 42 */     this.img = setImg();
/* 43 */     this.rotateSpeed = MathUtils.random(-700.0F, 700.0F);
/*    */   }
/*    */   
/*    */   private TextureAtlas.AtlasRegion setImg() {
/* 47 */     switch (MathUtils.random(0, 5)) {
/*    */       case 0:
/* 49 */         return ImageMaster.DUST_1;
/*    */       case 1:
/* 51 */         return ImageMaster.DUST_2;
/*    */       case 2:
/* 53 */         return ImageMaster.DUST_3;
/*    */       case 3:
/* 55 */         return ImageMaster.DUST_4;
/*    */       case 4:
/* 57 */         return ImageMaster.DUST_5;
/*    */     } 
/* 59 */     return ImageMaster.DUST_6;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 64 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 65 */     if (this.duration < 0.0F) {
/* 66 */       this.isDone = true;
/*    */     }
/* 68 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 69 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 71 */     this.rotation += this.rotateSpeed * Gdx.graphics.getDeltaTime();
/* 72 */     this.scale = Interpolation.swing.apply(0.01F, this.targetScale, 1.0F - this.duration / this.startDur);
/*    */     
/* 74 */     if (this.duration < 0.5F) {
/* 75 */       this.color.a = this.duration * 2.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 81 */     sb.setColor(this.color);
/* 82 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.offsetX, this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 83 */     sb.setColor(new Color(this.color.r, this.color.g, this.color.b, this.color.a / 3.0F));
/* 84 */     sb.setBlendFunction(770, 1);
/* 85 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.offsetX, this.img.offsetY, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 86 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ExhaustEmberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */