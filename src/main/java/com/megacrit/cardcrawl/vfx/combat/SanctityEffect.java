/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SanctityEffect extends AbstractGameEffect {
/*    */   private float x;
/* 12 */   private int count = 10; private float y; private float vfxTimer;
/*    */   
/*    */   public SanctityEffect(float newX, float newY) {
/* 15 */     this.x = newX;
/* 16 */     this.y = newY;
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 21 */     if (this.vfxTimer < 0.0F) {
/* 22 */       this.count--;
/* 23 */       this.vfxTimer = MathUtils.random(0.0F, 0.02F);
/*    */       
/* 25 */       for (int i = 0; i < 3; i++) {
/* 26 */         AbstractDungeon.effectsQueue.add(new LightRayFlyOutEffect(this.x, this.y, new Color(1.0F, 0.9F, 0.7F, 0.0F)));
/*    */       }
/*    */     } 
/*    */     
/* 30 */     if (this.count <= 0)
/* 31 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\SanctityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */