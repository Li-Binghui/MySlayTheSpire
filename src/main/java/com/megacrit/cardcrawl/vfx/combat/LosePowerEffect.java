/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class LosePowerEffect extends AbstractGameEffect {
/*    */   private static final float EFFECT_DUR = 1.0F;
/*    */   private float x;
/*    */   private float y;
/* 14 */   private static final float IMG_WIDTH = 64.0F * Settings.scale; private float shake_offset; private float offset_y; private Texture img; private float shakeVelo;
/*    */   private float popVelo;
/* 16 */   private static final float POP_VELO_START = 150.0F * Settings.scale;
/* 17 */   private static final float SHAKE_VELO_START = 200.0F * Settings.scale;
/* 18 */   private static final float SHAKE_DIST = 16.0F * Settings.scale;
/* 19 */   private static final float FALL_SPEED = 8.0F * Settings.scale;
/* 20 */   private static final float SHAKE_TAPER_SPEED = 30.0F * Settings.scale;
/*    */   private boolean shakeLeft = true;
/*    */   
/*    */   public LosePowerEffect(float x, float y, Texture img) {
/* 24 */     this.img = img;
/* 25 */     this.duration = 1.0F;
/* 26 */     this.startingDuration = 1.0F;
/* 27 */     this.x = x;
/* 28 */     this.y = y;
/* 29 */     this.shake_offset = 0.0F;
/* 30 */     this.offset_y = 0.0F;
/* 31 */     this.shakeVelo = SHAKE_VELO_START;
/* 32 */     this.popVelo = POP_VELO_START;
/*    */     
/* 34 */     this.color = Color.WHITE.cpy();
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     if (this.duration == this.startingDuration);
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.offset_y += this.popVelo * Gdx.graphics.getDeltaTime();
/* 43 */     this.popVelo -= FALL_SPEED;
/* 44 */     this.shakeVelo -= Gdx.graphics.getDeltaTime() * SHAKE_TAPER_SPEED;
/* 45 */     if (this.shakeLeft) {
/* 46 */       this.shake_offset -= this.shakeVelo * Gdx.graphics.getDeltaTime();
/* 47 */       if (this.shake_offset < -SHAKE_DIST) {
/* 48 */         this.shakeLeft = !this.shakeLeft;
/*    */       }
/*    */     } else {
/* 51 */       this.shake_offset += this.shakeVelo * Gdx.graphics.getDeltaTime();
/* 52 */       if (this.shake_offset > SHAKE_DIST) {
/* 53 */         this.shakeLeft = !this.shakeLeft;
/*    */       }
/*    */     } 
/*    */     
/* 57 */     if (this.color.g > 0.3F) {
/* 58 */       this.color.g -= Gdx.graphics.getDeltaTime();
/* 59 */       this.color.b -= Gdx.graphics.getDeltaTime();
/*    */     } 
/*    */     
/* 62 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 64 */     if (this.duration > 1.0F) {
/* 65 */       this.color.a = (1.5F - this.duration) * 2.0F;
/* 66 */     } else if (this.duration < 1.0F && this.duration > 0.33F) {
/* 67 */       this.color.a = 1.0F;
/* 68 */     } else if (this.duration < 0.33F && this.duration > 0.0F) {
/* 69 */       this.color.a = this.duration * 3.0F;
/* 70 */     } else if (this.duration < 0.0F) {
/* 71 */       this.isDone = true;
/* 72 */       this.color.a = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 78 */     if (!this.isDone) {
/* 79 */       sb.setColor(this.color);
/* 80 */       sb.draw(this.img, this.x - IMG_WIDTH * 0.25F + this.shake_offset, this.y - IMG_WIDTH * 0.25F + this.offset_y, IMG_WIDTH * 1.5F, IMG_WIDTH * 1.5F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\LosePowerEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */