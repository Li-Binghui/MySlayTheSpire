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
/*    */ public class RoomTintEffect extends AbstractGameEffect {
/*    */   private static final float DEFAULT_DUR = 2.0F;
/*    */   private float tintTransparency;
/*    */   
/*    */   public RoomTintEffect(Color color, float tintTransparency) {
/* 16 */     this(color, tintTransparency, 2.0F, true);
/*    */   }
/*    */   
/*    */   public RoomTintEffect(Color color, float tintTransparency, float setDuration, boolean renderBehind) {
/* 20 */     this.renderBehind = renderBehind;
/* 21 */     this.startingDuration = setDuration;
/* 22 */     this.duration = setDuration;
/* 23 */     this.color = color;
/* 24 */     this.color.a = 0.0F;
/* 25 */     this.tintTransparency = tintTransparency;
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     if (this.duration > this.startingDuration * 0.5F) {
/* 30 */       this.color.a = Interpolation.fade.apply(this.tintTransparency, 0.0F, (this.duration - this.startingDuration * 0.5F) / this.startingDuration);
/*    */ 
/*    */     
/*    */     }
/* 34 */     else if (this.duration < this.startingDuration * 0.5F) {
/* 35 */       this.color.a = Interpolation.fade.apply(0.0F, this.tintTransparency, this.duration / this.startingDuration / 0.5F);
/*    */     } 
/*    */     
/* 38 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     sb.setColor(this.color);
/* 47 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\RoomTintEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */