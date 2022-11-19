/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class FastSmoke {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 15 */   private float scale = 0.01F; private float vY; private float rotation; private float fadeInTime; private float fadeInTimer; private float targetScale;
/*    */   private boolean fadingIn = true;
/*    */   private Color color;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private boolean killed = false;
/*    */   private float killSpeed;
/*    */   private float decelerateY;
/*    */   
/*    */   public FastSmoke(float x, float y) {
/* 24 */     this.targetScale = MathUtils.random(1.0F, 1.5F) * Settings.scale;
/* 25 */     this.fadeInTime = MathUtils.random(1.0F, 1.5F);
/* 26 */     this.fadeInTimer = this.fadeInTime;
/* 27 */     float darkness = MathUtils.random(0.4F, 0.9F);
/* 28 */     this.color = new Color(darkness + 0.1F, darkness + 0.1F, darkness + 0.05F, 1.0F);
/*    */     
/* 30 */     if (this.targetScale > 0.5F) {
/* 31 */       this.img = ImageMaster.EXHAUST_L;
/*    */     } else {
/* 33 */       this.img = ImageMaster.EXHAUST_S;
/* 34 */       this.vX /= 3.0F;
/*    */     } 
/*    */     
/* 37 */     this.x = x + MathUtils.random(-75.0F, 75.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 38 */     this.y = y + MathUtils.random(-75.0F, 75.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 39 */     this.vY = MathUtils.random(50.0F * Settings.scale, 400.0F * Settings.scale);
/* 40 */     this.vX = MathUtils.random(-140.0F * Settings.scale, 140.0F * Settings.scale);
/* 41 */     this.rotation = MathUtils.random(360.0F);
/* 42 */     this.killSpeed = MathUtils.random(1.0F, 4.0F);
/* 43 */     this.decelerateY = MathUtils.random(1.0F * Settings.scale, 3.0F * Settings.scale);
/*    */   }
/*    */   
/*    */   public void update() {
/* 47 */     if (this.fadingIn) {
/* 48 */       this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/* 49 */       if (this.fadeInTimer < 0.0F) {
/* 50 */         this.fadeInTimer = 0.0F;
/* 51 */         this.fadingIn = false;
/*    */       } 
/* 53 */       this.scale = Interpolation.swingIn.apply(this.targetScale, 0.01F, this.fadeInTimer / this.fadeInTime);
/*    */     } 
/*    */     
/* 56 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 57 */     this.vY -= Gdx.graphics.getDeltaTime() * this.decelerateY;
/* 58 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 59 */     this.rotation += this.vX * 2.0F * Gdx.graphics.getDeltaTime();
/*    */     
/* 61 */     if (this.vY < 0.0F) {
/* 62 */       this.vY = 0.0F;
/*    */     }
/*    */     
/* 65 */     if (this.killed) {
/* 66 */       this.color.a -= this.killSpeed * Gdx.graphics.getDeltaTime();
/* 67 */       if (this.color.a < 0.0F) {
/* 68 */         this.color.a = 0.0F;
/*    */       }
/* 70 */       this.scale += 5.0F * Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void kill() {
/* 75 */     this.killed = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 79 */     sb.setColor(this.color);
/* 80 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FastSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */