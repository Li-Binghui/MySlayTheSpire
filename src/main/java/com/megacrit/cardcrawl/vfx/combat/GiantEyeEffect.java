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
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class GiantEyeEffect
/*    */   extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public GiantEyeEffect(float setX, float setY, Color setColor) {
/* 18 */     this.duration = 1.0F;
/* 19 */     this.startingDuration = 1.0F;
/* 20 */     this.color = setColor.cpy();
/* 21 */     this.color.a = 0.0F;
/* 22 */     this.img = ImageMaster.EYE_ANIM_0;
/* 23 */     this.x = setX - this.img.packedWidth / 2.0F;
/* 24 */     this.y = setY - this.img.packedHeight / 2.0F;
/*    */   }
/*    */   private float x; private float y;
/*    */   public void update() {
/* 28 */     if (1.0F - this.duration < 0.1F) {
/* 29 */       this.color.a = Interpolation.fade.apply(0.0F, 0.9F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 31 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 0.9F, this.duration);
/*    */     } 
/*    */     
/* 34 */     if (this.duration > this.startingDuration * 0.85F) {
/* 35 */       this.img = ImageMaster.EYE_ANIM_0;
/* 36 */     } else if (this.duration > this.startingDuration * 0.8F) {
/* 37 */       this.img = ImageMaster.EYE_ANIM_1;
/* 38 */     } else if (this.duration > this.startingDuration * 0.75F) {
/* 39 */       this.img = ImageMaster.EYE_ANIM_2;
/* 40 */     } else if (this.duration > this.startingDuration * 0.7F) {
/* 41 */       this.img = ImageMaster.EYE_ANIM_3;
/* 42 */     } else if (this.duration > this.startingDuration * 0.65F) {
/* 43 */       this.img = ImageMaster.EYE_ANIM_4;
/* 44 */     } else if (this.duration > this.startingDuration * 0.6F) {
/* 45 */       this.img = ImageMaster.EYE_ANIM_5;
/* 46 */     } else if (this.duration > this.startingDuration * 0.55F) {
/* 47 */       this.img = ImageMaster.EYE_ANIM_6;
/* 48 */     } else if (this.duration > this.startingDuration * 0.38F) {
/* 49 */       this.img = ImageMaster.EYE_ANIM_5;
/*    */     } 
/*    */     
/* 52 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 60 */     sb.setBlendFunction(770, 1);
/* 61 */     sb.setColor(this.color);
/* 62 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, (this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 70 */         MathUtils.random(-0.02F, 0.02F)) * 3.0F, (this.scale + 
/* 71 */         MathUtils.random(-0.03F, 0.03F)) * 3.0F, this.rotation);
/*    */     
/* 73 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, (this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 81 */         MathUtils.random(-0.02F, 0.02F)) * 3.0F, (this.scale + 
/* 82 */         MathUtils.random(-0.03F, 0.03F)) * 3.0F, this.rotation);
/*    */     
/* 84 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GiantEyeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */