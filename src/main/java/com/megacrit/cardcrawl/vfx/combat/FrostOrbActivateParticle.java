/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class FrostOrbActivateParticle extends AbstractGameEffect {
/*    */   public static final int W = 64;
/* 15 */   private Texture img = null; private float cX;
/*    */   private float cY;
/*    */   private float sX;
/*    */   
/*    */   public FrostOrbActivateParticle(int index, float x, float y) {
/* 20 */     this.cX = x;
/* 21 */     this.cY = y;
/* 22 */     this.sX = this.cX;
/* 23 */     this.sY = this.cY;
/*    */     
/* 25 */     this.rotation = MathUtils.random(-5.0F, 5.0F);
/* 26 */     switch (index) {
/*    */       case 0:
/* 28 */         this.tX = this.sX;
/* 29 */         this.tY = this.sY + 5.0F * Settings.scale;
/* 30 */         this.img = ImageMaster.FROST_ACTIVATE_VFX_1;
/* 31 */         this.tRotation = MathUtils.random(-5.0F, 5.0F);
/*    */         break;
/*    */       case 1:
/* 34 */         this.tX = this.sX - 10.0F * Settings.scale;
/* 35 */         this.tY = this.sY - 5.0F * Settings.scale;
/* 36 */         this.img = ImageMaster.FROST_ACTIVATE_VFX_2;
/* 37 */         this.tRotation = this.rotation + MathUtils.random(-30.0F, 30.0F);
/*    */         break;
/*    */       default:
/* 40 */         this.tX = this.sX + 10.0F * Settings.scale;
/* 41 */         this.tY = this.sY - 5.0F * Settings.scale;
/* 42 */         this.tRotation = this.rotation - MathUtils.random(-30.0F, 30.0F);
/* 43 */         this.img = ImageMaster.FROST_ACTIVATE_VFX_3;
/*    */         break;
/*    */     } 
/*    */     
/* 47 */     this.renderBehind = false;
/* 48 */     this.color = new Color(0.5F, 0.95F, 1.0F, 0.9F);
/* 49 */     this.scale = 2.0F * Settings.scale;
/* 50 */     this.startingDuration = 0.3F;
/* 51 */     this.duration = this.startingDuration;
/*    */   }
/*    */   private float sY;
/*    */   private float tX;
/*    */   
/*    */   public void update() {
/* 57 */     this.color.a = Interpolation.pow2Out.apply(0.2F, 0.9F, this.duration / this.startingDuration);
/* 58 */     if (this.color.a < 0.0F) {
/* 59 */       this.color.a = 0.0F;
/*    */     }
/*    */     
/* 62 */     this.cX = Interpolation.swingIn.apply(this.tX, this.sX, this.duration / this.startingDuration);
/* 63 */     this.cY = Interpolation.swingIn.apply(this.tY, this.sY, this.duration / this.startingDuration);
/* 64 */     this.rotation = Interpolation.swingIn.apply(this.tRotation, 0.0F, this.duration / this.startingDuration);
/* 65 */     this.scale = Interpolation.fade.apply(2.4F, 2.0F, this.duration / this.startingDuration) * Settings.scale;
/*    */     
/* 67 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 68 */     if (this.duration < 0.0F)
/* 69 */       this.isDone = true; 
/*    */   }
/*    */   private float tY;
/*    */   private float tRotation;
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 75 */     sb.setColor(this.color);
/* 76 */     sb.setBlendFunction(770, 1);
/* 77 */     sb.draw(this.img, this.cX - 32.0F, this.cY - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, this.rotation, 0, 0, 64, 64, false, false);
/* 78 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FrostOrbActivateParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */