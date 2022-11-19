/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
/*    */ 
/*    */ public class GhostIgniteEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final int COUNT = 25;
/*    */   
/*    */   public GhostIgniteEffect(float x, float y) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/*    */   }
/*    */   private float x; private float y;
/*    */   public void update() {
/* 19 */     for (int i = 0; i < 25; i++) {
/* 20 */       AbstractDungeon.effectsQueue.add(new FireBurstParticleEffect(this.x, this.y));
/* 21 */       AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.x, this.y, Color.CHARTREUSE));
/*    */     } 
/* 23 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\GhostIgniteEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */