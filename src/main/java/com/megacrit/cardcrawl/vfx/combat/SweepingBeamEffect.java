/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SweepingBeamEffect extends AbstractGameEffect {
/*    */   private float sX;
/*    */   private float sY;
/*    */   private float dX;
/* 19 */   private final float DUR = 0.5F; private float dY; private float dst; private boolean isFlipped = false;
/*    */   private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public SweepingBeamEffect(float sX, float sY, boolean isFlipped) {
/* 23 */     if (img == null) {
/* 24 */       img = ImageMaster.vfxAtlas.findRegion("combat/laserThin");
/*    */     }
/*    */     
/* 27 */     this.isFlipped = isFlipped;
/*    */     
/* 29 */     if (isFlipped) {
/* 30 */       this.sX = sX - 32.0F * Settings.scale;
/* 31 */       this.sY = sY + 20.0F * Settings.scale;
/*    */     } else {
/* 33 */       this.sX = sX + 40.0F * Settings.scale;
/* 34 */       this.sY = sY + 50.0F * Settings.scale;
/*    */     } 
/*    */     
/* 37 */     this.color = Color.CYAN.cpy();
/* 38 */     this.duration = 0.5F;
/* 39 */     this.startingDuration = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 46 */     if (this.isFlipped) {
/* 47 */       this.dX = Settings.WIDTH / 2.0F * Interpolation.pow3Out.apply(this.duration);
/* 48 */       this.dY = AbstractDungeon.floorY + 10.0F * Settings.scale;
/*    */     } else {
/* 50 */       this.dX = Settings.WIDTH + -Settings.WIDTH / 2.0F * Interpolation.pow3Out.apply(this.duration);
/* 51 */       this.dY = AbstractDungeon.floorY + 30.0F * Settings.scale;
/*    */     } 
/*    */     
/* 54 */     this.dst = Vector2.dst(this.sX, this.sY, this.dX, this.dY) / Settings.scale;
/* 55 */     this.rotation = MathUtils.atan2(this.dX - this.sX, this.dY - this.sY);
/* 56 */     this.rotation *= 57.295776F;
/* 57 */     this.rotation = -this.rotation + 90.0F;
/*    */     
/* 59 */     if (this.duration > this.startingDuration / 2.0F) {
/* 60 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 0.25F) * 4.0F);
/*    */     } else {
/* 62 */       this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration * 4.0F);
/*    */     } 
/*    */     
/* 65 */     if (this.duration < 0.0F) {
/* 66 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 72 */     sb.setBlendFunction(770, 1);
/* 73 */     sb.setColor(this.color);
/* 74 */     sb.draw((TextureRegion)img, this.sX, this.sY - img.packedHeight / 2.0F + 10.0F * Settings.scale, 0.0F, img.packedHeight / 2.0F, this.dst, 50.0F, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 82 */         MathUtils.random(-0.01F, 0.01F), this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */     
/* 86 */     sb.setColor(new Color(0.3F, 0.3F, 1.0F, this.color.a));
/* 87 */     sb.draw((TextureRegion)img, this.sX, this.sY - img.packedHeight / 2.0F, 0.0F, img.packedHeight / 2.0F, this.dst, 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 94 */         MathUtils.random(50.0F, 90.0F), this.scale + 
/* 95 */         MathUtils.random(-0.02F, 0.02F), this.scale, this.rotation);
/*    */ 
/*    */     
/* 98 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SweepingBeamEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */