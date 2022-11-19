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
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class StarBounceEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private static final float DUR = 1.0F;
/*    */   private float x;
/* 18 */   private static final float GRAVITY = 3000.0F * Settings.scale;
/*    */   
/*    */   private float y;
/*    */   
/*    */   private float vX;
/*    */   private float vY;
/*    */   private float floor;
/*    */   
/*    */   public StarBounceEffect(float x, float y) {
/* 27 */     this.img = ImageMaster.TINY_STAR;
/* 28 */     this.duration = MathUtils.random(0.5F, 1.0F);
/* 29 */     this.x = x - (this.img.packedWidth / 2);
/* 30 */     this.y = y - (this.img.packedHeight / 2);
/* 31 */     this
/*    */ 
/*    */       
/* 34 */       .color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.6F, 0.8F), MathUtils.random(0.0F, 0.6F), 0.0F);
/*    */     
/* 36 */     this.color.a = 0.0F;
/* 37 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 38 */     this.scale = MathUtils.random(0.5F, 2.0F) * Settings.scale;
/* 39 */     this.vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
/* 40 */     this.vY = MathUtils.random(500.0F, 1000.0F) * Settings.scale;
/* 41 */     this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 45 */     this.vY -= GRAVITY / this.scale * Gdx.graphics.getDeltaTime();
/* 46 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 47 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 48 */     Vector2 test = new Vector2(this.vX, this.vY);
/* 49 */     this.rotation = test.angle();
/*    */     
/* 51 */     if (this.y < this.floor) {
/* 52 */       this.vY = -this.vY * 0.75F;
/* 53 */       this.y = this.floor + 0.1F;
/* 54 */       this.vX *= 1.1F;
/*    */     } 
/*    */     
/* 57 */     if (1.0F - this.duration < 0.1F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 60 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*    */     } 
/*    */     
/* 63 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 64 */     if (this.duration < 0.0F) {
/* 65 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 71 */     sb.setBlendFunction(770, 1);
/* 72 */     sb.setColor(this.color);
/* 73 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 81 */         MathUtils.random(0.8F, 1.2F), this.scale * 
/* 82 */         MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */     
/* 84 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 92 */         MathUtils.random(0.8F, 1.2F), this.scale * 
/* 93 */         MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */     
/* 95 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\StarBounceEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */