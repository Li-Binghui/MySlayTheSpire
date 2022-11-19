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
/*    */ public class FastDarkSmoke {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vX;
/* 15 */   private float scale = 0.01F; private float rotation; private float fadeInTime; private float fadeInTimer; private float targetScale;
/*    */   private boolean fadingIn = true;
/*    */   private Color color;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private boolean killed = false;
/*    */   private float killSpeed;
/*    */   
/*    */   public FastDarkSmoke(float x, float y) {
/* 23 */     this.targetScale = MathUtils.random(0.5F, 2.0F) * Settings.scale;
/* 24 */     this.fadeInTime = MathUtils.random(1.0F, 1.5F);
/* 25 */     this.fadeInTimer = this.fadeInTime;
/* 26 */     float darkness = MathUtils.random(0.0F, 0.1F);
/* 27 */     this.color = new Color(darkness + 0.1F + 0.05F, darkness + 0.1F, darkness + 0.05F, 1.0F);
/*    */     
/* 29 */     if (this.targetScale > 0.5F) {
/* 30 */       this.img = ImageMaster.EXHAUST_L;
/*    */     } else {
/* 32 */       this.img = ImageMaster.EXHAUST_S;
/* 33 */       this.vX /= 3.0F;
/*    */     } 
/*    */     
/* 36 */     this.x = x + MathUtils.random(-100.0F, 100.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 37 */     this.y = y + MathUtils.random(-75.0F, 75.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 38 */     this.rotation = MathUtils.random(360.0F);
/* 39 */     this.killSpeed = MathUtils.random(1.0F, 4.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 43 */     if (this.fadingIn) {
/* 44 */       this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/* 45 */       if (this.fadeInTimer < 0.0F) {
/* 46 */         this.fadeInTimer = 0.0F;
/* 47 */         this.fadingIn = false;
/*    */       } 
/* 49 */       this.scale = Interpolation.swingIn.apply(this.targetScale, 0.01F, this.fadeInTimer / this.fadeInTime);
/*    */     } 
/*    */     
/* 52 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 53 */     this.rotation += this.vX * 2.0F * Gdx.graphics.getDeltaTime();
/* 54 */     if (this.killed) {
/* 55 */       this.color.a -= this.killSpeed * Gdx.graphics.getDeltaTime();
/* 56 */       if (this.color.a < 0.0F) {
/* 57 */         this.color.a = 0.0F;
/*    */       }
/* 59 */       this.scale += 5.0F * Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void kill() {
/* 64 */     this.killed = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 68 */     sb.setColor(this.color);
/* 69 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\FastDarkSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */