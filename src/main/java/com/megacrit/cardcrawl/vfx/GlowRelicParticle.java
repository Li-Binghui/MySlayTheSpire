/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class GlowRelicParticle
/*    */   extends AbstractGameEffect {
/*    */   private static final float DURATION = 3.0F;
/* 13 */   private float scale = 0.01F;
/*    */   
/*    */   private static final int IMG_W = 128;
/*    */   private Texture img;
/*    */   
/*    */   public GlowRelicParticle(Texture img, float x, float y, float angle) {
/* 19 */     this.duration = 3.0F;
/* 20 */     this.img = img;
/* 21 */     this.x = x;
/* 22 */     this.y = y;
/* 23 */     this.rotation = angle;
/* 24 */     this.color = Color.WHITE.cpy();
/*    */   }
/*    */   private float x; private float y;
/*    */   
/*    */   public void update() {
/* 29 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 30 */     this.scale = Interpolation.fade.apply(Settings.scale, 2.0F * Settings.scale, 1.0F - this.duration / 3.0F);
/* 31 */     this.color.a = Interpolation.fade.apply(1.0F, 0.0F, 1.0F - this.duration / 3.0F) / 2.0F;
/*    */     
/* 33 */     if (this.duration < 0.0F) {
/* 34 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 40 */     sb.setBlendFunction(770, 1);
/* 41 */     sb.setColor(this.color);
/* 42 */     sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GlowRelicParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */