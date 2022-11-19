/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ShineLinesEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 0.25F;
/*    */   private float x;
/*    */   private float y;
/*    */   private static final float SCALE_INCREASE_RATE = 8.0F;
/* 17 */   private TextureAtlas.AtlasRegion img = ImageMaster.GRAB_COIN;
/*    */   
/*    */   public ShineLinesEffect(float x, float y) {
/* 20 */     this.duration = 0.25F;
/* 21 */     this.startingDuration = 0.25F;
/*    */     
/* 23 */     this.x = x - this.img.packedWidth / 2.0F;
/* 24 */     this.y = y - this.img.packedHeight / 2.0F;
/* 25 */     this.rotation = MathUtils.random(0.0F, 360.0F);
/* 26 */     this.color = Color.WHITE.cpy();
/* 27 */     this.scale = 0.0F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 31 */     super.update();
/* 32 */     this.scale += Gdx.graphics.getDeltaTime() * 8.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 37 */     if (!this.isDone) {
/* 38 */       sb.setColor(this.color);
/* 39 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ShineLinesEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */