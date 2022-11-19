/*    */ package com.megacrit.cardcrawl.vfx.stance;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CalmParticleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 20 */   private float dur_div2 = this.duration / 2.0F;
/*    */   
/* 22 */   private float vX = MathUtils.random(-300.0F, -50.0F) * Settings.scale;
/* 23 */   private float vY = MathUtils.random(-200.0F, -100.0F) * Settings.scale;
/* 24 */   private float x = AbstractDungeon.player.hb.cX + MathUtils.random(100.0F, 160.0F) * Settings.scale - 32.0F;
/* 25 */   private float y = AbstractDungeon.player.hb.cY + MathUtils.random(-50.0F, 220.0F) * Settings.scale - 32.0F;
/*    */   
/* 27 */   private float dvx = 400.0F * Settings.scale * this.scale;
/* 28 */   private float dvy = 100.0F * Settings.scale;
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 33 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 34 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 35 */     this.vY += Gdx.graphics.getDeltaTime() * this.dvy;
/* 36 */     this.vX -= Gdx.graphics.getDeltaTime() * this.dvx;
/* 37 */     this.rotation = -(57.295776F * MathUtils.atan2(this.vX, this.vY)) - 0.0F;
/*    */     
/* 39 */     if (this.duration > this.dur_div2) {
/* 40 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 42 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*    */     } 
/*    */     
/* 45 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 46 */     if (this.duration < 0.0F) {
/* 47 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     sb.setColor(this.color);
/* 54 */     sb.setBlendFunction(770, 1);
/* 55 */     sb.draw(ImageMaster.FROST_ACTIVATE_VFX_1, this.x, this.y, 32.0F, 32.0F, 25.0F, 128.0F, this.scale, this.scale + (this.dur_div2 * 0.4F - this.duration) * Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
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


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\stance\CalmParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */