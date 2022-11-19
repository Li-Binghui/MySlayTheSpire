/*    */ package com.megacrit.cardcrawl.vfx.stance;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class StanceChangeAbsorptionParticle extends AbstractGameEffect {
/*    */   private float oX;
/*    */   private float oY;
/*    */   private float x;
/*    */   
/*    */   public StanceChangeAbsorptionParticle(Color color, float x, float y) {
/* 17 */     this.startingDuration = 1.0F;
/* 18 */     this.duration = this.startingDuration;
/* 19 */     this.color = color.cpy();
/* 20 */     this.color.r -= MathUtils.random(0.1F);
/* 21 */     this.color.g -= MathUtils.random(0.1F);
/* 22 */     this.color.b -= MathUtils.random(0.1F);
/*    */ 
/*    */     
/* 25 */     this.rotation = MathUtils.random(360.0F);
/* 26 */     this.oX = x;
/* 27 */     this.oY = y;
/* 28 */     this.distOffset = MathUtils.random(-200.0F, 1000.0F);
/* 29 */     this.renderBehind = true;
/* 30 */     this.aV = MathUtils.random(50.0F, 80.0F);
/* 31 */     this.scaleOffset = MathUtils.random(1.0F);
/*    */   }
/*    */   private float y; private float aV; private float distOffset; private float scaleOffset;
/*    */   public void update() {
/* 35 */     this.x = this.oX + MathUtils.cosDeg(this.rotation) * (800.0F + this.distOffset) * this.duration * Settings.scale;
/* 36 */     this.y = this.oY + MathUtils.sinDeg(this.rotation) * (800.0F + this.distOffset) * this.duration * Settings.scale;
/* 37 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 38 */     this.rotation -= this.duration * Interpolation.pow5Out.apply(this.aV, 2.0F, this.duration);
/* 39 */     this.scale = (this.duration + 0.2F + this.scaleOffset) * Settings.scale;
/* 40 */     this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/* 41 */     if (this.duration < 0.0F) {
/* 42 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     sb.setColor(this.color);
/* 49 */     sb.setBlendFunction(770, 1);
/* 50 */     sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 58 */         MathUtils.random(0.5F, 2.0F), this.scale * 
/* 59 */         MathUtils.random(0.5F, 2.0F), this.rotation - 200.0F, 0, 0, 32, 32, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 67 */     sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 75 */         MathUtils.random(0.6F, 2.5F), this.scale * 
/* 76 */         MathUtils.random(0.6F, 2.5F), this.rotation - 200.0F, 0, 0, 32, 32, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 84 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\StanceChangeAbsorptionParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */