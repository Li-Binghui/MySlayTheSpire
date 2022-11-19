/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class DarkOrbActivateParticle
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float scaleY;
/*    */   
/*    */   public DarkOrbActivateParticle(float x, float y) {
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.renderBehind = true;
/* 22 */     this.duration = 0.25F;
/* 23 */     this.startingDuration = 0.25F;
/* 24 */     this.color = new Color(MathUtils.random(0.5F, 1.0F), MathUtils.random(0.6F, 1.0F), 1.0F, 0.5F);
/* 25 */     this.scale = MathUtils.random(1.0F, 2.0F) * Settings.scale;
/* 26 */     this.rotation = MathUtils.random(-8.0F, 8.0F);
/* 27 */     this.flipHorizontal = MathUtils.randomBoolean();
/* 28 */     this.flipVertical = MathUtils.randomBoolean();
/* 29 */     this.scale = Settings.scale;
/* 30 */     this.scaleY = 2.0F * Settings.scale;
/* 31 */     this.aV = MathUtils.random(-100.0F, 100.0F);
/*    */   }
/*    */   private float aV; private static final int W = 140; private boolean flipHorizontal; private boolean flipVertical;
/*    */   
/*    */   public void update() {
/* 36 */     this.rotation += Gdx.graphics.getDeltaTime() * this.aV;
/* 37 */     this.scale = Interpolation.pow4Out.apply(5.0F, 1.0F, this.duration * 4.0F) * Settings.scale;
/* 38 */     this.scaleY = Interpolation.bounceOut.apply(0.2F, 2.0F, this.duration * 4.0F) * Settings.scale;
/* 39 */     this.color.a = Interpolation.pow5Out.apply(0.01F, 0.5F, this.duration * 4.0F);
/* 40 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 41 */     if (this.duration < 0.0F) {
/* 42 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     sb.setColor(this.color);
/* 49 */     sb.setBlendFunction(770, 1);
/* 50 */     sb.draw(ImageMaster.DARK_ORB_ACTIVATE_VFX, this.x - 70.0F, this.y - 70.0F, 70.0F, 70.0F, 140.0F, 140.0F, this.scale, this.scaleY, this.rotation, 0, 0, 140, 140, this.flipHorizontal, this.flipVertical);
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
/* 67 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DarkOrbActivateParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */