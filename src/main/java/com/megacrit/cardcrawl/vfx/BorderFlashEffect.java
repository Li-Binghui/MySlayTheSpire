/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class BorderFlashEffect extends AbstractGameEffect {
/* 13 */   private TextureAtlas.AtlasRegion img = ImageMaster.BORDER_GLOW_2;
/*    */ 
/*    */   
/*    */   private static final float DUR = 1.0F;
/*    */ 
/*    */   
/*    */   private boolean additive;
/*    */ 
/*    */ 
/*    */   
/*    */   public BorderFlashEffect(Color color) {
/* 24 */     this(color, true);
/*    */   }
/*    */   
/*    */   public BorderFlashEffect(Color color, boolean additive) {
/* 28 */     this.duration = 1.0F;
/* 29 */     this.color = color.cpy();
/* 30 */     this.color.a = 0.0F;
/* 31 */     this.additive = additive;
/*    */   }
/*    */   
/*    */   public void update() {
/* 35 */     if (1.0F - this.duration < 0.1F) {
/* 36 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
/*    */     } else {
/* 38 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
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
/* 51 */       sb.setColor(this.color);
/* 52 */       sb.draw((TextureRegion)this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/* 53 */       sb.setBlendFunction(770, 771);
/*    */     } else {
/* 55 */       sb.setColor(this.color);
/* 56 */       sb.draw((TextureRegion)this.img, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\BorderFlashEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */