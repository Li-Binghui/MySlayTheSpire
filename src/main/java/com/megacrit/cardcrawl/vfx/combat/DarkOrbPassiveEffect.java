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
/*    */ public class DarkOrbPassiveEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float startingScale;
/*    */   
/*    */   public DarkOrbPassiveEffect(float x, float y) {
/* 20 */     int roll = MathUtils.random(2);
/* 21 */     switch (roll) {
/*    */       case 0:
/* 23 */         this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_1;
/*    */         break;
/*    */       case 1:
/* 26 */         this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_2;
/*    */         break;
/*    */       default:
/* 29 */         this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_3;
/*    */         break;
/*    */     } 
/*    */     
/* 33 */     this.color = new Color(MathUtils.random(0.0F, 1.0F), 0.3F, MathUtils.random(0.7F, 1.0F), 0.01F);
/* 34 */     this.renderBehind = false;
/* 35 */     this.duration = 2.0F;
/* 36 */     this.startingDuration = 2.0F;
/* 37 */     this.x = x;
/* 38 */     this.y = y;
/* 39 */     this.rotation = MathUtils.random(360.0F);
/* 40 */     this.startingScale = MathUtils.random(1.2F, 1.8F) * Settings.scale;
/* 41 */     this.scale = this.startingScale;
/* 42 */     this.rotationSpeed = MathUtils.random(100.0F, 360.0F);
/*    */   }
/*    */   private float rotationSpeed; private Texture img; private static final int W = 74;
/*    */   public void update() {
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 47 */     this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
/*    */     
/* 49 */     if (this.duration > 1.0F) {
/* 50 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration - 1.0F);
/*    */     } else {
/* 52 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } 
/*    */     
/* 55 */     this.scale = Interpolation.swingOut.apply(0.01F, this.startingScale, this.duration / 2.0F) * Settings.scale;
/*    */     
/* 57 */     if (this.scale < 0.0F || this.duration < 0.0F) {
/* 58 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     sb.draw(this.img, this.x - 37.0F, this.y - 37.0F, 37.0F, 37.0F, 74.0F, 74.0F, this.scale, this.scale, this.rotation, 0, 0, 74, 74, false, false);
/* 67 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\DarkOrbPassiveEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */