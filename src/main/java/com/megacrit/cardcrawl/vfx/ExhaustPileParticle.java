/*    */ package com.megacrit.cardcrawl.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ 
/*    */ public class ExhaustPileParticle extends AbstractGameEffect {
/*    */   private float x;
/* 14 */   private float scale = 0.01F; private float y; private float vX; private float targetScale;
/*    */   private static TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public ExhaustPileParticle(float x, float y) {
/* 18 */     if (img == null) {
/* 19 */       img = ImageMaster.EXHAUST_L;
/*    */     }
/*    */     
/* 22 */     this.targetScale = MathUtils.random(0.5F, 0.7F) * Settings.scale;
/* 23 */     this.color = new Color();
/* 24 */     this.color.a = 0.0F;
/* 25 */     this.color.g = MathUtils.random(0.2F, 0.4F);
/* 26 */     this.color.r = this.color.g + 0.1F;
/* 27 */     this.color.b = this.color.r + 0.1F;
/*    */     
/* 29 */     this.x = x - img.packedWidth / 2.0F;
/* 30 */     this.y = y - img.packedHeight / 2.0F;
/* 31 */     this.rotation = MathUtils.random(360.0F);
/* 32 */     this.startingDuration = 2.0F;
/* 33 */     this.duration = this.startingDuration;
/*    */   }
/*    */   
/*    */   public void update() {
/* 37 */     this.scale = Interpolation.bounceIn.apply(this.targetScale, 0.1F, this.duration / this.startingDuration);
/* 38 */     this.rotation += this.vX * this.startingDuration * Gdx.graphics.getDeltaTime();
/* 39 */     this.color.a = this.duration / this.startingDuration;
/*    */     
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */     if (this.duration < 0.0F) {
/* 43 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     sb.setColor(this.color);
/* 49 */     sb.draw((TextureRegion)img, this.x, this.y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\ExhaustPileParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */