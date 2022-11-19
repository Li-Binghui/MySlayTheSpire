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
/*    */ public class ExhaustBlurEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   
/*    */   public ExhaustBlurEffect(float x, float y) {
/* 18 */     this.color = Color.BLACK.cpy();
/* 19 */     this.color.r = MathUtils.random(0.1F, 0.4F);
/* 20 */     this.color.g = this.color.r;
/* 21 */     this.color.b = this.color.r + 0.05F;
/*    */     
/* 23 */     if (MathUtils.randomBoolean()) {
/* 24 */       this.img = ImageMaster.EXHAUST_L;
/* 25 */       this.duration = MathUtils.random(0.9F, 1.2F);
/* 26 */       this.targetScale = MathUtils.random(0.5F, 1.3F);
/*    */     } else {
/* 28 */       this.img = ImageMaster.EXHAUST_S;
/* 29 */       this.duration = MathUtils.random(0.6F, 1.4F);
/* 30 */       this.targetScale = MathUtils.random(0.3F, 1.0F);
/*    */     } 
/*    */     
/* 33 */     this.startDur = this.duration;
/*    */     
/* 35 */     this.x = x + MathUtils.random(-150.0F * Settings.scale, 150.0F * Settings.scale) - this.img.packedWidth / 2.0F;
/* 36 */     this.y = y + MathUtils.random(-240.0F * Settings.scale, 150.0F * Settings.scale) - this.img.packedHeight / 2.0F;
/* 37 */     this.scale = 0.01F;
/* 38 */     this.rotation = MathUtils.random(360.0F);
/* 39 */     this.aV = MathUtils.random(-250.0F, 250.0F);
/* 40 */     this.vY = MathUtils.random(1.0F * Settings.scale, 5.0F * Settings.scale);
/*    */   }
/*    */   private float aV; private TextureAtlas.AtlasRegion img; private float startDur; private float targetScale;
/*    */   public void update() {
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 45 */     if (this.duration < 0.0F) {
/* 46 */       this.isDone = true;
/*    */     }
/* 48 */     this.x += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 49 */     this.y += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 50 */     this.y += this.vY * Gdx.graphics.getDeltaTime() * 60.0F;
/* 51 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 52 */     this.scale = Interpolation.swing.apply(0.01F, this.targetScale, 1.0F - this.duration / this.startDur);
/*    */ 
/*    */     
/* 55 */     if (this.duration < 0.33F) {
/* 56 */       this.color.a = this.duration * 3.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 62 */     sb.setColor(this.color);
/* 63 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ExhaustBlurEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */