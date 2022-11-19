/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect;
/*    */ 
/*    */ public class FireballEffect extends AbstractGameEffect {
/*    */   private static final float FIREBALL_INTERVAL = 0.016F;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private float vfxTimer = 0.0F; private float startX; private float startY; private float targetX; private float targetY;
/*    */   
/*    */   public FireballEffect(float startX, float startY, float targetX, float targetY) {
/* 19 */     this.startingDuration = 0.5F;
/* 20 */     this.duration = 0.5F;
/* 21 */     this.startX = startX;
/* 22 */     this.startY = startY;
/* 23 */     this.targetX = targetX + MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 24 */     this.targetY = targetY + MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 25 */     this.x = startX;
/* 26 */     this.y = startY;
/*    */   }
/*    */   
/*    */   public void update() {
/* 30 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.duration / this.startingDuration);
/* 31 */     this.y = Interpolation.fade.apply(this.targetY, this.startY, this.duration / this.startingDuration);
/*    */     
/* 33 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 34 */     if (this.vfxTimer < 0.0F) {
/* 35 */       this.vfxTimer = 0.016F;
/* 36 */       AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.x, this.y, Color.CHARTREUSE));
/* 37 */       AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(this.x, this.y));
/*    */     } 
/*    */     
/* 40 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 41 */     if (this.duration < 0.0F) {
/* 42 */       this.isDone = true;
/* 43 */       AbstractDungeon.effectsQueue.add(new GhostIgniteEffect(this.x, this.y));
/* 44 */       AbstractDungeon.effectsQueue.add(new GhostlyWeakFireEffect(this.x, this.y));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\FireballEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */