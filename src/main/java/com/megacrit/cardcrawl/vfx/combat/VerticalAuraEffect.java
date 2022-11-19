/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class VerticalAuraEffect
/*    */   extends AbstractGameEffect {
/*    */   private static final float NUM_PARTICLES = 20.0F;
/*    */   
/*    */   public VerticalAuraEffect(Color c, float x, float y) {
/* 13 */     this.color = c;
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/*    */   }
/*    */   private float x; private float y;
/*    */   
/*    */   public void update() {
/* 20 */     for (int i = 0; i < 20.0F; i++) {
/* 21 */       AbstractDungeon.effectsQueue.add(new VerticalAuraParticle(this.color, this.x, this.y));
/*    */     }
/* 23 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\VerticalAuraEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */