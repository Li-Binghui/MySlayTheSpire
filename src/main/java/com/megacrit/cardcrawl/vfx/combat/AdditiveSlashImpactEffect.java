/*    */ package com.megacrit.cardcrawl.vfx.combat;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class AdditiveSlashImpactEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public AdditiveSlashImpactEffect(float x, float y, Color color) {
/* 19 */     if (img == null) {
/* 20 */       img = ImageMaster.vfxAtlas.findRegion("ui/impactLineThick");
/*    */     }
/* 22 */     this.x = x - img.packedWidth / 2.0F;
/* 23 */     this.y = y - img.packedHeight / 2.0F;
/* 24 */     this.color = color;
/* 25 */     this.duration = 0.4F;
/* 26 */     this.scale = 0.01F;
/* 27 */     this.targetScale = MathUtils.random(3.0F, 5.0F);
/* 28 */     this.rotation = MathUtils.random(360.0F);
/*    */   }
/*    */   private float targetScale; private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public void update() {
/* 33 */     if (this.duration > 0.2F) {
/* 34 */       this.color.a = Interpolation.fade.apply(0.0F, 0.8F, (this.duration - 0.2F) * 5.0F);
/* 35 */       this.scale = Interpolation.fade.apply(0.01F, this.targetScale, (this.duration - 0.2F) * 5.0F) * Settings.scale;
/*    */     } else {
/* 37 */       this.color.a = Interpolation.fade.apply(0.0F, 0.8F, this.duration * 5.0F);
/* 38 */       this.scale = Interpolation.fade.apply(0.01F, this.targetScale, this.duration * 5.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 49 */     sb.setColor(this.color);
/* 50 */     sb.setBlendFunction(770, 1);
/* 51 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale / 3.0F, this.scale, this.rotation);
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
/* 62 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale / 6.0F, this.scale * 0.5F, this.rotation + 90.0F);
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
/* 73 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\AdditiveSlashImpactEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */