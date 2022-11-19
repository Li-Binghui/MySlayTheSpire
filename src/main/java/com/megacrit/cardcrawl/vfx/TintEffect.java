/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ 
/*    */ public class TintEffect {
/*  7 */   public Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  8 */   private Color targetColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/*  9 */   private float lerpSpeed = 3.0F;
/*    */   
/*    */   public void changeColor(Color targetColor, float lerpSpeed) {
/* 12 */     this.targetColor = targetColor;
/* 13 */     this.lerpSpeed = lerpSpeed;
/*    */   }
/*    */   
/*    */   public void changeColor(Color targetColor) {
/* 17 */     changeColor(targetColor, 3.0F);
/*    */   }
/*    */   
/*    */   public void fadeOut() {
/* 21 */     this.targetColor.set(this.color.r, this.color.g, this.color.b, 0.0F);
/* 22 */     this.lerpSpeed = 3.0F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 26 */     this.color.lerp(this.targetColor, Gdx.graphics.getDeltaTime() * this.lerpSpeed);
/*    */   }
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\TintEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */