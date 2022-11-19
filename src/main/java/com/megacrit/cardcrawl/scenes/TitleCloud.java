/*    */ package com.megacrit.cardcrawl.scenes;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class TitleCloud {
/*    */   private TextureAtlas.AtlasRegion region;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public TitleCloud(TextureAtlas.AtlasRegion region, float vX, float x) {
/* 14 */     this.region = region;
/* 15 */     this.vX = vX;
/* 16 */     this.x = x;
/* 17 */     this.y = Settings.HEIGHT - 1100.0F * Settings.scale + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
/* 18 */     this.vY = MathUtils.random(-vX / 10.0F, vX / 10.0F) * Settings.scale;
/* 19 */     this.sliderJiggle = MathUtils.random(-4.0F, 4.0F);
/*    */   }
/*    */   private float vX; private float vY; private float sliderJiggle;
/*    */   public void update() {
/* 23 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 24 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 26 */     if (this.vX > 0.0F && this.x > 1920.0F * Settings.xScale) {
/* 27 */       this.x = -1920.0F * Settings.xScale;
/* 28 */       this.vX = MathUtils.random(10.0F, 50.0F) * Settings.xScale;
/* 29 */       this.y = Settings.HEIGHT - 1100.0F * Settings.scale + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
/* 30 */       this.vY = MathUtils.random(-this.vX / 5.0F, this.vX / 5.0F) * Settings.scale;
/* 31 */     } else if (this.x < -1920.0F * Settings.xScale) {
/* 32 */       this.x = 1920.0F * Settings.xScale;
/* 33 */       this.vX = MathUtils.random(-50.0F, -10.0F) * Settings.xScale;
/* 34 */       this.y = Settings.HEIGHT - 1100.0F * Settings.scale + MathUtils.random(-50.0F, 50.0F) * Settings.scale;
/* 35 */       this.vY = MathUtils.random(-this.vX / 5.0F, this.vX / 5.0F) * Settings.scale;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb, float slider) {
/* 40 */     renderRegion(sb, this.region, this.x, (-55.0F + this.sliderJiggle) * Settings.scale * slider + this.y);
/*    */   }
/*    */   
/*    */   private void renderRegion(SpriteBatch sb, TextureAtlas.AtlasRegion region, float x, float y) {
/* 44 */     sb.draw(region
/* 45 */         .getTexture(), region.offsetX * Settings.scale + x, region.offsetY * Settings.scale + y, 0.0F, 0.0F, region.packedWidth, region.packedHeight, Settings.scale, Settings.scale, 0.0F, region
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 55 */         .getRegionX(), region
/* 56 */         .getRegionY(), region
/* 57 */         .getRegionWidth(), region
/* 58 */         .getRegionHeight(), false, false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\scenes\TitleCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */