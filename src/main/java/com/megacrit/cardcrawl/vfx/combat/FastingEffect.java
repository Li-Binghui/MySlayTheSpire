/*    */ package com.megacrit.cardcrawl.vfx.combat;
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
/*    */ public class FastingEffect
/*    */   extends AbstractGameEffect {
/*    */   private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public FastingEffect(float x, float y, Color c) {
/* 19 */     if (img == null) {
/* 20 */       img = ImageMaster.WHITE_RING;
/*    */     }
/* 22 */     this.startingDuration = 1.0F;
/* 23 */     this.duration = 1.0F;
/* 24 */     this.scale = 3.0F * Settings.scale;
/* 25 */     this.color = c.cpy();
/* 26 */     this.color.a = 0.0F;
/* 27 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 28 */     this.x = x - img.packedWidth / 2.0F;
/* 29 */     this.y = y - img.packedHeight / 2.0F;
/*    */   }
/*    */   private float x; private float y;
/*    */   
/*    */   public void update() {
/* 34 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 35 */     this.rotation -= Gdx.graphics.getDeltaTime() * 205.0F;
/*    */     
/* 37 */     if (this.duration > 0.5F) {
/* 38 */       this.color.a = Interpolation.fade.apply(0.45F, 0.0F, (this.duration - 0.5F) * 2.0F);
/*    */     } else {
/* 40 */       this.color.a = Interpolation.fade.apply(0.0F, 0.45F, this.duration * 2.0F);
/* 41 */       this.scale = Interpolation.swingOut.apply(0.0F, 3.0F * Settings.scale, this.duration * 2.0F);
/*    */     } 
/*    */     
/* 44 */     if (this.duration < 0.0F) {
/* 45 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 51 */     sb.setBlendFunction(770, 1);
/* 52 */     sb.setColor(this.color);
/* 53 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 61 */         MathUtils.random(-0.05F, 0.05F), this.scale + 
/* 62 */         MathUtils.random(-0.05F, 0.05F), this.rotation);
/*    */     
/* 64 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 72 */         MathUtils.random(-0.05F, 0.05F), this.scale + 
/* 73 */         MathUtils.random(-0.05F, 0.05F), this.rotation + 180.0F);
/*    */     
/* 75 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FastingEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */