/*    */ package com.megacrit.cardcrawl.vfx.scene;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LightFlareSEffect extends AbstractGameEffect {
/*    */   private float x;
/* 16 */   public static TextureAtlas.AtlasRegion[] imgs = new TextureAtlas.AtlasRegion[2];
/*    */ 
/*    */   
/*    */   private float y;
/*    */   
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public static boolean renderGreen = false;
/*    */ 
/*    */   
/*    */   public LightFlareSEffect(float x, float y) {
/* 27 */     if (imgs[0] == null) {
/*    */       
/* 29 */       imgs[0] = ImageMaster.vfxAtlas.findRegion("env/lightFlare1");
/* 30 */       imgs[1] = ImageMaster.vfxAtlas.findRegion("env/lightFlare2");
/*    */     } 
/*    */     
/* 33 */     this.duration = MathUtils.random(2.0F, 3.0F);
/* 34 */     this.startingDuration = this.duration;
/* 35 */     this.img = imgs[MathUtils.random(imgs.length - 1)];
/* 36 */     this.x = x - (this.img.packedWidth / 2);
/* 37 */     this.y = y - (this.img.packedHeight / 2);
/* 38 */     this.scale = Settings.scale * MathUtils.random(3.0F, 3.5F);
/* 39 */     this.rotation = MathUtils.random(360.0F);
/*    */     
/* 41 */     if (!renderGreen) {
/* 42 */       this
/*    */ 
/*    */         
/* 45 */         .color = new Color(MathUtils.random(0.6F, 1.0F), MathUtils.random(0.4F, 0.7F), MathUtils.random(0.2F, 0.3F), 0.01F);
/*    */     } else {
/*    */       
/* 48 */       this
/*    */ 
/*    */         
/* 51 */         .color = new Color(MathUtils.random(0.1F, 0.3F), MathUtils.random(0.5F, 0.9F), MathUtils.random(0.1F, 0.3F), 0.01F);
/*    */     } 
/*    */     
/* 54 */     this.renderBehind = true;
/*    */   }
/*    */   
/*    */   public void update() {
/* 58 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 59 */     if (this.duration < 0.0F) {
/* 60 */       this.isDone = true;
/*    */     }
/* 62 */     if (this.startingDuration - this.duration < 1.0F) {
/* 63 */       this.color.a = Interpolation.fade.apply(0.2F, 0.0F, this.duration / this.startingDuration);
/*    */     } else {
/* 65 */       this.color.a = Interpolation.fade.apply(0.0F, 0.2F, this.duration / this.startingDuration);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 71 */     sb.setBlendFunction(770, 1);
/* 72 */     sb.setColor(this.color);
/* 73 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\LightFlareSEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */