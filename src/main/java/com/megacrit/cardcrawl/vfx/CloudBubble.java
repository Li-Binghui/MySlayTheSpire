/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class CloudBubble
/*    */ {
/*    */   private static final int RAW_W = 128;
/*    */   private float x;
/* 16 */   private float scale = 0.01F; private float y; private float fadeInTime; private float fadeInTimer; private float targetScale;
/*    */   private boolean fadingIn = true;
/*    */   private FloatyEffect f_effect;
/*    */   private Color color;
/*    */   private Texture img;
/*    */   private boolean killed = false;
/*    */   private float killSpeed;
/*    */   
/*    */   public CloudBubble(float x, float y, float targetScale) {
/* 25 */     this.x = x;
/* 26 */     this.y = y;
/* 27 */     this.targetScale = targetScale * Settings.scale;
/* 28 */     this.fadeInTime = MathUtils.random(0.7F, 2.5F);
/* 29 */     this.fadeInTimer = this.fadeInTime;
/* 30 */     this.f_effect = new FloatyEffect(this.targetScale * 3.0F, 1.0F);
/* 31 */     float darkness = MathUtils.random(0.8F, 0.9F);
/* 32 */     this.color = new Color(darkness, darkness - 0.04F, darkness - 0.05F, 1.0F);
/*    */     
/* 34 */     if (targetScale > 0.5F) {
/* 35 */       this.img = ImageMaster.LARGE_CLOUD;
/*    */     } else {
/* 37 */       this.img = ImageMaster.SMALL_CLOUD;
/* 38 */       this.targetScale *= 3.0F;
/*    */     } 
/* 40 */     this.killSpeed = MathUtils.random(8.0F, 24.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 44 */     this.f_effect.update();
/* 45 */     if (this.fadingIn) {
/* 46 */       this.fadeInTimer -= Gdx.graphics.getDeltaTime();
/* 47 */       if (this.fadeInTimer < 0.0F) {
/* 48 */         this.fadeInTimer = 0.0F;
/* 49 */         this.fadingIn = false;
/*    */       } 
/* 51 */       this.scale = Interpolation.swingIn.apply(this.targetScale, 0.0F, this.fadeInTimer / this.fadeInTime);
/*    */     } 
/*    */     
/* 54 */     if (this.killed) {
/* 55 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * this.killSpeed);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void kill() {
/* 62 */     this.killed = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw(this.img, this.x - 64.0F + this.f_effect.x, this.y - 64.0F + this.f_effect.y, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, 0.0F, 0, 0, 128, 128, false, false);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\CloudBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */