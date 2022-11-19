/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class WebParticleEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public WebParticleEffect(float x, float y) {
/* 16 */     this.x = x - 32.0F;
/* 17 */     this.y = y - 32.0F;
/* 18 */     this.startingDuration = 1.0F;
/* 19 */     this.duration = this.startingDuration;
/*    */     
/* 21 */     this.scale = 0.01F;
/* 22 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 23 */     this.renderBehind = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 27 */     this.duration -= Gdx.graphics.getDeltaTime();
/*    */     
/* 29 */     if (this.duration > this.startingDuration / 2.0F) {
/* 30 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 32 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 35 */     this.scale = Interpolation.elasticIn.apply(2.5F, 0.01F, this.duration / this.startingDuration) * Settings.scale;
/*    */     
/* 37 */     if (this.duration < 0.0F) {
/* 38 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 44 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.color.a));
/* 45 */     sb.setBlendFunction(770, 1);
/* 46 */     sb.draw(ImageMaster.WEB_VFX, this.x, this.y, 32.0F, 32.0F, 64.0F, 64.0F, this.scale, this.scale, 0.0F, 0, 0, 64, 64, false, false);
/* 47 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\WebParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */