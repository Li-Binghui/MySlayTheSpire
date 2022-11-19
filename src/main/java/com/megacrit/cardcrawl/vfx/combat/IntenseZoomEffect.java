/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */ 
/*    */ public class IntenseZoomEffect
/*    */   extends AbstractGameEffect {
/*    */   private boolean isBlack;
/*    */   private float x;
/*    */   
/*    */   public IntenseZoomEffect(float x, float y, boolean isBlack) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.isBlack = isBlack;
/*    */   }
/*    */   private float y; private static final int AMT = 10;
/*    */   public void update() {
/* 22 */     if (this.isBlack) {
/* 23 */       AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.BLACK, this.isBlack));
/*    */     } else {
/* 25 */       AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Settings.GOLD_COLOR, this.isBlack));
/*    */     } 
/* 27 */     for (int i = 0; i < 10; i++) {
/* 28 */       AbstractDungeon.effectsQueue.add(new IntenseZoomParticle(this.x, this.y, this.isBlack));
/*    */     }
/* 30 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\IntenseZoomEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */