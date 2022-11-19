/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class WarningSignEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public WarningSignEffect(float x, float y) {
/* 15 */     this.duration = 1.0F;
/* 16 */     this.color = Color.SCARLET.cpy();
/* 17 */     this.color.a = 0.0F;
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/*    */   }
/*    */   
/*    */   public void update() {
/* 23 */     if (1.0F - this.duration < 0.1F) {
/* 24 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 26 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
/*    */     } 
/*    */     
/* 29 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 30 */     if (this.duration < 0.0F) {
/* 31 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 37 */     sb.setBlendFunction(770, 1);
/* 38 */     sb.setColor(this.color);
/* 39 */     sb.draw(ImageMaster.WARNING_ICON_VFX, this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, Settings.scale * 2.0F, Settings.scale * 2.0F, 0.0F, 0, 0, 64, 64, false, false);
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
/* 56 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\WarningSignEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */