/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ThirdEyeParticleEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public ThirdEyeParticleEffect(float x, float y, float vX, float vY) {
/* 20 */     int roll = MathUtils.random(2);
/* 21 */     if (roll == 0) {
/* 22 */       this.img = ImageMaster.FLAME_1;
/* 23 */     } else if (roll == 1) {
/* 24 */       this.img = ImageMaster.FLAME_3;
/*    */     } else {
/* 26 */       this.img = ImageMaster.FLAME_3;
/*    */     } 
/* 28 */     this.x = x - this.img.packedWidth / 2.0F;
/* 29 */     this.y = y - this.img.packedHeight / 2.0F;
/* 30 */     this.vX = vX * Settings.scale;
/* 31 */     this.vY = vY * Settings.scale;
/*    */     
/* 33 */     this.rotation = (new Vector2(vX, vY)).angle() - 90.0F;
/*    */     
/* 35 */     this.scale = 3.0F * Settings.scale;
/* 36 */     this.color = Color.VIOLET.cpy();
/* 37 */     this.color.a = 0.0F;
/* 38 */     this.startingDuration = 0.6F;
/* 39 */     this.duration = this.startingDuration;
/* 40 */     this.renderBehind = true;
/*    */   }
/*    */   private float vX; private float vY; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 44 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 45 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 47 */     if (this.duration > this.startingDuration / 2.0F) {
/* 48 */       this.color.a = Interpolation.pow2Out.apply(0.7F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*    */     
/*    */     }
/*    */     else {
/*    */       
/* 53 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration / this.startingDuration / 2.0F);
/* 54 */       this.scale = Interpolation.fade.apply(0.01F, 3.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 57 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 58 */     if (this.duration < 0.0F) {
/* 59 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 65 */     sb.setColor(this.color);
/* 66 */     sb.setBlendFunction(770, 1);
/* 67 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 75 */         MathUtils.random(-0.05F, 0.05F), this.scale + 
/* 76 */         MathUtils.random(-0.05F, 0.05F), this.rotation);
/*    */     
/* 78 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ThirdEyeParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */