/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class EntangleEffect extends AbstractGameEffect {
/*    */   float x;
/*    */   float y;
/*    */   float dX;
/*    */   
/*    */   public EntangleEffect(float x, float y, float dX, float dY) {
/* 16 */     this.tX = x - 32.0F;
/* 17 */     this.tY = y - 32.0F;
/* 18 */     this.dX = dX - 32.0F;
/* 19 */     this.dY = dY - 32.0F;
/* 20 */     this.x = dX;
/* 21 */     this.y = dY;
/* 22 */     this.startingDuration = 1.0F;
/* 23 */     this.duration = this.startingDuration;
/*    */     
/* 25 */     this.scale = 0.01F;
/* 26 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 27 */     this.renderBehind = false;
/*    */   }
/*    */   float dY; float tX; float tY;
/*    */   public void update() {
/* 31 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 32 */     this.x = Interpolation.pow5In.apply(this.dX, this.tX, this.duration);
/* 33 */     this.y = Interpolation.pow5In.apply(this.dY, this.tY, this.duration);
/*    */     
/* 35 */     if (this.duration > this.startingDuration / 2.0F) {
/* 36 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 38 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 41 */     this.scale = Interpolation.bounceIn.apply(5.0F, 1.0F, this.duration) * Settings.scale;
/*    */     
/* 43 */     if (this.duration < 0.0F) {
/* 44 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 50 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.color.a));
/* 51 */     sb.setBlendFunction(770, 1);
/* 52 */     sb.draw(ImageMaster.WEB_VFX, this.x, this.y, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 53 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\EntangleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */