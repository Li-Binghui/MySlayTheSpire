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
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
/*    */ 
/*    */ public class ChestShineEffect extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/*    */   private float y;
/* 17 */   private TextureAtlas.AtlasRegion img = ImageMaster.ROOM_SHINE_2; private float vY; private float alpha; private float targetScale;
/*    */   
/*    */   public ChestShineEffect() {
/* 20 */     this.effectDuration = MathUtils.random(1.0F, 3.0F);
/* 21 */     this.duration = this.effectDuration;
/* 22 */     this.startingDuration = this.effectDuration;
/* 23 */     this.x = AbstractChest.CHEST_LOC_X + MathUtils.random(-200.0F, 170.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 24 */     this.y = AbstractChest.CHEST_LOC_Y + MathUtils.random(-250.0F, 50.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 25 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 26 */     this.alpha = MathUtils.random(0.7F, 1.0F);
/*    */     
/* 28 */     this.color = new Color(1.0F, 1.0F, MathUtils.random(0.6F, 0.9F), this.alpha);
/* 29 */     this.scale = 0.01F;
/* 30 */     this.targetScale = MathUtils.random(0.5F, 1.2F);
/* 31 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 35 */     if (this.vY != 0.0F) {
/* 36 */       this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 37 */       MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 38 */       if (this.vY < 0.5F) {
/* 39 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 44 */     float t = (this.effectDuration - this.duration) * 2.0F;
/* 45 */     if (t > 1.0F) {
/* 46 */       t = 1.0F;
/*    */     }
/* 48 */     float tmp = Interpolation.bounceOut.apply(0.01F, this.targetScale, t);
/* 49 */     this.scale = tmp * tmp * Settings.scale;
/*    */     
/* 51 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 52 */     if (this.duration < 0.0F) {
/* 53 */       this.isDone = true;
/* 54 */     } else if (this.duration < this.effectDuration / 2.0F) {
/* 55 */       this.color.a = Interpolation.exp5In.apply(0.0F, this.alpha, this.duration / this.effectDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 61 */     sb.setColor(this.color);
/* 62 */     sb.setBlendFunction(770, 1);
/* 63 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 71 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 72 */         MathUtils.random(0.7F, 1.3F), this.rotation);
/*    */     
/* 74 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ChestShineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */