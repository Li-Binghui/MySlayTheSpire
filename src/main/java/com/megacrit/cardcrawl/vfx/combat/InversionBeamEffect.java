/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class InversionBeamEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public InversionBeamEffect(float x) {
/* 16 */     this.startingDuration = 0.5F;
/* 17 */     this.duration = this.startingDuration;
/* 18 */     this.x = x;
/* 19 */     this.y = 0.01F;
/* 20 */     this.renderBehind = MathUtils.randomBoolean();
/*    */   }
/*    */   
/*    */   public void update() {
/* 24 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 25 */     if (this.duration < 0.0F) {
/* 26 */       this.isDone = true;
/* 27 */     } else if (this.duration < this.startingDuration / 2.0F) {
/* 28 */       this.y = Interpolation.fade.apply(0.01F, 50.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 30 */       this.y = Interpolation.fade.apply(50.0F, 0.01F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */ 
/*    */     
/* 34 */     this.scale = Interpolation.bounce.apply(0.01F, 5.0F, this.duration / this.startingDuration);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 39 */     sb.setBlendFunction(775, 769);
/* 40 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, this.x - this.y / 2.0F, 0.0F, this.y, Settings.HEIGHT - 64.0F * Settings.scale);
/* 41 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\InversionBeamEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */