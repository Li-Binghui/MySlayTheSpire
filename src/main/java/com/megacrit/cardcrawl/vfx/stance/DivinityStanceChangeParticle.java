/*    */ package com.megacrit.cardcrawl.vfx.stance;
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
/*    */ public class DivinityStanceChangeParticle extends AbstractGameEffect {
/* 15 */   private TextureAtlas.AtlasRegion img = ImageMaster.STRIKE_LINE; private float oX; private float oY;
/*    */   private float x;
/*    */   
/*    */   public DivinityStanceChangeParticle(Color color, float x, float y) {
/* 19 */     this.startingDuration = 0.5F;
/* 20 */     this.duration = this.startingDuration;
/* 21 */     this.color = color.cpy();
/* 22 */     this.rotation = MathUtils.random(360.0F);
/* 23 */     this.oX = x - this.img.packedWidth / 2.0F + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 24 */     this.oY = y - this.img.packedHeight / 2.0F + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 25 */     this.distOffset = MathUtils.random(800.0F, 1200.0F);
/* 26 */     this.renderBehind = true;
/* 27 */     this.aV = MathUtils.random(50.0F, 80.0F);
/* 28 */     this.scaleOffset = MathUtils.random(4.0F, 5.0F);
/*    */     
/* 30 */     this.aV = MathUtils.random(0.4F);
/*    */   }
/*    */   private float y; private float aV; private float distOffset; private float scaleOffset;
/*    */   public void update() {
/* 34 */     if (this.aV > 0.0F) {
/* 35 */       this.aV -= Gdx.graphics.getDeltaTime();
/*    */       
/*    */       return;
/*    */     } 
/* 39 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 40 */     if (this.duration < 0.0F) {
/* 41 */       this.isDone = true;
/*    */       
/*    */       return;
/*    */     } 
/* 45 */     this.x = this.oX + MathUtils.cosDeg(this.rotation) * this.distOffset * Interpolation.pow2In.apply(0.02F, 0.95F, this.duration * 2.0F) * Settings.scale;
/*    */     
/* 47 */     this.y = this.oY + MathUtils.sinDeg(this.rotation) * this.distOffset * Interpolation.pow3In.apply(0.02F, 0.95F, this.duration * 2.0F) * Settings.scale;
/*    */ 
/*    */     
/* 50 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 51 */     this.scale = this.scaleOffset * (this.duration + 0.1F) * Settings.scale;
/* 52 */     this.color.a = Interpolation.pow3In.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 57 */     sb.setColor(this.color);
/* 58 */     sb.setBlendFunction(770, 1);
/* 59 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
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
/*    */     
/* 81 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\DivinityStanceChangeParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */