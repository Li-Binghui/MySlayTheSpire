/*    */ package com.megacrit.cardcrawl.vfx.combat;
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
/*    */ public class VerticalAuraParticle extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 16 */   private float fadeInTimer = 0.2F; private float vY; private static final float FADE_IN_TIME = 0.2F; private static final float FADE_OUT_TIME = 0.8F; private float fadeOutTimer = 0.8F;
/*    */   private float stallTimer;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public VerticalAuraParticle(Color c, float x, float y) {
/* 21 */     this.img = ImageMaster.VERTICAL_AURA;
/* 22 */     this.color = c.cpy();
/* 23 */     randomizeColor(this.color, 0.1F);
/* 24 */     this.color.a = 0.0F;
/* 25 */     this.x = x + MathUtils.random(-200.0F, 200.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 26 */     this.y = y + MathUtils.random(-200.0F, 200.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 27 */     this.vY = MathUtils.random(-300.0F, 300.0F) * Settings.scale;
/* 28 */     this.stallTimer = MathUtils.random(0.0F, 0.2F);
/* 29 */     this.scale = MathUtils.random(0.6F, 1.7F) * Settings.scale;
/* 30 */     this.renderBehind = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 35 */     if (this.stallTimer > 0.0F) {
/* 36 */       this.stallTimer -= Gdx.graphics.getDeltaTime();
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 41 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */ 
/*    */     
/* 44 */     if (this.fadeInTimer != 0.0F) {
/* 45 */       this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/* 46 */       if (this.fadeInTimer < 0.0F) {
/* 47 */         this.fadeInTimer = 0.0F;
/*    */       }
/* 49 */       this.color.a = Interpolation.fade.apply(0.5F, 0.0F, this.fadeInTimer / 0.2F);
/* 50 */     } else if (this.fadeOutTimer != 0.0F) {
/* 51 */       this.fadeOutTimer -= Gdx.graphics.getDeltaTime();
/* 52 */       if (this.fadeOutTimer < 0.0F) {
/* 53 */         this.fadeOutTimer = 0.0F;
/*    */       }
/* 55 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.fadeOutTimer / 0.8F);
/*    */     } else {
/* 57 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void randomizeColor(Color c, float amt) {
/* 62 */     float r = c.r + MathUtils.random(-amt, amt);
/* 63 */     float g = c.g + MathUtils.random(-amt, amt);
/* 64 */     float b = c.b + MathUtils.random(-amt, amt);
/*    */     
/* 66 */     if (r > 1.0F) {
/* 67 */       r = 1.0F;
/* 68 */     } else if (r < 0.0F) {
/* 69 */       r = 0.0F;
/*    */     } 
/*    */     
/* 72 */     if (g > 1.0F) {
/* 73 */       g = 1.0F;
/* 74 */     } else if (g < 0.0F) {
/* 75 */       g = 0.0F;
/*    */     } 
/*    */     
/* 78 */     if (b > 1.0F) {
/* 79 */       b = 1.0F;
/* 80 */     } else if (b < 0.0F) {
/* 81 */       b = 0.0F;
/*    */     } 
/*    */     
/* 84 */     c.r = r;
/* 85 */     c.g = g;
/* 86 */     c.b = b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 91 */     sb.setColor(this.color);
/* 92 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\VerticalAuraParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */