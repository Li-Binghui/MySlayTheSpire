/*    */ package com.megacrit.cardcrawl.vfx.scene;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
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
/*    */ public class WobblyCircleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 28 */   private Vector2 pos = new Vector2(
/* 29 */       MathUtils.random(0, Settings.WIDTH), 
/* 30 */       MathUtils.random(-100.0F, 500.0F) * Settings.scale + AbstractDungeon.floorY);
/* 31 */   private float vX = MathUtils.random(-72.0F, 72.0F) * Settings.scale;
/* 32 */   private float vY = MathUtils.random(-24.0F, 24.0F) * Settings.scale; private float aV;
/*    */   
/*    */   public WobblyCircleEffect() {
/* 35 */     float colorTmp = MathUtils.random(0.7F, 1.0F);
/* 36 */     this.color = new Color(MathUtils.random(0.7F, 0.8F), colorTmp, colorTmp, 0.0F);
/* 37 */     this.color.a = 0.0F;
/* 38 */     this.aV = MathUtils.random(0.2F, 0.4F);
/*    */   }
/*    */   private static final int W = 32;
/*    */   public void update() {
/* 42 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 43 */     this.pos.add(this.vX * Gdx.graphics.getDeltaTime(), this.vY * Gdx.graphics.getDeltaTime());
/*    */     
/* 45 */     float dst = this.pos.dst(InputHelper.mX, InputHelper.mY);
/* 46 */     if (dst < 200.0F * Settings.scale) {
/* 47 */       this.pos.add((this.pos.x - InputHelper.mX) * Gdx.graphics
/* 48 */           .getDeltaTime(), (this.pos.y - InputHelper.mY) * Gdx.graphics
/* 49 */           .getDeltaTime());
/*    */     }
/*    */     
/* 52 */     if (this.duration < 0.0F) {
/* 53 */       this.isDone = true;
/*    */     }
/*    */     
/* 56 */     if (this.duration > this.startingDuration / 2.0F) {
/* 57 */       float tmp = this.duration - this.startingDuration / 2.0F;
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration / 2.0F - tmp) * this.aV;
/*    */     } else {
/* 60 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.startingDuration / 2.0F) * this.aV;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 66 */     sb.setColor(this.color);
/* 67 */     sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.pos.x - 16.0F, this.pos.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale, this.scale, 0.0F, 0, 0, 32, 32, false, false);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\scene\WobblyCircleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */