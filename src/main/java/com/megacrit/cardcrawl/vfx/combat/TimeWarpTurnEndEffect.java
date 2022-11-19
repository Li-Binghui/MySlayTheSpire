/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class TimeWarpTurnEndEffect extends AbstractGameEffect {
/*    */   private float x;
/* 14 */   private static TextureAtlas.AtlasRegion img = null; private float y;
/*    */   
/*    */   public TimeWarpTurnEndEffect() {
/* 17 */     if (img == null) {
/* 18 */       img = AbstractPower.atlas.findRegion("128/time");
/*    */     }
/* 20 */     this.startingDuration = 2.0F;
/* 21 */     this.duration = this.startingDuration;
/* 22 */     this.scale = Settings.scale * 3.0F;
/* 23 */     this.x = Settings.WIDTH * 0.5F - img.packedWidth / 2.0F;
/* 24 */     this.y = img.packedHeight / 2.0F;
/* 25 */     this.color = Color.WHITE.cpy();
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 30 */     if (this.duration < 0.0F) {
/* 31 */       this.isDone = true;
/*    */     }
/*    */     
/* 34 */     if (this.duration < 1.0F) {
/* 35 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } else {
/* 37 */       this.y = Interpolation.swingIn.apply(Settings.HEIGHT * 0.7F - img.packedHeight / 2.0F, -img.packedHeight / 2.0F, this.duration - 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 46 */     sb.setColor(this.color);
/* 47 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.duration * 360.0F);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\TimeWarpTurnEndEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */