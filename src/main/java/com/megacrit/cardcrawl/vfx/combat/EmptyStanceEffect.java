/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ 
/*    */ public class EmptyStanceEffect extends AbstractGameEffect {
/* 11 */   private int numParticles = 10;
/*    */   private float x;
/*    */   
/*    */   public EmptyStanceEffect(float x, float y) {
/* 15 */     this.duration = 0.0F;
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/*    */   }
/*    */   private float y;
/*    */   
/*    */   public void update() {
/* 22 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 24 */     if (this.duration < 0.0F) {
/* 25 */       if (this.numParticles == 10) {
/* 26 */         AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SKY, true));
/*    */       }
/* 28 */       AbstractDungeon.effectsQueue.add(new EmptyStanceParticleEffect(this.x, this.y));
/* 29 */       AbstractDungeon.effectsQueue.add(new EmptyStanceParticleEffect(this.x, this.y));
/* 30 */       AbstractDungeon.effectsQueue.add(new EmptyStanceParticleEffect(this.x, this.y));
/* 31 */       this.numParticles--;
/* 32 */       if (this.numParticles <= 0)
/* 33 */         this.isDone = true; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\EmptyStanceEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */