/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;

/*    */
/*    */ public class UncommonPotionParticleEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float oX;
/* 16 */   private Hitbox hb = null; private float oY; private float vX; private float vY; private float dur_div2;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public UncommonPotionParticleEffect(float x, float y) {
/* 20 */     this((Hitbox)null);
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/*    */   }
/*    */   
/*    */   public UncommonPotionParticleEffect(Hitbox hb) {
/* 26 */     this.hb = hb;
/* 27 */     this.img = ImageMaster.GLOW_SPARK_2;
/* 28 */     this.duration = MathUtils.random(0.8F, 1.0F);
/* 29 */     this.scale = MathUtils.random(0.4F, 0.7F) * Settings.scale;
/* 30 */     this.dur_div2 = this.duration / 2.0F;
/* 31 */     this.color = new Color(0.6F, 0.7F, MathUtils.random(0.8F, 1.0F), 0.0F);
/*    */     
/* 33 */     this.oX = MathUtils.random(-25.0F, 25.0F) * Settings.scale;
/* 34 */     this.oY = MathUtils.random(-25.0F, 25.0F) * Settings.scale;
/* 35 */     this.oX -= this.img.packedWidth / 2.0F;
/* 36 */     this.oY -= this.img.packedHeight / 2.0F;
/* 37 */     this.vX = MathUtils.random(-5.0F, 5.0F) * Settings.scale;
/* 38 */     this.vY = MathUtils.random(-7.0F, 7.0F) * Settings.scale;
/*    */     
/* 40 */     this.renderBehind = MathUtils.randomBoolean(0.2F + this.scale - 0.5F);
/* 41 */     this.rotation = MathUtils.random(-8.0F, 8.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 46 */     if (this.duration > this.dur_div2) {
/* 47 */       this.color.a = Interpolation.pow3In.apply(0.5F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 49 */       this.color.a = Interpolation.pow3In.apply(0.0F, 0.5F, this.duration / this.dur_div2);
/*    */     } 
/*    */     
/* 52 */     this.oX += this.vX * Gdx.graphics.getDeltaTime();
/* 53 */     this.oY += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 55 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 56 */     if (this.duration < 0.0F) {
/* 57 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     if (this.hb != null) {
/* 67 */       sb.draw((TextureRegion)this.img, this.hb.cX + this.oX, this.hb.cY + this.oY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 75 */           MathUtils.random(0.8F, 1.2F), this.scale * 
/* 76 */           MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */     } else {
/*    */       
/* 79 */       sb.draw((TextureRegion)this.img, this.x + this.oX, this.y + this.oY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 87 */           MathUtils.random(0.8F, 1.2F), this.scale * 
/* 88 */           MathUtils.random(0.8F, 1.2F), this.rotation);
/*    */     } 
/*    */     
/* 91 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\UncommonPotionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */