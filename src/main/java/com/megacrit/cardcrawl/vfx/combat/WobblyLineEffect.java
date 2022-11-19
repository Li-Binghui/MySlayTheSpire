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
/*    */ public class WobblyLineEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 2.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float speed;
/*    */   
/*    */   public WobblyLineEffect(float x, float y, Color color) {
/* 22 */     this.img = ImageMaster.WOBBLY_LINE;
/* 23 */     this.rotation = MathUtils.random(360.0F);
/* 24 */     this.scale = MathUtils.random(0.2F, 0.4F);
/* 25 */     this.x = x - this.img.packedWidth / 2.0F;
/* 26 */     this.y = y - this.img.packedHeight / 2.0F;
/* 27 */     this.duration = 2.0F;
/* 28 */     this.color = color;
/* 29 */     this.renderBehind = MathUtils.randomBoolean();
/* 30 */     this.speedStart = MathUtils.random(300.0F, 1000.0F) * Settings.scale;
/* 31 */     this.speedTarget = 900.0F * Settings.scale;
/* 32 */     this.speed = this.speedStart;
/*    */     
/* 34 */     if (MathUtils.randomBoolean()) {
/* 35 */       this.flipper = 90.0F;
/*    */     } else {
/* 37 */       this.flipper = 270.0F;
/*    */     } 
/* 39 */     color.g -= MathUtils.random(0.1F);
/* 40 */     color.b -= MathUtils.random(0.2F);
/* 41 */     color.a = 0.0F;
/*    */   }
/*    */   private float speedStart; private float speedTarget; private float flipper; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 45 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 46 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 47 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 48 */     this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / 2.0F);
/* 49 */     this.x += tmp.x;
/* 50 */     this.y += tmp.y;
/*    */     
/* 52 */     this.scale += Gdx.graphics.getDeltaTime();
/* 53 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 54 */     if (this.duration < 0.0F) {
/* 55 */       this.isDone = true;
/* 56 */     } else if (this.duration > 1.5F) {
/* 57 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (2.0F - this.duration) * 2.0F);
/* 58 */     } else if (this.duration < 0.5F) {
/* 59 */       this.color.a = Interpolation.fade.apply(0.0F, 0.7F, this.duration * 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 75 */         MathUtils.random(-0.08F, 0.08F) * Settings.scale, this.scale + 
/* 76 */         MathUtils.random(-0.08F, 0.08F) * Settings.scale, this.rotation + this.flipper + 
/* 77 */         MathUtils.random(-5.0F, 5.0F));
/* 78 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WobblyLineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */