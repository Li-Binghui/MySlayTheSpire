/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShinySparkleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 24 */   private float x = MathUtils.random(0, Settings.WIDTH);
/* 25 */   private float y = MathUtils.random(-100.0F, 550.0F) * Settings.scale + AbstractDungeon.floorY;
/* 26 */   private float vX = MathUtils.random(-24.0F, 24.0F) * Settings.scale;
/* 27 */   private float vY = MathUtils.random(-24.0F, 36.0F) * Settings.scale; private float aV;
/*    */   
/*    */   public ShinySparkleEffect() {
/* 30 */     float colorTmp = MathUtils.random(0.6F, 1.0F);
/* 31 */     this.color = new Color(colorTmp - 0.3F, colorTmp, colorTmp + MathUtils.random(-0.1F, 0.1F), 0.0F);
/* 32 */     this.color.a = 0.0F;
/* 33 */     this.aV = MathUtils.random(-120.0F, 120.0F);
/*    */   }
/*    */   private static final int W = 32;
/*    */   public void update() {
/* 37 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 40 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/*    */     
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */     
/* 46 */     if (this.duration > this.startingDuration / 2.0F) {
/* 47 */       float tmp = this.duration - this.startingDuration / 2.0F;
/* 48 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration / 2.0F - tmp) / 4.0F;
/*    */     } else {
/* 50 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration / 2.0F) / 4.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     sb.setColor(this.color);
/* 57 */     sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 65 */         MathUtils.random(1.0F, 1.2F), this.scale / 4.0F, 0.0F, 0, 0, 32, 32, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 74 */     sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 82 */         MathUtils.random(1.0F, 1.5F), this.scale / 4.0F, 90.0F, 0, 0, 32, 32, false, false);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\ShinySparkleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */