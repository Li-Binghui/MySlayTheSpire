/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BorderLongFlashEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float DUR = 2.0F;
/*    */   private float startAlpha;
/*    */   private boolean additive = true;
/*    */   
/*    */   public BorderLongFlashEffect(Color color) {
/* 23 */     this(color, true);
/*    */   }
/*    */   
/*    */   public BorderLongFlashEffect(Color color, boolean additive) {
/* 27 */     this.duration = 2.0F;
/* 28 */     this.startAlpha = color.a;
/* 29 */     this.color = color.cpy();
/* 30 */     this.color.a = 0.0F;
/* 31 */     this.additive = additive;
/*    */   }
/*    */   
/*    */   public void update() {
/* 35 */     if (2.0F - this.duration < 0.2F) {
/* 36 */       this.color.a = Interpolation.fade.apply(0.0F, this.startAlpha, (2.0F - this.duration) * 10.0F);
/*    */     } else {
/* 38 */       this.color.a = Interpolation.pow2Out.apply(0.0F, this.startAlpha, this.duration / 2.0F);
/*    */     } 
/*    */     
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 49 */     if (this.additive) {
/* 50 */       sb.setBlendFunction(770, 1);
/*    */     }
/*    */     
/* 53 */     sb.setColor(this.color);
/* 54 */     sb.draw((TextureRegion)ImageMaster.BORDER_GLOW_2, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */     
/* 56 */     if (this.additive)
/* 57 */       sb.setBlendFunction(770, 771); 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\BorderLongFlashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */