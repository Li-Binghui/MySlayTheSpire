/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ShieldParticleEffect extends AbstractGameEffect {
/*    */   private static final int RAW_W = 64;
/*    */   private float x;
/*    */   private float y;
/* 14 */   private float scale = Settings.scale / 2.0F;
/*    */   
/*    */   public ShieldParticleEffect(float x, float y) {
/* 17 */     this.duration = 2.0F;
/*    */     
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.renderBehind = true;
/* 22 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 27 */     this.scale += Gdx.graphics.getDeltaTime() * Settings.scale * 1.1F;
/*    */ 
/*    */     
/* 30 */     if (this.duration > 1.0F) {
/* 31 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, 1.0F - this.duration - 1.0F);
/*    */     } else {
/* 33 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, 1.0F - this.duration);
/*    */     } 
/*    */     
/* 36 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 37 */     if (this.duration < 0.0F) {
/* 38 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 44 */     sb.setBlendFunction(770, 1);
/* 45 */     sb.setColor(this.color);
/* 46 */     sb.draw(ImageMaster.INTENT_DEFEND, this.x - 32.0F, this.y - 32.0F, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
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
/* 63 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ShieldParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */