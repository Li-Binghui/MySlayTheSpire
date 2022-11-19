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
/*    */ public class GenericSmokeEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   
/*    */   public GenericSmokeEffect(float x, float y) {
/* 18 */     this.color = Color.WHITE.cpy();
/* 19 */     this.color.r = MathUtils.random(0.4F, 0.6F);
/* 20 */     this.color.g = this.color.r - 0.05F;
/* 21 */     this.color.b = this.color.r - 0.1F;
/* 22 */     this.color.a = 0.0F;
/* 23 */     this.renderBehind = false;
/*    */     
/* 25 */     if (MathUtils.randomBoolean()) {
/* 26 */       this.img = ImageMaster.EXHAUST_L;
/* 27 */       this.duration = MathUtils.random(0.9F, 1.2F);
/* 28 */       this.targetScale = MathUtils.random(0.9F, 1.3F);
/*    */     } else {
/* 30 */       this.img = ImageMaster.EXHAUST_S;
/* 31 */       this.duration = MathUtils.random(0.6F, 1.4F);
/* 32 */       this.targetScale = MathUtils.random(0.7F, 1.0F);
/*    */     } 
/*    */     
/* 35 */     this.startDur = this.duration;
/*    */     
/* 37 */     this.x = x - this.img.packedWidth / 2.0F;
/* 38 */     this.y = y - this.img.packedHeight / 2.0F;
/* 39 */     this.scale = 0.01F;
/* 40 */     this.rotation = MathUtils.random(360.0F);
/* 41 */     this.aV = MathUtils.random(-250.0F, 250.0F);
/* 42 */     this.vY = MathUtils.random(1.0F * Settings.scale, 5.0F * Settings.scale);
/*    */   }
/*    */   private float aV; private TextureAtlas.AtlasRegion img; private float startDur; private float targetScale;
/*    */   public void update() {
/* 46 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 47 */     if (this.duration < 0.0F) {
/* 48 */       this.isDone = true;
/*    */     }
/* 50 */     this.x += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 51 */     this.y += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 52 */     this.y += this.vY;
/* 53 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 54 */     this.scale = Interpolation.swing.apply(0.01F, this.targetScale, 1.0F - this.duration / this.startDur);
/*    */ 
/*    */     
/* 57 */     if (this.duration < this.startDur / 2.0F) {
/* 58 */       this.color.a = this.duration * 3.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\GenericSmokeEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */