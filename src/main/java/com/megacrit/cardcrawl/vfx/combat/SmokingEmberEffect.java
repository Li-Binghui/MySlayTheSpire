/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.FastSmokeParticle;
/*    */ 
/*    */ public class SmokingEmberEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float vX;
/*    */   private float y;
/* 14 */   private float smokeTimer = 0.0F; private float vY; private float gravity; private static final float INTERVAL = 0.01F;
/*    */   
/*    */   public SmokingEmberEffect(float x, float y) {
/* 17 */     this.x = x;
/* 18 */     this.y = y;
/* 19 */     this.vX = MathUtils.random(-600.0F, 600.0F) * Settings.scale;
/* 20 */     this.vY = MathUtils.random(-200.0F, 600.0F) * Settings.scale;
/* 21 */     this.gravity = 800.0F * Settings.scale;
/*    */     
/* 23 */     this.scale = MathUtils.random(0.2F, 0.4F) * Settings.scale;
/* 24 */     this.duration = MathUtils.random(0.3F, 0.6F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 29 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 30 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 31 */     this.vY -= this.gravity * Gdx.graphics.getDeltaTime();
/*    */     
/* 33 */     this.smokeTimer -= Gdx.graphics.getDeltaTime();
/* 34 */     if (this.smokeTimer < 0.0F) {
/* 35 */       this.smokeTimer = 0.01F;
/* 36 */       AbstractDungeon.effectsQueue.add(new FastSmokeParticle(this.x, this.y));
/*    */     } 
/*    */     
/* 39 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 41 */     if (this.duration < 0.0F)
/* 42 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SmokingEmberEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */