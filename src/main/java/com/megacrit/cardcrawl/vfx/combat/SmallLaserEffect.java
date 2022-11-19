/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SmallLaserEffect extends AbstractGameEffect {
/*    */   private float sX;
/*    */   private float sY;
/*    */   private float dX;
/*    */   
/*    */   public SmallLaserEffect(float sX, float sY, float dX, float dY) {
/* 21 */     if (img == null) {
/* 22 */       img = ImageMaster.vfxAtlas.findRegion("combat/laserThin");
/*    */     }
/* 24 */     this.sX = sX;
/* 25 */     this.sY = sY;
/* 26 */     this.dX = dX;
/* 27 */     this.dY = dY;
/*    */     
/* 29 */     this.dst = Vector2.dst(this.sX, this.sY, this.dX, this.dY) / Settings.scale;
/* 30 */     this.color = Color.CYAN.cpy();
/* 31 */     this.duration = 0.5F;
/* 32 */     this.startingDuration = 0.5F;
/*    */     
/* 34 */     this.rotation = MathUtils.atan2(dX - sX, dY - sY);
/* 35 */     this.rotation *= 57.295776F;
/* 36 */     this.rotation = -this.rotation + 90.0F;
/*    */   }
/*    */   private float dY; private float dst; private static final float DUR = 0.5F; private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public void update() {
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 43 */     if (this.duration > this.startingDuration / 2.0F) {
/* 44 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 0.25F) * 4.0F);
/*    */     } else {
/* 46 */       this.color.a = Interpolation.bounceIn.apply(0.0F, 1.0F, this.duration * 4.0F);
/*    */     } 
/*    */     
/* 49 */     if (this.duration < 0.0F) {
/* 50 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     sb.setBlendFunction(770, 1);
/* 57 */     sb.setColor(this.color);
/* 58 */     sb.draw((TextureRegion)img, this.sX, this.sY - img.packedHeight / 2.0F + 10.0F * Settings.scale, 0.0F, img.packedHeight / 2.0F, this.dst, 50.0F, this.scale + 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 66 */         MathUtils.random(-0.01F, 0.01F), this.scale, this.rotation);
/*    */ 
/*    */ 
/*    */     
/* 70 */     sb.setColor(new Color(0.3F, 0.3F, 1.0F, this.color.a));
/* 71 */     sb.draw((TextureRegion)img, this.sX, this.sY - img.packedHeight / 2.0F, 0.0F, img.packedHeight / 2.0F, this.dst, 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 78 */         MathUtils.random(50.0F, 90.0F), this.scale + 
/* 79 */         MathUtils.random(-0.02F, 0.02F), this.scale, this.rotation);
/*    */ 
/*    */     
/* 82 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SmallLaserEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */