/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class PingHpEffect
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public PingHpEffect(float x) {
/* 16 */     this.duration = 1.5F;
/* 17 */     this.color = new Color(1.0F, 1.0F, 0.2F, 0.0F);
/* 18 */     this.x = x;
/*    */   }
/*    */   
/*    */   public void update() {
/* 22 */     this.scale = Interpolation.pow5In.apply(1.15F, 1.0F, this.duration / 1.5F);
/* 23 */     this.color.a = Interpolation.pow2Out.apply(0.0F, 0.7F, this.duration / 1.5F);
/* 24 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 25 */     if (this.duration < 0.0F) {
/* 26 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 32 */     sb.setColor(this.color);
/* 33 */     sb.setBlendFunction(770, 1);
/* 34 */     sb.draw(ImageMaster.TP_HP, this.x - 32.0F + 32.0F * Settings.scale, Settings.HEIGHT - 32.0F * Settings.scale - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
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
/* 51 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\PingHpEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */