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
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LightRayFlyOutEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float speed;
/*    */   
/*    */   public LightRayFlyOutEffect(float x, float y, Color color) {
/* 22 */     this.img = ImageMaster.GLOW_SPARK;
/* 23 */     this.rotation = MathUtils.random(360.0F);
/* 24 */     this.scale = MathUtils.random(1.0F, 1.4F);
/* 25 */     this.scaleXMod = MathUtils.random(0.0F, 1.5F);
/* 26 */     this.x = x - this.img.packedWidth / 2.0F;
/* 27 */     this.y = y - this.img.packedHeight / 2.0F;
/* 28 */     this.duration = 2.0F;
/* 29 */     this.color = color;
/* 30 */     this.renderBehind = MathUtils.randomBoolean();
/* 31 */     this.speedStart = MathUtils.random(500.0F, 1000.0F) * Settings.scale;
/* 32 */     this.speedTarget = 1300.0F * Settings.scale;
/* 33 */     this.speed = this.speedStart;
/*    */     
/* 35 */     if (MathUtils.randomBoolean()) {
/* 36 */       this.flipper = 90.0F;
/*    */     } else {
/* 38 */       this.flipper = 270.0F;
/*    */     } 
/* 40 */     color.g -= MathUtils.random(0.1F);
/* 41 */     color.b -= MathUtils.random(0.2F);
/* 42 */     color.a = 0.0F;
/*    */   }
/*    */   private float speedStart; private float speedTarget; private float scaleXMod; private float flipper; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 46 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 47 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 48 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 49 */     this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / 2.0F);
/* 50 */     this.x += tmp.x;
/* 51 */     this.y += tmp.y;
/*    */     
/* 53 */     this.scale += Gdx.graphics.getDeltaTime();
/* 54 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 55 */     if (this.duration < 0.0F) {
/* 56 */       this.isDone = true;
/* 57 */     } else if (this.duration > 1.5F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (2.0F - this.duration) * 2.0F);
/* 59 */     } else if (this.duration < 0.5F) {
/* 60 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 66 */     sb.setBlendFunction(770, 1);
/* 67 */     sb.setColor(this.color);
/* 68 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, (this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 76 */         MathUtils.random(-0.08F, 0.08F)) * Settings.scale, (this.scale + this.scaleXMod + 
/* 77 */         MathUtils.random(-0.08F, 0.08F)) * Settings.scale, this.rotation + this.flipper + 
/* 78 */         MathUtils.random(-5.0F, 5.0F));
/* 79 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LightRayFlyOutEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */