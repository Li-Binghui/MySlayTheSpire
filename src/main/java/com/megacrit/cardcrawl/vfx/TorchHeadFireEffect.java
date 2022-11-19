/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class TorchHeadFireEffect
/*    */   extends AbstractGameEffect {
/*    */   private Texture img;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private boolean flippedX = MathUtils.randomBoolean(); private float vX; private float vY; private static final int W = 128;
/*    */   private static final float DUR = 0.7F;
/*    */   
/*    */   public TorchHeadFireEffect(float x, float y) {
/* 20 */     this.img = getImg();
/* 21 */     this.x = x + MathUtils.random(-5.0F, 5.0F) * Settings.scale;
/* 22 */     this.y = y + MathUtils.random(-5.0F, 5.0F) * Settings.scale;
/* 23 */     this.vX = MathUtils.random(-30.0F, 30.0F) * Settings.scale;
/* 24 */     this.vY = MathUtils.random(20.0F, 100.0F) * Settings.scale;
/* 25 */     this.duration = 0.7F;
/* 26 */     this.color = Color.CHARTREUSE.cpy();
/* 27 */     this.color.a = 0.0F;
/* 28 */     this.scale = MathUtils.random(0.8F, 0.9F) * Settings.scale;
/* 29 */     this.renderBehind = MathUtils.randomBoolean();
/*    */   }
/*    */   
/*    */   private Texture getImg() {
/* 33 */     if (MathUtils.randomBoolean()) {
/* 34 */       return ImageMaster.GHOST_ORB_1;
/*    */     }
/* 36 */     return ImageMaster.GHOST_ORB_2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 42 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 43 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 45 */     if (this.duration < 0.0F) {
/* 46 */       this.isDone = true;
/*    */     }
/* 48 */     this.color.a = this.duration / 2.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     sb.setBlendFunction(770, 1);
/* 54 */     sb.setColor(this.color);
/* 55 */     sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale * 1.2F, this.scale, 0.0F, 0, 0, 128, 128, this.flippedX, false);
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
/* 72 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\TorchHeadFireEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */