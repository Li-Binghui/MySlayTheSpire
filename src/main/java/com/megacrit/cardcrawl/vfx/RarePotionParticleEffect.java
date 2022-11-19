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
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class RarePotionParticleEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/* 16 */   private Hitbox hb = null; private float oX; private float oY; private float dur_div2;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public RarePotionParticleEffect(float x, float y) {
/* 20 */     this((Hitbox)null);
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/*    */   }
/*    */   
/*    */   public RarePotionParticleEffect(Hitbox hb) {
/* 26 */     this.hb = hb;
/* 27 */     this.img = ImageMaster.ROOM_SHINE_2;
/* 28 */     this.duration = MathUtils.random(0.9F, 1.2F);
/* 29 */     this.scale = MathUtils.random(0.4F, 0.6F) * Settings.scale;
/* 30 */     this.dur_div2 = this.duration / 2.0F;
/* 31 */     this.color = new Color(1.0F, MathUtils.random(0.7F, 1.0F), 0.4F, 0.0F);
/*    */     
/* 33 */     this.oX += MathUtils.random(-27.0F, 27.0F) * Settings.scale;
/* 34 */     this.oY += MathUtils.random(-27.0F, 27.0F) * Settings.scale;
/* 35 */     this.oX -= this.img.packedWidth / 2.0F;
/* 36 */     this.oY -= this.img.packedHeight / 2.0F;
/*    */     
/* 38 */     this.renderBehind = MathUtils.randomBoolean(0.2F + this.scale - 0.5F);
/* 39 */     this.rotation = MathUtils.random(-5.0F, 5.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     if (this.duration > this.dur_div2) {
/* 45 */       this.color.a = Interpolation.pow3In.apply(0.6F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 47 */       this.color.a = Interpolation.pow3In.apply(0.0F, 0.6F, this.duration / this.dur_div2);
/*    */     } 
/*    */     
/* 50 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 51 */     if (this.duration < 0.0F) {
/* 52 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 58 */     sb.setColor(this.color);
/* 59 */     sb.setBlendFunction(770, 1);
/* 60 */     if (this.hb == null) {
/* 61 */       sb.draw((TextureRegion)this.img, this.x + this.oX, this.y + this.oY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 70 */           MathUtils.random(0.6F, 1.4F), this.rotation);
/*    */     } else {
/*    */       
/* 73 */       sb.draw((TextureRegion)this.img, this.hb.cX + this.oX, this.hb.cY + this.oY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 82 */           MathUtils.random(0.6F, 1.4F), this.rotation);
/*    */     } 
/*    */     
/* 85 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RarePotionParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */