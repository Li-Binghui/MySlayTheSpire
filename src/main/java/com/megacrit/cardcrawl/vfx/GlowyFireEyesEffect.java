/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class GlowyFireEyesEffect
/*    */   extends AbstractGameEffect {
/*    */   private Texture img;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private boolean flippedX = MathUtils.randomBoolean(); private float vX; private float vY; private static final int W = 128;
/*    */   private static final float DUR = 1.0F;
/*    */   
/*    */   public GlowyFireEyesEffect(float x, float y) {
/* 20 */     this.img = getImg();
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/* 23 */     this.vX = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 24 */     this.vY = MathUtils.random(30.0F, 90.0F) * Settings.scale;
/* 25 */     this.duration = 1.0F;
/* 26 */     this.color = Color.CHARTREUSE.cpy();
/* 27 */     this.color.a = 0.0F;
/* 28 */     this.scale = MathUtils.random(0.45F, 0.45F) * Settings.scale;
/*    */   }
/*    */   
/*    */   private Texture getImg() {
/* 32 */     if (MathUtils.randomBoolean()) {
/* 33 */       return ImageMaster.GHOST_ORB_1;
/*    */     }
/* 35 */     return ImageMaster.GHOST_ORB_2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 41 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 42 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 43 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 44 */     if (this.duration < 0.0F) {
/* 45 */       this.isDone = true;
/*    */     }
/* 47 */     this.color.a = this.duration / 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 52 */     sb.setBlendFunction(770, 1);
/* 53 */     sb.setColor(this.color);
/* 54 */     sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, 0.0F, 0, 0, 128, 128, this.flippedX, false);
/* 55 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GlowyFireEyesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */