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
/*    */ 
/*    */ 
/*    */ public class RefreshEnergyEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float EFFECT_DUR = 0.4F;
/* 18 */   private float scale = Settings.scale / 1.2F;
/* 19 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public RefreshEnergyEffect() {
/* 24 */     this.img = ImageMaster.WHITE_RING;
/* 25 */     this.x = 198.0F * Settings.scale - this.img.packedWidth / 2.0F;
/* 26 */     this.y = 190.0F * Settings.scale - this.img.packedHeight / 2.0F;
/* 27 */     this.duration = 0.4F;
/*    */   }
/*    */   private float x; private float y;
/*    */   
/*    */   public void update() {
/* 32 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 33 */     this.scale *= 1.0F + Gdx.graphics.getDeltaTime() * 2.5F;
/* 34 */     this.color.a = Interpolation.fade.apply(0.0F, 0.75F, this.duration / 0.4F);
/* 35 */     if (this.color.a < 0.0F) {
/* 36 */       this.color.a = 0.0F;
/*    */     }
/*    */     
/* 39 */     if (this.duration < 0.0F) {
/* 40 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     sb.setColor(this.color);
/* 47 */     sb.setBlendFunction(770, 1);
/* 48 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.5F, this.scale * 1.5F, this.rotation);
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
/* 59 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\RefreshEnergyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */