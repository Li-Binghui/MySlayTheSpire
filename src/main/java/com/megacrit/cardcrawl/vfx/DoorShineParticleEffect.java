/*    */ package com.megacrit.cardcrawl.vfx;
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
/*    */ 
/*    */ public class DoorShineParticleEffect extends AbstractGameEffect {
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private static final float DUR = 1.0F;
/*    */   private float x;
/* 18 */   private static final float GRAVITY = 3000.0F * Settings.scale;
/*    */   
/*    */   private float y;
/*    */   
/*    */   private float vX;
/*    */   
/*    */   private float vY;
/*    */ 
/*    */   
/*    */   public DoorShineParticleEffect(float x, float y) {
/* 28 */     if (MathUtils.randomBoolean()) {
/* 29 */       this.img = ImageMaster.STRIKE_LINE_2;
/*    */     } else {
/* 31 */       this.img = ImageMaster.GLOW_SPARK_2;
/*    */     } 
/*    */     
/* 34 */     this.duration = MathUtils.random(0.2F, 1.0F);
/* 35 */     this.x = x - (this.img.packedWidth / 2);
/* 36 */     this.y = y - (this.img.packedHeight / 2);
/* 37 */     this
/*    */ 
/*    */       
/* 40 */       .color = new Color(MathUtils.random(0.7F, 1.0F), MathUtils.random(0.6F, 0.7F), MathUtils.random(0.1F, 0.3F), 0.0F);
/*    */     
/* 42 */     this.color.a = 0.0F;
/* 43 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 44 */     this.scale = MathUtils.random(0.5F, 1.5F) * Settings.scale;
/* 45 */     this.vX = MathUtils.random(-2000.0F, 2000.0F) * Settings.scale;
/* 46 */     this.vY = MathUtils.random(-1500.0F, 2500.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 50 */     this.vY -= GRAVITY / this.scale * Gdx.graphics.getDeltaTime();
/* 51 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 52 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 53 */     Vector2 test = new Vector2(this.vX, this.vY);
/* 54 */     this.rotation = test.angle();
/*    */     
/* 56 */     if (1.0F - this.duration < 0.1F) {
/* 57 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 59 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*    */     } 
/*    */     
/* 62 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 63 */     if (this.duration < 0.0F) {
/* 64 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 70 */     sb.setBlendFunction(770, 1);
/* 71 */     sb.setColor(this.color);
/* 72 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 80 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 81 */         MathUtils.random(0.9F, 1.1F), this.rotation);
/*    */     
/* 83 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 91 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 92 */         MathUtils.random(0.9F, 1.1F), this.rotation);
/*    */     
/* 94 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DoorShineParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */