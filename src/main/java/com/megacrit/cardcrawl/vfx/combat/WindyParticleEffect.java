/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class WindyParticleEffect extends AbstractGameEffect {
/*    */   private float scaleY;
/*    */   private float x;
/*    */   
/*    */   public WindyParticleEffect(Color setColor, boolean reverse) {
/* 16 */     if (!reverse) {
/* 17 */       this.x = MathUtils.random(-400.0F, -100.0F) * Settings.scale - 128.0F;
/* 18 */       this.vX = MathUtils.random(1500.0F, 2500.0F) * Settings.scale;
/*    */     } else {
/* 20 */       this.x = Settings.WIDTH + MathUtils.random(400.0F, 100.0F) * Settings.scale - 128.0F;
/* 21 */       this.vX = MathUtils.random(-1500.0F, -2500.0F) * Settings.scale;
/*    */     } 
/* 23 */     this.y = MathUtils.random(0.15F, 0.85F) * Settings.HEIGHT - 128.0F;
/* 24 */     this.vY = MathUtils.random(-100.0F, 100.0F) * Settings.scale;
/* 25 */     this.duration = 2.0F;
/* 26 */     this.scale = MathUtils.random(1.5F, 3.0F);
/* 27 */     this.vX *= this.scale;
/* 28 */     this.scale *= Settings.scale;
/* 29 */     this.scaleY = MathUtils.random(0.5F, 2.0F) * Settings.scale;
/* 30 */     this.color = setColor.cpy();
/* 31 */     this.color.a = MathUtils.random(0.5F, 1.0F);
/* 32 */     if (this.scaleY < 1.0F * Settings.scale)
/* 33 */       this.renderBehind = true; 
/*    */   }
/*    */   private float y; private float vX; private float vY;
/*    */   
/*    */   public WindyParticleEffect() {
/* 38 */     this.x = MathUtils.random(-400.0F, -100.0F) * Settings.scale - 128.0F;
/* 39 */     this.y = MathUtils.random(0.15F, 0.85F) * Settings.HEIGHT - 128.0F;
/* 40 */     this.vX = MathUtils.random(1500.0F, 2500.0F) * Settings.scale;
/* 41 */     this.vY = MathUtils.random(-100.0F, 100.0F) * Settings.scale;
/* 42 */     this.duration = 2.0F;
/* 43 */     this.scale = MathUtils.random(1.5F, 3.0F);
/* 44 */     this.vX *= this.scale;
/* 45 */     this.scale *= Settings.scale;
/* 46 */     this.scaleY = MathUtils.random(0.5F, 2.0F) * Settings.scale;
/* 47 */     this.color = new Color(0.9F, 0.9F, 1.0F, MathUtils.random(0.5F, 1.0F));
/* 48 */     if (this.scaleY < 1.0F * Settings.scale) {
/* 49 */       this.renderBehind = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void update() {
/* 54 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 55 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 56 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 57 */     if (this.duration < 0.0F) {
/* 58 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 63 */     sb.setBlendFunction(770, 1);
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.draw(ImageMaster.HORIZONTAL_LINE, this.x, this.y, 128.0F, 128.0F, 256.0F, 256.0F, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 73 */         MathUtils.random(0.7F, 1.3F), this.scaleY * 
/* 74 */         MathUtils.random(0.7F, 1.3F), this.rotation, 0, 0, 256, 256, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 82 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WindyParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */