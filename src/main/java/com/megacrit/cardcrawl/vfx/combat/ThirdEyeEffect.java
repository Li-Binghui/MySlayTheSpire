/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class ThirdEyeEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   
/*    */   public ThirdEyeEffect(float x, float y) {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/*    */   }
/*    */   private float y;
/*    */   public void update() {
/* 16 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 800.0F, 0.0F));
/* 17 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, -800.0F, 0.0F));
/* 18 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 0.0F, 500.0F));
/* 19 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 0.0F, -500.0F));
/* 20 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 600.0F, 0.0F));
/* 21 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, -600.0F, 0.0F));
/* 22 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 0.0F, 400.0F));
/* 23 */     AbstractDungeon.effectsQueue.add(new ThirdEyeParticleEffect(this.x, this.y, 0.0F, -400.0F));
/* 24 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\ThirdEyeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */