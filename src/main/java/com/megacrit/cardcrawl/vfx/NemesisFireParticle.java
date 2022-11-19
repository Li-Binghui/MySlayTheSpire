/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
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
/*    */ 
/*    */ public class NemesisFireParticle
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public NemesisFireParticle(float x, float y) {
/* 23 */     this.duration = MathUtils.random(0.5F, 1.0F);
/* 24 */     this.startingDuration = this.duration;
/* 25 */     this.img = getImg();
/* 26 */     this.x = x - (this.img.packedWidth / 2);
/* 27 */     this.y = y - (this.img.packedHeight / 2);
/* 28 */     this.scale = Settings.scale * MathUtils.random(0.25F, 0.5F);
/* 29 */     this.vY = MathUtils.random(1.0F, 10.0F) * Settings.scale;
/* 30 */     this.vY *= this.vY;
/* 31 */     this.rotation = MathUtils.random(-20.0F, 20.0F);
/* 32 */     this.color = new Color(0.1F, 0.2F, 0.1F, 0.01F);
/*    */   }
/*    */   
/*    */   private TextureAtlas.AtlasRegion getImg() {
/* 36 */     switch (MathUtils.random(0, 2)) {
/*    */       case 0:
/* 38 */         return ImageMaster.TORCH_FIRE_1;
/*    */       case 1:
/* 40 */         return ImageMaster.TORCH_FIRE_2;
/*    */     } 
/* 42 */     return ImageMaster.TORCH_FIRE_3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 47 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 48 */     if (this.duration < 0.0F) {
/* 49 */       this.isDone = true;
/*    */     }
/* 51 */     this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration);
/* 52 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 58 */     sb.setColor(this.color);
/* 59 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\NemesisFireParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */