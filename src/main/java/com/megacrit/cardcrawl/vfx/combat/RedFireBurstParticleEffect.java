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
/*    */ public class RedFireBurstParticleEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private static final float DUR = 1.0F;
/*    */   private float x;
/* 18 */   private static final float GRAVITY = 180.0F * Settings.scale;
/*    */   
/*    */   private float y;
/*    */   
/*    */   private float vX;
/*    */   private float vY;
/*    */   private float floor;
/*    */   
/*    */   public RedFireBurstParticleEffect(float x, float y, int timesUpgraded) {
/* 27 */     int roll = MathUtils.random(0, 2);
/* 28 */     if (roll == 0) {
/* 29 */       this.img = ImageMaster.FLAME_1;
/* 30 */     } else if (roll == 1) {
/* 31 */       this.img = ImageMaster.FLAME_2;
/*    */     } else {
/* 33 */       this.img = ImageMaster.FLAME_3;
/*    */     } 
/*    */     
/* 36 */     this.duration = MathUtils.random(0.5F, 1.0F);
/* 37 */     this.x = x - (this.img.packedWidth / 2);
/* 38 */     this.y = y - (this.img.packedHeight / 2) + MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 39 */     this
/*    */ 
/*    */       
/* 42 */       .color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.3F, 0.8F), MathUtils.random(0.1F, 0.2F), 0.0F);
/*    */     
/* 44 */     this.color.a = 0.0F;
/* 45 */     this.rotation = MathUtils.random(-10.0F, 10.0F);
/*    */     
/* 47 */     this.scale = MathUtils.random(1.5F, 2.0F);
/* 48 */     this.scale += timesUpgraded * 0.1F;
/*    */     
/* 50 */     this.scale *= Settings.scale;
/* 51 */     this.vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
/* 52 */     this.vY = MathUtils.random(0.0F, 300.0F) * Settings.scale;
/* 53 */     this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 57 */     this.vY += GRAVITY / this.scale * Gdx.graphics.getDeltaTime();
/* 58 */     this.x += this.vX * Gdx.graphics.getDeltaTime() * MathUtils.sinDeg(Gdx.graphics.getDeltaTime());
/* 59 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 61 */     if (this.scale > 0.3F * Settings.scale) {
/* 62 */       this.scale -= Gdx.graphics.getDeltaTime() * 2.0F;
/*    */     }
/*    */     
/* 65 */     if (this.y < this.floor) {
/* 66 */       this.vY = -this.vY * 0.75F;
/* 67 */       this.y = this.floor + 0.1F;
/* 68 */       this.vX *= 1.1F;
/*    */     } 
/*    */     
/* 71 */     if (1.0F - this.duration < 0.1F) {
/* 72 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 74 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*    */     } 
/*    */     
/* 77 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 78 */     if (this.duration < 0.0F) {
/* 79 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 85 */     sb.setBlendFunction(770, 1);
/* 86 */     sb.setColor(this.color);
/* 87 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 98 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\RedFireBurstParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */