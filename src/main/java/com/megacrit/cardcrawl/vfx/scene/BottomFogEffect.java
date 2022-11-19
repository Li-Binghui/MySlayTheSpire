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
/*    */ public class BottomFogEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 15 */   private boolean flipX = MathUtils.randomBoolean(); private float vX; private float aV; private boolean flipY = MathUtils.randomBoolean();
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public BottomFogEffect(boolean renderBehind) {
/* 19 */     this.duration = MathUtils.random(10.0F, 12.0F);
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
/* 34 */     this.x = MathUtils.random(-200.0F, 2120.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 35 */     this.y = Settings.HEIGHT / 2.0F + MathUtils.random(60.0F, 410.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 36 */     this.vX = MathUtils.random(-200.0F, 200.0F) * Settings.scale;
/* 37 */     this.aV = MathUtils.random(-10.0F, 10.0F);
/*    */     
/* 39 */     this.renderBehind = renderBehind;
/*    */     
/* 41 */     float tmp = MathUtils.random(0.1F, 0.15F);
/* 42 */     this.color = new Color();
/* 43 */     this.color.r = tmp + MathUtils.random(0.1F);
/* 44 */     this.color.g = tmp;
/* 45 */     this.color.b = this.color.r + MathUtils.random(0.05F);
/* 46 */     this.scale = MathUtils.random(4.0F, 6.0F) * Settings.scale;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 51 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 52 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/*    */     
/* 54 */     if (this.startingDuration - this.duration < 5.0F) {
/* 55 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, (this.startingDuration - this.duration) / 5.0F);
/* 56 */     } else if (this.duration < 5.0F) {
/* 57 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, 1.0F - this.duration / 5.0F);
/*    */     } 
/* 59 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 60 */     this.scale += Gdx.graphics.getDeltaTime() / 3.0F;
/* 61 */     if (this.duration < 0.0F) {
/* 62 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb, float srcX, float srcY) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 77 */     sb.setColor(this.color);
/* 78 */     if (this.flipX && !this.img.isFlipX()) {
/* 79 */       this.img.flip(true, false);
/* 80 */     } else if (!this.flipX && this.img.isFlipX()) {
/* 81 */       this.img.flip(true, false);
/*    */     } 
/* 83 */     if (this.flipY && !this.img.isFlipY()) {
/* 84 */       this.img.flip(false, true);
/* 85 */     } else if (!this.flipY && this.img.isFlipY()) {
/* 86 */       this.img.flip(false, true);
/*    */     } 
/* 88 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\BottomFogEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */