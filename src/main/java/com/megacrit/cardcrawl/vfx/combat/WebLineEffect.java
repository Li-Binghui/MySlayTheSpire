/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class WebLineEffect extends AbstractGameEffect {
/*    */   float x;
/*    */   float y;
/*    */   
/*    */   public WebLineEffect(float x, float y, boolean facingLeft) {
/* 17 */     this.x = x + MathUtils.random(-20.0F, 20.0F) * Settings.scale;
/* 18 */     this.y = y - 128.0F + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
/* 19 */     this.startingDuration = 1.0F;
/* 20 */     this.duration = this.startingDuration;
/* 21 */     this.rotation = MathUtils.random(185.0F, 170.0F);
/*    */     
/* 23 */     if (!facingLeft) {
/* 24 */       this.rotation += 180.0F;
/*    */     }
/*    */     
/* 27 */     this.scale = MathUtils.random(0.8F, 1.0F) * Settings.scale;
/* 28 */     float g = MathUtils.random(0.6F, 0.9F);
/* 29 */     this.color = new Color(g, g, g + 0.1F, 0.0F);
/* 30 */     this.renderBehind = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 34 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 36 */     if (this.duration > this.startingDuration / 2.0F) {
/* 37 */       this.color.a = Interpolation.fade.apply(0.8F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 39 */       this.color.a = Interpolation.pow5Out.apply(0.01F, 0.8F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 49 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.color.a));
/* 50 */     sb.setBlendFunction(770, 1);
/* 51 */     sb.draw(ImageMaster.HORIZONTAL_LINE, this.x, this.y, 0.0F, 128.0F, 256.0F, 256.0F, this.scale * 2.0F * (
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 59 */         MathUtils.cos(this.duration * 16.0F) / 4.0F + 1.5F), this.scale, this.rotation, 0, 0, 256, 256, false, false);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WebLineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */