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
/*    */ public class BossChestShineEffect extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/*    */   private float y;
/* 16 */   private TextureAtlas.AtlasRegion img = ImageMaster.ROOM_SHINE_2; private float vY; private float alpha; private float targetScale;
/*    */   
/*    */   public BossChestShineEffect(float x, float y) {
/* 19 */     this.effectDuration = MathUtils.random(1.0F, 2.0F);
/* 20 */     this.duration = this.effectDuration;
/* 21 */     this.startingDuration = this.effectDuration;
/* 22 */     this.x = x;
/* 23 */     this.y = y;
/* 24 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 25 */     this.alpha = MathUtils.random(0.7F, 1.0F);
/*    */     
/* 27 */     this.color = new Color(1.0F, MathUtils.random(0.4F, 0.9F), 1.0F, this.alpha);
/* 28 */     this.scale = 0.01F;
/* 29 */     this.targetScale = MathUtils.random(0.7F, 1.3F);
/* 30 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*    */   }
/*    */   
/*    */   public BossChestShineEffect() {
/* 34 */     this.effectDuration = MathUtils.random(1.0F, 3.0F);
/* 35 */     this.duration = this.effectDuration;
/* 36 */     this.startingDuration = this.effectDuration;
/* 37 */     this.x = Settings.WIDTH / 2.0F + MathUtils.random(-450.0F, 450.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 38 */     this.y = Settings.HEIGHT / 2.0F + MathUtils.random(-200.0F, 250.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/* 39 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 40 */     this.alpha = MathUtils.random(0.7F, 1.0F);
/*    */     
/* 42 */     this.color = new Color(1.0F, MathUtils.random(0.4F, 0.9F), 1.0F, this.alpha);
/* 43 */     this.scale = 0.01F;
/* 44 */     this.targetScale = MathUtils.random(0.5F, 1.3F);
/* 45 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 49 */     if (this.vY != 0.0F) {
/* 50 */       this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 51 */       MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 52 */       if (this.vY < 0.5F) {
/* 53 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 58 */     float t = (this.effectDuration - this.duration) * 2.0F;
/* 59 */     if (t > 1.0F) {
/* 60 */       t = 1.0F;
/*    */     }
/* 62 */     float tmp = Interpolation.bounceOut.apply(0.01F, this.targetScale, t);
/* 63 */     this.scale = tmp * tmp * Settings.scale;
/*    */     
/* 65 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 66 */     if (this.duration < 0.0F) {
/* 67 */       this.isDone = true;
/* 68 */     } else if (this.duration < this.effectDuration / 2.0F) {
/* 69 */       this.color.a = Interpolation.exp5In.apply(0.0F, this.alpha, this.duration / this.effectDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 75 */     sb.setColor(this.color);
/* 76 */     sb.setBlendFunction(770, 1);
/* 77 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 85 */         MathUtils.random(0.9F, 1.1F), this.scale * 
/* 86 */         MathUtils.random(0.7F, 1.3F), this.rotation);
/*    */     
/* 88 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\BossChestShineEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */