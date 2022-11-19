/*    */ package com.megacrit.cardcrawl.vfx;
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
/*    */ public class RoomShineEffect2 extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private TextureAtlas.AtlasRegion img = ImageMaster.ROOM_SHINE_2; private float vY; private float alpha; private float targetScale;
/*    */   
/*    */   public RoomShineEffect2() {
/* 19 */     this.effectDuration = MathUtils.random(1.0F, 3.0F);
/* 20 */     this.duration = this.effectDuration;
/* 21 */     this.startingDuration = this.effectDuration;
/* 22 */     this.x = MathUtils.random(50.0F, 1870.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 23 */     this.y = MathUtils.random(Settings.HEIGHT * 0.0F, Settings.HEIGHT * 0.9F) - this.img.packedHeight / 2.0F;
/* 24 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 25 */     this.alpha = MathUtils.random(0.7F, 1.0F);
/*    */     
/* 27 */     this.color = new Color(1.0F, 1.0F, MathUtils.random(0.6F, 0.9F), this.alpha);
/* 28 */     this.scale = 0.01F;
/* 29 */     this.targetScale = MathUtils.random(1.0F, 1.5F);
/* 30 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 34 */     if (this.vY != 0.0F) {
/* 35 */       this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 36 */       MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 37 */       if (this.vY < 0.5F) {
/* 38 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 43 */     float t = (this.effectDuration - this.duration) * 2.0F;
/* 44 */     if (t > 1.0F) {
/* 45 */       t = 1.0F;
/*    */     }
/* 47 */     float tmp = Interpolation.bounceOut.apply(0.01F, this.targetScale, t);
/* 48 */     this.scale = tmp * tmp * Settings.scale;
/*    */     
/* 50 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 51 */     if (this.duration < 0.0F) {
/* 52 */       this.isDone = true;
/* 53 */     } else if (this.duration < this.effectDuration / 2.0F) {
/* 54 */       this.color.a = Interpolation.exp5In.apply(0.0F, this.alpha, this.duration / this.effectDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 60 */     sb.setColor(this.color);
/* 61 */     sb.setBlendFunction(770, 1);
/* 62 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 70 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 71 */         MathUtils.random(0.7F, 1.3F), this.rotation);
/*    */     
/* 73 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RoomShineEffect2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */