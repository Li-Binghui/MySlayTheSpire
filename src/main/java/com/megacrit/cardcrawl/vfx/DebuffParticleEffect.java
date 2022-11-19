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
/*    */ public class DebuffParticleEffect extends AbstractGameEffect {
/*    */   private Texture img;
/* 14 */   private static int IMG_NUM = 0;
/*    */   
/*    */   private boolean spinClockwise;
/* 17 */   private float scale = 0.0F; private float x; private float y;
/*    */   
/*    */   public DebuffParticleEffect(float x, float y) {
/* 20 */     this.x = x + MathUtils.random(-36.0F, 36.0F) * Settings.scale;
/* 21 */     this.y = y + MathUtils.random(-36.0F, 36.0F) * Settings.scale;
/* 22 */     this.duration = 4.0F;
/* 23 */     this.rotation = MathUtils.random(360.0F);
/* 24 */     this.spinClockwise = MathUtils.randomBoolean();
/*    */     
/* 26 */     if (IMG_NUM == 0) {
/* 27 */       this.renderBehind = true;
/* 28 */       this.img = ImageMaster.DEBUFF_VFX_1;
/* 29 */       IMG_NUM++;
/* 30 */     } else if (IMG_NUM == 1) {
/* 31 */       this.img = ImageMaster.DEBUFF_VFX_3;
/* 32 */       IMG_NUM++;
/*    */     } else {
/* 34 */       this.img = ImageMaster.DEBUFF_VFX_2;
/* 35 */       IMG_NUM = 0;
/*    */     } 
/*    */     
/* 38 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 45 */     if (this.spinClockwise) {
/* 46 */       this.rotation += Gdx.graphics.getDeltaTime() * 120.0F;
/*    */     } else {
/* 48 */       this.rotation -= Gdx.graphics.getDeltaTime() * 120.0F;
/*    */     } 
/*    */ 
/*    */     
/* 52 */     if (this.duration > 3.0F) {
/* 53 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, 1.0F - this.duration - 3.0F);
/* 54 */     } else if (this.duration <= 1.0F) {
/*    */ 
/*    */       
/* 57 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, 1.0F - this.duration);
/* 58 */       this.scale = Interpolation.fade.apply(Settings.scale, 0.0F, 1.0F - this.duration);
/*    */     } 
/*    */     
/* 61 */     if (this.duration > 2.0F) {
/* 62 */       this.scale = Interpolation.bounceOut.apply(0.0F, Settings.scale, 2.0F - this.duration - 2.0F);
/*    */     }
/*    */     
/* 65 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 66 */     if (this.duration < 0.0F) {
/* 67 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 73 */     sb.setColor(this.color);
/* 74 */     sb.draw(this.img, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale, this.rotation, 0, 0, 32, 32, false, false);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\DebuffParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */