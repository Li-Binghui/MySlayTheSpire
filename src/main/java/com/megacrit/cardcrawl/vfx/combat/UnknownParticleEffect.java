/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class UnknownParticleEffect extends AbstractGameEffect {
/*    */   private Texture img;
/*    */   private static final int RAW_W = 128;
/*    */   private static final float DURATION = 1.5F;
/* 17 */   private static int renderNum = 0;
/*    */   private float x;
/*    */   private float y;
/*    */   private float scale;
/*    */   private float targetScale;
/*    */   
/*    */   public UnknownParticleEffect(float x, float y) {
/* 24 */     if (renderNum == 0) {
/* 25 */       this.targetScale = Settings.scale * 0.8F;
/* 26 */       this.rotation = 24.0F;
/* 27 */       this.x = x - 24.0F * Settings.scale;
/* 28 */       this.y = y - MathUtils.random(6.0F, 10.0F) * Settings.scale;
/* 29 */       if (MathUtils.randomBoolean()) {
/* 30 */         this.color = Color.GOLDENROD.cpy();
/*    */       } else {
/* 32 */         this.color = Color.GOLD.cpy();
/*    */       } 
/* 34 */       this.renderBehind = true;
/* 35 */     } else if (renderNum == 1) {
/* 36 */       this.targetScale = Settings.scale * 1.2F;
/* 37 */       this.rotation = 0.0F;
/* 38 */       this.x = x;
/* 39 */       this.y = y;
/* 40 */       this.color = Color.WHITE.cpy();
/* 41 */       this.renderBehind = false;
/*    */     } else {
/* 43 */       this.targetScale = Settings.scale * 0.8F;
/* 44 */       this.rotation = -24.0F;
/* 45 */       this.x = x + 24.0F * Settings.scale;
/* 46 */       this.y = y - MathUtils.random(6.0F, 10.0F) * Settings.scale;
/* 47 */       if (MathUtils.randomBoolean()) {
/* 48 */         this.color = Color.GOLDENROD.cpy();
/*    */       } else {
/* 50 */         this.color = Color.GOLD.cpy();
/*    */       } 
/* 52 */       this.renderBehind = true;
/*    */     } 
/* 54 */     this.scale = 0.01F;
/*    */     
/* 56 */     renderNum++;
/* 57 */     if (renderNum > 2) {
/* 58 */       renderNum = 0;
/*    */     }
/*    */     
/* 61 */     this.img = ImageMaster.INTENT_UNKNOWN_L;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 66 */     if (this.duration > 0.5F) {
/* 67 */       this.scale = Interpolation.elasticOut.apply(0.01F, this.targetScale, 1.5F - this.duration);
/*    */     }
/*    */     
/* 70 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 71 */     if (this.duration < 0.0F) {
/* 72 */       this.isDone = true;
/* 73 */     } else if (this.duration < 0.5F) {
/* 74 */       this.color.a = this.duration * 2.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 80 */     sb.setColor(this.color);
/* 81 */     sb.draw(this.img, this.x - 64.0F, this.y - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, this.scale, this.scale, this.rotation, 0, 0, 128, 128, false, false);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\UnknownParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */